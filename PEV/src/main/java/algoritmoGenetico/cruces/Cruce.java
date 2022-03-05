package algoritmoGenetico.cruces;

import algoritmoGenetico.individuos.Individuo;

/*Clase abstracta que contiene la información y métodos necesarios para el cruce*/
public abstract class Cruce {

	protected float probCruce;	//Probabilidad de cruce
	
	public Cruce(float probCruce) {
		this.probCruce = probCruce;
	}
	/*
	 * Metodo abstracto para cruzar individuos
	 * @param individuos poblacion de individuos a cruzar
	 * */
	public abstract Individuo[] cruzar(Individuo[] individuos);
}
