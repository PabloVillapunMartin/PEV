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
import algoritmoGenetico.AlgoritmoGenetico.TipoMutacion;
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
import javax.swing.SpinnerNumberModel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
		
		JLabel lblNewLabel = new JLabel("Maximo de Iteraciones");
		panel.add(lblNewLabel);
		
		maxIt = new JTextField();
		maxIt.setText("100");
		panel.add(maxIt);
		maxIt.setColumns(10);
		
		final JCheckBox elite = new JCheckBox("\u00C9lite");
		panel.add(elite);
		
		final JSpinner perElite = new JSpinner();
		perElite.setModel(new SpinnerNumberModel(0.01, 0.0, 1.0, 0.01));
		panel.add(perElite);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.scrollbar);
		frmGp.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblProbabilidadCruce = new JLabel("Probabilidad Cruce");
		panel_1.add(lblProbabilidadCruce);
		
		final JSpinner ProbCruce = new JSpinner();
		ProbCruce.setModel(new SpinnerNumberModel(0.6, 0.0, 1.0, 0.05));
		panel_1.add(ProbCruce);
		
		JLabel ProbabilidadDeMutacion = new JLabel("Probabilidad de Mutaci\u00F3n");
		ProbabilidadDeMutacion.setToolTipText("Probabilidad de Mutaci\u00F3n");
		panel_1.add(ProbabilidadDeMutacion);
		
		final JSpinner ProbMut = new JSpinner();
		ProbMut.setModel(new SpinnerNumberModel(0.05, 0.0, 1.0, 0.05));
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
		
		JLabel lblNewLabel_5 = new JLabel("Tipo Mutaci\u00F3n");
		panel_1.add(lblNewLabel_5);
		
		final JComboBox tipoMut = new JComboBox();
		tipoMut.setModel(new DefaultComboBoxModel(new String[] {"Uniforme"}));
		panel_1.add(tipoMut);
		
		JPanel panel_2 = new JPanel();
		frmGp.getContentPane().add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(4, 2, 10, 0));
		
		JLabel lblTipoDeFuncion = new JLabel("Tipo de Funcion");
		panel_3.add(lblTipoDeFuncion);
		
		final JComboBox TipoFuncion = new JComboBox();
		TipoFuncion.setModel(new DefaultComboBoxModel(new String[] {"Individuo Funcion 1", "Individuo Funcion 2", "Individuo Funcion 3", "Individuo Funcion 4", "Individuo Funcion 4 Real"}));
		panel_3.add(TipoFuncion);
		TipoFuncion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(TipoFuncion.getSelectedIndex() == 4) //Si estamos en el valor real
					tipoCruce.setModel(new DefaultComboBoxModel(new String[] {"Monopunto", "Uniforme", "Aritmetico", "Blx_Alpha"}));				
				else
					tipoCruce.setModel(new DefaultComboBoxModel(new String[] {"Monopunto", "Uniforme"}));
					
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Precision de Individuo");
		panel_3.add(lblNewLabel_2);
		
		final JSpinner precision = new JSpinner();
		precision.setModel(new SpinnerNumberModel(3, 0, 9, 1));
		panel_3.add(precision);
		
		JLabel lblNewLabel_3 = new JLabel("N\u00FAmero n Funcion4");
		panel_3.add(lblNewLabel_3);
		
		final JSpinner n = new JSpinner();
		n.setModel(new SpinnerNumberModel(new Integer(6), new Integer(0), null, new Integer(1)));
		panel_3.add(n);
		
		JLabel lblNewLabel_4 = new JLabel("Alpha Funci\u00F3n 4 Real");
		panel_3.add(lblNewLabel_4);
		
		final JSpinner alpha = new JSpinner();
		alpha.setModel(new SpinnerNumberModel(new Float(0.6f), new Float(0.0f), new Float(1.0f), new Float(0.1f)));
		panel_3.add(alpha);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		

		
		JButton empezar = new JButton("Empezar Algoritmo");
		panel_4.add(empezar);
		empezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlgoritmoGenetico AG = new AlgoritmoGenetico();
				int poblacion = Integer.parseInt(tamPoblacion.getText());
				int iteraciones = Integer.parseInt(maxIt.getText());
							
				AG.configura(FuncionIndividuo.values()[TipoFuncion.getSelectedIndex()], poblacion, iteraciones, TipoCruce.values()[tipoCruce.getSelectedIndex()], TipoSeleccion.values()[tipoSelec.getSelectedIndex()],
						TipoMutacion.values()[tipoMut.getSelectedIndex()],(Double)ProbMut.getValue(), (Double)ProbCruce.getValue(), (Double)perElite.getValue(), elite.isSelected(),
						frmGp, 1/Math.pow(10, (double)(Integer)precision.getValue()), (Integer)n.getValue(),(Float)alpha.getValue());
				AG.run();
			}
		});
		empezar.setForeground(UIManager.getColor("menuPressedItemB"));
		
	}

}
