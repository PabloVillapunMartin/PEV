package algoritmoGenetico.individuos;

import java.util.Arrays;
import java.util.Random;

import algoritmoGenetico.seleccion.Seleccion;

public class IndividuoFuncion1 extends Individuo<Boolean>{
	
	private double min[];
	private double max[];
	private double valorError;
	
	private Random rand;
	
	public IndividuoFuncion1() {
		inicializaValores();		
		
		for(int i = 0; i < this.cromosoma.length; i++)  
			this.cromosoma[i] = this.rand.nextBoolean();
	}
	
	protected void inicializaValores(){
		this.min = new double[] {-3.0, 4.1};
		this.max = new double[] {12.1, 5.8};
		this.valorError = 0.001;
		this.tamGenes = new int[2];
		this.tamGenes[0] = this.tamGen(this.valorError, min[0], max[0]);
		this.tamGenes[1] = this.tamGen(this.valorError, min[1], max[1]);
		int tamTotal = tamGenes[0] + tamGenes[1];
		this.cromosoma = new Boolean[tamTotal];
		
		this.rand = new Random();
	}
	
	@Override
	public double getValor() {
		double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
		return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
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
		return this.getValor();
	}
	
	public int compareTo(Individuo o) {
		if(this.getValor() - o.getValor() > 0)
			return -1;
		if(this.getValor() - o.getValor() < 0)
			return 1;
		else return 0;
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
	
	private double getFenotipo(int genIndex) {
		return this.min[genIndex] + bin2dec(genIndex) *
				((this.max[genIndex] - this.min[genIndex]) / (Math.pow(2, this.tamGenes[genIndex]) - 1.0));
	}
	
}
