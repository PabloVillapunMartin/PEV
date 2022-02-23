package algoritmoGenetico.cruces;

import algoritmoGenetico.individuos.Individuo;

public abstract class Cruce {

	protected float probCruce;
	
	public Cruce(float probCruce) {
		this.probCruce = probCruce;
	}
	public abstract Individuo[] cruzar(Individuo[] individuos);
}
