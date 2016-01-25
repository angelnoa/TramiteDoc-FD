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
		<title>Modificar Documento</title>
		
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
				descripcion_tipo.readOnly = false;
				numero_documento.readOnly = false;
				asunto_documento.readOnly = false;
				//observa_movimiento.readOnly = true;
				
				fecha_registro.readOnly = false;
				hora_registro.readOnly = false;
				procedencia.readOnly = true;
				firmadopor.readOnly = false;
				dirigido.readOnly = false;
				codigo_solicitud.readOnly = false;
				medio.readOnly = false;
				
				//btnEliminar.style.display = '';
				btnGrabar.style.display = '';
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
				asunto_documento.readOnly = true;
				//observa_movimiento.readOnly = true;
				
				fecha_registro.readOnly = true;
				hora_registro.readOnly = true;
				procedencia.readOnly = true;
				firmadopor.readOnly = true;
				dirigido.readOnly = true;
				
				codigo_solicitud.readOnly = true;
				medio.readOnly = true;
				
				//observa_movimiento.value = pobserva_movimiento;
					fecha.value = '';
					hora.value = '';
					//observacion.value = '';
					
					fecha_registro.value = '';
					hora_registro.value = '';
					procedencia.value = '';
					firmadopor.value = '';
					dirigido.value = '';
					asunto_documento.value = '';
					codigo_solicitud.value = '';
					medio.value = '';
					observa_documento.value = '';
					//asunto_documento.value = '';
				
					//btnEliminar.style.display = 'none';
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
				//observacion.value = '';
				fecha_registro.value = '';
				hora_registro.value = '';
				procedencia.value = '';
				firmadopor.value = '';
				dirigido.value = '';
				asunto_documento.value = '';
				codigo_solicitud.value = '';
				medio.value = '';
				observa_documento.value = '';
				
				//usuario.readOnly = false;
				
				//removeElements(perfilSeleccionados);
				//btnEliminar.style.display = 'none';						
				fecha.focus();
			}
		}
		
		
		
		function fn_eliminar(){
			if(confirm("Está seguro de eliminar este documento?")){
				with(document.forms[0]){				
					//agregado
					vaction = action;
					action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			    tipo.value = 'eliminar';	
			    //target = 'mainFrame';
				target = '';
					submit();
					//action = vaction;				
				}
			}
		}
		
		function fn_editar(pcodigo_documento,pcodigo_expediente,pdescripcion_tipo,pnumero_documento
		,pcodigo_oficina,pestado_movimiento,pfecha_movimiento,phora_movimiento,				   
		psecuencia_movimiento,pnaturaleza_documento,pasunto_documento,pcodigo_recepcion,pfecha_registro,phora_registro,pprocedencia,pfirmadopor,pdirigido,pcodigo_solicitud,pmedio,pobserva_documento){
			with(document.forms[0]){
			
		
				codigo_documento.value = pcodigo_documento;
				codigo_expediente.value = pcodigo_expediente;
				descripcion_tipo.value = pdescripcion_tipo;
				numero_documento.value = pnumero_documento;
				codigo_oficina.value = pcodigo_oficina;
				estado_movimiento.value = pestado_movimiento;
				fecha.value = pfecha_movimiento;
				hora.value = phora_movimiento;
				secuencia_movimiento.value = psecuencia_movimiento;
				//naturaleza_documento.value = pnaturaleza_documento;
				asunto_documento.value = pasunto_documento;
				codigo_recepcion.value = pcodigo_recepcion;
				//observa_movimiento.value = pobserva_movimiento;
				/*fecha.value = pfecha_def;
				hora.value = phora_def;*/
				/*fecha.value = pfecha;
				hora.value = phora;
				observacion.value = pobservacion;*/
				
				//observacion.value = pobserva_registro;
				
				
				fecha_registro.value = pfecha_registro;
				hora_registro.value = phora_registro;
				procedencia.value = pprocedencia;
				firmadopor.value = pfirmadopor;
				dirigido.value = pdirigido;
				codigo_solicitud.value = pcodigo_solicitud;
				medio.value = pmedio;
				observa_documento.value = pobserva_documento;
				//alert(pasunto_documento);
				
			
				//alert(pobserva_registro);
				/*alert(codigo_oficina);
				alert(estado_movimiento);
				alert(codigo_documento);
				alert(codigo_expediente);*/
				
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				//numero_documento.readOnly = true;
				//asunto_documento.readOnly = true;
				//observa_movimiento.readOnly = true;
				
				//fecha_registro.readOnly = true;
				//hora_registro.readOnly = true;
				procedencia.readOnly = true;
				//firmadopor.readOnly = true;
				//dirigido.readOnly = true;
				
				//btnGrabar.style.display = '';	
					
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

	function regresar()
		{
		
			/* <c:choose>
        	<c:when test='${codigo_oficina ==  "2"}'>
		var strURL = "ListaDocumentos.do?tipo=recepcion&operacion=R";
		 location=strURL;
		return;
		</c:when>
		<c:otherwise>
		var strURL = "ListaDocumentos.do?tipo=recepcion&operacion=RN";
		 location=strURL;
		return;
		</c:otherwise>
		</c:choose>  */ 
		
		var strURL = "ListaDocumentos.do?tipo=recepcion&operacion=RN";
		 location=strURL;
		return;
		}
		
</script>
	</head>
	<body topmargin="0" leftmargin="0" onLoad="fn_onLoad();">
		<html:form action="/MantModificar" method="POST">
			<html:hidden property="tipo" />
			<html:hidden property="codigo_oficina" />
			<html:hidden property="estado_movimiento" />
			<html:hidden property="descripcion_tipo"  />
			<html:hidden property="secuencia_movimiento"  />
			<html:hidden property="codigo_recepcion"  />
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
							  <tr><td width="675" height="26" align="right" valign="middle" class="label">Modificar Documentos</td>
							<td width="551" align="right" valign="middle"><A  href="javascript:regresar()"><img  src="img/salir.gif" alt="Ir a la lista Recepcion documentos" border="0" ></A> &nbsp;&nbsp;&nbsp;</td>
						    </tr></table>
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							
							<tr>
								<td height="15" >&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									N&deg; Registro:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
							  <td align="left" valign="middle" >
			                    <html:text property="codigo_documento" style="width: 100px;"  styleClass="caja" maxlength="15"/>							  </td>
								<td >&nbsp;</td>
								<td >&nbsp;</td>
								<td>&nbsp;</td>
								<td width="10" style="width: 10px;">&nbsp;								</td>
							</tr>
							
							<tr>
								<td height="15" >&nbsp;								</td>
								<td  class="label" align="left">
									Fecha Registro:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td colspan="2" align="left" valign="middle" style="width: 300px;">
						
						<html:text property="fecha_registro" style="width: 80px;" styleClass="caja" />	
						&nbsp; <a href="" id="lanzador1"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy
												  </td>
								<td align="left" valign="middle" class="label">Procedencia: </td>
								<td align="left" valign="middle">
								<html:text property="procedencia" style="width: 290px;"  styleClass="caja"/>																	  </td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							
							<tr>
								<td height="15" >&nbsp;								</td>
								<td  class="label" align="left">Hora Registro:																	</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
							  <td colspan="2" align="left" valign="middle" style="width: 300px;">
				                					 <html:text property="hora_registro" style="width: 80px;" maxlength="11" styleClass="caja" />	&nbsp;00:00	  </td>
								<td align="left" valign="middle" class="label"> Firmado por:	</td>
								<td align="left" valign="middle"><html:text property="firmadopor" style="width: 290px;" styleClass="caja"/></td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							<tr>
								<td height="15" >&nbsp;								</td>
								<td align="left" valign="middle" class="label">								N&deg; Expediente:</td>
								<td>&nbsp;</td>
								<td colspan="2" align="left" valign="middle"><html:text property="codigo_expediente" style="width: 100px;" styleClass="caja"/></td>
								<td class="label">Dirigido a :</td>
								<td align="left" valign="middle" class="label">
								<html:text property="dirigido" style="width: 290px;"  styleClass="caja"/>															</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							<tr>
								<td height="15" >&nbsp;								</td>
								<td align="left" valign="middle" class="label">								Medio:</td>
								<td>&nbsp;</td>
								<td colspan="2" align="left" valign="middle">
								 <html:select property="medio" styleClass="caja" >														
								<logic:present name="rs_medios">
									<html:options collection="rs_medios" property="codigo_medio" labelProperty=		 		 								"descripcion"/>
								</logic:present>
							</html:select>
								
								</td>
								<td class="label">Solicitud :</td>
								<td align="left" valign="middle" class="label">
								 <html:select property="codigo_solicitud" styleClass="caja" >														
								<logic:present name="rs_solicitud">
									<html:options collection="rs_solicitud" property="codigo_solicitud" labelProperty=		 		 								"nombre_solicitud"/>
								</logic:present>
							</html:select>															</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
                          <tr>
								<td height="15" >&nbsp;								</td>
								<td align="left" valign="middle" class="label">								N&deg; Documento:</td>
								<td>&nbsp;</td>
								<td colspan="2" align="left" valign="middle"><html:text property="numero_documento" style="width: 300px;" styleClass="caja" /></td>
								<td class="label">Fecha de Recepci&oacute;n :</td>
								<td align="left" valign="middle" class="label">
											<html:text property="fecha" style="width: 80px;" maxlength="11" styleClass="caja" 
										onkeypress="validarCaracterFecha(this);"  />			
										  &nbsp; <a href="" id="lanzador"><img  src="img/cal.gif" alt="Fecha (dd/mm/yyyy)" border="0"></a> 
				   &nbsp;dd/mm/yyyy						</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
						  <tr>
								<td height="23" >&nbsp;								</td>
								<td align="left" valign="middle" class="label" style="width: 90px;">
									Asunto:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td colspan="4" align="left" valign="middle" style="width: 300px;">					  		 	  
						          <html:textarea property="asunto_documento" style="width: 700px;height: 70px;"   styleClass="caja"/>														  </td>
								<td style="width: 10px;">&nbsp;								</td>
						</tr>
                           <tr>
								<td height="23" >&nbsp;								</td>
								<td align="left" valign="middle" class="label">Hora Recepci&oacute;n:</td>
								<td>&nbsp;</td>
								<td colspan="2" align="left" valign="middle" class="label"><html:text property="hora" style="width: 80px;" maxlength="15" styleClass="caja" />	</td>
								<td align="left" valign="middle" class="label" style="width: 90px;"> Observaci&oacute;n: </td>
								<td align="left" valign="middle" style="width: 300px;">
			                      <html:textarea property="observa_documento" style="width: 300px;height: 50px;"  styleClass="caja"/>								  </td>
								<td style="width: 10px;">&nbsp;								</td>
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
							<tr>
								<td height="31" colspan="7" align="center">
									<table>
										<tr>
											<td>
											<html:button property="btnGrabar" value="Modificar" onclick="fn_grabar();" style="width: 90;" styleClass="boton"/>
																
										  </td>
											<td>
												<html:button property="btnCancelar" value="Cancelar" onclick="cancel();" style="width: 90;" styleClass="boton"/>											</td>
											<td>
											
																
										  </td>
										</tr>
									</table>								</td>
							</tr>
							
					  </table>
                   
          		
				  </td>
				</tr>
				<tr>
				  <SCRIPT type=text/javascript>
					Calendar.setup({
						inputField     :    "fecha",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador" 
						   // el id del botón que lanzará el calendario
					});
					
					 Calendar.setup({
						inputField     :    "fecha_registro",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador1" 
						   // el id del bot?n que lanzar? el calendario
					});
					//fecha();
					
				</SCRIPT>
					<td>
						<table width="100%" align="center" cellpadding="0" cellspacing="0" >
						<tr>
								<td align="center" valign="middle" background="img/fondoplomo8.jpg" class="label" height="25">
									Lista de Documentos 								</td>
						  </tr>
							<tr>
								<td>
									
									<display:table name="sessionScope.rs_modificar_doc" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										
										<display:column property="codigo_documento" title="N&deg; Registro" />
										<display:column property="codigo_expediente" title="N&deg; Expediente" />
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
