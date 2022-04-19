package algoritmoGenetico.tablaMultiplexor;

public class TablaMultiplexor {
	//Singleton
	private static TablaMultiplexor tablaMultiplexor = null;
	private TablaMultiplexor() {};
	
	private int _numEntradasA;
	private int _numEntradasD;
	private int _maxProfundidad;
	private byte[][] _tabla;
	
	public static TablaMultiplexor getInstance() {
		if(tablaMultiplexor == null) {
			tablaMultiplexor = new TablaMultiplexor();
		}
		return tablaMultiplexor;
	}
	
	public void init(int entradasA, int entradasD, int maxProfundidad) {
		this._maxProfundidad = maxProfundidad;
		this._numEntradasA = entradasA;
		this._numEntradasD = entradasD;
		
		int combinacionesEntradasA = (int) Math.pow(2, entradasA);
		int combinacionesEntradasD = (int) Math.pow(2, entradasD);
		int filasTabla = combinacionesEntradasA * combinacionesEntradasD;
		
		this._tabla = new byte[filasTabla][entradasA + entradasD + 1];
		
		//entradas A
		for(int entradaA = 0; entradaA < combinacionesEntradasA; ++entradaA) 
		{
			byte[] direccionBin = intToBin(entradaA, entradasA);
			//entradas D
			for(int entradaD = 0; entradaD < combinacionesEntradasD; ++entradaD) 
			{
				byte[] entradaBin = intToBin(entradaD, entradasD);
				byte[] filaTabla = this._tabla[entradaA * combinacionesEntradasD + entradaD];

				rellenaFila(filaTabla, direccionBin, entradaBin);
				filaTabla[entradasA + entradasD] = filaTabla[entradasA + entradaA];
			}
		}
	}
	
	public byte[][] getTabla(){
		return this._tabla;
	}
	
	public int getNumEntradasA() {
		return this._numEntradasA;
	}
	
	public int getNumEntradasD() {
		return this._numEntradasD;
	}
	
	public int getMaxProfundidad() {
		return this._maxProfundidad;
	}
	
	private byte[] intToBin(int n, int numbits) {
		byte[] resultado = new byte[numbits];
		int index = resultado.length - 1;
		
		while (index >= 0) {
			if(n % 2 == 0) resultado[index] = 0; 
			else resultado[index] = 1;
			n = n / 2;
			--index;
		}
		
		return resultado;
	}
	
	private void rellenaFila(byte[] fila, byte[] entradaA, byte[] entradaD) {
		for(int i = 0; i < entradaA.length; ++i) {
			fila[i] = entradaA[i];
		}
		
		for(int i = 0; i < entradaD.length; ++i) {
			fila[i + entradaA.length] = entradaD[i];
		}
	}
}
