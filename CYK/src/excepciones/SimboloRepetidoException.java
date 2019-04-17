package excepciones;

public class SimboloRepetidoException extends Exception {
	
	public SimboloRepetidoException() {
		super("Hay un símbolo de su alfabeto que está repetido");
	}

}
