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
<LINK href="css/estilo1.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/fstooltips.css">

<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
<link rel="stylesheet" href="css/modeloupload.css" type="text/css" />

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

	
	
	#uploadcontainer
	{
		position: relative; 
		background: #F0F5FB;
	}
	
		
</style>

<style type="text/css">
.buttonupload{
	/*margin:10px 55px 10px 0px;*/
	/*font-weight: bold;*/
	/*line-height: 1;*/
	padding: 6px 10px;
	cursor:pointer;   
	color: #fff;
	font-size: 12px;
	text-align: center;
	/*text-shadow: 0 -1px 1px #64799e;*/
	
	/* Background gradient */
	/*background: #ffffff;*/
	background: -moz-linear-gradient(top, #6faddd 0%, #156bb9 100%);
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#6faddd), to(#156bb9));
	
	/* Border style */
  	border: 1px solid #1b55a9;  
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
  
	/* Box shadow */
	/*-moz-box-shadow: inset 0 1px 0 0 #aec3e5;
	-webkit-box-shadow: inset 0 1px 0 0 #aec3e5;
	box-shadow: inset 0 1px 0 0 #aec3e5;*/
	
	-moz-box-shadow: inset 0 1px 0 0 #156bb9;
	-webkit-box-shadow: inset 0 1px 0 0 #156bb9;
	box-shadow: inset 0 1px 0 0 #156bb9;
}

.buttonupload:hover {
	background: #6faddd;
    cursor: pointer;
	}
	
</style>

<script language="javascript">

$(document).ready(function(){

	
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
	      //title: 'Como son',
	      modal: true
	      
	    });
	  });
 
});
</script>

<script type="text/javascript">

	function abrirVentana2(valor){
		var coddoc = valor;
		$( "#dialog-confirm" ).dialog( "open");
	
	}
	
	function verDetalle(codigo,estado){
		var codigov= codigo;
		var estadov= estado;
		var value='detalle';
		
		$("#ventanaDetalle").load("obtenerListaDestinatarios.do", {cambia:value,id_cab_upinternos:codigov,estado:estadov});
		$( "#dialog-confirm" ).dialog( "open");
	}


</SCRIPT>
</HEAD>
<body id="cuerpo"  bgcolor="#F0F5FB">
<form id="form_datos" name="form_datos" ENCTYPE="multipart/form-data" METHOD="POST" ACTION="">
<input type="hidden" value="codigo_documento_vb_requerido">
<div id="uploadcontainer">
	<div id="break" ></div>
	
   
		<table width="100%" border="0"  class="asdf">
			               
		<tr> 
		<TD  width="100%" height="50"  valign="top" align="center"> 
		<table width="70%" border="1" cellpadding="0" cellspacing="0">
							 
			   <tr> 
			     <td width="60" height="22" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">Nro</td>
			     <td width="619" align="center" valign="middle" class="Columna10"  background="img/fondoplomo8.gif">Nombre del Archivo</td>
			  <td width="221" align="center" valign="middle"  background="img/fondoplomo8.gif" class="Columna10">Adjuntos</td>
			  <c:choose>
			  <c:when test='${verEstado == "2" || verEstado == "3" || verEstado == "4"}'>
			  </c:when>
			  <c:otherwise>
			  		<td width="68" align="center" valign="middle" class="Columna10" background="img/fondoplomo8.gif">Visar</td>
			  </c:otherwise>
			  </c:choose>
			  
			   <c:if test='${verEstado == "1" || verEstado == "2"}'>
			   		<td width="300" align="center" valign="middle" class="Columna10" background="img/fondoplomo8.gif">Estado</td>
			   </c:if>
			   </tr>
			   <c:choose>
			    <c:when test='${not empty rs_upload_doc_internos}'>
			     <c:forEach items='${rs_upload_doc_internos}' var='pr' varStatus="c"> 
			  
			     <tr><td height="18" align="center" valign="middle"  class="Columna"><font color="#FF0000"><c:out value="${c.count}"></c:out></font></td>
			       <td align="left" valign="middle" class="Columna"><font color="#FF0000"><c:out value='${pr.nombre_archivo}'/></font></td>
			 
				   <td align="center" valign="middle" class="Columna">
				   <c:choose>
				   <c:when test='${verEstado == "1" || verEstado == "2"}'>
				   		<a  target="_blank" href="VerArchivoPDFDocumentoInterno.do?filename=<c:out value='${pr.nombre_archivo_cifrado}'/>&index=<c:out value='${pr.estado}'/>&fileroute=xipxx">Visualizar</a>
				   </c:when>
				   <c:otherwise>
				   		<a  target="_blank" href="VerArchivoPDFDocumentoInterno.do?filename=<c:out value='${pr.nombre_archivo_cifrado}'/>&index=<c:out value='${pr.id_archivo}'/>&fileroute=xipxx">Visualizar</a>
				   </c:otherwise>
				   </c:choose>   
			 		
				   </td>
			       
			       <c:choose>
			       <c:when test='${verEstado == "2" || verEstado == "3" || verEstado == "4"}'>
			       
			       </c:when>
			       <c:otherwise>
			       <c:choose>
					       <c:when test='${esApto == "1" && (tipoDeFirmaUsuario == "0" || tipoDeFirmaUsuario == "1")}'>
							       <c:choose>
								       <c:when test='${pr.isvisado && pr.ischecked}'>
											<td align="center" valign="middle" class="Columna"><input type="checkbox" name="selectedItems" checked="checked" disabled value="<c:out value='${pr.nombre_archivo}'/>"  /></td>
										</c:when>
										<c:otherwise>
											<c:choose>
											<c:when test='${pr.ischecked}'>
												<td align="center" valign="middle" class="Columna"><input type="checkbox" name="selectedItems" disabled checked="checked" value="<c:out value='${pr.nombre_archivo}'/>"  /></td>
											</c:when>
											<c:otherwise>
												<td align="center" valign="middle" class="Columna"><input type="checkbox" name="selectedItems" disabled  value="<c:out value='${pr.nombre_archivo}'/>"  /></td>	
											</c:otherwise>
											</c:choose>
										
										</c:otherwise>
									</c:choose>     
							</c:when>
					<c:otherwise>
					<td align="center" valign="middle" class="Columna"></td>
					</c:otherwise>
					</c:choose>
			       
			       </c:otherwise>
			       </c:choose>
			       
			
				<c:if test='${verEstado == "1" || verEstado == "2"}'>
			   		<td align="center" valign="middle" class="Columna"><a href="javascript:verDetalle(<c:out value='${pr.id_archivo}'/>,'<c:out value='${pr.estado}'/>')" >Ver Detalle</a> </td>
			   </c:if>
			
			     </tr>
			   </c:forEach>
			    </c:when> 
			
			   </c:choose> 
			    
			 </table>
							</TD>
			                 </tr>
	</table>
			
          <br><br>
    <c:if test='${verEstado == "4"}'>      
    <table width="100%" border="0"  class="asdf">
			               
		<tr> 
		<TD  width="100%" height="50"  valign="top" align="center"> 
		<input type="button" value="Firmar" style="width: 90;" class="buttonupload" onclick="javascript:window.parent.firmaSolicitada(<c:out value='${codigo_documento_vb_requerido}'/>,0,0);" />
		</TD>
		</tr>
	</table>	
    </c:if>
   </div>
   <div id="dialog-confirm" title="Detalle de Documento">
   		<div  id="ventanaDetalle">
   		
   		</div>
   </div>
</FORM>

</BODY></HTML>

<% } %>