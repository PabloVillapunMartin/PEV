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
	public Individuo[] seleccionar(Individuo[] individuos, double[] fitness) {
		int n = individuos.length;
		Individuo [] individuosSeleccionados = new Individuo[individuos.length];
		int[] sel_super = new int[n];
		float prob = 0.0f;
		int pos_super = 0;
		
		//Calcular la suma de cada individuo
		float sumaFitness = 0;
		for(int i = 0; i < n; ++i) {
			sumaFitness += individuos[i].getFitness();
		}
		
		double fitnessAcumulado;
		//Proceso de seleccion
		for(int i = 0; i < n; ++i) {
			prob = rnd.nextFloat();
			pos_super = 0;
			fitnessAcumulado = (float)individuos[0].getFitness() / sumaFitness;
			
			while(prob > fitnessAcumulado){
				pos_super++;
				fitnessAcumulado = (float)individuos[pos_super].getFitness() / sumaFitness;
			}
			individuosSeleccionados[i] = individuos[pos_super];
		}
		
		return individuosSeleccionados;
	}

}
