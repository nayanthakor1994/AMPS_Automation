package com.base;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public synchronized static void setTLDriver(String browser) {
		if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + File.separator + "Driver" + File.separator + "geckodriver.exe");
			tlDriver = ThreadLocal.withInitial(() -> new FirefoxDriver());
		} else if (browser.equals("chrome")) {
			System.out.println("Creating driver for thread: " + Thread.currentThread().getId());
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + File.separator + "Driver" + File.separator + "chromedriver.exe");
			tlDriver = ThreadLocal.withInitial(() -> new ChromeDriver());
		} else if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + File.separator + "Driver" + File.separator + "msedgedriver.exe");
			tlDriver = ThreadLocal.withInitial(() -> new EdgeDriver());
		}
	}

	public synchronized static WebDriver getTLDriver() {
		try {
			return tlDriver.get();
		} catch (Exception e) {
			return tlDriver.get();
		}
	}

}
