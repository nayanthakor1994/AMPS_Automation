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
import pages.Project.AddProjectCityStatePage;


@Listeners(com.listeners.MyListeners.class)
public class AddProjectCityStateTest extends BasePage {
	LoginPage objLogin;
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddProjectCityStatePage objCityState;
	Map<String, String> map = new HashMap<String, String>();
	

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objCityState = new AddProjectCityStatePage(driver);
	}

	@Test(dataProvider = "data-provider")
	public void add_Project_State_Country_TC_02(String testName, String appURL) throws Exception {
		setEnvironment(appURL);
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.Login, "1");
		objLogin.login(map);
		log("navigating to create City and State");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), "CountryState", "New");
		objCityState.addStateAndContryInformation(map);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName),"CountryState","Update");
		objCityState.updateStateAndContryInformation(map);

	}
	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "environment");
	}

}
