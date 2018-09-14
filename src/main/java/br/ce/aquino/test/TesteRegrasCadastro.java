package br.ce.aquino.test;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.aquino.page.CampoTreinamentoPage;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

	@RunWith(Parameterized.class)
	public class TesteRegrasCadastro {
	
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public Object sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msg;

	//Metodo before que ser� executado automaticamente antes de qualquer teste:
	@Before
	public void inicializa(){
		System.setProperty("webdriver.chrome.driver", "C:/Users/erica.castro/Documents/Pessoais/Curso de Selenium/Chromedriver/chromedriver_win32/chromedriver.exe");
		
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + ("/src/main/resources/componentes.html"));
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void finaliza(){
		killDriver();
	}	
		
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object [] []{
			{"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			{"Let�cia", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			{"Let�cia", "Melo", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Let�cia", "Melo", "Feminino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Let�cia", "Melo", "Feminino", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void ValidarItemOqueEhEsporte(){
		
		page.setNome(nome);	
		page.setSobrenome(sobrenome);
		if(sexo.equals("Masculino")){
			page.setSexoMasculino();
		}if (sexo.equals("Feminino")){
			page.setSexoFeminino();
		}
		
		if (comidas.contains("Carne"))	page.setComidaCarne();	
		if (comidas.contains("Pizza"))	page.setComidaPizza();
		if (comidas.contains("Vegetariano"))	page.setComidaVegetariana();
		page.setEsporte(esportes);
		
		page.cadastrar();
		System.out.println(msg);
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
						
	}
}
