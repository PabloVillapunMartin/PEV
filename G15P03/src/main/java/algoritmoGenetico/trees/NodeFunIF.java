package algoritmoGenetico.trees;

import java.util.ArrayList;

import algoritmoGenetico.trees.NodeFunction.FunctionType;

public class NodeFunIF extends NodeFunction {

private Node cond, first, second;
	
	public NodeFunIF(int height, Node cond, Node first, Node second) {
		super(height);
		this.type =  FunctionType.IF;
		
		this.cond = cond;
		this.first = first;
		this.second = second;
		
	}
	
	@Override
	public int evalue(byte []A, byte []D)
	{
		if(this.cond.evalue(A, D) == 1){
			return this.first.evalue(A, D);
		}
		else return this.second.evalue(A, D);
	};
	
	@Override
	public ArrayList<Node> getChildren(){
		ArrayList<Node> list = new ArrayList<Node>();
		list.add(cond);
		list.add(first);
		list.add(second);
		return list;
	}
}
