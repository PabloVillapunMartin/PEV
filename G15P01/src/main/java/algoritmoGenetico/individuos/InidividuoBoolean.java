package algoritmoGenetico.individuos;

import java.util.Arrays;
import java.util.Random;

public class InidividuoBoolean  extends Individuo<Boolean>{

	protected double min[];
	protected double max[];
	protected double valorError;
	
	protected Random rand;
	
	public InidividuoBoolean(double valorError){
		this.valorError = valorError;
	}
	
	public int compareTo(Individuo arg0) {
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
		for (int i=0; i < this.cromosoma.length; i++) {
			if (r.nextDouble() < probMutacion) 
				cromosoma[i] = r.nextBoolean();		
		}
	}
	
	protected double bin2dec(int genIndex) {
		int inicio = 0;
		for(int i = 0; i < genIndex; ++i) inicio += this.tamGenes[i];
		
		Boolean values[] = Arrays.copyOfRange(this.cromosoma, inicio, inicio + this.tamGenes[genIndex]);
		
		long result = 0;
        for (boolean bit : values) {
            result = result * 2 + (bit ? 1 : 0);
        }
        return result;
    }
	
	/*
	 * Copia los atributos de otro individuo a este objeto
	 */
	@Override
	public void copiarIndividuo(Individuo other) {	
		for(int i = 0; i < other.getCromosoma().length; ++i){
			this.cromosoma[i] = (Boolean) other.getCromosoma()[i];
		}
	}
}
