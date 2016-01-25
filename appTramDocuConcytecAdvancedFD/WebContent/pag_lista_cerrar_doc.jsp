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
		<title>Cerrar Documentos</title>
		
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
		
		function fn_onLoad(){
		
		}
		function selectAll(combo, status){
			for(i=0; i<combo.options.length; i++){
				combo.options[i].selected = status;
			}
		}
		
		function cancel(){
			with(document.forms[0]){
					
				
				fecha_recepcion.value = '';
				codigo_guia_courier_recep.value = '';
				btnGuardar.style.display = 'none';						
				
				
			}
		}
		
		function ver_detalle(id_paquete){
		//alert('ingreso');
		with(document.forms[0]){
		codigo_cierre.value = id_paquete;
		tipo.value = "ver_detalle";
		submit();
		}}
		//location="MantCerrar.do?tipo=ver_detalle&id_cierre="+id_paquete;
		
		
		function fn_enviar(){
			
				with(document.forms[0]){				
				
				if(fecha_recepcion.value == ''){
				alert('Falta Elemento');
				fecha_recepcion.focus();
				return;				
				}
				
				if(codigo_guia_courier_recep.value == ''){
				alert('Falta Elemento');
				codigo_guia_courier_recep.focus();
				return;				
				}
			
			if(confirm("Está seguro de Cerrar este documento?")){	
			    tipo.value = 'guardar';	
			    
					submit();
					//action = vaction;				
				}
			}
		}
		
		function fn_editar(pcodigo_documento
		,pcodigo_expediente
		,pdescripcion_tipo,pnumero_documento
		,pcodigo_oficina,pestado_movimiento,pfecha_movimiento,phora_movimiento,pobserva_registro,pcodigo_recepcion,psecuencia_movimiento){
			with(document.forms[0]){
			
			
				codigo_documento.value = pcodigo_documento;
				codigo_expediente.value = pcodigo_expediente;
				descripcion_tipo.value = pdescripcion_tipo;
				numero_documento.value = pnumero_documento;
				/*fecha.value = pfecha_def;
				hora.value = phora_def;*/
				/*fecha.value = pfecha;
				hora.value = phora;
				observacion.value = pobservacion;*/
				
				codigo_oficina.value = pcodigo_oficina;
				estado_movimiento.value = pestado_movimiento;
				fecha.value = pfecha_movimiento;
				hora.value = phora_movimiento;
				observacion.value = pobserva_registro;
				codigo_recepcion.value = pcodigo_recepcion;
				secuencia_movimiento.value = psecuencia_movimiento;
				//alert(pobserva_registro);
				
				if(pobserva_registro=="null"){
					pobserva_registro="";
					observacion.value = pobserva_registro;
					//alert(pobserva_registro);
				}
				//alert(pobserva_registro);
				/*alert(codigo_oficina);
				alert(estado_movimiento);
				alert(codigo_documento);
				alert(codigo_expediente);*/
				
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
				
				btnEnviar.style.display = '';	
					
				//agregado
			  vaction = action;
				action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
				//alert(action);
				target = '';
				//alert(target);
				tipo.value = 'editar';	
				//alert(tipo);	
				submit();
			}		
		}
		
		
		
		
		function SeleccionarCampo(campo, valor){
		  for(var i = 0; i < document.forms[0].elements.length; i++){
			if (document.forms[0].elements[i].name==campo){
			  for(var j=0; j< document.forms[0].elements[i].length; j++){      
				if (document.forms[0].elements[i].options[j].value==valor){
				  document.forms[0].elements[i].options[j].selected=true;
				}
			  }
			}    
		  }
		}
		
		function fecha()
		{
				var mifecha
				var mydate=new Date();
				var year=mydate.getYear();
				if (year < 1000)
				year+=1900;
				var day=mydate.getDay();
				var month=mydate.getMonth()+1;
				if (month<10)
				month="0"+month;
				var daym=mydate.getDate();
				if (daym<10)
				daym="0"+daym;
				mifecha=daym+"/"+month+"/"+year;
				//alert(mifecha);
				//document.form_datos.fecha_doc.value= mifecha;
				
				
				with(document.forms[0]){
				fecha.value = mifecha;
				//alert(mifecha);
				/*fechaProceso.disabled = true;
				imgFechaProceso.style.display = 'none';*/
				}
				//document.form1.FechaActualizacion.value = mifecha; 
		}

	function listadocumentos()
		{
		
		var strURL = "ListaDocumentos.do?tipo=modificar&operacion=M";
				
		 location=strURL;
		
		return;
		}
		
</script>
	</head>
	<body topmargin="0" leftmargin="0" onLoad="fn_onLoad();">
		
			<table width="100%" cellspacing="0" cellpadding="0"  align="left">
			<tr>
			  <td height="50" align="left" valign="middle" style="width:100%">
						<table width="100%" height="50" cellspacing="0" cellpadding="0"  align="left">
						<tr><td bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></td></tr></table>
			  </td>
			  </tr>
				<tr>
					<td class="label" align="center" style="width:100%" background="img/fondoplomo8.jpg" height="26">
							<table width="100%" cellpadding="0" cellspacing="0">
							  <!--DWLayoutTable-->
							  <tr><td width="1228" height="26" align="LEFT" valign="middle" class="label1">CERRAR DOCUMENTOS</td>
							</tr></table>
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
				  <html:form action="/MantCerrar" method="POST">
				  <html:hidden property="tipo"/><html:hidden property="codigo_cierre"/>
				  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							
							<tr>
								<td width="9" height="23" style="width: 10px;">&nbsp;								</td>
								<td width="89" align="left" class="label" style="width: 90px;"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td width="150" align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td width="455" align="left" valign="middle"></td>
								<td width="121" align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td width="365" align="left" valign="middle"> </td>
								<td width="13" style="width: 10px;">&nbsp;								</td>
							</tr>
							
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td class="label">&nbsp;Fecha de Envio</td>
						      <td class="label">
						      
							    <input type="text"  size="12" readonly="readonly" value="<c:out value="${FFormMantCerrar.fecha_envio}"/>">
							    <td class="label">&nbsp;Tipo Servicio:</td>
					          <td align="left" valign="middle" class="label">
						        <input type="text" readonly="readonly" size="50" value="<c:out value="${FFormMantCerrar.tipo_servicio}"/>"/> </td>
						        <td style="width: 10px;">&nbsp;								</td>
							</tr>
                            <tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td class="label">&nbsp;Destinatario:</td>
							  <td align="left" valign="middle" class="label">
							  <input type="text"  readonly="readonly" size="75" value="<c:out value="${FFormMantCerrar.nombre_destinatario}"/>"/> </td>
								<td class="label">&nbsp;Courier:</td>
						      <td align="left" valign="middle" class="label">
						      <input type="text"  size="50" readonly="readonly" value="<c:out value="${FFormMantCerrar.nombre_courier}"/>"> </td>
							    <td style="width: 10px;">&nbsp;								</td>
							</tr>
                            <tr>
                              <td height="23" style="width: 10px;">&nbsp;</td>
                              <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td class="label">&nbsp;Remitente:</td>
                              <td align="left" valign="middle" class="label">
                              <input type="text"  size="75" readonly="readonly" value="<c:out value="${FFormMantCerrar.nombre_remitente}"/>"/> </td>
                              <td class="label">&nbsp;Codigo de Guia de courier:</td>
                              <td align="left" valign="middle" class="label">
                              <input type="text" readonly="readonly" value="<c:out value="${FFormMantCerrar.codigo_guia_courier_recep}"/>">
                              </td>
                              <td style="width: 10px;">&nbsp;</td>
                            </tr>
                            <tr>
                              <td height="23" style="width: 10px;">&nbsp;</td>
                              <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td class="label">Objeto del Envio:</td>
                              <td align="left" valign="middle" class="label">
                              <textarea rows="4" cols="56" readonly="readonly"><c:out value="${FFormMantCerrar.objeto_envio}"/></textarea> 
                              
                              </td>
                              <td class="label">Fecha de recepci&oacute;n:</td>
                              <td align="left" valign="middle" class="label"><html:text property="fecha_recepcion" style="width: 80px;" maxlength="11" styleClass="caja" 
										onkeypress="validarCaracterFecha(this);"  />			
										  &nbsp; <a href="" id="lanzador"><img  src="img/cal.gif" alt="Fecha (dd/mm/yyyy)" border="0"></a> 
				   &nbsp;dd/mm/yyyy	</td>
                              <td style="width: 10px;">&nbsp;</td>
                            </tr>
                            <tr>
                              <td height="23" style="width: 10px;">&nbsp;</td>
                              <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td class="label">Codigo de Guia de Recepci&oacute;n: </td>
                              <td align="left" valign="middle" class="label"><html:text property="codigo_guia_courier_recep" style="width: 80px;" maxlength="11" styleClass="caja" 
										onkeypress="validarCaracterFecha(this);"  /></td>
                              <td style="width: 10px;">&nbsp;</td>
                            </tr>
                            <tr>
                              <td height="23" style="width: 10px;">&nbsp;</td>
                              <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td style="width: 10px;">&nbsp;</td>
                            </tr>
                           <tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
							 <td align="left" valign="middle" class="label"> </td>
								<td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td style="width: 10px;">&nbsp;								</td>
						</tr>
 
							
						
							<tr>
							  <td height="31" colspan="7" align="center">
							  <html:button property="btnGuardar" value="Aceptar"  onclick="fn_enviar();" style=" width: 90;" styleClass="boton"/>
							  <html:button property="btnCancelar" value="Cancelar" onclick="cancel();" style="width: 90;" styleClass="boton"/>
							  </td>
							</tr>
							<tr>
								<td height="10" style="height: 10px;">								</td>
								<td align="left">								</td>
								<td>								</td>
								<td></td>
								<td></td>
								<td></td>
								<td>								</td>
							</tr>
					  </table>
				  </html:form>
				  </td>
				</tr>
				<tr>
				  <SCRIPT type=text/javascript>
				  Calendar.setup({
						inputField     :    "fecha_recepcion",      // id del campo de texto
						ifFormat       :    "%Y-%m-%d",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador" 
						   // el id del botón que lanzará el calendario
					});
				</SCRIPT>
					<td>
						<table width="100%" align="center" cellpadding="0" cellspacing="0" >
						<tr>
								<td align="center" valign="middle" background="img/fondoplomo8.jpg" class="label" height="25">Documentos Pendientes Por Cerrar </td>
						  </tr>
							<tr>
								<td>
									<display:table name="sessionScope.listado_pendientes" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										

										<display:column property="fecha_envio" title="Fecha Envio" />
										
										<display:column property="courier" title="Courier Envio" />
										<display:column property="remitente" title="Remitente" />
										<display:column property="destinatario" title="Destinatario" />
										<display:column property="tipo_servicio" title="Tipo Servicio" />										
										
									</display:table>
								</td>
							</tr>
					  </table>
					</td>
				</tr>
		  </table>
		
		
	</body>
</html>
<% } %>
