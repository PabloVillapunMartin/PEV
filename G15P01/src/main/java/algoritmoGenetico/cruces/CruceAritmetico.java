package algoritmoGenetico.cruces;

import java.util.Arrays;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoReal;

public class CruceAritmetico extends Cruce {

	private float alpha;
	
	/*
	 * Constructora de clase
	 */
	public CruceAritmetico(double probCruce2, float alpha) {
		super(probCruce2);
		this.alpha = alpha;
	}

	/*
	 * Cruce aritmetico
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

				cruzaMedia((IndividuoReal)individuos[i], (IndividuoReal)individuos[padre2]);
			}
		}
			
		return individuos;
	}

	/*
	 * Realiza la media ponderada de los cromosomas de ambos individuos y les asigna ambos valores
	 */
	private void cruzaMedia(IndividuoReal individuo1, IndividuoReal individuo2) {
		for(int i = 0; i < individuo1.getCromosoma().length; ++i) {
			//Medias ponderadas
			double h1 = this.alpha * individuo1.getCromosoma()[i] + (1-this.alpha) * individuo2.getCromosoma()[i];
			double h2 = this.alpha * individuo2.getCromosoma()[i] + (1-this.alpha) * individuo1.getCromosoma()[i];
			
			//Asignacion
			individuo1.getCromosoma()[i] = h1;
			individuo2.getCromosoma()[i] = h2;
		}
	}
}
