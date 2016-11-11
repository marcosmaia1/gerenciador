<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}"/>

<div class="modal fade" id="janelaModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-danger">
				<button type="button" class="close" data-dismiss="modal">
					<span area-hidden="true">&times;</span><span class="sr-only"><fmt:message key="fechar"/></span>
				</button>
				<h4 class="modal-title"><fmt:message key="atencao"/>!</h4>
			</div>
			<div class="modal-body">
				<h4></h4>
			</div>
			<div class="modal-footer">
				<form class="form-horizontal" id="form-excluir" action=""
					method="post">
					<input type="hidden" name="_method" value="delete" /> <input
						type="submit" class="btn btn-danger" id="btnSimExcluir" value="<fmt:message key="sim"/>"
						style="width: 80px;" /> <a href="#" type="button"
						class="btn btn-default" id="nao" data-dismiss="modal"
						style="width: 80px;"><fmt:message key="nao"/></a>
				</form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->