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
		<title>Reversion de documentos derivados</title>
		
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
        <script src="js/funciones.js" type="text/javascript"></script>
		<script  src="js/prototype-1.4.0.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/scriptaculous.js"></script>
       
       <link rel="stylesheet" href="css/modelo.css" type="text/css" />
       
        <script type="text/javascript" src="js/ajaxtags.js"></script>
		<script language="JavaScript" src="js/avatec.js"></script>
		<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
		<!-- librería para cargar el lenguaje deseado -->
		<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
		<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
		<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
		
		<script language="JavaScript">
		
		function Enter(e){
		if(e.keyCode==13){
		 buscar();
		}
		}
		function buscar(){
		 var codigo_doc=document.getElementById('codigo_doc');
         if(codigo_doc.value==''){
		 alert('Ingrese el codigo de documento a Buscar');
		   codigo_doc.focus();
		 }else{
		   var strURL = "ListaDocumentos.do?tipo=modificar&operacion=MD&codigo_doc="+codigo_doc.value;
		   location=strURL;
		   	return;
		 }
		 
		}
		function fn_onLoad(){
		    var codigo_doc=document.getElementById('codigo_doc');
		    codigo_doc.focus();
			cambiar_color_tabla("b", "tablaBackgray")
			with(document.forms[0]){						
				if(trim(tipo.value) == "editar"){
				
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
					
				//btnEliminar.style.display = '';
				//btnCopias.style.display = '';
				//btnGrabar.style.display = 'none';
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
				fecha.readOnly = true;
				hora.readOnly = true;
					//fecha.value = '';
					//hora.value = '';
					observacion.value = '';
				
					//btnEliminar.style.display = '';
					//btnCopias.style.display = '';	
					//btnGrabar.style.display = 'none';
					//btnCancelar.style.display = 'none';
					
					
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
				if(trim(estado_movimiento.value) == 0){
					alert("Seleccionar");
					estado_movimiento.focus();
					return;
				}
					
					
				vaction = action;
				action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
		    //target = 'mainFrame';
				tipo.value = 'enviar';	
				target = '';
				submit();
				//action = vaction;			
			}
		}
		
		function listar_todos(){
	
	
	var strURL = "ListaDocumentos.do?tipo=modificar&operacion=MD";
	
 	 location=strURL;
	return;

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
				
				
				//usuario.readOnly = false;
				
				//removeElements(perfilSeleccionados);
				//btnEliminar.style.display = 'none';
				//btnCopias.style.display = 'none';							
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
		
		function fn_editar(pcodigo_documento
		,pcodigo_expediente
		,pdescripcion_tipo,pnumero_documento
		,pcodigo_oficina,pestado_movimiento,pfecha_movimiento,phora_movimiento,pobserva_registro,psecuencia_movimiento){
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
				secuencia_movimiento.value = psecuencia_movimiento;
				
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
				
				//btnEliminar.style.display = '';	
				//btnCopias.style.display = '';	
					
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
		//ListaDocumentos.do?tipo=derivar&operacion=D
		var strURL = "ListaDocumentos.do?tipo=derivar&operacion=D";
		 location=strURL;
		return;
		}
		
		 var winPopup = 0;
		function fn_oficinas_copia(){
   
	 //var strURL = 'MantModDeriva.do?tipo=copias';
	 ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value
	ls_codigo_documento = document.getElementById("codigo_documento").value

	 var strURL="MantModDeriva.do?tipo=copias"+"&secuencia_movimiento="+ls_secuencia_movimiento+"&codigo_documento="+ls_codigo_documento;	
		//strURL += '&num_doc='+num_doc;
			
	   winPopup = window.open(strURL,"","scrollbars,HEIGHT=270,WIDTH=500")
			
		}
		
</script>
	</head>
	<body topmargin="0" leftmargin="0" onLoad="fn_onLoad();">
	<div id="container">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
		<html:form action="/MantModDeriva" method="POST">
			<html:hidden property="tipo" />
			<html:hidden property="codigo_oficina" />
			<html:hidden property="descripcion_tipo"  />
			<html:hidden property="secuencia_movimiento"  />
			<table width="100%" cellspacing="0" cellpadding="0"  align="left">
			
				<tr>
					<td class="label" align="center" style="width:100%" background="img/fondoplomo8.jpg" height="26">
							<table width="100%" cellpadding="0" cellspacing="0">
							  <!--DWLayoutTable-->
							  <tr>
							    <td width="932" height="26" align="LEFT" valign="middle" class="label1">REVERSION DE DOCUMENTOS DERIVADOS</td>
						      <td width="42"><a  href="javascript:regresar()"><img  src="img/salir.gif" alt="Ir a la lista Derivaci&oacute;n Documentos" border="0" height="30" ></a></td>
							  </tr>
							  <tr>
							    <td height="26" colspan="2" align="right" valign="middle" class="groupcell"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
  <!--DWLayoutTable-->
  <tr>
    <td width="162" height="29" align="LEFT" valign="middle" class="label"><strong>B&uacute;squeda 
      por : </strong></td>
    <td width="77">N&deg; Registro:</td>
    <td width="199" align="left" valign="middle">&nbsp; &nbsp;
        <input type="text" name="codigo_doc" id="codigo_doc" class="caja" onKeyPress="javascript:Enter(event)" value="<c:out value='${codigo_doc}'/>" size="20" >
        <script language="JavaScript" type="text/javascript">if(document.getElementById) document.getElementById('codigo_doc').focus();</script>    </td>
    <td width="66" align="center" valign="middle"><input type="button" name="button2" class="boton" value="Buscar" onClick="buscar()" alt="buscar"></td>
    <td width="129" align="center" valign="middle"><input type="button" name="button" class="boton" value="Listar Todos" onClick="listar_todos()" alt="Listar Todos"></td>
    <td width="341">&nbsp;</td>
  </tr>
</table></td>
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
								<td width="303">								</td>
								<td width="161"></td>
								<td width="356"></td>
								<td width="13">								</td>
							</tr>
							
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									N&deg; Registro:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
							  <td align="left" valign="middle" style="width: 300px;">
				                <html:text property="codigo_documento" style="width: 150px;"  styleClass="caja"/>							  </td>
								<td align="left" valign="middle" class="label"> N&deg; Expediente:	</td>
								<td align="left" valign="middle"><html:text property="codigo_expediente" style="width: 150px;" styleClass="caja"/></td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							
							<tr>
								<td height="24" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									N&deg; Doc.:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td align="left" valign="middle" style="width: 300px;">
						
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
								<td class="label">Estado:</td>
								<td>
								<html:select property="estado_movimiento" styleClass="caja" >
										<html:option value="0" >-- Seleccionar --</html:option>
										
										<html:option value="3">Tramite</html:option>
										<html:option value="5">Derivado</html:option>
										
									</html:select>	
							
								</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
                            <tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									Observaci&oacute;n:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td colspan="3" align="left" valign="middle" style="width: 400px;">
					<html:textarea property="observacion" style="width: 200px;"  styleClass="caja"/>							  </td>
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
											<html:button property="btnGrabar" value="Modificar" onclick="fn_grabar();" style="width: 90;" styleClass="boton"/>										  </td>
											<td>
												<html:button property="btnCancelar" value="Cancelar" onclick="cancel();" style="width: 90;" styleClass="boton"/>											</td>
											<td>
																					  </td><td>
																				  </td>
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
						  <!--DWLayoutTable-->
						<tr>
								<td width="295" height="25" background="img/fondoplomo8.jpg" class="textomensaje10bold"><c:out value='${mensaje}'/></td>
						        <td width="675" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label">
								Lista de Documentos 								</td>
						  </tr>
							<tr>
								<td colspan="2">
									<c:if test="${mostrar_listado!='NO'}">
									<display:table name="sessionScope.rs_modificar_doc" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										
										<display:column property="codigo_documento" title="N&deg; Registro" />
										<display:column property="codigo_expediente" title="N&deg; Expediente" />
										<display:column property="numero_documento" title="N&deg;  Documento" />
										<display:column property="oficina_origen" title="Oficina Origen" />
										<display:column property="oficina_deriva" title="Oficina Destino" />
										<display:column property="estado_movimiento" title="Estado" />
									</display:table>	</c:if>							</td>
							</tr>
					  </table>
						
					</td>
				</tr>
		  </table>
		
		</html:form>
		</div>
	</body>
</html>
<% } %>
