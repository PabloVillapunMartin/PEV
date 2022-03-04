package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class CruceMonopunto extends Cruce {

	Random random;

	public CruceMonopunto(float probCruce) {
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
			float rnd = random.nextFloat();
			if(rnd <= this.probCruce && !visitados[i]) {
							
				visitados[i] = true;
				int segundoProg = random.nextInt(n);
				while(visitados[segundoProg] ) {
					segundoProg = (segundoProg + 1)%n;
				}
			
				visitados[segundoProg] = true;
				int puntoDeCorte = random.nextInt(individuos[i].getCromosoma().length);
							
				for(int j = 0; j < puntoDeCorte; j++) {					
					Object aux = individuos[i].getCromosoma()[j];
					individuos[i].getCromosoma()[j] = individuos[segundoProg].getCromosoma()[j];
					individuos[segundoProg].getCromosoma()[j] = aux;
				}
				for(int j = puntoDeCorte; j < individuos[i].getCromosoma().length; j++) {
					Object aux = individuos[segundoProg].getCromosoma()[j];
					individuos[segundoProg].getCromosoma()[j] = individuos[i].getCromosoma()[j];
					individuos[i].getCromosoma()[j] = aux;
				}
			}
		}
			
		return individuos;
	}

}
