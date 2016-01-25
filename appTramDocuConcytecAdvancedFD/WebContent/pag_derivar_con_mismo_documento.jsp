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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 

<HTML>
<fmt:setBundle basename="ApplicationResources" />
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Derivar con el mismo documento</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
     <LINK href="css/PlanSec.css" rel="stylesheet" type="text/css">
	<link href="css/main.css"   rel="stylesheet" type="text/css">  
	<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		 <LINK href="css/bkinHome.css" type=text/css rel=stylesheet>
		<LINK href="css/bkin2.css" type=text/css rel=stylesheet>
    <META content="PB9.0.0.5507" name="GENERATOR">
</HEAD>
<SCRIPT language="JavaScript" src="js/funciones.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/prototype-1.4.0.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/scriptaculous.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/overlibmws.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript src="js/ajaxtags.js"></SCRIPT>
<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
<SCRIPT language="JavaScript" >
function validarCaracterFecha(){
    var objeto = validarCaracterFecha.arguments[0];
    var wKey = window.event.keyCode;
    valor = objeto.value;
    var nAnt = 0;
    n = valor.length;
    if (n > 0){
      for(var i=0; i< n ; i++){
        if(nAnt < n){ 
          if(n==2 && i==1) objeto.value = valor+"/";
          if(n==5 && i==3) objeto.value = valor+"/";
        }
      }
    }
    nAnt = n;
	}
	
	function HabilitaText()
	{
	if (document.form_datos.cbo_fecharpta.checked)
	{
	 
	document.form_datos.fecha_rpta.disabled = false; 
	lanzador1.style.display = '';
	
	}
	else
	{
	
	document.form_datos.fecha_rpta.disabled = true;
	lanzador1.style.display = 'none';
	document.form_datos.fecha_rpta.value="";
	
	}
	}

function agregar(){

 				if(document.form_datos.codigo_oficina.value=="0"){
						  alert("Ingresar Oficina");
				document.form_datos.codigo_oficina.focus();
				return false;
				}
				if(document.form_datos.personas.value=="0"){
				alert("Ingresar Personal");
				document.form_datos.personas.focus();
				return false;
				}
				if(document.form_datos.codigo_motivo.value=="0"){
				alert("Ingresar Motivo");
				document.form_datos.codigo_motivo.focus();
				return false;
				}
				
		   //var strURL="MantDocumentosDerivados.do?operacion=N";
		    var ls_operacion = "N";
		   document.form_datos.action='MantDocumentosDerivados.do'+'?operacion='+ls_operacion;
			form_datos.submit();
	
		   //document.getElementById("ifra_listadocumentos").Document.location.href=strURL
		  
}

function cerrar(){

var strURL = "CargaMesaPartes.do?operacion=buscar";

opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
window.close()

}
</SCRIPT>

<BODY bgColor="white" PSPARAMS="" >
<FORM id="form_datos" name="form_datos" action="" method="post">
<TABLE id="TABLE1"  border="0" align="center" width="100%" bgcolor="#FFFFFF">
    <TBODY id="TBODY1">	
    <TR class="formulario">
	     <TD>
		 <TABLE width="60%" borderColor="#e9e9e9" cellSpacing="0" align="center" border="1" bgColor="#F0F5FB">
		 <TR class="formulario">
		  <TD>
		  <TABLE width="100%" border="0" align="center">
                  <!--DWLayoutTable-->
                  <TR class="formulario" > 
                    <TD height="20" colspan="2"  align="center" valign="top" class="TextFieldOn35"><font color="#000000" size="2" ><strong>DERIVAR DOCUMENTO</strong></font></TD>
                  </TR>
                  <TR class="formulario" > 
                    <TD width="288" align="right" height="18">N&deg; Registro:</TD>
                    <TD width="288" align="left"> &nbsp;&nbsp;<c:out value='${documento}'/> 
                    </TD>
                  </TR>
                  <TR class="formulario"> 
                    <TD align="right" height="18">N&deg; Expediente:</TD>
                    <TD align="left"> &nbsp;&nbsp;<c:out value='${codigo_expediente}'/> 
                    </TD>
                  </TR>
                  <TR class="formulario"> 
                    <TD align="right" height="18"> N&deg; Doc.: </TD>
                    <TD align="left"> &nbsp;&nbsp;<c:out value='${descripcion_tipo}'/><c:out value='${numero_documento}'/> 
                    </TD>
                  </TR>
                  <TR class="formulario"> 
                    <TD align="right">Oficina:</TD>
                    <TD align="left"> <select id="codigo_oficina" name="codigo_oficina" class="caja" style="WIDTH: 250px; HEIGHT: 21px">
                        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                        <c:choose> <c:when test='${not empty rs_oficina}'> <c:forEach items='${rs_oficina}' var='pa'>	<option value=<c:out value='${pa.codigo_oficina}'/>> 
                        <c:out value='${pa.nombre_corto}'/> </option> </c:forEach> 
                        </c:when> </c:choose> </select> </TD>
                  </TR>
                  <TR class="formulario"> 
                    <TD align="right" >Personal:</TD>
                    <TD align="left" valign="middle"> <select id="personas" name="destinatario" class="caja" >
                        <option value="0">:: Personas ::</option>
                      </select> </TD>
                  </TR>
                  <TR class="formulario"> 
                    <TD align="right">Motivo:</TD>
                    <TD align="left"> <select id="codigo_motivo" name="codigo_motivo" class="caja" style="WIDTH: 250px; HEIGHT: 21px">
                        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                        <c:choose> <c:when test='${not empty rs_motivos}'> <c:forEach items='${rs_motivos}' var='pa'>	<option value=<c:out value='${pa.codigo_motivo}'/>> 
                        <c:out value='${pa.descripcion_motivo}'/> </option> </c:forEach> 
                        </c:when> </c:choose> </select> </TD>
                  </TR>
                  <c:choose> <c:when test='${codigo_oficina ==  "0001"}'> </c:when> 
                  <c:otherwise> 
                  <TR class="formulario"> 
                    <TD align="right" height="23">Requiere Prioridad :</TD>
                    <TD align="left"> <input language="JavaScript" type="checkbox" name="cbo_fecharpta" id="cbo_fecharpta" value="checkbox" onClick="javascript:HabilitaText();"> 
                    </TD>
                  </TR>
                  <TR class="formulario"> 
                    <TD align="right">Fecha Rpta:</TD>
                    <TD align="left"> <INPUT language="JavaScript" class="txt" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta"  maxLength="10" size="11" name="fecha_rpta"> 
                      &nbsp; <a href="" id="lanzador1"  style="display: none; width: 20;" ><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a> 
                      &nbsp;dd/mm/yyyy </TD>
                  </TR>
                  </c:otherwise> </c:choose> 
                  <TR class="formulario"> 
                    <TD id="TD1" align="right">Observación: </TD>
                    <TD align="left"> <TEXTAREA id="TEXTAREA1" name="observacion" class="txt" rows="3" cols="35"></TEXTAREA> 
                    </TD>
                  </TR>
                 
                </TABLE>
 </TD>
 </TR>
 </TABLE
 ></TD>
 </TR>
   <TR class="formulario">
        <TD id="TD2" colSpan="2" align="center">
            <INPUT class="Button5" id="INPUT1"  type="button" value="Agregar" name="cmb_agregar" onClick="agregar()"  style="WIDTH: 82px; HEIGHT: 20px">
        </TD>
    </TR>
 <TR class="formulario">
    <TD colspan="2">&nbsp;</TD>

  </TR><TR class="formulario">
    <TD colspan="2">
				<TABLE width="80%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#ffffff" bgcolor="#ffffff" height="100">
                	<TR>
			   			<TD>
						<IFRAME id="ifra_listadocumentos" name="ifra_listadocumentos" align="top" src="VerFrameListas.do?tipo=derivadocumento" frameBorder="0" width="100%" height="100%" scrolling="auto" ></IFRAME>
			   		    </TD>
        		    </TR>
              </TABLE>
     </TD>			  
 </TR>
 <TR class="formulario">
   <TD colspan="2">
  <TABLE width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
            <!--DWLayoutTable-->
            <TR> 
              <TD width="970" height="43" align="center" valign="middle">
<INPUT  class="Button5"  type="button" onClick="cerrar();" name="Grabar" value="Cerrar"  style="WIDTH: 82px; HEIGHT: 20px"></TD>
            </TR>
          </TABLE>
  </TD>
  </TR>
      </TBODY>
	   <ajax:select baseUrl="${pageContext.request.contextPath}/obtenerLista.do" parameters="cambia=ofi&codigo_oficina={codigo_oficina}" source="codigo_oficina" target="personas"  />
</TABLE>

</FORM>
 <c:choose>
<c:when test='${codigo_oficina ==  "0001"}'>
			   
</c:when>
<c:otherwise>
<SCRIPT type=text/javascript>
  
	Calendar.setup({
        inputField     :    "fecha_rpta",      // id del campo de texto
        ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
        button         :    "lanzador1" 
		   // el id del botón que lanzará el calendario
    });
</SCRIPT>
<SCRIPT language="Javascript">
inicio();
function inicio(){
   document.form_datos.fecha_rpta.disabled = "true"; 
};
</SCRIPT>
 </c:otherwise>
 </c:choose>   
</BODY>
</HTML>
<% } %>