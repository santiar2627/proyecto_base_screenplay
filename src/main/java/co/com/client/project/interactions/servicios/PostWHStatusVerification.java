package co.com.client.project.interactions.servicios;

import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;

import java.util.Map;

import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;

public class PostWHStatusVerification extends RestInteraction {
    private final String resource;
    private final String body;
    private final Map<String, Object> headers;
    private final int expected;

    // Compatibilidad: si no pasas expected, valida 200
    public PostWHStatusVerification(String resource, String body, Map<String, Object> headers) {
        this(resource, body, headers, 200);
    }

    public PostWHStatusVerification(String resource, String body, Map<String, Object> headers, int expected) {
        this.resource = resource;
        this.body = body;
        this.headers = headers;
        this.expected = expected;
    }

    @Step("{0} ejecuta POST #resource con headers esperando HTTP {#expected}")
    @Override
    public <T extends Actor> void performAs(T actor) {
        rest().contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .headers(headers)
                .body(body)
                .when()
                .post(as(actor).resolve(resource))
                .then().statusCode(expected);
    }
}
