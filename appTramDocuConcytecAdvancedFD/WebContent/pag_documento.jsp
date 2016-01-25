
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Mantenimiento de Documento</title>
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
					alert('Ingrese el Nro de Rgistro');
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
			
			//alert(cod);
			if(confirm("Desea Eliminar este archivo?")){
			location = "Busquedas.do?tipo=eli_archivo_admin&id="+id+"&cod="+cod;
			}

			} 
		
function modificar_archivo(id,cod){
			
			//alert(cod);
			if(confirm("Desea Modificar este archivo?")){
				$("#theFile").click();	
				
				$("#id_detalle_d").val(id);
			//location = "Busquedas.do?tipo=eli_archivo_admin&id="+id+"&cod="+cod;
			}

			} 
		
function Aceptar(){
    
	var ls_operacion = "UADMIN";
	
	 // alert(ls_operacion);
	var nombre_archivo = document.getElementById("theFile").value;
	var id_det = document.getElementById("id_detalle_d").value;
	
	if (Validarcampovacio(document.form_datos.theFile.value) == false){              
		document.form_datos.theFile.focus();
		return false;           
    }
	 		   
	document.form_datos.action='MantDocumento.do'+'?operacion='+ls_operacion+'?id_det='+id_det;
	document.form_datos.submit();	
	
//alert('El archivo '+ nombre_archivo+' que ha ingresado presenta caracteres no validos');
//window.close();

}		
		
		
		function Continuar(){
			return;
		}
		
		function AgregarAnexos(){
			
			var strURL='AgregarImagen.do?operacion=UP&tipo=I';	
			winPopup =  window.open(strURL,"","WIDTH=600,HEIGHT=300,scrollbars=yes,resizable=yes");
			
		}
		
		
		function selectAll(combo, status){
			for(i=0; i<combo.options.length; i++){
				combo.options[i].selected = status;
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
		
		
		
		function refresca(){
			setTimeout('showProductsOficinas_()', 500);
		}

</script>
	</head>
	<body topmargin="0" leftmargin="0" onload="fn_onLoad();">
		<html:form  action="/MantDocumentoA" method="POST">
			<html:hidden property="tipo" />
            <input  type="hidden" id="id_detalle_d"  name="id_detalle_d"  value="" >
			<input size="50" type="file" name="theFile" id="theFile" class="caja" style="display:none" onchange="Aceptar();">
			<input  type="hidden" id="codigo_oficina"  name="codigo_oficina"  value="<c:out value='${detallePersonal.codigo_oficina}'></c:out>" >
			<table   width="100%" cellspacing="7px." cellpadding="0">
				<tr>
					<td style="width:100%">
						<div class="TitleScreen">
							&nbsp;&nbsp;Mantenimiento de Documento
						</div>
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							
							<tr>
							  <td width="10" height="17" style="height: 10px;"></td>
							  <td colspan="3" width="89" align="left" valign="top" class="label">Nro. de Registro :							    </td>
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
							Lista de Documentos								
							</td>
						
						  <td width="50%" align="right" valign="middle" background="img/fondoplomo8.jpg">&nbsp;
						  
						   </td>
						</tr>
							<tr>
								<td colspan="2">
									<table width="100%" border="1" cellpadding="0" cellspacing="0">
					  <!--DWLayoutTable-->
    <tr > 
      <td width="60" height="22" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">C&oacute;digo      </td>
      <td width="619" align="center" valign="middle" class="Columna10"  background="img/fondoplomo8.gif">Nombre del Archivo      </td>
	  <td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Adjuntos</td>
    	<td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Oficina</td>
    	<td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Modificar</td>
    	<td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Eliminar</td>
    </tr>
    <c:choose>
    
     <c:when test='${not empty rs_upload_doc}'>
     
      <c:forEach items='${rs_upload_doc}' var='pr'> 
   
      <tr><td height="18" align="center" valign="middle"  class="Columna"> <font color="#000000"> 
        <c:out value='${pr.contador}'/> 
        </font> </td>
      <td align="left" valign="middle" class="Columna">&nbsp;&nbsp;&nbsp; 
        <c:out value='${pr.nombre_archivo}'/> </td>
  
		   <td align="center" valign="middle" class="Columna">   <c:choose>
		     <c:when test='${pr.nombre_archivo ==  ""}'>
		       ------		  
		     </c:when>
		     <c:otherwise>
  <a target="_blank" href="ListaDocumentos.do?tipo=uploaddoc&iddetupload=<c:out value='${pr.id_det_upload}'/>&nombre_archivo=<c:out value='${pr.nombre_archivo}'/>&fileroute=<c:out value='${pr.ruta_archivo}'/>">Ver Archivo</a></c:otherwise>
		            </c:choose>	  </td>
		            
		     <td align="center" valign="middle" class="Columna">   <c:choose>
		     <c:when test='${pr.descripcion_oficina ==  ""}'>
		       ------		  
		     </c:when>
		     <c:otherwise>
  				<c:out value='${pr.descripcion_oficina}'/></c:otherwise>
		            </c:choose>	  </td>
		            <td align="center" valign="middle"><a href="javascript:modificar_archivo(<c:out value='${pr.id_det_upload}'/>,<c:out value='${pr.codigo_documento}'/>)"><img src="img/editardoc.png" border="0" height="24" alt="Eliminar Linea."></a></td>		
  			<td align="center" valign="middle"><a href="javascript:eliminar_archivo(<c:out value='${pr.id_det_upload}'/>,<c:out value='${pr.codigo_documento}'/>)"><img src="img/trash.png" border="0" height="24" alt="Eliminar Linea."></a></td>		
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
</html>
