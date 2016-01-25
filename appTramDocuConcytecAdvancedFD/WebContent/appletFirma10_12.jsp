<%@page import="org.apache.poi.hssf.record.formula.functions.Value"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String server =(String)request.getAttribute("rutaServidor");
    String origen =(String)request.getAttribute("rutaOrigen");
	String destino =(String)request.getAttribute("rutaDestino");
	String imagen =(String)request.getAttribute("rutaImagen");
	System.out.print("Ruta de Servidor: " + server);
	String nombreArchivo=(String)request.getAttribute("nombreArchivo"); 
	System.out.print("Nombre de Archivo: "+ nombreArchivo);
	String nombreImagen=(String)request.getAttribute("nombreImagen"); 
	System.out.print("Nombre de la Imagen: " + nombreImagen);
	String codDoc=(String)request.getAttribute("coDoc");
	System.out.print("Codigo de Documento: " +codDoc);
	String rutaDestino="C:\\documents\\documentosxFirmar";
	String rutaOrigenExtras=request.getScheme().toLowerCase()+ "://" + request.getServerName()+":"+ request.getServerPort()+request.getContextPath()+"/firmasDigitales/";
	String urlService=request.getScheme().toLowerCase()+ "://" + request.getServerName()+":"+ request.getServerPort()+request.getContextPath()+"/ServletUpload";
	
	String oficina_firmante_ls = (String) request.getAttribute("oficina_firmante_ls");
	String usarToken_ls = (request.getAttribute("usarToken_ls")).toString();
	String aliasCertificadoToken_ls = (String)request.getAttribute("aliasCertificadoToken_ls");
	String pfx_ls = (String)request.getAttribute("pfx_ls");
	String cargo_firmante_ls = (String) request.getAttribute("cargo_firmante_ls");
	String razon_ls = "Firma de documento";
	String nombreImagenVisto_ls = (String) request.getAttribute("nombreImagenVisto");
%>

<script language="Javascript" type="text/javascript">
	//mantiene una referencia a la ventana padre
	var windowOpener;
	
	function setOpener(){
		//setea la ventana padre
		windowOpener = window.opener;
	}

	function validacionCert(msg){
		var coDoc="<%=codDoc%>";
		windowOpener.MostrarMensaje(msg, coDoc);
		window.close();
	}
	function detectExtension() {
		
		setTimeout(foo, 3000);
	
	}
		 
		function foo(){
		       var exten = "https://chrome.google.com/webstore/detail/signnet-tab/ehabollbfgjdlnganbhlbbojkcdpenbg";
		       var testUrl = "chrome-extension://ehabollbfgjdlnganbhlbbojkcdpenbg/redir.htm";        
		       try {
		             if (typeof (chrome) !== 'undefined') {
		                 $.ajax({
		                     url: testUrl,
		                     timeout: 100,
		                     type: 'HEAD',
		                     error: function(){
		                          alert("Extension no encontrada");
		                          window.open(exten);
		                     }
		                 });
		             }
		       } catch (err) {}
		}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="setOpener();detectExtension();">

<applet code="com.pe.softnet.signnet.applet.view.SignApplet.class" codebase="http://dsic.concytec.gob.pe:8080/SignnetSignature/resources/firmaDigital/" width="600" height="330"
	archive="firmadigitalPDF.jar">
		<param name="cargo" value="<%=cargo_firmante_ls%>"/>
		<param name="comentario" value=""/>
		<param name="razon" value="<%=razon_ls%>" />
		<param name="ubicacion" value="<%=oficina_firmante_ls%>"/>
		<param name="urlConfigService" value="http://dsic.concytec.gob.pe:8080/SignnetSignature/configuracion" />
		<param name="webService" value="http://dsic.concytec.gob.pe:8080/SignnetSignature/FirmaDigitalWs?wsdl" />
		<param name="imagen " value="<%=nombreImagen%>" />
		<param name="nombreArchivos" value="<%=nombreArchivo%>" />
		<param name="rutaOrigen" value="<%=origen%>" />
		<param name="rutaDestino" value="<%=destino%>" />
		<param name="rutaImagen" value="<%=imagen%>" />
		<param name="nameTag" value="" />
		<param name="listarArchivos" value="1" />
		<param name="firmarPfxAlmacen" value="1" />
		<param name="activarDescripcion" value="1"/>
		<param name="posicionFirma" value="SD"/>
		<param name="estiloFirma" value="ID"/>
		<param name="ubicacionPagina" value="PP"/>
		<param name="altoRubrica" value="55"/>
		<param name="anchoRubrica" value="150"/>
		<param name="aplicarImagen" value="1"/>
		<param name="usarPersonalizado" value="1"/>
</applet>



</body>
</html>