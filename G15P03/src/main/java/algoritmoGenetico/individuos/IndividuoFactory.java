package algoritmoGenetico.individuos;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.AlgoritmoGenetico.TipoArbolInicio;
import algoritmoGenetico.tablaMultiplexor.TablaMultiplexor;

public class IndividuoFactory {
	
	/*
	 * Devuelve un objeto del individuo correspondiente a la funcion
	 */
	public static Individuo getIndividuo(FuncionIndividuo funcion, TipoArbolInicio t, int altura) {
		switch(funcion) {
			case FuncionArborea: return new IndividuoArboreo(altura, t);
			default: return null;
		}
	}
}
