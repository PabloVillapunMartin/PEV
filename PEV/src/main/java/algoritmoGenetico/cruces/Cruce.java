package algoritmoGenetico.cruces;

import algoritmoGenetico.individuos.Individuo;

/*Clase abstracta que contiene la información y métodos necesarios para el cruce*/
public abstract class Cruce {

	protected double probCruce;	//Probabilidad de cruce
	
	public Cruce(double probCruce2) {
		this.probCruce = probCruce2;
	}
	/*
	 * Metodo abstracto para cruzar individuos
	 * @param individuos poblacion de individuos a cruzar
	 * */
	public abstract Individuo[] cruzar(Individuo[] individuos);
}
