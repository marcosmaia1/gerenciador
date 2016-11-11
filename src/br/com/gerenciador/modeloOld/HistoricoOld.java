package br.com.gerenciador.modeloOld;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class HistoricoOld{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idHistorico;
	
	@Column(length=300, nullable = false)
	private String comentario;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="idnegociacao")
	private NegociacaoOld idNegociacao;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "idoperador")
	private OperadorOld idoperador;
	
	private Date data;
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(int idHistorico) {
		this.idHistorico = idHistorico;
	}

	public NegociacaoOld getIdNegociacao() {
		return idNegociacao;
	}

	public void setIdNegociacao(NegociacaoOld idNegociacao) {
		this.idNegociacao = idNegociacao;
	}

	public OperadorOld getIdoperador() {
		return idoperador;
	}

	public void setIdoperador(OperadorOld idoperador) {
		this.idoperador = idoperador;
	}

	public String getResumo() {
		if(comentario.length() > 100){
			return comentario.substring(0, 99) + "...";
		} else {
			return comentario;
		}
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
