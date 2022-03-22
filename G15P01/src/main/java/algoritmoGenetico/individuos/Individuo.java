package algoritmoGenetico.individuos;

import java.util.Arrays;
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
	
	/*
	 * Setter del cromosoma
	 */
	public void setCromosoma(T[] cromosoma) {
		this.cromosoma = cromosoma;
	}

	/*
	 * Getter del size de los genes
	 */
	public int[] getTamGenes() {
		return tamGenes;
	}

	/*
	 * Setter del size de los genes
	 */
	public void setTamGenes(int[] tamGenes) {
		this.tamGenes = tamGenes;
	}
	
	/*
	 * Devuelve el valor del individuo
	 */
	public abstract double getValor();

	/*
	 * Devuelve la aptitud del individuo
	 */
	public abstract double getFitness();	
	
	/********************************************************
	 * 					OTROS MÉTODOS						*
	 ********************************************************/
	
	/*
	 * Calcula el size de un gen
	 * @param precision cantidad de cifras decimales de precision
	 * @param valor minimo
	 * @param max valor maximo 
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
	public  abstract void copiarIndividuo(Individuo other);
	
	public String toString(){
		String s = "";
		for(int i = 0; i < this.cromosoma.length; ++i){
			s += this.cromosoma[i] + " ";
		}
		return s;
	}
}
