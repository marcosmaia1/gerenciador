package br.com.gerenciador.infra;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.gerenciador.enumeration.CamposRelatorio;
import br.com.gerenciador.enumeration.StatusNegociacao;
import br.com.gerenciador.modelo.Contato;
import br.com.gerenciador.modelo.Historico;
import br.com.gerenciador.modelo.Negociacao;
import br.com.gerenciador.modelo.Operador;
import br.com.gerenciador.modelo.RelatorioFiltros;

@Component
public class NegociacaoDao {

	private Session session;

	public NegociacaoDao(Session session) {
		this.session = session;
	}

	public void salva(Negociacao negociacao) {
		session.save(negociacao);
	}
	
	public void salva(Historico historico) {
		session.save(historico);
	}

	@SuppressWarnings("unchecked")
	public List<Negociacao> listaTudo(Operador operador) {
		Criteria criteria = session.createCriteria(Negociacao.class);
		criteria.createAlias("regional", "regional")
				.createAlias("contato", "contato")
				.createAlias("operadorCadastro", "operadorCadastro");
		if(!operador.getAdministrador()){
			criteria.add(Restrictions.eq("operadorCadastro", operador));
		}
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Negociacao> carregaRelatorio(RelatorioFiltros filtros, Operador operador) {
		Criteria criteria = session.createCriteria(Negociacao.class);
		criteria.createAlias("regional", "regional")
				.createAlias("contato", "contato")
				.createAlias("operadorCadastro", "operadorCadastro");
		
		if(filtros.getCampoAgrupamento() != null){
			//criteria.add(Restrictions.eq("status", filtros.getStatus()));
		}
		
		if(filtros.getCampoOrdenacao() != null && filtros.getCampoOrdenacao() != CamposRelatorio.NENHUM){
			switch (filtros.getCampoOrdenacao()) {
			case CONTATO: criteria.addOrder(Order.asc("contato.nome"));  
				break;
			case OPERADOR: criteria.addOrder(Order.asc("operadorCadastro.nome"));
				break;
			case REGIONAL: criteria.addOrder(Order.asc("regional.descricao"));
				break;
			case DATA: criteria.addOrder(Order.asc("dataInicio"));
				break;
			case STATUS_NEGOCIACAO: criteria.addOrder(Order.asc("status"));
				break;
			case NENHUM:
				break;
			}
		}
		
		if(filtros.getContato().getId() != null && filtros.getContato().getId() > 0){
			criteria.add(Restrictions.eq("contato.id", filtros.getContato().getId()));
		}
		
		if(filtros.getDataInicial() != null && filtros.getDataFinal() != null && filtros.getDataInicial().before(filtros.getDataFinal())){
			criteria.add(Restrictions.ge("dataInicio", filtros.getDataInicial())); 
			criteria.add(Restrictions.lt("dataInicio", filtros.getDataFinal()));
		} else if(filtros.getDataInicial() != null && filtros.getDataFinal() == null){
			criteria.add(Restrictions.ge("dataInicio", filtros.getDataInicial()));
		} else if(filtros.getDataFinal() != null && filtros.getDataInicial() == null){
			criteria.add(Restrictions.lt("dataInicio", filtros.getDataFinal()));
		}
		
		if(operador.getAdministrador() && filtros.getOperador().getId() != null && filtros.getOperador().getId() > 0){
			criteria.add(Restrictions.eq("operadorCadastro.id", filtros.getOperador().getId()));
		}
		
		if(filtros.getRegional().getId() != null && filtros.getRegional().getId() > 0){
			criteria.add(Restrictions.eq("regional.id", filtros.getRegional().getId()));
		}
		
		if(filtros.getStatus() != null && filtros.getStatus() != StatusNegociacao.NENHUM){
			criteria.add(Restrictions.eq("status", filtros.getStatus()));
		}
		
		if(!operador.getAdministrador()){
			criteria.add(Restrictions.eq("operadorCadastro", operador));
		}
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Negociacao> listaNegociacao(Operador operador, StatusNegociacao status) {
		Criteria criteria = session.createCriteria(Negociacao.class);
		if(!operador.getAdministrador()){
			criteria.add(Restrictions.eq("operadorCadastro", operador));
		}
		criteria.add(Restrictions.eq("status", status));
		criteria.addOrder(Order.desc("dataInicio"));
		
		return criteria.setMaxResults(20).list();
	}

	public Negociacao getNegociacao(Long id) {
		Criteria criteria = session.createCriteria(Negociacao.class);
		criteria.add(Restrictions.eq("id", id));
		return (Negociacao) criteria.uniqueResult();
	}

	public void atualiza(Negociacao negociacao) {
		session.merge(negociacao);
	}

	@SuppressWarnings("unchecked")
	public List<Negociacao> busca(String obs) {
		Criteria criteria = session.createCriteria(Negociacao.class);
		criteria.add(Restrictions.ilike("obs", obs, MatchMode.ANYWHERE));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Contato> retornaListaExportacao(){
		Criteria criteria = session.createCriteria(Negociacao.class);
		criteria.add(Restrictions.eq("status", StatusNegociacao.CONCLUIDO));
		if(criteria.list() != null){
			List<Negociacao> resultado = (List<Negociacao>) criteria.list();
			List<Contato> listaContatos = new ArrayList<Contato>();
			for(Negociacao negociacao : resultado){
				if(!listaContatos.contains(negociacao.getContato())){
					listaContatos.add(negociacao.getContato());
				}
			}
			return listaContatos;
		} else {
			return null;
		}
	}

	public void recarrega(Negociacao negociacao) {
		session.refresh(negociacao);
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}

