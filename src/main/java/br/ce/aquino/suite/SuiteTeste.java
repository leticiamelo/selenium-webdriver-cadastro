package br.ce.aquino.suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.aquino.test.Cadastro;
import br.ce.aquino.test.TesteCampoTreinamento;
import br.ce.aquino.test.TesteRegrasCadastro;

	@RunWith(Suite.class)
	@SuiteClasses({
		Cadastro.class,
		TesteRegrasCadastro.class,
		TesteCampoTreinamento.class
	})
	public class SuiteTeste {

	}
