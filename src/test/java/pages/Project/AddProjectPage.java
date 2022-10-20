package pages.Project;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

import com.util.ReportsClass;

public class AddProjectPage extends BasePage {
	TestUtil util;
	CommonFunction commonFunction;

	public AddProjectPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

//	By txtProjectName = By.xpath("//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_radPrjName']");
	By txtProjectName = By.xpath("//input[contains(@id,'radPrjName')][1]");
	By txtProjectNumber = By.xpath("(//input[contains(@id,'radPrjNum')])[1]");
	By drpProjectType = By.xpath("(//input[contains(@id,'ProjType')])[1]");
	By txtAbbreviation = By.xpath("//input[contains(@id,'OBJPROJECTINFO_ABBREVIATION')][1]");
	By drpArea = By.xpath("//input[contains(@id,'yalPrjArea_radYALDropDownList_Input')]");
	By btnSave = By.xpath("//input[contains(@id,'btnSaveProject')]");
	By btnArchieve = By.xpath("//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_btnArchive']");
	By drpTemporaryRow = By.xpath("//input[contains(@name,'yalTempROWWidth$radYALDropDownList')]");
	By drpPermanentRow = By.xpath("//input[contains(@name,'yalPermROWWidth_radYALDropDownList')]");
	By drpUnitRow = By.xpath("//input[contains(@name,'PERMROW_WIDTH_UNIT$radYALDropDownList')]");

	By archieveOk = By.xpath("//a[contains(@onClick,'confirm')][1]");
	By archieveOkbutton = By.xpath("(//div[contains(@id,'confirm')])[1]");
	By btnUnArchieve = By.xpath("//input[@name='ctl00$ConPHRightTop$radPrjPanels$i0$i0$OBJPROJECTINFO$btnUnarchive']");
	By btnUnArchievedot = By
			.xpath("//input[@name='ctl00$ConPHRightTop$PRDT_UC$radPrjPanels$i0$i0$OBJPROJECTINFO$btnUnarchive']");
	By drpClient = By.xpath("//input[contains(@id,'yalPrjArea_radYALDropDownList_Input')]");
	By drpProjectStatus = By.xpath("//input[contains(@id,'PROJECT_STATUS_ID_radYALDropDownList_Input')]");

	By txtProjectNameSummary = By.xpath("(//*[contains(text(),'Project Name')]/following::td/strong)[1]");
	By txtProjectCodeSummary = By.xpath("(//*[contains(text(),'Project #')]/following::td/strong)[1]");

	By btnArchieveDot = By
			.xpath("//input[@name='ctl00$ConPHRightTop$PRDT_UC$radPrjPanels$i0$i0$OBJPROJECTINFO$btnArchive']");
	By archieveokPopupFrame = By.xpath("//iframe[contains(@name,'confirm')]");
	By btnUnArchieveDot = By
			.xpath("//input[@name='ctl00$ConPHRightTop$PRDT_UC$radPrjPanels$i0$i0$OBJPROJECTINFO$btnUnarchive']");

	public void verifySummaryALT() {
		String getStrProjectCode = driver
				.findElement(By.xpath("(//input[contains(@id,'OBJPROJECTINFO_radPrjNum')])[1]")).getAttribute("value");
		String getStrProjectName = driver
				.findElement(By.xpath("(//input[contains(@id,'OBJPROJECTINFO_radPrjName')])[1]")).getAttribute("value");

		String getProjectCodeSummary = driver
				.findElement(By.xpath("(//*[contains(text(),'Project')]/following::td/strong)[1]")).getText();
		String getProjectNameSummary = driver
				.findElement(By.xpath("(//*[contains(text(),'Project')]/following::td/strong)[2]")).getText();

		if (getProjectCodeSummary.equals(getStrProjectCode)) {
			System.out.println("Project Code Matched !!!");
			ReportsClass.logStat(Status.PASS, "Project Code Matched !!!");
			log("STEP 11: Project Code Matched !!!", Status.PASS);
		} else {
			System.out.println("Project Code not Matched");
			log("STEP 11: Project Code not Matched", Status.FAIL);
			ReportsClass.logStat(Status.FAIL, "Project Code not Matched");
		}
		if (getProjectNameSummary.equals(getStrProjectName)) {
			System.out.println("Project Name Matched !!!");
			log("STEP 11: Project Name Matched !!!", Status.PASS);
			ReportsClass.logStat(Status.PASS, "Project Name Matched !!!");
		} else {
			System.out.println("Project Name not Matched");
			log("STEP 11: Project Name not Matched", Status.FAIL);
			ReportsClass.logStat(Status.FAIL, "Project Name not Matched");
		}

	}

	public void setProjectNumber() {
		util.inputText(txtProjectNumber, util.randomNumber());
	}

	public void setProjectName(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(txtProjectName, value);
	}

	public void setProjectType(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(drpProjectType, value);
		util.pressENTERkey();
	}

	public void setAbbreviation(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(txtAbbreviation, value);
	}

	public void setArea(String value) throws InterruptedException {
		if (!commonFunction.checkNA(value))
			util.inputText(drpArea, value);
		util.waitFor(1000);
		util.pressENTERkey();

	}

	public void setClient(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(drpClient, value);
		util.pressENTERkey();

	}

	public void setProjectStatus(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(drpProjectStatus, value);
		util.pressENTERkey();

	}

	public void setTemporaryROW(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(drpTemporaryRow, value);
		util.pressENTERkey();

	}

	public void setPermanentROW(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(drpPermanentRow, value);
		util.pressENTERkey();

	}

	public void setUnit(String value) {
		if (!commonFunction.checkNA(value))
			util.inputText(drpUnitRow, value);
		util.pressENTERkey();
	}

	public void clickOnArchieveButtonROW() throws InterruptedException {
		Thread.sleep(1000);
		util.waitUntilElementDisplay(btnArchieveDot);
		util.click(btnArchieveDot);
		Thread.sleep(1000);
		util.waitUntilElementDisplay(archieveOk);
		util.click(archieveOk);

		if (util.isElementPresent(btnUnArchieveDot)) {
			System.out.println("UnArchieve Present !!!");
			ReportsClass.logStat(Status.PASS, "UnArchieve Present !!!");
		} else {
			System.out.println("UnArchieve not Present !!!");
			ReportsClass.logStat(Status.FAIL, "UnArchieve not Present !!!");
		}
	}

	public void addProjectInformation(Map<String, String> map) throws InterruptedException {
		try {
			commonFunction.navigateToProjectDeails();
			log("STEP 1: User can navigate to the Project details", Status.PASS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log("STEP 1: User cannot see the option in Menu ", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		try {
			commonFunction.clickOnAddButton();
			log("STEP 2: User can click on the add button", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: User cannot see the add button  ", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		try {
			setProjectName(map.get(Excel.ProjectName));
			log("STEP 3: User can enter the value in the text field ", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: User cannot add value  to the field ", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		try {
			setProjectNumber();
			log("STEP 4: User can enter the value in the Project number  field", Status.PASS);
		} catch (Exception e) {
			log("STEP 4:  User cannot add value  to the field ", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		try {
			setProjectType(map.get(Excel.ProjectType));
			log("STEP 5:  Added value displays in the field", Status.PASS);
		} catch (Exception e) {
			log("STEP 5:  User  cannot select  value  from the DD ", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		try {
			setAbbreviation(map.get(Excel.Abbreviation));
			log("STEP 6:  User can enter Abbreviation value in the field    ", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: User cannot add Abbreviation value in the field  ", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		try {
			setArea(map.get(Excel.Area));
			log("STEP 7: Added Area value displays in the field    ", Status.PASS);
		} catch (InterruptedException e) {
			log("STEP 7: User cannot add Area value in the field ", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}
		try {
			setClient(map.get(Excel.Client));
			log("Added Client value displays in the field", Status.PASS);
		} catch (Exception e) {
			log(" User cannot Client value displays in the field ", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
		try {
			setProjectStatus(map.get(Excel.ProjectStatus));
			log( "Added ProjectStatus value displays in the field", Status.PASS);
		} catch (Exception e) {
			log("User cannot ProjectStatus value displays in the field ", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}
		try {
			setTemporaryROW(map.get(Excel.TemporaryROW));
			log("Added TemporaryROW value displays in the field", Status.PASS);
		} catch (Exception e) {
			log("User cannot TemporaryROW value displays in the field ", Status.FAIL);
			throw new RuntimeException("Failed in step Added TemporaryROW ");
		}
		try {
			setPermanentROW(map.get(Excel.PermanentROW));
			log("Added PermanentROW value displays in the field", Status.PASS);
		} catch (Exception e) {
			log("User cannot PermanentROW value displays in the field ", Status.FAIL);
			throw new RuntimeException("Failed in step Added PermanentROW ");
		}
		try {
			setUnit(map.get(Excel.Unit));
			log("Added Unit value displays in the field", Status.PASS);
		} catch (Exception e) {
			log("User cannot Unit value displays in the field ", Status.FAIL);
			throw new RuntimeException("Failed in step Added Unit ");
		}
		try {
			commonFunction.clickOnSaveButton();
			log("STEP 8: User can navigate to the Project details", Status.PASS);
		} catch (Exception e) {
			log("STEP 8: Project is not created ", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
		try {
			commonFunction.clickOnArchieveButton();
			log("STEP 9: The button text should be changed to UNARCHIVE", Status.PASS);
		} catch (InterruptedException e) {
			log("STEP 9: Button text or color does not change ", Status.FAIL);
			throw new RuntimeException("Failed in step 9 ");
		}
		try {
			commonFunction.clickOnSaveButton();
			log("STEP 10: Project is unarchive and does not display in Poject list DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 10: Project still displays under the project list", Status.FAIL);
			throw new RuntimeException("Failed in step 10 ");
		}
		verifySummaryALT();
	}

}
