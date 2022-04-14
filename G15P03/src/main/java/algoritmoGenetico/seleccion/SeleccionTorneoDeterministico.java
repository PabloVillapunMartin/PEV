package algoritmoGenetico.seleccion;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionTorneoDeterministico extends Seleccion {

	Random rnd;
	public SeleccionTorneoDeterministico() {
		this.rnd = new Random();
	}
	
	/*
	 * Seleccion por torneo deterministico
	 */
	@Override
	public int[] seleccionar(Individuo[] individuos) {
		int n = individuos.length;
		int[] individuosSeleccionados = new int[n];
		
		//por cada proceso se toma el mejor de los individuos de un conjuntos tomados al azar
		//de la poblacion base
		for(int i = 0; i< n; i++) {			
			int mejorIndividuo = rnd.nextInt(n);	
			
			//Busqueda del mejor individuo
			for(int j = 1; j < 3; j++) {
				int aux = rnd.nextInt(n);
				if(individuos[aux].compareTo(individuos[mejorIndividuo]) < 0)
					mejorIndividuo = aux;
			}
			individuosSeleccionados[i] = mejorIndividuo;
		}
		
		return individuosSeleccionados;
	}
}
