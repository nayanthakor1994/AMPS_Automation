package pages.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.util.CommonFunction;
import com.util.TestUtil;

public class MyDashboardPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction ;

	public MyDashboardPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}
	
	By tabRequestedDocument = By.xpath("//span[text()='Requested Documents']");
	
	public void navigateToRequestedDocumentTab() {
		util.click(tabRequestedDocument);
	}
	
	public boolean isJobCreated(String status) {
		By jobPath = By.xpath("//table[contains(@id,'Queue_grdDocumentQueue')]//td[text()='"+status+"']");
		return util.isElementPresent(jobPath);
	}
}
