package com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportsClass {
	public static ExtentTest test;
	public static ExtentReports extent;
	static ReadPropertyFile readPro = new ReadPropertyFile();

	public static void startUp() {
		reportStartUp();
	}

	public static void initialisation(String testName) {
		test = extent.createTest(testName);
	}

	public static void logStat(Status status, String message) {
		System.out.println(message);
		test.log(status, message);
	}

	public static void endTest() {
		extent.flush();
	}

	public static void reportStartUp() {
		DateFormat dateformat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		Date date = new Date();
				
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(readPro.getReportPath()+"-"+dateformat.format(date)+".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	public static void attachScreenshot(String screenshot, String name) {
		test.addScreenCaptureFromBase64String(screenshot, name);
	}
}
