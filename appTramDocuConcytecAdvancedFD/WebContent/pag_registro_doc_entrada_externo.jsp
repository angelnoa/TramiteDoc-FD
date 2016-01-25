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

<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<script language="JavaScript" src="js/avatec.js"></script>
<SCRIPT LANGUAGE=javascript src="js/prototype-1.4.0.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/scriptaculous.js"></SCRIPT>

<SCRIPT LANGUAGE=javascript src="js/ajaxtags.js"></SCRIPT>
<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librer�a para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librer�a que declara la funci�n Calendar.setup, que ayuda a generar un calendario en unas pocas l�neas de c�digo -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
<SCRIPT type=text/javascript>
    function ValidarEntero(valor){
	if (isNaN(valor)) { 
            //entonces (no es numero) devuelvo el valor cadena vacia 
            return ''; 
      }else{ 
            //En caso contrario (Si era un número) devuelvo el valor 
            return valor;
      } 

	}
	function Cerrar(){
	 var strURL = "ValidaPaginas.do?tipo=inicio";
	//alert(strURL);
	window.location.href=strURL
	//window.close()
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

	function buscar_persona()
	{
	
			medio = document.getElementById("medio").value
			codigo_tipo = document.getElementById("codigo_tipo").value
			numero_documento = document.getElementById("numero_documento").value
			
			firmadopor = document.getElementById("firmadopor").value
			fecha_registro = document.getElementById("fecha_registro").value
			dirigido = document.getElementById("dirigido").value
			hora = document.getElementById("hora").value
			folios_documento = document.getElementById("folios_documento").value
			asunto_documento = document.getElementById("asunto_documento").value
			codigo_solicitud = document.getElementById("codigo_solicitud").value
			observa_documento = document.getElementById("observa_documento").value
			cbo_fecharpta = document.getElementById("cbo_fecharpta").value
			fecha_rpta = document.getElementById("fecha_rpta").value
			numero_referencia = document.getElementById("numero_referencia").value
			personas = document.getElementById("personas").value
			
			correo = document.getElementById("correo_electronico").value
			dominio = document.getElementById("dominio_correo").value
			
			accion="R";
			
 			var strURL = "ValidaPaginas.do?tipo=instcontactos";
			
			strURL += "&medio="+medio;
			strURL += "&codigo_tipo="+codigo_tipo;
			strURL += "&numero_documento="+numero_documento;
			strURL += "&firmadopor="+firmadopor;
			strURL += "&fecha_registro="+fecha_registro;
			strURL += "&dirigido="+dirigido;
			strURL += "&hora="+hora;
			strURL += "&folios_documento="+folios_documento;
			strURL += "&asunto_documento="+asunto_documento;
			strURL += "&codigo_solicitud="+codigo_solicitud;
			strURL += "&observa_documento="+observa_documento;
			strURL += "&cbo_fecharpta="+cbo_fecharpta;
			strURL += "&fecha_rpta="+fecha_rpta;
			strURL += "&numero_referencia="+numero_referencia;
			strURL += "&personas="+personas;
			strURL += "&accion="+accion;
			strURL += "&correo="+correo;
			strURL += "&dominio="+dominio;
	
 	 location=strURL;
	
	return;
	}

function buscar_persona_dirigido()
	{
	
			medio = document.getElementById("medio").value
			codigo_tipo = document.getElementById("codigo_tipo").value
			numero_documento = document.getElementById("numero_documento").value
			
			firmadopor = document.getElementById("firmadopor").value
			fecha_registro = document.getElementById("fecha_registro").value
			dirigido = document.getElementById("dirigido").value
			hora = document.getElementById("hora").value
			folios_documento = document.getElementById("folios_documento").value
			asunto_documento = document.getElementById("asunto_documento").value
			codigo_solicitud = document.getElementById("codigo_solicitud").value
			observa_documento = document.getElementById("observa_documento").value
			cbo_fecharpta = document.getElementById("cbo_fecharpta").value
			fecha_rpta = document.getElementById("fecha_rpta").value
			numero_referencia = document.getElementById("numero_referencia").value
			//selectedItems = document.getElementById("selectedItems").value;
			accion="RD";
			
 			var strURL = "ValidaPaginas.do?tipo=dirigidos";
			
			strURL += "&medio="+medio;
			strURL += "&codigo_tipo="+codigo_tipo;
			strURL += "&numero_documento="+numero_documento;
			strURL += "&firmadopor="+firmadopor;
			strURL += "&fecha_registro="+fecha_registro;
			strURL += "&dirigido="+dirigido;
			strURL += "&hora="+hora;
			strURL += "&folios_documento="+folios_documento;
			strURL += "&asunto_documento="+asunto_documento;
			strURL += "&codigo_solicitud="+codigo_solicitud;
			strURL += "&observa_documento="+observa_documento;
			strURL += "&cbo_fecharpta="+cbo_fecharpta;
			strURL += "&fecha_rpta="+fecha_rpta;
			strURL += "&numero_referencia="+numero_referencia;
			strURL += "&accion="+accion;
			//strURL += "&selectedItems="+selectedItems;
	
 	 location=strURL;
	
	return;
	}
	
function Validar  (){
 
 	
		 if (Validarcampovacio(document.form_datos.numero_documento.value) == false)
              {              
                    alert("Ingresar Numero de Documento");
                    document.form_datos.numero_documento.focus();
                    return false;           
              } 
			 if (Validarcampovacio(document.form_datos.fecha_registro.value) == false)
              {              
                    alert("Ingresar Fecha Registro");
                    document.form_datos.fecha_registro.focus();
                    return false;           
              } 
			 
			

			/*   if (Validarcampovacio(document.form_datos.sle_nombre_institucion.value) == false)
              {              
                    alert("Ingresar Origen Documento");
                    document.form_datos.desc_origen.focus();
                    return false;           
              } */
			 

			   if (Validarcampovacio(document.form_datos.hora.value) == false)
              {              
                    alert("Ingresar Hora");
                    document.form_datos.hora.focus();
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.folios_documento.value) == false)
              {              
                    alert("Ingresar Folios del Documento");
                    document.form_datos.folios_documento.focus();
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.asunto_documento.value) == false)
              {              
                    alert("Ingresar Asunto del Documento");
                    document.form_datos.asunto_documento.focus();
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.firmadopor.value) == false)
              {              
                    alert("Ingresar Persona  que Firma Documento");
                    document.form_datos.firmadopor.focus();
                    return false;           
              } 
			 
			    
				 if(document.form_datos.medio.value=="0"){
						  alert("Ingresar  Medio");
				document.form_datos.medio.focus();
				return false;
				}
				
				 if(document.form_datos.codigo_tipo.value=="0"){
						  alert("Ingresar  Codigo Tipo");
				document.form_datos.codigo_tipo.focus();
				return false;
				}
				 if(document.form_datos.codigo_oficina.value=="0"){
						  alert("Ingresar  Codigo Oficina");
				document.form_datos.codigo_oficina.focus();
				return false;
				}
				 if(document.form_datos.personas.value=="0"){
						  alert("Ingresar  Personas");
				document.form_datos.personas.focus();
				return false;
				}
				
				if(document.form_datos.codigo_solicitud.value=="0"){
						  alert("Ingresar  Solicitud");
				document.form_datos.codigo_solicitud.focus();
				return false;
				}
			/**********************************/	
			if (Validarcampovacio(document.form_datos.desc_origen.value) == false )
			  {              
					alert("Ingresar Procedencia");
					document.form_datos.desc_origen.focus();
					return false;           
			  } 
			   if (Validarcampovacio(document.form_datos.dirigido.value) == false)
			  {              
					alert("Ingresar Documento Dirigido");
					document.form_datos.dirigido.focus();
					return false;           
			  } 
				
				//alert(document.getElementById("origen_documento").value);
				/*if(document.form_datos.origen_documento.value==null){
						  alert("Ingresar  Procedencia");
				document.form_datos.desc_origen.focus();
				return false;
				}*/
				
	medio = document.getElementById("medio").value
	codigo_tipo = document.getElementById("codigo_tipo").value
	destinatario = document.getElementById("personas").value
	//alert(destinatario);
	codigo_solicitud = document.getElementById("codigo_solicitud").value
	numero_documento = document.getElementById("numero_documento").value
	fecha_registro = document.getElementById("fecha_registro").value
	hora = document.getElementById("hora").value
	folios_documento = document.getElementById("folios_documento").value
	asunto_documento = document.getElementById("asunto_documento").value
	observa_documento = document.getElementById("observa_documento").value
	firmadopor = document.getElementById("firmadopor").value

	codigo_oficina = document.getElementById("codigo_oficina").value
	/*********************************/
	desc_origen = document.getElementById("desc_origen").value
	tipo_persona = document.getElementById("tipo_persona").value
	origen_documento = document.getElementById("origen_documento").value
	/**************************************/
	cbo_fecharpta = document.getElementById("cbo_fecharpta").value
	fecha_rpta = document.getElementById("fecha_rpta").value
	numero_referencia = document.getElementById("numero_referencia").value
	dirigido = document.getElementById("dirigido").value
	
	
	
	
	//alert(cbo_fecharpta);
	//alert(fecha_rpta);
	
	var radiogrp= document.form_datos.rb_seleccion
	//alert(radiogrp);
	var flag
	flag = "0" 
	
 
 <c:choose>
	<c:when test='${codigo_oficina ==  "1"}'>

 for (var i=0;i<radiogrp.length;i++)
	{
	
		if(radiogrp[i].checked)
		{
		 rb_seleccion  = radiogrp[i].value
		 flag = "1";
		//document.location.href="ActionSeleccion.do?"+"rb_seleccion="+rb_seleccion
		//alert(rb_seleccion);
		  break;
		 }
	 }
	 </c:when>
	<c:otherwise>
//alert("Diferente de 1..");
	rb_seleccion  = "I";
	flag = "1";
	</c:otherwise>
    </c:choose>	
	 

	 if(flag=="0")
	 {
	 alert("Seleccione la naturaleza")
	 return
	 }	
	 
	document.form_datos.action="MantDocumento.do?operacion=N"+"&medio="+medio
	+"&codigo_tipo="+codigo_tipo+"&codigo_oficina="+codigo_oficina+"&destinatario="+destinatario+"&codigo_solicitud="+codigo_solicitud+"&numero_documento="+numero_documento+"&fecha_registro="+fecha_registro+"&hora="+hora+"&folios_documento="+folios_documento+"&asunto_documento="+asunto_documento+"&observa_documento="+observa_documento+"&firmadopor="+firmadopor+"&origen_documento="+origen_documento+"&desc_origen="+desc_origen+"&tipo_persona="+tipo_persona+"&cbo_fecharpta="+cbo_fecharpta+"&fecha_rpta="+fecha_rpta+"&numero_referencia="+numero_referencia+"&rb_seleccion="+rb_seleccion+"&dirigido="+dirigido;
	
	document.form_datos.submit();	
	
}

function AgregarArchivos(){
   
    medio = document.getElementById("medio").value
	codigo_tipo = document.getElementById("codigo_tipo").value
	destinatario = document.getElementById("personas").value
	//alert(destinatario);
	codigo_solicitud = document.getElementById("codigo_solicitud").value
	numero_documento = document.getElementById("numero_documento").value
	fecha_registro = document.getElementById("fecha_registro").value
	hora = document.getElementById("hora").value
	folios_documento = document.getElementById("folios_documento").value
	asunto_documento = document.getElementById("asunto_documento").value
	observa_documento = document.getElementById("observa_documento").value
	firmadopor = document.getElementById("firmadopor").value
	operacionper = document.getElementById("operacion").value
	dirigido = document.getElementById("dirigido").value
	
	
	//alert(operacion);
	
	var sle_nombre_institucion="";
	var tipo="";
	var origen_documento="";
	var desc_origen="";
	var tipo_persona="";
	var naturaleza_documento="";
	
	/*if(operacionper==""){
	//alert("Dentro de operacion vacio");
	sle_nombre_institucion = document.getElementById("sle_nombre_institucion").value
	tipo = document.getElementById("tipo").value
	}else{
	
		//alert("Dentro de operacion diferente de vacio");
	origen_documento = document.getElementById("origen_documento").value
	desc_origen = document.getElementById("desc_origen").value
	tipo_persona = document.getElementById("tipo_persona").value
	naturaleza_documento = document.getElementById("naturaleza_documento").value
	
	}*/
	
	if(operacionper!=""){
	
		//alert("Dentro de operacion diferente de vacio");
	origen_documento = document.getElementById("origen_documento").value
	desc_origen = document.getElementById("desc_origen").value
	tipo_persona = document.getElementById("tipo_persona").value
	naturaleza_documento = document.getElementById("naturaleza_documento").value
	
	}
	
	//alert(origen_documento);
	codigo_oficina = document.getElementById("codigo_oficina").value
	
	cbo_fecharpta = document.getElementById("cbo_fecharpta").value
	fecha_rpta = document.getElementById("fecha_rpta").value
	numero_referencia = document.getElementById("numero_referencia").value

				var ls_operacion = "A";
				
				var strURL="MantDocumento.do?"+"operacion="+ls_operacion+"&medio="+medio
	+"&codigo_tipo="+codigo_tipo+"&codigo_oficina="+codigo_oficina+"&destinatario="+destinatario+"&codigo_solicitud="+codigo_solicitud+"&numero_documento="+numero_documento+"&fecha_registro="+fecha_registro+"&hora="+hora+"&folios_documento="+folios_documento+"&asunto_documento="+asunto_documento+"&observa_documento="+observa_documento+"&firmadopor="+firmadopor+"&origen_documento="+origen_documento+"&desc_origen="+desc_origen+"&tipo_persona="+tipo_persona+"&cbo_fecharpta="+cbo_fecharpta+"&fecha_rpta="+fecha_rpta+"&numero_referencia="+numero_referencia+"&desc_origen="+desc_origen+"&naturaleza_documento="+naturaleza_documento
	+"&tipo="+tipo+"&operacionper="+operacionper+"&dirigido="+dirigido;	
				//window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes")
				winPopup = window.open(strURL,"","scrollbars,HEIGHT=250,WIDTH=580")
			
		}

		function MostrarAdjunto(codigo){
 			
			//nombre_doc.value = codigo;
			//document.getElementById("doc_adjunto_img").value=codigo;
			//cod_emp.value = operacion;
			//					ActionSeleccion.do?rb_seleccion=E
			//alert(codigo);
			if(winPopup){
				if(!winPopup.closed) 
					winPopup.close();
					
					//var strURL = "MantDocumento.do?operacion=MA&codigo="+codigo;
					var strURL = "ValidaPaginas.do?tipo=seleccion&operacion=T&codigocontador="+codigo;
					location=strURL;
					//alert(strURL);
				}
				
		}
		
function MensajeRegistro(){
		var strURL="ValidaPaginas.do?tipo=mensajerecepcion"
 		window.open(strURL,"","HEIGHT=160,WIDTH=300,scrollbars=yes")
		}
				
function cambiaDestino(){

  document.forms[0].provincia.selectedIndex=0;
  document.forms[0].provincia.length=1;

}

function HabilitaText()
{
if (document.form_datos.cbo_fecharpta.checked)
{ document.form_datos.fecha_rpta.disabled = false; 
}
else
{document.form_datos.fecha_rpta.disabled = true;

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


var winPopup = 0;
function ayuda_doc_pend()
	{
	alert("Hola..");
		 var strURL = 'ValidaPaginas.do?tipo=ayudamedios';
		//strURL += '&num_doc='+num_doc;
			
	   winPopup = window.open(strURL,"","scrollbars,HEIGHT=540,WIDTH=700")
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
		
		
		function listadocumentos()
		{
		
		var strURL = "ListaDocumentos.do?tipo=modificar&operacion=ME&codigo_documento=0";
				
		 location=strURL;
		
		return;
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
					
</SCRIPT>
<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
  
    <TITLE>Registro de Documento de Entrada</TITLE>
  	    <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
        <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">

</HEAD>
<BODY bgColor="#F0F5FB" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" >
	
	<form id="form_datos" name="form_datos" method="post" action="" enctype='multipart/form-data'>	
	<INPUT  type="hidden" id="operacion" name="operacion" value="<c:out value='${operacion}'/>">
	 <c:choose>
		     <c:when test='${operacionpopup ==  "X"}'>
                <SCRIPT language="javascript">
				 MensajeRegistro();
				</SCRIPT>
       </c:when>
	</c:choose> 		  
    <TABLE borderColor="#e9e9e9" cellSpacing="0" cellPadding="0" width="100%" align="center" bgColor="#F0F5FB" border="0">
      <!--DWLayoutTable-->
        <TBODY>
		<TR>
            <TD width="978" height="50" align="middle" bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/> </TD>
        </TR>
        
        
        
        <TR>
            <TD height="555" align="middle" class="groupcell">  
          <TABLE id="TABLE1" width="100%" height="100%" align="center" border="0" bgColor="#F0F5FB" cellpadding="0">
            <!--DWLayoutTable-->
          
            <TBODY id="TBODY1">
             <TR class="formulario" id="TR2">
               <TD height="20" colspan="7" align="left" valign="middle" bgcolor="#FFFFFF" class="label2"><span class="label1">REGISTRO DOCUMENTO</span></TD>
               <TD width="50" rowspan="2" valign="top"><span class="label2"><a  href="javascript:listadocumentos()"><img src="img/modifica.gif" alt="Modificar Documentos" width="37" height="38" border="0" ></a></span></TD>
             </TR>
             <TR class="formulario" id="TR2"> 
                <TD height="14" colspan="7" align="center" valign="middle" bgcolor="BAD0EB" class="label2">Seleccione la Naturaleza</TD>
              </TR>
			    <TR class="formulario" id="TR2"> 
                <TD height="26" colspan="8" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label" id="TD5"> 
				  <c:choose>
				    <c:when test='${codigo_oficina ==  "1"}'>
				      <INPUT id="rb_seleccion" type="radio" value="E" name="rb_seleccion"  checked>
				      Externo&nbsp;<INPUT id="rb_seleccion" type="radio" value="I" name="rb_seleccion">
			        Interno				</c:when>
				    <c:otherwise>
			        <INPUT id="rb_seleccion" type="radio" value="I" name="rb_seleccion" checked>Interno				</c:otherwise>
			        </c:choose>				 </TD>
              </TR>
              
              <TR class="formulario" id="TR2"> 
                <TD height="18" colspan="2" align="right" valign="middle" class="label" id="TD5">Medio:</TD>
                <TD width="23" align="left" valign="middle" i><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD width="342" align="left" valign="middle" i>
				<select id="medio" name="medio" class="caja1" style="width:100" value=''>
                  <option value="0">--Selec--</option>
                  <option value="OR">Original</option>
                  <option value="FA">Fax </option>
                  <option value="CO">Copia Inf.</option>
                  <option value="EM">Email</option>
                </select>&nbsp;&nbsp;<A  href="javascript:ayuda_doc_pend()">Documentos Pendientes</A></TD>
                <TD width="84"  align="right" valign="middle"  >Procedencia :</TD>
                <TD width="4"  align="right" valign="middle" class="label" id="TD7"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD colspan="2" align="left" valign="top" id="TD8">
				  
				<c:choose>
				<c:when test='${codigo_oficina ==  "1"}'>

				 <c:choose> 
		<c:when test='${operacion == "T" }' > 
                  <INPUT class="txt" maxLength="40" size="40" id="desc_origen"  name="desc_origen" readonly="true" value="<c:out value='${descripcion_persona}'/>
                  "> 
                  <INPUT  type="hidden" maxLength="6" size="6" name="origen_documento" id="origen_documento" value='<c:out value='${codigo}'/>
                  '> 
                  <INPUT  type="hidden" maxLength="6" size="6" name="tipo_persona" id="tipo_persona" value='<c:out value='${tipopersona}'/>
                  '> </c:when> <c:otherwise> 
                    <INPUT class="txt"  maxLength="40" size="40" name="desc_origen" id="desc_origen"  readonly="true"  value="<c:out value='${descripcion_persona}'/>">
                    </c:otherwise> 
                </c:choose>
				</c:when>
				<c:otherwise>
				
				
				<c:choose> <c:when test='${operacion == "T" }' > 
                  
<INPUT class="txt" maxLength="40" size="40" id="desc_origen"  name="desc_origen" readonly="true" value="<c:out value='${desc_origen}'/>">
	<INPUT  type="hidden" maxLength="6" size="6" name="tipo_persona" id="tipo_persona" value="<c:out value='${tipo_persona}'/>">
<INPUT  type="hidden" maxLength="6" size="6" name="origen_documento" id="origen_documento" value="<c:out value='${origen_documento}'/>" >
 </c:when> <c:otherwise> 

 <INPUT class="txt"  maxLength="40" size="40" name="desc_origen" id="desc_origen"  readonly="true"  value="<c:out value='${desc_origen}'/>">
                    </c:otherwise> 
                </c:choose>
			</c:otherwise>
		        </c:choose>		
&nbsp;&nbsp;<A  href="javascript:buscar_persona()"><IMG style="WIDTH: 20px; HEIGHT: 14px" height="20"  src="img/flechadwnazul_on.gif" width="20" border="0" alt="Seleccionar Procedencia"></A></TD>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD height="18" colspan="2" align="right" class="label" id="TD5">Tipo Doc:</TD>
                <TD align="left" valign="middle" i><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD align="left" valign="middle" i> <select id="codigo_tipo" name="codigo_tipo" class="caja1" >
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  <c:choose> <c:when test='${not empty rs_tipodoc}'>
                    <c:forEach items='${rs_tipodoc}' var='pa'>
                      <option value=<c:out value='${pa.codigo_tipo}'/>> 
                      <c:out value='${pa.descripcion_tipo}'/> 
                      </option>
                      </c:forEach> 
                    </c:when> </c:choose> 
                  </select> </TD>
                <TD align="right" valign="middle" class="label" id="TD11">Tipo:</TD>
                <TD align="right" valign="middle" class="label" id="TD11"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD colspan="2" align="left" valign="top">
				
				<c:choose>
				<c:when test='${codigo_oficina ==  "1"}'>
 <c:choose> <c:when test='${operacion == "T" }' > 
                  <INPUT class="txt" id="naturaleza_documento" maxLength="40" size="20" name="naturaleza_documento" readonly="true" value='<c:out value='${tipopersona}'/>
                  '> </c:when> <c:otherwise> 
				    <INPUT class="txt" type="hidden" id="naturaleza_documento" maxLength="40" size="20" name="naturaleza_documento" readonly="true" value='<c:out value='${tipopersona}'/>
                  '> 
                    <INPUT class="txt" id="tipo" maxLength="40" size="20" name="tipo" readonly="true" value="<c:out value='${tipopersona}'/>">
                    </c:otherwise> 
                </c:choose>
				</c:when>
				<c:otherwise>
				
				<c:choose> <c:when test='${operacion == "T" }' > 
                 <INPUT class="txt" id="tipo" maxLength="40" size="20" name="tipo" readonly="true" value="<c:out value='${tipo_persona}'/>">
				   <INPUT class="txt" type="hidden" id="naturaleza_documento" maxLength="40" size="20" name="naturaleza_documento" readonly="true" value='<c:out value='${tipopersona}'/>
                  '> 
				  </c:when> <c:otherwise> 
                     <INPUT class="txt" id="tipo" maxLength="40" size="20" name="tipo" readonly="true" value="<c:out value='${tipo_persona}'/>">
                    </c:otherwise> 
                </c:choose>
			</c:otherwise>
		        </c:choose>				</TD>
              </TR>
              <TR class="formulario" id="TR3"> 
                <TD height="19" colspan="2" align="right" class="label" id="TD9">N&uacute;mero Doc.:</TD>
                <TD align="left" valign="middle"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD align="left" valign="middle"> <INPUT class="txt" id="numero_documento"  maxLength="40" size="40" name="numero_documento" value="<c:out value='${numero_documento}'/>">                </TD>
                <TD align="right" valign="middle" class="label" id="TD11">Firmado por:</TD>
                <TD align="right" valign="middle" class="label" id="TD11"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD colspan="2" align="left" valign="middle">
				<c:choose>
				<c:when test='${codigo_oficina ==  "1"}'>
				 <INPUT class="txt" id="firmadopor" maxLength="40" size="40" name="firmadopor" value="<c:out value='${firmadopor}'/>" > 
                  <INPUT id="INPUT4" type="hidden" maxLength="6" size="6" name="sle_codigo_contacto">    
				  </c:when>
				<c:otherwise>
				  <INPUT class="txt" id="firmadopor" maxLength="40" size="40" name="firmadopor" value="<c:out value='${firmado_por}'/>" > 
                  <INPUT id="INPUT4" type="hidden" maxLength="6" size="6" name="sle_codigo_contacto"> 
				  </c:otherwise>
		        </c:choose>				              </TD>
              </TR>
              <TR class="formulario" id="TR4"> 
                <TD height="18" colspan="2" align="right" class="label" id="TD13">Fecha Doc.:</TD>
                <TD align="left" valign="middle" id="TD14"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD align="left" valign="middle" id="TD14"> <INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_registro"  maxLength="10" size="11" name="fecha_registro" value="<c:out value='${fecha}'/>"> 
                &nbsp; <a href="" id="lanzador"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy </TD>
                <TD align="right" valign="middle" class="label">Dirigido a:</TD>
                <TD align="right" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD colspan="2" align="left" valign="middle" class="label">
				
				 <c:choose> <c:when test='${operacion_dir == "D"}' > 
                  <INPUT class="txt" id="dirigido" readonly="true" maxLength="40" size="40" name="dirigido" value="<c:out value='${descripcion_persona_dir}'/>" >
                   </c:when> <c:otherwise> 
                    <INPUT class="txt" id="dirigido" readonly="true" maxLength="40" size="40" name="dirigido" >
                    </c:otherwise> 
                </c:choose>
				
				&nbsp;&nbsp;<A  href="javascript:buscar_persona_dirigido()"><IMG style="WIDTH: 20px; HEIGHT: 14px" height="20"  src="img/flechadwnazul_on.gif" width="20" border="0" alt="Seleccionar Persona"></A>				 </TD>
              </TR>
              <TR class="formulario" id="TR4"> 
                <TD height="18" colspan="2" align="right" class="label" id="TD13">Hora:</TD>
                <TD align="left" valign="middle" id="TD14"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD align="left" valign="middle" id="TD14"> <INPUT language="JavaScript" class="txt"  id="hora" maxLength="8" size="9" name="hora" value="<c:out value='${hora}'/>">
&nbsp;00:00 </TD>
                <TD align="right" valign="middle" class="label" id="TD15">Destino :</TD>
                <TD align="right" valign="middle" class="label" id="TD15"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD colspan="2" align="left" valign="top" id="TD16">
				  <select id="codigo_oficina" name="codigo_oficina" class="caja12" style="WIDTH: 320px; HEIGHT: 21px">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_oficina}'>
					 <c:forEach items='${rs_oficina}' var='pa'>
						<option value=<c:out value='${pa.codigo_oficina}'/>> 
						<c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/>
						 </option> 
						</c:forEach> 
                    </c:when> </c:choose> 
		          </select> </TD>
              </TR>
              <TR id="TR5" class="label"> 
                <TD height="18" colspan="2" align="right" class="label" id="TD17">Folios:</TD>
                <TD align="left" valign="middle" id="TD18"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD align="left" valign="middle" id="TD18"> <INPUT class="txt" onKeyPress="return validatecla('enteros', this);" id="folios_documento" maxLength="3" size="3" name="folios_documento" value="<c:out value='${folios_documento}'/>">                </TD>
                <TD align="right" valign="middle" id="TD15">Destinatario :</TD>
                <TD align="right" valign="middle" class="label" id="TD15"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD colspan="2" align="left" valign="middle" id="TD16"> 
				  <c:choose>
				    <c:when test='${operacion ==  "N"}'>
				      <select id="personas" name="personas" class="caja12" >
				        <option value="0">:: Personas ::</option>
			          </select>
			        </c:when>
				    <c:otherwise>
				      <select id="personas" name="personas" class="caja12" style="WIDTH: 150px; HEIGHT: 21px">
				        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
				        <c:choose> <c:when test='${not empty rs_destinatario}'>
						 <c:forEach items='${rs_destinatario}' var='pa'>
						 	<option value=<c:out value='${pa.codigo_personal}'/>> 
				          <c:out value='${pa.nombre_personal}'/>
						   </option> 
						   </c:forEach> 
				          </c:when>
			            </c:choose> 
			          </select>
			        </c:otherwise>
		          </c:choose>				 </TD>
              </TR>
			  
			  <TR id="TR5" class="label"> 
                <TD colspan="2" align="right" class="label" id="TD17">Solicitud:</TD>
                <TD align="left" valign="middle" id="TD18"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD align="left" valign="middle" id="TD18"><select id="codigo_solicitud" name="codigo_solicitud" class="caja1" style="WIDTH: 300px; HEIGHT: 21px">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_solicitud}'> <c:forEach items='${rs_solicitud}' var='pa'>	<option value=<c:out value='${pa.codigo_solicitud}'/>> 
                    <c:out value='${pa.nombre_solicitud}'/> </option> </c:forEach> 
                    </c:when> </c:choose> 
                </select></TD>
				
                <TD rowspan="6" align="right" valign="middle" id="TD19"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD height="18" align="right" valign="middle" class="label" id="TD19"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD colspan="2" rowspan="6" align="left" valign="middle" id="TD20"><div id="scroll">
                  <table  >
                  
                    <c:forEach items='${rs_oficina}' var='pa'>
					<tr>
                      <td><input  class="caja" type="checkbox" name="selectedItems" value="<c:out value='${pa.codigo_oficina}'/>"></td>
                      <td class="texto_small">	
                            <c:out value='${pa.siglas_oficina}'/>
                           - 
                          <c:out value='${pa.descripcion_oficina}'/>                         </td>
                    </tr></c:forEach>
      </table></div></TD>
              </TR>
			  <TR id="TR5" class="label">
			    <TD colspan="2" align="right" class="label" id="TD17">Fecha de Respuesta Legal:</TD>
			    <TD align="left" valign="middle" id="TD18"><!--DWLayoutEmptyCell-->&nbsp;</TD>
			    <TD align="left" valign="middle" id="TD18"><input language="JavaScript" type="checkbox" class="caja" name="cbo_fecharpta" id="cbo_fecharpta" value="checkbox" onClick="javascript:HabilitaText();">
&nbsp;
<input language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta"  maxlength="10" size="11" name="fecha_rpta">
&nbsp; <a href="" id="lanzador1"  style="display: none; width: 20;" ><img src="img/cal.gif" alt="Fecha de Respuesta Legal (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy </TD>
			    <TD height="18" align="right" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
              </TR>
			  <TR id="TR5" class="label">
			    <TD colspan="2" align="right" class="label" id="TD17">N&deg; de Referencia :</TD>
			    <TD align="left" valign="middle" id="TD18"><!--DWLayoutEmptyCell-->&nbsp;</TD>
			    <TD align="left" valign="middle" id="TD18"><input class="txt" id="numero_referencia" onKeyPress="return validatecla('enteros', this);" maxlength="10" size="10" name="numero_referencia" value="<c:out value='${numero_referencia}'/>"></TD>
			    <TD height="18" align="right" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
              </TR>
              <TR class="" id="TR6"> 
                <TD height="18" colspan="2" align="right" valign="middle" class="label">Adjuntar Doc:</TD>
                <TD align="left" valign="middle" id="TD18"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD align="left" valign="middle" id="TD18"><input type="button"  id="btnUpload" name="btnUpload"  value="Agregar Archivos" onClick="AgregarArchivos()" alt="Agregar Archivos" styleclass="boton" >
&nbsp;
<c:choose>
  <c:when test='${operacion ==  "T" && codigocontador ==  "1"}'> <img src="img/docadjuntos.gif" alt="Documentos Adjuntos"> </c:when>
</c:choose></TD>
                <TD align="right" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
              </TR>
              <TR class="" id="TR6">
                <TD height="25" colspan="2" align="right" valign="middle" class="label">Asunto</TD>
                <TD align="left" valign="middle" id="TD18"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD align="left" valign="middle" id="TD18"><span class="label">
                  <textarea id="asunto_documento" style="WIDTH: 300px; HEIGHT: 70px" name="asunto_documento" rows="2" cols="71"><c:out value='${asunto_documento}'/></textarea>
                </span></TD>
                <TD rowspan="2" align="right" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
              </TR>
              <TR class="" id="TR6">
                <TD height="27" colspan="2" align="right" valign="middle" class="label">Observaci&oacute;n:</TD>
                <TD align="left" valign="middle" id="TD18"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <TD align="left" valign="middle" id="TD18"><span class="label">
                  <textarea id="observa_documento" style="WIDTH: 300px; HEIGHT: 70px" name="observa_documento" rows="2" cols="50"><c:out value='${observa_documento}'/></textarea>
                </span></TD>
              </TR>
              <tr>
                <td width="37" height="1"></td>
                <td width="21"></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td width="397"></td>
                <td></td>
              </tr>
            </TBODY>
          </TABLE>          </TD>
        </TR>
        </TBODY>
  </TABLE>
     <ajax:select baseUrl="${pageContext.request.contextPath}/obtenerLista.do" parameters="cambia=ofi&codigo_oficina={codigo_oficina}" source="codigo_oficina" target="personas"  />
			
  <TABLE width="100%" align="center" bgColor="#F0F5FB">
 
                <SCRIPT language="javascript">
					  SeleccionarCampo("medio","<c:out value='${medio_rq}'/>");
					  SeleccionarCampo("codigo_tipo","1");
					  SeleccionarCampo("codigo_oficina","<c:out value='${codigo_oficina_rq}'/>");
					  SeleccionarCampo("personas","<c:out value='${destinatario}'/>");
					  SeleccionarCampo("codigo_solicitud","<c:out value='${codigo_solicitud}'/>");
					  <c:choose>
						<c:when test='${codigo_oficina !=  "1"}'>
					  SeleccionarCampo("codigo_solicitud","1");
					  </c:when>
					  </c:choose>
					 
					 /*  <c:choose>
					    <c:when test='${operacion ==  "T"}'>
					   SeleccionarCampo("personas","<c:out value='${destinatario}'/>");
					   </c:when>
					 	 </c:choose>*/
					  //SeleccionarCampo("codigo_tipo","<c:out value='${codigo_tipo}'/>");
					inicio();
					function inicio(){
					//var scroll_chks = document.getElementById('scroll');
					with(document.forms[0]){
							fecha_rpta.disabled = "true"; 
							//scroll_chks.style.visibility = 'hidden'; 
							//document.form_datos.fecha_rpta.disabled = "true"; 
							}
					   
					};
					
					
					
			    </SCRIPT> 
                
    <TBODY>
        <TR>
            
        <TD align="center"> 
           <INPUT  class="boton" style="WIDTH: 100px; HEIGHT: 20px" id="INPUT5" onClick="javascript:Validar();" type="button" value="Registrar" name="cmb_grabar">
		 
          </TD>
        </TR>
    </TBODY>
  </TABLE>
	<SCRIPT type=text/javascript>
    Calendar.setup({
        inputField     :    "fecha_registro",      // id del campo de texto
        ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
        button         :    "lanzador" 

    });
	
	 Calendar.setup({
						inputField     :    "fecha_rpta",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador1" 

					});
					
</SCRIPT>
</form>

</BODY>
</HTML>
<% } %>