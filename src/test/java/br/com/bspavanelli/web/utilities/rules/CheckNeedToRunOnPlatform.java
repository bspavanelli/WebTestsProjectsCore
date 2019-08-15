package br.com.bspavanelli.web.utilities.rules;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import br.com.bspavanelli.web.utilities.enums.WebBrowser;

public class CheckNeedToRunOnPlatform implements TestRule {

	private boolean checkNeedToRunOnPlatform;

	public CheckNeedToRunOnPlatform(WebBrowser os) {
		this.checkNeedToRunOnPlatform = verifyIfWillTestInPlatform(os);
	}

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				if (!checkNeedToRunOnPlatform) {
					throw new AssumptionViolatedException("Not the platform required. Skipping test!");
				} else {
					base.evaluate();
				}
			}
		};
	}

	public boolean verifyIfWillTestInPlatform(WebBrowser os) {
		String browser = System.getProperty("WebBrowser");

		if (browser == null)
			return true;

		if (WebBrowser.valueOfIgnoreCase(browser).equals(WebBrowser.ALL))
			return true;

		if (os.equals(WebBrowser.valueOfIgnoreCase(browser)))
			return true;

		return false;
	}

}
