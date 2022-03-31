package algoritmoGenetico.cruces;

import java.util.Arrays;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFactory;

public class CruceCX extends Cruce {

	public CruceCX(double probCruce, FuncionIndividuo funcion) {
		super(probCruce, funcion);
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

				CruceCX(individuos[i], individuos[padre2]);				
			}
		}
		
		return individuos;
		
	}
	
	/*
	 * Cruza por ciclos los individuos dados, creando dos hijos,
	 * cruzandolos y asignandolos.
	 * */
	private void CruceCX(Individuo p1, Individuo p2) {
		Individuo hijo1 = IndividuoFactory.getIndividuo(funcion);
		Individuo hijo2 = IndividuoFactory.getIndividuo(funcion);
			
		//Asigno a -1 los valores de los hijos
		for(int i = 0; i < hijo1.getCromosoma().length; ++i) {
			hijo1.getCromosoma()[i] = null;
			hijo2.getCromosoma()[i] = null;
		}
		
		rellenaConCiclos(p1.getCromosoma(), p2.getCromosoma(), hijo1.getCromosoma());
		rellenaConCiclos(p2.getCromosoma(), p1.getCromosoma(), hijo2.getCromosoma());
		
		p1.copiarIndividuo(hijo1);
		p2.copiarIndividuo(hijo2);
	}
	
	/*
	 * Busca la posición donde se encuentra el valor dentro del progenitor
	 * */
	private int buscaPosicion(Object[] progenitor1, Object valor){
		for(int i = 0; i < progenitor1.length; i++){
			if(valor == progenitor1[i]) return i;
		}
		//este caso no se debería de dar nunca
		return -1;
	}
	
	/*
	 * Rellena utilizando la tecnica de los ciclos el cromosoma hijo con el progenitor1. Una vez el ciclo termina
	 * rellena lo sobrante con el progenitor2
	 * */
	private void rellenaConCiclos(Object[] progenitor1, Object[] progenitor2, Object[] cromosomaHijo){
		
		int index = 0;
		//Realizamos un ciclo completo hasta que se repita el valor
		do{
			cromosomaHijo[index] = progenitor1[index];				//asignamos el valor
			index = buscaPosicion(progenitor1, progenitor2[index]);	//buscamos la siguiente posición a asignar
		}while(cromosomaHijo[index] == null);
		
		//Rellenamos lo que queda con el otro progenitor
		for(int i = 0; i < progenitor1.length; i++){
			if(cromosomaHijo[i] == null) 
				cromosomaHijo[i] = progenitor2[i];
		}
	}
}
