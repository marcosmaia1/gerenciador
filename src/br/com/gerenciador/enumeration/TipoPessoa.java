package br.com.gerenciador.enumeration;


public enum TipoPessoa {
	
	F(1,"Fisica"),
	J(2,"Juridica");

	private String label;
	private int id;

	private TipoPessoa(int id, String label) {
		this.label = label;
		this.id    = id;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
