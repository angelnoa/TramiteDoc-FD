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
<HEAD>
  
    <TITLE>Documentos pendientes por Cerrar</TITLE>
  	    <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
        <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">

<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<script language="JavaScript" src="js/avatec.js"></script>
<SCRIPT LANGUAGE=javascript src="js/prototype-1.4.0.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/scriptaculous.js"></SCRIPT>

<SCRIPT LANGUAGE=javascript src="js/ajaxtags.js"></SCRIPT>
<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>

<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>

<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
<script>
function quitarguion(valor){
   arreglo = new Array();
   arreglo = valor.split("-");
   var retorno = arreglo[0];
   return retorno;
}

function armarMensaje(docs){

var mensaje = "";
if(docs.length==1){
 mensaje +=docs[0]+" (1 documento)";
}else if(docs.length>1){
for(var i=0;i<docs.length;i++){
if(i==0){
  mensaje += docs[i];
}else{
  mensaje += ","+docs[i];
}
 
}
 mensaje +=" ("+ docs.length+" documentos)";
}

return mensaje;
}

function enviar(f,cual){


todos = new Array();
docs=new Array();
 for (var i = 0, total = f[cual].length; i < total; i++)
 {  
     if (f[cual][i].checked) 
        { todos[todos.length] = f[cual][i].value;
          docs[docs.length]=quitarguion(f[cual][i].value);
         }
 }  
 var text = armarMensaje(docs); 

 f.submit();
 
 opener.mostrarDatos(text);
 window.close();
}
function buscar(){
var nombre = document.getElementById('nombre');
var codigo = document.getElementById('codigo');

var strURL = "Busquedas.do?tipo=instituciones";
strURL += "&codigo="+codigo.value+"&nombre="+nombre.value;
	 
document.getElementById("ifra_frame_listas").Document.location=strURL;

}
function cargar(){

var nombre = document.getElementById('nombre');
nombre.focus();
}
</script>
</HEAD>
<BODY bgColor="#F0F5FB" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" onLoad="javascript:cargar();">
<form action="Busquedas.do">
<table border="0">
  <!--DWLayoutTable-->
  <tr>
    <td height="10" colspan="2" class="label1">Documentos Pendientes Por Cerrar</td>
  </tr>
 <tr>
    <td height="5" colspan="2" bgcolor="BAD0EB"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top"><div id="scroll_pendientes">
      <table width="653" border="0" >
        <!--DWLayoutTable-->
        <tr class="tablaGrayBorde" height="5">
          <td width="10"  ><input type="hidden" name="tipo" value="seleccionar" /></td>
          <td width="32">Nro Reg. </td>
          <td width="119">Nro Documento </td>
          <td width="228">Asunto</td>
          <td width="151">Area</td>
          
        </tr>
        <c:forEach items="${FFormMantPrecerrar.paquete}" var="doc">
          <tr class="calendar">
            <td height="15" valign="middle"><label>
            <c:choose >
            <c:when test="${doc.seleccionado}">
            <input type="checkbox" name="documento" checked="checked" value='<c:out value="${doc.codigo_documento}"/>-<c:out value="${doc.secuencia_movimiento}"/>'>
            </c:when>
            <c:otherwise>
            <input type="checkbox" name="documento" value='<c:out value="${doc.codigo_documento}"/>-<c:out value="${doc.secuencia_movimiento}"/>'>
            </c:otherwise>
            </c:choose>
              
            </label></td>
            <td ><c:out value="${doc.codigo_documento}"/></td>
            <td ><c:out value="${doc.numero_documento}"/></td>
            <td ><c:out value="${doc.asunto_documento}"/></td>
            <td ><c:out value="${doc.codigo_oficina}"/></td>            
          </tr>
        </c:forEach>
      </table>
    </div></td>
  </tr>
  <tr>
    <td height="10" colspan="2" valign="top" align="center"><input type="button" class="boton" onClick="javascript:enviar(this.form,'documento');" value="Enviar">
    <input name="Submit2" type="button" class="boton" onClick="window.close();" value="Cerrar"></td>
  </tr>
</table>
</form>
</BODY>
</HTML>
  <%}%>

