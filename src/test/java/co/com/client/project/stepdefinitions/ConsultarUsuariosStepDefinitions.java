package co.com.client.project.stepdefinitions;

import co.com.client.project.hooks.HooksAPI;
import co.com.client.project.questions.api.LastResponseBodyField;
import co.com.client.project.questions.api.LastResponseIntAtPath;
import co.com.client.project.questions.api.LastResponseStatus;
import co.com.client.project.tasks.api.ConsultarUsuarios;
import co.com.client.project.tasks.api.ConsultarUsuariosSinApiKey;
import co.com.client.project.utils.Resources;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class ConsultarUsuariosStepDefinitions {

    private static final String ACTOR_NAME = "Usuario API";

    @Dado("que el actor de API está configurado contra la base URL")
    public void configurarActorApi() {
        // Toma la URL resuelta por el Hook; si por alguna razón es nula, usa fallback
        String baseUrl = (HooksAPI.RESOLVED_API_URL != null && !HooksAPI.RESOLVED_API_URL.isBlank())
                ? HooksAPI.RESOLVED_API_URL
                : "https://reqres.in";

        // Crea (o recupera) al actor y LO PONE EN FOCO aquí.
        OnStage.theActorCalled(ACTOR_NAME).whoCan(CallAnApi.at(baseUrl));
    }

    // -------- Escenario 401 (sin API key) --------
    @Cuando("consulta la lista de usuarios sin api key")
    public void consultaListaUsuariosSinApiKey() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ConsultarUsuariosSinApiKey.desde(Resources.USERS)
        );
    }

    // -------- Escenario 200 (éxito) --------
    @Cuando("consulta la lista de usuarios")
    public void consultaListaUsuarios() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ConsultarUsuarios.desde(Resources.USERS_PAGE)
        );
    }

    // -------- Asserts genéricos --------
    @Entonces("la respuesta HTTP debe ser {int}")
    public void laRespuestaHttpDebeSer(int esperado) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El status code", LastResponseStatus.code(), equalTo(esperado))
        );
    }

    @Y("el campo {string} debe ser {string}")
    public void elCampoDebeSer(String field, String expected) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Campo " + field, LastResponseBodyField.value(field), equalTo(expected))
        );
    }

    @Y("el campo {string} debe ser mayor o igual que {int}")
    public void elCampoDebeSerMayorOIgualQue(String jsonPath, int minimo) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Campo " + jsonPath, LastResponseIntAtPath.value(jsonPath), greaterThanOrEqualTo(minimo))
        );
    }


    @Y("el usuario con id {int} debe tener los siguientes datos")
    public void elUsuarioConIdDebeTenerLosSiguientesDatos(int id, DataTable data) {
        var expected = data.asMap(String.class, String.class);

        OnStage.theActorInTheSpotlight().should(
                // email
                seeThat("email del usuario " + id,
                        LastResponseBodyField.value("data.find { it.id == " + id + " }.email"),
                        equalTo(expected.get("email"))
                ),
                // first_name
                seeThat("first_name del usuario " + id,
                        LastResponseBodyField.value("data.find { it.id == " + id + " }.first_name"),
                        equalTo(expected.get("first_name"))
                ),
                // last_name
                seeThat("last_name del usuario " + id,
                        LastResponseBodyField.value("data.find { it.id == " + id + " }.last_name"),
                        equalTo(expected.get("last_name"))
                ),
                // avatar
                seeThat("avatar del usuario " + id,
                        LastResponseBodyField.value("data.find { it.id == " + id + " }.avatar"),
                        equalTo(expected.get("avatar"))
                )
        );
    }
}
