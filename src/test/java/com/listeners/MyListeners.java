package com.listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNGException;

import com.aventstack.extentreports.ExtentReports;
import com.base.BasePage;
import com.base.DriverFactory;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import extentReports.ExtentManager;
import extentReports.ExtentTestManager;

public class MyListeners extends BasePage implements ITestListener {

	private static ExtentReports extent = ExtentManager.getInstance();
	int extentPassCount = 0;
	int extentFailCount = 0;
	int extentTestTotal = 0;
	int extentTestSkipped = 0;
	ATUTestRecorder recorder;
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Case Started : "+result.getName());
//		ReportsClass.initialisation(result.getName());
		
		try {
			ExtentTestManager.createTest(result.getName());
			ExtentTestManager.info(result.getMethod().getMethodName() + " Started...");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		// Start Recording
		try {
			if (System.getProperty("isVideo").equals("true")) {
				DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
				Date date = new Date();
				try {
					recorder = new ATUTestRecorder(System.getProperty("user.dir") + "\\Videos\\",
							result.getName() + "-" + dateFormat.format(date), false);
				} catch (Exception e) {
					ExtentTestManager.fail("Error in finding the location of the video.");
				}
				try {
					recorder.start();
				} catch (Exception e) {
					ExtentTestManager.fail("Error in starting the video");
				}
			}
		} catch (Exception e1) {
			System.out.println("Video recorder parameter is not set");
		}
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Case Passed : "+result.getName());
//		ReportsClass.logStat(Status.PASS, result.getMethod().getMethodName() + " Testcase passed...");
		
		ExtentTestManager.pass(result.getMethod().getMethodName() + " Passed...");
		extentPassCount++;
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Case Failed : "+result.getName());
//		ReportsClass.logStat(Status.FAIL, "Failed with " + result.getThrowable());
//		try {
//			ReportsClass.attachScreenshot(getBase64ScreenshotString(getDriver()));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		ReportsClass.logStat(Status.FAIL, result.getMethod().getMethodName() + " Testcase failed...");
		
		
		try {
			ExtentTestManager.fail(result.getMethod().getMethodName()+ " failed because of "+ result.getThrowable());
		}catch (Exception e) {
			e.printStackTrace();
		}
		extentFailCount++;
		WebDriver driverWithFailure = DriverFactory.getTLDriver();
		try {
			ExtentTestManager.info("Adding screen capture...");
			ExtentTestManager.getTest().addScreenCaptureFromBase64String(getBase64ScreenshotString(driverWithFailure), result.getMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.fail("Failed to get screenshot");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Case Skipped : "+result.getName());
		
		try {
			ExtentTestManager.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
			ExtentTestManager.skip(result.getMethod().getMethodName()+ " skipped because of "+ result.getThrowable());
			extentTestSkipped++;
			try {
				ExtentTestManager.info("Adding screen capture...");
				ExtentTestManager.getTest().addScreenCaptureFromBase64String(getBase64ScreenshotString(DriverFactory.getTLDriver()), result.getMethod().getMethodName());
			} catch (Exception e2) {
				e2.printStackTrace();
				ExtentTestManager.fail("Failed to get screenshot");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		try {
			if (System.getProperty("isVideo").equals("true")) {
				try {
					recorder.stop();
				} catch (ATUTestRecorderException e) {
					ExtentTestManager.fail("Failed to save video");
				}
			}
		} catch (Exception e1) {
			System.out.println("Video recorder parameter is not set");
		}
		extent.flush();	
		
//		if(context.getFailedTests().size()>0) {
//			throw new TestNGException("Failures present");
//		}
//		
//		if(context.getSkippedTests().size()>0) {
//			throw new TestNGException("Skipped Tests present");
//		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
//
//	public synchronized String getBase64ScreenshotString(WebDriver driver) throws IOException {
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//
//		byte[] fileContent = FileUtils.readFileToByteArray(source);
//		String base64StringofScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
//		return base64StringofScreenshot;
//	}	
//	private synchronized static String getScreenshotAsBase64(WebDriver driver) throws IOException {
//		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		String path = System.getProperty("user.dir") + "/target/Screenshots/image.png";
//		FileUtils.copyFile(source, new File(path));
//		byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(path));
//		return Base64.getEncoder().encodeToString(imageBytes);
//	}
//	private synchronized static String getBase64ScreenshotString(WebDriver driver) throws IOException {
//		TakesScreenshot ts = (TakesScreenshot) driver;
//	//	File source = ts.getScreenshotAs(OutputType.FILE);
//
//		//byte[] fileContent = FileUtils.readFileToByteArray(source);
//		/*
//		 * String base64StringofScreenshot = "data:image/png;base64," +
//		 * Base64.getEncoder().encodeToString(fileContent); return
//		 * base64StringofScreenshot;
//		 */
//	}
//
	private synchronized static String getScreenshotAsBase64(WebDriver driver) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/target/Screenshots/image.png";
		FileUtils.copyFile(source, new File(path));
		byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(path));
		return Base64.getEncoder().encodeToString(imageBytes);
	}
	
	public synchronized String getBase64ScreenshotString(WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(source);
		String base64StringofScreenshot = "data:image/png;base64,"+Base64.getEncoder().encodeToString(fileContent);
		return base64StringofScreenshot;
	}
	
}
