package algoritmoGenetico.mutacion;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public abstract class Mutacion {
	protected double probMutacion;
	protected Random rnd;
	
	public Mutacion(double probMutacion2) {
		this.probMutacion = probMutacion2;
		rnd = new Random();
	}
	
	public abstract Individuo[] mutar(Individuo[] individuos);
}
