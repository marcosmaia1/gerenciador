<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
	<div class="grid">
		<geren:cabecalhoForm operacao="${entity.operacao.label}" controller="${entity.entidade}" entidade="${entity.entidade.label}"/>
			<form id="contatoForm" action="${pageContext.request.contextPath}/contato" method="POST">
			<fieldset <c:if test="${entity.operacao eq 'EXIBINDO'}">disabled</c:if>>
				<c:if test="${entity.id != null}">
					<input type="hidden" name="_method" value="PUT" />
					<input type="hidden" id="id" name="entity.id" value="${entity.id}">
				</c:if>
				<c:if test="${entity.operacao != 'NOVO'}">
					<div class="row">
						<div class="col-xs-5 col-sm-3 col-md-2"">
							<label>Código:</label>
							<div class="input-control text" data-role="input-control">
								<input type="text" disabled placeholder="type text" name="entity.id" value="${entity.id}">
							</div>
						</div>
						<div class="col-xs-5 col-sm-3 col-md-2">
							<label>Desativado:</label>	
							<div class="input-control switch">
							    <label>
							        <input for="checkStatus" type="checkbox" <c:if test="${!(operadorWeb.logado.administrador)}">disabled</c:if> name="entity.desativado" <c:if test="${entity.desativado}">checked</c:if> <c:if test="${entity.operacao eq 'NOVO'}">disabled</c:if>/>
							        <span id="checkStatus" class="check"></span>
							    </label>
							</div>	
						</div>
					</div>
					<br/>
				</c:if>
				<c:if test="${!(entity.cidadeNome eq null)}">
					<input type="hidden" name="entity.cidadeNome" value="${entity.cidadeNome}" />
				</c:if>
				<div class="tab-control" data-role="tab-control">
					<ul class="tabs">
						<li class="active"><a href="#_page_1">Dados do contato</a></li>
						<li><a href="#_page_2">Informações adicionais</a></li>
					</ul>
					<div class="frames">
						<div class="frame" id="_page_1">				
							<div class="row">
								<div class="col-sm-6 col-md-6">
									<label>Nome</label>	
									<div class="input-control text <c:if test="${errorNome}">error-state</c:if>" data-role="input-control">
										<input type="text" maxlength="50" placeholder="type text" name="entity.nome" value="${entity.nome}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>	
								</div>
								<div class="col-sm-3 col-md-2">
									<label>Tipo de pessoa</label>
									<div class="input-control select">
										<select id="tipoPessoaSelect" name="entity.tipoPessoa" data-live-search="true">
											<c:forEach items="${tipoPessoaList}" var="item">
													<option value="${item}" <c:if test="${entity.tipoPessoa eq item}">selected="selected"</c:if>>${item.label}</option>
											</c:forEach>		
										</select>
									</div>
								</div>
								<div class="col-sm-3 col-md-4">
									<label id="label-cpfCnpj">CPF</label>	
									<div class="input-control text <c:if test="${errorCpfCnpj}">error-state</c:if>" id="div-cpfCnpj" data-role="input-control">
										<input type="text" maxlength="20" id="cpfCnpj" placeholder="type text" name="entity.cpfCnpj" value="${entity.cpfCnpj}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6 col-md-6">
									<label>Razão Social</label>
									<div class="input-control text" data-role="input-control">
										<input type="text" maxlength="50" placeholder="type text" name="entity.razao" value="${entity.razao}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>			
								</div>							
								<div class="col-sm-6 col-md-6">
									<label>Nome Fantasia</label>
									<div class="input-control text" data-role="input-control">
										<input type="text" maxlength="50" placeholder="type text" name="entity.fantasia" value="${entity.fantasia}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>		
								</div>								
							</div>
							<div class="row">
								<div class="col-sm-6 col-md-6">
									<label>Telefone Principal</label>	
									<div class="input-control text <c:if test="${errorTel}">error-state</c:if>" data-role="input-control">
										<input id="telefone1" class="telefone" type="text" maxlength="12" placeholder="Somente números." name="entity.tel" value="${entity.tel}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>	
								</div>
								<div class="col-sm-6 col-md-6">
									<label>Telefone Secundário</label>	
									<div class="input-control text" data-role="input-control">
										<input id="telefone2" class="telefone" type="text" maxlength="12" placeholder="Somente números." name="entity.tel2" value="${entity.tel2}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>	
								</div>
							</div>											
							<div class="row">
								<div class="col-sm-6 col-md-6">
									<label>Email Principal</label>	
									<div class="input-control text <c:if test="${errorEmail}">error-state</c:if>" data-role="input-control">
										<input type="text" maxlength="50" placeholder="type text" name="entity.email" value="${entity.email}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>	
								</div>
								<div class="col-sm-6 col-md-6">
									<label>Email Secundário</label>	
									<div class="input-control text <c:if test="${errorEmail2}">error-state</c:if>" data-role="input-control">
										<input type="text" maxlength="50" placeholder="type text" name="entity.email2" value="${entity.email2}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>	
								</div>
							</div>		
							<div class="row">
								<div class="col-sm-6 col-md-3">
									<label>Estado</label>
									<div class="input-control select">
										<select id="estadosSelect" name="entity.cidade.uf" data-live-search="true">
											<c:forEach items="${ufList}" var="item">
													<option value="${item}" <c:if test="${entity.cidade.uf eq item}">selected="selected"</c:if>>${item} - ${item.label}</option>
											</c:forEach>		
										</select>
									</div>
								</div>
								<div class="col-sm-6 col-md-4">
									<label>Cidade</label>	
									<div class="input-control select">
										<select id="cidadesSelect" name="entity.cidade.id">
											<c:forEach items="${cidadeList}" var="item">
													<option value="${item.id}" <c:if test="${entity.cidade.id eq item.id}">selected="selected"</c:if>>${item.nome}</option>
											</c:forEach>		
										</select>
									</div>	
								</div>
							</div>
						</div>
						<div class="frame" id="_page_2">
							<div class="row">
								<div class="col-sm-5 col-md-5">
									<label>Endereço</label>
									<div class="input-control text" data-role="input-control" name="entity.endereco" value="${entity.endereco}">
										<input type="text" maxlength="140" placeholder="type text">
										<button class="btn-clear" tabindex="-1"></button>
									</div>			
								</div>
								<div class="col-sm-2 col-md-2">
									<label>Número</label>
									<div class="input-control text" data-role="input-control">
										<input type="text" maxlength="10" placeholder="type text" name="entity.numero" value="${entity.numero}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>		
								</div>								
								<div class="col-sm-5 col-md-5">
									<label>Bairro</label>
									<div class="input-control text" data-role="input-control">
										<input type="text" maxlength="20" placeholder="type text" name="entity.bairro" value="${entity.bairro}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>		
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-12 col-lg-12">
									<label>Complemento</label>	
									<div class="input-control text" data-role="input-control">
										<input type="text" maxlength="50" placeholder="type text" name="entity.complemento" value="${entity.complemento}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>	
								</div>								
							</div>											
						</div>
					</div>
				</div>		
				<div class="row">
						<geren:campoAlteracao entity="${entity}" />
				</div>	
			</fieldset>
			<br />
			<br />
			<div class="row">	
				<div class="col-sm-6 col-md-6">
					<c:if test="${entity.operacao eq 'EXIBINDO'}">						
						<geren:botaoAlterar controller="${entity.entidade}" id="${entity.id}" />
						<geren:botaoExcluir controller="${entity.entidade}" id="${entity.id}" />
					</c:if>	
					<c:if test="${!(entity.operacao eq 'EXIBINDO')}">
						<geren:botaoSalvar/>
					</c:if>							
					<geren:botaoCancelar controller="${entity.entidade}" />
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/files/js/views/funcoesUtil.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/files/js/views/contato.js" charset="UTF-8"></script>
<script>

	var path = '${pageContext.request.contextPath}';
	$(document).ready(function() {
		$('.telefone').mask("(99) 9999-9999?9");
		atualizaCidade(path, '${entity.cidade.id}');
		formataCampos();
	});	
	$("#estadosSelect").on('change', function(){
		atualizaCidade(path, '${entity.cidade.id}');
	});
</script>