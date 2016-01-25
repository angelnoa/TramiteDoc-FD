<%@ include file="taglibs.jsp" %>

<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
	<TITLE>Ventana de Mesa de Partes</TITLE>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Last-Modified" content="0">
<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
<meta http-equiv="Pragma" content="no-cache">
     <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  
        <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
        <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">

<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>

<script type=text/javascript>
 function Buscar(){
 document.form_datos.submit();
 return;
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
<BODY bgColor="white" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" >

<TABLE  cellSpacing="0" cellPadding="0" width="100%" border="0"  height="">
  <!--DWLayoutTable-->
    <TBODY>
	<TR >
        <TD height="50" colspan="2" valign="top" bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></TD>
      </TR>
    <TR>
        <TD height="26" colspan="2" class="label" align="middle"  valign="middle"   background="img/fondoplomo8.jpg"></TD>
    </TR>
    <TR >
      <TD width="100%" height="100%" align="center">
	  <FORM id="form_datos" name="form_datos" action="ReportesGestion.do" method="get">
	  <input type="hidden" name="tipo" value="doc_pend"/>
				
	<TABLE width="80%" CELLSPACING="7px." CELLPADDING="0">
<TR>
			<td align="center" valign="middle" class="TextFieldOn35"><TABLE align="center" cellspacing="0" class="groupcell" cellpadding="0" width="65%" border="0" >
			    <!--DWLayoutTable-->
        <TR>
						<td style="height: 10px;" colspan="5">&nbsp;</td>
			    </TR>	
					
					<TR>
					  <td width="21" style="width: 10px;">&nbsp;</td>
					  <td width="418"  height="25"  align="right"  valign="middle" class="label" >Oficina:</td>
					  <td width="21" align="center" class="labelsubtitle" style="width: 10px;">&nbsp;</td>
					  <td width="750" align="left" valign="middle" style="width: 350px;"><select id="codigo_oficina" name="codigo_oficina" class="caja12" style="WIDTH: 320px; HEIGHT: 21px">
                        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                        <c:choose>
                          <c:when test='${not empty rs_oficina_reporte}'>
                            <c:forEach items='${rs_oficina_reporte}' var='pa'>
                            <c:choose>
                            <c:when test="${pa.codigo_oficina==codigo_oficina}">
                            <option selected="selected" value=<c:out value='${pa.codigo_oficina}'/> > <c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/>
                              </option>
                            </c:when>
                            <c:otherwise>
                            <option value=<c:out value='${pa.codigo_oficina}'/> > <c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/>
                              </option>
                            </c:otherwise>
                            </c:choose>
                            </c:forEach>
                          </c:when>
                        </c:choose>
                      </select></td>
					  <td width="30" style="width: 10px;">&nbsp;</td>
				  </TR>
					<TR>
						<td height="35"></td>
					    <td colspan="3"  align="center">
                        <INPUT  class="boton" style="WIDTH: 80px; HEIGHT: 20px" id="INPUT5" onClick="javascript:Buscar();" type="button" value="Buscar" name="cmb_grabar"></td>
					    <td></td>
					</TR>	
			</TABLE></td>
	  </TR>				
		
		<TR>
		  <TD width="100%" align="center" valign="middle" >
		  			  <display:table name="sessionScope.listado_docs" export="false" sort="list" id="b" pagesize="25" class="simple" style="width:100%">
										
										<display:column property="codigo_documento" media="html" title="N&deg; Registro" align="left" />
										<display:column property="numero_documento" media="html" title="N&deg;  Documento" align="right" />
										<display:column property="desc_origen" media="html" title="Procedencia" align="right" />
										<display:column property="nombre_oficina_origen" media="html" title="Origen" align="right" />
										<display:column property="nombre_oficina_destino" media="html"  title="Destino" align="right" />
										<display:column property="nombre_personal" media="html"  title="Destinatario" align="right" />
										
										<display:column property="fecha_envio" media="html"  title="Fecha de Env&iacute;o "  align="right" />
										<display:column property="fecha_recepcion" media="html"   title="Fecha de recepci&oacute;n" align="right" />
										<display:column property="fecha_derivacion" media="html"  title="Fecha de Derivaci&oacute;n" align="right" />
										<display:column property="tiempo_bandeja" media="html"  title="Tiempo en bandeja" align="right" />
										<display:column property="observa_movimiento" media="html"  title="Observaci&oacute;n" align="right" />


				</display:table></TD>
		</TR>
		
  </TABLE>

  	
</FORM>

	  </TD>
      <TD width="4">
</TD>
    </TR>
    </TBODY>
</TABLE>

</BODY>
</HTML>
