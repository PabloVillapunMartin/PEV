package algoritmoGenetico.aviones;

public class TraficoAereo {

	private static TraficoAereo traficoAereo = null;
	InfoAvion[] infoAviones;
	
	public static TraficoAereo getInstance(int problema) {
		if(traficoAereo == null) {
			traficoAereo = new TraficoAereo();
			traficoAereo.init(problema);
		}
		return traficoAereo;
	}
	
	public void init(int problema) {
		switch(problema) {
		case 0: problema1(); break;
		case 1: problema2(); break;
		case 2: problema3(); break;
		}		
	}
	
	private void problema1() {
		infoAviones = new InfoAvion[12];
	}
	private void problema2() {
		infoAviones = new InfoAvion[12];
		//TODO
	}
	private void problema3() {
		infoAviones = new InfoAvion[12];
		//TODO:
	}
	
	
	public InfoAvion getInfo(int numVuelo) {
		return this.infoAviones[numVuelo];
	}
}