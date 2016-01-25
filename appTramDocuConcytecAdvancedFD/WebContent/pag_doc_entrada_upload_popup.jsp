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
<HEAD><TITLE>Registro de Documentos - Upload</TITLE>
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

function Aceptar(){
    
		var ls_operacion = "UA";
		var ls_cabecera_upload = document.getElementById("cabecera_upload").value;
		 // alert(ls_operacion);
		var nombre_archivo = document.getElementById("theFile").value;
		
		
		if (Validarcampovacio(document.form_datos.theFile.value) == false){              
			document.form_datos.theFile.focus();
			return false;           
	    }
		 		   
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
<INPUT  type="hidden" id="operacion" name="operacion" value="P">
<input type="hidden" name="cabecera_upload" id="cabecera_upload" value="<c:out value='${cabecera_upload}'/>" >
<input type="hidden" name="cabecera_secuencia" id="cabecera_secuencia" value="<c:out value='${cabecera_secuencia}'/>" >

  <TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="TextFieldOn24" >
    <TR> 
      <TD width="1249" height="100%" valign="top"  > <CENTER>
          <TABLE width="100%" cellpadding="0" cellspacing="0">
            <TR> 
              <TD width="978" height="49" valign="middle" bgcolor="#BAD0EB" class="titulolistado01" ><FONT size="2">&nbsp; 
                DOCUMENTOS ENTRADA - ADJUNTAR ARCHIVOS
                </FONT></TD>
            </TR>
          </TABLE>
          <TABLE width="100%" border="0"  >
            <!--DWLayoutTable-->
         
            <TR> 
              <TD width="972" height="51" valign="top"> <table width="100%" border="0" class="Celda02Dir1">
                <!--DWLayoutTable-->
               
                  
                  <tr> 
                    <TD  width="390" height="45" align="right" valign="middle" class="Columna">Archivo: 
                    &nbsp;&nbsp;&nbsp;</TD>
                    <TD colspan="2" valign="middle">     
                    <input size="50" type="file" name="theFile" id="theFile" class="caja"></TD>
                    <TD colspan="2" valign="top"></TD>
                  </tr>
				  <tr> 
                    <TD  height="30" colspan="5" align="left" valign="middle">&nbsp;&nbsp;  
					<c:choose>
								        <c:when test='${operacion ==  "N"}'>
					                    <input  type="button" class="boton2"  name="button" value="Aceptar"  onClick="javascript:Aceptar()">
						  </c:when>
								        <c:when test='${operacion ==  "M"}'>	
										 <input  type="button" class="boton2"  name="button" value="Aceptar"  onClick="javascript:Modificar()">
					    </c:when>
					  </c:choose>					  					</TD>
                  </tr>
				  <tr>
				    <td height="3"></td>
				    <td width="194"></td>
				    <td width="301"></td>
				    <td width="37"></td>
				    <td></td>
			      </tr>
                </table></TD>
            </TR>
           
			<TR> 
              <TD height="36" valign="top"> <table width="100%" border="0"  class="asdf">
                
                  <tr> 
                    <TD  width="100%" height="50"  valign="top"> 
                    <c:choose>
								        <c:when test='${operacion ==  "M"}'>
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
					 
    <tr > 
      <td width="60" height="22" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">C&oacute;digo      </td>
      <td width="619" align="center" valign="middle" class="Columna10"  background="img/fondoplomo8.gif">Nombre del Archivo      </td>
	  <td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Adjuntos</td>
    
    <td width="68" valign="top" background="img/fondoplomo8.gif"><!--DWLayoutEmptyCell-->&nbsp;</td>
    </tr>
    <c:choose>
     <c:when test='${not empty rs_upload_doc}'>
      <c:forEach items='${rs_upload_doc}' var='pr'> 
   
      <tr><td height="18" align="center" valign="middle"  class="Columna"> <font color="#000000"> 
        <c:out value='${pr.contador}'/> 
        </font>  </td>
        <td align="left" valign="middle" class="Columna">&nbsp;&nbsp;&nbsp; 
        <c:out value='${pr.nombre_archivo}'/> </td>
  
		   <td align="center" valign="middle" class="Columna">   <c:choose>
		     <c:when test='${pr.nombre_archivo ==  ""}'>
		       ------		  </c:when>
		     <c:otherwise>
  <a target="_blank" href="ListaDocumentos.do?tipo=uploaddoc&nombre_archivo=<c:out value='${pr.nombre_archivo}'/>">Ver Archivo</a>	</c:otherwise>
		            </c:choose>	  </td>
    <td align="center" valign="middle"><A id="A1" target="_parent" href="MantDocumento.do?operacion=EU&id_det_upload=<c:out value='${pr.id_det_upload}'/>&codigo_documento=<c:out value='${cabecera_upload}'/>&nombre_archivo=<c:out value='${pr.nombre_archivo}'/>">
        <font color="#0000FF"><img src="img/trash.png" border="0" height="24" alt="Eliminar Linea."></font> </A></td>
      </tr>
    </c:forEach>
     </c:when> 
    </c:choose> 
    <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
  </table>
					</c:when>
					 <c:when test='${operacion ==  "N"}'>
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
					</c:choose>
					</TD>
                  </tr>
              </table></TD>
            </TR>
            <TR>
              <TD height="30" align="center" valign="bottom" ><input  type="button" class="boton2"  name="button" value="Cerrar"  onClick="javascript:cerrar('<c:out value='${contador}'/>');"></TD>
            </TR>
          </TABLE>
        </CENTER></TD>
    </TR>
  </TABLE>
</FORM>
</BODY></HTML>

<% } %>