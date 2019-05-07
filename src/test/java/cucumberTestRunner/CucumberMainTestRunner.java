package cucumberTestRunner;

import java.io.File;
import java.io.IOException;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;

import com.cucumber.listener.Reporter;

import basePage.BasePage;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)

@CucumberOptions(features = { "src/test/resources/features/" }, glue = {
		"src/test/java/stepDefinitions/" },dryRun = true, monochrome = true, tags = {}, plugin = { "pretty", "html:target/cucumber",
				"json:target/cucumber.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report.html" })

public class CucumberMainTestRunner extends AbstractTestNGCucumberTests {
	@AfterClass
	public static void writeExtentReport() throws IOException {
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "\\src\\main\\java\\Utils\\extent-config.xml"));
		BasePage.copyLatestExtentReport();
	}
	

}