package extentReports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.base.DriverFactory;


public class ExtentTestManager {

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> extentTestNode = new ThreadLocal<ExtentTest>();
	private static ExtentReports extent = ExtentManager.getInstance();
	private static ThreadLocal<String> extentTestName = new ThreadLocal<String>();
	private static ThreadLocal<String> overrideExtentTestName = new ThreadLocal<String>();
	private static ThreadLocal<Boolean> switchToNodeFlag = new ThreadLocal<Boolean>() {

		@Override
		protected Boolean initialValue() {
			return Boolean.FALSE;
		}

	};

	public synchronized static void swithLoggingToLatestNode() {
		switchToNodeFlag.set(Boolean.TRUE);
	}

	public synchronized static void switchLoggingOutOfLatestNode() {
		switchToNodeFlag.set(Boolean.FALSE);
	}

	public synchronized static ExtentTest getTest() {
		if (switchToNodeFlag.get()) {
			return extentTestNode.get();
		} else {
			return extentTest.get();
		}
	}

	public synchronized static ExtentTest getNode() {
		return extentTestNode.get();
	}

	public synchronized static ExtentTest createTest(String name, String description, String category) {
		ExtentTest test = extent.createTest(name, description);
		if (category != null && !category.isEmpty())
			test.assignCategory(category);

		extentTest.set(test);

		return getTest();
	}

	public synchronized static ExtentTest createTest(String name, String description) {
		return createTest(name, description, null);
	}

	public synchronized static ExtentTest createTest(String name) {
		return createTest(name, null);
	}

	public synchronized static void log(String message) {
		getTest().info(message);
	}
	
	public synchronized static void log(Status status, String message) {
		getTest().log(status, message);
	}

	public synchronized static void fail(String message) {
		System.out.println("[FAILURE] " + message);
		try {
			getTest().fail(message, MediaEntityBuilder
					.createScreenCaptureFromBase64String(getScreenshotAsBase64(DriverFactory.getTLDriver())).build());
		} catch (Exception e1) {
			getTest().fail(message);
		}
	}

	public synchronized static void error(Throwable t) {
		System.out.println("[ERROR] " + t.getMessage());
		try {
			getTest().fail(t, MediaEntityBuilder
					.createScreenCaptureFromBase64String(getScreenshotAsBase64(DriverFactory.getTLDriver())).build());
		} catch (Exception e1) {
			getTest().fail(e1);
		}
	}

	public synchronized static void createNode(String nodeName) {
		System.out.println("[CREATENODE] " + nodeName);
		if(switchToNodeFlag.get()) {
			switchLoggingOutOfLatestNode();
		}
		extentTestNode.set(getTest().createNode(nodeName));
		getNode().info(nodeName + " started");
		swithLoggingToLatestNode();
	}

	public synchronized static void nodeInfo(String message) {
		System.out.println("[NODEINFO] " + message);
		getNode().info(message);
		switchLoggingOutOfLatestNode();
	}

	public synchronized static void info(String message) {
		System.out.println("[INFO] " + message);
		getTest().info(message);
	}

	public synchronized static void pass(String message) {
		System.out.println("[PASS] " + message);
		getTest().pass(message);
	}
	

	public synchronized static void passWithScreenshot(String message) {
		System.out.println("[PASS] " + message);
		try {
			getTest().addScreenCaptureFromBase64String(getBase64ScreenshotString(DriverFactory.getTLDriver()));
			getTest().pass(message, MediaEntityBuilder
					.createScreenCaptureFromBase64String(getScreenshotAsBase64(DriverFactory.getTLDriver())).build());
		} catch (Exception e1) {
			getTest().pass(message);
		}
	}

	public synchronized static void infoWithScreenshot(String message) {
		System.out.println("[INFO] " + message);
		try {
			getTest().info(message, MediaEntityBuilder
					.createScreenCaptureFromBase64String(getScreenshotAsBase64(DriverFactory.getTLDriver())).build());
		} catch (Exception e1) {
			getTest().info(message);
		}
	}

	public synchronized static void warning(String message) {
		System.out.println("[WARNING] " + message);
		try {
			getTest().warning(message, MediaEntityBuilder
					.createScreenCaptureFromBase64String(getScreenshotAsBase64(DriverFactory.getTLDriver())).build());
		} catch (Exception e1) {
			getTest().warning(message);
		}
	}

	public synchronized static void skip(String message) {
		System.out.println("[SKIPPING] " + message);
		getTest().skip(message);
	}

	public synchronized static String getTestName() {
		return extentTestName.get();
	}

	public synchronized static String setTestName(String testName) {
		extentTestName.set(testName);
		return getTestName();
	}

	public synchronized static String getOverrideTestName() {
		if (overrideExtentTestName.get() != null) {
			return overrideExtentTestName.get();
		} else {
			return getTestName();
		}
	}

	public synchronized static String setOverrideTestName(String testName) {
		overrideExtentTestName.set(testName);
		return getOverrideTestName();
	}

	private synchronized static String getBase64ScreenshotString(WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		byte[] fileContent = FileUtils.readFileToByteArray(source);
		String base64StringofScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
		return base64StringofScreenshot;
	}

	private synchronized static String getScreenshotAsBase64(WebDriver driver) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshots/image.png";
		FileUtils.copyFile(source, new File(path));
		byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(path));
		return Base64.getEncoder().encodeToString(imageBytes);
	}

	public synchronized static void logTestCaseInformation(String testCaseName, String testSheetName,
			String testCaseRow, String testCaseColumn) {
		ExtentTestManager.info("TestCaseName: " + testCaseName);
		ExtentTestManager.info("testSheetName: " + testSheetName);
		ExtentTestManager.info("testCaseRow: " + testCaseRow);
		ExtentTestManager.info("testCaseColumn: " + testCaseColumn);
	}
}
