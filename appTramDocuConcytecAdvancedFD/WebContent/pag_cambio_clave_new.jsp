<%@ include file="taglibs.jsp" %>

<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Ventana de Mesa de Partes</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  
        <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		<link rel="stylesheet" href="css/modelo.css" type="text/css" />

<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<script>

 function Cancelar(){
  var strURL = "./pag_mesa_partes.jsp";
  location=strURL;
 }
  function Modificar(){
 
 	
		 if (Validarcampovacio(document.form_datos.usuario.value) == false)
              {              
                    alert("Ingresar Usuario");
                    document.form_datos.usuario.focus();
                    return false;           
              } 
			   
				
	usuario = document.getElementById("usuario").value

	clavenueva = document.getElementById("new_password").value
	confirmarclavenueva = document.getElementById("confirm_new_password").value

	if(clavenueva!=confirmarclavenueva){
	
					alert("Nueva Contraseña y Confirmar Contraseña diferentes");
                    document.form_datos.new_password.focus();
                    return false;           
	
	}else if(clavenueva=='') {
	     alert("Contraseña en blanco");
                    document.form_datos.new_password.focus();
                    return false; 
	}
	
	document.form_datos.submit();
    //	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	
	//window.close()
	
	
}

</script>

    
</HEAD>
<BODY bgColor="#F0F5FB" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" >
<div id="container">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
<TABLE  cellSpacing="0" cellPadding="0" width="100%" border="0"  height="">
  <!--DWLayoutTable-->
    <TBODY>
	
    <TR>
        <TD height="26" colspan="2" class="label" align="middle"  valign="middle"   background="img/fondoplomo8.jpg"></TD>
    </TR>
    <TR >
      <TD width="100%" height="100%" align="center">
	  <FORM id="form_datos" name="form_datos" action="AdmUsuario.do" method="post">
	  <input type="hidden" name="tipo" value="cambiar_password"/>
				
	<TABLE width="43%" CELLSPACING="7px." CELLPADDING="0">
<TR>
			<td align="center" valign="middle" class="TextFieldOn35">
				<font color="#000000" size="2" ><strong>CAMBIO DE CONTRASEÑA</strong></font>			</td>
	  </TR>				
		
		<TR>
		  <TD width="100%" align="center" valign="middle" class="groupcell">
			  <TABLE align="center" cellspacing="0" cellpadding="0" width="100%" border="0" >
			    <!--DWLayoutTable-->
        <TR>
						<td style="height: 10px;" colspan="5">&nbsp;</td>
			    </TR>	
					
					<TR>
					  <td width="21" style="width: 10px;">&nbsp;</td>
					  <td width="418"  height="25"  align="right"  valign="middle" class="label" >Usuario:</td>
						<td width="21" align="center" class="labelsubtitle" style="width: 10px;">&nbsp;</td>
						
          <td width="750" align="left" valign="middle" style="width: 350px;"> 
        <INPUT class="txt" id="usuario" maxLength="30"  readonly size="30" name="usuario"  value="<c:out value='${nombreusuario}'/>">						</td>						
					  <td width="30" style="width: 10px;">&nbsp;</td>
				  </TR>
					
					<TR>
						<td style="width: 10px;">&nbsp;</td>
					  <td align="right" valign="middle"  class="label" height="25">Nueva Contraseña:</td>
						<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;</td>
						<td style="width: 350px;" align="left" valign="middle">																				
					  <INPUT class="txt" id="new_password" maxLength="30" size="30" name="new_password"  type="password">						</td>						
						<td style="width: 10px;">&nbsp;</td>
					</TR>
					
					<TR>
						<td style="width: 10px;">&nbsp;</td>
					  <td align="right" valign="middle"  class="label" height="25">Confirmar Contraseña:</td>
						<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;</td>
						<td style="width: 350px;" align="left" valign="middle">																				
					  <INPUT class="txt" id="confirm_new_password" maxLength="30" size="30" name="confirm_new_password"  type="password">						</td>						
						<td style="width: 10px;">&nbsp;</td>
					</TR>									
								
					<TR>
						<td height="35"></td>
					    <td colspan="3"  align="center">
                        <INPUT  class="Button5" style="WIDTH: 80px; HEIGHT: 20px" id="INPUT5" onClick="javascript:Modificar();" type="button" value="Grabar" name="cmb_grabar">
		  <INPUT name="cmd_salir" onClick="javascript:Cancelar();" type="button" id="cmd_salir" value="Cancelar" class="Button5" style="WIDTH: 80px; HEIGHT: 20px">                        </td>
					    <td></td>
					</TR>	
			</TABLE>
		  </TD>
	  </TR>
		
  </TABLE>

  
</FORM>
	  
	  </TD>
      <TD width="4"></TD>
    </TR>
    </TBODY>
</TABLE>
</div>
</BODY>
</HTML>
