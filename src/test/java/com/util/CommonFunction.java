package com.util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;



public class CommonFunction extends BasePage {

	TestUtil util;
	
	public CommonFunction(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
	}

	// Open URL
	public void openURL(String url) {
		String Url = "https://uat.geoamps.com/PIPE/Project/ProjectDashboard.aspx?mode=PRDT";
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	// Close Browser
	public void closeBrowser() {
		driver.quit();
	}

	

	// Login altAMPS
	/*
	 * public void login(String username, String password) { // Enter Username
	 * util.waitUntilElementDisplay(txtUsername); util.inputText(txtUsername,
	 * username);
	 * 
	 * // Enter Password util.waitUntilElementDisplay(txtPassword);
	 * util.inputText(txtPassword, password);
	 * 
	 * // Click on Login Button util.waitUntilElementDisplay(btnLogin);
	 * util.click(btnLogin);
	 * 
	 * // Verify Login Success if (isLoginSuccess != null) {
	 * System.out.println("Login Successfully !!!");
	 * ReportsClass.logStat(Status.PASS, "Login Successfully !!!"); } else {
	 * System.out.println("Failed Login !!!"); ReportsClass.logStat(Status.FAIL,
	 * "Failed Login !!!"); } Assert.assertTrue((isLoginSuccess != null),
	 * "Failed Login !!!"); }
	 */

	// Delete Video
	public static void deleteVideos(String path) {
		File directory = new File(path);
		File[] files = directory.listFiles();
		for (File file : files) {
			file.delete();
		}
	}

//	public static void captureScreenshot(WebDriver driver, String screenshotName) {
//
//		try {
//			TakesScreenshot ts = (TakesScreenshot) driver;
//
//			File source = ts.getScreenshotAs(OutputType.FILE);
//			FileUtils.copyFile(source, new File("./Screenshots/" + screenshotName + ".png"));
//
//			System.out.println("Screenshot taken: " + screenshotName);
//		} catch (Exception e) {
//
//			System.out.println("Exception while taking screenshot " + e.getMessage());
//		}
//	}

	// Delete Screennshot
	public static void deleteScreenshot(String screenshotName) {
		try {
			Files.deleteIfExists(Paths.get("./Screenshots/" + screenshotName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	By navToolsMenu = By.xpath("//a//span[@class='rmText rmExpandDown' and contains(text(),'Tools')]");
	By navToolsMyDashboard = By.xpath("//a//span[contains(text(),'My Dashboard')]");
	By navProjectMenu = By.xpath("*//a//span[@class='rmText rmExpandDown' and contains(text(),'Project')] ");
	By navProjectInformation = By.xpath("*//a//span[contains(text(),'Project Information')] ");
	By navProjectDetails = By.xpath("*//a//span[contains(text(),'Project Details')] ");
	By btnLogOff = By.xpath("//input[@id='ctl00_ContentPlaceHolderHeader_YALHD_LogStatus']");
	By tabProjectDetails = By.xpath("//div[@id='ctl00_ConPHRightTop_RadTabStrip1']");

	// Add Button
	By topProjectMenu = By.xpath("//img[@id='ctl00_ConPHLeftTop_prjMenu']");
	By btnAdd = By.xpath("*//a//span[@class='rmText' and contains(text(),'Add')]");
	By btnArchieve = By.xpath("//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_btnArchive']");
	By btnArchieveDot = By.xpath("//input[@name='ctl00$ConPHRightTop$PRDT_UC$radPrjPanels$i0$i0$OBJPROJECTINFO$btnArchive']");
	By btnUnArchieve = By.xpath("//input[@name='ctl00$ConPHRightTop$radPrjPanels$i0$i0$OBJPROJECTINFO$btnUnarchive']");
	By btnUnArchieveDot = By.xpath("//input[@name='ctl00$ConPHRightTop$PRDT_UC$radPrjPanels$i0$i0$OBJPROJECTINFO$btnUnarchive']");
	By btnSave = By.xpath("//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_btnSaveProject']");
	public void navigateToProjectDeails() {
		util.waitUntilElementDisplay(navProjectMenu);
		util.click(navProjectMenu);
		util.waitUntilElementDisplay(navProjectInformation);
		util.click(navProjectInformation);
		util.waitUntilElementDisplay(navProjectDetails);
		util.click(navProjectDetails);
		String tabProjectDetails = driver.findElement(By.xpath("//div[@id='ctl00_ConPHRightTop_RadTabStrip1']"))
				.getText().trim();
		if (tabProjectDetails.contains("Project Details")) {
			System.out.println("User can navigate to the Project details !!!");
			ReportsClass.logStat(Status.PASS, "Step 1 : User can navigate to the Project details !!!");
		} else {
			System.out.println("User cannot see the option in Menu !!!");
			ReportsClass.logStat(Status.FAIL, "Step 1 : User cannot see the option in Menu !!!");
		}
		Assert.assertTrue(tabProjectDetails.contains("Project Details"), "User cannot see the option in Menu !!!");
		log("navigating to create new Project");
	}
	
	public void navigateToMyDashboard() {
		util.waitUntilElementDisplay(navToolsMenu);
		util.click(navToolsMenu);
		util.waitUntilElementDisplay(navToolsMyDashboard);
		util.click(navToolsMyDashboard);
		log("navigating to create new Project");
	}
	
	public void clickOnAddButton() {
		// Click on Project Menu
		util.waitUntilElementDisplay(topProjectMenu);
		util.click(topProjectMenu);

		// Click on Add Button
		util.waitUntilElementDisplay(btnAdd);
		util.click(btnAdd);
	}
	public void clickOnArchieveButton() {
		util.waitUntilElementDisplay(btnArchieve);
		util.click(btnArchieve);
		//util.waitUntilElementDisplay(btnUnArchieve);
		if(util.isElementPresent(btnUnArchieve)){
			System.out.println("UnArchieve Present !!!");
			ReportsClass.logStat(Status.PASS, "UnArchieve Present !!!");
		}
		else {
			System.out.println("UnArchieve not Present !!!");
			ReportsClass.logStat(Status.FAIL, "UnArchieve not Present !!!");
		}
}
	public void clickOnSaveButton() {
		util.waitUntilElementDisplay(btnSave);
		util.click(btnSave);
	}
}