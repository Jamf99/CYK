package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelGramatica extends JPanel {
	
	public PanelGramatica(int variables) {
		 TitledBorder border = BorderFactory.createTitledBorder("Crear gramática");
		 setPreferredSize(new Dimension(400,300));
		 border.setTitleColor(Color.RED);
		 setBorder(border);
		 setLayout(new BorderLayout());
		 
		 inicializarGramatica(variables);
	}
	
	public void inicializarGramatica(int cantidadVariables) {
		JLabel[] variables = new JLabel[cantidadVariables];
		JTextField[] producciones = new JTextField[cantidadVariables];
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(cantidadVariables, 2));
		for(int i = 0; i < cantidadVariables; i++) {
			int valor = 65 + i-1;
			char var;
			if(i == 0) {
				var = (char) 83;
			}else {
				var = (char)valor;
			}
			variables[i] = new JLabel("                  "+var+" →");
			producciones[i] = new JTextField();
			aux.add(variables[i]); aux.add(producciones[i]);
		}
		JScrollPane scroll = new JScrollPane(aux);
		add(scroll, BorderLayout.CENTER);
	}

}
