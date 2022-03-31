package algoritmoGenetico.cruces;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFactory;

public class CruceOXPP extends Cruce {

	float numPosiciones = 0.3f;
	
	public CruceOXPP(double probCruce2, FuncionIndividuo funcion) {
		super(probCruce2, funcion);
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

				cruceOXPP(individuos[i], individuos[padre2]);				
			}
		}
		
		return individuos;
	}

	private void cruceOXPP(Individuo p1, Individuo p2) {		
		Individuo hijo1 = IndividuoFactory.getIndividuo(funcion);
		Individuo hijo2 = IndividuoFactory.getIndividuo(funcion); 
		
		int[] posiciones = new int[(int)(numPosiciones * p1.getCromosoma().length)];
		
		Set<Integer> usados = new HashSet<Integer>();
		for(int i = 0; i < posiciones.length; ++i) {
			int pos = this.random.nextInt(p1.getCromosoma().length);
			while(usados.contains(pos))
				pos = (pos + 1) % p1.getCromosoma().length;
			posiciones[i] = pos;
		}
		
		for(int i = 0; i < posiciones.length; ++i) {
			hijo1.getCromosoma()[posiciones[i]] = p2.getCromosoma()[posiciones[i]];
			hijo2.getCromosoma()[posiciones[i]] = p1.getCromosoma()[posiciones[i]];
		}
		
		bajarPadre(p1.getCromosoma(), hijo1.getCromosoma(), posiciones);
		bajarPadre(p2.getCromosoma(), hijo2.getCromosoma(), posiciones);
		
		p1.copiarIndividuo(hijo1);
		p2.copiarIndividuo(hijo2);
	}
	
	private void bajarPadre(Object[] progenitor, Object[] cromosomaHijo, int[] posiciones) {
		int indexPadre = 0;
		int indexHijo = 0;
		
		for(int i = 0; i < progenitor.length; ++i){
			
			if(!esIndiceValido(i, posiciones)) {
				indexPadre = (indexPadre + 1) % cromosomaHijo.length;
				indexHijo = (indexHijo + 1) % cromosomaHijo.length;
				continue;
			}
			if(contieneObjeto(cromosomaHijo, progenitor[indexPadre], posiciones))
				indexPadre = (indexPadre + 1) % cromosomaHijo.length;
			else {
				cromosomaHijo[indexHijo] = progenitor[indexPadre];
				indexPadre = (indexPadre + 1) % cromosomaHijo.length;
				indexHijo = (indexHijo + 1) % cromosomaHijo.length;
			}
					
		}
	}
	
	private boolean esIndiceValido(int index, int [] posiciones) {
		for(int i = 0; i < posiciones.length; ++i) {
			if(posiciones[i] == index) return false;
		}
		
		return true;
	}
	
	private boolean contieneObjeto(Object[] cromosoma, Object o, int[] posiciones) {
		for(int i = 0; i < posiciones.length; ++i) {
			if(cromosoma[posiciones[i]] == o) return true;
		}
		
		return false;
	}
}
