package algoritmoGenetico;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceMonopunto;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFuncion1;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.seleccion.Seleccion;
import algoritmoGenetico.seleccion.SeleccionEstocasticaUniversal;
import algoritmoGenetico.seleccion.SeleccionRuleta;
import algoritmoGenetico.seleccion.SeleccionTorneoDeterministico;
import algoritmoGenetico.seleccion.SeleccionTorneoProbabilistico;

public class AlgoritmoGenetico {
	
	public enum TipoCruce { monopunto, uniforme}
	public enum TipoSeleccion {porRuleta, torneoDet, torneoProb, estoUniversal, truncamiento, restos}
	
	TipoSeleccion tipoSeleccion = TipoSeleccion.porRuleta;
	TipoCruce tipoCruce = TipoCruce.monopunto;
	
	private int tamPoblacion;
	private int tamTorneo;
	private int maxGeneraciones;
	private double mejor_absoluto;
	private double mejor_generacion;
	private double media_generacion;
	
	private double[] fitness;
	private float probCruce;
	private float probMutacion;
	
	private Individuo[] poblacion;
	private Individuo elMejor;
	
	private Seleccion seleccion;
	private Mutacion mutacion;
	private Cruce cruce;
	
	private int generacionActual;
	
	public void configure(int tamPoblacion, int tamTorneo, int maxGeneraciones,
			TipoCruce tipoCruce, TipoSeleccion tipoSeleccion, float probMutacion, float probCruce) {
		this.tipoCruce = tipoCruce;
		this.tipoSeleccion = tipoSeleccion;
		elegirSeleccion();
		elegirCruce();
		this.tamPoblacion = tamPoblacion;
		this.tamTorneo = tamTorneo;
		this.maxGeneraciones = maxGeneraciones;
		this.probMutacion = probMutacion;
		this.probCruce = probCruce;
	}
	
	public void run() {
			
		iniciarPoblacion();
		evaluar();
		while(this.generacionActual < this.maxGeneraciones) {
		
			this.seleccion.seleccionar(poblacion, fitness);
			this.cruce.cruzar(poblacion);
			this.mutacion.mutar(poblacion);
			evaluar();
			generaGrafica();
			
			
			this.generacionActual++;
		}
	}
	
	private void iniciarPoblacion() {
		this.poblacion = new Individuo[this.tamPoblacion];
		for(int i = 0; i< this.tamPoblacion; ++i) {
			this.poblacion[i] = new IndividuoFuncion1();
		}
		
		this.mejor_absoluto = 0;
	}
	
	private void evaluar() {
		double fitnessInd = 0;
		this.mejor_generacion = 0;
		this.media_generacion = 0;
		for(Individuo ind : this.poblacion) {
			fitnessInd = ind.getFitness();
			this.media_generacion += fitnessInd;
			
			if(fitnessInd > this.mejor_absoluto) {
				this.mejor_absoluto = fitnessInd;
				this.elMejor = ind;
			}
			if(fitnessInd > this.mejor_generacion) this.mejor_generacion = fitnessInd;
		}
		this.media_generacion /= this.poblacion.length;
	}
	
	private void generaGrafica() {
		System.out.print("Generacion " + this.generacionActual + " " + this.mejor_absoluto);
	}
	
	private void elegirSeleccion() {
		switch(this.tipoSeleccion) {
			case porRuleta: 	this.seleccion = new SeleccionRuleta(); 				break;
			case torneoDet: 	this.seleccion = new SeleccionTorneoDeterministico(); 	break;
			case torneoProb: 	this.seleccion = new SeleccionTorneoProbabilistico(); 	break;
			case estoUniversal: this.seleccion = new SeleccionEstocasticaUniversal(); 	break;
		}
	}
	private void elegirCruce() {
		switch(this.tipoCruce) {
			case monopunto: this.cruce = new CruceMonopunto(this.probCruce); 				break;
			case uniforme: break;
		}
	}
}
