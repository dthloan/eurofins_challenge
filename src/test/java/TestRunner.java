import _base.ApiBaseRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/features"},
        plugin = {"pretty", "html:target/cucumber-reports/html-report.html"},
        glue = {"steps"},
        tags = "@quote_creation")
public class TestRunner extends ApiBaseRunner {
}