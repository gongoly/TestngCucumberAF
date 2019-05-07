package stepDefinitions;

import basePage.BasePage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import driverFactory.CreateDriver;
import driverFactory.Global_VARS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseTest extends CreateDriver {

    public WebDriver driver;

    public BaseTest() {
        driver = CreateDriver.getInstance().getDriver();
    }

    @Before
    public void setup()
            throws Exception {
        // create driver
        CreateDriver.getInstance().setDriver(
                Global_VARS.BROWSER,
                Global_VARS.PLATFORM,
                Global_VARS.ENVIRONMENT);
    }

    @After
    public void tearDownAndScreenshotOnFailure(Scenario scenario) {
        try {
            if (driver != null && scenario.isFailed()) {
                //scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
                BasePage.captureScreenshot();
                driver.manage().deleteAllCookies();
                driver.quit();
                driver = null;
            }
            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            System.out.println("Methods failed: tearDownAndScreenshotOnFailure, Exception: " + e.getMessage());
        }
    }
}
