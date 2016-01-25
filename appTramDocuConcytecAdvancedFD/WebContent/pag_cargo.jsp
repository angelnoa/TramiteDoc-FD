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
		<title>Mantenimiento de Oficinas</title>
		
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
        <script src="js/funciones.js" type="text/javascript"></script>
		<script  src="js/prototype-1.4.0.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/scriptaculous.js"></script>
        <script type="text/javascript" src="js/overlibmws.js"></script>
        <script type="text/javascript" src="js/ajaxtags.js"></script>
		<script language="JavaScript" src="js/avatec.js"></script>
		<script language="JavaScript">
		
		function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray")
			with(document.forms[0]){						
				if(trim(tipo.value) == "editar"){
					codigo_cargo.readOnly = true;
					
					btnEliminar.style.display = '';
					//dpto.style.display = 'none';
					//users.focus();
				}
				else{
					
					codigo_cargo.readOnly = true;
					nombrecargo.value = '';
					
					
					btnEliminar.style.display = 'none';
					nombrecargo.focus();
				}
			}
		}
		
		function fn_grabar(){
			with(document.forms[0]){
				if(trim(nombrecargo.value) == ""){
					alert("Se requiere un valor");
					nombrecargo.focus();
					return;
				}
				
				
				
						
								
				vaction = action;
				action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
		    target = 'mainFrame';
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
				codigo_cargo.value = '';			
				nombrecargo.value = '';
				
				codigo_cargo.readOnly = true;
				
				//removeElements(perfilSeleccionados);
				btnEliminar.style.display = 'none';						
				nombrecargo.focus();
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
		
		function fn_editar(pcodigo_cargo,pnombrecargo){
			with(document.forms[0]){
			
				codigo_cargo.value = pcodigo_cargo;
				nombrecargo.value = pnombrecargo;
				
				codigo_cargo.readOnly = true;
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
		
		
		function fn_checkMail(caja){
			if(trim(caja.value) != ""){
				if(!checkMail(trim(caja.value))){
					alert("No es un email correcto");
					caja.select();
					caja.focus();
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
</script>
	</head>
	<body topmargin="0" leftmargin="0" onload="fn_onLoad();">
		<html:form action="/MantCargo" method="POST">
			<html:hidden property="tipo" />
			
			<table width="100%" cellspacing="7px." cellpadding="0">
				<tr>
					<td style="width:100%">
						<div class="TitleScreen">
							&nbsp;&nbsp;Mantenimiento de Cargos
						</div>
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							<tr>
								<td width="8" height="23" style="height: 10px;">&nbsp;
									
								</td>
								<td width="88" align="left">&nbsp;
									
								</td>
								<td width="8">&nbsp;
									
								</td>
								<td colspan="2">&nbsp;
									
								</td>
								<td width="13">&nbsp;
									
								</td>
							</tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Codigo:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								  <html:text property="codigo_cargo" style="width: 100px;" maxlength="20" styleClass="caja"
										onkeypress="return validatecla('letras', this);" />
							  </td>
								<td style="width: 10px;">&nbsp;
									
								</td>
							</tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Descripci&oacute;n:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								  <html:text property="nombrecargo" style="width: 330px;" maxlength="90" styleClass="caja"
										onkeypress="return validatecla('letras', this);" />
							  </td>
								<td style="width: 10px;">&nbsp;
									
								</td>
							</tr>
							
                           
							<tr>
								<td height="23" style="height: 10px;">&nbsp;
									
								</td>
								<td align="left">&nbsp;
									
								</td>
								<td>&nbsp;
									
								</td>
								<td colspan="2">&nbsp;
									
								</td>
								<td>&nbsp;
									
								</td>
							</tr>
							<tr>
								<td height="31" colspan="6" align="center">
									<table>
										<tr>
											<td>
												<html:button property="btnGrabar" value="Grabar" onclick="fn_grabar();" style="width: 90;" styleClass="boton"/>
											</td>
											<td>
												<html:button property="btnCancelar" value="Nuevo" onclick="cancel();" style="width: 90;" styleClass="boton"/>
											</td>
											<td>
												<html:button property="btnEliminar" value="Eliminar" onclick="fn_eliminar();"
													style="display: none; width: 90;" styleClass="boton"/>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="23" style="height: 10px;">&nbsp;
									
								</td>
								<td align="left">&nbsp;
									
								</td>
								<td>&nbsp;
									
								</td>
								<td colspan="2">&nbsp;
									
								</td>
								<td>&nbsp;
									
								</td>
							</tr>
					  </table>
                   
          		
				  </td>
				</tr>
				<tr>
					<td>
						<table width="100%" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<display:table name="sessionScope.listacargos" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										<display:column property="ACTION" title="Seleccione" media="html" style="width: 50px; text-align: center;" />
										<display:column property="codigo_cargo" title="Codigo" style="width: 50px; text-align: center;" />
										<display:column property="nombrecargo" title="Descripci&oacute;n" />
									
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
