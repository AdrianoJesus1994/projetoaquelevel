
$(document).ready(function(){
    $("#txt_cpf").mask("000.000.000-00");
    $("#txt_dataNasc").mask("00/00/0000");
    
    document.forms[0].addEventListener("focus", function (event) { 
        alert("teste");
     }, false)

    //Verifica se os camopos de senha estão iguais no momento do submit.
    document.forms[0].addEventListener("submit", function(){
        if ($("#txt_senha").val() == $("#txt_senhaConfirm").val()) {
            return;
        }
        alert("As senha digitas não conferem.");
        event.preventDefault();
    } ,false);


});
