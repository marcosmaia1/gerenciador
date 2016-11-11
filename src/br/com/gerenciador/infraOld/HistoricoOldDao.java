package br.com.gerenciador.infraOld;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.gerenciador.modeloOld.HistoricoOld;

@Component
public class HistoricoOldDao {

	private Session session;

	public HistoricoOldDao(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void salva(HistoricoOld historico) {
		Transaction transaction = session.beginTransaction();
		session.save(historico);
		transaction.commit();
	}

	public HistoricoOld getHistoricoOld(Long id) {
		return (HistoricoOld) session.load(HistoricoOld.class, id);
	}

	public void atualiza(HistoricoOld historico) {
		Transaction transaction = session.beginTransaction();
		session.update(historico);
		transaction.commit();
	}

	public void remove(HistoricoOld historico) {
		Transaction transaction = session.beginTransaction();
		session.delete(historico);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	public List<HistoricoOld> busca(String comentario) {
		Criteria criteria = session.createCriteria(HistoricoOld.class);
		criteria.add(Restrictions.ilike("comentario", comentario, MatchMode.ANYWHERE));
		return criteria.list();
	}

	public void recarrega(HistoricoOld historico) {
		session.refresh(historico);
	}
}

