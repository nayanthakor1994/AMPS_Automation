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
import com.util.ExcelUtils;
import com.util.ReadPropertyFile;
import com.util.ReportsClass;

import page.Common.LoginPage;
import pages.Project.AddProjectPage;
import pages.Project.UpdateProjectSettingAndMaintancePage;

@Listeners(com.listeners.MyListeners.class)
public class UpdateProjectSettingAndMaintanceTest extends BasePage {
	LoginPage objLogin;
	ReadPropertyFile readPro = new ReadPropertyFile();
	UpdateProjectSettingAndMaintancePage objUpdateProject;
	Map<String, String> map = new HashMap<String, String>();
	
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objUpdateProject = new UpdateProjectSettingAndMaintancePage(driver);
	}


	@Test(dataProvider = "data-provider")
	public void Update_Project_Setting_Maintence_TC_03(String testName, String appURL) throws Exception {
		setEnvironment(appURL);
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.Login, "1");
		objLogin.login(map);
		String testcaseName = null;
		if(isALT) {
			testcaseName = "Update_ProjectMaintence_ALT";
		} else if (isDOT) {
			testcaseName = "Update_ProjectMaintence_DOT";
		} else if (isROW) {
			testcaseName = "Update_ProjectMaintence_ROW";
		}
		log("Data picked : " + testcaseName);
		log("navigating to create UpdateProjectSettingMaintence");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), "ProjectSettingsAndMaintence", testcaseName);
		objUpdateProject.updateProjectSettingAndMaintence(map);
	}

	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "environment");
	}


}
