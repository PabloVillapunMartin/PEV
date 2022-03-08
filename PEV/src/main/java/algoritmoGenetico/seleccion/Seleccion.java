package algoritmoGenetico.seleccion;

import algoritmoGenetico.individuos.Individuo;

public abstract class Seleccion {
	
	public abstract int[] seleccionar(Individuo[] individuos);
	
	public double[] calculaFitness(Individuo[] individuos){
		double[] fitness = new double[individuos.length];
		
		double menor = 0;
		for(int i = 0; i < individuos.length; ++i){
			if(individuos[i].getFitness() < menor)
				menor = individuos[i].getFitness();
		}
		double value =  Math.abs(menor) * 1.05;
		//Si existe un negativo dentro de los fitness de los individuos
		//tenemos que desplazarlos
		for(int i = 0; i < individuos.length; ++i)
			fitness[i] = individuos[i].getFitness() + value;
	
		return fitness;
	}
}
