package algoritmoGenetico;

import algoritmoGenetico.individuos.Individuo;

public class AlgoritmoGenetico {
	private int tamPoblacion;
	private int tamTorneo;
	private int maxGeneraciones;
	private int pos_mejor;
	private double[] fitness;
	private double probCruce;
	private double probMutacion;	
	private Individuo[] poblacion;
	private Individuo elMejor;
	
	private int generacionActual;
	
	public void run() {
		iniciarPoblacion();
		while(this.generacionActual < this.maxGeneraciones) {
		
			//Seleccion
			//Cruce
			//Mutacion
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
}
