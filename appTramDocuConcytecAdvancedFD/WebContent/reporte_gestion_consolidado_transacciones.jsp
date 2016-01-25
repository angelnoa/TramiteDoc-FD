<%@ include file="taglibs.jsp" %>
<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Ventana de Mesa de Partes</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  
        <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
        <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="css/modelo.css" type="text/css" />
        <link rel="stylesheet" href="css/tipografias.css"  type="text/css">
        
<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />

<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<script language="JavaScript" >
$(document).ready(function(){
	
	$(function() {
	    $( "#dialog-confirm" ).dialog({
	      autoOpen: false,	
	      resizable: false,
	      height:180,
	      modal: true,
	      buttons: {
	        
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



 function Buscar(){
 document.form_datos.submit();
 $( "#dialog-confirm" ).dialog( "open");
 }

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
 
  function Modificar(){
		 if (Validarcampovacio(document.form_datos.usuario.value) == false)
           {              
                 alert("Ingresar Usuario");
                 document.form_datos.usuario.focus();
                 return false;           
           } 

		usuario = document.getElementById("usuario").value;
		clavenueva = document.getElementById("new_password").value;
		confirmarclavenueva = document.getElementById("confirm_new_password").value;
	
	if(clavenueva!=confirmarclavenueva){
					alert("Nueva Contraseña y Confirmar Contraseña diferentes");
                    document.form_datos.clavenueva.focus();
                    return false;           
	
	}
	
	document.form_datos.submit();
    //	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	//window.close()
}

</script>
</HEAD>
<BODY bgColor="#F0F5FB"  bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" >
<div class="container">	
<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
</div>
<div id="break" ></div>
	
	<FORM id="form_datos" name="form_datos" action="ReportesGestion.do" method="get">
	<div id="contactform-busqueda" class="rounded" >
	  <input type="hidden" name="tipo" value="cons_tran"/>
		<div id="dialog-confirm" title="Mensaje de Espera" >
  		<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0; font-size : 8px; "></span></p>
  		Espere unos instantes.
  		<br><br><br>
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="img/loading11.gif" border="0" align="top">
		</div>
	<div id="TDHeadCab">
	<span class="labelrounded">CONSOLIDADO DE TRANSACCIONES</span>
	
	</div>	
		<!-- LINEA SEPARADORA ENTRE EL TITULO DEL MARCO Y LOS CONTROLES -->
     <div id="Lineaform" ></div>
	<div class="center-big-left">
		<p><label>Fecha de Inicio:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<INPUT  class="input-medium" onKeyPress="validarCaracterFecha(this);" id="fecha_inicio"  maxLength="10" size="11" name="fecha_inicio" value="<c:out value='${fecha_inicio}'/>">
            <a href="" id="lanzador1"  style="width: 20;" ><img src="img/cal.gif" alt="Fecha de Respuesta Legal (dd/mm/yyyy)" border="0"></a> 
		</p>
		<p><label>Fecha Fin:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		 <INPUT class="input-medium" onKeyPress="validarCaracterFecha(this);" id="fecha_fin"  maxLength="10" size="11" name="fecha_fin" value="<c:out value='${fecha_fin}'/>">
				      <a href="" id="lanzador2"  style="width: 20;" ><img src="img/cal.gif" alt="Fecha de Respuesta Legal (dd/mm/yyyy)" border="0"></a> 
		</p>
	</div>
	
	<div class="center-big">
		<INPUT  class="button"  id="INPUT5" onClick="javascript:Buscar();" type="button" value="Buscar" name="cmb_grabar">
	</div>
	<SCRIPT>
	   Calendar.setup({
						inputField     :    "fecha_inicio",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador1" 
					});
					
						   Calendar.setup({
						inputField     :    "fecha_fin",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador2" 
					});
					
	</SCRIPT>
	<br>
	</div>
	<div id="break" ></div>
		<div class="derivalista">
		<c:choose>
		<c:when test='${not empty listado_trans}'>
		<table width="100%"  cellpadding="0" cellspacing="0" >
					<tr>
					<td height="13" colspan="2" valign="top" align="center" >
						<display:table name="requestScope.listado_trans" export="false" sort="list" id="b" pagesize="36" class="simple" style="width:100%">
											
											<display:column property="oficina" media="html" title="Oficina" align="left" />
											<display:column property="creados" media="html" title="Creados" align="right" />
											<display:column property="derivados" media="html" title="Derivados" align="right" />
											<display:column property="archivados" media="html" title="Archivados" align="right" />
											<display:column property="anulados" media="html"  title="Anulados" align="right" />
		
						</display:table>
					</td>
					</tr>
		</table>
		</c:when>
		</c:choose>
		</div>
	</FORM>
	</div>

</BODY>
</HTML>