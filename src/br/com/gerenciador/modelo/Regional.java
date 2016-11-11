package br.com.gerenciador.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Index;

import br.com.gerenciador.enumeration.Entidade;

@Entity
public class Regional extends ClasseGenerica{
	
	@Column(length=50, nullable=false)
	@Index(name="regionalDescricaoIdx")
	private String descricao;
			
	private Boolean desativado;
	
	public Regional(){
		super.setEntidade(Entidade.REGIONAL);
	}	
	
	public Operador getOperadorCadastro() {
		return operadorCadastro;
	}
	public void setOperadorCadastro(Operador operadorCadastro) {
		this.operadorCadastro = operadorCadastro;
	}
	public Boolean getDesativado() {
		return desativado;
	}
	public void setDesativado(Boolean desativado) {
		this.desativado = desativado;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
