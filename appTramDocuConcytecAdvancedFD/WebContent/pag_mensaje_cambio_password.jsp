<%@ include file="taglibs.jsp" %>

<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Ventana de Mesa de Partes</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  
        <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />


<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>

  
    
</HEAD>
<BODY bgColor="white" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" >
 <FORM id="form_datos" name="form_datos" method="post" action="">
<TABLE  cellSpacing="0" cellPadding="0" width="100%" border="0"  height="">
  <!--DWLayoutTable-->
    <TBODY>
	<TR >
        <TD height="50" colspan="2" valign="top" bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></TD>
      </TR>
    <TR>
        <TD height="26" colspan="2" class="label" align="middle"  valign="middle"   background="img/fondoplomo8.jpg"></TD>
    </TR>
    <TR >
      <TD width="100%" height="100%" align="center" style="color: red; font-size: 18px; font-weight: normal;font-family : Arial, Helvetica, sans-serif;" ><c:out value="${mensaje_sistema}"/></TD>
      
      
      <TD width="4"></TD>
    </TR>
    </TBODY>
</TABLE>
</FORM>
</BODY>
</HTML>
