package br.com.gerenciador.controller;

import static br.com.caelum.vraptor.view.Results.json;
import static br.com.gerenciador.util.FuncoesUtil.isCNPJ;
import static br.com.gerenciador.util.FuncoesUtil.isCPF;
import static br.com.gerenciador.util.FuncoesUtil.isEmail;
import static br.com.gerenciador.util.FuncoesUtil.retornaSomenteNumeros;
import static br.com.gerenciador.util.FuncoesUtil.strToLong;
import static br.com.gerenciador.util.FuncoesUtil.iniciaTransacao;
import static br.com.gerenciador.util.FuncoesUtil.comitaTransacao;
import static br.com.gerenciador.util.FuncoesUtil.cancelaTransacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.gerenciador.enumeration.Entidade;
import br.com.gerenciador.enumeration.Operacao;
import br.com.gerenciador.enumeration.TipoPessoa;
import br.com.gerenciador.enumeration.UF;
import br.com.gerenciador.infra.CidadeDao;
import br.com.gerenciador.infra.ContatoDao;
import br.com.gerenciador.infra.ContatoOldDao;
import br.com.gerenciador.infra.OperadorDao;
import br.com.gerenciador.modelo.Contato;
import br.com.gerenciador.modelo.Operador;
import br.com.gerenciador.modelo.OperadorWeb;
import br.com.gerenciador.modelo.Restrito;
import br.com.gerenciador.modeloOld.ContatoOld;

@Resource
public class ContatoController {

	private final ContatoDao dao;
	private final Result result;
	private final Validator validator;
	private final OperadorWeb operadorWeb;

	public ContatoController(ContatoDao dao, Result result, Validator validator, OperadorWeb operadorWeb) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.operadorWeb = operadorWeb;
	}
	
	@Restrito
	@Get("/contato")
	public void lista() {
		//importaDados();
		//dao.atualizaCidade();
		//atualicaContato();
		result.include("contatoList", dao.listaTudo()).include("operacao", Operacao.LISTAGEM.getLabel()).include("entidade", Entidade.CONTATO);
	}
	
	public void atualicaContato(){
		List<Contato> contatoLista = dao.listaTudo();
		Transaction transaction = dao.getSession().beginTransaction();
		for(Contato contato : contatoLista){
			Operador operador = new OperadorDao(dao.getSession()).getOperador(Long.parseLong(contato.getNumero()));
			contato.setOperadorCadastro(operador);
			contato.setOperadorAlteracao(operador);
			try{
				dao.salva(contato);
			}catch(Exception e){
				System.out.println(e);
				transaction.rollback();
			}
		}
		transaction.commit();
	}
	
	public void importaDados(){
		List<ContatoOld> contatoOldLista = new ContatoOldDao(dao.getSession()).listaTudo();
		Transaction transaction = dao.getSession().beginTransaction();
		for(ContatoOld old : contatoOldLista){
			Contato novo = dao.getContatoByIdOld(new Long(old.getIdContato()));
			if(novo == null){
				try{
					novo = new Contato();
					//Operador operador = new OperadorDao(dao.getSession()).getOperador(new Long(old.getIdContato()));
					novo.setIdOld(new Long(old.getIdContato()));
					novo.setDataCadastro(new Date());
					novo.setUltimaAlteracao(new Date());
					novo.setDesativado(false);
					//novo.setOperadorAlteracao((operador != null && operador.getId() != null) ? operador : operadorWeb.getLogado());
					//novo.setOperadorCadastro((operador != null && operador.getId() != null) ? operador : operadorWeb.getLogado());
					novo.setOperadorAlteracao(operadorWeb.getLogado());
					novo.setOperadorCadastro(operadorWeb.getLogado());
					novo.setBairro(old.getBairro());
					novo.setComplemento(old.getComplemento());
					novo.setCpfCnpj(retornaSomenteNumeros(old.getCnpj()));
					novo.setEmail(old.getEmail());
					novo.setEmail2(old.getEmail2());
					novo.setEndereco(old.getEndereco());
					novo.setFantasia(old.getFantasia());
					novo.setIndicacao(old.getIndicacao());
					novo.setNome(old.getNome());
					novo.setNumero(old.getIdoperador().getIdOperador()+"");
					novo.setRazao(old.getRazao());
					novo.setSistema(old.getSistema());
					novo.setTel(retornaSomenteNumeros(old.getTel()));
					novo.setTel2(retornaSomenteNumeros(old.getTel2()));
					if(novo.getCpfCnpj().length() == 11){
						novo.setTipoPessoa(TipoPessoa.F);
					} else {
						novo.setTipoPessoa(TipoPessoa.J);
					}
					if(dao.getCidadeByContato(old.getCidade(), old.getEstado()) == null){
						novo.setCidadeNome(old.getCidade());
						novo.setCidade(new CidadeDao(dao.getSession()).getCidade(2678L));
					} else {
						novo.setCidade(dao.getCidadeByContato(old.getCidade(), old.getEstado()));
					}
					
					dao.salva(novo);
				} catch(Exception e){
					System.out.println(e);
					transaction.rollback();
				}
			}
		}
		transaction.commit();
	}
	
	private boolean validaFormulario(Contato entity) {
		boolean resultado = true;
				
		if(entity.getNome() == null || entity.getNome().isEmpty()){
			validator.add(new ValidationMessage("Nome em branco", "error"));
			result.include("errorNome", true);
			resultado = false;
		}
		
		if(entity.getNome() != null && dao.existeCampo(entity.getId(), entity.getNome(), "nome")){
			validator.add(new ValidationMessage("Nome já cadastrado", "error"));
			result.include("errorNome", true);
			resultado = false;
		}
		
		if(entity.getCpfCnpj() != null && dao.existeCampo(entity.getId(), entity.getCpfCnpj(), "cpfCnpj")){
			validator.add(new ValidationMessage("CPF/CNPJ já cadastrado", "error"));
			result.include("errorCpfCnpj", true);
			resultado = false;
		}
		
		if(entity.getCpfCnpj() != null && !entity.getCpfCnpj().isEmpty()){
			if(entity.getTipoPessoa() == TipoPessoa.F && !isCPF(entity.getCpfCnpj())){
				validator.add(new ValidationMessage("CPF inválido", "error"));
				result.include("errorCpfCnpj", true);
				resultado = false;
			} else if(entity.getTipoPessoa() == TipoPessoa.J && !isCNPJ(entity.getCpfCnpj())){
				validator.add(new ValidationMessage("CNPJ inválido", "error"));
				result.include("errorCpfCnpj", true);
				resultado = false;
			}
		}
		
		if(entity.getTel() == null || entity.getTel().isEmpty()){
			validator.add(new ValidationMessage("Telefone em branco", "error"));
			result.include("errorTel", true);
			resultado = false;
		}
		
		if(entity.getEmail() == null || entity.getEmail().isEmpty()){
			validator.add(new ValidationMessage("Email em branco", "error"));
			result.include("errorEmail", true);
			resultado = false;
		}
		
		if(entity.getEmail() != null && !entity.getEmail().isEmpty() && !isEmail(entity.getEmail())){
			validator.add(new ValidationMessage("Email inválido", "error"));
			result.include("errorEmail", true);
			resultado = false;
		}
		
		if(entity.getEmail2() != null && !entity.getEmail2().isEmpty() && !isEmail(entity.getEmail2())){
			validator.add(new ValidationMessage("Email 2 inválido", "error"));
			result.include("errorEmail2", true);
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
			result.include("entity", entity).include("ufList",UF.values()).include("tipoPessoaList", TipoPessoa.values());
			validator.onErrorUsePageOf(this).formulario(entity);
		} else{
			entity.setCidade(dao.getCidadeById(entity.getCidade().getId()));
			entity.setCpfCnpj(retornaSomenteNumeros(entity.getCpfCnpj()));
			entity.setTel(retornaSomenteNumeros(entity.getTel()));
			entity.setTel2(retornaSomenteNumeros(entity.getTel2()));
		}
		
		return resultado;
		
	}

	@Restrito
	@Get("/contato/novo")
	public void formulario(Contato entity) {
		if(entity == null){
			entity = new Contato();
		}
		entity.setDesativado(false);
		entity.setOperacao(Operacao.NOVO);
		result.include("entity", entity)
				.include("ufList",UF.values())
				.include("tipoPessoaList", TipoPessoa.values());
	}
	
	@Restrito
	@Post("/contato")
	public void adiciona(Contato entity) {
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
				result.include("success", "Contato cadastrado com sucesso")
						.redirectTo(ContatoController.class).lista();
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
	@Get("/contato/{entity.id}")
	public void exibir(Contato entity) {
		entity = dao.getContato(entity.getId());
		entity.setOperacao(Operacao.EXIBINDO);
		result.include("entity", entity).include("ufList",UF.values()).include("tipoPessoaList", TipoPessoa.values());
	}
	
	@Restrito
	@Get("/contato/{entity.id}/edita")
	public void edita(Contato entity) {
		entity = dao.getContato(entity.getId());
		entity.setOperacao(Operacao.EDITAR);
		result.include("entity", entity).include("ufList",UF.values()).include("tipoPessoaList", TipoPessoa.values());
	}

	@Restrito
	@Put("/contato")
	public void altera(Contato entity) {
		if(validaFormulario(entity)){
			if(operadorWeb.getLogado() != null){
				entity.setOperadorAlteracao(operadorWeb.getLogado());
				entity.setUltimaAlteracao(new Date());
			}
			try{
				iniciaTransacao(dao.getSession());
				dao.atualiza(entity);
				comitaTransacao(dao.getSession());
				result.include("success", "Contato alterado com sucesso");
				result.redirectTo(ContatoController.class).lista();
			} catch (Exception e){
				System.out.println(e);
				cancelaTransacao(dao.getSession());
				result.include("error", e.getMessage()).redirectTo(ContatoController.class).edita(entity);
			}
		} else {
			result.redirectTo(this).lista();
		}
	}

	@Restrito
	@Get("/contato/{id}/remove")
	public void remove(Long id) {
		Contato contato = dao.getContato(id);
		try{
			iniciaTransacao(dao.getSession());
			dao.remove(contato);
			comitaTransacao(dao.getSession());
		} catch (Exception e){
			System.out.println(e);
			cancelaTransacao(dao.getSession());
			result.include("error", e.getMessage());
		}
		result.include("success", "Contato excluído com sucesso").redirectTo(this).lista();
	}
	
	@Post("/contato/jsonObjContato")
	public void buscaJson(String id) {
		Contato contato = new Contato(); 
		contato = dao.getContato(strToLong(id));
		List<String> retorno = new ArrayList<String>();
		   
		if (contato != null){
			retorno.add(contato.getNome());
			retorno.add(contato.getRazao() == null ? "" : contato.getRazao());
			retorno.add(contato.getTel());
			retorno.add(contato.getTel2() == null ? "" : contato.getTel2());
			retorno.add(contato.getEmail());
			retorno.add(contato.getEmail2() == null ? "" : contato.getEmail2());
		}else{
			retorno.add("");
			retorno.add("");
			retorno.add("");
			retorno.add("");
			retorno.add("");
			retorno.add("");
		}
		result.use(json()).from(retorno).serialize();
	}
	
	@Restrito
	@Get("/contato/exportaContatos")
	public List<Contato> exportaContatos(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
		
	}
	
	@Restrito
	@Get("/contato/busca")
	public List<Contato> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
		
	}
}

