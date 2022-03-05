package algoritmoGenetico.individuos;

import java.util.Random;

/* Clase abstracta que codifica un individuo de una poblacion*/
public abstract class Individuo<T> implements Comparable<Individuo> {
	T[] cromosoma;	//codificación de un cromosoma
	int[] tamGenes;	//tamaño de los genes del individuo
	
	/********************************************************
	 * 					GETTERS Y SETTERS					*
	 ********************************************************/
	public T[] getCromosoma() {
		return cromosoma;
	}
	
	public void setCromosoma(T[] cromosoma) {
		this.cromosoma = cromosoma;
	}

	public int[] getTamGenes() {
		return tamGenes;
	}

	public void setTamGenes(int[] tamGenes) {
		this.tamGenes = tamGenes;
	}
	
	public abstract double getValor();

	public abstract double getFitness();	
	
	/********************************************************
	 * 					OTROS MÉTODOS						*
	 ********************************************************/
	
	/*
	 * Devuelve el tamnio de un gen
	 * */
	public int tamGen(double precision, double min, double max) {
		return (int) (Math.log10(((max - min) / precision) + 1) / Math.log10(2));
	}
	/*
	 * Muta el individuo con la probabilidad dada
	 * @probMutacion probabilidad de mutacion
	 * */
	public abstract void mutar(double probMutacion, Random r);
	
	/*
	 * Copia los atributos de otro individuo a este objeto
	 */
	public void copiarIndividuo(Individuo other) {
		for(int i = 0; i < this.cromosoma.length; i++)
			this.cromosoma[i] = (T) other.getCromosoma()[i];
	}
}
