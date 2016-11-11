<%@ attribute name="id" required="true" type="java.lang.String" %>
<script type="text/javascript">
	$(function(){
	    $('#${id}').bind('keypress',somenteNumero);
	    $('#${id}').bind('paste', function (e) {
	    	e.preventDefault();	
	    });
	});
</script>