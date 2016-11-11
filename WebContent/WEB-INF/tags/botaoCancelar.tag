<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="controller"     required="true"  type="java.lang.String"%>
<%@ attribute name="caminhoRotinaInterna"     required="false"  type="java.lang.String"%>


<button 
	id="cancelar" 
	type="button"
	onclick="window.location.href='${pageContext.request.contextPath}/${fn:toLowerCase(controller)}${caminhoRotinaInterna}'" 
	class="large warning" >
	<a title="Cancelar"></a>Cancelar
</button>