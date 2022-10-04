package pages.Project;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.util.TestUtil;
import com.util.CommonFunction;
import com.util.ReportsClass;

public class AddProjectCityStatePage extends BasePage {
	TestUtil util;
	CommonFunction commonFunction;

	public AddProjectCityStatePage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);

	}

	By btnAddNewRecord = By.xpath(
			"//img[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_PROJECTSTATECOUNTY_radYALGridControl_ctl00_ctl02_ctl00_Img4']");
	By drpState = By.xpath(
			"//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_PROJECTSTATECOUNTY_radYALGridControl_ctl00_ctl02_ctl03_EditFormControl_RadState_Input']");
	By drpCountry = By.xpath(
			"//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_PROJECTSTATECOUNTY_radYALGridControl_ctl00_ctl02_ctl03_EditFormControl_RadCounty_Input']");
	By btnInsert = By.xpath(
			"//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_PROJECTSTATECOUNTY_radYALGridControl_ctl00_ctl02_ctl03_EditFormControl_btnInsert']");

	By btnEdit = By.xpath("//input[contains(@name,'EditButton')]");
	By EditState = By.xpath("//tr[contains(@id,'EditFormControl')]//input[contains(@name,'RadState')]");
	By EditCountry = By.xpath("//tr[contains(@id,'EditFormControl')]//input[contains(@name,'RadCounty')]");
	By btnUpdateCountry = By.xpath("//input[contains(@id,'btnUpdate')]");
	By tableCountryName = By.xpath("((//table[@class='rgMasterTable'])[1]//tbody)[last()]//tr[1]//td[5]");
	By tableStateName = By.xpath("((//table[@class='rgMasterTable'])[1]//tbody)[last()]//tr[1]//td[4]");
	By btnDeleteSTCT = By.xpath("//input[contains(@id,'DeleteAlignment')]");
	By deletePopupFrame = By.xpath("//iframe[contains(@name,'confirm')]");
	By deleteOk = By.xpath("//a[contains(@onClick,'confirm')]//span[text()='OK']");
	By changesSavedSuccessfully = By.xpath("//span[text()='Changes saved successfully!']");
	String tableValue = "//table//tbody//tr//td[text()='%s']";

	public void setState(String state) {
		util.selectDropDownValue(drpState, state);
	}

	public void setCountry(String country) {
		util.selectDropDownValue(drpCountry, country);
	}

	public void updateState(String updateState) {
		util.selectDropDownValue(EditState, updateState);
	}
	public void updateCountry(String updateCity) {
		util.selectDropDownValue(EditCountry, updateCity);
	}
	public void clickInsetButton() {
		util.click(btnInsert);
	}

	public void addStateAndContryInformation(Map<String, String> map) {
		commonFunction.navigateToProjectDeails();
		util.waitUntilElementDisplay(btnAddNewRecord);
		util.click(btnAddNewRecord);
		setState(map.get("StateName"));
		setCountry(map.get("CountryName"));
		clickInsetButton();
		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Verify changes saved successfully.");
		if (util.isElementPresent(changesSavedSuccessfully)) {
			ReportsClass.logStat(Status.PASS, "State and Country Information message displyed");
		} else {
			ReportsClass.logStat(Status.FAIL, "State and Country Information message not displayed");
		}
		if (util.isElementPresent(String.format(tableValue, map.get("CountryName")))) {
			ReportsClass.logStat(Status.PASS, "State and Country Information is added sucessfully !!!");
		} else {
			ReportsClass.logStat(Status.FAIL, "State and Country Information is not added sucessfully !!!");
		}
	}

	public void updateStateAndContryInformation(Map<String, String> map) {
		util.waitUntilElementDisplay(btnEdit);
		util.click(btnEdit);
		updateState(map.get("StateName"));
		updateCountry(map.get("CountryName"));
		util.waitFor(2000);
		util.click(btnUpdateCountry);
		util.waitUntilElementDisappear(btnUpdateCountry);
		String countryName = util.getText(tableCountryName);
		String stateName = util.getText(tableStateName);
		
		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Verify changes saved successfully.");
		Assert.assertEquals(countryName, map.get("CountryName"), "Verify country name");
		Assert.assertEquals(stateName, map.get("StateName"), "Verify state name");
//		if(util.isElementPresent(changesSavedSuccessfully)){
//			ReportsClass.logStat(Status.PASS, "State and Country Information is updated sucessfully !!!");
//		} else {
//			ReportsClass.logStat(Status.FAIL, "State and Country Information is not updated sucessfully !!!");
//		}
		if(countryName.equals(map.get("CountryName"))){
			ReportsClass.logStat(Status.PASS, "Country information is updated sucessfully !!!");
		} else {
			ReportsClass.logStat(Status.FAIL, "Country information is not  updated sucessfully !!!");
		}
		
		if(stateName.equals(map.get("StateName"))){
			ReportsClass.logStat(Status.PASS, "State information is updated sucessfully !!!");
		} else {
			ReportsClass.logStat(Status.FAIL, "State information is not  updated sucessfully !!!");
		}
		
		// Delete updated country-state
		util.waitUntilElementDisplay(btnDeleteSTCT);
		util.click(btnDeleteSTCT);
		
		util.waitFor(2000);
		util.click(deleteOk);
		util.waitFor(2000);
		
		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Verify changes saved successfully.");
		if(util.isElementPresent(changesSavedSuccessfully)){
			ReportsClass.logStat(Status.PASS, "State and Country Information is deleted sucessfully !!!");
		} else {
			ReportsClass.logStat(Status.FAIL, "State and Country Information is not deleted sucessfully !!!");
		}
		
	}

}
