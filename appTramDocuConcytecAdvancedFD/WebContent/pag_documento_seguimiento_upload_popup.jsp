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
<HEAD><TITLE>Seguimiento - Upload</TITLE>
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
function cerrar(){
     
        window.close();
}

function Aceptar(){
    
		  var ls_operacion = "N";
		 // alert(ls_operacion);
		 var ls_codigo_documento = document.getElementById("codigo_documento").value;
		 var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
		 var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;
		   
	document.form_datos.action='MantSeguimiento.do'+'?operacion='+ls_operacion+'&tipo=uploadficheros'+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&secuencia_movimiento='+ls_secuencia_movimiento;
	document.form_datos.submit();	
			//window.close();
}
function Modificar(){
    
		  var ls_operacion = "M";
		 /*alert("La operacion es");
		 alert(ls_operacion);*/
		
	document.form_datos.action='MantUpload.do'+'?operacion='+ls_operacion+'&tipo=P';
	document.form_datos.submit();	
	
}
</SCRIPT>
<BODY class="def"  leftMargin="0"  topMargin="0" rightMargin="0" bottomMargin="0" marginwidth="0" marginheight="0">

<form id="form_datos" name="form_datos" ENCTYPE="multipart/form-data" METHOD="POST" ACTION="">
<INPUT  type="hidden" id="operacion" name="operacion" value="P">
<input type="hidden" name="codigo_documento" id="codigo_documento" value="<c:out value='${codigo_documento}'/>" >
<input type="hidden" name="codigo_expediente" id="codigo_expediente" value="<c:out value='${codigo_expediente}'/>" >
<input type="hidden" name="secuencia_movimiento" id="secuencia_movimiento" value="<c:out value='${secuencia_movimiento}'/>" >
  <TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="TextFieldOn24" >
    <TR> 
      <TD width="1249" height="100%" valign="top"  > <CENTER>
          <TABLE width="100%" cellpadding="0" cellspacing="0">
            <TR> 
              <TD width="978" height="49" valign="middle" bgcolor="#BAD0EB" class="titulolistado01" ><FONT size="2">&nbsp; 
                SEGUIMIENTO DOCUMENTOS - ARCHIVOS ADJUNTOS
                </FONT></TD>
            </TR>
          </TABLE>
          <TABLE width="100%" border="0"  >
            <!--DWLayoutTable-->
         
            <TR> 
              <TD width="972" height="20" valign="top"> <table width="100%" border="0" class="Celda02Dir1">
                <!--DWLayoutTable-->
               
                  
				  
                </table></TD>
            </TR>
           
			<TR> 
              <TD height="36" valign="top"> <table width="100%" border="0"  class="asdf">
                
                  <tr> 
                    <TD  width="100%" height="50"  valign="top"> 
                    
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
					  <!--DWLayoutTable-->
    <tr > 
      <td width="60" height="22" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">C&oacute;digo      </td>
      <td width="619" align="center" valign="middle" class="Columna10"  background="img/fondoplomo8.gif">Nombre del Archivo      </td>
	  <td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Adjuntos</td>
    	<td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Oficina</td>
    
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
  
      </tr>
    </c:forEach>
     </c:when> 
    </c:choose> 
    <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
  </table>
					</TD>
                  </tr>
              </table></TD>
            </TR>
            <TR>
              <TD height="30" align="center" valign="bottom" ><input  type="button" class="boton2"  name="button" value="Cerrar"  onClick="javascript:cerrar();"></TD>
            </TR>
          </TABLE>
        </CENTER></TD>
    </TR>
  </TABLE>
</FORM>
</BODY></HTML>
<% } %>