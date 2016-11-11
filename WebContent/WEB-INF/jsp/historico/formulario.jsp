<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<div class="container">
		<div class="grid">
			<h1>
				<a href="${pageContext.request.contextPath}/negociacao/${entity.negociacao.id}/edita"><i class="icon-arrow-left-3 fg-darker smaller"></i></a> ${entity.entidade.label}<small class="on-right"> ${entity.operacao.label}</small>
			</h1>
			<br />
			<form id="historicoForm" action="${pageContext.request.contextPath}/historico" method="POST">
				<fieldset <c:if test="${entity.operacao eq 'EXIBINDO'}">disabled</c:if>>
					<c:if test="${entity.id != null}">
						<input type="hidden" name="_method" value="PUT" />
						<input type="hidden" id="id" name="entity.id" value="${entity.id}">
					</c:if>
					<input hidden="true" name="entity.operacao" value="${entity.operacao}">
					<input hidden="true" name="entity.negociacao.id" value="${entity.negociacao.id}">
					<c:if test="${!(entity.operacao eq 'NOVO')}">
						<div class="row">
							<div class="col-xs-5 col-sm-3 col-md-2"">
								<label>Código:</label>
								<div class="input-control text" data-role="input-control">
									<input type="text" disabled placeholder="type text" name="entity.id" value="${entity.id}">
								</div>
							</div>
						</div>
						<br/>
					</c:if>
					<div class="tab-control" data-role="tab-control">
						<ul class="tabs">
							<li class="active"><a href="#_page_1">Registro de histórico</a></li>
						</ul>
						<div class="frames">
							<div class="frame" id="_page_1">				
								<div class="row" style="margin-bottom: 60px;">
									<div class="col-sm-6 col-md-6">
										<label>Comentário:</label>
										<div class="input-control text <c:if test="${errorComentario}">error-state</c:if>" data-role="input-control">
											<div class="input-control textarea" data-role="input-control">
												<textarea type="text" name="entity.comentario" maxlength="300" placeholder="type text" value="${entity.comentario}"><c:out value="${entity.comentario}"/></textarea>
											</div>
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
						<c:if test="${entity.operacao eq 'EXIBINDO' && operadorWeb.logado.administrador}">						
							<geren:botaoAlterar controller="${entity.entidade}" id="${entity.id}" />
							<geren:botaoExcluir controller="${entity.entidade}" id="${entity.id}" />
						</c:if>	
						<c:if test="${!(entity.operacao eq 'EXIBINDO')}">
							<geren:botaoSalvar/>
						</c:if>							
						<geren:botaoCancelar controller="${entity.entidade}" caminhoRotinaInterna="/${entity.negociacao.id}/cancelar"/>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/files/js/gerenciador.js"></script>