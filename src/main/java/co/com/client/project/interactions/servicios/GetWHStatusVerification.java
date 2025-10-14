package co.com.client.project.interactions.servicios;

import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;

import java.util.Map;

import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;

public class GetWHStatusVerification extends RestInteraction {

    private final Map<String, Object> headers;
    private final String resource;
    private final int expectedStatus;

    public GetWHStatusVerification(String resource, Map<String, Object> headers, int expectedStatus) {
        this.headers = headers;
        this.resource = resource;
        this.expectedStatus = expectedStatus;
    }

    @Step("{0} ejecuta GET #resource con headers y espera status #expectedStatus")
    @Override
    public <T extends Actor> void performAs(T actor) {
        rest().contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .headers(headers)
                .when()
                .get(as(actor).resolve(resource))
                .then()
                .statusCode(expectedStatus);
    }
}