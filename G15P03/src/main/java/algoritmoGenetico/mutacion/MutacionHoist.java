package algoritmoGenetico.mutacion;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoArboreo;
import algoritmoGenetico.trees.Node;

public class MutacionHoist  extends Mutacion {

	public MutacionHoist(double probMutacion, FuncionIndividuo funcion) {
		super(probMutacion, funcion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Individuo[] mutar(Individuo[] individuos) {	
		int size  = individuos.length;

		for(int i = 0; i < size; i++){
			if(this.rnd.nextFloat() < this.probMutacion)
				individuos[i] = mutarIndividuo((IndividuoArboreo)individuos[i]);
		}
		return individuos;
	}
	
	private IndividuoArboreo mutarIndividuo(IndividuoArboreo ind){
		Node root = ind.getArbol().getRoot();
		Node node = ind.getArbol().getSubtree();
		
		root = node;
		
		return ind;
	}
}
