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
import pages.Project.AddInterconnectionInformationPage;
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

	@Test()
	public void add_An_Interconnection_Information_TC_04() throws Exception {
		log("TC04 : Add an Interconnection Information");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddInterconnection" + environment;
		log("Data picked : " + testcaseName);
		log("navigating to add Interconnection Information");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), Excel.InterconnectionInformation, testcaseName);
		objAddInterconnectionInformation.addInterconnectionInformation(map);

		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), Excel.InterconnectionInformation,
				testcaseName);
		objAddInterconnectionInformation.updateInterconnectionInformation(map);

		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), Excel.InterconnectionInformation,
				testcaseName);
		objAddInterconnectionInformation.updateInterconnectionDocument(map);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), Excel.InterconnectionInformation,
				testcaseName);
		objAddInterconnectionInformation.deleteInterconnection(map);
		

	}

	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "environmentALT");
	
	}

}
