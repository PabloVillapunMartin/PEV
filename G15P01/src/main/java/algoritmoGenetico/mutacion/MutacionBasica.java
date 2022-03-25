package algoritmoGenetico.mutacion;

import algoritmoGenetico.individuos.Individuo;

public class MutacionBasica extends Mutacion {

	/*
	 * Constructora de clase
	 */
	public MutacionBasica(double probMutacion) {
		super(probMutacion);
	}

	/*
	 * Realiza mutaciones en los individuos segun la probabilidad de mutacion
	 */
	@Override
	public Individuo[] mutar(Individuo[] individuos) {
		int n = individuos.length;
		
		for(int i = 0; i < n; ++i) {
			individuos[i].mutar(this.probMutacion, rnd);
		}	
		return individuos;
	}
	
}
