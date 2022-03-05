package algoritmoGenetico.cruces;

import java.util.Arrays;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class CruceUniforme extends Cruce {

	Random random;

	public CruceUniforme(float probCruce) {
		super(probCruce);
		random = new Random();
	}

	@Override
	public Individuo[] cruzar(Individuo[] individuos) {
		
		int n = individuos.length;
		Boolean[] visitados = new Boolean[n];		
		Arrays.fill(visitados, false);
			
		if(n%2 != 0) n--;
		for(int i = 0; i< n; ++i) {
			if(!visitados[i]){
				visitados[i] = true;
				int padre2 = buscarIndividuo(visitados, n);
				
				float rnd = random.nextFloat();
				if(rnd <= this.probCruce) {
								
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
	
	private int buscarIndividuo(Boolean[] visitados, int n){
		int i = 0;
		while(visitados[i] ) {
			i = (i + 1)%n;
		}
		visitados[i] = true;
		return i;
	}

}
