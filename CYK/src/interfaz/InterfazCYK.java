package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InterfazCYK extends JFrame {
	private JLabel labAlfabeto;
	private JTextField txtAlfabeto;
	private JLabel labCantidadVariables;
	private JTextField txtCantidadVariables;
	private JButton butEmpezar;
	
	public InterfazCYK() {
		setSize(new Dimension(500,300));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Algoritmo de CYK");
		setLayout(new BorderLayout());
		labAlfabeto = new JLabel("<html>Ingrese el alfabeto de la gramática<br>d(separado por comas y sin espacios</html>");
		txtAlfabeto = new JTextField();
		labCantidadVariables = new JLabel("<html>Ingrese el número de variables<br>que tendrá la gramática</html>");
		txtCantidadVariables = new JTextField();
		butEmpezar = new JButton("Empezar");
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(2,2));
		aux.add(labAlfabeto);
		aux.add(txtAlfabeto);
		aux.add(labCantidadVariables);
		aux.add(txtCantidadVariables);
		add(aux, BorderLayout.CENTER);
		add(butEmpezar, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		InterfazCYK cyk = new InterfazCYK();
		cyk.setVisible(true);
	}

}
