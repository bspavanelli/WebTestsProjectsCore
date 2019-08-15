package br.com.bspavanelli.web.tests.home;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import br.com.bspavanelli.web.utilities.BaseConstants;
import br.com.bspavanelli.web.utilities.enums.WebBrowser;
import br.com.bspavanelli.web.utilities.rules.CheckNeedToRunOnPlatform;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class Test_HomeChrome extends HomeTest {
	private static WebBrowser browser = WebBrowser.CHROME;

	@ClassRule
	public static CheckNeedToRunOnPlatform checkNeedToRunOnPlatform = new CheckNeedToRunOnPlatform(browser);

	@Before
	public void setUpPlatformAndPages() {
		BaseConstants.webBrowser = browser;
		setUpPages();
	}

	@Test
	@Severity(SeverityLevel.NORMAL)
	@Feature("Chrome")
	@Story("Home Page")
	public void verifyHomePage() {
		super.verifyHomePage();
	}
}
