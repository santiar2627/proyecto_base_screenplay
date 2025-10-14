package co.com.client.project.tasks;


import co.com.client.project.interactions.servicios.CallService;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;


public class Create implements Task {

    private String user;
    private String resource;

    public Create(String resource, String user) {
        this.user = user;
        this.resource = resource;
    }

    public static Create call(String resource, String user){
        return new Create(resource,user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(CallService.restPost(resource,user));
    }
}
