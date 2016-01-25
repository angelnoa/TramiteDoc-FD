<% 
//Verifica que exista una sesion
String nombreusuario = String.valueOf(session.getAttribute("nombreusuario"));
String flag = String.valueOf(session.getAttribute("ls_flag"));

if (nombreusuario==null || nombreusuario.equals("null")|| !flag.equals("A") ){
%> 
<SCRIPT language="Javascript">
parent.document.location ="inicio.jsp";
</SCRIPT>
<% 
}else {
%>
<HTML>
<HEAD>
<link href="css/main.css" rel="stylesheet" type="text/css">
<LINK href="css/topmenustyles.css" type="text/css" rel="stylesheet">
<LINK href="css/sidebarstyles.css" type="text/css" rel="stylesheet">
<LINK href="css/workspacestyles.css" type="text/css" rel="stylesheet">
<LINK href="css/footerstyles.css" type="text/css" rel="stylesheet">
<LINK href="css/workspacestyles.css" type="text/css" rel="stylesheet">
<LINK href="css/stylo.css" type="text/css" rel="stylesheet">
<LINK href="css/estilo1.css" type="text/css" rel="stylesheet">
</HEAD>
<FRAMESET rows="70,*,34" cols="*" frameborder="no" border="0" framespacing="1" >
  <FRAME src="pag_menu.jsp" name="topFrame" scrolling="NO" noresize >
  <FRAMESET rows="*" cols="230,*" framespacing="0" frameborder="NO" border="0">
    <FRAME src="pag_opciones.jsp" name="leftFrame" scrolling="NO"  class="body_azul">
    <FRAME src="pag_bandeja.jsp" name="mainFrame" scrolling="AUTO" class="body_azul">
  </FRAMESET>
  <FRAME src="pag_footer.jsp" name="topFrame" scrolling="NO" noresize >
</FRAMESET>
<NOFRAMES>
<BODY class="body_azul" >
</BODY>
</NOFRAMES>
</HTML>
<% 
}
%>