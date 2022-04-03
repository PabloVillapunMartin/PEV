package algoritmoGenetico.aviones;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GeneraTablaAvion {

	public GeneraTablaAvion(){
		
	}
	
	public JTable generaTabla(ArrayList<ArrayList<InfoPista>> pistas){
		
		//numero que guarda el valor maximo de aviones entre todas las pistas
		int max = buscaMax(pistas);
		
		//Creo los arrays donde guardare la info de la tabla
		//+2 para añadirle a la columna los títulos
		String [][] vuelos = new String[pistas.size()][max + 1];
		String [][] TLA = new String[pistas.size()][max + 1];
		String [][] name =new String[pistas.size()][max + 1];
		
		setData(pistas, vuelos, TLA, name);
			
		DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
               
        JTable tabla = new JTable(dtm);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setModel(dtm);

		for(int i = 0; i < pistas.size(); i++){
			dtm.addColumn("Pista " + (i+1),vuelos[i]);
			dtm.addColumn(" ",name[i]);
			dtm.addColumn(" ", TLA[i]);
		}
		
		return tabla;
	}
	
	public JScrollPane getScrollPane(JTable table){
		JScrollPane jScroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
		return jScroll;
	}
	
	private void setData(ArrayList<ArrayList<InfoPista>> pistas, String [][] vuelos, String [][] TLA, String [][] name){
		for(int i = 0; i < pistas.size(); i++){
			vuelos[i][0] = "vuelos";
			TLA[i][0] = "TLA";
			name[i][0] = "nombre";
			for(int j = 0; j < pistas.get(i).size(); j ++){
				vuelos[i][j + 1] = Integer.toString(pistas.get(i).get(j).vuelo);
				TLA[i][j + 1] = Float.toString(pistas.get(i).get(j).TLA);
				name[i][j + 1] = TraficoAereo.getInstance().getInfo(pistas.get(i).get(j).vuelo).getId();
			}
		}
		
	}
	
	private int buscaMax(ArrayList<ArrayList<InfoPista>> pistas){
		int max = 0;
		for(int i = 0; i < pistas.size(); i++)
			if(pistas.get(i).size() > max)
				max = pistas.get(i).size();
		return max;
	}
}
