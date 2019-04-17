package tests;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import modelo.Gramatica;

class TestCYK extends TestCase {

	private Gramatica mundo;
	
	private void escenario1() {
		String[] alfabeto = new String[2];
		alfabeto[0] = "a"; alfabeto[1] = "b";
		mundo = new Gramatica(4, alfabeto);
		mundo.agregarProducciones(0, "BA");
		mundo.agregarProducciones(0, "AC");
		mundo.agregarProducciones(1, "CC");
		mundo.agregarProducciones(1, "b");
		mundo.agregarProducciones(2, "AB");
		mundo.agregarProducciones(2, "a");
		mundo.agregarProducciones(3, "BA");
		mundo.agregarProducciones(3, "a");
	}
	
	@Test
	public void cykTest1() {
		escenario1();
		assertTrue(mundo.CYK("bbab"));
	}

}
