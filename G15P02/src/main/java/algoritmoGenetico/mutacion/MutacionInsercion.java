package algoritmoGenetico.mutacion;

import java.util.ArrayList;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFactory;

public class MutacionInsercion extends Mutacion {

	public MutacionInsercion(double probMutacion, FuncionIndividuo funcion) {
		super(probMutacion, funcion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Individuo[] mutar(Individuo[] individuos) {
		int n  = individuos.length;

		for(int i = 0; i < n; i++){
			if(this.rnd.nextFloat() < this.probMutacion)
				mutarIndividuo(individuos[i]);
		}
		return individuos;
	}
	
	private void mutarIndividuo(Individuo ind){
		int inserciones = 1;
		int n = ind.getCromosoma().length;
		
		Individuo aux = IndividuoFactory.getIndividuo(this.funcionIndividuo);
		aux.copiarIndividuo(ind);
		
		for(int i = 0; i < inserciones; i++){		
			int posInsertar = Math.abs(rnd.nextInt()) % n;	//calculamos la posicion en la que queremos insertar
			int posInd = Math.abs(rnd.nextInt()) % n;			//recogemos la posicion que queremos que sea insertado
			
			//guarda la posicion en el orden dado
			int sigInd = 0;
			for(int j = 0; j < n; j++){
				//si nos encontramos en la posicion de insertar insertamos
				//el elegido
				if(j == posInsertar){
					aux.getCromosoma()[j] = ind.getCromosoma()[posInd];
				}
				//si no seguimos con el que toca
				else {
					if (sigInd == posInd)	//si estamos en la posicion del elegido nos movemos
						sigInd++;
					aux.getCromosoma()[j] = ind.getCromosoma()[sigInd];
					sigInd++;					
				}
			}
		}
		ind = aux;
	}

}
