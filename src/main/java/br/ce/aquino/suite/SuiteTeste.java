package br.ce.aquino.suite;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.aquino.test.Cadastro;
import br.ce.aquino.test.TesteRegrasCadastro;
import br.ce.wcaquino.core.DriverFactory;

@RunWith(Suite.class)
@SuiteClasses({
	Cadastro.class,
	TesteRegrasCadastro.class,
	
})

	
	public class SuiteTeste {
		@AfterClass
		public static void finalizaTudo(){
		DriverFactory.killDriver();
	}

}
