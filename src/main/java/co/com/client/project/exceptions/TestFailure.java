package co.com.client.project.exceptions;

import java.util.Map;
import java.util.StringJoiner;

/**
 * Excepción de fallo de prueba orientada a Screenplay.
 * Extiende AssertionError para integrarse con Serenity/JUnit.
 */
public final class TestFailure extends AssertionError {

    private static final long serialVersionUID = 1L;

    // Constructores
    public TestFailure() { super(); }

    public TestFailure(String message) { super(message); }

    public TestFailure(Throwable cause) { super(cause); } // acepta null

    public TestFailure(String message, Throwable cause) { super(message, cause); }

    // Helpers estilo Assert.fail()
    public static void fail(String message) {
        throw new TestFailure(message);
    }

    public static void fail(String message, Throwable cause) {
        throw new TestFailure(message, cause);
    }

    public static void failf(String template, Object... args) {
        throw new TestFailure(String.format(template, args));
    }

    /** Agrega contexto clave→valor al mensaje antes de fallar. */
    public static void failWithContext(String message, Map<String, ?> context) {
        if (context != null && !context.isEmpty()) {
            StringJoiner joiner = new StringJoiner(", ", " {", "}");
            context.forEach((k, v) -> joiner.add(k + "=" + v));
            message = message + joiner;
        }
        throw new TestFailure(message);
    }
}