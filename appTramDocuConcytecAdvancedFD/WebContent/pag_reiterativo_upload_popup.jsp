<%@ include file="taglibs.jsp"%>
<%
	//Verifica que exista una sesion
	String nombreusuario = String.valueOf(session
			.getAttribute("nombreusuario"));
	//System.out.println("el nombre de usuario es.."+nombreusuario);
	if (nombreusuario == null || nombreusuario.equals("null")) {
%> 
<SCRIPT language="Javascript">
parent.document.location ="pag_expiracion.jsp";
</SCRIPT>
<%
	} else {
%>

<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD><TITLE>Subir Archivo Adjunto</TITLE>
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

function eliminarArchivo(i){
	
}
function cerrar(codigo){
     
   window.close();
   window.opener.MostrarAdjunto(codigo);	
	
}

function Aceptar(){
	   
	document.forms[0].action = 'UploadArchivo.do';
	document.forms[0].opcion.value = 'adjuntar';
	document.forms[0].submit();	

}
</SCRIPT>
<BODY class="def"  leftMargin="0"  topMargin="0" rightMargin="0" bottomMargin="0" marginwidth="0" marginheight="0">

<form id="form_datos" name="form_datos" ENCTYPE="multipart/form-data" METHOD="POST" ACTION="">
<INPUT  type="hidden" id="opcion" name="opcion" value="">
<input type="hidden" name="codigo_documento" value="<c:out value='${FFormUploadArchivo.codigo_documento}'/>" />
  <TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="TextFieldOn24" >
    <TR> 
      <TD width="1249" height="100%" valign="top"  > <CENTER>
          <TABLE width="100%" cellpadding="0" cellspacing="0">
            <TR> 
              <TD width="978" height="49" valign="middle" bgcolor="#BAD0EB" class="titulolistado01" ><FONT size="2">ADJUNTAR y/o ELIMINAR ARCHIVOS
                </FONT></TD>
            </TR>
          </TABLE>
          <TABLE width="100%" border="0"  >
            <!--DWLayoutTable-->
         
            <TR> 
              <TD width="972" height="51" valign="top"> <table width="100%" border="0" class="Celda02Dir1">
                <!--DWLayoutTable-->
               
                  <tr> 
                    <TD  width="247" height="45" align="right" valign="middle" class="Columna">Archivo: 
                    &nbsp;&nbsp;&nbsp;</TD>
                    <TD  width="595" valign="middle">     <input size="50" type="file" name="theFile" class="caja"></TD>
                    <TD  width="116" valign="top"><!--DWLayoutEmptyCell-->&nbsp;</TD>
                  </tr>
				  <tr> 
                    <TD  height="30" colspan="3" align="left" valign="middle">&nbsp;&nbsp;  

					                    <input  type="button" class="boton2"  name="button" value="Aceptar"  onClick="javascript:Aceptar()">
						  </TD>
                  </tr>
                </table></TD>
            </TR>
           
			<TR> 
              <TD height="36" valign="top"> <table width="100%" border="0"  class="asdf">
                
                  <tr> 
                    <TD  width="100%" height="50"  valign="top"> 
                    
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
					  <!--DWLayoutTable-->
    <tr > 
      <td width="60" height="22" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">C&oacute;digo      </td>
      <td width="619" align="center" valign="middle" class="Columna10"  background="img/fondoplomo8.gif">Nombre del Archivo      </td>
	  <td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Adjuntos</td>
    
    <td width="68" valign="top" background="img/fondoplomo8.gif"><!--DWLayoutEmptyCell-->&nbsp;</td>
    </tr>
    
      <c:forEach items='${FFormUploadArchivo.archivos}' var='pr' varStatus='c'> 
   
      <tr><td height="18" align="center" valign="middle"  class="Columna"> <font color="#000000"> 
        <c:out value="${c.count}"></c:out> 
        </font> </td>
      <td align="left" valign="middle" class="Columna">&nbsp;&nbsp;&nbsp; 
        <c:out value='${pr.nombre_archivo}'/> </td>
  
		   <td align="center" valign="middle" class="Columna"><c:choose>
		     <c:when test='${pr.nombre_archivo ==  ""}'>
		       ------		  </c:when>
		     <c:otherwise>
  <a target="_blank" href="ListaDocumentos.do?tipo=uploaddoc&nombre_archivo=<c:out value='${pr.nombre_archivo}'/>&iddetupload=0&id=<c:out value='${c.count-1}'/>&fileroute=T">Ver Archivo</a>	</c:otherwise>
		            </c:choose>	  </td>
    <td align="center" valign="middle">
    <A id="A1" target="_parent" href="UploadArchivo.do?opcion=eliminar&id=<c:out value='${c.count-1}'/>&id_detalle=<c:out value='${c.count-1}'/>&codigo_documento=<c:out value='${FFormUploadArchivo.codigo_documento}'/> ">
        <font color="#0000FF"><img src="img/trash.png" border="0" height="24" alt="Eliminar Linea."></font> </A></td>
      </tr>
    </c:forEach>
     
    <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
  </table>
					</TD>
                  </tr>
              </table></TD>
            </TR>
            <TR>
              <TD height="30" align="center" valign="bottom" >
			  <input  type="button" class="boton2"  name="button" value="Cerrar"  onClick="javascript:cerrar('<c:out value='${FFormUploadArchivo.contador}'/>');"></TD>
            </TR>
          </TABLE>
        </CENTER></TD>
    </TR>
  </TABLE>
</FORM>
</BODY></HTML>
<%
	}
%>