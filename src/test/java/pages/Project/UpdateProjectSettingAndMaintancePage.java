package pages.Project;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.util.CommonFunction;
import com.util.TestUtil;

import com.util.ReportsClass;

public class UpdateProjectSettingAndMaintancePage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;
	public UpdateProjectSettingAndMaintancePage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}
	// Project Settings And Maintence
			By tabProjectSettingsAndMaintence = By.xpath("//span[contains(text(),'Project Settings & Maintenance')]");
			By btnEditRoyaltyProcessing = By.xpath(
					"//input[@id='ctl00_ConPHRightTop_radPrjPanels_i2_i0_ProjectSettings_radYALGridControl_ctl00_ctl04_EditButton']");
			By drpRoyaltyProcessing = By.xpath(
					"//input[@id='ctl00_ConPHRightTop_radPrjPanels_i2_i0_ProjectSettings_radYALGridControl_ctl00_ctl05_EditFormControl_Setting_Value_ID_radYALDropDownList_Input']");
			By btnUpdate = By.xpath(
					"//input[@id='ctl00_ConPHRightTop_radPrjPanels_i2_i0_ProjectSettings_radYALGridControl_ctl00_ctl05_EditFormControl_btnUpdate']");

			By successMessage = By.xpath("*//div//span[contains(text(),'Changes saved successfully!')]");
			By getEditRotaltyProcessMsg = By.xpath("*//div//span[contains(text(),'Changes saved successfully!')]");
			
			public void setRoyaltyProcessing(String royaltyProcessing) {
				util.inputText(drpRoyaltyProcessing, royaltyProcessing);
				util.pressENTERkey();
			}

	public void updateProjectSettingAndMaintence(Map<String, String> map) {
				commonFunction.navigateToProjectDeails();
				util.waitUntilElementDisplay(tabProjectSettingsAndMaintence);
				util.click(tabProjectSettingsAndMaintence);
				util.click(btnEditRoyaltyProcessing);
				util.waitUntilElementDisplay(drpRoyaltyProcessing);
				setRoyaltyProcessing(map.get("Royalty Processing"));
				util.click(btnUpdate);

				// Verify Royalty Processing Changes Successfuly
				util.waitUntilElementDisplay(successMessage);
				String getEditRotaltyProcessMsg = driver
						.findElement(By.xpath("*//div//span[contains(text(),'Changes saved successfully!')]")).getText().trim();
				if (getEditRotaltyProcessMsg.contains("Changes saved successfully!")) {
					System.out.println("Edit Royalty Processing Changes Successfully !!!");
					ReportsClass.logStat(Status.PASS, "Edit Royalty Processing Changes Successfully !!!");
				} else {
					System.out.println("Failed to Edit Royalty Processing Changes !!!");
					ReportsClass.logStat(Status.FAIL, "Failed to Edit Royalty Processing Changes !!!");
				}
				Assert.assertTrue(getEditRotaltyProcessMsg.contains("Changes saved successfully!"),
						"Failed to Edit Royalty Processing Changes !!!");
		
	}

}
