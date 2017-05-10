package br.com.gerenciador.infra;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class CriadorDeSessionFactory implements ComponentFactory<SessionFactory>{
	
	private SessionFactory sessionFactory;

	@PostConstruct
	public void abre(){
		Configuration annotationConfiguration = new Configuration();
		annotationConfiguration.configure("hibernate.cfg.xml");
		sessionFactory = annotationConfiguration.buildSessionFactory();
	}

	public SessionFactory getInstance() {
		return sessionFactory;
	}
	
	@PreDestroy
	public void fecha(){
		sessionFactory.close();
	}

}
