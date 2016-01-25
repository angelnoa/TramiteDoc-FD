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
		<title>Derivar Documentos</title>
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		
		 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
        <script src="js/funciones.js" type="text/javascript"></script>
		<script  src="js/prototype-1.4.0.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/scriptaculous.js"></script>
        <script  src="js/effects.js" type="text/javascript"></script>
       
        <script type="text/javascript" src="js/ajaxtags.js"></script>
		
		<script language="JavaScript" src="js/avatec.js"></script>
		<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
		<!-- librería para cargar el lenguaje deseado -->
		<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
		<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
		<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
		
		<script language="JavaScript">
		
		function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray");
			var scroll_chks = document.getElementById('scroll');
			
			with(document.forms[0]){						
             //alert('tipo de entrada---:'+tipo.value);
			 
			 
			  if(trim(tipo.value) == ""){
			  scroll_chks.style.visibility = 'visible'; 
				}else if(trim(tipo.value) == "editar"){
               

				btnEnviar.style.display = '';
				btnGrabar.style.display = 'none';
				btnCancelar.style.display = '';
				btnUpload.style.display = '';
				btnUploadInt.style.display = '';
				
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
					

				//btnHojaEnvio.style.display = '';
				//btnHojaEnvio.style.display = '';
				
				//btnCancelar.style.display = 'none';		
				//dpto.style.display = 'none';
				//users.focus();
				}
				else if(trim(tipo.value) == "contadjunto"){
				
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
					
				btnEnviar.style.display = '';
				btnGrabar.style.display = 'none';
				btnUpload.style.display = '';
				btnUploadInt.style.display = '';
				//btnHojaEnvio.style.display = '';
				//btnHojaEnvio.style.display = '';
				
				//btnCancelar.style.display = 'none';		
				//dpto.style.display = 'none';
				//users.focus();
				}
				
				else if(trim(tipo.value) == "derivar"){
					//dpto.style.display = 'none';
					//alert('entro a derivar --');
					
					//scroll_chks.style.visibility = 'hidden';
				//v_scroll.visi.display = 'none'; 
				btnEnviar.style.display = 'none';
				btnGrabar.style.display = 'none';
				btnCancelar.style.display = 'none';
				btnUpload.style.display = 'none';
				btnUploadInt.style.display = 'none';
				
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
									
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
				
				//cbo_copia=""
		
				/*for(var i=0 ; i<document.getElementById("cbo_copia").length ;i++)
				{
		
				 if(document.getElementById("cbo_copia").options[i].selected)
				 {
					cbo_copia=cbo_copia + document.getElementById("cbo_copia").options[i].value +"|"		 
				 }
		
				}*/
				
				if(chk_copia.checked)
				{
					chk_copia.value="S"
				}
				else{
					chk_copia.value="N"
				}
			
				vaction = action;
				action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
		    //target = 'mainFrame';
				target = '';
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
				
				codigo_documento.value = '';
				codigo_expediente.value = '';
				descripcion_tipo.value = '';
				numero_documento.value = '';
				
				fecha.value = '';
				hora.value = '';
				observacion.value = '';
				
				
				//usuario.readOnly = false;
				
				//removeElements(perfilSeleccionados);
				btnEnviar.style.display = 'none';	
				btnUpload.style.display = 'none';
				btnUploadInt.style.display = 'none';
				fecha.focus();
			}
		}
		
		
		
		function fn_enviar(){
			
				with(document.forms[0]){				
					//agregado
					
					if(trim(codigo_oficina.value) == 0){
					alert("Se requiere un valor");
					codigo_oficina.focus();
					return;
					}
					if(trim(codigo_motivo.value) == 0){
					alert("Se requiere un valor");
					codigo_motivo.focus();
					return;
					}
					if(trim(personas.value) == 0){
					alert("Se requiere un valor");
					personas.focus();
					return;
					}
					if(trim(observacion.value) == 0){
					alert("Se requiere un valor");
					observacion.focus();
					return;
					}
					//codigo_persona
					
					if(confirm("Está seguro de derivar este documento?")){
					var ls_codigo_persona_session=<c:out value='${codigo_persona}'/>;
					var ls_codigo_personal_pag=personas.value;
									
					if(ls_codigo_persona_session == ls_codigo_personal_pag){
					alert("El documento no puede ser derivado a la misma persona...!");
					personas.focus();
					return;
					}
					
					
					vaction = action;
					action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			    tipo.value = 'enviar';	
			    
				target = '';
				
					submit();
					}
					 		
				}			
		}
		
		function fn_editar_copia(pcodigo_documento, psecuencia_movimiento){
			
		with(document.forms[0]){
		codigo_documento.value = pcodigo_documento;
        secuencia_movimiento.value = psecuencia_movimiento;
				codigo_documento.readOnly = true;				
				numero_documento.readOnly = true;				
				btnEnviar.style.display = '';	
					
				//alert(action);
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
		,pcodigo_oficina,pestado_movimiento,pfecha_movimiento,phora_movimiento,psecuencia_movimiento,pcodigo_inicia,pnaturaleza_documento,pcodigo_recepcion){
			
			with(document.forms[0]){
			
			
				codigo_documento.value = pcodigo_documento;
				codigo_expediente.value = pcodigo_expediente;
				descripcion_tipo.value = pdescripcion_tipo;
				numero_documento.value = pnumero_documento;
				codigo_oficina.value = pcodigo_oficina;
				estado_movimiento.value = pestado_movimiento;
				fecha.value = pfecha_movimiento;
				hora.value = phora_movimiento;
				
				secuencia_movimiento.value = psecuencia_movimiento;
				codigo_inicia.value = pcodigo_inicia;
				naturaleza_documento.value = pnaturaleza_documento;
				codigo_recepcion.value = pcodigo_recepcion;
				
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
				
				btnEnviar.style.display = '';	
					
				
			  vaction = action;
				action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
				
				target = '';
				
				tipo.value = 'editar';	
				
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

	function HabilitaText()
		{
		with(document.forms[0]){
						
				if (cbo_fecharpta.checked)
				{
				 
				fecha_rpta.disabled = false; 
				lanzador1.style.display = '';
				
				}
				else
				{
				
				fecha_rpta.disabled = true;
				lanzador1.style.display = 'none';
				fecha_rpta.value="";
				
				}
								}
		}
		
		function HabilitaCombo(){

            var scroll_chks = document.getElementById('scroll');
			with(document.forms[0]){
			   if (chk_copia.checked) {			

					scroll_chks.style.visibility = 'visible'; 
			   }
			   else{			
				scroll_chks.style.visibility = 'hidden'; 
			   }
			}
		}
	
	
	function AgregarArchivos(){
   
				var ls_operacion = "N";
				var ls_codigo_documento = document.getElementById("codigo_documento").value;
				var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
				var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;
				//alert(ls_codigo_documento);
				
				var strURL='MantDerivar.do?tipo=upload'+'&operacion='+ls_operacion+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&secuencia_movimiento='+ls_secuencia_movimiento;	
				//window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes")
				winPopup = window.open(strURL,"","scrollbars,HEIGHT=250,WIDTH=580")
				
		}
	
	function AgregarDocInternos() {

		//alert("Internos");
		
		var strURL="MantenimientoDocumentoInterno.do";
		winPopup = window.open(strURL,"","scrollbars,HEIGHT=540,WIDTH=700");
	 }
	
	function VerArchivos(ls_codigo_documento,ls_codigo_expediente,ls_codigo_recepcion){
   
				var ls_operacion = "N";
				/*var ls_codigo_documento = document.getElementById("codigo_documento").value;
				var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
				var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;*/
				//alert(ls_codigo_documento);
				
				var strURL='MantSeguimiento.do?tipo=upload'+'&operacion='+ls_operacion+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&codigo_recepcion='+ls_codigo_recepcion;	
				window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes")
				
				
		}
	function VerHojaEnvio(pcodigo_documento,pcodigo_expediente){
		
		//alert(pcodigo_documento);
		//alert(pcodigo_expediente);
		//if(confirm("Desea Imprimir hoja de envio?")){


		 		var strURL="ValidaPaginas.do?tipo=hojaenvio"
				strURL+="&codigo_documento="+pcodigo_documento+"&codigo_expediente="+pcodigo_documento;
					//window.location.href=strURL
					window.open(strURL,"","HEIGHT=440,WIDTH=770,scrollbars=yes")
			//										}
		}
		
	function HojaEnvio(pcodigo_documento_env,pcodigo_expediente_env){
		
		//alert(pcodigo_documento);
		//alert(pcodigo_expediente);
		
		alert("Dentro de Hoja de Envio...")
		if(confirm("Desea Imprimir hoja de envio?")){
			
			//VerHojaEnvio(pcodigo_documento_env,pcodigo_expediente_env);
			with(document.forms[0]){
			
			btnHojaEnvio.style.display = 'none';
			
			}
													}
		}
		
		
		function MostrarAdjunto(codigo){
			/*if(winPopup){
				if(!winPopup.closed) 
					winPopup.close();
					
					//var strURL = "MantDocumento.do?operacion=MA&codigo="+codigo;
					//var strURL = "ValidaPaginas.do?tipo=seleccion&operacion=T&codigocontador="+codigo;
					var strURL = "MantDerivar.do?tipo=editar&codigocontador="+codigo+"&codigo_documento="+codigo_documento;
					location=strURL;
					//alert(strURL);
				}*/
				//alert(codigo);
				with(document.forms[0])	{						
			codigocontador.value = codigo;
			//winPopup.close();
			
			if(winPopup){
			if(!winPopup.closed) 
		    	winPopup.close();
			}		
			
			vaction = action;
		action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
		tipo.value = 'contadjunto';
		target = '';
	
				//alert(tipo);	
				submit();
		}
	
		//form_datos.submit();
				
		}
		
		
		function fn_editar(pcodigo_documento
		,pcodigo_expediente
		,pdescripcion_tipo,pnumero_documento
		,pcodigo_oficina,pestado_movimiento,pfecha_movimiento,phora_movimiento,psecuencia_movimiento,pcodigo_inicia,pnaturaleza_documento,pcodigo_recepcion){
			
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
				
				secuencia_movimiento.value = psecuencia_movimiento;
				codigo_inicia.value = pcodigo_inicia;
				naturaleza_documento.value = pnaturaleza_documento;
				codigo_recepcion.value = pcodigo_recepcion;
				//alert(pcodigo_recepcion);
				/*alert(psecuencia_movimiento);
				alert(pcodigo_inicia);
				alert(pnaturaleza_documento);*/
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
		
		
		function listadocumentos()
		{
		
		var strURL = "ListaDocumentos.do?tipo=modificar&operacion=MD&inicia=SI";
				
		 location=strURL;
		
		return;
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
	
	
	function ver_frame(){
				with(document.forms[0]){				
					
				var elem = document.getElementById("codigo_documento_busqueda");
			//alert("Entre aqui");
					// Check to see if the field contains a valid date
				if ( ! checkNumber(elem)) {
					//alert("Entre aqui 2");
					    alert( "Este campo no es un número." );
					    elem.value="";
					    elem.focus();
					    return false;
					}
					
				vaction = action;
				action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			    tipo.value = 'buscar';	
			    //target = 'mainFrame';
				target = '';
				
				//	submit();
					
					
					var strURL = "MantDerivar.do?tipo=buscar";
				 	 location=strURL;
					return;
					
				
			}
			
		}
	
	
		
	/*function ver_frame(){
	
	
	//var ls_numero_documento = document.getElementById("numero_documento").value;
	var ls_codigo_expediente = document.getElementById("codigo_expediente_busqueda").value;
	//var ls_codigo_oficina = document.getElementById("codigo_oficina_busqueda").value;
	var ls_codigo_documento = document.getElementById("codigo_documento_busqueda").value;
	
	
	 var strURL = "ListaDocumentos.do?tipo=derivar&operacion=B";
	 strURL += "&codigo_expediente_busqueda="+ls_codigo_expediente+"&codigo_documento_busqueda="+ls_codigo_documento;
	
 	 location=strURL;
	return;

	}*/
	
function listar_todos(){
	
	
	//var strURL = "ListaDocumentos.do?tipo=modificar&operacion=ME";
	var strURL = "ListaDocumentos.do?tipo=derivar&operacion=D";
 	 location=strURL;
	return;

	}
	
	function checkNumber(elem ) {
		/*valida si contiene al menos un digito, se hizo pensando en el numero de registro de documento.
		 *Fecha:18-09-2011
		 * */
		    return !elem.value || /^\d+$/.test(elem.value);
		}
	
</script>
	    <style type="text/css">
<!--
.style1 {font-size: 12px}
-->
        </style>
</head>
	<body topmargin="0" leftmargin="0" onLoad="fn_onLoad();">
		<html:form action="/MantDerivar" method="POST">
			<html:hidden property="tipo" />
			<html:hidden property="estado_movimiento" />
			<html:hidden property="descripcion_tipo"  />
			<html:hidden property="hora"  />
			<html:hidden property="secuencia_movimiento" styleId="secuencia_movimiento"  />
			<html:hidden property="codigo_inicia"  />
			<html:hidden property="naturaleza_documento"  />
			<html:hidden property="codigo_recepcion"  />
			<html:hidden property="codigocontador"  />
			
			<table width="100%" cellspacing="0" cellpadding="0"  align="left">
			  <!--DWLayoutTable-->
			<tr>
			  <td height="50" colspan="3" align="left" valign="middle" style="width:100%">
						<table width="100%" height="50" cellspacing="0" cellpadding="0"  align="left">
						<tr><td bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></td></tr></table>			  </td>
			  </tr>
			  </table>

 
			  <table width="100%" cellspacing="0" cellpadding="0"  align="left">
				<tr>
				  <td height="26" align="LEFT" valign="middle"  background="img/fondoplomo8.jpg" class="textomensaje10bold"><span class="label1">DERIVAR DOCUMENTOS</span></td>
				  <td align="center" valign="middle" background="img/fondoplomo8.jpg" class="label1" ><!--DWLayoutEmptyCell-->&nbsp;</td>
				  <td align="center" valign="middle" background="img/fondoplomo8.jpg"><a  href="javascript:listadocumentos()"><img src="img/modifica.gif" alt="Reversi&oacute;n de la Derivaci&oacute;n" border="0" height="30" ></a></td>
			  </tr>
				<tr>
					<td width="409" height="18" align="LEFT" valign="middle"  background="img/fondoplomo8.jpg" class="textomensaje10bold">
							<c:out value='${mensaje_recepcion}'/>
							<br>
				  <c:out value='${mensaje_numreg}'/>			</td>
				    <td width="568" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label1" ><!--DWLayoutEmptyCell-->&nbsp;</td>
			        <td width="47" align="center" valign="middle" background="img/fondoplomo8.jpg">&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
			  <td height="32" colspan="3" align="left" valign="middle" style="width:100%"  class="groupcell">
						<table width="100%" cellspacing="0" cellpadding="0"  align="left">
						<tr > 
                          <td width="118" height="28" align="left" valign="middle" class="label">&nbsp;&nbsp;&nbsp;&nbsp;<strong>B&uacute;squeda 
                          por :</strong> </td>
                          <td width="103" align="right" valign="middle" class="label">N&deg; Registro::</td>
                          <td width="173">&nbsp;&nbsp;
                          <html:text property="codigo_documento_busqueda" styleId="codigo_documento_busqueda" onkeypress="return validatecla('enteros',this)" style="width: 150px;" styleClass="caja"/></td>
                          <td width="113" align="center" valign="middle"><input  type="submit" id="button" name="button"  class="boton" value="Buscar" onClick="ver_frame()" alt="buscar"  ></td>
                          <td width="465"><input type="button" name="button" class="boton" value="Listar Todos" onClick="listar_todos()" alt="Listar Todos"  ></td>
                        </tr>
				  </table>			  </td>
			  </tr>
				<tr>
			  <td height="5" colspan="3" align="left" valign="middle" style="width:100%"  class="groupcell">								  </td>
			  </tr>
				<tr>
				  <td height="100%" colspan="3" align="center" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							<tr>
								<td width="10" height="10" style="height: 10px;">								</td>
								<td width="119" align="left">								</td>
								<td width="10">								</td>
								<td width="300">								</td>
								<td width="77"></td>
								<td width="77"></td>
								<td width="11"></td>
								<td></td>
								<td width="10">								</td>
							</tr>
							
							
							<tr>
								<td height="20" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									N&deg; Registro:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
							  <td align="left" valign="middle" style="width: 300px;">
				                <html:text property="codigo_documento"  styleId="codigo_documento" readonly="true" style="width: 150px;"  styleClass="caja"/>		  </td>
								<td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td align="left" valign="middle" class="label"> N&deg; Expediente: </td>
								<td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td align="left" valign="middle"><html:text property="codigo_expediente" styleId="codigo_expediente" readonly="true" style="width: 150px;" styleClass="caja"/></td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							
							<tr>
								<td height="20" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									N&deg; Doc.:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td align="left" valign="middle" style="width: 300px;">
						
						<html:text property="numero_documento" styleId="numero_documento" style="width: 260px;" styleClass="caja" />							  </td>
								<td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td align="left" valign="middle" class="label">Fecha Derivaci&oacute;n:</td>
								<td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td align="left" valign="middle">
						          <html:text property="fecha" styleId="fecha" style="width: 80px;" maxlength="11" styleClass="caja" 
										onkeypress="validarCaracterFecha(this);"  />			
							    &nbsp; <a href="" id="lanzador"><img  src="img/cal.gif" alt="Fecha (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy </td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							<tr>
								<td height="20" style="width: 10px;">&nbsp;								</td>
								<td style="width: 90px;" class="label" align="left">
									Oficina:								</td>
								<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;								</td>
								<td align="left" valign="middle" style="width: 300px;">
						
											<c:choose>
											<c:when test='${operacion ==  "derivar"}'>
														 <select id="codigo_oficina" name="codigo_oficina" class="caja12" style="WIDTH: 300px; HEIGHT: 21px">
														<OPTION value="0" selected> -- Selecc Uno -- </OPTION>
														<c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	
														<option value=<c:out value='${pa.codigo_oficina}'/>> 
														<c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/>
														 </option> </c:forEach> 
														</c:when> </c:choose> </select> 
										</c:when>
										<c:when test='${operacion ==  "editar"}'>
								<select id="codigo_oficina" name="codigo_oficina" class="caja12" style="WIDTH: 300px; HEIGHT: 21px">
													<OPTION value="0" selected> -- Selecc Uno -- </OPTION>
													<c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	
													<option value=<c:out value='${pa.codigo_oficina}'/>> 
													<c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/>
													 </option> </c:forEach> 
													</c:when> </c:choose> </select> 
										</c:when>
										<c:otherwise>
									 <select id="codigo_oficina" name="codigo_oficina" class="caja12" style="WIDTH: 300px; HEIGHT: 21px">
										<OPTION value="0" selected> -- Selecc Uno -- </OPTION>
										<c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
										<c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/> </option> </c:forEach> 
										</c:when> </c:choose> </select>
										</c:otherwise>
									</c:choose>															  </td>
								<td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td align="left" valign="middle" class="label">Personal:</td>
								<td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td align="left" valign="middle">
									<INPUT  type="hidden" id="oper" name="oper" value="<c:out value='${operacion}'/>">
									<INPUT  type="hidden" id="per" name="per" value="<c:out value='${personas}'/>">
									<INPUT  type="hidden" id="ofi" name="ofi" value="<c:out value='${codigo_oficina}'/>">
									<INPUT  type="hidden" id="contador" name="contador" value="<c:out value='${codigocontador}'/>">
								  <c:choose>
        							<c:when test='${operacion ==  "derivar"}'>
        							<select id="personas" name="personas" class="caja12" >
									<option value="0">:: Personas ::</option>
								  </select>
        							</c:when>
      								<c:when test='${codigocontador ==  "1"}'>
        							<select id="personas" name="personas"  class="caja12" >
											<option value="0">:: Personas ::</option>
                                        	<c:choose> <c:when test='${not empty rs_personas}'> 
                                            	<c:forEach items='${rs_personas}' var='pa'>
													<option value=<c:out value='${pa.codigo_personal}'/>> 
                                                    <c:out value='${pa.nombre_personal}'/> </option>	
                                                </c:forEach> </c:when> 
                                            </c:choose> 
                                      </select>
        							</c:when>
									
        							<c:when test='${operacion ==  "editar"}'>
     									   <c:choose>
											<c:when test='${cargo_personal=="3" || cargo_personal=="10"}'>								
												<select id="personas" name="personas" class="caja12" >
											<option value="0">:: Personas ::</option>
										  </select>
								 			 </c:when>
											 <c:otherwise>
											<select id="personas" name="personas"  class="caja12" >
											<option value="0">:: Personas ::</option>
                                        	<c:choose> <c:when test='${not empty rs_personas}'> 
                                            	<c:forEach items='${rs_personas}' var='pa'>
													<option value=<c:out value='${pa.codigo_personal}'/>> 
                                                    <c:out value='${pa.nombre_personal}'/> </option>	
                                                </c:forEach> </c:when> 
                                            </c:choose> 
                                        </select>
											</c:otherwise>
									  </c:choose> 
        							</c:when>
         							<c:otherwise>
        								<select id="personas" name="personas"  class="caja12" disabled >
                                        	<c:choose> <c:when test='${not empty rs_personas}'> 
                                            	<c:forEach items='${rs_personas}' var='pa'>
													<option value=<c:out value='${pa.codigo_personal}'/>> 
                                                    <c:out value='${pa.nombre_personal}'/> </option>	
                                                </c:forEach> </c:when> 
                                            </c:choose> 
                                        </select>
        							</c:otherwise>
    							  </c:choose>				 	</td>
								<td style="width: 10px;">&nbsp;								</td>
							</tr>
							<tr>
							  <td rowspan="2" style="width: 10px;">&nbsp;								</td>
							  <td align="left" class="label" style="width: 90px;"><span class="label" style="width: 90px;">Acci&oacute;n a Realizar: </span></td>
							  <td align="center" class="labelsubtitle" style="width: 10px;">&nbsp;								</td>
							  <td align="left" valign="middle" style="width: 300px;"><c:choose>
							    <c:when test='${operacion ==  "derivar"}'>
							      <select id="codigo_motivo" name="codigo_motivo" class="caja1" style="WIDTH: 250px; HEIGHT: 21px">
                                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                                    <c:choose>
                                      <c:when test='${not empty rs_motivos}'>
                                        <c:forEach items='${rs_motivos}' var='pa'>
                                          <option value=<c:out value='${pa.codigo_motivo}'/>
                                          >
                                          <c:out value='${pa.descripcion_motivo}'/>
                                          </option>
                                        </c:forEach>
                                      </c:when>
                                    </c:choose>
                                  </select>
                                </c:when>
                                <c:when test='${operacion ==  "editar"}'>
                                  <select id="codigo_motivo" name="codigo_motivo" class="caja1" style="WIDTH: 250px; HEIGHT: 21px">
                                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                                    <c:choose>
                                      <c:when test='${not empty rs_motivos}'>
                                        <c:forEach items='${rs_motivos}' var='pa'>
                                          <option value=<c:out value='${pa.codigo_motivo}'/>
                                          >
                                          <c:out value='${pa.descripcion_motivo}'/>
                                          </option>
                                        </c:forEach>
                                      </c:when>
                                    </c:choose>
                                  </select>
                                </c:when>
                                <c:otherwise>
                                  <select id="codigo_motivo" name="codigo_motivo" class="caja1" style="WIDTH: 250px; HEIGHT: 21px">
                                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                                    <c:choose>
                                      <c:when test='${not empty rs_motivos}'>
                                        <c:forEach items='${rs_motivos}' var='pa'>
                                          <option value=<c:out value='${pa.codigo_motivo}'/>
                                          >
                                          <c:out value='${pa.descripcion_motivo}'/>
                                          </option>
                                        </c:forEach>
                                      </c:when>
                                    </c:choose>
                                  </select>
                                </c:otherwise>
                              </c:choose></td>
							  <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
							  <td rowspan="5" align="left" valign="middle" class="label"><span class="label" style="width: 90px;">Con Copia:
                                  <input language="JavaScript"  id="chk_copia"  checked="checked" class="caja" onClick="javascript:HabilitaCombo();" type="checkbox" name="chk_copia">
                              </span></td>
							  <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
							  <td rowspan="5" align="left" valign="middle"><div id="scroll">
							    <table>
  <c:forEach items='${rs_oficina}' var='pa'>
    <tr>
      <td><input  class="caja" type="checkbox" name="cbo_copia" id="cbo_copia" value="<c:out value='${pa.codigo_oficina}'/>"></td>
                      <td class="texto_small">	
                          <c:out value='${pa.siglas_oficina}'/>
                          - 
                          <c:out value='${pa.descripcion_oficina}'/>                         </td>
                    </tr></c:forEach>
  </table>
					          </div></td>
							  <td style="width: 10px;">&nbsp;</td>
					    </tr>
							<tr>
							  <td align="left" class="label" style="width: 90px;">Requiere Prioridad:</td>
								<td align="center" class="labelsubtitle" style="width: 10px;">&nbsp;</td>
								<td align="left" valign="middle" style="width: 300px;"><table width="100%" border="0">
                                <tr>
                                  <td width="6%"><input language="JavaScript" type="checkbox" class="caja" name="cbo_fecharpta" id="cbo_fecharpta" value="checkbox" onClick="javascript:HabilitaText();"></td>
                                  <td width="24%" class="label">Fecha Requerida</td>
                                  <td width="70%"><span class="label">
						         <c:choose>
                                     <c:when test='${codigocontador ==  "1"}'>
                                       <INPUT language="JavaScript" class="caja1" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta"  maxLength="10" size="11" name="fecha_rpta" value="<c:out value='${fecha_rpta}'/>">
                                     </c:when>
                                     <c:otherwise>
                                       <INPUT language="JavaScript" class="caja1" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta"  maxLength="10" size="11" name="fecha_rpta">
                                     </c:otherwise>
                                  </c:choose>
&nbsp; <a href="" id="lanzador1"  style="display: none; width: 20;" ><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy </span></td>
                                </tr>
                                                              </table></td>
								<td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
								<td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
							  <td style="width: 10px;">&nbsp;								</td>
							</tr>
							<tr>
							  <td height="10" style="width: 10px;">&nbsp;</td>
							  <td align="left" class="label" style="width: 90px;"><span class="label" style="width: 90px;"> Observaci&oacute;n: </span></td>
							  <td>&nbsp;</td>
							  <td align="left" valign="middle" style="width: 300px;"><html:textarea property="observacion" styleId="observacion" style="width: 300px;height: 70px;"  styleClass="caja"/> </td>
							  <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
							  <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
							  <td style="width: 10px;">&nbsp;</td>
					    </tr>
							<tr>
							  <td height="10" style="width: 10px;">&nbsp;</td>
							  <td align="left" valign="middle" class="label">Doc. de Rpta::</td>
							  <td>&nbsp;</td>
							  <td align="left" valign="middle" style="width: 300px;"><input  class="caja1"  id="doc_resp"  maxlength="60" size="40" name="doc_resp"> <input type="button"  id="btnUpload" name="btnUpload"  value="Agregar Archivos" onClick="AgregarArchivos()" alt="Agregar Archivos" style=" width: 120;" styleClass="boton" >
&nbsp; <c:choose>
				<c:when test='${operacion ==  "contadjunto" && codigocontador ==  "1"}'>
			<img src="img/docadjuntos.gif" alt="Documentos Adjuntos">				</c:when>
				</c:choose>	</td>
							  <td align="left" valign="middle" class="label">&nbsp;</td>
							  <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
							  <td style="width: 10px;">&nbsp;</td>
					    </tr>
							<tr>
							  <td height="20" style="width: 10px;">&nbsp;</td>
							  <td align="left" valign="middle" class="label">Fecha Rpta.:</td>
							  <td>&nbsp;</td>
							  <td align="left" valign="middle" style="width: 300px;"><input language="JavaScript" class="caja1" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta_rq"  maxlength="10" size="11" name="fecha_rpta_rq">
&nbsp; <a href="" id="lanzador2"  ><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy</td>
							  <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
							  <td align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</td>
							  <td style="width: 10px;">&nbsp;</td>
					    </tr>
                            
                       
							
							<tr>
								<td height="31" colspan="9" align="center">
									<table>
										<tr>
											<td>
											<html:button property="btnEnviar" styleId="btnEnviar" value="Derivar" onclick="fn_enviar();"
													style=" width: 90;" styleClass="boton"/>										  </td>
											<td>
												<html:button property="btnCancelar" styleId="btnCancelar" value="Cancelar" onclick="cancel();" style="width: 90;" styleClass="boton"/>											</td>
											<td>
												<html:button property="btnGrabar" styleId="btnGrabar" value="Grabar" onclick="fn_grabar();" style=" display: none;  width: 90;" styleClass="boton"/>										</td>
										</tr>
									</table>								</td>
							</tr>
				    </table>				  </td>
				</tr>
				
				
		  </table>
		
		
		
		<table width="100%" align="center" cellpadding="0" cellspacing="0" >
						  <tr>
								<td height="35" colspan="2" valign="top">
							<table width="100%" align="center" cellpadding="0" cellspacing="0" background="img/fondoplomo8.jpg">
							  <!--DWLayoutTable-->
							<tr>
							<td width="227" height="30" valign="top"  class="textomensaje10bold">													  </td>
						    <td width="157">							</td>
						    <td width="588" align="left" valign="middle" background="img/fondoplomo8.jpg" class="label">
							Documentos Pendientes por Derivar								</td>
						  </tr>
							</table>							</td>
						  </tr>
						
							<tr>
								<td height="13" colspan="2" valign="top">
							<display:table name="sessionScope.rs_derivacion_doc" export="false" sort="list" id="b" pagesize="10" class="simple" style="width:100%">
										
										<display:column property="codigo_documento" media="html" title="N&deg; Registro" />
										<display:column property="codigo_expediente" media="html" title="N&deg; Expediente" />
										<display:column property="fecha_movimiento" media="html" title="Fecha Registro" />
										<display:column property="numero_documento" media="html" title="N&deg;  Documento" />
										<display:column property="oficina_origen" media="html"  title="Oficina Origen" />
										<display:column property="estado_movimiento" title="Estado" />
										<display:column property="view_archivo" align="center" title="Ver Documento" />
										<display:column property="documento_respuesta" align="center" title="Operacion" />
								</display:table>								</td>
							</tr>
		</table>
		
		
				<ajax:select baseUrl="${pageContext.request.contextPath}/obtenerLista.do" parameters="cambia=ofi&codigo_oficina={codigo_oficina}" source="codigo_oficina" target="personas"  />
				  <SCRIPT type=text/javascript>
					Calendar.setup({
						inputField     :    "fecha",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador" 
						   // el id del botón que lanzará el calendario
					});
					
					Calendar.setup({
						inputField     :    "fecha_rpta",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador1" 
						   // el id del botón que lanzará el calendario
					});
					
					Calendar.setup({
						inputField     :    "fecha_rpta_rq",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador2" 
						   // el id del botón que lanzará el calendario
					});
					
					inicio();
					function inicio(){
					
					
					with(document.forms[0]){
							fecha_rpta.disabled = "true"; 
							//scroll_chks.style.visibility = 'hidden'; 
							//document.form_datos.fecha_rpta.disabled = "true"; 
							}
					   
					};
					
				<c:choose>
				<c:when test='${codigocontador ==  "1"}'>
					SeleccionarCampo("personas","<c:out value='${personas}'/>");
					SeleccionarCampo("codigo_oficina","<c:out value='${codigo_oficina}'/>");
					SeleccionarCampo("codigo_motivo","<c:out value='${codigo_motivo}'/>");
				</c:when>
				</c:choose>
 
				</SCRIPT>
					
		
		
		</html:form>
	   
        
        
</body>
</html>
<% } %>
