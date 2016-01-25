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
  
    <TITLE>Busqueda de instituciones</TITLE>
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
function seleccionar2(codigo,nombre){

opener.document.getElementById('destinatario').value=codigo;
opener.document.getElementById('nombre_destinatario').value=nombre;
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
<table height="466" border="0" width="500">
  <!--DWLayoutTable-->
  <tr>
    <td height="10" colspan="2" class="label1">Busqueda de Instituciones </td>
  </tr>
  <tr>
    <td height="5" colspan="2" bgcolor="BAD0EB"></td>
  </tr>
  
  <tr>
    <td width="120" height="10">Codigo:</td>
    <td width="320"><label>
      <input type="text" name="codigo" id="codigo" onKeyPress="javascript:buscar();">
    </label></td>
  </tr>
  <tr>
    <td height="10">Descripci&oacute;n:</td>
    <td height="10">      <input type="text" name="nombre" id="nombre" onKeyPress="javascript:buscar();">
      <label></label></td>
  </tr>


<tr>
    <td height="5" colspan="2" bgcolor="BAD0EB"></td>
  </tr>
  <tr>
    <td height="205" colspan="2" valign="top"><iframe id="ifra_frame_listas"  name="ifra_frame_listas" align="middle"  frameborder="0" width="100%"   scrolling="auto" height="100%"  marginheight="30%"  ></iframe></td>
  </tr>
  <tr>
    <td height="10" colspan="2" valign="top" align="center"><input type="button" name="Submit2" value="Cerrar" onClick="window.close();"></td>
  </tr>
</table>

</BODY>
</HTML>
  <%}%>
</p>
