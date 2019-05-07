package driverFactory;

import java.util.Properties;

import basePage.BasePage;
import cucumber.api.Scenario;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

public class BaseTest1 extends CreateDriver {


	public static Logger LOGGER = LoggerFactory.getLogger(BaseTest1.class);
	public static Properties ppBrowser;
	public static Properties ppEnv;
	public static String Browser;
	public static String Env;


	@Before
	public void setup() throws Exception {
		WebDriver driver = CreateDriver.getInstance().getDriver();
	//	Launch.launch();

	}

	@After
	public void tearDownAndScreenshotOnFailure(Scenario scenario) {
		WebDriver driver = CreateDriver.getInstance().getDriver();
		try {
			if(driver != null && scenario.isFailed()) {
				//scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
				BasePage.captureScreenshot();
				driver.manage().deleteAllCookies();
				driver.quit();
				driver = null;
			}
			if(driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
				driver = null;
			}
		} catch (Exception e) {
			System.out.println("Methods failed: tearDownAndScreenshotOnFailure, Exception: " + e.getMessage());
		}
	}
}


