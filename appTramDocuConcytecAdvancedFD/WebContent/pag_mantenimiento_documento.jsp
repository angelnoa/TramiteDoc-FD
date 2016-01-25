<%@ include file="taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Mantenimiento Documento</TITLE>
     <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	 
    <LINK href="PlanSec.css" type="text/css" rel="stylesheet">
		<link href="css/main.css"   rel="stylesheet" type="text/css"> 
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		 <LINK href="css/bkinHome.css" type=text/css rel=stylesheet>
		<LINK href="css/bkin2.css" type=text/css rel=stylesheet>
		<LINK href="css/gridStyle.css" type=text/css rel=stylesheet>
        <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
</HEAD>
<script src="js/funciones.js" type="text/javascript"></script>
<script  src="js/prototype-1.4.0.js" type="text/javascript"></script>
<script type="text/javascript" src="js/scriptaculous.js"></script>
<script type="text/javascript" src="js/overlibmws.js"></script>
<script type="text/javascript" src="js/ajaxtags.js"></script>
<script language="JavaScript" src="js/avatec.js"></script>
<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
<script language="JavaScript">
	function Cerrar(){
	 var strURL = "CargaMesaPartes.do?operacion=buscar";
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	window.close()
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
		
		function Modificar(){
 
 	
		 if (Validarcampovacio(document.form_datos.numero_documento.value) == false)
              {              
                    alert("Ingresar Numero de Documento");
                    document.form_datos.numero_documento.focus();
                    return false;           
              } 
			 if (Validarcampovacio(document.form_datos.fecha_registro.value) == false)
              {              
                    alert("Ingresar Fecha Registro");
                    document.form_datos.fecha_registro.focus();
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.fecha_movimiento.value) == false)
              {              
                    alert("Ingresar Fecha Movimiento");
                    document.form_datos.fecha_movimiento.focus();
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.hora.value) == false)
              {              
                    alert("Ingresar Hora");
                    document.form_datos.hora.focus();
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.folios_documento.value) == false)
              {              
                    alert("Ingresar Folios del Documento");
                    document.form_datos.folios_documento.focus();
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.asunto_documento.value) == false)
              {              
                    alert("Ingresar Asunto del Documento");
                    document.form_datos.asunto_documento.focus();
                    return false;           
              } 
			  
			 
				 if(document.form_datos.naturaleza_documento.value=="0"){
						  alert("Ingresar  Naturaleza");
				document.form_datos.naturaleza_documento.focus();
				return false;
				}
				
				 if(document.form_datos.estado_movimiento.value=="0"){
						  alert("Ingresar  Estado");
				document.form_datos.estado_movimiento.focus();
				return false;
				}
				 if(document.form_datos.origen_documento.value=="0"){
						  alert("Ingresar  Origen");
				document.form_datos.origen_documento.focus();
				return false;
				}
				 if(document.form_datos.destino_documento.value=="0"){
						  alert("Ingresar  Destino");
				document.form_datos.destino_documento.focus();
				return false;
				}
				 if(document.form_datos.codigo_motivo.value=="0"){
						  alert("Ingresar  Motivo");
				document.form_datos.codigo_motivo.focus();
				return false;
				}
				
	codigo_documento = document.getElementById("codigo_documento").value
	numero_documento = document.getElementById("numero_documento").value
	fecha_registro = document.getElementById("fecha_registro").value
	fecha_movimiento = document.getElementById("fecha_movimiento").value
	hora = document.getElementById("hora").value
	folios_documento = document.getElementById("folios_documento").value
	asunto_documento = document.getElementById("asunto_documento").value
	observa_movimiento = document.getElementById("observa_movimiento").value
	observa_documento = document.getElementById("observa_documento").value
	naturaleza_documento = document.getElementById("naturaleza_documento").value
	estado_movimiento = document.getElementById("estado_movimiento").value
	origen_documento = document.getElementById("origen_documento").value
	destino_documento = document.getElementById("destino_documento").value
	codigo_motivo = document.getElementById("codigo_motivo").value
	

	var strURL = "MantDocumento.do?operacion=M";
	
			strURL += "&codigo_documento="+codigo_documento;
			strURL += "&numero_documento="+numero_documento;
			strURL += "&fecha_registro="+fecha_registro;
			strURL += "&fecha_movimiento="+fecha_movimiento;
			strURL += "&hora="+hora;
			strURL += "&folios_documento="+folios_documento;
      		strURL += "&asunto_documento="+asunto_documento;
			strURL += "&observa_movimiento="+observa_movimiento;
			strURL += "&observa_documento="+observa_documento;
			
			strURL += "&naturaleza_documento="+naturaleza_documento;
			strURL += "&estado_movimiento="+estado_movimiento;
			strURL += "&origen_documento="+origen_documento;
			strURL += "&destino_documento="+destino_documento;
			strURL += "&codigo_motivo="+codigo_motivo;
			
			strURL += "&h="+<%=System.currentTimeMillis()%>;
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	
	window.close()
	
}

function Eliminar(){
 
 				if(confirm("Está seguro de eliminar este registro?")){
				
				var strURL = "MantDocumento.do?operacion=E";
				opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	
				window.close()
	
				}
				
				
	
}
</script>
<BODY PSPARAMS="">
<FORM id="form_datos" name="form_datos" action="" method="post">
<TABLE align="center" width="100%" border="1" cellpadding="0" cellspacing="0" borderColor="#e9e9e9">
<TR class="TextFieldOn35"><TD align="center"><font color="#000000" size="2" ><strong>MANTENIMIENTO DEL DOCUMENTO</strong></font></TD></TR>
<TR>
    <TD height="234"> 
      <TABLE align="center" width="100%" border="0" bgColor="#F0F5FB">
        <!--DWLayoutTable-->
        <TBODY>
          <TR> 
            <TD width="84" height="41" align="right" valign="middle" class="label">Codigo 
              :</TD>
            <TD width="452" align="left" valign="middle" class="tablepar"> <INPUT class="txt" id="codigo_documento" maxLength="15" size="15" name="codigo_documento" readonly="true" value='<c:out value='${detalleConsulta.codigo_documento}'/>
              '> </TD>
            <TD width="77" align="right" valign="middle" class="label">Naturaleza 
              :</TD>
            <TD width="349" align="left" valign="middle"><SELECT class="caja" id="naturaleza_documento" style="WIDTH: 130px" name="naturaleza_documento">
                <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                <OPTION value="E">Entrada</OPTION>
                <OPTION value="I">Interno</OPTION>
              </SELECT> </TD>
            <TD width="5">&nbsp;</TD>
          </TR>
          <TR> 
            <TD height="29" align="right" valign="middle" class="label">N&uacute;mero 
              Doc.:</TD>
            <TD align="left" valign="middle" class="tablepar"> <INPUT class="txt" id="numero_documento" maxLength="40" size="40" name="numero_documento" value="<c:out value='${detalleConsulta.numero_documento}'/>"> 
            </TD>
            <TD align="right" valign="middle" class="label">N&deg; folios : </TD>
            <TD align="left" valign="middle" class="label"> <INPUT class="txt" id="folios_documento" maxLength="3" size="3" name="folios_documento" value="<c:out value='${detalleConsulta.folios_documento}'/> "> 
            </TD>
            <TD>&nbsp;</TD>
          </TR>
          <TR> 
            <TD height="29" align="right" valign="middle" class="label">Fecha 
              movimiento:</TD>
            <TD align="left" valign="middle" class="tablepar"> <INPUT class="txt" id="fecha_movimiento" onKeyPress="validarCaracterFecha(this);"  maxLength="15" size="15" name="fecha_movimiento" value="<c:out value='${detalleConsulta.fecha_movimiento}'/>"> 
			&nbsp;<a href="" id="lanzador1"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a>
            </TD>
            <TD align="right" valign="middle" class="label">Estado :</TD>
            <TD align="left" valign="middle" class="label"> 
              <SELECT class="caja" id="estado_movimiento" style="WIDTH: 130px" name="estado_movimiento">
                <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                <OPTION value="1">Registrado</OPTION>
                <OPTION value="2">Pendiente</OPTION>
                <OPTION value="3">Recibido</OPTION>
                <OPTION value="4">Archivado</OPTION>
                <OPTION value="5">Derivado</OPTION>
                <OPTION value="6">Preliquidado</OPTION>
                <OPTION value="7">Liquidado</OPTION>
                <OPTION value="8">Entregado</OPTION>
              </SELECT> </TD>
            <TD>&nbsp;</TD>
          </TR>
          <TR> 
            <TD height="28" align="right" valign="middle" class="label">Origen 
              :</TD>
            <TD valign="top" class="tablepar"> <select id="origen_documento" name="origen_documento" class="caja" style="WIDTH: 250px; HEIGHT: 21px">
                <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.nombre_corto}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select></TD>
            <TD align="right" valign="middle" class="label">Destino :</TD>
            <TD valign="top" class="tablepar"><select id="destino_documento" name="destino_documento" class="caja" style="WIDTH: 250px; HEIGHT: 21px">
                <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.nombre_corto}'/> </option> </c:forEach> 
                    </c:when> </c:choose>  </select>
            </TD>
            <TD class="tablepar"></TD>
          </TR>
          <TR> 
            <TD height="41" align="right" valign="middle" class="label">Motivo 
              :</TD>
            <TD align="left" valign="middle" class="tablepar"> 
              <select id="codigo_motivo" name="codigo_motivo" class="caja" >
                <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                <c:choose> <c:when test='${not empty rs_motivos}'> <c:forEach items='${rs_motivos}' var='pa'>	<option value=<c:out value='${pa.codigo_motivo}'/>> 
                  <c:out value='${pa.descripcion_motivo}'/> </option> </c:forEach> 
                  </c:when> </c:choose> 
              </select>
            </TD>
            <TD align="right" valign="middle" class="label">Obs. de la Deriv :</TD>
            <TD align="left" valign="middle" class="tablepar"> 
              <TEXTAREA id="observa_movimiento" style="WIDTH: 300px; HEIGHT: 72px" name="observa_movimiento" rows="4" cols="71"><c:out value='${detalleConsulta.observa_movimiento}'/></TEXTAREA>  
            </TD>
            <TD class="tablepar"></TD>
          </TR>
          <TR> 
            <TD height="41" align="right" valign="middle" class="label">Fecha 
              Registro :</TD>
            <TD align="left" valign="middle" class="tablepar"> 
              <INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_registro"  maxLength="10" size="11" name="fecha_registro" value="<c:out value='${detalleConsulta.fecha_registro}'/> "> 
                  &nbsp; <a href="" id="lanzador"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a> 
				   &nbsp;dd/mm/yyyy               
			
            </TD>
            <TD align="right" valign="middle" class="label">Hora Registro :</TD>
            <TD align="left" valign="middle" class="tablepar"> 
              <INPUT language="JavaScript" class="txt"  id="hora" maxLength="8" size="9" name="hora" value="<c:out value='${detalleConsulta.hora_registro}'/> "> 
                  &nbsp;00:00
			 
            </TD>
            <TD>&nbsp;</TD>
          </TR>
          <TR> 
            <TD height="34" align="right" valign="middle" class="label">Asunto 
              :</TD>
            <TD colspan="4" valign="top" class="tablepar"> <TEXTAREA id="asunto_documento" style="WIDTH: 300px; HEIGHT: 72px" name="asunto_documento" rows="4" cols="71" ><c:out value='${detalleConsulta.asunto_documento}'/></TEXTAREA> 
            </TD>
          </TR>
          <TR> 
            <TD height="48" align="right" valign="middle" class="label">Obs. del 
              Reg:</TD>
            <TD colspan="4" valign="top" class="tablepar"><TEXTAREA id="observa_documento" style="WIDTH: 300px; HEIGHT: 72px" name="observa_documento" rows="4" cols="50"><c:out value='${detalleConsulta.observa_documento}'/> </TEXTAREA> 
            </TD>
          </TR>
        </TBODY>
      </TABLE>
</TD>
</TR>
</TABLE>
				
<TABLE align="center" width="100%">
  <!--DWLayoutTable-->
  <TR> 
    <TD width="982" height="19" align="center" valign="middle"> 
      <INPUT  class="Button5" style="WIDTH: 70px; HEIGHT: 17px" id="INPUT5" onClick="javascript:Modificar();" type="button" value="Modificar" name="cmb_grabar">
	  <INPUT  class="Button5" style="WIDTH: 70px; HEIGHT: 17px" id="INPUT5" onClick="javascript:Eliminar();" type="button" value="Eliminar" name="cmb_grabar">  
      <INPUT type="button"  class="Button5" style="WIDTH: 70px; HEIGHT: 17px" value="Cerrar" onClick="javascript:Cerrar();"></TD>
  </TR>
</TABLE>
 				<SCRIPT language="javascript">
			          SeleccionarCampo("naturaleza_documento","<c:out value='${detalleConsulta.naturaleza_documento}'/>");
					  SeleccionarCampo("estado_movimiento","<c:out value='${detalleConsulta.estado_movimiento}'/>");
					  SeleccionarCampo("origen_documento","<c:out value='${detalleConsulta.oficina_origen}'/>");
					  SeleccionarCampo("destino_documento","<c:out value='${detalleConsulta.oficina_deriva}'/>");
					  SeleccionarCampo("codigo_motivo","<c:out value='${detalleConsulta.codigo_motivo}'/>");
			    </SCRIPT>   
				 
			<SCRIPT type=text/javascript>
				Calendar.setup({
					inputField     :    "fecha_registro",      // id del campo de texto
					ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
					button         :    "lanzador" 
					   // el id del botón que lanzará el calendario
				});
				Calendar.setup({
					inputField     :    "fecha_movimiento",      // id del campo de texto
					ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
					button         :    "lanzador1"   // el id del botón que lanzará el calendario
				});
			</SCRIPT>
</FORM>
</BODY>
</HTML>
