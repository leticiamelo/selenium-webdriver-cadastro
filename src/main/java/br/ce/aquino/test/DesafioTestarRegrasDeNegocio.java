package br.ce.aquino.test;
	import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.aquino.page.CampoTreinamentoPage;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;


public class DesafioTestarRegrasDeNegocio {
	
	private DSL dsl;
	private CampoTreinamentoPage page;

	//Metodo before que será executado automaticamente antes de qualquer teste:
	@Before
	public void inicializa(){
	
		System.setProperty("webdriver.chrome.driver", "C:/Users/erica.castro/Documents/Pessoais/Curso de Selenium/Chromedriver/chromedriver_win32/chromedriver.exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + ("/src/main/resources/componentes.html"));
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void finaliza(){
		
		DriverFactory.killDriver();
	}	

	@Test
	public void ValidarNomeObrigatorio(){
		
		page.cadastrar();
					
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
	
	}
	
	@Test
	public void ValidarSobrenomeObrigatorio(){
		
		page.setNome("Leticia");	
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void ValidarSexoObrigatorio(){
		
		page.setNome("Leticia");	
		page.setSobrenome("Melo");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
	}	
	
	@Test
	public void ValidarComidaVegetariana(){
		
		page.setNome("Leticia");	
		page.setSobrenome("Melo");
		page.setSexoFeminino();				
		page.setComidaCarne();
		page.setComidaVegetariana();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void ValidarItemOqueEhEsporte(){
		
		page.setNome("Leticia");	
		page.setSobrenome("Melo");
		page.setSexoFeminino();						
		page.setEsporte("Futebol");
		page.setEsporte("O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
								
	}
				
}
