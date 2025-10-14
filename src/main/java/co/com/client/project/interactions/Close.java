package co.com.client.project.interactions;

import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Close {

    private Close() {
    }

    public static Interaction currentWindow() {
        return instrumented(CloseCurrentWindow.class);
    }

    public static Interaction browser(){return instrumented(CloseBrowser.class);}
}
