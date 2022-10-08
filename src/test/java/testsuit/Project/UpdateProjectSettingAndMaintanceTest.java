package testsuit.Project;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.ExcelUtils;
import com.util.ReadPropertyFile;
import com.util.ReportsClass;

import page.Common.LoginPage;
import pages.Project.AddProjectPage;
import pages.Project.UpdateProjectSettingAndMaintancePage;

@Listeners(com.listeners.MyListeners.class)
public class UpdateProjectSettingAndMaintanceTest extends BasePage {
	LoginPage objLogin;
	CommonFunction commonFunction;
	ReadPropertyFile readPro = new ReadPropertyFile();
	UpdateProjectSettingAndMaintancePage objUpdateProject;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objUpdateProject = new UpdateProjectSettingAndMaintancePage(driver);
		commonFunction = new CommonFunction(driver);
	}

	@Test(dataProvider = "data-provider")
	public void Update_Project_Setting_Maintence_TC_03(String testName, String appURL, String env) throws Exception {
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.Login, "1");
		objLogin.login(map);
		String testcaseName = "ProjectSettingsAndMaintence" + env;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.ProjectSettingsAndMaintence,
				testcaseName);
		log("navigating to create UpdateProjectSettingMaintence");
		commonFunction.navigateToProjectDeails();
		objUpdateProject.updateProjectSettingAndMaintence(map);
		
		
	}

	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "environment");
	}

}
