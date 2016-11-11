package br.com.gerenciador.infra;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.gerenciador.enumeration.UF;
import br.com.gerenciador.modelo.Cidade;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class CidadeDao {

	private Session session;

	public CidadeDao(Session session) {
		this.session = session;
	}

	public void salva(Cidade cidade) {
		Transaction transaction = session.beginTransaction();
		session.save(cidade);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> listaTudo() {
		Criteria criteria = session.createCriteria(Cidade.class);
		return criteria.list();
	}

	public Cidade getCidade(Long id) {
		return (Cidade) session.load(Cidade.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> getCidadeByUf(String uf) {
		Criteria criteria = session.createCriteria(Cidade.class);
		criteria.add(Restrictions.eq("uf", UF.valueOf(uf)));
		criteria.addOrder(Order.asc("nome"));
		return (List<Cidade>) criteria.list();
	}
	
	public Cidade getCidadeByNameUf(String nome, UF uf) {
		Criteria criteria = session.createCriteria(Cidade.class);
		if(uf != null){
			criteria.add(Restrictions.eq("uf", uf));
		}
		criteria.add(Restrictions.ilike("nome", nome));
		return (Cidade) criteria.uniqueResult();
	}

	public void atualiza(Cidade cidade) {
		Transaction transaction = session.beginTransaction();
		session.update(cidade);
		transaction.commit();
	}

	public void remove(Cidade cidade) {
		Transaction transaction = session.beginTransaction();
		session.delete(cidade);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> busca(String nome) {
		Criteria criteria = session.createCriteria(Cidade.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		return criteria.list();
	}

}

