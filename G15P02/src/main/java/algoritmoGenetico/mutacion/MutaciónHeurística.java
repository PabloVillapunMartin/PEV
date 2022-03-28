package algoritmoGenetico.mutacion;

import java.util.ArrayList;

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
		int inserciones = 1;
		int size = ind.getCromosoma().length;
		
		int [] posiciones = new int [n];
		for(int i = 0; i < n; i++)
			posiciones[i] = this.rnd.nextInt(size);
		
		int [] solucionActual = new int [n];
		int [] mejorSolucion = new int [n];
		guardaPermutacionRec(ind, posiciones, 0, solucionActual, mejorSolucion, ind.getValor());
		
		Individuo aux = IndividuoFactory.getIndividuo(this.funcionIndividuo);
		aux.copiarIndividuo(ind);
		//Asignamos al nuevo individuo el valor de la solución actual
		for(int i = 0; i < n; i++){
			aux.getCromosoma()[posiciones[i]] = ind.getCromosoma()[mejorSolucion[i]];
		}
		
		return aux;
	}
	
	private void guardaPermutacionRec(Individuo ind, int [] posiciones, int index, int [] solucionActual, int [] mejorSolucion, double mejorValor){
		//si hemos llegado al final de una permutación
		if(index == n){
			mejorValor = checkIndividuo(ind, posiciones, solucionActual, mejorSolucion, mejorValor);
			return;
		}
		//Si no hemos llegado al final de una permutacion seguimos añadiendo	
		for(int i = 0; i < n; i++){
			//Asignamos el valor
			solucionActual[index] = posiciones[i];
			//Si el valor asignado no esta dentro de la permutación rellenando
			if(esValido(solucionActual, posiciones[i], index))
				guardaPermutacionRec(ind, posiciones, index + 1, solucionActual, mejorSolucion, mejorValor);
		}
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
	 * @returm devuelve el mejorValor entre el actual y el que daría con la solución actual
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
