package algoritmoGenetico.individuos;

import java.util.Random;

public abstract class Individuo<T> {
	T[] cromosoma;
	int[] tamGenes;
	
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

	
	public int tamGen(double precision, double min, double max) {
		return (int) (Math.log10(((max - min) / precision) + 1) / Math.log10(2));
	}
	public abstract void mutar(double probMutacion, Random r);
	
	public abstract double getValor();

	public abstract double getFitness();
	
}
