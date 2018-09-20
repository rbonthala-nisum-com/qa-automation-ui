package com.nisum.qa.automation.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtils {

	Logger log = Logger.getLogger(getClass());
	private Properties properties = new Properties();
	private static PropertiesUtils readProperties;

	/**
	 * Default constructor.
	 */
	public PropertiesUtils() {

	}

	/**
	 * Parameterized constructor
	 */
	public static PropertiesUtils getInstance() {
		if (readProperties == null) {
			readProperties = new PropertiesUtils();
		}
		return readProperties;
	}

	/**
	 * Load the properties file and returns the value of given key from given
	 * properties file
	 */

	public Properties readPropertyValue(String filePath) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(filePath);
			properties.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			log.error("Unale to find properties file at " + filePath);
		} catch (IOException e) {
			log.error("Cannot read configuration file - " + " at " + filePath);
		}
		return properties;

	}

}
