function montarTabelaContato(path){
	table = $("#tabelaContato").dataTable({
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
		              {"targets": 4, "width": 150 },
		              { "orderable": false, "searchable": false, "targets": 0, "width": 86 }],			
		oLanguage : {
			sUrl : path + '/files/datatables/js/dataTables.pt_BR.txt',
			sSearch : 'Pesquisar:'
		}
	});
}

function atualizaCidade(path, codCidade) {
	var selecionado = $("#estadosSelect").val();
	if (selecionado.trim() != 0){
		$.ajax({
			url : path + "/cidade/jsonCidade",
			data : {
				estado : selecionado
			},
			type : "POST",
			dataType : "json", 
			success : function(json) {
				var options = [];
				for (var i = 0; i < json.list.length; i++) {
					if (json.list[i].id == codCidade) {
						options.push('<option value="' + json.list[i].id
								+ '" selected="selected">' + json.list[i].nome
								+ '</option>');
					} else {
						options.push('<option value="' + json.list[i].id + '">'
								+ json.list[i].nome + '</option>');
					}
				}
				$('#cidadesSelect').find('option').remove().end();
				$('#cidadesSelect').html(options.join(''));
			},
			error : function() {
				alert('erro');
				$('#cidadesSelect').html('<option selected="selected" value="0"></option>');
			}
		});
	} else {
		$('#cidadesSelect').html('<option selected="selected" value="0"></option>');
	}
}

function formataCampos(){
	if($("#tipoPessoaSelect").val() == "F"){
		$("#label-cpfCnpj").text("CPF");
		$("#cpfCnpj").mask("999.999.999-99");
	} else if ($("#tipoPessoaSelect").val() == "J"){
		$("#label-cpfCnpj").text("CNPJ");
		$("#cpfCnpj").mask("99.999.999/9999-99");
	}
}

$("#tipoPessoaSelect").on("change", function(){
	formataCampos();
});

$("#cpfCnpj").on("blur", function(){
	var valor = $("#cpfCnpj").val();
	if(valor != null && valor != "" && !valida_cpf_cnpj(valor)){
		mensagemAlerta("Número de documento inválido", "error");
		$("#div-cpfCnpj").addClass("error-state");
	} else {
		$("#div-cpfCnpj").removeClass("error-state");
	}
});

$( ".telefone").blur(function( event ) { 
    var phone, element;
    element = $(this);
    element.unmask();
    phone = element.val().replace(/\D/g, '');
    if(phone.length > 10) {
        element.mask("(99) 99999-999?9");
    } else {
        element.mask("(99) 9999-9999?9");
    }
});
