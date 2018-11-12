package com.nisum.qa.automation.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static Logger log;
	public static Map<String, String> getCellData(String filePath, String sheetName) {
		XSSFRow row;
		XSSFCell cell;
		Map<String, String> data = new HashMap<String, String>();
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		String key;
		String value;
		try {
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				if (sheetName.equalsIgnoreCase(workbook.getSheetName(i))) {
					sheet = workbook.getSheet(sheetName);
					break;
				}
			}
			int lastRow = sheet.getLastRowNum();
			for (int i = 0; i <= lastRow; i++) {
				row = sheet.getRow(i);
				cell = row.getCell(0);
				key = cell.getStringCellValue();
				Boolean isFound = key.contains("Date");
				if (isFound) {
					cell = row.getCell(1);
					Date d = cell.getDateCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(d);
					value = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-"
							+ cal.get(Calendar.DAY_OF_MONTH);
				} else {
					cell = row.getCell(1);
					value = cell.getStringCellValue();
				}
				data.put(key, value);
			}
		} catch (FileNotFoundException e) {
			log.error("Please correct the specified path  : "+filePath);
			e.printStackTrace();
		}catch(IOException e) {
			log.error("Please check the I/O of the file ");
			e.printStackTrace();
		}finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("Not able to close fis object");
				}
			}
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					System.out.println("Not able to close workbook object");
				}
			}
		}
		return data;
	}
	
}
