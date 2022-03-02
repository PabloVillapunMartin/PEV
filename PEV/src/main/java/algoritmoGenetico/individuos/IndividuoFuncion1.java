package algoritmoGenetico.individuos;

import java.util.Random;

import algoritmoGenetico.seleccion.Seleccion;

public class IndividuoFuncion1 extends Individuo<Boolean> {
	
	private double min[];
	private double max[];
	private double valorError;
	
	private Random rand;
	
	public IndividuoFuncion1() {
		this.tamGenes = new int[2];
		this.min = new double[2];
		this.max = new double[2];
		this.min[0] = -3.000;
		this.min[1] = 4.100;
		this.max[0] = 12.100;
		this.max[1] = 5.800;
		this.valorError = 0.001;
		this.tamGenes[0] = this.tamGen(this.valorError, min[0], max[0]);
		this.tamGenes[1] = this.tamGen(this.valorError, min[1], max[1]);
		int tamTotal = tamGenes[0] + tamGenes[1];
		this.cromosoma = new Boolean[tamTotal];
		
		this.rand = new Random();
		
		for(int i = 0; i < tamTotal; i++)  
			this.cromosoma[i] = this.rand.nextBoolean();
	}

	public double bin2dec(int genIndex) {
		int valorDecimal = 0;
		int indice = 0;
		if(genIndex == 1)
			indice = this.tamGenes[0];			
		
        for(int i = indice; i < this.tamGenes[genIndex] + indice; ++i) {
        	if(this.cromosoma[i]) {
        		valorDecimal += Math.pow(2,this.tamGenes[genIndex] - 1 - i);
           	}
        }
		 
        return valorDecimal;
    }
	
	@Override
	public double getValor() {
		double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
		return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
	}

	@Override
	public double getFitness() {
		return getValor();
	}

	@Override
	public void mutar(double probMutacion, Random r) {
		for (int i=0; i < cromosoma.length; i++) {
			if (r.nextDouble() < probMutacion) 
				cromosoma[i] = r.nextBoolean();		
		}
	}
	
	private double getFenotipo(int genIndex) {
		return this.min[genIndex] + bin2dec(genIndex) *
				(this.max[genIndex] - this.min[genIndex]) / (Math.pow(2, this.cromosoma.length) - 1);
	}
	
}
