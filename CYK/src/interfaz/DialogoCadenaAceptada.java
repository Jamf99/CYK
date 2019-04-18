package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DialogoCadenaAceptada extends JDialog implements ActionListener {
	
	public final static String VOLVER = "Volver";
	
	private InterfazCYK principal;
	private PanelTabla panelTabla;
	private JButton butVolver;
	private JLabel mensaje;
	
	public DialogoCadenaAceptada(InterfazCYK principal, String[][] tablaCYK, String cadena, boolean genera) {
		this.principal = principal;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 200);
		setLayout(new BorderLayout());
		if(genera) {
			mensaje = new JLabel("¡La cadena \""+cadena+"\" es generada por la gramática!", SwingConstants.CENTER);
			setTitle("Cadena aceptada");
		}else {
			mensaje = new JLabel("La cadena \""+cadena+"\" NO es generada por la gramática", SwingConstants.CENTER);
			setTitle("Cadena no aceptada");
		}
		
		setResizable(false);
		
		panelTabla = new PanelTabla(tablaCYK);
		butVolver = new JButton(VOLVER);
		butVolver.addActionListener(this);
		butVolver.setActionCommand(VOLVER);
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(2,1));
		aux.add(mensaje);
		aux.add(butVolver);
		
		add(panelTabla, BorderLayout.CENTER);
		add(aux, BorderLayout.SOUTH);
		pack();
	}
	
	public void dispose() {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(VOLVER)) {
			principal.cerrarDialogoCadenaAceptada();
		}
	}

}
