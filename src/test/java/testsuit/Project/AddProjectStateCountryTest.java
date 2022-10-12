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
import pages.Project.AddProjectStateCountryPage;

@Listeners(com.listeners.MyListeners.class)
public class AddProjectStateCountryTest extends BasePage {
	LoginPage objLogin;
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddProjectStateCountryPage objCityState;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objCityState = new AddProjectStateCountryPage(driver);
	}

	@Test(dataProvider = "data-provider")
	public void add_Project_State_Country_TC_02(String testName, String appURL, String env) throws Exception {
		log("TC02 : Add a Project State and County");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.Login, "1");
		objLogin.login(map);
		String testcaseName = "AddCountryState" + env;
		log("Data picked : " + testcaseName);
		log("navigating to create City and State");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.CountryState, testcaseName);
		objCityState.addStateAndContryInformation(map);
		objCityState.updateStateAndContryInformation(map);

	}

	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "environmentALT");
	}

}
