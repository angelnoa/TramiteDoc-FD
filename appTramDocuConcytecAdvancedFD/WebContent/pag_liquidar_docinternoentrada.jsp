<%@ include file="taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT> 
<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
<SCRIPT language="JavaScript">
function Cerrar(){
 var strURL = "CargaMesaPartes.do?operacion=buscar";

opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
window.close()
}
function grabar(){

		   if (Validarcampovacio(document.form_datos.fecha.value) == false)
              {              
                    alert("Ingresar Fecha");
                    document.form_datos.fecha.focus();
                    return false;           
              } 
			     if (Validarcampovacio(document.form_datos.hora.value) == false)
              {              
                    alert("Ingresar Hora");
                    document.form_datos.hora.focus();
                    return false;           
              } 

/* var ls_operacion = "N";
	var strURL = document.form_datos.action='MantEntregarRecibir.do'+'?operacion='+ls_operacion;
	
	document.form_datos.submit();*/
	
		var fecha  = document.getElementById("fecha").value
		var hora  = document.getElementById("hora").value
		var observacion  = document.getElementById("observacion").value
		
		var strURL = "MantEntregarRecibir.do?operacion=N";
 		strURL += "&fecha="+fecha;
		strURL += "&hora="+hora;
		strURL += "&observacion="+observacion;
		strURL +="&h="+ <%= System.currentTimeMillis()%>

opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL

var strURL="ValidaPaginas.do?tipo=mensajerecepcion"

	strURL+="&codigo_recepcion="+"<c:out value='${correlativorecepcion}'/>";
		
 window.open(strURL,"","HEIGHT=160,WIDTH=300,scrollbars=yes")
 
window.close()
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

<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Seleccion de Serie Documental y Expediente</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <META content="PB9.0.0.5507" name="GENERATOR">
    <LINK href="css/PlanSec.css" rel="stylesheet" type="text/css">
	<link href="css/main.css"   rel="stylesheet" type="text/css"> 
	<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
	 <LINK href="css/bkinHome.css" type=text/css rel=stylesheet>
	<LINK href="css/bkin2.css" type=text/css rel=stylesheet>
	<LINK href="css/gridStyle.css" type=text/css rel=stylesheet>
</HEAD>
<BODY PSPARAMS="">
<FORM id="form_datos" name="form_datos" action="" method="post">
    <TABLE align="center" width="60%" border="1" bordercolor="#E9E9E9" cellpadding="0" cellspacing="0">
	 <TR>
	   <TD align="center" class="TextFieldOn35" colspan="2" bgcolor="e9e9e9"><font color="#000000" size="2" ><strong>Recibir un Documento Courier</strong></font></TD>
	 </TR>
	<TR>
	<TD>
    <TABLE width="100%" align="center" border="0" bgColor="#F0F5FB">
          <!--DWLayoutTable-->
          <TBODY>
            <TR> 
              <TD width="135" height="20" align="right" valign="middle" class="formulario" > 
                N&deg; Registro: </TD>
              <TD width="153" align="left" valign="middle" >&nbsp; &nbsp;<c:out value='${documento}'/>  
              </TD>
              <TD width="101" align="right" valign="middle" > N&deg; Expediente:</TD>
              <TD width="187" align="left" valign="middle" >&nbsp;&nbsp;<c:out value='${codigo_expediente}'/></TD>
            </TR>
            <TR> 
              <TD width="135" height="23" align="right" valign="middle" class="formulario" > 
                N&deg; Doc.: </TD>
              <TD colspan="3" align="left" valign="middle" >&nbsp;&nbsp;<c:out value='${descripcion_tipo}'/><c:out value='${numero_documento}'/> 
              </TD>
            </TR>
            <TR> 
              <TD width="135" height="23" align="right" valign="middle" class="formulario" > 
                Fecha: </TD>
              <TD colspan="3" align="left" valign="top" >&nbsp; <INPUT class="txt" id="fecha" maxLength="11" size="15" name="fecha"onKeyPress="validarCaracterFecha(this);" > 
                <script language="JavaScript" type="text/javascript">if(document.getElementById) document.getElementById('fecha').focus();</script> 
                &nbsp; <a href="" id="lanzador"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a>
				 &nbsp;dd/mm/yyyy 
              </TD>
            </TR>
            <TR> 
              <TD height="20" align="right" valign="middle" class="formulario"> 
                Hora: </TD>
              <TD colspan="3" align="left" valign="top"> &nbsp;&nbsp; <INPUT class="txt" id="hora" maxLength="11" size="11" name="hora" > 
			  &nbsp;00:00 
              </TD>
            </TR>
            <TR> 
              <TD height="20" align="right" valign="middle" class="formulario"> 
                Observaci&oacute;n: </TD>
              <TD colspan="3" align="left" valign="top"> &nbsp;&nbsp; <TEXTAREA id="observacion" style="WIDTH: 300px; HEIGHT: 72px" name="observacion" rows="4" cols="71"></TEXTAREA> 
              </TD>
            </TR>
            <TR> 
              <TD height="20">&nbsp;</TD>
              <TD>&nbsp;</TD>
              <TD>&nbsp;</TD>
              <TD>&nbsp;</TD>
            </TR>
            <TR> 
              <TD height="27" colSpan="4" align="center"> <INPUT language="JavaScript"  class="Button5" sid="cmdGrabar" onClick="javascript:grabar();" type="button" value="Grabar" name="cmdGrabar" style="WIDTH: 80px; HEIGHT: 20px"> 
                <INPUT name="cmd_salir" onClick="javascript:Cerrar();" type="button" id="cmd_salir" value="Cerrar" class="Button5" style="WIDTH: 80px; HEIGHT: 20px"> 
              </TD>
            </TR>
			
          </TBODY>
        </TABLE>
	</TD>
	</TR>
	</TABLE>
	<SCRIPT type=text/javascript>
    Calendar.setup({
        inputField     :    "fecha",      // id del campo de texto
        ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
        button         :    "lanzador" 
		   // el id del botón que lanzará el calendario
    });
</SCRIPT>
</FORM>
<P>&nbsp;</P>
</BODY>
</HTML>
