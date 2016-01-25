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
		<style type="text/css">
		.buttoncss{
			/*margin:10px 55px 10px 0px;*/
			/*font-weight: bold;*/
			/*line-height: 1;*/
			padding: 6px 10px;
			cursor:pointer;   
			color: #fff;
			text-align: center;
			/*text-shadow: 0 -1px 1px #64799e;*/
			
			/* Background gradient */
			background: #ffffff;
			background: -moz-linear-gradient(top, #ffffff 0%, #c4c4c4 100%);
			background: -webkit-gradient(linear, 0% 5%, 0% 100%, from(#ffffff), to(#c4c4c4));
			
			/* Border style */
		  	border: 1px solid #6d6d6d;  
			-moz-border-radius: 5px;
			-webkit-border-radius: 5px;
			border-radius: 5px;
		  
			/* Box shadow */
			/*-moz-box-shadow: inset 0 1px 0 0 #aec3e5;
			-webkit-box-shadow: inset 0 1px 0 0 #aec3e5;
			box-shadow: inset 0 1px 0 0 #aec3e5;*/
			
			-moz-box-shadow: 0px 2px 2px #7e7e7e;
			-webkit-box-shadow: 0px 2px 2px #7e7e7e;
			box-shadow: 0px 2px 2px #7e7e7e;
		}
		
		.buttoncss:hover {
			/*background: #848FB2;*/
		    cursor: pointer;
			}
			
		</style>
		<script language="JavaScript">
		$(document).ready(function(){
			$( "#tabs" ).tabs();
			
			$("#botoninvisibleAnular").mouseover(function(event){
	    	    $("#botoninvisibleAnular").addClass("buttoncss");
	    	 });
	    	 $("#botoninvisibleAnular").mouseout(function(event){
	    	    $("#botoninvisibleAnular").removeClass("buttoncss");
	    	});
	    	 
	    	 $('#botoninvisibleAnular').click(function() {
	    		 
	    			var strURL = "ListaDocumentos.do?tipo=derivar&operacion=D";
	    			location=strURL;	
	    			return;
	    	});
			
			
		});
		
		
		function Enter(e){
		if(e.keyCode==13){
		 buscar();
		}
		}
		
				
		function buscar(){
			var elem = document.getElementById("keywords");
			//var strURL ="";
			with(document.forms[0]){
				codigo_documento_busqueda_ls.value = elem.value;
				vaction = action;
				action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			   				
				
				if(codigo_documento_busqueda_ls.value=="Buscar..."){
					//alert("1 "+codigo_documento_busqueda_ls.value);
					tipo.value = 'nuevo';
					/*var strURL = "ListaDocumentos.do?tipo=modificar&operacion=MD";
					location=strURL;
				   	return;*/
					
				}else{
					//alert("2 "+codigo_documento_busqueda_ls.value);
					/*var strURL = "ListaDocumentos.do?tipo=modificar&operacion=MD&codigo_doc="+codigo_documento_busqueda_ls.value;*/
					tipo.value = 'modificar';
										
				}
		 	
				target = '';
				submit();
			}
		}
			
					
		/*function buscar(){
			
		 var codigo_doc=document.getElementById('codigo_doc');
         if(codigo_doc.value==''){
		 alert('Ingrese el codigo de documento a Buscar');
		   codigo_doc.focus();
		 }else{
		   var strURL = "ListaDocumentos.do?tipo=modificar&operacion=MD&codigo_doc="+codigo_doc.value;
		   location=strURL;
		   	return;
		 }
		 
		}*/
		function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray");
			
		    var codigo_doc=document.getElementById('codigo_doc');
		    codigo_doc.focus();
			
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
				
				if(trim(codigo_documento.value) == ""){
					alert("Se requiere el Num. Registro");
					codigo_documento.focus();
					return;
				}
				
				
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
	 ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;
	ls_codigo_documento = document.getElementById("codigo_documento").value;

	 var strURL="MantModDeriva.do?tipo=copias"+"&secuencia_movimiento="+ls_secuencia_movimiento+"&codigo_documento="+ls_codigo_documento;	
		//strURL += '&num_doc='+num_doc;
			
	   winPopup = window.open(strURL,"","scrollbars,HEIGHT=270,WIDTH=500");
			
		}
		
</script>
	</head>
	<body bgColor="#F0F5FB"  topmargin="0" leftmargin="0" onLoad="fn_onLoad();">
	<div class="derivaconta">
	
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
		<html:form action="/MantModDeriva" method="POST">
			<html:hidden property="tipo" />
			<html:hidden property="codigo_oficina" />
			<html:hidden property="descripcion_tipo"  />
			<html:hidden property="secuencia_movimiento"  />
			<INPUT  type="hidden" id="codigo_documento_busqueda_ls" name="codigo_documento_busqueda_ls" value="">
			
			<div id="break" ></div>
			<div id="contactderiva" class="rounded"> 
			<div id="TDHeadCab">
					   		   	<span class="labelrounded">REVERSION DE DOCUMENTOS DERIVADOS </span>
					   		   	
					   		   	
					   		   		<div  id ="botoninvisibleAnular" title="Ir a la lista Derivaci&oacute;n Documentos" style="width:80px; height:20px; position: absolute; top: 6px; padding: 3px 10px; text-align: center;  FONT-SIZE: 8pt; font-weight:normal;  left:72%; color: #003366;  line-height: 20px;" >&nbsp;<img  src="img/salir.gif"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp; Regresar</div>
					   		   		<input type="text" name="q" id="keywords"  class="input-search" onfocus="this.value=''" onkeypress="return validatecla('enteros',this)" value="Buscar..."/>
					   		   	 	<button  class="image" onclick="buscar()" title="Buscar por Nro de Registro" >Buscador</button>
					   		   	 	
					   		   	 	<!-- a  href="javascript:regresar()"><img  src="img/salir.gif" alt="Ir a la lista Derivaci&oacute;n Documentos" border="0" height="30" ></a-->
					  			
			</div>	
			<div id="TDHeadCabMensaje" >
			<table width="100%" cellspacing="0" cellpadding="0"  align="left">
		
						<tr>
							<td width="409" height="18" align="LEFT" valign="middle"  background="img/fondoplomo8.jpg" class="textomensaje10bold">
									<c:out value='${mensaje}'/></td>
						    <td width="568" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label1" ><!--DWLayoutEmptyCell-->&nbsp;</td>
					        <td width="47" align="center" valign="middle" background="img/fondoplomo8.jpg">&nbsp;&nbsp;&nbsp;</td>
						</tr>
			</table>
			</div>
			<div id="Lineaform2"></div>
			<div id="left" class="columna-left-small" >
     <p><label>N&deg; Registro:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
     <html:text property="codigo_documento"  styleId="codigo_documento" readonly="true" style="width: 150px;"  styleClass="input-medium"/>
     </p>
     <p><label>N&deg; Doc.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
     <html:text property="numero_documento" styleId="numero_documento" style="width: 270px;" styleClass="input-medium" readonly="true" />
     </p>
     <p><label>Hora:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
     <html:text property="hora" styleId="hora" style="width: 80px;" maxlength="15" styleClass="input-medium" readonly="true"/>
     </p>
     <p><label>Observaci&oacute;n:&nbsp;&nbsp;&nbsp;</label>
     <html:textarea property="observacion" styleId="observacion" style="width: 300px;height: 70px;"  styleClass="textarea" readonly="true"/>
     </p>
     </div>
     <div id="center"  class="columna-center-small" >&nbsp;</div>
     
	 <div id="rigth" class ="columna-rigth-small" >
	 <p><label>N&deg; Expediente:&nbsp;&nbsp;&nbsp;&nbsp;</label>
	 <html:text property="codigo_expediente" styleId="codigo_expediente" readonly="true" style="width: 150px;" styleClass="input-medium"/>
     </p>
     <p><label>Fecha:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
     <html:text property="fecha" styleId="fecha" style="width: 90px;" maxlength="11" styleClass="input-medium" 
		onkeypress="validarCaracterFecha(this);"  readonly="true" />			
		&nbsp; <img  src="img/cal.gif" title="Fecha(dd/mm/yyyy)" border="0"><!-- a href="" id="lanzador"><img  src="img/cal.gif" title="Fecha(dd/mm/yyyy)" border="0"></a-->
     </p>
     <p><label>Estado:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
     <html:select property="estado_movimiento"  styleId="estado_movimiento" styleClass="input-medium" >
		<html:option value="0" >-- Seleccionar --</html:option>
		
		<html:option value="3">Tramite</html:option>
		<html:option value="5">Derivado</html:option>
		
	 </html:select>	
	 </p>
	 <!-- SCRIPT type=text/javascript>
		Calendar.setup({
			inputField     :    "fechanone",      // id del campo de texto
			ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
			button         :    "lanzador" 
			   // el id del botón que lanzará el calendario
		});
	</SCRIPT-->			
	 </div>
	 
	 <div class="center-big">
	 
		 <html:button property="btnGrabar"  styleId="btnGrabar" value="Modificar" onclick="fn_grabar();"  styleClass="button"/>
	     <html:button property="btnCancelar"  styleId="btnCancelar" value="Cancelar" onclick="cancel();" style="width: 90;" styleClass="button"/>
	 </div>
	 <br>
			</div>
			
			<div id="break" ></div>
			<c:choose>
			<c:when test='${mostrar_listado eq null || mostrar_listado == "NO" }'>
			</c:when>
			<c:otherwise>
			<div class="derivalista">
			<div id="tabs">
			  <ul>
			    <li><a href="#tabs-1">Derivaciones Posibles de Revertir</a></li>
			  </ul>
			  <div id="tabs-1"> 
			  
			  <table width="100%"  cellpadding="0" cellspacing="0" >
						  <tr>
								<td height="35" align="center" colspan="2" valign="top">
							<table width="100%"  cellpadding="0" cellspacing="0" background="img/fondoplomo8.jpg">
							  <!--DWLayoutTable-->
							<tr>
							<td width="33%" height="30" valign="top"  class="textomensaje10bold">				</td>
						    <td width="33%" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label">Lista de Documentos					</td>
						    <td width="33%" >
							</td>
						  </tr>
							</table>							</td>
						  </tr>
						
							<tr>
							<td height="13" colspan="2" valign="top" align="center" >
							
									<display:table name="sessionScope.rs_modificar_doc" 
									export="false" sort="list" id="b" pagesize="10" 	class="simple" style="width:100%">
										
										<display:column property="codigo_documento" title="N&deg; Registro" />
										<display:column property="codigo_expediente" title="N&deg; Expediente" />
										<display:column property="numero_documento" title="N&deg;  Documento" />
										<display:column property="oficina_origen" title="Oficina Origen" />
										<display:column property="oficina_deriva" title="Oficina Destino" />
										<display:column property="estado_movimiento" title="Estado" />
									</display:table>	
							
							</td>
							</tr>
			</table>
			  
	 		  </div>
	 		 </div>
	 		</div>
			</c:otherwise>
			</c:choose>
			
     
		</html:form>
		</div>
	</body>
</html>
<% } %>