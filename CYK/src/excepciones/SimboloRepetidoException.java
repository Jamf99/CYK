package excepciones;

public class SimboloRepetidoException extends Exception {
	
	public SimboloRepetidoException() {
		super("Hay un s�mbolo de su alfabeto que est� repetido");
	}

}
