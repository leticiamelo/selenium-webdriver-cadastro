package br.ce.aquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TesteAlert {
	
	private DSL dsl;

	//Metodo before que será executado automaticamente antes de qualquer teste:
	@Before
	public void inicializa(){
	
		System.setProperty("webdriver.chrome.driver", "C:/Users/erica.castro/Documents/Pessoais/Curso de Selenium/Chromedriver/chromedriver_win32/chromedriver.exe");
		
		getDriver().get("file:///" + System.getProperty("user.dir") + ("/src/main/resources/componentes.html"));
		dsl = new DSL ();
	}
	
	@After
	public void finaliza(){
		
		DriverFactory.killDriver();
	}

	@Test
	public void deveInteragirComAlertSimples(){
		
	   dsl.clicarBotao("alert");
		
	   //Guardar o texto do alert em uma variável:		
		String texto = dsl.alertaObterTextoEAceita();
		
		//Verificando o texto do alert		
		Assert.assertEquals("Alert Simples", texto);
	
		//Inserindo o texto do alert em outro campo:		
		dsl.escreve("elementosForm:nome", texto);
	}
	
	@Test
	public void deveInteragirComAlertComConfirmacao(){
		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
		
	
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoENega());
		
	}
	
	@Test
	public void deveInteragirComAlertComNegacao(){
		
		DriverFactory.getDriver().findElement(By.id("confirm")).click();
		
		//Para o webdriver pegar o alert:
		Alert alertNeg = DriverFactory.getDriver().switchTo().alert();
	
		//Para clicar no botão Ok do alerta:
		alertNeg.dismiss();
		
		//Guardar o texto do alert em uma variável:		
		String texto3 = alertNeg.getText();
		
		//Verificando o texto do alert		
		Assert.assertEquals("Negado", texto3);
		
		alertNeg.accept();
	
		//Inserindo o texto do alert em outro campo:		
		DriverFactory.getDriver().findElement(By.id("elementosForm:nome")).sendKeys(texto3);
	}
	
	@Test
	public void deveInteragirComAlertComCampodeTexto(){
		
	    dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		//Inserir dados no campo de texto:
		dsl.alertaEscrever("10");
		//Verificando o texto do alert		
		Assert.assertEquals("Era 10?", dsl.alertaObterTextoEAceita());
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
	}
}
