package driverFactory;

import java.io.File;

/**
 * @author MbembaDramé
 *
 *         Global Variable Utility Class
 *
 */
public class Global_VARS {
	// browser defaults
	public static final String BROWSER = "chrome";
	public static final String PLATFORM = "Windows 10";
	public static final String ENVIRONMENT = "local";
	public static String DEF_BROWSER = null;
	public static String DEF_PLATFORM = null;
	public static String DEF_ENVIRONMENT = null;
// suite folder defaults
	public static String SUITE_NAME = null;
	public static final String TARGET_URL = "https://www.airfrance.fr/";
	public static String propFile = "src/main/resources/selenium.properties";
	public static final String SE_PROPS = new File(propFile).getAbsolutePath();
	public static final String TEST_OUTPUT_PATH = "test-output/";
	public static final String LOGFILE_PATH = TEST_OUTPUT_PATH + "Logs/";
	public static final String REPORT_PATH = TEST_OUTPUT_PATH + "Reports/";
	public static final String REPORT_IMG_PATH = TEST_OUTPUT_PATH + "Images/";
	public static final String REPORT_CONFIG_FILE = "src/main/java/Utils/extent-config.xml";
	// suite timeout defaults
	public static final int TIMEOUT_MINUTE = 60;
	public static final int TIMEOUT_ELEMENT = 10;
}
