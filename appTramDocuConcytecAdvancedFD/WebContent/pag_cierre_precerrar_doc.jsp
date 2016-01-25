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
	function mostrarDatos(valor){
	document.forms[0].contenido.value=valor;
	
	}
	function recepcionChecks(text){
	   document.getElementById('val_docs').value = text;
    }
	   function SeleccionDocumentos(){
	   window.open("Busquedas.do?tipo=documentos_pendientes_cerrar","documentos", "HEIGHT=500,WIDTH=700");
	   }
	    function buscar_persona(){
	     
	     window.open("pag_cierre_pop_instituciones.jsp","instituciones", "HEIGHT=500,WIDTH=500");
	    }	
		
		function buscar_usuario(){
		window.open("pag_cierre_pop_usuarios.jsp","usuarios", "HEIGHT=500,WIDTH=500");
		}
		function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray")
			with(document.forms[0]){						
				
			}
		}
		function fn_grabar(){
			with(document.forms[0]){
		
		       
		       
				if(trim(fecha_envio.value) == ""){
					alert("Se requiere un valor");
					fecha_envio.focus();
					return;
				}
				
				if(trim(destinatario.value) == ""){
					alert("Se requiere un valor");
					nombre_destinatario.focus();
					return;
				}
				
				if(trim(remitente.value) == ""){
					alert("Se requiere un valor");
					nombre_remitente.focus();
					return;
				}
				
				if(trim(objeto_envio.value) == ""){
					alert("Se requiere un valor");
					objeto_envio.focus();
					return;
				}
				
				if(trim(tipo_servicio.value) == ""){
					alert("Seleccionar");
					tipo_servicio.focus();
					return;
				}
				
				if(trim(codigo_courier.value) == ""){
					alert("Seleccionar");
					tipo_servicio.focus();
					return;
				}
				
				if(trim(codigo_guia_courier.value) == ""){
					alert("Se requiere un valor");
					codigo_guia_courier.focus();
					return;
				}
				
				if(window.confirm("Desea registrar el PRE-Cierre?")){	
				submit();
			   }			
			}
		}
		
		function selectAll(combo, status){
			for(i=0; i<combo.options.length; i++){
				combo.options[i].selected = status;
			}
		}
		
		function cancel(){
			with(document.forms[0]){
				
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
		<html:form action="/MantPrecerrar" method="POST">
			
			<table width="100%" cellspacing="0" cellpadding="0"  align="left">
			<tr>
			  <td height="50" align="left" valign="middle" style="width:100%">
						<table width="100%" height="50" cellspacing="0" cellpadding="0"  align="left">
						<tr><td bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></td></tr></table>			  </td>
			  </tr>
				<tr>
					<td class="label" align="center" style="width:100%" background="img/fondoplomo8.jpg" height="26">
							<table width="100%" cellpadding="0" cellspacing="0">
							  <!--DWLayoutTable-->
							  <tr>
							  <td width="50%" height="26" align="LEFT" valign="middle" class="label1">PRE-CERRAR DOCUMENTOS</td>
							  <td width="50%" height="26" align="right" valign="middle" class="label1"></td>
							</tr></table>					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							<tr>
								<td width="10" height="10" style="height: 10px;">								</td>
								<td width="90" align="left">								</td>
								<td width="143"><input name="tipo" type="hidden" id="tipo" value="guardar"></td>
								<td width="555"></td>
								<td width="27"></td>
								<td width="125"></td>
								<td width="13">								</td>
							</tr>
							 <tr>
                              <td height="23" style="width: 10px;">&nbsp;</td>
                              <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td class="label">Nro. Documentos : </td>
                              <td align="left" valign="middle" class="label"><input type="button" value="seleccionar" onClick="javascript:SeleccionDocumentos();" class="boton"/> <input name="contenido" class="calendar" value="" size="75" maxlength="100"   readonly="readonly"/>
                              <td>
                              <td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td style="width: 10px;">&nbsp;</td>
                            </tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td align="left" valign="middle" class="label">Fecha de Env&iacute;o:</td>
								<td align="left" valign="middle"><html:text property="fecha_envio" style="width: 80px;" maxlength="11" styleClass="caja" 
										onkeypress="validarCaracterFecha(this);"  />
  &nbsp; <a href="" id="lanzador"><img  src="img/cal.gif" alt="Fecha (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy </td>
								<td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td align="left" valign="middle"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td class="label">Destinatario:</td>
							  <td class="label">
								<input name="destinatario" type="hidden" id="destinatario"/>
								
								<html:text property="nombre_destinatario" size="75" readonly="readonly" />
								<A  href="javascript:buscar_persona()"><IMG style="WIDTH: 20px; HEIGHT: 14px" height="20"  src="img/flechadwnazul_on.gif" width="20" border="0" alt="Seleccionar Procedencia"></A>
							  <td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
							  <td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
                            <tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td class="label">Remitente:</td>
							  <td align="left" valign="middle" class="label"><input name="remitente" type="hidden" id="remitente" />
							  <html:text property="nombre_remitente" readonly="readonly" size="75"/>							  
							    <A  href="javascript:buscar_usuario()"><IMG style="WIDTH: 20px; HEIGHT: 14px" height="20"  src="img/flechadwnazul_on.gif" width="20" border="0" alt="Seleccionar Procedencia"></A></td>
								<td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
							  <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
                           
                            <tr>
                              <td height="23" style="width: 10px;">&nbsp;</td>
                              <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td class="label">Objeto del Env&iacute;o: </td>
                              <td align="left" valign="middle" class="label"><html:textarea property="objeto_envio" style="WIDTH: 300px; HEIGHT: 72px;"  styleClass="caja"/></td>
                              <td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td style="width: 10px;">&nbsp;</td>
                            </tr>
                            <tr>
                              <td height="23" style="width: 10px;">&nbsp;</td>
                              <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td class="label">Tipo de Servicio: </td>
                              <td align="left" valign="middle" class="label"> 
                               <html:select property="tipo_servicio">
                              <html:options collection="lista_tipo_servicios" property="codigo_tipo_servicio" labelProperty="descripcion" />
                              </html:select></td>
                              <td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td style="width: 10px;">&nbsp;</td>
                            </tr>
                            <tr>
                              <td height="23" style="width: 10px;">&nbsp;</td>
                              <td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td class="label">Courier:</td>
                              <td align="left" valign="middle" class="label"><html:select property="codigo_courier">
                              <html:options collection="lista_couriers" property="id_courier" labelProperty="nombre_courier" />
                              </html:select>
                             </td>
                              <td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
                              <td style="width: 10px;">&nbsp;</td>
                            </tr>
                           <tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td class="label">Codigo Guia de courier:</td>
							    <td align="left" valign="middle" class="label">
							 <html:text property="codigo_guia_courier"/>
							 </td>
								<td class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td style="width: 10px;">&nbsp;								</td>
						</tr>
 
							
						
							<tr>
								<td height="31" colspan="7" align="center">
									<table>
										<tr>
											<td><html:button property="btnGrabar" value="Registrar" onclick="fn_grabar();" style="width: 90;" styleClass="boton"/>																				  </td>
											<td>
												<html:button property="btnCancelar" value="Cancelar" onclick="cancel();" style="width: 90;" styleClass="boton"/>											</td>
											<td>																					  </td>
										</tr>
									</table>								</td>
							</tr>
							<tr>
								
								<td colspan="7" align="center"><table align="center" width="100%">
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
					  </table>				  
					  <SCRIPT type=text/javascript>
					Calendar.setup({
						inputField     :    "fecha_envio",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador" 
						   // el id del botón que lanzará el calendario
					});
					
					
					//fecha();
					
				</SCRIPT></td>
				</tr>
		  </table>
		
		</html:form>
	</body>
</html>
<% } %>
