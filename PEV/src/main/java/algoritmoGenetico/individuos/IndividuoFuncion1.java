package algoritmoGenetico.individuos;

import java.util.Random;

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

	public double getFenotipo(int genIndex) {
		 
		//COgemos la parte del gen adecuada 
		Boolean[] binario = new Boolean[this.tamGenes[genIndex]];
		//Posicion inicial desde la cual se va a copiar el cromosoma
		int posicionInicial = 0;
		//Si el gen que queremos mirar es el 2do  el inicio de la copia 
		//del cromosoma empieza donde termina el primer gen
		if(genIndex == 1) 
			posicionInicial = this.tamGenes[0];
		//Copiamos el gen
		for(int i = 0; i < binario.length; i++) {
			binario[i] = this.cromosoma[i + posicionInicial];
		}
		//Para la parte decimal, sabemos que son 3 cifras y que el valor max es 999
        double decimal = 0;
        int tamGen = binario.length;
        int tamDecimal = Integer.toBinaryString(999).length();
        //En posicion de bits, de derecha a izquierda, interpretamos cada bit en potencia
        //de 2 y añadimos su valor al acumulador
        int parteDecimal = 0;
        for(int i = 0; i < tamDecimal; ++i) {
        	int pos = binario.length - i - 1;
        	if(binario[pos]) 
        		parteDecimal += Math.pow(2, i);
        }
        
        //Calculamos la parte entera de la misma forma
        int tamEntero = binario.length - tamDecimal - 1; //-1 porque el primero es el signo
        int parteEntera = 0;
        for(int i = 0; i < tamEntero; ++i) {
        	int pos = tamEntero - i - 1;
        	if(binario[pos]) 
        		parteEntera += Math.pow(2, i);
        }
        
        //Hallamos el fenotipo con la suma de los valores y obtenemos el signo
        double fenotipo = parteEntera + parteDecimal * this.valorError;
        fenotipo *= binario[0] ? 1 : -1;
        
        return fenotipo;
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
}
