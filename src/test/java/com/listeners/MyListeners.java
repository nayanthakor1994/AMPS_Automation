package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.util.ReportsClass;

public class MyListeners implements ITestListener {

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

	
	
}
