package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class CruceMonopunto<T> extends Cruce<T> {

	Random random;
	int tamIndividuo;
	
	public CruceMonopunto(float probCruce, int tamIndividuo) {
		super(probCruce);
		random = new Random();
		this.tamIndividuo = tamIndividuo;
	}

	@Override
	public Individuo<T>[] cruzar(Individuo<T>[] individuos) {

		List<Individuo<T>> individuosCruzados = new ArrayList<Individuo<T>>();

		for(int i = 0; i< individuos.length; ++i) {
			float rnd = random.nextFloat();
			if(rnd <= this.probCruce) {
				
				int segundoProg = random.nextInt(individuos.length);
				int puntoDeCorte = random.nextInt(tamIndividuo);
							
				for(int j = 0; i < puntoDeCorte; j++) {
					
					T aux = individuos[i].getCromosoma()[j];
					individuos[i].getCromosoma()[j] = individuos[segundoProg].getCromosoma()[j];
					individuos[segundoProg].getCromosoma()[j] = aux;
				}
				for(int j = puntoDeCorte; i < individuos.length; j++) {
					//
				}
			}
		}
		return null;
	}

}
