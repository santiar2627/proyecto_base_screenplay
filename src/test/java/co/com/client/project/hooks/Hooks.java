package co.com.client.project.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.WebDriver;


public class Hooks {

    public static Actor actor;

    @Managed(driver = "chrome")
    public static WebDriver driver;

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
        actor = Actor.named("Usuario");
        actor.can(BrowseTheWeb.with(driver));
    }

}

