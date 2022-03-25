package algoritmoGenetico.individuos;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.aviones.TraficoAereo;

public class IndividuoFactory {
	
	/*
	 * Devuelve un objeto del individuo correspondiente a la funcion
	 */
	public static Individuo getIndividuo(FuncionIndividuo funcion, double valorError, int n) {
		switch(funcion) {
			case FuncionAvion: return new IndividuoAvion(TraficoAereo.getInstance().getNumAviones());
			default: return null;
		}
	}
}
