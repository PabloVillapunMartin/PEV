package algoritmoGenetico.individuos;

import java.util.Arrays;
import java.util.Random;

public class IndividuoFuncion4 extends Individuo<Boolean>{

	private int n = 2;
	private double max;
	private double min;
	private double valorError;
	
	private Random rand;
	
	public IndividuoFuncion4() {
		inicializaValores();
		
		for(int i = 0; i < this.cromosoma.length; i++)  
			this.cromosoma[i] = this.rand.nextBoolean();
	}
	
	public int compareTo(Individuo o) {
		if(this.getValor() - o.getValor() > 0)
			return 1;
		if(this.getValor() - o.getValor() < 0)
			return -1;
		else return 0;
	}

	@Override
	public double getValor() {
		double sum = 0;
		
		for(int i = 1; i <= n; ++i) 
		{
			sum += Math.sin(this.getFenotipo(i - 1)) * Math.pow(Math.sin(((i + 1) * Math.pow(this.getFenotipo(i - 1), 2)) / Math.PI), 20);
		}
		
		return -sum;
	}

	@Override
	public double getFitness() {
		return this.getValor() + 2.0;
	}

	@Override
	public void mutar(double probMutacion, Random r) {
		for (int i=0; i < this.cromosoma.length; i++) {
			if (r.nextDouble() < probMutacion) 
				cromosoma[i] = r.nextBoolean();		
		}
	}
	
	private void inicializaValores() {
		this.valorError = 0.001;
		this.min = 0.0;
		this.max = Math.PI;
		this.tamGenes = new int[n];
		
		int tamTotal = 0;
		for(int i = 0; i < n; ++i) 
		{
			this.tamGenes[i] = this.tamGen(this.valorError, min, max);
			tamTotal += this.tamGenes[i];
		}
		
		this.cromosoma = new Boolean[tamTotal];
		
		rand = new Random();
	}
	
	private double getFenotipo(int genIndex) {
		return this.min + bin2dec(genIndex) *
				((this.max - this.min) / (Math.pow(2, this.tamGenes[genIndex]) - 1.0));
	}
	
	private double bin2dec(int genIndex) {
		int inicio = 0;
		for(int i = 0; i < genIndex; ++i) inicio += this.tamGenes[i];
		
		Boolean values[] = Arrays.copyOfRange(this.cromosoma, inicio, inicio + this.tamGenes[genIndex]);
		
		long result = 0;
        for (boolean bit : values) {
            result = result * 2 + (bit ? 1 : 0);
        }

        return result;
    }

}
