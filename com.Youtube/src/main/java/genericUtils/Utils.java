package genericUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
	FileInputStream fis;

	public String ToFetchDataFromPropertyFile(String key) throws IOException {
		fis = new FileInputStream(".\\src\\main\\resources\\Data.properties");

		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}

}
