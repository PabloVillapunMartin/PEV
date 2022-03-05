package main;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.AlgoritmoGenetico.TipoCruce;
import algoritmoGenetico.AlgoritmoGenetico.TipoSeleccion;

public class Main {
	public static void main(String[] args) {
		AlgoritmoGenetico AG = new AlgoritmoGenetico();
		AG.configura(100, 100, TipoCruce.uniforme, TipoSeleccion.restos, 0.05f, 0.6f,0.1f, true);
		AG.run();
	}
}
