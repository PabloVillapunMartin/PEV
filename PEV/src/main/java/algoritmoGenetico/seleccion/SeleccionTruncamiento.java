package algoritmoGenetico.seleccion;

import java.util.Arrays;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionTruncamiento extends Seleccion {

	private int truncamiento = 50;
	
	@Override
	public Individuo[] seleccionar(Individuo[] individuos, double[] fitness) {
		int n = individuos.length;
		Individuo [] individuosSeleccionados = new Individuo[individuos.length];
		
		Arrays.sort(individuos);
		
		return individuosSeleccionados;
	}

}
