package pages.Project;

import java.io.File;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.CommonConstant;
import com.util.CommonFunction;
import com.util.TestUtil;

public class AddInterconnectionInformationPage extends BasePage {
	TestUtil util;
	CommonFunction commonFunction;

	public AddInterconnectionInformationPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	public void setInterconnectionGrid(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(drpInterconnectionGrid, value);
		util.pressENTERkey();
	}

	public void setCost(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(txtCost, value);
	}

	public void updateInterconnectionGrid(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(updateInterConnectionName, value);
		util.pressENTERkey();
	}

	public void updateCost(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(editCost, value);
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

	public void addInterconnectionInformation(Map<String, String> map) throws InterruptedException {
		commonFunction.navigateToProjectDeails();
		util.waitUntilElementDisplay(tabInterconnectionInformation);
		util.click(tabInterconnectionInformation);
		util.waitUntilElementDisplay(btnAddNewRecordInterconnectionInformation);
		try {
			util.click(btnAddNewRecordInterconnectionInformation);
			log("STEP 1: The panel fields displays.", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:  The panel does not expand.", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}

		try {
			setInterconnectionGrid(map.get("Interconnection Grid"));
			log("STEP 2: Value added diplays in the Interconnection grid column field.", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  Added value does not display in the field. ", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		try {
			setCost(map.get("Cost"));
			log("STEP 3: Amount added diplays in the cost column field. ", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  Added value does not display in the field.  ", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}

		// Click on Insert Button
		util.waitUntilElementDisplay(btnInsertInterconnectionInformation);
		try {
			util.click(btnInsertInterconnectionInformation);
			log("STEP 4: The added values displays in the grid", Status.PASS);
		} catch (Exception e) {
			log("STEP 4:  Added values does  not displayed in the grid  ", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}

		// Verify Interconnection Information Saved Successfully
		util.waitUntilElementDisplay(By.xpath("*//div//span[contains(text(),'Changes saved successfully!')]"));
		String getInterconnectionInfoSuccessMsg = driver
				.findElement(By.xpath("*//div//span[contains(text(),'Changes saved successfully!')]")).getText().trim();
		if (getInterconnectionInfoSuccessMsg.contains("Changes saved successfully!")) {
			System.out.println("Interconnection Information Saved Successfully !!!");
			log("STEP 4: Interconnection Information Saved Successfully ", Status.PASS);
		} else {
			System.out.println("Failed to Save Interconnection Information !!!");
			log("STEP 4:  Failed to Save Interconnection Information  ", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		Assert.assertTrue(getInterconnectionInfoSuccessMsg.contains("Changes saved successfully!"),
				"Failed to Save Interconnection Information !!!");
		Assert.assertTrue(util.isElementPresent(String.format(tableRecord, map.get("Interconnection Grid"))));
		CommonConstant.addedName = map.get("Interconnection Grid");
		CommonConstant.addedCost = map.get("Cost");

	}

	public void updateInterconnectionInformation(Map<String, String> map) throws InterruptedException {
		By viewEditBtn = By.xpath(String.format(editInterconnection, map.get("EditInterconnection Grid")));
		util.waitUntilElementDisplay(viewEditBtn);
		try {
			util.click(viewEditBtn);
			log("STEP 5: added value diplays in the comment field  ", Status.PASS);
		} catch (Exception e) {
			log("STEP 5:  added  value does not displayed in the field. OR 2) Cannot add values in the text field  ",
					Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}

		try {
			updateInterconnectionGrid(map.get("EditInterconnection Grid"));
			log("Changed value  diplays in the grid ", Status.PASS);
		} catch (Exception e) {
			log("Update value does not display in the grid.", Status.FAIL);
			throw new RuntimeException("Update value does not display in the grid");

		}
		try {
			updateCost(map.get("EditCost"));
			log("Changed value Cost diplays in the grid ", Status.PASS);
		} catch (Exception e) {
			log("Update value does not display in the grid  ", Status.FAIL);
			throw new RuntimeException("Update value does not display in the grid");
		}
		util.waitUntilElementDisplay(btnUpdateInterconnection);
		util.click(btnUpdateInterconnection);

		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Verify changes saved successfully.");
		if (util.isElementPresent(changesSavedSuccessfully)) {
			log("STEP 6: Interconnection information updated successfully ", Status.PASS);
		} else {
			log("STEP 6:  Interconnection information does not updated successfully  ", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		Assert.assertTrue(util.isElementPresent(String.format(tableRecord, map.get("EditInterconnection Grid"))));
		/*
		 * CommonConstant.addedName = map.get("EditInterconnection Grid");
		 * CommonConstant.addedCost = map.get("EditCost");
		 */
		util.waitFor(2000);

	}

	public void updateInterconnectionDocument(Map<String, String> map) {
		String filepath = System.getProperty("user.dir") + File.separator + "test.txt";
		By viewEditBtn = By.xpath(String.format(viewEditDocument, map.get("Interconnection Grid")));
		util.waitUntilElementDisplay(viewEditBtn);
		try {
			util.click(viewEditBtn);
			log("STEP 7: Document popup window Opened ", Status.PASS);
		} catch (Exception e) {
			log("STEP 7:  The document pop window does not display  ", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}

		util.waitUntilElementDisplay(documentIframe);
		util.switchToIframe(documentIframe);
		util.waitUntilElementDisplay(btnAddDocument);
		util.click(btnAddDocument);
		util.switchToIframe(documentAddIframe);
		util.waitUntilElementDisplay(drpCategory);
		try {
			util.selectValueFromDropdown2("test doc cat", drpCategory);
			log("STEP 8: Value added diplays in the field : Category ", Status.PASS);
		} catch (Exception e) {
			log("STEP 8:  Added value does not display in the field : Category  ", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
		try {
			util.inputText(txtDescription, "Test Automation");
			log("STEP 9: User can enter a value in the field    ", Status.PASS);
		} catch (Exception e) {
			log("STEP 9:  User cannot add a value in the field   ", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}
		driver.findElement(documentFileUpload).sendKeys(filepath);
		util.waitFor(2000);
		try {
			util.click(loadDocumentFile);
			log("STEP 10: User can upload document from the system  ", Status.PASS);
		} catch (Exception e) {
			log("STEP 10:  User cannot upload a document  ", Status.FAIL);
			throw new RuntimeException("Failed in step 10");
		}

		util.waitUntilElementDisplay(documentSuccessMessage);
		if (util.getText(documentSuccessMessage).contains("Loaded: test.txt")) {
			log("STEP 11: Document Saved Successfully ", Status.PASS);
		} else {
			log("STEP 11:  Document does not saved Successfully  ", Status.FAIL);
			throw new RuntimeException("Failed in step 11");
		}
		util.switchToDefaultContent();
		try {
			log("STEP 12: upon popup close, auto refresh the panel to display updated information ", Status.PASS);
			util.click(btnDocumentClose);
		} catch (Exception e) {
			log("STEP 12:  Autorefresh of the panel does not happen  ", Status.FAIL);
			throw new RuntimeException("Failed in step 12");
		}

	}

	public void deleteInterconnection(Map<String, String> map) {
		By viewDeleteBtn = By.xpath(String.format(deleteInterconnection, map.get("Interconnection Grid")));
		util.waitUntilElementDisplay(viewDeleteBtn);
		try {
			util.click(viewDeleteBtn);
			log("STEP 13:  Delete popup window should display ", Status.PASS);
		} catch (Exception e1) {
			log("STEP 13:  The delete pop window does not display  ", Status.FAIL);
			throw new RuntimeException("Failed in step 13");
		}

		util.waitFor(2000);
		try {
			util.click(deleteOk);
			log("STEP 14: Deleted record does not display in the grid and ", Status.PASS);
		} catch (Exception e) {
			log("STEP 14:  Record does not display in grid  ", Status.FAIL);
			throw new RuntimeException("Failed in step 14");
		}
		util.waitFor(2000);

		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Verify changes saved successfully.");
		if (util.isElementPresent(changesSavedSuccessfully)) {
			log(" Interconnection Information is deleted sucessfully  ", Status.PASS);
		} else {
			log("Interconnection Information is not deleted sucessfully", Status.FAIL);
			throw new RuntimeException("Interconnection Information is not deleted sucessfully");
		}

	}

}
