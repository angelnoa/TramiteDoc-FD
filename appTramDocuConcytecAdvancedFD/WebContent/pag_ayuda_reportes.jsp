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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Ayuda de Reportes Consolidados</title>
		<meta http-equiv="Expires" content="0">
<meta http-equiv="Last-Modified" content="0">
<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
<meta http-equiv="Pragma" content="no-cache">

		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
        <script src="js/funciones.js" type="text/javascript"></script>
		<script  src="js/prototype-1.4.0.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/scriptaculous.js"></script>
       
        <script type="text/javascript" src="js/ajaxtags.js"></script>
		
		<script language="JavaScript" src="js/avatec.js"></script>
		<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
		<!-- librer�a para cargar el lenguaje deseado -->
		<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
		<!-- librer�a que declara la funci�n Calendar.setup, que ayuda a generar un calendario en unas pocas l�neas de c�digo -->
		<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
		
		<SCRIPT type=text/javascript>

function exportar_excel2(){

			//var strURL='generateReport.do?tipo=RD';	
			//window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes");
			//return;
	document.form_datos.action='generateReport.do?tipo=RD';
  	document.form_datos.target='_blank';
	document.form_datos.submit();
}
		
function ver_frame(){
	
	
	var ls_numero_registro = document.getElementById("numero_registro").value;
	//var ls_codigo_expediente = document.getElementById("codigo_expediente").value;

	 var strURL = "ValidaPaginas.do?tipo=ayudamedios";
	 strURL += "&numero_registro="+ls_numero_registro.toUpperCase();
	
 	 location=strURL;
	return;

	}
		
	function regresar(){
	
	 document.location.href="ValidaPaginas.do?tipo=regresar&pagina=docentrada"
	return;
	}
		
	function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray");
		
	}
	
	function fn_onUnLoad(){
		window.opener.document.forms[0].target = 'mainFrame';
	}
	

	function VerArchivos(ls_codigo_documento,ls_codigo_expediente,ls_codigo_recepcion){
		   
		var ls_operacion = "N";
		/*var ls_codigo_documento = document.getElementById("codigo_documento").value;
		var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
		var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;*/
		//alert(ls_codigo_documento);
		
		var strURL='MantSeguimiento.do?tipo=upload'+'&operacion='+ls_operacion+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&codigo_recepcion='+ls_codigo_recepcion;	
		window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes")
		
		
}
</SCRIPT>
	</head>
	<body topmargin="0" leftmargin="0" onLoad="fn_onLoad();">
		
		<form id="form_datos" name="form_datos" method="post" action="">
			<table width="100%" cellspacing="0" cellpadding="0"  align="left">
			
				<tr>
					<c:choose>
					<c:when test='${operacion ==  "1"}'>
					<td class="label" align="center" style="width:100%" background="img/fondoplomo8.jpg" height="26">
							Lista Documentos Pendientes Por Recibir 
					</td>
					</c:when>
					<c:otherwise>
					<td class="label" align="center" style="width:100%" background="img/fondoplomo8.jpg" height="26">
							Lista Documentos Pendientes en Tramite 
					</td>
					</c:otherwise>
					</c:choose>
					
				</tr>
				<tr>
					<td class="label" align="center" style="width:100%" background="img/fondoplomo8.jpg" height="26">
							<c:out value='${oficinaz}' /> 
					</td>
				</tr>
				<tr>
				
				 
					<td>
						<table width="100%" align="center" cellpadding="0" cellspacing="0">
						
							<tr>
								<td colspan="2">
										<display:table id="b" name="sessionScope.listadocumentos" pagesize="10" export="false" style="width:100%" class="simple" > 
										<display:column property="codigo_documento"  title="N&deg; Registro"   />
										<display:column property="codigo_expediente"   title="N&deg; Expediente"  />
										<display:column property="numero_documento"   title="N&deg;  Documento"  />
										<display:column property="fecha_registro"  title="Fecha Registro"  />
										<c:if test='${operacion ==  "0"}'>
											<display:column property="fecha_movimiento"  title="Fecha de Recepcion"  />
										</c:if>
										<display:column property="firmadopor"  title="Dependencia Origen - Firmado por"  />
										<display:column property="oficina_origen"  title="Oficina Origen"  />
										<display:column property="oficina_deriva"  title="Oficina Destino"  />
										<display:column property="estado_movimiento"  title="Estado"  />
										<display:column property="view_archivo" align="center"  title="Ver Documento" />
										<display:column property="asunto_documento"  title="Asunto Documento" />
										</display:table>
										</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
				<td width="50%" align="right" valign="middle" background="img/fondoplomo8.jpg">Exportar a:&nbsp;
						   <A  href="javascript:exportar_excel2()" ><img src="img/xls.gif" border="0" height="20" title="Exportar a Excel"></A>
						   </td>
				</tr>
		  </table>
		
		</FORM>
	</body>
</html>
<% } %>
