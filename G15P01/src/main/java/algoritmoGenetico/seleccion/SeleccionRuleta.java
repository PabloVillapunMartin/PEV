package algoritmoGenetico.seleccion;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionRuleta extends Seleccion {

	private Random rnd;
	
	/*
	 * Constructora de clase
	 */
	public SeleccionRuleta() {
		rnd = new Random();
	}
	
	/*
	 * Seleccion por ruleta
	 */
	@Override
	public int[] seleccionar(Individuo[] individuos) {
		int n = individuos.length;
		int [] individuosSeleccionados = new int[individuos.length];
		
		//Calcular fitness de cada individuo
		double [] fitness = calculaFitness(individuos);
		//Array que contiene los puntos acumulados de cada individuo
		double [] punt_acu = new double [n];
		
		double sumaFitness = 0;
		//Bucle para calcular la suma del fitness y los puntos acumulados
		for(int i = 0; i < n; ++i) {
			punt_acu[i] = sumaFitness;
			sumaFitness += fitness[i];
		}
		for(int i = 0; i < n; ++i) {
			punt_acu[i] /= sumaFitness;
		}
		punt_acu[n - 1] = 1.0;
		
		//Proceso de seleccion
		float prob = 0.0f;
		int pos_super = 0;
		for(int i = 0; i < n; ++i) {
			prob = rnd.nextFloat();
			pos_super = 0;
			
			while(punt_acu[pos_super] < prob){
				pos_super++;
			}
			individuosSeleccionados[i] = pos_super;
		}
		

		return individuosSeleccionados;
	}

}
