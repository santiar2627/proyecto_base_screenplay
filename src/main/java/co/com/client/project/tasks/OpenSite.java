package co.com.client.project.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class OpenSite implements Task {
    private final String url;
    public OpenSite(String url){ this.url = url; }
    public static Performable to(String url){ return net.serenitybdd.screenplay.Tasks.instrumented(OpenSite.class, url); }
    public <T extends Actor> void performAs(T actor){ actor.attemptsTo(Open.url(url)); }
}
