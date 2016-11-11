package br.com.gerenciador.infra;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.gerenciador.modelo.Operador;

@Component
public class OperadorDao {

	private final Session session;

	public Session retornaSession(){
		return session;
	}
	
	public OperadorDao(Session session) {
		this.session = session;
	}
	public void salva(Operador operador) {
		session.save(operador);
	}

	@SuppressWarnings("unchecked")
	public List<Operador> listaTudo() {
		Criteria criteria = session.createCriteria(Operador.class);
		return criteria.list();
	}

	public Operador getOperador(Long id) {
		return (Operador) session.load(Operador.class, id);
	}

	public void atualiza(Operador operador) {
		session.merge(operador);
	}

	public void remove(Operador operador) {
		session.delete(operador);
	}

	@SuppressWarnings("unchecked")
	public List<Operador> busca(String nome) {
		Criteria criteria = session.createCriteria(Operador.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		return criteria.list();
	}

	public void recarrega(Operador operador) {
		session.refresh(operador);
	}
	
	public Operador carrega(Operador operador) {
		Criteria criteria = session.createCriteria(Operador.class);
		criteria.add(Restrictions.eq("login", operador.getLogin()));
		criteria.add(Restrictions.eq("senha", operador.getSenha()));
		return (Operador) criteria.uniqueResult();
		}
	
	public boolean existeCampo(Long id, String texto, String campo){
		Criteria criteria = session.createCriteria(Operador.class);
		if(id != null){
			criteria.add(Restrictions.not(Restrictions.eq("id", id)));
		}
		
		criteria.add(Restrictions.ilike(campo, texto, MatchMode.EXACT));

		return criteria.uniqueResult() != null;
	}

	public Session getSession() {
		return session;
	}
	
}

