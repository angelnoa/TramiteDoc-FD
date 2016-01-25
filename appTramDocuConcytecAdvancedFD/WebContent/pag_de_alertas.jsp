<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<%@ page import="org.apache.struts.action.*, org.apache.struts.Globals" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String cantidadBandejaAtencionUrgente = String.valueOf(session.getAttribute("cantidadBandejaAtencionUrgente"));
String cantidadBandejaFechaRptaPlazo = String.valueOf(session.getAttribute("cantidadBandejaFechaRptaPlazo"));
String cantidadBandejaFechaRptaLimite = String.valueOf(session.getAttribute("cantidadBandejaFechaRptaLimite"));
String cantidadBandejaFechaRptaFuera = String.valueOf(session.getAttribute("cantidadBandejaFechaRptaFuera"));
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>Alerts</title>
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		<link rel="stylesheet" href="css/modelo.css" type="text/css" />
		<link rel="stylesheet" href="css/tipografias.css"  type="text/css">
        <script src="js/funciones.js" type="text/javascript"></script>
        <script language="JavaScript" src="js/avatec.js"></script>
        <!-- script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT-->
        
<script type="text/javascript">
 

timer = setTimeout('temporizador()', 30000);

function temporizador() {
	$(document).ready(function() {
		$("#cuadro-alertas").fadeIn(500).delay(5000).fadeOut(800).fadeIn(800).fadeOut(500).fadeIn(500).fadeOut(500, function(){
			actualizainformacionBandejaTramite();
			
			$("#cuadro-alertas").fadeIn(500).delay(5000).fadeOut(800).fadeIn(800).fadeOut(500).fadeIn(500).fadeOut(500, function(){
				actualizainformacionAtencionUrgente();
				
				$("#cuadro-alertas").fadeIn(500).delay(5000).fadeOut(800).fadeIn(800).fadeOut(500).fadeIn(500).fadeOut(500, function(){
					actualizainformacion();
					timer = setTimeout("temporizador()", 30000);
				});

			});	

			/**$("#cuadro-alertas").fadeIn(500).delay(5000).fadeOut(800).fadeIn(800).fadeOut(500).fadeIn(500).fadeOut(500, function(){
				actualizainformacionFechaRptaPlazo();
				
				$("#cuadro-alertas").fadeIn(500).delay(5000).fadeOut(800).fadeIn(800).fadeOut(500).fadeIn(500).fadeOut(500, function(){
					actualizainformacionFechaRptaLimite();
					
					$("#cuadro-alertas").fadeIn(500).delay(5000).fadeOut(800).fadeIn(800).fadeOut(500).fadeIn(500).fadeOut(500, function(){
						actualizainformacionFechaRptaVencido();
						
						$("#cuadro-alertas").fadeIn(500).delay(5000).fadeOut(800).fadeIn(800).fadeOut(500).fadeIn(500).fadeOut(500, function(){
							actualizainformacion();
							timer = setTimeout("temporizador()", 20000);
						});
					});
				});
			});**/

		});
	});
}


function enlazaporrecibir(){
	var strURL = "ListaDocumentos.do?tipo=recepcion&operacion=RN&inicia=SI";
	//alert(strURL);
	window.location.href=strURL;
}

function enlazaentramite(){
	var strURL = "ListaDocumentos.do?tipo=derivar&operacion=D&inicia=SI";
	//alert(strURL);
	window.location.href=strURL;
}

function actualizainformacionAtencionUrgente(){
	$(document).ready(function(){
	    $("#cuadro-alertas").load("mostraralerta.do", {valorBandeja:'1'});
	});
}

function actualizainformacionFechaRptaPlazo(){
	$(document).ready(function(){
	    $("#cuadro-alertas").load("mostraralerta.do", {valorBandeja:'2'});
	});
}

function actualizainformacionFechaRptaLimite(){
	$(document).ready(function(){
	    $("#cuadro-alertas").load("mostraralerta.do", {valorBandeja:'3'});
	});
}

function actualizainformacionFechaRptaVencido(){
	$(document).ready(function(){
	    $("#cuadro-alertas").load("mostraralerta.do", {valorBandeja:'4'});
	});
}

function actualizainformacion(){
	$(document).ready(function(){
	    $("#cuadro-alertas").load("mostraralerta.do", {valorBandeja:'0'});
	});
}

function actualizainformacionBandejaTramite(){
	$(document).ready(function(){
	    $("#cuadro-alertas").load("mostraralerta.do", {valorBandeja:'5'});
	});
}

</script>


<style type="text/css">
	
	h3 {
	 	text-transform:capitalize;
	  	font-family:Arial, Helvetica, sans-serif;
	}
	/*p {
		font-size:16px;
		font-family:Arial, Helvetica, sans-serif;
	}*/
	
	#mensajealerta {
	/**display:none;
		left: 50%; 
		top: 50%;
		margin-left: -390px;
		*/
	position:relative;

	padding:0px;
	text-align: center;
	font-family: Verdana, sans-serif;
	font-size: 13px;
	font-weight: bold;
	color: #003366;
	width: 200px;
	background: #f1f4ac;
	/**height: 70px;
	
	line-height: 12px;**/
	overflow:auto;
	}
	
.rounded-small{
	border: 2px solid #acc835;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;	
	clear: both;
	}
	

</style>
</head>
<body>
	<div style="width:320px; height:50px; position: relative;">
	<div  id ="cuadro-alertas" title="Pendientes de recibir" style="width:300px; height:50px; position: static; top: 6px; padding: 3px 10px; text-align: center;  FONT-SIZE: 10pt; font-weight:bold;  color: white;  line-height: 20px;" >
	<c:out value='${cantidadBandejaRecepcion}'/>&nbsp;<img  src="img/email-receive-icon.png" onclick="enlazaporrecibir();"  style=" vertical-align: middle; width:64px; height:64px;  border:0px; ">
	&nbsp; Por Recibir</div>
	</div>
	
</body>
</html>