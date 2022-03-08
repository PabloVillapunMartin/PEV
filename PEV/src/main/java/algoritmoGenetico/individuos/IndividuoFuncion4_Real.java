package algoritmoGenetico.individuos;

import java.util.Random;

public class IndividuoFuncion4_Real extends Individuo<Double> {

	protected double min[];
	protected double max[];
	protected double valorError;
	
	protected Random rand;
	
	int n;
	
	public IndividuoFuncion4_Real(double valorError, int n){
		this.valorError = valorError;
		this.n = n;
		inicializaValores();
		for(int i = 0; i < this.cromosoma.length; i++)  
			this.cromosoma[i] = rand.nextDouble() * Math.PI;	
		
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
		return -this.getValor();
	}

	@Override
	public void mutar(double probMutacion, Random r) {
		for (int i=0; i < this.cromosoma.length; i++) {
			if (r.nextDouble() < probMutacion) 
				cromosoma[i] = r.nextDouble() * Math.PI;		
		}
	}
	
	private double getFenotipo(int genIndex) {
		return this.min[0] + cromosoma[genIndex] *
				((this.max[0] - this.min[0]) / (Math.pow(2, this.tamGenes[genIndex]) - 1.0));
	}
	
	private void inicializaValores() {
		this.min = new double[1];
		this.min[0] = 0.0;
		this.max = new double[1];
		this.max[0] = Math.PI;
		
		this.tamGenes = new int[n];
		
		int tamTotal = 0;
		for(int i = 0; i < n; ++i) 
		{
			this.tamGenes[i] = 1;
			tamTotal += this.tamGenes[i];
		}
		
		this.cromosoma = new Double[tamTotal];
		
		rand = new Random();
	}


}
