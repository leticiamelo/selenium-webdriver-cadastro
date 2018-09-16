package br.ce.wcaquino.core;

public class Propriedades {
	//essa constante fecha o browser a cada teste. Apesar de mais demorado, é o mais recomendado.
	public static boolean FECHAR_BROWSER = false;
	
	public static Browsers browser = Browsers.CHROME;
	
	public enum Browsers{
		CHROME,
		FIREFOX
	}
}
