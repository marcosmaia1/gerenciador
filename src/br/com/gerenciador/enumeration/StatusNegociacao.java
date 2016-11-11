package br.com.gerenciador.enumeration;


public enum StatusNegociacao {
	
	NENHUM(1, "Nenhum"),
	EM_ANDAMENTO(2,"Em andamento"),
	CONCLUIDO(3,"Concluído"),
	CONCLUIDO_SEM_SUCESSO(4,"Concluído sem sucesso"),
	PROSPECTADO(5,"Prospectado");

	private String label;
	private int id;

	private StatusNegociacao(int id, String label) {
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
