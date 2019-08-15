package br.com.bspavanelli.web.pages.home;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.bspavanelli.web.utilities.screen_actions.ScreenActions;

public class HomePage extends ScreenActions {

	@FindBy(id = "home-btn")
	private WebElement btn_meetOurClients;
	
	public HomePage() {
		initElements(this);
	}
	
	public HomePage checkMeetOurClientsButton() {
		elementExists(btn_meetOurClients);
		return this;
	}
}
