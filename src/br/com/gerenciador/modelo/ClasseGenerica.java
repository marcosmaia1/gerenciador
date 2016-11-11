package br.com.gerenciador.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.gerenciador.enumeration.Entidade;
import br.com.gerenciador.enumeration.Operacao;

@MappedSuperclass
public abstract class ClasseGenerica {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
		
	@Transient
	private Operacao operacao;
	
	@Transient
	private Entidade entidade;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="operador_cadastro_id")
	protected Operador operadorCadastro;	
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="operador_alteracao_id")
	protected Operador operadorAlteracao;		
	
	@Column(name="data_cadastro", updatable=false)	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date dataCadastro;

	@Column(name="ultima_alteracao")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date ultimaAlteracao;	
	
    @PrePersist
    protected void onCreate() {
    	this.ultimaAlteracao = this.dataCadastro = new Date();
    	if (this.operadorAlteracao != null){
    		this.operadorCadastro = this.operadorAlteracao;
    	}	
    }

    @PreUpdate
    protected void onUpdate() {
    	this.ultimaAlteracao = new Date();
    	if (this.operadorAlteracao != null){
    		if (this.operadorCadastro == null){
    			this.operadorCadastro = this.operadorAlteracao;
    		}
    	}	
    }		
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public Operador getOperadorCadastro() {
		return operadorCadastro;
	}

	public void setOperadorCadastro(Operador operadorCadastro) {
		this.operadorCadastro = operadorCadastro;
	}

	public Operador getOperadorAlteracao() {
		return operadorAlteracao;
	}

	public void setOperadorAlteracao(Operador operadorAlteracao) {
		this.operadorAlteracao = operadorAlteracao;
	}

	protected String getFormatoData(){
		return "dd/MM/yyyy"; 
	}
	
	protected String getFormatoDataHora(){
		return "dd/MM/yyyy HH:mm:ss";
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}
	
}