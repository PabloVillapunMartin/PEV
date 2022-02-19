package algoritmoGenetico.cruces;

import algoritmoGenetico.individuos.Individuo;

public abstract class Cruce<T> {

	protected float probCruce;
	
	public Cruce(float probCruce) {
		this.probCruce = probCruce;
	}
	public abstract Individuo<T>[] cruzar(Individuo<T>[] individuos);
}
