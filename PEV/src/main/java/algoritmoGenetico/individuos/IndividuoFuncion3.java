package algoritmoGenetico.individuos;

import java.util.Arrays;
import java.util.Random;

public class IndividuoFuncion3 extends Individuo<Boolean>{

	private double min[];
	private double max[];
	private double valorError;
	
	private Random rand;
	
	public IndividuoFuncion3() {
		inicializaValores();
		
		for(int i = 0; i < this.cromosoma.length; i++)  
			this.cromosoma[i] = this.rand.nextBoolean();
	}

	@Override
	public double getValor() {
		double x1 = this.getFenotipo(0);
		double x2 = this.getFenotipo(1);
		double parte1 = -(x2 + 47) * Math.sin(Math.sqrt(Math.abs(x2 + (x1/2) + 47)));
		double parte2 = -x1 * Math.sin(Math.sqrt(Math.abs(x1 - (x2 + 47))));
		
		return parte1 + parte2;
	}

	@Override
	public double getFitness() {
		return -this.getValor();
	}

	@Override
	public void mutar(double probMutacion, Random r) {
		for (int i=0; i < this.cromosoma.length; i++) {
			if (r.nextDouble() < probMutacion) 
				cromosoma[i] = r.nextBoolean();		
		}
	}

	protected void inicializaValores() {
		this.valorError = 0.001;
		this.min = new double[] {-512, -512};
		this.max = new double[] {512, 512};
		this.tamGenes = new int[2];
		this.tamGenes[0] = this.tamGen(this.valorError, min[0], max[0]);
		this.tamGenes[1] = this.tamGen(this.valorError, min[1], max[1]);
		this.cromosoma = new Boolean[tamGenes[0] + tamGenes[1]];
		
		rand = new Random();
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
		for(int i = 0; i < genIndex; ++i) inicio += this.tamGenes[i];
		
		Boolean values[] = Arrays.copyOfRange(this.cromosoma, inicio, inicio + this.tamGenes[genIndex]);
		
		long result = 0;
        for (boolean bit : values) {
            result = result * 2 + (bit ? 1 : 0);
        }

        return result;
    }
}
