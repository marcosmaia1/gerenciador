<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="controller"     required="true"  type="java.lang.String"%>
<%@ attribute name="id"     required="true"  type="java.lang.String"%>

<button 
	id="excluir" 
	class="large danger"
	type="button"
	onclick="window.location.href='${pageContext.request.contextPath}/${fn:toLowerCase(controller)}/${id}/remove'"
	>
	<a title="Excluir"></a>Excluir
</button>
