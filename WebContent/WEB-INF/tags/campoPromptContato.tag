<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="geren"%>
<%@ attribute name="id" required="true"%>
<%@ attribute name="label" required="true"%>
<%@ attribute name="name" required="false"%>
<%@ attribute name="valor" required="false"%>
<%@ attribute name="error" required="false" type="java.lang.Boolean"%>
<%@ attribute name="divPromptClass" required="true"%>
<%@ attribute name="autofocus" required="false" type="java.lang.Boolean"%>
<%@ attribute name="readonly" required="false" type="java.lang.Boolean"%>
<%@ attribute name="exibicaoCompleta" required="true" type="java.lang.Boolean"%>
<div class="row">
	<div class="col-xs-5 col-sm-3 col-md-2">
		<label>Contato</label>
		<div class="input-control text <c:if test="${error}">error-state</c:if>" data-role="input-control">
			<input type="text" maxlength="6" id="${id}" 
				<c:if test="${name != null}"> name="${name}"</c:if> 
				<c:if test="${autofocus == true}"> autofocus="autofocus"</c:if> 
				<c:if test="${readonly == true}">readonly</c:if> 
				<c:if test="${valor != null}"> value="${valor}"</c:if> 
			>
			<button id="botaoPromptContato" type="button" href="#promptContato" class="btn-search" <c:if test="${readonly == true}">disabled</c:if> data-toggle="modal"></button>
		</div>	
	</div>
	<div class="col-sm-4 col-md-4">
		<label>Nome</label>	
		<div class="input-control text <c:if test="${error}">error-state</c:if>" data-role="input-control">
			<input id="inputNomeContato" type="text" disabled>
		</div>	
	</div>
	<div class="col-sm-4 col-md-4">
		<label>Razão Social</label>	
		<div class="input-control text <c:if test="${error}">error-state</c:if>" data-role="input-control">
			<input id="inputRazaoContato" type="text" disabled>
		</div>	
	</div>
</div>
<c:if test="${exibicaoCompleta}">
<div class="row">
	<div class="col-sm-3 col-md-3">
		<label>Telefone Principal</label>	
		<div class="input-control text <c:if test="${error}">error-state</c:if>" data-role="input-control">
			<input id="inputTelContato" type="text" disabled>
		</div>	
	</div>
	<div class="col-sm-3 col-md-3">
		<label>Telefone Secundário</label>	
		<div class="input-control text <c:if test="${error}">error-state</c:if>" data-role="input-control">
			<input id="inputTel2Contato" type="text" disabled>
		</div>	
	</div>
	<div class="col-sm-3 col-md-3">
		<label>Email Principal</label>	
		<div class="input-control text <c:if test="${error}">error-state</c:if>" data-role="input-control">
			<input id="inputEmailContato" type="text" disabled>
		</div>	
	</div>
	<div class="col-sm-3 col-md-3">
		<label>Email Secundário</label>	
		<div class="input-control text <c:if test="${error}">error-state</c:if>" data-role="input-control">
			<input id="inputEmail2Contato" type="text" disabled>
		</div>	
	</div>
</div>
</c:if>	

<script type="text/javascript">
preencheCamposPromptContato();
/* $('#promptConta').on('shown.bs.modal', function () {

$('#tabelaContatoPrompt').dataTable({
					lengthMenu: [[5], [5]],				
					retrieve: true,
					responsive : true,
					bAutoWidth : true,
					bPaginate : true,
					bFilter : true,
					bSort : true,
					bInfo : true,
					bJQueryUI : false,
					bProcessing : false,
					sPaginationType : 'simple_numbers',
						columnDefs: [
					               {
					                   width:15, 
					                   orderable: false,
					                   targets:0
					               }
					           ],	
						oLanguage : {
						sUrl : '${pageContext.request.contextPath}/datatables/js/dataTables.${language}.txt',
						sSearch : '<fmt:message key="pesquisar"/>: '
					},	
					bServerSide : true,
					sAjaxSource : '${pageContext.request.contextPath}/conta/json/datatables/prompt',
					sServerMethod : "POST"
				});	

}); */ 

function promptContato(codigo, nome, razao, tel, tel2, email, email2){
	$('#${id}').val(codigo);
	$('#inputNomeContato').val(nome);
	$('#inputRazaoContato').val(razao);
	$('#inputTelContato').val(tel);
	$('#inputTel2Contato').val(tel2);
	$('#inputEmailContato').val(email);
	$('#inputEmail2Contato').val(email2);
	$('#${id}').focus();
}

function preencheCamposPromptContato(){
	var inputId = $.trim($("#${id}").val());
	if(inputId != ''){
		$.ajax({
			url: "${pageContext.request.contextPath}/contato/jsonObjContato",
			data:{id: inputId},
			type: "POST",
			dataType: "json",
			success: function(json){
				if(json.list){
					$('#inputNomeContato').val(json.list[0]);
					$('#inputRazaoContato').val(json.list[1]);
					$('#inputTelContato').val(json.list[2]);
					$('#inputTel2Contato').val(json.list[3]);
					$('#inputEmailContato').val(json.list[4]);
					$('#inputEmail2Contato').val(json.list[5]);
				}else{
					$('#inputNomeContato').val("");
					$('#inputRazaoContato').val("");
					$('#inputTelContato').val("");
					$('#inputTel2Contato').val("");
					$('#inputEmailContato').val("");
					$('#inputEmail2Contato').val("");
				}
			},
			error: function(){
				$('#inputNomeContato').val("");
				$('#inputRazaoContato').val("");
				$('#inputTelContato').val("");
				$('#inputTel2Contato').val("");
				$('#inputEmailContato').val("");
				$('#inputEmail2Contato').val("");
			}
		});
	}else{
		$('#inputNomeContato').val("");
		$('#inputRazaoContato').val("");
		$('#inputTelContato').val("");
		$('#inputTel2Contato').val("");
		$('#inputEmailContato').val("");
		$('#inputEmail2Contato').val("");
	}
}	

<%
if((readonly == null) || (!readonly)){
	out.print("$(document).ready(function() {");
	out.print("		$('#"+id+"').on('blur', function() {");
	out.print("			preencheCamposPromptContato();");
	out.print("		});");
	out.print("});");
}
%>

$('#botaoPromptContato').click(function(){
	$('.div-tabela-contato').slideDown('fast');	
});

$('#btnFecharPrompt').click(function(){
	$('.div-tabela-contato').slideUp('fast');	
});

$('#tabelaContato').on('click', 'button#btnSelecionarPrompt', function() {
	var id = $(this).data('tr-id');
	$('#${id}').val($('#contato-'+id).val());
	preencheCamposPromptContato();	
	$('.div-tabela-contato').slideUp('fast');
});
</script>
