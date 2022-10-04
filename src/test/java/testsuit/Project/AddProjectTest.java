package testsuit.Project;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.util.ExcelUtils;
import com.util.ReadPropertyFile;
import com.util.ReportsClass;

import page.Common.LoginPage;
import pages.Project.AddProjectPage;
@Listeners(com.listeners.MyListeners.class) 
public class AddProjectTest extends BasePage {
	LoginPage objLogin = new LoginPage(driver);
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddProjectPage objAddProject;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddProject = new AddProjectPage(driver);
	}

	@Test()
	public void add_Project_TC_01() throws Exception {
		
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "Login", "1");
		objLogin.login(map);
		log("navigating to create new Project");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "Project Information", "AddProjectALT");
		objAddProject.addProjectInformation(map);

	}
	
	

}
