package br.com.gerenciador.enumeration;


public enum CamposRelatorio {
	
	NENHUM(1, "Nenhum"),
	CONTATO(2,"Contato"),
	OPERADOR(3,"Operador"),
	REGIONAL(4,"Regional"),
	DATA(5,"Data inical"),
	STATUS_NEGOCIACAO(5,"Status");

	private String label;
	private int id;

	private CamposRelatorio(int id, String label) {
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
