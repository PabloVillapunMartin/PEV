package algoritmoGenetico.individuos;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;

public class IndividuoFactory {
	
	/*
	 * Devuelve un objeto del individuo correspondiente a la funcion
	 */
	public static Individuo getIndividuo(FuncionIndividuo funcion) {
		switch(funcion) {
		case Funcion1:	return new IndividuoFuncion1();
		case Funcion2:	return new IndividuoFuncion2();
		case Funcion3:	return new IndividuoFuncion3();
		case Funcion4:	return new IndividuoFuncion4();
		default: 		return new IndividuoFuncion1();
		}
	}
}
