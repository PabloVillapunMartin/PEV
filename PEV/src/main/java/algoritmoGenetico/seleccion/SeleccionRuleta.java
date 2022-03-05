package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionRuleta extends Seleccion {

	private Random rnd;
	
	public SeleccionRuleta() {
		rnd = new Random();
	}
	
	@Override
	public int[] seleccionar(Individuo[] individuos, double[] fitness) {
		int n = individuos.length;
		int [] individuosSeleccionados = new int[individuos.length];
		
		double [] punt_acu = new double [n + 1];
		double sumaFitness = 0;
		for(int i = 0; i < n; ++i) {
			punt_acu[i] = sumaFitness;
			sumaFitness += individuos[i].getFitness();
		}
		for(int i = 0; i < n; ++i) {
			punt_acu[i] /=sumaFitness;
		}
		punt_acu[n] = 1.0;
		
		//Proceso de seleccion
		float prob = 0.0f;
		int pos_super = 0;
		for(int i = 0; i < n; ++i) {
			prob = rnd.nextFloat();
			pos_super = 0;
			
			while(prob > punt_acu[pos_super]){
				pos_super++;
			}
			individuosSeleccionados[i] = pos_super - 1;
		}
		

		return individuosSeleccionados;
	}

}
