<%@ include file="taglibs.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="language" content="es" />

<link href="theme/css3/style.css" rel="stylesheet" type="text/css" />
<link href="theme/css3/style-adicional.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/tipografias.css"  type="text/css">

<!--INCLUYO SCRIPTS ADICIONALES-->
<!-- jsp:include page="/function-theme.jsp"/-->
<!-- FIN DE SCRIPTS ADICIONALES-->  


<SCRIPT language="javascript">
	
	function ingreso(){
		form_datos.action="Login.do";
		form_datos.submit();	
	}
	
	function registrarse(){
		form_datos.action="solicitarReg.do";
		form_datos.submit();	
	}
	
	
</SCRIPT>

<script language="javascript" type="text/javascript">
<!--
/****************************************************
     Author: Eric King
     Url: http://redrival.com/eak/index.shtml
     This script is free to use as long as this info is left in
     Featured on Dynamic Drive script library (http://www.dynamicdrive.com)
****************************************************/
var win=null;
function NewWindow(mypage,myname,w,h,scroll,pos){
if(pos=="random"){LeftPosition=(screen.width)?Math.floor(Math.random()*(screen.width-w)):100;TopPosition=(screen.height)?Math.floor(Math.random()*((screen.height-h)-75)):100;}
if(pos=="center"){LeftPosition=(screen.width)?(screen.width-w)/2:100;TopPosition=(screen.height)?(screen.height-h)/2:100;}
else if((pos!="center" && pos!="random") || pos==null){LeftPosition=0;TopPosition=20}
settings='width='+w+',height='+h+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no';
win=window.open(mypage,myname,settings);}
// -->
</script>

<script type="text/javascript">
 

//timer = setTimeout('temporizador()', 30000);

function temporizador() {
	NewWindow('http://www.google.com','mywin','400','200','no','center');
	timer = setTimeout("temporizador()", 30000);
}

</script>


<title>Sistema de Tramite Documentario **-CONCYTEC-**</title>
<!--TERMINA EL HEAD-->
</head>
<body>



<!--EMPIEZA EL CONTENEDOR DEL PAGE-->
<div id="page">
<!--INCLUYO EL MENU-->
<!-- jsp:include page="/inc_header.jsp"/-->
<jsp:include page="/headTramite.jsp"/>

<div class="clear" id="content">

<div id="cent" style="background-image:url(img/logo-front-background-new.png);">
  <div class="clear" id="botones"><br><br>
  <!-- ul>
 <li class="buscar-btn">
  <a href="BusquedaInvestigadores.do?tipo=busqinv"><span>Buscar</span></a>  
  </li>
   <li class="registrar-btn">
  <a href="DirectorioCTI.do?tipo=registrousuario"><span>Registrar</span></a>  
  </li>
  </ul-->
  </div>
  <!--Fomurario de Login-->
  <form name="form_datos" id="form_datos" class="login-form" method="post" action="Login.do" >
	<!-- <div class="clear" id="contexto-form">
		<img src="theme/images/loguito-2013.png" width="32" height="32"/>
	</div> -->
	<!--HEADER-->
	<br></br><br></br><br>
    <div class="header" style="padding-left : 0;">
    <!--TITLE--><h1>Ingreso de Usuarios</h1><!--END TITLE-->
    </div>
    <!--END HEADER-->
	
	<!--CONTENT-->
    <div class="content">
    			  <p><label>Usuario</label>
	<!--USERNAME--><input id="login-form" name="usuario" type="text" class="input username" value="Username" onfocus="this.value=''" /><!--END USERNAME-->
				  </p>	
					
					<p><label>Password</label>
    <!--PASSWORD--><input name="password" type="password" class="input password" value="Password" onfocus="this.value=''" /><!--END PASSWORD-->
    				</p>
    				<div style="color:red; font-size:9pt; font-weight: bold; line-height: 9px; ">&nbsp;<html:errors property="login"/></div>
    					
    </div>
    <!--END CONTENT-->
    
    <!--FOOTER-->
    <div class="footer">
    <!--LOGIN BUTTON--><input type="submit"  name="submit" value="INGRESAR" class="button" /><!--END LOGIN BUTTON-->
    <!--REGISTER BUTTON><input type="submit" name="submit" value="Register" class="register" /><--END REGISTER BUTTON-->
    </div>
    <!--END FOOTER-->

	</form>
  <!--Fin de Formulario de Login-->
  

</div>
</div>
<!--INCLUYO EL FOOTER-->
<jsp:include page="/inc_footer.jsp"/>
</div>
</body>
</html>