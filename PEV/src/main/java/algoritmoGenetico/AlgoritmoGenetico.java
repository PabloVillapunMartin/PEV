package algoritmoGenetico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceMonopunto;
import algoritmoGenetico.cruces.CruceUniforme;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFactory;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.mutacion.MutacionBasica;
import algoritmoGenetico.seleccion.Seleccion;
import algoritmoGenetico.seleccion.SeleccionEstocasticaUniversal;
import algoritmoGenetico.seleccion.SeleccionRestos;
import algoritmoGenetico.seleccion.SeleccionRuleta;
import algoritmoGenetico.seleccion.SeleccionTorneoDeterministico;
import algoritmoGenetico.seleccion.SeleccionTorneoProbabilistico;
import algoritmoGenetico.seleccion.SeleccionTruncamiento;

public class AlgoritmoGenetico {
	
	public enum TipoCruce { monopunto, uniforme}
	public enum TipoSeleccion {porRuleta, torneoDet, torneoProb, estoUniversal, truncamiento, restos}
	
	TipoSeleccion tipoSeleccion = TipoSeleccion.porRuleta;
	TipoCruce tipoCruce = TipoCruce.monopunto;
	
	//Enum que identifica la funcion del problema
	public enum FuncionIndividuo { Funcion1, Funcion2, Funcion3, Funcion4}
	
	private FuncionIndividuo funcion;	//Funcion del problema
	
	private int generacionActual;	//generacion por la que va el algoritmo
	
	private int tamPoblacion;		//tama�o de la poblacion
	private int maxGeneraciones;	//numero de generaciones a ejecutar el algoritmo
	
	private double[] mejor_absoluto;	//mejor valor obtenido de todas las generaciones
	private double[] mejor_generacion;	//mejor obtenido en la generacion actual
	private double[] media_generacion;	//media de la generacion actual
	private double[] generaciones;			//array que guarda el numero de generaciones para la grafica
	
	private float probCruce;		//probabilidad de cruce
	private float probMutacion;		//probabilidad de mutacion
	private float perElite;			//porcentaje de la elite
	
	private double[] fitness;		//fitness de los individuos
	
	private Individuo[] poblacion;	//poblacion actual
	private Individuo elMejor;		//el mejor individuo encontrado hasta el momento
	private Individuo[] elite;		//elite guardada
	
	private Seleccion seleccion;	//M�todo de seleccion
	private Mutacion mutacion;		//M�todo de mutaci�n
	private Cruce cruce;			//M�todo de cruce
	
	private boolean conElite;		//Si se desea ejecutar el algoritmo con elite o sin ella
	
	JFrame jframe;					//panel donde se va a situar la gr�fica
	
	
	/*
	 * Configura todo lo necesario para iniciar el algoritmo evolutivo
	 * @params tamPoblacion tama�o de la poblacion con la que se quiere realizar el algoritmo
	 * @param maxGeneraciones numero maximo de generaciones que se va a ejecutar el algoritmo
	 * @param tipoCruce tipo de cruce que se va a ejecutar
	 * @param tipoSeleccion tipo de seleccion que se va a ejecutar
	 * @param probMutacion probabilidad de mutacion
	 * @param probCruce probabilidad de cruce
	 * @param perElite porcentaje de elite
	 * */
	public void configura(FuncionIndividuo funcion, int tamPoblacion, int maxGeneraciones, TipoCruce tipoCruce, TipoSeleccion tipoSeleccion, 
	float probMutacion, float probCruce, float perElite, boolean elite,  JFrame jframe) {
		
		this.funcion = funcion;
		
		this.tamPoblacion = tamPoblacion;
		this.maxGeneraciones = maxGeneraciones;
		
		this.probMutacion = probMutacion;
		this.probCruce = probCruce;
		
		this.tipoCruce = tipoCruce;
		this.tipoSeleccion = tipoSeleccion;
		
		this.perElite = perElite;
		this.conElite = elite;
		
		this.jframe = jframe;
		
		this.mutacion = new MutacionBasica(this.probMutacion);
		elegirSeleccion();
		elegirCruce();
	}
	
	/* 
	 * Ejecuta el algoritmo inicializando la poblacion. Contiene el bucle principal que se 
	 * ejecuta hasta terminar el numero de generaciones.
	 * */
	public void run() {
			
		iniciarPoblacion();
		evaluar();
		while(this.generacionActual < this.maxGeneraciones) {
			
			if(conElite)
				guardarElite();
			
			seleccionar();
			this.cruce.cruzar(this.poblacion);
			this.mutacion.mutar(this.poblacion);
			
			if(conElite)
				preservarElite();
			
			evaluar();			
			
			this.generaciones[this.generacionActual] = this.generacionActual;
			this.generacionActual++;
		}
		generaGrafica();		
	}
	
	/*
	 * Inicializa la poblacion con la informaci�n obtenida en los datos de la interfaz
	 * */
	private void iniciarPoblacion() {
		//Inicializamos los arrays est�ticos
		this.poblacion = new Individuo[this.tamPoblacion];
		this.fitness = new double[this.tamPoblacion];
		this.elite = new Individuo[(int)(this.tamPoblacion * this.perElite)];
		
		//Inicializamos la poblacion con la funci�n dada
		for(int i = 0; i< this.tamPoblacion; ++i) {
			this.poblacion[i] = IndividuoFactory.getIndividuo(funcion);
		}
		//Asignamos el mejor absoluto a 0
		this.mejor_absoluto = new double[this.maxGeneraciones];
		this.mejor_generacion = new double[this.maxGeneraciones];
		this.media_generacion = new double[this.maxGeneraciones];
		this.generaciones = new double[this.maxGeneraciones];
		this.elMejor = this.poblacion[0];
	}
	
	/*
	 * Evalua la generacion actual comprobando si se ha superado al mejor absoluto
	 * Calcula tambi�n el mejor de la generaci�n y la media
	 * */
	private void evaluar() {
		//Reset de variables
		mejor_generacion[this.generacionActual] = 0;
		media_generacion[this.generacionActual] = 0;
		
		//Ordena la poblacion de individuos por valor
		Arrays.sort(this.poblacion);
		
		//Mejor individuo de la generacion
		this.mejor_generacion[this.generacionActual] = this.poblacion[0].getValor();
		
		//Comprobamos si hemos obtenido el mejor absoluto hasta el momento
		if(this.poblacion[0].compareTo(elMejor) < 0) {
			this.mejor_absoluto[this.generacionActual] = this.mejor_generacion[this.generacionActual];
			this.elMejor = this.poblacion[0];
		}
		else if(this.generacionActual > 0){
			this.mejor_absoluto[this.generacionActual] = this.mejor_absoluto[this.generacionActual - 1];
		}
		
		//Medimos la media del valor de la generacion
		for(int i = 0; i < this.tamPoblacion; i++){
			media_generacion[this.generacionActual] += this.poblacion[i].getValor();
		}
		media_generacion[this.generacionActual] /= this.tamPoblacion;			
	}
	
	/*
	 * Genera una grafica con los datos recogidos en esta generacion
	 */
	private void generaGrafica() {
	
		JPanel panel = new JPanel();
		
        Plot2DPanel plot = new Plot2DPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 200);
            }
        };
        plot.addLinePlot("Media Generacion", Color.GREEN, this.generaciones, this.media_generacion);
		plot.addLinePlot("Mejor Generacion", Color.RED, this.generaciones, this.mejor_generacion);
		plot.addLinePlot("Mejor Absoluto", Color.BLUE, this.generaciones, this.mejor_absoluto);
		plot.addLegend("SOUTH");
        panel.setLayout(new BorderLayout());
        panel.add(plot);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(panel);
        jframe.setLocation(150, 150);
        jframe.setVisible(true);
	}
	
	/*
	 * Selecciona de entre los individuos de la poblacion usando el m�todo de seleccion
	 * obtenido por la interfaz
	 * */
	private void seleccionar(){
		int[] seleccionados = this.seleccion.seleccionar(this.poblacion, fitness);
		for(int i = 0; i < this.tamPoblacion; i++){
			Individuo ind = IndividuoFactory.getIndividuo(funcion);
			ind.copiarIndividuo(this.poblacion[seleccionados[i]]);
			this.poblacion[i] = ind;
		}
	}
	
	/* 
	 * Crea el tipo de seleccion obtenido por la interfaz y lo guarda
	 * */
	private void elegirSeleccion() {
		switch(this.tipoSeleccion) {
			case porRuleta: 	this.seleccion = new SeleccionRuleta(); 				break;
			case torneoDet: 	this.seleccion = new SeleccionTorneoDeterministico(); 	break;
			case torneoProb: 	this.seleccion = new SeleccionTorneoProbabilistico(); 	break;
			case estoUniversal: this.seleccion = new SeleccionEstocasticaUniversal(); 	break;
			case truncamiento: 	this.seleccion = new SeleccionTruncamiento(); 			break;
			case restos: 		this.seleccion = new SeleccionRestos(); 				break;
		}
	}
	
	/*
	 * Crea el tipo de cruce obtenido por la interfaz y lo guarda
	 * */
	private void elegirCruce() {
		switch(this.tipoCruce) {
			case monopunto: this.cruce = new CruceMonopunto(this.probCruce); 				break;
			case uniforme:	this.cruce = new CruceUniforme(this.probCruce);					break;
		}
	}
	
	/*
	 * Guarda la elite de la poblacion
	 * */
	private void guardarElite(){
		Arrays.sort(this.poblacion);
		for(int i = 0; i < this.elite.length; i++) {
			Individuo ind = IndividuoFactory.getIndividuo(funcion);
			ind.copiarIndividuo(this.poblacion[i]);
			this.elite[i] = ind;
		}
	}
	
	/*
	 * Introduce de nuevo la elite de la poblacion
	 * */
	private void preservarElite(){
		Arrays.sort(this.poblacion);
		for(int i = 0; i < this.elite.length; i++) 
			this.poblacion[this.tamPoblacion - 1 - i] = this.elite[i];
	}
}
