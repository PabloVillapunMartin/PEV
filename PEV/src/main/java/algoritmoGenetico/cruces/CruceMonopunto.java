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

		List<Individuo> individuosCruzados = new ArrayList<Individuo>();
		
		int n = individuos.length;
		Boolean[] visitados = new Boolean[n];
		
		if(n%2 != 0) n--;
		for(int i = 0; i< n; ++i) {
			float rnd = random.nextFloat();
			if(rnd <= this.probCruce) {
				
				int segundoProg = random.nextInt(n);
				
				int nIter = 0; //para ver si ha dado una vuelta entera sin encontrar un progenitor
				while(visitados[segundoProg] && nIter < n) {
					segundoProg = segundoProg++%n;
					nIter++;
				}
				if(nIter < n)
					individuosCruzados.add(individuos[i]);
				
				visitados[segundoProg] = true;
				int puntoDeCorte = random.nextInt(individuos[i].getTamGenes().length);
							
				for(int j = 0; i < puntoDeCorte; j++) {					
					Object aux = individuos[i].getCromosoma()[j];
					individuos[i].getCromosoma()[j] = individuos[segundoProg].getCromosoma()[j];
					individuos[segundoProg].getCromosoma()[j] = aux;
				}
				for(int j = puntoDeCorte; i < n; j++) {
					Object aux = individuos[segundoProg].getCromosoma()[j];
					individuos[segundoProg].getCromosoma()[j] = individuos[i].getCromosoma()[j];
					individuos[i].getCromosoma()[j] = aux;
				}
				individuosCruzados.add(individuos[i]);
				individuosCruzados.add(individuos[segundoProg]);
			}
			else {
				individuosCruzados.add(individuos[i]);
			}
		}
		return (Individuo[]) individuosCruzados.toArray();
	}

}
