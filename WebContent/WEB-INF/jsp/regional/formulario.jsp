<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div class="container">
		<div class="grid">
			<geren:cabecalhoForm operacao="${entity.operacao.label}" controller="${entity.entidade}" entidade="${entity.entidade.label}"/>
			<form id="regionalForm" action="${pageContext.request.contextPath}/regional" method="POST">
				<fieldset <c:if test="${entity.operacao eq 'EXIBINDO'}">disabled</c:if>>
					<c:if test="${entity.id != null}">
						<input type="hidden" name="_method" value="PUT" />
						<input type="hidden" id="id" name="entity.id" value="${entity.id}">
					</c:if>
					<input hidden="true" name="entity.operacao" value="${entity.operacao}">
					<c:if test="${!(entity.operacao eq 'NOVO')}">
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
					<div class="tab-control" data-role="tab-control">
						<ul class="tabs">
							<li class="active"><a href="#_page_1">Dados da regional</a></li>
						</ul>
						<div class="frames">
							<div class="frame" id="_page_1">				
								<div class="row">
									<div class="col-sm-6 col-md-6">
										<label>Descrição:</label>
										<div class="input-control text <c:if test="${errorDescricao}">error-state</c:if>" data-role="input-control">
											<input maxlength="50" type="text" placeholder="type text" name="entity.descricao" value="${entity.descricao}">
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/files/js/gerenciador.js"></script>	
	