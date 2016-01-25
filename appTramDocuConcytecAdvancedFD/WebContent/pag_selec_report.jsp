<%@ include file="taglibs.jsp" %>
<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD><TITLE>Oficinas Usuario</TITLE>
		<link href="css/main.css"   rel="stylesheet" type="text/css"> 
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		 <LINK href="css/bkinHome.css" type=text/css rel=stylesheet>
		<LINK href="css/bkin2.css" type=text/css rel=stylesheet>
		<LINK href="css/gridStyle.css" type=text/css rel=stylesheet>
         <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" /> 
	
</HEAD>
<SCRIPT type=text/javascript>
function Cerrar(){
	 var strURL = "CargaMesaPartes.do?operacion=buscar";
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	window.close()
	}
</SCRIPT>
<BODY bgColor="white" PSPARAMS=""  bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" >
<table  width="100%" border="0" bgcolor="265ca6" height="38" cellSpacing="0" cellPadding="0">
  <tr>
    <td><jsp:include page="/headTramite.jsp"/> </td>
  </tr>
</table>

<br>
<TABLE align="center" border="1" bordercolor="#999999" cellpadding="0" cellspacing="0" width="50%">
<TR class="cabeceratable" >
  <TD align="center" class="TextFieldOn35"> <font color="#000000" size="2" ><strong>Seleccionar reportes 
        </strong></font></TD>
</TR>
<TR>
<TD align="center" >
<TABLE id="TABLE1" align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
        <!--DWLayoutTable-->
        <TBODY id="TBODY1">
          <TR id="TR1"> 
            <TD width="501" height="20" align="left" valign="middle" background="img/fondoplomo8.jpg"   id="TD1"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
              &nbsp;&nbsp;&nbsp;Generales</font> </TD>
          </TR>
          <TR id="TR1"> 
            <TD height="20" align="center" valign="middle"   id="TD1"><A id="A1" href="ValidaPaginas.do?tipo=reportes&accion=general"><font  color="#0033FF" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
              Naturaleza/Estado/Tipo Documento</font></A> </TD>
          </TR>
          <TR id="TR1"> 
            <TD height="20" align="left" valign="middle"   id="TD1" background="img/fondoplomo8.jpg"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
              &nbsp;&nbsp;&nbsp;Por Criterio</font> </TD>
          </TR>
          <TR id="TR1"> 
            <TD height="20" align="center" valign="middle"   id="TD1"><A id="A1" href="ValidaPaginas.do?tipo=reportes&accion=criteriousuario"><font color="#0033FF" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
              Usuarios/Fechas/Areas</font></A> </TD>
          </TR>
          <TR id="TR1"> 
            <TD height="20" align="center" valign="middle"   id="TD1"><A id="A1" href="ValidaPaginas.do?tipo=reportes&accion=criteriotipodoc"><font color="#0033FF" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
              Tipo Documento/Procedencia/Firma</font></A> </TD>
          </TR>
        </TBODY>
      </TABLE>
</TD>
</TR>
</TABLE>
<br>
<center>
<INPUT name="cmd_salir" onClick="javascript:Cerrar();" type="button" id="cmd_salir" value="Cerrar" class="Button5" style="WIDTH: 80px; HEIGHT: 20px">
</center>
</BODY>
</HTML>
