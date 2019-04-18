package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import excepciones.AlfabetoInvalidoException;
import excepciones.CampoVacioException;
import excepciones.GramaticaInvalidaException;
import excepciones.SimboloRepetidoException;
import modelo.Gramatica;
import modelo.Produccion;

public class InterfazCYK extends JFrame implements ActionListener {
	
	public final static String EMPEZAR = "Empezar";
	
	private DialogoGramatica dg;
	private DialogoCadenaAceptada ca;
	private JLabel labAlfabeto;
	private JTextField txtAlfabeto;
	private JLabel labCantidadVariables;
	private JTextField txtCantidadVariables;
	private JButton butEmpezar;
	
	private Gramatica gramatica;
	
	public InterfazCYK() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		try {
			if(txtCantidadVariables.getText().equals("") || txtAlfabeto.getText().equals("")) {
				throw new CampoVacioException();
			}else {
				int cantidadVariables = Integer.parseInt(txtCantidadVariables.getText());
				String[] alfabeto = txtAlfabeto.getText().split(",");
				
				for (int i = 0; i < alfabeto.length; i++) {
					if(alfabeto[i].length()!=1) {
						throw new AlfabetoInvalidoException();
					}
				}
				
				for (int i = 0; i < alfabeto.length; i++) {
					for (int j = 0; j < alfabeto.length; j++) {
						if(alfabeto[i].equals(alfabeto[j]) && i!=j) {
							throw new SimboloRepetidoException();
						}
					}
				}
				gramatica = new Gramatica(cantidadVariables, alfabeto);
				abrirGramatica(cantidadVariables, gramatica.getProducciones());
			}
		}catch(Exception e) {
			if(e.getClass()== NumberFormatException.class){
				JOptionPane.showMessageDialog(this, "Procure de que el número de variables sea válido", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void abrirGramatica(int variables, ArrayList<Produccion> producciones) {
		dg = new DialogoGramatica(this, variables, producciones);
		dg.setLocationRelativeTo(this);
		dg.setVisible(true);
		butEmpezar.setEnabled(false);
		this.setVisible(false);
	}
	
	public void reiniciar() {
		butEmpezar.setEnabled(true);
		dg.setVisible(false);
		this.setVisible(true);
		gramatica = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(EMPEZAR)) {
			validar();
		}
	}
	
	public void validacionesGramatica(String[] prod, String cadena) {
		try {
			for(int i = 0; i < prod.length; i++) {
				if(prod[i].equals("") || cadena.equals("")) {
					throw new CampoVacioException();
				}
			}
			if(gramatica.validarCadenaPerteneceAlAlfabeto(cadena)) {
				if(gramatica.validarProducciones(prod)) {
					ejecutarCYK(prod, cadena);
				}else {
					throw new GramaticaInvalidaException();
				}
			}else {
				throw new GramaticaInvalidaException();
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void ejecutarCYK(String[] prod, String cadena) {
		for(int i = 0; i < gramatica.getProducciones().size(); i++) {
			String[] p = prod[i].split(",");
			for(int j = 0; j < p.length; j++) {
				gramatica.agregarProducciones(i, p[j]);
			}
		}
		boolean genera = gramatica.CYK(cadena);
		ca = new DialogoCadenaAceptada(this, gramatica.getTablaCYK(), cadena, genera);
		ca.setLocationRelativeTo(this);
		ca.setVisible(true);
		dg.setVisible(false);
	}
	
	public void cerrarDialogoCadenaAceptada() {
		ca.setVisible(false);
		dg.setVisible(true);
		dg.borrarValorCampo();
	}
	
	public static void main(String[] args) {
		InterfazCYK cyk = new InterfazCYK();
		cyk.setVisible(true);
	}

}
