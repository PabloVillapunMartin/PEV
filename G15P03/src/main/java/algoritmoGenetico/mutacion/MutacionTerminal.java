package algoritmoGenetico.mutacion;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoArboreo;
import algoritmoGenetico.tablaMultiplexor.TablaMultiplexor;
import algoritmoGenetico.trees.NodeInput;

public class MutacionTerminal extends Mutacion {

	public MutacionTerminal(double probMutacion, FuncionIndividuo funcion) {
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
		NodeInput node = ind.getArbol().getRandomLeaf();
		int p = this.rnd.nextInt(TablaMultiplexor.getInstance().getNumEntradasA());
		node.setPos(p);
		
		return ind;
	}
}
