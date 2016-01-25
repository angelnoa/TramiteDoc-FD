<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<%@ page buffer = "16kb" %>
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
<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />
<link rel="stylesheet" href="css/modelo.css" type="text/css" />
<link rel="stylesheet" href="css/tipografias.css"  type="text/css">

<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
		
		
<style type="text/css">
	
	/*h3 {
	 	text-transform:capitalize;
	  	font-family:Arial, Helvetica, sans-serif;
	}
	p {
		font-size:16px;
		font-family:Arial, Helvetica, sans-serif;
	}*/
	
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
	height: 40px;
	margin-left: -390px;
	background: #f1f4ac;
	/*overflow:auto;*/
	
	border: 2px solid #acc835;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;	
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
	
	$("#botoninvisible").mouseover(function(event){
	      $("#botoninvisible").addClass("buttoncss");
	   });
	$("#botoninvisible").mouseout(function(event){
	      $("#botoninvisible").removeClass("buttoncss");
	});	
	
	$('#botoninvisible').click(function() {
		var strURL = "generateReport.do?tipo=RDF";
		location=strURL;	
		return;
	});
	
$(function() {
    $( "#dialog-confirm" ).dialog({
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
        	var strURL = "ReportesGestion.do?tipo=mos_cons_tran";
        	location = strURL;
          $( this ).dialog( "close" );
        }
      },
      close: function() {
    	  var strURL = "ReportesGestion.do?tipo=mos_cons_tran";
      	location = strURL;
        }
    });
  });

});

</script>

<script type="text/javascript">
/*	
	
$(document).ready(function(){
	//$("#fecha_inicio").datepicker();
	    
	
	    var value = 'codAccion';
	    //llama al servlet con el parametro seleccionado
	    $("#accionCode").load("obtenerLista.do", {cambia:value});
	    
	    var value2 = 'codAccionffff';
	    //llama al servlet con el parametro seleccionado
	    $("#accionCode").load("obtenerLista.do", {cambia:value2});		

});	   */ 
	    
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
			
			//verifico que si firma desde la bandeja por firmar
			/*if(codigo==""){
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
function validarCaracterFecha(){
    var objeto = validarCaracterFecha.arguments[0];
    var wKey = window.event.keyCode;
    var numeros = "0123456789";
    valor = objeto.value;
    var nAnt = 0;
    n = valor.length;
    if (n > 0){
      for(var i=0; i< n ; i++){
        if(nAnt < n){ 
          if(n==2 && i==1) objeto.value = valor+"/";
          if(n==5 && i==3) objeto.value = valor+"/";
        }
      }
    }
    nAnt = n;

	}

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

function MensajedeBusqueda(){
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
	//defrente busco
	if(document.getElementById("fecha_inicio").value !=''){
	if(validaFechaDDMMAAAA(document.getElementById("fecha_inicio").value)==false){
		alert("Ingrese una Fecha Inicio Valida");
		document.forms[0].fecha_inicio.value='';
		document.forms[0].fecha_inicio.focus();
		return;
	}}
	
	if(document.getElementById("fecha_fin").value!=''){
	if(validaFechaDDMMAAAA(document.getElementById("fecha_fin").value)==false){
		alert("Ingrese una Fecha Fin Valida");
		document.forms[0].fecha_fin.value='';
		document.forms[0].fecha_fin.focus();
		return;
	}
	}
	document.forms[0].submit();
	 $( "#dialog-confirm" ).dialog( "open");
}
 
 function Regresar(){
		var strURL = "ListaDocumentosFirmados.do?tipo=seguimiento&operacion=S&inicia=SI";
		location=strURL;

		return;
	}
 
 function validaFechaDDMMAAAA(fecha){
		var dtCh= "/";
		var minYear=1900;
		var maxYear=2100;
		function isInteger(s){
			var i;
			for (i = 0; i < s.length; i++){
				var c = s.charAt(i);
				if (((c < "0") || (c > "9"))) return false;
			}
			return true;
		}
		function stripCharsInBag(s, bag){
			var i;
			var returnString = "";
			for (i = 0; i < s.length; i++){
				var c = s.charAt(i);
				if (bag.indexOf(c) == -1) returnString += c;
			}
			return returnString;
		}
		function daysInFebruary (year){
			return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
		}
		function DaysArray(n) {
			for (var i = 1; i <= n; i++) {
				this[i] = 31;
				if (i==4 || i==6 || i==9 || i==11) {this[i] = 30;}
				if (i==2) {this[i] = 29;}
			}
			return this;
		}
		function isDate(dtStr){
			var daysInMonth = DaysArray(12);
			var pos1=dtStr.indexOf(dtCh);
			var pos2=dtStr.indexOf(dtCh,pos1+1);
			var strDay=dtStr.substring(0,pos1);
			var strMonth=dtStr.substring(pos1+1,pos2);
			var strYear=dtStr.substring(pos2+1);
			strYr=strYear;
			if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1);
			if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1);
			for (var i = 1; i <= 3; i++) {
				if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
			}
			month=parseInt(strMonth);
			day=parseInt(strDay);
			year=parseInt(strYr);
			if (pos1==-1 || pos2==-1){
				return false;
			}
			if (strMonth.length<1 || month<1 || month>12){
				return false;
			}
			if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
				return false;
			}
			if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
				return false;
			}
			if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
				return false;
			}
			return true;
		}
		if(isDate(fecha)){
			return true;
		}else{
			return false;
		}
	}
 
</script>



<title><bean:message key="sistema.documento.internos.titulo" /></title>
</head>
<body bgColor="#F0F5FB" onLoad="fn_onLoad();">
<div class="container">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
	<html:form   enctype="multipart/form-data"  action="ListaDocumentosFirmados.do?tipo=seguimiento" >
	<INPUT  type="hidden" id="valor1" name="valor1" value="">
	<INPUT  type="hidden" id="valor2" name="valor2" value=""> 
	<INPUT  type="hidden" id="valor3" name="valor3" value="">
	<div id="dialog-confirm" title="Mensaje de Espera" >
  		<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0; font-size : 8px; "></span></p>
  		Espere unos instantes.
  		<br><br><br>
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="img/loading11.gif" border="0" align="top">
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
	<div id="cerrar-mensajegeneral" style="cursor:pointer; position: absolute;  width:10%; height:40px; top:15px; left:90%; text-align:center ;">  </div>
		   <br>
		   Ningun documento encontrado
	       <br>
	</div>
	
	<div id="break" ></div>
	
	<div id="contactform-busqueda" class="rounded" >
	<div id="TDHeadCab">

				    		<span class="labelrounded">BUSQUEDA DE DOCUMENTOS FIRMADOS</span> 
	<c:choose>
	<c:when test='${verReporteDocumentoInternos == "1"}'>
		<div  id ="botoninvisible" title="Exportar a Excel" style="width:90px; height:20px; position: absolute; top: 6px; padding: 3px 10px; text-align: center;  FONT-SIZE: 8pt; font-weight:normal; left:85%;  color: #003366;  line-height: 20px;" >&nbsp;<img  src="img/xls.gif"  style=" vertical-align: middle; width:16px; height:16px;  border:0px; ">&nbsp;&nbsp; Exportar</div>
	</c:when>
	</c:choose>
	</div>
	
     <div id="Lineaform" ></div>
     
     <div id="left" class="columna-left" > 
     
     <p>
	     <label><bean:message key="sistema.documento.internos.tipo.documento" /></label>
    	<html:select property="codigo_tipo_documento_interno"  styleId="codigo_tipo_documento_interno" styleClass="input" >			             
	             <html:option value="0" >Seleccionar</html:option>
					<html:options collection="tipoDocumentoInternosListTemp"  property="idBean"
						labelProperty="nombreBean"  /> 
				</html:select> <span class="error" ><html:errors property="codigo_tipo_documento_interno"/></span>
	</p>
    

	     <p>
	      <label >&nbsp;&nbsp; <bean:message key="sistema.documento.internos.numero.documento" /></label>
	      <html:text  property="codigo_documento_interno_busqueda" styleId="codigo_documento_interno_busqueda" onkeypress="return validatecla('enteros',this)"  styleClass="input-medium"/>
	    </p>
     	<p>
     	<label > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="sistema.documento.internos.destinatario" /></label>
     	<html:text property="dirigido_a" styleId="dirigido_a"  style="width: 300px;" styleClass="input"/>	
     	</p>
     </div>
     <div id="center"  class="columna-center2" >
     &nbsp;
     </div>
     <div id="rigth" class ="columna-rigth2" >
     <p><label><bean:message key="sistema.documento.internos.numero.registro" /></label>
	 <html:text property="codigo_documento" styleId="codigo_documento" onkeypress="return validatecla('enteros',this)" style="width: 100px;" styleClass="input-medium"/>
     </p>
     <p><label><bean:message key="sistema.documento.internos.fecha.inicio" /></label>
     <html:text  property="fecha_inicio" styleId="fecha_inicio"  style="width: 100px;" styleClass="input-medium" onkeypress="validarCaracterFecha(this);" size="12" maxlength="10"/>
	 &nbsp; <a href="#" id="lanzador1" title="Fecha Inicio (dd/mm/yyyy)"><img src="img/cal.gif"  border="0"></a>
     </p>
     <p>
     <label>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="sistema.documento.internos.fecha.fin" /></label>
	<html:text  property="fecha_fin" styleId="fecha_fin"  style="width: 100px;" styleClass="input-medium" onkeypress="validarCaracterFecha(this);" size="12" maxlength="10" />
		&nbsp; <a href="" id="lanzador2" title="Fecha Fin (dd/mm/yyyy)"><img src="img/cal.gif"  border="0"></a>
     </p>
     </div>
      <!-- a href="javascript:Buscar()" class="button-new round blue image-right ic-right-arrow" title="Buscar" >BUSCAR</a-->
      
      <div class="center-big">
      <input type="button" name="button" class="button" value="Buscar" onClick="Buscar()" alt="busqueda">
    	
     </div>
     <br>
	</div>
	
	
	<div id="break" ></div>
	<c:choose>
	<c:when test='${verReporteDocumentoInternos == "1"}'>
	<div id="containerlistabusqueda">
	<div id="tabsbusqueda">

  <ul>
    <li><a href="#tabsbusquedabandeja">Documentos Firmados Encontrados</a></li>
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
				  <display:table name="sessionScope.reportelistaDocumentosInternos"  
				  export="false" sort="list" id="b" 
				  pagesize="8"  class="simple" style="width: 90%" >
				  						
				  						<display:column title="Nro Documento" property="numero_doc" sort="true" align="center" />
				  						<display:column  sort="true" title="Fecha de Envio" >
				  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_modificacion}" />
				  						</display:column>
				  						<display:column  title="Oficina Destino" sort="true" >
				  						
					  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
						  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
						  								<c:out value="${oficina.descripcion_oficina}"/>
						  							</c:if>
						  					</logic:iterate>
						  					
				  						</display:column>
				  						<display:column property="asunto" title="Asunto" sort="true" width="350"/>
				  						<display:column title="Nro Registro" property="codigo_documento" sort="true" align="center" />
										<display:column   title="Tipo de Documento" >
					  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
					  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />	
					  					<display:column property="dirigido_a" title="Destinatario" sort="true" style="FONT-SIZE: 8pt;"/>	
				  						<display:column   title="Estado" >
					  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
					  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						<display:column property="link"  align="center" title="*Operaciones*" />
				  							
		
				  </display:table>
				 
				  </td>
				  
			 </tr>
			
		</table>
				    			  
	</div>
	</div>
	</div>
	</c:when>
	<c:otherwise>
	<c:if test='${verReporteDocumentoInternos == "2"}'>
			<SCRIPT language="javascript">
				 MensajedeBusqueda();
			</SCRIPT>
			<c:remove var="verReporteDocumentoInternos" scope="session" />
			<c:remove var="verReporteDocumentoInternos" scope="request" />
	</c:if>		  
	<div id="break" ></div>
	
	</c:otherwise>
	</c:choose>
	<SCRIPT type=text/javascript>
				Calendar.setup({
					inputField     :    "fecha_inicio",      // id del campo de texto
					ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
					button         :    "lanzador1" 
					   // el id del botón que lanzará el calendario
				});
				
				Calendar.setup({
					inputField     :    "fecha_fin",      // id del campo de texto
					ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
					button         :    "lanzador2" 
					   // el id del botón que lanzará el calendario
				});

	</SCRIPT>
	</html:form>
	</div>
</body>
</html>