package utility;

import java.io.FileReader;
import java.util.Properties;
import static project_constants.Constants.*;

public class PropertiesReader {

	private Properties prop;
	/*
	 * This method is used to return value from the properties file "config.prperties -> scr/main/resources"
	 * @param: key
	 */
	public String getDataFromPropertiesFile(String key) {
		
		FileReader fileReader;
		try {
			fileReader = new FileReader(PROJECT_DIRECTORY + PROPERTY_FILE_LOCATION);
			prop = new Properties();  
			prop.load(fileReader);  
		}catch (Exception e) {
			e.printStackTrace();
		}  
		return prop.getProperty(key);		
	}
}
