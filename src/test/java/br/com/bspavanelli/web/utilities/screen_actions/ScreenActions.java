package br.com.bspavanelli.web.utilities.screen_actions;

import static br.com.bspavanelli.web.utilities.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import br.com.bspavanelli.web.utilities.BaseConstants;

public class ScreenActions {

	/**
	 * Perform a sleep
	 * 
	 * @param time
	 *            = Time in miliseconds
	 */
	protected void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send keys to the element
	 * 
	 * @param element
	 * @param text
	 */
	protected void sendKeys(WebElement element, String text) {
		element.sendKeys(text);
	}

	/**
	 * Get the attribute value of the element
	 * 
	 * @param element
	 * @param attribute
	 * @return
	 */
	protected String getAttribute(WebElement element, String attribute) {
		String text = "";
		try {
			text = element.getAttribute(attribute);
		} catch (StaleElementReferenceException e) {
			sleep(500);
			text = element.getAttribute(attribute);
		}
		return removeOtherSpaces(text);
	}

	/**
	 * Get the text of the element
	 * 
	 * @param element
	 * @return
	 */
	protected String getText(WebElement element) {
		String text = "";
		try {
			text = element.getText();
		} catch (StaleElementReferenceException e) {
			sleep(500);
			text = element.getText();
		}
		return removeOtherSpaces(text);
	}

	/**
	 * Click on an element
	 * 
	 * @param element
	 */
	protected void click(WebElement element) {
		element.click();
		sleep(1000);
	}

	/**
	 * Click on an element found in a list with the following value of an attribute
	 * 
	 * @param element
	 */
	protected void click(List<WebElement> elements, String attribute, String value) {
		for (WebElement element : elements) {
			if (removeOtherSpaces(element.getAttribute(attribute)).equals(value)) {
				element.click();
				return;
			}
		}
		throw new RuntimeException(
				"Erro! Elemento com atributo '" + attribute + "' de valor '" + value + "' não encontrado.");
	}

	/**
	 * Click on an element found by text
	 * 
	 * @param text
	 */
	protected void click(String text) {
		click(getDriver().findElement(By.xpath("//*[@text='" + text + "' or @value='" + text + "']")));
	}

	/**
	 * Click on an element found by attribute value
	 * 
	 * @param attribute
	 *            = the attribute to search
	 * @param text
	 */
	protected void click(String attribute, String value) {
		click(getDriver().findElement(By.xpath("//*[@" + attribute + "='" + value + "']")));
	}

	/**
	 * Click on a Combo element and select the option searching by value
	 * 
	 * @param element
	 *            = Element to click
	 * @param value
	 *            = Value to select
	 */
	protected void selectCombo(WebElement element, String value) {
		element.click();
		click(value);
	}

	/**
	 * Verify if the attribute checked of the element is true
	 * 
	 * @param element
	 * @return
	 */
	protected boolean isChecked(WebElement element) {
		return element.getAttribute("checked")
			.equals("true");
	}

	/**
	 * Check if the element exists
	 * 
	 * @param element
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(WebElement element) {
		try {
			element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Check if the element exists
	 * 
	 * @param element
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(List<WebElement> elements) {
		return elements.size() > 0;
	}

	/**
	 * Check if the element exists
	 * 
	 * @param element
	 * @param timeout
	 *            = the implicitly wait to be set before the check
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(List<WebElement> elements, int timeout) {
		return elements.size() > 0;
	}

	/**
	 * Check if the element exists, searching by the given text.
	 * 
	 * @param text
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(String text) {
		return elementExists(getDriver().findElements(By.xpath("//*[@text='" + text + "' or @value='" + text + "']")));
	}

	/**
	 * Check if the element exists, searching by the given text.
	 * 
	 * @param text
	 * @param timeout
	 *            = the implicitly wait to be set before the check
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(String text, int timeout) {
		return elementExists(getDriver().findElements(By.xpath("//*[@text='" + text + "' or @value='" + text + "']")),
				timeout);
	}

	/**
	 * Check if the element exists, searching by the given text.
	 * 
	 * @param attribute
	 *            = the attribute to search
	 * @param text
	 *            = the value of the attribute
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(String attribute, String text) {
		return elementExists(getDriver().findElements(By.xpath("//*[@" + attribute + "='" + text + "']")));
	}

	/**
	 * Check if the element exists, searching by the given text.
	 * 
	 * @param attribute
	 *            = the attribute to search
	 * @param text
	 *            = the value of the attribute
	 * @param timeout
	 *            = the implicitly wait to be set before the check
	 * @return = true if found, false if not
	 */
	protected boolean elementExists(String attribute, String text, int timeout) {
		return elementExists(getDriver().findElements(By.xpath("//*[@" + attribute + "='" + text + "']")), timeout);
	}

	/**
	 * Remove all double space, line break and other types of space except simple
	 * space of the string
	 * 
	 * @param string
	 * @return string with only simple spaces
	 *
	 */
	protected String removeOtherSpaces(String string) {
		return string.replaceAll("\u00a0", " ")
			.replaceAll("\\s+", " ")
			.trim();
	}

	/**
	 * Execute the initElements of the Page Factory with a field decorator timeout
	 * equals to the param seconds
	 * 
	 * @param page
	 * @param seconds
	 */
	protected void initElements(Object page, int seconds) {
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), seconds), page);
	}

	/**
	 * Execute the initElements of the Page Factory with a field decorator timeout
	 * equals to the default in BaseConstants
	 * 
	 * @param page
	 */
	protected void initElements(Object page) {
		initElements(page, BaseConstants.DEFAULT_IMPLICITLY_WAIT);
	}

}
