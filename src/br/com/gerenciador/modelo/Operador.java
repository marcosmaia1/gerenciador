package br.com.gerenciador.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;

import br.com.gerenciador.enumeration.Entidade;

@Entity
public class Operador extends ClasseGenerica{
	
	@Column(length=40, nullable=false)
	@Index(name="operadorNomeIdx")
	private String nome;
	
	@Column(length=20, nullable=false)
	private String login;
	
	@Column(length=20, nullable=false)
	private String senha;
	
	@Transient
	private String senha2;
	
	@Column(nullable=false)
	private Boolean administrador;
	
	@Column(nullable=false)
	private Boolean desativado;
	
	public Operador(){
		super.setEntidade(Entidade.OPERADOR);
	}	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
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
	public String getSenha2() {
		return senha2;
	}
	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}
	public Boolean getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}
}
