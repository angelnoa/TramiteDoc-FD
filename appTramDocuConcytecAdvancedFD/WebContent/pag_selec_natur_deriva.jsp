<%@ include file="taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<fmt:setBundle basename="ApplicationResources" />
<HTML>
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Seleccione la Naturaleza del Documento</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <LINK href="PlanSec.css" type="text/css" rel="stylesheet"> 
	<link href="css/main.css"   rel="stylesheet" type="text/css"> 
    
    <SCRIPT language="JavaScript" src="js/funciones_varias1.js"></SCRIPT>
    <SCRIPT language="javascript">
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
    document.location.href="Servlet_Seleccion?"+"rb_seleccion="+rb_seleccion
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
<BODY language="JavaScript" bgColor="white" onload="" PSPARAMS="">
<FORM id="frmSeleccion" name="frmSeleccion" action="Servlet_Seleccion" method="post">
    <TABLE borderColor="#e9e9e9" cellSpacing="0" cellPadding="0" width="50%" align="center" border="1" bgColor="#F0F5FB">
        <TBODY>
        <TR class="cabeceratable">
            <TD align="middle" class="TextFieldOn35"><font color="#000000" size="2" ><strong>Seleccione la Naturaleza 
        </strong></font> </TD>
        </TR>
        <TR>
            <TD>
                <TABLE id="TABLE1" width="100%" align="center" border="0" bgColor="#F0F5FB">
                    <TBODY id="TBODY1">
                    <TR id="TR1">
                        <TD id="TD1">&nbsp; </TD>
                    </TR>
                    <TR class="formulario">
                        <TD align="middle">
                            <INPUT id="rb_seleccion" type="radio" value="S" name="rb_seleccion">Salida&nbsp;&nbsp;
                        </TD>
                    </TR>
                    <TR class="formulario" id="TR2">
                        <TD id="TD2" align="middle">
                            <INPUT id="rb_seleccion" type="radio" value="I" name="rb_seleccion">Interno
                        </TD>
                    </TR>
                    <TR>
                        <TD>&nbsp;</TD>
                    </TR>
                    <TR id="TR3">
                        <TD id="TD3" align="middle">
                            <INPUT language="JavaScript" class="Button1" id="cmb_aceptar" onclick="javascript:Validar();" type="button" value="Aceptar" name="cmb_aceptar">
                        </TD>
                    </TR>
                    </TBODY>
                </TABLE>
            </TD>
        </TR>
        </TBODY>
    </TABLE>
</FORM>
</BODY>
</HTML>
