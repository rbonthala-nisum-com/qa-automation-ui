package com.nisum.automation.util;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	public String getCellData(String filePath, String sheetName, String fieldName) {
		String columnValue = "";
		try {
			FileInputStream fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			int col_Num = -1;
			sheet = workbook.getSheet(sheetName);
			int lastRow = sheet.getLastRowNum();
			for (int i = 0; i <= lastRow; i++) {
				row = sheet.getRow(i);
				if (row.getCell(0).getStringCellValue().trim().toLowerCase().equals(fieldName.trim().toLowerCase())) {
					col_Num = 1;
					cell = row.getCell(col_Num);
					columnValue = cell.getStringCellValue();
					break;
				}
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "row or column does not exist  in Excel";
		}
		return columnValue;

	}
}
