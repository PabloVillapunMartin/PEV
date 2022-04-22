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
		childs = new ArrayList<Node>();
	}
	public Node(Node node) {
		this.height = node.height;
		this.isLeaf = node.isLeaf;
		
		childs = new ArrayList<Node>();
		
		for(Node child: node.childs) {
			if(child.isLeaf)
				this.childs.add(new NodeInput((NodeInput)child));
			else {
				switch(((NodeFunction)child).type){
					case AND: 	this.childs.add(new NodeFunAND(child));	break;
					case OR:	this.childs.add(new NodeFunOR(child));	break;
					case IF:	this.childs.add(new NodeFunIF(child));	break;
					case NOT:	this.childs.add(new NodeFunNOT(child));	break;
				}
			}
		}
	}

	@Override
	public String toString() 
	{
		return null;
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
	
	public ArrayList<Node> getChildren(){
		return this.childs;
	}
}
