package driverFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import Utils.ReadPropertieFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.remote.RemoteWebDriver;


public class CreateDriver {
	// local variables
		private static CreateDriver driver = null;
		private static final int IMPLICIT_TIMEOUT = 0;
		private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
		private ThreadLocal<String> sessionId = new ThreadLocal<String>();
		private ThreadLocal<String> sessionBrowser = new ThreadLocal<String>();
		private ThreadLocal<String> sessionPlatform = new ThreadLocal<String>();
		private ThreadLocal<String> sessionVersion = new ThreadLocal<String>();
		private String getEnv = null;
		private Properties props = new Properties();

	// constructor
	public CreateDriver() {
	}

	/**
	 * getInstance method to retrieve active driver instance
	 *
	 * @return CreateDriver
	 */
	public static CreateDriver getInstance() {
		if (driver == null) {
            driver = new CreateDriver();
		}

		return driver;
	}

	/**
	 * setDriver method to create driver instance
	 *
	 * @param browser
	 * @param environment
	 * @param platform
	 * @param optPreferences
	 * @throws Exception
	 */
	@SafeVarargs
	public final void setDriver(String browser,
								String platform,
								String environment,
								Map<String, Object>... optPreferences)
			throws Exception {

		DesiredCapabilities caps = null;
		String getPlatform = null;
		props.load(new FileInputStream(Global_VARS.SE_PROPS));
		switch (browser) {
			case "firefox":
				caps = DesiredCapabilities.firefox();

				FirefoxOptions ffOpts = new FirefoxOptions();
				FirefoxProfile ffProfile = new FirefoxProfile();

				ffProfile.setPreference("browser.autofocus", true);
				ffProfile.setPreference("browser.tabs.remote.autostart.2", false);

				caps.setCapability(FirefoxDriver.PROFILE, ffProfile);
				caps.setCapability("marionette", true);

				// then pass them to the local WebDriver
				if (environment.equalsIgnoreCase("local")) {
					System.setProperty("webdriver.gecko.driver", props.getProperty("gecko.driver.mac.path"));
					webDriver.set(new FirefoxDriver(ffOpts.merge(caps)));
				}

				break;
			case "chrome":
				caps = DesiredCapabilities.chrome();

				ChromeOptions chOptions = new ChromeOptions();
				chOptions.addArguments("disable-infobars");
				chOptions.addArguments("--disable-extensions");
				chOptions.addArguments("--disable-notifications");
				chOptions.addArguments("--start-maximized");
				chOptions.addArguments("--disable-web-security");
				chOptions.addArguments("--no-proxy-server");
				chOptions.addArguments("--enable-automation");
				chOptions.addArguments("--disable-save-password-bubble");
				Map<String, Object> chromePrefs = new HashMap<String, Object>();

				chromePrefs.put("credentials_enable_service", false);
				chromePrefs.put("profile.password_manager_enabled", false);

				chOptions.setExperimentalOption("prefs", chromePrefs);
				chOptions.addArguments("--disable-plugins", "--disable-extensions", "--disable-popup-blocking");

				caps.setCapability(ChromeOptions.CAPABILITY, chOptions);
				caps.setCapability("applicationCacheEnabled", false);

				if (environment.equalsIgnoreCase("local")) {
					System.setProperty("webdriver.chrome.driver", props.getProperty("chrome.driver.mac.path"));
					webDriver.set(new ChromeDriver(chOptions.merge(caps)));
				}

				break;
			case "internet explorer":
				caps = DesiredCapabilities.internetExplorer();

				InternetExplorerOptions ieOpts = new InternetExplorerOptions();

				ieOpts.requireWindowFocus();
				ieOpts.merge(caps);

				caps.setCapability("requireWindowFocus", true);

				if (environment.equalsIgnoreCase("local")) {
					System.setProperty("webdriver.ie.driver", props.getProperty("ie.driver.mac.path"));
					webDriver.set(new InternetExplorerDriver(ieOpts.merge(caps)));
				}

				break;
		}

		getEnv = environment;
		getPlatform = platform;
		sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId().toString());
		sessionBrowser.set(caps.getBrowserName());
		sessionVersion.set(caps.getVersion());
		sessionPlatform.set(getPlatform);

		System.out.println("\n*** TEST ENVIRONMENT = "
				+ getSessionBrowser().toUpperCase()
				+ "/" + getSessionPlatform().toUpperCase()
				+ "/" + getEnv.toUpperCase()
				+ "/Selenium Version=" + props.getProperty("selenium.revision")
				+ "/Session ID=" + getSessionId() + "\n");

		getDriver().manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
	}

	/**
	 * getProperty method to retrieve properties
	 *
	 * @return WebDriver
	 */
	public static Properties getProperty(String fichier) throws IOException {
		Properties properties = new Properties();
		InputStream input = null;
		input = ReadPropertieFile.class.getClassLoader().getResourceAsStream(fichier);
		properties.load(input);
		return properties;
	}

    /**
     * getDriver method to retrieve active driver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return webDriver.get();
    }

    /**
     * closeDriver method to close active driver
     */
    public void closeDriver() {
        try {
            getDriver().quit();
        } catch (Exception e) {
            // do something
        }
    }

    /**
     * getSessionId method to retrieve active id
     *
     * @return String
     * @throws Exception
     */
    public String getSessionId() throws Exception {
        return sessionId.get();
    }

    /**
     * getSessionBrowser method to retrieve active browser
     *
     * @return String
     * @throws Exception
     */
    public String getSessionBrowser() throws Exception {
        return sessionBrowser.get();
    }

    /**
     * getSessionVersion method to retrieve active version
     *
     * @return String
     * @throws Exception
     */
    public String getSessionVersion() throws Exception {
        return sessionVersion.get();
    }

    /**
     * getSessionPlatform method to retrieve active platform
     *
     * @return String
     * @throws Exception
     */
    public String getSessionPlatform() throws Exception {
        return sessionPlatform.get();
    }
	/**Method to select element 'by' type
	 * @param type : String : 'By' type
	 * @param access_name : String : Locator value
	 * @return By
	 */
	public By getelementbytype(String type, String access_name)
	{
		switch(type)
		{
			case "id" : return By.id(access_name);
			case "name" : return By.name(access_name);
			case "class" : return By.className(access_name);
			case "xpath" : return By.xpath(access_name);
			case "css" : return By.cssSelector(access_name);
			case "linkText" : return By.linkText(access_name);
			case "partialLinkText" : return By.partialLinkText(access_name);
			case "tagName" : return By.tagName(access_name);
			default : return null;

		}
	}


}
