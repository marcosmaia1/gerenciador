package br.com.gerenciador.enumeration;

public enum Entidade {
	
	REGIONAL(1,"Regionais"),
	CIDADE(2,"Cidades"),
	OPERADOR(3,"Operadores"),
	CONTATO(4,"Contatos"),
	NEGOCIACAO(5,"Negociações"),
	HISTORICO(6,"Histórico"),
	RELATORIO(7,"Relatórios");

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
