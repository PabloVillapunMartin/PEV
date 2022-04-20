package algoritmoGenetico.tablaMultiplexor;

public class TablaMultiplexor {
	//Singleton
	private static TablaMultiplexor tablaMultiplexor = null;
	private TablaMultiplexor() {};
	
	private int _numEntradasA;
	private int _numEntradasD;
	private int _numCombinacionesA;
	private int _numCombinacionesD;
	private int _maxProfundidad;
	private byte[][] _tablaA;
	private byte[][] _tablaD;
	private byte[] _soluciones;
	
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
		
		_numCombinacionesA = (int) Math.pow(2, entradasA);
		_numCombinacionesD = (int) Math.pow(2, entradasD);
		int filasTabla = _numCombinacionesA * _numCombinacionesD;
		
		this._tablaA = new byte[_numCombinacionesA][entradasA];
		this._tablaD = new byte[_numCombinacionesD][entradasD];
		this._soluciones = new byte[filasTabla];
		
		//entradas D
		for(int entradaD = 0; entradaD < _numCombinacionesD; ++entradaD) 
		{
			byte[] entradaBin = intToBin(entradaD, entradasD);
			rellenaFila(this._tablaD[entradaD], entradaBin);
		}
		
		//entradas A
		for(int entradaA = 0; entradaA < _numCombinacionesA; ++entradaA) 
		{
			byte[] direccionBin = intToBin(entradaA, entradasA);
			rellenaFila(this._tablaA[entradaA], direccionBin);
		}
		
		//Soluciones tabla
		for(int i = 0; i < filasTabla; ++i) {
			int indexA = i / _numCombinacionesD;
			this._soluciones[i] = this._tablaD[i % _numCombinacionesD][indexA];
		}
	}
	
	public byte[] getEntradaA(int fila) {
		
		return this._tablaA[fila / this._numCombinacionesD];
	}
	
	public byte[] getEntradaD(int fila) {
		return this._tablaD[fila % this._numCombinacionesD];
	}
	
	public byte getSolucion(int fila) {
		return this._soluciones[fila];
	}
	
	public int getNumFilas() {
		return this._soluciones.length;
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
	
	private void rellenaFila(byte[] fila, byte[] entrada) {
		for(int i = 0; i < fila.length; ++i) {
			fila[i] = entrada[i];
		}
	}
}
