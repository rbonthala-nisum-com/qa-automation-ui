package com.nisum.qa.automation.components;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDown extends TimeOutMethods {

	public DropDown(WebDriver driver) {
		super(driver);
	}

	// To select a value from given dropdown.
	public void selectAValueFromDropDown(By locator, String value, int timeInSeconds) {
		boolean elementPresence = whetherElementPresent(locator, timeInSeconds);
		Select select = new Select(driver.findElement(locator));
		if (elementPresence) {
			try {
				select.selectByValue(value);
			} catch (NoSuchElementException e) {
				log.error("Given web element: " + locator + " was not found in DOM in time - " + timeInSeconds
						+ " Seconds" + " - NoSuchElementException");
				e.printStackTrace();
			}
		} else {
			log.info("Element is not present in DOM");
		}
	}

}
