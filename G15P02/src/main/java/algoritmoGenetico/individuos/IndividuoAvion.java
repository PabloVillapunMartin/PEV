package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import algoritmoGenetico.aviones.TraficoAereo;

public class IndividuoAvion extends Individuo<Integer>{

	protected Random rand;
	int n;
	/*
	 * Constructora de clase
	 */
	public IndividuoAvion(int n) {
		this.n = n;
		inicializaValores();		
		
		boolean [] visitado = new boolean[n];
		Arrays.fill(visitado, false);
		for(int i = 0; i < this.cromosoma.length; i++)  {
			int j = this.rand.nextInt()%n;
			while(visitado[j]) j = (j+1)%n;
			
			visitado[j] = true;
			this.cromosoma[i] = j;
		}
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
		ArrayList<ArrayList<Float>> pistas = new ArrayList<ArrayList<Float>>(TraficoAereo.getInstance().getNumPistas());
		for(int avion = 0; avion < this.cromosoma.length; avion++)  {
			int minimo = Integer.MAX_VALUE;	//El tel mínimo de la pista final
			int indexPista = 0;				//Pista final a la que vaya el avion
			for(int pista = 0; pista < TraficoAereo.getInstance().getNumPistas(); pista++) {
				float tiempoLlegada = TraficoAereo.getInstance().getTel(pista, avion);
				//for(int i = 0; i < pistas) TODO iterar con cada pista buscando si es posible y ir modificando el tiempo de llegada 
			}
		}
	}
	@Override
	public double getFitness() {
		return -getValor();
	}
	
	/*
	 * Inicializa los valores para individuo 1
	 */
	protected void inicializaValores(){
		
		this.tamGenes = new int[1];
		this.tamGenes[0] = n;
		this.cromosoma = new Integer[n];	
		this.rand = new Random();
	}
	 
}
