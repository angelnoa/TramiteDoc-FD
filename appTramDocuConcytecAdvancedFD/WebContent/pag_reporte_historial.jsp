<%@ include file="taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Historial Documento</TITLE>
     <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY PSPARAMS="">
<TABLE align="center" width="100%" border="1" cellpadding="0" cellspacing="0" borderColor="#e9e9e9">
<TR ><TD align="left" height="50" width="100%"><table width="100%" height="50" cellspacing="0" cellpadding="0"  align="left">
						<tr><td bgcolor="a8b5c8"><jsp:include page="/pag_menu.jsp"/></td></tr></table></TD></TR>
<TR ><TD align="center" class="label" background="img/fondoplomo8.jpg" width="100%" height="26">HISTORIAL DOCUMENTO</TD></TR>
<TR>
    <TD height="234"> 
      <TABLE align="center" width="100%" border="0">
        <!--DWLayoutTable-->
        <TBODY>
          <TR> 
            <TD width="53" height="27" class="label">Codigo :</TD>
            <TD width="269" align="left" ><c:out value='${detalleConsulta.codigo_documento}'/></TD>
            <TD width="70" class="label">Documento :</TD>
            <TD width="268" align="left" ><c:out value='${detalleConsulta.descripcion_tipo}'/><c:out value='${detalleConsulta.numero_documento}'/></TD>
            <TD width="65" class="label">Naturaleza :</TD>
            <TD width="52" align="left" valign="middle" > <c:choose> <c:when test='${detalleConsulta.naturaleza_documento ==  "I"}'> 
              Interno </c:when> <c:when test='${detalleConsulta.naturaleza_documento ==  "S"}'> 
              Salida </c:when> <c:otherwise> Externo </c:otherwise> </c:choose> 
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
              <FONT  color="#00CC66"> Tramite </FONT> </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "4"}'> 
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
          <TR> 
            <TD height="100%" colspan="8" valign="top" class="tablepar"><TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#ffffff" bgcolor="#ffffff" height="170">
                <TR> 
                  <TD> <IFRAME id="ifra_listadocumentos" name="ifra_listadocumentos" align="top" src="VerFrameListas.do?tipo=reportehistorial&codigo_documento=<c:out value='${detalleConsulta.codigo_documento}'/>" frameBorder="0" width="100%" height="100%" scrolling="auto" ></IFRAME> 
                  </TD>
                </TR>
              </TABLE></TD>
          </TR>
          <TR align="center" valign="middle"> 
            <TD height="30" colspan="8" > <INPUT type="button"  class="boton" style="WIDTH: 70px; HEIGHT: 17px" value="Imprimir" onClick="javascript:window.print()"> 
              
            </TD>
          </TR>
          <TR align="center" valign="middle"> 
            <TD height="30" colspan="8" ><A HREF=" javascript: window.history.back() "><font size="1" color="#0033FF" face="Verdana, Arial, Helvetica, sans-serif">Ir 
              a la p&aacute;gina anterior </font></a> </TD>
          </TR>
        </TBODY>
      </TABLE>
</TD>
</TR>
</TABLE>

</BODY>
</HTML>
