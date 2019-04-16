package interfaz;

import java.awt.BorderLayout;

import javax.swing.JDialog;

public class DialogoGramatica extends JDialog {
	
	private InterfazCYK principal;

	public DialogoGramatica(InterfazCYK principal) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.principal = principal;
		setSize(1200, 600);
		setLayout(new BorderLayout());
		setTitle("Construya su gramática");
		setResizable(false);
	}
	
	public void dispose() {
		principal.reiniciar();
	}
}
