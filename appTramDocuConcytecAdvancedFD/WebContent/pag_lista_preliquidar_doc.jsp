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
		<title>Pre-Cerrar Documentos</title>
		
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
			cambiar_color_tabla("b", "tablaBackgray")
			with(document.forms[0]){						
				if(trim(tipo.value) == "editar"){
				
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
				observacion.readOnly = true;
				
				codigo_tipo.value = '';
				cod_oficina_rem.value = '';
				cod_ruta.value = '';
				cod_modalidad.value = '';
				descripcion.value = '';	
				
				btnEnviar.style.display = '';
				btnGrabar.style.display = 'none';
				//btnCancelar.style.display = 'none';		
				//dpto.style.display = 'none';
				//users.focus();
				}
				else{
					//dpto.style.display = 'none';
					
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
				
					fecha.value = '';
					hora.value = '';
					observacion.value = '';
					asunto_documento.value = '';
					
					codigo_tipo.value = '';
					cod_oficina_rem.value = '';
					cod_ruta.value = '';
					cod_modalidad.value = '';
					descripcion.value = '';
					
				
					btnEnviar.style.display = 'none';
					btnGrabar.style.display = 'none';
					btnCancelar.style.display = 'none';
					
					fecha.focus();
				}
			}
		}
		function fn_grabar(){
			with(document.forms[0]){
		
				if(trim(fecha.value) == ""){
					alert("Se requiere un valor");
					fecha.focus();
					return;
				}
				
				if(trim(hora.value) == ""){
					alert("Seleccionar");
					hora.focus();
					return;
				}
					
				vaction = action;
				action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
		    //target = 'mainFrame';
				target = '';
				submit();
				//action = vaction;			
			}
		}
		
		function selectAll(combo, status){
			for(i=0; i<combo.options.length; i++){
				combo.options[i].selected = status;
			}
		}
		
		function cancel(){
			with(document.forms[0]){
				tipo.value = 'nuevo';	
				
				codigo_documento.value = '';
				codigo_expediente.value = '';
				descripcion_tipo.value = '';
				numero_documento.value = '';
				
				fecha.value = '';
				hora.value = '';
				observacion.value = '';
				asunto_documento.value = '';
				
				codigo_tipo.value = '';
				cod_oficina_rem.value = '';
				cod_ruta.value = '';
				cod_modalidad.value = '';
				descripcion.value = '';
				//usuario.readOnly = false;
				
				//removeElements(perfilSeleccionados);
				btnEnviar.style.display = 'none';						
				fecha.focus();
			}
		}
		
		
		
		function fn_enviar(){
			if(confirm("Está seguro de preliquidar este documento?")){
				with(document.forms[0]){				
					//agregado
					vaction = action;
					action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			    tipo.value = 'enviar';	
			    //target = 'mainFrame';
				target = '';
					submit();
					//action = vaction;				
				}
			}
		}
		
		function fn_editar(pcodigo_documento,pcodigo_expediente,pdescripcion_tipo,pnumero_documento,pcodigo_oficina,pestado_movimiento,pfecha_movimiento,phora_movimiento,pobserva_movimiento,psecuencia_movimiento,pasunto_documento){
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
				observacion.value = pobserva_movimiento;
				//codigo_recepcion.value = pcodigo_recepcion;
				secuencia_movimiento.value = psecuencia_movimiento;
				asunto_documento.value = pasunto_documento;
				//alert(pobserva_registro);
				
				/*if(pobserva_registro=="null"){
					pobserva_registro="";
					observacion.value = pobserva_registro;
					//alert(pobserva_registro);
				}*/
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
		<html:form action="/MantPreliquidar" method="POST">
			<html:hidden property="tipo" />
			<html:hidden property="codigo_oficina" />
			<html:hidden property="estado_movimiento" />
			<html:hidden property="descripcion_tipo"  />
			<html:hidden property="codigo_recepcion"  />
			<html:hidden property="secuencia_movimiento"  />
			<html:hidden property="asunto_documento"  />
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
							  <tr><td width="1228" height="26" align="LEFT" valign="middle" class="label1">PRE-CERRAR DOCUMENTOS</td>
							</tr></table>
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							<tr>
								<td width="8" height="10" style="height: 10px;">								</td>
								<td width="88" align="left">								</td>
								<td width="8">								</td>
								<td width="223">								</td>
								<td width="148"></td>
								<td width="458"></td>
								<td width="13">								</td>
							</tr>
							
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									N&deg; Registro:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
							  <td align="left" valign="middle" >
				                <html:text property="codigo_documento" style="width: 150px;"  styleClass="caja"/>							  </td>
								<td align="left" valign="middle" class="label"> N&deg; Expediente:	</td>
								<td align="left" valign="middle"><html:text property="codigo_expediente" style="width: 150px;" styleClass="caja"/></td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									N&deg; Doc.:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td align="left" valign="middle">
						
						<html:text property="numero_documento" style="width: 260px;" styleClass="caja" />							  </td>
								<td align="left" valign="middle" class="label">Fecha:</td>
								<td align="left" valign="middle">
								 <html:text property="fecha" style="width: 80px;" maxlength="11" styleClass="caja" 
										onkeypress="validarCaracterFecha(this);"  />			
										  &nbsp; <a href="" id="lanzador"><img  src="img/cal.gif" alt="Fecha (dd/mm/yyyy)" border="0"></a> 
				   &nbsp;dd/mm/yyyy				 	</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									Hora:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
							  <td align="left" valign="middle" >
			                    <html:text property="hora" style="width: 80px;" maxlength="15" styleClass="caja" />							  </td>
								<td class="label">Observaci&oacute;n Derivaci&oacute;n:</td>
								<td class="label">
								<html:textarea property="observacion" style="WIDTH: 300px; HEIGHT: 72px;"  styleClass="caja"/></td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
                            <tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									Tipo Doc.:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
							  <td align="left" valign="middle" >
			                   	  <html:select property="codigo_tipo" styleClass="caja1" >														
								<logic:present name="rs_tipodoc">
									<html:options collection="rs_tipodoc" property="codigo_tipo" labelProperty=		 		 								"descripcion_tipo"/>
								</logic:present>
							</html:select>					  </td>
								<td class="label">Ruta:</td>
								<td align="left" valign="middle" class="label">
								<select id="cod_ruta" name="cod_ruta" class="caja1" style="WIDTH: 100; HEIGHT: 21px">
								<OPTION value="0" selected> -- Selecc Uno -- </OPTION>
								<c:choose> <c:when test='${not empty rs_ruta}'>
								 <c:forEach items='${rs_ruta}' var='pa'>
									<option value=<c:out value='${pa.cod_ruta}'/>> 
									<c:out value='${pa.nombre_ruta}'/>
									 </option> 
								  </c:forEach> 
								</c:when> </c:choose> 
							  </select> 
							  &nbsp;&nbsp;Modalidad: &nbsp;&nbsp; 
							  <select id="cod_modalidad" name="cod_modalidad" class="caja1" >
								<option value="0">-- Selecc Uno --</option>
							  </select>							 </td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
                           <tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									&Aacute;rea Remitente:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
							  <td align="left" valign="middle" >
			                   	  <html:select property="cod_oficina_rem" styleClass="caja1" >														
								<logic:present name="rs_oficina">
									<html:options collection="rs_oficina" property="codigo_oficina" labelProperty=		 		 								"siglas_oficina"/>
								</logic:present>
							</html:select>					  </td>
								<td class="label">Descripci&oacute;n:	</td>
								<td align="left" valign="middle" class="label">
									<html:textarea property="descripcion" style="WIDTH: 300px; HEIGHT: 72px;"  styleClass="caja"/>					 </td>
								<td style="width: 10px;">&nbsp;								</td>
						</tr>
 
							
						
							<tr>
								<td height="31" colspan="7" align="center">
									<table>
										<tr>
											<td>
											<html:button  property="btnEnviar" value="Preliquidar" onclick="fn_enviar();"
													style="display: none; width: 90;" styleClass="boton"/>										  </td>
											<td>
												<html:button property="btnCancelar" value="Cancelar" onclick="cancel();" style="width: 90;" styleClass="boton"/>											</td>
											<td>
											<html:button property="btnGrabar" value="Grabar" onclick="fn_grabar();" style="width: 90;" styleClass="boton"/>										  </td>
										</tr>
									</table>								</td>
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
				  <ajax:select baseUrl="${pageContext.request.contextPath}/obtenerLista.do" parameters="cambia=ruta&cod_ruta={cod_ruta}" source="cod_ruta" target="cod_modalidad"  />
				  <SCRIPT type=text/javascript>
					Calendar.setup({
						inputField     :    "fecha",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador" 
						   // el id del botón que lanzará el calendario
					});
					
					
					//fecha();
					
				</SCRIPT>
					<td>
						<table width="100%" align="center" cellpadding="0" cellspacing="0" >
						<tr>
								<td align="center" valign="middle" background="img/fondoplomo8.jpg" class="label" height="25">
									Documentos Pendientes por Preliquidar								</td>
						  </tr>
							<tr>
								<td>
									
									<display:table name="sessionScope.rs_recepcion_doc" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										
										<display:column property="codigo_documento" title="N&deg; Registro" />
										<display:column property="codigo_expediente" title="N&deg; Expediente" />
										<display:column property="fecha_movimiento" media="html" title="Fecha Registro" />
										<display:column property="numero_documento" title="N&deg;  Documento" />
										<display:column property="oficina_origen" title="Oficina Origen" />
										<display:column property="oficina_deriva" title="Oficina Destino" />
										<display:column property="estado_movimiento" title="Estado" />
										
									</display:table>
								</td>
							</tr>
					  </table>
					</td>
				</tr>
		  </table>
		
		</html:form>
	</body>
</html>
<% } %>
