package testsuit.Project;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.base.Excel;
import com.util.ExcelUtils;
import com.util.ReadPropertyFile;
import com.util.ReportsClass;

import page.Common.LoginPage;
import pages.Project.AddProjectPage;

@Listeners(com.listeners.MyListeners.class)
public class AddProjectWorkflowTest extends BasePage {

	LoginPage objLogin;
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddProjectPage objAddProject;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddProject = new AddProjectPage(driver);
	}

	@Test(dataProvider = "data-provider")
	public void Add_Project_TC_01(String testName, String appURL) throws Exception {
		setEnvironment(appURL);
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.Login, "1");
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
//		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.ProjectInformation,
//				testcaseName);
//		objAddProject.addProjectInformation(map);

	}

	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "AddProject");
	}

}
