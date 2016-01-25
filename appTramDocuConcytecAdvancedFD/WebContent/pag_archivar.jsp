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
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Archivar documento</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="css/main.css"   rel="stylesheet" type="text/css"> 
	<link href="css/estilo1.css" rel="stylesheet" type="text/css">
   
    <SCRIPT language="JavaScript" src="js/funciones_varias1.js"></SCRIPT>
    <SCRIPT language="javascript">
	function Cerrar(){
	 var strURL = "CargaMesaPartes.do?operacion=buscar";
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	window.close()
	}
function EstablecerFoco(){
  document.frmArchivar.sle_observacion.focus();
}

function Validar(){

  if(Validarcampovacio(document.frmArchivar.sle_observacion.value)==false){
    alert("Ingresar la Observacion");
    document.frmArchivar.sle_observacion.focus();
    return;
  }

sle_observacion= document.getElementById("sle_observacion").value

strURL="MantDocumentosArchivar.do?operacion=N"
strURL+="&sle_observacion="+sle_observacion;
strURL += "&h="+<%=System.currentTimeMillis()%>;

opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
window.close()
}
</SCRIPT>
</HEAD>
<BODY bgColor="white" PSPARAMS="" onload="javascript:EstablecerFoco();">
<FORM id="frmArchivar" name="frmArchivar" action="" method="post">
<TABLE align="center"  border="1" cellpadding="0" cellspacing="0" bordercolor="#e9e9e9" >
<TR class="TextFieldOn35"><TD align="center"><font color="#000000" size="2" ><strong>Archivar documento</strong></font></TD></TR>
<TR >
<TD bgColor="#F0F5FB">
<TABLE id="TABLE1" border="0">
    <TBODY id="TBODY1">
    <TR id="TR1">
        <TD id="TD1">

        </TD>
    </TR>
    <TR id="TR2">
        <TD id="TD2">
                <TEXTAREA id="sle_observacion" name="sle_observacion" rows="5" cols="50"></TEXTAREA>
        </TD>
    </TR>
    </TBODY>
</TABLE>
</TD>
</TR>
</TABLE>
<TABLE align="center" width="100%" >
<TR >
<TD align="center">
    <INPUT class="Button5" language="JavaScript" id="cmb_enviar" style="WIDTH: 82px; HEIGHT: 20px" onclick="javascript:Validar();" type="button" value="Grabar" name="cmb_enviar">
	 <INPUT class="Button5" onclick="javascript:Cerrar();" type="button" value="Cerrar" style="WIDTH: 82px; HEIGHT: 20px">
	</TD>
</TR>
</TABLE>
</FORM>
</BODY>
</HTML>
<% } %>