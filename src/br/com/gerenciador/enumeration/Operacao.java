package br.com.gerenciador.enumeration;


public enum Operacao {
	
	EXIBINDO(1,"Exibindo"),
	NOVO(2,"Novo"),
	EDITAR(3,"Edição"),
	EXCLUSAO(4,"Exclusão"),
	PESQUISA(5,"Pesquisa"),
	RELATORIO(6,"Relatório"),
	LOGIN(7,"Login"),
	LOGOUT(8,"Logout"),
	ALTERACAO_SENHA(9,"Alteração de Senha"),
	IMPORTACAO_DADOS(10,"Importação de Dados"),
	VALIDACAO(11,"Validação de Dados"),
	LISTAGEM(12,"Listagem");

	private String label;
	private int id;

	private Operacao(int id, String label) {
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
