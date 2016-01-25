<%@ include file="taglibs.jsp" %>
<html>
<head>
<title>
</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<LINK href="css/style.css" type="text/css" rel="stylesheet">
<LINK href="css/estilo1.css" type="text/css" rel="stylesheet"></head>
<SCRIPT type="text/javascript" src="js/latest.js"></SCRIPT>
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

//            alert(strTipoDoc)
document.form_datos.valor_fila.value = strTipoDoc;  
//document.form_datos.mas.value = strTipoDoc;  
//           alert("paso...");

    //Aqui abres una ventana, haces un submit, etc.
}
</script>
<SCRIPT type=text/javascript>
 
function uno(src,color_entrada) { 
    src.bgColor=color_entrada;src.style.cursor="hand"; 
} 
function dos(src,color_default) { 
    src.bgColor=color_default;src.style.cursor="default"; 
} 

function ingresar_nueva_marca(){
	
	  var params="";  
 	  var file = "";
          var ls_operacion = "N";
          var ls_codigo = "";
	  var window = "w_ventana";
  file = "IngresarMarca.do"+"?operacion="+ls_operacion + "&codigo="+ls_codigo ;
  childWindow=open(file,window,'scrollbars=yes,resizable=yes,width=640,height=500');  
  if (childWindow.opener == null) childWindow.opener = self;
}

function Seleccionar_Busqueda(codigo){
   
 document.form_datos.valor_fila.value = codigo; 
 alert(codigo);
 var ls_operacion="X";
  alert(ls_operacion);
/* var strURL="AbrirPopupBusq.do?accion=buscarmplanemp"
 strURL+="&codigo="+codigo;
 strURL+="&operacion="+ls_operacion;
 location=strURL   */


document.form_datos.action='AbrirPopupBusq.do?accion=buscarmplanemp'+'&operacion='+ls_operacion+'&codigo='+codigo;
	form_datos.submit();
	

/*var strURL="AbrirPopupBusq.do?accion=buscarmplanemp"	
 window.open(strURL,"","HEIGHT=400,WIDTH=580,scrollbars=yes")*/
 
	}
</SCRIPT>

<style>

.seleccionado td { background-color:#ddeeff }

</style>
<STYLE type=text/css>

TABLE {
	FONT-SIZE: 1em; WIDTH: 100%; BORDER-COLLAPSE: collapse; border-spacing: 0
}


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
<body   bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" >
	
<form  id="form_datos" name="form_datos" method="post" action="">
  <table width="100%" border="1" cellpadding="0" cellspacing="0">
    <!--DWLayoutTable-->
    <tr> 
      <td width="129" height="20" align="center" valign="middle" bgcolor="265ca6"><font color="#FFFFFF">Acci&oacute;n</font></td>
      <td width="129" height="22" align="center" valign="middle" bgcolor="265ca6"><font color="#FFFFFF">C&oacute;digo</font></td>
      <td width="552" align="center" valign="middle" bgcolor="265ca6"><font color="#FFFFFF">Descripci&oacute;n 
        </font></td>
      <td width="189" align="center" valign="middle" bgcolor="265ca6"><font color="#FFFFFF">Tipo 
        </font></td>
      <td colspan="2" align="center" valign="middle" bgcolor="265ca6"><font color="#FFFFFF">Acci&oacute;n 
        </font></td>
    </tr>
    <c:choose>
     <c:when test='${not empty listaPersonas}'>
      <c:forEach items='${listaPersonas}' var='pr'> 
    <tr> 
      <td height="25" align="center" valign="middle"> <b> <A id="A1"  target="_parent" href="ValidaPaginas.do?tipo=seleccion_dir&codigo_persona_dir=<c:out value='${pr.codigo_persona}'/>&nombre_persona_dir=<c:out value='${pr.nombre_persona}'/>&tipodoc_dir=<c:out value='${pr.tipo}'/>&operacion=D&accion_p=<c:out value='${accion_p}'/>"  > 
        <font color="#0000FF"> Seleccionar</font> </A></b> </td>
      <td align="center" valign="middle"> <font color="#000000"> <c:out value='${pr.contador}'/> 
        </font> </td>
      <td align="left" valign="middle">&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        <c:out value='${pr.nombre_persona}'/> </td>
      <td align="center" valign="middle"> <c:if test='${pr.tipo == "J"}'> Juridica 
        </c:if> 
         <c:if test='${pr.tipo == "N"}'> Natural 
        </c:if> 
        </td>
      <td width="71" align="center" valign="middle"> <b><A id="A1" target="_parent" href="DetalleFrame.do?tipo=dirigidos&codigo_persona=<c:out value='${pr.codigo_persona}'/>&nombre_persona=<c:out value='${pr.nombre_persona}'/>&tipodoc=<c:out value='${pr.tipo}'/>&operacion=M&accion=<c:out value='${accion_p}'/>"> 
        <font color="#0000FF">Modificar</font> </A></b> </td>
      <td width="69" align="center" valign="middle"><b> <A id="A1" target="_parent" href="DetalleFrame.do?tipo=dirigidos&codigo_persona=<c:out value='${pr.codigo_persona}'/>&nombre_persona=<c:out value='${pr.nombre_persona}'/>&tipodoc=<c:out value='${pr.tipo}'/>&operacion=E&accion=<c:out value='${accion_p}'/>"> 
        <font color="#0000FF"> Eliminar</font> </A></b></td>
    </tr>
    </c:forEach>
     </c:when> 
   
    
    </c:choose> 
    <INPUT   type="hidden" id="valor_fila" name="valor_fila" value="">
  </table>				
</form>
</body></html>