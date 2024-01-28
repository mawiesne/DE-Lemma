package de.hhn.mi.delemma.api;

import java.util.stream.Stream;

public enum ModelType {

    LEMMATIZER_MODEL_DEFAULT("DE-Lemma_UD-gsd-2022-maxent"),
    LEMMATIZER_MODEL_GSD("DE-Lemma_UD-gsd-2022-maxent"),
    LEMMATIZER_MODEL_HDT("DE-Lemma_UD-hdt-2022-maxent"),
    LEMMATIZER_MODEL_BUREG("DE-Lemma_Tue-BuReg-2022-maxent"),
    LEMMATIZER_MODEL_TUEBA("DE-Lemma_Tue-Wiki-2022-maxent");

    private final String textValue;

    ModelType(String textValue) {
        this.textValue = textValue;
    }

    @Override
    public String toString() {
        return textValue;
    }

    public static ModelType fromString(String textValue) {
        return Stream.of(ModelType.values())
                .filter(e -> e.textValue.equalsIgnoreCase(textValue))
                .findFirst()
                .orElse(LEMMATIZER_MODEL_DEFAULT);
    }
}
