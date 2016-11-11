<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="controller"     required="true"  type="java.lang.String"%>
<%@ attribute name="id"     required="true"  type="java.lang.String"%>
<%@ attribute name="somenteLeitura"     required="true"  type="java.lang.Boolean"%>

<button 
	class="default default"
	type="button" 
	onclick="window.location.href='${pageContext.request.contextPath}/${fn:toLowerCase(controller)}/${id}'">
	<a title="Exibir">
		<i class="icon-search fg-white" id="icone"></i>
	</a>
</button>