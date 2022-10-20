package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertyFile {
	Properties configProperty;

	public ReadPropertyFile() {
		File src =  new File(System.getProperty("user.dir") + "/config.properties");
//		File src1 = new File(System.getProperty("user.dir") + "/Configuration/testdata.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			configProperty = new Properties();
			configProperty.load(fis);

//			FileInputStream fis1 = new FileInputStream(src1);
//			configProperty = new Properties();
//			configProperty.load(fis1);
		} catch (Exception e) {
			System.out.println("Exception is" + e.getMessage());
		}
	}

	// Chrome Driver get Property
	public String getChromeDriver() {
		String chromeDriver = configProperty.getProperty("CHROME_DRIVER");
		return chromeDriver;
	}
	
	// Chrome Driver get Property
		public String getFireFoxDriver() {
			String firefoxDriver = configProperty.getProperty("FIREFOX_DRIVER");
			return firefoxDriver;
		}
		
		// Chrome Driver get Property
		public String getEdgeDriver() {
			String edgeDriver = configProperty.getProperty("EDGE_DRIVER");
			return edgeDriver;
		}

	// Get Extent Report Path
	public String getReportPath() {
		String extentReport = configProperty.getProperty("EXTENT_REPORT_PATH");
		return extentReport;
	}

	// Get AltAmps URL
	public String getAltAmpsURL() {
		String altAmpsURL = configProperty.getProperty("ALT_AMPS_URL");
		return altAmpsURL;
	}
	
	// Get DOT AMPS URL
	public String getDotAMPSURL(){
		String dotAMPSURL = configProperty.getProperty("DOT_AMPS_URL");
		return dotAMPSURL;
	}

	// Get ROW AMPS URL
	public String getRowAMPSURL(){
		String rowAMPSURL = configProperty.getProperty("ROW_AMPS_URL");
		return rowAMPSURL;
	}
	
	// Get AltAMPS Username
	public String getAltAmpsUsername() {
		String altAmpsUserName = configProperty.getProperty("ALT_ADMIN_ROLE_UNAME");
		return altAmpsUserName;
	}

	// Get Password
	public String getPassword() {
		String altAmpsPassword = configProperty.getProperty("PASSWORD");
		return altAmpsPassword;
	}
	
	// Get Excel Test Data Sheet
	public String getExcelSheet(){
//		String excelSheet = configProperty.getProperty("EXCEL_TEST_DATA");
		String excelSheet = System.getProperty("user.dir") + File.separator+ "Excel" + File.separator + "Test_Data.xlsx";
		return excelSheet;
	}
	
	public String getExcelFileName(){
		String sheetOne = configProperty.getProperty("excel_file");
		return sheetOne;
	}
	
	// Get Sheet One
	public String getSheetOne(){
		String sheetOne = configProperty.getProperty("SHEET_ONE");
		return sheetOne;
	}
	
	// Get Sheet Two
	public String getSheetTwo(){
		String sheetTwo = configProperty.getProperty("SHEET_TWO");
		return sheetTwo;
	}
	
	// Get Sheet Three
	public String getSheetThree(){
		String sheetThree = configProperty.getProperty("SHEET_THREE");
		return sheetThree;
	}
	
	public String getCountryStateSheet() {
		String sheetThree = configProperty.getProperty("CountryState");
		return sheetThree;
	}
	public String getCurrectExcelFile(){
		String excelFile = "/Excel/Test_Data.xlsx";
		return excelFile;
	}
}
