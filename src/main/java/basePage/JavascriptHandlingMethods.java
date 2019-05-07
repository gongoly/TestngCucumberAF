package basePage;

import driverFactory.CreateDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavascriptHandlingMethods extends CreateDriver implements BaseTest1 {
    public WebDriver driver;
    public WebDriverWait wait;

    public JavascriptHandlingMethods() {
        driver = CreateDriver.getInstance().getDriver();
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Method to handle alert
     *
     * @param decision : String : Accept or dismiss alert
     */
    public void handleAlert(String decision) {
        if (decision.equals("accept"))
            driver.switchTo().alert().accept();
        else
            driver.switchTo().alert().dismiss();
    }
}
