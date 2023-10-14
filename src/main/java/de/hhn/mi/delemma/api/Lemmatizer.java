package de.hhn.mi.delemma.api;


/**
 * Provides functionality to lemmatize tokens.
 */
public interface Lemmatizer {

    /**
     * Checks for the lemma of a given {@code concept} noun (NN).
     *
     * @param concept
     *          The token that will be lemmatized - must not be {@code null}.
     *
     * @return The best matching lemma for the {@code concept} if it exists, {@code null} otherwise.
     *
     * @throws AssertionError Thrown if the given parameters were invalid.
     */
    String lemmatize(CharSequence concept);

    /**
     * Checks for the lemma of a given {@code concept}.
     * 
     * @param concept
     *          The token that will be lemmatized - must not be {@code null}.
     * @param tag
     *          The POS-tag {@code concept} (token) is meant to be interpreted.
     * @return The best matching lemma for the {@code concept} if it exists, {@code null} otherwise.
     *
     * @throws AssertionError Thrown if the given parameters were invalid.
     */
    String lemmatize(CharSequence concept, CharSequence tag);
}
