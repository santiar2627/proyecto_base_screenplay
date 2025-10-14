package co.com.client.project.interactions;


import co.com.client.project.exceptions.TestFailure;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.annotations.Step;

import java.util.Map;
import java.util.StringJoiner;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Interacciones para provocar un FAIL explícito en Screenplay.
 * Para aserciones, prioriza Ensure/Questions; esto es para abortar con mensaje claro.
 */
public final class Fail {

    private Fail() {}

    /** Falla con un mensaje simple. */
    public static Interaction withMessage(String message) {
        return instrumented(FailWithMessage.class, message);
    }

    /** Falla con mensaje + contexto clave→valor. */
    public static Interaction withContext(String message, Map<String, ?> context) {
        return instrumented(FailWithContext.class, message, context);
    }

    // ---------- Implementaciones ----------

    public static final class FailWithMessage implements Interaction {
        private final String message;

        public FailWithMessage(String message) { this.message = message; }

        @Override
        @Step("{0} provoca un FAIL: '#message'")
        public <T extends Actor> void performAs(T actor) {
            throw new TestFailure(message);
        }

        @Override
        public String toString() {
            return "FAIL: " + message;
        }
    }

    public static final class FailWithContext implements Interaction {
        private final String message;
        private final Map<String, ?> context;

        public FailWithContext(String message, Map<String, ?> context) {
            this.message = message;
            this.context = context;
        }

        @Override
        @Step("{0} provoca un FAIL: '#message' con contexto")
        public <T extends Actor> void performAs(T actor) {
            String finalMessage = message;
            if (context != null && !context.isEmpty()) {
                StringJoiner joiner = new StringJoiner(", ", " {", "}");
                context.forEach((k, v) -> joiner.add(k + "=" + v));
                finalMessage = message + joiner;
            }
            throw new TestFailure(finalMessage);
        }

        @Override
        public String toString() {
            return "FAIL: " + message;
        }
    }
}