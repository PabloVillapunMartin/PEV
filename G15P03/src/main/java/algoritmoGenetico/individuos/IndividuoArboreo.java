package algoritmoGenetico.individuos;

import algoritmoGenetico.trees.Tree;

public class IndividuoArboreo extends Individuo<Integer> {

	Tree _arbol;
	
	public IndividuoArboreo(int maxAltura) {
		this._arbol = new Tree(maxAltura);
	}
	
	public Tree getArbol() {
		return _arbol;
	}
	
	public int compareTo(Individuo o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getFitness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareValue(double fitness) {
		// TODO Auto-generated method stub
		return 0;
	}

}
