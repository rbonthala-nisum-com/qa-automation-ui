package com.nisum.qa.automation.components;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Window extends TimeOutMethods {

	public Window(WebDriver driver) {
		super(driver);
	}

	// To enter text into given web element.
	public String getWindowTitle(int... timeInSeconds) {
		String title = "";
		int givenWaitTime = 0;
		try {
			givenWaitTime = toGetGivenAmountOfTime(timeInSeconds);
			implicitWaitForGivenTime(givenWaitTime);
			title = driver.getWindowHandle();
			log.info("Current window title is:  " + title);
		}

		catch (Exception e) {
			log.error("unable to get page title");
			Assert.fail("Unable to get page title");
		}
		return title;
	}
	
	public void switchToNewWindow(WebDriver driver, String parentWindow, int timeInSeconds) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			for (String childWindow : allWindows) {
				if(!parentWindow.equalsIgnoreCase(childWindow))
				{
					driver.switchTo().window(childWindow);
				}
				log.info("Switched to new window");
			}
		}

		catch (Exception e) {
			log.error("unable to switch to new window");
			Assert.fail("unable to switch to new window");
		}

	}


}
