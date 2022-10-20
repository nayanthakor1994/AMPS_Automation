package pageTest.common;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.util.CommonFunction;
import com.util.ExcelUtils;

import page.Common.LoginPage;

public class LoginTest extends BasePage {

	LoginPage objLogin;
	CommonFunction commonFunction;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		 driver = getDriver();
		objLogin = new LoginPage(driver);
	}

	@Test
	public void loginToApplication() {
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty("EXCEL_TEST_DATA"), "Login", "1");
		objLogin.login(map);
	}

}
