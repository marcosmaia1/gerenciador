<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="controller"     required="true"  type="java.lang.String"%>
<%@ attribute name="rotinaPaiId"     required="false"  type="java.lang.String"%>
<%@ attribute name="path"     required="true"  type="java.lang.String"%>
<%@ attribute name="somenteLeitura"     required="true"  type="java.lang.Boolean"%>

<%
	String onClick = "";
	if(rotinaPaiId == null || (rotinaPaiId != null && rotinaPaiId.equals(""))){
		onClick = "location.href='"+path+"/"+controller.toLowerCase()+"/novo'";
	}
	request.setAttribute("onClick", onClick);
%>
<button 
	id="adicionar" 
	class="large success" 
	<c:if test="${somenteLeitura}">disabled</c:if> 
	type="button" 
	onclick="${onClick}">Adicionar
</button>
<br />
<br />
<% 
	if(rotinaPaiId != null && !rotinaPaiId.equals("")){
		out.print("<script type=\"text/javascript\">\n");
		out.print("	$('#adicionar').on('click', function(){\n");
		out.print("		window.location.href='"+path+"/"+controller.toLowerCase()+"/"+rotinaPaiId+"/novo';\n");
		out.print("	});\n");
		out.print("</script>\n");
	}
%>