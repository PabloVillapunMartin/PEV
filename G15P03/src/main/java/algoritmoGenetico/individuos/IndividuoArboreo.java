package algoritmoGenetico.individuos;

import algoritmoGenetico.AlgoritmoGenetico.TipoArbolInicio;
import algoritmoGenetico.tablaMultiplexor.TablaMultiplexor;
import algoritmoGenetico.trees.Tree;

public class IndividuoArboreo extends Individuo<Integer> {

	Tree _arbol;
	
	public IndividuoArboreo(int maxAltura, TipoArbolInicio t) {
		this._arbol = new Tree(maxAltura, t);
	}
	
	public Tree getArbol() {
		return _arbol;
	}
	
	public int compareTo(Individuo o) {
		double valorMio = this.getValor();
		double valorOtro = o.getValor();
		
		if(valorMio - valorOtro > 0)	return -1;
		if(valorMio - valorOtro < 0)	return 1;
		else							return 0;
	}

	@Override
	public double getValor() {
		int aciertos = 0;
		for(int i = 0; i < TablaMultiplexor.getInstance().getNumFilas(); ++i) {
			byte[] valoresA = TablaMultiplexor.getInstance().getEntradaA(i)
				  ,valoresD = TablaMultiplexor.getInstance().getEntradaD(i);
			if(this._arbol.evalue(valoresA, valoresD) == TablaMultiplexor.getInstance().getSolucion(i))
				++aciertos;
		}
		return aciertos;
	}

	@Override
	public double getFitness() {
		return getValor();
	}

	
	@Override
	public String toString(){
		return this._arbol.toString();	
	}
	
	
	/*
	 * Copia los atributos de otro individuo a este objeto
	 */
	public void copiarIndividuo(Individuo other) {
		this._arbol = new Tree(((IndividuoArboreo)other).getArbol());
	}
}
