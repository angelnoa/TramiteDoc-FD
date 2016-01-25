<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<%@ include file="taglibs.jsp" %>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >

		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		<link rel="stylesheet" href="css/modelo.css" type="text/css" />
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
 		<link rel="stylesheet" href="css/tipografias.css"  type="text/css">
 		
		<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
		<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
		<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
		
        <script src="js/funciones.js" type="text/javascript"></script>
        <script src="js/scriptaculous.js" type="text/javascript" ></script>
        <script  src="js/effects.js" type="text/javascript"></script>
       
        <script type="text/javascript" src="js/ajaxtags.js"></script>
		<script language="JavaScript" src="js/avatec.js"></script>
		
		<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
		<!-- librería para cargar el lenguaje deseado -->
		<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
		<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
		<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
		 
<style type="text/css">
	
		
	#mensajegeneral {
	display:none;
	position:relative;

	padding:0px;
	top: 50%;
	left: 50%; 
	text-align: center;
	font-family: Verdana, sans-serif;
	font-size: 13px;
	font-weight: bold;
	color: #003366;
	width: 780px;
	height: 80px;
	margin-left: -390px;
	background: #f1f4ac;
	overflow:auto;
	
	border: 2px solid #acc835;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;	
	clear: both;
	}
	
</style>
		 
<script language="JavaScript">
		
		$(document).ready(function(){
		    $("#cerrar-panel").click(function () {
			      $("#panel").slideUp("fast");
		    });
		    
		    $("#cerrar-mensajegeneral").click(function () {
			      $("#mensajegeneral").slideUp("fast");
		  	});

			$( "#tabs" ).tabs();
		
		});
</script>
		
<script language="JavaScript">			
		function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray");
			document.forms[0].codigo_documento.focus();
		}
		
		function buscar(){
			with(document.forms[0]){
			operacion.value = 'buscar';
			submit();
			}
		}
		function registrar(indice){
		
		document.forms[0].operacion.value = 'registrar';
		document.forms[0].indice.value = indice; 
		//alert("es "+indice);
		document.forms[0].submit();
		}
		
		function mantener(indice){
		
		document.forms[0].operacion.value = 'mantener';
		document.forms[0].indice.value = indice; 
		document.forms[0].submit();
		}
		
		function crea_doc(indice){
			
			document.forms[0].operacion.value = 'registrar';
			document.forms[0].indice.value = indice; 
			document.forms[0].submit();
		}
		
		function MensajeRegistro(){
			$(document).ready(function(){
				$("#mensajegeneral").fadeIn("slow");
			
			});
		}
		
		function MensajeFrame(){
			$(document).ready(function(){
				   $("#panel").slideDown("slow");
			});
		}
		
</script>

	<title>Busqueda de expedientes</title>
	
	</head>
	<body bgColor="#F0F5FB" topmargin="0" leftmargin="0"  onLoad="fn_onLoad();" >
	<div class="derivaconta">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
		<html:form action="Reiterativo">
		<html:hidden property="operacion" value="buscar" />
		<html:hidden property="indice" />
		<c:remove var="pagina"/>
		<c:choose>
	   			<c:when test='${operacionpopup == "Y"}'>
                <SCRIPT language="javascript">
				 MensajeRegistro();
				</SCRIPT>
				<c:remove var="operacionpopup" scope="request" />
       			</c:when>
		</c:choose>
		<c:choose>
	   		<c:when test='${cuadro_mensaje ==  "BREI"}'>
                <SCRIPT language="javascript">
				 MensajeFrame();
				</SCRIPT>
				<c:remove var="cuadro_mensaje" scope="request" />
       		</c:when>
		</c:choose> 
						

		<div id="panel" >
			<div id="vvvv" style=" position: absolute;  top:18px; left:20%; ">
				<a  href="#"><img src="img/ok.png"  /></a>
			</div>
			<div id="cerrar-panel" style="cursor:pointer; position: absolute;  width:10%; height:40px; top:15px; left:90%; text-align:left;">  </div>
			<br>
			<c:out value='${mensaje}'/><br>
				<a  href="VerArchivoPDFDocumentoInterno.do?filename=<c:out value='${nombreArchivoVer_}'/>&codigo_estado=<c:out value='${tipoDocumentoVer_}'/>&codigo_oficina_pertenece=<c:out value='${codigoOficinaVer_}'/>"><c:out value='${nombreArchivoVer_}'/></a><br>
			<br>
			&nbsp;&nbsp;
		</div>
		<div id="mensajegeneral" class="rounded">
			<div id="vvvv" style=" position: absolute;  top:5px; left:20%; ">
				<a  href="#"><img src="img/signatureok.png"  /></a>
			</div>
			<div id="cerrar-mensajegeneral" style="cursor:pointer; position: absolute;  width:10%; height:40px; top:15px; left:90%; text-align:left;">  </div>
		   	<br>
	       <c:out value='${mensaje}'/><br>
	       <a  href="VerArchivoPDFDocumentoInterno.do?filename=<c:out value='${nombreArchivoVer_}'/>&codigo_estado=<c:out value='${tipoDocumentoVer_}'/>&codigo_oficina_pertenece=<c:out value='${codigoOficinaVer_}'/>"><c:out value='${nombreArchivoVer_}'/></a><br>
	       <c:out value='${mensaje_reg}'></c:out><br>
	       <c:out value='${mensaje_exp}'></c:out><br>
		</div>
		<div id="break" ></div>
		<div id="contactderiva" class="rounded"> 
		<div id="TDHeadCab">
				   		   	<span class="labelrounded">BUSCAR DOCUMENTOS </span>
			  			
		</div>
		<div id="TDHeadCabMensaje" >
	<table width="100%" cellspacing="0" cellpadding="0"  align="left">

				<tr>
					<td width="409" height="18" align="LEFT" valign="middle"  background="img/fondoplomo8.jpg" class="textomensaje10bold">
							<c:if test="${mensaje_usuario != null or mensaje_usuario != ''}">
    <table align="center" width="100%">
						
						<tr>
							<td class="servicos-texto3" align="center" style="padding-bottom:12px;">
								<font color="red">
									<b>
										<c:out value="${mensaje_usuario}"></c:out>
									</b>								</font>
								<br/>							</td>
						</tr>
						
	</table>
	</c:if>			
				  </td>
				    <td width="568" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label1" ><!--DWLayoutEmptyCell-->&nbsp;</td>
			        <td width="47" align="center" valign="middle" background="img/fondoplomo8.jpg">&nbsp;&nbsp;&nbsp;</td>
				</tr>
					
	</table>
	</div>	
	<div id="Lineaform2" > </div>	
		<div class="center-big-left">
		<p><label>Oficina:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<html:select property="codigo_oficina" styleClass="input">      
      <html:options collection="rs_oficina_lista" property="codigo_oficina" labelProperty="descripcion_oficina"/>  
      </html:select>  
		</p>
		<p><label>Nro de registro:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<html:text property="codigo_documento" styleClass="input-medium"></html:text> 
		</p>
		<p><label>Nro de expediente:</label>
		 <html:text property="codigo_expediente" styleClass="input-medium"></html:text> 
		</p>
		
		</div>
		<div class="center-big">
		<input name="button" type="submit" value="Buscar" class="button">
		</div>
		<br>

		</div>
				
		
		<div id="break" ></div>
		<div class="derivalista">
		<div id="tabs">
		  <ul>
		    <li><a href="#tabs-1">Documentos disponibles para Adicionar</a></li>
		    <!-- li><a href="#tabs-2">Documentos Observados</a></li-->
		    
		  </ul>
		  <div id="tabs-1">
		<table width="100%"  cellpadding="0" cellspacing="0" >
					<tr>
					<td height="13" colspan="2" valign="top" align="center" >
					<display:table id="b" name="sessionScope.listaDocs" pagesize="10" export="false" style="width:85%" class="simple" align="center">
							<display:column property="codigo_documento" sortable="true"  title="N&deg; Registro"   />
										<display:column property="codigo_expediente" sortable="true"  title="Expediente"   />
										<display:column property="numero_documento" sortable="true"  title="N&deg;  Documento"  />										
										<display:column property="procedencia" sortable="true"  title="Procedencia" />										
									    <display:column property="asunto_documento" sortable="true"  title="Asunto Documento" />
									    <display:column property="observa_documento" sortable="true"  title="Observacion Documento" />
									    <display:column property="registrar" sortable="true"  title="Operacion" />
									    <!-- display:column property="doc_rspta" sortable="true" align="center" title="Operacion" /-->
					</display:table>	</td>
							</tr>
		</table>
		</div>
		</div>	
		</div>
		<div id="break" ></div>
		
		</html:form>
    </div>
	</body>
</html>
<% } %>


