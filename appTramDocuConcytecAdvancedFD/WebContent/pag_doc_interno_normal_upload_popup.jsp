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

<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
<link rel="stylesheet" href="css/modeloupload.css" type="text/css" />

<script type="text/javascript" src="<c:url value="js/scriptaculous.js"/>"></script>
<script type="text/javascript" src="<c:url value="js/overlibmws.js"/>"></script>
<script type="text/javascript" src="<c:url value="js/ajaxtags.js"/>"></script>
<SCRIPT LANGUAGE=javascript src="js/funciones.js"></SCRIPT>

<style type="text/css">
	
	h3 {
	 	text-transform:capitalize;
	  	font-family:Arial, Helvetica, sans-serif;
	}
	p {
		font-size:16px;
		font-family:Arial, Helvetica, sans-serif;
	}
	#capaVentana {
		border: 1px solid #000000;
		position:fixed;
		background:#f0f0f0;
		visibility:hidden;
		/*display:none;*/
		padding:0px;
		left: 50%; 
		width: 450px; 
		margin-left: -225px;
		top:100px;
		z-index:3;
		
	}
	#capaFondo1 {
		visibility:hidden;
		/*display:none;*/
		position:fixed;
		padding:0px;
		left:0px;
		top:0px;
		right:0px;
		bottom:0px;
		background-color: #808080;
		background-repeat:repeat;
		width:100%;
		height:100%;
		z-index:2;
		filter: alpha(opacity=50);
	opacity: 0.8;
-moz-opacity:0.8;
-webkit-opacity:0.8;
-o-opacity:0.8;
-ms-opacity:0.8;
	}
	#capaFondo2 {
		visibility:hidden;
		position:absolute;
		padding:0px;
		left:0px;
		top:0px;
		right:0px;
		bottom:0px;
		background-image:url(trans02.gif);
		background-repeat:repeat;
		width:100%;
		height:596px;
		z-index:2;
	}
	#capaFondo3 {
		visibility:hidden;
		position:absolute;
		padding:0px;
		left:0px;
		top:0px;
		right:0px;
		bottom:0px;
		background-image:url(trans03.gif);
		background-repeat:repeat;
		width:100%;
		height:596px;
		z-index:2;
	}
	
	#uploadcontactform {
	position: relative;
	/*top: 50%;*/
	float: center;
	left: 50%;
	width: 600px;
	
	/*margin-top: -200px;*/
	margin-left: -300px;
	

	background: #f0f0f0;
	/*overflow:auto;*/
	
	border: 1px solid #cccccc;
	-moz-border-radius: 7px;
	-webkit-border-radius: 7px;
	border-radius: 7px;	
	
	-moz-box-shadow: 2px 2px 2px #cccccc;
	-webkit-box-shadow: 2px 2px 2px #cccccc;
	box-shadow: 2px 2px 2px #cccccc;
	}
	
	#uploadcontainer
	{
		position: relative; 
		background: #F0F5FB;
	}
	
	#containerlistabusqueda {
		position:relative;
		float: center;
		top: 15%;
		left: 50%; 
		/*line-height: 120%;*/

	width: 1000px;
	margin-left: -500px;
	background: #FFFFFF;*
	overflow:auto;
	}
	
</style>

<style type="text/css">
.buttoncss{
	/*margin:10px 55px 10px 0px;*/
	/*font-weight: bold;*/
	/*line-height: 1;*/
	padding: 6px 10px;
	cursor:pointer;   
	color: #fff;
	text-align: center;
	/*text-shadow: 0 -1px 1px #64799e;*/
	
	/* Background gradient */
	background: #ffffff;
	background: -moz-linear-gradient(top, #ffffff 0%, #c4c4c4 100%);
	background: -webkit-gradient(linear, 0% 5%, 0% 100%, from(#ffffff), to(#c4c4c4));
	
	/* Border style */
  	border: 1px solid #6d6d6d;  
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
  
	/* Box shadow */
	/*-moz-box-shadow: inset 0 1px 0 0 #aec3e5;
	-webkit-box-shadow: inset 0 1px 0 0 #aec3e5;
	box-shadow: inset 0 1px 0 0 #aec3e5;*/
	
	-moz-box-shadow: 0px 2px 2px #7e7e7e;
	-webkit-box-shadow: 0px 2px 2px #7e7e7e;
	box-shadow: 0px 2px 2px #7e7e7e;
}

.buttoncss:hover {
	/*background: #848FB2;*/
    cursor: pointer;
	}
	
</style>

<script type="text/javascript">
onload=function(){
window.moveTo(0, 0);
window.resizeTo(screen.availWidth, screen.availHeight);
};

</script>   
    


<script language="javascript">

$(document).ready(function(){
	
	
	$('#botoncerrar').click(function() {
		
		Cerrar();
		
		//windowOpener.Continuar();
		//window.close();
	});
	
function Cerrar(){
	window.opener.Continuar();
	window.close();
}
	
$("#botoninvisible").mouseover(function(event){
    $("#botoninvisible").addClass("buttoncss");
 });
 $("#botoninvisible").mouseout(function(event){
    $("#botoninvisible").removeClass("buttoncss");
});
 
 $('#botoninvisible').click(function() {
		//$("#containerlista").css("visibility","visible");
	   	//$("#capaFondo1").css("visibility","visible");
	   	abrirVentana(1);
	});
	
	
 
 $( "#tabsbusqueda" ).tabs();
 
 $(function() {
	    $( "#dialog-confirm" ).dialog({
	      autoOpen: false,	
	      resizable: false,
	      height:180,
	      modal: true
	      
	    });
	  });
 
});
</script>
<script language="javascript">


	function abrirVentana(ventana)
	{
		if (ventana=="1")
		{
			document.getElementById("capaFondo1").style.visibility="visible";
			
		}
		document.getElementById("capaVentana").style.visibility="visible";
		document.form_datos.bAceptar.focus();
		
		return;
	}
	
	function cerrarVentana()
	{
		document.getElementById("capaFondo1").style.visibility="hidden";
		document.getElementById("capaVentana").style.visibility="hidden";
 //http://www.telefonica.net/web2/blas-mar/index.html
	}
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

function Aceptar(){
	var extensiones_permitidas = new Array(".pdf"); 
	var mierror = ""; 
	var	permitida = false; 
				
	if (Validarcampovacio(document.form_datos.fileupload.value) == false){              
		document.form_datos.fileupload.focus();
		return false;           
    } 
				 
	var fotografia=	document.form_datos.fileupload.value;	 
	var extension = (fotografia.substring(fotografia.lastIndexOf("."))).toLowerCase();
		
	for (var i = 0; i < extensiones_permitidas.length; i++) { 
	     if (extensiones_permitidas[i] == extension) { 
	         permitida = true; 
	         break; 
	      } 
	} 
		
		 if (!permitida) { 
	         mierror = "Comprueba la extensión del Documento. \nSólo se puede subir archivos con extensiones: " + extensiones_permitidas.join();
	         alert (mierror);
	         document.form_datos.fileupload.focus();
             return false;
	         
	       }else{ 
	   			var ls_operacion = "UA";
  	    	 	var nombre_archivo = document.getElementById("fileupload").value;
	   			document.form_datos.action='AdjuntarDocumentoInterno.do?operacion='+ls_operacion;
	   			document.form_datos.submit();	
	       		return 1; 
	       } 

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

function abrirVentana2(valor){
	var coddoc = valor;
	$( "#dialog-confirm" ).dialog( "open");

}
</SCRIPT>
</HEAD>
<body id="cuerpo"  bgcolor="#F0F5FB">
<form id="form_datos" name="form_datos" ENCTYPE="multipart/form-data" METHOD="POST" ACTION="">
<div id="dialog-confirm" title="Subir Documento" >
  		<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0; font-size : 8px; "></span></p>
  		El documento será eliminado de manera permanente.
</div>
<div id="capaVentana">
		
					<table cellpadding="1" cellspacing="0" border="0" width="450" >
					<tr>
				<td>  	</td>
				<td style="cursor:pointer;" height="24" width="24"
				onclick="cerrarVentana()"> <img src="img/close.png" alt="Cerrar Ventana" />
				</td>
				
			</tr>
					<tr>
						<td  align="center" valign="middle" class="label" >
							&nbsp;&nbsp;Archivo:&nbsp;<input size="50" type="file" name="fileupload" id="fileupload" class="caja">
							<br />
							<br/>
							<br/>
						</td>
					</tr>
					<tr>
						<td align="center" valign="middle">
														
						</td>
					</tr>
					<tr>
						<td align="center" valign="middle">
								<input  type="button" class="boton2"  name="button" value="Aceptar"  onClick="javascript:Aceptar()">
								<br/>
								<br/>
						</td>
					</tr>
					
					</table>
				
</div>
<div id="capaFondo1"></div>
<div id="uploadcontainer">
	<div id="break" ></div>
	<div id="uploadcontactform" >
	 <div id="TDHeadCab">
		<span class="labelrounded">DOCUMENTOS INTERNOS - ADJUNTAR ARCHIVOS </span>
		 <div  id ="botoninvisible" title="Subir Adjuntos" style="width:100px; height:20px; position: absolute; top: 6px; padding: 3px 10px; text-align: center;  FONT-SIZE: 8pt; font-weight:normal; left:78%;  color: #003366;  line-height: 20px;" >&nbsp;<img  src="img/filesupload.png"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp; SubirFile</div>
      </div>
      
          <TABLE width="100%" border="0"  >
            <!--DWLayoutTable-->
                    
			<TR> 
              <TD height="36" valign="top"> 
              
	  
	<table width="100%" border="0"  class="asdf">
                
                  <tr> 
                    <TD  width="100%" height="50"  valign="top" align="center"> 
                    <c:choose>
	<c:when test='${operacioninterno == "O"}'>
					<!-- NUEVA VISTA MIXTA-->
					<table width="70%" border="1" cellpadding="0" cellspacing="0">
					 
    <tr> 
      <td width="60" height="22" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">Nro</td>
      <td width="619" align="center" valign="middle" class="Columna10"  background="img/fondoplomo8.gif">Nombre del Archivo</td>
	  <td width="221" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">Adjuntos</td>
    <td width="68" align="center" valign="middle" class="Columna10" background="img/fondoplomo8.gif">Visar</td>
    <td width="68" align="center" valign="middle" class="Columna10" background="img/fondoplomo8.gif">Eliminar</td>
    </tr>
    <c:choose>
     <c:when test='${not empty rs_upload_doc_internos}'>
      <c:forEach items='${rs_upload_doc_internos}' var='pr' varStatus="c"> 
   
      <tr><td height="18" align="center" valign="middle"  class="Columna"><font color="#FF0000"><c:out value="${c.count}"></c:out></font></td>
        <td align="left" valign="middle" class="Columna"><font color="#FF0000"><c:out value='${pr.nombre_archivo}'/></font></td>
  
		   <td align="center" valign="middle" class="Columna">   
  	<a  target="_blank" href="VerArchivoPDFDocumentoInterno.do?filename=<c:out value='${pr.nombre_archivo_cifrado}'/>&index=<c:out value='${pr.id_archivo}'/>&fileroute=xipxx">Visualizar</a>

		   </td>
	       
	       <c:choose>
	       <c:when test='${esApto == "1" && (tipoDeFirmaUsuario == "0" || tipoDeFirmaUsuario == "1")}'>
	       <c:choose>
	       <c:when test='${pr.isvisado && pr.ischecked}'>
	<td align="center" valign="middle" class="Columna"><input type="checkbox" name="selectedItems" checked="checked" value="<c:out value='${pr.nombre_archivo}'/>" onclick="ModificarEstado('<c:out value='${pr.nombre_archivo}'/>')" /></td>
	</c:when>
	<c:otherwise>
		<c:choose>
		<c:when test='${pr.ischecked}'>
	<td align="center" valign="middle" class="Columna"><input type="checkbox" name="selectedItems" checked="checked" value="<c:out value='${pr.nombre_archivo}'/>" onclick="ModificarEstado('<c:out value='${pr.nombre_archivo}'/>')" /></td>
	</c:when>
	<c:otherwise>
		<td align="center" valign="middle" class="Columna"><input type="checkbox" name="selectedItems" value="<c:out value='${pr.nombre_archivo}'/>" onclick="ModificarEstado('<c:out value='${pr.nombre_archivo}'/>')" /></td>	
	</c:otherwise>
		</c:choose>
	
	</c:otherwise>
	</c:choose>     
	</c:when>
	<c:otherwise>
	<td align="center" valign="middle" class="Columna"></td>
	</c:otherwise>
	</c:choose>
	
    <td align="center" valign="middle"><A id="A1" target="_parent" href="AdjuntarDocumentoInterno.do?operacion=EDIF&nombre=<c:out value='${pr.nombre_archivo}'/>&cod_doc_interno=<c:out value='${pr.tipo_archivo}'/>">
        <font color="#0000FF"><img src="img/trash.png" border="0" height="24" alt="Eliminar Linea."></font> </A></td>
      </tr>
    </c:forEach>
     </c:when> 

    </c:choose> 
      <c:forEach items='${FFormDocumentoInterno.archivos}' var='ar' varStatus="c"> 
   
      <tr><td height="18" align="center" valign="middle"  class="Columna"><c:out value="${c.count+cantidad_lista}"></c:out></td>
      <td align="left" valign="middle" class="Columna"><c:out value="${ar.nombre_archivo}"></c:out></td>
  
	<td align="center" valign="middle" class="Columna"><a href="Busquedas.do?tipo=ver_archivo_interno_normal&id=<c:out value='${c.count-1}'/>">Visualizar</a></td>
		   
	
	<c:choose>
	<c:when test='${esApto == "1" && (tipoDeFirmaUsuario == "0" || tipoDeFirmaUsuario == "1")}'>
	<c:choose>
	<c:when test='${ar.ischecked}'>
	<td align="center" valign="middle" class="Columna"><input type="checkbox" name="selectedItemsz" checked="checked" value="<c:out value='${ar.nombre_archivo}'/>" onclick="ModificarEstadoArchivo('<c:out value='${c.count-1}'/>')" /></td>
	</c:when>
	<c:otherwise>
	<td align="center" valign="middle" class="Columna"><input type="checkbox" name="selectedItemsz" value="<c:out value='${ar.nombre_archivo}'/>" onclick="ModificarEstadoArchivo('<c:out value='${c.count-1}'/>')" /></td>
	</c:otherwise>
	</c:choose> 
	
	</c:when>
	<c:otherwise>
	
	<td align="center" valign="middle" class="Columna"></td>
	
	</c:otherwise>
	
	</c:choose>

    <td align="center" valign="middle"><a href="javascript:eliminar_archivo(<c:out value='${c.count-1}'/>)"><img src="img/trash.png" border="0" height="24" alt="Eliminar Linea."></a></td>
      </tr>
    </c:forEach>
	
  </table>
				
	</c:when>
					
					</c:choose>
					</TD>
                  </tr>
              </table></TD>
            </TR>
            
            </table>
          <br><br>
          
       </div>
	<div id="break" ></div>
            
     <div id="containerlistabusqueda">
     <div id="tabsbusqueda">
  		<ul>
    		<li><a href="#tabsbusquedabandeja">Proyectos de Documentos Internos</a></li>
    	</ul>
  		<div id="tabsbusquedabandeja">
  		
            <c:choose>
            <c:when test='${operacioninterno == "O"}'>
 
				<tr>
				
					<td width="1249" height="100%" valign="top" >
						<table width="100%" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td align="center" valign="middle" background="img/fondoplomo8.jpg" class="label" height="25">
									Lista de documentos	Internos</td>
						  </tr>
							<tr>
				  <td width="100%" align="center" colspan="2">				  
				  <display:table name="sessionScope.listaDocumentosInternosProyectos" export="false" sort="list" id="b" pagesize="5"  class="simple" style="width: 100%">
				  						
				  						<display:column title="Nro Documento"  align="center" property="codigo_documento_interno" sort="true" />
											
				  						<display:column property="asunto" title="Asunto" sort="true" />								
					  						
				  						<display:column  title="Oficina Destino" sort="true">
				  						
					  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
						  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
						  								<c:out value="${oficina.descripcion_oficina}"/>
						  							</c:if>
						  						</logic:iterate>
				  						</display:column>
				  						<display:column property="dirigido_a" title="Destinatario" sort="true"/>
				  						
				  						<display:column   title="Tipo de Documento" sort="true">
					  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
					  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						
				  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />
				  						
				  						
				  						
				  						<display:column   title="Estado" sort="true">
					  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
					  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  										  						
				  						<display:column  sort="true" title="Fecha de creacion" >
				  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
				  						</display:column>
				  						
				  						<display:column align="center" property="link"  title="*Operaciones*" width="80"/>
		
				  						
				</display:table>
				  </td>

			 </tr>
						</table>
					</td>
				</tr>
		 
		  </c:when>
		  </c:choose>
          
          
  		</div>
  	</div>
  	
	 </div> 
	 
	<div  id ="botoncerrar" class="buttoncss" title="Salir" style="width:100px; margin-left: -50px; height:20px; position: relative;   left: 50%; float:center; top: 9px; padding: 3px 10px; text-align: center;  FONT-SIZE: 8pt; font-weight:normal;  color: #003366;  line-height: 20px;" >&nbsp;<img  src="img/closeicon2.png"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp; Salir</div> 
	 
	 
   </div>
</FORM>

</BODY></HTML>

<% } %>