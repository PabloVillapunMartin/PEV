package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionTorneoProbabilistico extends Seleccion {
	
	private Random rnd;
	
	public SeleccionTorneoProbabilistico() {
		rnd = new Random();
	}
	
	@Override
	public Individuo[] seleccionar(Individuo[] individuos, double[] fitness) {
		int n = individuos.length;
		List<Individuo> individuosSeleccionados = new ArrayList<Individuo>();
		float p = 0.55f;
		for(int i = 0; i< n; i++) {			
			int mejorIndividuo = rnd.nextInt(n);	
			int peorIndividuo = mejorIndividuo;
			//Hallamos el mejor y el peor individuo
			for(int j = 1; j < 3; j++) {
				int aux = rnd.nextInt(n);
				if(individuos[aux].getFitness() > individuos[mejorIndividuo].getFitness())
					mejorIndividuo = aux;
				if(individuos[aux].getFitness() < individuos[peorIndividuo].getFitness())
					peorIndividuo = aux;
			}
			//Elegimos de forma probabilistica
			float random = rnd.nextFloat();
			if(random > p)
				individuosSeleccionados.add(individuos[mejorIndividuo]);
			else
				individuosSeleccionados.add(individuos[peorIndividuo]);		
		}
		
		return (Individuo[]) individuosSeleccionados.toArray();
	}

}
