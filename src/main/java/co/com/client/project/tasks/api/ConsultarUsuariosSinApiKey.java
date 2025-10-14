package co.com.client.project.tasks.api;

import co.com.client.project.interactions.servicios.GetWHStatusVerification; // versión con expected
import co.com.client.project.utils.Resources;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultarUsuariosSinApiKey implements Task {

    private final Resources resource;

    public ConsultarUsuariosSinApiKey(Resources resource) {
        this.resource = resource;
    }

    public static Performable desde(Resources resource) {
        return instrumented(ConsultarUsuariosSinApiKey.class, resource);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Solo Accept; sin Authorization/x-api-key -> debe dar 401 según tu caso
        Map<String, Object> headers = Map.of("Accept", "application/json");
        int esperado = 401;

        actor.attemptsTo(
                // Usa la clase con headers + expected que agregamos antes
                new GetWHStatusVerification(resource.path(), headers, esperado)
        );
    }
}
