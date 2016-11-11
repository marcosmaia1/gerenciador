package br.com.gerenciador.modelo;

import br.com.gerenciador.controller.OperadorController;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class AutorizacaoInterceptor implements Interceptor{

	private final OperadorWeb operador;
	private final Result result;

	public AutorizacaoInterceptor(OperadorWeb operador, Result result) {
		this.operador = operador;
		this.result = result;
	}
	
	public boolean accepts(ResourceMethod method) {
		return !operador.isOperadorLogado() && method.containsAnnotation(Restrito.class);
	}

	public void intercept(InterceptorStack arg0, ResourceMethod arg1,
			Object arg2) throws InterceptionException {
		result.redirectTo(OperadorController.class).loginForm();
	}
}
