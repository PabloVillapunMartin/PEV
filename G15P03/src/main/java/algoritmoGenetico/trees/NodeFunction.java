package algoritmoGenetico.trees;

import java.util.ArrayList;

public class NodeFunction extends Node{

	public NodeFunction(int height) {
		super(height, false);
	}
	public NodeFunction(Node n) {
		super(n);
		this.type = ((NodeFunction)n).type;
	}

	enum FunctionType {  AND, OR, NOT, IF, MAX_VALUES }
	protected FunctionType type;
	
	@Override
	public int evalue(byte []A, byte []D)
	{
		throw new RuntimeException("Se está llamando al evaluar de NodeFunction");
	};

	public ArrayList<Node> getChildren(){
		return this.childs;
	}
}
