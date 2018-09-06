package com.nisum.qa.automation.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class PropertiesUtils {

	Logger log = Logger.getLogger(getClass());
	private Properties properties = new Properties();
	private String propertiesFileName = "application";
	private String fileSeperator = System.getProperty("file.separator");
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
	 * Returns the value of given key from given properties file
	 */
	public String toGetGivenProperty(String provideKey) {
		String value = "";
		if (provideKey != "") {
			loadingOfPropertiesFile();
			try {
				if (!properties.getProperty(provideKey).trim().isEmpty())
					value = properties.getProperty(provideKey).trim();
			} catch (NullPointerException e) {

			}
		} else {
			log.error("Should provide existing key");

		}
		return value;
	}

	// This is to load properties file.
	private void loadingOfPropertiesFile() {
		FileInputStream fis;
		try {
			fis = new FileInputStream(getPropertiesFilePath(propertiesFileName));
			properties.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			log.error("Unale to find properties file - " + propertiesFileName + ".properties" + " at "
					+ getPropertiesFilePath(propertiesFileName));
		} catch (IOException e) {
			log.error("Cannot read configuration file - " + " at " + getPropertiesFilePath(propertiesFileName));
		}
	}

	public void toWritingProperty(String provideKey, String provideData) {
		if (provideKey.trim().length() > 0) {
			try {
				PropertiesConfiguration propertyConfigure = null;
				propertyConfigure = new PropertiesConfiguration(getPropertiesFilePath(propertiesFileName));
				propertyConfigure.setProperty(provideKey, provideData);
				propertyConfigure.getLayout();
				propertyConfigure.save();
			} catch (ConfigurationException e) {
			}
		} else {

		}	
	}

	public String getPropertiesFilePath(String fileName) {
		String propertiesFilePath;
		propertiesFilePath = getPropertiesDataFolderPath() + fileSeperator + fileName + ".properties";
		return propertiesFilePath;
	}

	// This is to get provided data file path.
	public String getPropertiesDataFolderPath() {
		return System.getProperty("user.dir") + fileSeperator + "Data";
	}

}
