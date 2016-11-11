<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="controller"     required="true"  type="java.lang.String"%>
<%@ attribute name="id"     required="true"  type="java.lang.String"%>
<%@ attribute name="somenteLeitura"     required="true"  type="java.lang.Boolean"%>

<button 
	class="default primary"
	type="button" 
	<c:if test="${somenteLeitura}">disabled</c:if> 
	onclick="window.location.href='${pageContext.request.contextPath}/${fn:toLowerCase(controller)}/${id}/edita'">
	<a title="Editar"><i class="icon-pencil"></i></a>
</button>