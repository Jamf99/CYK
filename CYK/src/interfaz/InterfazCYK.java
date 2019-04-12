package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class InterfazCYK extends JFrame implements ActionListener {
	
	public final static String EMPEZAR = "Empezar";
	
	private JLabel labAlfabeto;
	private JTextField txtAlfabeto;
	private JLabel labCantidadVariables;
	private JTextField txtCantidadVariables;
	private JButton butEmpezar;
	
	public InterfazCYK() {
		setSize(new Dimension(500,250));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Algoritmo de CYK");
		setLayout(new BorderLayout());
		labAlfabeto = new JLabel("<html>Ingrese el alfabeto de la gramática<br>(separado por comas y sin espacios)</html>");
		txtAlfabeto = new JTextField();
		labCantidadVariables = new JLabel("<html>Ingrese el número de variables<br>que tendrá la gramática</html>");
		txtCantidadVariables = new JTextField();
		butEmpezar = new JButton("Empezar");
		butEmpezar.setActionCommand(EMPEZAR);
		butEmpezar.addActionListener(this);
		JPanel aux = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder("Rellene todos los campos");
		border.setTitleColor(Color.RED);
		aux.setBorder(border);
		aux.setLayout(new GridLayout(5,2));
		aux.add(new JLabel());aux.add(new JLabel());
		aux.add(labAlfabeto);
		aux.add(txtAlfabeto);
		aux.add(new JLabel());aux.add(new JLabel());
		aux.add(labCantidadVariables);
		aux.add(txtCantidadVariables);
		aux.add(new JLabel());aux.add(new JLabel());
		add(aux, BorderLayout.CENTER);
		add(butEmpezar, BorderLayout.SOUTH);
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){	
		}
	}
	
	public void validar() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(EMPEZAR)) {
			validar();
		}
	}
	
	public static void main(String[] args) {
		InterfazCYK cyk = new InterfazCYK();
		cyk.setVisible(true);
	}

}
