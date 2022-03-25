package algoritmoGenetico.seleccion;

import java.util.Arrays;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionRanking extends Seleccion {

	float beta;
	Random rnd;
	
	public SeleccionRanking() {
		beta = 1.5f;
		rnd = new Random();
	}
	@Override
	public int[] seleccionar(Individuo[] individuos) {
		int n = individuos.length;
		int[] individuosSeleccionados = new int[n];
		
		Arrays.sort(individuos);
		double [] punt_acu = new double [n];
		
		punt_acu[0] = probabilidadRanking(n, 0);
		for(int i = 1; i < n; ++i) {
			punt_acu[i] = punt_acu[i - 1] + probabilidadRanking(n, i);
		}
			
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
	
	private double probabilidadRanking(int n, int i) {
		double division = ((double)i-1.0f)/((double)n-1.0f);
		double betaProduct = beta - 2 * (beta - 1) * division;
		return (1/n) * betaProduct;
	}
	

}
