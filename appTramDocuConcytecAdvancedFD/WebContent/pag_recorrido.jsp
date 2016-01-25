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

<html>
<head>
<SCRIPT LANGUAGE=javascript src="js/overlibmws.js"></SCRIPT>
<script LANGUAGE="JavaScript" src="js/avatec.js"></script>

<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">

<LINK rel="stylesheet" href="css/main.css" type="text/css" />

<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />
<link rel="stylesheet" href="css/modelo.css" type="text/css" />
<link rel="stylesheet" href="css/tipografias.css"  type="text/css" />
	
	<SCRIPT>
	$(document).ready(function(){
		$( "#tabs" ).tabs();
	});
	
	function Cerrar(){
	/* var strURL = "CargaMesaPartes.do?operacion=buscar";
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	window.close()*/
	//window.history.back()
	/*		var strURL = "ListaDocumentos.do?tipo=seguimiento&operacion=S";
			window.location.href=strURL;*/
		window.history.back();

	}
	
	var winPopup = 0;
	function RecorridoFechaNotNull(codigo_documento,codigo_expediente,fecha_rpta,secuencia_movimiento)
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
			//alert(strURL);
		  }else{
		  
		 /* var strURL = "ListaDocumentos.do?tipo=selecrecorrido&operacion=D&tiposegumiento="+ls_tiposegumiento;
			window.location.href=strURL*/
			
			var strURL = "ListaDocumentos.do?tipo=selecrecorrido&tiposegumiento="+ls_tiposegumiento+"&codigo_documento="+ls_codigo_documento+"&codigo_expediente="+ls_codigo_expediente+"&fecha_rpta="+ls_fecha_rpta+"&secuencia_movimiento="+ls_secuencia_movimiento;
			window.location.href=strURL;
			//alert(strURL);
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
	
	</SCRIPT>
	 <title>Recorrido de un Documento </title>
</head>
 
<body bgcolor="#F0F5FB" topmargin="0" leftmargin="0" >
<div class="derivaconta">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
</div>

<form id="form_datos" name="form_datos" action="" method="post">
		<INPUT  type="hidden" id="codigo_documento" name="codigo_documento" value="<c:out value='${detalleConsulta.codigo_documento}'/>">
		<INPUT  type="hidden" id="codigo_expediente" name="codigo_expediente" value="<c:out value='${detalleConsulta.codigo_expediente}'/>">
		<INPUT  type="hidden" id="fecha_rpta" name="fecha_rpta" value="<c:out value='${detalleConsulta.fecha_rpta}'/>">
		<INPUT  type="hidden" id="secuencia_movimiento" name="secuencia_movimiento" value="<c:out value='${detalleConsulta.secuencia_movimiento}'/>">
		
		<div id="break" ></div>
			<div id="contactderiva" class="rounded"> 
			<div id="TDHeadCab">
						   		   	<span class="labelrounded">SEGUIMIENTO DEL REGISTRO Nº<c:out value='${detalleConsulta.codigo_documento}'/></span>
						   		   	
						  			<!--div class="linklista"><a  href="javascript:listadocumentos()">
						  			<img src="img/modifica.gif" alt="Modificar Documentos" width="32" height="32" border="0" ></a>
						  			</div-->
						   		
			</div>
			<div id="Lineaform2" ></div>

			<div id="left" class="columna-left2" > 
			<p><label>N&deg; Registro :</label> <c:out value='${detalleConsulta.codigo_documento}'/></p>
			<p><label>N&deg; Documento :</label> <c:out value='${detalleConsulta.numero_documento}'/></p>
			<p><label>Acci&oacute;n : </label> <c:out value='${detalleConsulta.nombre_motivo}'/></p>
			<p><label>Fecha Registro : </label> <c:out value='${detalleConsulta.fecha_registro}'/>    <label>Hora Registro : </label> <c:out value='${detalleConsulta.hora_movimiento}'/></p>
			<p><label>Asunto : </label> <c:out value='${detalleConsulta.asunto_documento}'/> <label>Obs. del Doc : </label><c:out value='${detalleConsulta.observa_documento}'/></p>
						
			</div>
			<div id="center"  class="columna-center3" >&nbsp;</div>
			 <div id="rigth" class ="columna-rigth3" >
			 <p><label>Naturaleza :</label><c:choose> 
		              <c:when test='${detalleConsulta.naturaleza_documento ==  "I"}'> 
		              Interno </c:when> <c:when test='${detalleConsulta.naturaleza_documento ==  "S"}'> 
		              Salida </c:when> <c:otherwise> Entrada </c:otherwise> </c:choose></p>
			<p><label>N&deg; folios : </label> <c:out value='${detalleConsulta.folios_documento}'/></p>
			<p><label>Ultimo Usuario : </label> <c:out value='${detalleConsulta.ultimo_usuario}'/></p>
			<p><label>Estado : </label> <c:choose> <c:when test='${detalleConsulta.estado_movimiento ==  "1"}'> 
		              Registrado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "2"}'> 
		              <FONT color="#FF0000"> Por Recibir </FONT> </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "3"}'> 
		              <FONT  color="#00CC66"> Recibido </FONT> </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "4"}'> 
		              Archivado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "5"}'> 
		              Derivado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "6"}'> 
		              Preliquidado </c:when> <c:when test='${detalleConsulta.estado_movimiento ==  "7"}'> 
		              Liquidado </c:when> <c:otherwise> Entregado </c:otherwise> </c:choose>
		              
		              <label>Fecha movimiento: </label><c:out value='${detalleConsulta.fecha_movimiento}'/></p>
			<p><label>Tipo Seguimiento: </label> <select id="tiposegumiento" name="tiposegumiento" class="caja" style="width:100" onChange="javascript:SeleccionaSeguimiento(this);">
		                <option value="0">--Selec--</option>
		                <option value="R">Resumen</option>
		                <option value="D">Detalle </option>
		              </select></p>
			
			 </div>
			 <div class="center-big">
		    	<input type="button" class="button"  id="INPUT1" name="cmd_print"  onClick="javascript:RecorridoFechaNotNull('<c:out value='${detalleConsulta.codigo_documento}'/>','<c:out value='${detalleConsulta.codigo_expediente}'/>','<c:out value='${detalleConsulta.fecha_rpta}'/>','<c:out value='${detalleConsulta.secuencia_movimiento}'/>');"  value="Ver Reporte" >	
				<input type="button" class="button" id="close" name="close" onClick="javascript:Cerrar()"  style="width: 90;" value="Cerrar"  >
		    	<br>	
			 </div>
			 <br>
			<SCRIPT type=text/javascript>
							SeleccionarCampo("tiposegumiento","<c:out value='${tiposegumiento}'/>");
			 </SCRIPT> 
			 <br>
			</div>
		
		<div id="break" ></div>
				<div class="derivalista">
					<div id="tabs">
					  <ul>
					    <li><a href="#tabs-1">Lista de documentos</a></li>
					   
					  </ul>
					  <div id="tabs-1">	
					  <TABLE align="center" width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#E9E9E9">
		 <TR >
		    <TD align="center"  background="img/fondoplomo8.jpg" height="25"><font color="#000000" size="2" ><strong>Seguimiento del Documento</strong></font></TD>
		 </TR>
		 <TR >
		    <TD   background="img/fondoplomo8.jpg" height="25">
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
			  <!--DWLayoutTable-->
			<TR class="cabeceratable" align="center">
		    	<TD width="52" height="27" align="center" valign="middle"> <img src="img/verde.gif" width="20">  </TD>
		        <TD width="267" align="left" valign="middle" class="TextFieldOn42">Fecha es Menor a la Fecha de Respuesta</TD>
		        <TD width="45" align="center" valign="middle"><img src="img/amarillo.gif" width="20">   </TD>
		        <TD width="96" align="left" valign="middle" class="TextFieldOn42">Fecha Limite</TD>
		        <TD width="39" align="center" valign="middle"><img src="img/atencion.gif" width="20"></TD>
		        <TD width="475" align="left" valign="middle" class="TextFieldOn42">Fecha es Mayor a la Fecha de Respuesta</TD>
		        </TR>
			</table>
			</TD>
		 </TR>
		 <TR>
		  <TD>
		<c:choose>
		 <c:when test='${tiposegumiento ==  "D"}'> 
		<TABLE width="100%" align="center" border="0"  cellpadding="0">
		  <!--DWLayoutTable-->
		    <TBODY>
		  
		    <TR class="cabeceratable" align="center">
		    	<TD height="24" align="center" valign="middle" class="TextFieldOn36">Orden</TD>
		        <TD width="160" align="center" valign="middle" class="TextFieldOn36">Ubicaci&oacute;n/Procedencia</TD>
		        <TD width="118" align="center" valign="middle" class="TextFieldOn36">Estado</TD>
		        <TD colspan="2" align="center" valign="middle" class="TextFieldOn36">Fecha Reg.</TD>
		        <TD width="60" align="center" valign="middle" class="TextFieldOn36">Hora Reg.</TD>
		        <TD align="center" class="TextFieldOn36">Usuario</TD>
		        <TD width="126" align="center" class="TextFieldOn36">Motivo</TD>
		        <TD width="77" align="center" class="TextFieldOn36">N&deg; Dias Bandeja</TD>
				<TD width="76" align="center" class="TextFieldOn36">Fecha para Responder</TD>
				<TD width="75" align="center" class="TextFieldOn36">Alertas</TD>
		    </TR>
		  
		  					<c:choose>           
							  <c:when test='${not empty rs_recorrido}'> 
							  <c:forEach items='${rs_recorrido}' var='pr'> 
		     <TR   align="center">
			    <TD height="25" align="center" class="caja7"><STRONG><c:out value='${pr.contador}'/></STRONG> </TD>
		        <TD align="center" class="caja7">
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
		        <TD align="center" class="caja7">
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
		        <TD colspan="2" align="center" class="caja7"><c:out value='${pr.fecha_movimiento}'/></TD>
		        <TD align="center" class="caja7"><c:out value='${pr.hora_movimiento}'/></TD>
		        <TD align="center" class="caja7">
				<c:out value='${pr.ultimo_usuario}'/>		</TD>
		        <TD align="center" class="caja7">
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
				 <TD align="center" class="caja7">
				<c:out value='${dias_bandeja}'/>		</TD>
				 <TD align="center" class="caja7">
				<c:out value='${pr.fecha_rpta}'/>		</TD>
				 <TD align="center" class="caja7">
				
				 <c:choose>
		        <c:when test='${pr.fecha_rpta ==  ""}'>
				-----
				 </c:when>
				  <c:otherwise> 
						  <c:if test="${verde == '1'}" > 
							   <img src="img/verde.gif" width="20">           </c:if>
						 <c:if test="${amarillo == '2'}" > 
							  <img src="img/amarillo.gif" width="20">           </c:if>
						   <c:if test="${rojo == '3'}" > 
							   <img src="img/atencion.gif" width="20">           </c:if>
					 </c:otherwise>
					    </c:choose>
					   		</TD>
		    </TR>
		     
			 </c:forEach> </c:when>   </c:choose> 
		    </TBODY>
		</TABLE>
				
		  
		</c:when>
		<c:otherwise> 
		
		<TABLE  border="0" align="center" cellpadding="0"  width="100%">
		    <TR align="center" >
		     
		      <TD    align="center" class="TextFieldOn36">N&deg; Registro </TD>
			  
		      <TD   align="center" class="TextFieldOn36" >N&deg;  Documento</TD>
			 
			  <TD    align="center" valign="middle" class="TextFieldOn36">Procedencia</TD>
		      <TD    align="center" valign="middle" class="TextFieldOn36" >Origen</TD>
		      
		      <TD   align="center" class="TextFieldOn36">Destino</TD>
		     <TD    align="center" valign="middle" class="TextFieldOn36">Destinatario</TD>
		      <TD    align="center" valign="middle" class="TextFieldOn36">Estado</TD>
		      <TD   align="center" class="TextFieldOn36">Naturaleza</TD>
		      <TD   align="center" class="TextFieldOn36">Fecha.Doc&nbsp;/&nbsp;Hora</TD>
		      <TD align="center"  class="TextFieldOn36"> Observaci&oacute;n
		      </TD>
			  <TD align="center"  class="TextFieldOn36"> Fecha para Responder
		      </TD>
		      <TD width="75" align="center" class="TextFieldOn36">Alertas</TD>
			  
		  </TR>
		   					 <c:choose> 
		                              
		                              <c:when test='${not empty rs_listaHistorial}'> 
		                              <c:forEach items='${rs_listaHistorial}' var='pr'> 
			  <TR  class="tablepar" align="center"  >
			   
		        
		      <TD align="center"  class="caja7"><c:out value='${pr.codigo_documento}'/> 
		      </TD>
				
		        <TD align="center" class="caja7">
				<c:out value='${pr.descripcion_tipo}'/><c:out value='${pr.numero_documento}'/>
				
				</TD>
				 
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
		    </c:choose>    
				</TD>
		        <TD align="center" class="caja7">
				<c:out value='${pr.nombre_oficina_origen}'/>
				</TD>
		        
		        <TD align="center" class="caja7">
				<c:out value='${pr.nombre_oficina_destino}'/>  
				</TD>
		       <TD align="center" class="caja7">
			 <c:out value='${pr.nombre_personal}'/>
				
				</TD>
		        <TD align="center" class="caja7">
				<c:choose>
		        <c:when test='${pr.estado_movimiento ==  "1"}'>
				Registrado
				 </c:when>
				  <c:when test='${pr.estado_movimiento ==  "2"}'>
				<FONT color="#FF0000" class="caja10"> Por Recibir </FONT>		 </c:when>
				  <c:when test='${pr.estado_movimiento ==  "3"}'>
				  <FONT  color="#00CC66" class="caja11">  Recibido </FONT>		 </c:when>
				  <c:when test='${pr.estado_movimiento ==  "4"}'>
				 Archivado
				 </c:when>
				  <c:when test='${pr.estado_movimiento ==  "5"}'>
				 Derivado
				 </c:when>
				  <c:when test='${pr.estado_movimiento ==  "6"}'>
				 Preliquidado
				 </c:when>
				  <c:when test='${pr.estado_movimiento ==  "7"}'>
				 Liquidado
				 </c:when>
		        <c:otherwise>    
				Entregado
				   </c:otherwise>
		    </c:choose>    
				
				</TD>
		        <TD align="center" class="caja7">
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
				  Entrada
				   </c:otherwise>
		    	</c:choose>    
				
				</TD>
		        <TD align="center" class="caja7">
				<c:out value='${pr.fecha_movimiento}'/>&nbsp;
				<c:out value='${pr.hora_movimiento}'/>
				</TD>
		        <TD align="center" class="caja7"><c:out value='${pr.observa_movimiento}'/></TD>
				<TD align="center" class="caja7"><c:out value='${pr.fecha_rpta}'/></TD>
		        <TD align="center" class="caja7">
				 <c:choose>
				        <c:when test='${pr.fecha_rpta !=  ""}'>
							 <c:if test="${verde == '1'}" > 
										   <img src="img/verde.gif" width="20"></c:if>
							 <c:if test="${amarillo == '2'}" > 
								  <img src="img/amarillo.gif" width="20"></c:if>
							 <c:if test="${rojo == '3'}" > 
								   <img src="img/atencion.gif" width="20"></c:if>
						 </c:when>
						<c:otherwise> 
								  ------
						</c:otherwise>
					    </c:choose>
				</TD>
				
		      </TR>
		      </c:forEach> </c:when>   </c:choose> 
			  <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
			   
		</TABLE>
		</c:otherwise> 
		</c:choose>
		
		
		</TD>
		</TR>
		</TABLE>
					  </div>
					 </div>
				</div>

		</form>

</body>
</html>
<% } %>