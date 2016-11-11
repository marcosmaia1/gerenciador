<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="id"             required="true"%>
<%@ attribute name="name"           required="false"%>
<%@ attribute name="value"          required="false"  type="java.util.Date"%>
<%@ attribute name="error"          required="false"%>
<%@ attribute name="label"          required="true"   type="java.lang.String"%>
<%@ attribute name="pattern"        required="false"  type="java.lang.String"%>
<%@ attribute name="mask"           required="false"  type="java.lang.String"%>
<%@ attribute name="format"         required="false"  type="java.lang.String"%>
<%@ attribute name="autofocus"      required="false"  type="java.lang.Boolean"%>
<%@ attribute name="pickTime"       required="false"  type="java.lang.Boolean"%>
<%@ attribute name="readonly"       required="false"  type="java.lang.Boolean"%>
<%@ attribute name="disabled"       required="false"  type="java.lang.Boolean"%>
<%@ attribute name="operacao"       required="false"  type="br.com.gerenciador.enumeration.Operacao"%>
<%@ attribute name="placeholder"    required="false"  type="java.lang.String"%>
<%@ attribute name="title"          required="false"  type="java.lang.String"%>
<%@ attribute name="ocultarCampo"   required="false"  type="java.lang.Boolean"%>
<%@ attribute name="ocultarLabel"   required="false"  type="java.lang.Boolean"%>
<%@ attribute name="removeTamanho"  required="false"  type="java.lang.Boolean"%>
<%
Boolean mostrarHora = false;
if((pickTime != null) && (pickTime)){
	mostrarHora = true;
}
String dataMask = "99/99/9999";
if((mask != null) && (mask != "")){
	dataMask = mask;
}
String dataPattern = "dd/MM/yyyy";
if((pattern != null) && (pattern != "")){
	dataPattern = pattern;
}
String dataFormat = "dd/MM/yyyy";
if((format != null) && (format != "")){
	dataFormat = format;
}
request.setAttribute("dataFormat", dataFormat);request.setAttribute("dataPattern", dataPattern);
request.setAttribute("mostrarHora", mostrarHora);request.setAttribute("dataMask", dataMask);
%>
<c:if test="${((removeTamanho == null) || ((removeTamanho != null) && (!removeTamanho)))}">
	<div id="div-${id}" <c:if test="${ocultarCampo == true}">style="display: none;"</c:if> class="col-xs-10 col-sm-4 col-md-3 col-lg-3">
		<div id="div-error-${id}" class="input-control text <c:if test="${error}">error-state</c:if>">
			<c:if test="${((ocultarLabel == null) || ((ocultarLabel != null) && (!ocultarLabel)))}">
				<label for="${id}" class="control-label"><fmt:message key="${label}" />:</label>
			</c:if>
			<c:if test="${(name != '') && (readonly != null) && (readonly == true)}">
				<input type="hidden" name="${name}" value="<fmt:formatDate value="${value}" pattern="${dataPattern}"/>">
			</c:if>
			<div class="input-group date" id="div${id}">
				<input 
					<c:if test="${(readonly == null) || (readonly == false)}">name="${name}"</c:if> id="${id}" 
					<c:if test="${readonly == true}">readonly</c:if> type="text" value="<fmt:formatDate value="${value}" pattern="${dataPattern}"/>" 
					class="input-control text" data-format="${dataFormat}" data-mask="${dataMask}" <c:if test="${title != null}">title="<fmt:message key="${title}"/>"</c:if>  
					<c:if test="${placeholder != null}">placeholder="<fmt:message key="${placeholder}"/>"</c:if> 
					<c:if test="${autofocus == true}"> autofocus="autofocus"</c:if>
				/>
				<span class="input-group-addon">
					<span class="fam-date"></span>
				</span>
			</div>
		</div>
	</div>											
</c:if>
<script>
if ((('${operacao}' == '') && ('${readonly}' != 'true')) || (('${readonly}' != 'true') && ('${operacao}' != 'EXIBICAO'))){
	$('#div${id}').datetimepicker({
		pick12HourFormat: false,
		pickTime: <%=mostrarHora%>,
		autoClose: true,
		language: '${language}'
	});
}
<%
	if((disabled != null) && (disabled)){
		out.print("$('#div"+id+"').datetimepicker('disable');\n");
	}
%>

</script>