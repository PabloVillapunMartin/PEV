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
		float n = individuos.length;
		float distancia = 1.0f / n;
		double fitnessAcumulado = 0;
		
		//Obtener fitness de cada inividuo
		double [] fitness = calculaFitness(individuos);
			
		//Hallamos la media del fitnes
		for(int i = 0; i< n; i++) {
			fitnessAcumulado+=fitness[i];
		}
			
		//Hallamos la probabilidad de seleccion
		double [] probSeleccion = new double[(int) n];
		probSeleccion[0]= fitness[0]/fitnessAcumulado;
		for(int i = 1; i< n; i++) {
			probSeleccion[i] = fitness[i]/fitnessAcumulado + probSeleccion[i-1];
		}
		
		//Establecer punto inicial de la busqueda
		float puntoInicial = rnd.nextFloat() * distancia;
		int [] individuosSeleccionados = new int[individuos.length];
		

		for(int i = 0; i < n; ++i) {
			//punto tras i iteraciones
			float puntoActual = puntoInicial + distancia * i;
			
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
