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
import algoritmoGenetico.AlgoritmoGenetico.TipoArbolInicio;
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
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		frmGp.setTitle("G15_P3");
		frmGp.setBounds(100, 100, 1069, 776);
		frmGp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGp.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(7, 7, 338, 498);
		panel.setBackground(UIManager.getColor("scrollbar"));
		frmGp.getContentPane().add(panel);
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
		
		JLabel lblTipoDeFuncion = new JLabel("Tipo de Funcion");
		panel.add(lblTipoDeFuncion);
		
		final JComboBox TipoFuncion = new JComboBox();
		panel.add(TipoFuncion);
		TipoFuncion.setModel(new DefaultComboBoxModel(new String[] {"Problema Avion"}));
		TipoFuncion.setSelectedIndex(0);
		
		JLabel lblNewLabel_3 = new JLabel("Inicializaci\u00F3n Arbol");
		panel.add(lblNewLabel_3);
		
		final JComboBox TipoArbol = new JComboBox();
		TipoArbol.setModel(new DefaultComboBoxModel(new String[] {"Full Initialization", "Grow Initialization", "RampAndHalf"}));
		TipoArbol.setSelectedIndex(0);
		panel.add(TipoArbol);
		
		JLabel lblNewLabel_5_1 = new JLabel("Numero Entrada A");
		panel.add(lblNewLabel_5_1);
		
		final JSpinner entrada = new JSpinner();
		entrada.setModel(new SpinnerNumberModel(2, 2, 3, 1));
		panel.add(entrada);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Profundidad Arbol");
		panel.add(lblNewLabel_5_1_1);
		
		final JSpinner profundidad = new JSpinner();
		profundidad.setModel(new SpinnerNumberModel(5, 3, 15, 1));
		panel.add(profundidad);
		
		final JCheckBox elite = new JCheckBox("\u00C9lite");
		panel.add(elite);
		
		final JSpinner perElite = new JSpinner();
		perElite.setModel(new SpinnerNumberModel(0.01, 0.0, 1.0, 0.01));
		panel.add(perElite);
		
		JLabel lblProbabilidadCruce = new JLabel("Probabilidad Cruce");
		panel.add(lblProbabilidadCruce);
		
		final JSpinner ProbCruce = new JSpinner();
		panel.add(ProbCruce);
		ProbCruce.setModel(new SpinnerNumberModel(0.6, 0.0, 1.0, 0.05));
		
		JLabel ProbabilidadDeMutacion = new JLabel("Probabilidad de Mutaci\u00F3n");
		panel.add(ProbabilidadDeMutacion);
		ProbabilidadDeMutacion.setToolTipText("Probabilidad de Mutaci\u00F3n");
		
		final JSpinner ProbMut = new JSpinner();
		panel.add(ProbMut);
		ProbMut.setModel(new SpinnerNumberModel(0.15, 0.0, 1.0, 0.05));
		
		JLabel lblTipoCruce = new JLabel("Tipo Cruce");
		panel.add(lblTipoCruce);
		
		final JComboBox tipoCruce = new JComboBox();
		panel.add(tipoCruce);
		tipoCruce.setModel(new DefaultComboBoxModel(new String[] {"Cruce Arboreo"}));
		tipoCruce.setSelectedIndex(0);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo Selecci\u00F3n");
		panel.add(lblNewLabel_1);
		
		final JComboBox tipoSelec = new JComboBox();
		panel.add(tipoSelec);
		tipoSelec.setModel(new DefaultComboBoxModel(new String[] {"Por Ruleta", "Torneo Determinista", "Torneo Probabilistico", "Estoc\u00E1stico Universal", "Truncamiento", "Por Restos", "Ranking"}));
		
		JLabel lblNewLabel_5 = new JLabel("Tipo Mutaci\u00F3n");
		panel.add(lblNewLabel_5);
		
		final JComboBox tipoMut = new JComboBox();
		panel.add(tipoMut);
		tipoMut.setModel(new DefaultComboBoxModel(new String[] {"Terminal", "Arbol_Subarbol", "Hoist", "Contraccion", "Expansion"}));
		tipoMut.setSelectedIndex(0);
		
		final JPanel panelGrafica = new JPanel();
		panelGrafica.setBounds(380, 44, 663, 682);
		frmGp.getContentPane().add(panelGrafica);
		
		final JLabel texto = new JLabel("");
		texto.setVerticalAlignment(SwingConstants.TOP);
		texto.setHorizontalAlignment(SwingConstants.LEFT);
		texto.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 12));
		texto.setForeground(Color.DARK_GRAY);
		texto.setBounds(7, 574, 363, 152);
		frmGp.getContentPane().add(texto);
		
		JButton empezar = new JButton("Empezar Algoritmo");
		empezar.setBounds(73, 516, 200, 47);
		frmGp.getContentPane().add(empezar);
		
		final AlgoritmoGenetico AG = new AlgoritmoGenetico();
		
		empezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int poblacion = Integer.parseInt(tamPoblacion.getText());
				int iteraciones = Integer.parseInt(maxIt.getText());
							
				AG.configura(FuncionIndividuo.values()[TipoFuncion.getSelectedIndex()], poblacion, iteraciones, TipoCruce.values()[tipoCruce.getSelectedIndex()], TipoSeleccion.values()[tipoSelec.getSelectedIndex()],
						TipoMutacion.values()[tipoMut.getSelectedIndex()], TipoArbolInicio.values()[TipoArbol.getSelectedIndex()],(Double)ProbMut.getValue(), (Double)ProbCruce.getValue(), (Double)perElite.getValue(), 
						elite.isSelected(),	panelGrafica, texto, (Integer)entrada.getValue(), (Integer)profundidad.getValue());
				AG.run();
			}
		});
		empezar.setForeground(UIManager.getColor("menuPressedItemB"));
		

		

		

		
	}
}
