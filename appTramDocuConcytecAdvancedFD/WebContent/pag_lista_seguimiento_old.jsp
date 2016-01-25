<%@ include file="taglibs.jsp" %>
<%@ page buffer = "16kb" %>
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

<html>
	<head>
		<title>Lista de Documentos</title>
		
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
        <script src="js/funciones.js" type="text/javascript"></script>
		<script  src="js/prototype-1.4.0.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/scriptaculous.js"></script>
       
        <script type="text/javascript" src="js/ajaxtags.js"></script>
		
		<script language="JavaScript" src="js/avatec.js"></script>
		<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
		<!-- librería para cargar el lenguaje deseado -->
		<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
		<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
		<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
		
		<SCRIPT type=text/javascript>


function ver_frame(){
	
	document.form_datos.action='ListaDocumentos.do?tipo=seguimiento&operacion=S';
  	document.form_datos.target='';
	document.form_datos.submit();
	
	}
	
function listar_todos(){
	
	
	var strURL = "ListaDocumentos.do?tipo=seguimiento&operacion=S";
	
 	 location=strURL;
	return;

	}
	
	function agregar(){
	
	 document.location.href="CajaChica.do?tipo=mantempleados&operacion=N";
	return;
	
	}
	
	function regresar(){
	
	 document.location.href="ValidaPaginas.do?tipo=regresar&pagina=docentrada";
	return;
	}
	
	function VerDetalleBusqueda(num_doc,fecha_doc,cod_clase_doc,operacion,apepatmatnomb,parameter){
	
	 var strURL = "CajaChica.do?tipo=seleccionarempleado"; 
			strURL += "&num_doc="+num_doc;
			strURL += "&fecha_doc="+fecha_doc;
			strURL += "&cod_clase_doc="+cod_clase_doc;
			strURL += "&apepatmatnomb="+apepatmatnomb;
			strURL += "&operacion="+operacion;
			strURL += "&parameter="+parameter;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
		alert(strURL);	
		location=strURL;
		alert(strURL);
		 window.close();
		 
	}
	
	function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray");
		
	}
	
	function fn_onUnLoad(){
		window.opener.document.forms[0].target = 'mainFrame';
	}

	function VerArchivos(ls_codigo_documento,ls_codigo_expediente,ls_codigo_recepcion){
   
				var ls_operacion = "N";
				/*var ls_codigo_documento = document.getElementById("codigo_documento").value;
				var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
				var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;*/
				//alert(ls_codigo_documento);
				
				var strURL='MantSeguimiento.do?tipo=upload'+'&operacion='+ls_operacion+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&codigo_recepcion='+ls_codigo_recepcion;	
				window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes");
				
				
		}
		
		function VerHojaEnvio(codigo_documento,codigo_expediente,codigo_recepcion){
		
		//alert(pcodigo_documento);
		//alert(pcodigo_expediente);
		
				 var strURL="ValidaPaginas.do?tipo=hojaenvio";
					strURL+="&codigo_recepcion="+codigo_recepcion;
					strURL+="&codigo_documento="+codigo_documento;
					strURL+="&codigo_expediente="+codigo_expediente;
					//window.location.href=strURL
					window.open(strURL,"","HEIGHT=440,WIDTH=770,scrollbars=yes");
	
		}
		
		
function exportar_excel(){

	//document.form_datos.action='VerDatosPatente.do?tipo=reporte_excel';
//	var strURL = "ListaDocumentos.do?tipo=seguimiento&operacion=S";
	document.form_datos.action='ListaDocumentos.do?tipo=reporte_excel';
  	document.form_datos.target='';
	document.form_datos.submit();
}

function exportar_excel2(){

	//document.form_datos.action='VerDatosPatente.do?tipo=reporte_excel';
//	var strURL = "ListaDocumentos.do?tipo=seguimiento&operacion=S";
	document.form_datos.action='generateReport.do';
  	document.form_datos.target='_blank';
	document.form_datos.submit();
}
		
</SCRIPT>
	</head>
	<body topmargin="0" leftmargin="0" onLoad="fn_onLoad();">
		<form id="form_datos" name="form_datos" method="post" action="">
		
			<table width="100%" cellspacing="0" cellpadding="0"  align="left">
			<tr>
			  <td height="50" align="left" valign="middle" style="width:100%">
						<table width="100%" height="50" cellspacing="0" cellpadding="0"  align="left">
						<tr><td bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></td></tr></table>
			  </td>
			  </tr>
				<tr>
					<td class="label" align="center" style="width:100%" background="img/fondoplomo8.jpg" height="26">
							Seguimiento de Documentos
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							
							<tr>
								<td height="31" colspan="7" align="center">
									<table width="100%" border="0" cellpadding="0" cellspacing="0" >
									  <!--DWLayoutTable-->
                       
                        <tr > 
                          <td height="20" colspan="2" align="left" valign="middle" class="label">&nbsp;&nbsp;&nbsp;&nbsp;<strong>B&uacute;squeda 
                          por :</strong> </td>
                          <td width="225">&nbsp;</td>
                          <td width="540">&nbsp;</td>
                        </tr>
						 
						 <tr> 
                          <td height="20" width="201" align="right" valign="middle" class="label">Procedencia:</td>
                          <td align="left" valign="middle" width="420">&nbsp;
						  
						  <INPUT class="caja1" maxLength="40" size="40" name="procedencia" id="procedencia"  > 
				  <script language="JavaScript" type="text/javascript">if(document.getElementById) document.getElementById('procedencia').focus();</script></td>
                          <td align="right" valign="middle" class="label" width="225">Fecha 
                  Registro:</td>
                        <td class="label" width="540">&nbsp;
                  <INPUT onKeyPress="validarCaracterFecha(this);" id="sle_fecha"  maxLength="10" size="11" name="sle_fecha"> 
                  &nbsp; <a href="" id="lanzador"><img src="img/cal.gif" alt="Fecha de Registro (dd/mm/yyyy)" border="0"></a></td>
						 </tr>
						  <tr> 
                          <td height="20" width="201" align="right" valign="middle" class="label">Fecha Inicio:</td>
                          <td align="left" valign="middle" width="420">&nbsp;
						  
						 <INPUT  onKeyPress="validarCaracterFecha(this);" id="sle_fecha_inicio"  maxLength="10" size="11" name="sle_fecha_inicio"> 
                  &nbsp; <a href="" id="lanzador1"><img src="img/cal.gif" alt="Fecha de Inicio (dd/mm/yyyy)" border="0"></a>                      </td>
                          <td align="right" valign="middle" class="label" width="225">Fecha 
                  Fin:</td>
                        <td class="label" width="540">&nbsp;
                  <INPUT   onKeyPress="validarCaracterFecha(this);" id="sle_fecha_fin"  maxLength="10" size="11" name="sle_fecha_fin"> 
                  &nbsp; <a href="" id="lanzador2"><img src="img/cal.gif" alt="Fecha de Fin (dd/mm/yyyy)" border="0"></a></td>
						 </tr>
                        <tr> 
                          <td height="20" align="right" valign="middle" class="label">Medio :</td>
                          <td align="left" valign="middle">&nbsp;
                             <select id="medio" name="medio" class="caja1" style="width:100;" >
									<option value="0">--Selec--</option>
									<option value="OR">Original</option>
									<option value="FA">Fax </option>
									<option value="CO">Copia Inf.</option>
									<option value="EM">Email</option>
							</select>                             </td>
                          <td align="right" valign="middle" class="label">Tipo 
                  Documento :</td>
                        <td align="left" valign="middle" class="label">&nbsp; <select id="codigo_tipo" name="codigo_tipo" class="caja1" >
                    <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                    <c:choose> <c:when test='${not empty listatipodoc}'> <c:forEach items='${listatipodoc}' var='pa'>	<option value=<c:out value='${pa.codigo_tipo}'/>> 
                    <c:out value='${pa.descripcion_tipo}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select></td>
                        </tr>
						 
						
						 <tr> 
                          <td height="20" align="right" valign="middle" class="label">Area/Oficinas:</td>
                          <td align="left" valign="middle">&nbsp;
						   <select id="codigo_oficina" name="codigo_oficina" class="caja12" style="WIDTH: 300px; HEIGHT: 21px">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_oficina_lista}'>
					 <c:forEach items='${rs_oficina_lista}' var='pa'>
					 	<option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.siglas_oficina}'/>  - <c:out value='${pa.descripcion_oficina}'/> 
					</option> </c:forEach> 
                    </c:when> </c:choose> </select>                           </td>
                          <td align="right" valign="middle" class="label">Solicitud :</td>
                        <td> &nbsp;&nbsp;<select id="codigo_solicitud" name="codigo_solicitud" class="caja1" style="WIDTH: 280px; HEIGHT: 21px">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty listasolicitud}'> <c:forEach items='${listasolicitud}' var='pa'>	<option value=<c:out value='${pa.codigo_solicitud}'/>> 
                    <c:out value='${pa.nombre_solicitud}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select></td>
						 </tr>
						  <tr> 
                          <td width="201" height="20" align="right" valign="middle" class="label">Firmado por:</td>
                          <td width="420" align="left" valign="middle">&nbsp;
						  <input type="text" name="firmadopor" id="firmadopor" size="20" class="caja1"   >                    </td>
                         <td align="right" valign="middle" class="label">Dirigido a:</td>
                        <td class="label">&nbsp;&nbsp;<input type="text" name="dirigido" id="dirigido" size="20" class="caja1"   ></td>
						   </tr>
						    <tr> 
                          <td width="201" height="20" align="right" valign="middle" class="label">N&deg; de Registro:</td>
                          <td width="420" align="left" valign="middle">&nbsp;
						  <input type="text" name="codigo_documento" id="codigo_documento" size="20" class="caja1"   >                    </td>
                         <td align="right" valign="middle" class="label">N&deg; de Expediente:</td>
                        <td class="label">&nbsp;&nbsp;<input type="text" name="codigo_expediente" id="codigo_expediente" size="20" class="caja1"   ></td>
						   </tr>
						   <tr> 
                          <td width="201" height="20" align="right" valign="middle" class="label">Estado:</td>
                          <td width="420" align="left" valign="middle">&nbsp;
						  <SELECT class="caja1" id="estado" style="WIDTH: 130px" name="estado">
						  
						  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
						  <c:forEach items="${estados}" var="e">
						  <OPTION value="<c:out value='${e.codigo}'/>"><c:out value='${e.descripcion}'/></OPTION>						  
						  </c:forEach>
						</SELECT>		                    </td>
                         <td align="right" valign="middle" class="label">N&deg; de Documento:</td>
                        <td>&nbsp;&nbsp;<input type="text" name="numero_documento" id="numero_documento" size="40" class="caja1"   ></td>
						   </tr>
						    <tr> 
                          <td width="201" height="20" align="right" valign="middle" class="label">Doc. de Rpta:</td>
                          <td width="420" align="left" valign="middle">&nbsp;
						  <INPUT class="caja1" maxLength="70" size="40" name="doc_resp" id="doc_resp"  >	                    </td>
                         <td align="right" valign="middle" class="label">Asunto:</td>
                        <td>&nbsp;&nbsp;<input type="text" name="asunto_documento" id="asunto_documento" size="80" class="caja1"   ></td>
						   </tr>
						    <tr> 
                          <td width="201" height="20" align="right" valign="middle" class="label">Acci&oacute;n a Realizar:</td>
                          <td width="420" align="left" valign="middle">&nbsp;
						 <select id="codigo_motivo" name="codigo_motivo" class="caja1" style="WIDTH: 250px; HEIGHT: 21px">
                        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                        <c:choose> <c:when test='${not empty rs_motivos}'> <c:forEach items='${rs_motivos}' var='pa'>	<option value=<c:out value='${pa.codigo_motivo}'/>> 
                        <c:out value='${pa.descripcion_motivo}'/> </option> </c:forEach> 
                        </c:when> </c:choose> </select>	                    </td>
                         <td align="right"><span class="label">Observacion</span></td>
                         <td>&nbsp; <input type="text" name="observacion" id="observacion" size="80" class="caja1"   ></td>
						   </tr>
						<tr> 
                          <td height="29" colspan="4" align="center" valign="middle" class="label"><input  type="submit" name="button" class="boton" value="Buscar" onClick="ver_frame()" alt="buscar"  >&nbsp;<input type="button" name="button" class="boton" value="Listar Todos" onClick="listar_todos()" alt="Listar Todos"  ></td>
                          </tr>
                    </table>
									
							  </td>
							</tr>
							<tr>
								<td height="10" style="height: 10px;">								</td>
								<td align="left">								</td>
								<td>								</td>
								<td>								</td>
								<td></td>
								<td></td>
								<td>								</td>
							</tr>
					  </table>
                   
          		
				  </td>
				</tr>
				
				<tr>
				<SCRIPT type=text/javascript>
				Calendar.setup({
					inputField     :    "sle_fecha",      // id del campo de texto
					ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
					button         :    "lanzador" 
					   // el id del botón que lanzará el calendario
				});
				
				Calendar.setup({
					inputField     :    "sle_fecha_inicio",      // id del campo de texto
					ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
					button         :    "lanzador1" 
					   // el id del botón que lanzará el calendario
				});
				
				Calendar.setup({
					inputField     :    "sle_fecha_fin",      // id del campo de texto
					ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
					button         :    "lanzador2" 
					   // el id del botón que lanzará el calendario
				});
			</SCRIPT>
				 
					<td>
						<table width="100%" align="center" cellpadding="0" cellspacing="0">
						  <!--DWLayoutTable-->
						<tr>
							<td width="50%" height="25" align="right" valign="middle" background="img/fondoplomo8.jpg" class="label">
							Lista de documentos								</td>
						  <td width="50%" align="right" valign="middle" background="img/fondoplomo8.jpg">Exportar a:&nbsp;
						   <A  href="javascript:exportar_excel2()" ><img src="img/xls.gif" border="0" height="20" alt="Exportar a Excel"></A>
						   </td>
						</tr>
						
						
							<tr>
								<td colspan="2">
										<display:table id="b" name="sessionScope.FFormMantSeguimiento.seguimientoList" requestURI="/ListaDocumentos.do?tipo=seguimiento&operacion=S" pagesize="10" export="false" style="width:100%" class="simple" > 
										<display:column property="codigo_documento" sortable="true"  title="N&deg; Registro"   />
										<display:column property="codigo_expediente" sortable="true"  title="N&deg; Expediente"  />
										<display:column property="numero_documento" sortable="true"  title="N&deg;  Documento"  />
										<display:column property="fecha_registro" sortable="true"  title="Fecha Registro"  />
										<display:column property="firmadopor" sortable="true"  title="Dependencia Origen - Firmado por"  />
									
										<display:column property="oficina_origen" sortable="true"  title="Oficina Origen"  />
										<display:column property="oficina_deriva" sortable="true"  title="Oficina Destino"  />
										<display:column property="estado_movimiento" sortable="true"  title="Estado"  />
										<display:column property="view_archivo" align="center"  title="Ver Documento" />
										<display:column property="asunto_documento" sortable="true"  title="Asunto Documento" />
										</display:table>
										</td>
							</tr>
						</table>
					</td>
				</tr>
		  </table>
		
		</FORM>
	</body>
</html>
<% } %>
