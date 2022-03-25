package algoritmoGenetico.aviones;

import algoritmoGenetico.aviones.InfoAvion.Tipo;

public class TraficoAereo {

	private static TraficoAereo traficoAereo = null;
	InfoAvion[] infoAviones;
	int [][] tel;
	float [][] SEP;
	
	int tamPistas;
	int tamAviones;
	
	public static TraficoAereo getInstance() {
		if(traficoAereo == null) {
			traficoAereo = new TraficoAereo();
		}
		return traficoAereo;
	}
	
	public void init(int problema) {
		
		float[][] s = {{1f,1.5f,2f}, {1f,1.5f,1.5f}, {1f,1f,2f}};
		this.SEP = s;
		switch(problema) {
			case 0: problema1(); break;
			case 1: problema2(); break;
			case 2: problema3(); break;
		}		
	}
	
	private void problema1() {
		this.tamPistas = 3;
		this.tamAviones = 12;
		
		infoAviones = new InfoAvion[this.tamAviones];
		this.infoAviones[0] = new InfoAvion(Tipo.W, "UA138");
		this.infoAviones[1] = new InfoAvion(Tipo.G, "UA532");
		this.infoAviones[2] = new InfoAvion(Tipo.W, "UA599");
		this.infoAviones[3] = new InfoAvion(Tipo.W, "NW358");	
		this.infoAviones[4] = new InfoAvion(Tipo.P, "UA2987");	
		this.infoAviones[5] = new InfoAvion(Tipo.W, "AA128");	
		this.infoAviones[6] = new InfoAvion(Tipo.G, "UA1482");		
		this.infoAviones[7] = new InfoAvion(Tipo.W, "NW357");	
		this.infoAviones[8] = new InfoAvion(Tipo.W, "AA129");	
		this.infoAviones[9] = new InfoAvion(Tipo.W, "UA2408");		
		this.infoAviones[10] = new InfoAvion(Tipo.W, "UA805");		
		this.infoAviones[11] = new InfoAvion(Tipo.G, "AA309");

		
		int [][] telo = {{11,15,6,6,9,7,15,6,6,9,7,9}, 
						{10,17,7,7,12,6,17,7,7,12,6,7},
						{9,19,8,8,15,5,19,8,8,15,5,5}};
		this.tel = telo;
	}
	private void problema2() {
		infoAviones = new InfoAvion[12];
		//TODO
	}
	private void problema3() {
		infoAviones = new InfoAvion[12];
		//TODO:
	}
	
	public int getNumPistas() {
		return this.tamPistas;
	}
	
	public int getNumAviones() {
		return this.tamAviones;
	}
	
	public InfoAvion getInfo(int numVuelo) {
		return this.infoAviones[numVuelo];
	}
	
	public int getTel(int pista, int avion) {
		return this.tel[pista][avion];
	}
	
	public float getSEP(int anterior, int siguiente) {
		return this.SEP[anterior][siguiente];
	}
}