package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogoGramatica extends JDialog implements ActionListener{
	
	public final static String VALIDAR_CADENA = "Validar Cadena";
	
	private InterfazCYK principal;
	private PanelGramatica panelGramatica;
	private JButton butValidarCadena;
	private JLabel labValidarCadena;
	private JTextField txtValidarCadena;

	public DialogoGramatica(InterfazCYK principal) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.principal = principal;
		setSize(600, 400);
		setLayout(new BorderLayout());
		setTitle("Algoritmo de CYK");
		setResizable(false);
		
		panelGramatica = new PanelGramatica();
		
		butValidarCadena = new JButton(VALIDAR_CADENA);
		butValidarCadena.addActionListener(this);
		butValidarCadena.setActionCommand(VALIDAR_CADENA);
		
		labValidarCadena = new JLabel("       Escribir la cadena que se quiera validar:");
		txtValidarCadena = new JTextField();
		
		JPanel aux1 = new JPanel();
		aux1.setLayout(new GridLayout(1, 2));
		aux1.add(labValidarCadena); aux1.add(txtValidarCadena);
		
		JPanel aux2 = new JPanel();
		aux2.setLayout(new GridLayout(2,1));
		aux2.add(aux1); aux2.add(butValidarCadena);
		
		add(aux2, BorderLayout.SOUTH);
		add(panelGramatica, BorderLayout.CENTER);
	}
	
	public void dispose() {
		principal.reiniciar();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
