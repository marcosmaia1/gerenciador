<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
	<div class="grid">
		<h1>
			<geren:cabecalhoForm operacao="${operacao}" controller="" entidade="${entidade.label}"/>
		</h1>
			<div class="tab-control" data-role="tab-control">
				<ul class="tabs">
					<li class="active"><a href="#_page_1">Listagem de regionais</a></li>
				</ul>
				<div class="frames">
					<div class="frame" id="_page_1">
						<div class="row">
							<div class="row">
								<div class="col-sm-3 col-md-3">
									<geren:botaoAdicionar
										controller="${entidade}"
										path="${pageContext.request.contextPath}" 
										somenteLeitura="false"
									/>
								 </div>
							 </div>
						<table id="tabelaRegional" class="table table-striped table bordered table-hover" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Opções</th>
									<th>Código</th>
									<th>Descrição</th>
								</tr>
							</thead>
							<c:forEach var="entity" items="${regionalList}">
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
									    	<geren:botaoExcluirListagem 
									    		controller="${entity.entidade}" 
									    		id="${entity.id}"
									    		somenteLeitura="false"
									    	/>
										</div>
									</td>
									<td>${entity.id}</td>
									<td>${entity.descricao}</td>
								</tr>	
							</c:forEach>
						</table>					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/files/js/views/regional.js" charset="UTF-8"></script>
<script>
	$(document).ready(function() {
		montarTabelaRegional('${pageContext.request.contextPath}');
	});	
</script>