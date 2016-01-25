
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Mantenimiento de Personal</title>
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
        <script src="js/funciones.js" type="text/javascript"></script>
        
         <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></script>
		<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
		
		
		<!-- <script  src="js/prototype-1.4.0.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/scriptaculous.js"></script>
        <script type="text/javascript" src="js/overlibmws.js"></script>
        <script type="text/javascript" src="js/ajaxtags.js"></script>  -->
        
		<script language="JavaScript" src="js/avatec.js"></script>
		<script language="JavaScript">
		
		function Enter(e){ 
			if(e.keyCode==13){ 
				fn_buscar();
		  	}
		}
		
		function fn_buscar(){
			with(document.forms[0]){		
				if(codigoUsuarioBuscar.value == ''){
					alert('Ingrese el codigo de la busqueda');
				}else{
					vaction = action;
					tipo.value = 'buscar';
					action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			        target = 'mainFrame';
					submit();
				}
			}
		}
		
		function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray");
			with(document.forms[0]){						
				if(trim(tipo.value) == "editar"){
					codigo_personal.readOnly = true;
					
					btnEliminar.style.display = '';
					//validacion.value= '';
					//dpto.style.display = 'none';
					//users.focus();
					
				}
				else{
					//dpto.style.display = 'none';
					
					codigo_personal.readOnly = true;
					nombreapellido_usuario.value = '';
					c_usuario.value = '';
					cargo_personal.value = '';
					//oficina.value = '';
					es_responsable.value = '';
					sede.value = '';
					
					btnEliminar.style.display = 'none';
					cargo_personal.focus();
				}
			}
		}
		
		function showProducts(){
		    //obtiene los objetos productCode, 
		    var code=$("#c_usuario").val(); //.. y se obtiene el valor
		    var val2 = 'lista_nombres';
		    //llama al servlet con el parametro seleccionado
		    $("#nombreapellido").load("ObtenerUsuario.do", {cambia:val2,c_usuario:code});
		}
		
		function showProductsOficinas(){
			//obtiene los objetos productCode, 
			
		    var code=$("#sede").val(); //.. y se obtiene el valor
		    //var pertenece_a_ls=$("#pertenece_a").val();
		    var val2 = 'oficinasxsede';
		    //var externo =$("#rb_seleccion").val(); 
		    //llama al servlet con el parametro seleccionado
		    $("#oficina").load("obtenerListaDestinatarios.do", {cambia:val2,codigo_institucion:code});
		    //refresca();
		    
		    //document.forms[0].personas.selectedIndex=0;
			//document.forms[0].personas.length=1;
		}
		
		
		function Continuar(){
			return;
		}
		
		function AgregarAnexos(){
			
			var strURL='AgregarImagen.do?operacion=UP&tipo=I';	
			winPopup =  window.open(strURL,"","WIDTH=600,HEIGHT=300,scrollbars=yes,resizable=yes");
			
		}
		function fn_grabar(){
			with(document.forms[0]){
				if(trim(c_usuario.value) == ""){
					alert("Se requiere un valor");
					c_usuario.focus();
					return;
				}
				
				if(trim(cargo_personal.value) == ""){
					alert("Seleccionar");
					cargo_personal.focus();
					return;
				}
				
				if(trim(oficina.value) == ""){
					alert("Seleccionar");
					oficina.focus();
					return;
				}
				if(trim(es_responsable.value) == ""){
					alert("Seleccionar");
					es_responsable.focus();
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
				
				codigo_personal.value = '';
				nombreapellido_usuario.value = '';
				c_usuario.value = '';
				cargo_personal.value = '';
				oficina.value = '';
				sede.value = '';
				es_responsable.value = '';	
				validacion.value = '';
				codigo_personal.readOnly = true;
				
				//removeElements(perfilSeleccionados);
				btnEliminar.style.display = 'none';						
				cargo_personal.focus();
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
		
		function fn_editar(pcodigo_personal,pc_usuario,pnombreapellido_usuario,pcargo_personal,poficina,pes_responsable,ptipo_firma,pvalidacion){
			var x = ptipo_firma;
			if(x==null){
				ptipo_firma=0;
			}
			with(document.forms[0]){
			
			//alert(poficina+","+pcargo_personal);
			
				codigo_personal.value = pcodigo_personal;
				c_usuario.value = pc_usuario;
				nombreapellido_usuario.value = pnombreapellido_usuario;
				
				cargo_personal.value = pcargo_personal;
				oficina.value = poficina;
				
				
				es_responsable.value = pes_responsable;
				validacion = pvalidacion;
				
				//tipo_firma.value = ptipo_firma;
				
				codigo_personal.readOnly = true;
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
		
		function muestramensaje(valor){
			alert(valor);
		}
		
		function refrescaoficinas(valor){
			var value=valor;
			if(value=='personal'){
				showProductsOficinas();
			}
		}
		
		function exportar_excel(){
			
			var strURL = "generateReport.do?tipo=ADMINUSU&operacion=pers";
			location = strURL;
			
		}
		
		function showProductsOficinas_(){
			//esta me traera la lista con la oficina seleccionada
		    var code=$("#sede").val(); //.. y se obtiene el valor
		    
		    var val2 = 'oficinasxsedexgrupoxpadre';
		    var codigo_oficina=$("#codigo_oficina").val();
		    $("#oficina").load("obtenerListaDestinatarios.do", {cambia:val2,codigo_institucion:code,codigo_padre:codigo_oficina,personal:'personal'});
		   
		}
		
		function refresca(){
			setTimeout('showProductsOficinas_()', 500);
		}

</script>
	</head>
	<body topmargin="0" leftmargin="0" onload="fn_onLoad();">
		<html:form action="/MantPersonal" method="POST">
			<html:hidden property="tipo" />
            
			
			<input  type="hidden" id="codigo_oficina"  name="codigo_oficina"  value="<c:out value='${detallePersonal.codigo_oficina}'></c:out>" >
			<table   width="100%" cellspacing="7px." cellpadding="0">
				<tr>
					<td style="width:100%">
						<div class="TitleScreen">
							&nbsp;&nbsp;Mantenimiento de Personal
						</div>
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							
							<tr>
							  <td width="10" height="17" style="height: 10px;"></td>
							  <td width="89" align="left" valign="top" class="label">Usuario de B&uacute;squeda :							    </td>
							  <td colspan="3" valign="top">
							  	<html:text property="codigoUsuarioBuscar" onkeypress="javascript:Enter(event);"/>
							  </td>
							  <td width="365" align="left"><html:button property="btnBuscar" value="Buscar" onclick="fn_buscar();"
													style=" width: 90;" styleClass="boton"/></td>
							  <td width="12"></td>
					    	</tr>
							
							<tr>
							  <td height="10" colspan="7" style="height: 10px;"><hr /></td>
					        </tr>
							
							<tr>
								<td width="8" height="23" style="height: 10px;">&nbsp;
									
								</td>
								<td width="88" align="left">&nbsp;
									
								</td>
								<td width="8">&nbsp;
									
								</td>
								<td colspan="2" width="40%">&nbsp;
									
								</td>
								<td colspan="2" width="40%" rowspan="7">
									<div id="cuadro-anexos" style="width: 100%; height: 100%">
									<c:choose>
									<c:when test='${FFormMantPersonal.codigo_personal eq null }'>
									</c:when>
									<c:otherwise>
										<div style=" font-size : 12px; color: #5882FA; font-weight: bolder;	font-style :  normal; text-align: center; font-family : Arial, Helvetica, sans-serif;">
											Configuracion de la Firma Digital <br /><br />
											<a href="javascript:AgregarAnexos()" title="Adjuntar archivos para Firma Digital"><img src="img/usericon.png" width="32" height="32" border="0" ></img></a>
											<br /><br />
											Tipo de Firma:&nbsp;&nbsp;<html:select property="tipo_firma" styleClass="caja" >
										<html:option value="2">Sin Certificado</html:option>
										<html:option value="0">Por Certificado en Pc</html:option>
										<html:option value="1">Por Certificado en Token</html:option>
									</html:select>
										</div>
									</c:otherwise>
									</c:choose>
									
									</div>
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
								<td colspan="2" align="left" valign="middle" width="40%">
								  <html:text property="codigo_personal" style="width: 100px;" maxlength="20" styleClass="caja"	onkeypress="return validatecla('letras', this);" />
							  </td>
								
							</tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Usuario:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								<td colspan="2" align="left" valign="middle" width="40%">
                        
							<html:select property="c_usuario" styleId="c_usuario" styleClass="caja" onchange="showProducts()">														
								<logic:present name="listausuarios">
									<html:options collection="listausuarios" property="usuario" labelProperty="usuario"/>
								</logic:present>
							</html:select>

							  </td>
								
							</tr>
							
							<tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Nombres y Apellidos:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								<td colspan="2" align="left" valign="middle" width="40%">
                               
                                <c:choose>
        							<c:when test='${operacion ==  "personal"}'>
										 <select id="nombreapellido" name="nombreapellido_usuario" class="caja" >
										<option value="0">:: Personas ::</option>
									  	</select> 
	                                </c:when>
                                	<c:when test='${operacion ==  "editar"}'>
                             
	                                  <select id="nombreapellido" name="nombreapellido_usuario"  class="caja" style="width:200" >
				                        <c:choose> 
				                        	<c:when test='${not empty listanombresapell}'> 
				                         			<c:forEach items='${listanombresapell}' var='pa'>	
				                         			<option value=<c:out value='${pa.usuario}'/>> <c:out value='${pa.nombres}'/> </option>	
				                                 	</c:forEach> 
				                            </c:when> 
				                        </c:choose> 
		                              </select>   
	                              </c:when>
		   							<c:otherwise>
						                <select id="nombreapellido" name="nombreapellido_usuario"  class="caja" style="width:200" >
						                 <c:choose> 
						                 	<c:when test='${not empty listanombresapell}'> 
						                  			<c:forEach items='${listanombresapell}' var='pa'>	
						                  				<option value=<c:out value='${pa.usuario}'/>><c:out value='${pa.nombres}'/> </option>	
						                    		</c:forEach> 
						                    </c:when> 
						                 </c:choose> 
						                </select>   
			                       </c:otherwise>
    							</c:choose>
							  </td>
								
							</tr>
							
							<tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Cargo:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								<td colspan="2" align="left" valign="middle" style="width: 40%;">
                                   <html:select property="cargo_personal" styleClass="caja" >														
								<logic:present name="listacargos">
									<html:options collection="listacargos" property="codigo_cargo" labelProperty="nombrecargo"/>
								</logic:present>
							</html:select>
                            
							  </td>
								
							</tr>
							
							<tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Sede:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								
																
								<td colspan="2" align="left" valign="middle" style="width: 40%;">
								
		                            <select id="sede" class="caja"  onchange="showProductsOficinas()" name="sede" >
				                            <option value="" >::Escoja sede::</option>
					                                 <c:choose>
							                 	
								                 	<c:when test='${not empty tipo_sede}'> 
								                  			<c:forEach items='${tipo_sede}' var='pa'>
								                  				<c:if test='${pa.descripcion != "GENERAL" }'>
								                  					<option value=<c:out value='${pa.codigo}'/>><c:out value='${pa.descripcion}'/> </option>
								                  				</c:if>	
								                  					
								                    		</c:forEach> 
								                    </c:when> 
								               
				    						</c:choose>
		                                	 
									</select>
		                          </td>
							</tr>
							
                            <tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Oficina:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								<td colspan="2" align="left" valign="middle" style="width: 40%;">
								<c:choose>
        							<c:when test='${operacion ==  "personal"}'>
										 <select id="oficina" name="oficina" class="caja" >
				                        <c:choose> 
				                        	<c:when test='${not empty listaoficinas}'> 
				                         			<c:forEach items='${listaoficinas}' var='pa'>	
				                         			<option value=<c:out value='${pa.codigo_oficina}'/>> <c:out value='${pa.descripcion_oficina}'/> </option>	
				                                 	</c:forEach> 
				                            </c:when> 
				                        </c:choose> 
		                              </select>   
	                                </c:when>
                                	<c:when test='${operacion ==  "buscar"}'>
                             
	                                  <select id="oficina" name="oficina" class="caja" >
				                        <c:choose> 
				                        	<c:when test='${not empty listaoficinas}'> 
				                         			<c:forEach items='${listaoficinas}' var='pa'>	
				                         			<option value=<c:out value='${pa.codigo_oficina}'/>> <c:out value='${pa.descripcion_oficina}'/> </option>	
				                                 	</c:forEach> 
				                            </c:when> 
				                        </c:choose> 
		                              </select>   
	                              </c:when>
		   							<c:otherwise>
						                <select id="oficina" name="oficina" class="caja" >
										<option value="0" >::Seleccione oficina::</option>
									
										</select>
			                       </c:otherwise>
    							</c:choose>
								
                               
                          </td>
								
							</tr>
							
                            <tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Responsable:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								<td colspan="2" align="left" valign="middle" style="width: 40%;" class="label">
								  	<html:select property="es_responsable" styleClass="caja" >
										<html:option value="S">Si</html:option>
										<html:option value="N">No</html:option>
									</html:select>	
									
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
                                  		
							  </td>
								
							</tr>
							 <tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Validado:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								<td colspan="2" align="left" valign="middle" style="width: 40%;" class="label">
								  	<html:select property="validacion" styleClass="caja" styleId="validacion">
								  	    <html:option value="">::Escoja::</html:option>
										<html:option value="1">Si</html:option>
										<html:option value="0">No</html:option>
									</html:select>	
									
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
                                  		
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
					  
                    
					
          		 <SCRIPT language="javascript">
			          
					  //muestramensaje("<c:out value='${operacion}'/>");
					  
					  //refrescaoficinas("<c:out value='${operacion}'/>");
					  
			    </SCRIPT>    
				  </td>
				</tr>
				<tr>
					<td>
						<table width="100%" align="center" cellpadding="0" cellspacing="0">
							<tr>
							<td width="50%" height="25" align="right" valign="middle" background="img/fondoplomo8.jpg" class="label">
							Lista de Personal								
							</td>
						
						  <td width="50%" align="right" valign="middle" background="img/fondoplomo8.jpg">Exportar a:&nbsp;
						   <A  href="javascript:exportar_excel()" ><img src="img/xls.gif" border="0" height="20" alt="Exportar a Excel"></A>
						   </td>
						</tr>
							<tr>
								<td colspan="2">
									<display:table name="sessionScope.listapersonal" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										<display:column property="ACTION" title="Seleccione" media="html" style="width: 50px; text-align: center;" />
										<display:column property="codigo_personal" title="Codigo"  style="text-align: center;" />
                                        <display:column property="c_usuario"  sort="true" title="Usuario" />
										<display:column property="nombre_personal"  sort="true" title="Nombres/Apellidos" />
										<display:column property="cargo_personal" title="Cargo" />
										<display:column property="oficina" sort="true" title="Oficina" />
										<display:column property="nombre_sede" sort="true" title="Sede" />
									</display:table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<c:choose>
			   <c:when test='${operacion == "editar"}'>
			   
		                <SCRIPT language="javascript">
		                		
	                		SeleccionarCampo("sede","<c:out value='${detallePersonal.sede}'/>");
	                		SeleccionarCampo("c_usuario","<c:out value='${detallePersonal.c_usuario}'/>");
			  				SeleccionarCampo("oficina","<c:out value='${detallePersonal.codigo_oficina}'/>");
	  					  	SeleccionarCampo("codigo_cargo","<c:out value='${detallePersonal.cargo_personal}'/>");
	  					  	SeleccionarCampo("validacion","<c:out value='${detallePersonal.validacion}'/>");
	  					  	refresca();
	  					  	  
		                </SCRIPT>
		       </c:when>
		       
			</c:choose>
		</html:form>
	</body>
</html>
