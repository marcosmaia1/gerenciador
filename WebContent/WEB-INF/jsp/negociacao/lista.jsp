<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
	<div class="grid">
		<h1>
			<geren:cabecalhoForm operacao="${operacao}" controller="" entidade="${entidade.label}"/>
		</h1>
			<div class="tab-control" data-role="tab-control">
				<ul class="tabs">
					<li class="active"><a href="#_page_1">Listagem de Negociações</a></li>
				</ul>
				<div class="frames">
					<div class="frame" id="_page_1">
						<div class="row">
							<div class="row">
								<div class="col-sm-6 col-md-6">
									<geren:botaoAdicionar
										controller="${entidade}"
										path="${pageContext.request.contextPath}" 
										somenteLeitura="false"
									/>
								</div>
								<div class="col-sm-6 col-md-6" style="text-align: right;">
									<button download id="downloadArquivoExportacao" class="large info" type="button">Exportar Contatos</button>
								</div>
							 </div>
						<table id="tabelaNegociacao" class="table table-striped table bordered table-hover" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Opções</th>
									<th>Código</th>
									<th>Data de início</th>
									<th>Contato</th>
									<th>Fechamento</th>
									<th>Status</th>
									<th>Responsável</th>
								</tr>
							</thead>
							<c:forEach var="entity" items="${negociacaoList}">
								<tr>
									<td>
										<div class="toolbar">
											<geren:botaoVisualizarListagem 
									    		controller="${entity.entidade}" 
									    		id="${entity.id}"
									    		somenteLeitura="false"
									    	/>
									    	<geren:botaoEditarListagem 
									    		controller="${entity.entidade}" 
									    		id="${entity.id}"
									    		somenteLeitura="false"
									    	/>
										</div>
									</td>
									<td>${entity.id}</td>
									<td><fmt:formatDate value="${entity.dataInicio}" pattern="dd/MM/yyyy"/></td>
									<td>${entity.contato.nome}</td>
									<td>${entity.fechamento.label}</td>
									<td>${entity.status.label}</td>
									<td>${entity.operadorCadastro.nome}</td>
								</tr>	
							</c:forEach>
						</table>					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/files/js/views/negociacao.js" charset="UTF-8"></script>
<script>
	$(document).ready(function() {
		montarTabelaNegociacao('${pageContext.request.contextPath}');
	});	
	$("#downloadArquivoExportacao").on("click", function(){
		var win = window.open('${pageContext.request.contextPath}/${fn:toLowerCase(entidade)}/exportacaoClientes.csv', '_blank');
		if(win){
		    //Browser has allowed it to be opened
		    win.focus();
		}else{
		    //Broswer has blocked it
		    alert('Please allow popups for this site');
		}
	});
</script>