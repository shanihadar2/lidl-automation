package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
	
	public static String readProperty(String key) {
		// for reading the configuration.properties file
		String value = "";
		try (InputStream input = new FileInputStream("./src/data/configuration.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			value = prop.getProperty(key);
		} catch (Exception e) {
		}
		return value;
	}
}
