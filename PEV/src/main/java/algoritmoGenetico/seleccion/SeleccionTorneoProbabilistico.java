package algoritmoGenetico.seleccion;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionTorneoProbabilistico extends Seleccion {
	
	private Random rnd;
	
	/*
	 * Constructora de clase
	 */
	public SeleccionTorneoProbabilistico() {
		rnd = new Random();
	}
	
	/*
	 * Seleccion por torneo probabilistico
	 */
	@Override
	public int[] seleccionar(Individuo[] individuos) {
		int n = individuos.length;
		int[] individuosSeleccionados = new int[n];
		
		float p = 0.55f;
		//Realizacion de tantos torneos como individuos tiene la poblacion
		for(int i = 0; i< n; i++) {			
			int mejorIndividuo = rnd.nextInt(n);	
			int peorIndividuo = mejorIndividuo;
			//Hallamos el mejor y el peor individuo
			for(int j = 1; j < 3; j++) {
				int aux = rnd.nextInt(n);
				if(individuos[aux].compareTo(individuos[mejorIndividuo]) < 0)
					mejorIndividuo = aux;
				if(individuos[aux].compareTo(individuos[peorIndividuo]) > 0)
					peorIndividuo = aux;
			}
			//Elegimos de forma probabilistica
			float random = rnd.nextFloat();
			if(random > p)
				individuosSeleccionados[i] = mejorIndividuo;
			else
				individuosSeleccionados[i] = peorIndividuo;		
		}
		
		return individuosSeleccionados;
	}

}
