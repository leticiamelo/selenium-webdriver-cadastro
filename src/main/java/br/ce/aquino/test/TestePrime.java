package br.ce.aquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TestePrime {
	
	private DSL dsl;
	
	//Metodo before que será executado automaticamente antes de qualquer teste:
		@Before
		public void inicializa(){
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/erica.castro/Documents/Pessoais/Curso de Selenium/Chromedriver/chromedriver_win32/chromedriver.exe");
		
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl = new DSL();
		}
		
		@After
		public void finaliza(){
			
			DriverFactory.killDriver();
		}
	
	@Test
	public void deveInteragirComRadioPrime(){
		dsl.clicarRadio(By.xpath(".//*[@id='j_idt115:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt115:console:0"));
		}
	
	}
