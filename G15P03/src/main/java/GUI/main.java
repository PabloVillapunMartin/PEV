package GUI;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFactory;
import algoritmoGenetico.tablaMultiplexor.TablaMultiplexor;

public class main {
	public static void main(String[] args) {
		TablaMultiplexor tabla = TablaMultiplexor.getInstance();
		tabla.init(2, 4, 5);
		Individuo i = IndividuoFactory.getIndividuo(FuncionIndividuo.FuncionArborea);
		double fit = i.getFitness();
	}
}
