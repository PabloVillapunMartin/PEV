package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;

import algoritmoGenetico.AlgoritmoGenetico.FuncionIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoArboreo;
import algoritmoGenetico.trees.Node;

public class CruceArboreo extends Cruce {

	public CruceArboreo(double probCruce2, FuncionIndividuo funcion) {
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
			if(!visitados[i] && restantes.size() > 1 && rnd <= this.probCruce){	
				visitados[i] = true;						//Lo visitamos
				int index = restantes.indexOf(i);
				restantes.remove(index);
				
				int indexRestante = buscarIndividuo(restantes);	//Buscamos otro padre
				int padre2 = restantes.get(indexRestante);
				
				visitados[padre2] = true;
				restantes.remove(indexRestante);

				CruceArboreo((IndividuoArboreo)individuos[i], (IndividuoArboreo)individuos[padre2]);				
			}
		}
		
		return individuos;
	}
	
	private void CruceArboreo(IndividuoArboreo ind1, IndividuoArboreo ind2){
		Node subarbol1 = ind1.getArbol().getSubtree();
		Node subarbol2 = ind2.getArbol().getSubtree();
		
		if(subarbol1.getParent() != null)
			subarbol1.getParent().setChild(subarbol1.getParentList(), subarbol2);
		else 
			ind1.getArbol().setRoot(subarbol2);
		if(subarbol2.getParent() != null)
			subarbol2.getParent().setChild(subarbol2.getParentList(), subarbol1);
		else
			ind2.getArbol().setRoot(subarbol1);
	}

}
