package algoritmoGenetico.individuos;

import java.util.Arrays;
import java.util.Random;

public class IndividuoFuncion3 extends InidividuoBoolean{
	
	public IndividuoFuncion3(double valorError) {
		super(valorError);
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

	protected void inicializaValores() {
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
}
