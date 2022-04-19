package algoritmoGenetico.trees;

import java.util.ArrayList;

public class Node {
	
	protected int height;
	protected boolean isLeaf;
	protected ArrayList<Node> childs;
	
	public Node(int height, boolean isLeaf){
		this.height = height;
		this.isLeaf = isLeaf;
	}
	
	/*
	 * Evalua el nodo devolviendo 1 o 0
	 * */
	public int evalue(byte []A, byte []D)
	{
		throw new RuntimeException("Se está llamando al evaluar deNode");
	};	
	
	public int getHeight(){
		return height;
	}
	
	public boolean isLeaf(){
		return this.isLeaf;
	}
}
