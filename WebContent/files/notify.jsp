<c:if test="${not empty success}">
	<script type="text/javascript">
		$(function() {
		    var not = $.Notify({
		    	caption: "Sucesso",
		    	content: "${success}",
		        style: {background: 'green', color: 'white'},
		        timeout: 10000}); // 10 seconds
		});
	</script>
</c:if>
<c:if test="${not empty error}">
	<script type="text/javascript">
		$(function() {
		    var not = $.Notify({
		    	caption: "Erro",
		        content: "${error}",
		        style: {background: 'red', color: 'white'},
		        timeout: 10000}); // 10 seconds
		});
	</script>
</c:if>
<c:if test="${not empty info}">
	<script type="text/javascript">
		$(function() {
		    var not = $.Notify({
		    	caption: "Info",
		        content: "${info}",
		        style: {background: 'blue', color: 'white'},
		        timeout: 10000}); // 10 seconds
		});
	</script>
</c:if>
<c:if test="${not empty warning}">
	<script type="text/javascript">
		$(function() {
		    var not = $.Notify({
		    	caption: "Cuidado",
		        content: "${warning}",
		        style: {background: 'red', color: 'white'},
		        timeout: 10000}); // 10 seconds
		});
	</script>
</c:if>
<c:if test="${not empty alert}">
	<script type="text/javascript">
		$(function() {
		    var not = $.Notify({
		    	caption: "Alerta",
		        content: "${alert}",
		        style: {background: 'white', color: 'black'},
		        timeout: 10000}); // 10 seconds
		});
	</script>
</c:if>
<c:if test="${not empty errors }">
	<script type="text/javascript">
		$(function() {
			<c:forEach items="${errors}" var="error">
				var legenda = "Alerta";
				var corFonte = "black"; 
				var corFundo = "white";
				switch ("${error.category}") {
					case "alert": corFundo = "white"; corFonte = "black"; legenda = "Alerta";
						break;
					case "error": corFundo = "red"; corFonte = "white"; legenda = "Erro";
						break;
					case "info": corFundo = "blue"; corFonte = "white"; legenda = "Informação";
						break;
					case "warning": corFundo = "yellow"; corFonte = "black"; legenda = "Cuidado";
						break;
					case "success": corFundo = "green"; corFonte = "white"; legenda = "Sucesso";
				}
			    var not = $.Notify({
			    	caption: legenda,
			        content: "${error.message}",
			        style: {background: corFundo, color: corFonte},
			        timeout: 10000}); // 10 seconds
			</c:forEach>
		});
	</script>
</c:if>
<script type="text/javascript">
/* if(/Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ) {
	$('.selectpicker').selectpicker('mobile');
}
$(function () {
    // Set theme based on URI
         $(function () {
             $.scrollUp({
                 animation: 'fade'
             });
         });
         $('.pill-switch').addClass('active');
 }); */
</script>