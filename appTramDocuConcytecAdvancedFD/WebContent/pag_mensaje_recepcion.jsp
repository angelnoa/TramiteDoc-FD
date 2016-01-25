<%@ include file="taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT> 
<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
<SCRIPT language="JavaScript">
function CerrarMensaje(){
 
window.close();
}

function  ImprimirReporte(){
	//	alert('imprimir');
	
	var strURL = 'generate_report.do';	
	winPopup = window.open(strURL,"","scrollbars,HEIGHT=540,WIDTH=700")
	
	
}

function VerHojaRuta(codigo_documento,codigo_expediente){
	
	//alert(pcodigo_documento);
	//alert(pcodigo_expediente);
	
			 var strURL="ValidaPaginas.do?tipo=verhojaruta";
				//strURL+="&codigo_recepcion="+codigo_recepcion;
				strURL+="&codigo_documento="+codigo_documento;
				strURL+="&codigo_expediente="+codigo_expediente;
				//window.location.href=strURL
				//alert(strURL);
				window.open(strURL,"","HEIGHT=440,WIDTH=770,scrollbars=yes");

	}

</SCRIPT>

<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Mensaje de Recepci&oacute;n Documento</TITLE>
    <meta http-equiv="Expires" content="0">
<meta http-equiv="Last-Modified" content="0">
<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
<meta http-equiv="Pragma" content="no-cache">
    <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <META content="PB9.0.0.5507" name="GENERATOR">
    <LINK href="css/PlanSec.css" rel="stylesheet" type="text/css">
	<link href="css/main.css"   rel="stylesheet" type="text/css"> 
	<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
	 <LINK href="css/bkinHome.css" type=text/css rel=stylesheet>
	<LINK href="css/bkin2.css" type=text/css rel=stylesheet>
	<LINK href="css/gridStyle.css" type=text/css rel=stylesheet>
	 <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
</HEAD>
<BODY >
<FORM id="form_datos" name="form_datos" action="" method="post">
    <TABLE align="center" width="100%" border="1" bordercolor="#E9E9E9" cellpadding="0" cellspacing="0">
	 <TR>
	   <TD align="center" class="TextFieldOn35" colspan="2" bgcolor="e9e9e9"><font color="#000000" size="2" ><strong><c:out value='${titulo}'/></strong></font></TD>
	 </TR>
	<TR>
	<TD>
    <TABLE width="100%" align="center" border="0" bgColor="#F0F5FB">
          <!--DWLayoutTable-->
          <TBODY>
            <TR> 
              <TD width="95" height="20">&nbsp;</TD>
              <TD colspan="2" align="left" valign="bottom">
			   <font color="#FF0000" size="2">
			   <c:out value='${mensaje}'/>
			   </font>
			  
   			  </TD>
            <TD width="95">&nbsp;</TD>
            </TR>
            <TR>
              <TD height="20">&nbsp;</TD>
              <TD width="149">&nbsp;</TD>
              <TD width="634" align="left" valign="middle"> <font color="#FF0000" size="2"><c:out value='${mensaje_reg}'/>
		      </font></TD>
              <TD>&nbsp;</TD>
            </TR>
            
            <logic:present name="mensaje_clave" >
              <TR>
              <TD height="20">&nbsp;</TD>
              <TD width="149">&nbsp;</TD>
              <TD width="634" align="left" valign="middle"> <font color="#FF0000" size="2"><c:out value='${mensaje_clave}'/>
		      </font></TD>
              <TD>&nbsp;</TD>
            </TR> 
     		</logic:present>
            
              <TR>
              <TD height="20">&nbsp;</TD>
              <TD width="149">&nbsp;</TD>
              <TD width="634" align="left" valign="middle"> <font color="#FF0000" size="2"><c:out value='${mensaje_exp}'/>
		      </font></TD>
              <TD>&nbsp;</TD>
            </TR>
          </TBODY>
        </TABLE>
	</TD>
	</TR>
	<TR>
	   <TD align="center"  colspan="2" ></TD>
	 </TR>
	</TABLE>
	<center>
	<INPUT name="cmd_salir" onClick="javascript:CerrarMensaje();" type="button" id="cmd_salir" value="Cerrar" class="boton" style="WIDTH: 80px; HEIGHT: 20px">
	 <logic:present name="mensaje_clave" >
              <INPUT name="cmd_imprimirReporte" onClick="javascript:ImprimirReporte();" type="button" id="cmd_imprimirReporte" value="Imprimir" class="boton" style="WIDTH: 80px; HEIGHT: 20px">
              <INPUT name="cmd_cmd_imprimirhruta" onClick="javascript:VerHojaRuta('<c:out value='${vcodigo_documento}'/>','<c:out value='${vcodigo_expediente}'/>');" type="button" id="cmd_imprimirhruta" value="Hoja de Ruta" class="boton" style="WIDTH: 100px; HEIGHT: 20px">  
     </logic:present>
	 
</center>
</FORM>
<P>&nbsp;</P>
</BODY>
</HTML>
