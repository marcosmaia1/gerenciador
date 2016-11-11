package br.com.gerenciador.infra;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.gerenciador.modeloOld.OperadorOld;

@Component
public class OperadorOldDao {

	private final Session session;

	public Session retornaSession(){
		return session;
	}
	
	public OperadorOldDao(Session session) {
		this.session = session;
	}
	public void salva(OperadorOld operador) {
		Transaction transaction = session.beginTransaction();
		session.save(operador);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	public List<OperadorOld> listaTudo() {
		Criteria criteria = session.createCriteria(OperadorOld.class);
		return criteria.list();
	}

	public OperadorOld getOperadorOld(Long id) {
		return (OperadorOld) session.load(OperadorOld.class, id);
	}

	public void atualiza(OperadorOld operador) {
		Transaction transaction = session.beginTransaction();
		session.update(operador);
		transaction.commit();
	}

	public void remove(OperadorOld operador) {
		Transaction transaction = session.beginTransaction();
		session.delete(operador);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	public List<OperadorOld> busca(String nome) {
		Criteria criteria = session.createCriteria(OperadorOld.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		return criteria.list();
	}

	public void recarrega(OperadorOld operador) {
		session.refresh(operador);
	}
	
}

