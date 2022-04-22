package algoritmoGenetico.mutacion;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoArboreo;
import algoritmoGenetico.tablaMultiplexor.TablaMultiplexor;
import algoritmoGenetico.trees.Node;
import algoritmoGenetico.trees.NodeInput;

public class MutacionContraccion extends Mutacion {

	public MutacionContraccion(double probMutacion, FuncionIndividuo funcion) {
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
		Node node =  ind.getArbol().getRandomBranch();
		Node rnd = ind.getArbol().getRandomLeaf();
		
		if(node.getParent() != null)
			node.getParent().setChild(node.getParentList(), rnd);
		else
			ind.getArbol().setRoot(rnd);
		
		return ind;
	}

}
