<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
	<div class="grid">
		<h1>
			<a href="${pageContext.request.contextPath}/cidade"><i class="icon-arrow-left-3 fg-darker smaller"></i></a> Regionais<small class="on-right"> ${cidade.operacao.label}</small>
		</h1>
		<form id="cidadeForm" action="${pageContext.request.contextPath}/cidade" method="POST">
			<input hidden name="regional.operacao">
			<fieldset <c:if test="${cidade.operacao eq 'EXIBINDO'}">disabled</c:if>>
				<div class="row">
					<div class="col-xs-5 col-sm-3 col-md-2"">
						<label>Código:</label>
						<div class="input-control text" data-role="input-control">
							<input type="text" disabled placeholder="type text" name="cidade.id" value="${cidade.id}">
							<button class="btn-clear" tabindex="-1"></button>
						</div>	
					</div>
				</div>
				<br/>
				<div class="tab-control" data-role="tab-control">
					<ul class="tabs">
						<li class="active"><a href="#_page_1">Dados da cidade</a></li>
					</ul>
					<div class="frames">
						<div class="frame" id="_page_1">				
							<div class="row">
								<div class="col-sm-6 col-md-6">
									<label>Nome:</label>	
									<div class="input-control text" data-role="input-control">
										<input type="text" placeholder="type text" name="cidade.nome" value="${cidade.nome}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>	
								</div>
								<div class="col-sm-3 col-md-3">
									<label>Estado:</label>	
									<div class="input-control text" data-role="input-control">
										<input type="text" placeholder="type text" name="cidade.estado" value="${cidade.estado}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>	
								</div>
								<div class="col-sm-3 col-md-3">
									<label>Código IBGE:</label>	
									<div class="input-control text" data-role="input-control">
										<input type="text" placeholder="type text" name="cidade.codigoIBGE" value="${cidade.codigoIBGE}">
										<button class="btn-clear" tabindex="-1"></button>
									</div>	
								</div>
							</div>
						</div>
					</div>
				</div>		
				<br />
			</fieldset>
				<div class="row">					
						<button class="large warning" onclick="location.href='${pageContext.request.contextPath}/cidade'"><a title="Cancelar"></a><i class="icon-blocked"></i> Cancelar</button>
					</div>
				</div>
		</form>
	</div>
	<script type="text/javascript">
	$("#checkStatus").is(":checked");
	</script>