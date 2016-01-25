<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<%@ page import="org.apache.struts.action.*, org.apache.struts.Globals" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<SCRIPT LANGUAGE=javascript src="js/prototype-1.4.0.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/scriptaculous.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/overlibmws.js"></SCRIPT>
<script LANGUAGE="JavaScript" src="js/avatec.js"></script>
<SCRIPT LANGUAGE=javascript src="js/ajaxtags.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/effects.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/controls.js"></SCRIPT>


<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librera para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>

<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>

 <link rel="stylesheet" href="css/avatec.css" type="text/css" />
 <link rel="stylesheet" href="css/table.css" type="text/css" />
 <link rel="stylesheet" href="css/modelo.css" type="text/css" />


<script type="text/javascript">
function fn_onLoad(){
	cambiar_color_tabla("b", "tablaBackgray");

}

function ocultar(element,valor){
	if (valor=="mostrar"){
	              document.getElementById(element).style.visibility="visible";
	}else {
	              document.getElementById(element).style.visibility="hidden";
	}
	
	function mostrar(nombreCapa){ 
		document.getElementById(nombreCapa).style.visibility="visible"; 
		} 
	function ocultar(nombreCapa){ 
		document.getElementById(nombreCapa).style.visibility="hidden"; 
		}
		
</script>
  
<script type="text/javascript">

function toogle(a,b,c)
{
document.getElementById(b).style.display=a;
document.getElementById(c).style.display=a;
}

</script>
<!-- <link rel="stylesheet" href="css/botones.css" type="text/css"></link> -->
<!-- <link rel="stylesheet" href="css/live_examples.css" type="text/css"></link> -->
<!-- <style type="text/css">
#modal
{
position: absolute;
padding: 0;
margin: 0;
width: 100%;
height: 200%;
z-index: 500;
filter: alpha(opacity=50);
opacity: 0.8;
-moz-opacity:0.8;
-webkit-opacity:0.8;
-o-opacity:0.8;
-ms-opacity:0.8;
background-color: #808080;
left: 0;
top: 0;
overflow: auto;
}

.contenedor
{
width: 500px;
background: #fff;
position: relative;
margin: 10% auto;
padding: 30px;
-moz-border-radius: 7px;
border-radius: 7px;
-webkit-box-shadow: 0 3px 20px rgba(0,0,0,0.9);
-moz-box-shadow: 0 3px 20px rgba(0,0,0,0.9);
box-shadow: 0 3px 20px rgba(0,0,0,0.9);
background: -moz-linear-gradient(#fff, #ccc);
background: -webkit-gradient(linear, right bottom, right top, color-stop(1, rgb(255,255,255)), color-stop(0.57, rgb(230,230,230)));
text-shadow: 0 1px 0 #fff;
}

.contenedor h2 {
font-size: 36px;
padding: 0 0 20px;
}

.contenedor a[href="#close"] {
position: absolute;
right: 0;
top: 0;
color: transparent;
}

.contenedor a[href="#close"]:focus {
outline: none;
}

.contenedor a[href="#close"]:after {
content: 'X';
display: block;
position: absolute;
right: -10px;
top: -10px;
width: 1.5em;
padding: 1px 1px 1px 2px;
text-decoration: none;
text-shadow: none;
text-align: center;
font-weight: bold;
background: #000;
color: #fff;
border: 3px solid #fff;
-moz-border-radius: 20px;
border-radius: 20px;
-webkit-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
-moz-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
box-shadow: 0 1px 3px rgba(0,0,0,0.5);
}

.contenedor a[href="#close"]:focus:after,
.contenedor a[href="#close"]:hover:after {
-webkit-transform: scale(1.1,1.1);
-moz-transform: scale(1.1,1.1);
}
</style> -->

<script language="JavaScript">

function Guardar()
{
	document.forms[0].submit();
	
}
 
 function listadocumentos()
	{
	
	var strURL = "ListaDocumentos.do?tipo=anular&operacion=A&inicia=SI&document=SI";
			
	 location=strURL;
	
	return;
	}
 
 function AgregarDocInternos() {

	//alert("Internos");
	
	var strURL="AdjuntarDocumentoInterno.do";
	winPopup = window.open(strURL,"","scrollbars=YES,resizable=YES,location=NO,WIDTH=800,HEIGHT=400");
 }
 
 function HabilitaCombo(){
	   var scroll_chks = document.getElementById('cajoncito1');
	   var scroll_chksz = document.getElementById('cajoncito2');
			with(document.forms[0]){
			   if (checked.checked) {
					scroll_chks.style.visibility = 'visible';
					scroll_chksz.style.visibility = 'visible';
			   }
			   else{			
				scroll_chks.style.visibility = 'hidden'; 
				scroll_chksz.style.visibility = 'hidden';
			   }
			 }
	}
 
function Cambiardocumento(valor){
		//alert("entre"+valor);
		var vvalor=valor;
		
		if (vvalor==2){
			var strURL = "MostrarFormularioCrearcionDocumentoInternoEspecialC.do";
			location=strURL;
			return;
		}
		if (vvalor==3){
			var strURL = "MostrarFormularioCrearcionDocumentoInternoEspecialT.do";
			location=strURL;
			return;
		}
			
}
 
function MensajeRegistro(){
		var strURL="ValidaPaginas.do?tipo=mensajerecepcion";
		window.open(strURL,"","HEIGHT=140,WIDTH=500,scrollbars=no");
}
 
 function mostrardiv() {
	 div = document.getElementById('flotante');
	 div.style.display ='';
}

function cerrar() {
	 div = document.getElementById('flotante');
	 div.style.display='none';
}
 
 function Regresar(){
				
	/*	alert(var_apellido_paterno);
		alert(var_apellido_materno);
		alert(var_nombres);
		
		habilito ventana modal
		pido observacion y motivo
		ingresan datos presionan boton entonces desactivo modal
		y sigo con registro automaticamente
		si cancelan desactivo modal*/
		
		var strURL = "MostrarFormularioCrearcionDocumentoInterno.do?tipo=firma";
		location=strURL;
	
		return;
	}
 
 
 function Regresarbandeja(){
		
		/*	alert(var_apellido_paterno);
			alert(var_apellido_materno);
			alert(var_nombres);
			*/
			var strURL = "MostrarFormularioCrearcionDocumentoInterno.do";
			location=strURL;
		
			return;
		}
	
 function Buscar(){
		
		/*	alert(var_apellido_paterno);
			alert(var_apellido_materno);
			alert(var_nombres);
			
			var var_apellido_materno = document.getElementById('apellido_materno').value;
			var var_apellido_paterno = document.getElementById('apellido_paterno').value;
			var var_nombres = document.getElementById('nombres').value;*/
			
			//var var_codigo_tipo_documento_interno = document.getElementById('codigo_tipo_documento_interno').value;
			//var var_codigo_oficina = document.getElementById('codigo_oficina').value;
			//var var_nombre_archivo = document.getElementById('referencia').value;
			//var var_asunto = document.getElementById('asunto').value;
			
			var var_busqueda = document.getElementById('codigo_documento_interno_busqueda').value;
			
			if (Validarcampovacio(var_busqueda) == false){              
		          alert("Ingresar Codigo de Documento");
		          document.forms[0].codigo_documento_interno_busqueda.focus();
		          return false;           
		    }
			
			
			var strURL = "MostrarFormularioCrearcionDocumentoInterno.do?";
			
			strURL += "tipo=busqueda&";
			//strURL += "tipo="+var_codigo_tipo_documento_interno+"&";
			//strURL += "cod_ofic="+var_codigo_oficina+"&";
			//strURL += "nomdoc="+var_nombre_archivo+"&";
			//strURL += "asnt="+var_asunto;
			strURL += "nrodoc="+var_busqueda;
			location=strURL;
			return;
		}

 }
 </script>
	
<title><bean:message key="sistema.documento.internos.titulo" /></title>
</head>
<body bgColor="#F0F5FB" onLoad="fn_onLoad();">
	<html:form   enctype="multipart/form-data"  action="CrearDocumentoInterno.do"  >   
	<c:choose>
	   <c:when test='${operacionpopup ==  "X"}'>
                <SCRIPT language="javascript">
				 MensajeRegistro();
				</SCRIPT>
       </c:when>
	</c:choose> 
	<table style="width: 100%;">
			 <tr>
				  <td height="50" align="left" valign="middle" style="width:100%">
				     <table width="707%" height="50" cellspacing="0" cellpadding="0"  align="left"  class="groupcell" style="width: 100%; ">
							<tr>
								<td bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></td>
							</tr>
					 </table>			  
				  </td>
			 </tr>
			
	</table>
	<div class="container">
	<div id="break" ></div>
	<div id="contactform" class="rounded">
	<div id="TDHeadCab">
	 
	 <c:choose>
				   		<c:when test='${es_admiitido==0}'>
				   		   	<span class="labelrounded">CREAR DOCUMENTO INTERNO </span>
				   		   	<div class="linklista">&nbsp;&nbsp;&nbsp;</div>
				  			<div class="linklista"><a  href="javascript:listadocumentos()">
				  			<img src="img/document_delete.png" alt="Anular Documentos" width="32" height="32" border="0" ></a>
				  			</div>
				  			
				   		</c:when>
				   		<c:otherwise>
				   		
				    			   <span class="labelrounded">BANDEJA DOCUMENTOS POR FIRMAR</span> 
				  			
				   		</c:otherwise>
	</c:choose>
	</div>
	<div id="TDHeadCabMensaje" >
        <html:messages  message="true" id="mensajes" header="messages.header" footer="messages.footer">
			<%= pageContext.getAttribute("mensajes") %>
		</html:messages>
          <!--<logic:present name="firmdocInterno" >
          <br>
           <bean:write name="firmdocInterno" property="nombre_arhivo"/>
           <a  href='VerArchivoPDFDocumentoInterno.do?filename=<bean:write name="firmdocInterno" property="nombre_arhivo"/>&codigo_oficina_pertenece=<bean:write name="firmdocInterno" property="codigo_oficina_pertenece"/>'><img src='img/pdf.gif' alt='ver documento PDF' width='24' height='24' border='0'> </a>
           <a  href='VerArchivoPDFDocumentoInterno.do?filename=<bean:write name="firmdocInterno" property="nombre_arhivo"/>&codigo_oficina_pertenece=<bean:write name="firmdocInterno" property="codigo_oficina_pertenece"/>'><img src='img/docadjuntos.gif' alt='adjuntar a registro' width='24' height='24' border='0'> </a>
           <a  href='VerArchivoPDFDocumentoInterno.do?filename=<bean:write name="firmdocInterno" property="nombre_arhivo"/>&codigo_oficina_pertenece=<bean:write name="firmdocInterno" property="codigo_oficina_pertenece"/>'><img src='img/nuevo.gif' alt='crear un registro' width='24' height='24' border='0'> </a>
          </logic:present>-->
          <logic:present name="docInterno" >
          <br>
           <bean:write name="docInterno" property="nombre_arhivo"/>
           <a  href='VerArchivoPDFDocumentoInterno.do?filename=<bean:write name="docInterno" property="nombre_arhivo"/>&codigo_oficina_pertenece=<bean:write name="docInterno" property="codigo_oficina_pertenece"/>'><img src='img/pdf.gif' alt='ver documento PDF' width='24' height='24' border='0'> </a>
          </logic:present>
     </div>
     <div id="TDHeadCabBusqueda" >
     <c:if test='${es_admiitido==1}'>
	 <div style="width: 25%; height: 25px; color: #336699; FONT-SIZE: 8pt; font-weight: bold;	margin-left: auto; margin-right: auto; margin-top: 16px;
		text-align: right; float:left;">
     <p>Búsqueda por : &nbsp;&nbsp;&nbsp;&nbsp;</p>
     </div>
     <div style="width:25%; margin-left: auto; margin-right: auto; margin-top: 15px; float:left;">
     	<html:text property="codigo_documento_interno_busqueda" styleId="codigo_documento_interno_busqueda" onkeypress="return validatecla('enteros',this)" style="width: 150px;" styleClass="caja"/>
      </div>
     <div style="width:50%; float:left;">
     	<input type="button" name="button" class="button" value="Buscar" onClick="Buscar()" alt="busqueda"  >
        <input type="button" name="button" class="button" value="Listar Todos" onClick="Regresar()" alt="Listar Todos"  >
     </div>	
	</c:if>
     </div>
     <div id="TDHeadCabRadios" >
    <a href="#" ><html:radio property="radiobuttons" value="1" ><bean:message key="sistema.documento.internos.normales" /></html:radio></a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:Cambiardocumento(2)" ><html:radio property="radiobuttons" value="2" onclick="Cambiardocumento(2)"><bean:message key="sistema.documento.internos.ccp" /></html:radio></a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:Cambiardocumento(3)" ><html:radio property="radiobuttons" value="3" onclick="Cambiardocumento(3)"><bean:message key="sistema.documento.internos.tdr" /></html:radio></a>
     </div>
     <div id="Lineaform" >
     </div>
	<table width="100%" cellspacing="0">
			 <tr>
				   <td  style="padding:0px; margin:0px;" align="left" valign="middle" >
				   		<table  width="100%" >
				   		<!-- cabecera -->
				   		<!-- AQUI ESTABA LOS MENSAJES -->
				   		<!-- insercion de busqueda -->
				   		<!-- insercion de radios -->
				   		<tr><td colspan="2">
				   			</td>
				   		</tr>
				   		<tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.registrar" />  
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<html:checkbox property="checked" styleId="checked" ></html:checkbox>
				    			

				  			</td>
				   		
				   		</tr>
				   		<tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.tipo.documento" />  
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<html:select property="codigo_tipo_documento_interno"  styleId="codigo_tipo_documento_interno" styleClass="caja" >			             
					             <html:option value="0" >Seleccionar</html:option>
									<html:options collection="tipoDocumentoInternosListTemp"  property="idBean"
 									labelProperty="nombreBean"  /> 
								</html:select> <span class="error" ><html:errors property="codigo_tipo_documento_interno"/></span>
				  			</td>
				   		</tr>

				   		<tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.area.administrativa.destino" />  
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<html:select property="codigo_oficina" styleId="codigo_oficina" styleClass="caja">			             
					             <html:option value="0" >Seleccionar</html:option>
									<html:options collection="areasAdministrativasList"  property="codigo_oficina"
 									labelProperty="descripcion_oficina"  /> 
								</html:select> 
								<span class="error"><br><html:errors property="codigo_oficina"/></span>
				  			</td>
				   		
				   		</tr>
				   		
				   		<tr>
				   		
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.abreviatura" />
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<html:text property="abreviatura_grado_profesion"  size="10" styleId="abreviatura_grado_profesion" onkeypress="return validatecla('texto',this)" />
				    			<span class="error"><br><html:errors property="abreviatura_grado_profesion"/></span>
				    			<!-- a href="#" onclick="toogle('block','modal','ventana')">Abrir Modal</a -->
				    			<div id="modal" style="display:none">
								<div id="ventana" class="contenedor" style="display:none">
									<h2>Titulo</h2> "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
									<a href="#close" title="Cerrar" onclick="toogle('none','modal','ventana')" >Close</a>
								</div>
								</div>
				  			</td>
				   		</tr>
				   		<tr>
				   		
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.destinatario" />
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<c:choose>
									<c:when test='${FFormDocumentoInterno.codigo_documento_interno eq null }'>
									  
										<html:select property="personas" styleId="personas" styleClass="caja"  >			             
							             <html:option value="0" >Seleccionar</html:option>									
										</html:select> 
									</c:when>				  
							 		<c:otherwise>
				          	             <html:select property="personas" styleId="personas" styleClass="caja"  >			             
								             <html:option value="0" >Seleccionar</html:option>	
								             <html:options collection="rs_destinatario"  property="codigo_personal"
	 										 labelProperty="nombre_personal"  /> 
										</html:select>   
			        		      </c:otherwise>

     						  </c:choose> 
				   
								<span class="error"><br><html:errors property="personas"/></span>
				  			</td>
				   		</tr>
				   		
				   		
				   		
				   		
				   		<tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.asunto" />
				  			</td>
				  			<td  align="left" valign="middle" >
				    			 <html:textarea property="asunto" cols="60" rows="2"  styleId="asunto" /> 
								 <span class="error"><br><html:errors property="asunto"/></span>
				  			</td>
				   		
				   		</tr>
				   		
				   		<tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.referencia" />
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<html:textarea property="referencia" cols="60" rows="2"  styleId="referencia" /> 
				  			</td>
				   		
				   		</tr>
				   		
				   		<tr>
				   			<td  align="right" valign="top" class="label" >
				    			<bean:message key="sistema.documento.internos.descripcion"  />
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<html:textarea property="descripcion" cols="60" rows="10"  styleId="descripcion" /> 								
								<span class="error"><br><html:errors property="descripcion"/></span>
				  			</td>
				   		
				   		</tr>
				   		
				   		<tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.autor" />
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<html:text property="firmado_por" size="50"  styleId="firmado_por" onkeypress="return validatecla('letras',this)"/>
								 <span class="error"><br><html:errors property="firmado_por"/></span>
				  			</td>
				   		
				   		</tr>
				   		
				   		<tr>
				   			<td  align="right" valign="middle" class="label" >
				    			<bean:message key="sistema.documento.internos.adjuntos"  />
				  			</td>
				  			<td  align="left" valign="middle" >
				    			<html:button  value="Agregar documento" style="width: 90;" property="" styleClass="boton" onclick="javascript:AgregarDocInternos();"/>
				    			<c:choose>
				  				<c:when test='${not empty rs_upload_doc_internos}'>
				    			<img src="img/docadjuntos.gif" alt="Documentos Adjuntos"></c:when>
			   	 				</c:choose>	
				  			</td>
				   			
				   		</tr>

				   		<tr>
				   			<td  align="right" valign="top" class="label" >
				    			<bean:message key="sistema.documento.internos.copias_destinatario" />
				  			</td>
				  			<td  align="left" valign="middle" >
						    			<div style="background: white;  width:500px; height:200px; overflow: scroll;" >	
										<logic:iterate id="oficinas_des" name="areasAdministrativasList" >
										   <html:multibox property="copia_oficinas_destino" >	
										    <bean:write name="oficinas_des" property="codigo_oficina"/> 
										   </html:multibox> 
										   <label class="caja7"><bean:write name="oficinas_des" property="descripcion_oficina"  /></label>
										   <br>
										</logic:iterate>
									</div>
								 </td>
				  		
				   		
				   		</tr>
				   		<c:choose>
				   		<c:when test='${es_admiitido==0}'>
				   		<tr>
				   			<td  align="right" valign="top" class="label" >
				    			<bean:message key="sistema.documento.internos.estado" />
				  			</td>
				  			<td  align="left" valign="middle" >
						    				<html:select property="codigo_estado_documento" styleId="codigo_estado_documento" styleClass="caja">			             
					             <html:option value="0" >Seleccionar</html:option>
									<html:options collection="estadoDocumentoInternoListSecretaria"  property="idBean"
 									labelProperty="nombreBean"  /> 
 									
 									
								</html:select> 
								<span class="error"><br><html:errors property="codigo_estado_documento"/></span>
								 </td>
				  		
				   		
				   		</tr>
				   		</c:when>
				   		<c:otherwise>
				   		<tr>
				   			<td  align="right" valign="top" class="label" >
				    			<bean:message key="sistema.documento.internos.estado" />
				  			</td>
				  			<td  align="left" valign="middle" >
						    				<html:select property="codigo_estado_documento" styleId="codigo_estado_documento" styleClass="caja">			             
					             <html:option value="0" >Seleccionar</html:option>
									<html:options collection="estadoDocumentoInternoList"  property="idBean"
 									labelProperty="nombreBean"  /> 
 									
 									
								</html:select> 
								<span class="error"><br><html:errors property="codigo_estado_documento"/></span>
								 </td>
				  		
				   		
				   		</tr>
				   		
				   		</c:otherwise>
				   		</c:choose>
				   		
				   		<tr>
							   			<td  align="right" valign="top" class="label" >
							    			<bean:message key="sistema.documento.internos.buscar.firma" />
							  			</td>
							  			<td  align="left" valign="middle" >
									    			 <html:file property="file" size="60" />
									    			 <span class="error"><br><html:errors property="file"/></span>
										 </td>
							   		
						</tr>

				   		<tr>
				   			<td  align="center" valign="top" class="label" colspan="2" >
				    				 <c:choose>
				
									 <c:when test='${FFormDocumentoInterno.codigo_documento_interno eq null }'>
									      <c:if test='${es_admiitido==0}'>
									     <html:button  value="GUARDAR" style="width: 90;" property="" styleClass="button" onclick="this.disabled=true; this.value='Enviando...'; this.form.submit()" />
					          			 <!--<html:submit value="GUARDAR"   style="width: 90;" styleClass="button" />--> 
					          			 <html:reset value="CANCELAR" style="width: 90;" styleClass="button" />
					          			 </c:if>
					         		 </c:when>
					         		 <c:when test='${verbotonescreacion == "1" && es_admiitido==0}'>
					         		 	<html:submit value="ACTUALIZAR"  style="width: 90;" styleClass="button" /> 
					                    <html:button  value="CANCELAR" style="width: 90;" property="" styleClass="button" onclick="javascript:Regresarbandeja();"/>
					         		 </c:when>				  
									 <c:otherwise>
					                     <html:submit value="ACTUALIZAR"  style="width: 90;" styleClass="button" /> 
					                     <html:button  value="CANCELAR" style="width: 90;" property="" styleClass="button" onclick="javascript:Regresar();" />
					        		 </c:otherwise>

     								 </c:choose>
				  			</td>

				   		</tr>

				   		</table>
				    			  
				  </td>

			 </tr>
			
	</table>
	</div>
	
	</div>
	<div id="break" ></div>
	<div class="containerlista">

	<table width="100%">
			 <tr>
				  <td  align="left" valign="middle" width="10%">
				    			  
				  </td>
				   <td  align="left" valign="middle" width="80%">
				   
				   		<table border="0" width="100%">
				   		
				   		 <tr>
				  <td align="center" colspan="2" height="50"  background="img/fondoplomo8.jpg" class="label"><bean:message key="sistema.documento.internos.creados" /></td>
				  
			 </tr>
			 <tr>
				  <td align="center" colspan="2">				  
				  <display:table name="sessionScope.listaDocumentosInternos" 
				  export="false" sort="list" id="b" 
				  pagesize="10"  class="simple" style="width: 100%" >
				  						
				  						<display:column title="Nro Documento" property="codigo_documento_interno" sort="true" align="center" />
											
				  						<display:column property="asunto" title="Asunto" sort="true" width="350"/>								
					  						
				  						<display:column  title="Oficina Destino" sort="true">
				  						
					  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
						  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
						  								<c:out value="${oficina.descripcion_oficina}"/>
						  							</c:if>
						  						</logic:iterate>
				  						</display:column>
				  						<display:column property="dirigido_a" title="Destinatario" sort="true"/>
				  						
				  						<display:column   title="Tipo de Documento" sort="true">
					  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
					  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						
				  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />
				  						

				  						<display:column   title="Estado" sort="true">
					  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
					  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  										  						
				  						<display:column  sort="true" title="Fecha de creacion" >
				  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
				  						</display:column>
				  						
				  						<display:column property="link"  title="Operaciones" width="100"/>
				  							
		
				  </display:table>
				 
				  </td>
				  
			 </tr>

				   		</table>
				    			  
				  </td>
				   <td  align="left" valign="middle" width="10%">
				    			  
				  </td>
			 </tr>
			
	</table>

	</div>
		<ajax:select baseUrl="${pageContext.request.contextPath}/obtenerLista.do" parameters="cambia=ofi&codigo_oficina={codigo_oficina}" source="codigo_oficina" target="personas"  />
		
	</html:form>

</body>
</html>