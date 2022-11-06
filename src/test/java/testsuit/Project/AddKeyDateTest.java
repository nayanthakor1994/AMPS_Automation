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
import pages.Project.AddKeyDatePage;
import pages.Project.UpdateProjectSettingAndMaintancePage;

@Listeners(com.listeners.MyListeners.class)
public class AddKeyDateTest extends BasePage {

	LoginPage objLogin;
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddKeyDatePage objKeyDate;
	CommonFunction commonFunction;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objKeyDate = new AddKeyDatePage(driver);
		commonFunction = new CommonFunction(driver);
	}

	@Test()
	public void Add_a_Key_Date_TC_06() throws Exception {
		log("TC06 : Add a Key Date");
	navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddKey" + environment;
		log("Data picked : " + testcaseName);
		map.clear();
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.KeyDate, testcaseName);
		log("navigating to Add Key Date");
		commonFunction.navigateToProjectDeails();
		objKeyDate.addKeyDate(map,testcaseName);

	}

	@DataProvider(name = "data-provider")
	public Object[][] getTestcaseData() throws Exception {
		return ExcelUtils.getURLFromSheet(prop.getProperty(Excel.excelFileName), Excel.TestCases, "environmentROW");
	}

}
