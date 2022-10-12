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
		try {
			clickOnAddNewRecord();
			log("STEP 1: The panel fields displays", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: The panel does not expand ", Status.FAIL);
		}
		Thread.sleep(3000);
		try {
			setAgencyName(map.get(Excel.AgencyName));
			log("STEP 2: Value added diplays in the Agency Name column field", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Added value does not display in the field. ", Status.FAIL);
		}
		try {
			setPermitNumber(map.get(Excel.PermitNumber));
			log("STEP 3: Value added diplays in the Permit Number column  field", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Added value does not display in the field.  ", Status.FAIL);
		}
		util.click(btnInsert);
		if (util.isElementPresent(String.format(tableValue, map.get(Excel.PermitNumber)))) {
			log("STEP 4: ProjectPermit is added sucessfully", Status.PASS);
			ReportsClass.logStat(Status.PASS, "ProjectPermit is added sucessfully !!!");
		} else {
			log("STEP 4: ProjectPermit is not added sucessfully ", Status.FAIL);
			ReportsClass.logStat(Status.FAIL, "ProjectPermit is not added sucessfully !!!");
		}

	}
	public void updateProjectPermit(Map<String, String> map) {
		editProjectPermit(map.get(Excel.PermitNumber));
		try {
			setComment(map.get(Excel.Comment));
			log("STEP 5: added value diplays in the comment column field ", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: added  value does not displayed in the field ", Status.FAIL);
		}
		util.click(btnUpdate);

		if (util.isElementPresent(String.format(tableValue, map.get(Excel.Comment)))) {
			log("STEP 6: ProjectPermit is Edit sucessfully ", Status.PASS);
			ReportsClass.logStat(Status.PASS, "ProjectPermit is Edit sucessfully !!!");
		} else {
			log("STEP 6: ProjectPermit is not Edit sucessfully ", Status.FAIL);
			ReportsClass.logStat(Status.FAIL, "ProjectPermit is not Edit sucessfully !!!");
		}

	}

	public void addDocument(Map<String, String> map) {
		// TODO Auto-generated method stub

	}

	public void deletProjectPermit(Map<String, String> map) {
		try {
			deleteProjectPermit(map.get(Excel.PermitNumber));
			log("STEP 12:upon popup close, auto refresh the panel to display updated information  ", Status.PASS);
		} catch (Exception e) {
			log("STEP 12: Autorefresh of the panel does not happen ", Status.FAIL);
		}
		util.waitFor(2000);
		try {
			util.click(deleteOk);
			log("STEP 13: Delete popup window Open ", Status.PASS);
		} catch (Exception e) {
			log("STEP 13: The delete pop window does not display ", Status.FAIL);
		}
		util.waitFor(2000);

		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Verify changes saved successfully.");
		if (util.isElementPresent(changesSavedSuccessfully)) {
			log("STEP 14: ProjectPermit is deleted sucessfully ", Status.PASS);
			ReportsClass.logStat(Status.PASS,
					map.get("Interconnection Grid") + ": Interconnection Information is deleted sucessfully !!!");
		} else {
			log("STEP 14: ProjectPermit is not deleted sucessfully ", Status.FAIL);
			ReportsClass.logStat(Status.FAIL,
					map.get("Interconnection Grid") + ": Interconnection Information is not deleted sucessfully !!!");
		}

	}

}
