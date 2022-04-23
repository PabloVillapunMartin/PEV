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
	 * Constructora arbol
	 * */
	public Tree(int maxHeight, TipoArbolInicio tipo){
		this.rnd = new Random();
		this.maxHeight = maxHeight - 1;
			
		switch(tipo) {
			case grow:  growInitialization();				break;
			case full:	this.root = fullInitialization(0);	break;
		}
		setParents(this.root);
	}
	
	/*
	 * Constructora por copia
	 * */
	public Tree(Tree other){
		this.maxHeight = other.maxHeight;
		this.rnd = new Random();

		if(other.root.isLeaf)
			this.root = new NodeInput((NodeInput)other.root);
		else {
			switch(((NodeFunction)other.root).type){
				case AND: 	this.root = new NodeFunAND(other.root);	break;
				case OR:	this.root = new NodeFunOR(other.root);	break;
				case IF:	this.root = new NodeFunIF(other.root);	break;
				case NOT:	this.root = new NodeFunNOT(other.root);	break;
			}
		}
		
		setParents(this.root);
	}
	
	
	
	//--------------GETTERS & SETTERS--------------
	public Node getRoot() {
		return this.root;
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
		if(height >= this.maxHeight || rnd.nextFloat() < 0.3f)
			return new NodeInput(height);
		else
			return getRandomFun(height);
	}
	/*
	 * Crea un nodo funcion aleatorio dada la altura por la que se está construyendo
	 * */
	public NodeFunction getRandomFun(int height){
		int r = this.rnd.nextInt(FunctionType.MAX_VALUES.ordinal());
		FunctionType type = FunctionType.values()[r];
		
		switch(type){
		case AND: 	return new NodeFunAND(height, getRandomNode(height + 1), getRandomNode(height + 1));
		case OR: 	return new NodeFunOR(height, getRandomNode(height + 1), getRandomNode(height + 1));
		case NOT:	return new NodeFunNOT(height, getRandomNode(height + 1));
		case IF:	return new NodeFunIF(height, getRandomNode(height + 1), getRandomNode(height + 1), getRandomNode(height + 1));
		default: 	return new NodeFunAND(height, getRandomNode(height + 1), getRandomNode(height + 1));
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
			case AND: 	return new NodeFunAND(height, fullInitialization(height + 1), fullInitialization(height + 1));
			case OR: 	return new NodeFunOR(height, fullInitialization(height + 1), fullInitialization(height + 1));
			case NOT:	return new NodeFunNOT(height, fullInitialization(height + 1));
			case IF:	return new NodeFunIF(height, fullInitialization(height + 1), fullInitialization(height + 1), getRandomNode(height + 1));
			default: 	return new NodeFunAND(height, fullInitialization(height + 1), fullInitialization(height + 1));
			}
		}
		else
			return new NodeInput(height); 
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
	public NodeInput getRandomLeaf(){
		Node node = null;
		//Creamos una cola para hacer una busqueda en anchura
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(this.root);
				
		ArrayList<NodeInput> candidates = new ArrayList<NodeInput>();
		if(this.root.isLeaf())
			candidates.add((NodeInput)this.root);
		while(!queue.isEmpty()){
			Node current = queue.remove();
			//Recogemos los hijos de la funcion y los añadimos a la cola
			ArrayList<Node> list = current.getChildren();
			for(Node n: list) {
				queue.add(n);
				if(!n.isLeaf()) continue;
				candidates.add((NodeInput)n);
			}
		}
		if(candidates.size() == 0)
			candidates.size();
		int rand = rnd.nextInt(candidates.size());
		return candidates.get(rand);
	}
	
	/*
	 * Busca una rama aleatoria y la devuelve
	 */
	public Node getRandomBranch() {
		Node node = null;
		//Creamos una cola para hacer una busqueda en anchura
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(this.root);
		
		ArrayList<Node> candidates = new ArrayList<Node>();
		if(!this.root.isLeaf())
			candidates.add(this.root);
		while(!queue.isEmpty()){
			Node current = queue.remove();
			//Recogemos los hijos de la funcion y los añadimos a la cola
			ArrayList<Node> list = current.getChildren();
			for(Node n: list) {
				if(n.isLeaf()) continue;
				queue.add(n);
				candidates.add(n);
			}
		}
		if(candidates.size() == 0)
			return this.root;
		int rand = rnd.nextInt(candidates.size());
		return candidates.get(rand);
	}
	
	
	/*
	 * Devuelve un subarbol aleatorio
	 * */
	public Node getSubtree(){
		Node node = null;
		//Creamos una cola para hacer una busqueda en anchura
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(this.root);
		
		ArrayList<Node> candidates = new ArrayList<Node>();
		candidates.add(this.root);
		while(!queue.isEmpty()){
			Node current = queue.remove();
			//Recogemos los hijos de la funcion y los añadimos a la cola
			ArrayList<Node> list = current.getChildren();
			for(Node n: list) {
				candidates.add(n);
				if(n.isLeaf()) continue;
				queue.add(n);
			}
		}
		int rand = rnd.nextInt(candidates.size());
		return candidates.get(rand);
	}
	
	//-----------------------------------------------------------------------------------
	/*
	 * Devuelve la informacion del arbol en cadena de string
	 * */
	public String toString(){
		return this.root.toString();
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
	
	/*
	 * Controlar el bloating cuando se asigna una nueva altura, cortando las ramas que sobrepasen
	 * la altura maxima
	 */
	public static void bloatingCheck(Node node, int maxHeight) {
		//Asignamos nueva altura
		if(node.parent == null) {
			node.height = 0;
		}
		else node.height = node.parent.height + 1;
		
		//Si llegamos a la altura maxima, decimos al padre que pase todos sus hijos a hojas
		if(node.height >= maxHeight) {
			for(int i = 0; i < node.parent.childs.size(); ++i) {
				node.parent.childs.set(i, new NodeInput(node.height));
			}
		}
		//En caso contrario se sigue controlando el bloating en los hijos
		else {
			for(int i = 0; i < node.childs.size(); ++i) {
				bloatingCheck(node.childs.get(i), maxHeight);
			}
		}
	}
	
}
