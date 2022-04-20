package algoritmoGenetico.trees;

import java.util.ArrayList;

public class Node {
	
	protected int height;
	protected boolean isLeaf;
	protected ArrayList<Node> childs;
	
	protected Node parent;	//padre
	int parentListPos;		//posicion a la pertenece dentro de la lista del padre
	
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
	
	public Node getParent() {
		return this.parent;
	}
	
	public int getParentList() {
		return this.parentListPos;
	}
	
	public void setParent(Node node) {
		this.parent = node;
	}
	
	public void setParentListPos(int p) {
		this.parentListPos = p;
	}
	
	public void setChild(int pos, Node n) {
		this.childs.set(pos, n);
	}
}
