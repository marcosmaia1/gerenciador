package br.com.gerenciador.modelo;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class OperadorWeb {
	
	private Operador logado;
	private String language = "pt_BR";
	
	public void login(Operador operador) {
		this.logado = operador;
	}
	
	public boolean isOperadorLogado() {
		return logado != null;
	}
	
	public String getNome(){
		return logado.getNome();
	}

	public void logout() {
		this.logado = null;
	}

	public Operador getLogado() {
		return logado;
	}

	public void setLogado(Operador logado) {
		this.logado = logado;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getValores(){
		String variavel = "";
		variavel=logado.getNome();
		
		return variavel;
	}
	
}
