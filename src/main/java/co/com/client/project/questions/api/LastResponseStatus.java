package co.com.client.project.questions.api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

public class LastResponseStatus {
    private LastResponseStatus(){}

    @Subject("el status HTTP de la última respuesta")
    public static Question<Integer> code() {
        return actor -> (SerenityRest.lastResponse() != null)
                ? SerenityRest.lastResponse().statusCode()
                : -1;
    }
}