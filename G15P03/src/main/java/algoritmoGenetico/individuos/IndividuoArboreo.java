package algoritmoGenetico.individuos;

import java.util.Arrays;

import algoritmoGenetico.tablaMultiplexor.TablaMultiplexor;
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
		if(this.getValor() - o.getValor() > 0)
			return 1;
		if(this.getValor() - o.getValor() < 0)
			return -1;
		else return 0;
	}

	@Override
	public double getValor() {
		int aciertos = 0;
		for(int i = 0; i < TablaMultiplexor.getInstance().getTabla().length; ++i) {
			byte[] filaTabla = TablaMultiplexor.getInstance().getTabla()[i];
			byte[] valoresA, valoresD;
			valoresA = Arrays.copyOfRange(filaTabla, 0, TablaMultiplexor.getInstance().getNumEntradasA());
			valoresD = Arrays.copyOfRange(filaTabla, TablaMultiplexor.getInstance().getNumEntradasA(), filaTabla.length - 1);
			if(this._arbol.evalue(valoresA, valoresD) == filaTabla[filaTabla.length - 1]) {
				++aciertos;
			}
		}
		return aciertos;
	}

	@Override
	public double getFitness() {
		return getValor();
	}

	@Override
	public int compareValue(double fitness) {
		// TODO Auto-generated method stub
		return 0;
	}

}
