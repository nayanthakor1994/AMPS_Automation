package pages.Project;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.ReportsClass;
import com.util.TestUtil;

public class AddProjectPermitPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public AddProjectPermitPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By PrjectPermit = By.xpath("//a[normalize-space()='Project Permits']");
	By addProjectPermit = By.xpath("//*[normalize-space()='Project Permits']/..//a[normalize-space()='Add new record']");
	By drpAgentName = By.xpath("//input[contains(@id,'EditFormControl_AGENCY_ID_radYALDropDownList_Input')]");
	By txtPermitNumber = By.xpath("//input[contains(@name,'EditFormControl$PERMIT_NUMBER')]");
	By txtComment = By.xpath("//textarea[contains(@id,'EditFormControl_COMMENTS')]");
	By btnInsert = By.xpath("//input[@value='Insert']");
	By btnUpdate = By.xpath("//input[@value='Update']");
	By deleteOk = By.xpath("//a[contains(@onClick,'confirm')]//span[text()='OK']");
	By changesSavedSuccessfully = By.xpath("//span[text()='Changes saved successfully!']");

	String tableValue = "//table//tbody//tr//td[text()='%s']";

	public void clickOnAddNewRecord() {
		util.click(addProjectPermit);
	}
	public void setAgencyName(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(drpAgentName, value);
		util.pressENTERkey();
	}

	public void setPermitNumber(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(txtPermitNumber, value);
	}

	public void setComment(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(txtComment, value);
	}

	public void editProjectPermit(String value) {
		By btnEdit = By.xpath("//td[contains(text(),'" + value + "')]/parent::tr/td/input[@title='Edit']");
		util.click(btnEdit);
	}

	public void deleteProjectPermit(String value) {
		By btnDelete = By.xpath("//td[contains(text(),'" + value + "')]/parent::tr/td/input[@title='Delete']");
		util.click(btnDelete);
	}
	public void addProjectPermit(Map<String, String> map) throws InterruptedException {
		util.click(PrjectPermit);
		clickOnAddNewRecord();
		Thread.sleep(3000);
		setAgencyName(map.get(Excel.AgencyName));
		setPermitNumber(map.get(Excel.PermitNumber));
		util.click(btnInsert);
		if (util.isElementPresent(String.format(tableValue, map.get(Excel.PermitNumber)))) {
			ReportsClass.logStat(Status.PASS, "ProjectPermit is added sucessfully !!!");
		} else {
			ReportsClass.logStat(Status.FAIL, "ProjectPermit is not added sucessfully !!!");
		}

	}
	public void updateProjectPermit(Map<String, String> map) {
		editProjectPermit(map.get(Excel.PermitNumber));
		setComment(map.get(Excel.Comment));
		util.click(btnUpdate);

		if (util.isElementPresent(String.format(tableValue, map.get(Excel.Comment)))) {
			ReportsClass.logStat(Status.PASS, "ProjectPermit is Edit sucessfully !!!");
		} else {
			ReportsClass.logStat(Status.FAIL, "ProjectPermit is not Edit sucessfully !!!");
		}

	}

	public void addDocument(Map<String, String> map) {
		// TODO Auto-generated method stub

	}

	public void deletProjectPermit(Map<String, String> map) {
		deleteProjectPermit(map.get(Excel.PermitNumber));
		util.waitFor(2000);
		util.click(deleteOk);
		util.waitFor(2000);

		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Verify changes saved successfully.");
		if (util.isElementPresent(changesSavedSuccessfully)) {
			ReportsClass.logStat(Status.PASS,
					map.get("Interconnection Grid") + ": Interconnection Information is deleted sucessfully !!!");
		} else {
			ReportsClass.logStat(Status.FAIL,
					map.get("Interconnection Grid") + ": Interconnection Information is not deleted sucessfully !!!");
		}

	}

}
