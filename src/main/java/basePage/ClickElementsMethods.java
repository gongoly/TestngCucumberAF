package basePage;

import driverFactory.CreateDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;


public class ClickElementsMethods extends CreateDriver implements BaseTest1 {
    public WebDriver driver;
    public WebDriverWait wait;

    public ClickElementsMethods() {
        driver = CreateDriver.getInstance().getDriver();
        wait = new WebDriverWait(driver, 30);
    }

    //SelectElementByType eleType= new SelectElementByType();
    private WebElement element = null;

    /**
     * Method to click on an element
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void click1(String accessType, String accessName) {
        element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
        element.click();
    }

    /**
     * Method to forcefully click on an element
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void clickForcefully(String accessType, String accessName) {
        element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
        JavascriptExecutor executor = (JavascriptExecutor) CreateDriver.getInstance().getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * Method to Double click on an element
     *
     * @param accessType   : String : Locator type (id, name, class, xpath, css)
     * @param accessValue : String : Locator value
     */
    public void doubleClick(String accessType, String accessValue) {
        element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessValue)));

        Actions action = new Actions(CreateDriver.getInstance().getDriver());
        action.moveToElement(element).doubleClick().perform();
    }


    /*
     * Waits until an element is visible and, at the same time, enabled.
     *
     */
    public void waitAndClickElement(By element) throws InterruptedException, IOException {
        boolean clicked = false;
        int attempts = 0;
        while (!clicked && attempts < 10) {

            try {
                this.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                System.out.println("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
                clicked = true;
            } catch (Exception e) {
                System.out.println("Unable to wait and click on WebElement, Exception: " + e.getMessage());
                Assert.fail(
                        "Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
            }
            attempts++;
        }
    }
    //Click Method by using JAVA Generics (You can use both By or Webelement)
    public <T> void click (T elementAttr) {
        if(elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }
    //Write Text by using JAVA Generics (You can use both By or Webelement)
    public <T> void writeText (T elementAttr, String text) {
        if(elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).sendKeys(text);
        } else {
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

    //Read Text by using JAVA Generics (You can use both By or Webelement)
    public <T> String readText (T elementAttr) {
        if(elementAttr.getClass().getName().contains("By")) {
            return driver.findElement((By) elementAttr).getText();
        } else {
            return ((WebElement) elementAttr).getText();
        }
    }

}