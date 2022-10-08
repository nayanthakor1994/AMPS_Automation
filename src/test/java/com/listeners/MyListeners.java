package com.listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.util.ReportsClass;

public class MyListeners extends BasePage implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Case Started : "+result.getName());
		ReportsClass.initialisation(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Case Passed : "+result.getName());
		ReportsClass.logStat(Status.PASS, result.getMethod().getMethodName() + " Testcase passed...");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Case Failed : "+result.getName());
		ReportsClass.logStat(Status.FAIL, result.getMethod().getMethodName() + " Testcase failed...");
		ReportsClass.logStat(Status.FAIL, "Failed with " + result.getThrowable());
		try {
			getScreenshotAsBase64(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Case Skipped : "+result.getName());
	}


	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
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
	
}
