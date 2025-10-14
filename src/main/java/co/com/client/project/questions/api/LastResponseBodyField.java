package co.com.client.project.questions.api;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.rest.SerenityRest;

public class LastResponseBodyField {
    private LastResponseBodyField() {
    }

    @Subject("el valor del campo JSON '{field}' en la última respuesta")
    public static Question<String> value(String field) {
        return actor -> SerenityRest.lastResponse().jsonPath().getString(field);
    }
}