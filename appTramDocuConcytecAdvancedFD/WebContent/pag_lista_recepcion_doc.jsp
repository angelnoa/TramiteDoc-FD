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
		<title>Lista Recepci&oacute;n de documentos</title>
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
					
				btnEnviar.style.display = '';
				btnGrabar.style.display = 'none';
				btnUpload.style.display = '';
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
					//asunto_documento.value = '';
				
					btnEnviar.style.display = 'none';
					btnGrabar.style.display = 'none';
					btnCancelar.style.display = 'none';
					btnUpload.style.display = 'none';
					
					fecha.focus();
				}
			}
		}
		function fn_grabar(){
			with(document.forms[0]){
		
				if(trim(fecha.value) == 0){
					alert("Se requiere un valor");
					fecha.focus();
					return;
				}
				
				if(trim(hora.value) == 0){
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
				//asunto_documento.value = '';
				
				//usuario.readOnly = false;
				
				//removeElements(perfilSeleccionados);
				btnEnviar.style.display = 'none';						
				fecha.focus();
			}
		}
		
		
		
		function fn_enviar(){
			if(confirm("Está seguro de recepcionar este documento?")){
				with(document.forms[0]){				
					//agregado
					vaction = action;
					action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			    tipo.value = 'enviar';	
			    //target = 'mainFrame';
				target = '';
				
				//alert(pestado_movimiento);
				if(trim(codigo_oficina.value) == 0){
					alert("Se requiere un valor");
					codigo_oficina.focus();
					return;
					}
					
				if(trim(codigo_motivo.value) == 0){
				alert("Se requiere un valor");
				codigo_motivo.focus();
				return;
				}
					
				if(trim(estado_movimiento.value) == 5){
					alert("El documento se encuentra Derivado..!");
					fecha.focus();
					return;
				}
				if(trim(estado_movimiento.value) == 6){
					alert("El documento se encuentra Preliquidado..!");
					fecha.focus();
					return;
				}
				if(trim(estado_movimiento.value) == 7){
					alert("El documento se encuentra Liquidado..!");
					fecha.focus();
					return;
				}
				if(trim(codigo_oficina.value) == 1 && trim(estado_movimiento.value) == 2){
					alert("No se puede recepcionar el documento, Ir al proceso Preliquidar Documento..!");
					fecha.focus();
					return;
				}
				
				
					submit();
					//action = vaction;				
				}
			}
		}
		
		/*function fn_editar(pcodigo_documento
		,pcodigo_expediente
		,pdescripcion_tipo,pnumero_documento
		,pcodigo_oficina,pestado_movimiento,pfecha_movimiento,phora_movimiento,pobserva_registro,pcodigo_recepcion,psecuencia_movimiento,pasunto_documento){
			with(document.forms[0]){*/
			
			function fn_editar(observa_movimiento,pcodigo_documento,pcodigo_expediente,pdescripcion_tipo,pnumero_documento
		,pcodigo_oficina,pestado_movimiento,pfecha_movimiento,phora_movimiento,				   
		psecuencia_movimiento,pnaturaleza_documento){
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
				/*alert(pfecha_movimiento);
				alert(phora_movimiento);*/
				//observacion.value = pobserva_registro;
				//codigo_recepcion.value = pcodigo_recepcion;
				secuencia_movimiento.value = psecuencia_movimiento;
				//alert(psecuencia_movimiento);
				naturaleza_documento.value = pnaturaleza_documento;
				//asunto_documento.value = pasunto_documento;
				
				//alert(pasunto_documento);
				
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
		
		
		function HabilitaCombo(){
			with(document.forms[0]){
			   if (chk_copia.checked) {
			
					cbo_copia.disabled = false;
			   }
			   else{
			
				cbo_copia.disabled = true; 
			   }
			   					}
			}
			
			
		function HabilitaText()
		{
		with(document.forms[0]){
						
				if (cbo_fecharpta.checked)
				{
				 
				fecha_rpta.disabled = false; 
				lanzador1.style.display = '';
				
				}
				else
				{
				
				fecha_rpta.disabled = true;
				lanzador1.style.display = 'none';
				fecha_rpta.value="";
				
				}
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
		
		function ver_documento(codigo)
	{
			/*medio = document.getElementById("medio").value
			codigo_tipo = document.getElementById("codigo_tipo").value
			numero_documento = document.getElementById("numero_documento").value
			
			accion="R";*/
			var ls_ruta="N:\\DocumentosTramite\\"+codigo+".PDF";
			
 			var strURL = "ValidaPaginas.do?tipo=instcontactos";
			
			strURL += "&medio="+medio;
			strURL += "&codigo_tipo="+codigo_tipo;
			strURL += "&numero_documento="+numero_documento;
			strURL += "&accion="+accion;
	
 	 	location=strURL;
	
		return;
	}
	
	
	function AgregarArchivos(){
   
				var ls_operacion = "N";
				var ls_codigo_documento = document.getElementById("codigo_documento").value;
				var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
				var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;
				//alert(ls_codigo_documento);
				
				var strURL='MantRecepcion.do?tipo=upload'+'&operacion='+ls_operacion+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&secuencia_movimiento='+ls_secuencia_movimiento;	
				window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes")
				
				
		}
		
		function VerArchivos(ls_codigo_documento,ls_codigo_expediente,ls_codigo_recepcion){
   
				var ls_operacion = "N";
				/*var ls_codigo_documento = document.getElementById("codigo_documento").value;
				var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
				var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;*/
				//alert(ls_codigo_documento);
				
				var strURL='MantSeguimiento.do?tipo=upload'+'&operacion='+ls_operacion+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&codigo_recepcion='+ls_codigo_recepcion;	
				window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes")
				
				
		}	
</script>
	</head>
	<body topmargin="0" leftmargin="0" onLoad="fn_onLoad();">
		<html:form action="/MantRecepcion" method="POST">
			<html:hidden property="tipo" />
			<html:hidden property="codigo_oficina" />
			<html:hidden property="estado_movimiento" />
			<html:hidden property="descripcion_tipo"  />
			<html:hidden property="secuencia_movimiento"  />
			<html:hidden property="naturaleza_documento"  />
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
							  <tr><td width="653" height="26" align="right" valign="middle" class="label">Recepci&oacute;n de Documentos</td>
							<td width="573" align="right" valign="middle">
							<A  href="javascript:listadocumentos()"><img src="img/modifica.gif" alt="Modificar Documentos" border="0" ></A> &nbsp;&nbsp;&nbsp;</td>
						    </tr></table>
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							<tr>
								<td width="7" height="10" style="height: 10px;">								</td>
								<td width="86" align="left">								</td>
								<td width="7">								</td>
								<td width="322">								</td>
								<td width="92"></td>
								<td width="33"></td>
								<td width="66"></td>
								<td width="318"></td>
								<td width="10">								</td>
							</tr>
							
							<tr>
								<td height="20" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									N&deg; Registro:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
							  <td align="left" valign="middle" >
				                <html:text property="codigo_documento" style="width: 150px;"  styleClass="caja"/>							  </td>
								<td align="left" valign="middle" class="label"> N&deg; Expediente:	</td>
								<td colspan="3" align="left" valign="middle"><html:text property="codigo_expediente" style="width: 150px;" styleClass="caja"/></td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							
							<tr>
								<td height="20" >&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									N&deg; Doc.:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td align="left" valign="middle" >
						
						<html:text property="numero_documento" style="width: 260px;" styleClass="caja" />							  </td>
								<td align="left" valign="middle" class="label">Fecha:</td>
								<td colspan="3" align="left" valign="middle">
								 <html:text property="fecha" style="width: 80px;" maxlength="11" styleClass="caja" 
										onkeypress="validarCaracterFecha(this);"  />			
										  &nbsp; <a href="" id="lanzador"><img  src="img/cal.gif" alt="Fecha (dd/mm/yyyy)" border="0"></a> 
				   &nbsp;dd/mm/yyyy				 	</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							
							<tr>
								<td height="20" >&nbsp;								</td>
								<td  class="label" align="left">
									Hora:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
							  <td align="left" valign="middle" >
			                    <html:text property="hora" style="width: 80px;" maxlength="15" styleClass="caja" />							  </td>
								<td colspan="4" valign="top" class="label">Fecha Rpta.: &nbsp;
							    <INPUT language="JavaScript" class="caja" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta_rq"  maxLength="10" size="11" name="fecha_rpta_rq">								  &nbsp; <a href="" id="lanzador2"  ><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
                          <tr>
								<td height="20" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									Oficina:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td align="left" valign="middle" style="width: 300px;">
						
						<c:choose>
						<c:when test='${operacion ==  "recepcion"}'>
          			 <select id="codigo_oficina_drv" name="codigo_oficina_drv" class="caja" style="WIDTH: 300px; HEIGHT: 21px">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	
					<option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.nombre_corto}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select> 
					</c:when>
					<c:when test='${operacion ==  "editar"}'>
        		<select id="codigo_oficina_drv" name="codigo_oficina_drv" class="caja" style="WIDTH: 300px; HEIGHT: 21px">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.nombre_corto}'/> </option> </c:forEach> 
                    </c:when> </c:choose>
					 </select>
					</c:when>
					<c:otherwise>
				 <select id="codigo_oficina_drv" name="codigo_oficina_drv" class="caja" style="WIDTH: 300px; HEIGHT: 21px">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.nombre_corto}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select>
					</c:otherwise>
				</c:choose> 						  </td>
								<td align="left" valign="middle" class="label">Personal:</td>
								<td colspan="3" align="left" valign="middle">
								<c:choose>
        							<c:when test='${operacion ==  "recepcion"}'>
        							<select id="personas" name="personas" class="caja" >
									<option value="0">:: Personas ::</option>
								  </select>
        							</c:when>
      
        							<c:when test='${operacion ==  "editar"}'>
        								<select id="personas" name="personas" class="caja" >
									<option value="0">:: Personas ::</option>
								  </select>
        							</c:when>
         							<c:otherwise>
        								<select id="personas" name="personas"  class="caja" disable>
                                        	<c:choose> <c:when test='${not empty rs_personas}'> 
                                            	<c:forEach items='${rs_personas}' var='pa'>
													<option value=<c:out value='${pa.codigo_personal}'/>> 
                                                    <c:out value='${pa.nombre_personal}'/> </option>	
                                                </c:forEach> </c:when> 
                                            </c:choose> 
                                        </select>
        							</c:otherwise>
    							</c:choose>				 	</td>
								<td style="width: 10px;">&nbsp;								</td>
						</tr>
							<tr>
								<td height="20" style="width: 10px;">&nbsp;								</td>
								<td rowspan="2" align="left" class="label" style="width: 90px;">
									Motivo:								</td>
								<td align="center" class="labelsubtitle" style="width: 10px;">&nbsp;								</td>
								<td rowspan="2" align="left" valign="middle" style="width: 300px;">
						
						<c:choose>
						<c:when test='${operacion ==  "recepcion"}'>
          			<select id="codigo_motivo" name="codigo_motivo" class="caja" style="WIDTH: 250px; HEIGHT: 21px">
                        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                        <c:choose> <c:when test='${not empty rs_motivos}'> <c:forEach items='${rs_motivos}' var='pa'>	<option value=<c:out value='${pa.codigo_motivo}'/>> 
                        <c:out value='${pa.descripcion_motivo}'/> </option> </c:forEach> 
                        </c:when> </c:choose> </select>
					</c:when>
					<c:when test='${operacion ==  "recepcion"}'>
        		<select id="codigo_motivo" name="codigo_motivo" class="caja" style="WIDTH: 250px; HEIGHT: 21px">
                        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                        <c:choose> <c:when test='${not empty rs_motivos}'> <c:forEach items='${rs_motivos}' var='pa'>	<option value=<c:out value='${pa.codigo_motivo}'/>> 
                        <c:out value='${pa.descripcion_motivo}'/> </option> </c:forEach> 
                        </c:when> </c:choose> </select>
					</c:when>
					<c:otherwise>
				<select id="codigo_motivo" name="codigo_motivo" class="caja" style="WIDTH: 250px; HEIGHT: 21px">
                        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                        <c:choose> <c:when test='${not empty rs_motivos}'> <c:forEach items='${rs_motivos}' var='pa'>	<option value=<c:out value='${pa.codigo_motivo}'/>> 
                        <c:out value='${pa.descripcion_motivo}'/> </option> </c:forEach> 
                        </c:when> </c:choose> </select>
					</c:otherwise>
				</c:choose> 							  </td>
								<td rowspan="2" align="left" valign="middle" class="label">Requiere Prioridad:</td>
								<td rowspan="2" align="left" valign="middle">
							      <input language="JavaScript" type="checkbox" class="caja" name="cbo_fecharpta" id="cbo_fecharpta" value="checkbox" onClick="javascript:HabilitaText();">				 	</td>
								<td colspan="2" valign="bottom" class="label">Fecha Requerida</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							<tr>
							  <td height="47" style="width: 10px;">&nbsp;</td>
							  <td align="center" class="labelsubtitle" style="width: 10px;">&nbsp;</td>
							  <td colspan="2" valign="top"><INPUT language="JavaScript" class="caja" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta"  maxLength="10" size="11" name="fecha_rpta">							    &nbsp; <a href="" id="lanzador1"  style="display: none; width: 20;" ><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy </td>
								<td style="width: 10px;">&nbsp;</td>
					    </tr>
						 <tr>
								<td height="20" style="width: 10px;">&nbsp;								</td>
								<td rowspan="3" align="left" class="label" style="width: 90px;">
									Observaci&oacute;n:							</td>
								<td align="center" class="labelsubtitle" style="width: 10px;">&nbsp;								</td>
								<td rowspan="3" align="left" valign="middle" style="width: 300px;">
						
						 <html:textarea property="observacion" style="width: 300px;height: 70px;"  styleClass="caja"/>				  </td>
								<td align="left" valign="bottom" class="label">Con Copia:						   </td>
							    <td colspan="2" rowspan="3" align="left" valign="middle" class="label">Copiar a Oficinas:</td>
							<td rowspan="3" align="left" valign="middle">
						      <SELECT class="caja" id="cbo_copia" style="WIDTH: 300px; HEIGHT: 90px" multiple name="cbo_copia">
                                <c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.nombre_corto}'/> </option> </c:forEach> 
                    </c:when> </c:choose>
                                </SELECT>					 	   </td>
								<td style="width: 10px;">&nbsp;								</td>
						</tr>
						 <tr>
						   <td height="39" style="width: 10px;">&nbsp;</td>
						   <td align="center" class="labelsubtitle" style="width: 10px;">&nbsp;</td>
						   <td align="left" valign="top">  <c:choose>
						<c:when test='${operacion ==  "recepcion"}'>
          		  <INPUT language="JavaScript" id="chk_copia" class="caja" onClick="javascript:HabilitaCombo();" type="checkbox" name="chk_copia">
					</c:when>
					<c:when test='${operacion ==  "editar"}'>
        		  <INPUT language="JavaScript" id="chk_copia" class="caja" onClick="javascript:HabilitaCombo();" type="checkbox" name="chk_copia">
					</c:when>
					<c:otherwise>
				  <INPUT language="JavaScript" id="chk_copia" class="caja" onClick="javascript:HabilitaCombo();" type="checkbox" name="chk_copia">
					</c:otherwise>
                           </c:choose> 	</td>
						   <td style="width: 10px;">&nbsp;</td>
					    </tr>
						 <tr>
						   <td height="35" style="width: 10px;">&nbsp;</td>
						   <td align="center" class="labelsubtitle" style="width: 10px;">&nbsp;</td>
						   <td align="left" valign="middle"><input type="button"  id="btnUpload" name="btnUpload"  value="Agregar Archivos" onClick="AgregarArchivos()" alt="Agregar Archivos" style="display: none; width: 120;" styleClass="boton" ></td>
						   <td style="width: 10px;">&nbsp;</td>
					    </tr>
						 

							<tr>
								<td height="31" colspan="9" align="center">
									<table>
										<tr>
											<td>
											<html:button  property="btnEnviar" value="Recepcion" onclick="fn_enviar();"
													style="display: none; width: 90;" styleClass="boton"/>										  </td>
											<td>
												<html:button property="btnCancelar" value="Cancelar" onclick="cancel();" style="width: 90;" styleClass="boton"/>											</td>
											<td>
											<html:button property="btnGrabar" value="Grabar" onclick="fn_grabar();" style="width: 90;" styleClass="boton"/>										  </td>
										</tr>
									</table>								</td>
							</tr>
					  </table>
                   
          		
				  </td>
				</tr>
				<tr>
				  <ajax:select baseUrl="${pageContext.request.contextPath}/obtenerLista.do" parameters="cambia=ofi&codigo_oficina={codigo_oficina_drv}" source="codigo_oficina_drv" target="personas"  />
				  <SCRIPT type=text/javascript>
					Calendar.setup({
						inputField     :    "fecha",      // id del campo de texto
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
					Calendar.setup({
						inputField     :    "fecha_rpta_rq",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador2" 
						   // el id del botón que lanzará el calendario
					});
					
					inicio();
					function inicio(){
					with(document.forms[0]){
							fecha_rpta.disabled = "true"; 
							cbo_copia.disabled = "true"; 
							//document.form_datos.fecha_rpta.disabled = "true"; 
							}
					   
					};
				</SCRIPT>
					<td>
						<table width="100%" align="center" cellpadding="0" cellspacing="0" >
						  <!--DWLayoutTable-->
						<tr>
							<td width="386" height="25" align="left" valign="middle" background="img/fondoplomo8.jpg" class="textomensaje10bold"><span class="textomensaje10bold">
						<c:choose>
						<c:when test='${mensaje_recepcion ==  "1"}'>
          				<c:out value='${mensaje_recepcion_exito}'/>
							<br><c:out value='${mensaje_numreg}'/>
							<br><c:out value='${mensaje_numexp}'/>
							<br><c:out value='${mensaje_numenv}'/>
						</c:when>
						<c:when test='${mensaje_recepcion ==  "2"}'>
          		  DOCUMENTO FISICO CON N&deg; DE REGISTRO : <c:out value='${codigo_documento_env}'/>
				   			<br>N&deg; DE ENVIO : <c:out value='${numenvio}'/>
						</c:when>
						<c:otherwise>
						
						</c:otherwise>
						</c:choose>
						  </span></td>
						    <td width="584" align="left" valign="middle" background="img/fondoplomo8.jpg" class="label">
							Bandeja de Documentos Pendientes por Recepcionar								</td>
						  </tr>
							<tr>
								<td height="13" colspan="2" valign="top">
									
									<display:table name="sessionScope.rs_recepcion_doc" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										
										<display:column property="codigo_documento" title="N&deg; Registro" />
										<display:column property="codigo_expediente" title="N&deg; Expediente" />
										<display:column property="fecha_movimiento" media="html" title="Fecha Registro" />
										<display:column property="numero_documento" title="N&deg;  Documento" />
										<display:column property="oficina_origen" title="Oficina Origen" />
										<display:column property="oficina_deriva" title="Oficina Destino" />
										<display:column property="estado_movimiento" title="Estado" />
										<display:column property="view_archivo" align="center" title="Ver Documento" />
									</display:table>								</td>
							</tr>
					  </table>
					</td>
				</tr>
		  </table>
		
		</html:form>
	</body>
</html>
<% } %>
