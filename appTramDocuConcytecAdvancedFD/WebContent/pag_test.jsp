<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String codDoc=(String)request.getAttribute("coDoc");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="Javascript" type="text/javascript">
var windowOpener;

function setOpener(){
	//setea la ventana padre
	windowOpener = window.opener;
}

	function validacionCert(){
	var coDoc="<%=codDoc%>";
	var mnsje = 0
	windowOpener.MostrarMensaje(mnsje,coDoc);
	window.close();
	}
	
	
</script>
<title>PuentedePrueba</title>
</head>
<body onload="setOpener();">
	<a href="javascript:validacionCert()">Click</a>
</body>
</html>