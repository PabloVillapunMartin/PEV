package algoritmoGenetico.seleccion;

import algoritmoGenetico.individuos.Individuo;

public abstract class Seleccion {
	
	public abstract Individuo[] seleccionar(Individuo[] individuos, double [] fitness);
}
