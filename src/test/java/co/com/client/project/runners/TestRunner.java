package co.com.client.project.runners;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = {"src/test/resources/features/feature.feature"},
        glue = {"co.com.client.project.stepdefinitions",
                "co.com.client.project.hooks"},
        tags = "@tag")

public class TestRunner {
}
