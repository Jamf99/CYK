package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import modelo.Produccion;

public class PanelGramatica extends JPanel {
	
	private JTextField[] txtProducciones;
	
	public PanelGramatica(int variables, ArrayList<Produccion> producciones) {
		 TitledBorder border = BorderFactory.createTitledBorder("Crear gramática");
		 setPreferredSize(new Dimension(400,300));
		 border.setTitleColor(Color.RED);
		 setBorder(border);
		 setLayout(new BorderLayout());
		 
		 inicializarGramatica(variables, producciones);
	}
	
	public void inicializarGramatica(int cantidadVariables, ArrayList<Produccion> varProducciones) {
		JLabel[] variables = new JLabel[cantidadVariables];
		txtProducciones = new JTextField[cantidadVariables];
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(cantidadVariables, 2));
		for(int i = 0; i < cantidadVariables; i++) {
			variables[i] = new JLabel("                  "+varProducciones.get(i).getNombre()+" →");
			txtProducciones[i] = new JTextField();
			aux.add(variables[i]); aux.add(txtProducciones[i]);
		}
		JScrollPane scroll = new JScrollPane(aux);
		add(scroll, BorderLayout.CENTER);
	}
	
	public String[] darProducciones() {
		String[] producciones = new String[txtProducciones.length];
		for (int i = 0; i < txtProducciones.length; i++) {
			producciones[i] = txtProducciones[i].getText();
		}
		return producciones;
	}

}
