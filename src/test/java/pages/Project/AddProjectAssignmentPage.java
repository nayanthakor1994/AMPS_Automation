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
	By drpSelectUser = By.xpath("//input[contains(@id,'MSUSERS_YALComboBox_Input')]");
	By drpSelectRole = By.xpath("//input[contains(@id,'PrjRole_radYALDropDownList_Input')]");
	By drpSelectAgent = By.xpath("//input[contains(@id,'ProjectAssignments_CUSERS_YALComboBox_Input')]");
	By btnAdd = By.xpath("//input[@value='Add']");
	By btnUpdate = By.xpath("//input[contains(@id,'EditFormControl_btnUpdate')]");
	By btnEdit = By.xpath("//td[contains(text(),'%s')]/parent::tr/td");
	By drpEdituser = By.xpath("//input[contains(@id,'EditFormControl_USER_ID_radYALDropDownList_Input')]");
	By drpEditRole = By.xpath("//input[contains(@id,'EditFormControl_PROJECT_ROLE_ID_radYALDropDownList_Input')]");
	By drpEditAgent = By.xpath("");

	String tableValue = "//table//tbody//tr//td[text()='%s']";

	public void setUser(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputText(drpSelectUser, value);
			util.pressENTERkey();
		}
	}

	public void setRole(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputText(drpSelectRole, value);
			util.pressENTERkey();
		}
	}

	public void setAgent(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputText(drpSelectAgent, value);
			util.pressENTERkey();
		}
	}

	public void editUser(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputText(drpEdituser, value);
			util.pressENTERkey();
		}
	}

	public void editRole(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputText(drpEditRole, value);
			util.pressENTERkey();
		}
	}

	public void editAgent(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputText(drpEditAgent, value);
			util.pressENTERkey();
		}
	}

	public void editProjectAssignment(String value) {
		By btnEdit = By.xpath("//td[contains(text(),'" + value + "')]/parent::tr/td");
		util.click(btnEdit);
	}

	public void addProjectAssignment(Map<String, String> map) {
		util.click(addProjectAssigment);
		setUser(map.get(Excel.SelectUser));
		setRole(map.get(Excel.SelectRole));
		setAgent(map.get(Excel.SelectAgent));
		util.click(btnAdd);
		if (util.isElementPresent(String.format(tableValue, map.get(Excel.SelectUser)))) {
			ReportsClass.logStat(Status.PASS, "ProjectAssignment is added sucessfully !!!");
		} else {
			ReportsClass.logStat(Status.FAIL, "ProjectAssignment is not added sucessfully !!!");
		}

	}

	public void UpdateProjectAssignment(Map<String, String> map) {
		editProjectAssignment(map.get(Excel.SelectUser));
		editUser(map.get(Excel.EditUser));
		editRole(map.get(Excel.EditRole));
		editAgent(map.get(Excel.EditAgent));
		util.click(btnUpdate);
		if (util.isElementPresent(String.format(tableValue, map.get(Excel.EditUser)))) {
			ReportsClass.logStat(Status.PASS, "ProjectAssignment is Edit sucessfully !!!");
		} else {
			ReportsClass.logStat(Status.FAIL, "ProjectAssignment is not Edit sucessfully !!!");
		}

	}

}
