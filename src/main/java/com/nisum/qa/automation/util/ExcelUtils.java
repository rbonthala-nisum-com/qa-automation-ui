package com.nisum.qa.automation.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	// private static ExcelUtils uniqueInstance;
	//
	// private ExcelUtils() {}
	//
	// public static ExcelUtils getInstance() {
	// if (uniqueInstance == null) {
	// uniqueInstance = new ExcelUtils();
	// }
	// return uniqueInstance;
	// }

	public static Map<String, String> getCellData(String filePath, String sheetName) {
		XSSFRow row;
		XSSFCell cell;
		Map<String, String> data = new HashMap<String, String>();
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		String key;
		String value;
		String sheets = "";
		try {
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
//				sheets = sheets + "," + workbook.getSheetName(i);
				if(sheetName.equalsIgnoreCase(workbook.getSheetName(i))) {
					sheet = workbook.getSheet(sheetName);
					break;
				}
            }
			int lastRow = sheet.getLastRowNum();
			for (int i = 0; i <= lastRow; i++) {
				row = sheet.getRow(i);
				cell = row.getCell(0);
				key = cell.getStringCellValue();
				cell = row.getCell(1);
				value = cell.getStringCellValue();
				data.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//return "row or column does not exist  in Excel";
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
}
