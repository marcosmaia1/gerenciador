package br.com.gerenciador.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import br.com.gerenciador.enumeration.UF;

@Entity
@Table(name="cidade")
public class Cidade extends ClasseGenerica{

	@Column(length=50, nullable = false)
	@Index(name="cidadeNomeIdx")
	private String nome;

	@Column(length=2, nullable = false)
	@Enumerated(EnumType.STRING)
	private UF uf;	

	@Column(length=20, nullable = true)
	private String estado;	
	
	@Column(length=10, nullable = true)
	@Index(name="cidadeCodigoIBGEIdx")
	private String codigoIBGE;

	
    @PrePersist
    protected void onCreate() {
    	this.estado = this.uf.getLabel();
    	this.ultimaAlteracao = dataCadastro = new Date();
    	if (this.operadorAlteracao != null){
    		this.operadorCadastro = this.operadorAlteracao;
    	}	
    }

    @PreUpdate
    protected void onUpdate() {
    	this.estado = this.uf.getLabel();
    	this.ultimaAlteracao = new Date();
    	if (this.operadorAlteracao != null){
    		if (this.operadorCadastro == null){
    			this.operadorCadastro = this.operadorAlteracao;
    		}
    	}	
    }		
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoIBGE() {
		return codigoIBGE;
	}

	public void setCodigoIBGE(String codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}
}