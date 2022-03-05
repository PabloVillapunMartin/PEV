package algoritmoGenetico.individuos;

import java.util.Arrays;
import java.util.Random;

public class IndividuoFuncion2 extends Individuo<Boolean> {

	private double min[];
	private double max[];
	private double valorError;
	
	private Random rand;
	
	public IndividuoFuncion2() {
		inicializaValores();
		
		for(int i = 0; i < this.cromosoma.length; i++)  
			this.cromosoma[i] = this.rand.nextBoolean();
	}
	
	protected void inicializaValores() {
		this.valorError = 0.001;
		this.min = new double[] {-10.0, -10.0};
		this.max = new double[] {10.0, 10.0};
		this.tamGenes = new int[2];
		this.tamGenes[0] = this.tamGen(this.valorError, min[0], max[0]);
		this.tamGenes[1] = this.tamGen(this.valorError, min[1], max[1]);
		this.cromosoma = new Boolean[tamGenes[0] + tamGenes[1]];
		
		rand = new Random();
	}

	@Override
	public double getValor() {
		double sum1 = 0.0;
		double sum2 = 0.0;
		
		for(int i = 1; i <= 5; ++i) sum1 += i * Math.cos((i + 1) * this.getFenotipo(0) + i);
		
		for(int i = 1; i <= 5; ++i) sum2 += i * Math.cos((i + 1) * this.getFenotipo(1) + i);
		
		return sum1 * sum2;
	}

	@Override
	public void mutar(double probMutacion, Random r) {
		for (int i=0; i < this.cromosoma.length; i++) {
			if (r.nextDouble() < probMutacion) 
				cromosoma[i] = r.nextBoolean();		
		}		
	}
	
	@Override
	public double getFitness() {
		return this.getValor() + 187;
	}
	
	public int compareTo(Individuo o) {
		if(this.getValor() - o.getValor() > 0)
			return 1;
		if(this.getValor() - o.getValor() < 0)
			return -1;
		else return 0;
	}
	
	private double getFenotipo(int genIndex) {
		return this.min[genIndex] + bin2dec(genIndex) *
				((this.max[genIndex] - this.min[genIndex]) / (Math.pow(2, this.tamGenes[genIndex]) - 1.0));
	}
	
	private double bin2dec(int genIndex) {
		int inicio = 0;
		if(genIndex == 1)
			inicio += this.tamGenes[0];
		Boolean values[] = Arrays.copyOfRange(this.cromosoma, inicio, inicio + this.tamGenes[genIndex]);
		
		long result = 0;
        for (boolean bit : values) {
            result = result * 2 + (bit ? 1 : 0);
        }

        return result;
    }
	
}
