<%@ include file="taglibs.jsp" %>

<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/prototype-1.4.0.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/scriptaculous.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/overlibmws.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/ajaxtags.js"></SCRIPT>
<SCRIPT type=text/javascript>
	
	function buscar()
	{
	
 var strURL = "ValidaPaginas.do?tipo=instcontactos";
	
 	 location=strURL;
	return;
	
	/*var strURL="ValidaPaginas.do?tipo=instcontactos"

 window.open(strURL,"","HEIGHT=500,WIDTH=800,scrollbars=no")*/
	   //window.showModalDialog("ValidaPaginas.do?tipo=instcontactos", window, "dialogHeight:500px; dialogWidth:800px; center:yes; help:yes; resizable:no; status:yes;scrollbar:no");       
	}


function Validar  (){
 
 	
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
	origen_documento = document.getElementById("origen_documento").value
	codigo_oficina = document.getElementById("codigo_oficina").value
/*var ls_operacion = "N";
 alert(ls_operacion);
   //var strURL = "MantDocumento.do?operacion";
	document.form_datos.action='MantDocumento.do'+'?operacion='+ls_operacion;
	form_datos.submit();
	 alert("Despues del MantDocumento");*/
	 
	var strURL = "MantDocumento.do?operacion=N";
	 alert(strURL);
		strURL += "&medio ="+medio;
		alert(strURL);
			strURL += "&codigo_tipo="+codigo_tipo;
			alert(strURL);
			strURL += "&codigo_oficina ="+codigo_oficina;
			strURL += "&destinatario ="+destinatario ;
			strURL += "&codigo_solicitud ="+codigo_solicitud ;
			strURL += "&numero_documento ="+numero_documento;
      		strURL += "&fecha_registro="+fecha_registro;
			strURL += "&hora ="+hora ;
			strURL += "&folios_documento="+folios_documento;
			strURL += "&asunto_documento ="+asunto_documento;
			strURL += "&observa_documento ="+observa_documento;
			strURL += "&firmadopor ="+firmadopor;
			strURL += "&origen_documento="+origen_documento;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	 alert(strURL);
	window.close()
	
}

function cambiaDestino(){

  document.forms[0].provincia.selectedIndex=0;
  document.forms[0].provincia.length=1;

}
</SCRIPT>

<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
  
    <TITLE>Registro de Documento de Entrada</TITLE>
    <LINK href="PlanSec.css" type="text/css" rel="stylesheet">
		<link href="css/main.css"   rel="stylesheet" type="text/css"> 
</HEAD>
<BODY bgColor="white" PSPARAMS="">

<FORM id="form_datos" name="form_datos" action="" method="post">
    <TABLE borderColor="#e9e9e9" cellSpacing="0" cellPadding="0" width="80%" align="center" bgColor="#F0F5FB" border="1">
        <TBODY>
        <TR class="cabeceratable">
            <TD align="middle" bgcolor="265ca6"><font color="#FFFFFF" size="2" ><strong>REGISTRO DE UN DOCUMENTO DE ENTRADA</strong></font> </TD>
        </TR>
        <TR>
            <TD align="middle" width="100%">
                <P align="center">
                
          <TABLE id="TABLE1" width="100%" align="center" border="0" bgColor="#F0F5FB">
            <!--DWLayoutTable-->
            <TBODY id="TBODY1">
              <TR id="TR1"> 
                <TD id="TD1" colSpan="4"></TD>
                <td>&nbsp;</td>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD align="right" id="TD5">Medio:</TD>
                <TD align="left" i> <SELECT id="medio" name="medio" class="caja" style="width:100" value=''>
                    <option value="0">--Selec--</option>
                    <option value="OR">Original</option>
                    <option value="FA">Fax </option>
                    <option value="CO">Copia Inf.</option>
                    <option value="EM">Emali</option>
                  </SELECT> </TD>
                <TD align="right" id="TD7">Procedencia :</TD>
                <TD id="TD8" align="left"> 
				<c:choose> 
				<c:when test='${operacion == "T" }' >
                <INPUT class="txt" id="INPUT1" maxLength="40" size="40" name="sle_nombre_institucion" readonly="true" value='<c:out value='${descripcion_persona}'/>'> 
				<INPUT id="INPUT2" type="hidden" maxLength="6" size="6" name="origen_documento" value='<c:out value='${codigo}'/>'> 
				<INPUT id="INPUT3" type="hidden" maxLength="6" size="6" name="sle_tipo_persona" value='<c:out value='${tipopersona}'/>'> 
                </c:when> 
				 <c:otherwise>
				<INPUT class="txt" id="INPUT1" maxLength="40" size="40" name="sle_nombre_institucion" readonly="true" >
                  </c:otherwise>
                  </c:choose> 
                </TD>
                <TD> <A tabIndex="12" href="javascript:buscar()"><IMG style="WIDTH: 20px; HEIGHT: 14px" height="10" src="img/lupita.gif" width="10" border="0"></A></TD>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD align="right" id="TD5">Tipo Doc:</TD>
                <TD align="left" i> <select id="codigo_tipo" name="codigo_tipo" class="caja" >
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_tipodoc}'> <c:forEach items='${rs_tipodoc}' var='pa'>	<option value=<c:out value='${pa.codigo_tipo}'/>> 
                    <c:out value='${pa.descripcion_tipo}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select> </TD>
                <TD align="right" id="TD11">Tipo</TD>
                <TD align="left"> 
				<c:choose> 
				<c:when test='${operacion == "T" }' >
				<INPUT class="txt" id="naturaleza_documento" maxLength="40" size="20" name="naturaleza_documento" readonly="true" value='<c:out value='${tipopersona}'/>'> 
                   </c:when> 
				 <c:otherwise>
				 <INPUT class="txt" id="INPUT3" maxLength="40" size="20" name="tipo" readonly="true" > 
				   </c:otherwise>
                  </c:choose> 
                </TD>
                <TD>&nbsp;</TD>
              </TR>
              <TR class="formulario" id="TR3"> 
                <TD align="right" id="TD9">n&uacute;mero Doc.:</TD>
                <TD align="left"> <INPUT class="txt" id="numero_documento" maxLength="5" size="5" name="numero_documento"> 
                </TD>
                <TD align="right" id="TD11">Firmado por:</TD>
                <TD align="left"> <INPUT class="txt" id="firmadopor" maxLength="40" size="40" name="firmadopor" > 
                  <INPUT id="INPUT4" type="hidden" maxLength="6" size="6" name="sle_codigo_contacto"> 
                </TD>
                <TD>&nbsp;</TD>
              </TR>
              <TR class="formulario" id="TR4"> 
                <TD align="right" id="TD13">Fecha Doc.:</TD>
                <TD id="TD14" align="left"> <INPUT language="JavaScript" class="txt" onkeypress="validarCaracterFecha(this);" id="fecha_registro" style="WIDTH: 74px; HEIGHT: 16px" maxLength="10" size="11" name="fecha_registro"> 
                </TD>
                <TD align="right" id="TD15">Destino :</TD>
                <TD id="TD16" align="left"> <select id="codigo_oficina" name="codigo_oficina" class="caja" style="WIDTH: 250px; HEIGHT: 21px">
                    <OPTION value="0000" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_oficina}'> 
                    <c:forEach items='${rs_oficina}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.descripcion_oficina}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select> </TD>
                <td>&nbsp;</td>
              </TR>
              <TR class="formulario" id="TR4"> 
                <TD align="right" id="TD13">Hora:</TD>
                <TD id="TD14" align="left"> <INPUT language="JavaScript" class="txt"  id="hora" style="WIDTH: 50px; HEIGHT: 16px" maxLength="10" size="11" name="hora"> 
                </TD>
                <TD align="right" id="TD15">Destinatario :</TD>
                <TD id="TD16" align="left">
				 <select id="personas" name="destinatario" class="caja" >
           <option value="0">:: Personas 
                ::</option>
           </select>
					 </TD>
                <td>&nbsp;</td>
              </TR>
              <TR class="formulario" id="TR5"> 
                <TD align="right" id="TD17">Folios</TD>
                <TD id="TD18" align="left"> <INPUT class="txt" id="folios_documento" maxLength="3" size="3" name="folios_documento"> 
                </TD>
                <TD align="right" id="TD19">Solicitud :</TD>
                <TD id="TD20" align="left"> <select id="codigo_solicitud" name="codigo_solicitud" class="caja" style="WIDTH: 250px; HEIGHT: 21px">
                    <OPTION value="0000" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_solicitud}'> 
                    <c:forEach items='${rs_solicitud}' var='pa'>	<option value=<c:out value='${pa.codigo_solicitud}'/>> 
                    <c:out value='${pa.nombre_solicitud}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select> </TD>
                <td>&nbsp;</td>
              </TR>
              <TR class="formulario" id="TR6"> 
                <TD align="right">Asunto</TD>
                <TD class="formulario" id="TD21" colSpan="2"> <TEXTAREA id="asunto_documento" style="WIDTH: 300px; HEIGHT: 72px" name="asunto_documento" rows="4" cols="71"></TEXTAREA> 
                </TD>
                <TD></TD>
                <td>&nbsp;</td>
              </TR>
              <TR class="formulario" id="TR7"> 
                <TD align="right">Observaci&oacute;n</TD>
                <TD class="formulario" id="TD25" colSpan="2"> <TEXTAREA id="observa_documento" style="WIDTH: 300px; HEIGHT: 72px" name="observa_documento" rows="4" cols="50"></TEXTAREA> 
                </TD>
                <TD></TD>
                <td>&nbsp;</td>
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
          <INPUT  class="Button5" style="WIDTH: 120px; HEIGHT: 20px" id="INPUT5" onclick="javascript:Validar();" type="button" value="Grabar y Finalizar" name="cmb_grabar">
		  <INPUT name="cmd_salir" onClick="javascript:window.close();" type="button" id="cmd_salir" value="Cerrar" class="Button5" style="WIDTH: 80px; HEIGHT: 20px">
            </TD>
        </TR>
        </TBODY>
    </TABLE>
</FORM>
</BODY>
</HTML>
