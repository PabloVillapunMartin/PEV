package algoritmoGenetico.trees;

import java.util.Random;

public class NodeInput extends Node{
	
	enum InputType { A, D, MAX_VALUES}
	InputType type;
	
	private Random rnd;
	
	int pos;	//valor dentro de la A o D espctivamente(A1,A2,... o D1,D2..)
	
	public NodeInput(int height) {
		super(height, true);
		this.rnd = new Random();	
		
		//Crea un tipo aleatorio de input
		type = InputType.values()[this.rnd.nextInt(InputType.MAX_VALUES.ordinal())];
		if(type == InputType.A)
			pos = this.rnd.nextInt(2);
		else
			pos = this.rnd.nextInt(4);
	}
	
	@Override
	public int evalue(int []A, int []D)
	{
		if(type == InputType.A)
			return A[pos];
		else
			return D[pos];
	};

}
