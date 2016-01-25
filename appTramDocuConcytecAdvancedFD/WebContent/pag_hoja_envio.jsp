<%@ include file="taglibs.jsp" %>
<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD><TITLE>Hoja de Envio</TITLE>
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

<style type="text/css">
<!--
.style1 {font-weight: bold}
-->
</style>
</HEAD>
<BODY   bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0">

<form id="form_datos" name="form_datos" method="post" action="" >

  <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0 height="100%">
   
      <TR> 
      <TD    width="5" >
        <TD height="80%" align="center" valign="top"  style="WIDTH: 100%" >
         <!--DWLayoutTable-->
        <table width="100%" >
                      <!--DWLayoutTable-->
                      <tr valign="middle"> 
                    <td width="967" height="78" align="left" valign="top">
                      <table cellspacing="0px" cellpadding="0" border="0" align="CENTER" width="100%">
	
	<tr>
		<td width="10%" align="left" valign="middle" >
			&nbsp;
			<IMG   src="img/concyteclogo.jpg" border="0" width="60" >
			&nbsp;&nbsp;
			<!-- bean:message key="general.kycprinttitle.label" /-->		</td>
		<td  valign="middle" align="center" colspan="2">
			CONSEJO NACIONAL DE CIENCIA, TECNOLOGIA E INNOVACION TECNOL&Oacute;GICA<BR> CONCYTEC<BR>
			<span class="style1">SECRETARIA GENERAL</span></td>
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
</table>                      </td>
                  </tr>
                     
      <tr>
        <td height="60" valign="top" ><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <!--DWLayoutTable-->
          <tr>
            <td width="177" height="33" align="right" valign="middle">HOJA ENVIO N&deg;&nbsp;:</td>
              <td width="284" align="left" valign="middle">&nbsp;&nbsp;<c:out value='${detalleDocumento.codigo_recepcion}'/></td>
              <td width="131" align="right" valign="middle">FECHA&nbsp;:</td>
              <td width="377" align="left" valign="middle">&nbsp;&nbsp;<c:out value='${detalleDocumento.fecha_movimiento}'/></td>
            </tr>
          <tr>
            <td height="30" align="right" valign="middle">EXPEDIENTE N&deg;&nbsp;:</td>
              <td align="left" valign="middle">&nbsp;&nbsp;<c:out value='${detalleDocumento.codigo_expediente}'/></td>
              <td align="right" valign="middle">REGIST&nbsp;:</td>
              <td align="left" valign="middle">&nbsp;&nbsp;<c:out value='${detalleDocumento.codigo_documento}'/></td>
            </tr>
        </table></td>
      </tr>
	   <tr>
        <td height="60" valign="top" ><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="114" height="33" align="right" valign="middle">PASE A:</td>
              <td width="306"></td>
              <td width="103">&nbsp;</td>
              <td width="446">&nbsp;</td>
            </tr>
          <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9">
			<c:choose>
        	<c:when test='${codigo_oficina0 ==  "14" || codigo_oficina1 ==  "14" || codigo_oficina2 ==  "14" || codigo_oficina3 ==  "14" }'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> 
			</td>
              <td align="left" valign="middle"class="TextFieldOn9">PRESIDENCIA</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${codigo_oficina0 ==  "6"  || codigo_oficina1 ==  "6" || codigo_oficina2 ==  "6" || codigo_oficina3 ==  "6"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose></td>
              <td align="left" valign="middle" class="TextFieldOn9">OF. ASESORIA JURIDICA</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9">( &nbsp; )&nbsp;&nbsp;&nbsp;</td>
              <td align="left" valign="middle" class="TextFieldOn9">CONSEJO DIRECTIVO</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${codigo_oficina0 ==  "4" || codigo_oficina1 ==  "4" || codigo_oficina2 ==  "4" || codigo_oficina3 ==  "4"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose></td>
              <td align="left" valign="middle" class="TextFieldOn9">OF. GENERAL DE ADMINISTRACI&Oacute;N</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9">( &nbsp; )&nbsp;&nbsp;&nbsp;</td>
              <td align="left" valign="middle" class="TextFieldOn9">ASESOR DE PRESIDENCIA</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${codigo_oficina0 ==  "10" || codigo_oficina1 ==  "10" || codigo_oficina2 ==  "10" || codigo_oficina3 ==  "10"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose></td>
              <td align="left" valign="middle" class="TextFieldOn9">D. POLITICAS Y PLANES DE CTI</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9">( &nbsp; )&nbsp;&nbsp;&nbsp;</td>
              <td align="left" valign="middle" class="TextFieldOn9">CONID</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${codigo_oficina0 ==  "11" || codigo_oficina1 ==  "11" || codigo_oficina2 ==  "11" || codigo_oficina3 ==  "11" }'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose></td>
              <td align="left" valign="middle" class="TextFieldOn9">D. ARTICULACION Y GESTI&Oacute;N-SINACYT</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${codigo_oficina0 ==  "2" || codigo_oficina1 ==  "2" || codigo_oficina2 ==  "2" || codigo_oficina3 ==  "2"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose></td>
              <td align="left" valign="middle" class="TextFieldOn9">SECRETAR&Iacute;A GENERAL </td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${codigo_oficina0 ==  "12" || codigo_oficina1 ==  "12" || codigo_oficina2 ==  "12" || codigo_oficina3 ==  "12"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose></td>
              <td align="left" valign="middle" class="TextFieldOn9">D. CIENCIA Y TECNOLOG&Iacute;A</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9">( &nbsp; )&nbsp;&nbsp;&nbsp;</td>
              <td align="left" valign="middle" class="TextFieldOn9">ARCHIVO CENTRAL -S.G.</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${codigo_oficina0 ==  "13" || codigo_oficina1 ==  "13" || codigo_oficina2 ==  "13" || codigo_oficina3 ==  "13"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose></td>
              <td align="left" valign="middle" class="TextFieldOn9">D. PROSPECT. E INNOVACION TECNOL.</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${codigo_oficina0 ==  "9" || codigo_oficina1 ==  "9" || codigo_oficina2 ==  "9" || codigo_oficina3 ==  "9" }'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose></td>
              <td align="left" valign="middle" class="TextFieldOn9">ORGANO DE CONTROL INSTITUCIONAL</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${codigo_oficina0 ==  "5" || codigo_oficina1 ==  "5" || codigo_oficina2 ==  "5" || codigo_oficina3 ==  "5" }'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose></td>
              <td align="left" valign="middle" class="TextFieldOn9">D. SISTEMAS DE INFORM. Y COM. DE C'</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${codigo_oficina0 ==  "7" || codigo_oficina1 ==  "7" || codigo_oficina2 ==  "7" || codigo_oficina3 ==  "7"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose></td>
              <td align="left" valign="middle" class="TextFieldOn9">OF. PLANEAMIENTO Y PSPTO.</td>
              <td align="right" valign="middle" class="TextFieldOn9">
			  <c:choose>
			  <c:when test='${codigo_oficina0 ==  "8" || codigo_oficina1 ==  "8" || codigo_oficina2 ==  "8" || codigo_oficina3 ==  "8"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose></td>
              <td align="left" valign="middle" class="TextFieldOn9">FONCECYT</td>
            </tr>
        </table></td>
      </tr>
	   <tr>
        <td height="60" valign="top" ><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="118" height="33" align="right" valign="middle">PARA:</td>
              <td width="285">&nbsp;</td>
              <td width="81">&nbsp;</td>
              <td width="485">&nbsp;</td>
            </tr>
          <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9">
			<c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "1"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> 
			</td>
              <td align="left" valign="middle"class="TextFieldOn9">ASISTIR E INFORMAR</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "12"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">DIFUSION</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "2"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">ATENCI&Oacute;N URGENTE</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "13"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">ESTUDIO Y OPINI&Oacute;N</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "3"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">A TRATAR PROX. REUNI&Oacute;N</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "14"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">EVALUACI&Oacute;N</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "4"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">CONOCIMIENTO Y ARCHIVO</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "15"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">INFORMAR</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "5"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">CONOCIMIENTO Y CUMPLIMIENTO </td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "16"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">PREPARAR</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "6"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">CONOCIMIENTO Y FINES</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "17"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">PREPARAR RESPUESTA</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "7"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">CORREGIR PROPUESTA</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "18"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">PROYECTAR RESOLUCI&Oacute;N</td>
            </tr>
			  <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "8"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">COORDINAR</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "19"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">RECOMENDACI&Oacute;N</td>
            </tr>
			 <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "9"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">CONSOLIDAR</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "20"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">REVISI&Oacute;N</td>
            </tr>
			 <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "10"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">CONVERSAR CON SR. PRESIDENTE</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "21"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">TOMAR NOTA Y DEVOLVER</td>
            </tr>
			 <tr>
            <td height="20" align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "11"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">DESIGNAR</td>
              <td align="right" valign="middle" class="TextFieldOn9"><c:choose>
        	<c:when test='${detalleDocumento.codigo_motivo ==  "22"}'>
			( <strong>X</strong> )&nbsp;&nbsp;&nbsp;
			</c:when>
           	<c:otherwise>
			( &nbsp; )&nbsp;&nbsp;&nbsp;
				</c:otherwise>
    		</c:choose> </td>
              <td align="left" valign="middle" class="TextFieldOn9">TRAMITE NECESARIO</td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td height="85" valign="top" ><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <!--DWLayoutTable-->
          <tr>
            <td height="40" colspan="2" align="right" valign="bottom">OBSERVACI&Oacute;N:</td>
            <td width="372">&nbsp;</td>
            <td width="346">&nbsp;</td>
            <td width="100">&nbsp;</td>
          </tr>
          <tr>
            <td width="113" height="34">&nbsp;</td>
            <td colspan="3" align="left" valign="bottom" class="TextFieldOn9">&nbsp;&nbsp;              <c:out value='${detalleDocumento.observa_documento}'/></td>
            <td>&nbsp;</td>
          </tr>
		  <tr>
            <td height="60">&nbsp;</td>
            <td width="38">&nbsp;</td>
            <td>&nbsp;</td>
            <td align="left" valign="bottom" class="TextFieldOn9"></td>
            <td>&nbsp;</td>
          </tr>
		  <tr>
		    <td height="26">&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td align="center" valign="middle" class="TextFieldOn41">Firma</td>
		    <td>&nbsp;</td>
		    </tr>
		  <tr>
		    <td height="13">&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    <td>&nbsp;</td>
		    </tr>
		  
          
          
          
			
			

        </table>
        </td>
      </tr>
     
          </table>
        </TD>
      </TR>
  
  </TABLE>

</FORM>

</BODY></HTML>