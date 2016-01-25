<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<%@ page import="org.apache.struts.action.*, org.apache.struts.Globals" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String valorlista = (String)session.getAttribute("verLista");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >

<SCRIPT LANGUAGE=javascript src="js/overlibmws.js"></SCRIPT>
<script LANGUAGE="JavaScript" src="js/avatec.js"></script>

<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />

<link rel="stylesheet" href="menuvertical/css/style.css" type="text/css" />
<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />
<link rel="stylesheet" href="css/modelo.css" type="text/css" />
<style type="text/css">
	
	/*h3 {
	 	text-transform:capitalize;
	  	font-family:Arial, Helvetica, sans-serif;
	}
	p {
		font-size:16px;
		font-family:Arial, Helvetica, sans-serif;
	}
	*/
	#mensajegeneral {
	display:none;
	position:relative;

	padding:0px;
	top: 50%;
	left: 50%; 
	text-align: center;
	font-family: Verdana, sans-serif;
	font-size: 13px;
	font-weight: bold;
	line-height: 100%;
	color: #003366;
	width: 780px;
	height: 80px;
	margin-left: -390px;
	background: #f1f4ac;
	overflow:auto;
	
	border: 2px solid #acc835;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;	
	clear: both;
	}
	
	#cuadrocodigo {
	/*display:none;*/
	position:absolute;
	left:91%; 
	width:60px;
	height:25px;
	padding-top:5px;
	top:5px;
	text-align: center;
	line-height: 100%;
	 color: #DF0101;	
	 FONT-SIZE: 14pt; 
	 font-weight: bold;
	background: #F5A9A9;
	/*overflow:auto;*/
	border: 1px solid #336699;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;	
	/*clear: both;*/
	}
	
	#complemento {
		visibility:hidden;
		position:fixed;
		float: center;
		padding:2px;
		top: 25%;
		left: 50%; 
		
	width: 400px;
	margin-left: -200px;
	background: #f0f0f0;
	overflow:auto;
	
	border: 1px solid #cccccc;
	-moz-border-radius: 7px;
	-webkit-border-radius: 7px;
	border-radius: 7px;	
	
	-moz-box-shadow: 2px 2px 2px #010101;
	-webkit-box-shadow: 2px 2px 2px #010101;
	box-shadow: 2px 2px 2px #010101;
	z-index:3;
	}
	
	#containerlista {
		visibility:hidden;
		position:fixed;
		float: center;
		padding:2px;
		top: 15%;
		left: 50%; 
		line-height: 120%;

	width: 1000px;
	margin-left: -500px;
	background: #FFFFFF;
	overflow:auto;
	
	border: 1px solid #cccccc;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;	
	z-index:3;
	}
	
	#containerlistaproyectos {
		visibility:hidden;
		position:fixed;
		float: center;
		padding:2px;
		top: 15%;
		left: 50%; 
		line-height: 120%;

	width: 1000px;
	margin-left: -500px;
	background: #FFFFFF;
	overflow:auto;
	
	border: 1px solid #cccccc;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;	
	z-index:3;
	}
	
	#capaFondo1 {
		visibility:hidden;
		position:fixed;
		padding:0px;
		left:0px;
		top:0px;
		right:0px;
		bottom:0px;
		background-color: #808080;
		background-repeat:repeat;
		width:100%;
		height:100%;
		z-index:2;
		filter: alpha(opacity=50);
		opacity: 0.8;
		-moz-opacity:0.8;
		-webkit-opacity:0.8;
		-o-opacity:0.8;
		-ms-opacity:0.8;
	}
	
	#Theadsmall
	{	/*BACKGROUND-COLOR: #30569D;*/
	font-size : 14px;
	color: #FFFFFF;
	font-weight: bolder;
	font-style :  normal;
	text-align: center;
	text-valign: center;
	font-family : Arial, Helvetica, sans-serif;
    width: 100%;
    height: 30px;
    margin-left: auto;
	margin-right: auto;
	padding: 0px;
	/*background: url(../img/bg1.png) ;*/
	background:#6E6E6E;
	/*overflow:auto;*/
	
	border: 0px solid #cccccc;
	-moz-border-radius: 7px;
	-webkit-border-radius: 7px;
	border-radius: 7px;	
	}
	
</style>

<style type="text/css">
.buttoncss{
	/*margin:10px 55px 10px 0px;*/
	/*font-weight: bold;*/
	/*line-height: 1;*/
	padding: 6px 10px;
	cursor:pointer;   
	color: #fff;
	text-align: center;
	/*text-shadow: 0 -1px 1px #64799e;*/
	
	/* Background gradient */
	background: #ffffff;
	background: -moz-linear-gradient(top, #ffffff 0%, #c4c4c4 100%);
	background: -webkit-gradient(linear, 0% 5%, 0% 100%, from(#ffffff), to(#c4c4c4));
	
	/* Border style */
  	border: 1px solid #6d6d6d;  
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
  
	/* Box shadow */
	/*-moz-box-shadow: inset 0 1px 0 0 #aec3e5;
	-webkit-box-shadow: inset 0 1px 0 0 #aec3e5;
	box-shadow: inset 0 1px 0 0 #aec3e5;*/
	
	-moz-box-shadow: 0px 2px 2px #7e7e7e;
	-webkit-box-shadow: 0px 2px 2px #7e7e7e;
	box-shadow: 0px 2px 2px #7e7e7e;
}

.buttoncss:hover {
	/*background: #848FB2;*/
    cursor: pointer;
	}
	
</style>

<script type="text/javascript">
	
$(document).ready(function(){	
	
	$(function() {
	    $( "#dialog-confirm-envio" ).dialog({
	      autoOpen: false,	
	      resizable: false,
	      height:180,
	      modal: true,
	      buttons: {
	        /*"Eliminar documento": function() {
	          var codigo = $( this ).dialog( "option", "appendTo" );
	          //alert(codigo);
	          var strURL = "MantenimientoDocumentoInterno.do?cod_doc_interno="+codigo+"&tipofirma=si";
	          location = strURL;
	          $( this ).dialog( "close" );
	        },*/
	        "Cancelar": function() {
	        //	var strURL = "ReportesGestion.do?tipo=mos_cons_tran";
	        //	location = strURL;
	          $( this ).dialog( "close" );
	        }
	      },
	      close: function() {
	    	//  var strURL = "ReportesGestion.do?tipo=mos_cons_tran";
	      	//location = strURL;
	    	  $( this ).dialog( "close" );
	        }
	    });
	  });
		
	$(function() {
	    $( "#dialog-confirm" ).dialog({
	      autoOpen: false,	
	      resizable: false,
	      height:180,
	      modal: true,
	      buttons: {
	        "Eliminar documento": function() {
	          var codigo = $( this ).dialog( "option", "appendTo" );
	          //alert(codigo);
	          var strURL = "MantenimientoDocumentoInterno.do?cod_doc_interno="+codigo;
	          location = strURL;
	          $( this ).dialog( "close" );
	        },
	        Cancelar: function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });
	  });
	
	$(function() {
	    $( "#dialog-confirminicia" ).dialog({
	      autoOpen: false,	
	      resizable: false,
	      height:180,
	      modal: true,
	      buttons: {
	        "Eliminar documento": function() {
	          var codigoz = $( this ).dialog( "option", "appendTo" );
	          //alert(codigo);
	          var strURL = "MantenimientoDocumentoInterno.do?cod_doc_interno="+codigoz+"&tipofirma=si";
	          location = strURL;
	          $( this ).dialog( "close" );
	        },
	        Cancelar: function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });
	  });

});
	
$(document).ready(function(){
	
$(function() {
    var availableTags = [
      "Sr.",
      "Sra.",
      "Dr.",
      "Dra.",
      "Eco.",
      "Abo.",
      "Ing.",
      "Mg."
    ];
    $( "#abreviatura_grado_profesion" ).autocomplete({
      source: availableTags
    });
  });
	
/*$(function() {
    $( document ).tooltip();
    
  });	*/
  
  $( "#tabs" ).tabs();
  $( "#tabsproyectos" ).tabs();
	
	$('#clickme').click(function() {
		if ($("#concopia").is(":hidden")) {
			$("#concopia").slideDown("slow");
		} else {
			$("#concopia").hide();
		}
	});
	
	$("#agregarAdjunto").button();
	$("#firmarDocumento").button();
	
	$( "#cradio1" ).buttonset();
	$( "#cradio2" ).buttonset();
	$( "#cradio3" ).buttonset();
	$( "#cradio4" ).buttonset();
	$( "#cradio5" ).buttonset();
	$( "#cradio6" ).buttonset();
	$( "#cradio7" ).buttonset();
	$( "#cradio8" ).buttonset();
	$( "#cradio9" ).buttonset();
	
	$("#botoninvisible").mouseover(function(event){
	      $("#botoninvisible").addClass("buttoncss");
	   });
	$("#botoninvisible").mouseout(function(event){
	      $("#botoninvisible").removeClass("buttoncss");
	});
	   
	$("#botoninvisibleAnular").mouseover(function(event){
		      $("#botoninvisibleAnular").addClass("buttoncss");
		   });
		   $("#botoninvisibleAnular").mouseout(function(event){
		      $("#botoninvisibleAnular").removeClass("buttoncss");
		});
	
		   $("#botoninvisibleProyectos").mouseover(function(event){
			      $("#botoninvisibleProyectos").addClass("buttoncss");
			   });
			   $("#botoninvisibleProyectos").mouseout(function(event){
			      $("#botoninvisibleProyectos").removeClass("buttoncss");
			});
		   
	$('#botoninvisible').click(function() {
		$("#containerlista").css("visibility","visible");
  	   	$("#capaFondo1").css("visibility","visible");
  	   	$("#personas").load("obtenerLista.do", {cambia:'setear'});	
	});
	
	$('#botoninvisibleProyectos').click(function() {
		$("#containerlistaproyectos").css("visibility","visible");
  	   	$("#capaFondo1").css("visibility","visible");
  	   	$("#personas").load("obtenerLista.do", {cambia:'setear2'});	
	});
	
	$('#botoninvisibleAnular').click(function() {
		var strURL = "ListaDocumentos.do?tipo=anular&operacion=A&inicia=SI&document=SI";
		location=strURL;	
		return;
	});
	
	$("#cerrar-panel").click(function () {
	      $("#panel").slideUp("fast");
    });
	
	$("#cerrar-mensajegeneral").click(function () {
	      $("#mensajegeneral").slideUp("fast");
  	});
	
	$("#cerrar-aviso-checked").click(function () {
	    $("#aviso-checked").slideUp("fast");
	});
		
});

	
	$(document).ready(function(){
		$("#enviar-complemento").click(function () {
	    	   $("#complemento").css("visibility","hidden");
	    	   $("#capaFondo1").css("visibility","hidden");
	    	   
			var codigo=$("#valor1").val();
			var tipo=$("#valor2").val();
			var observacion=$("#observacion").val();
			var tipoenvio=$("#accionCode").val();
			var ppaginaderivacion = $("#ppaginaderivacion").val();
			
			if(tipoenvio==null){
				tipoenvio=0;
			}
			if(ppaginaderivacion==null){
				ppaginaderivacion=0;
			}
			
			if(codigo==""){
				//alert("entre");
				codigo=$("#pcodigodocumentointerno").val();
				tipo=$("#ptipocodigodocumentointerno").val();
			}
			//alert(codigo+","+tipo);
			var strURL = "CopiarPDFATemporal.do?codDoc="+codigo+"&codTip="+tipo+"&observacion="+observacion+"&tipoenvio="+tipoenvio+"&pagina="+ppaginaderivacion;
			referenciaVentanaFirma = window.open(strURL,"Firma Digital","scrollbars=YES,resizable=YES,location=NO,WIDTH=730,HEIGHT=430");
			//var strURL = "UtilidadRegistroMovimiento.do?codDoc="+codigo+"&codTip="+tipo+"&observacion="+observacion+"&tipoenvio="+tipoenvio;
			//location=strURL;
			
			return;
	    });
		
		$("#cerrar-complemento").click(function () {
	    	   $("#complemento").css("visibility","hidden");
	    	   $("#capaFondo1").css("visibility","hidden");
	    });
		

	});
	
	$(document).ready(function(){
	
	    var value = 'codAccion';
	    //llama al servlet con el parametro seleccionado
	    $("#accionCode").load("obtenerLista.do", {cambia:value});
	    
	    var value2 = 'codAccionffff';
	    //llama al servlet con el parametro seleccionado
	    $("#accionCode").load("obtenerLista.do", {cambia:value2});	
		
	    var texto = "<%=valorlista%>";
	    
	    if(texto == null){
	    	
	    }else{
	    	if(texto == "1"){
	    	$("#containerlista").css("visibility","visible");
	     	$("#capaFondo1").css("visibility","visible");
	    	}
	    	if(texto == "2"){
		    	$("#containerlistaproyectos").css("visibility","visible");
		     	$("#capaFondo1").css("visibility","visible");
		    	}
	    }
	});
	
</script>


<script language="javascript">

function mostraralerta(){
	  // Asumiendo que tenemos #shoutbox
	  //alert("daaa");
	  //$('#alerta').load('mostraralerta.do');
}
	
//setInterval("mostraralerta()", 10000 );

	
function fn_onLoad(){
	cambiar_color_tabla("b", "tablaBackgray");

}

	
function Cambiachecks(){
	//activo y desactivo los checks
	$("#cuadrodelcheck-show").hide();
	$("#cuadrodelcheck-hide").show();
}

function Ocultadata(){
	//activo y desactivo los campos
	$("#no-muestra1").hide();
	$("#no-muestra2").hide();
	$("#no-muestra3").hide();
	$("#no-muestra4").hide();
	$("#no-muestra5").hide();
	$("#no-muestra6").hide();
}

function deletedocument(codDoc){
	var coddoc = codDoc;
		
	$( "#dialog-confirm" ).dialog({ appendTo: coddoc });
	$( "#dialog-confirm" ).dialog( "option", "appendTo", coddoc );
	$( "#dialog-confirm" ).dialog( "open");

}

function deletedocumentinicia(codDoc2){
	var coddoc2 = codDoc2;
		
	$( "#dialog-confirminicia" ).dialog({ appendTo: coddoc2 });
	$( "#dialog-confirminicia" ).dialog( "option", "appendTo", coddoc2 );
	$( "#dialog-confirminicia" ).dialog( "open");
	
}

function ocultar(element,valor){
	if (valor=="mostrar"){
	              document.getElementById(element).style.visibility="visible";
	}else {
	              document.getElementById(element).style.visibility="hidden";
	}
	
	function mostrar(nombreCapa){ 
		document.getElementById(nombreCapa).style.visibility="visible"; 
		} 
	function ocultar(nombreCapa){ 
		document.getElementById(nombreCapa).style.visibility="hidden"; 
		}
}		
</script>
  

<script language="Javascript" type="text/javascript">
function showProducts(){
    //obtiene los objetos productCode, 
    var code=$("#codigo_oficina").val(); //.. y se obtiene el valor
    var val2 = 'ofiInterno';
    //llama al servlet con el parametro seleccionado
    $("#personas").load("obtenerLista.do", {cambia:val2,codigo_oficina:code});
}

function Guardar(){	
	document.forms[0].submit();
}
 
 function anulardocumentos(){
	var strURL = "ListaDocumentos.do?tipo=anular&operacion=A&inicia=SI&document=SI";
	location=strURL;
	
	return;
}
 
 function AgregarDocInternos() {
	var strURL="AdjuntarDocumentoInterno.do";
	//winPopup = window.open(strURL,"","scrollbars=YES,resizable=YES,location=NO,WIDTH=650,HEIGHT=300");
	winPopup = window.open(strURL,"","location=NO, toolbar, top=0, left=0, width='+screen.availWidth+', height='+screen.availHeight);return false;");
 }
 
 function Continuar(){
	 //para actualizar el boton agregaradjunto
	 
	 $(document).ready(function(){
		 $("#cuadro-docs-adjuntos").load("actualizaDocumentoAdjunto.do", {cambia:'actualiza'});
		 
	 });
	 return;
 }
 
function LlamarAppletFormulario(valor){
	var valorinput = valor;
	
    $(document).ready(function(){
    	   $("#complemento").css("visibility","visible");
    	   $("#capaFondo1").css("visibility","visible");
    	   if(valorinput == 0){
	    	   	$("#dialogoaccionCode").hide("fast");
	    	   }else{
	    		   $("#dialogoaccionCode").show("fast");
	    	   }

	});
}
 
function LlamarApplet(valor,valor2,valor3) {
	    
	    var ab = valor;
	    var bc = valor2;
		var cd = valor3;
		
		
	    $(document).ready(function(){
	    	//$("#containerlista").css("visibility","hidden");
			$("#personas").load("obtenerLista.do", {cambia:'setearcero'});
	    	   $("#complemento").css("visibility","visible");
	    	   $("#capaFondo1").css("visibility","visible");
	    	   if(cd == 0){
	    	   	$("#dialogoaccionCode").hide("fast");
	    	   }else{
	    		   $("#dialogoaccionCode").show("fast");
	    	   }
	    	   
			   $("#valor1").val(ab);
			   $("#valor2").val(bc);
			   $("#valor3").val(cd);
		});

		//var strURL = "CopiarPDFATemporal.do?codDoc="+valor+"&codTip="+valor2;
		//referenciaVentanaFirma = window.open(strURL,"Firma Digital","scrollbars=YES,resizable=YES,location=NO,WIDTH=730,HEIGHT=430");
		
}
  
function LlamarListaDocumentos(){
	
    $(document).ready(function(){
    	   $("#containerlista").css("visibility","visible");
    	   $("#capaFondo1").css("visibility","visible");
    	   $("#personas").load("obtenerLista.do", {cambia:'setear'});
	});
    
}

function LimpiarLista(){
	
    $(document).ready(function(){
    	$("#containerlista").css("visibility","hidden");
    	$("#capaFondo1").css("visibility","hidden");
    	$("#personas").load("obtenerLista.do", {cambia:'setearcero'});
	    //$("#accionCode").load("obtenerLista.do", {cambia:'codAccionffff'});
	});
    //window.location.reload();
}

function LimpiarListaProyectos(){
	
    $(document).ready(function(){
    	$("#containerlistaproyectos").css("visibility","hidden");
    	$("#capaFondo1").css("visibility","hidden");
    	$("#personas").load("obtenerLista.do", {cambia:'setearceroproy'});
	});
}

function Cambiacondicion(valentrada){
	var valentradap = valentrada;
	
	if(valentradap==1){
		$("#v-ocultar").hide();
		$("#v-ocultar-2").hide();
		$("#v-ocultar-3").hide();
		$("#v-ocultar-4").hide();
		$("#v-ocultar-5").hide();
		$("#v-ocultar-6").hide();
		$("#v-mostrar").show();
	}else{
		var x = $("#valorchecked").val();
		if(x == 1){
			$("#valorchecked").val(0);
			$("#aviso-checked").slideDown("slow");
			$("#v-ocultar").hide();
			$("#v-ocultar-2").hide();
			$("#v-ocultar-3").hide();
			$("#v-ocultar-4").hide();
			$("#v-ocultar-5").hide();
			$("#v-ocultar-6").hide();
			$("#v-mostrar").show();
			
			
		}else{
			$("#valorchecked").val(1);
			$("#aviso-checked").slideUp("fast");
			$("#v-ocultar").show();
			$("#v-ocultar-2").show();
			$("#v-ocultar-3").show();
			$("#v-ocultar-4").show();
			$("#v-ocultar-5").show();
			$("#v-ocultar-6").show();
			$("#v-mostrar").hide();
		}	
	}
	
	
}


 function HabilitaCombo(){
	   var scroll_chks = document.getElementById('cajoncito1');
	   var scroll_chksz = document.getElementById('cajoncito2');
			with(document.forms[0]){
			   if (checked.checked) {
					scroll_chks.style.visibility = 'visible';
					scroll_chksz.style.visibility = 'visible';
			   }
			   else{			
				scroll_chks.style.visibility = 'hidden'; 
				scroll_chksz.style.visibility = 'hidden';
			   }
			 }
	}
 
function Cambiardocumento(valor){
		//alert("entre"+valor);
		var vvalor=valor;
		
		if (vvalor==2){
			var strURL = "MostrarFormularioCrearcionDocumentoInternoEspecialC.do";
			location=strURL;
			return;
		}
		if (vvalor==3){
			var strURL = "MostrarFormularioCrearcionDocumentoInternoEspecialT.do";
			location=strURL;
			return;
		}
			
}

function MostrarMensaje(mensaje,coDoc){
	//alert("Soy el padre 1: " + mensaje+", Soy el Código: "+ coDoc);
	var mensajez = mensaje;
	if(mensajez == 0){
		var strURL = "UtilidadRegistroMovimiento.do";
		location=strURL;
		$( "#dialog-confirm-envio" ).dialog( "open");
		return;
	}
	
	if(mensajez == 1){
		alert("No se logro firmar los documentos");
	}
	
}

function MensajeRegistro(){
		//var strURL="ValidaPaginas.do?tipo=mensajerecepcion";
		//window.open(strURL,"","HEIGHT=140,WIDTH=500,scrollbars=no");
		$(document).ready(function(){
			$("#mensajegeneral").fadeIn("slow");
		
		});
}

function MensajeFrame(){
	$(document).ready(function(){

		   $("#panel").slideDown("slow");
		  // $("#panel").delay(2000);
		  // $("#panel").slideUp("slow");
		
		});
}
  
 function mostrardiv() {
	 div = document.getElementById('flotante');
	 div.style.display ='';
}

function cerrar() {
	 div = document.getElementById('flotante');
	 div.style.display='none';
}
 
function Regresar(){
		
		var strURL = "MostrarFormularioCrearcionDocumentoInterno.do?inicia=SI&tipo=firma";
		location=strURL;
	
		return;
	}
 
 
 function Regresarbandeja(){
		
		/*	alert(var_apellido_paterno);
			alert(var_apellido_materno);
			alert(var_nombres);
			*/
			var strURL = "MostrarFormularioCrearcionDocumentoInterno.do";
			location=strURL;
		
			return;
		}
 
		
function Regresabandejad(){
				var strURL = "ListaDocumentos.do?tipo=derivar&operacion=D&inicia=SI";
				location=strURL;
				return;
	}

function Regresabandejare(){
		var strURL = "Reiterativo.do?tipo=regresarbr";
		location=strURL;
		return;
}
	
function LlamarPuente(){
		var value1 = document.getElementById('pcodigo_documento').value;
		var value2 = document.getElementById('psecuencia_movimiento').value;
		
		var strURL = "PuenteDocumentoInterno.do?cdi="+value1+"&cei=0&cri=0&sec="+value2;
		location=strURL;
		return;
}
	
 
	
 function Buscar(){
		
		/*	
		onclick="this.setAttribute('ID', 'NuevaID');"
			function divactual(i)
		{
		var actual=getelementByID('div'+i)

		//Y ahora habria que cambiar el nombre 'div'+i a actual
		actual.id = 'otroId';
		}
		document.getElementById("oldID").id = "new ID";
		alert(var_apellido_paterno);
			alert(var_apellido_materno);
			alert(var_nombres);
			
			var var_apellido_materno = document.getElementById('apellido_materno').value;
			var var_apellido_paterno = document.getElementById('apellido_paterno').value;
			var var_nombres = document.getElementById('nombres').value;*/
			
			//var var_codigo_tipo_documento_interno = document.getElementById('codigo_tipo_documento_interno').value;
			//var var_codigo_oficina = document.getElementById('codigo_oficina').value;
			//var var_nombre_archivo = document.getElementById('referencia').value;
			//var var_asunto = document.getElementById('asunto').value;
			
			var var_busqueda = document.getElementById('codigo_documento_interno_busqueda').value;
			
			if (Validarcampovacio(var_busqueda) == false){              
		          alert("Ingresar Codigo de Documento");
		          document.forms[0].codigo_documento_interno_busqueda.focus();
		          return false;           
		    }
			
			
			var strURL = "MostrarFormularioCrearcionDocumentoInterno.do?";
			
			strURL += "tipo=busqueda&";
			//strURL += "tipo="+var_codigo_tipo_documento_interno+"&";
			//strURL += "cod_ofic="+var_codigo_oficina+"&";
			//strURL += "nomdoc="+var_nombre_archivo+"&";
			//strURL += "asnt="+var_asunto;
			strURL += "nrodoc="+var_busqueda;
			location=strURL;
			return;
		}
 
 
</script>



<title><bean:message key="sistema.documento.internos.titulo" /></title>
</head>
<body bgColor="#F0F5FB" onLoad="fn_onLoad();">
	<div id="top-bar">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
	<html:form   enctype="multipart/form-data"  action="CrearDocumentoInterno.do"  >
	<INPUT  type="hidden" id="valor1" name="valor1" value="">
	<INPUT  type="hidden" id="valor2" name="valor2" value=""> 
	<INPUT  type="hidden" id="valor3" name="valor3" value=""> 
	<INPUT  type="hidden" id="pcodigo_documento" name="pcodigo_documento" value="<c:out value='${ls_codigo_documento}'></c:out>"> 
	<INPUT  type="hidden" id="psecuencia_movimiento" name="psecuencia_movimiento" value="<c:out value='${ls_secuencia}'></c:out>">
	<INPUT  type="hidden" id="pcodigodocumentointerno" name="pcodigodocumentointerno" value="<c:out value='${FFormDocumentoInterno.codigo_documento_interno}'></c:out>">
	<INPUT  type="hidden" id="ptipocodigodocumentointerno" name="ptipocodigodocumentointerno" value="<c:out value='${FFormDocumentoInterno.codigo_tipo_documento_interno}'></c:out>">
	<INPUT  type="hidden"" id="contador" value="0">
	<INPUT  type="hidden"" id="valorchecked" value="<c:out value='${valorchecked}'></c:out>">
	<INPUT  type="hidden"" id="ppaginaderivacion" value="<c:out value='${pagina}'></c:out>">
	<INPUT  type="hidden"" id="tipo_proyecto" name="tipo_proyecto" value="<c:out value='${es_proyecto}'></c:out>">
	<INPUT  type="hidden"" id="es_observado" name="es_observado" value="<c:out value='${es_observado}'></c:out>">
	
	<div id="dialog-confirm-envio" title="Mensaje de Espera" >
  		<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0; font-size : 8px; "></span></p>
  		Espere unos instantes.
  		<br><br><br>
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="img/loading11.gif" border="0" align="top">
		</div>
	<div id="dialog-confirm" title="Mensaje de Alerta" >
  		<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0; font-size : 8px; "></span></p>
  		El documento será eliminado de manera permanente.
	</div>
	<div id="dialog-confirminicia" title="Mensaje de Alerta" >
  		<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0; font-size : 8px; "></span></p>
  		El documento será eliminado de manera permanente.
	</div>
	<div id="complemento" class="rounded">
	<div id="Theadsmall"><div style="padding:7px">Información Adicional</div></div>
		
    	<div id="dialogoaccionCode" style="margin-left:20px; margin-top:20px;">
    	<p class="label" >Accion a realizar:</p>
    	<select id="accionCode" name="accionCode" >
    	
    	</select>
    	
    	</div>
    	
    	<div style="margin-left:20px; margin-top:20px; ">
    	<p class="label">Observacion:</p>
    	<textarea rows="4" cols="40" id="observacion" ><c:out value='${ls_observacion}'/></textarea>
    	<br></div>
    	<div style="margin-left:25%;">
    	<input type="button" id="enviar-complemento" class="button" value="Enviar">
    	<input type="button" id="cerrar-complemento" class="button" value="Cancelar"></div>
	</div>
	<div id="capaFondo1"></div>
	
	<c:choose>
	   <c:when test='${operacionpopup == "X"}'>
                <SCRIPT language="javascript">
				 MensajeRegistro();
				</SCRIPT>
				<c:remove var="operacionpopup" scope="session" />
       </c:when>
	</c:choose> 
	<c:choose>
	   <c:when test='${cuadro_mensaje ==  "M"}'>
                <SCRIPT language="javascript">
				 MensajeFrame();
				</SCRIPT>
				<c:remove var="cuadro_mensaje" scope="session" />
       </c:when>
	</c:choose> 
	
		
	<div class="container">
	<div id="panel" >
	<div id="vvvv" style=" position: absolute;   top:18px; left:20%; ">
		<a  href="#"><img src="img/ok.png"  /></a>
	</div>
	<div id="cerrar-panel" style="cursor:pointer; position: absolute;  width:10%; height:40px; top:15px; left:90%; text-align:left;">  </div>
		<br>
		<html:messages  message="true" id="mensajes" header="messages.header" footer="messages.footer">
			<%=pageContext.getAttribute("mensajes") %>
		</html:messages>
        <logic:present name="docInterno" >
          <br>
           <a  href='VerArchivoPDFDocumentoInterno.do?filename=<bean:write name="docInterno" property="nombre_arhivo"/>&codigo_estado=<bean:write name="docInterno" property="codigo_estado_documento"/>&codigo_oficina_pertenece=<bean:write name="docInterno" property="codigo_oficina_pertenece"/>'><bean:write name="docInterno" property="nombre_arhivo"/></a>
		</logic:present>
		<br>
		&nbsp;&nbsp;
	</div>
	<div id="aviso-checked" >
	<img src="img/alerticon1.png"  style=" vertical-align: middle; width:36px; height:36px;  border:0px; ">&nbsp;&nbsp; Se generará un Proyecto de Documento
	<div id="cerrar-aviso-checked" style="cursor:pointer; position: absolute;  width:10%; height:40px; top:15px; left:90%; text-align:left;">  </div>	
	</div>
	<div id="mensajegeneral" class="rounded">
	<div id="vvvv" style=" position: absolute;  top:5px; left:20%; ">
		<a  href="#"><img src="img/signatureok.png"  /></a>
	</div>
	<div id="cerrar-mensajegeneral" style="cursor:pointer; position: absolute;  width:10%; height:40px; top:15px; left:90%; text-align:left;">  </div>
		   <br>
	       <c:out value='${mensaje}'/><br>
	       <a  href="VerArchivoPDFDocumentoInterno.do?filename=<c:out value='${nombreArchivoVer_}'/>&codigo_estado=<c:out value='${tipoDocumentoVer_}'/>&codigo_oficina_pertenece=<c:out value='${codigoOficinaVer_}'/>"><c:out value='${nombreArchivoVer_}'/></a><br>
	       <c:out value='${mensaje_reg}'></c:out><br>
	       <c:out value='${mensaje_exp}'></c:out><br>
	       
	</div>
	<div id="break" ></div>
	
	<div id="contactform" class="rounded">
	<div id="TDHeadCab">
	 
	 <c:choose>
				   		<c:when test='${es_admiitido==0}'>
				   		   	<span class="labelrounded">CREAR DOCUMENTO INTERNO </span>
				   		   	
				   		   	<c:choose>
				   		   	<c:when test='${ls_respuesta == "respuesta" && FFormDocumentoInterno.codigo_documento eq 0 }'>
				   		   	<c:choose>
				   		   	<c:when test='${FFormDocumentoInterno.tipo_envio =="R"}'>
					          <div id="cradio1" style="width:100px; position: absolute; top:7px; FONT-SIZE: 8pt;  font-weight:bold; left:75%;">
					          			  <a  href="javascript:Regresabandejare()" title="Regresar a la Bandeja de Reiterativo"><img src="img/01regresar.png"  width="16" height="16" border="0" align="top"> Regresar</a>
							  </div>
				   		   	</c:when>
				   		   	<c:otherwise>
				   		   	<div id="cradio5" style="width:100px; position: absolute; top:7px; FONT-SIZE: 8pt;  font-weight:bold; left:75%;">
					          			  <a  href="javascript:Regresabandejad()" title="Regresar a la Bandeja de Derivación"><img src="img/01regresar.png"  width="16" height="16" border="0" align="top"> Regresar</a>
							  </div>
				   		   	</c:otherwise>
				   		   	</c:choose>
				   		   	
				   		   	<div id="cuadrocodigo">
				   		   		<c:out value='${ls_codigo_documento}'></c:out>
				   		   	</div>
				   		   	</c:when>
				   		   	<c:when test='${FFormDocumentoInterno.codigo_documento > 0 }'>
				   		   	<c:choose>
				   		   	<c:when test='${FFormDocumentoInterno.tipo_envio =="R"}'>
				   		   	<c:choose>
				   		   		<c:when test='${verbotonesbandeja =="1"}'>
				   		   	  	<div id="cradio2" style="width:200px; position: absolute;  top:7px; FONT-SIZE: 8pt;  font-weight: bold; left:65%;">
					          			  <a  href="javascript:Regresabandejare()" title="Regresar a la Bandeja de Reiterativo" ><img src="img/01regresar.png"  width="16" height="16" border="0" align="top"> Regresar</a>
    									  <a  href="javascript:AgregarDocInternos()" title="Eliminar Documento" ><img src="img/02eliminar.png"  width="16" height="16" border="0" align="top"> Eliminar</a>
							  	</div>
							  	</c:when>
							  	<c:otherwise>
							  	<div id="cradio2" style="width:200px; position: absolute;  top:7px; FONT-SIZE: 8pt;  font-weight: bold; left:65%;">
					          			  <a  href="javascript:Regresarbandeja()" title="Regresar a Bandeja Documentos" ><img src="img/01regresar.png"  width="16" height="16" border="0" align="top"> Regresar</a>
    									  <a  href="javascript:deletedocument(<c:out value='${FFormDocumentoInterno.codigo_documento_interno}' />)"  title="Eliminar Documento" ><img src="img/02eliminar.png"  width="16" height="16" border="0" align="top"> Eliminar</a>
							  	</div>
							  	</c:otherwise>
							</c:choose>
				   		   	</c:when>
				   		   	<c:otherwise>
				   		   		<c:choose>
				   		   		<c:when test='${verbotonesbandeja =="1"}'>
				   		   		<div id="cradio6" style="width:200px; position: absolute;  top:7px; FONT-SIZE: 8pt;  font-weight: bold; left:65%;">
					          			  <a  href="javascript:Regresabandejad()" title="Regresar a la Bandeja de Derivación" ><img src="img/01regresar.png"  width="16" height="16" border="0" align="top"> Regresar</a>
    									  <a  href="javascript:AgregarDocInternos()" title="Eliminar Documento"><img src="img/02eliminar.png" alt="Anular Documentos" width="16" height="16" border="0" align="top"> Eliminar</a>
							  	</div>
				   		   		
				   		   		</c:when>
				   		   		<c:when test='${verbotonescreacion =="1"}'>
				   		   		<div  id="cradio3" style="width:100px; position: absolute; top:7px; FONT-SIZE: 8pt;  font-weight:bold; left:75%;">
					          			  <a  href="javascript:Regresarbandeja();" title="Regresar a Bandeja Documentos" ><img src="img/01regresar.png"  width="16" height="16" border="0" align="top"> Regresar</a>
    									  
							  	</div>
				   		   		</c:when>
				   		   		<c:otherwise>
				   		   		<div id="cradio6" style="width:200px; position: absolute;  top:7px; FONT-SIZE: 8pt;  font-weight: bold; left:65%;">
					          			  <a  href="javascript:Regresarbandeja();" title="Regresar a Bandeja Documentos" ><img src="img/01regresar.png"  width="16" height="16" border="0" align="top"> Regresar</a>
    									  <a  href="javascript:deletedocument(<c:out value='${FFormDocumentoInterno.codigo_documento_interno}' />)" title="Eliminar Documento"><img src="img/02eliminar.png" alt="Anular Documentos" width="16" height="16" border="0" align="top"> Eliminar</a>
							  </div>
				   		   		
				   		   		</c:otherwise>
				   		   		</c:choose>
				   		   	
				   		   	</c:otherwise>
				   		   	</c:choose>
				   		   	
				   		   	<div id="cuadrocodigo">
				   		   		<c:out value='${FFormDocumentoInterno.codigo_documento}'></c:out>
				   		   	</div>
				   		   	</c:when>
				   		   	<c:otherwise>
				   		   	<c:choose>
				   		   	<c:when test='${verbotonescreacion == "1" }'>
				   		   		<div  id="cradio3" style="width:100px; position: absolute; top:7px; FONT-SIZE: 8pt;  font-weight:bold; left:85%;">
				   		   			<a   href="javascript:Regresarbandeja();"  title="Regresar a Bandeja Documentos" ><img src="img/01regresar.png"  width="16" height="16" border="0" align="top"> Regresar</a>
				   		   		</div>
				   		   	</c:when>
				   		   	<c:otherwise>
				   		   			  <div  id ="botoninvisibleProyectos" title="Ver Lista de Proyectos" style="width:95px; height:20px; position: absolute; top: 6px; padding: 3px 10px; text-align: center;  FONT-SIZE: 8pt; font-weight:normal; left:58%;  color: #003366;  line-height: 20px;" >&nbsp;<img  src="img/proyectos-img.png"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp; Proyectos</div>
				   		   			  <div  id ="botoninvisible" title="Ver Lista de Documentos" style="width:95px; height:20px; position: absolute; top: 6px; padding: 3px 10px; text-align: center;  FONT-SIZE: 8pt; font-weight:normal; left:72%;  color: #003366;  line-height: 20px;" >&nbsp;<img  src="img/08search.png"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp; Documentos</div>
				   		   	          <div  id ="botoninvisibleAnular" title="Anular Documento" style="width:70px; height:20px; position: absolute; top: 6px; padding: 3px 10px; text-align: center;  FONT-SIZE: 8pt; font-weight:normal;  left:86%; color: #003366;  line-height: 20px;" >&nbsp;<img  src="img/02eliminar.png"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp; Anular</div>
				   		   	</c:otherwise>
				   		   	</c:choose>
							
				   		   	</c:otherwise>
				   		   	
				   		   	</c:choose>
				   		   	
				   		</c:when>
				   		<c:otherwise>
				    		<span class="labelrounded">DOCUMENTO POR FIRMAR</span> 
				   		   	<c:choose>
				   		   	<c:when test='${FFormDocumentoInterno.codigo_documento > 0}'>
				   		   	<c:choose>
				   		   	<c:when test='${FFormDocumentoInterno.tipo_envio =="R"}'>
				   		   	<c:choose>
				   		   		<c:when test='${verbotonesbandeja =="1"}'>
				   		   			<div id="cradio4" style="width:350px; position: absolute;  top:7px; FONT-SIZE: 7pt;  font-weight: bold; left:56%;">
    									<a  href="javascript:Regresabandejare()" title="Regresar a la Bandeja de Reiterativo" ><img src="img/01regresar.png"  width="16" height="16" border="0" align="top"> Regresar</a>
    									<a  href="javascript:deletedocumentinicia(<c:out value='${FFormDocumentoInterno.codigo_documento_interno}' />)" title="Eliminar Documento"><img src="img/02eliminar.png"  width="16" height="16" border="0" align="top"> Eliminar</a>
    									<a  href="javascript:LlamarAppletFormulario(1)" title="Firmar Documento"><img src="img/03firmar.png"  width="16" height="16" border="0" align="top"> Firmar</a>
    
 						    		</div>
 						    	</c:when>
 						    	<c:otherwise>
 						    		<div id="cradio4" style="width:350px; position: absolute;  top:7px; FONT-SIZE: 7pt;  font-weight: bold; left:56%;">
    									<a  href="javascript:Regresar()" title="Regresar a la Bandeja de Documentos" ><img src="img/01regresar.png"  width="16" height="16" border="0" align="top"> Regresar</a>
    									<a  href="javascript:AgregarDocInternos()" title="Eliminar Documento"><img src="img/02eliminar.png"  width="16" height="16" border="0" align="top"> Eliminar</a>
    									<a  href="javascript:LlamarAppletFormulario(1)" title="Firmar Documento"><img src="img/03firmar.png"  width="16" height="16" border="0" align="top"> Firmar</a>
    
 						    		</div>
 						    	</c:otherwise>
 						    </c:choose>
				   		   	</c:when>
				   		   	<c:otherwise>
				   		   		<c:choose>
				   		   		<c:when test='${verbotonesbandeja =="1"}'>
				   		   			<div id="cradio7" style="width:350px; position: absolute;  top:7px; FONT-SIZE: 7pt;  font-weight: bold; left:56%;">
    									<a  href="javascript:Regresabandejad()" title="Regresar a la Bandeja de Derivación" ><img src="img/01regresar.png" width="16" height="16" border="0" align="top"> Regresar</a>
    									<a  href="javascript:deletedocumentinicia(<c:out value='${FFormDocumentoInterno.codigo_documento_interno}' />)" title="Eliminar Documento"><img src="img/02eliminar.png"  width="16" height="16" border="0" align="top"> Eliminar</a>
    									<a  href="javascript:LlamarAppletFormulario(1)" title="Firmar Documento"><img src="img/03firmar.png"  width="16" height="16" border="0" align="top"> Firmar</a>
    
 						    		</div>
 						    	</c:when>
 						    	<c:otherwise>
 						    		<div id="cradio7" style="width:350px; position: absolute;  top:7px; FONT-SIZE: 7pt;  font-weight: bold; left:56%;">
    									<a  href="javascript:Regresar()" title="Regresar a la Bandeja de Documentos" ><img src="img/01regresar.png" width="16" height="16" border="0" align="top"> Regresar</a>
    									<a  href="javascript:AgregarDocInternos()" title="Eliminar Documento"><img src="img/02eliminar.png"  width="16" height="16" border="0" align="top"> Eliminar</a>
    									<a  href="javascript:LlamarAppletFormulario(1)" title="Firmar Documento"><img src="img/03firmar.png"  width="16" height="16" border="0" align="top"> Firmar</a>
 						    		</div>
 						    	</c:otherwise>
 						        </c:choose>
				   		   	</c:otherwise>
				   		   	</c:choose>
				   		   	
				   		   	<div id="cuadrocodigo">
				   		   		<c:out value='${FFormDocumentoInterno.codigo_documento}'></c:out>
				   		   	</div>
				   		   	</c:when>
				   		   	<c:otherwise>
				   		   	<c:choose>
				   		   	<c:when test='${FFormDocumentoInterno.tipo_envio =="R" || FFormDocumentoInterno.tipo_envio =="D"}'>
				   		   		<div  id="cradio9" style="width:90px; position: absolute; top:5px; FONT-SIZE: 8pt;  font-weight:bold; left:88%;">
				   		   		<a   href="javascript:LlamarAppletFormulario(1)"  title="Firmar Documento" ><img src="img/03firmar.png"  width="16" height="16" border="0" align="top"> Firmar</a>
				   		   	</div>
				   		   	</c:when>
				   		   	<c:otherwise>
				   		   		<div id="cradio6" style="width:200px; position: absolute;  top:7px; FONT-SIZE: 8pt;  font-weight: bold; left:75%;">
				   		   			<a  href="javascript:Regresar()" title="Regresar a la Bandeja de Documentos" ><img src="img/01regresar.png" width="16" height="16" border="0" align="top"> Regresar</a>
				   		   			<a   href="javascript:LlamarAppletFormulario(0)"  title="Firmar Documento" ><img src="img/03firmar.png"  width="16" height="16" border="0" align="top"> Firmar</a>
				   		   	</div>
				   		   	</c:otherwise>
				   		   	</c:choose>
				   		   	
				   		   	</c:otherwise>
				   		   	</c:choose>

				   		</c:otherwise>
	</c:choose>
	</div>
	<c:choose>
	<c:when test='${es_proyecto==1}'>
	</c:when>
	<c:otherwise>
	<div id="TDHeadCabRadios" >
     <c:if test='${es_admiitido==0 && FFormDocumentoInterno.codigo_documento eq 0 && FFormDocumentoInterno.codigo_documento_interno eq null}'>
    <a href="#" ><html:radio property="radiobuttons" value="1" ><bean:message key="sistema.documento.internos.normales" /></html:radio></a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:Cambiardocumento(2)" ><html:radio property="radiobuttons" value="2" onclick="Cambiardocumento(2)"><bean:message key="sistema.documento.internos.ccp" /></html:radio></a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:Cambiardocumento(3)" ><html:radio property="radiobuttons" value="3" onclick="Cambiardocumento(3)"><bean:message key="sistema.documento.internos.tdr" /></html:radio></a>
	</c:if>
     </div>
	</c:otherwise>
	</c:choose>
	
     <div id="Lineaform" >
     </div>
     
     
     
     
     
	<table width="100%" cellspacing="0">
			 <tr>
				   <td  style="padding:0px; margin:0px;" align="left" valign="middle" >
				   		<table  width="100%" >
				   		<tr><td colspan="2" height="15px" >
				   			</td>
				   		</tr>
				   		<tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.registrar" />  
				  			</td>
				  			<td  align="left" valign="middle" >
				  			<div id="no-muestra1" style="display:visibility;">
				  			<div id="cuadrodelcheck-show" style="display:visibility;">
				  			<html:checkbox property="checked" styleId="checked" onclick="Cambiacondicion(0)"  ></html:checkbox>
				  			</div>
				  			</div>
				  			<div id="cuadrodelcheck-hide" style="display:none;">
				  			<input type="checkbox" name="checkboxdeactivado" value="Car" checked="checked" disabled="disabled"> 
				  			</div>
				  			
				  			</td>
				   		
				   		</tr>
				   		<tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.tipo.documento" />  
				  			</td>
				  			<td  align="left" valign="middle" >
				  			<div id="no-muestra2" style="display:visibility;">
				    			<html:select property="codigo_tipo_documento_interno"  styleId="codigo_tipo_documento_interno"  >			             
					             <html:option value="0" >Seleccionar</html:option>
									<html:options collection="tipoDocumentoInternosListTemp"  property="idBean"
 									labelProperty="nombreBean"  /> 
								</html:select> <span class="error" ><html:errors property="codigo_tipo_documento_interno"/></span>
							</div>
				  			</td>
				   		</tr>

				   		<tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.area.administrativa.destino" />
				  			</td>
				  			<td  align="left" valign="middle" >
				  			<div id="no-muestra3" style="display:visibility;">
				    			<html:select property="codigo_oficina" styleId="codigo_oficina"  onchange="showProducts()">			             
					             <html:option value="0" >Seleccionar</html:option>
									<html:options collection="areasAdministrativasList"  property="codigo_oficina"
 									labelProperty="descripcion_oficina"  /> 
								</html:select> 
								<span class="error"><br><html:errors property="codigo_oficina"/></span>
							</div>
				  			</td>
				   		
				   		</tr>
				   		
				   		<tr>
				   		
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.abreviatura" />
				  			</td>
				  			<td  align="left" valign="middle" >
				  			<div class="ui-widget">
				    			<html:text property="abreviatura_grado_profesion"  size="10" styleId="abreviatura_grado_profesion"  onkeypress="return validatecla('texto',this)" />
				    			<span class="error"><br><html:errors property="abreviatura_grado_profesion"/></span>
				    		</div>	
				    			
				  			</td>
				   		</tr>
				   		<tr>
				   		
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.destinatario" />
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<c:choose>
									<c:when test='${FFormDocumentoInterno.codigo_documento_interno eq null }'>
									  
										<html:select property="personas" styleId="personas" styleClass="input-medium"  >			             
							             <html:option value="0" >Seleccionar</html:option>	
										</html:select> 
									</c:when>				  
							 		<c:otherwise>
				          	             <html:select property="personas" styleId="personas" styleClass="input-medium"  >			             
								             <html:option value="0" >Seleccionar</html:option>	
								             <html:options collection="rs_destinatario"  property="codigo_personal"
	 										 labelProperty="nombre_personal"  /> 
										</html:select>   
			        		      </c:otherwise>

     						  </c:choose> 
				   
								<span class="error"><br><html:errors property="personas"/></span>
				  			</td>
				   		</tr>
				   		
				   		<tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.asunto" />
				  			</td>
				  			<td  align="left" valign="middle" >
				    			 <html:textarea property="asunto" styleId="asunto" title="Ingresar Asunto" /> 
								 <span class="error"><br><html:errors property="asunto"/></span>
				  			</td>
				   		
				   		</tr>
				   		
				   		<tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.referencia" />
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<html:textarea property="referencia" styleId="referencia" /> 
				  			</td>
				   		
				   		</tr>
				   		
				   		<tr>
				   			<td  align="right" valign="top" class="label" >
				    			<bean:message key="sistema.documento.internos.descripcion"  />
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<html:textarea property="descripcion"  styleId="descripcion" /> 								
								<span class="error"><br><html:errors property="descripcion"/></span>
				  			</td>
				   		
				   		</tr>

				   		<!--  tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.adjuntos"  />
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<html:button  value="Agregar documento" style="width: 90;" property="" styleClass="boton" onclick="javascript:AgregarDocInternos();"/>
				    			<c:choose>
				  				<c:when test='${not empty rs_upload_doc_internos}'>
				    			<img src="img/docadjuntos.gif" alt="Documentos Adjuntos"></c:when>
			   	 				</c:choose>	
				  			</td>
				   			
				   		</tr-->
				   		<tr>
				   		<td colspan="2">
				   		<table width="100%" border="0">
				   		<tr>
				   		<td align="right" valign="middle" class="label" width="33%"><bean:message key="sistema.documento.internos.autor" /></td>
				   		<td align="left" valign="middle" width="20%">
				   		<div id="no-muestra4" style="display:visibility;">
				   		<html:text property="firmado_por" size="37"  styleId="firmado_por" onkeypress="return validatecla('letras',this)" styleClass="input"/>
								 <span class="error"><br><html:errors property="firmado_por"/></span>
						</div> </td>
						<td align="right" valign="top"  class="label" width="15%">
						
						<div id="v-ocultar-5" style="display:visibility;" >
						<div id="clickme" >
				    			<bean:message key="sistema.documento.internos.copias_destinatario" />
				    	</div>
				    	</div>
				    	
				    	</td>
				    	<td  width="32%">
				    	</td>
				   		</tr>
				   		<tr>
				   		<td  align="right" valign="middle" class="label" width="33%">
				   		<div id="v-ocultar-2" style="display:visibility;" >
				   		<bean:message key="sistema.documento.internos.adjuntos"  />
				   		</div>
				   		</td>
				   		<td width="20%">
				   		<div id="no-muestra5" style="display:visibility;">
				   		<div id="v-ocultar-3" style="display:visibility; float:left;" >
				   		<a  id="agregarAdjunto" href="javascript:AgregarDocInternos()" title="Adjuntar Documentos"><img src="img/04adjuntar.png"  width="16" height="16" border="0" align="top"> Adjuntar</a>
				   		</div>
				   		<div id="v-ocultar-6" style="display:visibility; float:left;" >
				   		<div id="cuadro-docs-adjuntos" style="display:visibility; float:left; "></div>
				   		</div>
				   		</div>
				   		</td>
				   		<td align="left" rowspan="3" colspan="2">
				   		<div id="no-muestra6" style="display:visibility;">
				   		<div id="v-ocultar-4" style="display:visibility;" >
				   		<div id="concopia" style=" display:visibility; background: white;  width:90%; height:80px; overflow: scroll; line-height: 100%; FONT-SIZE: 8pt; padding-left:5px;" >	
										<logic:iterate id="oficinas_des" name="areasAdministrativasList" >
										   <html:multibox property="copia_oficinas_destino" >	
										    <bean:write name="oficinas_des" property="codigo_oficina"/> 
										   </html:multibox> 
										   <label class="caja7"><bean:write name="oficinas_des" property="descripcion_oficina"  /></label>
										   <br>
										</logic:iterate>
						</div>
				   		</div>
				   		</div>
				   		</td>
				   		</tr>
				   		<tr>
				   		<td align="right" valign="middle" class="label" width="33%"><bean:message key="sistema.documento.internos.estado" /></td>
				   		<td align="left" valign="middle" width="20%" >
				   		<div id="v-ocultar" style="display:visibility;" >
				   		<html:select property="codigo_estado_documento" styleId="codigo_estado_documento" styleClass="caja">			             
					            <html:option value="0" >Seleccionar</html:option>
									<html:options collection="estadoDocumentoInternoListSecretaria"  property="idBean"
 									labelProperty="nombreBean"  /> 
						</html:select> 
								<span class="error"><br><html:errors property="codigo_estado_documento"/></span>
						</div>
						<div id="v-mostrar" style="display:none;">
							<input type="text" value="BORRADOR" id="texto" disabled="disabled">					
						</div>
						</td>
				   		</tr>
				   		<tr>
				   		<td  align="right" valign="middle" class="label" width="33%"></td><td width="20%"></td>
				   		</tr>
				   		</table>
				   		</td>
				   		</tr>
				   		<!-- tr>
							   			<td  align="right" valign="top" class="label" >
							    			<bean:message key="sistema.documento.internos.buscar.firma" />
							  			</td>
							  			<td  align="left" valign="middle" >
									    			 <html:file property="file" size="60" />
									    			 <span class="error"><br><html:errors property="file"/></span>
										 </td>
							   		
						</tr-->
						 <tr><td colspan="2" height="15px" >
				   			</td>
				   		</tr>
				   		<tr>
				   			<td  align="center" valign="top" class="label" colspan="2" >
				   			
				    				 <c:choose>
									 <c:when test='${FFormDocumentoInterno.codigo_documento_interno eq null }'>
									      <c:choose>
									      <c:when test='${es_admiitido==0 && ls_respuesta != "respuesta"}'>
									      <html:button  value="Guardar" style="width: 90;" property="" styleClass="button" onclick="this.disabled=true; this.value='GUARDANDO...'; this.form.submit()" />
					          			  <html:reset value="Cancelar" style="width: 90;" styleClass="button" />
									      
									      </c:when>
									      <c:when test='${ls_respuesta == "respuesta"}'>
									      <html:button  value="Guardar" style="width: 90;" property="" styleClass="button" onclick="this.disabled=true; this.value='GUARDANDO...'; this.form.submit()" />
					          			  <html:reset value="Cancelar" style="width: 90;" styleClass="button" />
									      </c:when>
									      </c:choose>

					         		 </c:when>
					         		 <c:when test='${verbotonescreacion == "1" && es_admiitido==0}'>
					         		 	<c:choose>
					         		 	<c:when test='${es_proyecto == "1"  ||  es_proyecto == "2"}'>
					         		 	<html:button  value="Guardar" style="width: 90;" property="" styleClass="button" onclick="this.disabled=true; this.value='GUARDANDO...'; this.form.submit()" />
					         		 	<html:reset value="Cancelar" style="width: 90;" styleClass="button" />
					         		 	</c:when>
					         		 	<c:otherwise>
					         		 	<html:button  value="Actualizar" style="width: 90;" property="" styleClass="button" onclick="this.disabled=true; this.value='ACTUALIZANDO...'; this.form.submit()" />
					         		 	<!-- html:button  value="Cancelar" style="width: 90;" property="" styleClass="button" onclick="javascript:Regresarbandeja();"/-->
					         		 	<html:reset value="Cancelar" style="width: 90;" styleClass="button" />
					         		 	</c:otherwise>
					         		 	</c:choose>
					         		 	
					         		 </c:when>				  
									 <c:otherwise>
									 	<c:choose>
									 	<c:when test='${ls_respuesta == "estatico"}'>
									 	<html:button  value="Actualizar" style="width: 90;" property="" styleClass="button" onclick="this.disabled=true; this.value='ACTUALIZANDO...'; this.form.submit()" />
									 	<html:reset value="Cancelar" style="width: 90;" styleClass="button" />
									 	</c:when>
									 	<c:when test='${es_proyecto == "1"}'>
									 	<html:button  value="Guardar" style="width: 90;" property="" styleClass="button" onclick="this.disabled=true; this.value='GUARDANDO...'; this.form.submit()" />
									 	<html:reset value="Cancelar" style="width: 90;" styleClass="button" />
									 	</c:when>
									 	<c:otherwise>
									 	<html:button  value="Actualizar" style="width: 90;" property="" styleClass="button" onclick="this.disabled=true; this.value='ACTUALIZANDO...'; this.form.submit()" />
					                    <html:reset value="Cancelar" style="width: 90;" styleClass="button" />
									 	</c:otherwise>
									 	
									 	</c:choose>
									 	
					        		 </c:otherwise>

     								 </c:choose>
				  			</td>

				   		</tr>
						<tr><td colspan="2" height="15px" >
				   			</td>
				   		</tr>
				   		</table>
				    			  
				  </td>

			 </tr>
			
	</table>
	</div>
	</div>
	<div id="break" ></div>
	<c:choose>
	<c:when test='${ls_respuesta == "respuesta" || ls_respuesta == "estatico"}'>
	
	</c:when>
	<c:otherwise>
	<div id="containerlista">
	<div id="TDHeadCabBusqueda" style="position:relative; z-index:4; display:none;">
	 <div style="width: 25%; height: 25px; color: #336699; FONT-SIZE: 8pt; font-weight: bold;	margin-left: auto; margin-right: auto; margin-top: 16px; text-align: right; float:left;">
     <p>Búsqueda por : &nbsp;&nbsp;&nbsp;&nbsp;</p>
     </div>
     <div style="width:25%; margin-left: auto; margin-right: auto; margin-top: 15px; float:left;">
     	<html:text property="codigo_documento_interno_busqueda" styleId="codigo_documento_interno_busqueda" onkeypress="return validatecla('enteros',this)" style="width: 150px;" styleClass="caja"/>
      </div>
     <div style="width:50%; float:left;">
     	<input type="button" name="button" class="button" value="Buscar" onClick="Buscar()" alt="busqueda"  >
        <input type="button" name="button" class="button" value="Listar Todos" onClick="Regresar()" alt="Listar Todos"  >
     </div>	
     </div>
     
    <c:choose>
	<c:when test='${verListaDocumentosInternosBusqueda == "1"}'>
	<div id="tabsbusqueda">
  	<ul>
    <li><a href="#tabsbusqueda-1">Documentos Internos Encontrados</a></li>
    </ul>
  	<div id="tabsbusqueda-1">
  	<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td height="35" align="center" colspan="2" valign="top">
							<table width="100%"  cellpadding="0" cellspacing="0" background="img/fondoplomo8.jpg">
							  <!--DWLayoutTable-->
							<tr>
							<td width="33%" height="30" valign="top"  class="textomensaje10bold">				</td>
						    <td width="33%" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label"><bean:message key="sistema.documento.internos.creados" /></td>
						    <td width="33%" >
							</td>
						  </tr>
							</table>							</td>
						  </tr>
			 
			 <tr>
				  <td align="center" colspan="2">				  
				  <display:table name="sessionScope.listaDocumentosInternosBusqueda" requestURI="/MostrarFormularioCrearcionDocumentoInterno.do?inicia=SI#tabsbusqueda-1"
				  export="false" sort="list" id="b" 
				  pagesize="8"  class="simple" style="width: 90%" >
				  						
				  						<display:column title="Nro Documento" property="codigo_documento_interno" sort="true" align="center" />
											
				  						<display:column property="asunto" title="Asunto" sort="true" width="350"/>								
					  						
				  						<display:column  title="Oficina Destino" sort="true">
				  						
					  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
						  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
						  								<c:out value="${oficina.descripcion_oficina}"/>
						  							</c:if>
						  						</logic:iterate>
				  						</display:column>
				  						<display:column property="dirigido_a" title="Destinatario" sort="true" style="FONT-SIZE: 8pt;"/>
				  						
				  						<display:column   title="Tipo de Documento" >
					  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
					  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						
				  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />
				  						

				  						<display:column   title="Estado" >
					  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
					  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  										  						
				  						<display:column  sort="true" title="Fecha de creacion" >
				  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
				  						</display:column>
				  						
				  						<display:column property="link"  align="center" title="***Operaciones***" />
				  							
		
				  </display:table>
				 
				  </td>
				  
			 </tr>
			 <tr>
			 <td align="center" valign="middle" ><br><a href="javascript:Regresar();" > [ Regresar ]</a></td>
			 </tr>
		</table>
  	</div>
  	</div>
	</c:when>
	<c:otherwise>
	<div id="tabs">
  <ul>
    <li><a href="#tabs-1">Documentos Nuevos</a></li>
    <li><a href="#tabs-2">Documentos para Derivar</a></li>
    <li ><a href="#tabs-3">Documentos para Reiterar</a></li>
  </ul>
  <div id="tabs-1">
  <table width="100%">
			 <tr>
							<td height="35" align="center" colspan="2" valign="top">
							<table width="100%"  cellpadding="0" cellspacing="0" background="img/fondoplomo8.jpg">
							  <!--DWLayoutTable-->
							<tr>
							<td width="33%" height="30" valign="top"  class="textomensaje10bold">				</td>
						    <td width="33%" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label"><bean:message key="sistema.documento.internos.creados" /></td>
						    <td width="33%" >
							</td>
						  </tr>
							</table>							</td>
			</tr>
			 
			 <tr>
				  <td align="center" colspan="2">				  
				  <display:table name="sessionScope.listaDocumentosInternosN"  requestURI="/MostrarFormularioCrearcionDocumentoInterno.do?inicia=SI#tabs-1"
				  export="false" sort="list" id="b" 
				  pagesize="8"  class="simple" style="width: 90%" >
				  						
				  						<display:column title="Nro Documento" property="codigo_documento_interno" sort="true" align="center" />
											
				  						<display:column property="asunto" title="Asunto" sort="true" width="350"/>								
					  						
				  						<display:column  title="Oficina Destino" sort="true">
				  						
					  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
						  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
						  								<c:out value="${oficina.descripcion_oficina}"/>
						  							</c:if>
						  						</logic:iterate>
				  						</display:column>
				  						<display:column property="dirigido_a" title="Destinatario" sort="true" style="FONT-SIZE: 8pt;"/>
				  						
				  						<display:column   title="Tipo de Documento" >
					  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
					  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						
				  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />
				  						

				  						<display:column   title="Estado" >
					  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
					  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  										  						
				  						<display:column  sort="true" title="Fecha de creacion" >
				  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
				  						</display:column>
				  						
				  						<display:column property="link"  align="center" title="***Operaciones***" />
				  							
		
				  </display:table>
				 
				  </td>
				  
			 </tr>
			 <tr>
			 <td align="center" valign="middle" ><br><a href="javascript:LimpiarLista();" > [ Regresar ]</a></td>
			 </tr>
			 
	</table>
  </div>
  <div id="tabs-2">
  <table width="100%">
			 <tr>
							<td height="35" align="center" colspan="2" valign="top">
							<table width="100%"  cellpadding="0" cellspacing="0" background="img/fondoplomo8.jpg">
							  <!--DWLayoutTable-->
							<tr>
							<td width="33%" height="30" valign="top"  class="textomensaje10bold">				</td>
						    <td width="33%" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label"><bean:message key="sistema.documento.internos.creados" /></td>
						    <td width="33%" >
							</td>
						  </tr>
							</table>							</td>
			</tr>
			 
			 <tr>
				  <td align="center" colspan="2">				  
				  <display:table name="sessionScope.listaDocumentosInternosD" requestURI="/MostrarFormularioCrearcionDocumentoInterno.do?inicia=SI#tabs-2"
				  export="false" sort="list" id="b" 
				  pagesize="8"  class="simple" style="width: 90%" >
				  						
				  						<display:column title="Nro Documento" property="codigo_documento_interno" sort="true" align="center" />
											
				  						<display:column property="asunto" title="Asunto" sort="true" width="350"/>								
					  						
				  						<display:column  title="Oficina Destino" sort="true">
				  						
					  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
						  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
						  								<c:out value="${oficina.descripcion_oficina}"/>
						  							</c:if>
						  						</logic:iterate>
				  						</display:column>
				  						<display:column property="dirigido_a" title="Destinatario" sort="true" style="FONT-SIZE: 8pt;"/>
				  						
				  						<display:column   title="Tipo de Documento" >
					  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
					  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						
				  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />
				  						

				  						<display:column   title="Estado" >
					  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
					  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  										  						
				  						<display:column  sort="true" title="Fecha de creacion" >
				  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
				  						</display:column>
				  						
				  						<display:column property="link"  align="center" title="***Operaciones***" />
				  							
		
				  </display:table>
				 
				  </td>
				  
			 </tr>
			 <tr>
			 <td align="center" valign="middle" ><br><a href="javascript:LimpiarLista();" > [ Regresar ]</a></td>
			 </tr>
			 
	</table>
  </div>
  <div id="tabs-3">
  <table width="100%">
			 <tr>
							<td height="35" align="center" colspan="2" valign="top">
							<table width="100%"  cellpadding="0" cellspacing="0" background="img/fondoplomo8.jpg">
							  <!--DWLayoutTable-->
							<tr>
							<td width="33%" height="30" valign="top"  class="textomensaje10bold">				</td>
						    <td width="33%" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label"><bean:message key="sistema.documento.internos.creados" /></td>
						    <td width="33%" >
							</td>
						  </tr>
							</table>							</td>
			</tr>
			 
			 <tr>
				  <td align="center" colspan="2">				  
				  <display:table name="sessionScope.listaDocumentosInternosR" requestURI="/MostrarFormularioCrearcionDocumentoInterno.do?inicia=SI#tabs-3"
				  export="false" sort="list" id="b" 
				  pagesize="8"  class="simple" style="width: 90%" >
				  						
				  						<display:column title="Nro Documento" property="codigo_documento_interno" sort="true" align="center" />
											
				  						<display:column property="asunto" title="Asunto" sort="true" width="350"/>								
					  						
				  						<display:column  title="Oficina Destino" sort="true">
				  						
					  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
						  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
						  								<c:out value="${oficina.descripcion_oficina}"/>
						  							</c:if>
						  						</logic:iterate>
				  						</display:column>
				  						<display:column property="dirigido_a" title="Destinatario" sort="true" style="FONT-SIZE: 8pt;"/>
				  						
				  						<display:column   title="Tipo de Documento" >
					  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
					  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						
				  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />
				  						

				  						<display:column   title="Estado" >
					  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
					  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  										  						
				  						<display:column  sort="true" title="Fecha de creacion" >
				  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
				  						</display:column>
				  						
				  						<display:column property="link"  align="center" title="***Operaciones***" />
				  							
		
				  </display:table>
				 
				  </td>
				  
			 </tr>
			 <tr>
			 <td align="center" valign="middle" ><br><a href="javascript:LimpiarLista();" > [ Regresar ]</a></td>
			 </tr>
			 
	</table>
  </div>
  
  </div>
	</c:otherwise>
    </c:choose> 
	</div>
	
	<div id="containerlistaproyectos">
	<div id="tabsproyectos">
  <ul>
    <li><a href="#tabsproyectos-1">Mis Proyectos</a></li>
    <li><a href="#tabsproyectos-2">Proyectos recibidos</a></li>
  </ul>
  <div id="tabsproyectos-1">
  <table width="100%">
			 <tr>
							<td height="35" align="center" colspan="2" valign="top">
							<table width="100%"  cellpadding="0" cellspacing="0" background="img/fondoplomo8.jpg">
							  <!--DWLayoutTable-->
							<tr>
							<td width="33%" height="30" valign="top"  class="textomensaje10bold">				</td>
						    <td width="33%" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label"><bean:message key="sistema.documento.internos.creados" /></td>
						    <td width="33%" >
							</td>
						  </tr>
							</table>							</td>
			</tr>
			 
			 <tr>
				  <td align="center" colspan="2">				  
				  <display:table name="sessionScope.listaDocumentosInternosProyectos" requestURI="/MostrarFormularioCrearcionDocumentoInterno.do?inicia=SI#tabsproyectos-1"
				  export="false" sort="list" id="b" 
				  pagesize="8"  class="simple" style="width: 90%" >
				  						
				  						<display:column title="Nro Documento" property="codigo_documento_interno" sort="true" align="center" />
											
				  						<display:column property="asunto" title="Asunto" sort="true" width="350"/>								
					  						
				  						<display:column  title="Oficina Destino" sort="true">
				  						
					  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
						  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
						  								<c:out value="${oficina.descripcion_oficina}"/>
						  							</c:if>
						  						</logic:iterate>
				  						</display:column>
				  						<display:column property="dirigido_a" title="Destinatario" sort="true" style="FONT-SIZE: 8pt;"/>
				  						
				  						<display:column   title="Tipo de Documento" >
					  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
					  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						
				  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />
				  						

				  						<display:column   title="Estado" >
					  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
					  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  										  						
				  						<display:column  sort="true" title="Fecha de creacion" >
				  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
				  						</display:column>
				  						
				  						<display:column property="link"  align="center" title="***Operaciones***" />
				  							
		
				  </display:table>
				 
				  </td>
				  
			 </tr>
			 <tr>
			 <td align="center" valign="middle" ><br><a href="javascript:LimpiarListaProyectos();" > [ Regresar ]</a></td>
			 </tr>
			 
	</table>
  </div>
    <div id="tabsproyectos-2">
    <table width="100%">
			 <tr>
							<td height="35" align="center" colspan="2" valign="top">
							<table width="100%"  cellpadding="0" cellspacing="0" background="img/fondoplomo8.jpg">
							  <!--DWLayoutTable-->
							<tr>
							<td width="33%" height="30" valign="top"  class="textomensaje10bold">				</td>
						    <td width="33%" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label"><bean:message key="sistema.documento.internos.creados" /></td>
						    <td width="33%" >
							</td>
						  </tr>
							</table>							</td>
			</tr>
			 
			 <tr>
				  <td align="center" colspan="2">				  
				  <display:table name="sessionScope.listaDocumentosInternosProyectosRecibidos" requestURI="/MostrarFormularioCrearcionDocumentoInterno.do?inicia=SI#tabsproyectos-2"
				  export="false" sort="list" id="b" 
				  pagesize="8"  class="simple" style="width: 90%" >
				  						
				  						<display:column title="Nro Registro" property="link2" sort="true" align="center" />
											
				  						<display:column property="asunto" title="Asunto" sort="true" width="350"/>								
					  						
				  						<display:column  title="Oficina Destino" sort="true">
				  						
					  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
						  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
						  								<c:out value="${oficina.descripcion_oficina}"/>
						  							</c:if>
						  						</logic:iterate>
				  						</display:column>
				  						<display:column property="dirigido_a" title="Destinatario" sort="true" style="FONT-SIZE: 8pt;"/>
				  						
				  						<display:column   title="Tipo de Documento" >
					  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
					  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						
				  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />
				  						

				  						<display:column   title="Estado" >
					  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
					  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  										  						
				  						<display:column  sort="true" title="Fecha de creacion" >
				  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
				  						</display:column>
				  						
				  						<display:column property="link"  align="center" title="***Operaciones***" />
				  							
		
				  </display:table>
				 
				  </td>
				  
			 </tr>
			 <tr>
			 <td align="center" valign="middle" ><br><a href="javascript:LimpiarListaProyectos();" > [ Regresar ]</a></td>
			 </tr>
			 
	</table>
  </div>
  </div>
	</div>
	<div id="break" ></div>
	
	</c:otherwise>
	</c:choose>
		<c:choose>
	   <c:when test='${valorchecked == "0"}'>
                <SCRIPT language="javascript">
                Cambiacondicion(1);
				</SCRIPT>
       </c:when>
       <c:when test='${actualizadjuntos == "1"}'>
                <SCRIPT language="javascript">
                Continuar();
				</SCRIPT>
       </c:when>
       <c:when test='${es_observado == "1"}'>
                <SCRIPT language="javascript">
                Ocultadata();
				</SCRIPT>
       </c:when>
       <c:when test='${es_admiitido != "0"}'>
                <SCRIPT language="javascript">
                Cambiachecks();
				</SCRIPT>
       </c:when>
       
	</c:choose>
	</html:form>
	
</body>
</html>