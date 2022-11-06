package pages.Project;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
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
	By drpSelectAgent = By.xpath("(//div[contains(@id,'ProjectAssignments_DDTMsUsers_RadDropDownTree1')])[2]");
	By btnAddDOT = By.xpath("//input[contains(@name,'ProjectAssignment$AddBulkUsers')]");
	By btnAdd = By.xpath("//input[@alt='Add']");
	By btnUpdate = By.xpath("//input[contains(@id,'EditFormControl_btnUpdate')]");
	By btnEdit = By.xpath("//td[contains(text(),'%s')]/parent::tr/td");
	By drpEdituser = By.xpath("//input[contains(@id,'EditFormControl_USER_ID_radYALDropDownList_Input')]");
	By drpEditRole = By.xpath("//input[contains(@id,'EditFormControl_PROJECT_ROLE_ID_radYALDropDownList_Input')]");
	By drpEditAgent = By.xpath("//input[contains(@id,'EditFormControl_USER_ID_radYALDropDownList_Input')]");
	By sideclick = By.xpath("//a[normalize-space()='Project Assignments']//following::tr[1]");
	String tableValue = "//table//tbody//tr//td[text()='%s']";
	By drpUserClose = By.xpath(
			"//a[contains(@id,'ProjectAssignment_MSUSERS_YALComboBox_Arrow')]");

	By clickOnAgent = By.xpath("//span[text()='Select Agents']");
	By addAgent = By.xpath("(//input[contains(@value,'Filtering...')])[1]");
	By closeAgent = By.xpath("//div[@id='ctl00_ConPHRightTop_PRDT_UC_radPrjPanels_i5_i0_ProjectAssignments_DDTMsUsers_RadDropDownTree1']//span[@class='rddtIcon']");
	public void setUser(String value) {
		if (!commonFunction.checkNA(value)) {
			util.selectValueFromDropdown(drpSelectUser, value);
			util.click(drpUserClose);
		}
	}
	
	public void setAgent(String value) {
		if (!commonFunction.checkNA(value)) {
			util.click(clickOnAgent);
			util.inputText(addAgent, value);
			By selectAgent = By.xpath("//em[contains(text(),'"+value+"')]/../../span[@class='rtUnchecked']");
			util.click(selectAgent);
			util.click(closeAgent);
		}
	}

	public void setRole(String value) {
		if (!commonFunction.checkNA(value)) {
			util.selectValueFromDropdown(drpSelectRole, value);
			//util.inputText(drpSelectRole, value);
			util.pressENTERkey();
		}
	}



	public void editUser(String value) {
		if (!commonFunction.checkNA(value)) {
			util.selectValueFromDropdown(drpEdituser, value);
			util.pressENTERkey();
		}
	}
	public void editAgent(String value) {
		if (!commonFunction.checkNA(value)) {
			util.selectValueFromDropdown(drpEditAgent, value);
			util.pressENTERkey();
		}
	}

	public void editRole(String value) {
		if (!commonFunction.checkNA(value)) {
			util.selectValueFromDropdown(drpEditRole, value);
			util.pressENTERkey();
		}
	}

	public void clickOnAdd() {
		try {
			util.click(btnAddDOT);
		} catch (Exception e) {
			util.click(btnAdd);
		}
	}
	
	public void clickUpdate() {
		util.click(btnUpdate);
	}

	public void editProjectAssignment(String value) throws InterruptedException {
		By btnEdit = By.xpath("//td[contains(text(),'" + value + "')]/parent::tr/td");
		util.waitUntilElementDisplay(btnEdit);
		util.waitForWebElementToBeClickable(btnEdit);
		util.click(btnEdit);
	}

	public void addProjectAssignment(Map<String, String> map,String testCaseName) throws InterruptedException {
			util.click(addProjectAssigment);
			if(testCaseName.equals("ProjectAssignmentROW"))
			{
				try {
					setAgent(map.get(Excel.SelectAgent));
					log("STEP 1: Value added diplays in the field", Status.PASS);
				} catch (Exception e) {
					log("STEP 1: Added value does not display in the field ", Status.FAIL);
					throw new RuntimeException("Failed in step 1");
				}
			}
			else {
				try {
					setUser(map.get(Excel.SelectUser));
					log("STEP 1: Value added diplays in the field", Status.PASS);
				} catch (Exception e) {
					log("STEP 1: Added value does not display in the field ", Status.FAIL);
					throw new RuntimeException("Failed in step 1");
				}
				
			}
		
		try {
			setRole(map.get(Excel.SelectRole));
			log("STEP 1: Value added diplays in the field", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Added value does not display in the field ", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		if(testCaseName.equals("ProjectAssignmentDOT")) {
			util.click(btnAddDOT);
		}
		else {
			util.click(btnAdd);

		}
		util.dummyWait(5);
		if (util.isElementPresent(String.format(tableValue, map.get(Excel.SelectRole)))) {
			log("STEP 2: ProjectAssignment is added sucessfully", Status.PASS);
		} else {
			log("STEP 2: ProjectAssignment is not added sucessfully ", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}

	}

	public void UpdateProjectAssignment(Map<String, String> map) throws InterruptedException {
		try {
			editProjectAssignment(map.get(Excel.SelectRole));
			log("STEP 3: The updated  values sets/displays in the field ", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  Updated values does  not displayed in the field.  ", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		util.dummyWait(2);
		try {
			editRole(map.get(Excel.EditRole));
			log("STEP 4:The updated  values sets/displays in the field", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Updated values does  not displayed in the field ", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
			// TODO Auto-generated catch block
		}
		clickUpdate();
		util.dummyWait(5);
		if (util.isElementPresent(String.format(tableValue, map.get(Excel.EditRole)))) {
			log("STEP 5: User can navigate to the Project details", Status.PASS);
		} else {
			log("STEP 5: ProjectAssignment is not added sucessfully ", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}

	}

}
