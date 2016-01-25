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
		<title>Lista de Documentos</title>
		
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		<link rel="stylesheet" href="css/modelo.css" type="text/css" />
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
 	<link rel="stylesheet" href="css/tipografias.css"  type="text/css">
 	
		<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
		<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
		<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
		
        <script src="js/funciones.js" type="text/javascript"></script>
        <script src="js/scriptaculous.js" type="text/javascript" ></script>
        <script  src="js/effects.js" type="text/javascript"></script>
       
        <script type="text/javascript" src="js/ajaxtags.js"></script>
		<script language="JavaScript" src="js/avatec.js"></script>
		
		<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
		<!-- librería para cargar el lenguaje deseado -->
		<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
		<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
		<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
		
		<!-- link href="theme/css3/style.css" rel="stylesheet" type="text/css" /-->
		
		<script language="JavaScript">
		$(document).ready(function(){
		    $( "#tabs" ).tabs();
		    
		    $("#keywords").focusout(function() {
		        if($.isNumeric($(this).val())){
		            
		        }
		        else{
		            $(this).val("Buscar...");
		        }
			});
		    
		   
		});
		
				
		function Enter(e){
				if(e.keyCode==13){
				 buscar();
				}
				return;
		}
		
		function buscar(){
			
			with(document.forms[0]){
				//alert("1");
				var elem = document.getElementById("keywords");
				
		         if(elem.value==''){
				 	/*alert('Ingrese el codigo de documento a Buscar');
						  $("#form_datos").submit(function() {
						        return false;
						    });*/
		        	 var formulario = document.getElementById("form_datos");
					 
					 //alert($("#keywords").val());
					 $("#codigo_busca").val($("#keywords").val());
					 formulario.submit();
					 return true;

				 }else{
					 
					 if($.isNumeric($("#keywords").val())){
						 var formulario = document.getElementById("form_datos");
						 
						 //alert($("#keywords").val());
						 $("#codigo_busca").val($("#keywords").val());
						 formulario.submit();
						 return true;
						   	

				        }
				        else{
				        	alert('Codigo invalido');
							$("#form_datos").submit(function() {
								   return false;
							});

				        }
					
				 }
             
            }
	 		return;
		}
		
		function listar_todos(){
		 
		   var strURL = "ListaDocumentos.do?tipo=archivar&operacion=A";
		   location=strURL;
		   	return;
		 
		}
		
		function fn_onLoad(){
		    var codigo_doc=document.getElementById('codigo_doc');
		    codigo_doc.focus();
			cambiar_color_tabla("b", "tablaBackgray");
			with(document.forms[0]){						
				if(trim(tipo.value) == "editar"){
				
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
					
				btnEnviar.style.display = '';
				btnUpload.style.display = '';
				btnCancelar.style.display = '';
				
				
				//btnCancelar.style.display = 'none';		
				//dpto.style.display = 'none';
				//users.focus();
				}
				else if(trim(tipo.value) == "archivar"){
					//dpto.style.display = 'none';
					
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
				
				btnEnviar.style.display = 'none';
				btnCancelar.style.display = 'none';
				//btnUpload.style.display = 'none';
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
			$("#cuadro-docs-adjuntos").load("actualizaDocumentoAdjunto.do", {cambia:'limpiaarchiva'});
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
				//btnEnviar.style.display = 'none';	
				//btnUpload.style.display = 'none';
				//btnCancelar.style.display = 'none';
				fecha.focus();
			}
		}
		
		
		
		function fn_enviar(){
			if(confirm("Está seguro de archivar este documento?")){
				with(document.forms[0]){				
					//agregado
					vaction = action;
					action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			    tipo.value = 'enviar';	
			    //target = 'mainFrame';
				//tipo.value = 'enviar';	
				target = '';
					submit();
					//action = vaction;				
				}
			}
		}
		
		function fn_desarchivar(){
			if(confirm("Está seguro de Desarchivar este documento?")){
				with(document.forms[0]){				
					//agregado
					vaction = action;
					action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			    tipo.value = 'desarchivar';	
			    //target = 'mainFrame';
				//tipo.value = 'enviar';	
				target = '';
					submit();
					//action = vaction;				
				}
			}
		}
		
		function fn_editar_copia(pcodigo_documento,
		psecuencia_movimiento){
			with(document.forms[0]){
			 
			codigo_documento.value = pcodigo_documento;				
			secuencia_movimiento.value = psecuencia_movimiento;
				
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
				
				/*
				btnEnviar.style.display = '';	
				btnDesarchivar.style.display = '';	
				*/

			  vaction = action;
				action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
				target = '';
				tipo.value = 'editar';	
				submit();
			}
		}
		
		function fn_editar(pcodigo_documento
		,pcodigo_expediente
		,pdescripcion_tipo,pnumero_documento
		,pcodigo_oficina,pestado_movimiento,pfecha_movimiento,phora_movimiento,psecuencia_movimiento,pcodigo_inicia,pnaturaleza_documento){
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
				//observacion.value = pobserva_registro;
				
				secuencia_movimiento.value = psecuencia_movimiento;
				codigo_inicia.value = pcodigo_inicia;
				naturaleza_documento.value = pnaturaleza_documento;
				
				
				
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
				var mifecha;
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
		
		function AgregarArchivos(){
			if (Validarcampovacio(document.forms[0].codigo_documento.value) == false){              
                  alert("Ingresar Numero de Documento");
                  document.forms[0].codigo_documento.focus();
                  return false;           
            }else{
            	
            	var ls_operacion = "N";
    			var ls_codigo_documento = document.getElementById("codigo_documento").value;
    			var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
    			var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;
    			var strURL='MantArchivar.do?tipo=upload'+'&operacion='+ls_operacion+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&secuencia_movimiento='+ls_secuencia_movimiento;	
    			winPopup = window.open(strURL,"","scrollbars,HEIGHT=250,WIDTH=580");
            	
            }
			
		}
		
		function Continuar(){
			 //para actualizar el boton agregaradjunto
			 
			 $(document).ready(function(){
				 $("#cuadro-docs-adjuntos").load("actualizaDocumentoAdjunto.do", {cambia:'actualizaarchiva'});
				 
			 });
			 return;
		 }

</script>
	</head>
	<body bgColor="#F0F5FB" topmargin="0" leftmargin="0" onLoad="fn_onLoad();">
	
	<div class="derivaconta">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
		<html:form  styleId="form_datos"  action="/MantArchivar" method="POST">
			<html:hidden property="tipo" />
			<html:hidden property="codigo_oficina" />
			<html:hidden property="estado_movimiento" />
			<html:hidden property="descripcion_tipo"  />
			<html:hidden property="codigo_inicia"  />
			<html:hidden property="naturaleza_documento"  />
			<INPUT  type="hidden" id="secuencia_movimiento" name="secuencia_movimiento" value="<c:out value='${FFormMantArchivar.secuencia_movimiento}'></c:out>">
			<INPUT  type="hidden" id="codigo_busca" name="codigo_busca" >
			
			
			<div id="break" ></div>
			<div id="contactderiva" class="rounded"> 
			<div id="TDHeadCab">
				   		   	<span class="labelrounded">ARCHIVAR DOCUMENTOS </span>
							<input type="text" name="q" id="keywords"  class="input-search" onfocus="this.value=''"  onkeypress="javascript:Enter(event)" value="Buscar..." />
				   		   	<button  class="image" onclick="buscar()" title="Buscar por Nro de Registro" >Buscador</button>
			</div>	
			<div id="TDHeadCabMensaje" >
			<table width="100%" cellspacing="0" cellpadding="0"  align="left">
		
						<tr>
							<td width="409" height="18" align="LEFT" valign="middle"  background="img/fondoplomo8.jpg" class="textomensaje10bold">
									<c:out value="${mensaje_exito}"/>
							</td>
						    <td width="568" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label1" ><!--DWLayoutEmptyCell-->&nbsp;</td>
					        <td width="47" align="center" valign="middle" background="img/fondoplomo8.jpg">&nbsp;&nbsp;&nbsp;</td>
						</tr>
			</table>
			</div>
			<!-- div id="TDHeadCabBusqueda" >
			 <div style="width: 50%; height: 50px; margin-left: auto; margin-right: auto; margin-top: 5px;
				text-align: right; float:left;">
		     <p><label>Búsqueda por : &nbsp;&nbsp;&nbsp;&nbsp;</label>
		     <input type="text" name="codigo_doc" id="codigo_doc" class="input-medium" onKeyPress="javascript:Enter(event)" value="<c:out value='${codigo_doc}'/>" size="20" >
        	<script language="JavaScript" type="text/javascript">
        	if(document.getElementById) document.getElementById('codigo_doc').focus();
        	</script>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
		     
		     </div>
		     <div style="width:50%; float:right ;">
		     	<input  type="submit" name="button"  class="deriva" value="Buscar" onClick="ver_frame()" alt="buscar"  >
        		<input type="button" name="button" class="deriva" value="Listar Todos" onClick="listar_todos()" alt="Listar Todos"  >
		     </div>	
		     <br>
		     <br>
		     <br>
			 </div-->
			 <div id="Lineaform2"></div> 
			<div id="left" class="columna-left-small" >
			<p><label>N&deg; Registro:&nbsp;&nbsp;</label>
     			<html:text property="codigo_documento" style="width: 150px;" styleId="codigo_documento" styleClass="input-medium" readonly="true"/>
     		</p>
     		<p><label>N&deg; Doc.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
     		<html:text property="numero_documento" style="width: 260px;" styleClass="input" readonly="true"/>
     		</p>
     		<p><label>Hora:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
     			<html:text property="hora" style="width: 80px;" maxlength="15" styleClass="input-medium" readonly="true"/>
     		</p>
     		<p><label>Observaci&oacute;n:</label>
     			<html:textarea property="observacion" style="width: 270px;"  styleClass="textarea"/>
     		</p>
			</div>
			<div id="center"  class="columna-center-small" >&nbsp;</div>
     
	 		<div id="rigth" class ="columna-rigth-small" >
	 		<p><label>N&deg; Expediente:</label>
     			<html:text property="codigo_expediente" style="width: 150px;" styleId="codigo_expediente" styleClass="input-medium" readonly="true"/>
     		</p>
     		<p><label>Fecha:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
     		 <html:text property="fecha" style="width: 90px;" maxlength="11" styleId="fecha" styleClass="input-medium" 	onkeypress="validarCaracterFecha(this);"  readonly="true"/>			
			 &nbsp; <a href="" id="lanzador"><img  src="img/cal.gif" title="Fecha (dd/mm/yyyy)" border="0"></a> 
     		</p>
     		<div id="cuadro-docs-adjuntos">
     		<p><label>Adjuntar Doc.:</label>
				<input type="button"  id="btnUpload" name="btnUpload" onClick="AgregarArchivos()" title="Agregar Archivos" value="Agregar Archivo" class="button" >
     		</p>
     		</div>
	 		</div>
	 		
	 		<div class="center-big">
	 		<c:choose>
				<c:when test="${FFormMantArchivar.estado_movimiento==4}">
				<html:button  property="btnEnviar" value="Des-Archivar" onclick="fn_enviar();"	style=" width: 90;" styleClass="button"/>
				</c:when>
				<c:otherwise>
				<html:button  property="btnEnviar" value="Archivar" onclick="fn_enviar();"	style=" width: 90;" styleClass="button"/>
				</c:otherwise>
			</c:choose>
				<html:button property="btnCancelar" value="Cancelar" onclick="cancel();" style="width: 90;" styleClass="button"/>
	 		</div>
			<br>
			</div>
			<SCRIPT type=text/javascript>
					Calendar.setup({
						inputField     :    "fecha",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador" 
						   // el id del botón que lanzará el calendario
					});
					
					
					//fecha();
					
			</SCRIPT>
			
			<div id="break" ></div>
			<div class="derivalista">
			<div id="tabs">
			  <ul>
			    <li><a href="#tabs-1">Documentos Pendientes por Archivar</a></li>
 
			  </ul>
			  <div id="tabs-1">
			  <table width="100%" align="center" cellpadding="0" cellspacing="0" >
						  <!--DWLayoutTable-->
						
						  <tr >
								<td width="492" height="25" valign="top" background="img/fondoplomo8.jpg"><span class="textomensaje14bold">
							    <c:out value='${error}'/>
								</span></td>
						        <td width="734" align="left" valign="middle"  class="label" background="img/fondoplomo8.jpg">
								Documentos Pendientes por Archivar								</td>
						  </tr>
							<tr>
								<td height="13" colspan="2" valign="top">
									
									<display:table name="sessionScope.rs_archivar_doc" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										
										<display:column property="codigo_documento" title="N&deg; Registro" />
										<display:column property="fecha_movimiento" media="html" title="Fecha Registro" />
										<display:column property="numero_documento" title="N&deg;  Documento" />
										<display:column property="oficina_origen" title="Oficina Origen" />
										<display:column property="oficina_deriva" title="Oficina Destino" />
										<display:column property="estado_movimiento" title="Estado" />
									</display:table>								</td>
							</tr>
					  </table>
			  </div>
			  
			  </div>
			  </div>

		</html:form>
		</div>
		<!-- jsp:include page="/inc_footer.jsp"/-->
	</body>
</html>
<% } %>