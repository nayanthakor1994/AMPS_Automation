package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BasePage;

public class TestUtil extends BasePage {
//	Logger logger = Logger.getLogger("Test");
	WebDriverWait wait;

	public TestUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	protected enum Condition {
		isDisplayed, isClickable, isPresent, isNotVisible
	}

	public void resetImplicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	/**
	 * This method is for hard wait in process execution
	 * 
	 * @param miliSecond
	 */
	public void waitFor(int miliSecond) {
		try {
			Thread.sleep(miliSecond);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will click on given xpath
	 * 
	 * @param xpath
	 */
	public void click(By xpath) {
		try {
			waitUntilElementDisplay(xpath);
			getElement(Condition.isClickable, xpath, 60).click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", getElement(Condition.isClickable, xpath, 60));
		}
	}

	public void selectDropDownValue(By xpath, String value) {
		waitUntilElementDisplay(xpath);
		click(xpath);
		waitFor(2000);
		By drpValue = By.xpath("//ul//li[text()='" + value + "']");
		if (!isElementPresent(drpValue)) {
			click(xpath);
			waitFor(2000);
		}
		click(drpValue);
//		Select ele = new Select(driver.findElement(xpath));
//		ele.selectByVisibleText(Value);
	}

	public void click(String xpath) {
		try {
			System.out.println("Waiting for : " + xpath);
			getElement(Condition.isClickable, By.xpath(xpath), 60).click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", getElement(Condition.isClickable, By.xpath(xpath), 60));
		}
	}

	/**
	 * This method will enter text into textbox
	 * 
	 * @param inputField as webelement
	 * @param text
	 */
	public void inputText(WebElement inputField, String text) {
		try {
			inputField.click();
			clearInputField(inputField);
			inputField.sendKeys(text);
		} catch (Exception e) {
			// report error
			System.out.println("Element not present "+inputField);
			e.printStackTrace();
		}
	}

	/**
	 * This method will enter text into textbox
	 * 
	 * @param locator as xpath string
	 * @param text
	 */
	public void inputText(By locator, String text) {
		try {
			waitUntilElementDisplay(locator);
			WebElement inputField = getElement(locator);
			inputField.click();
			clearInputField(inputField);
			inputField.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will clear all the text into textbox
	 * 
	 * @param ele
	 */
	public void clearInputField(WebElement ele) {
		ele.sendKeys(Keys.CONTROL + "a");
		waitFor(1000);
		ele.sendKeys(Keys.DELETE);
	}

	/**
	 * This method will clear all the text into textbox
	 * 
	 * @param locator
	 */
	public void clearInputField(By locator) {
		WebElement ele = getElement(locator);
		ele.sendKeys(Keys.CONTROL + "a");
		waitFor(1000);
		ele.sendKeys(Keys.DELETE);
	}

	/**
	 * This method will return WebElement for given xpath
	 * 
	 * @param value
	 * @return
	 */
	public WebElement getElement(By value) {
		WebElement ele = null;
		try {
			ele = getElement(Condition.isDisplayed, value, 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ele;
	}

	/**
	 * This method will return true or false if element is displayed or not
	 * 
	 * @param xpath
	 * @return
	 */
	public boolean isElementPresent(By locator) {
		boolean isPresent = false;
		try {
//			isPresent = driver.findElement(locator).isDisplayed();
			if (driver.findElements(locator).size() > 0)
				isPresent = true;
			else
				isPresent = false;
		} catch (Exception e) {
			return false;
		}
		if (isPresent)
			highlightElement(driver.findElement(locator));
		return isPresent;
	}

	public boolean isElementPresent(String locator) {
		boolean isPresent = false;
		try {
			isPresent = driver.findElement(By.xpath(locator)).isDisplayed();
//			if(driver.findElements(locator).size() > 0)
//				isPresent= true;
//			else
//				isPresent= false;
		} catch (Exception e) {
			return false;
		}
		if (isPresent)
			highlightElement(driver.findElement(By.xpath(locator)));
		return isPresent;
	}

	/**
	 * This method will return true or false if element is displayed or not
	 * 
	 * @param ele
	 * @return
	 */
	public boolean isElementPresent(WebElement ele) {
		boolean isPresent = false;
		try {
			isPresent = ele.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		if (isPresent)
			highlightElement(ele);
		return isPresent;
	}

	/**
	 * This method will wait untill element is present
	 * 
	 * @param locator
	 */
	public void waitUntilElementPresent(By locator) {
		System.out.println("Waiting for : " + locator);
		getElement(Condition.isPresent, locator, 60);
	}

	/**
	 * This method will wait untill element is displayed
	 * 
	 * @param xpath
	 */
	public void waitUntilElementDisplay(By locator) {
		System.out.println("Waiting for : " + locator);
		getElement(Condition.isDisplayed, locator, 20);
	}

	public List<WebElement> getWebElements(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public WebElement waitForWebElementToBeVisibleReturnElement(By locator) {
		WebElement visibleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return visibleElement;
	}

	public void waitForWebElementToBeVisible(By locator, int waitTime) {
		WebDriverWait waitVisible = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		waitVisible.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForWebElementToBeClickableReturnElement(By locator) {
		WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return clickableElement;
	}

	public WebElement waitForWebElementToBeClickableReturnElement(By locator, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return clickableElement;
	}

	public void waitForWebElementToBePresent(By locator) {
		scrollToElement(locator);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForWebElementToBePresent(By locator, int variableWaitTime) {
		WebDriverWait variableWait = new WebDriverWait(driver, Duration.ofSeconds(variableWaitTime));
		scrollToElement(locator);
		variableWait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForWebElementToBePresentReturn(By locator, int variableWaitTime) {
		WebDriverWait variableWait = new WebDriverWait(driver, Duration.ofSeconds(variableWaitTime));
		scrollToElement(locator);
		return variableWait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForWebElementToBePresentReturnElement(By locator) {
		scrollToElement(locator);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForWebElementToBeClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * This method will return WebElement based on condition, xpath and timeout
	 * 
	 * @param condition
	 * @param locator
	 * @param time
	 * @return
	 */
	private WebElement getElement(Condition condition, By by, int time) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

		try {
			switch (condition) {
			case isClickable:
				element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				if (element == null) {
					return element;
				} else if (element.getAttribute("clickable") == null) {
					return element;
				} else if (element.getAttribute("clickable") != null) {
					element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(by));
					return element;
				}
				break;
			case isDisplayed:
				element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				break;
			case isPresent:
				element = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(by));
				break;
			default:
				break;
			}
			if (!isVisibleInViewport(element))
				scrollToElement(element);
		} catch (Exception e) {
		}
		return highlightElement(element);
	}

	protected boolean isVisibleInViewport(WebElement element) {
		return ((Boolean) ((JavascriptExecutor) driver).executeScript(
				"var elem = arguments[0],box = elem.getBoundingClientRect(),cx = box.left + box.width / 2,cy = box.top + box.height / 2, e = document.elementFromPoint(cx, cy); for (; e; e = e.parentElement) {if (e === elem) return true;}return false;",
				new Object[] { element })).booleanValue();
	}

	/**
	 * 
	 * @param element
	 * @return desired element This method is used to mark red box around the
	 *         desired element
	 */
	public WebElement highlightElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid red'",
				new Object[] { element });
		return element;
	}

	/**
	 * This method will select the value from drop down
	 */
//	public void SelectDropDownValue(String Value, WebElement Element) {
//		Select authType = new Select(Element);
//		authType.selectByVisibleText(Value);
//	}

//	/**
//	 *  This method is move focus on the xpath
//	 * @param locator
//	 */
//	public void switchToBlock(By locator) {
//		WebElement wb = getElement(locator);
//		if (isChrome) {
//			Actions act = new Actions(driver);
//			act.moveToElement(wb).build().perform();
//		} else {
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb);
//		}
//	}

//	/**
//	 * This method is move focus on the WebElement
//	 * @param wb
//	 */
//	public void switchToBlock(WebElement wb) {
//		if (isChrome) {
//			Actions act = new Actions(driver);
//			act.moveToElement(wb).build().perform();
//		} else {
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb);
//		}
//	}

	/**
	 * This method will scroll down or up to element
	 * 
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		if (!isVisibleInViewport(element)) {
			((JavascriptExecutor) driver).executeScript(
					"window.scrollTo(" + element.getLocation().x + "," + (element.getLocation().y - 80) + ");");
		}
	}

	public String randomNumber() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		String formatted = String.format("%05d", num);
		System.out.println(formatted);
		return formatted;
	}

	public void pressENTERkey() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * This method will scroll down or up to element
	 * 
	 * @param locator
	 */
	public void scrollToElement(By locator) {
		WebElement element = getElement(locator);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		if (!isVisibleInViewport(element)) {
			((JavascriptExecutor) driver).executeScript(
					"window.scrollTo(" + element.getLocation().x + "," + (element.getLocation().y - 80) + ");");
		}
	}

	/**
	 * This method will return the current Date and time in fixed format
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateTime = dateFormat.format(date);
		return dateTime;
	}

	/**
	 * This method will return the text of given locator
	 * 
	 * @param by is locator
	 * @return
	 */
	public String getText(By locator) {
		return getElement(locator).getText();
	}

	/**
	 * This method will return the text of given WebElement
	 * 
	 * @param element
	 * @return
	 */
	public String getText(WebElement element) {
		return element.getText();
	}

	/**
	 * This method will wait(60 sec) until element will be disappeared
	 * 
	 * @param locator
	 */
	public void waitUntilElementDisappear(By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	/**
	 * This method will return status of button
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isButtonEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	/**
	 * This method will return name of file which having given extension
	 * 
	 * @param filepath
	 * @param extension
	 * @return
	 */
	public String getFileFromExtension(String filepath, String extension) {
		String pdfFile = null;
		File directoryPath = new File(filepath);
		String contents[] = directoryPath.list();
		for (int i = 0; i < contents.length; i++) {
			if (contents[i].contains(extension)) {
				pdfFile = contents[i];
				break;
			}
		}
		return pdfFile;
	}

	/**
	 * This method will return true if file is deleted else false
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method will return Date&Time in given format
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDateTime(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		String dateTime = dateFormat.format(date);
		return dateTime;
	}

	/**
	 * This method will copy one folder from source to destination
	 * 
	 * @param source
	 * @param destination
	 */
	public static void copyFolder(File source, File destination) {
		if (source.isDirectory()) {
			if (!destination.exists()) {
				destination.mkdirs();
			}
			String files[] = source.list();
			for (String file : files) {
				File srcFile = new File(source, file);
				File destFile = new File(destination, file);
				copyFolder(srcFile, destFile);
			}
		} else {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new FileInputStream(source);
				out = new FileOutputStream(destination);

				byte[] buffer = new byte[1024];

				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
			} catch (Exception e) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void scrollDownToPage() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
	}

	public void reloadPage() {
		driver.navigate().refresh();
	}

	public void selectValueFromDropdown(By locator, String value) {
		String drpSelectName = "//ul//li[normalize-space()='"+value+"']";
		try {
			waitFor(1000);
			waitUntilElementDisplay(locator);
			click(locator);
			waitFor(1000);
			if(!isElementVisible(By.xpath(drpSelectName))) {
				click(locator);
				waitFor(1000);
			}
			click(By.xpath(drpSelectName));
		} catch (Exception e) {
			click(locator);
			waitUntilElementDisplay(By.xpath(drpSelectName));
			click(By.xpath(drpSelectName));
		}
	}


	public void switchToIframe(By locator) {
		driver.switchTo().frame(driver.findElement(locator));
	}

	public void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectValueFromDropdown(String Value, By locator) {
		Select selct = new Select(driver.findElement(locator));
		selct.selectByVisibleText(Value);
	}

	public boolean isElementVisible(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isElementVisible(By by, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
}