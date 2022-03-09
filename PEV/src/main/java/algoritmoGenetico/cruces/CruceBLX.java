package algoritmoGenetico.cruces;

import java.util.Arrays;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoReal;

public class CruceBLX extends Cruce {

	private double alpha;
	public CruceBLX(double probCruce2, double alpha) {
		super(probCruce2);
		this.alpha = alpha;
	}

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
			}
		}
			
		return individuos;
	}
	
	private void cruzaBLX(IndividuoReal individuo1, IndividuoReal individuo2) {
		for(int i = 0; i < individuo1.getCromosoma().length; ++i) {
			double max = Math.max(individuo1.getCromosoma()[i], individuo2.getCromosoma()[i]);
			double min = Math.min(individuo1.getCromosoma()[i], individuo2.getCromosoma()[i]);
			
			double I = max - min;
			
			max = max + I * this.alpha;
			min = min + I * this.alpha;
			individuo1.getCromosoma()[i] = random.nextFloat() * I + min;
			individuo2.getCromosoma()[i] = random.nextFloat() * I + min;		
		}
	}

}
