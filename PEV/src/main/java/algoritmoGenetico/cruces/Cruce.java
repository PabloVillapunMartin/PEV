package algoritmoGenetico.cruces;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/*Clase abstracta que contiene la información y métodos necesarios para el cruce*/
public abstract class Cruce {

	protected Random random;
	protected double probCruce;	//Probabilidad de cruce
	
	public Cruce(double probCruce2) {
		this.probCruce = probCruce2;
		this.random = new Random();
	}
	/*
	 * Metodo abstracto para cruzar individuos
	 * @param individuos poblacion de individuos a cruzar
	 * */
	public abstract Individuo[] cruzar(Individuo[] individuos);
	
	/*
	 * Busca un individuo que no haya sido visitado
	 * @param visitados array de visitados para comprobar si lo han sido
	 * @param n tamaño del array
	 * @return devuelve el indice del individuo
	 * */
	protected int buscarIndividuo(Boolean[] visitados, int n){
		int i = 0;
		while(visitados[i] ) {
			i = (i + 1)%n;
		}
		visitados[i] = true;
		return i;
	}
}
