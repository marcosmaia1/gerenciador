package br.com.gerenciador.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.gerenciador.enumeration.Entidade;
import br.com.gerenciador.enumeration.Fechamento;
import br.com.gerenciador.enumeration.StatusNegociacao;

@Entity
public class Negociacao extends ClasseGenerica{
	
	private Long idOld;
	
	@Column(length=500, nullable = true)
	private String obs;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="regional_id", nullable = false)
	private Regional regional;
	
	@Enumerated(EnumType.STRING)
	@Column(length=22, nullable = false)
	private StatusNegociacao status;
	
	@Column(name="data_inicio", updatable=false)	
	private Date dataInicio;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="negociacao_id")
	private List<Historico> historico;			

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="contato_id", nullable = false)
	private Contato contato;
	
	@Column(precision = 15, scale = 4, nullable = true)
	private BigDecimal valorFechamento;
	
	@Enumerated(EnumType.STRING)
	@Column(length=4, nullable = false)
	private Fechamento fechamento;
	
	private Boolean desativado;
	
	public Negociacao(){
		super.setEntidade(Entidade.NEGOCIACAO);
	}	
	
	public String getObs() {
		return obs;
	}
	
	public void setObs(String obs) {
		this.obs = obs;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public StatusNegociacao getStatus() {
		return status;
	}

	public void setStatus(StatusNegociacao status) {
		this.status = status;
	}

	public List<Historico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Long getIdOld() {
		return idOld;
	}

	public void setIdOld(Long idOld) {
		this.idOld = idOld;
	}

	public BigDecimal getValorFechamento() {
		return valorFechamento;
	}

	public void setValorFechamento(BigDecimal valorFechamento) {
		this.valorFechamento = valorFechamento;
	}

	public Fechamento getFechamento() {
		return fechamento;
	}

	public void setFechamento(Fechamento fechamento) {
		this.fechamento = fechamento;
	}

	public Boolean getDesativado() {
		return desativado;
	}

	public void setDesativado(Boolean desativado) {
		this.desativado = desativado;
	}
}
