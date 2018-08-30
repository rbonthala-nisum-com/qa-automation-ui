package com.nisum.automation.applicationpages;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.nisum.automation.components.Clicks;
import com.nisum.automation.components.TextField;
import com.nisum.automation.components.TimeOutMethods;
import com.nisum.automation.locators.ActionLocators;
import com.nisum.automation.util.ReadProperties;

public class WelcomePage extends Clicks {

	public WebElement element = null;

	public WelcomePage(WebDriver driver) {
		super(driver);
	}

	public static void navigateToMyTimeApplicationURL(String URL) {
		navigateToURL(URL);
	}

	// To click on welcome page's sign in button
	public void clickOnSignInWithGoogleButton() {
		userClick(ActionLocators.myTime_Sign_In_With_Google_Button, TimeOutMethods.waitTime10Seconds);
		sleepInSeconds(waitTime10Seconds);
		try {
			String winHandleBefore = driver.getWindowHandle();
			Collection<String> allWindows = driver.getWindowHandles();
			for (String curWindow : allWindows) {
				driver.switchTo().window(curWindow);
			}
			enterUserName(ReadProperties.getInstance().toGetGivenProperty("username"));
			enterPassword(ReadProperties.getInstance().toGetGivenProperty("password"));
			clickOnLoginButton();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.switchTo().window(winHandleBefore);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void lnk_ClickEmail(WebDriver driver) {

		element = driver.findElement(ActionLocators.clickEmail);
	}

	public void lnk_ChooseEmail(WebDriver driver) {
		element = driver.findElement(ActionLocators.chooseEmail);
	}

	public void clickOnLoginButton() {
		userClick(ActionLocators.loginLocator, TimeOutMethods.waitTime30Seconds);
	}

	public void enterUserName(String uName) {
		new TextField(driver).userTypeIntoTextField(ActionLocators.loginLocator, uName,
				TimeOutMethods.waitTime30Seconds);
	}

	public void enterPassword(String password) {
		new TextField(driver).userTypeIntoTextField(ActionLocators.loginLocator, password,
				TimeOutMethods.waitTime30Seconds);
	}
}
