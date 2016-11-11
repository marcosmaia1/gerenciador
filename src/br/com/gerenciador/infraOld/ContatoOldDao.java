package br.com.gerenciador.infraOld;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.gerenciador.modeloOld.ContatoOld;

@Component
public class ContatoOldDao {

	private Session session;
	

	public ContatoOldDao(Session session) {
		this.session = session;
	}

	public void salva(ContatoOld contato) {
		Transaction transaction = session.beginTransaction();
		session.save(contato);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	public List<ContatoOld> listaTudo() {
		Criteria criteria = session.createCriteria(ContatoOld.class);
		return criteria.list();
	}

	public ContatoOld getContatoOld(Long id) {
		Criteria criteria = session.createCriteria(ContatoOld.class);
		criteria.add(Restrictions.eq("idcontato", id));
		ContatoOld contato = (ContatoOld) criteria.uniqueResult();
		
		return contato;
	}

	public void atualiza(ContatoOld contato) {
		Transaction transaction = session.beginTransaction();
		session.update(contato);
		transaction.commit();
	}

	public void remove(ContatoOld contato) {
		Transaction transaction = session.beginTransaction();
		session.delete(contato);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	public List<ContatoOld> busca(String nome) {
		Criteria criteria = session.createCriteria(ContatoOld.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		return criteria.list();
	}

	public void recarrega(ContatoOld contato) {
		session.refresh(contato);
	}
	
	
}

