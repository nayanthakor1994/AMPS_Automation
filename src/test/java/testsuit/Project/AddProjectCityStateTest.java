package testsuit.Project;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.util.ExcelUtils;
import com.util.ReadPropertyFile;
import com.util.ReportsClass;

import page.Common.LoginPage;
import pages.Project.AddProjectCityStatePage;
import pages.Project.AddProjectPage;

public class AddProjectCityStateTest extends BasePage {
	LoginPage objLogin = new LoginPage(driver);
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddProjectCityStatePage objCityState;
	AddProjectPage objAddProject;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddProject = new AddProjectPage(driver);
		objCityState = new AddProjectCityStatePage(driver);
//		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "Login", "1");
//		objLogin.login(map);
	}

	@Test
	public void add_Project_State_Country_TC_02() throws Exception {
		ReportsClass.initialisation("add_Project_State_Country_TC_02");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "Login", "1");
		objLogin.login(map);
		log("navigating to create City and State");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "CountryState", "New");
		objCityState.addStateAndContryInformation(map);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "CountryState", "Update");
		objCityState.updateStateAndContryInformation(map);

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
