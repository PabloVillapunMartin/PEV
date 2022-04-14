package algoritmoGenetico.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import algoritmoGenetico.trees.NodeFunction.FunctionType;

public class Tree {
	Node root;
	Random rnd;

	int maxHeight;
	
	/*
	 * Constructora arbol hoja
	 * */
	public Tree(){
		this.root = new NodeInput(0);
	}
	
	/*
	 * Constructora arbol
	 * */
	public Tree(int maxHeight){
		this.rnd = new Random();
		this.maxHeight = maxHeight;
		this.root = getRandomFun(0);
	}
	
	//--------------GETTERS & SETTERS--------------
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
	//----------------------------------------------
	
	/*
	 * Devuelve un nodo aleatorio dada la altura por la que se est� construyendo
	 * ese nodo
	 * */
	public Node getRandomNode(int height){
		if(height + 1 == this.maxHeight)
			return new NodeInput(height + 1);
		else
			return getRandomFun(height + 1);
	}
	
	/*
	 * Crea un nodo funcion aleatorio dada la altura por la que se est� construyendo
	 * */
	public NodeFunction getRandomFun(int height){
		FunctionType type = FunctionType.values()[this.rnd.nextInt(FunctionType.MAX_VALUES.ordinal())];
		
		NodeFunction node = null;
		switch(type){
		case AND: 	node = new NodeFunAND(height, getRandomNode(height), getRandomNode(height));
		case OR: 	node = new NodeFunOR(height, getRandomNode(height), getRandomNode(height));
		case NOT:	node = new NodeFunNOT(height, getRandomNode(height));
		case IF:	node = new NodeFunIF(height, getRandomNode(height), getRandomNode(height), getRandomNode(height));
		}
		return node;
	}
	
	/*
	 * Busca la primera hoja del arbol y la devuelve
	 * */
	public Node searchFirstLeaf(){
		Node node = null;
		//Creamos una cola para hacer una busqueda en anchura
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(this.root);
		while(node != null){
			Node current = queue.remove();
			//Si es una hoja la asignamos
			if(current.isLeaf())
				node = current;
			else {
				//Recogemos los hijos de la funcion y los a�adimos a la cola
				ArrayList<Node> list = ((NodeFunction)current).getChildren();
				for(Node n: list)
					queue.add(n);
			}
		}
		
		return node;
	}
	/*
	 * Evalua el arbol dados unos par�mtros de entrada
	 * */
	public int evalue(int[] A, int[] D){
		return this.root.evalue(A, D);
	}
}
