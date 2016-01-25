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

<HTML>
<HEAD>
<TITLE></TITLE>
<LINK href="PlanSec.css" type="text/css" rel="stylesheet">
		<link href="css/main.css"   rel="stylesheet" type="text/css"> 
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		 <LINK href="css/bkinHome.css" type=text/css rel=stylesheet>
		<LINK href="css/bkin2.css" type=text/css rel=stylesheet>
		<LINK href="css/gridStyle.css" type=text/css rel=stylesheet>
        <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<SCRIPT type=text/javascript>
	function Cerrar(){
	 var strURL = "CargaMesaPartes.do?operacion=buscar";
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	window.close()
	}
	

function Modificar(){
 
 	
		 if (Validarcampovacio(document.form_datos.usuario.value) == false)
              {              
                    alert("Ingresar Usuario");
                    document.form_datos.usuario.focus();
                    return false;           
              } 
			 if (Validarcampovacio(document.form_datos.clave.value) == false)
              {              
                    alert("Ingresar Clave");
                    document.form_datos.clave.focus();
                    return false;           
              } 
			   
				
	usuario = document.getElementById("usuario").value
	clave = document.getElementById("clave").value
	clavenueva = document.getElementById("clavenueva").value
	confirmarclavenueva = document.getElementById("confirmarclavenueva").value
	
	if(clavenueva!=confirmarclavenueva){
	
					alert("Nueva Contraseña y Confirmar Contraseña diferentes");
                    document.form_datos.clavenueva.focus();
                    return false;           
	
	}
	
	var strURL = "MantUsuarioClave.do?operacion=M";
	
			strURL += "&usuario="+usuario;
			strURL += "&clave="+clave;
			strURL += "&clavenueva="+clavenueva;
			strURL += "&confirmarclavenueva="+confirmarclavenueva;
		
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	
	window.close()
	
	
}


function cambiaDestino(){

  document.forms[0].provincia.selectedIndex=0;
  document.forms[0].provincia.length=1;

}

function HabilitaText()
{
if (document.form_datos.cbo_fecharpta.checked)
{ document.form_datos.fecha_rpta.disabled = false; 
}
else
{document.form_datos.fecha_rpta.disabled = true;

}
}

function SeleccionarCampo(campo, valor){
  for(var i = 0; i < document.forms[0].elements.length; i++){
    if (document.forms[0].elements[i].name==campo){
      for(var j=0; j< document.forms[0].elements[i].length; j++){      
        if (document.forms[0].elements[i].options[j].value==valor){
          document.forms[0].elements[i].options[j].selected=true;
        }
      }
    }    
  }
}

</SCRIPT>
<style type="text/css">
<!--
.Estilo1 {
	color: #FF0000;
	font-size: 12px;
}
-->
</style>
</HEAD>

<BODY bgColor="white" >

<FORM id="form_datos" name="form_datos" action="" method="post">
				
	<TABLE width="100%" CELLSPACING="7px." CELLPADDING="0">
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
					  <td width="15" style="width: 10px;">&nbsp;</td>
					  <td width="195"  height="25"  align="right"  valign="middle" class="label" >Usuario:</td>
						<td width="193" align="center" class="labelsubtitle" style="width: 10px;">&nbsp;</td>
						
          <td width="556" align="left" valign="middle" style="width: 350px;"> 
        <INPUT class="txt" id="usuario" maxLength="30"  readonly size="30" name="usuario"  value="<c:out value='${nombreusuario}'/>">						</td>						
					  <td width="18" style="width: 10px;">&nbsp;</td>
				  </TR>
					
					<TR>
							<td style="width: 10px;">&nbsp;</td>
					  <td align="right" valign="middle" class="label" height="25">Contraseña:</td>
							<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;</td>
					  <td style="width: 350px;" align="left" valign="middle">
         <INPUT class="txt" id="clave" readonly maxLength="15" size="30" name="clave" type="password"  value="<c:out value='${clave}'/>">
                      </td>						
							<td style="width: 10px;">&nbsp;</td>
					</TR>
					
					<TR>
						<td style="width: 10px;">&nbsp;</td>
					  <td align="right" valign="middle"  class="label" height="25">Nueva Contraseña:</td>
						<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;</td>
						<td style="width: 350px;" align="left" valign="middle">																				
					  <INPUT class="txt" id="clavenueva" maxLength="30" size="30" name="clavenueva"  type="password">						</td>						
						<td style="width: 10px;">&nbsp;</td>
					</TR>
					
					<TR>
						<td style="width: 10px;">&nbsp;</td>
					  <td align="right" valign="middle"  class="label" height="25">Confirmar Contraseña:</td>
						<td style="width: 10px;" align="center" class="labelsubtitle">&nbsp;</td>
						<td style="width: 350px;" align="left" valign="middle">																				
					  <INPUT class="txt" id="confirmarclavenueva" maxLength="30" size="30" name="confirmarclavenueva"  type="password">						</td>						
						<td style="width: 10px;">&nbsp;</td>
					</TR>									
								
					<TR>
						<td height="35"></td>
					    <td colspan="3"  align="center">
                        <INPUT  class="Button5" style="WIDTH: 80px; HEIGHT: 20px" id="INPUT5" onClick="javascript:Modificar();" type="button" value="Grabar" name="cmb_grabar">
		  <INPUT name="cmd_salir" onClick="javascript:Cerrar();" type="button" id="cmd_salir" value="Cerrar" class="Button5" style="WIDTH: 80px; HEIGHT: 20px">                        </td>
					    <td></td>
					</TR>	
                    
			</TABLE>
		  </TD>
	  </TR>
		
	</TABLE>

  
</FORM>

</BODY>
</HTML>
<% } %>
