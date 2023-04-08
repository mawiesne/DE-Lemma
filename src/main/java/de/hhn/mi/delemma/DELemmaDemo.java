package de.hhn.mi.delemma;

import de.hhn.mi.delemma.api.Lemmatizer;
import de.hhn.mi.delemma.api.ModelSearchService;
import de.hhn.mi.delemma.api.ModelService;
import de.hhn.mi.delemma.api.ModelType;
import de.hhn.mi.delemma.service.ModelSearchServiceImpl;
import de.hhn.mi.delemma.service.OpenNLPModelServiceImpl;
import org.slf4j.Logger;

import java.util.List;
import java.util.Locale;

public class DELemmaDemo {

  private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DELemmaDemo.class);

  public static void main(String[] args) {
    ModelSearchService searchService = new ModelSearchServiceImpl();
    ModelService modelService = new OpenNLPModelServiceImpl(searchService);

    // Note: Default = LEMMATIZER_MODEL_GSD
    Lemmatizer service = modelService.loadLemmatizer(Locale.GERMAN, ModelType.LEMMATIZER_MODEL_DEFAULT);

    List<String> tokens = List.of("Viren", "Herzzusatztöne", "Vorhofflatterns", "Klinikum", "Ärzte");
    for (String t : tokens) {
      String lemma = service.lemmatize(t);

      if (lemma != null) {
        logger.info("Found lemma '{}' for noun '{}'.", lemma, t);
      } else {
        logger.warn("Got an empty result for '{}'.", t);
      }
    }
  }
}
