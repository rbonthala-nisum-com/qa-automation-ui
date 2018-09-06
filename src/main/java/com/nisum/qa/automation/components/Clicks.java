package com.nisum.qa.automation.components;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Clicks extends TimeOutMethods {

	public Clicks(WebDriver driver) {
		super(driver);
		log = Logger.getLogger(getClass());
	}

	// To navigate to given http application URL.
	public static void navigateToURL(String url) {
		try {
			driver.navigate().to(url);
			log.info("Navigated to the given application URL: " + url);
		} catch (Exception e) {
			log.error("unable to navigate to the given appication URL: " + url);
			e.getStackTrace();
		}
	}

	// This function is to click on given web element.
	public static void userClick(By locator, int... timeInSeconds) {
		int givenWaitTime = 0;
		try {
			givenWaitTime = toGetGivenAmountOfTime(timeInSeconds);
			if (whetherElementClickable(locator, givenWaitTime)) {
				WebElement webelement = driver.findElement(locator);
				webelement.click();
				log.info("Clicked on the given web element: " + locator);
			} else {
				log.error(
						"Given web element: " + locator + " is not clickable in time - " + givenWaitTime + " Seconds");
				Assert.fail(
						"Given web elemnt:" + locator + " is not clickable in time - " + givenWaitTime + " Seconds");
			}
		} catch (NoSuchElementException e) {
			log.error("Given web element: " + locator + " was not found in DOM in time - " + givenWaitTime + " Seconds"
					+ " - NoSuchElementException");
			Assert.fail("Given web element: " + locator + " was not found in DOM in time - " + givenWaitTime
					+ " Seconds" + " - NoSuchElementException");
		} catch (Exception e) {
			log.error("Given web element: " + locator + " was not clickable" + e);
			Assert.fail("Given web element: " + locator + " was not found on the web page");
		}
	}

}
