package com.nisum.qa.automation.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


/**
 * @author Nisum
 *
 */
public class CommonUtils extends PropertiesUtils{

	String sheetName;
	String filePath;

	/**
	 * Gets the current system date time.
	 */
	public static String getCurrentSystemDateTime() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	/**
	 * Gets the random number. which will be used mostly while saving a screenshot
	 */
	public static int getRandomNumber() {
		Random rand = new Random();
		int numNoRange = rand.nextInt();
		return numNoRange;
	}

	/**
	 * Sheet name.
	 *
	 * @return the string
	 */
	public String sheetName() {
		try {
			sheetName = toGetGivenProperty("sheetName");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheetName;
	}

	public String filePath() {
		try {
			filePath = System.getProperty("user.dir") + "\\InputData\\InputData.xlsx";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}
}
