package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionEstocasticaUniversal extends Seleccion {
	Random rnd = new Random(); 
	@SuppressWarnings("unchecked")
	@Override
	public int[] seleccionar(Individuo[] individuos, double [] fitness) {
		int n = individuos.length;
		float distancia = 1 / n;
		double mediaFitness = 0;
		
		//Hallamos la media del fitnes
		for(int i = 0; i< n; i++) {
			mediaFitness+=fitness[i];
		}
		mediaFitness/=n;
		//Hallamos la probabilidad de seleccion
		double [] probSeleccion = new double[n];
		probSeleccion[0]= fitness[0]/mediaFitness;
		for(int i = 1; i< n; i++) {
			probSeleccion[i] = fitness[i]/mediaFitness + probSeleccion[i-1];
		}
		
		float puntoInicial = rnd.nextFloat() * distancia;
		int [] individuosSeleccionados = new int[individuos.length];
		for(int i = 0; i < n; ++i) {
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
