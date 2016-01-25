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
  
    <TITLE>Documentos pendientes por Cerrar</TITLE>
  	    <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
        <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">

<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<script language="JavaScript" src="js/avatec.js"></script>
<SCRIPT LANGUAGE=javascript src="js/prototype-1.4.0.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/scriptaculous.js"></SCRIPT>

<SCRIPT LANGUAGE=javascript src="js/ajaxtags.js"></SCRIPT>
<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>

<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>

<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
<script>

function enviar(f,cual){


todos = new Array();
 for (var i = 0, total = f[cual].length; i < total; i++)
 {  
     if (f[cual][i].checked) 
         todos[todos.length] = f[cual][i].value;
 }  
 var text = todos.join(".");
 
//opener.recepcionChecks(text);
//opener.document.getElementById('nombre_destinatario').value=nombre;
 f.submit();
 window.close();
}
function buscar(){
var nombre = document.getElementById('nombre');
var codigo = document.getElementById('codigo');

var strURL = "Busquedas.do?tipo=instituciones";
strURL += "&codigo="+codigo.value+"&nombre="+nombre.value;
	 
document.getElementById("ifra_frame_listas").Document.location=strURL;

}
function cargar(){

var nombre = document.getElementById('nombre');
nombre.focus();
}
</script>
</HEAD>
<BODY bgColor="#F0F5FB" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" onLoad="javascript:cargar();">
<form action="Busquedas.do">
<table height="466" border="0" width="500">
  <!--DWLayoutTable-->
  <tr>
    <td height="10" colspan="2" class="label1">Documentos Pendientes Por Cerrar </td>
  </tr>
  <tr>
    <td height="5" colspan="2" bgcolor="BAD0EB"></td>
  </tr>
  
  
 


<tr>
    <td height="5" colspan="2" bgcolor="BAD0EB"></td>
  </tr>
  <tr>
    <td height="205" colspan="2" valign="top"><div id="scroll_pendientes">
      <table height="44" border="0" width="420">
        <!--DWLayoutTable-->
        <tr>
          <td valign="top" class="groupcell2"><input type="hidden" name="tipo" value="seleccionar" /></td>
          <td height="5" valign="top" class="groupcell2">Nro Registro </td>
          <td valign="top" class="groupcell2">Nro Documento </td>
          <td height="5" valign="top" class="groupcell2">Asunto</td>
          <td valign="top" class="groupcell2">Area</td>
          <td valign="top" class="groupcell2">Remitente</td>
        </tr>
        <c:forEach items="${FFormMantPrecerrar.paquete}" var="doc">
          <tr>
            <td width="20" height="15" valign="top"><label>
            <c:choose >
            <c:when test="${doc.seleccionado}">
            <input type="checkbox" name="documento" checked="checked" value='<c:out value="${doc.codigo_documento}"/>-<c:out value="${doc.secuencia_movimiento}"/>'>
            </c:when>
            <c:otherwise>
            <input type="checkbox" name="documento" value='<c:out value="${doc.codigo_documento}"/>-<c:out value="${doc.secuencia_movimiento}"/>'>
            </c:otherwise>
            </c:choose>
              
            </label></td>
            <td width="50" valign="top"><c:out value="${doc.codigo_documento}"/></td>
            <td width="100" valign="top"><c:out value="${doc.numero_documento}"/></td>
            <td width="100" valign="top"><c:out value="${doc.asunto_documento}"/></td>
            <td width="100" valign="top"><c:out value="${doc.oficina}"/></td>
            <td width="100" valign="top"><c:out value="${doc.remitente}"/></td>
          </tr>
        </c:forEach>
      </table>
    </div></td>
  </tr>
  <tr>
    <td height="10" colspan="2" valign="top" align="center"><input type="button" value="Enviar" onClick="javascript:enviar(this.form,'documento');"><input type="button" name="Submit2" value="Cerrar" onClick="window.close();"></td>
  </tr>
</table>
</form>
</BODY>
</HTML>
  <%}%>

