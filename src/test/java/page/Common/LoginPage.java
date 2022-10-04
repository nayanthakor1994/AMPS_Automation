package page.Common;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.CommonConstant;
import com.base.Excel;
import com.util.ReportsClass;
import com.util.TestUtil;

public class LoginPage extends BasePage {
	TestUtil util;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
	}

	By txtUsername = By.xpath("//input[@id='UCLogin_txtUserName']");
	By txtPassword = By.xpath("//input[@id='UCLogin_txtPassword']");
	By btnLogin = By.xpath("//input[@id='UCLogin_cmdSubmit_login']");
	By isLoginSuccess = By.xpath("//input[@id='ctl00_ContentPlaceHolderHeader_YALHD_LogStatus']");


	public void setUserName(String userName) {
//		if (isALT) {
//			System.out.println("ALT");
//		}
		util.inputText(txtUsername, userName);
		
	}

	public void setPassword(String password) {
		util.inputText(txtPassword, password);
	}

	public void login(Map<String, String> map) {
//		CommonConstant.appURL = map.get("URL");
//		setEnvironment(CommonConstant.appURL);
//		openurl(map.get(Excel.URL));
		setUserName(map.get(Excel.UserName));
		setPassword(map.get(Excel.Password));
		util.click(btnLogin);
		if (isLoginSuccess != null) {
			ReportsClass.logStat(Status.PASS, "Login Successfully !!!");
		} else {
			ReportsClass.logStat(Status.FAIL, "Failed Login !!!");
		}
		Assert.assertTrue((isLoginSuccess != null), "Failed Login !!!");
	}

}

//		util.waitUntilElementDisplay(txtUsername);
//		util.inputText(txtUsername, map.);
//		util.waitUntilElementDisplay(txtPassword);
//		util.inputText(txtPassword, password);
//		util.waitUntilElementDisplay(btnLogin);
//		util.click(btnLogin);
//		if (isLoginSuccess != null) {
//			System.out.println("Login Successfully !!!");
//			ReportsClass.logStat(Status.PASS, "Login Successfully !!!");
//		} else {
//			System.out.println("Failed Login !!!");
//			ReportsClass.logStat(Status.FAIL, "Failed Login !!!");
//		}
//		Assert.assertTrue((isLoginSuccess != null), "Failed Login !!!");
//
//	}
