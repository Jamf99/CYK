package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelTabla extends JPanel {
	
	private JTable tabla;
	
	public PanelTabla(String[][] tablaCYK) {
		TitledBorder border = BorderFactory.createTitledBorder("Matriz de seguimiento del Algoritmo CYK");
		border.setTitleColor(Color.RED);
		setBorder(border);
		setLayout(new BorderLayout());
		inicializarTabla(tablaCYK);
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension((400*tablaCYK.length)/6, (100*tablaCYK.length)/6));
		add(scroll, BorderLayout.CENTER);
	}
	
	public void inicializarTabla(String[][] tablaCYK) {
		String[] p = new String[tablaCYK.length];
		DefaultTableModel dtm= new DefaultTableModel(tablaCYK, p) {
			 @Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabla = new JTable(dtm);
		tabla.setTableHeader(null);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i = 0; i < tablaCYK.length; i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}
		
	}

}
