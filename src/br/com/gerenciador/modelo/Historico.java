package br.com.gerenciador.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.gerenciador.enumeration.Entidade;

@Entity
public class Historico extends ClasseGenerica{
	
	@Column(length=300, nullable = false)
	private String comentario;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="negociacao_id")
	private Negociacao negociacao;

	public Historico(){
		super.setEntidade(Entidade.HISTORICO);
	}	
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Negociacao getNegociacao() {
		return negociacao;
	}

	public void setNegociacao(Negociacao negociacao) {
		this.negociacao = negociacao;
	}

	public String getResumo() {
		if(comentario.length() > 100){
			return comentario.substring(0, 99) + "...";
		} else {
			return comentario;
		}
	}
}
