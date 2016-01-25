<%@ include file="taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 

<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Filtro para Derivación</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=windows-1252">
    
	<link href="css/main.css"   rel="stylesheet" type="text/css"> 
	<link href="css/estilo1.css" rel="stylesheet" type="text/css">

</HEAD>
<SCRIPT type=text/javascript>
	function Cerrar(){
	 var strURL = "CargaMesaPartes.do?operacion=buscar";
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	window.close()
	}
	
	

</SCRIPT>
<BODY bgColor="white" PSPARAMS="">
<TABLE borderColor="#e9e9e9" cellSpacing="0" cellPadding="0" width="30%" align="center" border="1">
  <!--DWLayoutTable-->
  <TBODY>
    <TR > 
      <TD width="294" align="middle" class="TextFieldOn35"><font color="#000000" size="2" ><strong>Registro Documento Interno</strong></font></TD>
    </TR>
    <TR> 
      <TD height="46" valign="top"  bgColor="#F0F5FB"> 
        <TABLE id="TABLE1" width="100%" align="center" border="0"  >
          <!--DWLayoutTable-->
          <TBODY id="TBODY1">
            <TR  id="TR1"> 
              <TD width="100%" height="40" align="center" valign="middle" id="TD1"> 
                <A id="A1" href="ActionSeleccion.do?rb_seleccion=W"><font size="2" face="Verdana, Arial, Helvetica, sans-serif">
               Asociar a Documento de Entrada</font></A> </TD>
            </TR>
           
          </TBODY>
        </TABLE></TD>
    </TR>
  </TBODY>
</TABLE>
<br><br><br>
<center>
<INPUT name="cmd_salir" onClick="javascript:Cerrar();" type="button" id="cmd_salir" value="Cerrar" class="Button5" style="WIDTH: 80px; HEIGHT: 20px">
</center>
</BODY>
</HTML>
