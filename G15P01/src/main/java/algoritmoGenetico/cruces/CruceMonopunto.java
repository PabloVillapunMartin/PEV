package algoritmoGenetico.cruces;

import java.util.Arrays;

import algoritmoGenetico.individuos.Individuo;

public class CruceMonopunto extends Cruce {

	/*
	 * Constructora de clase
	 */
	public CruceMonopunto(double probCruce) {
		super(probCruce);
	}
	
	/*
	 * Cruza a una poblacion utilizando el algoritmo de monopunto
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
			float rnd = random.nextFloat();
			
			//Si el individuo no ha sido visitado y se selecciona para cruzar
			if(!visitados[i] && rnd <= this.probCruce){	
				visitados[i] = true;						//Lo visitamos
				int padre2 = buscarIndividuo(visitados, n);	//Buscamos otro padre

				//Hallamos el punto de corte y realizamos el cruce de los individuos
				int puntoDeCorte = random.nextInt(individuos[i].getCromosoma().length);					
				for(int j = 0; j < individuos[i].getCromosoma().length; j++) {		
					if(j < puntoDeCorte)
						individuos[padre2].getCromosoma()[j] = individuos[i].getCromosoma()[j];
					else 
						individuos[i].getCromosoma()[j] = individuos[padre2].getCromosoma()[j];
				}
			}
		}
			
		return individuos;
	}
}
