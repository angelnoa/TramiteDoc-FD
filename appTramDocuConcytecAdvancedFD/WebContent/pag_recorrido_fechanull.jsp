<%@page import="java.util.Collection"%>
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

<HTML>
<HEAD>
    <TITLE>Recorrido de un Documento</TITLE>
	<link rel="stylesheet" href="css/avatec.css" type="text/css" />
	<link rel="stylesheet" href="css/table.css" type="text/css" />
	<link rel="stylesheet" href="css/modelo.css" type="text/css" />
 	<LINK href="css/main.css" type="text/css" rel="stylesheet">
 	
 	<SCRIPT type="text/javascript" src="js/latest.js"></SCRIPT>
<SCRIPT>

function verReporte(){
var strURL = 'reporte_recorrido_fechanull.jsp';
winPopup = window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
}
function Cerrar(){
 
 		/*var strURL = "ListaDocumentos.do?tipo=seguimiento&operacion=S";
		window.location.href=strURL*/
		window.history.back();
}


var winPopup = 0;
function RecorridoFechaNull(codigo_documento,codigo_expediente,fecha_rpta,secuencia_movimiento)
	{
	
		
		 var strURL = 'ValidaPaginas.do?tipo=reportes&accion=fecharecorrido';
		strURL += '&codigo_documento='+codigo_documento;
		strURL += '&codigo_expediente='+codigo_expediente;
		strURL += '&fecha_rpta='+fecha_rpta;
		strURL += '&secuencia_movimiento='+secuencia_movimiento;
			
		//alert(strURL);	
	   winPopup = window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
	   
	}
	
	function SeleccionaSeguimiento(){
	
	   var ls_tiposegumiento  = document.getElementById("tiposegumiento").value;
	   var ls_codigo_documento  = document.getElementById("codigo_documento").value;
	   var ls_codigo_expediente  = document.getElementById("codigo_expediente").value;
	   var ls_fecha_rpta  = document.getElementById("fecha_rpta").value;
	   var ls_secuencia_movimiento  = document.getElementById("secuencia_movimiento").value;
	   
	   //alert(ls_tiposegumiento);
	   //alert(codigo);
	  
	  if(ls_tiposegumiento=="R"){
	  
	  //var strURL = "ListaDocumentos.do?tipo=selecrecorrido&operacion=S&tiposegumiento="+ls_tiposegumiento;
	  var strURL = "ListaDocumentos.do?tipo=selecrecorrido&tiposegumiento="+ls_tiposegumiento+"&codigo_documento="+ls_codigo_documento+"&codigo_expediente="+ls_codigo_expediente+"&fecha_rpta="+ls_fecha_rpta+"&secuencia_movimiento="+ls_secuencia_movimiento;
		window.location.href=strURL;
		
	  }else{
	  
	 /* var strURL = "ListaDocumentos.do?tipo=selecrecorrido&operacion=D&tiposegumiento="+ls_tiposegumiento;
		window.location.href=strURL*/
		
		var strURL = "ListaDocumentos.do?tipo=selecrecorrido&tiposegumiento="+ls_tiposegumiento+"&codigo_documento="+ls_codigo_documento+"&codigo_expediente="+ls_codigo_expediente+"&fecha_rpta="+ls_fecha_rpta+"&secuencia_movimiento="+ls_secuencia_movimiento;
		window.location.href=strURL;
		
	  }
	
	}
	
	
	function SeleccionarCampo(campo, valor){
  for(var i = 0; i < document.forms[0].elements.length; i++){
    if (document.forms[0].elements[i].name==campo){
      for(var j=0; j< document.forms[0].elements[i].length; j++){      
        if (document.forms[0].elements[i].options[j].value==valor){
          document.forms[0].elements[i].options[j].selected=true;
        }
      }
    }    
  }
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
</HEAD>


<BODY bgColor="white" leftMargin="0" topMargin="0" rightMargin="0" PSPARAMS="">
<div class="derivaconta">
	
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>


<FORM id="form_datos" name="form_datos" action="" method="post">
<INPUT  type="hidden" id="codigo_documento" name="codigo_documento" value="<c:out value='${detalleConsulta.codigo_documento}'/>">
<INPUT  type="hidden" id="codigo_expediente" name="codigo_expediente" value="<c:out value='${detalleConsulta.codigo_expediente}'/>">
<INPUT  type="hidden" id="fecha_rpta" name="fecha_rpta" value="<c:out value='${detalleConsulta.fecha_rpta}'/>">
<INPUT  type="hidden" id="secuencia_movimiento" name="secuencia_movimiento" value="<c:out value='${detalleConsulta.secuencia_movimiento}'/>">
<TABLE align="center" width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#E9E9E9">
 
 <TR >
    <TD align="center" background="img/fondoplomo8.jpg"  class="TextFieldOn35" height="25"><font color="#000000" size="2" ><strong>Seguimiento del Documento</strong></font></TD>
 </TR>
 <TR>
  <TD>

<TABLE width="100%" align="center" border="0">
  <!--DWLayoutTable-->
    <TBODY>
    <TR  align="center">
    	<TD width="95%" height="15" align="left" valign="top">
		<table width="99%" cellpadding="0" cellspacing="0">
                  <!--DWLayoutTable-->
                  <TR> 
            <TD width="85" height="27" class="label">N&deg; Registro :</TD>
                    <TD colspan="3" align="left" valign="middle"  class="caja7"><c:out value='${detalleConsulta.codigo_documento}'/></TD>
                    <TD width="160" class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                    <TD width="146" class="label">Naturaleza :</TD>
            <TD width="121" align="left" valign="middle" class="caja7" > <c:choose> 
              <c:when test='${detalleConsulta.naturaleza_documento ==  "I"}'> 
              Interno </c:when> <c:when test='${detalleConsulta.naturaleza_documento ==  "S"}'> 
              Salida </c:when> <c:otherwise> Externo </c:otherwise> </c:choose>            </TD>
            <TD width="83" class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
            <TD width="149" class="label">Procedencia:</TD>
            <TD width="100" valign="top" class="caja7"><c:out value='${detalleConsulta.desc_origen}'/></TD>
            <TD width="44">&nbsp;</TD>
            <TD width="115">&nbsp;</TD>
            <TD width="12">&nbsp;</TD>
            <TD width="46">&nbsp;</TD>
                  </TR>
          <TR> 
            <TD height="26" class="label">N&deg; Documento :</TD>
                    <TD colspan="3" align="left" valign="middle" class="caja7"> 
                      <c:out value='${detalleConsulta.numero_documento}'/> </TD>
                    <TD align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                    <TD align="left" valign="middle" class="label">N&deg; folios :</TD>
            <TD align="left" valign="middle" class="caja7"> <c:out value='${detalleConsulta.folios_documento}'/>            </TD>
            <TD class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
            <TD class="label">Firmado por:</TD>
            <TD valign="top" class="caja7"><c:out value='${detalleConsulta.firmadopor}'/></TD>
            <TD><!--DWLayoutEmptyCell-->&nbsp;</TD>
            <TD bgcolor="#BBFFCC">Documento Reiterado: </TD>
            <TD ><table width="2" border="0">
              <tr>
                <td>&nbsp;</td>
              </tr>
            </table></TD>
            <TD>&nbsp;</TD>
          </TR>
          <TR> 
            <TD height="40" class="label">Acci&oacute;n a realizar:</TD>
            <TD width="22" align="left" valign="middle" class="caja7"><c:out value='${detalleConsulta.nombre_motivo}'/>            </TD>
            <TD width="72">&nbsp;</TD>
            <TD width="55">&nbsp;</TD>
            <TD align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
            <TD align="left" valign="middle" class="label">Ultimo Usuario :</TD>
            <TD align="left" valign="middle" class="caja7"> <c:out value='${detalleConsulta.ultimo_usuario}'/>            </TD>
            <TD class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
            <TD class="label">Fecha Derivaci&oacute;n:</TD>
            <TD valign="top" class="caja7"><c:out value='${detalleConsulta.fecha_movimiento}'/></TD>
            <TD><!--DWLayoutEmptyCell-->&nbsp;</TD>
            <TD bgcolor="#DFFFBF"><!--DWLayoutEmptyCell-->&nbsp;Documento Original: </TD>
            <TD><table width="2" border="0">
              <tr>
                <td>&nbsp;</td>
              </tr>
            </table></TD>
            <TD>&nbsp;</TD>
          </TR>
          <TR> 
            <TD height="26" align="left" valign="middle" class="label">Fecha Registro 
              :</TD>
            <TD align="left" valign="middle" class="caja7"> <c:out value='${detalleConsulta.fecha_registro}'/>            </TD>
            <TD class="label">Hora Registro :</TD>
            <TD align="left" valign="middle" class="caja7"> <c:out value='${detalleConsulta.hora_movimiento}'/>            </TD>
            <TD align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
            <TD align="left" valign="middle" class="label">Estado :</TD>
            <TD align="left" valign="middle" class="caja7"> <c:choose> <c:when test='${detalleConsulta.estado_movimiento ==  "1"}'> 
              Registrado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "2"}'> 
              <FONT color="#FF0000"> Por Recibir </FONT> </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "3"}'> 
              <FONT  color="#00CC66"> Tramite </FONT> </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "4"}'> 
              Archivado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "5"}'> 
              Derivado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "6"}'> 
              Preliquidado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "7"}'> 
              Liquidado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "8"}'> 
              Por Enviar </c:when> <c:otherwise> ----- </c:otherwise> </c:choose>            </TD>
            <TD align="left" valign="middle" class="label"><!--DWLayoutEmptyCell-->&nbsp;</TD>
            <TD align="left" valign="middle" class="label">Tipo Seguimiento:</TD>
            <TD align="left" valign="middle" class="caja7" >
			  <select id="tiposegumiento" name="tiposegumiento" class="caja" style="width:100" onChange="javascript:SeleccionaSeguimiento(this);">
			    <option value="0">--Selec--</option>
			    <option value="R">Resumen</option>
			    <option value="D">Detalle </option>
			    </select>			</TD>
            <TD>&nbsp;</TD>
            <TD bgcolor="#D5E9FF">Documento Copia: </TD>
            <TD ><table width="2" border="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table></TD>
            <TD>&nbsp;</TD>
          </TR>
          <TR> 
            <TD height="50" class="label">Asunto :</TD>
            <TD colspan="5" align="left" valign="middle" class="caja7"> <c:out value='${detalleConsulta.asunto_documento}'/>            </TD>
            <TD align="left" valign="middle" class="label">Obs. del Doc :</TD>
            <TD colspan="3" align="left" valign="middle" class="caja7"><c:out value='${detalleConsulta.observa_documento}'/></TD>
            <TD>&nbsp;</TD>
            <TD>&nbsp;</TD>
            <TD>&nbsp;</TD>
            <TD>&nbsp;</TD>
          </TR>
        </table></TD>
        </TR>
		<SCRIPT language="javascript">
					SeleccionarCampo("tiposegumiento","<c:out value='${tiposegumiento}'/>");
	    </SCRIPT> 
  
  					
    </TBODY>
</TABLE>

</TD>
</TR>
<TR>
  <TD>


<c:choose>
 <c:when test='${tiposegumiento ==  "D"}'>
   <TABLE  border="0" align="center" cellpadding="0"  width="100%">
    <TR align="center" >
      <TD width="1%"    align="center" class="TextFieldOn36">No</TD>
     
      <TD width="5%"    align="center" class="TextFieldOn36">N&deg; Registro </TD>
	  
      <TD width="8%"   align="center" class="TextFieldOn36" >N&deg;  Documento</TD>
	 
	  <TD width="7%"    align="center" valign="middle" class="TextFieldOn36">Procedencia</TD>
      <TD width="5%"    align="center" valign="middle" class="TextFieldOn36" >Origen</TD>
      
      <TD width="5%"   align="center" class="TextFieldOn36">Destino</TD>
     <TD width="14%"    align="center" valign="middle" class="TextFieldOn36">Destinatario</TD>
      <TD width="13%"    align="center" valign="middle" class="TextFieldOn36">Estado</TD>
      <TD width="20%" align="center"  class="TextFieldOn36"> Observaci&oacute;n      </TD>
      </TR>
   					 <c:choose> 
                              
                              <c:when test='${not empty rs_listaHistorial}'> 
                              <c:forEach items='${rs_recorrido}' var='pr'> 
	  <TR  class="tablepar" align="center"  >
	    <TD align="center"  class="caja7"><c:out value='${pr.contador}'/></TD>
	   
        
      <TD align="center"  class="caja7"><c:out value='${pr.codigo_documento}'/>      </TD>
		
        <TD align="center" class="caja7">
		<c:out value='${pr.numero_documento}'/>		</TD>
		
		<TD align="center" valign="middle" class="caja7">
		<c:choose>
        <c:when test='${pr.naturaleza_documento ==  "I"}'>
		<c:out value='${pr.desc_origen}'/>
		 </c:when>
		 <c:when test='${pr.naturaleza_documento ==  "S"}'>
		<c:out value='${pr.desc_origen}'/>
		 </c:when>
        <c:otherwise>    
		<c:out value='${pr.desc_origen}'/>
		   </c:otherwise>
    </c:choose>		</TD>
        <TD align="center" class="caja7">
		<c:out value='${pr.nombre_oficina_origen}'/>		</TD>
        
        <TD align="center" class="caja7">
		<c:out value='${pr.nombre_oficina_destino}'/>		</TD>
       <TD align="center" class="caja7">
	 <c:out value='${pr.nombre_personal}'/>		</TD>
        <TD align="center" class="caja7">
		<c:out value ='${pr.estado_movimiento}' />		</TD>
        <TD align="center" class="caja7"><c:out value='${pr.observa_movimiento}'/></TD>
        </TR>
      </c:forEach> </c:when>   </c:choose> 
	  <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
</TABLE>
</c:when>
<c:otherwise> 
<TABLE  border="0" align="center" cellpadding="0"  width="100%">
    <TR align="center" >
     
      <TD width="5%"    align="center" class="TextFieldOn36">N&deg; Registro </TD>
	  
      <TD width="8%"   align="center" class="TextFieldOn36" >N&deg;  Documento</TD>
	 
	  <TD width="7%"    align="center" valign="middle" class="TextFieldOn36">Procedencia</TD>
      <TD width="5%"    align="center" valign="middle" class="TextFieldOn36" >Origen</TD>
      
      <TD width="5%"   align="center" class="TextFieldOn36">Destino</TD>
     <TD width="14%"    align="center" valign="middle" class="TextFieldOn36">Destinatario</TD>
      <TD width="13%"    align="center" valign="middle" class="TextFieldOn36">Estado</TD>
      <TD width="6%"   align="center" class="TextFieldOn36">Fecha de Env&iacute;o </TD>
      <TD width="6%" class="TextFieldOn36">Fecha de recepci&oacute;n </TD>
      <TD width="6%"   align="center" class="TextFieldOn36">Fecha de Derivaci&oacute;n </TD>
      <TD width="8%"   align="center" class="TextFieldOn36">Tiempo en bandeja </TD>
      <TD width="9%" align="center"  class="TextFieldOn36"> Observaci&oacute;n      </TD>
      </TR>
   					 <c:choose> 
                              
                              <c:when test='${not empty rs_listaHistorial}'> 
                              <c:forEach items='${rs_listaHistorial}' var='pr'>
		<c:choose>
							  <c:when test='${pr.tipo_envio=="P"}'> 					  
	  <TR align="center" bgcolor="#DFFFBF"  class="tablepar"  >
	   
        
      <TD align="center"  class="caja7"><c:out value='${pr.codigo_documento}'/>      </TD>
		
        <TD align="center" class="caja7">
		<c:out value='${pr.numero_documento}'/>		</TD>
		
		<TD align="center" valign="middle" class="caja7">
		<c:choose>
        <c:when test='${pr.naturaleza_documento ==  "I"}'>
		<c:out value='${pr.desc_origen}'/>
		 </c:when>
		 <c:when test='${pr.naturaleza_documento ==  "S"}'>
		<c:out value='${pr.desc_origen}'/>
		 </c:when>
        <c:otherwise>    
		<c:out value='${pr.desc_origen}'/>
		   </c:otherwise>
    </c:choose>		</TD>
        <TD align="center" class="caja7">
		<c:out value='${pr.nombre_oficina_origen}'/>		</TD>
        
        <TD align="center" class="caja7">
		<c:out value='${pr.nombre_oficina_destino}'/>		</TD>
       <TD align="center" class="caja7">
	 <c:out value='${pr.nombre_personal}'/>		</TD>
        <TD align="center" class="caja7">
		<c:out value ='${pr.estado_movimiento}' />		</TD>
        <TD align="center" class="caja7">  <fmt:formatDate value="${pr.fecha_envio}" pattern="dd/MM/yyyy HH:mm:ss"/></TD>
        <TD class="caja7"><fmt:formatDate value="${pr.fecha_recepcion}" pattern="dd/MM/yyyy HH:mm:ss"/>        </TD>
        <TD align="center" class="caja7">
		<fmt:formatDate value="${pr.fecha_derivacion}" pattern="dd/MM/yyyy HH:mm:ss"/>	</TD>
        <TD align="center" class="caja7"><c:out value="${pr.tiempo_bandeja}"/> </TD>
        <TD align="center" class="caja7"><c:out value='${pr.observa_movimiento}'/></TD>
        </TR>
      </c:when>
	   <c:when test='${pr.tipo_envio=="C"}'> 					  
	   <TR align="center" bgcolor="#D5E9FF"  class="tablepar"  >
	   
        
      <TD align="center"  class="caja7"><c:out value='${pr.codigo_documento}'/>      </TD>
		
        <TD align="center" class="caja7">
		<c:out value='${pr.numero_documento}'/>		</TD>
		
		<TD align="center" valign="middle" class="caja7">
		<c:choose>
        <c:when test='${pr.naturaleza_documento ==  "I"}'>
		<c:out value='${pr.desc_origen}'/>
		 </c:when>
		 <c:when test='${pr.naturaleza_documento ==  "S"}'>
		<c:out value='${pr.desc_origen}'/>
		 </c:when>
        <c:otherwise>    
		<c:out value='${pr.desc_origen}'/>
		   </c:otherwise>
    </c:choose>		</TD>
        <TD align="center" class="caja7">
		<c:out value='${pr.nombre_oficina_origen}'/>		</TD>
        
        <TD align="center" class="caja7">
		<c:out value='${pr.nombre_oficina_destino}'/>		</TD>
       <TD align="center" class="caja7">
	 <c:out value='${pr.nombre_personal}'/>		</TD>
        <TD align="center" class="caja7">
		<c:out value ='${pr.estado_movimiento}' />		</TD>
        <TD align="center" class="caja7">  <fmt:formatDate value="${pr.fecha_envio}" pattern="dd/MM/yyyy HH:mm:ss"/></TD>
        <TD class="caja7"><fmt:formatDate value="${pr.fecha_recepcion}" pattern="dd/MM/yyyy HH:mm:ss"/>        </TD>
        <TD align="center" class="caja7">
		<fmt:formatDate value="${pr.fecha_derivacion}" pattern="dd/MM/yyyy HH:mm:ss"/>	</TD>
        <TD align="center" class="caja7"><c:out value="${pr.tiempo_bandeja}"/> </TD>
        <TD align="center" class="caja7"><c:out value='${pr.observa_movimiento}'/></TD>
        </TR>
	   </c:when>
	   <c:when test='${pr.tipo_envio=="R"}'> 					  
	   <TR align="center" bgcolor="#BBFFCC"  class="tablepar"  >
	   
        
      <TD align="center"  class="caja7"><c:out value='${pr.codigo_documento}'/>      </TD>
		
        <TD align="center" class="caja7">
		<c:out value='${pr.numero_documento}'/>		</TD>
		
		<TD align="center" valign="middle" class="caja7">
		<c:choose>
        <c:when test='${pr.naturaleza_documento ==  "I"}'>
		<c:out value='${pr.desc_origen}'/>
		 </c:when>
		 <c:when test='${pr.naturaleza_documento ==  "S"}'>
		<c:out value='${pr.desc_origen}'/>
		 </c:when>
        <c:otherwise>    
		<c:out value='${pr.desc_origen}'/>
		   </c:otherwise>
    </c:choose>		</TD>
        <TD align="center" class="caja7">
		<c:out value='${pr.nombre_oficina_origen}'/>		</TD>
        
        <TD align="center" class="caja7">
		<c:out value='${pr.nombre_oficina_destino}'/>		</TD>
       <TD align="center" class="caja7">
	 <c:out value='${pr.nombre_personal}'/>		</TD>
        <TD align="center" class="caja7">
		<c:out value ='${pr.estado_movimiento}' />		</TD>
        <TD align="center" class="caja7">  <fmt:formatDate value="${pr.fecha_envio}" pattern="dd/MM/yyyy HH:mm:ss"/></TD>
        <TD class="caja7"><fmt:formatDate value="${pr.fecha_recepcion}" pattern="dd/MM/yyyy HH:mm:ss"/>        </TD>
        <TD align="center" class="caja7">
		<fmt:formatDate value="${pr.fecha_derivacion}" pattern="dd/MM/yyyy HH:mm:ss"/>	</TD>
        <TD align="center" class="caja7"><c:out value="${pr.tiempo_bandeja}"/> </TD>
        <TD align="center" class="caja7"><c:out value='${pr.observa_movimiento}'/></TD>
        </TR>
	   </c:when>
	  <c:otherwise>
	  <TR  class="tablepar" align="center"  >
	   
        
      <TD align="center"  class="caja7"><c:out value='${pr.codigo_documento}'/>      </TD>
		
        <TD align="center" class="caja7">
		<c:out value='${pr.numero_documento}'/>		</TD>
		
		<TD align="center" valign="middle" class="caja7">
		<c:choose>
        <c:when test='${pr.naturaleza_documento ==  "I"}'>
		<c:out value='${pr.desc_origen}'/>
		 </c:when>
		 <c:when test='${pr.naturaleza_documento ==  "S"}'>
		<c:out value='${pr.desc_origen}'/>
		 </c:when>
        <c:otherwise>    
		<c:out value='${pr.desc_origen}'/>
		   </c:otherwise>
    </c:choose>		</TD>
        <TD align="center" class="caja7">
		<c:out value='${pr.nombre_oficina_origen}'/>		</TD>
        
        <TD align="center" class="caja7">
		<c:out value='${pr.nombre_oficina_destino}'/>		</TD>
       <TD align="center" class="caja7">
	 <c:out value='${pr.nombre_personal}'/>		</TD>
        <TD align="center" class="caja7">
		<c:out value ='${pr.estado_movimiento}' />		</TD>
        <TD align="center" class="caja7">  <fmt:formatDate value="${pr.fecha_envio}" pattern="dd/MM/yyyy HH:mm:ss"/></TD>
        <TD class="caja7"><fmt:formatDate value="${pr.fecha_recepcion}" pattern="dd/MM/yyyy HH:mm:ss"/>        </TD>
        <TD align="center" class="caja7">
		<fmt:formatDate value="${pr.fecha_derivacion}" pattern="dd/MM/yyyy HH:mm:ss"/>	</TD>
        <TD align="center" class="caja7"><c:out value="${pr.tiempo_bandeja}"/> </TD>
        <TD align="center" class="caja7"><c:out value='${pr.observa_movimiento}'/></TD>
        </TR>
	  </c:otherwise>
	  </c:choose>
	  </c:forEach> </c:when>   </c:choose> 
	  <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
</TABLE>
 				

</c:otherwise> 
</c:choose>
</TD>
</TR>
</TABLE>
				
<TABLE width="100%" border="0">
<TR>
		<TD  align="center" > 
    <INPUT class="boton" language="JavaScript" id="INPUT1" onClick="javascript:verReporte();" type="button" value="Reporte"  name="cmd_print" >	
		</TD>
	</TR>
	<TR>
		<TD  align="center" height="50">
    <INPUT class="boton" language="JavaScript" id="close" onClick="javascript:Cerrar();" type="button" value="Cerrar" name="close" >	&nbsp;<INPUT class="boton" language="JavaScript" id="close" onClick="javascript:VerHojaRuta('<c:out value='${detalleConsulta.codigo_documento}'/>','<c:out value='${detalleConsulta.codigo_expediente}'/>','<c:out value='${detalleConsulta.codigo_recepcion}'/>');" type="button" value="Hoja de Ruta" name="close" >	
		</TD>
	</TR>
</TABLE>
</FORM>
</div>
</BODY>
</HTML>
<% } %>