package algoritmoGenetico.seleccion;

import algoritmoGenetico.individuos.Individuo;

public abstract class Seleccion {
	
	public abstract int[] seleccionar(Individuo[] individuos, double [] fitness);
}
