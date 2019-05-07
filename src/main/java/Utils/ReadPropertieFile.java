package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import driverFactory.CreateDriver;

public class ReadPropertieFile extends CreateDriver {
		public static Properties getProperty(String fichier) throws IOException {
			Properties properties = new Properties();
			InputStream input = null;
			input = ReadPropertieFile.class.getClassLoader().getResourceAsStream(fichier);
			properties.load(input);
			return properties;
		}
	

}
