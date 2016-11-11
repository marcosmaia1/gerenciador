<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container">
	<div class="grid">
		<geren:cabecalhoForm operacao="${entity.operacao.label}" controller="${entity.entidade}" entidade="${entity.entidade.label}"/>
		<input hidden="true" name="entity.operacao" value="${entity.operacao}">
		<div class="tab-control" data-role="tab-control">
			<ul class="tabs">
				<li class="active"><a href="#_page_1">Dados do relatório</a></li>
			</ul>
			<div class="frames">
				<div class="frame" id="_page_1">	
					<form id="relatorioForm" action="${pageContext.request.contextPath}/negociacao/relatorio" method="POST">	
						<input hidden="true" name="entity.operacao" value="${entity.operacao}">
						<geren:campoPromptContato 
							label="contato" 
							id="inputContato" 
							valor="${entity.contato.id}" 
							autofocus="true" 
							name="entity.contato.id"
							error="errorContato"
							exibicaoCompleta="false"
							divPromptClass="div-promptContato"
						/>
						<div class="row">
							<div class="col-sm-3 col-md-3">
								<label>Operador</label>
								<div class="input-control select">
									<select id="operadorSelect" name="entity.operador.id" data-live-search="true">
										<option value="0" selected="selected">Nenhum</option>
										<c:forEach items="${operadorList}" var="item">
												<option value="${item.id}" <c:if test="${entity.operador.id eq item.id}">selected="selected"</c:if>>${item.nome}</option>
										</c:forEach>		
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3">
								<label>Regional</label>
								<div class="input-control select">
									<select id="regionalSelect" name="entity.regional.id" data-live-search="true">
										<option value="0" selected="selected">Nenhum</option>
										<c:forEach items="${regionalList}" var="item">
												<option value="${item.id}" <c:if test="${entity.regional.id eq item.id}">selected="selected"</c:if>>${item.descricao}</option>
										</c:forEach>		
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3">
								<div class="input-control text" data-role="input-control">
									<label>Data Inicial</label>
									<input type="text" readonly placeholder="type text" id="dataInicial" name="entity.dataInicial" value="<fmt:formatDate value="${entity.dataInicial}" pattern="dd/MM/yyyy"/>">
								</div>
							</div>
							<div class="col-sm-3 col-md-3">
								<div class="input-control text" data-role="input-control">
									<label>Data Final</label>
									<input type="text" readonly placeholder="type text" id="dataFinal" name="entity.dataFinal" value="<fmt:formatDate value="${entity.dataFinal}" pattern="dd/MM/yyyy"/>">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-3 col-md-3">
								<label>Status</label>
								<div class="input-control select">
									<select id="statusSelect" name="entity.status" data-live-search="true">
										<c:forEach items="${statusList}" var="item">
												<option value="${item}" <c:if test="${entity.status eq item}">selected="selected"</c:if>>${item.label}</option>
										</c:forEach>		
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3">
								<label>Campo Agrupamento</label>
								<div class="input-control select">
									<select id="ordenacaoAgrupamentoList" name="entity.campoAgrupamento" data-live-search="true">
										<c:forEach items="${ordenacaoAgrupamentoList}" var="item">
												<c:if test="${item != 'DATA'}"><option value="${item}" <c:if test="${entity.campoAgrupamento eq item}">selected="selected"</c:if>>${item.label}</option></c:if>
										</c:forEach>		
									</select>
								</div>
							</div>
							<div class="col-sm-3 col-md-3">
								<label>Campo Ordenacao</label>
								<div class="input-control select">
									<select id="ordenacaoCampoList" name="entity.campoOrdenacao" data-live-search="true">
										<c:forEach items="${ordenacaoCampoList}" var="item">
												<option value="${item}" <c:if test="${entity.campoAgrupamento eq item}">selected="selected"</c:if>>${item.label}</option>
										</c:forEach>		
									</select>
								</div>
							</div>
						</div>
						<br />
						<div class="row">	
							<div class="toolbar">
								<div class="col-sm-5 col-md-5">
									<button class="large primary" type="submit"><a title="Carregar relatório"></a>Carregar relatório</button>
								<c:if test="${entity.relatorioList != null}">
									<div class="button-dropdown" style="height: 40px; display: inline-block;">
									    <button class="large info dropdown-toggle"><a >Opções</a></button>
									    <ul class="dropdown-menu" data-role="dropdown">
									        <li><a id="downloadRelatorio">Download do relatório</a></li>
									        <!-- <li><a href="#">Enviar relatório por e-mail</a></li>-->
									    </ul>
									</div>
								</c:if>
								</div>
							</div>
						</div>
					</form>
					<c:if test="${entity.relatorioList != null}">
						<table id="tabelaRelatorio" class="table table-striped table bordered table-hover" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Contato</th>
									<th>Data inicio</th>
									<th>Status</th>
									<th>Regional</th>
									<th>Responsável</th>
								</tr>
							</thead>
							<c:forEach var="relatorio" items="${entity.relatorioList}">
								<tr>
									<td>${relatorio.contato.nome}</td>
									<td>${relatorio.dataInicio}</td>
									<td>${relatorio.status.label}</td>
									<td>${relatorio.regional.descricao}</td>
									<td>${relatorio.operadorCadastro.nome}</td>
								</tr>	
							</c:forEach>
						</table>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/files/js/gerenciador.js"></script>
<script>
table = $("#tabelaRelatorio").dataTable({
	retrieve: true,
	responsive : true,
	bAutoWidth : true,
	bPaginate : true,
	bFilter : true,
	bSort : true,
	bInfo : true,
	bJQueryUI : false,
	sPaginationType : 'simple_numbers',
	bProcessing : false,
	columnDefs: [ {"targets": 1, "width": 120 },
	              {"targets": 2, "width": 200 },
	              {"targets": 4, "width": 200 }],			
	oLanguage : {
		sUrl : '${pageContext.request.contextPath}/files/datatables/js/dataTables.pt_BR.txt',
		sSearch : 'Pesquisar:'
	}
});
$("#AdownloadRelatorio").on("click", function(){
	var win = window.open('${pageContext.request.contextPath}/${fn:toLowerCase(entity.entidade)}/relatorio.pdf', '_blank');
	if(win){
	    //Browser has allowed it to be opened
	    win.focus();
	}else{
	    //Broswer has blocked it
	    alert('Please allow popups for this site');
	}
});
$("#downloadRelatorio").on("click", function(){
	//$('form').attr('method', 'POST');
	var x = $('form').attr('action');
	$('form').attr('action', x+'.pdf');
	$('form').submit();
});
</script>	
