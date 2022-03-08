package algoritmoGenetico.mutacion;

import algoritmoGenetico.individuos.Individuo;

public abstract class Mutacion {
	protected double probMutacion;
	
	public Mutacion(double probMutacion2) {
		this.probMutacion = probMutacion2;
	}
	
	public abstract Individuo[] mutar(Individuo[] individuos);
}
