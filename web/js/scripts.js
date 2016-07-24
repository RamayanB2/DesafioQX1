var elementos = [
{id: 0, descricao: 'Espresso'},
{id: 1, descricao: 'Capuccino'},
{id: 2, descricao: 'Mocca'},
{id: 3, descricao: 'Caf&eacute; au lait'}]

jQuery(document).ready(function($) {
		$("#botao").on("click",showId);
		loadData(elementos);	
});

function loadData(data){
	var opt;
	opt =  $('<option> -- Selecione -- </option>');
	opt.appendTo('#combo');
	for (i = 0; i < data.length; i++) {
		opt =  $('<option>'+data[i].descricao+'</option>')
					.attr('class', "opt")
					.attr('value', i);
		console.log(data[i]);
		opt.appendTo('#combo');
	}
}

function showId(){
	var id = $( "#combo option:selected").val();
	var text = $( "#combo option:selected").text();
	if(text!=" -- Selecione -- ")alert("Id: "+id+" - Um "+text);
	else alert("Nenhuma opcao foi selecionada.");
}