package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFactory;

public class CruceOX extends Cruce {

	public CruceOX(double probCruce, FuncionIndividuo funcion) {
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
		
		ArrayList<Integer> restantes = new ArrayList<Integer>();
		for(int i = 0; i < individuos.length; ++i) {
			restantes.add(i);
		}
		
		//En caso de ser números impares, eliminamos el último individuo
		if(n%2 != 0) 
			n--;	
		for(int i = 0; i< n; ++i) {
			float rnd = random.nextFloat();
			
			//Si el individuo no ha sido visitado y se selecciona para cruzar
			if(!visitados[i] && rnd <= this.probCruce){	
				visitados[i] = true;						//Lo visitamos
				int index = restantes.indexOf(i);
				restantes.remove(index);
				
				int indexRestante = buscarIndividuo(restantes);	//Buscamos otro padre
				int padre2 = restantes.get(indexRestante);
				
				visitados[padre2] = true;
				restantes.remove(indexRestante);

				cruceOX(individuos[i], individuos[padre2]);				
			}
		}
		
		return individuos;
	}
	
	/*
	 * Crea dos hijos dados los progenitores intercambiado una parte hallada con dos cortes
	 * y probando a bajar conservando el orden relativo y omitiendo las que ya estén presentes 
	 * */
	private void cruceOX(Individuo p1, Individuo p2) {

		Individuo hijo1 = IndividuoFactory.getIndividuo(funcion);
		Individuo hijo2 = IndividuoFactory.getIndividuo(funcion);
		
		//Puntos de corte
		int corte1 = this.random.nextInt(p1.getCromosoma().length);
		int corte2 = this.random.nextInt(p1.getCromosoma().length);
		
		//Verificar que corte1 sea mayor que corte2
		if(corte1 > corte2) {
			int aux = corte1;
			corte1 = corte2;
			corte2 = aux;
		}
		
		//Intercambio de valores entre los puntos de corte
		for(int i = corte1; i < corte2; ++i) {
			hijo1.getCromosoma()[i] = p2.getCromosoma()[i];
			hijo2.getCromosoma()[i] = p1.getCromosoma()[i];
		}
		
		bajarProgenitor(p1, hijo1.getCromosoma(), corte1, corte2);
		bajarProgenitor(p2, hijo2.getCromosoma(), corte1, corte2);
		
		p1.copiarIndividuo(hijo1);
		p2.copiarIndividuo(hijo2);
	}
	
	/*
	 * Comprueba si el valor o se encuentra en el cromosoma entre corte1 y corte2
	 * Devuelve el indice del lugar donde se ha encontrado. En caso de no encontrarlo -1
	 * */
	private boolean contieneObjeto(Object[] cromosoma, Object o, int corte1, int corte2) {		
		for(int i = corte1; i < corte2; ++i) {
			if(cromosoma[i] == o) {
				return true;
			}
		}		
		return false;
	}
	
	/*
	 * Para cada progenitor se parte de uno de los puntos de corte y se copian las
	 * ciudades del otro progenitor conservando el orden relativo y omitiendo las que ya
	 * estén presentes
	 * */
	private void bajarProgenitor(Individuo progenitor, Object[] cromosomaHijo, int corte1, int corte2) {
		int indexPadre = corte2;
		int indexHijo = corte2;
		
		while(indexHijo != corte1) {
			if(contieneObjeto(cromosomaHijo, progenitor.getCromosoma()[indexPadre], corte1, corte2))
				indexPadre = (indexPadre + 1) % cromosomaHijo.length;
			else {
				cromosomaHijo[indexHijo] = progenitor.getCromosoma()[indexPadre];
				indexPadre = (indexPadre + 1) % cromosomaHijo.length;
				indexHijo = (indexHijo + 1) % cromosomaHijo.length;
			}
					
		}
	}
	
	

}
