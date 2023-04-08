package de.hhn.mi.delemma.api;

import java.net.URL;
import java.util.Map;

/**
 * Detects lemmatizer models via their {@link ModelType}.
 */
public interface ModelSearchService {

    /**
     * Retrieves meta information for all persistent NLP model instances.
     *
     * @param type The {@link ModelType} instance to find a model for.
     *
     * @return A {@link Map} instance associating file names with a related {@link URL}.
     */
    Map<String, URL> findModels(ModelType type);

}
