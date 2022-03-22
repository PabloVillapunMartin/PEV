package algoritmoGenetico.individuos;

import java.util.Random;

public class IndividuoReal extends Individuo<Double>{

	public int compareTo(Individuo o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getFitness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void mutar(double probMutacion, Random r) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Copia los atributos de otro individuo a este objeto
	 */
	@Override
	public void copiarIndividuo(Individuo other) {	
		for(int i = 0; i < other.getCromosoma().length; ++i){
			this.cromosoma[i] = (Double) other.getCromosoma()[i];

		}
	}

}
