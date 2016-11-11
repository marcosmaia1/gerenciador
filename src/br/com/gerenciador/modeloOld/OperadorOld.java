package br.com.gerenciador.modeloOld;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OperadorOld {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOperador;

	@Column(length = 45, nullable = false)
	private String nome;

	@Column(length = 45, nullable = false)
	private String senha;

	@Column(nullable = false)
	private int permissao;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "op")
	private OperadorOld op;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(int idOperador) {
		this.idOperador = idOperador;
	}

	public int getPermissao() {
		return permissao;
	}

	public void setPermissao(int permissao) {
		this.permissao = permissao;
	}

	public OperadorOld getOp() {
		return op;
	}

	public void setOp(OperadorOld op) {
		this.op = op;
	}
	
}
