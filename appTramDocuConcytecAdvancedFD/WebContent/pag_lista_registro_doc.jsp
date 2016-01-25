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
<HEAD><TITLE>Modificar Registros</TITLE>
 
        <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
        <link rel="stylesheet" href="css/modelo.css" type="text/css" />
<link rel="stylesheet" href="css/tipografias.css"  type="text/css">


<STYLE type=text/css>

#scroll { 
     width:400px; 
     height:300px; 
     background-color:#ffffff; 
     overflow:auto; 
}
</STYLE>
<script src="js/funciones.js" type="text/javascript"></script>
<SCRIPT LANGUAGE=javascript src="js/scriptaculous.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/ajaxtags.js"></SCRIPT>
<script language="JavaScript" src="js/avatec.js"></script>
<SCRIPT type=text/javascript>


function ver_frame(){
	
	
	//var ls_numero_documento = document.getElementById("numero_documento").value;
	var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
    //var ls_codigo_oficina = document.getElementById("codigo_oficina").value;
	var ls_codigo_documento = document.getElementById("codigo_documento").value;
	
	
	 var strURL = "ListaDocumentos.do?tipo=modificar&operacion=ME";
	 strURL += "&codigo_expediente="+ls_codigo_expediente+"&codigo_documento="+ls_codigo_documento;
	
 	 location=strURL;
	return;

	}
	
function listar_todos(){
	
	
	var strURL = "ListaDocumentos.do?tipo=modificar&operacion=ME";
	
 	 location=strURL;
	return;

	}
	
	function agregar(){
	
	 document.location.href="CajaChica.do?tipo=mantespecifica&operacion=N"
	return;
	
	}
	
	function regresar(){
	
	 document.location.href="ValidaPaginas.do?tipo=regresar&pagina=docentrada";
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
		//alert(strURL);	
		location=strURL;
		//alert(strURL);
		 window.close();
		 
	}
	
	function fn_onLoad(){
			cambiar_color_tabla("b", "tablaBackgray");
		
	}
	
	function fn_onUnLoad(){
		window.opener.document.forms[0].target = 'mainFrame';
	}

	function VerArchivos(ls_codigo_documento,ls_codigo_expediente,ls_codigo_recepcion){
   
				var ls_operacion = "N";
				/*var ls_codigo_documento = document.getElementById("codigo_documento").value;
				var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
				var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;*/
				//alert(ls_codigo_documento);
				
				var strURL='MantSeguimiento.do?tipo=upload'+'&operacion='+ls_operacion+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&codigo_recepcion='+ls_codigo_recepcion;	
				window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes");
				
				
		}
</SCRIPT>
</HEAD>
<BODY   bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" bgColor="#F0F5FB" onLoad="fn_onLoad();" onUnload="fn_onUnLoad();">
<div id="container">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
<form id="form_datos" name="form_datos" method="post" action="" >
 

  <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0 height="100%">
    <TBODY>
      		
      <TR> 
        <TD height="100%" align="center" valign="top" class="tablaArbitraje"  style="WIDTH: 100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <!--DWLayoutTable-->
           
            
            <tr> 
              <td height="273" colspan="5" valign="top" width="100%" class="groupcel4"> 
                <br/>
                <br/>
                <table width="100%"  cellspacing="0" cellpadding="0">

                  <tr valign="middle" class="cabeceratable"> 
                    <td width="100%" height="40" align="left" background="img/fondoplomo8.jpg" class="TitleScreen" > 
                    DOCUMENTOS REGISTRADOS                   </td>
                  </tr>
                  <tr valign="middle"> 
                    <td height="90" align="left" valign="top"  bgColor="#F0F5FB" > <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                      <!--DWLayoutTable-->
                       
                        <tr > 
                          <td height="28" colspan="2" align="left" valign="middle" class="label">&nbsp;&nbsp;&nbsp;&nbsp;<strong>B&uacute;squeda 
                          por :</strong> </td>
                          <td width="67">&nbsp;</td>
                          <td width="133">&nbsp;</td>
                          <td width="366">&nbsp;</td>
                        </tr>
                        <tr> 
                          <td width="189" height="29" align="right" valign="middle" class="label">N&deg; Registro:</td>
                          <td width="343" align="left" valign="middle"> &nbsp; &nbsp;<input type="text" name="codigo_documento" id="codigo_documento" size="20" class="caja"   > 
                          <script language="JavaScript" type="text/javascript">if(document.getElementById) document.getElementById('codigo_documento').focus();</script>    </td>
                          <td align="center" valign="middle"> <input type="button" name="button" class="boton" value="Buscar" onClick="ver_frame()" alt="buscar"  ></td>
                          <td align="center" valign="middle"> <input type="button" name="button" class="boton" value="Listar Todos" onClick="listar_todos()" alt="Listar Todos"  ></td>
                         
                          <td>&nbsp;</td>
                        </tr>
						  <tr> 
                          <td height="29" align="right" valign="middle" class="label">N&deg; Expediente:</td>
                          <td align="left" valign="middle">&nbsp;&nbsp; <input type="text" name="codigo_expediente" id="codigo_expediente" size="20" class="caja"   >                            </td>
                          <td colspan="3" valign="top"><!--DWLayoutEmptyCell-->&nbsp;</td>
                        </tr>
						<tr> 
                          <td height="29" align="right" valign="middle" class="label"><!--Area/Oficinas:--></td>
                          <td align="left" valign="middle">&nbsp;&nbsp; <!-- <select id="codigo_oficina" name="codigo_oficina" class="caja" style="WIDTH: 300px; HEIGHT: 21px">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_oficina_lista}'> <c:forEach items='${rs_oficina_lista}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.nombre_corto}'/> </option> </c:forEach> 
                    </c:when> </c:choose> </select>       -->                    </td>
                          <td colspan="3" valign="top"><!--DWLayoutEmptyCell-->&nbsp;</td>
                        </tr>
						
                    </table></td>
                  </tr>
                 <tr> 
                    <td height="15" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label"> 
                    Lista de documentos        </td>
                  </tr>
                 
                  <tr> 
                    <td > 
                    <display:table name="sessionScope.rs_lista_todos" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										
										<display:column property="codigo_documento" media="html" title="N&deg; Registro" />
										<display:column property="codigo_expediente" media="html" title="N&deg; Expediente" />
										<display:column property="numero_documento" media="html" title="N&deg;  Documento" />
										<display:column property="oficina_origen" media="html" title="Oficina Origen" />
									<display:column property="view_archivo" align="center" media="html" title="Ver Documento" />
									
					  </display:table>               </td>
                  </tr>
                 
                </table></td>
            </tr>
          </table></TD>
      </TR>
       
    </TBODY>
  </TABLE>

</FORM>
</div>
</BODY></HTML>
<% } %>