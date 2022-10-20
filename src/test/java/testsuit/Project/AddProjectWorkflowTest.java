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

	@Test()
	public void Add_Project_TC_09() throws Exception {
		System.out.println("");
		log("TC09 : Add a Project workflow");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "ProjectApproval" + environment;
		log("Data picked : " + testcaseName);
		log("Nevigate to Add Workflow");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.ProjectApproval,
				testcaseName);
		
		commonFunction.navigateToProjectDeails();
		
		projectWorkflow.addNewWorkflow(map, true);
		projectWorkflow.addNewWorkflow(map, false);
		
		projectWorkflow.submitTheFormForReview();
		projectWorkflow.closeApprovalForm();
		projectWorkflow.verifyStoredRecord(map.get(Excel.ApprovalType));
		
		try {
			commonFunction.navigateToMyDashboard();
			log("STEP 25: Click on submit for review on the form", Status.PASS);
		} catch (Exception e) {
			log("STEP 25: Not Click on submit for review on the form", Status.FAIL);
			throw new RuntimeException("Failed in step 23");
		}
		try {
			dashboardPage.navigateToRequestedDocumentTab();
			log("STEP 26: Click on the requested documents tab", Status.PASS);
		} catch (Exception e) {
			log("STEP 26: Not Click on the requested documents tab", Status.FAIL);
			throw new RuntimeException("Failed in step 24");
		}
		Assert.assertTrue(dashboardPage.isJobCreated("Requested"), "Job is not created");
		log("STEP 27: The Job created is listed under the tab", Status.PASS);

	}

	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "environmentALT");
	}

}
