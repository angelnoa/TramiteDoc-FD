<%@ include file="taglibs.jsp" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.ResultSet" contentType="text/html; charset=UTF-8" %>
<%@ page import="tramitedoc.concytec.util.*" %>
<%@ page import="java.text.*" %>
<fmt:setBundle basename="ApplicationResources" />
<HTML>
<HEAD>
    <TITLE>Recorrido de un Documento</TITLE>
	<link rel="stylesheet" href="css/avatec.css" type="text/css" />
	<link rel="stylesheet" href="css/table.css" type="text/css" />
</HEAD>
 <SCRIPT type="text/javascript" src="js/latest.js"></SCRIPT>
  <SCRIPT type=text/javascript>
$(document).ready(function() {
	$('tbody tr:even').addClass('alt');
	

	});
	
</SCRIPT>
<SCRIPT>
function Cerrar(){
 
window.history.back()
//window.history.back()
}
</SCRIPT>
<STYLE type=text/css>
.seleccionado td { background-color:#ddeeff }
TABLE {
	FONT-SIZE: 1em; WIDTH: 100%; BORDER-COLLAPSE: collapse; border-spacing: 0
}
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
<BODY bgColor="white" leftMargin="0" topMargin="0" rightMargin="0" PSPARAMS="">
<TABLE align="center" width="100%" border="1" cellpadding="0" cellspacing="0" >

 <TR >
    <TD align="center" height="25">
	<table cellspacing="0px" cellpadding="0" border="0" align="CENTER" width="100%">
	
	<tr>
		<td width="10%" align="left" valign="middle" class="labelblack">
			&nbsp;
			<IMG   src="img/concyteclogo.jpg" border="0" width="60" >
			&nbsp;&nbsp;
			<!-- bean:message key="general.kycprinttitle.label" /-->		</td>
		<td class="labelblack" valign="middle" align="center" colspan="2">
			REPORTE SEGUIMIENTO DEL DOCUMENTO </td>
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
</table>
	</TD>
 </TR>
 <TR>
  <TD>

<TABLE width="100%" align="center" border="0">
  <!--DWLayoutTable-->
    <TBODY>
    <TR  align="center">
    	<TD width="95%" height="15" align="center" valign="top">
		<table width="95%" cellpadding="0" cellspacing="0">
		<TR> 
            <TD width="53" height="27" class="label">Codigo :</TD>
            <TD width="269" align="left" ><c:out value='${detalleConsulta.codigo_documento}'/></TD>
            <TD width="70" class="label">Documento :</TD>
            <TD width="268" align="left" ><c:out value='${detalleConsulta.descripcion_tipo}'/><c:out value='${detalleConsulta.numero_documento}'/></TD>
            <TD width="65" class="label">Naturaleza :</TD>
            <TD width="52" align="left" valign="middle" > <c:choose> <c:when test='${detalleConsulta.naturaleza_documento ==  "I"}'> 
              Interno </c:when> <c:when test='${detalleConsulta.naturaleza_documento ==  "S"}'> 
              Salida </c:when> <c:otherwise> Entrada </c:otherwise> </c:choose> 
            </TD>
            <TD width="74" class="label">Fecha movimiento:</TD>
            <TD width="103" align="left" valign="middle" ><c:out value='${detalleConsulta.fecha_movimiento}'/></TD>
          </TR>
          <TR> 
            <TD height="26" class="label">Origen :</TD>
            <TD > <c:choose> <c:when test='${detalleConsulta.oficina_origen ==  "1"}'> 
              MESA DE PARTES </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "2"}'> 
              SECRETARIA GENERAL </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "3"}'> 
              PRESIDENCIA </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "4"}'> 
              OFICINA GENERAL DE ADMINISTRACION </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "5"}'> 
              DIRECCION SISTEMAS INFORMACION Y COMUNICACION </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "6"}'> 
              OFICINA ASESORIA JURIDICA </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "7"}'> 
              OFICINA PLANEAMIENTO Y PLANIFICACION </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "8"}'> 
              FONDECYT </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "9"}'> 
              OFICINA CONTROL INTERNO </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "10"}'> 
              DIRECCION POLITICAS Y PLANES </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "11"}'> 
              DIRECCION ARTICULACION Y GESTION </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "12"}'> 
              DIRECCION CIENCIA Y TECNOLOGIA </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "13"}'> 
              DIRECCION PROSPECTIVA E INNOVACION TECNOLOGICA </c:when> <c:when test='${detalleConsulta.oficina_origen ==  "14"}'> 
              PRESIDENCIA </c:when> <c:otherwise> OTROS </c:otherwise> </c:choose> 
            </TD>
            <TD class="label">Destino :</TD>
            <TD > <c:choose> <c:when test='${detalleConsulta.oficina_deriva ==  "1"}'> 
              MESA DE PARTES </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "2"}'> 
              SECRETARIA GENERAL </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "3"}'> 
              PRESIDENCIA </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "4"}'> 
              OFICINA GENERAL DE ADMINISTRACION </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "5"}'> 
              DIRECCION SISTEMAS INFORMACION Y COMUNICACION </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "6"}'> 
              OFICINA ASESORIA JURIDICA </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "7"}'> 
              OFICINA PLANEAMIENTO Y PLANIFICACION </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "8"}'> 
              FONDECYT </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "9"}'> 
              OFICINA CONTROL INTERNO </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "10"}'> 
              DIRECCION POLITICAS Y PLANES </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "11"}'> 
              DIRECCION ARTICULACION Y GESTION </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "12"}'> 
              DIRECCION CIENCIA Y TECNOLOGIA </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "13"}'> 
              DIRECCION PROSPECTIVA E INNOVACION TECNOLOGICA </c:when> <c:when test='${detalleConsulta.oficina_deriva ==  "14"}'> 
              PRESIDENCIA </c:when> <c:otherwise> ---- </c:otherwise> </c:choose> 
            </TD>
            <TD class="tablepar"></TD>
            <TD class="tablepar"></TD>
            <TD class="label">Estado :</TD>
            <TD > <c:choose> <c:when test='${detalleConsulta.estado_movimiento ==  "1"}'> 
              Registrado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "2"}'> 
              <FONT color="#FF0000"> Por Recibir </FONT> </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "3"}'> 
              <FONT  color="#00CC66"> Recibido </FONT> </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "4"}'> 
              Archivado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "5"}'> 
              Derivado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "6"}'> 
              Preliquidado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "7"}'> 
              Liquidado </c:when> <c:otherwise> Entregado </c:otherwise> </c:choose> 
            </TD>
          </TR>
          <TR> 
            <TD height="26" class="label">Motivo :</TD>
            <TD align="left" valign="middle" class="tablepar"> <c:choose> <c:when test='${detalleConsulta.codigo_motivo ==  "1"}'> 
              Copia </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "2"}'> 
              Ref. Doc. </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "3"}'> 
              Conocimiento </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "4"}'> 
              Informe </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "5"}'> 
              Devolucion </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "6"}'> 
              Archivo </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "7"}'> 
              Atencion </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "8"}'> 
              Coordinacion </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "9"}'> 
              Preparar Rpta. </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "10"}'> 
              Liquidacion </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "11"}'> 
              Proyectar Resol. </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "12"}'> 
              Viciacion </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "13"}'> 
              Notificacion </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "14"}'> 
              Para tramitar </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "15"}'> 
              Revision y Conformidad </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "16"}'> 
              Opinion </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "17"}'> 
              Consideracion </c:when> <c:when test='${detalleConsulta.codigo_motivo ==  "18"}'> 
              Correccion </c:when> <c:otherwise> ---- </c:otherwise> </c:choose> 
            </TD>
            <TD class="label">Obs. de la Deriv :</TD>
            <TD  align="left"> <c:out value='${detalleConsulta.observa_movimiento}'/> 
            </TD>
            <TD class="tablepar"></TD>
            <TD class="tablepar"></TD>
            <TD class="tablepar"></TD>
            <TD class="tablepar"></TD>
          </TR>
          <TR> 
            <TD height="33" class="label">Fecha Registro :</TD>
            <TD align="left" valign="middle" class="tablepar"> <c:out value='${detalleConsulta.fecha_registro}'/> 
            </TD>
            <TD class="label">Hora Registro :</TD>
            <TD align="left" valign="middle" class="tablepar"> <c:out value='${detalleConsulta.hora_movimiento}'/> 
            </TD>
            <TD class="label">N&deg; folios :</TD>
            <TD align="left" valign="middle" class="tablepar"> <c:out value='${detalleConsulta.folios_documento}'/> 
            </TD>
            <TD class="label">Ultimo Usuario :</TD>
            <TD align="left" valign="middle" class="tablepar"> <c:out value='${detalleConsulta.ultimo_usuario}'/> 
            </TD>
          </TR>
          <TR> 
            <TD height="34" class="label">Asunto :</TD>
            <TD class="tablepar" colspan="7"> <c:out value='${detalleConsulta.asunto_documento}'/> 
            </TD>
          </TR>
          <TR> 
            <TD height="33" class="label">Obs. del Doc :</TD>
            <TD class="tablepar" colspan="7"> <c:out value='${detalleConsulta.observa_documento}'/> 
            </TD>
          </TR>
		</table></TD>
        </TR>
  
  					<c:choose>           
					  <c:when test='${not empty rs_recorrido}'> 
		  <c:forEach items='${rs_recorrido}' var='pr'>	 </c:forEach> </c:when>   </c:choose> 
    </TBODY>
</TABLE>

</TD>
</TR>
<TR>
  <TD>

<TABLE width="100%" align="center" border="0" bgColor="#F0F5FB">
  <!--DWLayoutTable-->
    <TBODY>
	  <TR  align="center" >
    	<TD height="21" colspan="4" align="center" valign="top" class="TitleScreen1" >Origen</TD>
        <TD colspan="7" align="center" valign="top" class="TitleScreen2">Destino</TD>
        </TR>
    <TR class="cabeceratable" align="center" >
    	<TD width="39" height="28" align="center">Orden</TD>
        <TD width="196" align="center" valign="middle">Area</TD>
        <TD width="40" align="center" valign="middle">Desc. Tipo</TD>
        <TD width="73" valign="top">N&uacute;m. Documento</TD>
        <TD width="110" align="center" valign="middle">Area</TD>
        <TD width="75" align="center">Fecha Recepci&oacute;n</TD>
        <TD width="77" align="center" valign="middle">Asunto Doc.</TD>
		 <TD width="88" align="center" valign="middle">Estado</TD>
		 <TD width="50" align="center" valign="middle">Usuario</TD>
		 <TD width="54" align="center" valign="top">N&deg; Dias Bandeja</TD>
        <TD width="126" align="center" valign="middle">Motivo</TD>
    </TR>

	
  					<c:choose>           
					  <c:when test='${not empty rs_recorrido}'> 
					  <c:forEach items='${rs_recorrido}' var='pr'> 
     <TR  class="tablepar" align="center">
	    <TD height="50" align="center"><STRONG><c:out value='${pr.contador}'/></STRONG> </TD>
        <TD align="center" valign="middle">
        <FONT color="#993300">
		<c:choose>
        <c:when test='${pr.oficina_origen ==  "1"}'>
		MESA DE PARTES		 </c:when>
		  <c:when test='${pr.oficina_origen ==  "2"}'>
		 SECRETARIA GENERAL		 </c:when>
		  <c:when test='${pr.oficina_origen ==  "3"}'>
		 PRESIDENCIA		 </c:when>
          <c:when test='${pr.oficina_origen ==  "4"}'>
		 OFICINA GENERAL DE ADMINISTRACION		 </c:when>
          <c:when test='${pr.oficina_origen ==  "5"}'>
		 DIRECCION SISTEMAS INFORMACION Y COMUNICACION		 </c:when>
          <c:when test='${pr.oficina_origen ==  "6"}'>
		 OFICINA ASESORIA JURIDICA		 </c:when>
         <c:when test='${pr.oficina_origen ==  "7"}'>
		 OFICINA PLANEAMIENTO Y PLANIFICACION		 </c:when>
         <c:when test='${pr.oficina_origen ==  "8"}'>
		FONDECYT		 </c:when>
         <c:when test='${pr.oficina_origen ==  "9"}'>
		 OFICINA CONTROL INTERNO		 </c:when>
         <c:when test='${pr.oficina_origen ==  "10"}'>
		 DIRECCION POLITICAS Y PLANES		 </c:when>
         <c:when test='${pr.oficina_origen ==  "11"}'>
		 DIRECCION ARTICULACION Y GESTION		 </c:when>
         <c:when test='${pr.oficina_origen ==  "12"}'>
		 DIRECCION CIENCIA Y TECNOLOGIA		 </c:when>
         <c:when test='${pr.oficina_origen ==  "13"}'>
		 DIRECCION PROSPECTIVA E INNOVACION TECNOLOGICA		 </c:when>
         <c:when test='${pr.oficina_origen ==  "14"}'>
		 PRESIDENCIA		 </c:when>
        <c:otherwise>    
		OTROS		   </c:otherwise>
		</c:choose>    
		</FONT>		   	</TD>
        <TD align="center" valign="middle">
		  <c:out value='${pr.descripcion_tipo}'/>		  		</TD>
        <TD align="center" valign="middle"><c:out value='${pr.numero_documento}'/></TD>
        <TD align="center" valign="middle">
		<FONT color="#993300">
		<c:choose>
        <c:when test='${pr.oficina_deriva ==  "1"}'>
		MESA DE PARTES		 </c:when>
		  <c:when test='${pr.oficina_deriva ==  "2"}'>
		 SECRETARIA GENERAL		 </c:when>
		  <c:when test='${pr.oficina_deriva ==  "3"}'>
		 PRESIDENCIA		 </c:when>
          <c:when test='${pr.oficina_deriva ==  "4"}'>
		 OFICINA GENERAL DE ADMINISTRACION		 </c:when>
          <c:when test='${pr.oficina_deriva ==  "5"}'>
		 DIRECCION SISTEMAS INFORMACION Y COMUNICACION		 </c:when>
          <c:when test='${pr.oficina_deriva ==  "6"}'>
		 OFICINA ASESORIA JURIDICA		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "7"}'>
		 OFICINA PLANEAMIENTO Y PLANIFICACION		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "8"}'>
		FONDECYT		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "9"}'>
		 OFICINA CONTROL INTERNO		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "10"}'>
		 DIRECCION POLITICAS Y PLANES		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "11"}'>
		 DIRECCION ARTICULACION Y GESTION		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "12"}'>
		 DIRECCION CIENCIA Y TECNOLOGIA		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "13"}'>
		 DIRECCION PROSPECTIVA E INNOVACION TECNOLOGICA		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "14"}'>
		 PRESIDENCIA		 </c:when>
        <c:otherwise>    
		----		   </c:otherwise>
    </c:choose>	
	</FONT>	
		</TD>
        <TD align="center"><c:out value='${pr.fecha_movimiento}'/></TD>
		<TD align="center" valign="middle"><c:out value='${pr.asunto_documento}'/></TD>
        <TD align="center" valign="middle"><c:choose>
        <c:when test='${pr.estado_movimiento ==  "1"}'>
		Registrado		 </c:when>
		  <c:when test='${pr.estado_movimiento ==  "2"}'>
		<FONT color="#FF0000"> Por Recibir </FONT>		 </c:when>
		  <c:when test='${pr.estado_movimiento ==  "3"}'>
		  <FONT  color="#00CC66">  Recibido </FONT>		 </c:when>
		  <c:when test='${pr.estado_movimiento ==  "4"}'>
		 Archivado		 </c:when>
		  <c:when test='${pr.estado_movimiento ==  "5"}'>
		 Derivado		 </c:when>
		  <c:when test='${pr.estado_movimiento ==  "6"}'>
		 Preliquidado		 </c:when>
		  <c:when test='${pr.estado_movimiento ==  "7"}'>
		 Liquidado		 </c:when>
        <c:otherwise>    
		Entregado		   </c:otherwise>
    </c:choose>	</TD>
        <TD align="center" valign="middle"><c:out value='${pr.ultimo_usuario}'/></TD>
        <TD align="center" valign="middle">
		  <c:out value='${dias_bandeja}'/>		</TD>
        <TD align="center" valign="middle">
		  <c:choose>
		    <c:when test='${pr.codigo_motivo ==  "1"}'>
		      Copia          </c:when>
		    <c:when test='${pr.codigo_motivo ==  "2"}'>
		      Ref. Doc.		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "3"}'>
		      Conocimiento		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "4"}'>
		      Informe		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "5"}'>
		      Devolucion		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "6"}'>
		      Archivo		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "7"}'>
		      Atencion		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "8"}'>
		      Coordinacion		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "9"}'>
		      Preparar Rpta.		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "10"}'>
		      Liquidacion		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "11"}'>
		      Proyectar Resol.		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "12"}'>
		      Viciacion		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "13"}'>
		      Notificacion		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "14"}'>
		      Para tramitar		 </c:when>
		    
		    <c:when test='${pr.codigo_motivo ==  "15"}'>
		      Revision y Conformidad		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "16"}'>
		      Opinion		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "17"}'>
		      Consideracion		 </c:when>
		    <c:when test='${pr.codigo_motivo ==  "18"}'>
		      Correccion		 </c:when>
		    <c:otherwise>    
		      ----		   </c:otherwise>
		          </c:choose>		</TD>
    </TR>
	 </c:forEach> </c:when>   </c:choose> 
    </TBODY>
</TABLE>

</TD>
</TR>
</TABLE>
</BODY>
</HTML>
