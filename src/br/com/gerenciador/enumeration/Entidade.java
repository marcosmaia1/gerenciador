package br.com.gerenciador.enumeration;

public enum Entidade {
	
	REGIONAL(1,"Regionais"),
	CIDADE(2,"Cidades"),
	OPERADOR(3,"Operadores"),
	CONTATO(4,"Contatos"),
	NEGOCIACAO(5,"Negocia��es"),
	HISTORICO(6,"Hist�rico"),
	RELATORIO(7,"Relat�rios");

	private String label;
	private int id;
	
	private Entidade(int id, String label) {
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
