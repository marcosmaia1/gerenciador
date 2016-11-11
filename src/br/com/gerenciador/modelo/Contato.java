package br.com.gerenciador.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Index;

import br.com.gerenciador.enumeration.Entidade;
import br.com.gerenciador.enumeration.TipoPessoa;
import br.com.gerenciador.util.FuncoesUtil;

@Entity
public class Contato extends ClasseGenerica{
	
	private Long idOld;
	
	@Index(name="contatoNomeIdx")
	@Column(length=50, nullable=false)
	private String nome;
	
	@Column(length=50, nullable = true)
	private String fantasia;
	
	@Column(length=70, nullable = true)
	private String razao;
	
	@Enumerated(EnumType.STRING)
	@Column(length=1, nullable = true)
	private TipoPessoa tipoPessoa;
	
	@Index(name="contatoCpfCnpjIdx")
	@Column(length=20, nullable = true)
	private String cpfCnpj;
	
	@Column(length=13, nullable = false)
	private String tel;
	
	@Column(length=13, nullable = true)
	private String tel2;
	
	@Column(length=50, nullable = false)
	private String email;
	
	@Column(length=50, nullable = true)
	private String email2;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.DETACH)
	@JoinColumn(name="cidade_id", nullable = true)
	private Cidade cidade;
	
	@Column(name="cidade", nullable = true)
	private String cidadeNome;
		
	@Column(length=45, nullable = true)
	private String sistema;
	
	@Column(length=45, nullable = true)
	private String indicacao;
	
	@Column(length=140, nullable = true)
	private String endereco;
	
	@Column(length=10, nullable = true)
	private String numero;
	
	@Column(length=20, nullable = true)
	private String bairro;
	
	@Column(length=50, nullable = true)
	private String complemento;
	
	private Boolean desativado;
	
	public Contato(){
		super.setEntidade(Entidade.CONTATO);
	}	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isDesativado() {
		return desativado;
	}
	public void setDesativado(boolean desativado) {
		this.desativado = desativado;
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
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public String getCpfCnpjF() {
		if(cpfCnpj.trim().length() == 14){
			return FuncoesUtil.formatarCNPJ(cpfCnpj);
		} else if(cpfCnpj.trim().length() == 11){
			return FuncoesUtil.formatarCPF(cpfCnpj);
		} else {
			return cpfCnpj;
		}
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getTel() {
		return tel;
	}
	public String getTelF() {
		if(tel.trim().length() == 10){
			return FuncoesUtil.formatarMascara("(##) ####-####", tel);
		} else if(tel.length() == 11){
			return FuncoesUtil.formatarMascara("(##) #####-####", tel);
		} else {
			return tel;
		}
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel2() {
		return tel2;
	}
	public String getTel2F() {
		if(tel2.trim().length() == 10){
			return FuncoesUtil.formatarMascara("(##) ####-####", tel2);
		} else if(tel2.length() == 11){
			return FuncoesUtil.formatarMascara("(##) #####-####", tel2);
		} else {
			return tel2;
		}
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
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Boolean getDesativado() {
		return desativado;
	}
	public void setDesativado(Boolean desativado) {
		this.desativado = desativado;
	}
	public String getCidadeNome() {
		return cidadeNome;
	}
	public void setCidadeNome(String cidadeNome) {
		this.cidadeNome = cidadeNome;
	}
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	public Long getIdOld() {
		return idOld;
	}
	public void setIdOld(Long idOld) {
		this.idOld = idOld;
	}	
}
