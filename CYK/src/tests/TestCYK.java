package tests;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import modelo.Gramatica;

class TestCYK extends TestCase {

	private Gramatica mundo;
	
	private void escenario1() {
		String[] alfabeto = new String[2];
		alfabeto[0] = "a"; alfabeto[1] = "b";
		mundo = new Gramatica(2, alfabeto);
		mundo.agregarProducciones(0, "AA");
		mundo.agregarProducciones(0, "a");
		mundo.agregarProducciones(1, "aA");
		mundo.agregarProducciones(1, "b");
		mundo.agregarProducciones(1, "a");
	}
	
	@Test
	public void cykTest1() {
		escenario1();
		assertTrue(mundo.CYK("aba"));
	}

}
