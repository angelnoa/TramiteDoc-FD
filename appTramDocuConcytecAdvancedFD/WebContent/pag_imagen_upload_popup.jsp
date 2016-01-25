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
<HEAD><TITLE>Documentos Internos - Upload</TITLE>
<meta http-equiv="Expires" content="0">
<meta http-equiv="Last-Modified" content="0">
<meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
<meta http-equiv="Pragma" content="no-cache">

<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		<LINK href="css/estilo1.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/fstooltips.css">

<script type="text/javascript" src="<c:url value="js/scriptaculous.js"/>"></script>
<script type="text/javascript" src="<c:url value="js/overlibmws.js"/>"></script>
<script type="text/javascript" src="<c:url value="js/ajaxtags.js"/>"></script>
<SCRIPT LANGUAGE=javascript src="js/funciones.js"></SCRIPT>


<script language="javascript">
//mantiene una referencia a la ventana padre
var windowOpener;

function setOpener(){
	//setea la ventana padre
	windowOpener = window.opener;
}

function Cerrar(){
	windowOpener.Continuar();
	window.close();
}

function AgregarFoto(){
	   
	var extensiones_permitidas = new Array(".gif",".jpg",".jpeg"); 
	var mierror = ""; 
	var	permitida = false; 
				
	if (Validarcampovacio(document.form_datos.theFile.value) == false)
              {              
                   // alert("Ingresar Foto");
                    document.form_datos.theFile.focus();
                    return false;           
              } 
				 
		var fotografia=	document.form_datos.theFile.value;	 
		var extension = (fotografia.substring(fotografia.lastIndexOf("."))).toLowerCase();
		

		for (var i = 0; i < extensiones_permitidas.length; i++) { 
	         if (extensiones_permitidas[i] == extension) { 
	         permitida = true; 
	         break; 
	         } 
	      } 
		
		 if (!permitida) { 
	         mierror = "Comprueba la extensión de la Fotografía. \nSólo se puede subir archivos con extensiones: " + extensiones_permitidas.join();
	         alert (mierror);
	         document.form_datos.theFile.focus();
             return false;
	         
	       }else{ 
	    		 var ls_operacion = "UA";
  
	    			document.form_datos.action='AgregarImagen.do?operacion='+ls_operacion;
	    			document.form_datos.submit();
	        		return 1; 
	       } 

}

function AgregarPfx(){
	   
	var extensiones_permitidas = new Array(".pfx",".p12"); 
	var mierror = ""; 
	var	permitida = false; 
				
	if (Validarcampovacio(document.form_datos.theFilePfx.value) == false)
              {              
                   // alert("Ingresar Foto");
                    document.form_datos.theFilePfx.focus();
                    return false;           
              } 
				 
		var fotografia=	document.form_datos.theFilePfx.value;	 
		var extension = (fotografia.substring(fotografia.lastIndexOf("."))).toLowerCase();
		

		for (var i = 0; i < extensiones_permitidas.length; i++) { 
	         if (extensiones_permitidas[i] == extension) { 
	         permitida = true; 
	         break; 
	         } 
	      } 
		
		 if (!permitida) { 
	         mierror = "Comprueba la extensión del Certificado. \nSólo se puede subir archivos con extensiones: " + extensiones_permitidas.join();
	         alert (mierror);
	         document.form_datos.theFilePfx.focus();
             return false;
	         
	       }else{ 
	    		 var ls_operacion = "UAPx";
  
	    			document.form_datos.action='AgregarImagen.do?operacion='+ls_operacion;
	    			document.form_datos.submit();
	        		return 1; 
	       } 

}


function EliminarFoto(){
  	var ls_id_investigador=document.form_datos.id_investigador.value;
	document.form_datos.action='UploadFotoPath.do'+'?tipo=eliminar'+'&id_investigador='+ls_id_investigador;
	document.form_datos.submit();
}



 //http://www.telefonica.net/web2/blas-mar/index.html
	
</script>

<script type="text/javascript">

function ModificarEstado(nombre){
	
	var ls_operacion = "MOD";
	location = "AdjuntarDocumentoInterno.do?operacion="+ls_operacion+"&nombre="+nombre;
	
}

function eliminar_archivo(id){
if(confirm("Desea Eliminar este archivo?")){
location = "Busquedas.do?tipo=eli_archivo_interno_normal&id="+id;
}

} 

function ModificarEstadoArchivo(id){
	//if(confirm("Desea Eliminar este archivo?")){
	location = "Busquedas.do?tipo=modificar_estado_docinterno&id="+id;
	//}
}

function cerrar(codigo){
	//alert("codigo: " +codigo);
    window.close();
	window.opener.MostrarAdjunto(codigo);
		
}
function cerrarint(){
	//alert("codigo: " +codigo);
   // window.close();
	//window.opener.MostrarAdjunto(codigo);
	window.close();
}



function Modificar(){
    
		  var ls_operacion = "AMU";
		  var ls_cabecera_upload = document.getElementById("cabecera_upload").value;
		  var ls_cabecera_secuencia = document.getElementById("cabecera_secuencia").value;
		 /*alert("La operacion es");
		 alert(ls_operacion);*/
		
	document.form_datos.action='MantDocumento.do'+'?operacion='+ls_operacion+'&cabecera_upload='+ls_cabecera_upload+'&cabecera_secuencia='+ls_cabecera_secuencia;
	document.form_datos.submit();	
	
}
</SCRIPT>
</HEAD>
<body class="def"  leftMargin="0"  topMargin="0" rightMargin="0" bottomMargin="0" marginwidth="0" marginheight="0" onload="setOpener();">

<form id="form_datos" name="form_datos" ENCTYPE="multipart/form-data" METHOD="POST" ACTION="" >
<INPUT  type="hidden"  name="tipoVistaw" value="<c:out value='${tipoVista}'></c:out>" >
<INPUT  type="hidden"  name="oficina_pw" value="<c:out value='${oficina_p_}'></c:out>" >
<INPUT  type="hidden"  name="c_usuariow" value="<c:out value='${c_usuario_}'></c:out>" >

	<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="TextFieldOn24" >
    <TR> 
      <TD width="1249" height="100%" valign="top"  > <CENTER>
          <TABLE width="100%" cellpadding="0" cellspacing="0">
            
            <c:choose>
            <c:when test='${tipoVista == "OFI"}'>
            <TR> 
              <TD width="978" height="49" valign="middle" bgcolor="#BAD0EB" class="titulolistado01" ><FONT size="2">
              &nbsp;             SELLO PARA LA FIRMA DIGITAL 
                </FONT></TD>
                
            </TR>
           
            </c:when>
            <c:otherwise>
            <TR> 
              <TD width="978" height="49" valign="middle" bgcolor="#BAD0EB" class="titulolistado01" ><FONT size="2">&nbsp; 
                ARCHIVOS PARA LA FIRMA DIGITAL
                 
                </FONT></TD>
                
            </TR>
           
            </c:otherwise>
            </c:choose>

          </TABLE>
          
          
          
          <TABLE width="100%" border="0"  >
            <!--DWLayoutTable-->
         
            <TR> 
              <TD width="972" height="20" valign="top"> 
              <table width="100%" border="0" class="Celda02Dir1">
                <!--DWLayoutTable-->
              
          <c:choose>
            <c:when test='${tipoVista == "OFI"}'>
           
            <tr>
            <TD width="978" height="49" valign="middle"  class="textfield05" ><FONT size="2">
                &nbsp;Oficina: <c:out value='${oficina_p_}'></c:out>
                </FONT></TD>
            </tr>
             
            </c:when>
            <c:otherwise>
            
            <tr>
            <TD width="978" height="49" valign="middle"  class="textfield05" ><FONT size="2">&nbsp; 
                 &nbsp;Usuario: <c:out value='${c_usuario_}'></c:out>
                </FONT></TD>
            </tr>
            </c:otherwise>
            </c:choose>
          
          
				  
                </table></TD>
            </TR>
           
			<TR> 
              <TD height="36" valign="top"> <table width="100%" border="0"  class="asdf">
                
                  <tr> 
                    <TD  width="100%" height="50"  valign="top"> 

                 
	<table width="100%" border="1" cellpadding="0" cellspacing="0">
					  <!--DWLayoutTable-->
    <tr > 
      <td width="60" height="22" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">Tipo</td>
      <td width="619" align="center" valign="middle" class="Columna10"  background="img/fondoplomo8.gif">Nombre de Archivo      </td>
	  <td width="221" align="center" valign="middle"   background="img/fondoplomo8.gif" class="Columna10">Acciones</td>
    
    
    </tr>
    
      
   
      <tr><td height="18" align="left" valign="middle"  class="Columna"> <font color="#000000"> 
        Sello:
        </font> </td>
      <td align="left" valign="middle" class="Columna">&nbsp;&nbsp;&nbsp; 
        <input size="50" type="file" name="theFile" id="theFile" class="caja">
        
  		</td>
		   <td align="center" valign="middle" class="Columna">   
		   <c:choose>
		     <c:when test='${nombre_archivo ==  ""}'>
		       ------		  
		     </c:when>
		     <c:otherwise>
		     <input  type="button" class="boton2"  name="button" value="Subir Visado"  align="left" onClick="javascript:AgregarFoto()">
  			<!-- a target="_blank" href="ListaDocumentos.do?tipo=uploaddoc&nombre_archivo=<c:out value='${nombre_archivo}'/>&fileroute=<c:out value='${ruta_archivo}'/>">Ver Archivo</a-->	
  			</c:otherwise>
		    </c:choose>	  
		    </td>
  
      </tr>
       <c:choose>
            <c:when test='${tipoVista == "OFI"}'>
            </c:when>
            <c:otherwise>
            <tr><td height="18" align="left" valign="middle"  class="Columna"> <font color="#000000"> 
        Certificado: 
        </font> </td>
      <td align="left" valign="middle" class="Columna">&nbsp;&nbsp;&nbsp; 
        <input size="50" type="file" name="theFilePfx" id="theFilePfx" class="caja">
        
        
        </td>
  
		   <td align="center" valign="middle" class="Columna">   <c:choose>
		     <c:when test='${nombre_archivo ==  ""}'>
		       ------		  
		     </c:when>
		     <c:otherwise>
		     <input  type="button" class="boton2"  name="button" value="Subir Certificado" align="left" onClick="javascript:AgregarPfx()">
  			<!-- a target="_blank" href="ListaDocumentos.do?tipo=uploaddoc&nombre_archivo=<c:out value='${nombre_archivo}'/>&fileroute=<c:out value='${ruta_archivo}'/>">Ver Archivo</a-->	
  			</c:otherwise>
		            </c:choose>	  </td>
  
      </tr>
            </c:otherwise>
        </c:choose>
      

    
  </table>

  
  
					</TD>
                  </tr>
              </table></TD>
            </TR>
            <TR>
              <TD height="30" align="center" valign="bottom" >
              <input  type="button" class="boton2"  name="button" value="Cerrar"  onClick="javascript:Cerrar()"></TD>
            </TR>
          </TABLE>
        </CENTER></TD>
    </TR>
  </TABLE>
	
	   	
       
</FORM>

</BODY></HTML>

<% } %>