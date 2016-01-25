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
<html>
	<head>
		<title>Busqueda de expedientes</title>
		
		<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		 <link rel="stylesheet" href="css/modelo.css" type="text/css" />
		 
		 <style type="text/css">
	
	h3 {
	 	text-transform:capitalize;
	  	font-family:Arial, Helvetica, sans-serif;
	}
	p {
		font-size:16px;
		font-family:Arial, Helvetica, sans-serif;
	}
	
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
		 
		 <script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
        <script src="js/funciones.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/scriptaculous.js"></script>
        <script type="text/javascript" src="js/ajaxtags.js"></script>
		<script language="JavaScript" src="js/avatec.js"></script>
		<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
		<!-- librería para cargar el lenguaje deseado -->
		<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
		<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
		<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
		
		
		<script language="JavaScript">
		
		$(document).ready(function(){
		    
		    $("#mensajegeneral").click(function () {
			      $("#mensajegeneral").fadeOut("fast");
		    });
		
		});
		
		function onLoad(){
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
</script>
	</head>
	<body topmargin="0" leftmargin="0" onLoad="javascript:onLoad();">
	
	<table width="100%" cellspacing="0" cellpadding="0"  align="left">
			  <!--DWLayoutTable-->
			<tr>
			  <td height="50" align="left" valign="middle" style="width:100%">
						<table width="100%" height="50" cellspacing="0" cellpadding="0"  align="left"  class="groupcell">
						<tr><td bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></td></tr></table>			  </td>
			  </tr>
				<tr>
					<td class="label1" align="LEFT" style="width:100%" background="img/fondoplomo8.jpg" height="26">BUSCAR DOCUMENTOS </td>
				</tr>
                <tr>
					<td align="CENTER" style="width:100%"  height="26" class="groupcell">
					<html:form action="Reiterativo">
		<html:hidden property="operacion" value="buscar" />
		<html:hidden property="indice" />
		<c:choose>
	   			<c:when test='${operacionpopup == "Y"}'>
                <SCRIPT language="javascript">
				 MensajeRegistro();
				</SCRIPT>
				<c:remove var="operacionpopup" scope="session" />
       			</c:when>
		</c:choose> 
		<div id="mensajegeneral" class="rounded">
	<div id="vvvv" style=" position: absolute;  top:18px; left:20%; ">
		<a  href="#"><img src="img/ok.png"  /></a>
	</div>
		   <br>
	       <c:out value='${mensaje}'></c:out><br>
	       <c:out value='${mensaje_reg}'></c:out><br>
	       <c:out value='${mensaje_exp}'></c:out><br>
	</div>
					<table width="50%" align="center" border="0" cellspacing="2" cellpadding="2"  >
  <tr>
    <td width="50%"><span class="labelsubtitle"><span class="label" style="width: 90px;">Oficina:</span></span></td>
    <td><html:select property="codigo_oficina">      
      <html:options collection="rs_oficina_lista" property="codigo_oficina" labelProperty="descripcion_oficina"/>  
      </html:select>      </td>
  </tr>
  <tr>
    <td><span class="labelsubtitle" style="align=&quot;center&quot;"><span class="label" >Nro de registro:</span></span></td>
    <td><label>
        <html:text property="codigo_documento"></html:text>
      </label></td>
  </tr>
  <tr>
    <td><span class="labelsubtitle" style="align=&quot;center&quot;"><span class="label" >Nro de expediente:</span></span></td>
    <td><label>
      <html:text property="codigo_expediente"></html:text>
      </label></td>
  </tr>
  
  <tr>
    <td colspan="2" align="center"><input name="boton" type="submit" value="Buscar" class="boton"></td>
    </tr>
</table>
</html:form>
 </td>
				</tr>
				<tr>
				
				  <td align="center" width="100%">
					 		<table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
  <!--DWLayoutTable-->
  
  
  <tr>
    <td height="2" align="center"><c:if test="${mensaje_usuario != null or mensaje_usuario != ''}">
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
					</c:if></td>
  </tr>
</table>  		  </td>
				</tr>
				<tr>
					<td align="center" style="width:100%" height="26">
					<display:table id="b" name="sessionScope.listaDocs" pagesize="10" export="false" style="width:85%" class="simple" align="center">
										
										<display:column property="codigo_documento" sortable="true"  title="N&deg; Registro"   />
										<display:column property="codigo_expediente" sortable="true"  title="Expediente"   />
										<display:column property="numero_documento" sortable="true"  title="N&deg;  Documento"  />										
										<display:column property="procedencia" sortable="true"  title="Procedencia" />										
									    <display:column property="asunto_documento" sortable="true"  title="Asunto Documento" />
									    <display:column property="registrar" sortable="true"  title="" />
									    <display:column property="doc_rspta" sortable="true"  title="Accion" />
									    
					</display:table>					</td>
				</tr>
		  </table>
    
	</body>
</html>
<% } %>



