<%@ include file="taglibs.jsp" %>
<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD><TITLE>Reportes</TITLE>
 <LINK href="css/PlanSec.css" rel="stylesheet" type="text/css">
 <LINK href="css/main.css" type="text/css" rel="stylesheet">
 <link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />
<script src="js/funciones.js" type="text/javascript"></script>
<SCRIPT type="text/javascript" src="js/latest.js"></SCRIPT>
 <SCRIPT type=text/javascript>
$(document).ready(function() {
	$('tbody tr:even').addClass('alt');
	

	});
</SCRIPT>
<SCRIPT>
function cerrar(){
   
	window.close();
}
</SCRIPT>	

<STYLE type=text/css>

.seleccionado td { background-color:#ddeeff }

.alt {
	BACKGROUND: #F6F6F6
}
.over {
	BACKGROUND: #ddeeff
}
.checked {
	BACKGROUND: #faeaea
}
</STYLE>
</HEAD>
<BODY   bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0">

<form id="form_datos" name="form_datos" method="post" action="" >

  <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0 height="100%">
    <!--DWLayoutTable-->
   
      
      
      <TR> 
      <TD    width="5" >
        <TD height="80%" align="center" valign="top"  style="WIDTH: 100%" >
         <!--DWLayoutTable-->
        <table width="100%" >
                      <!--DWLayoutTable-->
                      <tr valign="middle"> 
                    <td width="964" height="40" align="left" valign="top">
                      <table cellspacing="0px" cellpadding="0" border="0" align="CENTER" width="100%">
                      
	<tr>
		<td width="348" height="55" align="left" valign="middle" class="labelblack">
			
			<IMG src="img/cabeceraconcytec.gif" >					</td>
		<td width="440" align="left" valign="middle" class="labelblack">
			&nbsp;&nbsp;LISTA DE DOCUMENTOS  </td>
		<td width="9%" class="labelblack">
			&nbsp;&nbsp;
            <IMG src="img/Printable-Format.gif" border="0" onClick="javascript:window.print()" title="Imprimir" alt="Imprimir" style="cursor: hand;">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<BR>
			Imprimir&nbsp;&nbsp;&nbsp;&nbsp;		</td>
		<td width="9%" class="labelblack">
			&nbsp;
             <IMG src="img/Delete.gif" border="0" onClick="javascript:window.close()" title="Cerrar" alt="Cerrar" style="cursor: hand;">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<BR>
			Cerrar&nbsp;&nbsp;&nbsp;&nbsp;		</td>
	</tr>
	<tr>
	<td width="300" align="left" valign="middle" class="labelblack">
			&nbsp;&nbsp;Nro de Documentos encontrados : <c:out value='${cantidad_reporte}'/>  </td>
			<TD width="440" align="left" class="caja1">
		&nbsp;&nbsp; 
		</TD>
		<td width="400" class="labelblack">
			&nbsp;&nbsp;</td>
		<td width="9%" class="labelblack">
			&nbsp;&nbsp;</td>
			
	</tr>
</table>                      </td>
                  </tr>
                     
      <tr> 
           <td height="21" align="right" valign="top" class="label" >
          <TABLE  border="0" align="center" cellpadding="0"  width="100%">
   <TBODY>
    <TR align="center" >
     
      <TD    align="center" bgcolor="#006699" class="TextFieldOn36"><font color="#FFFFFF">N&deg; Registro</font></TD>
	  <TD    align="center" bgcolor="#006699" class="TextFieldOn36"><font color="#FFFFFF">N&deg; Expediente</font></TD>
      <TD   align="center" bgcolor="#006699" class="TextFieldOn36"><font color="#FFFFFF">N&deg;  Documento</font></TD>
	  <TD    align="center" bgcolor="#006699" class="TextFieldOn36"><font color="#FFFFFF">Oficina</font></TD>
      <TD    align="center" bgcolor="#006699" class="TextFieldOn36"><font color="#FFFFFF">Origen</font></TD>
      
      <TD   align="center" bgcolor="#006699" class="TextFieldOn36"><font color="#FFFFFF">Destino</font></TD>
      
      <TD    align="center" bgcolor="#006699" class="TextFieldOn36"><font color="#FFFFFF">Estado</font></TD>
      <TD   align="center" bgcolor="#006699" class="TextFieldOn36"><font color="#FFFFFF">Naturaleza</font></TD>
      <TD   align="center" bgcolor="#006699" class="TextFieldOn36"><font color="#FFFFFF">Fecha.Doc&nbsp;/&nbsp;Hora</font></TD>
      <TD align="center"  bgcolor="#006699" class="TextFieldOn36"><font color="#FFFFFF"> Asunto</font> 
      </TD>
	  <TD  align="center"   bgcolor="#006699" class="TextFieldOn36"><font color="#FFFFFF">Ult.Usuario</font></TD>
  </TR>
   					 <c:choose> 
                              
                              <c:when test='${not empty rs_reporte}'> 
                              <c:forEach items='${rs_reporte}' var='pr'> 
	  <TR  class="tablepar" align="center"    >
	   
        <TD align="center" class="caja1"><c:out value='${pr.codigo_documento}'/> </TD>
		 <TD align="center" class="caja1">
		<c:out value='${pr.codigo_expediente}'/>
		</TD>
        <TD align="center" class="caja1">
		<c:out value='${pr.numero_documento}'/>
		
		</TD>
       <TD align="center" class="caja1">
		<c:out value='${pr.codigo_oficina}'/>  
		</TD>
        <TD align="center" class="caja1">
		<c:out value='${pr.oficina_origen}'/>  
		</TD>
        <TD align="center" class="caja1">
		<c:out value='${pr.oficina_deriva}'/> 
		</TD>
        
        <TD align="center" class="caja1">
	<c:out value='${pr.estado_movimiento}'/> 
		
		</TD>
        <TD align="center" class="caja1">
		<c:choose>
        <c:when test="${pr.naturaleza_documento=='I'}">
		Interno
		 </c:when>
		  <c:when test="${pr.naturaleza_documento=='S'}">
		Salida
		 </c:when>
		 <c:when test="${pr.naturaleza_documento=='W'}">
		Interno
		 </c:when>
        <c:otherwise>    
		  Externo
		   </c:otherwise>
    	</c:choose>    
		
		</TD>
        <TD align="center" class="caja1">
		<c:out value='${pr.fecha_movimiento}'/>&nbsp;
		<c:out value='${pr.hora_movimiento}'/>
		</TD>
        <TD align="center" class="caja1"><c:out value='${pr.asunto_documento}'/></TD>
       
		<TD align="center" class="caja1">
		<c:out value='${pr.ultimo_usuario}'/>
		</TD>
      </TR>
      </c:forEach> </c:when>   </c:choose> 
	 
	  </TBODY> 
</TABLE>

  

            </td>
 </tr>
        
          <tr>
            <td height="0"></td>
            </tr>
          </table>
        </TD>
      </TR>
  
  </TABLE>

</FORM>

</BODY></HTML>