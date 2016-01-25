<%@ include file="taglibs.jsp" %>
<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD><TITLE>Oficinas Usuario</TITLE>
<LINK href="css/estilos.css" type="text/css" rel="stylesheet">
<LINK href="css/estilo1.css" type="text/css" rel="stylesheet">
 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">

	<SCRIPT language="Javascript">
 
 function CargaMesaPartes(oficina, persona)
    { 
	    var fecini = new Date()
		var fecfin = new Date()
		var mesini = fecini.getMonth("mm");
		var diaini = fecini.getDate("dd");
		var anoini = fecini.getFullYear();
		var mesfin = fecfin.getMonth("mm");
		var diafin = fecfin.getDate("dd");
		var anofin = fecfin.getFullYear();
   

	  if(mesini< 10)
		{
	 	  mesini="0"+mesini
		}
		
		if(diaini<10)
		{
			diaini="0"+diaini
		}
		
		if(mesfin< 10)
		{
	 	  mesfin="0"+mesfin
		}
		
		if(diafin<10)
		{
			diafin="0"+diafin
		}
		
		
		var fecha_ini=diaini+"/"+mesini+"/"+anoini
		var fecha_fin=diafin+"/"+mesfin+"/"+anofin
		
		
				 
          var strURL = "CargaInicial.do";
			strURL += "?oficina="+oficina;
			strURL += "&persona="+persona;
			strURL += "&naturaleza="+"*";
			strURL += "&estado="+"*";
			strURL += "&fecha_ini="+"*";
			strURL += "&fecha_fin="+"*";
		document.location=strURL   
    }

</SCRIPT>
</HEAD>

<BODY bgColor="white" PSPARAMS=""  bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" >
<table  width="100%" border="0" bgcolor="265ca6" height="38" cellSpacing="0" cellPadding="0">
  <tr>
    <td><jsp:include page="/headTramite.jsp"/> </td>
  </tr>
</table>

<br>
<TABLE align="center" border="1" bordercolor="#999999" cellpadding="0" cellspacing="0" width="50%">
<TR class="cabeceratable" >
  <TD align="center"> Oficinas Asignadas al Usuario</TD>
</TR>
<TR>
<TD align="center" >
<TABLE id="TABLE1" align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
    <TBODY id="TBODY1">
<c:choose> <c:when test='${not empty OficinasUsuario}'> 
                            <c:forEach items='${OficinasUsuario}' var='pr'>
   <TR id="TR1"> 
        <TD  class="TextFieldOn10" id="TD1" align="center"><A href='javascript:CargaMesaPartes("<c:out value='${pr.codigo_oficina}'/>","<c:out value='${pr.codigo_personal}'/>")' ><c:out value='${pr.descripcion_oficina}'/></A>
		</TD>
   </TR>
    </c:forEach> </c:when> </c:choose>
</TBODY>
</TABLE>
</TD>
</TR>
</TABLE>
</BODY>
</HTML>
