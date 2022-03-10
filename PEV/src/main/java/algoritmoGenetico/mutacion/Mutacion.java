package algoritmoGenetico.mutacion;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public abstract class Mutacion {
	protected double probMutacion;
	protected Random rnd;
	
	/*
	 * Constructora de clase
	 * @param probMutacion probabilidad de mutacion
	 */
	public Mutacion(double probMutacion) {
		this.probMutacion = probMutacion;
		rnd = new Random();
	}
	
	/*
	 * Metodo abstracto que implementa una funcionde mutacion
	 */
	public abstract Individuo[] mutar(Individuo[] individuos);
}
