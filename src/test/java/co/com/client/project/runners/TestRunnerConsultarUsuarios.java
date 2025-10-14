package co.com.client.project.runners;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/consultar_usuarios.feature",
        glue = {"co.com.client.project.stepdefinitions",
                "co.com.client.project.hooks"},
        tags = "@api",
        plugin = {"pretty"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true
)
public class TestRunnerConsultarUsuarios {
}