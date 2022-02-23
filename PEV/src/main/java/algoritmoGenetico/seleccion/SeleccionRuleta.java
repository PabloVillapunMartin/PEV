package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionRuleta<T> extends Seleccion<T> {

	private Random rnd;
	
	public SeleccionRuleta() {
		rnd = new Random();
	}
	
	@Override
	public Individuo<T>[] seleccionar(Individuo<T>[] individuos, double[] fitness) {
		int n = individuos.length;
		List<Individuo<T>> individuosSeleccionados = new ArrayList<Individuo<T>>();
		
		int[] sel_super = new int[n];
		float prob = 0.0f;
		int pos_super = 0;
		
		//Calcular la suma de cada individuo
		float sumaFitness = 0;
		for(int i = 0; i < n; ++i) {
			sumaFitness += individuos[i].getFitness();
		}
		
		//Proceso de seleccion
		for(int i = 0; i < n; ++i) {
			prob = rnd.nextFloat();
			pos_super = 0;
			while((prob > (float)individuos[i].getFitness() / sumaFitness) && 
					pos_super < n)
				pos_super++;
			individuosSeleccionados.add(individuos[pos_super]);
		}
		
		return (Individuo<T>[]) individuosSeleccionados.toArray();
	}

}
