package testsuit.Project;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
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
import pages.Project.AddInterconnectionInformationPage;
import pages.Project.AddProjectPage;
@Listeners(com.listeners.MyListeners.class)
public class AddInterconnectionInformationTest extends BasePage {
	LoginPage objLogin;
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddInterconnectionInformationPage objAddInterconnectionInformation;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddInterconnectionInformation = new AddInterconnectionInformationPage(driver);
	}

	@Test(dataProvider = "data-provider")
	public void add_An_Interconnection_Information_TC_04(String testName, String appURL) throws Exception {
		setEnvironment(appURL);
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.Login, "1");
		objLogin.login(map);
		log("navigating to add Interconnection Information");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "InterconnectionInformation", "New");
		objAddInterconnectionInformation.addInterconnectionInformation(map);

		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "InterconnectionInformation",
				"Update");
		objAddInterconnectionInformation.updateInterconnectionInformation(map);

		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "InterconnectionInformation",
				"Update");
		objAddInterconnectionInformation.updateInterconnectionDocument(map);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "InterconnectionInformation",
				"Update");
		objAddInterconnectionInformation.deleteInterconnection(map);
		

	}

	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "environment");
	
	}

}
