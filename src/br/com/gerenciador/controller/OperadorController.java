package br.com.gerenciador.controller;

import static br.com.caelum.vraptor.view.Results.json;
import static br.com.gerenciador.util.FuncoesUtil.iniciaTransacao;
import static br.com.gerenciador.util.FuncoesUtil.comitaTransacao;
import static br.com.gerenciador.util.FuncoesUtil.cancelaTransacao;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.gerenciador.enumeration.Entidade;
import br.com.gerenciador.enumeration.Operacao;
import br.com.gerenciador.infra.ContatoDao;
import br.com.gerenciador.infra.OperadorDao;
import br.com.gerenciador.infra.OperadorOldDao;
import br.com.gerenciador.modelo.Contato;
import br.com.gerenciador.modelo.Operador;
import br.com.gerenciador.modelo.OperadorWeb;
import br.com.gerenciador.modelo.Restrito;
import br.com.gerenciador.modeloOld.OperadorOld;

@Resource
public class OperadorController {

	private final OperadorDao dao;
	private final Result result;
	private final Validator validator;
	private final OperadorWeb operadorWeb;

	public OperadorController(OperadorDao dao, Result result, Validator validator, OperadorWeb operadorWeb) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.operadorWeb = operadorWeb;
	}
	
	@Restrito
	@Get("/operador")
	public void lista() {
		//importaDados();
		result.include("operadorList", dao.listaTudo()).include("operacao", Operacao.LISTAGEM.getLabel()).include("entidade", Entidade.OPERADOR);
	}
	
	public void importaDados(){
		List<OperadorOld> operadorOldLista = new OperadorOldDao(dao.getSession()).listaTudo();
		for(OperadorOld operadorOld : operadorOldLista){
			if(operadorOld.getIdOperador() != 1L){
				Operador novo = new Operador();
				novo.setAdministrador(false);
				novo.setDataCadastro(new Date());
				novo.setDesativado(false);
				novo.setId(new Long(operadorOld.getIdOperador()));
				novo.setLogin(operadorOld.getNome().toLowerCase().trim());
				novo.setNome(operadorOld.getNome());
				novo.setOperadorAlteracao(operadorWeb.getLogado());
				novo.setOperadorCadastro(operadorWeb.getLogado());
				novo.setSenha(operadorOld.getSenha());
				novo.setUltimaAlteracao(new Date());
				dao.salva(novo);
			}
		}
	}
	
	private boolean validaFormulario(Operador entity) {
		boolean resultado = true;
		
		if(entity.getNome() == null || entity.getNome() == ""){
			validator.add(new ValidationMessage("Nome em branco", "error"));
			result.include("errorNome", true);
			resultado = false;
		}
		
		if(entity.getNome() != null && dao.existeCampo(entity.getId(), entity.getNome(), "nome")){
			validator.add(new ValidationMessage("Nome já cadastrado", "error"));
			result.include("errorNome", true);
			resultado = false;
		}
		
		if(entity.getLogin() == null || entity.getLogin() == ""){
			validator.add(new ValidationMessage("Login em branco", "error"));
			result.include("errorLogin", true);
			resultado = false;
		}
		
		if(entity.getLogin() != null && dao.existeCampo(entity.getId(), entity.getLogin(), "login")){
			validator.add(new ValidationMessage("Login em branco", "error"));
			result.include("errorLogin", true);
			resultado = false;
		}
		
		if((entity.getSenha() == null || entity.getSenha2() == null) || (entity.getSenha().isEmpty() || entity.getSenha2().isEmpty())){
			validator.add(new ValidationMessage("Senha em branco", "error"));
			result.include("errorSenha", true);
			resultado = false;
		}
		
		if((entity.getSenha() != null && !entity.getSenha().isEmpty()) && (entity.getSenha2() != null && !entity.getSenha2().isEmpty()) && !(entity.getSenha().equals(entity.getSenha2()))){
			validator.add(new ValidationMessage("As senhas estão diferentes", "error"));
			result.include("errorSenha", true);
			resultado = false;
		}
		
		if(entity.getAdministrador() == null){
			entity.setAdministrador(false);
		}
		
		if(entity.getDesativado() == null){
			entity.setDesativado(false);
		}
		
		if(entity.getDesativado() == true && operadorWeb.getLogado().getAdministrador()){
			entity.setDesativado(true);
		} else {
			entity.setDesativado(false);
		}
		
		if(entity.getAdministrador() && !operadorWeb.getLogado().getAdministrador()){
			entity.setAdministrador(false);
		}
		
		if(!resultado){
			validator.onErrorUsePageOf(this).formulario(entity);
		}
		
		return resultado;
	}

	@Restrito
	@Get("/operador/novo")
	public void formulario(Operador entity) {
		if(operadorWeb.getLogado().getAdministrador()){
			if(entity == null){
				entity = new Operador();
			}
			entity.setDesativado(false);
			entity.setOperacao(Operacao.NOVO);
			result.include("entity", entity);
		} else {
			result.include("alert", "Operador não possui acesso a função");
			result.redirectTo(OperadorController.class).lista();
		}
	}
	
	@Restrito
	@Post("/operador")
	public void adiciona(Operador entity) {
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
				result.include("success", "Operador cadastrado com sucesso");
				result.redirectTo(OperadorController.class).lista();
			} catch (Exception e){
				cancelaTransacao(dao.getSession());
				System.out.println(e);
				result.include("error", e.getMessage());
			}
		} else {
			result.redirectTo(this).formulario(entity);
		}
	}
	
	@Restrito
	@Get("/operador/{entity.id}")
	public void exibir(Operador entity) {
		if(operadorWeb.getLogado().getAdministrador() || operadorWeb.getLogado().getId() == entity.getId()){
			entity = dao.getOperador(entity.getId());
			entity.setOperacao(Operacao.EXIBINDO);
			result.include("entity", entity);
		} else {
			result.include("alert", "Operador não possui acesso a função");
			result.redirectTo(OperadorController.class).lista();
		}
	}
	
	@Restrito
	@Get("/operador/{entity.id}/edita")
	public void edita(Operador entity) {
		if(operadorWeb.getLogado().getAdministrador() || operadorWeb.getLogado().getId() == entity.getId()){
			entity = dao.getOperador(entity.getId());
			entity.setOperacao(Operacao.EDITAR);
			result.include("entity", entity);
		} else {
			result.include("alert", "Operador não possui acesso a função");
			result.redirectTo(OperadorController.class).lista();
		}
	}

	@Restrito
	@Put("/operador")
	public void altera(Operador entity) {
		if(validaFormulario(entity)){
			if(operadorWeb.getLogado() != null){
				entity.setOperadorAlteracao(operadorWeb.getLogado());
				entity.setUltimaAlteracao(new Date());
			}
			try{
				iniciaTransacao(dao.getSession());
				dao.atualiza(entity);
				comitaTransacao(dao.getSession());
				result.include("success", "Operador alterado com sucesso");
				result.redirectTo(OperadorController.class).lista();
			} catch (Exception e){
				System.out.println(e);
				cancelaTransacao(dao.getSession());
				result.include("error", e.getMessage());
			}
		} else {
			result.redirectTo(this).lista();
		}		
	}

	@Restrito
	@Get("/operador/{id}/remove")
	public void remove(Long id) {
		if(operadorWeb.getLogado().getAdministrador() || (operadorWeb.getLogado().getAdministrador() && operadorWeb.getLogado().getId() != id)){
			Operador operador = dao.getOperador(id);
			try{
				iniciaTransacao(dao.getSession());
				dao.remove(operador);
				comitaTransacao(dao.getSession());
				result.redirectTo(this).lista();
			} catch (Exception e){
				cancelaTransacao(dao.getSession());
				result.include("error", "Erro ao excluir: "+e.getMessage());
				result.redirectTo(OperadorController.class).lista();
			}
		} else {
			result.include("alert", "Operador não possui acesso a função");
			result.redirectTo(OperadorController.class).lista();
		}
	}
	
	@Get("/operador/busca.json")
	public void buscaJson(String q) {
		result.use(Results.json()).withoutRoot().from(dao.busca(q)).exclude("id", "nome").serialize();
	}
	
	@Restrito
	@Get("/operador/busca")
	public List<Operador> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}
	
	@Get("/login")
	public void loginForm() {
		Locale.setDefault(new Locale("pt", "br"));
		result.include("language", "pt_BR");
	}
	
	@Post("/login")
	public void login(Operador operador) {
		if(operadorWeb != null){
			Operador carregado = dao.carrega(operador);
			if (carregado == null) {
				validator.add(new ValidationMessage("Login e/ou senha inválidos", "usuario.login"));
				result.include("Login e/ou senha inválidos").include("errorLogin", true);
			}
					
			validator.onErrorUsePageOf(OperadorController.class).loginForm();
			operadorWeb.setLanguage("pt_BR");
			operadorWeb.login(carregado);
			result.redirectTo("/");
		} else {
			operadorWeb.login(operador);
			result.redirectTo("/");
		}
	}
	
	@Path("/logout")
	public void logout() {
		operadorWeb.logout();
		result.redirectTo("/login");
	}
	
	@Get("/teste")
	public void resultadoBusca() {

		ContatoDao contatoDao = new ContatoDao(dao.retornaSession());
		List<Contato> retorno = contatoDao.listaTudo();
	   
		if (retorno != null){
			result.use(json()).from(retorno).serialize();
		} else {
			result.use(json()).from(retorno).serialize();
		}
	}
}
