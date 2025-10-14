package co.com.client.project.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class Page extends PageObject {

    public static final Target WEB_ELEMENT = Target.the("Elemento web esperado")
            .locatedBy("//img[@alt='logo']");

}
