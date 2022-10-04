package testsuit.Project;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.base.Excel;
import com.util.ExcelUtils;
import com.util.ReadPropertyFile;

import page.Common.LoginPage;
import pages.Project.AddProjectPage;

@Listeners(com.listeners.MyListeners.class)
public class AddProjectTest extends BasePage {
	LoginPage objLogin;
	AddProjectPage objAddProject;
	ReadPropertyFile readPro = new ReadPropertyFile();
	Map<String, String> map = new HashMap<String, String>();
	

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddProject = new AddProjectPage(driver);
	}
	
	@Test(dataProvider = "data-provider")
	public void add_Project_TC_01(String testName, String appURL) throws Exception {
		setEnvironment(appURL);
		navigateToApplication(appURL);
		String URLIndex = null;
		if(isALT) {
			URLIndex = "1";
		} else if (isDOT) {
			URLIndex = "2";
		} else if (isROW) {
			URLIndex = "3";
		}
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.Login, URLIndex);
		objLogin.login(map);
		String testcaseName = null;
		if(isALT) {
			testcaseName = "AddProjectALT";
		} else if (isDOT) {
			testcaseName = "AddProjectDOT";
		} else if (isROW) {
			testcaseName = "AddProjectROW";
		}
		log("Data picked : " + testcaseName);
		log("navigating to create new Project");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), "ProjectInformation", testcaseName);
		objAddProject.addProjectInformation(map);

	}
	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "environment");
	
	}

}
