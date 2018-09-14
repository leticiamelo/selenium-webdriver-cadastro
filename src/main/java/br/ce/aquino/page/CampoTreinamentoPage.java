package br.ce.aquino.page;
import org.openqa.selenium.By;

import br.ce.wcaquino.core.DSL;

public class CampoTreinamentoPage {
	
	private DSL dsl;
	
	public CampoTreinamentoPage(){
		dsl = new DSL();
	}
		
	public void setNome(String nome){
		dsl.escreve("elementosForm:nome", nome);
	}
	
	public void setSobrenome (String sobrenome){
		
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino (){
		
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino (){
		
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public void setComidaCarne(){
		
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaFrango(){
		
		dsl.clicarRadio("elementosForm:comidaFavorita:1");
	}
	
	public void setComidaPizza(){
		
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariana(){
		
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String valor){
		
		dsl.selecionarCombo("elementosForm:escolaridade", valor);
	}
	
	public void setEsporte(String... valores){
		for(String valor: valores)
		dsl.selecionarCombo("elementosForm:esportes", valor);
	}
	
	public void cadastrar(){
		
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro(){
		
		return dsl.obterTexto(By.xpath(".//*[@id='resultado']/span"));
	}
	
	public String obterNomeCadastro(){
		return dsl.obterTexto(By.xpath(".//*[@id='descNome']/span"));
	}
	
	public String obterSobrenomeCadastro(){
	
		return dsl.obterTexto(By.xpath(".//*[@id='descSobrenome']/span"));
	}
	
	public String obterSexoCadastro(){
		
		return dsl.obterTexto(By.xpath(".//*[@id='descSexo']/span"));
	}
	
	public String obterComidaCadastro(){
		
		return dsl.obterTexto(By.xpath(".//*[@id='descComida']/span"));
	}
	
	public String obterEscolaridade(){
		
		return dsl.obterTexto(By.xpath(".//*[@id='descEscolaridade']/span"));
	}
	
	public String obterEsporte(){
		
		return dsl.obterTexto(By.xpath(".//*[@id='descEsportes']/span"));
	}
}