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
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Ventana de Mesa de Partes</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  
    <link rel="stylesheet" href="css/avatec.css" type="text/css" />
	<link rel="stylesheet" href="css/table.css" type="text/css" />
	<link rel="stylesheet" href="css/modelo.css" type="text/css" />

<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
</HEAD>

<BODY bgColor="white" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0">
<div id="container">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
	<jsp:include page="/pag_bandeja.jsp"/>
 <FORM id="form_datos" name="form_datos" method="post" action="">
<TABLE  cellSpacing="0" cellPadding="0" width="100%" border="0"  height="">
  <!--DWLayoutTable-->
    <TBODY>
    <!-- TR>
        <TD height="26" colspan="2" class="label" align="CENTER"  valign="middle"   background="img/fondoplomo8.jpg"></TD>
    </TR>
    <TR >
      <TD width="100%" height="100%"><jsp:include page="/pag_bandeja.jsp"/></TD>
      <TD width="4"></TD>
    </TR-->
    </TBODY>
</TABLE>
</FORM>
</div>
</BODY>
</HTML>
<% } %>