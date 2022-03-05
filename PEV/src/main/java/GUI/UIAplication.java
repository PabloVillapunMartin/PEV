package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.UIManager;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.AlgoritmoGenetico.TipoCruce;
import algoritmoGenetico.AlgoritmoGenetico.TipoSeleccion;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		tamPoblacion.setText("100");
		panel.add(tamPoblacion);
		tamPoblacion.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Maximod de Iteraciones");
		panel.add(lblNewLabel);
		
		maxIt = new JTextField();
		maxIt.setText("100");
		panel.add(maxIt);
		maxIt.setColumns(10);
		
		final JCheckBox elite = new JCheckBox("\u00C9lite");
		panel.add(elite);
		
		final JSlider perElite = new JSlider();
		perElite.setToolTipText("Porcentaje de \u00C9lite");
		perElite.setValue(10);
		panel.add(perElite);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.scrollbar);
		frmGp.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblProbabilidadCruce = new JLabel("Probabilidad Cruce");
		panel_1.add(lblProbabilidadCruce);
		
		final JSlider ProbCruce = new JSlider();
		ProbCruce.setValue(60);
		panel_1.add(ProbCruce);
		
		JLabel ProbabilidadDeMutacion = new JLabel("Probabilidad de Mutaci\u00F3n");
		ProbabilidadDeMutacion.setToolTipText("Probabilidad de Mutaci\u00F3n");
		panel_1.add(ProbabilidadDeMutacion);
		
		final JSlider ProbMut = new JSlider();
		ProbMut.setValue(5);
		ProbMut.setToolTipText("Probabilidad de Mutacion");
		panel_1.add(ProbMut);
		
		JLabel lblTipoCruce = new JLabel("Tipo Cruce");
		panel_1.add(lblTipoCruce);
		
		final JComboBox tipoCruce = new JComboBox();
		tipoCruce.setModel(new DefaultComboBoxModel(new String[] {"Monopunto", "Uniforme"}));
		panel_1.add(tipoCruce);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo Selecci\u00F3n");
		panel_1.add(lblNewLabel_1);
		
		final JComboBox tipoSelec = new JComboBox();
		tipoSelec.setModel(new DefaultComboBoxModel(new String[] {"Por Ruleta", "Torneo Determinista", "Torneo Probabilistico", "Estoc\u00E1stico Universal", "Truncamiento", "Por Restos"}));
		panel_1.add(tipoSelec);
		
		JPanel panel_2 = new JPanel();
		frmGp.getContentPane().add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 1, 70, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JLabel lblTipoDeFuncion = new JLabel("Tipo de Funcion");
		panel_3.add(lblTipoDeFuncion);
		
		final JComboBox TipoFuncion = new JComboBox();
		TipoFuncion.setModel(new DefaultComboBoxModel(new String[] {"Funcion1", "Funcion2", "Funcion3", "Funcion4", "Funcion5", "Funcion6"}));
		panel_3.add(TipoFuncion);
		
		JButton empezar = new JButton("Empezar Algoritmo");
		empezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlgoritmoGenetico AG = new AlgoritmoGenetico();
				int poblacion = Integer.parseInt(tamPoblacion.getText());
				int iteraciones = Integer.parseInt(maxIt.getText());
				
				AG.configura(FuncionIndividuo.values()[TipoFuncion.getSelectedIndex()], poblacion, iteraciones, TipoCruce.values()[tipoCruce.getSelectedIndex()], TipoSeleccion.values()[tipoSelec.getSelectedIndex()],
						(float)ProbMut.getValue()/100.0f, (float)ProbCruce.getValue()/100.0f, (float)perElite.getValue()/100.0f, elite.isSelected());
				AG.run();
			}
		});
		empezar.setForeground(UIManager.getColor("menuPressedItemB"));
		panel_3.add(empezar);
		
		JPanel grafica = new JPanel();
		frmGp.getContentPane().add(grafica, BorderLayout.CENTER);
	}

}
