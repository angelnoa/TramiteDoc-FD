<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<%@ page import="org.apache.struts.action.*, org.apache.struts.Globals" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >

<!-- <SCRIPT LANGUAGE=javascript src="js/overlibmws.js"></SCRIPT> -->

<script LANGUAGE="JavaScript" src="js/avatec.js"></script>
<script type="text/javascript" src="js/funciones.js" ></script>

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
		
	width: 380px;
	margin-left: -190px;
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

	width: 980px;
	margin-left: -490px;
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
  
$(function() {
    $( "#dialog-confirm-firmado-manual" ).dialog({
      autoOpen: false,	
      resizable: false,
      height:180,
      modal: true,
      buttons: {
        "Eliminar documento": function() {
          var codigo = $( this ).dialog( "option", "appendTo" );
          //alert(codigo);
          var strURL = "MantenimientoDocumentoInterno.do?cod_doc_interno="+codigo+"&tipofirma=si&interno=SI";
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
			var tipofirma=$("#valor4").val();
			var strURL = "";
			
			if(tipoenvio==null){
				tipoenvio=0;
			}
			
			if(tipofirma==0){
				srtURL = "UtilidadRegistroMovimientoFirmaManual.do?codDoc="+codigo+"&codTip="+tipo+"&observacion="+observacion+"&tipoenvio="+tipoenvio;
				location = srtURL;
				return;
			}else{
				strURL = "CopiarPDFATemporal.do?codDoc="+codigo+"&codTip="+tipo+"&observacion="+observacion+"&tipoenvio="+tipoenvio;
				winPopup = window.open(strURL,"Firma_Digital","scrollbars=YES,resizable=YES,location=NO,WIDTH=730,HEIGHT=430");
			}
			//return;
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

function deletedocumentinterno(codDoc){
	var coddoc = codDoc;
		
	$( "#dialog-confirm-firmado-manual" ).dialog({ appendTo: coddoc });
	$( "#dialog-confirm-firmado-manual" ).dialog( "option", "appendTo", coddoc );
	$( "#dialog-confirm-firmado-manual" ).dialog( "open");

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
 
 function AgregarDocEscaneados(valor) {
	 	var codigo_documento = valor;
		var strURL = 'UploadArchivoFirmadoManualmente.do?codigo_documento=' + codigo_documento + '&opcion=default';
		winPopup = window.open(strURL, "", "scrollbars,HEIGHT=250,WIDTH=580");
	}
 
 function MostrarAdjunto(cantidad){
		
		if(cantidad > 0){
			var strURL = "FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=firma";
			location=strURL;
		}
		return;
	}
 
function LlamarApplet(valor,valor2,valor3,valor4) {
	    
	    var ab = valor;
	    var bc = valor2;
		var cd = valor3;
		var de = valor4;
		
	    $(document).ready(function(){
	    	//$("#containerlista").css("visibility","hidden");
			//$("#personas").load("obtenerLista.do", {cambia:'setearcero'});
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
			   $("#valor4").val(de);
		});

		//var strURL = "CopiarPDFATemporal.do?codDoc="+valor+"&codTip="+valor2;
		//referenciaVentanaFirma = window.open(strURL,"Firma Digital","scrollbars=YES,resizable=YES,location=NO,WIDTH=730,HEIGHT=430");
		
}

 
function Cambiardocumento(valor){
		//alert("entre"+valor);
		var vvalor=valor;
		
		if (vvalor==2){
			var strURL = "FormularioBusquedaDocumentoInternoEC.do?inicia=SI&tipo=firma";
			location=strURL;
			return;
		}
		if (vvalor==3){
			var strURL = "FormularioBusquedaDocumentoInternoET.do?inicia=SI&tipo=firma";
			location=strURL;
			return;
		}
			
}

function CambiardocumentoBandeja(valor){
	//alert("entre"+valor);
	var vvalor=valor;
	
	if (vvalor==2){
		var strURL = "FormularioBusquedaDocumentoInternoEC.do?inicia=SI&tipo=vista";
		location=strURL;
		return;
	}
	if (vvalor==3){
		var strURL = "FormularioBusquedaDocumentoInternoET.do?inicia=SI&tipo=vista";
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
			//alert("haaa"+var_busqueda);
			if (Validarcampovacio(var_busqueda) == false){              
		          alert("Ingresar Codigo de Documento");
		          document.forms[0].codigo_documento_interno_busqueda.focus();
		          return false;           
		    }
			
			
			var strURL = "FormularioBusquedaDocumentoInterno.do?";
			strURL += "tipo=busqueda&";
			//strURL += "tipo="+var_codigo_tipo_documento_interno+"&";
			//strURL += "cod_ofic="+var_codigo_oficina+"&";
			//strURL += "nomdoc="+var_nombre_archivo+"&";
			//strURL += "asnt="+var_asunto;
			strURL += "nrodoc="+var_busqueda;
			location=strURL;
			return;
		}
 
 function ModificarEstado(valor){
		
		var ls_operacion = "modifica";
		
		var strURL = "mostrarlistasdebandejadocumentosinternos.do?cambia=firma&tipo_accion="+ls_operacion+"&codigo_documento_interno="+valor;
		location=strURL;
		return;
		
	}
 
 function habilitaAll(form)
 {
	 var rbuton = form.selectedTodo;
	 var ls_operacion = "deshabilitaTodo";
	 //alert(rbuton.checked);
	 if(rbuton.checked == true){
		 ls_operacion = "habilitaTodo";
	 }
	 
	var strURL = "mostrarlistasdebandejadocumentosinternos.do?cambia=firma&tipo_accion="+ls_operacion;
	location=strURL;
	return;
  
 }
 
</script>



<title><bean:message key="sistema.documento.internos.titulo" /></title>
</head>
<body bgColor="#F0F5FB" onLoad="fn_onLoad();">

<div class="container">
	<html:form   enctype="multipart/form-data"  action="FormularioBusquedaDocumentoInterno.do"  >
	<INPUT  type="hidden" id="valor1" name="valor1" value="">
	<INPUT  type="hidden" id="valor2" name="valor2" value=""> 
	<INPUT  type="hidden" id="valor3" name="valor3" value=""> 
	<INPUT  type="hidden" id="valor4" name="valor4" > 
	<div id="dialog-confirm" title="Mensaje de Alerta" >
  		<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0; font-size : 8px; "></span></p>
  		El documento será eliminado de manera permanente.
	</div>
	<div id="dialog-confirm-firmado-manual" title="Mensaje de Alerta" >
  		<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0; font-size : 8px; "></span></p>
  		El documento será eliminado de manera permanente.
	</div>
	<div id="complemento" class="rounded">
	<div id="Theadsmall"><div style="padding:7px">Información Adicional</div></div>
    	<div id="dialogoaccionCode" style="margin-left:20px; margin-top:20px;">
    	<p class="label" >Accion a realizar:</p>
    	<select id="accionCode" name="accionCode"  class="input">
    	
    	</select>
    	
    	</div>
    	<div style="margin-left:20px; margin-top:20px; ">
    	<p class="label">Observacion:</p>
    	<textarea rows="4" cols="40" id="observacion" class="textarea"></textarea>
    	<br><br></div>
    	<div style="margin-left:25%;">
    	<input type="button" id="enviar-complemento" class="button" value="Enviar">
    	<input type="button" id="cerrar-complemento" class="button" value="Cancelar"></div>
    	<br><br>
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
	
	<div id="containerlista">
	<div id="contactderiva"  class="rounded" >
	<div id="TDHeadCab">
				    		<span class="labelrounded">FIRMA MASIVA DE DOCUMENTOS</span>
				    		<div style="position: absolute; text-align: center; left:90%;">
				    		<input type="button" name="button" class="buttonfirma" value="Firmar" onClick="javascript:window.parent.firmaMasivaIframeFirmaMasiva()" title="Firmar Registros Seleccionados"  ></div> 
		   		   	
	</div>
	
     <div id="Lineaform2" ></div>
     <div id="TDHeadCabBusqueda" >
     <c:if test='${es_admiitido==1}'>
     <div style="width: 100%; height: 40px; margin-left: auto; margin-right: auto; margin-top: 5px;
				text-align: right; float:left;">
		     <p><label><font color="red">Seleccionar todo:</font> &nbsp;&nbsp;</label>
		     <c:choose>
		     <c:when test='${inicia_firma=="1"}'>
		     	<input  type='checkbox' name='selectedTodo' value="ON" checked  onclick="javascript:habilitaAll(this.form);">
		     </c:when>
		     <c:otherwise>
		     	<input  type='checkbox' name='selectedTodo' value="ON" onclick="javascript:habilitaAll(this.form);">
		     </c:otherwise>
		     </c:choose>
		     
		     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		     
	</div>
    
     	<br>
     <br>
	</c:if>
	
	<div id="tabs">
	  <ul>
	   	<li><a href="#tabsbandeja-1">Documentos Creados</a></li>
	    
	  </ul>
		  <div id="tabsbandeja-1">
			<table width="100%" cellpadding="0" cellspacing="0">
								
					 <!-- requestURI="/FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=firma#tabsbandeja-1" -->
					 <tr>
						  <td align="center" colspan="2">				  
						  <display:table name="sessionScope.listaDocumentosInternosFullIFrame"  
						  export="false" sort="list" id="b" 
						  pagesize="20"  class="simple" style="width: 90%" >
						  						<display:column title="Seleccion" property="link3"  align="center" />
						  						<display:column title="Nro Registro" property="codigo_documento"  align="center" />
						  						<display:column title="Nro Documento" property="codigo_documento_interno"  align="center" />
													
						  						<display:column property="asunto" title="Asunto"  width="350"/>								
							  						
						  						<display:column  title="Oficina Destino" >
						  						
							  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
								  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
								  								<c:out value="${oficina.descripcion_oficina}"/>
								  							</c:if>
								  						</logic:iterate>
						  						</display:column>
						  						<display:column property="dirigido_a" title="Destinatario"  style="FONT-SIZE: 8pt;"/>
						  						
						  						<display:column   title="Tipo de Documento" >
							  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
							  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
							  								<c:out value="${estado.nombreBean}"/>
							  							</c:if>
							  						</logic:iterate>
						  						</display:column>
						  						
						  						<display:column property="nombre_arhivo" title="Nombre de Documento"  />
						  						
		
						  						<display:column   title="Estado" >
							  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
							  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
							  								<c:out value="${estado.nombreBean}"/>
							  							</c:if>
							  						</logic:iterate>
						  						</display:column>
						  										  						
						  						<display:column   title="Fecha de creacion" >
						  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
						  						</display:column>
						  						
						  						<display:column property="link"  align="center" title="**Operaciones**" />
						
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
     <!-- de aqui lo saque -->
	  
	</div>
	</div>
	
	</html:form>
	</div>
	
	
	<div id="break" ></div>
	
</body>
</html>