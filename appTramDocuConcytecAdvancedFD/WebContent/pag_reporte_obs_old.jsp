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
        
		<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
		<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
		<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />

		<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
		<!-- librería para cargar el lenguaje deseado -->
		<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
		<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
		<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
		<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<script type=text/javascript>
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
	
	function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray");
		
	}
	
	function fn_onUnLoad(){
		window.opener.document.forms[0].target = 'mainFrame';
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

function ayuda_reportes(codigooficina,tipo){
		
	var strURL = 'ValidaPaginas.do?tipo=ayudareportes&codofi='+codigooficina+'&operacion='+tipo;
	winPopup = window.open(strURL,"","scrollbars=YES,location=NO,WIDTH=650,HEIGHT=300");
}

function Buscar(){
 document.form_datos.submit();
 $( "#dialog-confirm" ).dialog( "open");
 }

  function Modificar(){
 
 	
		 if (Validarcampovacio(document.form_datos.usuario.value) == false)
              {              
                    alert("Ingresar Usuario");
                    document.form_datos.usuario.focus();
                    return false;           
              } 
			   
				
	usuario = document.getElementById("usuario").value

	clavenueva = document.getElementById("new_password").value
	confirmarclavenueva = document.getElementById("confirm_new_password").value
	
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
<BODY  bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" onLoad="fn_onLoad();">

<TABLE  cellSpacing="0" cellPadding="0" width="100%" border="0"  height="">
  <!--DWLayoutTable-->
    <TBODY>
	<TR >
        <TD height="50" colspan="2" valign="top" bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></TD>
      </TR>
    <TR>
        <TD height="26" colspan="2" class="label" align="middle"  valign="middle"   background="img/fondoplomo8.jpg"><div align="center">Consolidado Pendientes por oficina </div></TD>
    </TR>
    <TR >
      <TD width="100%" height="100%" align="center">
	  <FORM id="form_datos" name="form_datos" action="ReportesGestion.do" method="get" >
	  <input type="hidden" name="tipo" value="cons_pend"/>
	<div id="dialog-confirm" title="Mensaje de Espera" >
  		<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0; font-size : 8px; "></span></p>
  		Espere unos instantes.
  		<br><br><br>
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="img/loading11.gif" border="0" align="top">
		</div>				
	<TABLE width="80%" CELLSPACING="7px." CELLPADDING="0">
<TR>
			<td align="center" valign="middle" ><TABLE align="center" cellspacing="0" cellpadding="0" width="65%" border="0" class="groupcell">
			    <!--DWLayoutTable-->
        <TR>
						<td style="height: 10px;" colspan="5"><span class="label">&nbsp;&nbsp;&nbsp;&nbsp;<strong>B&uacute;squeda 
                        por :</strong> </span></td>
			    </TR>	
					
					<TR>
					  <td style="width: 100px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					  <td width="418"  height="25"  align="right"  valign="middle" class="label" >Fecha de Inicio :&nbsp;&nbsp;  </td>
					<td colspan="2" style="width: 300px;" align="left" valign="middle"><INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_inicio"  maxLength="10" size="11" name="fecha_inicio" value="<c:out value='${fecha_inicio}'/>">
                    <a href="" id="lanzador1"  style="width: 20;" ><img src="img/cal.gif" alt="Fecha de Respuesta Legal (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy</td>						
					  <td width="30" style="width: 10px;">&nbsp;</td>
				  </TR>
					
					<TR>
						<td style="width: 100px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					  <td width="418" align="right" valign="middle"  class="label" height="25">Fecha Fin:&nbsp;&nbsp;  </td>
						
					  <td colspan="2" style="width: 300px;" align="left" valign="middle"><INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_fin"  maxLength="10" size="11" name="fecha_fin" value="<c:out value='${fecha_fin}'/>">
				      <a href="" id="lanzador2"  style="width: 20;" ><img src="img/cal.gif" alt="Fecha de Respuesta Legal (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy</td>						
						<td width="30" style="width: 10px;">&nbsp;</td>
					</TR>									
					<TR>
						<td colspan="3" style="width: 10px;">&nbsp;</td>
					  
					</TR>
					<TR>
						<td style="width: 50px;">&nbsp;</td>
					  <td align="right" valign="middle"  class="label" height="25">Acci&oacute;n a Realizar: </td>
						<td align="left" valign="middle" style="width: 200px;">
						<select id="codigo_motivo" name="codigo_motivo" class="caja1" style="WIDTH: 250px; HEIGHT: 21px">
                                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                                    <c:choose>
                                      <c:when test='${not empty rs_motivos}'>
                                        <c:forEach items='${rs_motivos}' var='pa'>
                                          <option value=<c:out value='${pa.codigo_motivo}'/>
                                          >
                                          <c:out value='${pa.descripcion_motivo}'/>
                                          </option>
                                        </c:forEach>
                                      </c:when>
                                    </c:choose>
                                  </select></td>
					  <td style="width: 350px;" align="left" valign="middle"></td>						
						<td style="width: 10px;">&nbsp;</td>
					</TR>	
					<TR>
						<td height="35"></td>
						<c:choose>
						<c:when test='${not empty listado_rep_obs}'>
						<td colspan="3"  align="center">
                        <INPUT  class="boton" style="WIDTH: 80px; HEIGHT: 20px" id="INPUT5" onClick="javascript:Buscar();" type="button" value="Buscar" name="cmb_grabar">
                        &nbsp;&nbsp;<INPUT  class="boton" style="WIDTH: 80px; HEIGHT: 20px" id="INPUT5" onClick="javascript:window.print();" type="button" value="Imprimir" name="cmb_imprimir"></td>
					    <td></td>
						</c:when>
						<c:otherwise>
						<td colspan="3"  align="center">
                        <INPUT  class="boton" style="WIDTH: 80px; HEIGHT: 20px" id="INPUT5" onClick="javascript:Buscar();" type="button" value="Buscar" name="cmb_grabar"></td>
					    <td></td>
						</c:otherwise>
						</c:choose>
					    
					</TR>	
			</TABLE></td>
	  </TR>				
		
		<!--  -->
		<c:choose>
		<c:when test='${not empty listado_rep_obs}'>
		<TR>
		<td>
			<table width="100%" align="center" cellpadding="0" cellspacing="0">
			<tr>
		  		<TD width="70%" align="center" valign="middle" >
			  	<display:table id="b"  name="sessionScope.listado_rep_obs" export="false" sort="list" pagesize="37" class="simple" style="width:70%">
										<display:column property="oficina" title="Oficina" align="left" />
										<display:column property="actionporrecibir"  title="Por Recibir" align="right" />
										<display:column property="actionntramite" title="En Tramite" align="right" />
				</display:table>

		  		</TD>
		  	</tr>
		  </table>
		  </td>
	  </TR>
		</c:when>
		</c:choose>
		
  </TABLE>
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
  	
</FORM>

	  </TD>
      <TD width="4">
</TD>
    </TR>
    </TBODY>
</TABLE>

</BODY>
</HTML>
