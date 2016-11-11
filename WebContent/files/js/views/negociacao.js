function montarTabelaNegociacao(path){
	table = $("#tabelaNegociacao").dataTable({
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
		columnDefs: [ {"targets": 0, "width": 100 },
		              {"targets": 1, "width": 90, "orderable": true, "searchable": true},
		              {"targets": 2, "width": 130, "orderable": true, "searchable": true },
		              {"targets": 4, "width": 130, "orderable": true, "searchable": true },
		              { "orderable": false, "searchable": false, "targets": 0, "width": 86 }],			
		oLanguage : {
			sUrl : path + '/files/datatables/js/dataTables.pt_BR.txt',
			sSearch : 'Pesquisar:'
		}
	});
}

function montarTabelaContato(path){
	table = $("#tabelaContato").dataTable({
		lengthMenu: [[5], [5]],
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
		columnDefs: [ {"targets": 0, "width": 90 },
		              {"targets": 1, "width": 100 },
		              {"targets": 4, "width": 150 },
		              { "orderable": false, "searchable": false, "targets": 0, "width": 86 }],			
		oLanguage : {
			sUrl : path + '/files/datatables/js/dataTables.pt_BR.txt',
			sSearch : 'Pesquisar:'
		}
	});
}

function montarTabelaHistorico(path){
	table = $("#tabelaHistorico").dataTable({
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
		columnDefs: [ {"targets": 0, "width": 100 },
		              {"targets": 1, "width": 100 },
		              {"targets": 2, "width": 150 },
		              {"targets": 3, "width": 150 },
		              {"targets": 4, "width": 600 },
		              { "orderable": false, "searchable": false, "targets": 0, "width": 86 }],			
		oLanguage : {
			sUrl : path + '/files/datatables/js/dataTables.pt_BR.txt',
			sSearch : 'Pesquisar:'
		}
	});
}

function verificaStatus(){
	if($('#statusSelect').val() == 'CONCLUIDO'){
		$('#inputValorFechamento').removeAttr('readonly');
		$('#fechamentoSelect').val('F100');
	} else {
		$('#inputValorFechamento').attr('readonly', true);
	}
}

function verificaFechamento(){
	if($('#fechamentoSelect').val() == 'F100'){
		$('#statusSelect').val('CONCLUIDO');
		$('#inputValorFechamento').removeAttr('readonly');
	} else {
		$('#inputValorFechamento').attr('readonly', true);
	}
}

$("#datepicker").datepicker();

$("#datepicker").on("blur", function(){
	var data = new Date($("#datepicker").val() || Date.now());
	//data.replace(".", "/");
	$("#datepicker").val(formatarData(data));
	console.log(formatarData(data));
});

$(document).submit(function(){
	$(":input").removeAttr("disabled");
});

$('#statusSelect').change(function(){
	verificaStatus();
});

$('#fechamentoSelect').change(function(){
	verificaFechamento();
});
