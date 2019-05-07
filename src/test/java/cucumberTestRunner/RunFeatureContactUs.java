package cucumberTestRunner;

import basePage.BasePage;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.IOException;

@RunWith(Cucumber.class)

@CucumberOptions(features = { "src/test/resources/features/contactus.feature" }, glue = {
        "src/test/java/stepDefinitions/ContactUsSteps.java" },dryRun = false, monochrome = true, tags = {}, plugin = { "pretty", "html:target/cucumber",
        "json:target/cucumber.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report.html" })

public class RunFeatureContactUs extends AbstractTestNGCucumberTests {
    @AfterClass
    public static void writeExtentReport() throws IOException {
        Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "\\src\\main\\java\\Utils\\extent-config.xml"));
        BasePage.copyLatestExtentReport();
    }


}
