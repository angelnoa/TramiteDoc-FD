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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Lista de Documentos Pendientes</title>
		
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
        <script src="js/funciones.js" type="text/javascript"></script>
		<script  src="js/prototype-1.4.0.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/scriptaculous.js"></script>
       
        <script type="text/javascript" src="js/ajaxtags.js"></script>
		
		<script language="JavaScript" src="js/avatec.js"></script>
		<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
		<!-- librer�a para cargar el lenguaje deseado -->
		<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
		<!-- librer�a que declara la funci�n Calendar.setup, que ayuda a generar un calendario en unas pocas l�neas de c�digo -->
		<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
		
		<SCRIPT type=text/javascript>


function ver_frame(){
	
	
	var ls_numero_registro = document.getElementById("numero_registro").value;
	//var ls_codigo_expediente = document.getElementById("codigo_expediente").value;

	 var strURL = "ValidaPaginas.do?tipo=ayudamedios";
	 strURL += "&numero_registro="+ls_numero_registro.toUpperCase();
	
 	 location=strURL;
	return;

	}
	
function listar_todos(){
	
	
	var strURL = "ValidaPaginas.do?tipo=ayudamedios";
	
 	 location=strURL;
	return;

	}
	
	function agregar(){
	
	 document.location.href="CajaChica.do?tipo=mantempleados&operacion=N"
	return;
	
	}
	
	function regresar(){
	
	 document.location.href="ValidaPaginas.do?tipo=regresar&pagina=docentrada"
	return;
	}
	
	function VerDetalleBusqueda(num_doc,fecha_doc,cod_clase_doc,operacion,apepatmatnomb,parameter){
	
	 var strURL = "CajaChica.do?tipo=seleccionarempleado"; 
			strURL += "&num_doc="+num_doc;
			strURL += "&fecha_doc="+fecha_doc;
			strURL += "&cod_clase_doc="+cod_clase_doc;
			strURL += "&apepatmatnomb="+apepatmatnomb;
			strURL += "&operacion="+operacion;
			strURL += "&parameter="+parameter;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
		alert(strURL);	
		location=strURL;
		alert(strURL);
		 window.close();
		 
	}
	
	function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray")
		
	}
	
	function fn_onUnLoad(){
		window.opener.document.forms[0].target = 'mainFrame';
	}
	

	function VerArchivos(ls_codigo_documento){
		 		//alert("entre VerArchivos...");
		 		var width = 800;
		 	    var height = 600;		 	   
		 	    var windowFeatures = "width=" + width + ",height=" + height + 
		 	        ",status,resizable";	
		 	    
			var ls_operacion = "N";				
			var strURL='ValidaPaginas.do?tipo=verhojaruta'+'&operacion='+ls_operacion+'&codigo_documento='+ls_codigo_documento;
				//var optionString = "width=400,height=400,menubar=no,toolbar=no,scrollbars=yes,location=no,resizeable=no";
				//window.open(strURL,"",optionString);
				
			window.open(strURL,"",windowFeatures);
				
		// window.open(strURL, "subWind", windowFeatures);
				//location=strURL;
				
		}
</SCRIPT>
	</head>
	<body topmargin="0" leftmargin="0" onLoad="fn_onLoad();">
		
		
			<table width="100%" cellspacing="0" cellpadding="0"  align="left">
			
				<tr>
					<td class="label" align="center" style="width:100%" background="img/fondoplomo8.jpg" height="26">
							Ayuda Documentos Pendientes
					</td>
				</tr>
				<tr>
				  <td align="center" width="100%" class="groupcell">
					  <table align="center" cellspacing="0" cellpadding="2" width="100%" border="0">
							<!--DWLayoutTable-->
							<tr>
								<td width="10" height="10" style="height: 10px;">								</td>
								<td width="100" align="left">								</td>
								<td width="10">								</td>
								<td width="335">								</td>
								<td width="173"></td>
								<td width="306"></td>
								<td width="13">								</td>
							</tr>
                            
                         
							
							<tr>
								<td height="31" colspan="7" align="center">
									<table width="100%" border="0" cellpadding="0" cellspacing="0" >
									  <!--DWLayoutTable-->
                       
                        <tr > 
                          <td height="28" colspan="2" align="left" valign="middle" class="label">&nbsp;&nbsp;&nbsp;&nbsp;<strong>B&uacute;squeda 
                          por :</strong> </td>
                          <td width="92">&nbsp;</td>
                          <td width="162">&nbsp;</td>
                          <td width="487">&nbsp;</td>
                          </tr>
                        <tr> 
                          <td width="166" height="29" align="right" valign="middle" class="label4">N&deg; de Registro:</td>
                          <td width="319" align="left" valign="middle">&nbsp;
                            <input type="text" name="numero_registro" id="numero_registro" size="20" class="caja"   > 
                          <script language="JavaScript" type="text/javascript">if(document.getElementById) document.getElementById('numero_registro').focus();</script>    </td>
                          <td align="center" valign="middle"> <input  type="submit" name="button" class="boton" value="Buscar" onClick="ver_frame()" alt="buscar"  ></td>
                          <td align="center" valign="middle"> <input type="button" name="button" class="boton" value="Listar Todos" onClick="listar_todos()" alt="Listar Todos"  ></td>
                         
                          <td valign="top"><!--DWLayoutEmptyCell-->&nbsp;</td>
                        </tr>
						 
                    </table>
									
							  </td>
							</tr>
							<tr>
								<td height="10" style="height: 10px;">								</td>
								<td align="left">								</td>
								<td>								</td>
								<td>								</td>
								<td></td>
								<td></td>
								<td>								</td>
							</tr>
					  </table>
                   
          		
				  </td>
				</tr>
				
				<tr>
				
				 
					<td>
						<table width="100%" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td align="center" valign="middle" background="img/fondoplomo8.jpg" class="label" height="25">
									Lista de documentos	Pendientes</td>
						  </tr>
							<tr>
								<td>
									<display:table name="sessionScope.rs_lista_pendientes" export="false" sort="list" id="b" pagesize="15"
										class="simple1" style="width:100%">
										
										<display:column property="codigo_documento" media="html" title="N&deg; Registro" />
										<display:column property="numero_documento" media="html" title="N&deg;  Documento" />
										<display:column property="oficina_origen" media="html" title="Oficina Origen" />
										<display:column property="oficina_deriva" media="html" title="Oficina Destino" sort="true"/>
										<display:column property="fecha_movimiento" media="html" title="Fecha Mov." />
										<display:column property="medio" media="html" title="Medio" />
										<display:column property="estado_movimiento" media="html" title="Estado" />
										<display:column property="view_archivo" align="center" media="html" title="Hoja de Ruta" />
									</display:table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
		  </table>
		
		
	</body>
</html>
<% } %>
