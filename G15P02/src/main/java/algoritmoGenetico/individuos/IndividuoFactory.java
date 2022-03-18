package algoritmoGenetico.individuos;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;

public class IndividuoFactory {
	
	/*
	 * Devuelve un objeto del individuo correspondiente a la funcion
	 */
	public static Individuo getIndividuo(FuncionIndividuo funcion, double valorError, int n) {
		switch(funcion) {
		case Funcion1:		return new IndividuoFuncion1(valorError);
		case Funcion2:		return new IndividuoFuncion2(valorError);
		case Funcion3:		return new IndividuoFuncion3(valorError);
		case Funcion4:		return new IndividuoFuncion4(valorError, n);
		case Funcion4_Real: return new IndividuoFuncion4_Real(valorError, n);
		default: 			return new IndividuoFuncion1(valorError);
		}
	}
}
