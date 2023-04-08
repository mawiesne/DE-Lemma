package de.hhn.mi.delemma.api;

import java.util.Locale;

public interface ModelService {

    /**
     * @param locale Must not be {@code null}.
     * @return A valid {@link Lemmatizer} instance for {@link ModelType#LEMMATIZER_MODEL_DEFAULT default model}.
     *
     * @throws RuntimeException Thrown if runtime errors occurred upon load time.
     */
    Lemmatizer loadLemmatizer(Locale locale);

    /**
     * @param locale Must not be {@code null}.
     * @return A valid {@link Lemmatizer} instance for the given {@link ModelType lemmatizer model type}.
     *
     * @throws RuntimeException Thrown if runtime errors occurred upon load time.
     */
    Lemmatizer loadLemmatizer(Locale locale, ModelType type);

}
