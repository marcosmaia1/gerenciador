$(document).on('submit', function(){
	$(':input').removeAttr('disabled');
});

function botoesForm(controller, operacao){
	if (operacao == 'EXIBINDO'){
		$('form').attr('action', controller+"/edita");
		$('form').attr('method', 'GET');
	}
}