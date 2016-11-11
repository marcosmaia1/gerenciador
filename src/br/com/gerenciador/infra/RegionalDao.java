package br.com.gerenciador.infra;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.gerenciador.modelo.Regional;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class RegionalDao {

	private Session session;

	public RegionalDao(Session session) {
		this.session = session;
	}

	public void salva(Regional regional) {
		session.save(regional);
	}

	@SuppressWarnings("unchecked")
	public List<Regional> listaTudo() {
		Criteria criteria = session.createCriteria(Regional.class);
		return criteria.list();
	}

	public Regional getRegional(Long id) {
		return (Regional) session.load(Regional.class, id);
	}
	
	public Regional getRegionalByNome(String nome) {
		Criteria criteria = session.createCriteria(Regional.class);
		criteria.add(Restrictions.ilike("descricao", nome, MatchMode.EXACT));

		return (Regional) criteria.uniqueResult();
	}

	public void atualiza(Regional regional) {
		session.update(regional);
	}

	public void remove(Regional regional) {
		session.delete(regional);
	}

	@SuppressWarnings("unchecked")
	public List<Regional> busca(String descricao) {
		Criteria criteria = session.createCriteria(Regional.class);
		criteria.add(Restrictions.ilike("descricao", descricao, MatchMode.ANYWHERE));
		return criteria.list();
	}

	public void recarrega(Regional regional) {
		session.refresh(regional);
	}
	
	public boolean existeDescricao(Regional entity){
		Criteria criteria = session.createCriteria(Regional.class);
		if(entity.getId() != null){
			criteria.add(Restrictions.not(Restrictions.eq("id", entity.getId())));
		}
		criteria.add(Restrictions.ilike("descricao", entity.getDescricao().trim(), MatchMode.EXACT));

		return criteria.uniqueResult() != null;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}

