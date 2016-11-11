<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="id"          required="true"%>
<%@ attribute name="name"        required="false"%>
<%@ attribute name="value"       required="false"%>
<%@ attribute name="error"       required="false"%>
<%@ attribute name="label"       required="true"   type="java.lang.String"%>
<%@ attribute name="autofocus"   required="false"  type="java.lang.Boolean"%>
<%@ attribute name="readonly"    required="false"  type="java.lang.Boolean"%>
<%@ attribute name="operacao"    required="false"  type="br.com.gerenciador.enumeration.Operacao"%>
<%@ attribute name="placeholder" required="false"  type="java.lang.String"%>
<%@ attribute name="title"       required="false"  type="java.lang.String"%>

<div class="col-xs-6 col-sm-4 col-md-3 col-lg-3">
	<div id="div-error-${id}" class="form-group <c:if test="${error}">has-error</c:if>">
		<label for="${id}" class="control-label"><fmt:message key="${label}" />:</label>
		<input type="text" class="form-control" id="${id}" <c:if test="${readonly == true}">readonly</c:if> name="${name}" value="${value}" <c:if test="${title != null}">title="<fmt:message key="${title}"/>"</c:if>  <c:if test="${placeholder != null}">placeholder="<fmt:message key="${placeholder}"/>"</c:if> <c:if test="${autofocus == true}"> autofocus="autofocus"</c:if>>
	</div>
</div>
<script>
$('#${id}').focusout(function(){
    var phone, element;
    element = $(this);
    element.unmask();
    phone = element.val().replace(/\D/g, '');
    if(phone.length > 10) {
        element.mask("(99) 99999-999?9");
    } else {
        element.mask("(99) 9999-9999?9");
    }
}).trigger('focusout');
</script>