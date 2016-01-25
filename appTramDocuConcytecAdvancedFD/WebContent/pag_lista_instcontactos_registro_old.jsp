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
<HEAD><TITLE>Busqueda de Procedencia</TITLE>
<LINK href="css/estilos.css" type="text/css" rel="stylesheet">
<LINK href="css/estilo1.css" type="text/css" rel="stylesheet">
 <LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />
<link rel="stylesheet" href="css/modelo.css" type="text/css" />
<STYLE type=text/css>

#scroll { 
     width:400px; 
     height:300px; 
     background-color:#ffffff; 
     overflow:auto; 
}
</STYLE>
<script src="js/funciones.js" type="text/javascript"></script>
<SCRIPT LANGUAGE=javascript src="js/prototype-1.4.0.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/scriptaculous.js"></SCRIPT>

<SCRIPT LANGUAGE=javascript src="js/ajaxtags.js"></SCRIPT>
<SCRIPT type=text/javascript>

function ver_frame(){
	
	 var ls_operacion = document.getElementById("sel_opcion").value;
	 var ls_texto = document.getElementById("sle_texto").value;
	 var ls_accion = document.getElementById("accion").value;
	// var ls_accion = <c:out value='${accion}'/>;

	  if(ls_texto!= ""){
		 // alert(ls_texto);
	   var strURL = "VerFrameListas.do?tipo=procedencia";
		 strURL += "&operacion="+ls_operacion+"&sle_texto="+ls_texto.toUpperCase()+"&accion="+ls_accion;
		// alert(strURL);
		 document.getElementById("ifra_frame_listas").Document.location=strURL;
		 
		return;
	  }
	  
	}
function ver_frame_numero(){
	var ls_operacion= "";
	var ls_texto_nat = document.getElementById("sle_natural").value;
	var ls_texto_jur = document.getElementById("sle_juridica").value; 
	
	 if(ls_texto_nat!= ""){
	 
	 ls_operacion = "N";
	 
	 var strURL = "VerFrameListas.do?tipo=procedencia";
	 strURL += "&operacion="+ls_operacion+"&sle_texto="+ls_texto_nat.toUpperCase();
	
 	 document.getElementById("ifra_frame_listas").Document.location=strURL;
	return;
	
	 }else if(ls_texto_jur != ""){
	 	ls_operacion = "J";
	
	 var strURL = "VerFrameListas.do?tipo=procedencia";
	 strURL += "&operacion="+ls_operacion+"&sle_texto="+ls_texto_jur.toUpperCase();
	
 	 document.getElementById("ifra_frame_listas").Document.location=strURL;
	return;
	 
	 }
	 

	}
	
function listar_todos(){
	
	
	// var strURL = "VerFrameListasGlobal.do?tipo=maestanex";
	//window.showModalDialog("ValidaPaginas.do?tipo=instcontactos", window, "dialogHeight:500px; dialogWidth:800px; center:yes; help:no; resizable:no; status:no;scrollbar:no");       
	/*var strURL = "ValidaPaginas.do?tipo=instcontactos";
	
 	 location=strURL;
	return;*/
	
	
	 var ls_accion = document.getElementById("accion").value;
	// var ls_accion = <c:out value='${accion}'/>;
	
	   var strURL = "VerFrameListas.do?tipo=procedencia";
		 strURL += "&accion="+ls_accion;
		
		 document.getElementById("ifra_frame_listas").Document.location=strURL;
		// alert(strURL);
		return;
	}
	
	function agregar(){
	
	ls_accion = document.getElementById("accion").value;
	
	 document.location.href="ValidaPaginas.do?tipo=nuevaprocedencia&operacion=N"+"&accion="+ls_accion;
	return;
	}
	
	function regresar(){
	
	 document.location.href="ValidaPaginas.do?tipo=regresar&pagina=docentrada";
	return;
	}
	
</SCRIPT>
</HEAD>
<BODY bgColor="#F0F5FB"   bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0">
<div class="derivaconta">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
<form id="form_datos" name="form_datos" method="post" action="" >
 <INPUT  type="hidden" id="accion" name="accion" value="<c:out value='${accion}'/>">

  <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0 height="100%">
   
    <TBODY>
      
      
      <TR> 
        <TD height="100%" align="center" valign="top" class="tablaArbitraje"  style="WIDTH: 100%" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <!--DWLayoutTable-->
           
            
            <tr> 
              <td height="273" colspan="5" valign="top" width="100%"> 
                <table width="100%" cellpadding="0" cellspacing="0" >
                
                  <tr valign="middle"> 
                    <td height="40" colspan="3" align="left" bgcolor="265ca6" class="Columna11"  > 
                      &nbsp;&nbsp; <font color="#FFFFFF" size="2" ><strong>B&Uacute;SQUEDA</strong></font> 
                    </td>
                  </tr>
                  <tr valign="middle"> 
                    <td height="50" colspan="3" align="left"  > <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <!--DWLayoutTable-->
                        <tr> 
                          <td width="246" height="28" align="center" valign="middle"><strong>B&uacute;squeda 
                            por :</strong> </td>
                          <td width="188">&nbsp;</td>
                          <td width="107">&nbsp;</td>
                          <td width="100">&nbsp;</td>
                          <td width="98">&nbsp;</td>
                          <td width="238">&nbsp;</td>
                        </tr>
                        <tr> 
                          <td height="29" align="center" valign="middle"> <select name="sel_opcion" id="sel_opcion" size="1" class="caja">
                               <option value="0">--Selec--</option>
                              <option value="N">Persona Natural</option>
                              <option value="J">Persona Juridica</option>
                            </select></td>
                          <td align="center" valign="middle"> <input type="text" name="sle_texto" id="sle_texto" size="40" class="caja"  onkeyup="ver_frame()" > 
                            <script language="JavaScript" type="text/javascript">if(document.getElementById) document.getElementById('sle_texto').focus();</script> 
                          </td>
                          <td align="center" valign="middle"> <input type="button" name="button" class="boton" value="Buscar" onClick="ver_frame()" alt="buscar"  ></td>
                          <td align="center" valign="middle"> <input type="button" name="button" class="boton" value="Listar Todos" onClick="listar_todos()" alt="Listar Todos"  ></td>
                          <td align="center" valign="middle"> <input type="button" name="button" class="boton" value="Agregar" onClick="agregar()" alt="Agregar"  ></td>
                          <td valign="top"><!--DWLayoutEmptyCell-->&nbsp;</td>
                        </tr>
                        <tr> 
                          <td height="10" colspan="6" valign="top"><!--DWLayoutEmptyCell-->&nbsp;</td>
                        </tr>
                      </table></td>
                  </tr>
                  <tr valign="middle"> 
                    <td width="100%" align="center" valign="top"> </td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr> 
                    <td align="center" valign="top" height="220"> 
                    <IFRAME id="ifra_frame_listas"  name="ifra_frame_listas" src="VerFrameListas.do?tipo=procedencia&accion=<c:out value='${accion}'/>" align="middle"  frameBorder="0" width="100%"   scrolling="auto" height="100%"  marginheight="30%"  ></IFRAME> 
                    </td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr align="center" valign="middle"> 
                    <td height="28" align="center"   ><A HREF="javascript:regresar() "><font  color="#0000FF"> 
                      Regresar</font></a> </td>
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