package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class UIAplication {

	private JFrame frmGp;
	private JTextField tamPoblacion;
	private JTextField maxIt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIAplication window = new UIAplication();
					window.frmGp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UIAplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGp = new JFrame();
		frmGp.setTitle("G15_P1");
		frmGp.setBounds(100, 100, 665, 561);
		frmGp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGp.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("scrollbar"));
		frmGp.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel texttamPoblacion = new JLabel("Tama\u00F1o de la poblacion");
		panel.add(texttamPoblacion);
		
		tamPoblacion = new JTextField();
		panel.add(tamPoblacion);
		tamPoblacion.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Maximod de Iteraciones");
		panel.add(lblNewLabel);
		
		maxIt = new JTextField();
		panel.add(maxIt);
		maxIt.setColumns(10);
	}

}
