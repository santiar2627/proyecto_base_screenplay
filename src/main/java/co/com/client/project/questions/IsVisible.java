package co.com.client.project.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public final class IsVisible implements Question<Boolean> {
    private final Target target;

    private IsVisible(Target target) { this.target = target; }

    public static IsVisible of(Target target) {
        return new IsVisible(target);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return target.resolveFor(actor).isVisible();
    }
}
