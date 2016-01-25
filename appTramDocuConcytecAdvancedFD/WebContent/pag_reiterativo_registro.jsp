<%@ include file="taglibs.jsp"%>
<% 
//Verifica que exista una sesion
String nombreusuario = String.valueOf(session.getAttribute("nombreusuario"));
//System.out.println("el nombre de usuario es.."+nombreusuario);
if (nombreusuario==null || nombreusuario.equals("null")){
%>
<SCRIPT language="Javascript">
	parent.document.location = "pag_expiracion.jsp";
</SCRIPT>
<% 
}
else {
%>
<html>
<head>
<title>Registrar Movimiento</title>

		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="css/modelo.css" type="text/css" />
		<link rel="stylesheet" href="css/tipografias.css"  type="text/css">
		
		<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
		<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
		<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
		
		
        <script src="js/funciones.js" type="text/javascript"></script>
        <!-- script type="text/javascript" src="js/scriptaculous.js"></script-->
        
        <!-- script type="text/javascript" src="js/ajaxtags.js"></script-->
        <script language="JavaScript" src="js/avatec.js"></script>
		
		
		<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
		<!-- librería para cargar el lenguaje deseado -->
		<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
		<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
		<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>

<script language="JavaScript">
$(document).ready(function(){
	$( "#tabs" ).tabs();
	showInstituciones();
});

function fn_onload(){
  //alert(<c:out value='${FFormReiterativoRegistro.cantidadArchivos}' />);
  cambiar_color_tabla("b", "tablaBackgray");
  //MostrarAdjunto(<c:out value='${FFormReiterativoRegistro.cantidadArchivos}' />);
}
function showProducts(){
    //obtiene los objetos productCode, 
    var code=$("#codigo_oficina").val(); //.. y se obtiene el valor
    var val2 = 'ofiInterno';
    //llama al servlet con el parametro seleccionado
    $("#personas").load("obtenerListaDestinatarios.do", {cambia:val2,codigo_oficina:code});
}
function SeleccionarCampo(campo, valor){
	//alert("campo -"+campo+", valor -"+valor);
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
function MostrarAdjunto(cantidad){
	if(cantidad > 0){
		$(document).ready(function(){
			 var codigo_documento = document.forms[0].codigo_documento.value;
			 $("#cuadro-docs-adjuntos").load("actualizaDocumentoAdjunto.do", {cambia:'actualizareitera'});
		 });
	}else{
		$(document).ready(function(){
			 $("#cuadro-docs-adjuntos").load("actualizaDocumentoAdjunto.do", {cambia:'actualizareitera',operacion:'limpia'});
		 });
	}
	
	 return;
	
	/*
	var icono=document.getElementById("icono");
	if(cantidad==0){
			//Ocultar ICONO		
		icono.style.display='none';
	}else{
		//Mostrar ICONO
		icono.style.display='';
	}*/
}
function regresar(codigo_documento){
		var url = 'Reiterativo.do';		
		window.location = url;
		}
function AgregarDocInternos() {

	//alert("Internos");
	
	var strURL="MantenimientoDocumentoInterno.do";
	winPopup = window.open(strURL,"","scrollbars,HEIGHT=540,WIDTH=700");
 }		
 
function showInstituciones(){
    //obtiene los objetos productCode, 
    var code=$("#codigo_intitucion").val(); //.. y se obtiene el valor
    var pertenece_a_ls=$("#pertenece_a").val();
    var val2 = 'institucion';
    var externo =$("#rb_seleccion").val(); 
    //llama al servlet con el parametro seleccionado
    $("#codigo_oficina").load("obtenerListaDestinatarios.do", {cambia:val2,codigo_institucion:code,pertenece:pertenece_a_ls,externo_ls:externo});
    refresca();
    
    //document.forms[0].personas.selectedIndex=0;
	//document.forms[0].personas.length=1;
    
}
 
function refresca(){
	
	timer = setTimeout('showProducts()', 500);
    
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
	
    function registrar(){
	   with(document.forms[0]){
		   
		   opcion.value = 'registrar';
					var cumple=true;
					
					if(trim(codigo_intitucion.value)== 0){
						alert("Ingresar Institucion");
						codigo_intitucion.focus();
						cumple=false;
					}else	if(trim(codigo_oficina.value) == 0){
					alert("Se requiere un valor");
					codigo_oficina.focus();
					cumple=false;
					}else if(trim(codigo_motivo.value) == 0){
					alert("Se requiere un valor");
					codigo_motivo.focus();
					cumple=false;					
					}else if(trim(personas.value) == 0){
					alert("Se requiere un valor");
					personas.focus();
					cumple=false;					
					}else if(trim(observacion.value) == 0){
					alert("Se requiere un valor");
					observacion.focus();
					cumple=false;					
					}else{
						var ls_codigo_persona_session=<c:out value='${codigo_persona}'/>;
						var ls_codigo_personal_pag=personas.value;
						if(ls_codigo_persona_session == ls_codigo_personal_pag){						
					       alert("El documento no puede ser derivado a la misma persona...!");
					       personas.focus();
					       cumple=false;
						}
					}
					
					if(cumple==true){
					   if(confirm("Está seguro de derivar este documento?")){
					      submit();	
					    }
					}else{
						btn.value='Registrar';
						btn.disabled=false;
					}
					 		
				}
	}
	function AgregarArchivos() {

		var codigo_documento = document.forms[0].codigo_documento.value;
		var strURL = 'UploadArchivo.do?codigo_documento=' + codigo_documento
				+ '&opcion=default';
		winPopup = window.open(strURL, "", "scrollbars,HEIGHT=250,WIDTH=580");

	}
	
	function HabilitaText() {
		with (document.forms[0]) {

			if (cbo_fecharpta.checked) {

				fecha_rpta.disabled = false;
				lanzador1.style.display = '';

			} else {

				fecha_rpta.disabled = true;
				lanzador1.style.display = 'none';
				fecha_rpta.value = "";

			}
		}
	}
	
	function HabilitaCombo(valor){
		 var valor_ls=valor;
		   if(valor_ls==1){
		   var scroll_chks = document.getElementById('scroll');
				with(document.forms[0]){
				   if (chk_copia.checked) {
						scroll_chks.style.visibility = 'visible';
				   }
				   else{			
					scroll_chks.style.visibility = 'hidden'; 
				   }
				 }
		   }else{
			   var scroll_chks_s = document.getElementById('scroll_2');
				with(document.forms[0]){
				   if (chk_copia_other.checked) {
					   scroll_chks_s.style.visibility = 'visible';
				   }
				   else{			
					   scroll_chks_s.style.visibility = 'hidden'; 
				   }
				 }
		   }		
	
		}
</script>
</head>
<body bgColor="#F0F5FB" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" onLoad="fn_onload();">
<div class="derivaconta">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
	<html:form action="ReiterativoRegistro">
		<html:hidden property="naturaleza_documento" />
		<html:hidden property="opcion" />
		<INPUT  type="hidden" id="pertenece_a" name="pertenece_a" value="<c:out value='${pertenece_a}'/>">
		<div id="break" >
		
		
		</div>
		<div id="contactderiva" class="rounded"> 
		<div id="TDHeadCab">
						   		   	<span class="labelrounded">REGISTRAR MOVIMIENTO</span>
						   		   	
		</div>
		
		
		<div id="Lineaform2" >
		<c:if test="${mensaje_usuario!=null}">
		<table>
		<tr>
			    <td height="2" colspan="9" align="center"><table align="center" width="100%">
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
			  
			  </c:if>
		</div>
			
				    <div id="left" class="columna-left2" > 
				    	<p><label>N&deg; Registro:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    		<input id="codigo_documento" name="codigo_documento" value="<c:out value='${FFormReiterativoRegistro.codigo_documento}'/>" readonly="readonly" style="width: 150px;" class="input-medium" />                
				    	</p>
				    	<p><label>N&uacute;mero Doc.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
               			<input name="numero_documento" value="<c:out value='${FFormReiterativoRegistro.numero_documento}'/>" readonly="readonly" style="width: 300px;" class="input" />
				    	</p>
				    	<!-- JAZANERO -->
					     <p><label>Institucion <br>Destino:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
						     <select id="codigo_intitucion" name="codigo_intitucion" class="input-medium"  onchange="showInstituciones()">
												<OPTION value="0" selected> -- Selecc Uno -- </OPTION>
												<OPTION value="2" >CONCYTEC</OPTION>
												
							</select> 
					     </p>
				    	<p><label>Oficina:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    		<c:choose>
												<c:when test='${operacion ==  "derivar"}'>
													<select id="codigo_oficina" name="codigo_oficina"
														class="input"   onchange="showProducts()">
														<OPTION value="0" selected>-- Selecc Uno --</OPTION>
														<c:choose>
														<c:when test='${not empty rs_oficina}'> 
															<c:forEach items='${rs_oficina}' var='pa'>
															<c:choose>
																	 <c:when test='${es_padre == "0" }'>
																			<c:choose>
																			<c:when test="${pa.padre == 0 || pa.grupo_oficina_combo==1}">
																					<option value=<c:out value='${pa.codigo_oficina}'/>><c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/></option>
																			</c:when>
																			</c:choose>
																	 </c:when>
																	 <c:otherwise>
																	 <c:choose>
																		 <c:when test="${pa.grupo_oficina_combo==1}">
																					<option value=<c:out value='${pa.codigo_oficina}'/>><c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/></option>
																		</c:when>
																	 </c:choose>									 
																	 </c:otherwise>
																			 								 								 
															</c:choose>
																 
														 	</c:forEach> 
														</c:when>
														</c:choose>
														
													</select>
												</c:when>
												
												
												<c:when test='${operacion ==  "editar"}'>
													<select id="codigo_oficina" name="codigo_oficina"
														class="input"   onchange="showProducts()">
														<OPTION value="0" selected>-- Selecc Uno --</OPTION>
														<c:choose>
														<c:when test='${not empty rs_oficina}'> 
															<c:forEach items='${rs_oficina}' var='pa'>
															<c:choose>
																	 <c:when test='${es_padre == "0" }'>
																			<c:choose>
																			<c:when test="${pa.padre == 0 || pa.grupo_oficina_combo==1}">
																					<option value=<c:out value='${pa.codigo_oficina}'/>><c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/></option>
																			</c:when>
																			</c:choose>
																	 </c:when>
																	 <c:otherwise>
																	 <c:choose>
																		 <c:when test="${pa.grupo_oficina_combo==1}">
																					<option value=<c:out value='${pa.codigo_oficina}'/>><c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/></option>
																		</c:when>
																	 </c:choose>									 
																	 </c:otherwise>
																			 								 								 
															</c:choose>
																 
														 	</c:forEach> 
														</c:when>
														</c:choose>
													</select>
												</c:when>
												
												
												<c:otherwise>
													<select id="codigo_oficina" name="codigo_oficina"
														class="input"   onchange="showProducts()">
														<OPTION value="0" selected>-- Selecc Uno --</OPTION>
														<c:choose>
															<c:when test='${not empty rs_oficina}'> 
																<c:forEach items='${rs_oficina}' var='pa'>
																<c:choose>
																		 <c:when test='${es_padre == "0" }'>
																				<c:choose>
																				<c:when test="${pa.padre == 0 || pa.grupo_oficina_combo==1}">
																						<option value=<c:out value='${pa.codigo_oficina}'/>><c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/></option>
																				</c:when>
																				</c:choose>
																		 </c:when>
																		 <c:otherwise>
																		 <c:choose>
																			 <c:when test="${pa.grupo_oficina_combo==1}">
																						<option value=<c:out value='${pa.codigo_oficina}'/>><c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/></option>
																			</c:when>
																		 </c:choose>									 
																		 </c:otherwise>
																				 								 								 
																</c:choose>
																	 
															 	</c:forEach> 
															</c:when>
														</c:choose>
													</select>
												</c:otherwise>
											</c:choose>
				    	</p>
				    	<p><label>Personal:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<INPUT type="hidden" id="oper" name="oper" value="<c:out value='${operacion}'/>">
						<INPUT type="hidden" id="per" name="per" value="<c:out value='${personas}'/>"> 
						<INPUT	type="hidden" id="ofi" name="ofi" value="<c:out value='${codigo_oficina}'/>"> 
						<INPUT	type="hidden" id="contador" name="contador" 	value="<c:out value='${codigocontador}'/>"> 
						<select id="personas" name="personas" class="input">
								<option value="0">:: Personas ::</option>
								<c:choose>
									<c:when test='${not empty rs_personas}'>
										<c:forEach items='${rs_personas}' var='pa'>
											<option value=<c:out value='${pa.codigo_personal}'/>>
												<c:out value='${pa.nombre_personal}' />
											</option>
										</c:forEach>
									</c:when>
								</c:choose>
						</select>
				    	</p>
				    	
				    	
				    	<p><label>Requiere Prioridad:</label>
				    	<input  type="checkbox" class="caja" name="cbo_fecharpta"	id="cbo_fecharpta" value="checkbox" onClick="javascript:HabilitaText();">
							<label>&nbsp;&nbsp;&nbsp;Fecha Requerida:</label>
							 <c:choose>
					            <c:when test='${codigocontador ==  "1"}'>
					              <INPUT  class="input-medium" onKeyPress="validarCaracterFecha(this);" style="width: 80px;" id="fecha_rpta"  maxLength="10" size="11" name="fecha_rpta" value="<c:out value='${fecha_rpta}'/>">
					            </c:when>
					            <c:otherwise>
					              <INPUT  class="input-medium" onKeyPress="validarCaracterFecha(this);" style="width: 80px;" id="fecha_rpta"  maxLength="10" size="11" name="fecha_rpta">
					            </c:otherwise>
					     	</c:choose>
							&nbsp; <a href="" id="lanzador1"  style="display: none; width: 20;" ><img src="img/cal.gif" title="Fecha de Respuesta (dd/mm/yyyy)" border="0"></a>
				    	</p>
				    	<p><label>Observaci&oacute;n:</label> 
				    		<html:textarea	property="observacion" style="WIDTH: 330px; HEIGHT: 70px" styleClass="textarea" />
				    	</p>
				    	<p><label>Fecha Rpta.:</label>
				    	<input  class="input-medium" style="width: 90px;" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta_rq"	maxlength="10" size="11" name="fecha_rpta_rq"> &nbsp;
						<a href="" id="lanzador2"><img src="img/cal.gif" title="Fecha de Respuesta (dd/mm/yyyy)" border="0"></a>
						</p>
				    	
				    	<div id="cuadro-docs-adjuntos" style="float:left;">
			            	<p><label>Doc. Reiterativo:</label>
			            	<input  class="input-medium"  id="doc_resp"  maxlength="60" size="40" name="doc_resp" value="<c:out value='${doc_resp_ls}'/>">
			            	<input type="button"  id="btnUpload" name="btnUpload"  value="Agregar Archivos" onClick="AgregarArchivos()" title="Agregar Archivos"  class="button" >
							
							<c:choose>
								<c:when test='${operacion ==  "contadjunto" && codigocontador ==  "1"}'>
									<img src='img/attach2icon.png' title='Documentos Adjuntos'>				
								</c:when>
							</c:choose>
							</p>
						</div>
				    	
				    </div>
				    
				    <div id="center"  class="columna-center3" >&nbsp;</div>
				    
			 		<div id="rigth" class ="columna-rigth3" >
			 		<p><label>Expediente:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			 		<input name="codigo_expediente" value="<c:out value='${FFormReiterativoRegistro.codigo_expediente}'/>" readonly="readonly" style="width: 150px;" class="input-medium" /></p>
			 		
			 		<p><label>Fecha	Derivaci&oacute;n:</label>
			 		<input type="text" name="fecha" value="<c:out value='${FFormReiterativoRegistro.fecha}'/>" style="width: 80px;"  maxlength="11" onkeypress="validarCaracterFecha(this);" readonly="readonly" class="input-medium"/>
			 		</p>
			 		
			 		<p><label>Acci&oacute;n a Realizar:</label>
				    		<c:choose>
												<c:when test='${operacion ==  "derivar"}'>
													<select id="codigo_motivo" name="codigo_motivo"
														class="input" style="width: 200px;" >
														<OPTION value="0" selected>-- Selecc Uno --</OPTION>
														<c:choose>
															<c:when test='${not empty rs_motivos}'>
																<c:forEach items='${rs_motivos}' var='pa'>
																	<option value=<c:out value='${pa.codigo_motivo}'/>>
																		<c:out value='${pa.descripcion_motivo}' />
																	</option>
																</c:forEach>
															</c:when>
														</c:choose>
													</select>
												</c:when>
												<c:when test='${operacion ==  "editar"}'>
													<select id="codigo_motivo" name="codigo_motivo"
														class="input"  style="width: 200px;">
														<OPTION value="0" selected>-- Selecc Uno --</OPTION>
														<c:choose>
															<c:when test='${not empty rs_motivos}'>
																<c:forEach items='${rs_motivos}' var='pa'>
																	<option value=<c:out value='${pa.codigo_motivo}'/>>
																		<c:out value='${pa.descripcion_motivo}' />
																	</option>
																</c:forEach>
															</c:when>
														</c:choose>
													</select>
												</c:when>
												<c:otherwise>
													<select id="codigo_motivo" name="codigo_motivo"
														class="input"  style="width: 200px;">
														<OPTION value="0" selected>-- Selecc Uno --</OPTION>
														<c:choose>
															<c:when test='${not empty rs_motivos}'>
																<c:forEach items='${rs_motivos}' var='pa'>
																	<option value=<c:out value='${pa.codigo_motivo}'/>>
																		<c:out value='${pa.descripcion_motivo}' />
																	</option>
																</c:forEach>
															</c:when>
														</c:choose>
													</select>
												</c:otherwise>
											</c:choose>
				    	</p>
			 		
				    	<p><label><c:choose><c:when test='${pertenece_a ==  "1"}'>Con Copia FONDECYT</c:when><c:otherwise>Con Copia CONCYTEC</c:otherwise></c:choose></label>
			                <input  id="chk_copia" checked="checked" class="caja" onClick="javascript:HabilitaCombo(1);" type="checkbox" name="chk_copia"></p>
			                <div id="scroll" class="input" style="WIDTH: 370px; height:100px; " >
			                  <table>
			                    <c:forEach items='${rs_oficina}' var='pa'>
								<tr>
			                      <td>
			                      <input  class="caja" type="checkbox" name="cbo_copia"  id="cbo_copia"  value="<c:out value='${pa.codigo_oficina}'/>"></td>
			                      <td class="texto_small">	
			                            <c:out value='${pa.siglas_oficina}'/>
			                           - 
			                          <c:out value='${pa.descripcion_oficina}'/></td>
			                    </tr></c:forEach>
			      				</table>
			      				
			      				
			      			</div>
			      			<br>
			      			<div style="display:none">
			      		<p><label><c:choose><c:when test='${pertenece_a ==  "1"}'>Con Copia CONCYTEC </c:when><c:otherwise>Con Copia FONDECYT</c:otherwise></c:choose></label>
			                <input  id="chk_copia_other" checked="checked" class="caja" onClick="javascript:HabilitaCombo(0);" type="checkbox" name="chk_copia_other"></p>
			               </div>
			                <div id="scroll_2" class="input" style="WIDTH: 370px; height:100px;display:none " >
			                  <table>
			                  
			                    <c:forEach items='${rs_oficina_other}' var='da'>
								<tr>
			                      <td><input  class="caja" type="checkbox" name="cbo_copia_other"  id="cbo_copia_other" value="<c:out value='${da.codigo_oficina}'/>"></td>
			                      <td class="texto_small">	
			                            <c:out value='${da.siglas_oficina}'/>
			                           - 
			                          <c:out value='${da.descripcion_oficina}'/></td>
			                    </tr></c:forEach>
			      				</table>
			      			</div>
			      			<SCRIPT type=text/javascript>
										

										Calendar.setup({
											inputField : "fecha_rpta", // id del campo de texto
											ifFormat : "%d/%m/%Y", // formato de la fecha, cuando se escriba en el campo de texto
											button : "lanzador1"
										// el id del botón que lanzará el calendario
										});

										Calendar.setup({
											inputField : "fecha_rpta_rq", // id del campo de texto
											ifFormat : "%d/%m/%Y", // formato de la fecha, cuando se escriba en el campo de texto
											button : "lanzador2"
										// el id del botón que lanzará el calendario
										});
										
										SeleccionarCampo("codigo_intitucion","<c:out value='${ls_codigo_institucion}'/>");
										inicio();
										function inicio() {

											with (document.forms[0]) {
												fecha_rpta.disabled = "true";
												//scroll_chks.style.visibility = 'hidden'; 
												//document.form_datos.fecha_rpta.disabled = "true"; 
											}

										};

										
									</SCRIPT>
			 		</div>
			 		
			 		<div class="center-big">
			 				<input type="button" class="button" id="btn" value="Registrar" onclick="this.disabled=true; this.value='REGISTRANDO...'; registrar();" >						
							<input type="button" name="button" id="button" class="button"   value="Regresar" onclick="regresar(<c:out value='${FFormReiterativoRegistro.codigo_documento}'/>);" >
							<br><br>
			 		</div>
		
		</div>
		
		<div id="break" ></div>
		<div class="derivalista">
		<div id="tabs">
		  <ul>
		    <li><a href="#tabs-1">Recorrido del Documento</a></li>
		  </ul>
		  <div id="tabs-1">
				<table width="100%"  cellpadding="0" cellspacing="0" >
			
					<tr>
					<td height="13" colspan="2" valign="top" align="center" >
					<display:table id="b"
						name="sessionScope.listadoSecuenciasRegistro" pagesize="10"
						export="false" style="width:85%" class="simple" align="center">

						<display:column property="nombre_oficina_origen" sortable="true"
							title="Origen" />
						<display:column property="nombre_oficina_destino" sortable="true"
							title="Destino" />
							
						<display:column property="nombre_personal" sortable="true"
							title="Destinatario" />
							<display:column property="estado_movimiento" sortable="true"
							title="Estado" />
						<display:column property="fecha_envio" sortable="true" 
							title="Fecha de Envío" />
						<display:column property="fecha_recepcion" sortable="true"
							title="Fecha de recepción" />
						<display:column property="fecha_derivacion" sortable="true"
							title="Fecha de Derivación" />
						<display:column property="observa_movimiento" sortable="true"
							title="Observación" />
					</display:table>
					</td>
					</tr>
					</table>
		    
		  </div>
		 </div>
		 </div>
 		<c:choose>
		<c:when test='${codigocontador ==  "1"}'>
			<script language="javascript">
					SeleccionarCampo("personas","<c:out value='${personas}'/>");
					SeleccionarCampo("codigo_oficina","<c:out value='${codigo_oficina}'/>");
					SeleccionarCampo("codigo_motivo","<c:out value='${codigo_motivo}'/>");
			</script>		
		</c:when>
		</c:choose>
	</html:form>
	</div>
</body>
</html>
<% } %>



