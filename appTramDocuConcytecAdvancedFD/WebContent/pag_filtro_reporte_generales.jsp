<%@ include file="taglibs.jsp" %>
<% 
//Verifica que exista una sesion
String nombreusuario = String.valueOf(session.getAttribute("nombreusuario"));
//System.out.println("el nombre de usuario es.."+nombreusuario);
if (nombreusuario==null || nombreusuario.equals("null")){
%> 
<SCRIPT language="Javascript">
parent.document.location ="pag_expiracion.jsp";
</SCRIPT>
<% 
}
else {
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<fmt:setBundle basename="ApplicationResources" />
<HTML>
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Filtro del Reporte</TITLE>
      <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
    <SCRIPT language="JavaScript">

function buscar()
{
	var ls_accion = "D";
	var procedencia = document.getElementById("procedencia").value
	var sle_fecha = document.getElementById("sle_fecha").value
	var codigo_oficina = document.getElementById("codigo_oficina").value
	var codigo_solicitud = document.getElementById("codigo_solicitud").value
	var numero_expediente = document.getElementById("numero_expediente").value
	var medio = document.getElementById("medio").value
	var codigo_tipo = document.getElementById("codigo_tipo").value
	var numero_registro = document.getElementById("numero_registro").value
	
	var firmadopor = document.getElementById("firmadopor").value
	var dirigido = document.getElementById("dirigido").value
	var estado = document.getElementById("estado").value
  
		 var strURL = "ReportesBuscar.do";
			strURL += "?procedencia="+procedencia.toUpperCase();
			strURL += "&sle_fecha="+sle_fecha;
			strURL += "&codigo_oficina="+codigo_oficina;
			strURL += "&codigo_solicitud="+codigo_solicitud;
			strURL += "&numero_expediente="+numero_expediente;
			strURL += "&medio="+medio;
			strURL += "&codigo_tipo="+codigo_tipo;
			strURL += "&numero_registro="+numero_registro;
			strURL += "&firmadopor="+firmadopor.toUpperCase();
			strURL += "&dirigido="+dirigido.toUpperCase();
			strURL += "&estado="+estado;
			strURL += "&accion="+ls_accion;
			//strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		document.getElementById("ifra_listadocumentos").Document.location.href=strURL;
		return;
		
}

function listar_todos(){
	
	  var strURL = "VerFrameListas.do?tipo=reportegenerales";
	
		document.getElementById("ifra_listadocumentos").Document.location.href=strURL;	 
	
		
	}

function Cerrar(){
	 var strURL = "CargaMesaPartes.do?operacion=buscar";
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	window.close()
	}
	
	function regresar(){
	
	 document.location.href="ValidaPaginas.do?tipo=regresar&pagina=reportes"
	return;
	}
	
function reporte()
{
	
	var ls_accion = "R";
	var procedencia = document.getElementById("procedencia").value
	var sle_fecha = document.getElementById("sle_fecha").value
	var codigo_oficina = document.getElementById("codigo_oficina").value
	var codigo_solicitud = document.getElementById("codigo_solicitud").value
	var numero_expediente = document.getElementById("numero_expediente").value
	var medio = document.getElementById("medio").value
	var codigo_tipo = document.getElementById("codigo_tipo").value
	var numero_registro = document.getElementById("numero_registro").value
	
	var firmadopor = document.getElementById("firmadopor").value
	var dirigido = document.getElementById("dirigido").value
	var estado = document.getElementById("estado").value
  
		 var strURL = "ReportesBuscar.do";
			strURL += "?procedencia="+procedencia.toUpperCase();
			strURL += "&sle_fecha="+sle_fecha;
			strURL += "&codigo_oficina="+codigo_oficina;
			strURL += "&codigo_solicitud="+codigo_solicitud;
			strURL += "&numero_expediente="+numero_expediente;
			strURL += "&medio="+medio;
			strURL += "&codigo_tipo="+codigo_tipo;
			strURL += "&numero_registro="+numero_registro;
			strURL += "&firmadopor="+firmadopor.toUpperCase();
			strURL += "&dirigido="+dirigido.toUpperCase();
			strURL += "&estado="+estado;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
					
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870")
		
}
	
	function validarCaracterFecha(){
    var objeto = validarCaracterFecha.arguments[0];
    var wKey = window.event.keyCode;
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
	
	   </SCRIPT>
	   
<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
</HEAD>
<BODY bgColor="white" PSPARAMS="">
<FORM id="form_datos" name="form_datos" action="" method="post">
<TABLE borderColor="#E9E9E9" cellSpacing="0" cellPadding="0" width="100%" align="center" bgColor="#F0F5FB" border="1">
  <TBODY>
  <TR>
        <TD align="left" valign="middle" height="50" width="100%"><table width="100%" height="50" cellspacing="0" cellpadding="0"  align="left">
						<tr><td bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></td></tr></table></TD>
    </TR>
    <TR class="cabeceratable">
        <TD align="center"  valign="middle" class="label" background="img/fondoplomo8.jpg"  width="100%" height="26">Reportes del Sistema</TD>
    </TR>
    <TR>
        <TD>
            <TABLE id="TABLE1" width="100%">
            <!--DWLayoutTable-->
            <TBODY id="TBODY1">
              <TR class="formulario" id="TR2"> 
                <TD height="22" colspan="4" valign="middle" class="label3" bgcolor="BAD0EB">&nbsp;&nbsp;&nbsp;Generales</TD>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD width="15%" height="25" align="right" valign="middle"  class="label">Procedencia 
                  : </TD>
                <TD width="27%" align="left" valign="middle" id="TD4">&nbsp; 
                  <INPUT class="txt" maxLength="40" size="40" name="procedencia" id="procedencia"  > 
				  <script language="JavaScript" type="text/javascript">if(document.getElementById) document.getElementById('procedencia').focus();</script>                </TD>
                <TD width="12%" align="right" valign="middle" class="label">Fecha 
                  Registro:</TD>
                <TD width="46%" align="left" valign="middle"> &nbsp;
                  <INPUT language="JavaScript"  onKeyPress="validarCaracterFecha(this);" id="sle_fecha"  maxLength="10" size="11" name="sle_fecha"> 
                  &nbsp; <a href="" id="lanzador"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a>                </TD>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD height="25" align="right" valign="middle"  class="label">Area 
                  :</TD>
                <TD align="left" valign="middle" id="TD4">&nbsp;&nbsp;<select id="codigo_oficina" name="codigo_oficina" class="caja12" style="WIDTH: 300px; HEIGHT: 21px" >
                    <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                    <c:choose> <c:when test='${not empty listaoficinas}'> <c:forEach items='${listaoficinas}' var='pa'>
					 <option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.siglas_oficina}'/>  - <c:out value='${pa.descripcion_oficina}'/> 
					 </option> </c:forEach> 
                    </c:when> </c:choose> </select> </TD>
                <TD align="right" valign="middle" class="label">Solicitud :</TD>
                <TD align="left" valign="middle"> &nbsp;&nbsp;<select id="codigo_solicitud" name="codigo_solicitud" class="caja12" style="WIDTH: 280px; HEIGHT: 21px">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_solicitud}'> <c:forEach items='${rs_solicitud}' var='pa'>	<option value=<c:out value='${pa.codigo_solicitud}'/>> 
                    <c:out value='${pa.nombre_solicitud}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select> </TD>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD height="25" align="right" valign="middle"  class="label">N&deg; 
                  Expediente:</TD>
                <TD align="left" valign="middle" id="TD4"> &nbsp;
                  <INPUT class="txt" id="numero_expediente" maxLength="20" size="20" name="numero_expediente"  >                </TD>
                <TD align="right" valign="middle" class="label">Medio :</TD>
                <TD align="left" valign="middle">&nbsp; <select id="medio" name="medio" class="caja12" style="width:100" value=''>
                    <option value="0">--Selec--</option>
                    <option value="OR">Original</option>
                    <option value="FA">Fax </option>
                    <option value="CO">Copia Inf.</option>
                    <option value="EM">Email</option>
                  </select> </TD>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD height="25" align="right" valign="middle"  class="label">Tipo 
                  Documento : </TD>
                <TD align="left" valign="middle" id="TD4">&nbsp; <select id="codigo_tipo" name="codigo_tipo" class="caja12" >
                    <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                    <c:choose> <c:when test='${not empty listatipodoc}'> <c:forEach items='${listatipodoc}' var='pa'>	<option value=<c:out value='${pa.codigo_tipo}'/>> 
                    <c:out value='${pa.descripcion_tipo}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select> </TD>
                <TD align="right" valign="middle" class="label">N&deg; Registro 
                  : </TD>
                <TD align="left" valign="middle" id="TD5">&nbsp;&nbsp;<INPUT class="txt" id="numero_registro" maxLength="20" size="20" name="numero_registro"  >                </TD>
              </TR>
			  <TR class="formulario" id="TR2"> 
                <TD height="25" align="right" valign="middle"  class="label">Firmado por: </TD>
                <TD align="left" valign="middle" id="TD4">&nbsp; 
				<INPUT class="txt" maxLength="40" size="40" name="firmadopor" id="firmadopor"  >				</TD>
                <TD align="right" valign="middle" class="label">Dirigido a: </TD>
                <TD align="left" valign="middle" id="TD5">&nbsp;&nbsp;<INPUT class="txt" maxLength="40" size="40" name="dirigido" id="dirigido"  >                </TD>
              </TR>
			   <TR class="formulario" id="TR2"> 
                <TD height="25" align="right" valign="middle"  class="label">Estado: </TD>
                <TD align="left" valign="middle" id="TD4">&nbsp; 
				<SELECT class="caja12" id="estado" style="WIDTH: 130px" name="estado">
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                 
                  <OPTION value="2">Por Recibir</OPTION>
                  <OPTION value="3">Tramite</OPTION>
                  <OPTION value="4">Archivado</OPTION>
                  <OPTION value="5">Derivado</OPTION>
                  <OPTION value="6">Preliquidado</OPTION>
                  <OPTION value="7">Liquidado</OPTION>
                  <OPTION value="8">Entregado</OPTION>
				  <OPTION value="9">Anulado</OPTION>
                </SELECT>				</TD>
                <TD>&nbsp;</TD>
                <TD>&nbsp;</TD>
              </TR>
              <TR align="center" valign="middle" class="formulario" id="TR2"> 
                <TD height="29" colSpan="4" id="TD10" background="img/fondoplomo8.jpg"> <INPUT class="boton" onClick="buscar()" type="button" value="Buscar" style="WIDTH: 82px; HEIGHT: 20px"> 
				&nbsp;<INPUT class="boton" onClick="listar_todos()" type="button" value="Listar Todos" style="WIDTH: 100px; HEIGHT: 20px">                </TD>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD height="70" colspan="4" valign="top">
				<TABLE width="90%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#ffffff" bgcolor="#ffffff" height="170">
                	<TR>
			   			<TD>
						<IFRAME id="ifra_listadocumentos" name="ifra_listadocumentos" align="top" src="ReportesBuscar.do?accion=M" frameBorder="0" width="100%" height="100%" scrolling="auto" ></IFRAME>			   		    </TD>
        		    </TR>
              </TABLE>				</TD>
              </TR>
			   <TR align="center" valign="middle" class="formulario" id="TR2"> 
                <TD height="40" colspan="4" >
				<INPUT class="boton" style="WIDTH: 100px; HEIGHT: 20px" onClick="javascript:reporte()" type="button" value="Ver Reporte" name="cmd_aceptar">				</TD>
              </TR>
                          </TBODY>
          </TABLE>
      </TD>
    </TR>
    </TBODY>
</TABLE>

<SCRIPT type=text/javascript>
    Calendar.setup({
        inputField     :    "sle_fecha",      // id del campo de texto
        ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
        button         :    "lanzador" 
		   // el id del botón que lanzará el calendario
    });
</SCRIPT>

</FORM>
</BODY>
</HTML>
<% } %>