package algoritmoGenetico;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceMonopunto;
import algoritmoGenetico.individuos.Individuo;
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
	private int pos_mejor;
	
	private double[] fitness;
	private float probCruce;
	private float probMutacion;
	
	private Individuo[] poblacion;
	private Individuo elMejor;
	
	private Seleccion seleccion;
	private Mutacion mutacion;
	private Cruce cruce;
	
	private int generacionActual;
	
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
		
	}
	
	private void evaluar() {
		
	}
	
	private void generaGrafica() {
		
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
