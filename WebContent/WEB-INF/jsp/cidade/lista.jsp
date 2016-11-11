<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
	<div class="grid">
		<h1>
			<a href="${pageContext.request.contextPath}"><i class="icon-arrow-left-3 fg-darker smaller"></i></a>Cidades<small class="on-right"> Listagem</small>
		</h1>
			<div class="tab-control" data-role="tab-control">
				<ul class="tabs">
					<li class="active"><a href="#_page_1">Listagem de cidades</a></li>
				</ul>
				<div class="frames">
					<div class="frame" id="_page_1">
						<div class="row">
							<table id="tabelaCidade" class="table table-striped table bordered table-hover" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>Opções</th>
										<th>Código</th>
										<th>Cidade</th>
										<th>Estado</th>
									</tr>
								</thead>
								<c:forEach var="cidade" items="${cidadeList}">
									<tr>
										<td>
											<div class="toolbar">
											    <button class="default default" onclick="location.href='${pageContext.request.contextPath}/cidade/${cidade.id}'"><a title="Exibir"><i class="icon-search fg-white" id="icone"></i></a></button>
											    </form>
											</div>
										</td>
										<td>${cidade.id}</td>
										<td>${cidade.nome}</td>
										<td>${cidade.uf.label}</td>
									</tr>	
								</c:forEach>
							</table>					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	table = $("#tabelaCidade").dataTable({
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
		columnDefs: [ {"targets": 0, "width": 60 },
		              {"targets": 1, "width": 100 },
		              { "orderable": false, "searchable": false, "targets": 0, "width": 86 }],			
		oLanguage : {
			sUrl : '${pageContext.request.contextPath}/files/datatables/js/dataTables.pt_BR.txt',
			sSearch : 'Pesquisar:'
		}
	});
</script>