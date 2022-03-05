package algoritmoGenetico;

import java.util.Arrays;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceMonopunto;
import algoritmoGenetico.cruces.CruceUniforme;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFuncion1;
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
	
	private int generacionActual;	//generacion por la que va el algoritmo
	
	private int tamPoblacion;		//tamaño de la poblacion
	private int perElite;			//porcentaje de la elite
	private int maxGeneraciones;	//numero de generaciones a ejecutar el algoritmo
	
	private double mejor_absoluto;		//mejor valor obtenido de todas las generaciones
	private double mejor_generacion;	//mejor obtenido en la generacion actual
	private double media_generacion;	//media de la generacion actual
	
	private float probCruce;		//probabilidad de cruce
	private float probMutacion;
	
	private double[] fitness;
	
	private Individuo[] poblacion;
	private Individuo elMejor;
	private Individuo[] elite;
	
	private Seleccion seleccion;
	private Mutacion mutacion;
	private Cruce cruce;
	
	
	public void configure(int tamPoblacion, int maxGeneraciones, TipoCruce tipoCruce, TipoSeleccion tipoSeleccion, 
	float probMutacion, float probCruce, int perElite) {
		
		this.tamPoblacion = tamPoblacion;
		this.maxGeneraciones = maxGeneraciones;
		this.probMutacion = probMutacion;
		this.probCruce = probCruce;
		this.tipoCruce = tipoCruce;
		this.tipoSeleccion = tipoSeleccion;
		this.perElite = perElite;
		this.mutacion = new MutacionBasica(this.probMutacion);
		elegirSeleccion();
		elegirCruce();
	}
	
	public void run() {
			
		iniciarPoblacion();
		evaluar();
		while(this.generacionActual < this.maxGeneraciones) {
		
			/*Arrays.sort(this.poblacion);
			for(int i = 0; i < this.perElite; i++) {
				this.elite[i] = new IndividuoFuncion1((IndividuoFuncion1)this.poblacion[i]);
			}*/
			seleccionar();
			this.poblacion = this.cruce.cruzar(this.poblacion);
			this.poblacion = this.mutacion.mutar(this.poblacion);
			evaluar();
			generaGrafica();		
			
			this.generacionActual++;
		}
	}
	
	private void iniciarPoblacion() {
		this.poblacion = new Individuo[this.tamPoblacion];
		this.fitness = new double[this.tamPoblacion];
		this.elite = new Individuo[10];
		for(int i = 0; i< this.tamPoblacion; ++i) {
			this.poblacion[i] = new IndividuoFuncion1();
		}
		this.mejor_absoluto = 0;
	}
	
	private void evaluar() {
		mejor_generacion = 0;
		media_generacion = 0;
		
		for(int i = 0; i < this.tamPoblacion; i++){
			double fitness = this.poblacion[i].getFitness();
			if(fitness > this.mejor_absoluto){
				this.mejor_absoluto = fitness;
				this.elMejor = this.poblacion[i];
			}
			if(fitness > this.mejor_generacion){
				this.mejor_generacion = fitness;
			}
			media_generacion += fitness;
		}
		media_generacion /= this.tamPoblacion;
		
		/*Arrays.sort(this.poblacion);
		for(int i = 0; i < this.perElite; i++) {
			this.poblacion[this.tamPoblacion - 1 - i] = this.elite[i];
		}*/
			
	}
	
	private void generaGrafica() {
		System.out.println("Generacion " + this.generacionActual + " " + this.mejor_absoluto
				+ " MejorGen: " + this.mejor_generacion + " Media : " + this.media_generacion);
	}
	
	private void seleccionar(){
		int[] seleccionados = this.seleccion.seleccionar(this.poblacion, fitness);
		for(int i = 0; i < this.tamPoblacion; i++){
			this.poblacion[i] = new IndividuoFuncion1((IndividuoFuncion1)this.poblacion[i]);
		}
	}
	
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
	private void elegirCruce() {
		switch(this.tipoCruce) {
			case monopunto: this.cruce = new CruceMonopunto(this.probCruce); 				break;
			case uniforme:	this.cruce = new CruceUniforme(this.probCruce);					break;
		}
	}
	
	private void guardarFitness() {
		for(int i = 0; i < this.tamPoblacion; ++i) {
			this.fitness[i] = this.poblacion[i].getFitness();
		}
	}
}
