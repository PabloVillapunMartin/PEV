package algoritmoGenetico.mutacion;

import java.util.HashSet;
import java.util.Set;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFactory;

public class MutaciónHeurística extends Mutacion {

	int n = 3;
	
	public MutaciónHeurística(double probMutacion, FuncionIndividuo funcion) {
		super(probMutacion, funcion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Individuo[] mutar(Individuo[] individuos) {
		int size  = individuos.length;

		for(int i = 0; i < size; i++){
			if(this.rnd.nextFloat() < this.probMutacion)
				individuos[i] = mutarIndividuo(individuos[i]);
		}
		return individuos;
	}
	
	private Individuo mutarIndividuo(Individuo ind){
		int size = ind.getCromosoma().length;
		
		//Se escogen n posiciones diferentes a permutar
		Set<Integer> usados = new HashSet<Integer>();
		int [] posiciones = new int [n];
		for(int i = 0; i < n; ++i) {
			int pos = this.rnd.nextInt(size);
			while(usados.contains(pos))
				pos = (pos + 1) % size;
			posiciones[i] = pos;
		}
		
		int [] solucionActual = new int [n];
		int [] mejorSolucion = new int [n];
		for(int i = 0; i < n; ++i) {
			mejorSolucion[i] = posiciones[i];
		}
		guardaPermutacionRec(ind, posiciones, 0, solucionActual, mejorSolucion, ind.getValor());
		
		Individuo aux = IndividuoFactory.getIndividuo(this.funcionIndividuo);
		aux.copiarIndividuo(ind);
		
		//Asignamos al nuevo individuo el valor de la solución actual
		for(int i = 0; i < n; i++){
			aux.getCromosoma()[posiciones[i]] = ind.getCromosoma()[mejorSolucion[i]];
		}
		
		return aux;
	}
	
	private double guardaPermutacionRec(Individuo ind, int [] posiciones, int index, int [] solucionActual, int [] mejorSolucion, double mejorValor){
		//si hemos llegado al final de una permutación
		if(index == n){
			return checkIndividuo(ind, posiciones, solucionActual, mejorSolucion, mejorValor);
		}
		//Si no hemos llegado al final de una permutacion seguimos añadiendo	
		for(int i = 0; i < n; i++){
			//Asignamos el valor
			solucionActual[index] = posiciones[i];
			//Si el valor asignado no esta dentro de la permutación rellenando
			if(esValido(solucionActual, posiciones[i], index))
				mejorValor = guardaPermutacionRec(ind, posiciones, index + 1, solucionActual, mejorSolucion, mejorValor);
		}
		
		return mejorValor;
	}
	
	/*
	 * Comprueba si el valor añadido se encuentra ya en la solución. De ser así 
	 * se devuelve false. En caso contrario true
	 * */
	private boolean esValido(int [] solucionActual, int valor, int index){
		for(int i = 0; i < index; i++){
			if(solucionActual[i] == valor)
				return false;
		}
		return true;
	}
	
	/*
	 * Comprueba si dada la solución actual el fitness del individuo ha mejorado o no
	 * En caso de haber mejorado asigna a mejorSolucion los valores de solucionActual
	 * @return devuelve el mejorValor entre el actual y el que daría con la solución actual
	 * */
	private double checkIndividuo(Individuo ind, int [] posiciones, int [] solucionActual, int [] mejorSolucion, double mejorValor){
		Individuo aux = IndividuoFactory.getIndividuo(this.funcionIndividuo);
		aux.copiarIndividuo(ind);
		//Asignamos al nuevo individuo el valor de la solución actual
		for(int i = 0; i < n; i++){
			aux.getCromosoma()[posiciones[i]] = ind.getCromosoma()[solucionActual[i]];
		}
		//Comprobamos si su fitness ha mejorado
		double value = aux.getValor();
		if(aux.compareValue(mejorValor) <= 0) {
			mejorValor = value;
			//Si ha mejorado asignamos valores al anterior mejor
			for(int i = 0; i < n; i++){
				mejorSolucion[i] = solucionActual[i];
			}
		}
		
		return mejorValor;
	}
}
