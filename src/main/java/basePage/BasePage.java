package basePage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import driverFactory.Global_VARS;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

import driverFactory.CreateDriver;

/**
 * @author Mbemba Base d'une page pour avoir les fonctionnalitées communes
 * Timeout = Le temps d'attente avant de retourner une erreur lorsque
 * l'on attend un élément
 */
public class BasePage extends CreateDriver {
    protected WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;
    private static String screenshotName;
    private static String browser;

   private WebDriver driver = CreateDriver.getInstance().getDriver();

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */


    /**********************************************************************************
     ** CLICK METHODS author Mbemba
     *
     * @throws Exception
     *
     * @throws throws Exception
     **********************************************************************************/

    // using JAVA Generics (You can use both By or Webelement)
    public <T> void waitToBeClickableWithByOrWebElement(T elementAttr) throws Exception {
        if (elementAttr.getClass().getName().contains("By")) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated((By) elementAttr)).click();


            } catch (Exception e) {
                throw new Exception("Erreur sur la page " + this.getClass().getSimpleName() + " Element non trouvé : "
                        + elementAttr.toString());
            }



            try {
                wait.until(ExpectedConditions.elementToBeClickable((By) elementAttr));

            } catch (Exception e) {
                throw new Exception("Erreur sur la page " + this.getClass().getSimpleName()
                        + " Element pr�sent, mais non clickable : " + elementAttr.toString());
            }
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void waitForElement(By locator) throws Exception {
        try {

            wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();

        } catch (Exception e) {
            throw new Exception("Erreur sur la page " + this.getClass().getSimpleName() + " Element non trouvé : "
                    + locator.toString());
        }


    }

    protected void waitToBeClickable(By locator) throws Exception {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();


        } catch (Exception e) {
            throw new Exception("Erreur sur la page " + this.getClass().getSimpleName() + " Element non trouvé : "
                    + locator.toString());
        }

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        } catch (Exception e) {
            throw new Exception("Erreur sur la page " + this.getClass().getSimpleName()
                    + " Element present, mais non visible : " + locator.toString());
        }

        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));

        } catch (Exception e) {
            throw new Exception("Erreur sur la page " + this.getClass().getSimpleName()
                    + " Element pr�sent, mais non clickable : " + locator.toString());
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void waitAndClickElementsUsingByLocator(By locator) throws Exception {
        boolean clicked = false;
        int attempts = 0;
        while (!clicked && attempts < 10) {
            try {
                this.wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
                System.out.println(
                        "Successfully clicked on the element using by locator: " + "<" + locator.toString() + ">");
                clicked = true;

            } catch (Exception e) {
                throw new Exception("Erreur sur la page " + this.getClass().getSimpleName() + " Elément non trouvé : "
                        + locator.toString());
            }

            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
                System.out.println(
                        "Successfully clicked on the element using by locator: " + "<" + locator.toString() + ">");
                clicked = true;

            } catch (Exception e) {
                throw new Exception("Erreur sur la page " + this.getClass().getSimpleName()
                        + " Element present, mais non visible : " + locator.toString());
            }
            try {
                this.wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
                System.out.println(
                        "Successfully clicked on the element using by locator: " + "<" + locator.toString() + ">");
                clicked = true;
            } catch (Exception e) {
                throw new Exception("Erreur sur la page " + this.getClass().getSimpleName()
                        + " Element présent, mais non clickable : " + locator.toString());
            }
            attempts++;
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void clickOnTextFromDropdownList(By list, String textToSearchFor) throws Exception {
        Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);
        try {
            tempWait.until(ExpectedConditions.elementToBeClickable(list)).click();
            driver.findElement((By) list).sendKeys(textToSearchFor);
            driver.findElement((By) list).sendKeys(Keys.ENTER);
            System.out.println("Successfully sent the following keys: " + textToSearchFor
                    + ", to the following WebElement: " + "<" + list.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to send the following keys: " + textToSearchFor
                    + ", to the following WebElement: " + "<" + list.toString() + ">");
            Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
        }
    }

    // Write Text by using JAVA Generics (You can use both By or Webelement)
    public <T> void clickOnTextFromDropdownListWithByOrWebElement(T list, String textToSearchFor) {

        if (list.getClass().getName().contains("By")) {
            Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);
            try {
                tempWait.until(ExpectedConditions.elementToBeClickable((By) list)).click();
                // list.sendKeys(textToSearchFor);
                driver.findElement((By) list).sendKeys(textToSearchFor);
                driver.findElement((By) list).sendKeys(Keys.ENTER);
                // list.sendKeys(Keys.ENTER);
                System.out.println("Successfully sent the following keys: " + textToSearchFor
                        + ", to the following WebElement: " + "<" + list.toString() + ">");
            } catch (Exception e) {
                System.out.println("Unable to send the following keys: " + textToSearchFor
                        + ", to the following WebElement: " + "<" + list.toString() + ">");
                Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
            }
        } else {
            Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);
            try {
                tempWait.until(ExpectedConditions.elementToBeClickable(((WebElement) list))).click();
                // list.sendKeys(textToSearchFor);
                ((WebElement) list).sendKeys(textToSearchFor);
                ((WebElement) list).sendKeys(Keys.ENTER);
                // list.sendKeys(Keys.ENTER);
                System.out.println("Successfully sent the following keys: " + textToSearchFor
                        + ", to the following WebElement: " + "<" + list.toString() + ">");
            } catch (Exception e) {
                System.out.println("Unable to send the following keys: " + textToSearchFor
                        + ", to the following WebElement: " + "<" + list.toString() + ">");
                Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
            }
        }
    }

    public void clickOnTextFromDropdownList(WebElement list, String textToSearchFor) throws Exception {
        Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);
        try {
            tempWait.until(ExpectedConditions.elementToBeClickable(list)).click();
            list.sendKeys(textToSearchFor);
            list.sendKeys(Keys.ENTER);
            System.out.println("Successfully sent the following keys: " + textToSearchFor
                    + ", to the following WebElement: " + "<" + list.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to send the following keys: " + textToSearchFor
                    + ", to the following WebElement: " + "<" + list.toString() + ">");
            Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void clickOnElementUsingCustomTimeout(WebElement locator, WebDriver driver, int timeout) {
        try {
            final WebDriverWait customWait = new WebDriverWait(driver, timeout);
            customWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
            locator.click();
            System.out.println("Successfully clicked on the WebElement, using locator: " + "<" + locator + ">"
                    + ", using a custom Timeout of: " + timeout);
        } catch (Exception e) {
            System.out.println("Unable to click on the WebElement, using locator: " + "<" + locator + ">"
                    + ", using a custom Timeout of: " + timeout);
            Assert.fail("Unable to click on the WebElement, Exception: " + e.getMessage());
        }
    }

    protected void cliquer(WebElement champ) throws Exception {
        champ.click();
    }

    // Click Method by using JAVA Generics (You can use both By or Webelement)
    public <T> void click(T elementAttr) {
        if (elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** ACTION METHODS
     **********************************************************************************/
    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void actionMoveAndClick(WebElement element) throws Exception {
        Actions ob = new Actions(driver);
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
            ob.moveToElement(element).click().build().perform();
            System.out.println("Successfully Action Moved and Clicked on the WebElement, using locator: " + "<"
                    + element.toString() + ">");
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement elementToClick = element;
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).isEnabled();
            if (elementPresent == true) {
                ob.moveToElement(elementToClick).click().build().perform();
                System.out.println(
                        "(Stale Exception) - Successfully Action Moved and Clicked on the WebElement, using locator: "
                                + "<" + element.toString() + ">");
            }
        } catch (Exception e) {
            System.out.println("Unable to Action Move and Click on the WebElement, using locator: " + "<"
                    + element.toString() + ">");
            Assert.fail("Unable to Action Move and Click on the WebElement, Exception: " + e.getMessage());
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void actionMoveAndClickByLocator(By element) throws Exception {
        Actions ob = new Actions(driver);
        try {
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
            if (elementPresent == true) {
                WebElement elementToClick = driver.findElement(element);
                ob.moveToElement(elementToClick).click().build().perform();
                System.out.println("Action moved and clicked on the following element, using By locator: " + "<"
                        + element.toString() + ">");
            }
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement elementToClick = driver.findElement(element);
            ob.moveToElement(elementToClick).click().build().perform();
            System.out
                    .println("(Stale Exception) - Action moved and clicked on the following element, using By locator: "
                            + "<" + element.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to Action Move and Click on the WebElement using by locator: " + "<"
                    + element.toString() + ">");
            Assert.fail(
                    "Unable to Action Move and Click on the WebElement using by locator, Exception: " + e.getMessage());
        }
    }

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** SEND KEYS METHODS /
     **********************************************************************************/
    /*
     * Effacer le contenu d'un champs de formulaire The clear() method is used to
     * delete the text in an input box. This method does not need a parameter.
     */
    public void clearChamp(By champ) throws Exception {
        driver.findElement(champ).clear();
    }

    public void sendKeysToWebElement(WebElement element, String textToSend) throws Exception {
        try {
            this.WaitUntilWebElementIsVisible(element);
            element.clear();
            element.sendKeys(textToSend);
            System.out.println("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<"
                    + element.toString() + ">");
        } catch (Exception e) {
     //       System.out.println("Unable to locate WebElement: " + "<" + element.toString()
            //        + "> and send the following keys: " + textToSend);
         //   Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    protected void saisirChamp(By champ, String texte) throws Exception {

        if (browser.toString().contains("Chrome")) {
            saisirChampChrome(champ, texte);
        } else if (browser.toString().contains("Firefox")) {
            saisirChampFirefox(champ, texte);
        } else if (browser.toString().contains("InternetExplorer")) {
            saisirChampIE(champ, texte);
        }

    }

    /*
     * compare the actual title of the page with the expected one and print the The
     * clear() method is used to delete the text in an input box. This method does
     * not need a parameter.
     */
    protected void saisirChampChrome(By champ, String texte) throws Exception {
        this.waitForElement(champ);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(champ)).click().perform();
        driver.findElement(champ).click();
        driver.findElement(champ).clear();
        driver.findElement(champ).sendKeys(texte);

        // captureEcran(("saisirChamp "+
        // Thread.currentThread().getStackTrace()[2].getMethodName()
        // +" "+texte).replaceAll("[^a-zA-Z0-9]+",""));
    }

    /*
     * compare the actual title of the page with the expected one and print the The
     * clear() method is used to delete the text in an input box. This method does
     * not need a parameter.
     */
    protected void saisirChampFirefox(By champ, String texte) throws Exception {

        this.waitForElement(champ);
        // Actions actions = new Actions(app);
        // actions.moveToElement(app.findElement(champ)).click().perform();
        driver.findElement(champ).click();
        driver.findElement(champ).clear();
        driver.findElement(champ).sendKeys(texte);

        // captureEcran(("saisirChamp "+
        // Thread.currentThread().getStackTrace()[2].getMethodName()
        // +" "+texte).replaceAll("[^a-zA-Z0-9]+",""));
    }

    /*
     * compare the actual title of the page with the expected one and print the The
     * clear() method is used to delete the text in an input box. This method does
     * not need a parameter.
     */
    protected void saisirChampIE(By champ, String texte) throws Exception {
        this.waitForElement(champ);
        // Actions actions = new Actions(app);
        // actions.moveToElement(app.findElement(champ)).click().perform();
        driver.findElement(champ).click();
        driver.findElement(champ).clear();
        driver.findElement(champ).sendKeys(texte);

        // captureEcran(("saisirChamp "+
        // Thread.currentThread().getStackTrace()[2].getMethodName()
        // +" "+texte).replaceAll("[^a-zA-Z0-9]+",""));
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    protected void saisirChampSansClear(By champ, String texte) throws Exception {

        if (browser.toString().contains("Chrome")) {
            saisirChampSansClearCH(champ, texte);
        } else if (browser.toString().contains("Firefox")) {
            saisirChampSansClearFF(champ, texte);
        } else if (browser.toString().contains("InternetExplorer")) {
            saisirChampSansClearIE(champ, texte);
        }

    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    protected void saisirChampSansClearFF(By champ, String texte) throws Exception {
        this.waitForElement(champ);
        driver.findElement(champ).click();
        driver.findElement(champ).sendKeys(Keys.LEFT);
        driver.findElement(champ).sendKeys(texte);
        //
        // captureEcran(("saisirChampSansClear "+
        // Thread.currentThread().getStackTrace()[2].getMethodName()
        // +" "+texte).replaceAll("[^a-zA-Z0-9]+",""));
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    protected void saisirChampSansClearIE(By champ, String texte) throws Exception {
        this.waitForElement(champ);
        driver.findElement(champ).click();
        driver.findElement(champ).sendKeys(Keys.LEFT);

        driver.findElement(champ).sendKeys(texte);

        // captureEcran(("saisirChampSansClear "+
        // Thread.currentThread().getStackTrace()[2].getMethodName()
        // +" "+texte).replaceAll("[^a-zA-Z0-9]+",""));
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    protected void saisirChampSansClearCH(By champ, String texte) throws Exception {
        this.waitForElement(champ);
        driver.findElement(champ).click();
        driver.findElement(champ).sendKeys(Keys.LEFT);
        driver.findElement(champ).sendKeys(Keys.LEFT);
        driver.findElement(champ).sendKeys(Keys.LEFT);
        driver.findElement(champ).sendKeys(texte);

        // captureEcran(("saisirChampSansClear "+
        // Thread.currentThread().getStackTrace()[2].getMethodName()
        // +" "+texte).replaceAll("[^a-zA-Z0-9]+",""));
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    protected void saisirChampAGauche(By champ) throws Exception {

        this.waitForElement(champ);
        WebElement elem = driver.findElement(champ);

        int width = elem.getSize().getWidth();

        Actions act = new Actions(driver);
        act.moveToElement(elem).moveByOffset((width / 2) - 2, 2).click().perform();

        // app.findElement(champ).sendKeys(Keys.LEFT);
        // elem.sendKeys(texte);

        // captureEcran(("saisirChampSansClear "+
        // Thread.currentThread().getStackTrace()[2].getMethodName()
        // +" "+texte).replaceAll("[^a-zA-Z0-9]+",""));
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    protected void saisirChampADroite(By champ) throws Exception {

        this.waitForElement(champ);
        WebElement elem = driver.findElement(champ);

        int width = elem.getSize().getWidth();

        Actions act = new Actions(driver);
        act.moveToElement(elem).moveByOffset((width / 2) - 2, 2).click().perform();
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    protected void saisirChampSansClick(By champ, String texte) throws Exception {

        this.waitForElement(champ);

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(champ)).click().perform();

        driver.findElement(champ).sendKeys(Keys.LEFT);
        driver.findElement(champ).sendKeys(texte);

        // captureEcran(("saisirChampSansClear "+
        // Thread.currentThread().getStackTrace()[2].getMethodName()
        // +" "+texte).replaceAll("[^a-zA-Z0-9]+",""));
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    protected void verifierTexte(By champ, String texteAVerifier) throws Exception {
        this.waitForElement(champ);
        WebElement elementAControler = driver.findElement(champ);
        Assert.assertEquals(elementAControler.getText(), texteAVerifier);
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void tabulation(By locator) throws Exception {
        driver.findElement(locator).sendKeys(Keys.TAB);

    }

    // Read Text by using JAVA Generics (You can use both By or Webelement)
    public <T> String readText(T elementAttr) {
        if (elementAttr.getClass().getName().contains("By")) {
            return driver.findElement((By) elementAttr).getText();
        } else {
            return ((WebElement) elementAttr).getText();
        }
    }

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** JS METHODS & JS SCROLL
     **********************************************************************************/
    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void scrollToElementByWebElementLocator(WebElement element) {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -400)");
            System.out.println(
                    "Succesfully scrolled to the WebElement, using locator: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            System.out.println("Unable to scroll to the WebElement, using locator: " + "<" + element.toString() + ">");
            Assert.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void scrollToElement(By element) throws Exception {
        WebElement prod = driver.findElement(element);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + (prod.getLocation().y - 165) + ")");
    }

    /*
     * using JAVA Generics (You can use both By or Webelement) Autor Mbemba result
     * as "Passed" or "Failed"
     */
    public <T> void scrollToElementWithByOrWebElement(T elementAttr) {
        if (elementAttr.getClass().getName().contains("By")) {
            WebElement prod = driver.findElement((By) elementAttr);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + (prod.getLocation().y - 165) + ")");
        } else {
            try {
                this.wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr)).isEnabled();
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementAttr);
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -400)");
                System.out.println(
                        "Succesfully scrolled to the WebElement, using locator: " + "<" + elementAttr.toString() + ">");
            } catch (Exception e) {
                System.out.println(
                        "Unable to scroll to the WebElement, using locator: " + "<" + elementAttr.toString() + ">");
                Assert.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
            }
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void ScrollPageDown() throws Exception {

        driver.navigate().to("http://www.alexa.com/topsites/countries;15/LU");

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void jsPageScroll(int numb1, int numb2) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("scroll(" + numb1 + "," + numb2 + ")");
            System.out.println("Succesfully scrolled to the correct position! using locators: " + numb1 + ", " + numb2);
        } catch (Exception e) {
            System.out
                    .println("Unable to scroll to element using locators: " + "<" + numb1 + "> " + " <" + numb2 + ">");
            Assert.fail("Unable to manually scroll to WebElement, Exception: " + e.getMessage());
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void waitAndclickElementUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            js.executeScript("arguments[0].click();", element);
            System.out
                    .println("Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement staleElement = element;
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(staleElement)).isEnabled();
            if (elementPresent == true) {
                js.executeScript("arguments[0].click();", elementPresent);
                System.out.println("(Stale Exception) Successfully JS clicked on the following WebElement: " + "<"
                        + element.toString() + ">");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Unable to JS click on the following WebElement: " + "<" + element.toString() + ">");
            Assert.fail("Unable to JS click on the WebElement, Exception: " + e.getMessage());
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void scrollPageDown() throws Exception {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        // jse.executeScript("window.scrollBy(0,250)", "");
        jse.executeScript("scroll(0, 250);");
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void scrollPageDownUntil() throws Exception {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,450)", "");
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void switcherPages() throws Exception {

        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        // driver.close();
        // driver.switchTo().window(tabs2.get(0));

    }

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** WAIT METHODS
     **********************************************************************************/
    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public boolean WaitUntilWebElementIsVisible(WebElement element) {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(element));
            System.out.println("WebElement is visible using locator: " + "<" + element.toString() + ">");
            return true;
        } catch (Exception e) {
            System.out.println("WebElement is NOT visible, using locator: " + "<" + element.toString() + ">");
            Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
            return false;
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public boolean WaitUntilWebElementIsVisibleUsingByLocator(By element) {
        try {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            System.out.println("Element is visible using By locator: " + "<" + element.toString() + ">");
            return true;
        } catch (Exception e) {
            System.out.println("WebElement is NOT visible, using By locator: " + "<" + element.toString() + ">");
            Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
            return false;
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public boolean isElementClickable(WebElement element) {
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(element));
            System.out.println("WebElement is clickable using locator: " + "<" + element.toString() + ">");
            return true;
        } catch (Exception e) {
            System.out.println("WebElement is NOT clickable using locator: " + "<" + element.toString() + ">");
            return false;
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public boolean waitUntilPreLoadElementDissapears(By element) {
        return this.wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** PAGE METHODS
     **********************************************************************************/
    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public BasePage loadUrl(String url) throws Exception {
        driver.get(url);
        return new BasePage();
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public String getCurrentURL() {
        try {
            String url = driver.getCurrentUrl();
            System.out.println("Found(Got) the following URL: " + url);
            return url;
        } catch (Exception e) {
            System.out.println("Unable to locate (Get) the current URL, Exception: " + e.getMessage());
            return e.getMessage();
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public String waitForSpecificPage(String urlToWaitFor) {
        try {
            String url = driver.getCurrentUrl();
            this.wait.until(ExpectedConditions.urlMatches(urlToWaitFor));
            System.out.println("The current URL was: " + url + ", " + "navigated and waited for the following URL: "
                    + urlToWaitFor);
            return urlToWaitFor;
        } catch (Exception e) {
            System.out.println("Exception! waiting for the URL: " + urlToWaitFor + ",  Exception: " + e.getMessage());
            return e.getMessage();
        }
    }


    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** ALERT & POPUPS METHODS
     **********************************************************************************/
    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void closePopups(By locator) throws InterruptedException {
        try {
            List<WebElement> elements = this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    element.click();
                    this.wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
                    System.out.println("The popup has been closed Successfully!");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception! - could not close the popup!, Exception: " + e.toString());
            throw (e);
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public boolean checkPopupIsVisible() {
        try {
            @SuppressWarnings("unused")
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("A popup has been found!");
            return true;
        } catch (Exception e) {
            System.err.println("Error came while waiting for the alert popup to appear. " + e.getMessage());
        }
        return false;
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void closeAlertPopupBox() throws AWTException, InterruptedException {
        try {
            Alert alert = this.wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (UnhandledAlertException f) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            System.out.println("Unable to close the popup");
            Assert.fail("Unable to close the popup, Exception: " + e.getMessage());
        }
    }

    /**********************************************************************************/
    /**********************************************************************************/

    /***
     * EXTENT REPORT
     ****************************************************************/
    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public static String returnDateStamp(String fileExtension) {
        Date d = new Date();
        String date = d.toString().replace(":", "_").replace(" ", "_") + fileExtension;
        return date;
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public static   void captureScreenshot() throws IOException, InterruptedException {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        screenshotName = returnDateStamp(".jpg");

        FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\output\\imgs\\" + screenshotName));

        Reporter.addStepLog("Taking a screenshot!");
        Reporter.addStepLog("<br>");
        Reporter.addStepLog("<a target=\"_blank\", href=" + returnScreenshotName() + "><img src="
                + returnScreenshotName() + " height=200 width=300></img></a>");
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public static String returnScreenshotName() {
        return (System.getProperty("user.dir") + "\\output\\imgs\\" + screenshotName).toString();
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

        } finally {
            is.close();
            os.close();
        }
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public static void copyLatestExtentReport() throws IOException {
        Date d = new Date();
        String date = d.toString().replace(":", "_").replace(" ", "_");
        File source = new File(System.getProperty("user.dir") + "\\output\\report.html");
        File dest = new File(System.getProperty("user.dir") + "\\output\\" + date.toString() + ".html");
        copyFileUsingStream(source, dest);
    }

    /**********************************************************************************/
    /**********************************************************************************/

    /**********************************************************************************
     ** changer De Frame, De page
     **********************************************************************************/
    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void changerFrame(By frame) throws Exception {
        this.estPresent(frame);
        driver.switchTo().frame(driver.findElement(frame));
    }

    /*
     * frameToBeAvailableAndSwitchToIt() - Waits until the given frame is already
     * available, and then automatically switches to it. result as "Passed" or
     * "Failed"
     */
    public void frameToBeAvailableAndSwitchToIt(By frame) throws Exception {
        this.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));

    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public void changerFenetrePopUp() throws Exception {
        Thread.sleep(4000);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle); // va cliquer sur la derniere windows
            // LOGGER.info("TITLE : " + app.getTitle());
        }
    }

    public void TabHandles() {

        // It will return the parent window name as a String
        String parent = driver.getWindowHandle();

        // This will return the number of windows opened by Webdriver and will return
        // Set of St//rings
        Set<String> s1 = driver.getWindowHandles();

        // Now we will iterate using Iterator
        Iterator<String> I1 = s1.iterator();

        while (I1.hasNext()) {

            String child_window = I1.next();

            // Here we will compare if parent window is not equal to child window then we
            // will close

            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);

                System.out.println(driver.switchTo().window(child_window).getTitle());
            }

        }
    }

    /**********************************************************************************
     ** recuperer un élément, une propriété, un texte
     **********************************************************************************/
    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public String recupererInnerText(By champ) throws Exception {
        this.waitForElement(champ);
        return driver.findElement(champ).getAttribute("innerText");
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public String recupererPropertyText(By champ) throws Exception {
        this.waitForElement(champ);
        return driver.findElement(champ).getAttribute("text");
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public String recupererText(By champ) throws Exception {
        this.waitForElement(champ);
        return driver.findElement(champ).getText();
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public String recupererValue(By champ) throws Exception {
        this.waitForElement(champ);
        return driver.findElement(champ).getAttribute("value");
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public Boolean estPresent(By element) throws Exception {
        List<WebElement> presence = driver.findElements(element);
        return (presence.size() > 0);
    }

    /*
     * compare the actual title of the page with the expected one and print the
     * result as "Passed" or "Failed"
     */
    public Boolean estPresentetVisible(By element) throws Exception {
        boolean cr = false;
        WebDriverWait waitcourt = new WebDriverWait(driver, 1);
        try {
          //  waitcourt.until(ExpectedConditions.presenceOfElementLocated(element));
            waitcourt.until(ExpectedConditions.visibilityOfElementLocated(element));
            cr = true;

        } catch (Exception e) {
            System.out.println("Unable to load élément: " + e.getMessage());
        } finally {
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        }
        return cr;
    }
    /**********************************************************************************/
    /**********************************************************************************/



    /**
     * waitForURL method to poll page URL
     *
     * @param url
     * @param timer
     * @throws Exception
     */
    public static void waitForURL(String url,
                                  int timer)
            throws Exception {

        WebDriver driver = CreateDriver.getInstance().getDriver();
        WebDriverWait exists = new WebDriverWait(driver, timer);

        exists.until( ExpectedConditions.refreshed(ExpectedConditions.urlContains(url)) );
    }

    /**
     * loadPage method to navigate to Target URL
     *
     * @param url
     * @param timeout
     * @throws Exception
     */
    public static void loadPage(String url, int timeout) throws Exception {
        WebDriver driver = CreateDriver.getInstance().getDriver();
        driver.navigate().to(url);

        // wait for page URL
        waitForURL(Global_VARS.TARGET_URL, timeout);
    }

}
