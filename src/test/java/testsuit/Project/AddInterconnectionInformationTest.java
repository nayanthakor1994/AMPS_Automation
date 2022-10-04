package testsuit.Project;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.util.CommonFunction;
import com.util.ExcelUtils;
import com.util.ReadPropertyFile;
import com.util.ReportsClass;

import page.Common.LoginPage;
import pages.Project.AddInterconnectionInformationPage;
import pages.Project.AddProjectPage;

public class AddInterconnectionInformationTest extends BasePage {
	LoginPage objLogin = new LoginPage(driver);
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddInterconnectionInformationPage objAddInterconnectionInformation;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddInterconnectionInformation = new AddInterconnectionInformationPage(driver);
	}

	@Test
	public void add_An_Interconnection_Information_TC_04() throws Exception {

		ReportsClass.initialisation("add_An_Interconnection_Information_TC_04");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "Login", "1");
		objLogin.login(map);
		log("navigating to add Interconnection Information");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "Interconnection Information", "New");
		objAddInterconnectionInformation.addInterconnectionInformation(map);

		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "Interconnection Information",
				"Update");
		objAddInterconnectionInformation.updateInterconnectionInformation(map);

		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "Interconnection Information",
				"Update");
		objAddInterconnectionInformation.updateInterconnectionDocument(map);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "Interconnection Information",
				"Update");
		objAddInterconnectionInformation.deleteInterconnection(map);
		

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		// recorder.stop();
		if (ITestResult.FAILURE == result.getStatus()) {
			// CommonFunction.captureScreenshot(driver, result.getName());
			ReportsClass.logStat(Status.FAIL, "Test Failed with " + result.getThrowable());
		} else if (ITestResult.SUCCESS == result.getStatus()) {
			ReportsClass.logStat(Status.PASS, result.getMethod().getMethodName() + " Testcase passed...");
		}
		// commonFunction.closeBrowser();
	}

}
