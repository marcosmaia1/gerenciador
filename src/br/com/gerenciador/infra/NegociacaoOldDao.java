package br.com.gerenciador.infra;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.gerenciador.modelo.Operador;
import br.com.gerenciador.modeloOld.NegociacaoOld;

@Component
public class NegociacaoOldDao {

	private Session session;

	public NegociacaoOldDao(Session session) {
		this.session = session;
	}

	public void salva(NegociacaoOld negociacao) {
		Transaction transaction = session.beginTransaction();
		session.save(negociacao);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	public List<NegociacaoOld> listaTudo(Operador operador) {
		Criteria criteria = session.createCriteria(NegociacaoOld.class);
		return criteria.list();
	}

	public NegociacaoOld getNegociacaoOld(Long id) {
		Criteria criteria = session.createCriteria(NegociacaoOld.class);
		criteria.add(Restrictions.eq("idnegociacao", id));
		return (NegociacaoOld) criteria.uniqueResult();
	}

	public void atualiza(NegociacaoOld negociacao) {
		Transaction transaction = session.beginTransaction();
		session.merge(negociacao);
		transaction.commit();
	}

	public void remove(NegociacaoOld negociacao) {
		Transaction transaction = session.beginTransaction();
		session.delete(negociacao);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	public List<NegociacaoOld> busca(String obs) {
		Criteria criteria = session.createCriteria(NegociacaoOld.class);
		criteria.add(Restrictions.ilike("obs", obs, MatchMode.ANYWHERE));
		return criteria.list();
	}

	public void recarrega(NegociacaoOld negociacao) {
		session.refresh(negociacao);
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}

