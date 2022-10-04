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



public class AddProjectPage extends BasePage{
	TestUtil util;
	CommonFunction commonFunction ;

	public AddProjectPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}
	
	By txtProjectName = By.xpath("//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_radPrjName']");
	By txtProjectNumber = By
			.xpath("//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_radPrjNum']");
	By drpProjectType = By.xpath(
			"//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_yalProjType_radYALDropDownList_Input']");
	By txtAbbreviation = By
			.xpath("//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_ABBREVIATION']");
	By drpArea = By.xpath(
			"//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_yalPrjArea_radYALDropDownList_Input']");
	By btnSave = By.xpath("//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_btnSaveProject']");
	By btnSaveDOT = By.xpath("//input[@id='ctl00_ConPHRightTop_PRDT_UC_radPrjPanels_i0_i0_OBJPROJECTINFO_btnSaveProject']");
	By btnArchieve = By.xpath("//input[@id='ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_btnArchive']");
	By btnArchieveDot = By.xpath("//input[@name='ctl00$ConPHRightTop$PRDT_UC$radPrjPanels$i0$i0$OBJPROJECTINFO$btnArchive']");
	By archieveokPopupFrame = By.xpath("//iframe[contains(@name,'confirm')]");
	By archieveOk = By.xpath("//a[contains(@onClick,'confirm')][1]");
	//By archieveOkbutton = By.xpath("//div[contains(@id,'confirm')]//div/a[contains(@onClick,'confirm')][1]");
	By archieveOkbutton = By.xpath("(//div[contains(@id,'confirm')])[1]");
	By btnUnArchieve = By.xpath("//input[@name='ctl00$ConPHRightTop$radPrjPanels$i0$i0$OBJPROJECTINFO$btnUnarchive']");
	By btnUnArchieveDot = By.xpath("//input[@name='ctl00$ConPHRightTop$PRDT_UC$radPrjPanels$i0$i0$OBJPROJECTINFO$btnUnarchive']");
	By txtProjectNumberDot = By
			.xpath("//input[@id='ctl00_ConPHRightTop_PRDT_UC_radPrjPanels_i0_i0_OBJPROJECTINFO_radPrjNum']");
	By txtProjectNameDot = By
			.xpath("//input[@id='ctl00_ConPHRightTop_PRDT_UC_radPrjPanels_i0_i0_OBJPROJECTINFO_radPrjName']");
	By drpProjectTypeDot = By.xpath(
			"//input[@id='ctl00_ConPHRightTop_PRDT_UC_radPrjPanels_i0_i0_OBJPROJECTINFO_yalProjType_radYALDropDownList_Input']");
	By drpClientDot = By.xpath("//input[@id='ctl00_ConPHRightTop_PRDT_UC_radPrjPanels_i0_i0_OBJPROJECTINFO_yalPrjArea_radYALDropDownList_Input']");
	By drpProjectStatusDot = By.xpath("//input[@id='ctl00_ConPHRightTop_PRDT_UC_radPrjPanels_i0_i0_OBJPROJECTINFO_PROJECT_STATUS_ID_radYALDropDownList_Input']");
	
	By txtProjectNameSummary = By.xpath("(//*[contains(text(),'Project Name')]/following::td/strong)[1]");
	By txtProjectCodeSummary = By.xpath("(//*[contains(text(),'Project #')]/following::td/strong)[1]");		



	public void verifySummaryALT() {
		String getStrProjectCode = driver.findElement(By.id("ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_radPrjNum")).getAttribute("value");
		String getStrProjectName = driver.findElement(By.id("ctl00_ConPHRightTop_radPrjPanels_i0_i0_OBJPROJECTINFO_radPrjName")).getAttribute("value");
		
		String getProjectCodeSummary = driver.findElement(By.xpath("(//*[contains(text(),'Project')]/following::td/strong)[1]")).getText();
		String getProjectNameSummary = driver.findElement(By.xpath("(//*[contains(text(),'Project')]/following::td/strong)[2]")).getText();
		
		if(getProjectCodeSummary.equals(getStrProjectCode)){
			 System.out.println("Project Code Matched !!!");
				ReportsClass.logStat(Status.PASS, "Project Code Matched !!!");
			} else {
				System.out.println("Project Code not Matched");
				ReportsClass.logStat(Status.FAIL, "Project Code not Matched");
		}
		 if(getProjectNameSummary.equals(getStrProjectName)){
			 System.out.println("Project Name Matched !!!");
				ReportsClass.logStat(Status.PASS, "Project Name Matched !!!");
			} else {
				System.out.println("Project Name not Matched");
				ReportsClass.logStat(Status.FAIL, "Project Name not Matched");
		}
		
	}
	public void setProjectNumber(String projectNumber) {
		util.inputText(txtProjectNumber, util.randomNumber());
	}
	public void setProjectName(String projectName) {
		util.inputText(txtProjectName, projectName);
	}
	public void setProjectType(String projectType) {
		util.inputText(drpProjectType, projectType);
		util.pressENTERkey();
	}
	public void setAbbreviation(String abbreviation) {
		util.inputText(txtAbbreviation, abbreviation);
	}
	public void setArea(String area) throws InterruptedException {
		util.inputText(drpArea, area);
		util.waitFor(1000);
		util.pressENTERkey();
		
	}
	public void setClient(String client) {
		
	}
	public void setProjectStatus(String projectStatus) {
		
	}
	public void setTemporaryROW(String remporaryRow) {
		
	}
	public void setPermanentROW(String permanentROW) {
		
	}
	public void setUnit(String unit) {
		
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
