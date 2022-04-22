package algoritmoGenetico.trees;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.tablaMultiplexor.TablaMultiplexor;

public class NodeInput extends Node{
	
	enum InputType { A, D, MAX_VALUES}
	InputType type;
	
	private Random rnd;
	
	int pos;	//valor dentro de la A o D espctivamente(A1,A2,... o D1,D2..)
	
	
	public NodeInput(int height) {
		super(height, true);
		this.childs = new ArrayList<Node>();
		this.rnd = new Random();	
		
		//Crea un tipo aleatorio de input
		type = InputType.values()[this.rnd.nextInt(InputType.MAX_VALUES.ordinal())];
		if(type == InputType.A)
			pos = this.rnd.nextInt(TablaMultiplexor.getInstance().getNumEntradasA());
		else
			pos = this.rnd.nextInt(TablaMultiplexor.getInstance().getNumEntradasD());
	}
	
	
	public NodeInput(NodeInput other) {
		super(other);
		this.rnd = new Random();	
		
		this.type = other.type;
		this.pos = other.pos;
	}
	
	public InputType getType(){
		return this.type;
	}
	public void setType(InputType type){
		this.type = type;
	}
	public void setPos(int p){
		this.pos = p;
	}
	
	@Override
	public int evalue(byte []A, byte []D)
	{
		if(type == InputType.A)
			return A[pos];
		else
			return D[pos];
	};

}
