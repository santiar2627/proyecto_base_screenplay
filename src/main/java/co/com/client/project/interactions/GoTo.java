package co.com.client.project.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Switch;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class GoTo {

    private static String parentWindow;
    private static boolean flag = true;

    private GoTo() {
    }

    public static Performable lastWindow(Actor actor) {
        WebDriver webDriver = BrowseTheWeb.as(actor).getDriver();
        String lastWindow = "";
        Set<String> windowHandles = webDriver.getWindowHandles();

        for (String window : windowHandles) {
            if (flag) {
                parentWindow = window;
                flag = false;
            }
            lastWindow = window;
        }
        return Switch.toWindow(lastWindow);
    }

    public static Performable firstWindow() {
        return Switch.toWindow(parentWindow);
    }
}
