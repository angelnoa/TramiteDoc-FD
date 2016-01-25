<%@ include file="taglibs.jsp" %>

<HTML>
<HEAD>
<TITLE></TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;charset=utf-8" />

<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
<!-- LINK href="css/estilos.css" type="text/css" rel="stylesheet"-->
<!-- link rel="stylesheet" href="login/css/style.css"-->

<script type="text/javascript">
$(document).ready(function() {
	
$("ul#topnav li").hover(function() { //Hover over event on list item
	$(this).css({ 'background' : '#1376c9 url(topnav_active.gif) repeat-x'}); //Add background color + image on hovered list item
	$(this).find("span").show(); //Show the subnav
} , function() { //on hover out...
	$(this).css({ 'background' : 'none'}); //Ditch the background
	$(this).find("span").hide(); //Hide the subnav
});
	
});
</script>


</HEAD>
<BODY  leftMargin=0 topMargin=0 rightMargin=0 bottommargin="0" marginheight="0" marginwidth="0" >

<!-- Header Opcional Con Tablas-->
<table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="a8b5c8" >
  <TR vAlign=top style="background-image:url(img/top-nav-2013.gif);"> 
    <td width="565px" height="70px" align="left" valign="middle">
    	<img src="img/logo-cabecera-2013.png" width="565" height="70" align="left" border="0" />
    </td>
    <td width="100%" valign="middle" ><jsp:include page="/pag_de_alertas.jsp"/>&nbsp;</td>
  	<td width="331px" align="right" valign="middle">
  		<img src="img/logo-cabecera-rigth-2013.png" width="331px" height="70px" align="right" border="0"/>
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
	<li><a href="#">Registro</a>
	
	<ul>
           	<li><a href="ActionSeleccion.do?rb_seleccion=E" target="_parent">Registro Doc.</a></li> 
			<li><a href="ListaDocumentos.do?tipo=recepcion&operacion=RN&inicia=SI"  target="_parent">Recepci&oacute;n Doc.</a></li>
			<li><a href="ListaDocumentos.do?tipo=derivar&operacion=D&inicia=SI"  target="_parent">Derivaci&oacute;n Doc.</a></li>
			<li><a href="ListaDocumentos.do?tipo=archivar&operacion=A&inicia=SI"  target="_parent">Archivar Doc.</a></li>
			<li><a href="ListaDocumentos.do?tipo=anular&operacion=A&inicia=SI"  target="_parent">Anular Doc.</a></li>
			<li><a href="Reiterativo.do"  target="_parent">Adicionar Doc.</a></li>
			<c:choose>
					<c:when test="${codigo_oficina ==  '1' }" >
						<li><a href="ListaDocumentos.do?tipo=preliquidar&operacion=P"  target="_parent">Pre-Cerrar Doc.</a></li>
	            		<li><a href="ListaDocumentos.do?tipo=liquidar&operacion=L"  target="_parent">Cerrar Doc.</a></li>
					</c:when>
			</c:choose>
	</ul>
	
	
	</li>
	<li><a href="#">Seguimiento</a>

		<ul>
			<li><a href="ListaDocumentos.do?tipo=seguimiento&operacion=S&inicia=SI" target="_parent">Busqueda</a></li> 
		</ul>

	</li>
	<li><a href="#">Reportes</a>
	
		<ul>
			<li><a href="ValidaPaginas.do?tipo=reportes&accion=criterioestado" target="_parent">Estado / <br>Fecha Respuesta Legal</a></li> 
			
			<c:if test="${nombre_usuario.flag == 'S' || cargo_personal eq '11'}">
												
              <li><a href="ReportesGestion.do?tipo=mos_cons_tran" target="_parent">Consolidado de transacciones</a></li>
                        
			</c:if>
			
			<c:if test="${nombre_usuario.flag == 'S' || cargo_personal eq '11' || cargo_personal eq '10' || cargo_personal eq '13' || cargo_personal eq '3'}">
						
                        <li><a href="ReportesGestion.do?tipo=rep_mos_doc" target="_parent">Consolidado pendientes por oficina</a></li>
			</c:if>
				
			<c:choose>
       			<c:when test="${codigo_oficina eq '29' || codigo_oficina eq '30' || codigo_oficina eq '28' || codigo_oficina eq '27' || codigo_oficina eq '5' || codigo_oficina eq '8'}">
       				<c:choose>
               			<c:when test="${cargo_personal eq '11' || cargo_personal eq '10' || cargo_personal eq '13' || cargo_personal eq '5' || cargo_personal eq '6'  || cargo_personal eq '3'}">

                   		<li><a href="ListaDocumentosFirmados.do?tipo=seguimiento&operacion=S&inicia=SI" target="_parent">Reporte Documentos Firmados</a></li>

                   		</c:when>
                   	</c:choose>
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
	<li style="float:right; left:100px; "><a href="AdmUsuario.do?tipo=salir">Salir</a></li>

</ul> <!-- end .menu -->

</div>
<!--  TERMINA EL MENU -->
<div style="width:100%; height: 30px; background-color: #e9e9e9;  border-bottom: 1px solid #a0b3c6; padding: 0 0px; margin: 0px 0; line-height: 28px; FONT-SIZE: 12px; 
	COLOR: #616161; 
	FONT-FAMILY: Tahoma, Verdana" >
<div style="float:left; width: 383px; height: 30px; ">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img  src="img/im-user-green.png"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp;Bienvenido(a):&nbsp;
                        <c:out value='${nombreusuario}'/>
</div>

<div style="float:right; width: 254px; height: 30px; ">
&nbsp;<img  src="img/icon-calendar-2013.png"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp;
<script language="JavaScript">
var meses = new Array ("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
var diasSemana = new Array("Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado");
var f=new Date();
document.write(diasSemana[f.getDay()] + ", " + f.getDate() + " de " + meses[f.getMonth()] + " de " + f.getFullYear());
</script>
</div>
</div>

</BODY>
</HTML>