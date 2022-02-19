package algoritmoGenetico.seleccion;

import algoritmoGenetico.individuos.Individuo;

public abstract class Seleccion<T> {
	
	public abstract Individuo<T>[] seleccionar(Individuo<T>[] individuos, double [] fitness);
}
