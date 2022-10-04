package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;
import com.util.ReadPropertyFile;
import com.util.ReportsClass;

public class BasePage extends CommonConstant {

	public WebDriver driver;
	public String browser = "chrome";
	public Properties prop;
	public Logger logger = Logger.getLogger("TestBase");
	static ReadPropertyFile readPro = new ReadPropertyFile();
	public static String projectDir = System.getProperty("user.dir");
//	static String chromePath = readPro.getChromeDriver();
//	static String firefoxPath = readPro.getFireFoxDriver();
//	static String edgePath = readPro.getEdgeDriver();

	// Initialization of the properties files
	public BasePage() {
		FileInputStream fi;
		try {
			prop = new Properties();
			fi = new FileInputStream(System.getProperty("user.dir") + "/config.properties");
			System.setProperty("log4j.configurationFile", "log4j.xml");
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

	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public WebDriver initialization() throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") +File.separator +"Driver"+File.separator +"chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\Driver\\edge.exe");
			driver = new EdgeDriver();
		} else {
			throw new Exception("Browser is not correct");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		try {
			appURL = System.getProperty("envUrl");
		} catch (Exception e) {
			System.out.println("Please provide URL");
		}
		sheetName = prop.getProperty("EXCEL_TEST_DATA");
		return driver;
	}

//	@AfterClass
//	public void tearDown() {
//		driver.quit();
//	}

	@BeforeSuite
	public void beforeSuite() {
		ReportsClass.startUp();
	}

	@AfterSuite
	public void afterSuite() {
		ReportsClass.endTest();
	}

	public synchronized void log(String message) {
		ReportsClass.logStat(Status.INFO, message);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public void setEnvironment(String url) {
		if (url.toLowerCase().contains("alt")) {
			CommonConstant.isALT = true;
		} else if (url.toLowerCase().contains("dot")) {
			CommonConstant.isDOT = true;
		} else if (url.toLowerCase().contains("pipe")) {
			CommonConstant.isROW = true;
		}
	}
	
	public void navigateToApplication(String url) {
		driver.get(url);
		log("Navigating to : " + url);
	}
}
