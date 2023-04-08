package de.hhn.mi.delemma.service;

import de.hhn.mi.delemma.api.Lemmatizer;
import de.hhn.mi.delemma.api.ModelSearchService;
import de.hhn.mi.delemma.api.ModelService;
import de.hhn.mi.delemma.api.ModelType;
import opennlp.tools.lemmatizer.LemmatizerModel;
import opennlp.tools.util.InvalidFormatException;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class OpenNLPModelServiceImpl implements ModelService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OpenNLPModelServiceImpl.class);

    private final ModelSearchService nlpModelClassPathService;

    private final Map<ModelType, Lemmatizer> lemmatizers = new HashMap<>();

    public OpenNLPModelServiceImpl(final ModelSearchService nlpModelClassPathService) {
        assert nlpModelClassPathService!=null;
        this.nlpModelClassPathService = nlpModelClassPathService;
    }

    @Override
    public Lemmatizer loadLemmatizer(Locale locale) {
        return loadLemmatizer(locale, ModelType.LEMMATIZER_MODEL_DEFAULT);
    }

    @Override
    public Lemmatizer loadLemmatizer(Locale locale, ModelType type) {
        if (lemmatizers.containsKey(type)) {
            return lemmatizers.get(type);
        } else {
            boolean modelLoaded = false;
            try {
                for (Map.Entry<String, URL> entry : nlpModelClassPathService.findModels(type).entrySet()) {
                    String fileName = entry.getKey();
                    if (!fileName.toLowerCase(Locale.GERMAN).startsWith(locale.getLanguage())) {
                        continue;
                    }
                    try (InputStream wrappedStream = new BufferedInputStream(entry.getValue().openStream())) {
                        logger.info("Importing NLP model file '{}' ...", entry.getKey());
                        Lemmatizer lemmatizer = new OpenNLPLemmatizerImpl(new LemmatizerModel(wrappedStream), locale);
                        if (!lemmatizers.containsKey(type)) {
                            lemmatizers.put(type, lemmatizer);
                        }
                        logger.debug("Cached Lemmatizer instances = {}", lemmatizers.size());
                        modelLoaded = true;
                        break;

                    } catch (InvalidFormatException e) {
                        logger.warn("Skipping {} because it does not meet the NLP format conventions!", fileName, e);
                    }
                }
            } catch(IOException e) {
                throw new RuntimeException("Could not detect NLP models due to I/O issues!" + e.getLocalizedMessage(), e);
            }
            if (modelLoaded) {
                return lemmatizers.get(type);
            } else {
                throw new RuntimeException("No appropriate Lemmatizer models found in the classpath!");
            }
        }
    }
}
