package br.ce.wcaquino.core;

import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

//Ficou faltando fazer o import abaixo, por�m � necess�rio a JDK 1.7+
import org.apache.commons.io.FileUtils;

public class BaseTest {
	
	@Rule
	public TestName testName = new TestName();
	
	
	@After
	public void finaliza() throws IOException{
		TakesScreenshot ss = (TakesScreenshot) DriverFactory.getDriver();
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		//tirar uma foto de cada teste e inserir o nome do teste.jpg. Al�m disso salvar� na pasta informada.
		FileUtils.copyFile(arquivo, new File ("target" + File.separator + "screenshot" 
				+ File.separator + testName.getMethodName() + ".jpg"));
		
		//Utiliza a informa��o de propriedades para decidir se o browser vai fechar ao final de cada teste ou n�o.
		if(Propriedades.FECHAR_BROWSER){
			killDriver();
		}
		
	}

}
