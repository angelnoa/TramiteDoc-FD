<%@ include file="taglibs.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="css/modelo.css" type="text/css" />
		<link rel="stylesheet" href="css/tipografias.css"  type="text/css">
		
		<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
		<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
		<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
		
		
        <script src="js/funciones.js" type="text/javascript"></script>
        <script language="JavaScript" src="js/avatec.js"></script>
        
<script language="JavaScript">
		$(document).ready(function(){
			$( "#tabs" ).tabs();
			$( "#tabsproyectos" ).tabs();
			
			$(function() {
			    $( "#dialog-confirm" ).dialog({
			      autoOpen: false,	
			      resizable: false,
			      height:180,
			      modal: true,
			      buttons: {
			        "Eliminar documento": function() {
			          var codigo = $( this ).dialog( "option", "appendTo" );
			          //alert(codigo);
			          var strURL = "MantenimientoDocumentoInterno.do?cod_doc_interno="+codigo;
			          location = strURL;
			          $( this ).dialog( "close" );
			        },
			        Cancelar: function() {
			          $( this ).dialog( "close" );
			        }
			      }
			    });
			  });
		});
		
		function fn_onLoad(){

			cambiar_color_tabla("b", "tablaBackgray");
			
		}
		
		function VerArchivos(ls_codigo_documento,ls_codigo_expediente,ls_codigo_recepcion){
			   
			var ls_operacion = "N";
			var strURL='MantSeguimiento.do?tipo=upload'+'&operacion='+ls_operacion+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&codigo_recepcion='+ls_codigo_recepcion;	
			window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes");

		}	
		
		function deletedocument(codDoc){
			var coddoc = codDoc;
				
			$( "#dialog-confirm" ).dialog({ appendTo: coddoc });
			$( "#dialog-confirm" ).dialog( "option", "appendTo", coddoc );
			$( "#dialog-confirm" ).dialog( "open");

		}
		
</script>	
		
</head>
<body onLoad="fn_onLoad();">
<c:choose>
	<c:when test='${tipo_bandeja=="1"}'>
				<div class="derivalista">
					  <div id="tabs-1">	
					  <table width="90%" align="center" cellpadding="0" cellspacing="0" >
							
							<tr>
								<td height="13" colspan="2" valign="top" align="center">
								<div id="lista-bandeja-documentos-internos-creados">	
									<display:table name="sessionScope.listaDocumentosInternosFull" 
									  export="false" sort="list" id="b" 
									  pagesize="8"  class="simple" style="width: 90%" >
									  			
									  			<display:column title="Nro Registro" property="codigo_documento" sort="true" align="center" />
									  						
									  						<display:column title="Nro Documento" property="codigo_documento_interno" sort="true" align="center" />
																
									  						<display:column property="asunto" title="Asunto" sort="true" width="350"/>								
										  						
									  						<display:column  title="Oficina Destino" sort="true">
									  						
										  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
											  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
											  								<c:out value="${oficina.descripcion_oficina}"/>
											  							</c:if>
											  						</logic:iterate>
									  						</display:column>
									  						<display:column property="dirigido_a" title="Destinatario" sort="true" style="FONT-SIZE: 8pt;"/>
									  						
									  						<display:column   title="Tipo de Documento" >
										  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
										  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
										  								<c:out value="${estado.nombreBean}"/>
										  							</c:if>
										  						</logic:iterate>
									  						</display:column>
									  						
									  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />
									  						
					
									  						<display:column   title="Estado" >
										  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
										  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
										  								<c:out value="${estado.nombreBean}"/>
										  							</c:if>
										  						</logic:iterate>
									  						</display:column>
									  										  						
									  						<display:column  sort="true" title="Fecha de creacion" >
									  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
									  						</display:column>
									  						
									  						<display:column property="link"  align="center" title="***Operaciones***" />
									  			
									  						
									  </display:table>
								</div>
								</td>
							</tr>
							<!-- tr><td><a href="javascript:void(0);" onclick="window.parent.refDialog.dialog('close');">close</a></td></tr-->
					  </table>
					  </div>
					
						
				</div>
				
		</c:when>
		<c:otherwise>
<div id="tabsproyectos">
  <ul>
    <li><a href="#tabsproyectos-1">Mis Proyectos</a></li>
    <li><a href="#tabsproyectos-2">Proyectos recibidos</a></li>
  </ul>
  <div id="tabsproyectos-1">
  <table width="100%">
			 <!-- <tr>
							<td height="35" align="center" colspan="2" valign="top">
							<table width="100%"  cellpadding="0" cellspacing="0" background="img/fondoplomo8.jpg">
							  <!--DWLayoutTable>
							<tr>
							<td width="33%" height="30" valign="top"  class="textomensaje10bold">				</td>
						    <td width="33%" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label"><bean:message key="sistema.documento.internos.creados" /></td>
						    <td width="33%" >
							</td>
						  	</tr>
							</table>							</td>
			</tr>-->
			 
			 <tr>
				  <td align="center" colspan="2">				  
				  <display:table name="sessionScope.listaDocumentosInternosProyectos" 
				  export="false" sort="list" id="b" 
				  pagesize="8"  class="simple" style="width: 90%" >
				  						
				  						<display:column title="Nro Documento" property="codigo_documento_interno" sort="true" align="center" />
											
				  						<display:column property="asunto" title="Asunto" sort="true" width="350"/>								
					  						
				  						<display:column  title="Oficina Destino" sort="true">
				  						
					  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
						  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
						  								<c:out value="${oficina.descripcion_oficina}"/>
						  							</c:if>
						  						</logic:iterate>
				  						</display:column>
				  						<display:column property="dirigido_a" title="Destinatario" sort="true" style="FONT-SIZE: 8pt;"/>
				  						
				  						<display:column   title="Tipo de Documento" >
					  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
					  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						
				  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />
				  						

				  						<display:column   title="Estado" >
					  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
					  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  										  						
				  						<display:column  sort="true" title="Fecha de creacion" >
				  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
				  						</display:column>
				  						
				  						<display:column property="link"  align="center" title="***Operaciones***" />
				  							
		
				  </display:table>
				 
				  </td>
				  
			 </tr>
			 <!-- tr>
			 <td align="center" valign="middle" ><br><a href="javascript:LimpiarListaProyectos();" > [ Regresar ]</a></td>
			 </tr-->
			 
	</table>
  </div>
    <div id="tabsproyectos-2">
    <table width="100%">
			 <!-- <tr>
							<td height="35" align="center" colspan="2" valign="top">
							<table width="100%"  cellpadding="0" cellspacing="0" background="img/fondoplomo8.jpg">
							  <!--DWLayoutTable>
							<tr>
							<td width="33%" height="30" valign="top"  class="textomensaje10bold">				</td>
						    <td width="33%" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label"><bean:message key="sistema.documento.internos.creados" /></td>
						    <td width="33%" >
							</td>
						  </tr>
							</table>							</td>
			</tr>-->
			 
			 <tr>
				  <td align="center" colspan="2">				  
				  <display:table name="sessionScope.listaDocumentosInternosProyectosRecibidos" 
				  export="false" sort="list" id="b" 
				  pagesize="8"  class="simple" style="width: 90%" >
				  						
				  						<display:column title="Nro Registro" property="link2" sort="true" align="center" />
											
				  						<display:column property="asunto" title="Asunto" sort="true" width="350"/>								
					  						
				  						<display:column  title="Oficina Destino" sort="true">
				  						
					  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
						  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
						  								<c:out value="${oficina.descripcion_oficina}"/>
						  							</c:if>
						  						</logic:iterate>
				  						</display:column>
				  						<display:column property="dirigido_a" title="Destinatario" sort="true" style="FONT-SIZE: 8pt;"/>
				  						
				  						<display:column   title="Tipo de Documento" >
					  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
					  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						
				  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />
				  						

				  						<display:column   title="Estado" >
					  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
					  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  										  						
				  						<display:column  sort="true" title="Fecha de creacion" >
				  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
				  						</display:column>
				  						
				  						<display:column property="link"  align="center" title="***Operaciones***" />
				  							
		
				  </display:table>
				 
				  </td>
				  
			 </tr>
			 <!-- tr>
			 <td align="center" valign="middle" ><br><a href="javascript:LimpiarListaProyectos();" > [ Regresar ]</a></td>
			 </tr-->
			 
	</table>
  </div>
  </div>						  			
									  			
									  			
		</c:otherwise>
</c:choose>				
		<div id="dialog-confirm" title="Mensaje de Alerta" >
  		<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0; font-size : 8px; "></span></p>
  		El documento será eliminado de manera permanente.
		</div>
  
</body>
</html>