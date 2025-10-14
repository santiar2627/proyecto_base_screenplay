package co.com.client.project.interactions.servicios;

import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;

import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;

/**
 * Ejecuta un GET contra el recurso indicado y valida el código de estado HTTP.
 * - new GetStatusVerification("/api/users")           -> espera 200 por defecto
 * - new GetStatusVerification("/api/users", 404)      -> espera el status indicado
 */
public class GetStatusVerification extends RestInteraction {

    private final String resource;
    private final int expectedStatus;

    /** Valida 200 OK por defecto */
    public GetStatusVerification(String resource) {
        this(resource, 200);
    }

    /** Valida el status esperado que pases */
    public GetStatusVerification(String resource, int expectedStatus) {
        this.resource = resource;
        this.expectedStatus = expectedStatus;
    }

    @Step("{0} ejecuta GET en #resource y espera HTTP {#expectedStatus}")
    @Override
    public <T extends Actor> void performAs(T actor) {
        rest()
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get(as(actor).resolve(resource))
                .then()
                .statusCode(expectedStatus);
    }
}