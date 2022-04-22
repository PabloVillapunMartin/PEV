package algoritmoGenetico.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import algoritmoGenetico.AlgoritmoGenetico.TipoArbolInicio;
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
	public Tree(int maxHeight, TipoArbolInicio tipo){
		this.rnd = new Random();
		this.maxHeight = maxHeight;
			
		switch(tipo) {
			case grow:  growInitialization();	break;
			case full:	fullInitialization(0);	break;
		}
		setParents(this.root);
	}
	
	/*
	 * Constructora por copia
	 * */
	public Tree(Tree other){
		this.maxHeight = other.maxHeight;
		this.rnd = new Random();

		switch(((NodeFunction)other.root).type){
			case AND: 	new NodeFunAND(other.root);	break;
			case OR:	new NodeFunOR(other.root);	break;
			case IF:	new NodeFunIF(other.root);	break;
			case NOT:	new NodeFunIF(other.root);	break;
		}
		
		setParents(this.root);
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
	 * Evalua el arbol dados unos parámtros de entrada
	 * */
	public int evalue(byte[] A, byte[] D){
		return this.root.evalue(A, D);
	}
	
	//----------INICIALIZAR ARBOL------------------
	/*
	 * Devuelve un nodo aleatorio dada la altura por la que se está construyendo
	 * ese nodo
	 * */
	public Node getRandomNode(int height){
		//TODO quitar porbabilidad
		if(height + 1 == this.maxHeight || rnd.nextFloat() < 0.3f)
			return new NodeInput(height + 1);
		else
			return getRandomFun(height + 1);
	}
	/*
	 * Crea un nodo funcion aleatorio dada la altura por la que se está construyendo
	 * */
	public NodeFunction getRandomFun(int height){
		int r = this.rnd.nextInt(FunctionType.MAX_VALUES.ordinal());
		FunctionType type = FunctionType.values()[r];
		
		switch(type){
		case AND: 	return new NodeFunAND(height, getRandomNode(height), getRandomNode(height));
		case OR: 	return new NodeFunOR(height, getRandomNode(height), getRandomNode(height));
		case NOT:	return new NodeFunNOT(height, getRandomNode(height));
		case IF:	return new NodeFunIF(height, getRandomNode(height), getRandomNode(height), getRandomNode(height));
		default: 	return new NodeFunAND(height, getRandomNode(height), getRandomNode(height));
		}
	}
	
	/*
	 * Vamos tomando nodos del conjunto de funciones hasta llegar a una máxima
	 * profundidad del árbol definida previamente
	 * Una vez llegados a la profundidad máxima los símbolos sólo se toman del
	 * conjunto de símbolos terminales
	 * */
	public Node fullInitialization(int height) {
		if(height < this.maxHeight) {
			int r = this.rnd.nextInt(FunctionType.MAX_VALUES.ordinal());
			FunctionType type = FunctionType.values()[r];
			
			switch(type){
			case AND: 	return new NodeFunAND(height, fullInitialization(height), fullInitialization(height));
			case OR: 	return new NodeFunOR(height, fullInitialization(height), fullInitialization(height));
			case NOT:	return new NodeFunNOT(height, fullInitialization(height));
			case IF:	return new NodeFunIF(height, fullInitialization(height), fullInitialization(height), getRandomNode(height));
			default: 	return new NodeFunAND(height, fullInitialization(height), fullInitialization(height));
			}
		}
		else
			return new NodeInput(height + 1); 
	}
	
	/*
	 * Vamos tomando nodos del conjunto completo (funciones y terminales) hasta
	 * llegar al límite de profundidad especificado previamente
	 * Una vez llegados a la profundidad máxima este método de inicialización se
	 * comporta igual que el método de inicialización completa
	 * */
	private void growInitialization() {
		this.root = getRandomFun(0);
	}
	
	//-----------------COGER RAMAS U HOJAS--------------------------------------
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
				//Recogemos los hijos de la funcion y los añadimos a la cola
				ArrayList<Node> list = ((NodeFunction)current).getChildren();
				for(Node n: list)
					queue.add(n);
			}
		}
		
		return node;
	}
	
	/*
	 * Busca una hoja aleatoriamente y la devuelve
	 * */
	public Node getRandomLeaf(){
		Node node = null;
		//Creamos una cola para hacer una busqueda en anchura
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(this.root);
		while(node != null){
			Node current = queue.remove();
			//Si es una hoja la asignamos
			if(current.isLeaf() && this.rnd.nextFloat() < 0.5f)
				node = current;
			else {
				//Recogemos los hijos de la funcion y los añadimos a la cola
				ArrayList<Node> list = ((NodeFunction)current).getChildren();
				for(Node n: list)
					queue.add(n);
			}
		}
		
		return node;
	}
	
	/*
	 * Busca una hoja aleatoriamente y la devuelve
	 * */
	public Node getRandomBranch(){
		Node node = null;
		//Creamos una cola para hacer una busqueda en anchura
		Queue<Node> queue = new LinkedList<Node>();
		
		ArrayList<Node> list = ((NodeFunction)this.root).getChildren();
		for(Node n: list)
			queue.add(n);
		
		while(node != null){
			Node current = queue.remove();
			//Si no es una hoja la asignamos
			if(!current.isLeaf() && this.rnd.nextFloat() < 0.5f)
				node = current;
			else {
				//Recogemos los hijos de la funcion y los añadimos a la cola
				list = ((NodeFunction)current).getChildren();
				for(Node n: list)
					queue.add(n);
			}
		}
		
		return node;
	}
	
	/*
	 * Devuelve un subarbol aleatorio
	 * */
	public Node getSubtree(){
		Node node = null;
		//Creamos una cola para hacer una busqueda en anchura
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(this.root);
		while(node != null){
			Node current = queue.remove();
			//Si la proabilidad surje devuelve el actual, sino alguno de los hijos
			if(this.rnd.nextFloat() < 0.4)
				return current;
			else {
				//Recogemos los hijos de la funcion y los añadimos a la cola
				ArrayList<Node> list = ((NodeFunction)current).getChildren();
				for(Node n: list)
					queue.add(n);
			}
		}
		
		return node;
	}
	
	//-----------------------------------------------------------------------------------
	/*
	 * Devuelve la informacion del arbol en cadena de string
	 * */
	public String toString(Node node){
		String s = node.toString();
		for(Node n: node.childs) {
			s += toString(n);
		}
		//TODO: hacer tostring de los nodos
		return s;
	}
	
	/*
	 * Busca una hoja aleatoriamente y la devuelve
	 * */
	private void setParents(Node node){
		int i = 0;
		for(Node n: node.childs) {
			n.setParent(node);
			n.setParentListPos(i);
			i++;
			setParents(n);	
		}
	}
	

}
