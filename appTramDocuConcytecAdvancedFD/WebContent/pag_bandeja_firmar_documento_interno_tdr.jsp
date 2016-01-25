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
<link rel="stylesheet" href="css/tipografias.css"  type="text/css">

<style type="text/css">
	
	
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
		position:relative;
		float: center;
		top: 15%;
		left: 50%; 
		line-height: 120%;

	width: 1000px;
	margin-left: -500px;
	background: #FFFFFF;*
	overflow:auto;
	}
	
	#containerlistabusqueda {
		position:relative;
		float: center;
		top: 15%;
		left: 50%; 
		line-height: 120%;

	width: 1000px;
	margin-left: -500px;
	background: #FFFFFF;*
	overflow:auto;
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
	
/*	#menuFloat {
	top:0px;
	position: absolute;
	width: 100px;
 	margin-top: 30px;
  	border-top: 1px solid purple;
  	border: 1px solid #cccccc;
  	margin-left: 1200px;
  	padding-top: 19px;
	
	}
	
	#menuFloat.fixed {
	position: fixed;
	}*/
	
</style>


<script type="text/javascript">
$(document).ready(function(){
	//$("#calendarito").datepicker();
	
	    var value = 'codAccion';
	    //llama al servlet con el parametro seleccionado
	    $("#accionCode").load("obtenerLista.do", {cambia:value});
	    
	    var value2 = 'codAccionffff';
	    //llama al servlet con el parametro seleccionado
	    $("#accionCode").load("obtenerLista.do", {cambia:value2});		

});		
	
$(document).ready(function(){
	//$("#calendarito").datepicker();
	
/* MENU FLOTANTE
	//Obtenemos la posición y de la nueva capa que contiene el menú
	var top = $("#menuFloat").offset().top - parseFloat($("#menuFloat").css('marginTop').replace(/auto/, 0));
	$(window).scroll(function (event) {
	// Obtenemos la posición y del punto de scroll
	var y = $(this).scrollTop();
	// Chequeamos si y es mayor o igual a top
	if (y >= top) {
	// Si “y” es mayor o igual a “top” lo es añadimos la subclase “fixed” a la capa div contenedora del menú
		$("#menuFloat").addClass('fixed');
	} else {
	//Si “y” es inferior a “top” le quitamos la subclasse
		$("#menuFloat").removeClass('fixed');
	}
	});
*/
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
          var strURL = "MantenimientoDocumentoInterno.do?cod_doc_interno="+codigo+"&tipofirma=si";
          location = strURL;
          $( this ).dialog( "close" );
        },
        Cancelar: function() {
          $( this ).dialog( "close" );
        }
      }
    });
  });
	
  $( "#tabs" ).tabs();
  $( "#tabsbusqueda" ).tabs();
  

	$("#cerrar-panel").click(function () {
	      $("#panel").slideUp("fast");
    });
	
	$("#cerrar-mensajegeneral").click(function () {
	      $("#mensajegeneral").slideUp("fast");
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
			
			if(tipoenvio==null){
				tipoenvio=0;
			}
			
			/*if(codigo==""){
				//alert("entre");
				codigo=$("#pcodigodocumentointerno").val();
				tipo=$("#ptipocodigodocumentointerno").val();
			}*/
			//alert(codigo+","+tipo);
			var strURL = "CopiarPDFATemporal.do?codDoc="+codigo+"&codTip="+tipo+"&observacion="+observacion+"&tipoenvio="+tipoenvio;
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

function deletedocument(codDoc){
	var coddoc = codDoc;
		
	$( "#dialog-confirm" ).dialog({ appendTo: coddoc });
	$( "#dialog-confirm" ).dialog( "option", "appendTo", coddoc );
	$( "#dialog-confirm" ).dialog( "open");

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
	winPopup = window.open(strURL,"","scrollbars=YES,resizable=YES,location=NO,WIDTH=800,HEIGHT=400");
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
 
function Cambiardocumento(valor){
		//alert("entre"+valor);
		var vvalor=valor;
		
		if (vvalor==1){
			var strURL = "FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=firma";
			location=strURL;
			return;
		}
		if (vvalor==2){
			var strURL = "FormularioBusquedaDocumentoInternoEC.do?inicia=SI&tipo=firma";
			location=strURL;
			return;
		}
			
}

function CambiardocumentoBandeja(valor){
	//alert("entre"+valor);
	var vvalor=valor;
	
	if (vvalor==1){
		var strURL = "FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=vista";
		location=strURL;
		return;
	}
	if (vvalor==2){
		var strURL = "FormularioBusquedaDocumentoInternoEC.do?inicia=SI&tipo=vista";
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
 
 function mostrardiv() {
	 div = document.getElementById('flotante');
	 div.style.display ='';
}

function cerrar() {
	 div = document.getElementById('flotante');
	 div.style.display='none';
}
 
function Regresar(){
				
	/*	alert(var_apellido_paterno);
		alert(var_apellido_materno);
		alert(var_nombres);
		
		habilito ventana modal
		pido observacion y motivo
		ingresan datos presionan boton entonces desactivo modal
		y sigo con registro automaticamente
		si cancelan desactivo modal*/
		
		var strURL = "MostrarFormularioCrearcionDocumentoInterno.do?tipo=firma";
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
	
 function MensajeFrame(){
	$(document).ready(function(){

		   $("#panel").slideDown("slow");
		  // $("#panel").delay(2000);
		  // $("#panel").slideUp("slow");
		
		});
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
<div class="container">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
	<html:form   enctype="multipart/form-data"  action="FormularioBusquedaDocumentoInterno.do"  >
	<INPUT  type="hidden" id="valor1" name="valor1" value="">
	<INPUT  type="hidden" id="valor2" name="valor2" value=""> 
	<INPUT  type="hidden" id="valor3" name="valor3" value=""> 
	<div id="dialog-confirm" title="Mensaje de Alerta" >
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
    	<textarea rows="4" cols="40" id="observacion"></textarea>
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
	
	<div id="contactderiva" class="rounded" >
	<div id="TDHeadCab">

				    		<span class="labelrounded">BANDEJA DOCUMENTOS POR FIRMAR</span> 
				   		   	
	</div>
	<div id="TDHeadCabRadios" >
	<c:choose>
	<c:when test='${vista=="1"}'>
    <a href="javascript:CambiardocumentoBandeja(1)" ><html:radio property="radiobuttons" value="2" onclick="CambiardocumentoBandeja(1)"><bean:message key="sistema.documento.internos.normales" /></html:radio></a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:CambiardocumentoBandeja(2)" ><html:radio property="radiobuttons" value="3" onclick="CambiardocumentoBandeja(2)"><bean:message key="sistema.documento.internos.ccp" /></html:radio></a>
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" ><html:radio property="radiobuttons" value="1"><bean:message key="sistema.documento.internos.tdr" /></html:radio></a>
  	</c:when>
  	<c:otherwise>
  	<a href="javascript:Cambiardocumento(1)" ><html:radio property="radiobuttons" value="2" onclick="Cambiardocumento(1)"><bean:message key="sistema.documento.internos.normales" /></html:radio></a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:Cambiardocumento(2)" ><html:radio property="radiobuttons" value="3" onclick="Cambiardocumento(2)"><bean:message key="sistema.documento.internos.ccp" /></html:radio></a>
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" ><html:radio property="radiobuttons" value="1"><bean:message key="sistema.documento.internos.tdr" /></html:radio></a>
  	</c:otherwise>
  	</c:choose>
  	</div>
     <div id="Lineaform2" ></div>
     <div id="TDHeadCabBusqueda" >
     <c:if test='${es_admiitido==1}'>
     <div style="width: 50%; height: 50px; margin-left: auto; margin-right: auto; margin-top: 5px;
				text-align: right; float:left;">
		     <p><label>Búsqueda por : &nbsp;&nbsp;&nbsp;&nbsp;</label>
		     <html:text property="codigo_documento_interno_busqueda" styleId="codigo_documento_interno_busqueda" onkeypress="return validatecla('enteros',this)" style="width: 150px;" styleClass="input-medium"/>
		     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		     
	</div>
     <div style="width:50%; float:right ;">
     	<input type="button" name="button" class="button" value="Buscar" onClick="Buscar()" alt="busqueda"  >
        <input type="button" name="button" class="button" value="Listar Todos" onClick="Regresar()" alt="Listar Todos"  >
     </div>

     	<br>
     <br>
	</c:if>
     </div>
	</div>
	
	
	<div id="break" ></div>
	<c:choose>
	<c:when test='${verListaDocumentosInternosBusqueda == "1"}'>
	<div id="containerlistabusqueda">
	<div id="tabsbusqueda">
  <ul>
    <li><a href="#tabsbusquedabandeja">Documentos Internos Encontrados</a></li>
    
  </ul>
  <div id="tabsbusquedabandeja">
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
				  <display:table name="sessionScope.listaDocumentosInternosBusqueda"  requestURI="/FormularioBusquedaDocumentoInternoET.do?inicia=SI&tipo=firma#tabsbusquedabandeja"
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
	</div>
	</c:when>
	<c:otherwise>
	<div id="containerlista">
	<div id="tabs">
  <ul>
   	<li><a href="#tabsbandeja-1">Documentos Nuevos</a></li>
    <li><a href="#tabsbandeja-2">Documentos para Derivar</a></li>
    <li ><a href="#tabsbandeja-3">Documentos para Reiterar</a></li>
  </ul>
  <div id="tabsbandeja-1">
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
				  <display:table name="sessionScope.listaDocumentosInternosN" requestURI="/FormularioBusquedaDocumentoInternoET.do?inicia=SI&tipo=firma#tabsbandeja-1"
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
			 <!-- tr>
			 <td align="center" valign="middle" ><br><a href="javascript:LimpiarLista();" > [ Regresar ]</a></td>
			 </tr-->
		</table>
				    			  
	</div>
	<div id="tabsbandeja-2">
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
				  <display:table name="sessionScope.listaDocumentosInternosD" requestURI="/FormularioBusquedaDocumentoInternoET.do?inicia=SI&tipo=firma#tabsbandeja-2"
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
			 <!-- tr>
			 <td align="center" valign="middle" ><br><a href="javascript:LimpiarLista();" > [ Regresar ]</a></td>
			 </tr-->
				   		</table>
	</div>
	<div id="tabsbandeja-3">
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
				  <display:table name="sessionScope.listaDocumentosInternosR" requestURI="/FormularioBusquedaDocumentoInternoET.do?inicia=SI&tipo=firma#tabsbandeja-3"
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
			 <!-- tr>
			 <td align="center" valign="middle" ><br><a href="javascript:LimpiarLista();" > [ Regresar ]</a></td>
			 </tr-->
				   		</table>
	</div>
	</div>
			
	</div>			  
	<div id="break" ></div>
	
	</c:otherwise>
	</c:choose>
	</html:form>
	</div>
</body>
</html>