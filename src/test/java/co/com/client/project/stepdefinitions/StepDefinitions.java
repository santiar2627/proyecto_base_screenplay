package co.com.client.project.stepdefinitions;

import co.com.client.project.questions.IsVisible;
import co.com.client.project.tasks.OpenSite;
import co.com.client.project.utils.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.model.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static co.com.client.project.hooks.Hooks.actor;
import static co.com.client.project.userinterfaces.Page.WEB_ELEMENT;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;


public class StepDefinitions {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepDefinitions.class);

    @Before
    public void beforeRun() {
        LOGGER.info(Constants.Messages.MSG_INICIA_PRUEBA);
    }

    @Given("^precondicion del caso de prueba$")
    public void precondicionDelCasoDePrueba() {
        EnvironmentVariables env = ConfiguredEnvironment.getEnvironmentVariables();

        String url = env.optionalProperty("applications.url")
                .orElse(System.getProperty("url", "http://localhost:8080"));

        // logs solicitados
        LOGGER.info(Constants.Messages.MSG_EXEC_ACCION_PAGINA, url);
        actor.attemptsTo(OpenSite.to(url));
        LOGGER.info(Constants.Messages.MSG_FIN_ACCION_PAGINA);
    }

    @When("^accion realizada por el actor en el aplicativo$")
    public void accionRealizadaPorElActorEnElAplicativo() {
        // acción mínima (si aún no tienes Tasks, deja solo logging para evitar errores)
        LOGGER.info(Constants.Messages.MSG_EXEC_ACCION, "acción placeholder");
        // Ejemplo cuando tengas una Task real:
    /*    // actor.attemptsTo(HacerAlgo.enLaPagina());*/
    }

    @Then("^respuesta esperada$")
    public void respuestaEsperada() {
        String accion = "Elemento visible";
        LOGGER.info(Constants.Messages.MSG_EXEC_ACCION, accion);
        actor.attemptsTo(
                WaitUntil.the(WEB_ELEMENT, isVisible()).forNoMoreThan(10).seconds()
        );
        actor.should(seeThat(accion, IsVisible.of(WEB_ELEMENT), is(true)));
    }

    @After
    public void afterRun() {
        LOGGER.info(Constants.Messages.MSG_FIN_PRUEBA);
    }
}
