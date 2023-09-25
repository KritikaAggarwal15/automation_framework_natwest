package testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber.json", "pretty", "html:target/cucumber-reports"},
        features = "src/test/resources/feature/",
        glue = "stepDefinition",
        monochrome = true
//        tags = {"@"}

)
public class TestRunner extends AbstractTestNGCucumberTests {

}
