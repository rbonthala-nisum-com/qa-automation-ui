package com.nisum.qa.automation.components;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TextField extends TimeOutMethods {

	public TextField(WebDriver driver) {
		super(driver);
	}

	// To enter text into given web element.
	public void userTypeIntoTextField(By locator, String provideText, int... timeInSeconds) {
		int givenWaitTime = 0;
		try {
			givenWaitTime = toGetGivenAmountOfTime(timeInSeconds);
			if (whetherElementClickable(locator, givenWaitTime)) {
				WebElement webelement = driver.findElement(locator);
				webelement.sendKeys(provideText);
				log.info("Entered value: " + provideText + "in to the given web element: " + locator);
			} else {
				log.error("Not able to type" + provideText + " in to web element: " + locator + " in time - "
						+ givenWaitTime + " Seconds");
				Assert.fail("Not able to type" + provideText + " in to web element: " + locator + " in time - "
						+ givenWaitTime + " Seconds");
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