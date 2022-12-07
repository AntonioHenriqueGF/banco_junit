package br.com.cefet.banco.principal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.cefet.banco.negocio.NegocioTests;

@RunWith(Suite.class)
@SuiteClasses({NegocioTests.class})
public class AllTests {

}
