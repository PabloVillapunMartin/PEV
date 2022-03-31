package algoritmoGenetico.cruces;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFactory;

public class CruceOXOP extends Cruce {

	float numPosiciones = 0.3f;
	
	public CruceOXOP(double probCruce2, FuncionIndividuo funcion) {
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

				cruceOXOP(individuos[i], individuos[padre2]);				
			}
		}
		
		return individuos;
	}

	private void cruceOXOP(Individuo p1, Individuo p2) {
		Individuo hijo1 = IndividuoFactory.getIndividuo(funcion);
		Individuo hijo2 = IndividuoFactory.getIndividuo(funcion); 
		
		int[] posiciones = new int[(int)(numPosiciones * p1.getCromosoma().length)];
		
		Set<Integer> usados = new HashSet<Integer>();
		for(int i = 0; i < posiciones.length; ++i) {
			int pos = this.random.nextInt(p1.getCromosoma().length);
			while(usados.contains(pos))
				pos = (pos + 1) % p1.getCromosoma().length;
			posiciones[i] = pos;
			usados.add(pos);
		}
		
		completarIndividuo(hijo1, p1, p2, posiciones);
		completarIndividuo(hijo2, p2, p1, posiciones);
			
		p1.copiarIndividuo(hijo1);
		p2.copiarIndividuo(hijo2);
	}

	private void completarIndividuo(Individuo res, Individuo hijo1, Individuo hijo2, int[] posiciones) {
		int[] posicionesEnSegundo = new int[posiciones.length];
		
		for(int i = 0; i < posicionesEnSegundo.length; ++i) {
			posicionesEnSegundo[i] = buscaIndex(hijo1.getCromosoma()[posiciones[i]], hijo2.getCromosoma());
		}
		
		for(int i = 0; i < res.getCromosoma().length; ++i) {
			if(this.contieneIndice(posicionesEnSegundo, i)) res.getCromosoma()[i] = null;
			else res.getCromosoma()[i] = hijo2.getCromosoma()[i];
		}
		
		int pos = 0;
		for(int i = 0; i < res.getCromosoma().length; ++i) {
			if(res.getCromosoma()[i] != null) continue;
			res.getCromosoma()[i] = hijo1.getCromosoma()[posiciones[pos]];
			pos++;
		}
	}
	
	private int buscaIndex(Object index, Object[] valores) {
		for(int i = 0; i < valores.length; ++i) {
			if(index == valores[i]) {
				return i;
			}
		}
		
		return -1;
	}
	
	private boolean contieneIndice(int[] indices, int valor) {
		for(int i = 0; i < indices.length; ++i) {
			if(valor == indices[i]) return true;
		}
		return false;
	}
}
