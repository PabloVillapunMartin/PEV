package algoritmoGenetico.seleccion;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionRestos extends Seleccion {

	private Random rnd;
	
	/*
	 * Constructora de clase
	 */
	public SeleccionRestos() {
		rnd = new Random();
	}
	
	/*
	 * Seleccion por restos
	 */
	@Override
	public int[] seleccionar(Individuo[] individuos) {
		int n = individuos.length;
		int [] individuosSeleccionados = new int[individuos.length];
		
		//Probabilidades para cada individuo
		double [] fitness = calculaFitness(individuos);
		//Puntos acumulados para cada individuo
		double [] punt_acu = new double [n + 1];
		//Hallamos la probabilidad y los puntos acumulados para la seleccion
		//por restos y por ruleta respectivamente
		double sumaFitness = 0;
		for(int i = 0; i < n; ++i) {
			punt_acu[i] = sumaFitness;		//Guardamos el fitness acumulado
			sumaFitness += fitness[i];
		}
		//Dividimos entre el total
		for(int i = 0; i < n; ++i) {
			punt_acu[i] /=sumaFitness;
			fitness[i] /= sumaFitness;
		}
		punt_acu[n] = 1.0;	//Asignamos el ?ltimo a 1
		
		for(int i = 0; i < n; ++i) {
			if(fitness[i] *  n > 1)
				individuosSeleccionados[i] = i;
			else
				individuosSeleccionados[i] = ruleta(punt_acu);
				
		}
		
		return individuosSeleccionados;
	}
	
	/*
	 * Metodo de seleccion por el cual se seleccionan los individuos sobrantes
	 */
	public int ruleta(double [] punt_acu){
		float prob = rnd.nextFloat();
		int pos_super = 0;
		
		while(prob > punt_acu[pos_super]){
			pos_super++;
		}
		return  pos_super - 1;
	}

}
