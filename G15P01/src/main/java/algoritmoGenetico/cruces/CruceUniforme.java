package algoritmoGenetico.cruces;

import java.util.Arrays;

import algoritmoGenetico.individuos.Individuo;

public class CruceUniforme extends Cruce {
	
	/*
	 * Constructora de clase
	 */
	public CruceUniforme(double probCruce) {
		super(probCruce);
	}

	/*
	 * Cruza a una poblacion utilizando el algoritmo de uniforme
	 * */
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
			//Si el individuo no ha sido visitado
			if(!visitados[i]){	
				visitados[i] = true;						//Lo visitamos
				int padre2 = buscarIndividuo(visitados, n);	//Buscamos otro padre
				
				//Hallamos la probabilidad
				float rnd = random.nextFloat();
				//Si la probabilidad se da
				if(rnd <= this.probCruce) {							
					//Hallamos para cada alelo si se da o no
					for(int j = 0; j < individuos[i].getCromosoma().length; j++) {		
						if(random.nextFloat() < 0.5f){
							Object aleloAux = individuos[i].getCromosoma()[j];
							individuos[i].getCromosoma()[j] = individuos[padre2].getCromosoma()[j];
							individuos[padre2].getCromosoma()[j] = aleloAux;
						}
							
					}
				}
			}
		}
			
		return individuos;
	}

}
