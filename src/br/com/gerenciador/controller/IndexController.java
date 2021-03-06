/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.gerenciador.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.gerenciador.modelo.OperadorWeb;

@Resource
public class IndexController {

	private final Result result;
	private final OperadorWeb operadorWeb;

	public IndexController(Result result, OperadorWeb operadorWeb) {
		this.result = result;
		this.operadorWeb = operadorWeb;
	}

	@Path("/")
	public void index() {
		if(operadorWeb.getLogado() == null){
		result.redirectTo(OperadorController.class).loginForm();
		} else {
			result.redirectTo(NegociacaoController.class).paginaInicial();
		}
	}
}
