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

function seleccionar(codigo,nombre){
 
 
 parent.seleccionar2(codigo,nombre);
 //opener.document.getElementById('destinatario').value=codigo;
 //opener.document.getElementById('nombre_destinatario').value=nombre;
 
}
function buscar(){
var nombre = document.getElementById('nombre');
var codigo = document.getElementById('codigo');

var strURL = "VerFrameListas.do?tipo=dirigidos";
strURL += "&operacion="+ls_operacion+"&sle_texto="+ls_texto_jur.toUpperCase();
	 
document.getElementById("ifra_frame_listas").Document.location=strURL;

}
function cargar(){

var nombre = document.getElementById('nombre');
nombre.focus();
}
</script>
</HEAD>
<BODY bgColor="#F0F5FB" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" onLoad="javascript:cargar();">
<table height="44" border="0" width="450">
  <!--DWLayoutTable-->
  <tr>
    <td height="5" valign="top" class="groupcell2"><!--DWLayoutEmptyCell-->&nbsp;</td>
    <td height="5" valign="top" class="groupcell2">Codigo</td>
    <td height="5" valign="top" class="groupcell2">Nombre</td>
  </tr>
  <c:forEach items="${instituciones}" var="ins">
  <tr>
    <td width="440" height="15" valign="top"><label>
      <input type="submit" name="Submit" value="Seleccionar" onClick="javascript:seleccionar('<c:out value="${ins.codigo_persona}"/>','<c:out value="${ins.nombre_persona}"/>');">
    </label></td>
    <td width="440" valign="top"><c:out value="${ins.codigo_persona}"/></td>
    <td width="440" valign="top"><c:out value="${ins.nombre_persona}"/></td>
  </tr></c:forEach>
</table>

</BODY>
</HTML>
  <%}%>
</p>
