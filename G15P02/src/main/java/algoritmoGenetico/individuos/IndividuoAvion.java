package algoritmoGenetico.individuos;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import algoritmoGenetico.aviones.InfoAvion;
import algoritmoGenetico.aviones.InfoPista;
import algoritmoGenetico.aviones.TraficoAereo;

public class IndividuoAvion extends Individuo<Integer>{

	protected Random rand;
	int n;
	
	/*
	 * Constructora de clase
	 */
	public IndividuoAvion(int n) {
		this.n = n;
		inicializaValores();		

		boolean [] visitado = new boolean[n];
		Arrays.fill(visitado, false);
		for(int i = 0; i < this.cromosoma.length; i++)  {
			int j = Math.abs(this.rand.nextInt()) % n;
			while(visitado[j]) 
				j = (j + 1) % n;
			
			visitado[j] = true;
			this.cromosoma[i] = j;
		}
	}
	public int compareTo(Individuo o) {
		if(this.getValor() - o.getValor() > 0)
			return 1;
		if(this.getValor() - o.getValor() < 0)
			return -1;
		else return 0;
	}
	
	@Override
	public int compareValue(double o) {
		if(this.getValor() - o > 0)
			return 1;
		if(this.getValor() - o < 0)
			return -1;
		else return 0;
	}
	
	@Override
	public double getValor() {
		
		float valor = 0.0f;
		ArrayList<ArrayList<InfoPista>> pistas = new ArrayList<ArrayList<InfoPista>>();
		for(int i = 0; i < TraficoAereo.getInstance().getNumPistas(); i++) {
			pistas.add(new ArrayList<InfoPista>());
		}
		for(int avion = 0; avion < this.cromosoma.length; avion++)  {
			float minimoTla = Float.MAX_VALUE;	//El tla mínimo de la pista final
			float minimoTel =  Float.MAX_VALUE;	//El tel mínimo entre todas las pistas
			int indexPista = 0;				//Pista final a la que vaya el avion
			for(int pista = 0; pista < TraficoAereo.getInstance().getNumPistas(); pista++) {
				float tiempoLlegada = TraficoAereo.getInstance().getTel(pista, this.cromosoma[avion]);
				if(tiempoLlegada < minimoTel)
					minimoTel = tiempoLlegada;
				//Si la pista está vacía el tiempo de llegada sería el tel
				ArrayList<InfoPista> pistaActual = pistas.get(pista);
				if(!pistaActual.isEmpty()) {
					//Accedemos al último vuelo de la pista
					InfoPista anteriorAvion = pistaActual.get(pistaActual.size() - 1);
					float anteriorTLA = anteriorAvion.TLA;
					//Si es mayor o igual al ultimo vuelo le añadimos tiempo de separacion
					if(anteriorTLA >= tiempoLlegada) {
						int infoAnterior = TraficoAereo.getInstance().getInfo(anteriorAvion.vuelo).getTipo().ordinal();
						int infoActual = TraficoAereo.getInstance().getInfo(this.cromosoma[avion]).getTipo().ordinal();
						tiempoLlegada = anteriorTLA + TraficoAereo.getInstance().getSEP(infoAnterior, infoActual);
					}					
				}
				//Comprobamos si el tiempo es menor que en el resto de las pistas
				if(tiempoLlegada < minimoTla) {
					minimoTla = tiempoLlegada;
					indexPista = pista;
				}
			}
			//Asignamos el vuelo a la mejor pista encontrada
			pistas.get(indexPista).add(new InfoPista(this.cromosoma[avion], minimoTla));
			valor += Math.pow(minimoTla - minimoTel,2);
		}
		
		return valor;
	}
	@Override
	public double getFitness() {
		return -getValor();
	}
	
	/*
	 * Inicializa los valores para individuo 1
	 */
	protected void inicializaValores(){
		this.tamGenes = new int[1];
		this.tamGenes[0] = n;
		this.cromosoma = new Integer[n];	
		this.rand = new Random();
	}
	 
}
