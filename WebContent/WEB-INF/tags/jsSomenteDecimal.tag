<%@ attribute name="id" required="true" type="java.lang.String" %>

<script type="text/javascript">
	$(function(){
	    $('#${id}').bind('keypress', somenteDecimal);
	});	
    $('#${id}').bind('paste', function (e) {
    	e.preventDefault();	
    });
    $('#${id}').blur(function () {
    	var numero = $('#${id}').val();
    	$('#${id}').val(number_format(numero, 2));
    });    
</script>