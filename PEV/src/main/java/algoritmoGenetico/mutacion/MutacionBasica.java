package algoritmoGenetico.mutacion;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class MutacionBasica extends Mutacion {


	public MutacionBasica(double probMutacion) {
		super(probMutacion);
	}

	@Override
	public Individuo[] mutar(Individuo[] individuos) {
		int n = individuos.length;
		
		for(int i = 0; i < n; ++i) {
			if(rnd.nextFloat() < this.probMutacion)
				individuos[i].mutar(this.probMutacion, rnd);
		}	
		return individuos;
	}
	
}
