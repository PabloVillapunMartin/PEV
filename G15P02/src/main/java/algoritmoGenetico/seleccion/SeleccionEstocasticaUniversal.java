package algoritmoGenetico.seleccion;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionEstocasticaUniversal extends Seleccion {
	Random rnd = new Random(); 
	
	/*
	 * Seleccion estocastica universal
	 */
	@Override
	public int[] seleccionar(Individuo[] individuos) {
		int n = individuos.length;
		float distancia = 1 / n;
		double mediaFitness = 0;
		
		//Obtener fitness de cada inividuo
		double [] fitness = calculaFitness(individuos);
			
		//Hallamos la media del fitnes
		for(int i = 0; i< n; i++) {
			mediaFitness+=fitness[i];
		}
		
		//media del fitness
		mediaFitness/=n;
		
		//Hallamos la probabilidad de seleccion
		double [] probSeleccion = new double[n];
		probSeleccion[0]= fitness[0]/mediaFitness;
		for(int i = 1; i< n; i++) {
			probSeleccion[i] = fitness[i]/mediaFitness + probSeleccion[i-1];
		}
		
		//Establecer punto inicial de la busqueda
		float puntoInicial = rnd.nextFloat() * distancia;
		int [] individuosSeleccionados = new int[individuos.length];
		for(int i = 0; i < n; ++i) {
			//punto tras i iteraciones
			float puntoActual = (puntoInicial + distancia * i)/n;
			
			for(int j = 0; j < n; ++j) {
				if(puntoActual < probSeleccion[j]) {
					individuosSeleccionados[i] = j;
					break;
				}
			}
		}
		
		return individuosSeleccionados;
	}
}
