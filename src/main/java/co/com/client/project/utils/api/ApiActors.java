package co.com.client.project.utils.api;

import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;

/**
 * Crea actores con la habilidad de consumir APIs (Screenplay + Serenity Rest).
 * Prioridad de configuración de la base URL:
 *  1) -Dapi.url=...
 *  2) serenity.conf -> api.url
 *  3) Fallback: https://reqres.in
 */
public class ApiActors {

    private static String baseUrlFromConfig() {
        String sys = System.getProperty("api.url");
        if (sys != null && !sys.isBlank()) return sys;

        EnvironmentVariables env = ConfiguredEnvironment.getEnvironmentVariables();
        String conf = EnvironmentSpecificConfiguration.from(env).getProperty("api.url");
        if (conf != null && !conf.isBlank()) return conf;

        return "https://reqres.in";
    }

    /** Accesor público para usar en StepDefinitions */
    public static String baseUrl() {
        return baseUrlFromConfig();
    }

    public static Actor usuarioApi() {
        return Actor.named("Usuario API").whoCan(CallAnApi.at(baseUrlFromConfig()));
    }

    public static Actor usuarioApi(String baseUrl) {
        return Actor.named("Usuario API").whoCan(CallAnApi.at(baseUrl));
    }
}