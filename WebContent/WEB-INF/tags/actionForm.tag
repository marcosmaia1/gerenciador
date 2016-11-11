<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="id" 		required="true" type="java.lang.Long" %>
<%@ attribute name="controller" required="true" type="java.lang.String" %>
<%
	if((id == null) || (id == 0)){
		out.println("action=\""+ request.getContextPath()+"/"+controller+"\"");
	}else{
		out.println("action=\""+ request.getContextPath()+"/"+controller+"/"+id+"\"");		
	}
 %>
