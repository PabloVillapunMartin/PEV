package algoritmoGenetico.seleccion;

import java.util.Arrays;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionTruncamiento extends Seleccion {

	//Porcentaje de individuos con los que nos quedamos en el truncamiento
	private float truncamiento = 0.5f;
	
	@Override
	public int[] seleccionar(Individuo[] individuos) {
		int n = individuos.length;
		int [] individuosSeleccionados = new int[individuos.length];
		
		//Ordenar array de mayor a menor
		Arrays.sort(individuos);
		
		float offset = (n*truncamiento) / n;
		float index = 0;
		for(int i = 0; i < n; ++i) {
			individuosSeleccionados[i] = (int)index;
			index += offset;
		}
		
		return individuosSeleccionados;
	}

}
