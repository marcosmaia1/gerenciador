package br.com.gerenciador.modelo;

import java.util.Date;
import java.util.List;

import br.com.gerenciador.enumeration.CamposRelatorio;
import br.com.gerenciador.enumeration.Entidade;
import br.com.gerenciador.enumeration.Operacao;
import br.com.gerenciador.enumeration.StatusNegociacao;

public class RelatorioFiltros {
	
	private Contato contato;
	
	private Operador operador;
	
	private Regional regional;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private StatusNegociacao status;
	
	private CamposRelatorio campoOrdenacao;
	
	private CamposRelatorio campoAgrupamento;
	
	List<Negociacao> relatorioList;
	
	private Operacao operacao;
	
	private Entidade entidade;
	

	
	//Recupera os caminhos para que a classe possa encontrar os relatórios
	public RelatorioFiltros() {
		this.entidade = Entidade.NEGOCIACAO;
		this.operacao = Operacao.RELATORIO;
	}
	
	public Date getDataInicial() {
		return dataInicial;
	}
	
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	public Date getDataFinal() {
		return dataFinal;
	}
	
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	
	public Operador getOperador() {
		return operador;
	}
	
	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	
	public Regional getRegional() {
		return regional;
	}
	
	public void setRegional(Regional regional) {
		this.regional = regional;
	}
	
	public Contato getContato() {
		return contato;
	}
	
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public StatusNegociacao getStatus() {
		return status;
	}
	
	public void setStatus(StatusNegociacao status) {
		this.status = status;
	}

	public CamposRelatorio getCampoOrdenacao() {
		return campoOrdenacao;
	}

	public void setCampoOrdenacao(CamposRelatorio campoOrdenacao) {
		this.campoOrdenacao = campoOrdenacao;
	}

	public CamposRelatorio getCampoAgrupamento() {
		return campoAgrupamento;
	}

	public void setCampoAgrupamento(CamposRelatorio campoAgrupamento) {
		this.campoAgrupamento = campoAgrupamento;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

	public List<Negociacao> getRelatorioList() {
		return relatorioList;
	}

	public void setRelatorioList(List<Negociacao> relatorioList) {
		this.relatorioList = relatorioList;
	}
	
}
