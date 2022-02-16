package algoritmoGenetico.seleccion;

import algoritmoGenetico.individuos.Individuo;

public abstract class Seleccion<T> {
	
	public abstract Individuo<T>[] Seleccion(Individuo<T>[] individuos, double [] fitness);
}
