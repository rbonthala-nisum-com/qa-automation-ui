package com.nisum.qa.automation.components;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;

public class Browsers {

	WebDriver driver;
	Logger log = Logger.getLogger(getClass());
	String fileSeperator = System.getProperty("file.separator");

	// To launch browser based on given browser name.
	public WebDriver launchSpecifiedBrowser(String browserName, ITestContext context) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = chromeDriver();
			driver.manage().window().maximize();
			context.setAttribute("driver", driver);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = firefoxDriver();
			driver.manage().window().maximize();
			context.setAttribute("driver", driver);
		}
		else if(browserName.equalsIgnoreCase("internetExplorer")) {
			driver = ieDriver();
			context.setAttribute("driver", driver);
		}
		return driver;
	}

	// To launch Chrome browser.
	private WebDriver chromeDriver() {
		log.info("Launching google chrome browser");
		System.setProperty("webdriver.chrome.driver", getDriverPath());
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.prompt_for_download", false);
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless"); 
		options.addArguments("--incognito");
		options.addArguments("--test-type", "start-maximized");
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("disable-infobars");
		return new ChromeDriver(options);
		
//		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
//
//		ChromeOptions opts = new ChromeOptions();
//		opts.addArguments("start-maximized");
//		capabilities.setCapability(ChromeOptions.CAPABILITY, opts);
//
//		WebDriver driver = new ChromeDriver(capabilities);
//		driver.manage().deleteAllCookies();
//		return new ChromeDriver();
//		
	}

	// To launch Firefox browser.
	private WebDriver firefoxDriver() {
		log.info("Launching firefox browser");
		System.setProperty("webdriver.gecko.driver", getFirefoxDriverPath());
		driver = new FirefoxDriver();
		return driver;
	}

	//To launch IE driver.
	private WebDriver ieDriver() {
		log.info("Launching IE browser");
		System.setProperty("webdriver.ie.driver", getIEDriverPath());
		driver = new InternetExplorerDriver();
		return driver;
	}
	// This is to get Chrome driver path
	public String getDriverPath() {

		String chromeDriverLocation = System.getProperty("user.dir") + fileSeperator + "BrowserDrivers" + fileSeperator;
		chromeDriverLocation = chromeDriverLocation + "chromedriver.exe";
		return chromeDriverLocation;
	}

	// This is to get FF driver path.
	public String getFirefoxDriverPath() {

		String firefoxDriverLocation = System.getProperty("user.dir") + fileSeperator + "BrowserDrivers"
				+ fileSeperator;
		firefoxDriverLocation = firefoxDriverLocation + "geckodriver.exe";
		return firefoxDriverLocation;
	}
	
	//This is to get IE driver path.
	public String getIEDriverPath() {

		String internetExplorerDriverLocation = System.getProperty("user.dir") + fileSeperator + "BrowserDrivers"
				+ fileSeperator;
		internetExplorerDriverLocation = internetExplorerDriverLocation + "IEDriverServer.exe";
		return internetExplorerDriverLocation;
	}
}
