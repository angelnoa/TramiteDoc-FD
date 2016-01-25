<%@ include file="taglibs.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="tramitedoc.concytec.bean.*" %>
<%@ page import="tramitedoc.concytec.dao.sql.*" %>

<HTML>
<HEAD>
<TITLE></TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;charset=utf-8" />

<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
<!-- LINK href="css/estilos.css" type="text/css" rel="stylesheet"-->
<!-- link rel="stylesheet" href="login/css/style.css"-->

<style type="text/css">

	/*#topnav li:visited > li {
		background : #000204;
	}*/
	
	/*#topnav li ul li{
	/*display:none;
	position:relative;
	right : -160px;
	top : 0;
	background : none;
	}*/
	
	#topnav li ul li ul{
	/*position:relative;*/
	right : -160px;
	top : 0;
	background : #000204;
	}
	
	#topnav li ul li ul li{
		background : #000204;
	}
	
	#topnav li .flecha{
	font-size: 9px;
	padding-left: 6px;
	display: none;
	}
	
	

</style>

<script type="text/javascript">
$(document).ready(function() {
	
if($.browser.msie){
	//alert("es ie");
	window.location = "pg_default.jsp";
}

	
$("ul#topnav li").hover(function() { 
	$(this).css({ 'background' : '#1376c9 url(topnav_active.gif) repeat-x'}); //Add background color + image on hovered list item
	$(this).find("span").show(); //Show the subnav
	
	$(this).find("ul li").css({'position' : 'relative'});
	$(this).find("ul li ul").css({'background' : 'none'});
	//$(this).find("ul li ul li").css({'right' : '-160px','top' : '0','background-image' : 'url(img/topnav_s.gif)'});
	$(this).find("ul li ul li").css({'right' : '-160px','top' : '0'});
	
	} , function() { //on hover out...

		$(this).css({ 'background' : 'none'}); //Ditch the background
		$(this).find("span").hide(); //Hide the subnav
		
	});
	
$("ul#submenu").hover(function() { 

	} , function() { //on hover out...
		$(this).find("span").hide(); //Hide the subnav
		
	});
	
});

</script>


</HEAD>
<BODY  leftMargin=0 topMargin=0 rightMargin=0 bottommargin="0" marginheight="0" marginwidth="0" >

<!-- Header Opcional Con Tablas-->
<table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="2F577F" >
  <TR vAlign=top style="background-image:url(img/nav_nuevo.gif);"> 
    <td width="456px" height="70px" align="left" valign="middle">
    	<img src="img/banner_nuevo_usuario.png" width="456" height="70" align="left" border="0" />
    </td>
    <td width="100%" align="right" valign="top" ><!--DWLayoutEmptyCell-->&nbsp;</td>
    <!-- td width="200px" align="right" valign="middle" >&nbsp;ddd</td-->
    <td width="320px" align="right" valign="middle" ><jsp:include page="/pag_de_alertas.jsp"/>&nbsp;
    <div id="something" style="position:absolute; top: 5px;"></div></td>
  	<td width="361px" align="right" valign="middle">
  	
  	<img src="img/logo_rigth_nuevo2.png" width="331px" height="70px" align="right" border="0"/>
  	</td>
  </TR>
</table>

<!--Empieza el Header >
<div style="width:100%; height: 70px; background-color: #e9e9e9; background-image:url(img/top-nav-2013.gif);" >
<div style="float:left; width: 565px; height: 70px; ">
<!-- img src="img/cab1_cabecera_tramite_1.gif" width="383" height="70"  border="0" / background-image:url(img/linea_cabecera_tramite_2.jpg);>
<img src="img/logo-cabecera-2013.png" width="565" height="70"  border="0" />
</div>

<div style="float:right; width: 331px; height: 70px; ">
<!-- img src="img/cab2_cabecera_tramite_2.jpg" width="212" height="70"  border="0"/>
<img src="img/logo-cabecera-rigth-2013.png" width="331" height="70"  border="0"/>
</div>
</div-->

<!--Termina el Header-->
<div id="menu" style="background-image:url(img/topnav_s.gif);">
<ul id="topnav" class="menu">

	<li><a href="pag_mesa_partes.jsp">Inicio</a></li>
	
	<c:if test="${modo_tramite eq '1'}">
	<li><a href="#">Documento Interno<span  class="flecha">&#9660</span></a>
	
	<ul>
			<c:choose>
           			<c:when test="${oficinaPermisoFirma eq '1'}">
           				<c:choose>
                   			<c:when test="${cargo_personal eq '11' || cargo_personal eq '2' ||  cargo_personal eq '10' || cargo_personal eq '13' || cargo_personal eq '5' || cargo_personal eq '6'  || cargo_personal eq '3'}">
                     				<li><a href="MostrarFormularioCrearcionDocumentoInterno.do?inicia=SI" target="_parent">Crear Documento <br>Interno</a></li> 
                    		</c:when>
                   		</c:choose>
           			</c:when>
           	</c:choose>
			
			
			<c:choose>
           			<c:when test="${oficinaPermisoFirma eq '1'}">
           			<c:choose>
              				<c:when test="${cargo_personal eq '11' || cargo_personal eq '5' || cargo_personal eq '6'  || cargo_personal eq '3' || cargo_personal eq '2'}">
		              			
	              				<li><a href="FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=firma&operacion=all" target="_parent">Firma Masiva.</a></li>
	              				<li><a href="FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=firma" target="_parent">Firma y Envio.</a></li>
              				</c:when>
               				<c:otherwise>
               					<li><a href="FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=vista" target="_parent">Firma y Envio.</a></li>
               					<!-- <li><a href="FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=vista" target="_parent">Documentos por Firmar</a></li>  -->
               				</c:otherwise>
                    </c:choose>  
           			</c:when>
           	</c:choose>
           	
           	<!-- <li><a href="#">Mas opciones<span  class="flecha">&#9660</span></a>
					<ul class="submenu">
						<li class="item"><a href="#">SubMenu1</a></li>
						<li class="item"><a href="#">SubMenu2</a></li>
						<li><a href="FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=vistobuenosolicitado"  target="_parent">V.B. Requerido.</a></li>
						<li><a href="FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=firmasolicitada"  target="_parent">Firma Requerida.</a></li>
					</ul>
			</li> -->
			

	</ul>
	
	
	</li>
	</c:if>
	
	<li><a href="#">Operaciones<span  class="flecha">&#9660</span></a>
	
	<ul>
			<li><a href="ListaDocumentos.do?tipo=recepcion&operacion=RN&inicia=SI"  target="_parent">Recepci&oacute;n Doc.    [ <c:out value='${cantidadBandejaRecepcion}'/> ]</a></li>
			<li><a href="ListaDocumentos.do?tipo=derivar&operacion=D&inicia=SI"  target="_parent">Derivaci&oacute;n Doc.    [ <c:out value='${cantidadBandejaTramite}'/> ]</a></li>
			<li><a href="ListaDocumentos.do?tipo=archivar&operacion=A&inicia=SI"  target="_parent">Archivar Doc.</a></li>
			<li><a href="ListaDocumentos.do?tipo=anular&operacion=A&inicia=SI"  target="_parent">Anular Doc.</a></li>
			<li><a href="Reiterativo.do"  target="_parent">Adicionar/Reiterar Doc.</a></li>
	</ul>
	
	
	</li>
	
	<li><a href="#">Seguimiento<span  class="flecha">&#9660</span></a>

		<ul>
			<li><a href="ListaDocumentos.do?tipo=seguimiento&operacion=S&inicia=SI" target="_parent">Busqueda</a></li> 
			<li><a href="ListaDocumentosFirmados.do?tipo=seguimiento_archivos&operacion=S&inicia=SI" target="_parent">Busqueda de Archivos</a></li> 
			
			<c:if test="${modo_tramite eq '1'}">
			<c:choose>
           			<c:when test="${oficinaPermisoFirma eq '1'}">
           			<c:choose>
              				<c:when test="${cargo_personal eq '11' || cargo_personal eq '5' || cargo_personal eq '6'  || cargo_personal eq '3' || cargo_personal eq '2'}">
		              			<li><a href="FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=proyectos"  target="_parent">Proyectos.</a></li>  
								
              				</c:when>
               				
                    </c:choose>  
           			</c:when>
           	</c:choose>
			</c:if>
			
		</ul>

	</li>
	<li><a href="#">Reportes<span  class="flecha">&#9660</span></a>
	
		<ul>
			<li><a href="ValidaPaginas.do?tipo=reportes&accion=criterioestado" target="_parent">Estado / <br>Fecha Respuesta Legal</a></li> 
			
			<c:if test="${nombre_usuario.flag == 'S' || cargo_personal eq '11'}">
              <li><a href="ReportesGestion.do?tipo=mos_cons_tran" target="_parent">Consolidado de transacciones</a></li>
			</c:if>
			
			<c:if test="${nombre_usuario.flag == 'S' || cargo_personal eq '11' || cargo_personal eq '10' || cargo_personal eq '13' || cargo_personal eq '3'}">
               <li><a href="ReportesGestion.do?tipo=rep_mos_doc" target="_parent">Consolidado pendientes por oficina</a></li>
			</c:if>
			
			<c:if test="${modo_tramite eq '1'}">	
			<c:choose>
       			<c:when test="${oficinaPermisoFirma eq '1'}">
       				<c:choose>
               			<c:when test="${cargo_personal eq '11' || cargo_personal eq '10' || cargo_personal eq '13' || cargo_personal eq '5' || cargo_personal eq '6'  || cargo_personal eq '3'}">

                   		<li><a href="ListaDocumentosFirmados.do?tipo=seguimiento&operacion=S&inicia=SI" target="_parent">Reporte Documentos Firmados</a></li>

                   		</c:when>
                   	</c:choose>
                </c:when>
             </c:choose>
             </c:if>        			
			
		</ul>
	
	</li>
	
		<li><a href="#">Registro</a>
			<ul>
				<li><a href="ActionSeleccion.do?rb_seleccion=E" target="_parent">Registro Doc.</a></li> 
				<c:choose>
					<c:when test="${codigo_oficina ==  '1' }" >
						<li><a href="ListaDocumentos.do?tipo=preliquidar&operacion=P"  target="_parent">Pre-Cerrar Doc.</a></li>
	            		<li><a href="ListaDocumentos.do?tipo=liquidar&operacion=L"  target="_parent">Cerrar Doc.</a></li>
					</c:when>
				</c:choose>
				
			</ul>
		
		</li>
				
	
	<!-- li><a href="#">Reportes</a>
	
		<ul>
			<li><a href="#" class="documents">Documents</a></li> 
			<li><a href="#" class="messages">Messages</a></li>
			<li><a href="#" class="signout">Sign Out</a></li>
		</ul>
	
	</li-->
	
	<li style="float:right; left:-115px;"><a href="AdmUsuario.do?tipo=inicio">Cambiar contraseña</a></li>
	<!-- <li style="float:right; left:100px; "><a href="AdmUsuario.do?tipo=salir">Salir</a></li> -->
	<li style="float:right; left:100px; "><a id="lnkLogout" href="AdmUsuario.do?tipo=salir">Salir</a></li>

</ul> <!-- end .menu -->

</div>
<!--  TERMINA EL MENU -->
<div style="width:100%; height: 30px; background-color: #e9e9e9;  border-bottom: 1px solid #a0b3c6; padding: 0 0px; margin: 0px 0; line-height: 28px; FONT-SIZE: 12px; 
	COLOR: #616161; 
	FONT-FAMILY: Tahoma, Verdana" >
<div style="float:left; width: 500px; height: 30px; ">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img  src="img/im-user-green.png"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp;<b>Bienvenido(a):</b>&nbsp;
                        <c:out value='${nombreusuario}'/> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;               
<img  src="img/group-blue-icon.png"  style=" vertical-align: middle; width:24px; height:20px;  border:0px; ">&nbsp;&nbsp;<b>Oficina:</b>&nbsp;<c:out value='${nombrecortooficina}'/><c:if test="${ls_codigo_institucion=='2'}">
							- <b>CONCYTEC</b> 
							</c:if>
							<c:if test="${ls_codigo_institucion=='3'}">
							- <b>FONDECYT</b>
							</c:if>
</div>

<div style="float:right; width: 254px; height: 30px; ">
&nbsp;<img  src="img/icon-calendar-2013.png"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp;
<script language="JavaScript">
var meses = new Array ("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
var diasSemana = new Array("Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado");
var f=new Date();
document.write(diasSemana[f.getDay()] + ", " + f.getDate() + " de " + meses[f.getMonth()] + " de " + f.getFullYear());

<%
SqlParametroDAO sqlParametroDAO = new SqlParametroDAO();	
List<BParametro> parametros = sqlParametroDAO.listar();
String Valor = "";
String urlLogoutGoogle = "";
for(BParametro p : parametros){
	if(p.getSimbolo().equalsIgnoreCase("GLE")){
		Valor = p.getValor();			
	}
	if(p.getSimbolo().equalsIgnoreCase("DLE")){
		urlLogoutGoogle = p.getValor();
	}		
}

if(Valor.equalsIgnoreCase("1")) {
	%>
	$('#lnkLogout').click(function (e){
		e.preventDefault();
		var enlace = $(this).attr('href');	
		$('<iframe id="iframeLogoutGoogle" style="display:none;" src="<%=urlLogoutGoogle%>">').appendTo('body');
		$('#iframeLogoutGoogle').load(function (e){
			window.location = enlace;
		});	
	});
<%}%>

<%
String ls_picture_google = (String) session.getAttribute("picture_google");

if(ls_picture_google!=null){
%>
var img = $("<img />").attr({
   'src': '<%=ls_picture_google%>',
   'width': '50px',
   'height': '60px'
})
.load(function() {
if (!this.complete || typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) {
    alert('broken image!');
} else {
    $("#something").append(img);
}
});
<%}%>

</script>
</div>
</div>

</BODY>
</HTML>