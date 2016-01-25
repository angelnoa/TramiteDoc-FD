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

<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
  
<TITLE>Registro de Documento de Entrada</TITLE>
<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/prototype-1.4.0.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/scriptaculous.js"></SCRIPT>

<SCRIPT LANGUAGE=javascript src="js/ajaxtags.js"></SCRIPT>
<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<script type="text/javascript" src="js/effects.js"></script>

<link rel="stylesheet" href="css/modelo.css" type="text/css" />
<link rel="stylesheet" href="css/tipografias.css"  type="text/css">

<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librer�a que declara la funci�n Calendar.setup, que ayuda a generar un calendario en unas pocas l�neas de c�digo -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>

<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />
<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
        
        
<SCRIPT type=text/javascript>
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
			
			accion="RM";
			
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
			strURL += "&accion="+accion;
	
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
				
				
	codigo_documento = document.getElementById("codigo_documento").value;
	codigo_expediente = document.getElementById("codigo_expediente").value;
	secuencia_movimiento = document.getElementById("secuencia_movimiento").value;
			
	medio = document.getElementById("medio").value;
	codigo_tipo = document.getElementById("codigo_tipo").value;
	destinatario = document.getElementById("personas").value;
	//alert(destinatario);
	codigo_solicitud = document.getElementById("codigo_solicitud").value;
	numero_documento = document.getElementById("numero_documento").value;
	fecha_registro = document.getElementById("fecha_registro").value;
	hora = document.getElementById("hora").value;
	folios_documento = document.getElementById("folios_documento").value;
	asunto_documento = document.getElementById("asunto_documento").value;
	observa_documento = document.getElementById("observa_documento").value;
	firmadopor = document.getElementById("firmadopor").value;
	origen_documento = document.getElementById("origen_documento").value;
	//alert(origen_documento);
	codigo_oficina = document.getElementById("codigo_oficina").value;
	
	desc_origen = document.getElementById("desc_origen").value;
	tipo_persona = document.getElementById("tipo_persona").value;
	
	cbo_fecharpta = document.getElementById("cbo_fecharpta").value;
	fecha_rpta = document.getElementById("fecha_rpta").value;
	numero_referencia = document.getElementById("numero_referencia").value;
	codigo_recepcion = document.getElementById("codigo_recepcion").value;
	dirigido = document.getElementById("dirigido").value;
	
	document.form_datos.action="MantDocumento.do?operacion=M"
	+"&medio="+medio+"&codigo_tipo="+codigo_tipo+"&codigo_oficina="+codigo_oficina+"&destinatario="+destinatario+
	"&codigo_solicitud="+codigo_solicitud+"&numero_documento="+numero_documento+"&fecha_registro="+fecha_registro+
	"&hora="+hora+"&folios_documento="+folios_documento+"&asunto_documento="+asunto_documento+
	"&observa_documento="+observa_documento+"&firmadopor="+firmadopor+"&origen_documento="+origen_documento+
	"&desc_origen="+desc_origen+"&tipo_persona="+tipo_persona+"&cbo_fecharpta="+cbo_fecharpta+"&fecha_rpta="+fecha_rpta+
	"&numero_referencia="+numero_referencia+"&rb_seleccion=E"+"&codigo_documento="+codigo_documento+
	"&codigo_expediente="+codigo_expediente+"&secuencia_movimiento="+secuencia_movimiento+"&codigo_recepcion="+codigo_recepcion+"&dirigido="+dirigido;
	
	document.form_datos.submit();	
	
}

function AgregarArchivos(){
   
				var ls_operacion = "A";
				
				var strURL='MantDocumento.do?'+'operacion='+ls_operacion;	
				window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes");
				
				
		}

function VerArchivos(ls_codigo_documento,ls_secuencia_movimiento){   
				var ls_operacion = "AM";				
				var strURL='MantDocumento.do?'+'operacion='+ls_operacion+"&codigo_documento="+ls_codigo_documento+"&secuencia_movimiento="+ls_secuencia_movimiento;	
				window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes");
				
				
		}
function MostrarAdjunto(codigo){
	/**
	Creado por Moises Pelaez 
	Fecha: 26-06-2011
	muestre la una imagen de clip si existen archivos adjuntos, sin 
	refrescar la pagina.
	*/	
	//fade('imagenAdjunto1');
	if(codigo>0){
		//mostrar imagen
		appear('imagenAdjunto1');
	}else{
		//ocultar imagen
		fade('imagenAdjunto1');
	}
}

function appear(idErrorElement) {
    new Effect.Appear(idErrorElement, 
      { duration: 1.0 });
}       
function fade(idErrorElement) {
      new Effect.Fade(idErrorElement, 
        { duration: 1.0 });
}
		
		
function MensajeRegistro(){
		var strURL="ValidaPaginas.do?tipo=mensajerecepcion";
 		window.open(strURL,"","HEIGHT=160,WIDTH=300,scrollbars=yes");
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
	
		 var strURL = 'ValidaPaginas.do?tipo=ayudamedios';
		//strURL += '&num_doc='+num_doc;
			
	   winPopup = window.open(strURL,"","scrollbars,HEIGHT=540,WIDTH=700");
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
		
		var strURL = "ListaDocumentos.do?tipo=modificar&operacion=ME";
				
		 location=strURL;
		
		return;
		}
		
		function regresar()
		{
		
				
			var strURL = "ListaDocumentos.do?tipo=modificar&operacion=ME";
			 location=strURL;
			return;
		
		}
		
		function showProducts(){
		    //obtiene los objetos productCode, 
		    var code=$("#codigo_oficina").val(); //.. y se obtiene el valor
		    var val2 = 'ofiInterno';
		    //llama al servlet con el parametro seleccionado
		    $("#personas").load("obtenerListaDestinatarios.do", {cambia:val2,codigo_oficina:code});
		}
			
</SCRIPT>

</HEAD>
<BODY bgColor="#F0F5FB" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0"  >
<div id="container">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>

	<form id="form_datos" name="form_datos" method="post" action="" enctype='multipart/form-data'>	
	<INPUT  type="hidden" name="codigo_documento" id="codigo_documento" value="<c:out value='${docEntrada.codigo_documento}'/>"> 
	<INPUT  type="hidden" name="codigo_expediente" id="codigo_expediente" value="<c:out value='${docEntrada.codigo_expediente}'/>"> 
	<INPUT  type="hidden" name="secuencia_movimiento" id="secuencia_movimiento" value="<c:out value='${docEntrada.secuencia_movimiento}'/>"> 
	<INPUT  type="hidden" name="codigo_recepcion" id="codigo_recepcion" value="<c:out value='${docEntrada.codigo_recepcion}'/>"> 
	 <c:choose>
		     <c:when test='${operacionpopup ==  "X"}'>
                <SCRIPT language="javascript">
				 MensajeRegistro();
				</SCRIPT>
       </c:when>
	</c:choose> 		  
    <br/>
    <br/>
    <TABLE borderColor="#e9e9e9" cellSpacing="0" cellPadding="0" width="100%" align="center" bgColor="#F0F5FB" border="0">
      <!--DWLayoutTable-->
        <TBODY>
		
        <TR class="cabeceratable"  background="img/fondoplomo8.jpg">
            <TD width="224" height="25" align="left" valign="middle"  class="label1">Modificar Documento </TD>
        <TD width="605" align="left" valign="middle">
          <span class="textomensaje14bold">
          <c:out value='${mensajeregistro}'/>
          </span>
		  </TD>
        <TD width="149" align="right" valign="middle"><A  href="javascript:regresar()"><img  src="img/salir.gif" alt="Ir a la lista Recepcion documentos" border="0" ></A></TD>
        </TR>
        
        
        <TR>
            <TD height="377" colspan="3" align="middle" class="groupcell">  
          <TABLE id="TABLE1" width="100%" height="100%" align="center" border="0" bgColor="#F0F5FB" cellpadding="0">
            <!--DWLayoutTable-->
          
            <TBODY id="TBODY1">
             <TR class="formulario" id="TR2"> 
                <TD height="20" colspan="4" align="center" valign="middle" bgcolor="BAD0EB" class="label2">Seleccione la Naturaleza</TD>
                <td width="2"></td>
             </TR>
			    <TR class="formulario" id="TR2"> 
                <TD height="25" colspan="4" align="center" valign="middle" class="label" id="TD5" background="img/fondoplomo8.jpg">					 </TD>
                <td></td>
		      </TR>
              <TR class="formulario" id="TR2"> 
                <TD width="82" height="25" align="right" valign="middle" class="label" id="TD5">Medio:</TD>
                <TD width="397" align="left" valign="middle" >
				<c:choose>
        <c:when test='${operacion ==  "N" }'>
		<select id="medio" name="medio" class="caja" style="width:100" value=''>
		  <option value="0">--Selec--</option>
		  <option value="OR">Original</option>
		  <option value="FA">Fax </option>
		  <option value="CO">Copia Inf.</option>
		  <option value="EM">Email</option>
		</select>&nbsp;&nbsp;<A  href="javascript:ayuda_doc_pend()">Documentos Pendientes</A></c:when>
        <c:when test='${operacion ==  "M" }'>
		<select id="medio" name="medio" class="caja" style="width:100" value=''>
		  <option value="0">--Selec--</option>
		  <option value="OR">Original</option>
		  <option value="FA">Fax </option>
		  <option value="CO">Copia Inf.</option>
		  <option value="EM">Email</option>
		</select>
</c:when>
		 <c:otherwise>
		<select id="medio" name="medio" class="caja" disabled style="width:100" value=''>
		  <option value="0">--Selec--</option>
		  <option value="OR">Original</option>
		  <option value="FA">Fax </option>
		  <option value="CO">Copia Inf.</option>
		  <option value="EM">Email</option>
		</select>
</c:otherwise>
		  </c:choose>				</TD>
                <TD width="86"  align="right" valign="middle" class="label" id="TD7">Procedencia :</TD>
                <TD width="397" align="left" valign="top" id="TD8">
				  
				 <c:choose>
				  <c:when test='${operacionper == "T" }' > 
                  <INPUT class="txt" maxLength="40" size="40" id="desc_origen"  name="desc_origen" readonly="true" value="<c:out value='${descripcion_persona}'/>
                  "> 
                  <INPUT  type="hidden" maxLength="6" size="6" name="origen_documento" id="origen_documento" value='<c:out value='${codigo}'/>
                  '> 
                  <INPUT  type="hidden" maxLength="6" size="6" name="tipo_persona" id="tipo_persona" value='<c:out value='${tipopersona}'/>
                  '> </c:when> 
				  
				   <c:when test='${operacion == "M" }' > 
                  <INPUT class="txt"  maxLength="40" size="40" value="<c:out value='${docEntrada.desc_origen}'/>"  name="desc_origen" id="desc_origen" readonly="true" >
	<INPUT  type="hidden" maxLength="6" size="6" name="origen_documento" id="origen_documento" value="<c:out value='${docEntrada.remitente}'/>"> 
             <INPUT  type="hidden" maxLength="6" size="6" name="tipo_persona" id="tipo_persona" value="<c:out value='${docEntrada.tipo}'/>">
				  </c:when> 
				  
				  <c:otherwise> 
                   ---                    </c:otherwise> 
                </c:choose>
				
&nbsp;&nbsp;<A  href="javascript:buscar_persona()"><IMG style="WIDTH: 20px; HEIGHT: 14px" height="20"  src="img/flechadwnazul_on.gif" width="20" border="0" alt="Seleccionar Procedencia"></A>				</TD>
                <td></td>
              </TR>
              <TR class="formulario" id="TR2"> 
                <TD height="25" align="right" id="TD5" class="label">Tipo Doc:</TD>
                <TD align="left" valign="middle" i> 
				<c:choose>
        <c:when test='${operacion ==  "N" }'>
		<select id="codigo_tipo" name="codigo_tipo" class="caja" >
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  <c:choose> <c:when test='${not empty rs_tipodoc}'>
                    <c:forEach items='${rs_tipodoc}' var='pa'>
                      <option value=<c:out value='${pa.codigo_tipo}'/>> 
                      <c:out value='${pa.descripcion_tipo}'/> 
                      </option>
                      </c:forEach> 
                    </c:when> </c:choose> 
                  </select> 
</c:when>
        <c:when test='${operacion ==  "M" }'>
	<select id="codigo_tipo" name="codigo_tipo" class="caja" >
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  <c:choose> <c:when test='${not empty rs_tipodoc}'>
                    <c:forEach items='${rs_tipodoc}' var='pa'>
                      <option value=<c:out value='${pa.codigo_tipo}'/>> 
                      <c:out value='${pa.descripcion_tipo}'/> 
                      </option>
                      </c:forEach> 
                    </c:when> </c:choose> 
                  </select> 
</c:when>
		 <c:otherwise>
<select id="codigo_tipo" name="codigo_tipo" disabled class="caja" >
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  <c:choose> <c:when test='${not empty rs_tipodoc}'>
                    <c:forEach items='${rs_tipodoc}' var='pa'>
                      <option value=<c:out value='${pa.codigo_tipo}'/>> 
                      <c:out value='${pa.descripcion_tipo}'/> 
                      </option>
                      </c:forEach> 
                    </c:when> </c:choose> 
                  </select> 
</c:otherwise>
		  </c:choose>				  </TD>
                <TD align="right" valign="middle" class="label" id="TD11">Tipo:</TD>
                <TD align="left" valign="middle">
				 <c:choose>
				  <c:when test='${operacion == "T" || operacionper=="T" }' > 
                    <INPUT class="txt" id="naturaleza_documento" maxLength="40" size="20" name="naturaleza_documento" readonly="true" value='<c:out value='${tipopersona}'/>
							  '>				 </c:when> 
				  <c:when test='${operacion == "M" }' > 
				 <INPUT class="txt" id="INPUT3" maxLength="40" size="20" value="<c:out value='${docEntrada.tipo}'/>" name="tipo" readonly="true" >
                   </c:when> 
				  <c:otherwise> 
                    <INPUT class="txt" id="INPUT3" maxLength="40" size="20" value="<c:out value='${docEntrada.tipo}'/>" name="tipo" readonly="true" >
                    </c:otherwise> 
                </c:choose>				 </TD>
                <td></td>
              </TR>
              <TR class="formulario" id="TR3"> 
                <TD height="25" align="right" id="TD9" class="label">N&uacute;mero Doc.:</TD>
                <TD align="left" valign="middle"> 
				<c:choose>
        <c:when test='${operacion ==  "N" }'>
<INPUT class="txt" id="numero_documento" maxLength="40" size="40" name="numero_documento" value="<c:out value='${numero_documento}'/>">               
</c:when>
        <c:when test='${operacion ==  "M" }'>
<INPUT class="txt" id="numero_documento" maxLength="40" size="40" name="numero_documento" value="<c:out value='${docEntrada.numero_documento}'/>">               
</c:when>
		 <c:otherwise>
<INPUT class="txt" id="numero_documento" maxLength="40" size="40" readonly name="numero_documento" value="<c:out value='${numero_documento}'/>">
</c:otherwise>
		  </c:choose>				 </TD>
                <TD align="right" valign="middle" class="label" id="TD11">Firmado por:</TD>
                <TD align="left" valign="middle">
				<c:choose>
        <c:when test='${operacion ==  "N" }'>
<INPUT class="txt" id="firmadopor" maxLength="40" size="40" name="firmadopor" > 
</c:when>
        <c:when test='${operacion ==  "M" }'>
<INPUT class="txt" id="firmadopor" maxLength="40" size="40" name="firmadopor" value="<c:out value='${docEntrada.firmadopor}'/>" > 
</c:when>
		 <c:otherwise>
<INPUT class="txt" id="firmadopor" readonly maxLength="40" size="40" name="firmadopor" value="<c:out value='${docEntrada.firmadopor}'/>"> 
</c:otherwise>
		  </c:choose>                               </TD>
                <td></td>
              </TR>
              <TR class="formulario" id="TR4"> 
                <TD height="25" align="right" class="label" id="TD13">Fecha Doc.:</TD>
                <TD align="left" valign="middle" id="TD14"> 
				<c:choose>
        <c:when test='${operacion ==  "N" }'>
<INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_registro"  maxLength="10" size="11" name="fecha_registro" value="<c:out value='${fecha}'/>"> 
</c:when>
        <c:when test='${operacion ==  "M" }'>
<INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_registro"  maxLength="10" size="11" name="fecha_registro" value="<c:out value='${docEntrada.fecha_registro}'/>"> 
</c:when>
		 <c:otherwise>
<INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_registro"  maxLength="10" size="11" name="fecha_registro" value="<c:out value='${docEntrada.fecha_registro}'/>"> 
</c:otherwise>
		  </c:choose> 
				&nbsp; <a href="" id="lanzador"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy				 </TD>
                <TD align="right" valign="middle" class="label" >Dirigido a:</TD>
                <TD align="left" valign="middle" class="label" >
				<c:choose>
        <c:when test='${operacion ==  "N" }'>
<INPUT class="txt" id="dirigido" maxLength="40" size="40" name="dirigido" value="<c:out value='${dirigido}'/>" >
</c:when>
        <c:when test='${operacion ==  "M" }'>
		<INPUT class="txt" id="dirigido" maxLength="40" size="40" name="dirigido" value="<c:out value='${docEntrada.dirigido}'/>" >
</c:when>
		 <c:otherwise>
		 		<INPUT class="txt" id="dirigido" maxLength="40" size="40" readonly name="dirigido" value="<c:out value='${docEntrada.dirigido}'/>" >
</c:otherwise>
		  </c:choose> 
				
				
				</TD>
                <td></td>
              </TR>
              
              <TR class="formulario" id="TR4"> 
                <TD height="25" align="right" valign="middle" class="label" id="TD13">Hora:</TD>
                <TD align="left" valign="middle" id="TD14">
				<c:choose>
				  <c:when test='${operacion ==  "N" }'>
				    <INPUT language="JavaScript" class="txt"  id="hora" maxLength="8" size="9" name="hora" value="<c:out value='${hora}'/>">
				    </c:when>
				  <c:when test='${operacion ==  "M" }'>		 
				    <INPUT language="JavaScript" class="txt"  id="hora" maxLength="8" size="9" name="hora" value="<c:out value='${docEntrada.hora_movimiento}'/>">
				    </c:when>
				  <c:otherwise>
				    <INPUT language="JavaScript" class="txt"  id="hora" maxLength="8" size="9" name="hora" value="<c:out value='${docEntrada.hora_movimiento}'/>">
				    </c:otherwise>
				        </c:choose>
&nbsp;00:00 </TD>
                <TD align="right" valign="middle" class="label" id="TD15">Destino :</TD>
                <TD align="left" valign="top" id="TD16">
				  <c:choose>
				    <c:when test='${operacion ==  "N" }'>
				      <select id="codigo_oficina" name="codigo_oficina" class="caja" style="WIDTH: 300px; HEIGHT: 21px"  onchange="showProducts()">
				        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
				        <c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
				          <c:out value='${pa.nombre_corto}'/> </option> </c:forEach> 
			            </c:when> </c:choose> 
			          </select> 
			        </c:when>
				    <c:when test='${operacion ==  "M" }'>
				      <select id="codigo_oficina" name="codigo_oficina" class="caja" style="WIDTH: 300px; HEIGHT: 21px" onchange="showProducts()">
				        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
				        <c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
				          <c:out value='${pa.nombre_corto}'/> </option> </c:forEach> 
			            </c:when> </c:choose> 
			          </select> 
			        </c:when>
				    <c:otherwise>
				      <select id="codigo_oficina" disabled name="codigo_oficina" class="caja" style="WIDTH: 300px; HEIGHT: 21px" onchange="showProducts()">
				        <OPTION value="0" selected> -- Selecc Uno -- </OPTION> 
				        <c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
				          <c:out value='${pa.nombre_corto}'/> </option> </c:forEach> 
			            </c:when> </c:choose> 
			          </select> 
			        </c:otherwise>
		                          </c:choose>				  </TD>
                <td></td>
              </TR>
              
              <TR id="TR5" class="label">
                <TD height="25" align="right" valign="middle" class="label" id="TD17">Folios:</TD>
                <TD align="left" valign="middle" id="TD18">
				  <c:choose>
				    <c:when test='${operacion ==  "N" }'>
				      <INPUT class="txt" id="folios_documento" maxLength="3" size="3" name="folios_documento">  
			        </c:when>
				    <c:when test='${operacion ==  "M" }'>
				      <INPUT class="txt" id="folios_documento" maxLength="3" size="3" name="folios_documento" value="<c:out value='${docEntrada.folios_documento}'/>">  
			        </c:when>
				    <c:otherwise>
				      <INPUT class="txt" id="folios_documento" maxLength="3" size="3" name="folios_documento" value="<c:out value='${docEntrada.folios_documento}'/>">  
			        </c:otherwise>
		                </c:choose>				               </TD>
                <TD align="right" valign="middle" class="label" id="TD15">Destinatario :</TD>
                <TD align="left" valign="middle" id="TD16"> 
				  <c:choose>
				    <c:when test='${operacion ==  "N"}'>
				      <select id="personas" name="personas" class="caja" >
				        <option value="0">:: Personas ::</option>
			          </select>
			        </c:when>
				    <c:otherwise>
				      <select id="personas" name="personas" class="caja" style="WIDTH: 150px; HEIGHT: 21px">
				        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
				        <c:choose> <c:when test='${not empty rs_destinatario}'> <c:forEach items='${rs_destinatario}' var='pa'>	<option value=<c:out value='${pa.codigo_personal}'/>> 
				          <c:out value='${pa.nombre_personal}'/> </option> </c:forEach> 
				          </c:when>
			            </c:choose> 
				        </select>
			        </c:otherwise>
			              </c:choose>				 </TD>
                <td></td>
              </TR>
              <TR class="formulario" id="TR6"> 
                <TD rowspan="2" align="right" class="label" height="35">Asunto:</TD>
                <TD rowspan="2" valign="top" class="formulario" id="TD21"> 
				<c:choose>
        <c:when test='${operacion ==  "N" }'>
				<TEXTAREA id="asunto_documento" style="WIDTH: 300px; HEIGHT: 72px" name="asunto_documento" rows="4" cols="71"></TEXTAREA>
			</c:when>
        <c:when test='${operacion ==  "M" }'>	
		<TEXTAREA id="asunto_documento" style="WIDTH: 300px; HEIGHT: 72px" name="asunto_documento" rows="4" cols="71"><c:out value='${docEntrada.asunto_documento}'/></TEXTAREA>
		</c:when>
		 <c:otherwise>
		 <TEXTAREA id="asunto_documento" style="WIDTH: 300px; HEIGHT: 72px"  disabled name="asunto_documento" rows="4" cols="71"><c:out value='${docEntrada.asunto_documento}'/></TEXTAREA>
		 </c:otherwise>
		  </c:choose>				                </TD>
                
                <TD height="25" align="right" valign="middle" class="label" id="TD19">Solicitud :</TD>
                <TD align="left" valign="top" id="TD20">
				  <c:choose>
				    <c:when test='${operacion ==  "N" }'>
				      <select id="codigo_solicitud" name="codigo_solicitud" class="caja" style="WIDTH: 300px; HEIGHT: 21px">
				        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
				        <c:choose> <c:when test='${not empty rs_solicitud}'> <c:forEach items='${rs_solicitud}' var='pa'>	<option value=<c:out value='${pa.codigo_solicitud}'/>> 
				          <c:out value='${pa.nombre_solicitud}'/> </option> </c:forEach> 
			            </c:when> </c:choose> 
			          </select> 
			        </c:when>
				    <c:when test='${operacion ==  "M" }'>	
				      <select id="codigo_solicitud" name="codigo_solicitud" class="caja" style="WIDTH: 300px; HEIGHT: 21px">
				        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
				        <c:choose> <c:when test='${not empty rs_solicitud}'> <c:forEach items='${rs_solicitud}' var='pa'>	<option value=<c:out value='${pa.codigo_solicitud}'/>> 
				          <c:out value='${pa.nombre_solicitud}'/> </option> </c:forEach> 
			            </c:when> </c:choose> 
			          </select> 
			        </c:when>
				    <c:otherwise>
				      <select id="codigo_solicitud" disabled name="codigo_solicitud" class="caja" style="WIDTH: 300px; HEIGHT: 21px">
				        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
				        <c:choose> <c:when test='${not empty rs_solicitud}'> <c:forEach items='${rs_solicitud}' var='pa'>	<option value=<c:out value='${pa.codigo_solicitud}'/>> 
				          <c:out value='${pa.nombre_solicitud}'/> </option> </c:forEach> 
			            </c:when> </c:choose> 
			          </select> 
			        </c:otherwise>
		                          </c:choose>				</TD>
                <td></td>
              </TR>
              
              <TR class="formulario" id="TR6">
                <TD height="25" align="right" valign="middle" class="label">Fecha de Respuesta Legal:</TD>
                <TD valign="middle">
				  <c:choose>
				    <c:when test='${operacion ==  "N" }'>
				      <input language="JavaScript" type="checkbox" class="caja" name="cbo_fecharpta" id="cbo_fecharpta" value="checkbox" onClick="javascript:HabilitaText();">
				      &nbsp;
				      <INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta"  maxLength="10" size="11" name="fecha_rpta"> 
			        &nbsp; <a href="" id="lanzador1"  style="display: none; width: 20;" ><img src="img/cal.gif" alt="Fecha de Respuesta Legal (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy			</c:when>
				    <c:when test='${operacion ==  "M" }'>	
				      <input language="JavaScript" type="checkbox" class="caja" name="cbo_fecharpta" id="cbo_fecharpta" value="checkbox" onClick="javascript:HabilitaText();">
				      &nbsp;
				      <INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta"  maxLength="10" size="11" name="fecha_rpta" value="<c:out value='${docEntrada.fecha_rpta}'/>"> 
			        &nbsp; <a href="" id="lanzador1"  style="display: none; width: 20;" ><img src="img/cal.gif" alt="Fecha de Respuesta Legal (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy			</c:when>
				    <c:otherwise>
				      <input language="JavaScript" type="checkbox" class="caja" name="cbo_fecharpta" id="cbo_fecharpta" value="checkbox" onClick="javascript:HabilitaText();">
				      &nbsp;
				      <INPUT language="JavaScript" readonly class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta"  maxLength="10" size="11" name="fecha_rpta"> 
			        &nbsp; <a href="" id="lanzador1"  style="display: none; width: 20;" ><img src="img/cal.gif" alt="Fecha de Respuesta Legal (dd/mm/yyyy)" border="0"></a> &nbsp;dd/mm/yyyy				</c:otherwise>
		                        </c:choose>				</TD>
                <td></td>
              </TR>
              
              
              
              
              <TR class="formulario" id="TR7"  > 
                <TD rowspan="3" align="right" class="label">Observaci&oacute;n:</TD>
                <TD rowspan="3" valign="top" class="formulario" id="TD25" height="30"> 
				<c:choose>
        <c:when test='${operacion ==  "N" }'>
				<TEXTAREA id="observa_documento" style="WIDTH: 300px; HEIGHT: 72px" name="observa_documento" rows="4" cols="50"></TEXTAREA>       
		</c:when>
        <c:when test='${operacion ==  "M" }'>
				<TEXTAREA id="observa_documento" style="WIDTH: 300px; HEIGHT: 72px" name="observa_documento" rows="4" cols="50"><c:out value='${docEntrada.observa_documento}'/></TEXTAREA>       
				</c:when>
		 <c:otherwise>
		 <TEXTAREA id="observa_documento"  disabled style="WIDTH: 300px; HEIGHT: 72px" name="observa_documento" rows="4" cols="50"><c:out value='${docEntrada.observa_documento}'/></TEXTAREA>  
		 </c:otherwise>
		  </c:choose>				         </TD>
                <TD height="25" align="right" valign="middle" class="label">N&deg; de Referencia :</TD>
                <td align="left" valign="middle">
				  <c:choose>
				    <c:when test='${operacion ==  "N" }'>
				      <INPUT class="txt" id="numero_referencia" maxLength="10" size="10" name="numero_referencia">
			        </c:when>
				    <c:when test='${operacion ==  "M" }'>
				      <INPUT class="txt" id="numero_referencia" maxLength="10" size="10" value="<c:out value='${docEntrada.numero_referencia}'/>" name="numero_referencia">
			        </c:when>
				    <c:otherwise>
				      <INPUT class="txt" id="numero_referencia" maxLength="10" size="10" value="<c:out value='${docEntrada.numero_referencia}'/>" name="numero_referencia">
			        </c:otherwise>
		                    </c:choose>				</td>
                <td></td>
              </TR>
              
              
              <TR class="formulario" id="TR7">
                <TD height="25" align="right" valign="middle" class="label">Adjuntar Doc:</TD>
                <TD align="left" valign="middle">
				  <c:choose>
				    <c:when test='${operacion ==  "N" }'>
				      <input type="button"  id="btnUpload" name="btnUpload"  value="Agregar Archivos" onClick="AgregarArchivos()" alt="Agregar Archivos" styleClass="boton" >
			        </c:when>
				    <c:when test='${operacion ==  "M" }'>
				      <input type="button"  id="btnUpload" name="btnUpload"  value="Ver Archivos" onClick="VerArchivos('<c:out value='${docEntrada.codigo_documento}'/>','<c:out value='${docEntrada.secuencia_movimiento}'/>')" alt="Ver Archivos" styleClass="boton" >
			        </c:when>
				    <c:otherwise>
				      <input type="button"  id="btnUpload" name="btnUpload"  value="Ver Archivos" onClick="VerArchivos('<c:out value='${docEntrada.codigo_documento}'/>','<c:out value='${docEntrada.secuencia_movimiento}'/>')" alt="Ver Archivos" styleClass="boton" >
			        </c:otherwise>
		           </c:choose>		          
		           <c:choose>
			           <c:when test='${numeroArchivosAdjuntos > 0 }'>
					   <img src="img/docadjuntos.gif" id="imagenAdjunto1" alt="Documentos Adjuntos">				
					   </c:when>
				   </c:choose>
		           
		            </TD>
                <td></td>
              </TR>
              
              <TR class="formulario" id="TR7">
                <TD height="25" colspan="2" valign="top"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                <td></td>
              </TR>
            </TBODY>
          </TABLE>          </TD>
        </TR>
        </TBODY>
  </TABLE>
     
			
  <TABLE width="100%" align="center" bgColor="#F0F5FB">
 
                <SCRIPT language="javascript">
				inicio();
					function inicio(){
					with(document.forms[0]){
							fecha_rpta.disabled = "true"; 
							//cbo_copia.disabled = "true"; 
							//document.form_datos.fecha_rpta.disabled = "true"; 
							}
					   
					};
				//alert("<c:out value='${operacion}'/>");	
				if("<c:out value='${operacion}'/>"=="N"){
					  SeleccionarCampo("medio","OR");
					  SeleccionarCampo("codigo_tipo","1");
					  SeleccionarCampo("codigo_oficina","2");
					  
					
				}else{
				SeleccionarCampo("codigo_oficina","<c:out value='${docEntrada.codigo_oficina}'/>");
				SeleccionarCampo("personas","<c:out value='${docEntrada.destinatario}'/>");
				SeleccionarCampo("codigo_tipo","<c:out value='${docEntrada.codigo_tipo}'/>");
				SeleccionarCampo("medio","<c:out value='${docEntrada.medio}'/>");
				SeleccionarCampo("codigo_solicitud","<c:out value='${docEntrada.codigo_solicitud}'/>");
				}
					  
			    </SCRIPT> 
                
    <TBODY>
        <TR>
            
        <TD align="center"> 
           <INPUT  class="boton" style="WIDTH: 100px; HEIGHT: 20px" id="INPUT5" onClick="javascript:Validar();" type="button" value="Modificar" name="cmb_grabar">
		 
          </TD>
        </TR>
    </TBODY>
  </TABLE>
	<SCRIPT type=text/javascript>

    Calendar.setup({
        inputField     :    "fecha_registro",      // id del campo de texto
        ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
        button         :    "lanzador" 
		   // el id del bot�n que lanzar� el calendario
    });
	
	 Calendar.setup({
						inputField     :    "fecha_rpta",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador1" 
						   // el id del bot�n que lanzar� el calendario
					});
					
</SCRIPT>
</form>
</div>
</BODY>
</HTML>
<% } %>