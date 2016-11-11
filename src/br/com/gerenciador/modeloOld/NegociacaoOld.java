package br.com.gerenciador.modeloOld;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class NegociacaoOld{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNegociacao;
	
	@Column(length=140, nullable = true)
	private String obs;
	
	@Column(length=2, nullable = false)
	private String regional;
	
	@Column(length=5, nullable = false)
	private String fechamento;
	
	@Column(length=30, nullable = false)
	private String status;
	
	private Date data;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="idnegociacao")
	private List<HistoricoOld> historico;			

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="idcontato")
	private ContatoOld idcontato;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="idoperador")
	private OperadorOld idoperador;

	public String getObs() {
		return obs;
	}
	
	public void setObs(String obs) {
		this.obs = obs;
	}

	public List<HistoricoOld> getHistorico() {
		return historico;
	}

	public void setHistorico(List<HistoricoOld> historico) {
		this.historico = historico;
	}

	public int getIdNegociacao() {
		return idNegociacao;
	}

	public void setIdNegociacao(int idNegociacao) {
		this.idNegociacao = idNegociacao;
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public ContatoOld getIdcontato() {
		return idcontato;
	}

	public void setIdcontato(ContatoOld idcontato) {
		this.idcontato = idcontato;
	}

	public OperadorOld getIdoperador() {
		return idoperador;
	}

	public void setIdoperador(OperadorOld idoperador) {
		this.idoperador = idoperador;
	}

	public String getFechamento() {
		return fechamento;
	}

	public void setFechamento(String fechamento) {
		this.fechamento = fechamento;
	}
	
}
