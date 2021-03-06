package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Produccion;

public class DialogoGramatica extends JDialog implements ActionListener{
	
	public final static String VALIDAR_CADENA = "Validar Cadena";
	public final static String VOLVER = "Volver";
	
	private InterfazCYK principal;
	private PanelGramatica panelGramatica;
	
	private JButton butValidarCadena;
	private JButton butVolver;
	private JLabel labValidarCadena;
	private JTextField txtValidarCadena;

	public DialogoGramatica(InterfazCYK principal, int variables, ArrayList<Produccion> producciones) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.principal = principal;
		setSize(350, 250);
		setLayout(new BorderLayout());
		setTitle("Algoritmo de CYK");
		setResizable(false);
		
		panelGramatica = new PanelGramatica(variables, producciones);
		
		butValidarCadena = new JButton(VALIDAR_CADENA);
		butValidarCadena.addActionListener(this);
		butValidarCadena.setActionCommand(VALIDAR_CADENA);
		butVolver = new JButton(VOLVER);
		butVolver.addActionListener(this);
		butVolver.setActionCommand(VOLVER);
		
		labValidarCadena = new JLabel("          Cadena a validar:");
		txtValidarCadena = new JTextField();
		
		JPanel aux1 = new JPanel();
		aux1.setLayout(new GridLayout(2, 2));
		aux1.add(labValidarCadena); aux1.add(txtValidarCadena);
		aux1.add(butValidarCadena); aux1.add(butVolver);
		
		add(aux1, BorderLayout.SOUTH);
		add(panelGramatica, BorderLayout.CENTER);
	}
	
	public void borrarValorCampo() {
		txtValidarCadena.setText("");
	}
	
	public void dispose() {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(VALIDAR_CADENA)) {
			principal.validacionesGramatica(panelGramatica.darProducciones(), txtValidarCadena.getText());
		}else if(comando.equals(VOLVER)) {
			principal.reiniciar();
		}
	}
}
