package algoritmoGenetico.trees;

import java.util.ArrayList;

import algoritmoGenetico.trees.NodeFunction.FunctionType;

public class NodeFunNOT extends NodeFunction{

	private Node node;
	
	public NodeFunNOT(int height, Node node) {
		super(height);
		this.type =  FunctionType.NOT;
		
		this.node = node;
		
	}
	
	@Override
	public int evalue(byte []A, byte []D)
	{
		if(this.node.evalue(A, D) == 0)
			return 1;
		else return 0;
	};
	
	@Override
	public ArrayList<Node> getChildren(){
		ArrayList<Node> list = new ArrayList<Node>();
		list.add(node);
		return list;
	}
}
