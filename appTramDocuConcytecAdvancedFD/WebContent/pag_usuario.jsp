<% 
//Verifica que exista una sesion
String nombreusuario = String.valueOf(session.getAttribute("nombreusuario"));
String flag = String.valueOf(session.getAttribute("ls_flag"));

if (nombreusuario==null || nombreusuario.equals("null")|| !flag.equals("A") ){
%> 
<SCRIPT language="Javascript">
parent.document.location ="inicio.jsp";
</SCRIPT>
<% 
}else {
%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%
		String codUsu=(String)request.getAttribute("codUsu");
	%>
	
		<title>Mantenimiento de Usuarios</title>
		
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
        <script src="js/funciones.js" type="text/javascript"></script>
        
        <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></script>
		<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
		
		<!-- script  src="js/prototype-1.4.0.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/scriptaculous.js"></script 
        <script type="text/javascript" src="js/ajaxtags.js"></script> -->
        
        <script type="text/javascript" src="js/overlibmws.js"></script>
        <script language="JavaScript" src="js/avatec.js"></script>



		
<script language="JavaScript">

				
function Enter(e)
{ if(e.keyCode==13) 
  { fn_buscar();
  }
}
		function fn_buscar(){
			with(document.forms[0]){		
				
					vaction = action;
					tipo.value = 'buscar';
					action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			        target = 'mainFrame';
					submit();
				}
			
		}
		
		
		function showProducts(){
		    //obtiene los objetos productCode, 
		   var code=$("#flag").val(); //.. y se obtiene el valor
		   //alert(code);
		    //var val2 = 'ofiInterno';
		    //llama al servlet con el parametro seleccionado
		    //$("#personas").load("obtenerListaDestinatarios.do", {cambia:val2,codigo_oficina:code});
		   if (code =='A'){
			  // $("#cajonoculto1").fadeIn();
			  // $("#cajonoculto2").fadeIn();
		   } else{
			  // $("#cajonoculto1").fadeOut();
			  // $("#cajonoculto2").fadeOut();
		   }
		    		    
		}
		
		function validaUsuario(){
			//alert("fwdfwfwewe");
			var usuario=$("#usuario").val();
			var tipo=$("#tipo").val();
			//alert(usuario);
			//alert(tipo);
			
			if(tipo.trim() == 'nuevo'){
				$("#cajonmensaje").load("obtenerListaDestinatarios.do", {cambia:'validausuario',pusuario:usuario});	
			}
			
		}
		
		function fnvalidar(){
			showProducts();
		}
		
		function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray");
			with(document.forms[0]){
			    //usuario.readOnly = true;
			    codigoUsuarioBuscar.focus();
				
				//alert('El tipo value es:'+tipo.value);	
				if(trim(tipo.value) == "nuevo"){	
					//usuario.focus();
					usuario.readOnly = false;
					
					usuario.value='';
					clave.value = '';
					estado.value = '';
					flag.value = '';
					nombres.value = '';
					apellidos.value = '';
					email.value = '';
					sede.value = '';
					
				}else if(trim(tipo.value) == ""){
				    usuario.readOnly = true;
					clave.readOnly = false;
					/*
					
					clave.value = '';
					estado.value = '';
					flag.value = '';
					nombres.value = '';
					apellidos.value = '';
					email.value = '';*/
				}else if(trim(tipo.value) == "editar"){				
					btnEliminar.style.display = '';
					clave.readOnly = false;
					btnAgregarImagen.style.display = '';
					//dpto.style.display = 'none';
					//users.focus();
				}
			}
		}
		
		function fn_grabar(){
			with(document.forms[0]){
			
			var correo  = /^[^@\s]+@[^@\.\s]+(\.[^@\.\s]+)+$/
			ls_correo= email.value;
			
				if(trim(usuario.value) == ""){
					alert("Ingrese usuario");
					usuario.focus();
					return;
				}
				
				if(trim(clave.value) == ""){
					alert("Ingrese clave");
					clave.focus();
					return;
				}
				
				if(trim(estado.value) == ""){
					alert("Seleccione estado");
					estado.focus();
					return;
				}
				if(trim(flag.value) == ""){
					alert("Seleccione tipo usuario");
					flag.focus();
					return;
				}
				if(trim(nombres.value) == ""){
					alert("Ingrese nombres");
					nombres.focus();
					return;
				}if(trim(apellidos.value) == ""){
					alert("Ingrese apellidos");
					apellidos.focus();
					return;
				}
				if(trim(email.value) == ""){
					alert("Ingrese correo");
					email.focus();
					return;
				}
						
				if (!correo.test(ls_correo)) { 
			  	alert ("Direccion de E-Mail invalida."); 
				email.focus();
				return false; 
				} 
				
				if(trim(dni.value) == ""){
					alert("Ingrese DNI");
					dni.focus();
					return;
				}
				
				if(trim(sede.value) == ""){
					
					alert("Seleccionar Sede");
					sede.focus();
					return;
				}
				
				
				//alert(flag.value+" "+sede.value);
				if(trim(flag.value) != "A"){
					if(trim(sede.value) == "1"){
						alert("El tipo de usuario seleccionado deberia ser Administrador. Por favor seleccione el tipo correcto.");
						flag.focus();
						return;
					}
				}
				
						
				vaction = action;
				action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
		        target = 'mainFrame';
				tipo.value = 'enviar';
				submit();
				//action = vaction;			
			}
		}
						
		
		function fn_editar(pusuario,pclave,pestado,pflag,pnombres,papellidos,pemail,psede){
			with(document.forms[0]){
			
             //alert('entro a editar');
				usuario.value = pusuario;
				clave.value = pclave;
				estado.value = pestado;
				flag.value = pflag;
				nombres.value = pnombres;
				apellidos.value = papellidos;
				email.value = pemail;
				sede.value = psede;
				
				usuario.readOnly = true;
				btnEliminar.style.display = '';
				
				//agregado
			    vaction = action;
				action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
				//alert(action);
				target = 'mainFrame';
				//alert(target);
				tipo.value = 'editar';		
				submit();
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
				usuario.value = '';
				clave.value = '';
				estado.value = '';
				flag.value = '';
				nombres.value = '';
				apellidos.value = '';
				email.value = '';
				
				usuario.readOnly = false;
				
				//removeElements(perfilSeleccionados);
				btnEliminar.style.display = 'none';	
				btnAgregarImagen.style.display = 'none';
				usuario.focus();
			}
		}
		
		
		
		function fn_eliminar(){
			if(confirm("Está seguro de eliminar este registro?")){
				with(document.forms[0]){				
					//agregado
					vaction = action;
					action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			    tipo.value = 'eliminar';	
			    target = 'mainFrame';
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
		
		
		function exportar_excel(){
			
			var strURL = "generateReport.do?tipo=ADMINUSU&operacion=usu";
			location = strURL;
			
		}
</script>
	</head>
	<body  onload="fn_onLoad(); fnvalidar();">
		<html:form styleId="form_datos" action="/MantUsuarios" method="POST">
		
			<html:hidden property="tipo" styleId="tipo" />
			<input  type="hidden" id="tipo_update" name="tipo_update" value="<c:out value='${FFormMantUsuario.tipo}'></c:out>" />
			
			
			<table width="100%" cellspacing="7px." cellpadding="0">
				<tr>
					<td style="width:100%">
						<div class="TitleScreen">
							&nbsp;&nbsp;Mantenimiento de Usuarios
						</div>
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							<tr>
							  <td width="10" height="17" style="height: 10px;">
							  </td>
							  <td width="89" align="left" valign="top" class="label">Usuario de B&uacute;squeda :							    </td>
							  <td colspan="3" valign="top"><html:text property="codigoUsuarioBuscar" onkeypress="javascript:Enter(event);"/></td>
							  <td width="365" align="left"><html:button property="btnBuscar" value="Buscar" onclick="fn_buscar();"
													style=" width: 90;" styleClass="boton"/></td>
							  <td width="12"></td>
					    	</tr>
					    	
							<tr>
							  <td height="10" colspan="7" style="height: 10px;"><hr /></td>
					        </tr>							
							
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td colspan="2" align="left" class="label" style="width: 90px;">
									Usuario:								</td>
								<td width="10" align="center" class="labelsubtitle" style="width: 10px;">&nbsp;								</td>
								<td colspan="2" align="left" valign="middle" style="width: 300px;">
								
								  <html:text property="usuario" style="width: 150px;" maxlength="15" styleClass="caja" 	styleId="usuario"
								  onkeypress="return validatecla('letras', this);" />
								  	<script>
										$("#usuario").focusout(function() {
										  //$( this ).next( "span" ).css( "display", "inline" ).fadeOut( 1000 );
										  validaUsuario();
										});
									</script>
									
									<div id="cajonmensaje" style="text-align:left;"></div>	
								</td>
								<td width="114" rowspan="6" height="160"  align="center" valign="middle">
															
                    			</td>
							</tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td colspan="2" align="left" class="label" style="width: 90px;">
									Clave:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								  <html:password property="clave" style="width: 150px;" maxlength="20" styleClass="caja" />							  </td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td colspan="2" align="left" class="label" style="width: 90px;">
									Abreviatura:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								  <html:text property="abrev" style="width: 50px;" maxlength="10" styleClass="caja"
										onkeypress="return validatecla('abreviaturas', this);" />							  </td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td colspan="2" align="left" class="label" style="width: 90px;">
									Nombres:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								  <html:text property="nombres" style="width: 260px;" maxlength="20" styleClass="caja"
										onkeypress="return validatecla('letras', this);" />							  </td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td colspan="2" align="left" class="label" style="width: 90px;">
									Apellidos:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								  <html:text property="apellidos" style="width: 260px;" maxlength="20" styleClass="caja"
										onkeypress="return validatecla('letras', this);" />							  </td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td colspan="2" align="left" class="label" style="width: 90px;">
									DNI:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								  <html:text property="dni" style="width: 300px;" maxlength="100" styleClass="caja"
										onkeypress="return validatecla('enteros', this);" />							  </td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td colspan="2" align="left" class="label" style="width: 90px;">
									E-mail:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								  <html:text property="email" style="width: 260px;" maxlength="29" styleClass="caja"
										onkeypress="return validatecla('alfanum', this);" />							  </td>
								<td style="width: 10px;">
									&nbsp;
										 
                   				</td>
							</tr>
                            <tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td colspan="2" align="left" class="label" style="width: 90px;">
									Estado:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
					<html:select property="estado" styleClass="caja" ><html:option value=""></html:option>
										<html:option value="A">Activo</html:option>
										<html:option value="I">Inactivo</html:option>
									</html:select>							  </td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
                            <tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td colspan="2" align="left" class="label" style="width: 90px;">
									Tipo de Usuario:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								
								  	<html:select property="flag" styleClass="caja"  onchange="showProducts()" styleId="flag" >
									    <html:option value=""></html:option>
									    <html:options collection="tipo_usuarios" property="codigo" labelProperty="nombre" />

									</html:select>
								</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							
							<tr>
								<td height="23" style="width: 10px;">&nbsp;								</td>
								<td colspan="2" align="left" class="label" style="width: 90px;">
									<div id="cajonoculto1" style="display:inline;">Sede:</div>								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
									<div id="cajonoculto2"  style="display:inline;">
								  	<html:select property="sede" styleClass="caja">
									   
									    <html:options collection="tipo_sede" property="codigo" labelProperty="descripcion" />
									</html:select>
									</div>
								</td>
								<td style="width: 10px;">&nbsp;	</td>
							</tr>
							
							<tr>
								<td height="10" style="height: 10px;">								</td>
								<td align="left">								</td>
								<td width="0" align="left"></td>
								<td>								</td>
								<td width="238">								</td>
								<td></td>
								<td>								</td>
							</tr>
							<tr>
							  <td height="31" colspan="7" align="center"> 
							  <c:out value='${mensaje_usuario}'/> 
							  
							  
							  </td>
					    </tr>
							<tr>
								<td height="31" colspan="7" align="center">
									<table>
										<tr>
											<td>
											<c:choose>
											<c:when test='${FFormMantUsuario.tipo == "nuevo" }'>
													<html:button property="btnGrabar" value="Grabar" onclick="fn_grabar();" style="width: 90;" styleClass="boton"/>
											</c:when>
											<c:otherwise>
													<html:button property="btnGrabar" value="Grabar" onclick="fn_grabar();" style="width: 90;" styleClass="boton"/>
											</c:otherwise>
											</c:choose>
											
																						
											</td>
											<td>
												<html:button property="btnCancelar" value="Nuevo" onclick="cancel();" style="width: 90;" styleClass="boton"/></td>
											<td>
												<html:button property="btnEliminar" value="Eliminar" onclick="fn_eliminar();"
													style="display: none; width: 90;" styleClass="boton"/></td>
										</tr>
									</table>								</td>
							</tr>
							<tr>
								<td height="10" style="height: 10px;">								</td>
								<td align="left">								</td>
								<td align="left"></td>
								<td>								</td>
								<td>								</td>
								<td></td>
								<td>								</td>
							</tr>
					  </table>
                   
          		 <script language="javascript">
			          //SeleccionarCampo("estado","<c:out value='${detalleUsuarios.estado}'/>");
                      //SeleccionarCampo("flag","<c:out value='${detalleUsuarios.flag}'/>");
			    </script>    
				  </td>
				</tr>
				<tr>
					<td>
						<table width="100%" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="50%" height="25" align="right" valign="middle" background="img/fondoplomo8.jpg" class="label">
							Lista de Usuarios								
							</td>
						
						  <td width="50%" align="right" valign="middle" background="img/fondoplomo8.jpg">Exportar a:&nbsp;
						   <A  href="javascript:exportar_excel()" ><img src="img/xls.gif" border="0" height="20" alt="Exportar a Excel"></A>
						   </td>
						</tr>
							<tr>
								<td colspan="2">
									<display:table name="sessionScope.listausuarios" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										<display:column property="ACTION" title="Seleccione" media="html" style="width: 50px; text-align: center;" />
										<display:column property="usuario" sort="true" title="Usuario" />
										<display:column property="nombres" sort="true" title="Nombres" />
										<display:column property="apellidos" sort="true" title="Apellidos" />
										
					  					<display:column property="tipo_sede" sort="true" title="Sede" />
										<display:column property="tipo_estado" sort="true" title="Estado" />
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
<% 
}
%>
