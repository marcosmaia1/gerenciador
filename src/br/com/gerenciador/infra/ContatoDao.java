package br.com.gerenciador.infra;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.gerenciador.enumeration.UF;
import br.com.gerenciador.modelo.Cidade;
import br.com.gerenciador.modelo.Contato;
import br.com.gerenciador.util.FuncoesUtil;

@Component
public class ContatoDao {

	private Session session;
	
	public boolean existeCampo(Long id, String texto, String campo){
		Criteria criteria = session.createCriteria(Contato.class);
		if(id != null){
			criteria.add(Restrictions.not(Restrictions.eq("id", id)));
		}
		
		criteria.add(Restrictions.ilike(campo, texto, MatchMode.EXACT));

		return criteria.uniqueResult() != null;
	}

	public ContatoDao(Session session) {
		this.session = session;
	}

	public void salva(Contato contato) {
		session.save(contato);
	}

	@SuppressWarnings("unchecked")
	public List<Contato> listaTudo() {
		Criteria criteria = session.createCriteria(Contato.class);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	public void atualizaCidade() {
		List<Contato> listaContatos = this.listaTudo();
		for(Contato contato : listaContatos){
				try{
					if(contato.getCidade() == null){	
						contato.setCidade(new CidadeDao(session).getCidadeByNameUf(contato.getCidadeNome(), null));
						this.atualiza(contato);
					}   else {
						contato.setCidadeNome(null);
						this.atualiza(contato);
					}
				} catch (Exception e){
					System.out.println(e);
				}
			 
		}
	}
	
	public Cidade getCidadeByContato(String nomeCidade, String nomeEstado) {
		try{
			Cidade cidade = new CidadeDao(session).getCidadeByNameUf(nomeCidade, UF.valueOf(nomeEstado));
			if(cidade == null){
				return null;
			} else{
				return cidade;
			}
			
		} catch (Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public void atualizaCpfCnpj() {
		List<Contato> listaContatos = this.listaTudo();
		for(Contato contato : listaContatos){
				try{
					if(contato.getCpfCnpj() != null){
						contato.setCpfCnpj(FuncoesUtil.retornaSomenteNumeros(contato.getCpfCnpj()));
					}
					if(contato.getTel() != null){
						contato.setTel(FuncoesUtil.retornaSomenteNumeros(contato.getTel()));
					}
					if(contato.getTel2() != null){
						contato.setTel2(FuncoesUtil.retornaSomenteNumeros(contato.getTel2()));
					}
					this.atualiza(contato);
				} catch (Exception e){
					System.out.println(e);
				}
			 
		}
	}

	public Contato getContato(Long id) {
		Criteria criteria = session.createCriteria(Contato.class);
		criteria.add(Restrictions.eq("id", id));
		Contato contato = (Contato) criteria.uniqueResult();
		
		return contato;
	}
	
	public Contato getContatoByIdOld(Long id) {
		Criteria criteria = session.createCriteria(Contato.class);
		criteria.add(Restrictions.eq("idOld", id));
		Contato contato = (Contato) criteria.uniqueResult();
		
		return contato;
	}

	public void atualiza(Contato contato) {
		session.merge(contato);
	}

	public void remove(Contato contato) {
		session.delete(contato);
	}

	@SuppressWarnings("unchecked")
	public List<Contato> busca(String nome) {
		Criteria criteria = session.createCriteria(Contato.class);
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		return criteria.list();
	}
	
	public List<Cidade> getCidadeByUf(String uf) {
		return new CidadeDao(session).getCidadeByUf(uf);
	}
	
	public Cidade getCidadeById(Long id) {
		CidadeDao dao = new CidadeDao(session);
		return dao.getCidade(id);
	}

	public void recarrega(Contato contato) {
		session.refresh(contato);
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	
}

