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
<HEAD><TITLE>Proyecto Investigaci&oacute;n - Upload</TITLE>
<meta http-equiv="Expires" content="0">
<meta http-equiv="Last-Modified" content="0">
<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
<meta http-equiv="Pragma" content="no-cache">
<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		<LINK href="css/estilo1.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/fstooltips.css">
</HEAD>
<script type="text/javascript" src="<c:url value="js/prototype-1.4.0.js"/>"></script>
<script type="text/javascript" src="<c:url value="js/scriptaculous.js"/>"></script>
<script type="text/javascript" src="<c:url value="js/overlibmws.js"/>"></script>
<script type="text/javascript" src="<c:url value="js/ajaxtags.js"/>"></script>
<SCRIPT LANGUAGE=javascript src="js/funciones.js"></SCRIPT>
<SCRIPT language="javascript">

function eliminar_archivo(id){
if(confirm("Desea Eliminar este archivo?")){
location = "Busquedas.do?tipo=eli_archivo&id="+id;
}

} 

function cerrar(codigo){
	//alert("codigo: " +codigo);
    window.close();
	window.opener.MostrarAdjunto(codigo);
		
}
function cerrarint(){
	//alert("codigo: " +codigo);
   // window.close();
	//window.opener.MostrarAdjunto(codigo);
	window.close();
}

function Aceptar(){
    
		  var ls_operacion = "UA";
		  var ls_cabecera_upload = document.getElementById("cabecera_upload").value;
		 // alert(ls_operacion);
		 var nombre_archivo = document.getElementById("theFile").value;
		 
		 
		   
	document.form_datos.action='MantDocumento.do'+'?operacion='+ls_operacion+'&cabecera_upload='+ls_cabecera_upload;
	document.form_datos.submit();	
	
	
	//alert('El archivo '+ nombre_archivo+' que ha ingresado presenta caracteres no validos');
	
			//window.close();
}
function Modificar(){
    
		  var ls_operacion = "AMU";
		  var ls_cabecera_upload = document.getElementById("cabecera_upload").value;
		  var ls_cabecera_secuencia = document.getElementById("cabecera_secuencia").value;
		 /*alert("La operacion es");
		 alert(ls_operacion);*/
		
	document.form_datos.action='MantDocumento.do'+'?operacion='+ls_operacion+'&cabecera_upload='+ls_cabecera_upload+'&cabecera_secuencia='+ls_cabecera_secuencia;
	document.form_datos.submit();	
	
}
</SCRIPT>
<BODY class="def"  leftMargin="0"  topMargin="0" rightMargin="0" bottomMargin="0" marginwidth="0" marginheight="0">

<form id="form_datos" name="form_datos" ENCTYPE="multipart/form-data" METHOD="POST" ACTION="">

  <TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="TextFieldOn24" >
    <TR> 
      <TD width="1249" height="100%" valign="top"  > <CENTER>
          <TABLE width="100%" cellpadding="0" cellspacing="0">
            <TR> 
              <TD width="978" height="49" valign="middle" bgcolor="#BAD0EB" class="titulolistado01" ><FONT size="2">&nbsp; 
                DOCUMENTOS INTERNOS - ADJUNTAR ARCHIVOS
                </FONT></TD>
            </TR>
          </TABLE>
          <TABLE width="100%" border="0"  >
            <!--DWLayoutTable-->
                    
			<TR> 
              <TD height="36" valign="top"> <table width="100%" border="0"  class="asdf">
                
                  <tr> 
                    <TD  width="100%" height="50"  valign="top"> 
                    <c:choose>
								        <c:when test='${operacioninterno ==  "M"}'>
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
					 
    <tr > 
      <td width="60" height="22" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">C&oacute;digo      </td>
      <td width="619" align="center" valign="middle" class="Columna10"  background="img/fondoplomo8.gif">Nombre del Archivoo      </td>
	  <td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Adjuntos</td>
    
    <td width="68" valign="top" background="img/fondoplomo8.gif"><!--DWLayoutEmptyCell-->&nbsp;</td>
    </tr>
    
    <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
  </table>
					</c:when>
					 <c:when test='${operacioninterno ==  "N"}'>
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
					 
    <tr > 
      <td width="60" height="22" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">Nro      </td>
      <td width="619" align="center" valign="middle" class="Columna10"  background="img/fondoplomo8.gif">Nombre del Archivo      </td>
	  <td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Adjuntos</td>
    
    <td width="68" valign="top" background="img/fondoplomo8.gif"><!--DWLayoutEmptyCell-->&nbsp;</td>
    </tr>
    	
      <c:forEach items='${FFormMantDocumento.archivos}' var='ar' varStatus="c"> 
   
      <tr><td height="18" align="center" valign="middle"  class="Columna"><c:out value="${c.count}"></c:out></td>
      <td align="left" valign="middle" class="Columna"><c:out value="${ar.nombre_archivo}"></c:out></td>
  
		   <td align="center" valign="middle" class="Columna"><a href="Busquedas.do?tipo=ver_archivo&id=<c:out value='${c.count-1}'/>">Visualizar</a></td>
    <td align="center" valign="middle"><a href="javascript:eliminar_archivo(<c:out value='${c.count-1}'/>)"><img src="img/trash.png" border="0" height="24" alt="Eliminar Linea."></a></td>
      </tr>
    </c:forEach>
     
    <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
  </table>
					</c:when>
					<c:when test='${operacioninterno == "O"}'>
					<!-- NUEVA VISTA MIXTA-->
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
					 
    <tr> 
      <td width="60" height="22" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">Nro      </td>
      <td width="619" align="center" valign="middle" class="Columna10"  background="img/fondoplomo8.gif">Nombre del Archivo      </td>
	  <td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Adjuntosz</td>
    
    <td width="68" valign="top" background="img/fondoplomo8.gif"><!--DWLayoutEmptyCell-->&nbsp;</td>
    </tr>
    <c:choose>
     <c:when test='${not empty rs_upload_doc_internos}'>
      <c:forEach items='${rs_upload_doc_internos}' var='pr' varStatus="c"> 
   
      <tr><td height="18" align="center" valign="middle"  class="Columna"><font color="#FF0000"><c:out value="${c.count}"></c:out></font></td>
        <td align="left" valign="middle" class="Columna"><font color="#FF0000"><c:out value='${pr.nombre_archivo}'/></font></td>
  
		   <td align="center" valign="middle" class="Columna">   
  	<a  target="_blank" href="VerArchivoPDFDocumentoInterno.do?filename=<c:out value='${pr.nombre_archivo}'/>&codigo_oficina_pertenece=<c:out value='${codigo_de_oficina_pertenece}'/>">Visualizar</a>
		             </td>
    <td align="center" valign="middle"><A id="A1" target="_parent" href="MantDocumento.do?operacion=EDIF&nombre=<c:out value='${pr.nombre_archivo}'/>&cod_doc_interno=<c:out value='${pr.tipo_archivo}'/>">
        <font color="#0000FF"><img src="img/trash.png" border="0" height="24" alt="Eliminar Linea."></font> </A></td>
      </tr>
    </c:forEach>
     </c:when> 
    </c:choose> 
      <c:forEach items='${FFormMantDocumento.archivos}' var='ar' varStatus="c"> 
   
      <tr><td height="18" align="center" valign="middle"  class="Columna"><c:out value="${c.count+cantidad_lista}"></c:out></td>
      <td align="left" valign="middle" class="Columna"><c:out value="${ar.nombre_archivo}"></c:out></td>
  
		   <td align="center" valign="middle" class="Columna"><a href="Busquedas.do?tipo=ver_archivo&id=<c:out value='${c.count-1}'/>">Visualizar</a></td>
    <td align="center" valign="middle"><a href="javascript:eliminar_archivo(<c:out value='${c.count-1}'/>)"><img src="img/trash.png" border="0" height="24" alt="Eliminar Linea."></a></td>
      </tr>
    </c:forEach>
     
    <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
  </table>
				
					</c:when>
					
					</c:choose>
					</TD>
                  </tr>
              </table></TD>
            </TR>
            <c:choose>
            <c:when test='${operacioninterno == "O"}'>
            <TR>
              <TD height="30" align="center" valign="bottom" ><input  type="button" class="boton2"  name="button" value="Cerrar"  onClick="javascript:cerrarint();"></TD>
            </TR>
            </c:when>
            <c:otherwise>
            <TR>
              <TD height="30" align="center" valign="bottom" ><input  type="button" class="boton2"  name="button" value="Cerrar"  onClick="javascript:cerrarint();"></TD>
            </TR>
            </c:otherwise>
            </c:choose>
          </TABLE>
        </CENTER></TD>
    </TR>
  </TABLE>
  <c:choose>
  <c:when test='${operacioninterno == "O"}'>
  <table width="100%" cellspacing="0" cellpadding="0"  align="left">
	
				<tr>
				
					<td>
						<table width="100%" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td align="center" valign="middle" background="img/fondoplomo8.jpg" class="label" height="25">
									Lista de documentos	Internos</td>
						  </tr>
							<tr>
				  <td align="center" colspan="2">				  
				  <display:table name="sessionScope.listaDocumentosInternosProyectos" export="false" sort="list" id="b" pagesize="10"  class="simple" style="width: 100%">
				  						
				  						<display:column title="Nro Documento" property="codigo_documento_interno" sort="true" />
											
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
				  						
				  						<display:column property="link"  title="Operaciones" width="80"/>
				  							
				  						
				  						
				  </display:table>
				 
				  </td>
				  
			 </tr>
						</table>
					</td>
				</tr>
		  </table>
		  </c:when>
		  </c:choose>
</FORM>
</BODY></HTML>

<% } %>