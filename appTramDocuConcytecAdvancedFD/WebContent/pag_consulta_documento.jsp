<%@ include file="taglibs.jsp" %>
<% 
//Verifica que exista una sesion
String nombreusuario = String.valueOf(session.getAttribute("nombreusuario"));
//System.out.println("el nombre de usuario es.."+nombreusuario);
if (nombreusuario==null || nombreusuario.equals("null")){
%> 
<SCRIPT language="Javascript">
parent.document.location ="pag_expiracion.jsp";
</SCRIPT>
<% 
}
else {
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Consulta de Documento</TITLE>
     <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <LINK href="css/PlanSec.css" type="text/css" rel="stylesheet">
		<link href="css/main.css"   rel="stylesheet" type="text/css"> 
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		 <LINK href="css/bkinHome.css" type=text/css rel=stylesheet>
		<LINK href="css/bkin2.css" type=text/css rel=stylesheet>
		<LINK href="css/gridStyle.css" type=text/css rel=stylesheet> 
</HEAD>
<BODY PSPARAMS="">
<TABLE align="center" width="100%" border="1" cellpadding="0" cellspacing="0" borderColor="#e9e9e9">
<TR class="TextFieldOn35"><TD align="center"><font color="#000000" size="2" ><strong>DETALLE DEL DOCUMENTO</strong></font></TD></TR>
<TR>
    <TD height="234"> 
      <TABLE align="center" width="100%" border="0">
    <TBODY>
   
        <TR>
            <TD height="27" class="tableimpar">Codigo :</TD>
		 <TD class="tablepar" align="center"><c:out value='${detalleConsulta.codigo_documento}'/></TD>
		 <TD class="tableimpar">Documento :</TD>
		 <TD class="tablepar" align="center"><c:out value='${detalleConsulta.descripcion_tipo}'/><c:out value='${detalleConsulta.numero_documento}'/></TD>
		 <TD class="tableimpar">Naturaleza :</TD>
		 <TD class="tablepar" align="center">
		 <c:choose>
        <c:when test='${detalleConsulta.naturaleza_documento ==  "I"}'>
		Interno
		 </c:when>
		  <c:when test='${detalleConsulta.naturaleza_documento ==  "S"}'>
		Salida
		 </c:when>
        <c:otherwise>    
		  Entrada
		   </c:otherwise>
    </c:choose>    
			</TD>
		 <TD class="tableimpar">Fecha movimiento:</TD>
		 <TD class="tablepar" align="center"><c:out value='${detalleConsulta.fecha_movimiento}'/></TD>
        </TR>
		<TR>
		     
            <TD height="26" class="tableimpar">Origen :</TD>
			 <TD class="tablepar">
			  
		<c:choose>
        <c:when test='${detalleConsulta.oficina_origen ==  "1"}'>
		MESA DE PARTES
		 </c:when>
		  <c:when test='${detalleConsulta.oficina_origen ==  "2"}'>
		 SECRETARIA GENERAL
		 </c:when>
		  <c:when test='${detalleConsulta.oficina_origen ==  "3"}'>
		 PRESIDENCIA
		 </c:when>
          <c:when test='${detalleConsulta.oficina_origen ==  "4"}'>
		 OFICINA GENERAL DE ADMINISTRACION
		 </c:when>
          <c:when test='${detalleConsulta.oficina_origen ==  "5"}'>
		 DIRECCION SISTEMAS INFORMACION Y COMUNICACION
		 </c:when>
          <c:when test='${detalleConsulta.oficina_origen ==  "6"}'>
		 OFICINA ASESORIA JURIDICA
		 </c:when>
         <c:when test='${detalleConsulta.oficina_origen ==  "7"}'>
		 OFICINA PLANEAMIENTO Y PLANIFICACION
		 </c:when>
         <c:when test='${detalleConsulta.oficina_origen ==  "8"}'>
		FONDECYT
		 </c:when>
         <c:when test='${detalleConsulta.oficina_origen ==  "9"}'>
		 OFICINA CONTROL INTERNO
		 </c:when>
         <c:when test='${detalleConsulta.oficina_origen ==  "10"}'>
		 DIRECCION POLITICAS Y PLANES
		 </c:when>
         <c:when test='${detalleConsulta.oficina_origen ==  "11"}'>
		 DIRECCION ARTICULACION Y GESTION
		 </c:when>
         <c:when test='${detalleConsulta.oficina_origen ==  "12"}'>
		 DIRECCION CIENCIA Y TECNOLOGIA
		 </c:when>
         <c:when test='${detalleConsulta.oficina_origen ==  "13"}'>
		 DIRECCION PROSPECTIVA E INNOVACION TECNOLOGICA
		 </c:when>
         <c:when test='${detalleConsulta.oficina_origen ==  "14"}'>
		 PRESIDENCIA
		 </c:when>
        <c:otherwise>    
		OTROS
		   </c:otherwise>
    </c:choose>   

			 </TD>
			 <TD class="tableimpar">Destino :</TD>
			 <TD class="tablepar">
			
		
			<c:choose>
        <c:when test='${detalleConsulta.oficina_deriva ==  "1"}'>
		MESA DE PARTES
		 </c:when>
		  <c:when test='${detalleConsulta.oficina_deriva ==  "2"}'>
		 SECRETARIA GENERAL
		 </c:when>
		  <c:when test='${detalleConsulta.oficina_deriva ==  "3"}'>
		 PRESIDENCIA
		 </c:when>
          <c:when test='${detalleConsulta.oficina_deriva ==  "4"}'>
		 OFICINA GENERAL DE ADMINISTRACION
		 </c:when>
          <c:when test='${detalleConsulta.oficina_deriva ==  "5"}'>
		 DIRECCION SISTEMAS INFORMACION Y COMUNICACION
		 </c:when>
          <c:when test='${detalleConsulta.oficina_deriva ==  "6"}'>
		 OFICINA ASESORIA JURIDICA
		 </c:when>
         <c:when test='${detalleConsulta.oficina_deriva ==  "7"}'>
		 OFICINA PLANEAMIENTO Y PLANIFICACION
		 </c:when>
         <c:when test='${detalleConsulta.oficina_deriva ==  "8"}'>
		FONDECYT
		 </c:when>
         <c:when test='${detalleConsulta.oficina_deriva ==  "9"}'>
		 OFICINA CONTROL INTERNO
		 </c:when>
         <c:when test='${detalleConsulta.oficina_deriva ==  "10"}'>
		 DIRECCION POLITICAS Y PLANES
		 </c:when>
         <c:when test='${detalleConsulta.oficina_deriva ==  "11"}'>
		 DIRECCION ARTICULACION Y GESTION
		 </c:when>
         <c:when test='${detalleConsulta.oficina_deriva ==  "12"}'>
		 DIRECCION CIENCIA Y TECNOLOGIA
		 </c:when>
         <c:when test='${detalleConsulta.oficina_deriva ==  "13"}'>
		 DIRECCION PROSPECTIVA E INNOVACION TECNOLOGICA
		 </c:when>
         <c:when test='${detalleConsulta.oficina_deriva ==  "14"}'>
		 PRESIDENCIA
		 </c:when>
        <c:otherwise>    
		----
		   </c:otherwise>
    </c:choose>  
	
	
			 </TD>
			 <TD class="tablepar"></TD>
			 <TD class="tablepar"></TD>
			 <TD class="tableimpar">Estado :</TD>
			 <TD class="tablepar">
			<c:choose>
        <c:when test='${detalleConsulta.estado_movimiento ==  "1"}'>
		Registrado
		 </c:when>
		  <c:when test='${detalleConsulta.estado_movimiento ==  "2"}'>
		<FONT color="#FF0000"> Pendiente </FONT>
		 </c:when>
		  <c:when test='${detalleConsulta.estado_movimiento ==  "3"}'>
		  <FONT  color="#00CC66">  Recibido </FONT>
		 </c:when>
		  <c:when test='${detalleConsulta.estado_movimiento ==  "4"}'>
		 Archivado
		 </c:when>
		  <c:when test='${detalleConsulta.estado_movimiento ==  "5"}'>
		 Derivado
		 </c:when>
		  <c:when test='${detalleConsulta.estado_movimiento ==  "6"}'>
		 Preliquidado
		 </c:when>
		  <c:when test='${detalleConsulta.estado_movimiento ==  "7"}'>
		 Liquidado
		 </c:when>
        <c:otherwise>    
		Entregado
		   </c:otherwise>
    </c:choose>    
			 </TD>
		</TR>
		<TR>
		     
            <TD height="26" class="tableimpar">Motivo :</TD>
			 <TD class="tablepar" align="center">
			<c:choose>
        <c:when test='${detalleConsulta.codigo_motivo ==  "1"}'>
		Copia		 </c:when>
		  <c:when test='${detalleConsulta.codigo_motivo ==  "2"}'>
		 Ref. Doc.		 </c:when>
		  <c:when test='${detalleConsulta.codigo_motivo ==  "3"}'>
		   Conocimiento		 </c:when>
		  <c:when test='${detalleConsulta.codigo_motivo ==  "4"}'>
		 Informe		 </c:when>
		  <c:when test='${detalleConsulta.codigo_motivo ==  "5"}'>
		 Devolucion		 </c:when>
		  <c:when test='${detalleConsulta.codigo_motivo ==  "6"}'>
		 Archivo		 </c:when>
		  <c:when test='${detalleConsulta.codigo_motivo ==  "7"}'>
		 Atencion		 </c:when>
		 <c:when test='${detalleConsulta.codigo_motivo ==  "8"}'>
		 Coordinacion		 </c:when>
		 <c:when test='${detalleConsulta.codigo_motivo ==  "9"}'>
		 Preparar Rpta.		 </c:when>
		 <c:when test='${detalleConsulta.codigo_motivo ==  "10"}'>
		 Liquidacion		 </c:when>
		 <c:when test='${detalleConsulta.codigo_motivo ==  "11"}'>
		 Proyectar Resol.		 </c:when>
		 <c:when test='${detalleConsulta.codigo_motivo ==  "12"}'>
		 Viciacion		 </c:when>
		 <c:when test='${detalleConsulta.codigo_motivo ==  "13"}'>
		 Notificacion		 </c:when>
		 <c:when test='${detalleConsulta.codigo_motivo ==  "14"}'>
		 Para tramitar		 </c:when>
		 <c:when test='${detalleConsulta.codigo_motivo ==  "15"}'>
		 Revision y Conformidad		 </c:when>
		 <c:when test='${detalleConsulta.codigo_motivo ==  "16"}'>
		 Opinion		 </c:when>
		  <c:when test='${detalleConsulta.codigo_motivo ==  "17"}'>
		 Consideracion		 </c:when>
		  <c:when test='${detalleConsulta.codigo_motivo ==  "18"}'>
		 Correccion		 </c:when>
        <c:otherwise>    
		----		   </c:otherwise>
    </c:choose>
			
			 
			 </TD>
			 
      <TD class="tableimpar">Obs. de la Deriv :</TD>
			 <TD class="tablepar" align="center">
			 <c:out value='${detalleConsulta.observa_movimiento}'/>
			</TD>
			 <TD class="tablepar"></TD>
			 <TD class="tablepar"></TD>
			 <TD class="tablepar"></TD>
			 <TD class="tablepar"></TD>
		</TR>
		<TR>
		     
            <TD height="33" class="tableimpar">Fecha Registro :</TD>
			 <TD class="tablepar" align="center">
			 <c:out value='${detalleConsulta.fecha_registro}'/>
			</TD>
			 <TD class="tableimpar">Hora Registro :</TD>
			 <TD class="tablepar" align="center">
			  <c:out value='${detalleConsulta.hora_movimiento}'/>
			 </TD>
			 <TD class="tableimpar">N&deg; folios :</TD>
			 <TD class="tablepar" align="center">
			 <c:out value='${detalleConsulta.folios_documento}'/>
			</TD>
			 <TD class="tableimpar">Ultimo Usuario :</TD>
			 <TD class="tablepar" align="center">
			  <c:out value='${detalleConsulta.ultimo_usuario}'/>
			 </TD>
		</TR>
		<TR>
		     
            <TD height="34" class="tableimpar">Asunto :</TD>
			 <TD class="tablepar" colspan="7">
			  <c:out value='${detalleConsulta.asunto_documento}'/>
			 </TD>
			 
		</TR>
		<TR>
		     
            <TD height="48" class="tableimpar">Obs. del Doc :</TD>
			 <TD class="tablepar" colspan="7">
			   <c:out value='${detalleConsulta.observa_documento}'/>
			 </TD>
		</TR>
		
    </TBODY>
</TABLE>
</TD>
</TR>
</TABLE>

<TABLE align="center" width="100%">
  <TR>
    <TD align="right" valign="middle"> 
      <INPUT type="button"  class="Button5" style="WIDTH: 70px; HEIGHT: 17px" value="Imprimir" onClick="javascript:window.print()"></TD>
    <TD align="left" valign="middle"> 
      <INPUT type="button"  class="Button5" style="WIDTH: 70px; HEIGHT: 17px" value="Cerrar" onClick="javascript:window.close()"></TD>
</TR>
</TABLE>

</BODY>
</HTML>
<% } %>