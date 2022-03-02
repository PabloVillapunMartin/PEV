package main;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.AlgoritmoGenetico.TipoCruce;
import algoritmoGenetico.AlgoritmoGenetico.TipoSeleccion;

public class Main {
	public static void main(String[] args) {
		AlgoritmoGenetico AG = new AlgoritmoGenetico();
		AG.configure(100, 2, 50, TipoCruce.monopunto, TipoSeleccion.porRuleta, 0.1f, 0.6f);
		AG.run();
	}
}
