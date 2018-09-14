package br.ce.aquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;


public class FramesEJanelas {
	
	private DSL dsl;

	//Metodo before que será executado automaticamente antes de qualquer teste:
	@Before
	public void inicializa(){
	
		System.setProperty("webdriver.chrome.driver", "C:/Users/erica.castro/Documents/Pessoais/Curso de Selenium/Chromedriver/chromedriver_win32/chromedriver.exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + ("/src/main/resources/componentes.html"));
		dsl = new DSL();
	}
		
	@After
	public void finaliza(){
		
		DriverFactory.killDriver();
	}

	@Test
	public void deveInteragirComFrame(){
			
		//Interagir com Frames			
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");

		//Como o  frame abriu um alert, agora inserimos o código para interagir com alert:
		String texto = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", texto);
		
		dsl.sairFrame();
		dsl.escreve("elementosForm:nome", texto);
	}
		
	@Test
	public void deveInteragirComFrameEscondido(){
			
		WebElement frame = DriverFactory.getDriver().findElement(By.id("frame2"));
		//IMPORTANTE: esse comando encontra elementos que não estão sendo encontrados. Encontra o elemento pela posição x e y.			
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String texto = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", texto);
	}
	
	@Test
	public void deveInteragirComJanelas(){
		dsl.clicarBotao("buttonPopUpEasy");
		//Focar na pop-up:			
		dsl.trocarJanela("PopUp");
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		//Fecha apenas a pop-up:			
		DriverFactory.getDriver().close();
		//Volta pra tela principal:			
		dsl.trocarJanela("");
		dsl.escreve(By.tagName("textarea"), "e agora?");
		
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo(){
		
		dsl.clicarBotao("buttonPopUpHard");
		//Quando a pop-up não possui titulo, devemos identificar o Handle da tela. O segundo comando identifica todos os handles de todas as páginas abertas.			
		System.out.println(DriverFactory.getDriver().getWindowHandle());
		System.out.println(DriverFactory.getDriver().getWindowHandles());	
		//Abrir a pop-up já  com o  handle correto. Foi feito um cashing para o tipo string.		
		dsl.trocarJanela((String) DriverFactory.getDriver().getWindowHandles().toArray()[1]);
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		//Retornando pra tela principal. Só troquei a posição do array:			
		dsl.trocarJanela((String) DriverFactory.getDriver().getWindowHandles().toArray()[0]);
		dsl.escreve(By.tagName("textarea"), "E agora?");
	}
}
