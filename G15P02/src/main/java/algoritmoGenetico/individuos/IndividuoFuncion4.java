package algoritmoGenetico.individuos;

import java.util.Random;

public class IndividuoFuncion4 extends InidividuoBoolean{

	private int n = 2;
	
	/*
	 * Constructora de clase
	 */
	public IndividuoFuncion4(double valorError, int n) {
		super(valorError);
		
		this.n = n;
		inicializaValores();
		
		for(int i = 0; i < this.cromosoma.length; i++)  
			this.cromosoma[i] = this.rand.nextBoolean();
	}
	
	/*
	 * Operador que compara dos individuos de tipo 4
	 */
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
	
	/*
	 * Inicializa los valores para individuo 4
	 */
	private void inicializaValores() {
		this.min = new double[1];
		this.min[0] = 0.0;
		this.max = new double[1];
		this.max[0] = Math.PI;
		
		this.tamGenes = new int[n];
		
		int tamTotal = 0;
		for(int i = 0; i < n; ++i) 
		{
			this.tamGenes[i] = this.tamGen(this.valorError, min[0], max[0]);
			tamTotal += this.tamGenes[i];
		}
		
		this.cromosoma = new Boolean[tamTotal];
		
		rand = new Random();
	}
	
	/*
	 * Getter del fenotipo del individuo 4
	 */
	private double getFenotipo(int genIndex) {
		return this.min[0] + bin2dec(genIndex) *
				((this.max[0] - this.min[0]) / (Math.pow(2, this.tamGenes[genIndex]) - 1.0));
	}

}
