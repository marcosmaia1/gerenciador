package br.com.gerenciador.infra;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.gerenciador.modelo.Historico;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class HistoricoDao {

	private Session session;

	public HistoricoDao(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}



	public void salva(Historico historico) {
		session.save(historico);
	}

	@SuppressWarnings("unchecked")
	public List<Historico> listaTudo(Long idNegociacao) {
		Criteria criteria = session.createCriteria(Historico.class);
		criteria.add(Restrictions.eq("negociacao.id", idNegociacao));
		criteria.addOrder(Order.asc("dataCadastro"));
		
		return criteria.list();
	}

	public Historico getHistorico(Long id) {
		return (Historico) session.load(Historico.class, id);
	}

	public void atualiza(Historico historico) {
		session.update(historico);
	}

	public void remove(Historico historico) {
		Transaction transaction = session.beginTransaction();
		session.delete(historico);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Historico> busca(String comentario) {
		Criteria criteria = session.createCriteria(Historico.class);
		criteria.add(Restrictions.ilike("comentario", comentario, MatchMode.ANYWHERE));
		return criteria.list();
	}

	public void recarrega(Historico historico) {
		session.refresh(historico);
	}
}

