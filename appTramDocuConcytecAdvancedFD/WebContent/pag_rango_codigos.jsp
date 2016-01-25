<%@ include file="taglibs.jsp" %>
<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>pag_rango_codigos</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <LINK href="PlanSec.css" type="text/css" rel="stylesheet"> 
	<link href="css/main.css"   rel="stylesheet" type="text/css"> 
    <META content="PB9.0.0.5507" name="GENERATOR">
</HEAD>
<BODY bottomMargin="0"  leftMargin="0" topMargin="0" rightMargin="0" marginwidth="0" PSPARAMS="" bgColor="#F0F5FB" >
<TABLE cellSpacing="0" cellPadding="0" align="left" border="0" width="80%" bgColor="#F0F5FB">
  <!--DWLayoutTable-->
  <TBODY>
    <TR class="formulario">
     
      <TD width="111" height="21" align="right" valign="middle" class="formulario">Desde :</TD>
	  <TD width="173" align="left">&nbsp;&nbsp;
  <SELECT language="JavaScript" class="combo" id="cbodesde" style="WIDTH: 130px; HEIGHT: 21px" name="cbodesde">
                <OPTION value="00" selected>
                    &lt;&lt; Seleccionar &gt;&gt;                </OPTION>
               <c:choose> <c:when test='${not empty rs_rangocodigos}'> <c:forEach items='${rs_rangocodigos}' var='pa'>	
               <option value=<c:out value='${pa.codigo_documento}'/>> 
                    <c:out value='${pa.codigo_documento}'/> </option> </c:forEach> 
                    </c:when> </c:choose>
            </SELECT>        </TD>
        
      <TD width="92" align="right" valign="middle" class="formulario">Hasta :</TD>
	  <TD width="181" align="left">&nbsp;&nbsp;
       <select language="JavaScript" class="combo" id="cbohasta" style="WIDTH: 130px; HEIGHT: 21px" name="cbohasta">
          <OPTION value="00" selected>
                    &lt;&lt; Seleccionar &gt;&gt;   </OPTION>
               <c:choose> <c:when test='${not empty rs_rangocodigos}'> <c:forEach items='${rs_rangocodigos}' var='pa'>	
               <option value=<c:out value='${pa.codigo_documento}'/>> 
                    <c:out value='${pa.codigo_documento}'/> </option> </c:forEach> 
                    </c:when> </c:choose>
        </select></TD>
    </TR>
    </TBODY>
</TABLE>
</BODY>
</HTML>
