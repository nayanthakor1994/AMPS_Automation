package pages.Project;

import java.io.File;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.CommonConstant;
import com.util.TestUtil;

import com.util.CommonFunction;
import com.util.ReportsClass;

public class AddInterconnectionInformationPage extends BasePage {
	TestUtil util;
	CommonFunction commonFunction;

	public AddInterconnectionInformationPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	public void setInterconnectionGrid(String interconnectionGrid) {
		util.inputText(drpInterconnectionGrid, interconnectionGrid);
		util.pressENTERkey();
	}

	public void setCost(String cost) {
		util.inputText(txtCost, cost);
	}

	public void updateInterconnectionGrid(String updateName) {
		util.inputText(updateInterConnectionName, updateName);
		util.pressENTERkey();
	}

	public void updateCost(String updateCost) {
		util.inputText(editCost, updateCost);
	}

	public void navigateToProjectDetails() {
		// TODO Auto-generated method stub

	}

	public void deleteInterconnection(String updateName) {
		// TODO Auto-generated method stub

	}

	By tabInterconnectionInformation = By.xpath("*//span[contains(text(),'Interconnection Information')]");
	By btnAddNewRecordInterconnectionInformation = By.xpath(
			"//a[@id='ctl00_ConPHRightTop_radPrjPanels_i3_i0_INTERCONNECT_radYALGridControl_ctl00_ctl02_ctl00_lnkAddNewRecord']");
	By drpInterconnectionGrid = By.xpath(
			"//input[@id='ctl00_ConPHRightTop_radPrjPanels_i3_i0_INTERCONNECT_radYALGridControl_ctl00_ctl02_ctl03_EditFormControl_INTERCONNECT_GRID_ID_radYALDropDownList_Input']");
	By txtCost = By.xpath(
			"//input[@id='ctl00_ConPHRightTop_radPrjPanels_i3_i0_INTERCONNECT_radYALGridControl_ctl00_ctl02_ctl03_EditFormControl_BASE_COST']");
	By btnInsertInterconnectionInformation = By.xpath(
			"//input[@id='ctl00_ConPHRightTop_radPrjPanels_i3_i0_INTERCONNECT_radYALGridControl_ctl00_ctl02_ctl03_EditFormControl_btnInsert']");

	By btnAddDocument = By.cssSelector("#btnAdddocuments");
	By drpCategory = By.cssSelector("#RadUpload1category0");
	By txtDescription = By.cssSelector("#RadUpload1Desc0");
	By documentFileUpload = By.cssSelector("#RadUpload1file0");
	By loadDocumentFile = By.cssSelector("#buttonSubmit");
	By documentSuccessMessage = By.xpath("//span[@id='lblResults']");
	By btnDocumentClose = By.xpath("//a[@title='Close']");
	
	By documentIframe = By.xpath("//iframe[@name='ViewEditDocument']");
	By documentAddIframe = By.xpath("//iframe[@name='AddDocuments']");
	String viewEditDocument = "//div[contains(@id,'radYALGridControl')]//td[contains(.,'%s')]/following-sibling::td//a[text()='View/Edit Document']";
	String editInterconnection = "//div[contains(@id,'radYALGridControl')]//td[contains(.,'%s')]/preceding-sibling::td//input[contains(@id,'EditButton')]";
	String deleteInterconnection = "//div[contains(@id,'radYALGridControl')]//td[contains(.,'%s')]/preceding-sibling::td//input[contains(@id,'DeleteAlignment')]";
	By btnUpdateInterconnection = By.xpath("//input[contains(@id,'btnUpdate')]");
	By updateInterConnectionName = By
			.xpath("//input[contains(@name,'EditFormControl$INTERCONNECT_GRID_ID$radYALDropDownList')]");
	By editCost = By.xpath("//input[contains(@name,'EditFormControl$BASE_COST')]");
	String tableRecord = "//table[@id='ctl00_ConPHRightTop_radPrjPanels_i3_i0_INTERCONNECT_radYALGridControl_ctl00']//tbody//tr//td[contains(.,'%s')]";
	By changesSavedSuccessfully = By.xpath("//span[text()='Changes saved successfully!']");

	
	By deleteOk = By.xpath("//a[contains(@onClick,'confirm')]//span[text()='OK']");
	public void addInterconnectionInformation(Map<String, String> map) {
		commonFunction.navigateToProjectDeails();
		util.waitUntilElementDisplay(tabInterconnectionInformation);
		util.click(tabInterconnectionInformation);
		util.waitUntilElementDisplay(btnAddNewRecordInterconnectionInformation);
		util.click(btnAddNewRecordInterconnectionInformation);

		setInterconnectionGrid(map.get("Interconnection Grid"));
		setCost(map.get("Cost"));

		// Click on Insert Button
		util.waitUntilElementDisplay(btnInsertInterconnectionInformation);
		util.click(btnInsertInterconnectionInformation);

		// Verify Interconnection Information Saved Successfully
		util.waitUntilElementDisplay(By.xpath("*//div//span[contains(text(),'Changes saved successfully!')]"));
		String getInterconnectionInfoSuccessMsg = driver
				.findElement(By.xpath("*//div//span[contains(text(),'Changes saved successfully!')]")).getText().trim();
		if (getInterconnectionInfoSuccessMsg.contains("Changes saved successfully!")) {
			System.out.println("Interconnection Information Saved Successfully !!!");
			ReportsClass.logStat(Status.PASS,
					map.get("Interconnection Grid") + ": Interconnection Information Saved Successfully !!!");
		} else {
			System.out.println("Failed to Save Interconnection Information !!!");
			ReportsClass.logStat(Status.FAIL, map.get("Cost") + ": Failed to Save Interconnection Information !!!");
		}
		Assert.assertTrue(getInterconnectionInfoSuccessMsg.contains("Changes saved successfully!"),
				"Failed to Save Interconnection Information !!!");
		Assert.assertTrue(util.isElementPresent(String.format(tableRecord, map.get("Interconnection Grid"))));
		CommonConstant.addedName = map.get("Interconnection Grid");
		CommonConstant.addedCost = map.get("Cost");

	}

	public void updateInterconnectionInformation(Map<String, String> map) {
		By viewEditBtn = By.xpath(String.format(editInterconnection, CommonConstant.addedName));
		util.waitUntilElementDisplay(viewEditBtn);
		util.click(viewEditBtn);

		updateInterconnectionGrid(map.get("Interconnection Grid"));
		updateCost(map.get("Cost"));
		util.waitUntilElementDisplay(btnUpdateInterconnection);
		util.click(btnUpdateInterconnection);

		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Verify changes saved successfully.");
		if (util.isElementPresent(changesSavedSuccessfully)) {
			ReportsClass.logStat(Status.PASS,
					map.get("Interconnection Grid") + ": Interconnection information updated successfully");
		} else {
			ReportsClass.logStat(Status.FAIL,
					map.get("Interconnection Grid") + ": Interconnection information does not updated successfully");
		}
		Assert.assertTrue(util.isElementPresent(String.format(tableRecord, map.get("Interconnection Grid"))));
		CommonConstant.addedName = map.get("Interconnection Grid");
		CommonConstant.addedCost = map.get("Cost");
		util.waitFor(2000);

	}

	public void updateInterconnectionDocument(Map<String, String> map) {
		String filepath = System.getProperty("user.dir") + File.separator + "test.txt";
		By viewEditBtn = By.xpath(String.format(viewEditDocument, map.get("Interconnection Grid")));
		util.waitUntilElementDisplay(viewEditBtn);
		util.click(viewEditBtn);
		
		util.waitUntilElementDisplay(documentIframe);
		util.switchToIframe(documentIframe);
		util.waitUntilElementDisplay(btnAddDocument);
		util.click(btnAddDocument);
		util.switchToIframe(documentAddIframe);
		util.waitUntilElementDisplay(drpCategory);
		util.selectValueFromDropdown("test doc cat", drpCategory);
		util.inputText(txtDescription, "Test Automation");
		driver.findElement(documentFileUpload).sendKeys(filepath);
		util.waitFor(2000);
		util.click(loadDocumentFile);
		
		util.waitUntilElementDisplay(documentSuccessMessage);
		if(util.getText(documentSuccessMessage).contains("Loaded: test.txt")){
			ReportsClass.logStat(Status.PASS, map.get("Interconnection Grid") + ": Document Saved Successfully !!!");
		} else {
			ReportsClass.logStat(Status.FAIL, map.get("Interconnection Grid") + ": Document does not saved Successfully !!!");
		}
		util.switchToDefaultContent();
		util.click(btnDocumentClose);

	}

	public void deleteInterconnection(Map<String, String> map) {
		By viewDeleteBtn = By.xpath(String.format(deleteInterconnection, map.get("Interconnection Grid")));
		util.waitUntilElementDisplay(viewDeleteBtn);
		util.click(viewDeleteBtn);
		
		util.waitFor(2000);
		util.click(deleteOk);
		util.waitFor(2000);

		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Verify changes saved successfully.");
		if (util.isElementPresent(changesSavedSuccessfully)) {
			ReportsClass.logStat(Status.PASS, map.get("Interconnection Grid") + ": Interconnection Information is deleted sucessfully !!!");
		} else {
			ReportsClass.logStat(Status.FAIL, map.get("Interconnection Grid") + ": Interconnection Information is not deleted sucessfully !!!");
		}
		
	}

}
