package co.com.client.project.interactions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.DriverTask;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utilidades para trabajar con alertas del navegador (JS alerts/prompts/confirm).
 * Incluye esperas explícitas y acciones comunes.

 * Uso típico:
 *  actor.attemptsTo(Alert.accept()); // con timeout por defecto (5s)
 *  actor.attemptsTo(Alert.sendKeys("secret", Duration.ofSeconds(10)));
 *  String texto = actor.asksFor(Alert.text());
 */
public final class Alert {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(5);

    private Alert() {}

    // -----------------------
    // Accept
    // -----------------------
    public static Performable accept() {
        return accept(DEFAULT_TIMEOUT);
    }

    public static Performable accept(Duration timeout) {
        return new DriverTask(driver -> waitForAlert(driver, timeout).accept());
    }

    /** Acepta el alert solo si está presente; no falla si no existe. */
    public static Performable acceptIfPresent() {
        return new DriverTask(driver -> {
            try {
                driver.switchTo().alert().accept();
            } catch (NoAlertPresentException ignored) { /* noop */ }
        });
    }

    // -----------------------
    // Dismiss
    // -----------------------
    public static Performable dismiss() {
        return dismiss(DEFAULT_TIMEOUT);
    }

    public static Performable dismiss(Duration timeout) {
        return new DriverTask(driver -> waitForAlert(driver, timeout).dismiss());
    }

    /** Descarta el alert solo si está presente; no falla si no existe. */
    public static Performable dismissIfPresent() {
        return new DriverTask(driver -> {
            try {
                driver.switchTo().alert().dismiss();
            } catch (NoAlertPresentException ignored) { /* noop */ }
        });
    }

    // -----------------------
    // Send keys (prompts)
    // -----------------------
    public static Performable sendKeys(String text) {
        return sendKeys(text, DEFAULT_TIMEOUT);
    }

    public static Performable sendKeys(String text, Duration timeout) {
        return new DriverTask(driver -> waitForAlert(driver, timeout).sendKeys(text));
    }

    // -----------------------
    // Question: texto del alert
    // -----------------------
    public static Question<String> text() {
        return Question.about("texto del alert")
                .answeredBy(actor -> {
                    WebDriver driver = net.serenitybdd.core.Serenity.getDriver();
                    try {
                        return driver.switchTo().alert().getText();
                    } catch (NoAlertPresentException e) {
                        return ""; // o podríamos lanzar una excepción de negocio
                    }
                });
    }

    // -----------------------
    // Helper de espera
    // -----------------------
    private static org.openqa.selenium.Alert waitForAlert(WebDriver driver, Duration timeout) {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.alertIsPresent());
    }
}