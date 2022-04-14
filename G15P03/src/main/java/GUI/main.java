package GUI;

import algoritmoGenetico.tablaMultiplexor.TablaMultiplexor;

public class main {
	public static void main(String[] args) {
		TablaMultiplexor tabla = TablaMultiplexor.getInstance();
		tabla.init(2, 4);
	}
}
