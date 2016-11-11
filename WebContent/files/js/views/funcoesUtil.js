function replaceAll(str, de, para){
    var pos = str.indexOf(de);
    while (pos > -1){
		str = str.replace(de, para);
		pos = str.indexOf(de);
	}
    return (str);
}

function stringToFloat(valor){
    
    if(valor === ""){
       valor =  0;
    }else{
  	  if(getLanguage() =='pt_BR'){
  	      valor = replaceAll(valor, ".","");
          valor = replaceAll(valor, ",",".");
  	  }else{
  		  valor = replaceAll(valor, ",","");
  	  }
       valor = parseFloat(valor);
    }
    
    return valor;

 }

function lpad(valor, quantidade, caracter) {
    var s = valor+"";
    while (s.length < quantidade) s = caracter + s;
    return s;
}	

function formatarData(date) {
    var d = new Date(date || Date.now()),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [day, month, year].join('/');
}

function validaData(dataValue)
{
    var data = dataValue;
    var dia = data.substring(0,2);
    var mes = data.substring(3,5);
    var ano = data.substring(6,10);
 
    //Criando um objeto Date usando os valores ano, mes e dia.
    var novaData = new Date(ano,(mes-1),dia);
 
    var mesmoDia = parseInt(dia,10) == parseInt(novaData.getDate());
    var mesmoMes = parseInt(mes,10) == parseInt(novaData.getMonth())+1;
    var mesmoAno = parseInt(ano) == parseInt(novaData.getFullYear());
 
    if (!((mesmoDia) && (mesmoMes) && (mesmoAno)))
    {
        return false;
    }  
    return true;
}	

function validaDataPeriodo(fromDate, toDate){
	var dataFrom = new Date(fromDate.substr(6, 4)+ '-'+fromDate.substr(3, 2)+ '-' +fromDate.substr(0, 2) + ' '+ fromDate.substr(11, 8));
	var dataTo = new Date(toDate.substr(6, 4)+ '-'+toDate.substr(3, 2)+ '-' +toDate.substr(0, 2) + ' '+ toDate.substr(11, 8));
	
	if (Date.parse(dataFrom) > Date.parse(dataTo)) {
		return false;
	}else{
		return true;
	}
}	

mensagemAlerta = function(mensagem, tipoAlerta){
	var legenda = "Alerta";
	var corFonte = "black"; 
	var corFundo = "white";
	
	switch (tipoAlerta) {
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
        content: mensagem,
        style: {background: corFundo, color: corFonte},
        timeout: 10000}); // 10 seconds
};

function somenteDecimal(e){
	var keyCode = e.which;
	var ShiftPress = e.shiftKey;
	var CtrlPress  = e.ctrlKey;
	var type = e.handleObj.origType;
	var keyCodesDownPermitidos = new Array();
	//alert(keyCode);
    if ((!ShiftPress) && (type == "keypress")){
		//teclas adicionais permitidas (backspace,tab,seta left,seta right, delete, decimal point)
	    keyCodesPermitidos = new Array(44,46);     //8,9,37,39,46,110,190,194,13);
		keyCodesDownPermitidos.push(13);
	    
	    //numeros e 0 a 9 do teclado alfanumerico
	    for(var x=48;x<=57;x++){
	        keyCodesPermitidos.push(x);
	    }
	     
	    //Pega a tecla digitada
	     
	    //Verifica se a tecla digitada é permitida
	    if (((!ShiftPress) && (!CtrlPress) && ($.inArray(e.keyCode,keyCodesDownPermitidos) != -1)) || ($.inArray(keyCode,keyCodesPermitidos) != -1)){
	        return true;
	    }else{
	    	return false;
	    } 
    }
}

function somenteNumero(e){

	var keyCode = e.which;
	var ShiftPress = e.shiftKey;
	var CtrlPress  = e.ctrlKey;
	var type = e.handleObj.origType;
	var keyCodesDownPermitidos = new Array();
	 if((!ShiftPress) && (type == "keypress")){
		
		 keyCodesDownPermitidos.push(13);
		 
		 keyCodesPermitidos = new Array();
	     
	    //numeros e 0 a 9 do teclado alfanumerico
	    for(var x=48;x<=57;x++){
	        keyCodesPermitidos.push(x);
	    }
	     
	    //Verifica se a tecla digitada � permitida
	    if (((!ShiftPress) && (!CtrlPress) && ($.inArray(e.keyCode,keyCodesDownPermitidos) != -1)) || ($.inArray(keyCode,keyCodesPermitidos) != -1)){
	    	return true;
	    }else{
	    	return false;
	    } 
	}
 }

function somenteNumeroTelefone(e){
	var keyCodesPermitidos = new Array();
    //teclas adicionais permitidas (backspace,tab,seta left,seta right, delete)
    keyCodesPermitidos = new Array(8,9,32,37,39,40,41,109,46);
     
    //numeros e 0 a 9 do teclado alfanumerico
    for(var x=48;x<=57;x++){
        keyCodesPermitidos.push(x);
    }
     
    //numeros e 0 a 9 do teclado numerico
    for(var x=96;x<=105;x++){
        keyCodesPermitidos.push(x);
    }
     
    //Pega a tecla digitada
    keyCode = e.which; 
     
    //Verifica se a tecla digitada � permitida
    if ($.inArray(keyCode,keyCodesPermitidos) != -1){
        return true;
    }    
    return false;
}

function getLanguage(){
	return 'pt_BR';
} 

function number_format(number, decimals) {
  
  if(getLanguage() == 'pt_BR'){
	  dec_point = ',';
	  thousands_sep = '.';
  }else{
	  dec_point = '.';
	  thousands_sep = ',';
  }

  number = (number + '');
  number = number.replace(/[^0-9+\-Ee.]/g, '');
  var n = !isFinite(+number) ? 0 : +number,
    prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
    sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
    dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
    s = '',
    toFixedFix = function(n, prec) {
      var k = Math.pow(10, prec);
      return '' + (Math.round(n * k) / k)
        .toFixed(prec);
    };
  // Fix for IE parseFloat(0.55).toFixed(0) = 0;
  s = (prec ? toFixedFix(n, prec) : '' + Math.round(n))
    .split('.');
  if (s[0].length > 3) {
    s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
  }
  if ((s[1] || '')
    .length < prec) {
    s[1] = s[1] || '';
    s[1] += new Array(prec - s[1].length + 1)
      .join('0');
  }
  return s.join(dec);
}

function lowerText() {
	$(".minusculo").bind('paste', function(e) {
		var el = $(this);
		setTimeout(function() {
			var text = $(el).val();
			el.val(text.toLowerCase());
		}, 100);
	});

	$('.minusculo').focusout(function(){
		var el = $(this);
		if(el.val().trim() == ''){
			el.removeClass('texto-minusculo');	
		}else{
			if(!el.hasClass('texto-minusculo')){
				el.addClass('texto-minusculo');
			}	
		}
		
	});
		
	$(".minusculo").keypress(function() {
		var el = $(this);
		
		if(el.val().trim() != ''){
			if(!el.hasClass('texto-minusculo')){
				el.addClass('texto-minusculo');
			}	
		}else{
			if(el.hasClass('texto-minusculo')){
				el.removeClass('texto-minusculo');
			}	
		}
		setTimeout(function() {
			var text = $(el).val();
			el.val(text.toLowerCase());
		}, 100);
	});
}


function upperText() {
	$(".maiusculo").bind('paste', function(e) {
		var el = $(this);
		setTimeout(function() {
			var text = $(el).val();
			el.val(text.toUpperCase());
		}, 100);
	});
	
	$('.maiusculo').focusout(function(){
		var el = $(this);
		if(el.val().trim() == ''){
			el.removeClass('texto-maiusculo');	
		}else{
			if(!el.hasClass('texto-maiusculo')){
				el.addClass('texto-maiusculo');
			}	
		}
		
	});
		
	$(".maiusculo").keypress(function() {
		var el = $(this);
		
		if(el.val().trim() != ''){
			if(!el.hasClass('texto-maiusculo')){
				el.addClass('texto-maiusculo');
			}	
		}else{
			if(el.hasClass('texto-maiusculo')){
				el.removeClass('texto-maiusculo');
			}	
		}
		setTimeout(function() {
			var text = $(el).val();
			el.val(text.toUpperCase());
		}, 100);
	});
}

//////////////Início função de CPF e CNPJ
function verifica_cpf_cnpj ( valor ) {
    valor = valor.toString();
    valor = valor.replace(/[^0-9]/g, '');

    if ( valor.length === 11 ) {
        return 'CPF';
    }else if ( valor.length === 14 ) {
        return 'CNPJ';
    }else {
        return false;
    }
}

function calc_digitos_posicoes( digitos, posicoes, soma_digitos ) {
    digitos = digitos.toString();

    for ( var i = 0; i < digitos.length; i++  ) {
        soma_digitos = soma_digitos + ( digitos[i] * posicoes );
        posicoes--;

        if ( posicoes < 2 ) {
            posicoes = 9;
        }
    }

    soma_digitos = soma_digitos % 11;

    if ( soma_digitos < 2 ) {
        soma_digitos = 0;
    } else {
        soma_digitos = 11 - soma_digitos;
    }

    var cpf = digitos + soma_digitos;

    return cpf;
} // calc_digitos_posicoes

function valida_cpf( valor ) {
    valor = valor.toString();
    valor = valor.replace(/[^0-9]/g, '');
    var digitos = valor.substr(0, 9);
    var novo_cpf = calc_digitos_posicoes( digitos,10,0 );
    novo_cpf = calc_digitos_posicoes( novo_cpf, 11,0 );

    if ( novo_cpf === valor ) {
        return true;
    } else {
        return false;
    }
} // valida_cpf

function valida_cnpj ( valor ) {
    valor = valor.toString();
    valor = valor.replace(/[^0-9]/g, '');
    var cnpj_original = valor;
    var primeiros_numeros_cnpj = valor.substr( 0, 12 );
    var primeiro_calculo = calc_digitos_posicoes( primeiros_numeros_cnpj, 5, 0 );
    var segundo_calculo = calc_digitos_posicoes( primeiro_calculo, 6, 0  );
    var cnpj = segundo_calculo;

    if ( cnpj === cnpj_original ) {
        return true;
    }
    return false;
} // valida_cnpj

function valida_cpf_cnpj ( valor ) {
    //var valida = verifica_cpf_cnpj( valor );
    valor = valor.toString();
    valor = valor.replace(/[^0-9]/g, '');

    if ( valor.length == 11 ) {
        return valida_cpf( valor );
    }else if ( valor.length == 14 ) {
        return valida_cnpj( valor );
    }else {
        return false;
    }
} // valida_cpf_cnpj

function formata_cpf_cnpj( valor ) {
    var formatado = false;
    var valida = verifica_cpf_cnpj( valor );
    valor = valor.toString();
    valor = valor.replace(/[^0-9]/g, '');

    if ( valida === 'CPF' ) {
        if ( valida_cpf( valor ) ) {
            formatado  = valor.substr( 0, 3 ) + '.';
            formatado += valor.substr( 3, 3 ) + '.';
            formatado += valor.substr( 6, 3 ) + '-';
            formatado += valor.substr( 9, 2 ) + '';
        }
    }else if ( valida === 'CNPJ' ) {
        if ( valida_cnpj( valor ) ) {
            formatado  = valor.substr( 0,  2 ) + '.';
            formatado += valor.substr( 2,  3 ) + '.';
            formatado += valor.substr( 5,  3 ) + '/';
            formatado += valor.substr( 8,  4 ) + '-';
            formatado += valor.substr( 12, 14 ) + '';
        }
    } 
    return formatado;
} // formata_cpf_cnpj
//////////////Fim função de CPF e CNPJ

$(document).on("click", ".open-janelaModal", function() {
	$(".modal-body h4").text($(this).data('msg'));
	$(".modal-footer #form-excluir").attr("action", $(this).data('link'));
});

$('#janelaModal').on('shown.bs.modal', function () {
	$('#btnSimExcluir').focus();
});

$('.modal')
.on({
    'show.bs.modal': function() {
        var idx = $('.modal:visible').length;
        $(this).css('z-index', 1040 + (10 * idx));
    },
    'shown.bs.modal': function() {
        var idx = ($('.modal:visible').length) - 1;
        $('.modal-backdrop').not('.stacked').css('z-index', 1039 + (10 * idx)).addClass('stacked');
    },
    'hidden.bs.modal': function() {
        if ($('.modal:visible').length > 0) {
            setTimeout(function() {
                $(document.body).addClass('modal-open');
            }, 0);
        }
    }
});		

jQuery(document).ready(function($) {
	lowerText();
	upperText();
});
