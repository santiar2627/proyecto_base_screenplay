package co.com.client.project.interactions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.selectactions.SelectByIndexFromTarget;
import net.serenitybdd.screenplay.actions.selectactions.SelectByVisibleTextFromTarget;
import net.serenitybdd.screenplay.actions.selectactions.SelectByValueFromTarget;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public final class Select {

    private Select() {}

    /** Selecciona por texto visible del <option>. */
    public static Performable byVisibleText(Target target, String text) {
        return instrumented(SelectByVisibleTextFromTarget.class, target, text);
    }

    /** Selecciona por atributo 'value' del <option>. */
    public static Performable byValue(Target target, String value) {
        return instrumented(SelectByValueFromTarget.class, target, value);
    }

    /** Selecciona por índice (0-based). */
    public static Performable byIndex(Target target, int index) {
        return instrumented(SelectByIndexFromTarget.class, target, index);
    }
}
