package pages.Project;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class AddKeyDatePage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public AddKeyDatePage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By btnProjectSchedule = By.xpath(
			"//a[normalize-space()='Project Schedule' or normalize-space()='Project Key Dates (Milestones)']");
	By btnProjectScheduleDOT = By.xpath(
			"//a[normalize-space()='Project Key Dates (milestones)']");
	By editProjectSchedule = By.xpath("//table//tbody//tr//td//input[contains(@name,'PRJKEYDATES$radYALGridControl$ctl00$ctl04$EditButton')]");
	By txtExpectedDate = By.xpath("//input[contains(@id,'EditFormControl_Expected_Date_dateInput')][1]");
	By txtActualDate = By.xpath("//input[contains(@name,'EditFormControl$Actual_Date$dateInput')]");
	By txtExpectedEnd = By.xpath("//input[contains(@name,'Expected_End_Date$dateInput')]");
	By txtActualEnd = By.xpath("//input[contains(@name,'Actual_End_Date$dateInput')]");
	By btnUpdate = By.xpath("//input[contains(@name,'EditFormControl$btnUpdate')]");

	By successMessage = By.xpath("*//div//span[contains(text(),'Changes saved successfully!')]");

	public void clickProjectSchedule(String TestCaseName) {
		try {
			util.click(btnProjectSchedule);
		} catch (Exception e) {
			util.click(btnProjectScheduleDOT);
		}
		
	}

	public void editPrjectSchecule() {
		
			util.click(editProjectSchedule);
	}
	public void setExpectedDate(String value) {
		util.inputText(txtExpectedDate, value);
	}

	public void setActualDate(String value) {
		util.inputText(txtActualDate, value);
	}

	public void setExpectedEnd(String value) {
		util.inputText(txtExpectedEnd, value);

	}

	public void setAcutalEnd(String value) {
		util.inputText(txtActualEnd, value);
	}

	public void clickUpdate() {
		util.click(btnUpdate);
	}

	//table[contains(@id,'PRJKEYDATES_ygccontainTable')]
	//ctl00_ConPHRightTop_radPrjPanels_i6_i0_PRJKEYDATES_radYALGridControl
	
	//*[@id="ctl00_ConPHRightTop_radPrjPanels_i6_i0_PRJKEYDATES_radYALGridControl_ctl00"]
	
	//a[normalize-space()='Project Schedule' or normalize-space()='Project Key Dates (Milestones)']//following-sibling::div//following::table[contains(@id,'PRJKEYDATES_radYALGridControl_ctl00')]/..
	
	public void addKeyDate(Map<String, String> map,String TestcaseName) {
		// TODO Auto-generated method stub
		if (TestcaseName.equals("AddKeyDOT")) {
			util.click(btnProjectScheduleDOT);
		} else {
			util.click(btnProjectSchedule);
		}
		//clickProjectSchedule(String TestcaseName);
		try {
			editPrjectSchecule();
			log("STEP 1:  The panel fields displays", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: The panel does not expand  ", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		setExpectedDate(map.get(Excel.ExpectedDate));
		setActualDate(map.get(Excel.ActualDate));
		setExpectedEnd(map.get(Excel.ExpectedEnd));
		setAcutalEnd(map.get(Excel.ActualEnd));
		clickUpdate();

		// Verify Add Key Date Changes Successfuly
		util.waitUntilElementDisplay(successMessage);
		String getEditRotaltyProcessMsg = driver
				.findElement(By.xpath("*//div//span[contains(text(),'Changes saved successfully!')]")).getText().trim();
		if (getEditRotaltyProcessMsg.contains("Changes saved successfully!")) {
			System.out.println("Add Key Date Changes Successfully !!!");
			log("STEP 2:  Add Key Date Changes Successfullys", Status.PASS);
		} else {
			System.out.println("Failed to Add Key Date !!!");
			log("STEP 2: Failed to Add Key Date  ", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		Assert.assertTrue(getEditRotaltyProcessMsg.contains("Changes saved successfully!"),
				"Failed to Add Key Date !!!");

	}

}
