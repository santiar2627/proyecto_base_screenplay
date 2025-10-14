package co.com.client.project.tasks.api;

import co.com.client.project.interactions.servicios.GetWHStatusVerification; // versión con (resource, headers, expected)
import co.com.client.project.utils.Resources;
import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.thucydides.model.util.EnvironmentVariables;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultarUsuarios implements Task {

    private final Resources resource;

    public ConsultarUsuarios(Resources resource) {
        this.resource = resource;
    }

    public static Performable desde(Resources resource) {
        return instrumented(ConsultarUsuarios.class, resource);
    }

    private String resolveApiKey() {
        // 1) -Dapi.key=xxxx
        String fromSys = System.getProperty("api.key");
        if (fromSys != null && !fromSys.isBlank()) return fromSys;

        // 2) serenity.conf / serenity.properties -> api.key
        EnvironmentVariables env = ConfiguredEnvironment.getEnvironmentVariables();
        String fromConf = EnvironmentSpecificConfiguration.from(env).getProperty("api.key");
        if (fromConf != null && !fromConf.isBlank()) return fromConf;

        // 3) fallback de desarrollo
        return "demo-key";
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        // Agrega la API key para que el endpoint responda 200
        headers.put("x-api-key", resolveApiKey());

        int esperado = 200;

        actor.attemptsTo(
                new GetWHStatusVerification(resource.path(), headers, esperado)
        );
    }
}