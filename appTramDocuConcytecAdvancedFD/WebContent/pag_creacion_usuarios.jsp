
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Auditoria de Conexion</title>
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
       <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
        <script src="js/funciones.js" type="text/javascript"></script>
        
         <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></script>
		<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
		
		<script src="js/funciones.js" type="text/javascript"></script>



<SCRIPT src="js/calendar.js" type=text/javascript> </SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>

		
		
		
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
				if(fecha_conexion.value == ''){
					alert('Ingrese la fecha de Inicio');
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
		
		function eliminar_archivo(id,cod){
			
			alert(cod);
			if(confirm("Desea Eliminar este archivo?")){
			location = "Busquedas.do?tipo=eli_archivo_admin&id="+id+"&cod="+cod;
			}

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
		<html:form action="/MantConexion" method="POST">
			<html:hidden property="tipo" />
            
			
			<table   width="100%" cellspacing="7px." cellpadding="0">
				<tr>
					<td style="width:100%">
						<div class="TitleScreen">
							&nbsp;&nbsp;Auditoria de Conexion
						</div>
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							
							<tr>
							  <td width="10" height="17" style="height: 10px;"></td>
							  <td colspan="3" valign="top" >
						<p>
								<label>Fecha
									Inicio.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									 <INPUT
									class="input-medium" onKeyPress="validarCaracterFecha(this);"
									id="fecha_conexion" style="width: 90px;" maxLength="10" property="fecha_conexion"
									size="11" name="fecha_conexion"
									value="<c:out value='${fecha}'/>"> &nbsp; <a href=""
									id="lanzador"><img src="img/cal.gif"
									alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a>
							</p>

						    </td>
							  <td colspan="3" valign="top">
							  	<p>
								<label>Fecha
									Fin.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <INPUT
									class="input-medium" onKeyPress="validarCaracterFecha(this);"
									id="fecha_conexion_fin" style="width: 90px;" maxLength="10" property="Fecha_conexion_fin"
									size="11" name="fecha_conexion_fin"
									value="<c:out value='${fecha}'/>"> &nbsp; <a href=""
									id="lanzador1"><img src="img/cal.gif"
									alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a>
							</p>
							  </td>
							  <td width="365" align="left"><html:button property="btnBuscar" value="Buscar" onclick="fn_buscar();"
													style=" width: 90;" styleClass="boton"/></td>
							  <td width="12"></td>
					    	</tr>
							
							<tr>
							  <td height="10" colspan="7" style="height: 10px;"><hr /></td>
					        </tr>
							
							
							
							
					  </table>
					  
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
							Lista de Conexion								
							</td>
						
						  <td width="50%" align="right" valign="middle" background="img/fondoplomo8.jpg">&nbsp;
						  
						   </td>
						</tr>
							<tr>
								<td colspan="2">
									<table width="100%" border="1" cellpadding="0" cellspacing="0">
					  <!--DWLayoutTable-->
    <tr > 
      <td width="60" height="22" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">Usuario      </td>
      <td width="300" height="22" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">Datos      </td>
      <td width="319" align="center" valign="middle" class="Columna10"  background="img/fondoplomo8.gif">Fecha de Conexion      </td>
	  <td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Nombre Maquina</td>
    	<td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">IP Maquina</td>
    	
    </tr>
    <c:choose>
    
     <c:when test='${not empty rs_lista_conexion}'>
     
      <c:forEach items='${rs_lista_conexion}' var='pr'> 
   
      <tr><td height="18" align="center" valign="middle"  class="Columna"> <font color="#000000"> 
        <c:out value='${pr.usuario}'/> 
        </font> </td>
        <td height="18" align="center" valign="middle"  class="Columna"> <font color="#000000"> 
        <c:out value='${pr.apellidos}'/>,  <c:out value='${pr.nombres}'/> 
        </font> </td>
        <td height="18" align="center" valign="middle"  class="Columna"> <font color="#000000"> 
        <c:out value='${pr.fecha_conexion}'/> 
        </font> </td>
        <td height="18" align="center" valign="middle"  class="Columna"> <font color="#000000"> 
        <c:out value='${pr.host}'/> 
        </font> </td>
        <td height="18" align="center" valign="middle"  class="Columna"> <font color="#000000"> 
        <c:out value='${pr.ip}'/> 
        </font> </td>
       </tr>
    </c:forEach>
     </c:when> 
    </c:choose> 
    <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
  </table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			
		</html:form>
	</body>
	<SCRIPT language="javascript">
			Calendar.setup({
    			inputField     :    "fecha_conexion",      // id del campo de texto
    			ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
   				 button         :    "lanzador" 

			});

 			Calendar.setup({
					inputField     :    "fecha_conexion_fin",      // id del campo de texto
					ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
					button         :    "lanzador1" 

				});
 			
 			</script>
</html>
