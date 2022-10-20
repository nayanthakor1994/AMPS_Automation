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
import pages.Project.AddOpertaingCompanyPage;
import pages.Project.AddProjectWorkflowPage;
import pages.tools.MyDashboardPage;

@Listeners(com.listeners.MyListeners.class)
public class AddOpertaingCompanyTest extends BasePage {
	LoginPage objLogin;
	CommonFunction commonFunction;
	MyDashboardPage dashboardPage;
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddOpertaingCompanyPage operatingCompany;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		operatingCompany = new AddOpertaingCompanyPage(driver);
		commonFunction = new CommonFunction(driver);
		dashboardPage = new MyDashboardPage(driver);
	}

	@Test()
	public void Add_Project_TC_08() throws Exception {
		log("TC08 : Add an operating company");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "Add";
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.OperatingCompany,
				testcaseName);
		
		commonFunction.navigateToProjectDeails();
		
		operatingCompany.addOperatingCompanies(map);
		

	}

	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "environmentROW");
	}

}
