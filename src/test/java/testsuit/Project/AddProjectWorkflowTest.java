package testsuit.Project;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
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
	public void Add_Project_TC_09(String testName, String appURL, String env) throws Exception {
		log("TC09 : Add a Project workflow");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.Login, "1");
		objLogin.login(map);
		String testcaseName = "ProjectApproval" + env;
		log("Data picked : " + testcaseName);
		log("Nevigate to Add Workflow");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.ProjectApproval,
				testcaseName);
		
		commonFunction.navigateToProjectDeails();
		
		projectWorkflow.addNewWorkflow(map, true);
		
		projectWorkflow.addNewWorkflow(map, false);
		
//		projectWorkflow.addNewWorkflow2To18(map);
		projectWorkflow.submitTheFormForReview();
		projectWorkflow.closeApprovalForm();
		projectWorkflow.verifyStoredRecord(map.get(Excel.ApprovalType));
		//Step21 : Repeat steps from 2 to 18
		
//		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.ProjectApproval,
//				"NewTestData");
//		projectWorkflow.addNewWorkflow(map);
//		projectWorkflow.submitTheFormForReview();
//		projectWorkflow.closeApprovalForm();
//		projectWorkflow.verifyStoredRecord(map.get(Excel.ApprovalType));
		
		try {
			commonFunction.navigateToMyDashboard();
			log("STEP 23: Click on submit for review on the form", Status.PASS);
		} catch (Exception e) {
			log("STEP 23: Not Click on submit for review on the form", Status.FAIL);
			throw new RuntimeException("Failed in step 23");
		}
		try {
			dashboardPage.navigateToRequestedDocumentTab();
			log("STEP 24: Click on the requested documents tab", Status.PASS);
		} catch (Exception e) {
			log("STEP 24: Not Click on the requested documents tab", Status.FAIL);
			throw new RuntimeException("Failed in step 24");
		}
		Assert.assertTrue(dashboardPage.isJobCreated("Requested"), "Job is not created");
		log("STEP 25: The Job created is listed under the tab", Status.PASS);

	}

	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "environment");
	}

}
