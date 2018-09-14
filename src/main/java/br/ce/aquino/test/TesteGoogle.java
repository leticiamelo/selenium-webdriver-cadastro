package br.ce.aquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.core.DriverFactory;

public class TesteGoogle {
	
	@Test
	public void teste() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/erica.castro/Documents/Pessoais/Curso de Selenium/Chromedriver/chromedriver_win32/chromedriver.exe");
		System.out.println("Debug 1");
		System.out.println("Debug 2");
		getDriver().get("http://www.google.com.br");
		Assert.assertEquals("Google", DriverFactory.getDriver().getTitle());
		DriverFactory.killDriver();
	}

}
