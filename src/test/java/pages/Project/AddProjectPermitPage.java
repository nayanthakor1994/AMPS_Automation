package pages.Project;

import java.io.File;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
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
	By addProjectPermit = By
			.xpath("//*[normalize-space()='Project Permits']/..//a[normalize-space()='Add new record']");
	By drpAgentName = By.xpath("//input[contains(@id,'EditFormControl_AGENCY_ID_radYALDropDownList_Input')]");
	By txtPermitNumber = By.xpath("//input[contains(@name,'EditFormControl$PERMIT_NUMBER')]");
	By txtComment = By.xpath("//textarea[contains(@id,'EditFormControl_COMMENTS')]");
	By btnInsert = By.xpath("//input[@value='Insert']");
	By btnUpdate = By.xpath("//input[@value='Update']");
	By deleteOk = By.xpath("//a[contains(@onClick,'confirm')]//span[text()='OK']");
	By changesSavedSuccessfully = By.xpath("//span[text()='Changes saved successfully!']");
	
	By iframeDocument = By.xpath("//iframe[@name='UserListDialog']");
	By drpCategory = By.cssSelector("#rddtFakeInput");
	By txtDescription = By.cssSelector("#RadUpload1Desc0");
	By documentFileUpload = By.cssSelector("#RadUpload1file0");
	By loadDocumentFile = By.cssSelector("#buttonSubmit");
	By documentSuccessMessage = By.xpath("//span[@id='lblResults']");
	By btnDocumentClose = By.xpath("//a[@title='Close']");
	

	String tableValue = "//table//tbody//tr//td[text()='%s']";
	String addDocument = "//div[contains(@id,'radYALGridControl')]//td[contains(.,'%s')]/following-sibling::td//a[text()='Add Document']";
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
			throw new RuntimeException("Failed in step 1");
		}
		Thread.sleep(3000);
		try {
			setAgencyName(map.get(Excel.AgencyName));
			log("STEP 2: Value added diplays in the Agency Name column field", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Added value does not display in the field. ", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		try {
			setPermitNumber(map.get(Excel.PermitNumber));
			log("STEP 3: Value added diplays in the Permit Number column  field", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Added value does not display in the field.  ", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		util.click(btnInsert);
		if (util.isElementPresent(String.format(tableValue, map.get(Excel.PermitNumber)))) {
			log("STEP 4: ProjectPermit is added sucessfully", Status.PASS);
		} else {
			log("STEP 4: ProjectPermit is not added sucessfully ", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}

	}

	public void updateProjectPermit(Map<String, String> map) throws InterruptedException {
		editProjectPermit(map.get(Excel.PermitNumber));
		try {
			setComment(map.get(Excel.Comment));
			log("STEP 5: added value diplays in the comment column field ", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: added  value does not displayed in the field ", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		util.click(btnUpdate);
		util.waitFor(5000);

		if (util.isElementPresent(String.format(tableValue, map.get(Excel.Comment)))) {
			log("STEP 6: ProjectPermit is Edit sucessfully ", Status.PASS);
		} else {
			log("STEP 6: ProjectPermit is not Edit sucessfully ", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}

	}
	public void addDocument(Map<String, String> map) {
		String filepath = System.getProperty("user.dir") + File.separator + "test.txt";
		By addDocumnet = By.xpath(String.format(addDocument, map.get(Excel.PermitNumber)));
		util.waitUntilElementDisplay(addDocumnet);
		util.click(addDocumnet);
		util.waitForWebElementToBePresent(iframeDocument);
		util.switchToIframe(iframeDocument);
		
//		util.waitUntilElementDisplay(drpCategory);
//		try {
//			util.selectValueFromDropdown2("test doc cat", drpCategory);
//			log("STEP 7: Value added diplays in the field : Category ", Status.PASS);
//		} catch (Exception e) {
//			log("STEP 7:  Added value does not display in the field : Category  ", Status.FAIL);
//			throw new RuntimeException("Failed in step 8");
//		}
		try {
			util.inputText(txtDescription, "Test Automation");
			log("STEP 8: User can enter a value in the field    ", Status.PASS);
		} catch (Exception e) {
			log("STEP 8:  User cannot add a value in the field   ", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}
		driver.findElement(documentFileUpload).sendKeys(filepath);
		util.waitFor(2000);
		try {
			util.click(loadDocumentFile);
			log("STEP 9: User can upload document from the system  ", Status.PASS);
		} catch (Exception e) {
			log("STEP 9:  User cannot upload a document  ", Status.FAIL);
			throw new RuntimeException("Failed in step 10");
		}

		util.waitUntilElementDisplay(documentSuccessMessage);
		if (util.getText(documentSuccessMessage).contains("Loaded: test.txt")) {
			log("STEP 10: Document Saved Successfully ", Status.PASS);
		} else {
			log("STEP 10:  Document does not saved Successfully  ", Status.FAIL);
			throw new RuntimeException("Failed in step 11");
		}
		util.switchToDefaultContent();
		try {
			log("STEP 11: upon popup close, auto refresh the panel to display updated information ", Status.PASS);
			util.click(btnDocumentClose);
		} catch (Exception e) {
			log("STEP 11:  Autorefresh of the panel does not happen  ", Status.FAIL);
			throw new RuntimeException("Failed in step 12");
		}
	}

	public void deletProjectPermit(Map<String, String> map) {
		try {
			deleteProjectPermit(map.get(Excel.PermitNumber));
			log("STEP 12:upon popup close, auto refresh the panel to display updated information  ", Status.PASS);
		} catch (Exception e) {
			log("STEP 12: Autorefresh of the panel does not happen ", Status.FAIL);
			throw new RuntimeException("Failed in step 12");
		}
		util.waitFor(2000);
		try {
			util.click(deleteOk);
			log("STEP 13: Delete popup window Open ", Status.PASS);
		} catch (Exception e) {
			log("STEP 13: The delete pop window does not display ", Status.FAIL);
			throw new RuntimeException("Failed in step 13");
		}
		util.waitFor(2000);

		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Verify changes saved successfully.");
		if (util.isElementPresent(changesSavedSuccessfully)) {
			log("STEP 14: ProjectPermit is deleted sucessfully ", Status.PASS);
		} else {
			log("STEP 14: ProjectPermit is not deleted sucessfully ", Status.FAIL);
			throw new RuntimeException("Failed in step 14");
		}

	}

}
