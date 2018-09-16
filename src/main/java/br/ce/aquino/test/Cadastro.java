package br.ce.aquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.aquino.page.CampoTreinamentoPage;
import br.ce.wcaquino.core.BaseTest;

public class Cadastro extends BaseTest {
	
	private CampoTreinamentoPage page;

	//Metodo before que será executado automaticamente antes de qualquer teste:
	@Before
	public void inicializa(){
	
		System.setProperty("webdriver.chrome.driver", "C:/Users/erica.castro/Documents/Pessoais/Curso de Selenium/Chromedriver/chromedriver_win32/chromedriver.exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + ("/src/main/resources/componentes.html"));
		page = new CampoTreinamentoPage();
	}
	
	@Test
	public void deveRealizarCadastroComSucesso(){
		page.setNome("Letícia");
		page.setSobrenome("Melo");
	    page.setSexoMasculino();
	    page.setComidaPizza();
        page.setComidaVegetariana();
        page.setEscolaridade("Especializacao");
	
        //A linha abaixo faz a mesma coisa que a três ultimas linhas de cima:            
        //new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Especializacao");
	        
        page.setEsporte("Natacao", "Corrida");
        page.cadastrar();
        Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
        Assert.assertEquals("Letícia", page.obterNomeCadastro());  
        Assert.assertEquals("Melo", page.obterSobrenomeCadastro());  
        Assert.assertEquals("Masculino", page.obterSexoCadastro());
        Assert.assertEquals("Pizza Vegetariano", page.obterComidaCadastro());
        Assert.assertEquals("especializacao", page.obterEscolaridade());
        Assert.assertEquals("Natacao Corrida", page.obterEsporte());
		   
	}
		       
}
