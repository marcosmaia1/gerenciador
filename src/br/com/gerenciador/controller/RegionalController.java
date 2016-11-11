package br.com.gerenciador.controller;

import static br.com.gerenciador.util.FuncoesUtil.iniciaTransacao;
import static br.com.gerenciador.util.FuncoesUtil.cancelaTransacao;
import static br.com.gerenciador.util.FuncoesUtil.comitaTransacao;

import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.gerenciador.enumeration.Entidade;
import br.com.gerenciador.enumeration.Operacao;
import br.com.gerenciador.infra.RegionalDao;
import br.com.gerenciador.modelo.OperadorWeb;
import br.com.gerenciador.modelo.Regional;
import br.com.gerenciador.modelo.Restrito;

@Resource
public class RegionalController {

	private final RegionalDao dao;
	private final Result result;
	private final Validator validator;
	private final OperadorWeb operadorWeb;

	public RegionalController(RegionalDao dao, Result result, Validator validator, OperadorWeb operadorWeb) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.operadorWeb = operadorWeb;
	}

	@Restrito
	@Get("/regional")
	public void lista() {
		result.include("regionalList", dao.listaTudo()).include("operacao", Operacao.LISTAGEM.getLabel()).include("entidade", Entidade.REGIONAL);
	}

	private boolean validaFormulario(Regional entity) {
		boolean resultado = true;
		
		if(entity.getDescricao() == null || entity.getDescricao() == ""){
			validator.add(new ValidationMessage("Descrição em branco", "error"));
			result.include("errorDescricao", true);
			resultado = false;
		}
		
		if(entity.getDescricao() != null && dao.existeDescricao(entity)){
			validator.add(new ValidationMessage("Descrição já cadastrada", "error"));
			result.include("errorDescricao", true);
			resultado = false;
		}
		
		if(entity.getDesativado() == null){
			entity.setDesativado(false);
		}
		
		if(entity.getDesativado() == true && operadorWeb.getLogado().getAdministrador()){
			entity.setDesativado(true);
		} else {
			entity.setDesativado(false);
		}
		
		if(!resultado){
			validator.onErrorUsePageOf(this).formulario(entity);
		}
		
		return resultado;
	}	
	
	@Restrito
	@Get("/regional/novo")
	public void formulario(Regional entity) {
		if(entity == null){
			entity = new Regional();
		}
		entity.setDesativado(false);
		entity.setOperacao(Operacao.NOVO);
		result.include("entity", entity);
	}
	
	@Restrito
	@Post("/regional")
	public void adiciona(Regional entity) {
		entity.setDesativado(false);
		if(validaFormulario(entity)){
			if(operadorWeb.getLogado() != null){
				entity.setOperadorCadastro(operadorWeb.getLogado());
				entity.setOperadorAlteracao(operadorWeb.getLogado());
				entity.setDataCadastro(new Date());
				entity.setUltimaAlteracao(new Date());
			}
			try{
				iniciaTransacao(dao.getSession());
				dao.salva(entity);
				comitaTransacao(dao.getSession());
				result.include("success", "Regional cadastrada com sucesso");
				result.redirectTo(RegionalController.class).lista();
			} catch (Exception e){
				System.out.println(e);
				cancelaTransacao(dao.getSession());
				result.include("error", e.getMessage());
			}
		} else {
			result.redirectTo(this).formulario(entity);
		}
	}

	@Restrito
	@Get("/regional/{entity.id}")
	public void exibir(Regional entity) {
		entity = dao.getRegional(entity.getId());
		entity.setOperacao(Operacao.EXIBINDO);
		result.include("entity", entity);
	}

	@Restrito
	@Get("/regional/{entity.id}/edita")
	public void edita(Regional entity) {
		entity = dao.getRegional(entity.getId());
		entity.setOperacao(Operacao.EDITAR);
		result.include("entity", entity);
	}

	@Restrito
	@Put("/regional")
	public void altera(Regional entity) {
		if(validaFormulario(entity)){
			if(operadorWeb.getLogado() != null){
				entity.setOperadorAlteracao(operadorWeb.getLogado());
				entity.setUltimaAlteracao(new Date());
			}
			try{
				iniciaTransacao(dao.getSession());
				dao.atualiza(entity);
				comitaTransacao(dao.getSession());
				result.include("success", "Regional alterada com sucesso");
				result.redirectTo(RegionalController.class).lista();
			} catch (Exception e){
				cancelaTransacao(dao.getSession());
				System.out.println(e);
				result.include("error", e.getMessage());
			}
		} else {
			result.redirectTo(this).lista();
		}		
	}

	@Restrito
	@Get("/regional/{id}/remove")
	public void remove(Long id) {
		Regional entity = dao.getRegional(id);
		try{
			iniciaTransacao(dao.getSession());
			dao.remove(entity);
			comitaTransacao(dao.getSession());
		} catch (Exception e){
			cancelaTransacao(dao.getSession());
			System.out.println(e);
			result.include("error", e.getMessage());
		}
		result.redirectTo(this).lista();
	}

	@Get("/regional/busca.json")
	public void buscaJson(String q) {
		result.use(Results.json()).withoutRoot().from(dao.busca(q)).exclude("id", "nome").serialize();
	}

	@Restrito
	@Get("/regional/busca")
	public List<Regional> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}


}
