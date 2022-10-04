package com.util;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static String[][] getExcelData(String fileName, String SheetName) {
		String[][] arrayExcelData = null;
		try {
			int colCount, rowCount, i = 0, j = 0, count = 0;
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + fileName);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
			XSSFSheet mySheet = myWorkBook.getSheet(SheetName);
			XSSFRow row = null;
			row = mySheet.getRow(0);
			colCount = row.getLastCellNum();
			rowCount = mySheet.getLastRowNum() + 1;
			Iterator<Row> iterator = mySheet.iterator();
			arrayExcelData = new String[rowCount - 1][colCount];
			SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yy");
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				if (count == 0) {
					count++;
				} else {
					j = 0;
					while (cellIterator.hasNext()) {
						Cell currentCell = cellIterator.next();
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							arrayExcelData[i][j] = currentCell.getStringCellValue();
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							if (currentCell.getCellStyle().getDataFormatString().equalsIgnoreCase("General")) {
								arrayExcelData[i][j] = String.valueOf(currentCell.getNumericCellValue());
								arrayExcelData[i][j] = arrayExcelData[i][j].indexOf(".") < 0 ? arrayExcelData[i][j]
										: arrayExcelData[i][j].replaceAll("0*$", "").replaceAll("\\.$", "");
							} else {
								Date d = currentCell.getDateCellValue();
								arrayExcelData[i][j] = fmt.format(d);
							}
						}
						j++;
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}
	
	/**
	 * 	For this method, In Excel make first row empty
	 * @param fileName
	 * @param SheetName
	 * @param rowData
	 * @return
	 */
	public static Map<String, String> getRowFromRowNumber(String fileName, String SheetName, String rowData) {
		String[][] excelData = ExcelUtils.getExcelData(fileName, SheetName);
		String[] column= excelData[0];
		int colSize = column.length;
		int size = excelData.length;
		Map<String, String> map = new HashMap<String, String>();
		for (int i=0;i<size;i++){
			if(excelData[i][0].equals(rowData)) {
				for(int j=0;j<colSize;j++) {
					map.put(excelData[0][j], excelData[i][j]); 
				}
				break;
			}
		}
		return map;
	}
	
	/**
	 * 	For this method, In Excel make first row empty
	 * @param fileName
	 * @param SheetName
	 * @param rowData
	 * @return
	 * @throws Exception 
	 */
	public static Object[][] getURLFromSheet(String fileName, String SheetName, String testCaseName) throws Exception {
		String[][] excelData = ExcelUtils.getExcelData(fileName, SheetName);
		List<String> exp = new ArrayList<String>();
		List<String> env = new ArrayList<String>();
		for (int i=0;i<excelData.length;i++){
			if(excelData[i][0].equals(testCaseName)) {
				exp.add(excelData[i][1]);
				env.add(excelData[i][2]);
			}
		}
		Object[][] bojData = new Object[exp.size()][3];
		for (int i=0;i<bojData.length;i++){
			bojData[i][0] = testCaseName;
			bojData[i][1] = exp.get(i);
			bojData[i][2] = env.get(i);
		}
		
		return bojData;
	}
	
	
	public static Object[][] getExcelDataSpecific(String fileName, String SheetName, String testCaseColumnName,
			String testCaseName) throws Exception {
		String[][] arrayExcelData = null;
		int testCaseDataArrayLength = getTestCaseDataArrayLength(fileName, SheetName, "TestCase", testCaseName);
		int testCaseDataArrayWidth = getTestCaseDataArrayWidth(fileName, testCaseName, fileName, SheetName);
		System.out.println("length: " + testCaseDataArrayLength);
		System.out.println("width: " + testCaseDataArrayWidth);
		Object[][] expected = new Object[testCaseDataArrayLength][testCaseDataArrayWidth];

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet mySheet = workbook.getSheet(SheetName);
		XSSFRow row = null;

		DataFormatter formatter = new DataFormatter();
		int k = 0;
		int l = 0;
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(SheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				Iterator<Row> rows = sheet.iterator();// sheet is collections of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// rows is collection of cell
				int j = 0;
				int testCaseColumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (getCellString(value).equalsIgnoreCase(testCaseColumnName)) {
						testCaseColumn = j;
					}
					j++;
				}
				while (rows.hasNext()) {
					Row r = rows.next();
					if (getCellString(r.getCell(testCaseColumn)).equalsIgnoreCase(testCaseName)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							if (l <= testCaseDataArrayWidth) {
								Cell currentCell = cv.next();
								expected[k][l] = currentCell.getStringCellValue();
							} else {
								System.out
										.println("Excel data width was greater than the column data for column: " + l);
							}
							l++;
						}
						l = 0;
						k++;
					}

				}
			}
		}
		workbook.close();
		return expected;
	}
	
	private synchronized static String getCellString(Cell currentCell) {
		return String.valueOf(currentCell);
	}
	
	private static int getTestCaseDataArrayLength(String fileName, String workSheetName, String testCaseColumnName, String testcaseName) throws Exception {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet mySheet = workbook.getSheet(workSheetName);
		int testCaseDataArrayLength = 0;
		int sheets = workbook.getNumberOfSheets();
		for(int i=0; i<sheets;i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase(workSheetName)){
				XSSFSheet sheet = workbook.getSheetAt(i);

				Iterator<Row> rows=	sheet.iterator();// sheet is collections of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();//rows is collection of cell
				int j=0;
				int testCaseColumn = 0;
				while(ce.hasNext()) {
					Cell value = ce.next();
					if(String.valueOf(value).equalsIgnoreCase(testCaseColumnName)) {
						testCaseColumn = j;
					}
					j++;
				}
				while(rows.hasNext()) {
					Row r = rows.next();
					if(String.valueOf(r.getCell(testCaseColumn)).equalsIgnoreCase(testcaseName)) {
						testCaseDataArrayLength++;
					}
				}
			}
		}
		workbook.close();
		return testCaseDataArrayLength;
	}
	
	private synchronized static int getTestCaseDataArrayWidth(String fileName, String testCaseName, String workBookName, String workSheetName) throws Exception {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int testCaseDataArrayWidth = 0;
		int sheets = workbook.getNumberOfSheets();
		for(int i=0; i<sheets;i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase(workSheetName)){
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows=	sheet.iterator();// sheet is collections of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();//rows is collection of cell
				while(ce.hasNext()) {
					ce.next();
					testCaseDataArrayWidth++;
				}

			}
		}
		workbook.close();
		return testCaseDataArrayWidth;
	}
}
