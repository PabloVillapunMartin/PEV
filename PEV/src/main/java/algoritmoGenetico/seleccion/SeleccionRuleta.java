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
		
		double [] punt_acu = new double [n];
		double sumaFitness = 0;
		for(int i = 0; i < n; ++i) {
			sumaFitness += individuos[i].getFitness();
			punt_acu[i] = sumaFitness;
		}
		for(int i = 0; i < n; ++i) {
			punt_acu[i] /=sumaFitness;
		}
		
		
		double fitnessAcumulado;
		//Proceso de seleccion
		float prob = 0.0f;
		int pos_super = 0;
		for(int i = 0; i < n; ++i) {
			prob = rnd.nextFloat();
			pos_super = 0;
			fitnessAcumulado = 0;
			
			while(prob > punt_acu[pos_super] && pos_super < n){
				pos_super++;
			}
			individuosSeleccionados[i] = individuos[pos_super];
		}
		

		return individuosSeleccionados;
	}

}
