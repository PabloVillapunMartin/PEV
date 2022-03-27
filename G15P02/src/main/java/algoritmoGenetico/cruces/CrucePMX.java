package algoritmoGenetico.cruces;

import java.util.Arrays;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoAvion;
import algoritmoGenetico.individuos.IndividuoFactory;

public class CrucePMX extends Cruce {

	public CrucePMX(double probCruce2, FuncionIndividuo funcion) {
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

				CrucePMX(individuos[i], individuos[padre2]);				
			}
		}
		
		return individuos;
	}
	
	private void CrucePMX(Individuo p1, Individuo p2) {
		//Copia inicial de los cromosomas de los progenitores
		IndividuoAvion progenitor1 = (IndividuoAvion)p1;
		IndividuoAvion progenitor2 = (IndividuoAvion)p2;
		
		IndividuoAvion hijo1 = (IndividuoAvion)IndividuoFactory.getIndividuo(funcion);
		IndividuoAvion hijo2 = (IndividuoAvion)IndividuoFactory.getIndividuo(funcion);
		
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
			int aux = progenitor1.getCromosoma()[i];
			hijo1.getCromosoma()[i] = progenitor2.getCromosoma()[i];
			hijo2.getCromosoma()[i] = aux;
		}
		
		//Se especifican las X de progenitores que no generan conflicto en el hijo1
		for(int i = 0; i < progenitor1.getCromosoma().length; ++i) {
			if(i >= corte1 || i < corte2) continue;
			
			if(!ContieneObjeto(hijo1.getCromosoma(), progenitor1.getCromosoma()[i], corte1, corte2)) {
				hijo1.getCromosoma()[i] = progenitor1.getCromosoma()[i];
			}
			else {
				hijo1.getCromosoma()[i] = progenitor2.getCromosoma()[i];
			}
		}
		
		//Se especifican las X de progenitores que no generan conflicto en el hijo2
		for(int i = 0; i < progenitor2.getCromosoma().length; ++i) {
			if(i >= corte1 || i < corte2) continue;
					
			if(!ContieneObjeto(hijo2.getCromosoma(), progenitor2.getCromosoma()[i], corte1, corte2)) {
				hijo2.getCromosoma()[i] = progenitor2.getCromosoma()[i];
			}
			else {
				hijo2.getCromosoma()[i] = progenitor1.getCromosoma()[i];
			}
		}
		
		p1 = hijo1;
		p2 = hijo2;
	}

	private boolean ContieneObjeto(Integer[] cromosoma, Integer o, int corte1, int corte2) {		
		for(int i = corte1; i < corte2; ++i) {
			if(cromosoma[i] == o) return true;
		}
		
		return false;
	}
}
