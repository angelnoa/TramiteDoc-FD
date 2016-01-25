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
		<title>Mantenimiento de movimientos</title>
		
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
		
		function anular(codigo_doc,secuencia_mov){
			if(confirm('¿Esta seguro que desea anular este movimiento?')){
			
			with(document.forms[0]){
			  
			  opcion.value = 'anular';
			  codigo_documento.value = codigo_doc;
			  secuencia_movimiento.value = secuencia_mov;
			  submit();
			}
					
			}
		}
		
		function editar(codigo_doc,secuencia_mov){
		}
		
		function MostrarAdjunto(codigo){
			
		}
		function AgregarArchivos(){
   
			var codigo_documento = document.forms[0].codigo_documento.value;
				var strURL='UploadArchivo.do?codigo_documento='+codigo_documento+'&opcion=default';	
				winPopup = window.open(strURL,"","scrollbars,HEIGHT=250,WIDTH=580")
				
		}
		function regresar(codigo_documento){
		var url = 'Reiterativo.do';		
		window.location = url;
		}
		
</script>
	</head>
	<body topmargin="0" leftmargin="0">
	
		<html:form action="ReiterativoMantenimiento">
		<html:hidden property="opcion"/>
		<html:hidden property="secuencia_movimiento"/>
<table width="100%" cellspacing="0" cellpadding="0"  align="left">
			  <!--DWLayoutTable-->
			<tr>
			  <td height="50" align="left" valign="middle" style="width:100%">
						<table width="100%" height="50" cellspacing="0" cellpadding="0"  align="left">
						<tr><td bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></td></tr></table>			  </td>
			  </tr>
				<tr>
					<td class="label1" align="LEFT" style="width:100%" background="img/fondoplomo8.jpg" height="26">MANTENIMIENTO DE  MOVIMIENTO </td>
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
    <table width="100%">
    <tr>
    <td width="50"> </td>
    <td width="100">Nro. Registro</td>
    <td width="440"><input name='codigo_documento'  value="<c:out value='${FFormReiterativoMantenimiento.codigo_documento}'/>" size="25" readonly="readonly" /></td>
    <td width="131" rowspan="3">Observación del Documento</td>
    <td width="481" rowspan="3"><textarea cols="55" rows="4" readonly="readonly"><c:out value='${FFormReiterativoMantenimiento.observa_documento}'/></textarea></td>   
    </tr>
     <tr>
     <td> </td>
    <td width="100">N° Documento</td>
    <td><input readonly="readonly" value="<c:out value='${FFormReiterativoMantenimiento.numero_documento}'/>" size="55" /></td>
    </tr>
     <tr>
     <td> </td>
    <td width="100">N° Expediente</td>
    <td><input value="<c:out value='${FFormReiterativoMantenimiento.codigo_expediente}'/>" size="25" readonly="readonly" /></td>
    </tr>
    
     <tr>
     <td> </td>
    <td width="100">Procedencia</td>
    <td><input readonly="readonly" value="<c:out value='${FFormReiterativoMantenimiento.procedencia}'/>" size="65" /></td>
    
    <td>Editar Archivos </td>
    <td><input name="button" type="button" class="boton" onClick="javascript:AgregarArchivos();" value="Agregar Archivos">
      <img src="img/docadjuntos.gif" alt="Documentos Adjuntos"></td>
    </tr>
    </table>    </td>
  </tr>
  
  <tr>
    <td width="75" height="2" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td width="45%" height="2" align="left"><input type="button" value="Regresar" class="boton" onClick="javascript:regresar(<c:out value='${FFormReiterativoMantenimiento.codigo_documento}'/>);"></td>
    <td width="7%" height="2" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td width="7%" height="2" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td width="7%" height="2" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td width="7%" height="2" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td width="7%" height="2" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td width="7%" height="2" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td width="8%" height="2" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
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
									</b>								</font>							</td>
						</tr>
					</table></td>
  </tr>
  </c:if>
  <tr>
    <td height="31" colspan="9" align="center"></td>
  </tr>
</table>

<display:table id="b" name="sessionScope.listadoSecuenciasMantenimiento" pagesize="10" export="false" style="width:85%" class="simple" align="center">													 
							<display:column property="orden" sortable="true" title="N°" />			
							<display:column property="nombre_oficina_origen" sortable="true"
							title="Origen" />
							<display:column property="nombre_oficina_destino" sortable="true"
							title="Destino" />
							<display:column property="nombre_personal" sortable="true"  title="Destinatario" />
							<display:column property="estado_movimiento" sortable="true"  title="Estado" />											    
							<display:column property="observa_movimiento" sortable="true"  title="Observación" />
							<display:column property="anular" sortable="true"  title="" />
							<display:column property="editar" sortable="true"  title="" />
							
									    
			
					</display:table>
  		  </td>
				</tr>
				
		  </table>
		  </html:form>
	</body>
</html>
<% } %>



