package br.com.bspavanelli.web.utilities;

import org.junit.Rule;
import org.junit.rules.TestName;

import br.com.bspavanelli.web.pages.home.HomePage;
import br.com.bspavanelli.web.utilities.listener.TestListener;

public class BaseTest {

	@Rule
	public TestName testName = new TestName();

	@Rule
	public TestListener listener = new TestListener();

	protected HomePage homePage;
	
	public void setUpPages() {
		System.out.println("Iniciando os testes do método '" + testName.getMethodName() + "' no browser '"
				+ BaseConstants.webBrowser.getValor() + "'!");

		homePage = new HomePage();		
	}

}
