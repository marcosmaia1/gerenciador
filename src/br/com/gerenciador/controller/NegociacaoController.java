package br.com.gerenciador.controller;

import java.io.File;
import java.math.BigDecimal;
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
import br.com.gerenciador.enumeration.CamposRelatorio;
import br.com.gerenciador.enumeration.Entidade;
import br.com.gerenciador.enumeration.Fechamento;
import br.com.gerenciador.enumeration.Operacao;
import br.com.gerenciador.enumeration.StatusNegociacao;
import br.com.gerenciador.infra.ContatoDao;
import br.com.gerenciador.infra.HistoricoDao;
import br.com.gerenciador.infra.HistoricoOldDao;
import br.com.gerenciador.infra.NegociacaoDao;
import br.com.gerenciador.infra.NegociacaoOldDao;
import br.com.gerenciador.infra.OperadorDao;
import br.com.gerenciador.infra.RegionalDao;
import br.com.gerenciador.modelo.Contato;
import br.com.gerenciador.modelo.Historico;
import br.com.gerenciador.modelo.Negociacao;
import br.com.gerenciador.modelo.Relatorio;
import br.com.gerenciador.modelo.RelatorioFiltros;
import br.com.gerenciador.modelo.OperadorWeb;
import br.com.gerenciador.modelo.Restrito;
import br.com.gerenciador.modeloOld.HistoricoOld;
import br.com.gerenciador.modeloOld.NegociacaoOld;

import static br.com.gerenciador.util.FuncoesUtil.iniciaTransacao;
import static br.com.gerenciador.util.FuncoesUtil.comitaTransacao;
import static br.com.gerenciador.util.FuncoesUtil.cancelaTransacao;

@Resource
public class NegociacaoController {

	private final NegociacaoDao dao;
	private final Result result;
	private final Validator validator;
	private final OperadorWeb operadorWeb;

	public NegociacaoController(NegociacaoDao dao, Result result, Validator validator, OperadorWeb operadorWeb) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.operadorWeb = operadorWeb;
	}
	
	@Restrito
	@Get("/index")
	public void paginaInicial() {
		result.include("negociacaoEmAndamento", dao.listaNegociacao(operadorWeb.getLogado(), StatusNegociacao.EM_ANDAMENTO));
		result.include("negociacaoConcluido", dao.listaNegociacao(operadorWeb.getLogado(), StatusNegociacao.CONCLUIDO));
		result.include("negociacaoProspectado", dao.listaNegociacao(operadorWeb.getLogado(), StatusNegociacao.PROSPECTADO));
		result.include("negociacaoSemSucesso", dao.listaNegociacao(operadorWeb.getLogado(), StatusNegociacao.CONCLUIDO_SEM_SUCESSO));
	}	
	
	@Restrito
	@Get("/negociacao")
	public void lista() {
		//importaDados();
		//importaHist();
		result.include("negociacaoList", dao.listaTudo(operadorWeb.getLogado()))
				.include("operacao", Operacao.LISTAGEM.getLabel())
				.include("entidade", Entidade.NEGOCIACAO);
	}
	
	public void importaHist(){
		List<HistoricoOld> historicoOldLista = new HistoricoOldDao(dao.getSession()).listaTudo();
		Transaction transaction = dao.getSession().beginTransaction();
		for(HistoricoOld old : historicoOldLista){
			Historico novo = new Historico();
			novo.setDataCadastro(old.getData());
			novo.setUltimaAlteracao(old.getData());
			novo.setOperadorAlteracao(new OperadorDao(dao.getSession()).getOperador(new Long(old.getIdoperador().getIdOperador())));
			novo.setOperadorCadastro(new OperadorDao(dao.getSession()).getOperador(new Long(old.getIdoperador().getIdOperador())));

			novo.setNegociacao(dao.getNegociacao(new Long(old.getIdNegociacao().getIdNegociacao())));
			novo.setComentario(old.getComentario());
			
			try{
				new HistoricoDao(dao.getSession()).salva(novo);
			} catch (Exception e){
				System.out.println(e);
				transaction.rollback();
			}
		}
		transaction.commit();
	}
	
	public void importaDados(){
		List<NegociacaoOld> negociacaoOldLista = new NegociacaoOldDao(dao.getSession()).listaTudo(operadorWeb.getLogado());
		Transaction transaction = dao.getSession().beginTransaction();
		for(NegociacaoOld old : negociacaoOldLista){
			Negociacao novo = new Negociacao();
			novo.setIdOld(new Long(old.getIdNegociacao()));
			novo.setDataCadastro(old.getData());
			novo.setUltimaAlteracao(old.getData());
			novo.setOperadorAlteracao(new OperadorDao(dao.getSession()).getOperador(new Long(old.getIdoperador().getIdOperador())));
			novo.setOperadorCadastro(new OperadorDao(dao.getSession()).getOperador(new Long(old.getIdoperador().getIdOperador())));
			novo.setContato(new ContatoDao(dao.getSession()).getContato(new Long(old.getIdcontato().getIdContato())));
			novo.setDataInicio(old.getData());
			novo.setObs(old.getObs());
			novo.setRegional(new RegionalDao(dao.getSession()).getRegionalByNome(old.getRegional()));
			
			String status = old.getStatus();
			if(status.equals("EA")){
				novo.setStatus(StatusNegociacao.EM_ANDAMENTO);
			} else if(status.equals("C")){
				novo.setStatus(StatusNegociacao.CONCLUIDO);
			} else if(status.equals("CS")){
				novo.setStatus(StatusNegociacao.CONCLUIDO_SEM_SUCESSO);
			} else {
				novo.setStatus(StatusNegociacao.PROSPECTADO);
			}
			
			String fechamento = old.getFechamento();
			if(fechamento.equals("10%")){
				novo.setFechamento(Fechamento.F10);
			} else if(fechamento.equals("30%")){
				novo.setFechamento(Fechamento.F30);
			} else if(fechamento.equals("60%")){
				novo.setFechamento(Fechamento.F60);
			} else if(fechamento.equals("90%")){
				novo.setFechamento(Fechamento.F90);
			} else if(fechamento.equals("100%")){
				novo.setFechamento(Fechamento.F100);
			}
			
			novo.setValorFechamento(new BigDecimal(0));
			
			try{
				dao.salva(novo);
			} catch (Exception e){
				System.out.println(e);
				transaction.rollback();
			}
		}
		transaction.commit();
	}

	private boolean validaFormulario(Negociacao entity) {
		boolean resultado = true;
		
		if(entity.getRegional() == null || entity.getRegional().getId() == null){
			validator.add(new ValidationMessage("Regional inválida", "error"));
			result.include("errorRegional", true);
			resultado = false;
		}
		
		if(entity.getRegional().getId()!= null && entity.getRegional().getId() < 1L){
			validator.add(new ValidationMessage("Regional em branco", "error"));
			result.include("errorRegional", true);
			resultado = false;
		}
		
		if(entity.getContato() == null || entity.getContato().getId() == null){
			validator.add(new ValidationMessage("Contato inválido", "error"));
			result.include("errorContato", true);
			resultado = false;
		}
		
		if(entity.getContato().getId() != null && entity.getContato().getId() < 1L){
			validator.add(new ValidationMessage("Contato em branco", "error"));
			result.include("errorContato", true);
			resultado = false;
		}
		
		entity.setDataInicio(new Date());
		if(entity.getDataInicio() == null){
			validator.add(new ValidationMessage("Data em branco", "error"));
			result.include("errorDataInicio", true);
			resultado = false;
		}
		
		if(entity.getDataInicio() != null && entity.getDataInicio().after(new Date())){
			validator.add(new ValidationMessage("Data maior que a data atual", "error"));
			result.include("errorData", true);
			resultado = false;
		}
		
		if((entity.getOperacao() != Operacao.NOVO) && (entity.getStatus() == StatusNegociacao.CONCLUIDO) && (entity.getValorFechamento() != null && entity.getValorFechamento().doubleValue() <= 0)){
			validator.add(new ValidationMessage("Valor de fechamento deve ser maior que zero", "error"));
			result.include("errorValorFechamento", true);
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
			result.include("entity", entity)
					.include("statusList", StatusNegociacao.values())
					.include("regionalList", new RegionalDao(dao.getSession()).listaTudo());
			if(entity.getOperacao() != Operacao.NOVO){
				result.include("historicoList", new HistoricoDao(dao.getSession()).listaTudo(entity.getId()))
						.include("histEntidade", Entidade.HISTORICO)
						.include("fechamentoList", Fechamento.values())
						.include("operadorWeb", operadorWeb);
			} if(entity.getOperacao() == Operacao.NOVO){
				result.include("contatoList", new ContatoDao(dao.getSession()).listaTudo());
			}
				validator.onErrorUsePageOf(this).formulario(entity);
		} else{
			entity.setRegional(new RegionalDao(dao.getSession()).getRegional(entity.getRegional().getId()));
			entity.setContato(new ContatoDao(dao.getSession()).getContato(entity.getContato().getId()));
		}
		
		if(entity.getOperacao() == Operacao.NOVO){
			validator.onErrorUsePageOf(this).formulario(entity);
		}else{
			validator.onErrorUsePageOf(this).edita(entity);
		}
		
		return resultado;
		
	}
	
	@Restrito
	@Get("/negociacao/novo")
	public void formulario(Negociacao entity) {
		if(entity == null){
			entity = new Negociacao();
			entity.setDataInicio(new Date());
			entity.setDataInicio(new Date());
			entity.setValorFechamento(new BigDecimal(0));
		}
		entity.setOperacao(Operacao.NOVO);
		result.include("entity", entity)
				.include("statusList", StatusNegociacao.values())
				.include("regionalList", new RegionalDao(dao.getSession()).listaTudo())
				.include("contatoList", new ContatoDao(dao.getSession()).listaTudo());
	}
	
	@Restrito
	@Post("/negociacao")
	public void adiciona(Negociacao entity) {
		if(validaFormulario(entity)){
			if(operadorWeb.getLogado() != null){
				entity.setOperadorCadastro(operadorWeb.getLogado());
				entity.setOperadorAlteracao(operadorWeb.getLogado());
				entity.setDataCadastro(new Date());
				entity.setUltimaAlteracao(new Date());
				entity.setFechamento(Fechamento.F10);
				entity.setValorFechamento(new BigDecimal(0));
			}
			try{
				iniciaTransacao(dao.getSession());
				dao.salva(entity);
				comitaTransacao(dao.getSession());
				dao.recarrega(entity);
				result.include("success", "Negociação adicionada com sucesso");
				result.redirectTo(NegociacaoController.class).edita(entity);
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
	@Get("/negociacao/{entity.id}")
	public void exibir(Negociacao entity) {
		entity = dao.getNegociacao(entity.getId());
		entity.setOperacao(Operacao.EXIBINDO);
		if(entity.getHistorico() == null){
			entity.setHistorico(new HistoricoDao(dao.getSession()).listaTudo(entity.getId()));
		}
		result.include("entity", entity)
				.include("statusList", StatusNegociacao.values())
				.include("regionalList", new RegionalDao(dao.getSession()).listaTudo())
				.include("fechamentoList", Fechamento.values())
				.include("histEntidade", Entidade.HISTORICO);
	}
	
	@Restrito
	@Get("/negociacao/{entity.id}/edita")
	public void edita(Negociacao entity) {
		entity = dao.getNegociacao(entity.getId());
		if(entity.getStatus() == StatusNegociacao.CONCLUIDO){
			result.include("info", "Não é possivel editar uma negociação concluída.")
					.redirectTo(this).lista();
		} else {
			entity.setOperacao(Operacao.EDITAR);
			if(entity.getHistorico() == null){
				entity.setHistorico(new HistoricoDao(dao.getSession()).listaTudo(entity.getId()));
			}
			result.include("entity", entity)
			.include("statusList", StatusNegociacao.values())
			.include("regionalList", new RegionalDao(dao.getSession()).listaTudo())
			.include("fechamentoList", Fechamento.values())
			.include("histEntidade", Entidade.HISTORICO);
		}
	}

	@Restrito
	@Put("/negociacao")
	public void altera(Negociacao entity) {
		if(validaFormulario(entity)){
			if(operadorWeb.getLogado() != null){
				entity.setOperadorAlteracao(operadorWeb.getLogado());
				entity.setUltimaAlteracao(new Date());
			}
			try{
				iniciaTransacao(dao.getSession());
				Historico historico = gravaHistoricoAlteracao(entity);
				if(entity.getHistorico() == null){
					entity.setHistorico(new HistoricoDao(dao.getSession()).listaTudo(entity.getId()));
				}
				dao.atualiza(entity);
				if(historico.getComentario() != null){
					historico.setNegociacao(dao.getNegociacao(entity.getId()));
					dao.salva(historico);
				}
				comitaTransacao(dao.getSession());
				dao.recarrega(entity);
				result.include("success", "Negociação alterada com sucesso")
						.redirectTo(this).exibir(entity);
			} catch (Exception e){
				System.out.println(e);
				cancelaTransacao(dao.getSession());
				result.include("error", e.getMessage())
						.redirectTo(this).exibir(entity);
			}
		} else {
			result.redirectTo(this).formulario(entity);
		}
	}
	
	public Historico gravaHistoricoAlteracao(Negociacao entity){
		Negociacao negociacaoOld = dao.getNegociacao(entity.getId());
		Historico historico = new Historico();
		if(negociacaoOld != null && entity.getStatus() != negociacaoOld.getStatus()){
			historico.setComentario("Status da negociação alterado de "+negociacaoOld.getStatus().getLabel()+" para "+entity.getStatus().getLabel()+" pelo usuário "+operadorWeb.getNome());
			historico.setDataCadastro(new Date());
			historico.setNegociacao(entity);
			historico.setOperadorAlteracao(operadorWeb.getLogado());
			historico.setOperadorCadastro(operadorWeb.getLogado());
			historico.setUltimaAlteracao(new Date());
		}
		return historico;
	}
	
	@Restrito
	@Get("/negociacao/relatorio")
	public void relatorio(RelatorioFiltros entity){
		if(entity.getRelatorioList() == null){
			entity = new RelatorioFiltros();
		}
		entity.setOperacao(Operacao.IMPORTACAO_DADOS);
		entity.setOperacao(Operacao.RELATORIO);
		result.include("statusList", StatusNegociacao.values())
				.include("ordenacaoCampoList", CamposRelatorio.values())
				.include("ordenacaoAgrupamentoList", CamposRelatorio.values())
				.include("operadorList", new OperadorDao(dao.getSession()).listaTudo())
				.include("regionalList", new RegionalDao(dao.getSession()).listaTudo())
				.include("entity", entity);	
	}
	
	@Restrito
	@Post("/negociacao/relatorio")
	public void carregaRelatorio(RelatorioFiltros entity){
		if(validaRelatorio(entity)){
			try{
			entity.setOperacao(Operacao.RELATORIO);
			entity.setRelatorioList(dao.carregaRelatorio(entity, operadorWeb.getLogado()));
			result.redirectTo(this).relatorio(entity);
			} catch (Exception e){
				validator.onErrorUsePageOf(this).relatorio(entity);
				result.redirectTo(this).relatorio(entity);
			}
		} else {
			result.redirectTo(this).relatorio(entity);
		}
	}
	
	@Restrito
	@Post("/negociacao/relatorio.pdf")
	public File downloadRelatorio(RelatorioFiltros entity){
		Relatorio relatorio = new Relatorio();
		try {
			return relatorio.imprimir(dao.carregaRelatorio(entity, operadorWeb.getLogado()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Restrito
	@Get("/negociacao/exportacaoClientes.csv")
	public File downloadArquivoExportacao(){
		List<Contato> contatosList = dao.retornaListaExportacao();
		Relatorio arquivo = new Relatorio();
		try {
			return arquivo.geraListaContatos(contatosList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean validaRelatorio(RelatorioFiltros relatorio){
		return true;
	}
	
}
