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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
		<title>Mantenimiento de Oficinas</title>
		
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
        <script src="js/funciones.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/scriptaculous.js"></script>
        <script type="text/javascript" src="js/overlibmws.js"></script>
        <script type="text/javascript" src="js/ajaxtags.js"></script>
		<script language="JavaScript" src="js/avatec.js"></script>
		
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></script>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />

		<script language="JavaScript">
		
		function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray");
			with(document.forms[0]){						
				if(trim(tipo.value) == "editar"){
					codigo_oficina.readOnly = true;
					
					btnEliminar.style.display = '';
					//dpto.style.display = 'none';
					//users.focus();
				}
				else{
					//dpto.style.display = 'none';
					codigo_oficina.readOnly = true;
					descripcion_oficina.value = '';
					nombre_corto.value = '';
					siglas_oficina.value = '';
					es_activo.value = '';
					
					btnEliminar.style.display = 'none';
					descripcion_oficina.focus();
				}
			}
		}
		function AgregarImagen(){
			var value = $("#codigodelaoficina").val();
			var strURL='AgregarImagen.do?operacion=UPO&tipo=I&cod='+value;	
			winPopup =  window.open(strURL,"","WIDTH=600,HEIGHT=200,scrollbars=yes,resizable=yes");
			
		}
		
		function CargarFoto(img, ancho, alto){
			  derecha=(screen.width-ancho)/2;
			  arriba=(screen.height-alto)/2;
			  string="toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width="+ancho+",height="+alto+",left="+derecha+",top="+arriba+"";
			  var strURL='AgregarImagen.do?operacion=UPO&tipo=I';
			  winPopup =  window.open(strURL,"",string);
			}
		
		function EliminarImagen(){
			if(confirm("Está seguro de eliminar esta imagen?")){	
			
			$(document).ready(function(){
				
				var value = $("#codigodelaoficina").val();
				if(value==null){
					return;
				}
				var operacion = 'eliminarnombreoficina';
				    //llama al servlet con el parametro seleccionado
				    $("#div_frame").load("obtenerLista.do",{cambia:operacion,ls_texto:value});

			});
			
			}
		}
		
		function Continuar(){
			//alert("hola");
			$(document).ready(function(){
				
			var value = $("#codigodelaoficina").val();
			if(value==null){
				return;
			}
			var operacion = 'nombreoficina';
			    //llama al servlet con el parametro seleccionado
			    $("#div_frame").load("obtenerLista.do",{cambia:operacion,ls_texto:value});

		});
			
		}
		
		function fn_grabar(){
			with(document.forms[0]){
				
				
				if(trim(sede.value) == ""){
					alert("Ingrese la sede");
					sede.focus();
					return;
				}
				
				if(trim(descripcion_oficina.value) == ""){
					alert("Ingrese la descripcion oficina");
					descripcion_oficina.focus();
					return;
				}
				
				if(trim(nombre_corto.value) == ""){
					alert("Ingrese el nombre corto");
					nombre_corto.focus();
					return;
				}
				
				if(trim(siglas_oficina.value) == ""){
					alert("Ingrese siglas");
					siglas_oficina.focus();
					return;
				}
				if(trim(es_activo.value) == ""){
					alert("Ingrese estado");
					es_activo.focus();
					return;
				}
				
				//alert(oficina.value);
				
				if(trim(oficina.value) == "0"){
					var r = confirm("Se creara una oficina padre.");
					if (r == true) {
					   //alert("You pressed OK!");
					} else {
					    //alert("You pressed Cancel!");
					    oficina.focus();
						return;
					}
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
				codigo_oficina.value = '';			
				descripcion_oficina.value = '';
				nombre_corto.value = '';
				siglas_oficina.value = '';
				es_activo.value = '';
				codigo_oficina.readOnly = true;
				
				//removeElements(perfilSeleccionados);
				btnEliminar.style.display = 'none';						
				descripcion_oficina.focus();
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
		
		function fn_editar(pcodigo_oficina,pdescripcion_oficina,pnombre_corto,psiglas_oficina,pes_activo){
			with(document.forms[0]){
			
				codigo_oficina.value = pcodigo_oficina;
				descripcion_oficina.value = pdescripcion_oficina;
				nombre_corto.value = pnombre_corto;
				siglas_oficina.value = psiglas_oficina;
				es_activo.value = pes_activo;
				
				codigo_oficina.readOnly = true;
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
		
		
		function espadre(valor){
			var valor_recibido = valor;

			if(valor_recibido == '0'){
				
			}else
				refresca();
						
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
		
		function showProductsOficinas_(){
			//esta me traera la lista con la oficina seleccionada
		    var code=$("#sede").val(); //.. y se obtiene el valor
		    var val2 = 'oficinasxsedexgrupoxpadre';
		    var codeofipadre=$("#codofi").val();
		    $("#oficina").load("obtenerListaDestinatarios.do", {cambia:val2,codigo_institucion:code,codigo_padre:codeofipadre});
		   
		}
		
		function showProductsSubOficinas(){
			//obtiene los objetos productCode, 
			
		    var code=$("#oficina").val(); //.. y se obtiene el valor
		    //var pertenece_a_ls=$("#pertenece_a").val();
		    var val2 = 'showSubOficinas';
		    //var externo =$("#rb_seleccion").val(); 
		    //llama al servlet con el parametro seleccionado
		    $("#ayuda_oficinas").load("obtenerListaDestinatarios.do", {cambia:val2,codigo_institucion:code});
		    //refresca();
		    
		    //document.forms[0].personas.selectedIndex=0;
			//document.forms[0].personas.length=1;
		}
		
		function refresca(){
			setTimeout('showProductsOficinas_()', 500);
		}
		
		function muestramensaje(valor){
			alert(valor);
		}
</script>
	</head>
	<body topmargin="0" leftmargin="0" onload="fn_onLoad();">
		<html:form action="/MantOficinas" method="POST">
			
			<html:hidden property="tipo" />
			<input  type="hidden" id="codigodelaoficina"  value="<c:out value='${FFormMantOficinas.codigo_oficina}'></c:out>" >
			<input  type="hidden" id="codofi"  name="codofi"  value="<c:out value='${detalleOficinas.padre}'></c:out>" >
			
			<table width="100%" cellspacing="7px" cellpadding="0" >
				<tr>
					<td style="width:100%">
						<div class="TitleScreen">
							&nbsp;&nbsp;Mantenimiento de Oficinas
						</div>
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell" >
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
								<td  colspan="2" width="40%" rowspan="7">&nbsp;
									<div id="cuadro-anexos" style="width: 100%; height: 100%">
									<c:choose>
									<c:when test='${FFormMantOficinas.codigo_oficina eq null }'>
									<div id="ayuda_oficinas" style=" font-size : 12px; color: #5882FA; font-weight: bolder;	font-style : normal; text-align: center; font-family : Arial, Helvetica, sans-serif;">
									::SUB-OFICINAS:: <br /><br /></div>
									
									</c:when>
									<c:otherwise>
									<div style=" font-size : 12px; color: #5882FA; font-weight: bolder;	font-style : normal; text-align: center; font-family : Arial, Helvetica, sans-serif;">
										Configuración del Sello de la Oficina <br /><br />
										<a  href="javascript:AgregarImagen()"><img src="img/stamp.png" title="Agregar Sello" width="32" height="32" border="0" ></img></a>
									</div>
									<div id="div_frame" style=" overflow:auto; font-size : 10px;  font-style : normal; text-align: center; font-family : Arial, Helvetica, sans-serif;" >
									Archivo cargado: <br>
									<c:choose>
									<c:when test='${nombreArchivoSelloOficina eq null}'>
									No hay archivos cargados
									</c:when>
									<c:otherwise>
									<c:out value="${nombreArchivoSelloOficina}"></c:out>&nbsp;&nbsp;
									<a title="Eliminar Imagen" href="javascript:EliminarImagen()">X</a>
									</c:otherwise>
									</c:choose>
																		
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
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								  <html:text property="codigo_oficina" style="width: 100px;" maxlength="20" styleClass="caja"
										onkeypress="return validatecla('letras', this);" />
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
								
		                            <select name="sede" class="caja"  id="sede" onchange="showProductsOficinas()">
										  	 
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
									Oficina padre:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								
																
								<td colspan="2" align="left" valign="middle" style="width: 40%;">
								
		                           <select id="oficina" name="oficina" class="caja" onchange="showProductsSubOficinas()" >
		                           		<option value="0" >::Seleccione oficina::</option>
										<!-- <c:choose>
						                 	<c:when test='${not empty listaoficinasxsede}'> 
						                  			<c:forEach items='${listaoficinasxsede}' var='pa'>	
						                  				<option value=<c:out value='${pa.codigo}'/>><c:out value='${pa.descripcion}'/> </option>	
						                    		</c:forEach> 
						                    </c:when> 
						               
		    							</c:choose> -->
									</select>
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
								  <html:text property="descripcion_oficina" style="width: 330px;" maxlength="90" styleClass="caja"
										onkeypress="return validatecla('letras', this);" />
							  </td>
								
							</tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Nombre Corto:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								  <html:text property="nombre_corto" style="width: 260px;" maxlength="45" styleClass="caja"
										onkeypress="return validatecla('letras', this);" />
							  </td>
								
							</tr>
							<tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Siglas:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
								  <html:text property="siglas_oficina" style="width: 200px;" maxlength="20" styleClass="caja"
										onkeypress="return validatecla('letras', this);" />
							  </td>
								
							</tr>
                            <tr>
								<td height="23" style="width: 10px;">&nbsp;
									
								</td>
								<td style="width: 90px;" class="label" align="left">
									Estado:
								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;
									
								</td>
								<td colspan="2" align="left" valign="middle" style="width: 400px;">
					<html:select property="es_activo" styleClass="caja" >
										<html:option value="A">Activo</html:option>
										<html:option value="I">Inactivo</html:option>
									</html:select>			
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
								
							</tr>
							<tr>
								<td height="31" colspan="7" align="center">
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
								
							</tr>
					  </table>
                   
          		 <SCRIPT language="javascript">
			          SeleccionarCampo("es_activo","<c:out value='${detalleOficinas.es_activo}'/>");
			          			          
			          //muestramensaje("<c:out value='${operacion}'/>");
			    </SCRIPT>    
				  </td>
				</tr>
				<tr>
					<td>
						<table width="100%" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<display:table name="sessionScope.listaoficinas" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										<display:column property="ACTION" title="Seleccione" media="html" style="width: 50px; text-align: center;" />
										<display:column property="codigo_oficina" title="Codigo" style="width: 50px; text-align: center;" />
										<display:column property="descripcion_oficina" title="Descripci&oacute;n" />
										<display:column property="tipo_sede" sort="true" title="Sede" />
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
		                  	SeleccionarCampo("sede","<c:out value='${detalleOficinas.sede}'/>"); 
		                  	espadre("<c:out value='${detalleOficinas.padre}'/>");
		                	
		                </SCRIPT>
		       </c:when>
		       
			</c:choose>
		</html:form>
	</body>
</html>
<% 
}
%>
