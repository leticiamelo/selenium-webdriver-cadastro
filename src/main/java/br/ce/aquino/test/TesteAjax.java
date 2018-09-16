package br.ce.aquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TesteAjax {
	
	//criação da variavel driver:
	private DSL dsl;
		
	//Metodo before que será executado automaticamente antes de qualquer teste:
	@Before
	public void inicializa(){
	
		System.setProperty("webdriver.chrome.driver", "C:/Users/erica.castro/Documents/Pessoais/Curso de Selenium/Chromedriver/chromedriver_win32/chromedriver.exe");
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}
	
	@After
	public void finaliza(){
		
//		DriverFactory.killDriver();
	}

	@Test
	public void testAjax(){
		dsl.escreve("j_idt671:name", "teste");
		dsl.clicarBotao("j_idt671:j_idt674");
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt671:display"), "teste"));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt98")));
		Assert.assertEquals("teste", dsl.obterTexto("j_idt671:display"));
	}
	
}
