<%@ include file="taglibs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;charset=utf-8" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
<!-- LINK href="css/estilos.css" type="text/css" rel="stylesheet"-->
<!-- link rel="stylesheet" href="login/css/style.css"-->

<script type="text/javascript">
//$.browser.chrome = /chrome/.test(navigator.userAgent.toLowerCase()); 
if($.browser.msie){
	//alert("es ie");
	window.location = "pg_default.jsp";
}
/*if($.browser.chrome){
	alert("si es Crome");
}*/
</script>


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
<BODY   bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" >

<!--Empieza el Header -->
<!-- div style="width:100%; height: 70px; background-color: #e9e9e9;  background-image:url(img/top-nav-2013.gif);" >
<div style="float:left; width: 565px; height: 70px; ">
<img src="img/cab1_cabecera_tramite_1.gif" width="383" height="70"  border="0" / background-image:url(img/linea_cabecera_tramite_2.jpg);>
<img src="img/logo-cabecera-2013.png" width="565" height="70"  border="0" />
</div>

<div style="float:right; width: 331px; height: 70px; ">
<!-- img src="img/cab2_cabecera_tramite_2.jpg" width="212" height="70"  border="0"/>
<img src="img/logo-cabecera-rigth-2013.png" width="331" height="70"  border="0"/>
</div>
</div-->

<!-- Header Opcional Con Tablas-->
<table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="a8b5c8" >
  <TR vAlign=top style="background-image:url(img/top-navv2-2013.gif);"> 
    <td width="184px" height="71px" align="left" valign="middle">
    	<img src="img/logo-cabecerav2-John.png" width="184px" height="71px" align="left" border="0" />
    </td>
    <td width="100%" valign="top" >&nbsp;</td>
  	<td width="242px" align="right" valign="middle">
  		<img src="img/logo-cabecerav2-rigth-2013.png" width="242px" height="71px" align="right" border="0"/>
  	</td>
  </TR>
</table>


<!-- table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="a8b5c8">
  <TR vAlign=top style="background-image:url(img/linea_cabecera_tramite_2.jpg)"> 
    <td width="383" height="70" align="left" valign="middle"><img src="img/cab1_cabecera_tramite_1.gif" width="383" height="70" align="left" border="0" /></td>
    <td width="100%" valign="top" >&nbsp;</td>
  <td width="324" align="right" valign="middle"><img src="img/cab2_cabecera_tramite_2.jpg" width="324" height="70" align="right" border="0"/></td>
  </TR>
</table-->
</BODY>
