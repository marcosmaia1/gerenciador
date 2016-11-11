<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row" style="margin-top: 20px;"></div>
<div class="container fluid">
	<div class="grid">
		<div class="row">
			<div class="tab-control" data-role="tab-control">
				<ul class="tabs">
					<li class="active"><a href="#_page_1">Negociações em andamento</a></li>
					<li><a href="#_page_2">Negociações prospectadas</a></li>
					<li><a href="#_page_3">Negociações concluídas</a></li>
					<li><a href="#_page_4">Sem sucesso</a></li>
				</ul>
				<div class="frames">
					<div class="frame" id="_page_1">
						<div class="row">
							<table id="tabelaNegociacao1" class="table table-striped table bordered table-hover" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Opções</th>
										<th>Data de início</th>
										<th>Contato</th>
										<th>Cidade</th>
										<th>Fechamento</th>
										<th>Responsável</th>
									</tr>
								</thead>
								<c:forEach var="entity" items="${negociacaoEmAndamento}">
									<tbody>
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
											<td><fmt:formatDate value="${entity.dataInicio}" pattern="dd/MM/yyyy"/></td>
											<td>${entity.contato.nome} - ${entity.contato.telF}</td>
											<td>${entity.contato.cidade.nome} - ${entity.contato.cidade.uf}</td>
											<td>${entity.fechamento.label}</td>
											<td>${entity.operadorCadastro.nome}</td>
										</tr>
									</tbody>
								</c:forEach>
							</table>
						</div>		
					</div>
					<div class="frame" id="_page_2">
						<div class="row">
							<table id="tabelaNegociacao2" class="table table-striped table bordered table-hover" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Opções</th>
										<th>Data de início</th>
										<th>Contato</th>
										<th>Cidade</th>
										<th>Fechamento</th>
										<th>Responsável</th>
									</tr>
								</thead>
								<c:forEach var="entity" items="${negociacaoProspectado}">
									<tbody>
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
											<td><fmt:formatDate value="${entity.dataInicio}" pattern="dd/MM/yyyy"/></td>
											<td>${entity.contato.nome} - ${entity.contato.telF}</td>
											<td>${entity.contato.cidade.nome} - ${entity.contato.cidade.uf}</td>
											<td>${entity.fechamento.label}</td>
											<td>${entity.operadorCadastro.nome}</td>
										</tr>
									</tbody>
								</c:forEach>
							</table>
						</div>						
					</div>
					<div class="frame" id="_page_3">
						<div class="row">
							<table id="tabelaNegociacao3" class="table table-striped table bordered table-hover" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Opções</th>
										<th>Data de início</th>
										<th>Contato</th>
										<th>Cidade</th>
										<th>Fechamento</th>
										<th>Responsável</th>
									</tr>
								</thead>
								<c:forEach var="entity" items="${negociacaoConcluido}">
									<tbody>
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
											<td><fmt:formatDate value="${entity.dataInicio}" pattern="dd/MM/yyyy"/></td>
											<td>${entity.contato.nome} - ${entity.contato.telF}</td>
											<td>${entity.contato.cidade.nome} - ${entity.contato.cidade.uf}</td>
											<td>${entity.fechamento.label}</td>
											<td>${entity.operadorCadastro.nome}</td>
										</tr>
									</tbody>
								</c:forEach>
							</table>
						</div>	
					</div>
					<div class="frame" id="_page_4">
						<div class="row">
							<table id="tabelaNegociacao4" class="table table-striped table bordered table-hover" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Opções</th>
										<th>Data de início</th>
										<th>Contato</th>
										<th>Cidade</th>
										<th>Fechamento</th>
										<th>Responsável</th>
									</tr>
								</thead>
								<c:forEach var="entity" items="${negociacaoSemSucesso}">
									<tbody>
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
											<td><fmt:formatDate value="${entity.dataInicio}" pattern="dd/MM/yyyy"/></td>
											<td>${entity.contato.nome} - ${entity.contato.telF}</td>
											<td>${entity.contato.cidade.nome} - ${entity.contato.cidade.uf}</td>
											<td>${entity.fechamento.label}</td>
											<td>${entity.operadorCadastro.nome}</td>
										</tr>
									</tbody>
								</c:forEach>
							</table>
						</div>	
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" charset="UTF-8">
$(document).on('ready', function(){
	montarTabela1();
 	montarTabela2();
	montarTabela3();
	montarTabela4();
});

function montarTabela1(){
	$("#tabelaNegociacao1").dataTable({
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
		columnDefs: [ {"targets": 0, "width": 110, "orderable": false, "searchable": false },
		              {"targets": 1, "width": 110, "orderable": true, "searchable": true },
		              {"targets": 2, "orderable": true, "searchable": true },
		              {"targets": 3, "orderable": true, "searchable": true },
		              {"targets": 4, "width": 120, "orderable": true, "searchable": true },
		              {"targets": 5, "orderable": true, "searchable": true }
		              ],			
		oLanguage : {
			sUrl : '${pageContext.request.contextPath}/files/datatables/js/dataTables.pt_BR.txt',
			sSearch : 'Pesquisar:'
		}
	});
}

function montarTabela2(){
	table2 = $("#tabelaNegociacao2").dataTable({
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
		columnDefs: [ {"targets": 0, "width": 110, "orderable": false, "searchable": false },
		              {"targets": 1, "width": 110, "orderable": true, "searchable": true },
		              {"targets": 2, "orderable": true, "searchable": true },
		              {"targets": 3, "orderable": true, "searchable": true },
		              {"targets": 4, "width": 120, "orderable": true, "searchable": true },
		              {"targets": 5, "orderable": true, "searchable": true },		
		              ],
		oLanguage : {
			sUrl : '${pageContext.request.contextPath}/files/datatables/js/dataTables.pt_BR.txt',
			sSearch : 'Pesquisar:'
		}
	});
}

function montarTabela3(){
	table3 = $("#tabelaNegociacao3").dataTable({
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
		columnDefs: [ {"targets": 0, "width": 110, "orderable": false, "searchable": false },
		              {"targets": 1, "width": 110, "orderable": true, "searchable": true },
		              {"targets": 2, "orderable": true, "searchable": true },
		              {"targets": 3, "orderable": true, "searchable": true },
		              {"targets": 4, "width": 120, "orderable": true, "searchable": true },
		              {"targets": 5, "orderable": true, "searchable": true },			
		              ],
		oLanguage : {
			sUrl : '${pageContext.request.contextPath}/files/datatables/js/dataTables.pt_BR.txt',
			sSearch : 'Pesquisar:'
		}
	});
}

function montarTabela4(){
	table4 = $("#tabelaNegociacao4").dataTable({
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
		columnDefs: [ {"targets": 0, "width": 110, "orderable": false, "searchable": false },
		              {"targets": 1, "width": 110, "orderable": true, "searchable": true },
		              {"targets": 2, "orderable": true, "searchable": true },
		              {"targets": 3, "orderable": true, "searchable": true },
		              {"targets": 4, "width": 120, "orderable": true, "searchable": true },
		              {"targets": 5, "orderable": true, "searchable": true },
		              ],			
		oLanguage : {
			sUrl : '${pageContext.request.contextPath}/files/datatables/js/dataTables.pt_BR.txt',
			sSearch : 'Pesquisar:'
		}
	});
}
</script>