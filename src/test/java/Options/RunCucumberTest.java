package Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features/POST/Booking",plugin ={ "pretty", "json:target/jsonReports/cucumber-report.json" },glue= {"stepDefinitions"})
public class RunCucumberTest {
//tags= {"@DeletePlace"}  compile test verify
}
