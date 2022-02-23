package algoritmoGenetico.mutacion;

import algoritmoGenetico.individuos.Individuo;

public abstract class Mutacion {
	protected float probMutacion;
	
	public Mutacion(float probMut) {
		this.probMutacion = probMut;
	}
	
	public abstract Individuo[] mutar(Individuo[] individuos);
}
