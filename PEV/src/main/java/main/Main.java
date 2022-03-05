package main;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.AlgoritmoGenetico.TipoCruce;
import algoritmoGenetico.AlgoritmoGenetico.TipoSeleccion;

public class Main {
	public static void main(String[] args) {
		AlgoritmoGenetico AG = new AlgoritmoGenetico();
		AG.configure(10, 2, 100, TipoCruce.monopunto, TipoSeleccion.porRuleta, 0.05f, 0.6f,0);
		AG.run();
	}
}
