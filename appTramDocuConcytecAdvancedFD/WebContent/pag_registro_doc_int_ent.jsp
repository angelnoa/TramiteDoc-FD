<%@ include file="taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/prototype-1.4.0.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/scriptaculous.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/overlibmws.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/ajaxtags.js"></SCRIPT>
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


function Validar(){

	
		 if (Validarcampovacio(document.form_datos.numero_documento.value) == false)
              {              
                    alert("Ingresar Numero de Documento");
                    document.form_datos.numero_documento.focus();
                    return false;           
              } 
			 if (Validarcampovacio(document.form_datos.fecha_registro.value) == false)
              {              
                    alert("Ingresar Fecha Registro");
                    document.form_datos.fecha_registro.focus();
                    return false;           
              } 
			  
			 
			   if (Validarcampovacio(document.form_datos.asunto_documento.value) == false)
              {              
                    alert("Ingresar Asunto del Documento");
                    document.form_datos.asunto_documento.focus();
                    return false;           
              } 
			  
			  
				 if(document.form_datos.codigo_tipo.value=="0"){
						  alert("Ingresar  Codigo Tipo");
				document.form_datos.codigo_tipo.focus();
				return false;
				}
				
	codigo_tipo = document.getElementById("codigo_tipo").value
	numero_documento = document.getElementById("numero_documento").value
	fecha_registro = document.getElementById("fecha_registro").value
	asunto_documento = document.getElementById("asunto_documento").value
	observa_documento = document.getElementById("observa_documento").value

		var strURL = "MantDocumento.do?operacion=N";
	
			
			strURL += "&codigo_tipo="+codigo_tipo;
			strURL += "&numero_documento="+numero_documento;
      		strURL += "&fecha_registro="+fecha_registro;
			strURL += "&asunto_documento="+asunto_documento;
			strURL += "&observa_documento="+observa_documento;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
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
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Asociar Documento de Entrada a Documento Interno</TITLE>
     <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
     <LINK href="PlanSec.css" type="text/css" rel="stylesheet">
		<link href="css/main.css"   rel="stylesheet" type="text/css"> 
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		 <LINK href="css/bkinHome.css" type=text/css rel=stylesheet>
		<LINK href="css/bkin2.css" type=text/css rel=stylesheet>
		<LINK href="css/gridStyle.css" type=text/css rel=stylesheet>
        <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
</HEAD>
<BODY bgColor="white" >

<FORM id="form_datos" name="form_datos" action="" method="post">
    <TABLE borderColor="#E9E9E9" cellSpacing="0" cellPadding="0" width="80%" align="center" bgColor="#F0F5FB" border="1">
        <TBODY>
        <TR class="cabeceratable">
            <TD align="middle" height="20" class="TextFieldOn35"><font color="#000000" size="2" ><strong>REGISTRO DE UN DOCUMENTO INTERNO</strong></font></TD>
        </TR>
        <TR>
            <TD align="middle" width="100%">
                <P align="center">
                
          <TABLE id="TABLE1" width="100%" align="center" border="0" >
            <!--DWLayoutTable-->
            <TBODY id="TBODY1">
              <TR align="left" valign="middle" id="TR1"> 
                <TD height="18" colSpan="8" background="img/fondoplomo8.jpg" id="TD1"> 
                  &nbsp;&nbsp;&nbsp; <font size="2">Documento de Entrada</font> 
                </TD>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD width="70" height="20" align="right" valign="middle" id="TD5" class="label"> 
                  N&deg; Registro:</TD>
                <TD colspan="2" align="left" valign="middle" i> &nbsp;&nbsp;<c:out value='${documento}'/> 
                </TD>
                <TD width="111" align="right" valign="middle" id="TD7" class="label">N&deg; 
                  Expediente:</TD>
                <TD colspan="2" align="left" valign="middle" id="TD8"> <c:out value='${codigo_expediente}'/> 
                </TD>
                <TD width="90" align="right" valign="middle" class="label">N&deg; Doc.:</TD>
                <TD width="206" valign="middle">&nbsp;&nbsp;<c:out value='${descripcion_tipo}'/><c:out value='${numero_documento}'/> 
                </TD>
              </TR>
              <TR id="TR1"> 
                <TD height="18" colSpan="8" background="img/fondoplomo8.jpg" id="TD1"> 
                  <P align="center"> <STRONG>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    </STRONG>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    &nbsp; </P></TD>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD height="26" align="right" id="TD5" class="label" >Tipo Doc:</TD>
                <TD colspan="4" align="left" i>&nbsp;&nbsp; <select id="codigo_tipo" name="codigo_tipo" class="caja" >
                <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                <c:choose> <c:when test='${not empty rs_tipodoc_todos}'> <c:forEach items='${rs_tipodoc_todos}' var='pa'>	<option value=<c:out value='${pa.codigo_tipo}'/>> 
                  <c:out value='${pa.descripcion_tipo}'/> </option> </c:forEach> 
                  </c:when> </c:choose> 
              </select> </TD>
                <TD width="89">&nbsp;</TD>
                <TD>&nbsp;</TD>
                <TD>&nbsp;</TD>
              </TR>
              <TR  id="TR2" class="label" > 
                <TD height="26" align="right" id="TD5" class="label" >N&uacute;mero Doc.:</TD>
                <TD colspan="4" align="left" i> &nbsp;&nbsp; <INPUT class="txt" id="numero_documento" maxLength="40" size="40" name="numero_documento"> 
                </TD>
                <TD width="89">&nbsp;</TD>
                <TD>&nbsp;</TD>
                <TD>&nbsp;</TD>
              </TR>
              <TR class="formulario" id="TR4"> 
                <TD height="24" align="right" class="label" id="TD13">Fecha 
                  Doc.:</TD>
                <TD colspan="4" align="left" id="TD14"> &nbsp;&nbsp; <INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_registro"  maxLength="10" size="11" name="fecha_registro"> 
                  &nbsp; <a href="" id="lanzador"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a> 
				  &nbsp;dd/mm/yyyy
                </TD>
                <TD>&nbsp;</TD>
                <TD>&nbsp;</TD>
                <TD>&nbsp;</TD>
              </TR>
              <TR class="formulario" id="TR6"> 
                <TD height="74" align="right" class="label">Asunto:</TD>
                <TD colspan="4" class="label" id="TD21">&nbsp;&nbsp; <TEXTAREA id="asunto_documento" style="WIDTH: 300px; HEIGHT: 60px" name="asunto_documento" rows="4" cols="71"></TEXTAREA> 
                </TD>
                <TD>&nbsp;</TD>
                <TD>&nbsp;</TD>
                <TD>&nbsp;</TD>
              </TR>
              <TR class="formulario" id="TR6"> 
                <TD height="64" colspan="2" align="right" valign="middle" class="label"> <DIV id="divTexto1" style="visibility: visible;">Observaci&oacute;n:</DIV></TD>
                <TD colspan="3" valign="top">&nbsp;&nbsp; <DIV id="divCopia" style="visibility: visible;"> 
                    <TEXTAREA id="observa_documento" style="WIDTH: 300px; HEIGHT: 60px" name="observa_documento" rows="4" cols="71"></TEXTAREA>
                  </DIV></TD>
                <TD>&nbsp;</TD>
                <TD>&nbsp;</TD>
                <TD>&nbsp;</TD>
              </TR>
              <tr> 
                <td height="3"></td>
                <td width="1"></td>
                <td width="147"></td>
                <td></td>
                <td width="34"></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
            </TBODY>
          </TABLE>
            </TD>
        </TR>
        </TBODY>
    </TABLE>
	
    <TABLE width="100%" align="center">
        <TBODY>
        <TR>
            <TD align="center">
			<INPUT  class="Button5" style="WIDTH: 120px; HEIGHT: 20px" id="INPUT5" onClick="javascript:Validar();" type="button" value="Grabar y Finalizar" name="cmb_grabar">
		  <INPUT name="cmd_salir" onClick="javascript:Cerrar();" type="button" id="cmd_salir" value="Cerrar" class="Button5" style="WIDTH: 80px; HEIGHT: 20px">
                
            </TD>
        </TR>
        </TBODY>
	<SCRIPT type=text/javascript>
Calendar.setup({
	inputField     :    "fecha_registro",      // id del campo de texto
	ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
	button         :    "lanzador" 
	   // el id del botón que lanzará el calendario
});
</SCRIPT>

    </TABLE>
</FORM>
</BODY>


</HTML>
 