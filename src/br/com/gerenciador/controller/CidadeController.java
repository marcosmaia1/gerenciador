package br.com.gerenciador.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import br.com.gerenciador.enumeration.Operacao;
import br.com.gerenciador.infra.CidadeDao;
import br.com.gerenciador.modelo.Cidade;
import br.com.gerenciador.modelo.OperadorWeb;
import br.com.gerenciador.modelo.Restrito;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;

@Resource
public class CidadeController {

	private final CidadeDao dao;
	private final Result result;
	private final Validator validator;
	private final OperadorWeb operadorWeb;

	public CidadeController(CidadeDao dao, Result result, Validator validator, OperadorWeb operadorWeb) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.operadorWeb = operadorWeb;
	}
	
	public boolean valida(Cidade cidade){
		
				
		return true;
	}

	@Restrito
	@Get("/cidade")
	public void lista() {
		String mensagem = "teste";
		result.include("key", mensagem);
		result.include("cidadeList", dao.listaTudo());
	}

	@Restrito
	@Post("/cidade")
	public void adiciona(Cidade cidade) {
		if(cidade.getOperacao() == Operacao.NOVO){
			validaFormulario(cidade);
			cidade.setOperadorCadastro(operadorWeb.getLogado());
			dao.salva(cidade);
			result.redirectTo(this).lista();
		} else if(cidade.getOperacao() == Operacao.EDITAR){
			validaFormulario(cidade);
			cidade.setOperadorAlteracao(operadorWeb.getLogado());
			dao.atualiza(cidade);
			result.redirectTo(this).lista();
		}
		
	}

	@Restrito
	@Get("/cidade/novo")
	public void formulario() {
		Cidade cidade = new Cidade();
		cidade.setOperadorCadastro(operadorWeb.getLogado());
		cidade.setOperacao(Operacao.NOVO);
		result.include("cidade", cidade);
	}

	@Restrito
	@Get("/cidade/{id}")
	public Cidade formulario(Long id) {
		Cidade entidade = new Cidade();
		entidade = dao.getCidade(id);
		entidade.setOperacao(Operacao.EXIBINDO);
		return entidade;
	}

	@Restrito
	@Get("/cidade/{id}/edita")
	public Cidade edita(Long id) {
		Cidade entidade = new Cidade();
		entidade = dao.getCidade(id);
		entidade.setOperacao(Operacao.EDITAR);
		return entidade;
	}

	@Restrito
	@Put("/cidade/{cidade.id}")
	public void altera(Cidade cidade) {

		dao.atualiza(cidade);
		result.redirectTo(this).lista();
	}

	@Restrito
	@Get("/cidade/{id}/remove")
	public void remove(Long id) {
		Cidade cidade = dao.getCidade(id);
		dao.remove(cidade);
		result.redirectTo(this).lista();
	}

	@Get("/cidade/busca.json")
	public void buscaJson(String q) {
		result.use(Results.json()).withoutRoot().from(dao.busca(q)).exclude("id", "nome").serialize();
	}

	@Restrito
	@Get("/cidade/busca")
	public List<Cidade> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	private void validaFormulario(Cidade cidade) {
		validator.validate(cidade);
		validator.onErrorUsePageOf(this).formulario();
	}
	
	@Restrito
	@Post("/cidade/jsonCidade")  
	public void getCidades(String estado) {  
	   List<Cidade> cidades = dao.getCidadeByUf(estado); 
	   result.use(json()).from(cidades).exclude("uf","estado","codigoIBGE","dataCadastro", "ultimaAlteracao").serialize();
	}  	
}
