package algoritmoGenetico.mutacion;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoArboreo;
import algoritmoGenetico.trees.Node;

public class MutacionExpansion extends Mutacion {

	public MutacionExpansion(double probMutacion, FuncionIndividuo funcion) {
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
		Node node = ind.getArbol().getRandomLeaf();
		Node rnd = ind.getArbol().getRandomNode(node.getHeight() - 1);
		if(node.getParent() != null)
			node.getParent().setChild(node.getParentList(), rnd);
		else
			ind.getArbol().setRoot(rnd);
		
		ind.getArbol().bloatingCheck(rnd);
		
		return ind;
	}
}
