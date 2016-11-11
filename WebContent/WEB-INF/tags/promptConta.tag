<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="modal fade" id="promptConta">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button type="button" class="close" data-dismiss="modal">
					<span area-hidden="true">&times;</span><span class="sr-only"><fmt:message key="fechar"/></span>
				</button>
				<h4 class="modal-title"><fmt:message key="pesquisa.conta"/></h4>
			</div>
			<div class="modal-body">
				<table id="tabelaContaPrompt" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th style="text-align: center; width: 15px;"><span class="glyphicon glyphicon-check" title="<fmt:message key="selecionar"/>"></span></th>
							<th><fmt:message key="id"/></th>
							<th><fmt:message key="descricao"/></th>
							<th><fmt:message key="tipo.conta"/></th>
							<th><fmt:message key="status"/></th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fam-door-in"></i> <fmt:message key="fechar"/></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>	
<!-- /.modal -->