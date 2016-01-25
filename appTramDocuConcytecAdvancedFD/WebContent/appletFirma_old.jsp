<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ include file="../taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
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

<head>
<script language="Javascript" type="text/javascript">

	function validacionCert(msg){
		alert("Holaaaa: " + msg);
			
		//return false;
	}
</script>
</head>
<body class="claro">
		<script type="text/javascript">
		</script>
	<applet alt=""		
			code="applet.sign.SoftNetSignApplet.class"
			archive="firmasDigitales/SignNetSolutions.jar,firmasDigitales/SignNetLibrary.jar,firmasDigitales/bcmail-jdk16-146.jar, 
			firmasDigitales/bcprov-jdk16-146.jar, firmasDigitales/MITyCLibAPI-1.0.4.jar, firmasDigitales/MITyCLibCert-1.0.4.jar, 
			firmasDigitales/commons-logging-1.0.4.jar, firmasDigitales/plugin.jar, firmasDigitales/bctsp-jdk16-146.jar, 
			firmasDigitales/ridl-3.2.1.jar, firmasDigitales/unoil-3.2.1.jar, firmasDigitales/log4j-1.2.14.jar"			
			codebase="./"
			width="700"
			height="400">
		
		<!-- PARAMETROS -->
		
		<!-- documento pdf sin firmar -->
		<param name="validarTSL" id="validarTSL"  value="0"> <!-- Valida=1, NoValida=0 -->
		<param name="urlTSL" id="urlTSL"  value="https://iofe.indecopi.gob.pe/TSL/tsl-pe.xml">
		<param name="field" id="field"  value="FIRMA">
		<param name="rutasTSA" id="rutasTSA"  value="http://tsa.swisssign.net/,">
		<!-- Parametro de configuracion de la firma 
			 * si = Superior Izquierda
			 * sm = Superior Medio
			 * sd = Superior Derecha
			 * mi = Medio Izquierda
			 * mm = Medio Medio
			 * md = Medio Derecha
			 * ii = Inferior Izquierda
			 * im =Inferio Medio
			 * id = Inferior Derecha
			 * co= Coordenadas
		-->
		
		<param name="posFirma"  name="posFirma" value="sd">
		<param name="rutaOrigenPdf"  name="rutaOrigenPDF" value="<%=server%>"/>
		<param name="nombreArchivos"  name="nombreArchivos" value="<%=nombreArchivo%>"/>
		<param name="rutaDestino"  name="rutaDestino" value="<%=rutaDestino%>"/>
		<param name="algoritmoHash" id="algoritmoHash" value="SHA1"> <!-- Algorito: SHA1,MD5-->
		<param name="altoRubrica" id="altoRubrica" value="80">
		<param name="anchoRubrica" id="anchoRubrica" value="120">
		<param name="coordenadas" id="coordenadas" value="10,10,10,10">
		<param name="firmarTodo" id="firmarTodo" value="0">
		<param name="rutaOrigenImagen" id = "rutaOrigenImagen" value="<%=server%>"/>
		<param name="nomImagen" id = "nomImagen" value="<%=nombreImagen%>"/>
		<param name="rutaOrigenExtras" value="<%=rutaOrigenExtras%>"/>
		<param name="urlService" value="http://localhost:8099/aplicativo/ServletUpload">  
		<param name="checkProxy" value="1"/>
		<param name="port" value="3128"/>
		<param name="host" value="192.168.5.14"/>
		<param name="urlService"  value="<%=urlService%>"/>
		
	</applet>
</body>
</html>
