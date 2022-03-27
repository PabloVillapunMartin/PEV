package algoritmoGenetico.mutacion;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;

public class MutaciónIntercambio extends Mutacion {

	public MutaciónIntercambio(double probMutacion, FuncionIndividuo funcion) {
		super(probMutacion, funcion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Individuo[] mutar(Individuo[] individuos) {
		int n  = individuos.length;

		for(int i = 0; i < n; i++){
			if(this.rnd.nextFloat() < this.probMutacion){
				int pos1 = this.rnd.nextInt() % individuos[i].getCromosoma().length;
				int pos2 = this.rnd.nextInt() % individuos[i].getCromosoma().length;
				
				Object aux = individuos[i].getCromosoma()[pos1];
				individuos[i].getCromosoma()[pos1] = individuos[i].getCromosoma()[pos2];
				individuos[i].getCromosoma()[pos2] = aux;
			}
		}
		return individuos;
	}

}
