package algoritmoGenetico.trees;

import java.util.Random;

import algoritmoGenetico.trees.NodeFunction.FunctionType;

public class Node {
	
	protected int height;
	protected boolean isLeaf;
	
	public Node(int height, boolean isLeaf){
		this.height = height;
		this.isLeaf = isLeaf;
	}
	
	/*
	 * Evalua el nodo devolviendo 1 o 0
	 * */
	public int evalue(int []A, int []D)
	{
		throw new RuntimeException("Se est� llamando al evaluar deNode");
	};	
	
	public int getHeight(){
		return height;
	}
	
	public boolean isLeaf(){
		return this.isLeaf;
	}
}
