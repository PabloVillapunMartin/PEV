package algoritmoGenetico.mutacion;

import java.util.Random;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;

public abstract class Mutacion {
	protected double probMutacion;
	protected Random rnd;
	protected FuncionIndividuo funcionIndividuo;
	
	/*
	 * Constructora de clase
	 * @param probMutacion probabilidad de mutacion
	 */
	public Mutacion(double probMutacion, FuncionIndividuo funcion) {
		this.probMutacion = probMutacion;
		this.funcionIndividuo = funcion;
		rnd = new Random();
	}
	
	/*
	 * Metodo abstracto que implementa una funcionde mutacion
	 */
	public abstract Individuo[] mutar(Individuo[] individuos);
}
