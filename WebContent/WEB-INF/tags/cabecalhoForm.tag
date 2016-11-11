<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="controller"     required="true"  type="java.lang.String"%>
<%@ attribute name="operacao"     required="true"  type="java.lang.String"%>
<%@ attribute name="entidade"     required="true"  type="java.lang.String"%>
<%@ attribute name="value"    required="false"  type="java.lang.Boolean"%>

<h1>
	<a href="${pageContext.request.contextPath}/${fn:toLowerCase(controller)}"><i class="icon-arrow-left-3 fg-darker smaller"></i></a> ${entidade}<small class="on-right"> ${operacao}</small>
</h1>
<br />