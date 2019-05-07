package TestngTestRunner;

import Utils.ExtentReports.ExtentTestNGIReporterListener;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/"}, glue = {
        "stepDefinitions/" }, monochrome = true, tags = {},
        plugin = { "pretty", "html:target/cucumber", "json:target/cucumber.json",
                "com.cucumber.listener.ExtentCucumberFormatter:output/report.html" })

public class TestNGCucumberMainRunner  extends AbstractTestNGCucumberTests {


    @AfterClass
    public static void writeExtentReport() throws IOException {
        Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "src/test/java/Utils/ExtentReports/extent-config.xml"));
        ExtentTestNGIReporterListener.copyLatestExtentReport();
    }
}