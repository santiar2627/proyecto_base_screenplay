package co.com.client.project.interactions.servicios;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import net.serenitybdd.annotations.Step;

import java.util.Map;

import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;

public class Soap extends RestInteraction {

    private String resource;
    private String bodyRequest;
    private Map<String, String> headers;

    public Soap(String resource, String bodyRequest, Map<String, String> headers) {
        this.resource = resource;
        this.bodyRequest = bodyRequest;
        this.headers = headers;
    }

    @Step("{0} executes a SOAP on the resource #resource")
    @Override
    public <T extends Actor> void performAs(T actor) {
        rest().relaxedHTTPSValidation().contentType("text/xml;charset=UTF-8")
                .headers(headers)
                .body(bodyRequest)
                .when()
                .post(as(actor).resolve(resource));
    }
}
