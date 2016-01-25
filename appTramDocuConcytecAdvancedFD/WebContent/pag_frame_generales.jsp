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
     
      <TD align="center" class="TextFieldOn36"><font color="#FFFFFF">N&deg; Registro</font></TD>
	  <TD align="center" class="TextFieldOn36"><font color="#FFFFFF">N&deg; Expediente</font></TD>
      <TD align="center" valign="middle" class="TextFieldOn36" ><font color="#FFFFFF">Procedencia</font></TD>
	  <TD align="center" valign="middle" class="TextFieldOn36" ><font color="#FFFFFF">Fecha Registro</font></TD>
      <TD align="center" class="TextFieldOn36" ><font color="#FFFFFF">N&deg;  Documento</font></TD>
	  <TD align="center" class="TextFieldOn36"><font color="#FFFFFF">Oficina</font></TD>
      <TD align="center" class="TextFieldOn36"><font color="#FFFFFF">Oficina Origen</font></TD>
      <TD align="center" class="TextFieldOn36"><font color="#FFFFFF">Oficina Destino</font></TD>
       <TD align="center"  class="TextFieldOn36"><font color="#FFFFFF"> Estado</font>      </TD>
	        

	  <TD align="center"   class="TextFieldOn36"><font color="#FFFFFF">Ult.Usuario</font></TD>
  </TR>
   					 <c:choose> 
                              
                              <c:when test='${not empty rs_listaDocumentos}'> 
                              <c:forEach items='${rs_listaDocumentos}' var='pr'> 
	  <TR  class="tablepar" align="center"  >
	   
        
      <TD align="center"  ><A  title="Ver Detalle"  target="_parent"  href='ConsultaDocumento.do?tipo=reportes&documento=<c:out value='${pr.codigo_documento}'/>&secuencia=<c:out value='${pr.secuencia_movimiento}'/>&codigo_oficina=<c:out value='${pr.codigo_oficina}'/>' 
        > <font color="#0000FF" class="caja8"><c:out value='${pr.codigo_documento}'/></font> </A> 
      </TD>
		 <TD align="center" class="caja2">
		 <A  title="Ver Detalle" class="caja8" target="_parent"  href='ConsultaDocumento.do?tipo=reportes&documento=<c:out value='${pr.codigo_documento}'/>&secuencia=<c:out value='${pr.secuencia_movimiento}'/>&codigo_oficina=<c:out value='${pr.codigo_oficina}'/>' 
        > <font color="#0000FF" class="caja8"><c:out value='${pr.codigo_expediente}'/></font> </A> 
		
		</TD>
        <TD align="center" class="caja2">
		<c:choose>
        <c:when test='${pr.naturaleza_documento ==  "I"}'>
		 <A  title="Ver Detalle" class="caja8" target="_parent"  href='ConsultaDocumento.do?tipo=reportes&documento=<c:out value='${pr.codigo_documento}'/>&secuencia=<c:out value='${pr.secuencia_movimiento}'/>&codigo_oficina=<c:out value='${pr.codigo_oficina}'/>' 
        > <font color="#0000FF" class="caja8"><c:out value='${pr.desc_origen}'/></font> </A> 
		
		 </c:when>
		 <c:when test='${pr.naturaleza_documento ==  "S"}'>
		 <A  title="Ver Detalle" class="caja8" target="_parent"  href='ConsultaDocumento.do?tipo=reportes&documento=<c:out value='${pr.codigo_documento}'/>&secuencia=<c:out value='${pr.secuencia_movimiento}'/>&codigo_oficina=<c:out value='${pr.codigo_oficina}'/>' 
        > <font color="#0000FF" class="caja8"><c:out value='${pr.desc_origen}'/></font> </A> 
		 </c:when>
        <c:otherwise>    
		 <A  title="Ver Detalle" class="caja8" target="_parent"  href='ConsultaDocumento.do?tipo=reportes&documento=<c:out value='${pr.codigo_documento}'/>&secuencia=<c:out value='${pr.secuencia_movimiento}'/>&codigo_oficina=<c:out value='${pr.codigo_oficina}'/>' 
        > <font color="#0000FF" class="caja8"><c:out value='${pr.desc_origen}'/></font> </A> 
		   </c:otherwise>
   		 </c:choose>    
		</TD>
		<TD align="center" class="caja2">
		 <A  title="Ver Detalle" class="caja8" target="_parent"  href='ConsultaDocumento.do?tipo=reportes&documento=<c:out value='${pr.codigo_documento}'/>&secuencia=<c:out value='${pr.secuencia_movimiento}'/>&codigo_oficina=<c:out value='${pr.codigo_oficina}'/>' 
        > <font color="#0000FF" class="caja8"><c:out value='${pr.fecha_registro}'/></font> </A> 
		
		 	  
		</TD>
        <TD align="center" class="caja2">
		 <A  title="Ver Detalle" class="caja8" target="_parent"  href='ConsultaDocumento.do?tipo=reportes&documento=<c:out value='${pr.codigo_documento}'/>&secuencia=<c:out value='${pr.secuencia_movimiento}'/>&codigo_oficina=<c:out value='${pr.codigo_oficina}'/>' 
        > <font color="#0000FF" class="caja8"><c:out value='${pr.numero_documento}'/></font> </A> 
		
		 	  
		</TD>
		 <TD align="center" class="caja2">
		<A  title="Ver Detalle" class="caja8" target="_parent"  href='ConsultaDocumento.do?tipo=reportes&documento=<c:out value='${pr.codigo_documento}'/>&secuencia=<c:out value='${pr.secuencia_movimiento}'/>&codigo_oficina=<c:out value='${pr.codigo_oficina}'/>' 
        > <font color="#0000FF" class="caja8"><c:out value='${pr.codigo_oficina}'/></font> </A> 
		
		</TD>
        <TD align="center" class="caja2">
		<A  title="Ver Detalle" class="caja8" target="_parent"  href='ConsultaDocumento.do?tipo=reportes&documento=<c:out value='${pr.codigo_documento}'/>&secuencia=<c:out value='${pr.secuencia_movimiento}'/>&codigo_oficina=<c:out value='${pr.codigo_oficina}'/>' 
        > <font color="#0000FF" class="caja8"><c:out value='${pr.oficina_origen}'/></font> </A> 
		
		</TD>
        <TD align="center" class="caja2">
		<A  title="Ver Detalle" class="caja8" target="_parent"  href='ConsultaDocumento.do?tipo=reportes&documento=<c:out value='${pr.codigo_documento}'/>&secuencia=<c:out value='${pr.secuencia_movimiento}'/>&codigo_oficina=<c:out value='${pr.codigo_oficina}'/>' 
        > <font color="#0000FF" class="caja8"><c:out value='${pr.oficina_deriva}'/></font> </A> 
		
		</TD>
     
       <TD align="center" class="caja2"><c:out value='${pr.estado_movimiento}'/></TD>
	    
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
