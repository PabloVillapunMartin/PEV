package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFactory;

public class CruceCO extends Cruce  {

	public CruceCO(double probCruce2, FuncionIndividuo funcion) {
		super(probCruce2, funcion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Individuo[] cruzar(Individuo[] individuos) {
		int n = individuos.length;
		//Guarda si el individuo ha sido elegido para cruzar o no
		Boolean[] visitados = new Boolean[n];		
		//Inializamos todos los individuos a false
		Arrays.fill(visitados, false);
		
		//En caso de ser números impares, eliminamos el último individuo
		if(n%2 != 0) 
			n--;	
		for(int i = 0; i< n; ++i) {
			float rnd = random.nextFloat();
			
			//Si el individuo no ha sido visitado y se selecciona para cruzar
			if(!visitados[i] && rnd <= this.probCruce){	
				visitados[i] = true;						//Lo visitamos
				int padre2 = buscarIndividuo(visitados, n);	//Buscamos otro padre

				CruceCO(individuos[i], individuos[padre2]);				
			}
		}
		
		return individuos;
	}
	
	private void CruceCO(Individuo p1, Individuo p2) {
	
		Individuo hijo1 = IndividuoFactory.getIndividuo(funcion);
		Individuo hijo2 = IndividuoFactory.getIndividuo(funcion);
		
		//Serializamos los valores
		int[] hijo1Serializado = serializaIndividuo(hijo1);
		int[] hijo2Serializado = serializaIndividuo(hijo2);
		
		//Realizamos monopunto
		//Hallamos el punto de corte de forma aleatoria
		int puntoCorte = this.random.nextInt(hijo1Serializado.length);
		//Intercambiamos los valores de los hijos
		for(int i = 0; i < puntoCorte; i++){
			int aux = hijo1Serializado[i];
			hijo1Serializado[i] =	hijo2Serializado[i];
			hijo2Serializado[i] = aux;
		}
		//Deserializamos los individuos
		deserializaIndividuo(hijo1, hijo1Serializado);
		deserializaIndividuo(hijo2, hijo2Serializado);
		
		p1.copiarIndividuo(hijo1);
		p2.copiarIndividuo(hijo2);
	}
	
	
	/*
	 * Serializa un individuo en codificacion entera usando una lista ordenada.
	 * Se saca cada valor de la lista ordenada guardandose el índice del valor que
	 * servirá para la codificación del individuo.
	 * */
	private int[] serializaIndividuo(Individuo ind){
		int n = ind.getCromosoma().length;
		ArrayList<Integer> lista = rellenaLista(n);
		
		int [] individuoSerializado = new int[n];
		for(int i = 0; i < n; i++){
			//Obtenemos el indivce al que corresponde
			int index =	lista.indexOf(ind.getCromosoma()[i]);
			//Guardamos el indice(serializando el individuo)
			individuoSerializado[i] = index;
			//Quitamos el valor de la lista
			lista.remove(index);
		}
		
		return individuoSerializado;
	}
	/*
	 * Deserializa un individuo dada la codificacion de un array de enteros usando una 
	 * lista ordenada. Se recogen los valores de indices de la serialización y se asignan al
	 * individuo
	 * */
	private void deserializaIndividuo(Individuo ind, int[] serializacion){
		int n = ind.getCromosoma().length;
		ArrayList<Integer> lista = rellenaLista(n);
		
		int [] individuoSerializado = new int[n];
		for(int i = 0; i < n; i++){
			ind.getCromosoma()[i] = lista.get(serializacion[i]);
			lista.remove(serializacion[i]);
		}
	}
	
	private ArrayList<Integer> rellenaLista(int size){
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for(int i = 0; i < size; i++){
			lista.add(i);
		}
		return lista;
	}
	
	

}
