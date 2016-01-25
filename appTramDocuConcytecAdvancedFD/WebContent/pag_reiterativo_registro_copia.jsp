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
<html>
	<head>
		<title>Registrar Movimiento</title>
		
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
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
		
		<script language="JavaScript">
		
		
</script>
	</head>
	<body topmargin="0" leftmargin="0">
	
		<html:form action="ReiterativoRegistro">
<table width="100%" cellspacing="0" cellpadding="0"  align="left">
			  <!--DWLayoutTable-->
			<tr>
			  <td height="50" align="left" valign="middle" style="width:100%">
						<table width="100%" height="50" cellspacing="0" cellpadding="0"  align="left">
						<tr><td bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></td></tr></table>			  </td>
			  </tr>
				<tr>
					<td class="label1" align="LEFT" style="width:100%" background="img/fondoplomo8.jpg" height="26">REGISTRAR MOVIMIENTO </td>
				</tr>
				<tr>
					<td class="groupcell" align="center" style="width:100%" background="img/fondoplomo8.jpg" height="26">
					</td>
				</tr>
				
				<tr>
				  <td align="center" width="100%" class="groupcell">
					 		<table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
  <!--DWLayoutTable-->
  <tr>
    <td height="2" colspan="9" align="center">
    <table align="center" cellspacing="0" cellpadding="2" width="100%"
									border="0">
									<!--DWLayoutTable-->
									<tr>
										<td width="10" height="10" style="height: 10px;"></td>
										<td width="119" align="left" class="label">Nro. de Doc.</td>
										<td width="10"></td>
										<td width="300" colspan="5"><c:out value="${FFormReiterativoRegistro.numero_documento}"/></td>
										
										
										<td width="10"></td>
									</tr>


									<tr>
										<td height="20" style="width: 10px;">&nbsp;</td>
										<td style="width: 90px;" class="label" align="left">
											N&deg; Registro:</td>
										<td style="width: 10px;" align="center" class="labelsubtitle">
										</td>
										<td align="left" valign="middle" style="width: 300px;">
										<c:out value="${FFormReiterativoRegistro.codigo_documento}"/></td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td align="left" valign="middle" class="label" style="width: 88px; ">N&deg;
											Expediente:</td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td align="left" valign="middle"><c:out value="${FFormReiterativoRegistro.codigo_expediente}"/>
										</td>
										<td style="width: 10px;">&nbsp;</td>
									</tr>

									<tr>
										<td colspan="9"><hr/></td>
										
									</tr>
									<tr>
										<td height="20" style="width: 10px;">&nbsp;</td>
										<td style="width: 90px;" class="label" align="left">
											Fecha
											Derivaci&oacute;n:</td>
										<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
										</td>
										<td align="left" valign="middle" style="width: 300px;">
										<html:text
												property="fecha" style="width: 150px;" styleClass="caja" /></td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td align="left" valign="middle" class="label"></td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td align="left" valign="middle">
										</td>
										<td style="width: 10px;">&nbsp;</td>
									</tr>
									<tr>
										<td height="20" style="width: 10px;">&nbsp;</td>
										<td style="width: 90px;" class="label" align="left">
											Oficina:</td>
										<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
										</td>
										<td align="left" valign="middle" style="width: 300px;"><select
											id="codigo_oficina" name="codigo_oficina" class="caja12"
											style="WIDTH: 300px; HEIGHT: 21px">
												<option value="0" selected>-- Selecc Uno --</option>
										</select></td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td align="left" valign="middle" class="label">Personal:</td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td align="left" valign="middle"><select id="personas"
											name="personas" class="caja12">
												<option value="0">:: Personas ::</option>
										</select></td>
										<td style="width: 10px;">&nbsp;</td>
									</tr>
									<tr>
										<td rowspan="2" style="width: 10px;">&nbsp;</td>
										<td align="left" class="label" style="width: 90px;"><span
											class="label" style="width: 90px;">Acci&oacute;n a
												Realizar: </span>
										</td>
										<td align="center" class="labelsubtitle" style="width: 10px;">&nbsp;
										</td>
										<td align="left" valign="middle" style="width: 300px;"><select
											id="codigo_motivo" name="codigo_motivo" class="caja1"
											style="WIDTH: 250px; HEIGHT: 21px">
												<OPTION value="0" selected="">-- Selecc Uno --</OPTION>
												<c:choose>
													<c:when test="${not empty rs_motivos}">
														<c:forEach items="${rs_motivos}" var="pa">
															<option value="<c:out value='${pa.codigo_motivo}'/>">
																<c:out value="${pa.descripcion_motivo}" />
															</option>
														</c:forEach>
													</c:when>
												</c:choose>
										</select>
										</td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td rowspan="5" align="left" valign="middle" class="label"><span
											class="label" style="width: 90px;">Con Copia: <input
												language="JavaScript" id="chk_copia" checked="checked"
												class="caja" onClick="javascript:HabilitaCombo();"
												type="checkbox" name="chk_copia"> </span>
										</td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td rowspan="5" align="left" valign="middle"></td>
										<td style="width: 10px;">&nbsp;</td>
									</tr>
									<tr>
										<td align="left" class="label" style="width: 90px;">Requiere
											Prioridad:</td>
										<td align="center" class="labelsubtitle" style="width: 10px;">&nbsp;</td>
										<td align="left" valign="middle" style="width: 300px;"><html:text
												property="fecha_rpta"></html:text>
										</td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td style="width: 10px;">&nbsp;</td>
									</tr>
									<tr>
										<td height="10" style="width: 10px;">&nbsp;</td>
										<td align="left" class="label" style="width: 90px;"><span
											class="label" style="width: 90px;">
												Observaci&oacute;n: </span>
										</td>
										<td>&nbsp;</td>
										<td align="left" valign="middle" style="width: 300px;"><html:textarea
												property="observacion" style="width: 300px;height: 70px;"
												styleClass="caja" /></td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td style="width: 10px;">&nbsp;</td>
									</tr>
									<tr>
										<td height="10" style="width: 10px;">&nbsp;</td>
										<td align="left" valign="middle" class="label">Doc. de
											Rpta:</td>
										<td>&nbsp;</td>
										<td align="left" valign="middle" style="width: 300px;"><input
											class="caja1" id="doc_resp" maxlength="60" size="40"
											name="doc_resp"> <input type="button" id="btnUpload"
											name="btnUpload" value="Agregar Archivos"
											onClick="AgregarArchivos()" alt="Agregar Archivos"
											style="width: 120;" styleClass="boton"></td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td style="width: 10px;">&nbsp;</td>
									</tr>
									<tr>
										<td height="20" style="width: 10px;">&nbsp;</td>
										<td align="left" valign="middle" class="label">Fecha
											Rpta.:</td>
										<td>&nbsp;</td>
										<td align="left" valign="middle" style="width: 300px;"><input
											language="JavaScript" class="caja1"
											onKeyPress="validarCaracterFecha(this);" id="fecha_rpta_rq"
											maxlength="10" size="11" name="fecha_rpta_rq"> &nbsp;
											<a href="" id="lanzador2"><img src="img/cal.gif"
												alt="Fecha de nacimiento (dd/mm/yyyy)" border="0">
										</a> &nbsp;dd/mm/yyyy</td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td align="left" valign="middle" class="label">
											<!--DWLayoutEmptyCell-->&nbsp;</td>
										<td style="width: 10px;">&nbsp;</td>
									</tr>




								</table></td>
  </tr>
  
  <tr>
    <td height="2" colspan="9" align="center"><hr /></td>
  </tr>
  <c:if test="${mensaje_usuario!=null}">
  <tr>
    <td height="2" colspan="9" align="center"><table align="center" width="100%">
						<tr>
							<td class="servicos-texto3" align="center" style="padding-bottom:12px;">
								<font color="red">
									<b>
										<c:out value="${mensaje_usuario}"></c:out>
									</b>
								</font>
								<br/>
							</td>
						</tr>
					</table></td>
  </tr>
  </c:if>
  <tr>
    <td height="31" colspan="9" align="center"><html:submit property="btn" value="Registrar"/> </td>
  </tr>
</table>

<display:table id="b" name="requestScope.FFormReiterativoRegistro.listado" pagesize="10" export="false" style="width:85%" class="simple" align="center">													 
										
										<display:column property="descripcion_oficina" sortable="true"  title="Origen" />										
									    <display:column property="descripcion_destino" sortable="true"  title="Destino" />
									    <display:column property="nombre_personal" sortable="true"  title="Destinatario" />
									    <display:column property="fecha_envio" sortable="true"  title="Fecha de Envío" />
									    <display:column property="fecha_recepcion" sortable="true"  title="Fecha de recepción" />
									    <display:column property="fecha_derivacion" sortable="true"  title="Fecha de Derivación" />
									    <display:column property="observa_movimiento" sortable="true"  title="Observación" />
									    
									
									
			
									</display:table>
  		  </td>
				</tr>
				
		  </table>
		  </html:form>
	</body>
</html>
<% } %>



