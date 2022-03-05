package algoritmoGenetico.seleccion;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionRestos extends Seleccion {

	private Random rnd;
	
	public SeleccionRestos() {
		rnd = new Random();
	}
	@Override
	public int[] seleccionar(Individuo[] individuos, double[] fitness) {
		int n = individuos.length;
		int [] individuosSeleccionados = new int[individuos.length];
		
		//Probabilidades para cada individuo
		double [] prob = new double [n];	
		//Puntos acumulados para cada individuo
		double [] punt_acu = new double [n + 1];
		//Hallamos la probabilidad y los puntos acumulados para la seleccion
		//por restos y por ruleta respectivamente
		double sumaFitness = 0;
		for(int i = 0; i < n; ++i) {
			prob[i] = individuos[i].getFitness();	//Guardamos el fitness del individuo
			punt_acu[i] = sumaFitness;				//Guardamos el fitness acumulado
			sumaFitness += individuos[i].getFitness();
		}
		//Dividimos entre el total
		for(int i = 0; i < n; ++i) {
			punt_acu[i] /=sumaFitness;
			prob[i] /= sumaFitness;
		}
		punt_acu[n] = 1.0;	//Asignamos el último a 1
		
		for(int i = 0; i < n; ++i) {
			prob[i] += individuos[i].getFitness() / sumaFitness;
			if(prob[i] > 1)
				individuosSeleccionados[i] = i;
			else
				individuosSeleccionados[i] = ruleta(punt_acu);
				
		}
		
		return individuosSeleccionados;
	}
	
	public int ruleta(double [] punt_acu){
		float prob = rnd.nextFloat();
		int pos_super = 0;
		
		while(prob > punt_acu[pos_super]){
			pos_super++;
		}
		return  pos_super - 1;
	}

}
