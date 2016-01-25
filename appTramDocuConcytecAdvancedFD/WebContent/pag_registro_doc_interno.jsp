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
function HabilitaCombo(){
   if (document.frmRegDocumento.chk_copia.checked) {

        document.frmRegDocumento.cbo_copia.disabled = false;
   }
   else{

    document.frmRegDocumento.cbo_copia.disabled = true; 
   }
}

function Refrescar_ComboSerie()
{

	var serie  = document.getElementById("cboSerie").value
	var strURL = "pag_lista_expedientes.jsp";
			strURL += "?cboSerie="+serie;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
     
	document.getElementById("ifra_listaexpedientes").Document.location=strURL

}
	
function EstablecerFoco(){
  document.frmRegDocumento.sle_numero.focus();
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
			   if (Validarcampovacio(document.form_datos.hora.value) == false)
              {              
                    alert("Ingresar Hora");
                    document.form_datos.hora.focus();
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.folios_documento.value) == false)
              {              
                    alert("Ingresar Folios del Documento");
                    document.form_datos.folios_documento.focus();
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.asunto_documento.value) == false)
              {              
                    alert("Ingresar Asunto del Documento");
                    document.form_datos.asunto_documento.focus();
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.firmadopor.value) == false)
              {              
                    alert("Ingresar Persona  que Firma Documento");
                    document.form_datos.firmadopor.focus();
                    return false;           
              } 
			 
			    
				 if(document.form_datos.medio.value=="0"){
						  alert("Ingresar  Medio");
				document.form_datos.medio.focus();
				return false;
				}
				
				 if(document.form_datos.codigo_tipo.value=="0"){
						  alert("Ingresar  Codigo Tipo");
				document.form_datos.codigo_tipo.focus();
				return false;
				}
				 if(document.form_datos.codigo_oficina.value=="0"){
						  alert("Ingresar  Codigo Oficina");
				document.form_datos.codigo_oficina.focus();
				return false;
				}
				 if(document.form_datos.destinatario.value=="0"){
						  alert("Ingresar  Destinatario");
				document.form_datos.destinatario.focus();
				return false;
				}
				 if(document.form_datos.codigo_solicitud.value=="0"){
						  alert("Ingresar  Codigo Solicitud");
				document.form_datos.codigo_solicitud.focus();
				return false;
				}
				 if(document.form_datos.codigo_personal.value=="0"){
						  alert("Ingresar Remitente");
				document.form_datos.codigo_personal.focus();
				return false;
				}
				
			
    medio = document.getElementById("medio").value
	codigo_tipo = document.getElementById("codigo_tipo").value
	destinatario = document.getElementById("destinatario").value
	codigo_solicitud = document.getElementById("codigo_solicitud").value
	numero_documento = document.getElementById("numero_documento").value
	fecha_registro = document.getElementById("fecha_registro").value
	hora = document.getElementById("hora").value
	folios_documento = document.getElementById("folios_documento").value
	asunto_documento = document.getElementById("asunto_documento").value
	observa_documento = document.getElementById("observa_documento").value
	firmadopor = document.getElementById("firmadopor").value
	codigo_oficina = document.getElementById("codigo_oficina").value
	codigo_personal = document.getElementById("codigo_personal").value
	desc_origen = document.getElementById("nombre_oficina").value
	fecha_rpta = document.getElementById("fecha_rpta").value
		
		
		var strURL = "MantDocumento.do?operacion=N";
	
			strURL += "&medio="+medio;
			strURL += "&codigo_tipo="+codigo_tipo;
			strURL += "&codigo_oficina="+codigo_oficina;
			strURL += "&destinatario="+destinatario ;
			strURL += "&codigo_solicitud="+codigo_solicitud ;
			strURL += "&numero_documento="+numero_documento;
      		strURL += "&fecha_registro="+fecha_registro;
			strURL += "&hora="+hora ;
			strURL += "&folios_documento="+folios_documento;
			strURL += "&asunto_documento="+asunto_documento;
			strURL += "&observa_documento="+observa_documento;
			strURL += "&firmadopor="+firmadopor;
			strURL += "&codigo_personal="+codigo_personal;
			strURL += "&desc_origen="+desc_origen;
			strURL += "&fecha_rpta="+fecha_rpta;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	window.close()
}

function HabilitaCombo(){
   if (document.form_datos.chk_copia.checked) {

        document.form_datos.cbo_copia.disabled = false;
   }
   else{

    document.form_datos.cbo_copia.disabled = true; 
   }
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
	
function HabilitaText()
{
if (document.form_datos.cbo_fecharpta.checked)
{ document.form_datos.fecha_rpta.disabled = false; 
}
else
{document.form_datos.fecha_rpta.disabled = true;

}
}
</SCRIPT>

<HTML>
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Registro de un Documento Interno</TITLE>
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
<BODY bgColor="white" PSPARAMS="">

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
              <TR id="TR1"> 
                <TD id="TD1" colSpan="4"> <P align="center"> <STRONG>&nbsp;&nbsp; 
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </STRONG>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    &nbsp; </P></TD>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD width="81" align="right" id="TD5" class="label">Medio:</TD>
                <TD width="321" align="left" i> <SELECT id="medio" name="medio" class="caja" style="width:100" value=''>
                    <option value="0">--Selec--</option>
                    <option value="OR">Original</option>
                    <option value="FA">Fax </option>
                    <option value="CO">Copia Inf.</option>
                    <option value="EM">Emali</option>
                  </SELECT> </TD>
                <TD width="100" align="right" id="TD7" class="label">Origen :</TD>
                <TD width="270" align="left" id="TD8"> <INPUT class="txt" id="nombre_oficina" style="WIDTH: 250px; HEIGHT: 21px" maxLength="50" size="47" readonly value="<c:out value='${nombre_oficina}'/>" name="txt_oficina"> 
                </TD>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD align="right" id="TD5" class="label">Tipo Doc:</TD>
                <TD align="left" i>  <select id="codigo_tipo" name="codigo_tipo" class="caja" >
                <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                <c:choose> <c:when test='${not empty rs_tipodoc_todos}'> <c:forEach items='${rs_tipodoc_todos}' var='pa'>	<option value=<c:out value='${pa.codigo_tipo}'/>> 
                  <c:out value='${pa.descripcion_tipo}'/> </option> </c:forEach> 
                  </c:when> </c:choose> 
              </select>  </TD>
                <TD align="right" id="TD7" class="label">Firmado por:</TD>
                <TD id="TD8" align="left"> <INPUT class="txt" id="firmadopor" maxLength="40" size="40" name="firmadopor" > 
                </TD>
              </TR>
              <TR class="label" id="TR3"> 
                <TD align="right" id="TD9" class="label">N&uacute;mero Doc.:</TD>
                <TD align="left"> <INPUT class="txt" id="numero_documento" maxLength="40" size="40" name="numero_documento"> 
                </TD>
                <TD align="right" id="TD11" class="label">Remitente</TD>
                <TD align="left"> <select id="codigo_personal" name="codigo_personal" class="caja" style="WIDTH: 250px; HEIGHT: 21px">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_personas_nuestraoficina}'> 
                    <c:forEach items='${rs_personas_nuestraoficina}' var='pa'> <option value=<c:out value='${pa.codigo_personal}'/>> 
                    <c:out value='${pa.nombre_personal}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select> </TD>
              </TR>
              <TR class="formulario" id="TR4"> 
                <TD align="right" class="label" id="TD13">Fecha Doc.:</TD>
                <TD id="TD14" align="left"> <INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_registro"  maxLength="10" size="11" name="fecha_registro"> 
                  &nbsp; <a href="" id="lanzador"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a> 
				  &nbsp;dd/mm/yyyy
                </TD>
                <TD align="right" id="TD15" class="label">Destino :</TD>
                <TD id="TD16" align="left"> <select id="codigo_oficina" name="codigo_oficina" class="caja" style="WIDTH: 250px; HEIGHT: 21px">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.descripcion_oficina}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select> </TD>
              </TR>
              <TR class="label" id="TR5"> 
                <TD align="right" id="TD17" class="label">Hora:</TD>
                <TD id="TD18" align="left"> <INPUT language="JavaScript" class="txt"  id="hora" maxLength="8" size="9" name="hora"> 
                  &nbsp;00:00 </TD>
                <TD align="right" id="TD19" class="label">Destinatario :</TD>
                <TD id="TD20" align="left"> <select id="personas" name="destinatario" class="caja" >
                    <option value="0">:: Personas ::</option>
                  </select> </TD>
              </TR>
              <TR class="formulario" id="TR5"> 
                <TD align="right" id="TD17" class="label">Folios</TD>
                <TD id="TD18" align="left"> <INPUT class="txt" id="folios_documento" maxLength="3" size="3" name="folios_documento"> 
                </TD>
                <TD align="right" id="TD19" class="label">Solicitud :</TD>
                <TD id="TD20" align="left"> <select id="codigo_solicitud" name="codigo_solicitud" class="caja" style="WIDTH: 250px; HEIGHT: 21px">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_solicitud}'> <c:forEach items='${rs_solicitud}' var='pa'>	<option value=<c:out value='${pa.codigo_solicitud}'/>> 
                    <c:out value='${pa.nombre_solicitud}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select> </TD>
              </TR>
              <TR class="label" id="TR6"> 
                <TD rowspan="3" align="right" class="label">Asunto:</TD>
                <TD rowspan="3" class="formulario" id="TD21"> <TEXTAREA id="asunto_documento" style="WIDTH: 300px; HEIGHT: 60px" name="asunto_documento" rows="4" cols="71"></TEXTAREA> 
                </TD>
                <TD height="22" colspan="2" align="left" valign="middle" class="label"> &nbsp;&nbsp;&nbsp;Requiere 
                  Prioridad 
                  <input language="JavaScript" type="checkbox" name="cbo_fecharpta" id="cbo_fecharpta" value="checkbox" onClick="javascript:HabilitaText();"> 
				 </TD>
              </TR>
              <TR class="formulario" id="TR6"> 
                <TD height="22" align="right" valign="middle" class="label">Fecha Rpta:</TD>
                <TD valign="top">
				<INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta"  maxLength="10" size="11" name="fecha_rpta"> 
                  &nbsp; <a href="" id="lanzador1"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a> 
				</TD>
              </TR>
              <TR class="formulario" id="TR6"> 
                <TD height="14"></TD>
                <TD valign="top"></TD>
              </TR>
              <TR class="formulario"> 
                <TD align="right" class="label"> <DIV id="divTexto1" style="visibility: visible;">Observaci&oacute;n:</DIV></TD>
                <TD align="left"><DIV id="divCopia" style="visibility: visible;"> 
                    <TEXTAREA id="observa_documento" style="WIDTH: 300px; HEIGHT: 60px" name="observa_documento" rows="4" cols="50"></TEXTAREA>
                  </DIV></TD>
                <TD align="right" class="formulario"> </TD>
                <TD> </TD>
              </TR>
            </TBODY>
          </TABLE>
            </TD>
        </TR>
        </TBODY>
    </TABLE>
	<ajax:select baseUrl="${pageContext.request.contextPath}/obtenerLista.do" parameters="cambia=ofi&codigo_oficina={codigo_oficina}" source="codigo_oficina" target="personas"  />
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

Calendar.setup({
        inputField     :    "fecha_rpta",      // id del campo de texto
        ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
        button         :    "lanzador1" 
		   // el id del botón que lanzará el calendario
    });
	
</SCRIPT>
<SCRIPT language="Javascript">
inicio();
function inicio(){
   document.form_datos.fecha_rpta.disabled = "true"; 
};
</SCRIPT>
    </TABLE>
</FORM>
</BODY>


</HTML>
 