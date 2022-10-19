package pages.Project;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class AddOpertaingCompanyPage extends BasePage {

	
	TestUtil util;
	CommonFunction commonFunction;
	public AddOpertaingCompanyPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By btnOperatingComapnies =  By.xpath("(//a[normalize-space()='Operating Companies'])[2]");
	By drpLesses = By.xpath("//input[contains(@id,'ProjectGrantees_GRANTEES_ID_OnDemand_Input')]");
	By btnAdd = By.xpath("//input[contains(@id,'ProjectGrantees_ImageButton1')]");
	
	
	public void setLesses(String value) {
		util.inputText(drpLesses, value);
		util.pressENTERkey();
	}
	public void clickAdd() {
		util.click(btnAdd);
	}
	public void addOperatingCompanies(Map<String, String> map) {
		
		util.click(btnOperatingComapnies);
		setLesses(map.get(Excel.Lessees));
		try {
			clickAdd();
			log("STEP 1: Added Grantee displays", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Added grantee is not displayed in the pane  ", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		
		//Verify Company added or not 
		//a[normalize-space()='geoAMPS North LLC']
	}

}
