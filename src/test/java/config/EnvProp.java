package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import general.BasicLogger;

/*
 * Get the environment dependent parameters from properties file. By defining or
 * modifying .properties files changes on environents will be reflested the code
 * withouth touching the code itself
 */

public class EnvProp {

	public static String baseUrl;

	final static Logger log = Logger.getLogger(BasicLogger.class);

	// Get the key property value from .properties file
	public EnvProp() throws IOException {
		EnvProp.baseUrl = getProb(getActiveProfileFilePath()).getProperty("baseUrl");
	}

	/*
	 * Get active.profile from application.properties by reading
	 * "spring.profiles.active" value
	 */
	private static String getActiveProfile() {
		return getProb("application.properties").getProperty("spring.profiles.active");
	}

	// Select the application-env.properties file name according to
	// getActiveProfile.
	private static String getActiveProfileFilePath() {
		String activePropFileName = "application" + "-" + getActiveProfile() + "." + "properties";
		return activePropFileName;

	}

	// Create a new Properties with the input file
	private static Properties getProb(String fileName) {
		InputStream ip = getInputStream(fileName);

		Properties prop = new Properties();
		try {
			prop.load(ip);
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}

		return prop;

	}

	// Get the file's InputSream
	private static InputStream getInputStream(String fileName) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(fileName);

		return is;
	}

}
