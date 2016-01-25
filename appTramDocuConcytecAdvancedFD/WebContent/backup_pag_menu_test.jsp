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

<style type="text/css">
ul#topnav {
	margin: 0; 
	padding: 0;
	float: left;
	width: 970px;
	
	list-style: none;
	position: relative;
	font-size: 1.2em;
	/*background:url(../forms/images/topnav_s.gif) repeat-x;*/
}
ul#topnav li {
	float: left;
	margin: 0; 
	padding: 0;
	border-right: 1px solid #555;
	
}
ul#topnav li a {
	padding: 10px 15px;
	display: block;
	color: #f0f0f0;
	text-decoration: none;
	
	text-align: center; 
		/*line-height: 30px;
		color: #336699;*/
		FONT-SIZE: 9pt;
		font-weight: NORMAL;
}
ul#topnav li:hover { background: #1376c9 url (../forms/images/topnav_a.gif) repeat-x; }
ul#topnav li span {
	float: left;
	padding: 15px 0;
	position: absolute;
	left: 0; 
	top:35px;
	display: none;
	width: 970px;
	background: #1376c9;
	color: #fff;
	-moz-border-radius-bottomright: 5px;
	-khtml-border-radius-bottomright: 5px;
	-webkit-border-bottom-right-radius: 5px;
	-moz-border-radius-bottomleft: 5px;
	-khtml-border-radius-bottomleft: 5px;
	-webkit-border-bottom-left-radius: 5px;
}
ul#topnav li:hover span { display: block; }
ul#topnav li span a { display: inline; }
ul#topnav li span a:hover {text-decoration: underline;}
</style>


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
<!-- CABECERA -->
<div style="width:100%; height: 70px; background-color: #e9e9e9; background-image:url(img/linea_cabecera_tramite_2.jpg);" >
<div style="float:left; width: 383px; height: 70px; ">
<img src="img/cab1_cabecera_tramite_1.gif" width="383" height="70"  border="0" />
</div>

<div style="float:right; width: 324px; height: 70px; ">
<img src="img/cab2_cabecera_tramite_2.jpg" width="324" height="70"  border="0"/>
</div>
</div>
<!-- FIN CABECERA -->

<!-- MENU -->
<div style="width:100%; height: 35px; background-color: #e9e9e9; background-image:url(img/topnav_s.gif);" >
<ul id="topnav">
        <li><a href="pag_mesa_partes.jsp" >Inicio</a></li>
        <li>
            <a href="#">Registro</a>
            <span>
                <a href="ActionSeleccion.do?rb_seleccion=E" target="_parent">Registro Doc.</a> |
                <a href="ListaDocumentos.do?tipo=recepcion&operacion=RN&inicia=SI"  target="_parent">Recepci&oacute;n Doc.</a> |
                <a href="ListaDocumentos.do?tipo=derivar&operacion=D&inicia=SI"  target="_parent">Derivaci&oacute;n Doc.</a> |
                <a href="ListaDocumentos.do?tipo=archivar&operacion=A&inicia=SI"  target="_parent">Archivar Doc.</a> |
                <a href="ListaDocumentos.do?tipo=anular&operacion=A&inicia=SI"  target="_parent">Anular Doc.</a> |
                
                <c:choose>
				<c:when test="${codigo_oficina ==  '1' }" >
                <a href="ListaDocumentos.do?tipo=preliquidar&operacion=P"  target="_parent">Pre-Cerrar Doc.</a> |
                <a href="ListaDocumentos.do?tipo=liquidar&operacion=L"  target="_parent">Cerrar Doc.</a> |
                </c:when>
                </c:choose>
                
                <a href="Reiterativo.do"  target="_parent">Reiterativo</a> |
                <c:choose>
            			<c:when test="${codigo_oficina eq '29' || codigo_oficina eq '30' || codigo_oficina eq '28' || codigo_oficina eq '27' || codigo_oficina eq '5' || codigo_oficina eq '8'}">
            				<c:choose>
                    			<c:when test="${cargo_personal eq '11' || cargo_personal eq '10' || cargo_personal eq '13' || cargo_personal eq '5' || cargo_personal eq '6'  || cargo_personal eq '3'}">
                    	
                      				<a href="MostrarFormularioCrearcionDocumentoInterno.do?inicia=SI" target="_parent">Crear Documento Interno</a> | 
                        			

	                    		</c:when>
                    		</c:choose>
                    		<c:choose>
                      				<c:when test="${cargo_personal eq '11' || cargo_personal eq '5' || cargo_personal eq '6'  || cargo_personal eq '3'}">
                      				<a href="FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=firma" target="_parent">Documentos por Firmar</a>
                        				
                        				
                      				</c:when>
                      				<c:otherwise>
                      				<a href="FormularioBusquedaDocumentoInterno.do?inicia=SI&tipo=vista" target="_parent">Documentos para Firmar</a>
                       					  
                        				
                      				</c:otherwise>
                      			</c:choose>  
             			</c:when>
            		</c:choose>
               
                
            </span>
        </li>
        <li>
            <a href="#">Seguimiento</a>
            <span>
                <a href="">What We Do</a> |
                <a href="">Our Process</a> |
                <a href="">Testimonials</a>
            </span>
        </li>
        <li>
            <a href="#">Reportes</a>
            <span>
                <a href="">Web Design</a> |
                <a href="">Development</a> |
                <a href="">Identity</a> |
                <a href="">SEO &amp; Internet Marketing</a> |
                <a href="">Print Design</a>
            </span>
        </li>
        <li><a href="">Cambio de Contraseña</a></li>
        <li><a href="">Salir</a></li>
    </ul>
</div>

<div style="width:100%; height: 70px; background-color: #e9e9e9; background-image:url(img/linea_cabecera_tramite_2.jpg);" >
<div style="float:left; width: 383px; height: 70px; ">
<img src="img/cab1_cabecera_tramite_1.gif" width="383" height="70"  border="0" />
</div>

<div style="float:right; width: 324px; height: 70px; ">
<img src="img/cab2_cabecera_tramite_2.jpg" width="324" height="70"  border="0"/>
</div>
</div>


<TABLE cellSpacing=0 cellPadding=0 width=100% border=0 height="">
                <TBODY>
              <TR vAlign=top>
                      <TD 
                width=16 height="25" align=middle vAlign=top background=index_php_archivos/am.jpg class="Arial12px000000LinkMenuLocal">&nbsp;</TD>    
                      <TD class=Arial12px000000LinkMenuLocal vAlign=center 
                align=middle width=13 
                  background=index_php_archivos/am.jpg>&nbsp; </TD>
                    <TD class=Arial12px000000LinkMenuLocal vAlign=center align=left 
                width=223 background=img/am.jpg> 
                    &nbsp;&nbsp;&nbsp;Bienvenido(a):&nbsp;
                        <span class="style1">
                        <c:out value='${nombreusuario}'/></span>
					</TD>
                
                <TD width="312" vAlign=top background=index_php_archivos/am.jpg><IMG 
                  height=2 src="index_php_archivos/pixel_blank.gif" width=2></TD>
               <TD class=Arial12px000000LinkMenuLocal vAlign=center align=left 
                width=175 background=img/am.jpg> 
                      </TD>
                <TD class=Arial12px000000LinkMenuLocal vAlign=center align=right width=157 
                background="img/am.jpg"><div align="left" > 
        <script language="JavaScript">
		 document.write( f_fecha_actual() ); 
		 
       </script>
      </div></TD>
		</TR></TBODY>
</TABLE>

</BODY>
</HTML>