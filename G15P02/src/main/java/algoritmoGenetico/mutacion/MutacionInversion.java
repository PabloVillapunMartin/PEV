package algoritmoGenetico.mutacion;

import java.util.Arrays;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;

public class MutacionInversion extends Mutacion {

	public MutacionInversion(double probMutacion, FuncionIndividuo funcion) {
		super(probMutacion, funcion);
	}

	@Override
	public Individuo[] mutar(Individuo[] individuos) {
		int n  = individuos.length;

		for(int i = 0; i < n; i++){
			if(this.rnd.nextFloat() < this.probMutacion)
				mutarIndividuo(individuos[i]);
		}
		return individuos;
	}

	private void mutarIndividuo(Individuo individuo) {
		int corte1 = this.rnd.nextInt(individuo.getCromosoma().length);
		int corte2 = this.rnd.nextInt(individuo.getCromosoma().length);
		
		if(corte1 > corte2) {
			int aux = corte1;
			corte1 = corte2;
			corte2 = aux;
		}
		
		Object[] tramo = Arrays.copyOfRange(individuo.getCromosoma(), corte1, corte2);
		
		for(int i = 0; i < tramo.length; ++i) {
			individuo.getCromosoma()[corte1 + i] = tramo[tramo.length - i - 1];
		}
	}
}
