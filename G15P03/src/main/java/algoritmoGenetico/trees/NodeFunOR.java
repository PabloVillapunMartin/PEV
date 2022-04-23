package algoritmoGenetico.trees;

import java.util.ArrayList;

import algoritmoGenetico.trees.NodeFunction.FunctionType;

public class NodeFunOR extends NodeFunction {
	
	public NodeFunOR(int height, Node first, Node second) {
		super(height);
		this.type =  FunctionType.OR;
		
		this.childs = new ArrayList<Node>();
		this.childs.add(first);
		this.childs.add(second);
	}
	
	public NodeFunOR(Node other) {
		super(other);
	}
	
	@Override
	public int evalue(byte []A, byte []D)
	{
		return this.childs.get(0).evalue(A, D) | this.childs.get(1).evalue(A, D);
	};
	
	@Override
	public String toString() {
		return "(OR " + this.childs.get(0).toString() + ", " + this.childs.get(1) + ")";
	}
}
