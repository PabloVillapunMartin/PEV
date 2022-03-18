package algoritmoGenetico.cruces;

import java.util.Arrays;

import algoritmoGenetico.individuos.Individuo;

public class CruceBLX extends Cruce {

	private double alpha;
	
	/*
	 * Constructora de clase
	 */
	public CruceBLX(double probCruce2, double alpha) {
		super(probCruce2);
		this.alpha = alpha;
	}
	/*
	 * Cruce BLX
	 */
	@Override
	public Individuo[] cruzar(Individuo[] individuos) {
		int n = individuos.length;
		//Guarda si el individuo ha sido elegido para cruzar o no
		Boolean[] visitados = new Boolean[n];		
		//Inializamos todos los individuos a false
		Arrays.fill(visitados, false);
		
		//En caso de ser números impares, eliminamos el último individuo
		if(n%2 != 0) 
			n--;	
		for(int i = 0; i< n; ++i) {
			float rnd = random.nextFloat();
			
			//Si el individuo no ha sido visitado y se selecciona para cruzar
			if(!visitados[i] && rnd <= this.probCruce){	
				visitados[i] = true;						//Lo visitamos
				int padre2 = buscarIndividuo(visitados, n);	//Buscamos otro padre	
				cruzaBLX(individuos[i], individuos[padre2]);
			}
		}
			
		return individuos;
	}
	
	/*
	 * Cruce BLX para los individuos
	 */
	private void cruzaBLX(Individuo individuo1, Individuo individuo2) {
		for(int i = 0; i < individuo1.getCromosoma().length; ++i) {
			//Obtener el maximo y minimo valor en ambos cromosomas
			double max = Math.max((Double)individuo1.getCromosoma()[i], (Double)individuo2.getCromosoma()[i]);
			double min = Math.min((Double)individuo1.getCromosoma()[i], (Double)individuo2.getCromosoma()[i]);
			
			//Rango entre el maximo y minimo
			double I = max - min;
			
			//aplicacion del alpha
			max = max + I * this.alpha;
			min = min + I * this.alpha;
			
			//Asignacion de valores
			individuo1.getCromosoma()[i] = random.nextFloat() * I + min;
			individuo2.getCromosoma()[i] = random.nextFloat() * I + min;		
		}
	}

}
