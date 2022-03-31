package algoritmoGenetico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import algoritmoGenetico.aviones.TraficoAereo;
import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceOX;
import algoritmoGenetico.cruces.CruceOXOP;
import algoritmoGenetico.cruces.CruceOXPP;
import algoritmoGenetico.cruces.CrucePMX;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFactory;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.mutacion.MutacionInsercion;
import algoritmoGenetico.mutacion.MutacionInversion;
import algoritmoGenetico.mutacion.MutaciónHeurística;
import algoritmoGenetico.mutacion.MutaciónIntercambio;
import algoritmoGenetico.seleccion.Seleccion;
import algoritmoGenetico.seleccion.SeleccionEstocasticaUniversal;
import algoritmoGenetico.seleccion.SeleccionRanking;
import algoritmoGenetico.seleccion.SeleccionRestos;
import algoritmoGenetico.seleccion.SeleccionRuleta;
import algoritmoGenetico.seleccion.SeleccionTorneoDeterministico;
import algoritmoGenetico.seleccion.SeleccionTorneoProbabilistico;
import algoritmoGenetico.seleccion.SeleccionTruncamiento;

public class AlgoritmoGenetico {
	
	public enum TipoCruce { PMX, OrdenOX, OrdenOXPP, OrdenOXOP, CiclosCX, CO };
	public enum TipoSeleccion {porRuleta, torneoDet, torneoProb, estoUniversal, truncamiento, restos, ranking}
	public enum TipoMutacion { Inserción, Intercambio, Inversión, Heurística};
	
	TipoSeleccion tipoSeleccion = TipoSeleccion.porRuleta;
	TipoCruce tipoCruce = TipoCruce.CiclosCX;
	TipoMutacion tipoMutacion = TipoMutacion.Inserción;
	
	//Enum que identifica la funcion del problema
	public enum FuncionIndividuo { FuncionAvion}
	
	private FuncionIndividuo funcion;	//Funcion del problema
	
	private int generacionActual;	//generacion por la que va el algoritmo
	
	private int tamPoblacion;		//tamaño de la poblacion
	private int maxGeneraciones;	//numero de generaciones a ejecutar el algoritmo
	private int n;					//numero n para el individuo 4
	
	private double[] mejor_absoluto;	//mejor valor obtenido de todas las generaciones
	private double[] mejor_generacion;	//mejor obtenido en la generacion actual
	private double[] media_generacion;	//media de la generacion actual
	private double[] generaciones;		//array que guarda el numero de generaciones para la grafica
	
	private double probCruce;		//probabilidad de cruce
	private double probMutacion;	//probabilidad de mutacion
	private double perElite;		//porcentaje de la elite
		
	private Individuo[] poblacion;	//poblacion actual
	private Individuo elMejor;		//el mejor individuo encontrado hasta el momento
	private Individuo[] elite;		//elite guardada
	
	private Seleccion seleccion;	//Método de seleccion
	private Mutacion mutacion;		//Método de mutación
	private Cruce cruce;			//Método de cruce
	
	private boolean conElite;		//Si se desea ejecutar el algoritmo con elite o sin ella
	
	JFrame jframe;					//panel donde se va a situar la gráfica
	
	
	/*
	 * Configura todo lo necesario para iniciar el algoritmo evolutivo
	 * @params tamPoblacion tamaño de la poblacion con la que se quiere realizar el algoritmo
	 * @param maxGeneraciones numero maximo de generaciones que se va a ejecutar el algoritmo
	 * @param tipoCruce tipo de cruce que se va a ejecutar
	 * @param tipoSeleccion tipo de seleccion que se va a ejecutar
	 * @param probMutacion probabilidad de mutacion
	 * @param probCruce probabilidad de cruce
	 * @param perElite porcentaje de elite
	 * */
	public void configura(FuncionIndividuo funcion, int tamPoblacion, int maxGeneraciones, TipoCruce tipoCruce, TipoSeleccion tipoSeleccion, TipoMutacion tipoMutacion,
	double probMutacion, double probCruce, double perElite, boolean elite,  JFrame jframe,int n) {
		
		this.funcion = funcion;
		
		this.tamPoblacion = tamPoblacion;
		this.maxGeneraciones = maxGeneraciones;
		this.n = n;
		
		this.probMutacion = probMutacion;
		this.probCruce = probCruce;
		
		this.tipoCruce = tipoCruce;
		this.tipoSeleccion = tipoSeleccion;
		this.tipoMutacion = tipoMutacion;
		
		this.perElite = perElite;
		this.conElite = elite;
		
		this.jframe = jframe;
			
		elegirMutacion();
		elegirSeleccion();
		elegirCruce();
		
		TraficoAereo.getInstance().init(n);
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
	 * Inicializa la poblacion con la información obtenida en los datos de la interfaz
	 * */
	private void iniciarPoblacion() {
		//Inicializamos los arrays estáticos
		this.poblacion = new Individuo[this.tamPoblacion];
		this.elite = new Individuo[(int)(this.tamPoblacion * this.perElite)];
		
		this.mejor_absoluto = new double[this.maxGeneraciones];
		this.mejor_generacion = new double[this.maxGeneraciones];
		this.media_generacion = new double[this.maxGeneraciones];
		this.generaciones = new double[this.maxGeneraciones];
		
		//Inicializamos la poblacion con la función dada
		for(int i = 0; i< this.tamPoblacion; ++i) {
			this.poblacion[i] = IndividuoFactory.getIndividuo(funcion);
		}
		for(int i = 0; i< this.maxGeneraciones; ++i) {
			this.mejor_absoluto[i] = 0;
		}
		//Ordena la poblacion de individuos por valor
		Arrays.sort(this.poblacion);
		//Asignamos el mejor absoluto a 0
		this.elMejor = this.poblacion[0];
	}
	
	/*
	 * Evalua la generacion actual comprobando si se ha superado al mejor absoluto
	 * Calcula también el mejor de la generación y la media
	 * */
	private void evaluar() {
		//Reset de variables
		mejor_generacion[this.generacionActual] = 0;
		media_generacion[this.generacionActual] = 0;
		
		//Ordena la poblacion de individuos por valor
		Arrays.sort(this.poblacion);
		
		//Mejor individuo de la generacion
		Individuo mejorGen = IndividuoFactory.getIndividuo(funcion);
		mejorGen.copiarIndividuo(this.poblacion[0]);
		
		this.mejor_generacion[this.generacionActual] = mejorGen.getValor();
		
		//Comprobamos si hemos obtenido el mejor absoluto hasta el momento
		if(mejorGen.compareTo(elMejor) <= 0) {
			this.mejor_absoluto[this.generacionActual] = this.mejor_generacion[this.generacionActual];
			this.elMejor = mejorGen;
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
                return new Dimension(500, 400);
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
        jframe.setSize(new Dimension(1000, 700));
        jframe.setVisible(true);
        
        System.out.println("El mejor ha sido " + this.elMejor.getValor());
        System.out.println(this.elMejor.toString());
	}
	
	/*
	 * Selecciona de entre los individuos de la poblacion usando el método de seleccion
	 * obtenido por la interfaz
	 * */
	private void seleccionar(){
		
		int[] seleccionados = this.seleccion.seleccionar(this.poblacion);

		Individuo[] nuevaPoblacion = new Individuo[this.tamPoblacion];
		for(int i = 0; i < this.tamPoblacion; i++){
			nuevaPoblacion[i] = IndividuoFactory.getIndividuo(funcion);
			nuevaPoblacion[i].copiarIndividuo(this.poblacion[seleccionados[i]]);
		}
		this.poblacion = nuevaPoblacion;			
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
			case ranking:		this.seleccion = new SeleccionRanking();				break;
		}
	}
	
	/*
	 * Crea el tipo de cruce obtenido por la interfaz y lo guarda
	 * */
	private void elegirCruce() {
		switch(this.tipoCruce) {
			case PMX:		this.cruce = new CrucePMX(this.probCruce, this.funcion);	break;
			case OrdenOX:	this.cruce = new CruceOX(this.probCruce, this.funcion);		break;
			case OrdenOXPP: this.cruce = new CruceOXPP(this.probCruce, this.funcion);	break;
			case OrdenOXOP: this.cruce = new CruceOXOP(this.probCruce, this.funcion);	break;
		}
	}
	
	/*
	 * Establece el tipo de mutacion seleccionada por el usuario
	 */
	private void elegirMutacion(){
		switch(this.tipoMutacion){
			case Inserción: 	this.mutacion = new MutacionInsercion(this.probMutacion, this.funcion); 	break;
			case Intercambio:	this.mutacion = new MutaciónIntercambio(this.probMutacion, this.funcion);	break;
			case Inversión:		this.mutacion = new MutacionInversion(this.probMutacion, this.funcion); 	break;
			case Heurística:	this.mutacion = new MutaciónHeurística(this.probMutacion, this.funcion);	break;
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
		for(int i = 0; i < this.elite.length; i++) {
			this.poblacion[this.tamPoblacion - 1 - i] = this.elite[i];
		}
	}
	
	private void debug(){
		for(int i= 0; i < this.poblacion.length; i++){
			System.out.println(this.poblacion[i].toString());
		}
		System.out.println("------------------------------------------------");
	}
}
