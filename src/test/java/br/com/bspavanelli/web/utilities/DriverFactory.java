package br.com.bspavanelli.web.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	public static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {
			createDriver();
		}
		return driver;
	}

	public static void createDriver() {

		switch (BaseConstants.webBrowser) {
			case CHROME:
				driver = new ChromeDriver();
				break;
			case EDGE:
				break;
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			case INTERNET_EXPLORER:
				break;
			default:
				break;
		}
		driver.manage()
			.timeouts()
			.implicitlyWait(BaseConstants.DEFAULT_IMPLICITLY_WAIT, TimeUnit.SECONDS);
		
		driver.get(BaseConstants.BASE_URL);
	}

	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}