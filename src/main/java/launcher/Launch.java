package launcher;
import java.io.IOException;
import java.util.Properties;

import basePage.BasePage;
import driverFactory.CreateDriver;
import driverFactory.Global_VARS;

public class Launch extends BasePage {

	public Launch() throws IOException {
		super();
	}
	
	public static void launch() throws Exception {
	//	Properties ppEnv = CreateDriver.getProperty("environnement.properties");
	//	driver.get(ppEnv.getProperty("pageHome"));
		BasePage.loadPage(Global_VARS.TARGET_URL,10);
	}


	

}
