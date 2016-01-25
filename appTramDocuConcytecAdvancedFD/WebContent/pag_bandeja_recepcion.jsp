<%@ include file="taglibs.jsp" %>

<html>
<head>
<%
String nombre_columna_tabla = (String)session.getAttribute("nombre_columna_tabla");
%>
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
		});
		
		function fn_onLoad(){

			cambiar_color_tabla("b", "tablaBackgray");
			
		}
		
		function VerArchivos(ls_codigo_documento,ls_codigo_expediente,ls_codigo_recepcion){
			   
			var ls_operacion = "N";
			var strURL='MantSeguimiento.do?tipo=upload'+'&operacion='+ls_operacion+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&codigo_recepcion='+ls_codigo_recepcion;	
			window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes");

		}	
		
</script>	
		
</head>
<body onLoad="fn_onLoad();">
<div class="derivalista">
					
					  <div id="tabs-1">	
					  <table width="90%" align="center" cellpadding="0" cellspacing="0" >
							
							<tr>
								<td height="13" colspan="2" valign="top" align="center">
								<div id="lista-bandeja-recepcion-control">	
									<display:table name="sessionScope.rs_recepcion_doc_bandejas" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										
										<display:column property="codigo_documento" title="N&deg; Registro" />
										<display:column property="codigo_expediente" title="N&deg; Expediente" />
										<display:column property="numero_documento" title="N&deg;  Documento" />
										<display:column property="oficina_origen" title="Oficina Origen" />
										<display:column property="oficina_deriva" title="Oficina Destino" />
										<display:column property="destinatario" title="Destinatario" />
										<display:column property="fecha_registro" title="<%=nombre_columna_tabla%>" />
										<display:column property="tipo_doc" title="Tipo Doc." />
										<display:column property="estado_movimiento" title="Estado" />
										<display:column property="muestra_alerta" align="center" title="Alerta" />
										<display:column property="view_archivo" align="center" title="Ver Documento" />
										<!-- display:column property="observar" align="center" title="Observar" /-->
									</display:table>
								</div>
								</td>
							</tr>
							<!-- tr><td><a href="javascript:void(0);" onclick="window.parent.refDialog.dialog('close');">close</a></td></tr-->
					  </table>
					  </div>
					
						
				</div>
</body>
</html>