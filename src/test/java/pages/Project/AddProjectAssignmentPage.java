package pages.Project;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.ReportsClass;
import com.util.TestUtil;

public class AddProjectAssignmentPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public AddProjectAssignmentPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By addProjectAssigment = By.xpath("//a[normalize-space()='Project Assignments']");
	By drpSelectUser = By.xpath("//input[contains(@id,'YALComboBox_Input')]");
	By drpSelectRole = By.xpath("//input[contains(@id,'PrjRole_radYALDropDownList_Input')]");
	By drpSelectAgent = By.xpath("//input[contains(@id,'YALComboBox_Input')]");
	By btnAdd = By.xpath("//input[@alt='Add']");
	By btnAdd2 = By.xpath("//input[@value='Add']");
	By btnUpdate = By.xpath("//input[contains(@id,'EditFormControl_btnUpdate')]");
	By btnEdit = By.xpath("//td[contains(text(),'%s')]/parent::tr/td");
	By drpEdituser = By.xpath("//input[contains(@id,'EditFormControl_USER_ID_radYALDropDownList_Input')]");
	By drpEditRole = By.xpath("//input[contains(@id,'EditFormControl_PROJECT_ROLE_ID_radYALDropDownList_Input')]");
	By drpEditAgent = By.xpath("//input[contains(@id,'EditFormControl_USER_ID_radYALDropDownList_Input')]");

	String tableValue = "//table//tbody//tr//td[text()='%s']";

	public void setUser(String value) {
		if (!commonFunction.checkNA(value)) {
			util.selectValueFromDropdown(drpSelectUser, value);
			//util.inputText(drpSelectUser, value);
			util.pressENTERkey();
		}
	}

	public void setRole(String value) {
		if (!commonFunction.checkNA(value)) {
			util.selectValueFromDropdown(drpSelectRole, value);
			//util.inputText(drpSelectRole, value);
			util.pressENTERkey();
		}
	}

//	public void setAgent(String value) {
//		if (!commonFunction.checkNA(value)) {
//			util.selectValueFromDropdown(drpSelectAgent, value);
//		//	util.inputText(drpSelectAgent, value);
//			util.pressENTERkey();
//		}
//	}

	public void editUser(String value) {
		if (!commonFunction.checkNA(value)) {
			util.selectValueFromDropdown(drpEdituser, value);
			//util.inputText(drpEdituser, value);
			util.pressENTERkey();
		}
	}

	public void editRole(String value) {
		if (!commonFunction.checkNA(value)) {
			util.selectValueFromDropdown(drpEditRole, value);
			//util.inputText(drpEditRole, value);
			util.pressENTERkey();
		}
	}

//	public void editAgent(String value) {
//		if (!commonFunction.checkNA(value)) {
//			util.selectValueFromDropdown(drpEditAgent, value);
//		//	util.inputText(drpEditAgent, value);
//			util.pressENTERkey();
//		}
//	}
	public void clickOnAdd() {
		try {
			util.click(btnAdd);
		} catch (Exception e) {
			util.click(btnAdd2);
		}
	}
	
	public void clickUpdate() {
		util.click(btnUpdate);
	}

	public void editProjectAssignment(String value) throws InterruptedException {
		Thread.sleep(10000);
		By btnEdit = By.xpath("//td[contains(text(),'" + value + "')]/parent::tr/td");
		util.waitUntilElementDisplay(btnEdit);
		util.waitForWebElementToBeClickable(btnEdit);
		util.click(btnEdit);
	}

	public void addProjectAssignment(Map<String, String> map) throws InterruptedException {
			util.click(addProjectAssigment);
		try {
			setUser(map.get(Excel.SelectUser));
			log("STEP 1: Value added diplays in the field", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Added value does not display in the field ", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		try {
			setRole(map.get(Excel.SelectRole));
			log("STEP 1: Value added diplays in the field", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Added value does not display in the field ", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
//		try {
//			setAgent(map.get(Excel.SelectAgent));
//			log("STEP 1: User can navigate to the Project details", Status.PASS);
//		} catch (Exception e) {
//			log("STEP 1: User cannot see the option in Menu ", Status.FAIL);
//			throw new RuntimeException("Failed in step 1");
//		}
		clickOnAdd();
		Thread.sleep(5000);
		if (util.isElementPresent(String.format(tableValue, map.get(Excel.SelectRole)))) {
			log("STEP 2: ProjectAssignment is added sucessfully", Status.PASS);
			ReportsClass.logStat(Status.PASS, "ProjectAssignment is added sucessfully !!!");
		} else {
			log("STEP 2: ProjectAssignment is not added sucessfully ", Status.FAIL);
			ReportsClass.logStat(Status.FAIL, "ProjectAssignment is not added sucessfully !!!");
			throw new RuntimeException("Failed in step 2");
		}

	}

	public void UpdateProjectAssignment(Map<String, String> map) throws InterruptedException {
		try {
			editProjectAssignment(map.get(Excel.SelectUser));
			log("STEP 3: The updated  values sets/displays in the field ", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  Updated values does  not displayed in the field.  ", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
	//	editUser(map.get(Excel.EditUser));
		try {
			editRole(map.get(Excel.EditRole));
			log("STEP 4:The updated  values sets/displays in the field", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Updated values does  not displayed in the field ", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
			// TODO Auto-generated catch block
		}
//		editAgent(map.get(Excel.EditAgent));
		clickUpdate();
		Thread.sleep(5000);
		if (util.isElementPresent(String.format(tableValue, map.get(Excel.EditRole)))) {
			log("STEP 5: User can navigate to the Project details", Status.PASS);
			ReportsClass.logStat(Status.PASS, "ProjectAssignment is Edit sucessfully !!!");
		} else {
			log("STEP 5: ProjectAssignment is not added sucessfully ", Status.FAIL);
			ReportsClass.logStat(Status.FAIL, "ProjectAssignment is not Edit sucessfully !!!");
			throw new RuntimeException("Failed in step 5");
		}

	}

}
