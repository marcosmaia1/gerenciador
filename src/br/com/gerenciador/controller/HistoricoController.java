package br.com.gerenciador.controller;

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
import br.com.gerenciador.enumeration.Operacao;
import br.com.gerenciador.infra.HistoricoDao;
import br.com.gerenciador.infra.NegociacaoDao;
import br.com.gerenciador.modelo.Historico;
import br.com.gerenciador.modelo.OperadorWeb;
import br.com.gerenciador.modelo.Restrito;
import static br.com.gerenciador.util.FuncoesUtil.iniciaTransacao;
import static br.com.gerenciador.util.FuncoesUtil.comitaTransacao;
import static br.com.gerenciador.util.FuncoesUtil.cancelaTransacao;

@Resource
public class HistoricoController {

	private final HistoricoDao dao;
	private final Result result;
	private final Validator validator;
	private final OperadorWeb operadorWeb;

	public HistoricoController(HistoricoDao dao, Result result, Validator validator, OperadorWeb operadorWeb) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.operadorWeb = operadorWeb;
	}

	@Restrito
	@Get("/historico/{entity.negociacao.id}/cancelar")
	public void lista(Historico entity) {
		result.redirectTo(NegociacaoController.class).edita(entity.getNegociacao());
	}
	
	private boolean validaFormulario(Historico entity) {
		boolean resultado = true;
		
		if(entity.getComentario() == null || (entity.getComentario() != null && entity.getComentario().isEmpty())){
			validator.add(new ValidationMessage("Comentário em branco", "error"));
			result.include("errorComentario", true);
			resultado = false;
		}
		
		if(!resultado){
			result.include("entity", entity);
			validator.onErrorUsePageOf(this).formulario(entity);
		} else {
			entity.setNegociacao(new NegociacaoDao(dao.getSession()).getNegociacao(entity.getNegociacao().getId()));
		}

		return resultado;
		
	}
	
	@Restrito
	@Get("/historico/{entity.negociacao.id}/novo")
	public void formulario(Historico entity) {
		if(entity.getNegociacao().getId() != null){
				entity.setNegociacao(new NegociacaoDao(dao.getSession()).getNegociacao(entity.getNegociacao().getId()));
				entity.setOperacao(Operacao.NOVO);
				result.include("entity", entity);
		} else {
			result.include("error", "Erro durante o cadastro");
			result.redirectTo(NegociacaoController.class).lista();
		}
	}

	@Restrito
	@Post("/historico")
	public void adiciona(Historico entity) {
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
				result.include("success", "Histórico cadastrado com sucesso");
				result.redirectTo(NegociacaoController.class).edita(entity.getNegociacao());
			} catch (Exception e){
				System.out.println(e);
				cancelaTransacao(dao.getSession());
				result.include("error", e.getMessage())
						.redirectTo(NegociacaoController.class).edita(entity.getNegociacao());
				
			}
		} else {
			result.redirectTo(NegociacaoController.class).edita(entity.getNegociacao());
		}
	}

	@Restrito
	@Get("/historico/{entity.id}")
	public void exibir(Historico entity) {
		entity = dao.getHistorico(entity.getId());
		entity.setOperacao(Operacao.EXIBINDO);
		result.include("entity", entity);
	}
	
	@Restrito
	@Get("/historico/{id}/edita")
	public void edita(Long id) {
		if(operadorWeb.getLogado().getAdministrador()){
			Historico entity = new Historico();
			entity= dao.getHistorico(id);
			entity.setOperacao(Operacao.EDITAR);
			result.include("entity", entity);
		} else {
			result.include("error", "Usuário não possui permissão para acessar esta rotina");
			result.redirectTo(NegociacaoController.class).lista();
		}
	}

	@Restrito
	@Put("/historico")
	public void altera(Historico entity) {
		if(validaFormulario(entity)){
			if(operadorWeb.getLogado() != null){
				entity.setOperadorAlteracao(operadorWeb.getLogado());
				entity.setUltimaAlteracao(new Date());
			}
			try{
				iniciaTransacao(dao.getSession());
				dao.atualiza(entity);
				comitaTransacao(dao.getSession());
				result.include("success", "Histórico alterado com sucesso");
				result.redirectTo(NegociacaoController.class).edita(entity.getNegociacao());
			} catch (Exception e){
				System.out.println(e);
				cancelaTransacao(dao.getSession());
				result.include("error", e.getMessage());
			}
		} else {
			result.redirectTo(NegociacaoController.class).edita(entity.getNegociacao());
		}
	}

	@Get("/historico/busca.json")
	public void buscaJson(String q) {
		result.use(Results.json()).withoutRoot().from(dao.busca(q)).exclude("id", "nome").serialize();
	}

	@Restrito
	@Get("/historico/busca")
	public List<Historico> busca(String comentario) {
		result.include("comentario", comentario);
		return dao.busca(comentario);
	}

}
