function montarTabelaOperador(path){
	table = $("#tabelaOperador").dataTable({
		retrieve: true,
		responsive : true,
		bAutoWidth : true,
		bPaginate : true,
		bFilter : true,
		bSort : true,
		bInfo : true,
		bJQueryUI : false,
		sPaginationType : 'simple_numbers',
		bProcessing : false,
		columnDefs: [ {"targets": 0, "width": 150 },
		              {"targets": 1, "width": 100 },
		              { "orderable": false, "searchable": false, "targets": 0, "width": 86 }],			
		oLanguage : {
			sUrl : path + '/files/datatables/js/dataTables.pt_BR.txt',
			sSearch : 'Pesquisar:'
		}
	});
}

$(document).on("ready", function(){
	$("#senha2").val($("#senha1").val());
});

$("#senha2").on("blur", function(){
	if($("#senha2").val() != $("#senha1").val()){
		mensagemAlerta("As senhas estão diferentes.", "error");
		$("#div-senha2").addClass("error-state");
		$("#div-senha1").addClass("error-state");
	} else {
		$("#div-senha2").removeClass("error-state");
		$("#div-senha1").removeClass("error-state");
	}
});