package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class PanelGramatica extends JPanel {
	
	private JTable tabla;
	
	public PanelGramatica() {
		 TitledBorder border = BorderFactory.createTitledBorder("Cree su graḿatica");
		 border.setTitleColor(Color.RED);
		 setBorder(border);
		 setLayout(new BorderLayout());
	}

}
