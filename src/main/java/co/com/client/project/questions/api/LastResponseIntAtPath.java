package co.com.client.project.questions.api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

public class LastResponseIntAtPath {
    private LastResponseIntAtPath() {}

    @Subject("el valor numérico en JSON path '{path}'")
    public static Question<Integer> value(String path) {
        return actor -> {
            Object v = SerenityRest.lastResponse().jsonPath().get(path);
            if (v == null) return 0;
            if (v instanceof Number) return ((Number) v).intValue();
            try { return Integer.parseInt(String.valueOf(v)); }
            catch (Exception e) { return 0; }
        };
    }
}
