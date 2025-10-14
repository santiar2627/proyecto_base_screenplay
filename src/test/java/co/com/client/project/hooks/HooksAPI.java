package co.com.client.project.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static co.com.client.project.utils.PropertiesManager.getValue;

public class HooksAPI {

    public static String RESOLVED_API_URL; // <- disponible para los steps

    /** Prioridad para resolver la base URL:
     *  1) -Dapi.url (línea de comandos/Gradle)
     *  2) serenity.conf / serenity.properties -> api.url
     *  3) environment.properties -> endpoint
     *  4) https://reqres.in (fallback)
     */
    private String resolveBaseUrl() {
        // 1) -Dapi.url
        String fromSys = System.getProperty("api.url");
        if (fromSys != null && !fromSys.isBlank()) return fromSys;

        // 2) serenity.conf / serenity.properties
        EnvironmentVariables env = ConfiguredEnvironment.getEnvironmentVariables();
        String fromConf = EnvironmentSpecificConfiguration.from(env).getProperty("api.url");
        if (fromConf != null && !fromConf.isBlank()) return fromConf;

        // 3) environment.properties
        String fromEnvProps = getValue("endpoint");
        if (fromEnvProps != null && !fromEnvProps.isBlank()) return fromEnvProps;

        // 4) fallback
        return "https://reqres.in";
    }

    @Before(order = 0)
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
        RESOLVED_API_URL = resolveBaseUrl();   // <- sólo resolvemos y guardamos
    }

    @After(order = 0)
    public void tearDown() {
        OnStage.drawTheCurtain();
    }
}