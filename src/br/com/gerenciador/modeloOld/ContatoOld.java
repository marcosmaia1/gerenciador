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
public class ContatoOld {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idContato;
	
	@Column(length=150, nullable=false)
	private String nome;
	
	@Column(length=50, nullable = true)
	private String fantasia;
	
	@Column(length=70, nullable = true)
	private String razao;
	
	@Column(length=50, nullable = true)
	private String cidade;
	
	@Column(length=2, nullable = true)
	private String estado;
	
	@Column(length=45, nullable = true)
	private String cnpj;
	
	@Column(length=20, nullable = false)
	private String tel;
	
	@Column(length=20, nullable = true)
	private String tel2;
	
	@Column(length=70, nullable = false)
	private String email;
	
	@Column(length=70, nullable = true)
	private String email2;
		
	@Column(length=45, nullable = true)
	private String sistema;
	
	@Column(length=45, nullable = true)
	private String indicacao;
	
	@Column(length=140, nullable = true)
	private String endereco;
	
	@Column(length=10, nullable = true)
	private String numero;
	
	@Column(length=45, nullable = true)
	private String bairro;
	
	@Column(length=140, nullable = true)
	private String complemento;

	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="idoperador")
	private OperadorOld idoperador;	
			
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getFantasia() {
		return fantasia;
	}
	
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
	
	public String getRazao() {
		return razao;
	}
	
	
	public void setRazao(String razao) {
		this.razao = razao;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getTel2() {
		return tel2;
	}
	
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail2() {
		return email2;
	}
	
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	
	public String getSistema() {
		return sistema;
	}
	
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	
	public String getIndicacao() {
		return indicacao;
	}
	
	public void setIndicacao(String indicacao) {
		this.indicacao = indicacao;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getIdContato() {
		return idContato;
	}

	public void setIdContato(int idContato) {
		this.idContato = idContato;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public OperadorOld getIdoperador() {
		return idoperador;
	}

	public void setIdoperador(OperadorOld idoperador) {
		this.idoperador = idoperador;
	}
	
}
