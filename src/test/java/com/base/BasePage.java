package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;
import com.util.ReadPropertyFile;

import extentReports.ExtentTestManager;

public class BasePage extends CommonConstant {

	public WebDriver driver;
	public String browserName;
	public Properties prop;
	public Logger logger = Logger.getLogger("TestBase");
	static ReadPropertyFile readPro = new ReadPropertyFile();
	public static String projectDir = System.getProperty("user.dir");

	// Initialization of the properties files
	public BasePage() {
		FileInputStream fi;
		try {
			prop = new Properties();
			fi = new FileInputStream(System.getProperty("user.dir") + "/config.properties");
			prop.load(fi);

		} catch (FileNotFoundException e) {
			System.out.println("Exception from test Base Construction");
			logger.error("properties files are not found " + e);
		} catch (IOException e) {
			System.out.println("Exception from test Base Construction");
			logger.error("Not able to Read properties File " + e);
			e.printStackTrace();
		}
	}

	@BeforeTest(alwaysRun = true)
	@Parameters({"browser","env"})
	public WebDriver initialization(String browser, String env) throws Exception {
		if(browser != null) {
			browserName = browser;
		} else {
			browserName = "chrome";
		}
		if(env != null) {
			Environment.setEnvironment(env);
			environment = env;
		} else {
			throw new RuntimeException("Please provide environment in testng.xml");
		}
		DriverFactory.setTLDriver(browserName);
		driver = getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		try {
			appURL = Environment.getEnvironment();
		} catch (Exception e) {
			System.out.println("Please provide URL");
		}
		sheetName = prop.getProperty("EXCEL_TEST_DATA");
		return driver;
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}


	public synchronized void log(String message) {
		ExtentTestManager.info(message);
	}
	
	public synchronized void log(String message, Status status) {
		ExtentTestManager.log(status, message);
	}

	public WebDriver getDriver() {
		return DriverFactory.getTLDriver();
	}

	public void navigateToApplication(String url) {
		driver.get(Environment.getEnvironment());
		log("Navigating to : " + url);
	}
}
