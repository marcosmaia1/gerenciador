<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
	<div class="grid">
		<geren:cabecalhoForm 
			operacao="${entity.operacao.label}" 
			controller="${entity.entidade}" 
			entidade="${entity.entidade.label}"
		/>
			<form id="negociacaoForm" action="${pageContext.request.contextPath}/negociacao" method="POST">
				<input type="hidden" name="entity.operacao" value="${entity.operacao}">
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
							        <input for="checkStatus" type="checkbox" <c:if test="${!(operadorWeb.logado.administrador)}">disabled</c:if> name="entity.desativado" <c:if test="${entity.desativado}">checked</c:if>/>
							        <span id="checkStatus" class="check"></span>
							    </label>
							</div>	
						</div>
					</div>
					<br/>
				</c:if>
				<div class="tab-control" data-role="tab-control">
					<ul class="tabs">
						<li class="active"><a href="#_page_1">Dados da negociação</a></li>
					</ul>
					<div class="frames">
						<div class="frame" id="_page_1">
							<fieldset <c:if test="${entity.operacao eq 'EXIBINDO'}">disabled</c:if>>
								<div class="div-tabela-contato">
									<h3>Pesquisa de Contatos</h3>
									<table id="tabelaContato" class="table table-striped table bordered table-hover" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>Opções</th>
												<th>Código</th>
												<th>Nome</th>
												<th>CPF/CNPJ</th>
												<th>Telefone</th>
											</tr>
										</thead>
										<c:forEach var="contato" items="${contatoList}" varStatus="status">
											<tr>
												<td>
													<div class="toolbar">
													    <button 
															class="info"
															type="button"
															id="btnSelecionarPrompt"
															data-tr-id="${status.index}"
															<a title="Selecionar">
																<i class="icon-checkmark fg-white" id="icone"></i>
															</a>>
														</button>
													    <input type="hidden" id="contato-${status.index}" value="${contato.id}">
													</div>
												</td>
												<td>${contato.id}</td>
												<td>${contato.nome}</td>
												<td>${contato.cpfCnpjF}</td>
												<td>${contato.telF}</td>
											</tr>	
										</c:forEach>
									</table>
									<br>
									<button 
										class="large warning"
										type="button"
										id="btnFecharPrompt"
										<a title="Fechar prompt">Fechar prompt</a>>
									</button>
								</div>
								<br>
								<geren:campoPromptContato 
									label="contato" 
									id="inputContato" 
									valor="${entity.contato.id}" 
									autofocus="true" 
									name="entity.contato.id"
									readonly="${!(entity.operacao eq 'NOVO')}" 
									error="${errorContato}"
									exibicaoCompleta="true"
									divPromptClass="div-tabela-contato"
								/>
								<div class="row">
									<div class="col-lg-3 col-sm-3 col-md-3">
										<label>Regional</label>
										<div class="input-control select">
											<select id="regionalSelect" <c:if test="${entity.operacao != 'NOVO'}">disabled="disabled"</c:if> name="entity.regional.id" data-live-search="true">
												<c:forEach items="${regionalList}" var="item">
														<option value="${item.id}" <c:if test="${entity.regional eq item}">selected="selected"</c:if>>${item.descricao}</option>
												</c:forEach>		
											</select>
										</div>
									</div>
									<div class="col-lg-3 col-sm-3 col-md-3">
										<label>Status</label>
										<div class="input-control select">
											<select id="statusSelect" name="entity.status" data-live-search="true">
												<c:forEach items="${statusList}" var="item">
														<c:if test="${item != 'NENHUM'}">
															<option value="${item}" <c:if test="${entity.operacao eq 'NOVO' && (item eq 'CONCLUIDO' || item eq 'CONCLUIDO_SEM_SUCESSO')}">disabled</c:if> <c:if test="${entity.status eq item}">selected="selected"</c:if>>${item.label}</option>
														</c:if>
												</c:forEach>		
											</select>
										</div>
									</div>
									<c:if test="${entity.operacao != 'NOVO'}">
										<div class="col-lg-3 col-sm-3 col-md-3">
												<label>Fechamento</label>
												<div class="input-control select">
													<select id="fechamentoSelect"  name="entity.fechamento"  data-live-search="true">
														<c:forEach items="${fechamentoList}" var="item">
																<option value="${item}" <c:if test="${entity.fechamento eq item}">selected="selected"</c:if>>${item.label}</option>
														</c:forEach>		
													</select>
												</div>
										</div>
										<div class="col-lg-3 col-sm-3 col-md-3">
											<div class="input-control text <c:if test="${errorValorFechamento}">error-state</c:if>" data-role="input-control">
												<label for="inputValorFechamento">Valor do fechamento:</label> 
												<input type="text" readonly maxlength="9" class="input-control text-right" id="inputValorFechamento" name="entity.valorFechamento" value="<fmt:formatNumber  type="number" minFractionDigits="2" maxFractionDigits="2" value="${entity.valorFechamento}" />">
											</div>
										</div>
									</c:if>
								</div>
								<div class="row">
									<div class="col-lg-3 col-sm-3 col-md-3">
										<div class="input-control text" data-role="input-control">
											<label>Data Início</label>
											<input type="text" class="input-control" readonly placeholder="type text" name="entity.dataInicio" value="<fmt:formatDate value="${entity.dataInicio}" pattern="dd/MM/yyyy"/>">
										</div>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-lg-12 col-sm-12 col-md-12">
										<label>Observações</label>	
										<div class="input-control textarea" data-role="input-control">
											<textarea type="text" name="entity.obs" maxlength="500" placeholder="type text" value="${entity.obs}">${entity.obs}</textarea>
										</div>	
									</div>
								</div>
						</fieldset>
						<c:if test="${!(entity.operacao eq 'NOVO')}">
							<div class="row">
								<div class="tab-control" data-role="tab-control">
									<ul class="tabs">
										<li class="active"><a href="#_page_2">Histórico da negociação</a></li>
									</ul>
									<div class="frames">
										<div class="frame" id="_page_2">
											<div class="row">
												<div style="text-align: left; margin-bottom: 1%;" class="col-sm-6 col-md-6">
													<geren:botaoAdicionar controller="${histEntidade}" somenteLeitura="false" rotinaPaiId="${entity.id}" path="${pageContext.request.contextPath}"/>
												</div>
												<table id="tabelaHistorico" class="table table-striped table bordered table-hover" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th>Opções</th>
															<th>Código</th>
															<th>Data</th>
															<th>Operador</th>
															<th>Comentário</th>
														</tr>
													</thead>
													<c:forEach var="historico" items="${entity.historico}">
														<tr>
															<td class="botoes-centro">
																<div class="toolbar ">
																    <geren:botaoVisualizarListagem 
																    	controller="${historico.entidade}" 
																    	id="${historico.id}"
 																    	somenteLeitura="false"
 															    	/>
 															    	<c:if test="${operadorWeb.logado.administrador}">
	 															    	<geren:botaoEditarListagem
	 															    		controller="${historico.entidade}" 
																	    	id="${historico.id}"
	 																    	somenteLeitura="${!operadorWeb.logado.administrador}"
	 															    	/>
 															    	</c:if>
																</div>
															</td>
															<td>${historico.id}</td>
															<td><fmt:formatDate value="${historico.dataCadastro}" pattern="dd/MM/yyyy"/></td>
															<td>${historico.operadorAlteracao.nome}</td>
															<td>${historico.resumo}</td>
														</tr>
													</c:forEach>											
												</table>
											</div>		
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<div class="row">
					<geren:campoAlteracao entity="${entity}" />
			</div>
			<br />
			<div class="row">	
				<div class="col-sm-6 col-md-6">
					<c:if test="${entity.operacao eq 'EXIBINDO'}">						
						<geren:botaoAlterar controller="${entity.entidade}" id="${entity.id}" />
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
<geren:jsSomenteDecimal id="inputValorFechamento" />
<script type="text/javascript" src="${pageContext.request.contextPath}/files/js/views/negociacao.js" charset="UTF-8"></script>
<script>
	$(document).ready(function() {
		montarTabelaHistorico('${pageContext.request.contextPath}');
		montarTabelaContato('${pageContext.request.contextPath}');
		$('.div-tabela-contato').slideUp('fast');	
		verificaStatus();
		verificaFechamento();
	});	
</script>