package br.ce.aquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.ce.aquino.page.CampoTreinamentoPage;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TesteCampoTreinamento {

//criação da variavel driver:
	private DSL dsl;
	private CampoTreinamentoPage page;

//Metodo before que será executado automaticamente antes de qualquer teste:
	@Before
	public void inicializa(){
	
		System.setProperty("webdriver.chrome.driver", "C:/Users/erica.castro/Documents/Pessoais/Curso de Selenium/Chromedriver/chromedriver_win32/chromedriver.exe");
		
		getDriver().get("file:///" + System.getProperty("user.dir") + ("/src/main/resources/componentes.html"));
	
		dsl = new DSL();
		page = new CampoTreinamentoPage ();
	}
	
	@After
	public void finaliza(){
		DriverFactory.killDriver();
		
	}
	
	@Test
	public void testeTextField(){
		
		page.setNome("Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea(){
			
		//a linha abaixo serve para procurar um elemento e inserir um valor nele:	
		dsl.escreve("elementosForm:sugestoes", "teste");
		
		//validar se o texto foi inserido corretamente:		
		Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInteragirComRadioButton(){
		
		//como clicar em um elemento       
		dsl.clicarRadio("elementosForm:sexo:0");
		// Validando se o elemento foi clicado corretamente:		
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }
	
	@Test
	public void deveInteragirComCheckbox(){
		
		//como clicar em um elemento   
		dsl.clicarCheck("elementosForm:comidaFavorita:0");
		// Validando se o elemento foi clicado corretamente:		
	    Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:0"));
	    
	}
		
	@Test
	public void deveInteragirComCombo(){
		
		dsl.selecionarCombo("elementosForm:escolaridade", "Especializacao");
		
		//para verificar qual o valor que está selecionado
		Assert.assertEquals("Especializacao", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
		
	@Test
	public void deveVerificarValoresCombo(){

		Assert.assertEquals(8,  dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
		
	}
		
	@Test
	public void deveVerificarValoresComboMultiplo(){
		
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
        
		//verificando quantidade de itens selecionados no combo:		
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		//para retirar a seleção do combo.
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");

		//verificando quantidade de itens selecionados no combo:
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2,  opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
		
	@Test
	public void deveInteragirComBotoes(){
		
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	@Test
	//@Ignore
	public void deveInteragirComLinks(){
		
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	    
		//coloque o comando abaixo para que o teste falhe. Serve para lembrar de completar testes incompletos:		
		//Assert.fail();
	}
		
		
	@Test
	public void deveBuscarTextoNaPagina(){
		
    	//Exibe todo o texto visivel da tela	   	
		//System.out.println(driver.findElement(By.tagName("body")).getText());
		
		//	Procurando um texto dentro da tela.	
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
				
		//Codigo para verificar se um texto existe em um determinado campo/lugar:
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
						
		
		//Procurando pelo nome da classe:
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", 
				dsl.obterTexto(By.className("facilAchar")));
	}
	
	@Test
	public void validaCampoEApagaCampo(){
		
		dsl.escreve("elementosForm:nome", "Letícia");
		Assert.assertEquals("Letícia" , dsl.obterValorCampo("elementosForm:nome"));
		dsl.escreve("elementosForm:nome", "Melo");
		Assert.assertEquals("Melo" , dsl.obterValorCampo("elementosForm:nome"));
	}
		
	@Test
     public void testJavascript(){
    	 
    	 JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
    	 //js.executeScript("alert('Testando js via selenium')");
    	 js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js' ");
    	 js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");

    	 //deixa uma borda vermelha no campo.    	 
    	 WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:nome"));
    	 js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
    	 
     }
     
     @Test
     public void deveClicarBotaoTabela(){
    	 dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
     }
      	
}
