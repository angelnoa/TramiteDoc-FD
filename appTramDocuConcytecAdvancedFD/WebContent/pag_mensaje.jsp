<%@ include file="taglibs.jsp" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>pag_Error</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <LINK href="PlanSec.css" type="text/css" rel="stylesheet"> 
	<link href="css/main.css"   rel="stylesheet" type="text/css"> 
    <SCRIPT>
function Cerrar(){
 var strURL = "CargaMesaPartes.do?operacion=buscar";

opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
window.close()
}
    </SCRIPT>
    <META content="PB9.0.0.5507" name="GENERATOR">
</HEAD>
<BODY bgColor="white" leftMargin="0" topMargin="0" rightMargin="0" PSPARAMS="">
<table width="100%" cellpadding="0" cellspacing="0" bgcolor="265ca6"><tr><td><img src="img/header_blue.gif"></td></tr></table>

<DIV id="intermedio" style="HEIGHT: 50px"></DIV>
<DIV id="error" align="center">
    <TABLE borderColor="#e9e9e9" cellSpacing="0" cellPadding="0" width="100%" align="center" border="1">
        <TBODY>
        <TR class="cabeceratable">
            <TD align="middle" bordercolor="e9e9e9">Mensaje del Sistema</TD>
        </TR>
        <TR>
            <TD>
                <TABLE width="80%" align="center">
                    <TBODY>
                    <TR class="formulario">
                        
                <TD align="middle"><font color="#FF0000"><c:out value='${error}'/></font></TD>
                    </TR>
                    <TR>
                        <TD align="middle"><input class="Button1" onClick="javascript:Cerrar();" type="button" value="Cerrar" style="WIDTH: 80px; HEIGHT: 20px"></TD>
                    </TR>
                    </TBODY>
                </TABLE>
          </TD>
        </TR>
        </TBODY>
    </TABLE>
</DIV>
<% 
//session.removeAttribute("error");
%>
</BODY>
</HTML>
