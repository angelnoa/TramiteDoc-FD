<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String  server =(String)request.getAttribute("rutaServidor");
	System.out.print(server);
	String nombreArchivo=(String)request.getAttribute("nombreArchivo"); 
	System.out.print(nombreArchivo);
	String nombreImagen=(String)request.getAttribute("nombreImagen"); 
	System.out.print(nombreImagen);
	String rutaDestino="C:\\documents\\documentosxFirmar";
	String rutaOrigenExtras=request.getScheme().toLowerCase()+ "://" + request.getServerName()+":"+ request.getServerPort()+request.getContextPath()+"/firmasDigitales/";
	String urlService=request.getScheme().toLowerCase()+ "://" + request.getServerName()+":"+ request.getServerPort()+request.getContextPath()+"/SubirPDFAServidor.do";
	
%>

<script language="Javascript" type="text/javascript">

	function validacionCert(msg){
		alert("Holaaaa: " + msg);
			
		//return false;
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:plugin code="applet.sign.SoftNetSignApplet" 
			archive="SignNetSolutions.jar, SignNetLibrary.jar,bcmail-jdk16-146.jar, bcprov-jdk16-146.jar, MITyCLibAPI-1.0.4.jar, MITyCLibCert-1.0.4.jar, commons-logging-1.0.4.jar, plugin.jar, bctsp-jdk16-146.jar, ridl-3.2.1.jar, unoil-3.2.1.jar, log4j-1.2.14.jar"
	codebase="./" type="applet" height="400" width="700">
	<jsp:params>
		<jsp:param name="validarTSL" value="0" />
		<jsp:param name="urlTSL" value="https://iofe.indecopi.gob.pe/TSL/tsl-pe.xml" />
		<jsp:param name="field" value="FIRMA" />
		<jsp:param name="rutasTSA" value="http://tsa.swisssign.net/" />
		<jsp:param name="posFirma" value="sd" />
		<jsp:param name="rutaOrigenPdf" value="<%=server%>" />
		<jsp:param name="nombreArchivos" value="<%=nombreArchivo%>" />
		<jsp:param name="rutaDestino" value="<%=rutaDestino%>" />
		<jsp:param name="algoritmoHash" value="SHA1" />
		<jsp:param name="altoRubrica" value="80" />
		<jsp:param name="anchoRubrica" value="120" />
		<jsp:param name="coordenadas" value="10,10,10,10" />
		<jsp:param name="firmarTodo" value="0" />
		<jsp:param name="rutaOrigenImagen" value="<%=server%>" />
		<jsp:param name="nomImagen" value="<%=nombreImagen%>" />
		<jsp:param name="rutaOrigenExtras" value="<%=rutaOrigenExtras%>" />
		<jsp:param name="checkProxy" value="1" />
		<jsp:param name="port" value="3128" />
		<jsp:param name="host" value="192.168.5.14" />
		<jsp:param name="urlService" value="<%=urlService%>" />
	</jsp:params>
</jsp:plugin>



</body>
</html>