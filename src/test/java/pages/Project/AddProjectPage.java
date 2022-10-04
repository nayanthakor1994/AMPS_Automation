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
	By drpArea = By.xpath(
			"//input[contains(@id,'yalPrjArea_radYALDropDownList_Input')]");
	By btnSave = By.xpath("//input[contains(@id,'btnSaveProject')]");
	By btnArchieve = By.xpath("//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_btnArchive']");
	By drpTemporaryRow = By.xpath(
			"//input[contains(@name,'yalTempROWWidth$radYALDropDownList')]");
	By drpPermanentRow = By.xpath(
			"//input[contains(@name,'yalPermROWWidth_radYALDropDownList')]");
	By drpUnitRow = By.xpath(
			"//input[contains(@name,'PERMROW_WIDTH_UNIT$radYALDropDownList')]");

	By archieveokPopupFrame = By.xpath("//iframe[contains(@name,'confirm')]");
	By archieveOk = By.xpath("//a[contains(@onClick,'confirm')][1]");
	By archieveOkbutton = By.xpath("(//div[contains(@id,'confirm')])[1]");
	By btnUnArchieve = By.xpath("//input[@name='ctl00$ConPHRightTop$radPrjPanels$i0$i0$OBJPROJECTINFO$btnUnarchive']");
	By btnUnArchievedot = By
			.xpath("//input[@name='ctl00$ConPHRightTop$PRDT_UC$radPrjPanels$i0$i0$OBJPROJECTINFO$btnUnarchive']");
	By drpClient = By.xpath("//input[contains(@id,'yalPrjArea_radYALDropDownList_Input')]");
	By drpProjectStatus = By.xpath("//input[contains(@id,'PROJECT_STATUS_ID_radYALDropDownList_Input')]");

	By txtProjectNameSummary = By.xpath("(//*[contains(text(),'Project Name')]/following::td/strong)[1]");
	By txtProjectCodeSummary = By.xpath("(//*[contains(text(),'Project #')]/following::td/strong)[1]");

	public void verifySummaryALT() {
		String getStrProjectCode = driver
				.findElement(By.xpath("(//input[contains(@id,'OBJPROJECTINFO_radPrjNum')])[1]"))
				.getAttribute("value");
		String getStrProjectName = driver
				.findElement(By.xpath("(//input[contains(@id,'OBJPROJECTINFO_radPrjName')])[1]"))
				.getAttribute("value");

		String getProjectCodeSummary = driver
				.findElement(By.xpath("(//*[contains(text(),'Project')]/following::td/strong)[1]")).getText();
		String getProjectNameSummary = driver
				.findElement(By.xpath("(//*[contains(text(),'Project')]/following::td/strong)[2]")).getText();

		if (getProjectCodeSummary.equals(getStrProjectCode)) {
			System.out.println("Project Code Matched !!!");
			ReportsClass.logStat(Status.PASS, "Project Code Matched !!!");
		} else {
			System.out.println("Project Code not Matched");
			ReportsClass.logStat(Status.FAIL, "Project Code not Matched");
		}
		if (getProjectNameSummary.equals(getStrProjectName)) {
			System.out.println("Project Name Matched !!!");
			ReportsClass.logStat(Status.PASS, "Project Name Matched !!!");
		} else {
			System.out.println("Project Name not Matched");
			ReportsClass.logStat(Status.FAIL, "Project Name not Matched");
		}

	}

	public void setProjectNumber(String value) {
		if (!commonFunction.checkNA(value))
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
	public void addProjectInformation(Map<String, String> map) throws InterruptedException {
		commonFunction.navigateToProjectDeails();
		commonFunction.clickOnAddButton();
		setProjectName(map.get(Excel.ProjectName));
		setProjectNumber(map.get(Excel.ProjectNumber));
		setProjectType(map.get(Excel.ProjectType));
		setAbbreviation(map.get(Excel.Abbreviation));
		setArea(map.get(Excel.Area));
		setClient(map.get(Excel.Client));
		setProjectStatus(map.get(Excel.ProjectStatus));
		setTemporaryROW(map.get(Excel.TemporaryROW));
		setPermanentROW(map.get(Excel.PermanentROW));
		setUnit(map.get(Excel.Unit));
		commonFunction.clickOnSaveButton();
		commonFunction.clickOnArchieveButton();
		verifySummaryALT();
	}
	
	
}
