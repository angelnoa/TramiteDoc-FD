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
                            <td width="156" height="20" align="center" valign="middle" bgcolor="265ca6"><font color="#FFFFFF">Oficina</font></td>
      <td width="172" height="22" align="center" valign="middle" bgcolor="265ca6"><font color="#FFFFFF">Motivo</font></td>
      <td width="224" align="center" valign="middle" bgcolor="265ca6"><font color="#FFFFFF">Persona
      </font></td>
                            
      <td width="433" align="center" valign="middle" bgcolor="265ca6"><font color="#FFFFFF">Observaci&oacute;n
      </font></td>
    </tr>

                              <c:choose> 
                              
                              <c:when test='${not empty listaDerivacion}'> 
                              <c:forEach items='${listaDerivacion}' var='pr'> 
                          <tr> 
                          <td height="25" align="center" valign="middle" class="Columna">  
                               <c:out value='${pr.descripcion_oficina}'/>  </td>
                            <td height="25" align="center" valign="middle" class="Columna"> 
                             <c:out value='${pr.descripcion_motivo}'/> </td>
                            <td align="left" valign="middle" class="Columna">&nbsp;&nbsp;&nbsp;  
                              <c:out value='${pr.nombre_personal}'/> 
                              </td>
                            <td align="center" valign="middle" class="Columna"> 
                            <c:out value='${pr.observa_movimiento}'/> 
                           </td>
                          </tr>
                  </c:forEach> </c:when> 
                   
                  </c:choose> 
                         <INPUT  type="hidden" id="valor_fila" name="valor_fila" value="">
                        </table>				
</form>
</body></html>