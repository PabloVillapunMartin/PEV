package algoritmoGenetico.individuos;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.tablaMultiplexor.TablaMultiplexor;

public class IndividuoFactory {
	
	/*
	 * Devuelve un objeto del individuo correspondiente a la funcion
	 */
	public static Individuo getIndividuo(FuncionIndividuo funcion) {
		switch(funcion) {
			case FuncionArborea: return new IndividuoArboreo(TablaMultiplexor.getInstance().getMaxProfundidad());
			default: return null;
		}
	}
}
