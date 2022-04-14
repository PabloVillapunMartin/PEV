package algoritmoGenetico.trees;

import java.util.ArrayList;

import algoritmoGenetico.trees.NodeFunction.FunctionType;

public class NodeFunOR extends NodeFunction {

	private Node first, second;
	
	public NodeFunOR(int height, Node first, Node second) {
		super(height);
		this.type =  FunctionType.OR;
		
		this.first = first;
		this.second = second;
		
	}
	
	@Override
	public int evalue(int []A, int []D)
	{
		return this.first.evalue(A, D)	| this.second.evalue(A, D);
	};
	
	@Override
	public ArrayList<Node> getChildren(){
		ArrayList<Node> list = new ArrayList<Node>();
		list.add(first);
		list.add(second);
		return list;
	}
}
