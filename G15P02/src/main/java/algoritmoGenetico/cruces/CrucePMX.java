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
			hijo1.getCromosoma()[i] = progenitor2.getCromosoma()[i];
			hijo2.getCromosoma()[i] = progenitor1.getCromosoma()[i];
		}
		
		//Se especifican las X de progenitores que no generan conflicto en el hijo1
		homologo(progenitor1, hijo1.getCromosoma(),  corte1, corte2);
		
		//Se especifican las X de progenitores que no generan conflicto en el hijo2
		homologo(progenitor2, hijo2.getCromosoma(),  corte1, corte2);
		
		p1.copiarIndividuo(hijo1);
		p2.copiarIndividuo(hijo2);
	}

	/*
	 * Comprueba si el valor o se encuentra en el cromosoma entre corte1 y corte2
	 * Devuelve el indice del lugar donde se ha encontrado. En caso de no encontrarlo -1
	 * */
	private int contieneObjeto(Integer[] cromosoma, int o, int corte1, int corte2) {		
		int index = -1;
		for(int i = corte1; i < corte2; ++i) {
			if(cromosoma[i] == o) {
				return i;
			}
		}		
		return index;
	}
	
	/*
	 * Baja los números del progenitor al hijo en caso de poderse. Si no se puede va buscando el homólogo hasta
	 * conseguirlo
	 */
	private void homologo(IndividuoAvion progenitor, Integer[] cromosomaHijo, int corte1, int corte2){
		
		for(int i = 0; i < progenitor.getCromosoma().length; ++i) {
			if(i >= corte1 && i < corte2) continue;
			
			int check = contieneObjeto(cromosomaHijo, progenitor.getCromosoma()[i], corte1, corte2);
			int index = i;
			while(check != -1){
				index = check;
				check = contieneObjeto(cromosomaHijo, progenitor.getCromosoma()[index], corte1, corte2);
			}
		
			cromosomaHijo[i] = progenitor.getCromosoma()[index];			
		}
	}
}
