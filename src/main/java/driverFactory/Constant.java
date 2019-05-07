package driverFactory;

public class Constant {
	/**Config Properties file **/
	public final static String CONFIG_PROPERTIES_DIRECTORY = "src/main/resources/browser.properties";
	public final static String EXCEL_READ_DIRECTORY = "src/main/resources/files/DataDrive.xlsx";
	
	public final static String GECKO_DRIVER_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver";
	
	public final static String CHROME_DRIVER_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver";
	
	public final static String IE_DRIVER_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/drivers/IEDriverServer";

}
