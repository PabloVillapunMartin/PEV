package algoritmoGenetico.aviones;

public class InfoAvion {

	enum Tipo{ W, G, P};
	Tipo tipo;
	String id;
	
	public InfoAvion(Tipo tipo, String id) {
		this.tipo = tipo;
		this.id = id;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
