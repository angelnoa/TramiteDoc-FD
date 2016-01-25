<%@ include file="taglibs.jsp" %>
<fmt:setBundle basename="ApplicationResources" />
<HTML>
<HEAD>
    <TITLE>Recorrido de un Documento</TITLE>
	<link rel="stylesheet" href="css/avatec.css" type="text/css" />
	<link rel="stylesheet" href="css/table.css" type="text/css" /> 
</HEAD>
 
 
<SCRIPT>
function Cerrar(){
/* var strURL = "CargaMesaPartes.do?operacion=buscar";

opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
window.close()*/
window.history.back()
}
</SCRIPT>

<BODY bgColor="white" leftMargin="0" topMargin="0" rightMargin="0" PSPARAMS="">
<TABLE align="center" width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#E9E9E9">
<TR >
    <TD align="center"   height="50">
	<table cellspacing="0px" cellpadding="0" border="0" align="CENTER" width="100%">
	
	<tr>
		<td width="10%" align="left" valign="middle" class="labelblack">
			&nbsp;
			<IMG   src="img/concyteclogo.jpg" border="0" width="60" >
			&nbsp;&nbsp;
			<!-- bean:message key="general.kycprinttitle.label" /-->		</td>
		<td class="labelblack" valign="middle" align="center" colspan="2">
			REPORTE SEGUIMIENTO DEL DOCUMENTO  </td>
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
 <TR >
    <TD align="center"  >
	<table width="98%" cellpadding="0" cellspacing="0">
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
		</table>
	</TD>
 </TR>
 <TR >
    <TD align="center"  class="TextFieldOn35" background="img/fondoplomo8.jpg" height="25"><font color="#000000" size="2" ><strong>Seguimiento del Documento</strong></font></TD>
 </TR>
 <TR>
  <TD>

<TABLE width="100%" align="center" border="0" bgColor="#F0F5FB" cellpadding="0">
  <!--DWLayoutTable-->
    <TBODY>
    <TR class="cabeceratable" align="center">
    	<TD width="52" height="27" align="right" valign="middle"> <img src="img/verde.gif" width="30">  </TD>
        <TD colspan="2" align="center" valign="middle">Fecha es Menor a la Fecha de Respuesta</TD>
        <TD width="62" align="right" valign="middle"><img src="img/amarillo.gif" width="30">   </TD>
        <TD colspan="2" align="center" valign="middle">Fecha Limite</TD>
        <TD width="81" align="right" valign="middle"><img src="img/atencion.gif" width="30">  </TD>
        <TD colspan="4" align="center" valign="middle">Fecha es Mayor a la Fecha de Respuesta</TD>
        </TR>
    <TR class="cabeceratable" align="center">
    	<TD height="24" align="center">Orden</TD>
        <TD width="160" align="center">Ubicaci&oacute;n/Procedencia</TD>
        <TD width="118" align="center">Estado</TD>
        <TD colspan="2" align="center">Fecha Reg.</TD>
        <TD width="60" align="center">Hora Reg.</TD>
        <TD align="center">Usuario</TD>
        <TD width="126" align="center">Motivo</TD>
        <TD width="77" align="center">N&deg; Dias Bandeja</TD>
		<TD width="76" align="center">Fecha para Responder</TD>
		<TD width="75" align="center">Alertas</TD>
    </TR>
  
  					<c:choose>           
					  <c:when test='${not empty rs_listaHistorial}'> 
					  <c:forEach items='${rs_listaHistorial}' var='pr'> 
     <TR  class="tablepar" align="center">
	    <TD height="40" align="center"><STRONG><c:out value='${pr.contador}'/></STRONG> </TD>
        <TD align="center">
        <FONT color="#993300">
		<c:out value='${pr.descripcion_oficina}'/>/
		<c:out value='${pr.desc_origen}'/>
		</FONT>	
		<BR>
		<c:out value='${pr.codigo_documento}'/>
		<c:out value='${pr.secuencia_movimiento}'/>
		<BR>
		<c:out value='${pr.descripcion_tipo}'/>
		<c:out value='${pr.numero_documento}'/>		</TD>
        <TD align="center">
		<c:choose>
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
    </c:choose>		</TD>
        <TD colspan="2" align="center"><c:out value='${pr.fecha_movimiento}'/></TD>
        <TD align="center"><c:out value='${pr.hora_movimiento}'/></TD>
        <TD align="center">
		<c:out value='${pr.ultimo_usuario}'/>		</TD>
        <TD align="center">
		<c:choose>
        <c:when test='${pr.codigo_motivo ==  "1"}'>
		Copia		 </c:when>
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
		 <TD align="center">
		<c:out value='${dias_bandeja}'/>		</TD>
		 <TD align="center">
		<c:out value='${pr.fecha_rpta}'/>		</TD>
		 <TD align="center">
		  <c:if test="${verde == '1'}" > 
               <img src="img/verde.gif">           </c:if>
		 <c:if test="${amarillo == '2'}" > 
              <img src="img/amarillo.gif">           </c:if>
		   <c:if test="${rojo == '3'}" > 
               <img src="img/atencion.gif">           </c:if>		</TD>
    </TR>
     
	 </c:forEach> </c:when>   </c:choose> 
    </TBODY>
</TABLE>

</TD>
</TR>
</TABLE>

</BODY>
</HTML>
