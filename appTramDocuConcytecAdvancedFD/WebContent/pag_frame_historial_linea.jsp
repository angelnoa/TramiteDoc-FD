<%@ include file="taglibs.jsp" %>
<HTML>
<fmt:setBundle basename="ApplicationResources" />
 <HEAD>
 <TITLE>pag_lista_documentos</TITLE>
 <LINK href="css/PlanSec.css" rel="stylesheet" type="text/css">
 <LINK href="css/main.css" type="text/css" rel="stylesheet">
 <link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />
 <SCRIPT type="text/javascript" src="js/latest.js"></SCRIPT>
 <SCRIPT language="JavaScript">
   function cambiovista(codigodoc,secuencia)
   {    

	 var strURL = "ConsultaDocumento.do?tipo=reportes";
		   strURL += "&documento="+codigodoc;
		   strURL += "&secuencia="+secuencia;
		   strURL += "&operacion="+"1";
		   strURL += "&h="+<%=System.currentTimeMillis()%>;
		   
       	  location.href=strURL;
   }
 </SCRIPT>
 <SCRIPT type=text/javascript>
$(document).ready(function() {
	$('tbody tr:even').addClass('alt');
	$('tbody tr')
		.hover(
			function() {
				$(this).addClass('over');
			},
			function() {
				$(this).removeClass('over');
			}
		)

	});
</SCRIPT>
 <script language=javascript>
var oldTR;
function Seleccionar(objTR)
{    

	objTR.className='seleccionado'; //Para que pinte la fila

	
     if (typeof(oldTR)=='object') 

		 oldTR.className = ''; //limpio el color de la fila
		
    oldTR = objTR;
    var strTipoDoc = objTR.capturafila;
    var strNumDoc = objTR.numerodocumento;
	document.form_datos.valor_fila.value = strTipoDoc;  
	
}

</script>
<SCRIPT type=text/javascript>
 
function uno(src,color_entrada) { 
    src.bgColor=color_entrada;src.style.cursor="hand"; 
} 
function dos(src,color_default) { 
    src.bgColor=color_default;src.style.cursor="default"; 
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
 <BODY  topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
 <FORM id="form_datos" name="form_datos" method="post" action="">

  <TABLE  border="0" align="center" cellpadding="0"  width="100%">
    <TR align="center" >
     
      <TD    align="center" class="TextFieldOn36">N&deg; Registro</TD>
	  <TD    align="center" class="TextFieldOn36">N&deg; Expediente</TD>
      <TD   align="center" class="TextFieldOn36" >N&deg;  Documento</TD>
	  <TD   align="center" class="TextFieldOn36" >N&deg;  Envio</TD>
	  <TD    align="center" valign="middle" class="TextFieldOn36">Procedencia</TD>
      <TD    align="center" valign="middle" class="TextFieldOn36" >Origen</TD>
      
      <TD   align="center" class="TextFieldOn36">Destino</TD>
    
      <TD    align="center" valign="middle" class="TextFieldOn36">Estado</TD>
      <TD   align="center" class="TextFieldOn36">Naturaleza</TD>
      <TD   align="center" class="TextFieldOn36">Fecha.Doc&nbsp;/&nbsp;Hora</TD>
      <TD align="center"  class="TextFieldOn36"> Asunto
      </TD>
      
	  <TD  align="center"   class="TextFieldOn36">Ult.Usuario</TD>
  </TR>
   					 <c:choose> 
                              
                              <c:when test='${not empty rs_listaHistorial}'> 
                              <c:forEach items='${rs_listaHistorial}' var='pr'> 
	  <TR  class="tablepar" align="center"  >
	   
        
      <TD align="center"  class="caja2"><c:out value='${pr.codigo_documento}'/> 
      </TD>
		 <TD align="center" class="caja2">
		<c:out value='${pr.codigo_expediente}'/>
		</TD>
        <TD align="center" class="caja2">
		<c:out value='${pr.descripcion_tipo}'/><c:out value='${pr.numero_documento}'/>
		
		</TD>
		 <TD align="center" class="caja2">
		<c:out value='${pr.codigo_recepcion}'/>
		
		</TD>
		<TD align="center" valign="middle" class="caja2">
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
        <TD align="center" class="caja2">
		 <c:choose>
        <c:when test='${pr.naturaleza_documento ==  "I"}'>
		
		<c:choose>
        <c:when test='${pr.oficina_origen ==  "1"}'>
		MESA DE PARTES
		 </c:when>
		  <c:when test='${pr.oficina_origen ==  "2"}'>
		 SECRETARIA GENERAL
		 </c:when>
		  <c:when test='${pr.oficina_origen ==  "3"}'>
		 PRESIDENCIA
		 </c:when>
          <c:when test='${pr.oficina_origen ==  "4"}'>
		 OFICINA GENERAL DE ADMINISTRACION
		 </c:when>
          <c:when test='${pr.oficina_origen ==  "5"}'>
		 DIRECCION SISTEMAS INFORMACION Y COMUNICACION
		 </c:when>
          <c:when test='${pr.oficina_origen ==  "6"}'>
		 OFICINA ASESORIA JURIDICA
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "7"}'>
		 OFICINA PLANEAMIENTO Y PLANIFICACION
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "8"}'>
		FONDECYT
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "9"}'>
		 OFICINA CONTROL INTERNO
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "10"}'>
		 DIRECCION POLITICAS Y PLANES
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "11"}'>
		 DIRECCION ARTICULACION Y GESTION
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "12"}'>
		 DIRECCION CIENCIA Y TECNOLOGIA
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "13"}'>
		 DIRECCION PROSPECTIVA E INNOVACION TECNOLOGICA
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "14"}'>
		 PRESIDENCIA
		 </c:when>
        <c:otherwise>    
		OTROS
		   </c:otherwise>
    </c:choose>    
		
		
		 </c:when>
		 <c:when test='${pr.naturaleza_documento ==  "E"}'>
		
		<c:choose>
        <c:when test='${pr.oficina_origen ==  "1"}'>
		MESA DE PARTES
		 </c:when>
		  <c:when test='${pr.oficina_origen ==  "2"}'>
		 SECRETARIA GENERAL
		 </c:when>
		  <c:when test='${pr.oficina_origen ==  "3"}'>
		 PRESIDENCIA
		 </c:when>
          <c:when test='${pr.oficina_origen ==  "4"}'>
		 OFICINA GENERAL DE ADMINISTRACION
		 </c:when>
          <c:when test='${pr.oficina_origen ==  "5"}'>
		 DIRECCION SISTEMAS INFORMACION Y COMUNICACION
		 </c:when>
          <c:when test='${pr.oficina_origen ==  "6"}'>
		 OFICINA ASESORIA JURIDICA
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "7"}'>
		 OFICINA PLANEAMIENTO Y PLANIFICACION
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "8"}'>
		FONDECYT
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "9"}'>
		 OFICINA CONTROL INTERNO
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "10"}'>
		 DIRECCION POLITICAS Y PLANES
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "11"}'>
		 DIRECCION ARTICULACION Y GESTION
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "12"}'>
		 DIRECCION CIENCIA Y TECNOLOGIA
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "13"}'>
		 DIRECCION PROSPECTIVA E INNOVACION TECNOLOGICA
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "14"}'>
		 PRESIDENCIA
		 </c:when>
        <c:otherwise>    
		OTROS
		   </c:otherwise>
    </c:choose>  
		
		 </c:when>
        <c:otherwise> 
		   
		<c:choose>
        <c:when test='${pr.oficina_origen ==  "1"}'>
		MESA DE PARTES
		 </c:when>
		  <c:when test='${pr.oficina_origen ==  "2"}'>
		 SECRETARIA GENERAL
		 </c:when>
		  <c:when test='${pr.oficina_origen ==  "3"}'>
		 PRESIDENCIA
		 </c:when>
          <c:when test='${pr.oficina_origen ==  "4"}'>
		 OFICINA GENERAL DE ADMINISTRACION
		 </c:when>
          <c:when test='${pr.oficina_origen ==  "5"}'>
		 DIRECCION SISTEMAS INFORMACION Y COMUNICACION
		 </c:when>
          <c:when test='${pr.oficina_origen ==  "6"}'>
		 OFICINA ASESORIA JURIDICA
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "7"}'>
		 OFICINA PLANEAMIENTO Y PLANIFICACION
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "8"}'>
		FONDECYT
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "9"}'>
		 OFICINA CONTROL INTERNO
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "10"}'>
		 DIRECCION POLITICAS Y PLANES
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "11"}'>
		 DIRECCION ARTICULACION Y GESTION
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "12"}'>
		 DIRECCION CIENCIA Y TECNOLOGIA
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "13"}'>
		 DIRECCION PROSPECTIVA E INNOVACION TECNOLOGICA
		 </c:when>
         <c:when test='${pr.oficina_origen ==  "14"}'>
		 PRESIDENCIA
		 </c:when>
        <c:otherwise>    
		OTROS
		   </c:otherwise>
    </c:choose>     
		
		   </c:otherwise>
    </c:choose>    
		
		       
		</TD>
        
        <TD align="center" class="caja2">
		<c:choose>
        <c:when test='${pr.naturaleza_documento ==  "I"}'>
			<c:choose>
        <c:when test='${pr.oficina_deriva ==  "1"}'>
		MESA DE PARTES
		 </c:when>
		  <c:when test='${pr.oficina_deriva ==  "2"}'>
		 SECRETARIA GENERAL
		 </c:when>
		  <c:when test='${pr.oficina_deriva ==  "3"}'>
		 PRESIDENCIA
		 </c:when>
          <c:when test='${pr.oficina_deriva ==  "4"}'>
		 OFICINA GENERAL DE ADMINISTRACION
		 </c:when>
          <c:when test='${pr.oficina_deriva ==  "5"}'>
		 DIRECCION SISTEMAS INFORMACION Y COMUNICACION
		 </c:when>
          <c:when test='${pr.oficina_deriva ==  "6"}'>
		 OFICINA ASESORIA JURIDICA
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "7"}'>
		 OFICINA PLANEAMIENTO Y PLANIFICACION
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "8"}'>
		FONDECYT
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "9"}'>
		 OFICINA CONTROL INTERNO
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "10"}'>
		 DIRECCION POLITICAS Y PLANES
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "11"}'>
		 DIRECCION ARTICULACION Y GESTION
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "12"}'>
		 DIRECCION CIENCIA Y TECNOLOGIA
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "13"}'>
		 DIRECCION PROSPECTIVA E INNOVACION TECNOLOGICA
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "14"}'>
		 PRESIDENCIA
		 </c:when>
        <c:otherwise>    
		----
		   </c:otherwise>
    </c:choose>    
		 </c:when>
		  <c:when test='${pr.naturaleza_documento ==  "W"}'>
		<c:choose>
        <c:when test='${pr.oficina_deriva ==  "1"}'>
		MESA DE PARTES
		 </c:when>
		  <c:when test='${pr.oficina_deriva ==  "2"}'>
		 SECRETARIA GENERAL
		 </c:when>
		  <c:when test='${pr.oficina_deriva ==  "3"}'>
		 PRESIDENCIA
		 </c:when>
          <c:when test='${pr.oficina_deriva ==  "4"}'>
		 OFICINA GENERAL DE ADMINISTRACION
		 </c:when>
          <c:when test='${pr.oficina_deriva ==  "5"}'>
		 DIRECCION SISTEMAS INFORMACION Y COMUNICACION
		 </c:when>
          <c:when test='${pr.oficina_deriva ==  "6"}'>
		 OFICINA ASESORIA JURIDICA
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "7"}'>
		 OFICINA PLANEAMIENTO Y PLANIFICACION
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "8"}'>
		FONDECYT
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "9"}'>
		 OFICINA CONTROL INTERNO
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "10"}'>
		 DIRECCION POLITICAS Y PLANES
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "11"}'>
		 DIRECCION ARTICULACION Y GESTION
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "12"}'>
		 DIRECCION CIENCIA Y TECNOLOGIA
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "13"}'>
		 DIRECCION PROSPECTIVA E INNOVACION TECNOLOGICA
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "14"}'>
		 PRESIDENCIA
		 </c:when>
        <c:otherwise>    
		----
		   </c:otherwise>
    </c:choose>  
		 </c:when>
        <c:otherwise>    
		<c:choose>
       <c:when test='${pr.oficina_deriva ==  "1"}'>
		MESA DE PARTES
		 </c:when>
		  <c:when test='${pr.oficina_deriva ==  "2"}'>
		 SECRETARIA GENERAL
		 </c:when>
		  <c:when test='${pr.oficina_deriva ==  "3"}'>
		 PRESIDENCIA
		 </c:when>
          <c:when test='${pr.oficina_deriva ==  "4"}'>
		 OFICINA GENERAL DE ADMINISTRACION
		 </c:when>
          <c:when test='${pr.oficina_deriva ==  "5"}'>
		 DIRECCION SISTEMAS INFORMACION Y COMUNICACION
		 </c:when>
          <c:when test='${pr.oficina_deriva ==  "6"}'>
		 OFICINA ASESORIA JURIDICA
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "7"}'>
		 OFICINA PLANEAMIENTO Y PLANIFICACION
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "8"}'>
		FONDECYT
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "9"}'>
		 OFICINA CONTROL INTERNO
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "10"}'>
		 DIRECCION POLITICAS Y PLANES
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "11"}'>
		 DIRECCION ARTICULACION Y GESTION
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "12"}'>
		 DIRECCION CIENCIA Y TECNOLOGIA
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "13"}'>
		 DIRECCION PROSPECTIVA E INNOVACION TECNOLOGICA
		 </c:when>
         <c:when test='${pr.oficina_deriva ==  "14"}'>
		 PRESIDENCIA
		 </c:when>
        <c:otherwise>    
		----
		   </c:otherwise>
    </c:choose>  
		   </c:otherwise>
    </c:choose>    
		</TD>
       
        <TD align="center" class="caja2">
		<c:choose>
        <c:when test='${pr.estado_movimiento ==  "1"}'>
		Registrado
		 </c:when>
		  <c:when test='${pr.estado_movimiento ==  "2"}'>
		<FONT color="#FF0000" class="caja5"> Por Recibir </FONT>
		 </c:when>
		  <c:when test='${pr.estado_movimiento ==  "3"}'>
		  <FONT  color="#00CC66" class="caja6">  Recibido </FONT>
		 </c:when>
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
        <TD align="center" class="caja2">
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
        <TD align="center" class="caja2">
		<c:out value='${pr.fecha_movimiento}'/>&nbsp;
		<c:out value='${pr.hora_movimiento}'/>
		</TD>
        <TD align="center" class="caja2"><c:out value='${pr.asunto_documento}'/></TD>
       
		<TD align="center" class="caja2">
		<c:out value='${pr.ultimo_usuario}'/>
		</TD>
      </TR>
      </c:forEach> </c:when>   </c:choose> 
	  <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
	   
</TABLE>
</FORM>
 </BODY>

</HTML>
