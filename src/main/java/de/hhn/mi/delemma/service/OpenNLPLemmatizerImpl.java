package de.hhn.mi.delemma.service;

import de.hhn.mi.delemma.api.Lemmatizer;
import opennlp.tools.lemmatizer.LemmatizerME;
import opennlp.tools.lemmatizer.LemmatizerModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.List;
import java.util.Locale;

/**
 * A Lemmatizer implementation using OpenNLP library via {@link LemmatizerME}.
 */
public class OpenNLPLemmatizerImpl implements Lemmatizer {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(OpenNLPLemmatizerImpl.class);

    private final LemmatizerME lemmatizer;
    private final Locale locale;

    OpenNLPLemmatizerImpl(final LemmatizerModel lemmatizerModel, final Locale lemmatizerLocale) {
        assert lemmatizerModel != null;
        assert lemmatizerLocale != null;
        this.lemmatizer = new LemmatizerME(lemmatizerModel);
        this.locale = lemmatizerLocale;
        if(logger.isDebugEnabled()) {
            logger.debug("Lemmatizer initialization... [OK]");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String lemmatize(CharSequence concept) {
        return lemmatize(concept, "NN"); // NN is for word form 'noun'
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String lemmatize(CharSequence concept, CharSequence tag) {
        assert concept != null;
        List<List<String>> candidates = lemmatizer.lemmatize(
                List.of(concept.toString()), List.of(tag.toString()));
        return candidates.size() > 0 ? StringUtils.capitalize(candidates.get(0).get(0)) : null;
    }
}
