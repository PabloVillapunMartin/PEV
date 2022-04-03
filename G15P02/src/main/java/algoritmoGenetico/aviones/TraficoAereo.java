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
		
		float[][] s = {{1f,1.5f,2f}, {1f,1.5f,1.5f}, {1f,1f,1f}};
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
		this.tamPistas = 10;
		this.tamAviones = 35;
		
		infoAviones = new InfoAvion[this.tamAviones];
		
		infoAviones = new InfoAvion[this.tamAviones];
		this.infoAviones[0] = new InfoAvion(Tipo.W, "UA138");
		this.infoAviones[1] = new InfoAvion(Tipo.W, "AA129");	
		this.infoAviones[2] = new InfoAvion(Tipo.G, "UA532");
		this.infoAviones[3] = new InfoAvion(Tipo.W, "UA599");
		this.infoAviones[4] = new InfoAvion(Tipo.W, "NW358");	
		this.infoAviones[5] = new InfoAvion(Tipo.G, "AA309");
		this.infoAviones[6] = new InfoAvion(Tipo.W, "AA128");	
		this.infoAviones[7] = new InfoAvion(Tipo.G, "UA1482");		
		this.infoAviones[8] = new InfoAvion(Tipo.W, "NW357");	
		this.infoAviones[9] = new InfoAvion(Tipo.W, "UA2408");		
		this.infoAviones[10] = new InfoAvion(Tipo.P, "UA2987");	
		this.infoAviones[11] = new InfoAvion(Tipo.W, "UA805");		
		this.infoAviones[12] = new InfoAvion(Tipo.G, "AA205");	
		this.infoAviones[13] = new InfoAvion(Tipo.W, "UAJ205");		
		this.infoAviones[14] = new InfoAvion(Tipo.W, "UI142");		
		this.infoAviones[15] = new InfoAvion(Tipo.W, "BABI65");		
		this.infoAviones[16] = new InfoAvion(Tipo.G, "PABI68");		
		this.infoAviones[17] = new InfoAvion(Tipo.P, "MOV10");		
		this.infoAviones[18] = new InfoAvion(Tipo.P, "TEN1010");		
		this.infoAviones[19] = new InfoAvion(Tipo.W, "NY459");		
		this.infoAviones[20] = new InfoAvion(Tipo.G, "EC653");		
		this.infoAviones[21] = new InfoAvion(Tipo.G, "CON950");		
		this.infoAviones[22] = new InfoAvion(Tipo.W, "202122");		
		this.infoAviones[23] = new InfoAvion(Tipo.W, "SIM348");		
		this.infoAviones[24] = new InfoAvion(Tipo.P, "NDA00");				
		this.infoAviones[25] = new InfoAvion(Tipo.G, "PAT00");		
		this.infoAviones[26] = new InfoAvion(Tipo.W, "PATS01");		
		this.infoAviones[27] = new InfoAvion(Tipo.W, "MICHI965");				
		this.infoAviones[28] = new InfoAvion(Tipo.G, "BS165");		
		this.infoAviones[29] = new InfoAvion(Tipo.W, "AA252");		
		this.infoAviones[30] = new InfoAvion(Tipo.W, "AA365");		
		this.infoAviones[31] = new InfoAvion(Tipo.P, "AA147");	
		this.infoAviones[32] = new InfoAvion(Tipo.P, "AA789");	
		this.infoAviones[33] = new InfoAvion(Tipo.W, "VIN456");	
		this.infoAviones[34] = new InfoAvion(Tipo.G, "KEL321");	

		
		int [][] telo = {{11,15,6,6,9,7,15,6,6,9,7,9,6,7,8,4,6,8,14,15,18,9,9,6,12,11,10,17,16,20,14,6,17,14,12}, 
						{10,17,7,7,12,6,17,7,7,12,6,7,9,8,13,8,15,16,18,9,10,7,13,12,11,18,16,20,15,7,16,15,12,8,6},
						{9,19,8,8,15,5,19,8,8,15,5,5,8,6,9,7,9,6,7,8,4,6,8,14,15,18,9,9,6,12,11,10,17,16,20},
						{0,17,7,8,18,9,10,7,7,12,6,17,7,7,12,6,7,9,11,15,5,19,8,8,15,14,20,14,6,17,14,6,9,7,9},
						{15,6,6,9,7,9,6,7,8,4,6,8,14,15,18,3,12,11,18,10,17,7,7,12,6,19,8,8,15,11,15,14,20,14,16},
						{7,8,15,14,20,14,15,18,9,11,18,16,20,15,7,16,15,7,9,6,7,8,4,6,8,8,15,5,16,20,14,6,17,14,12},
						{8,9,11,18,16,20,15,7,16,15,7,9,6,7,8,4,6,9,9,6,12,11,10,17,16,5,19,8,8,15,5,5,8,6,9},
						{15,6,6,9,7,9,6,7,8,4,16,15,7,9,11,7,8,5,6,8,8,15,5,16,8,14,15,18,3,12,11,18,10,17,7},
						{11,6,5,9,7,9,6,7,14,7,16,15,7,9,6,7,8,8,4,6,8,14,15,18,3,12,11,18,13,20,15,7,16,15,12},
						{8,15,16,18,9,10,7,8,14,15,18,3,12,11,18,13,6,7,8,4,16,15,7,9,11,20,14,6,17,14,12,14,17,9,6,14}};
		this.tel = telo;
	}
	private void problema3() {
		this.tamPistas = 7;
		this.tamAviones = 27;
		
		infoAviones = new InfoAvion[this.tamAviones];
		this.infoAviones[0] = new InfoAvion(Tipo.W, "UA599");
		this.infoAviones[1] = new InfoAvion(Tipo.W, "AA129");	
		this.infoAviones[2] = new InfoAvion(Tipo.W, "202122");		
		this.infoAviones[3] = new InfoAvion(Tipo.W, "UA2408");		
		this.infoAviones[4] = new InfoAvion(Tipo.W, "NW358");	
		this.infoAviones[5] = new InfoAvion(Tipo.G, "AA309");
		this.infoAviones[6] = new InfoAvion(Tipo.W, "AA128");	
		this.infoAviones[7] = new InfoAvion(Tipo.W, "NY459");		
		this.infoAviones[8] = new InfoAvion(Tipo.W, "SIM348");		
		this.infoAviones[9] = new InfoAvion(Tipo.P, "UA2987");	
		this.infoAviones[10] = new InfoAvion(Tipo.P, "TEN1010");		
		this.infoAviones[11] = new InfoAvion(Tipo.W, "PATS01");		
		this.infoAviones[12] = new InfoAvion(Tipo.G, "AA205");	
		this.infoAviones[13] = new InfoAvion(Tipo.W, "UAJ205");		
		this.infoAviones[14] = new InfoAvion(Tipo.W, "NW357");	
		this.infoAviones[15] = new InfoAvion(Tipo.W, "UA138");
		this.infoAviones[16] = new InfoAvion(Tipo.W, "UI142");		
		this.infoAviones[17] = new InfoAvion(Tipo.G, "PABI68");		
		this.infoAviones[18] = new InfoAvion(Tipo.G, "PAT00");		
		this.infoAviones[19] = new InfoAvion(Tipo.W, "BABI65");		
		this.infoAviones[20] = new InfoAvion(Tipo.P, "MOV10");		
		this.infoAviones[21] = new InfoAvion(Tipo.G, "UA532");
		this.infoAviones[22] = new InfoAvion(Tipo.G, "EC653");		
		this.infoAviones[23] = new InfoAvion(Tipo.G, "CON950");		
		this.infoAviones[24] = new InfoAvion(Tipo.G, "UA1482");		
		this.infoAviones[25] = new InfoAvion(Tipo.P, "NDA00");				
		this.infoAviones[26] = new InfoAvion(Tipo.W, "UA805");		


		
		int [][] telo = {{11,15,6,6,9,7,15,6,6,9,7,9,6,7,8,4,6,8,14,15,18,9,9,6,12,11,9}, 
						{7,12,6,17,7,7,12,6,7,9,11,15,5,19,8,8,15,14,20,14,6,17,14,6,9,7,9},
						{8,4,6,8,14,15,18,3,12,11,19,10,17,7,7,12,6,19,8,8,15,11,15,14,20,14,16},
						{14,7,16,15,7,9,6,7,8,8,4,6,20,14,15,18,3,12,11,18,13,20,15,7,16,15,12},
						{9,11,18,16,20,15,15,16,15,7,9,6,7,8,4,6,8,8,15,5,16,20,14,6,17,14,12},
						{16,15,7,9,6,7,8,4,6,9,9,6,12,11,10,17,16,5,19,8,8,15,17,5,8,7,9},
						{14,15,18,12,12,13,18,13,6,7,8,4,16,15,7,9,11,20,14,6,17,14,12,14,17,9,6,14}};
		this.tel = telo;
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