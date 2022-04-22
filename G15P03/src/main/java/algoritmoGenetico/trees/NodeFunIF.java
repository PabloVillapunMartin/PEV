package algoritmoGenetico.trees;

import java.util.ArrayList;

import algoritmoGenetico.trees.NodeFunction.FunctionType;

public class NodeFunIF extends NodeFunction {
	
	public NodeFunIF(int height, Node cond, Node first, Node second) {
		super(height);
		this.type =  FunctionType.IF;
		
		this.childs = new ArrayList<Node>();
		this.childs.add(cond);
		this.childs.add(first);
		this.childs.add(second);
	}
	public NodeFunIF(Node other) {
		super(other);
	}
	
	@Override
	public int evalue(byte []A, byte []D)
	{
		if(this.childs.get(0).evalue(A, D) == 1){
			return this.childs.get(1).evalue(A, D);
		}
		else return this.childs.get(2).evalue(A, D);
	};
	
	@Override
	public String toString() {
		return "(IF ( " + this.childs.get(0).toString() + ", " 
				+ this.childs.get(1).toString() + ", "
				+ this.childs.get(2).toString() +"))";
	}
}
