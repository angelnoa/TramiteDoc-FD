<%@ include file="taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 

<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Seleccione la Naturaleza del Documento</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <LINK href="PlanSec.css" type="text/css" rel="stylesheet">
	<link href="css/main.css"   rel="stylesheet" type="text/css"> 

 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
 <LINK href="css/bkinHome.css" type=text/css rel=stylesheet>
<LINK href="css/bkin2.css" type=text/css rel=stylesheet>
<LINK href="css/gridStyle.css" type=text/css rel=stylesheet>
<link href="css/estilo1.css" rel="stylesheet" type="text/css">
    <META content="PB9.0.0.5507" name="GENERATOR"> 
    <SCRIPT language="JavaScript" src="funciones_varias1.js"></SCRIPT>
    <SCRIPT language="javascript">
	function Cerrar(){
	 var strURL = "CargaMesaPartes.do?operacion=buscar";
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	window.close()
	}
function EstablecerFoco(){
  document.frmSeleccion.rb_seleccion.focus();
}

function Validar(){
var radiogrp= document.frmSeleccion.rb_seleccion
var flag

flag = "0" 

for (var i=0;i<radiogrp.length;i++)
{

	if(radiogrp[i].checked)
	{
     rb_seleccion  = radiogrp[i].value
	 flag = "1";
    document.location.href="ActionSeleccion.do?"+"rb_seleccion="+rb_seleccion
	  break;
	 }
 }
 
 if(flag=="0")
 {
 alert("Seleccione la naturaleza")
 return
 }
  
}
    </SCRIPT>
</HEAD>
<BODY language="JavaScript" bgColor="white" onLoad="" PSPARAMS="">
<FORM id="frmSeleccion" name="frmSeleccion" action="" method="post">
<br>
  <TABLE align="center" width="50%" border="1s" bordercolor="#E9E9E9" cellpadding="0" cellspacing="0" bgColor="#F0F5FB">
  <TR class="TextFieldOn35"><TD align="center">Seleccione la Naturaleza </TD></TR>
   <TR> <TD>
    <TABLE width="100%" id="TABLE1" align="center" border="0" bgColor="#F0F5FB">
          <!--DWLayoutTable-->
          <TBODY id="TBODY1">
            <TR id="TR1"> 
              <TD width="477" height="20" id="TD1">&nbsp; </TD>
              <TD width="6" id="TD1">&nbsp;</TD>
            </TR>
            <TR class="formulario"> 
              <TD colspan="2" align="center"> <INPUT id="rb_seleccion" type="radio" value="E" name="rb_seleccion"  >
                Entrada </TD>
            </TR>
          
            <TR class="formulario" id="TR2"> 
              <TD height="16" colspan="2" align="center" valign="top" id="TD2"> 
                <INPUT id="rb_seleccion" type="radio" value="I" name="rb_seleccion">
                Interno </TD>
            </TR>
            <TR class="formulario" id="TR2">
              <TD height="20"></TD>
              <TD></TD>
            </TR>
            <TR id="TR3"> 
              <TD colspan="2" align="center" id="TD3"> <INPUT class="Button5" style="WIDTH: 82px; HEIGHT: 17px"  language="JavaScript" id="cmb_aceptar" onClick="javascript:Validar();" type="button" value="Aceptar" name="cmb_aceptar"> 
			   <INPUT class="Button5" onClick="javascript:Cerrar();" type="button" value="Cerrar" style="WIDTH: 82px; HEIGHT: 17px">
              </TD>
            </TR>
          </TBODY>
        </TABLE>
	</TD>
	</TR>
</TABLE>
 
</FORM>
</BODY>
</HTML>
