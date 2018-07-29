$(document).ready(function () { 

    $(".alturaReservatorio").hide();
    $(".larguraReservatorio").hide();
    $(".profundidadeReservatorio").hide();
    $(".raioMaiorReservatorio").hide();
    $(".raioMenorReservatorio").hide();

    $("#btn-imprimir").hide();
    $(".relatorio").hide();

    //verifica o tipo de tanque
    $("#lst_tipoReservatorio").on("change", function(){
        if($(this).val() == 1){
            //Se for hortoedro ou cubico
            $(".alturaReservatorio").show();
            $(".larguraReservatorio").show();
            $(".profundidadeReservatorio").show();
            $(".raioMaiorReservatorio").hide();
            $(".raioMenorReservatorio").hide();
        }else if($(this).val() == 2){
            //se for tronco de cone
            $(".alturaReservatorio").show();
            $(".larguraReservatorio").hide();
            $(".profundidadeReservatorio").hide();
            $(".raioMaiorReservatorio").show();
            $(".raioMenorReservatorio").show();

        }else if($(this).val() == 3){
            //se for cilindrico
            $(".alturaReservatorio").show();
            $(".larguraReservatorio").hide();
            $(".profundidadeReservatorio").hide();
            $(".raioMaiorReservatorio").show();
            $(".raioMenorReservatorio").hide();
        }
    })

    //grafico();

    

 });

 function gerarRelatorio(){
    if($("#lst_nomeReservatorio").val() == ""){
        alert("Informe o Reservatório");
    }else if($("#txt_dataInicio").val() == ""){
        alert("Informe o Início");
    }else if($("#txt_dataFim").val() == ""){
        alert("Inform o Final");
    }else{
        $.ajax({
            method: 'GET',
            url: '/relatorios/historico?reserv='+ $("#lst_nomeReservatorio").val() +'&ini='+ $("#txt_dataInicio").val() +'&fim='+$("#txt_dataFim").val(),
            beforeSend: function(){
                alert("Carregando...");
            },
            success: function(data){
                console.log(data.length);

                grafico(data);

                for (var i = 0; i < data.length; i++) {
                    console.log("Data> " + data[i].data + " Volume> " + data[i].volume);
                    $("#conteudo").append("<tr><td>"+ simpleDateFormate(data[i].data) +"</td>" + "<td>"+ data[i].volume +"</td></tr>");
                   
                }
                $(".relatorio").show();
                $("#btn-imprimir").show();
            }

        });
    }

 }

 function imprimirRelatorio(){
     window.print();
 }

 function simpleDateFormate(date){
     var data = new Date(date);
     return data.getDate() + "/" + (data.getMonth()+1) + "/" + data.getFullYear();
 }

 var grafico = function(data){
    var ctx = document.getElementById('myChart').getContext('2d');
    var chart = new Chart(ctx, {
        // The type of chart we want to create
        type: 'line',
    
        // The data for our dataset
        data: {
            labels: [simpleDateFormate(data[0].data), simpleDateFormate(data[1].data), simpleDateFormate(data[2].data)],
            datasets: [{
                label: "Histórico de Consumo (L)",
                backgroundColor: 'rgba(255, 99, 132, 0)',
                borderColor: 'rgb(0,64,255)',
                data: [data[0].volume, data[1].volume, data[2].volume],
            }]
        },
    
        // Configuration options go here
        options: {}
    });
 }