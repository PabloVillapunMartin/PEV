package algoritmoGenetico.trees;

import java.util.ArrayList;

import algoritmoGenetico.trees.NodeFunction.FunctionType;

public class NodeFunNOT extends NodeFunction{
	
	public NodeFunNOT(int height, Node node) {
		super(height);
		this.type =  FunctionType.NOT;
		
		this.childs = new ArrayList<Node>();
		this.childs.add(node);		
	}
	
	public NodeFunNOT(Node other) {
		super(other);
	}
	
	@Override
	public int evalue(byte []A, byte []D)
	{
		if(this.childs.get(0).evalue(A, D) == 0)
			return 1;
		else return 0;
	};
}
