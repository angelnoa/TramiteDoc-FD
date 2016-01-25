<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<%@ page import="org.apache.struts.action.*, org.apache.struts.Globals" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

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
	
	h3 {
	 	text-transform:capitalize;
	  	font-family:Arial, Helvetica, sans-serif;
	}
	p {
		font-size:16px;
		font-family:Arial, Helvetica, sans-serif;
	}
	
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

	#containerlistaproyectos {
		visibility:visible;
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
		visibility:visible;
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
  

function Regresar(){
	
	var strURL = "ListaDocumentos.do?tipo=derivar&operacion=D&inicia=SI";
	location=strURL;
	
	return;

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



<title>Bandeja Proyectos Recibidos</title>
</head>
<body bgColor="#F0F5FB" onLoad="fn_onLoad();">
	
	<div class="container">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
	<html:form   enctype="multipart/form-data"  action="CrearDocumentoInterno.do"  >

	<div id="capaFondo1"></div>

	<div id="break" ></div>
		
	<div id="containerlistaproyectos">
	<div id="tabsproyectos">
  <ul>
    <li><a href="#tabsproyectos-1">Proyectos recibidos en el Registro Nº <c:out value='${nuemro_registro_bandeja}'/></a></li>
  </ul>
    <div id="tabsproyectos-1">
    <table width="100%">
			 <tr>
							<td height="35" align="center" colspan="2" valign="top">
							<table width="100%"  cellpadding="0" cellspacing="0" background="img/fondoplomo8.jpg">
							  <!--DWLayoutTable-->
							<tr>
							<td width="33%" height="30" valign="top"  class="textomensaje10bold">				</td>
						    <td width="33%" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label">Proyectos Recibidos</td>
						    <td width="33%" >
							</td>
						  </tr>
							</table>							</td>
			</tr>
			 
			 <tr>
				  <td align="center" colspan="2">				  
				  <display:table name="sessionScope.listaProyectosxDocumento" 
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
			 <td align="center" valign="middle" ><br><a href="javascript:Regresar();" > [ Regresar ]</a></td>
			 </tr>
			 
	</table>
  </div>
  </div>
	</div>
	<div id="break" ></div>

		
	</html:form>
	</div>
</body>
</html>