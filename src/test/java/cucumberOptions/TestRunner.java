package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",
				 glue="stepDefinition",
				 dryRun=false,
				 plugin="json:target/jsonReports/cucumberReport.json"
				 //tags="@add"
				 )
public class TestRunner {

}
