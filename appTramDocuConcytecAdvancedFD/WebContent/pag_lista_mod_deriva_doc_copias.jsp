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
<fmt:setBundle basename="ApplicationResources" />
<HEAD><TITLE>Lista Documentos - Copia Oficinas</TITLE>
<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		<LINK href="css/estilo1.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/fstooltips.css">
</HEAD>
<script type="text/javascript" src="<c:url value="js/prototype-1.4.0.js"/>"></script>
<script type="text/javascript" src="<c:url value="js/scriptaculous.js"/>"></script>
<script type="text/javascript" src="<c:url value="js/overlibmws.js"/>"></script>
<script type="text/javascript" src="<c:url value="js/ajaxtags.js"/>"></script>
<SCRIPT LANGUAGE=javascript src="js/funciones.js"></SCRIPT>
<SCRIPT language="javascript">
function cerrar(codigo){
     
       //alert(codigo);
	  //var ls_operacion = "MA";
		 // alert(ls_operacion);
	//document.form_datos.action='MantDocumento.do'+'?operacion='+ls_operacion;
	//document.form_datos.submit();
	if(codigo==""){
	window.close();

	}else {
	window.opener.MostrarAdjunto(codigo);	
	}
}

function Aceptar(){
    
		  var ls_operacion = "N";
		 // alert(ls_operacion);
		 var ls_codigo_documento = document.getElementById("codigo_documento").value;
		 var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
		 var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;
		   
	document.form_datos.action='MantDerivar.do'+'?operacion='+ls_operacion+'&tipo=uploadficheros'+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&secuencia_movimiento='+ls_secuencia_movimiento;
	document.form_datos.submit();	
			//window.close();
}
function Modificar(){
    
		  var ls_operacion = "M";
		 /*alert("La operacion es");
		 alert(ls_operacion);*/
		
	document.form_datos.action='MantUpload.do'+'?operacion='+ls_operacion+'&tipo=P';
	document.form_datos.submit();	
	
}
</SCRIPT>
<BODY class="def"  leftMargin="0"  topMargin="0" rightMargin="0" bottomMargin="0" marginwidth="0" marginheight="0">

<form id="form_datos" name="form_datos" ENCTYPE="multipart/form-data" METHOD="POST" ACTION="">
<INPUT  type="hidden" id="operacion" name="operacion" value="P">
<input type="hidden" name="codigo_documento" id="codigo_documento" value="<c:out value='${codigo_documento}'/>" >
<input type="hidden" name="codigo_expediente" id="codigo_expediente" value="<c:out value='${codigo_expediente}'/>" >
<input type="hidden" name="secuencia_movimiento" id="secuencia_movimiento" value="<c:out value='${secuencia_movimiento}'/>" >
  <TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="TextFieldOn24" >
    <TR> 
      <TD width="1249" height="100%" valign="top"  > <CENTER>
          <TABLE width="100%" cellpadding="0" cellspacing="0">
            <TR> 
              <TD width="100%" height="49" valign="middle" bgcolor="#BAD0EB" class="titulolistado01" ><FONT size="2">&nbsp; 
                LISTA DOCUMENTOS - COPIA OFICINAS
                </FONT></TD>
            </TR>
          </TABLE>
          <TABLE width="100%" border="0"  >
          
			<TR> 
              <TD height="36" valign="top"> <table width="100%" border="0"  class="asdf">
                
                  <tr> 
                    <TD  width="100%" height="50"  valign="top"> 
                    
					<table width="100%"  cellpadding="0" cellspacing="0">
					
    <tr > 
      <td width="27" height="26">&nbsp;</td>
	    <td width="69" align="center" valign="middle"  background="img/fondoplomo8.gif" class="label">N&deg; Registro </td>
	    <td width="103" align="center" valign="middle"  background="img/fondoplomo8.gif" class="label">N&deg; Expediente      </td>
		  <td width="305" align="center" valign="middle"  background="img/fondoplomo8.gif" class="label">N&deg; Documento      </td>
		    <td width="128" align="center" valign="middle"  background="img/fondoplomo8.gif" class="label">Oficina Origen      </td>
			  <td width="112" align="center" valign="middle"  background="img/fondoplomo8.gif" class="label">Oficina Destino      </td>
      <td width="83" align="center" valign="middle"  background="img/fondoplomo8.gif" class="label">Estado      </td>
	  <td width="121" align="center" valign="middle" background="img/fondoplomo8.gif" class="label">Acci&oacute;n</td>
    </tr>
    <c:choose>
     <c:when test='${not empty rs_lista_doc_deriva}'>
      <c:forEach items='${rs_lista_doc_deriva}' var='pr'> 
   
      <tr>
	  <td height="30" align="center" valign="middle" class="label"><c:out value='${pr.contador}'/> </td>
		 <td align="center" valign="middle"  class="label"> 
        <c:out value='${pr.codigo_documento}'/> 
          </td>
		 <td align="center" valign="middle"  class="label">
        <c:out value='${pr.codigo_expediente}'/> 
        </td>
		 <td align="center" valign="middle"  class="label">  
        <c:out value='${pr.numero_documento}'/> 
         </td>
		 <td align="center" valign="middle"  class="label">  
        <c:out value='${pr.oficina_origen}'/> 
       </td>
		 <td align="center" valign="middle"  class="label">  
        <c:out value='${pr.oficina_deriva}'/> 
         </td>
		 <td align="center" valign="middle"  class="label">  
        <c:out value='${pr.estado_movimiento}'/> 
        </td>
      <td align="center" valign="middle" class="label">
	 
	  <A id="A1" target="_parent" href="MantModDeriva.do?tipo=elimcopofi&codigo_documento=<c:out value='${pr.codigo_documento}'/>&secuencia_movimiento=<c:out value='${pr.secuencia_movimiento}'/>">
        <font color="#0000FF"><img src="img/trash.png" border="0" height="24" alt="Eliminar Linea."></font> </A></td>
      </tr>
    </c:forEach>
     </c:when> 
    </c:choose> 
    <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
  </table>
					</TD>
                  </tr>
              </table></TD>
            </TR>
            <TR>
              <TD height="30" align="center" valign="bottom" >
			  <input  type="button" class="boton2"  name="button" value="Cerrar"  onClick="javascript:cerrar('<c:out value='${contador}'/>');"></TD>
            </TR>
          </TABLE>
        </CENTER></TD>
    </TR>
  </TABLE>
</FORM>
</BODY></HTML>
<% } %>