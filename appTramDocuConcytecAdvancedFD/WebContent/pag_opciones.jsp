<% 
//Verifica que exista una sesion
String nombreusuario = String.valueOf(session.getAttribute("nombreusuario"));
String flag = String.valueOf(session.getAttribute("ls_flag"));
String sede = String.valueOf(session.getAttribute("ls_sede"));
if (nombreusuario==null || nombreusuario.equals("null")|| !flag.equals("A") ){
%> 
<SCRIPT language="Javascript">
parent.document.location ="inicio.jsp";
</SCRIPT>
<% 
}else {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link  href="css/LightBlue_Theme.css" rel="stylesheet" type="text/css" />
<meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="js/modernizr.custom.29473.js"></script>
		
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></script>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />


<style type="text/css">
 #cuadro{
	font-family: Cambria, Palatino, "Palatino Linotype", "Palatino LT STD", Georgia, serif;
	padding-top:5px;
	height:25px;
	FONT-SIZE: 12pt; 
}
	
</style>  

<title>Pagina Opciones</title>
	
</head>

<body >
<div class="container">
			<!-- Codrops top bar -->
			<section class="ac-container">
				<div>
					<input id="ac-1" name="accordion-1" type="checkbox" />
					<label for="ac-1"><a href="pag_bandeja.jsp" target="mainFrame">Inicio</a></label>
					
				</div>
				<div>
				<style>
				.ac-container input:checked ~ article.ac-fit {
    height: 240px;
}
				
				</style>
					<input id="ac-2" name="accordion-1" type="checkbox" />
					<label for="ac-2">Maestros</label>
					<article class="ac-fit">
					<table width="100%" border="1">
					<tr>
					<td width="10%"></td>
					<td colspan="2" width="80%" align="left"><div id="cuadro" ><a href="Maestros.do?tipo=usuario" target="mainFrame">Usuarios</a></div></td>
					</tr>
					<tr>
					<td width="10%"></td>
					<td colspan="2" width="80%" align="left"><div id="cuadro" ><a href="Maestros.do?tipo=oficina" target="mainFrame">Oficinas</a></div></td>
					</tr>
					<tr>
					<td width="10%"></td>
					<td colspan="2" width="80%" align="left"><div id="cuadro" ><a href="Maestros.do?tipo=personal" target="mainFrame">Personal</a></div></td>
					</tr>
					<tr>
					<td width="10%"></td>
					<td colspan="2" width="80%" align="left"><div id="cuadro" ><a href="Maestros.do?tipo=documento" target="mainFrame">Documento</a></div></td>
					</tr>
					<tr>
					<td width="10%"></td>
					<td colspan="2" width="80%" align="left"><div id="cuadro" ><a href="Maestros.do?tipo=cargo" target="mainFrame">Cargos</a></div></td>
					</tr>
					<tr>
					<td width="10%"></td>
					<td colspan="2" width="80%" align="left"><div id="cuadro" ><a href="Maestros.do?tipo=tipodocumento" target="mainFrame">Tipo Documento</a></div></td>
					</tr>
					<tr>
					<td width="10%"></td>
					<td colspan="2" width="80%" align="left"><div id="cuadro" ><a href="Maestros.do?tipo=solicitud" target="mainFrame">Solicitud</a></div></td>
					</tr>
					<% 
					if(sede.equals("1")){
					%>
					<tr>
					<td width="10%"></td>
					<td colspan="2" width="80%" align="left"><div id="cuadro" ><a href="switchLogin.jsp" target="mainFrame">Acceso al sistema</a></div></td>
					</tr>
					<% 
					}
					%>
					</table>

					</article>
				</div>
				
				<div>
				<style>
				.ac-container input:checked ~ article.ac-fit1 {
    height: 50px;
}
				
				</style>
					<input id="ac-3" name="accordion-1" type="checkbox" />
					<label for="ac-3">Auditoria</label>
					<article class="ac-fit1">
					<table  border="1">
					<tr>
					<td width="10%"></td>
					<td colspan="2"  align="left"><div id="cuadro" ><a href="Maestros.do?tipo=conexionusuario" target="mainFrame">Conexion de usuarios</a></div></td>
					</tr>
					
					</table>

					</article>
				</div>
				<div>
					<input id="ac-3" name="accordion-1" type="checkbox" />
					<label for="ac-3"><a href="index.jsp" target="session.close()">Salir del Sistema</a></label>
				</div>
				
			</section>
        </div>


</body>
</html>
<% 
}
%>