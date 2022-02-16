package algoritmoGenetico.individuos;

public abstract class Individuo<T> {
	T[] cromosoma;
	int[] tamGenes;
	
	public int tamGen(double precision, double min, double max) {
		return (int) (Math.log10(((max - min) / precision) + 1) / Math.log10(2));
	}

}
