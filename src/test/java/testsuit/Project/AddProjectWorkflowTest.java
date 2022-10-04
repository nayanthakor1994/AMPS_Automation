package testsuit.Project;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.ExcelUtils;
import com.util.ReadPropertyFile;

import page.Common.LoginPage;
import pages.Project.AddProjectPage;
import pages.Project.AddProjectWorkflowPage;
import pages.tools.MyDashboardPage;

@Listeners(com.listeners.MyListeners.class)
public class AddProjectWorkflowTest extends BasePage {

	LoginPage objLogin;
	CommonFunction commonFunction;
	MyDashboardPage dashboardPage;
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddProjectWorkflowPage projectWorkflow;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		projectWorkflow = new AddProjectWorkflowPage(driver);
		commonFunction = new CommonFunction(driver);
		dashboardPage = new MyDashboardPage(driver);
	}

	@Test(dataProvider = "data-provider")
	public void Add_Project_TC_01(String testName, String appURL, String env) throws Exception {
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.Login, "1");
		objLogin.login(map);
		String testcaseName = "ProjectApproval" + env;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.ProjectApproval,
				testcaseName);
		
		commonFunction.navigateToProjectDeails();
		
		projectWorkflow.addNewWorkflow(map);
		projectWorkflow.submitTheFormForReview();
		projectWorkflow.closeApprovalForm();
		projectWorkflow.verifyStoredRecord(map.get(Excel.ApprovalType));
		//Step21 : Repeat steps from 2 to 18
		
//		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.ProjectApproval,
//				NewTestData);
//		projectWorkflow.addNewWorkflow(map);
//		projectWorkflow.submitTheFormForReview();
//		projectWorkflow.closeApprovalForm();
//		projectWorkflow.verifyStoredRecord(map.get(Excel.ApprovalType));
		
		commonFunction.navigateToMyDashboard();
		dashboardPage.navigateToRequestedDocumentTab();
		dashboardPage.isJobCreated("Requested");
		

	}

	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "environment");
	}

}