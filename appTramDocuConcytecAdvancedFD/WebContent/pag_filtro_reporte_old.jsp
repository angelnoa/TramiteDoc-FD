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
<fmt:setBundle basename="ApplicationResources" />
<HTML>
<HEAD>
    <META HTTP-EQUIV="PowerSiteData" NAME="SERVERLANGUAGE" CONTENT="Java">
    <TITLE>Filtro del Reporte</TITLE>
      <META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href="css/main.css"   rel="stylesheet" type="text/css"> 
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		 <LINK href="css/bkinHome.css" type=text/css rel=stylesheet>
		<LINK href="css/bkin2.css" type=text/css rel=stylesheet>
		<LINK href="css/gridStyle.css" type=text/css rel=stylesheet>
         <link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
   
    <SCRIPT language="JavaScript" src="funciones_varias1.js"></SCRIPT>
    <SCRIPT language="JavaScript">

function reportegenerales()
{
	var ls_accion = "";
	var naturaleza = document.getElementById("naturaleza").value
	var estado = document.getElementById("estado").value
	var tipodoc = document.getElementById("tipodoc").value
	
  
  			
  		if(naturaleza!= 0 && estado==0 && tipodoc==0){
		
		 ls_accion = "N"
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?naturaleza="+naturaleza;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870")
		
		}
		
		if(estado!= 0 && naturaleza==0 && tipodoc==0){
		
		 ls_accion = "E"
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?estado="+estado;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870")
		
		}
		
		if(tipodoc!= 0 && naturaleza==0 && estado==0){
		
		 ls_accion = "T"
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?tipodoc="+tipodoc;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870")
		
		}
		
		if(tipodoc!= 0 && estado!=0 && naturaleza==0){
		
		 ls_accion = "TE"
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?tipodoc="+tipodoc;
			strURL += "&estado="+estado;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870")
		
		}
		
		if(naturaleza!= 0 && estado!=0 && tipodoc==0){
		
		 ls_accion = "NE"
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?naturaleza="+naturaleza;
			strURL += "&estado="+estado;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870")
		
		}
		if(naturaleza!= 0 && tipodoc!=0 && estado==0 ){
		
		 ls_accion = "NT"
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?naturaleza="+naturaleza;
			strURL += "&tipodoc="+tipodoc;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870")
		
		}
		
		if(naturaleza!= 0 && estado!=0 && tipodoc!=0){
		
		 ls_accion = "NET"
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?naturaleza="+naturaleza;
			strURL += "&estado="+estado;
			strURL += "&tipodoc="+tipodoc;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870")
		
		}
		

		
		
}

function reportecriteriousuario()
{
	var ls_accion = "";
	
    var fecha= document.getElementById("sle_fecha").value
	var estado = document.getElementById("estado").value
	
	 <c:choose> 
	 <c:when test='${codigo_oficina ==  "2"}' > 
	var codigo_oficina = document.getElementById("codigo_oficina").value
	var usuario = document.getElementById("usuario").value
	 </c:when> 
	 <c:otherwise> 
	 var codigo_oficina = "0"
	 var usuario = "0"
	 </c:otherwise> 
	 </c:choose> 
	/***************usuario fecha codigo_oficina***********/

		//if(usuario!= 0 && fecha=="" && codigo_oficina==0){
		
		 ls_accion = "U"
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?usuario="+usuario;
			strURL += "&fecha="+fecha;
			strURL += "&codigo_oficina="+codigo_oficina;
			strURL += "&estado="+estado;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870")
		
}



function reportecriteriotipodoc()
{
	var ls_accion = "";
	var codigo_tipo = document.getElementById("codigo_tipo").value
    var procedencia= document.getElementById("procedencia").value
  	var firmadopor = document.getElementById("firmadopor").value

	/***************codigo tipo, procedencia, firmadopor***********/

		//if(codigo_tipo!= 0 && procedencia=="" && firmadopor==""){
		
		 ls_accion = "U"
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?codigo_tipo="+codigo_tipo;
			strURL += "&procedencia="+procedencia.toUpperCase();
			strURL += "&firmadopor="+firmadopor.toUpperCase();
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870")
		
		
}

function Cerrar(){
	 var strURL = "CargaMesaPartes.do?operacion=buscar";
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL
	window.close()
	}
	
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
	
	
	function buscar_persona()
	{
	/*var codigo_tipo = document.getElementById("codigo_tipo").value
    var procedencia= document.getElementById("procedencia").value*/
			//alert("Dentro del alert");
			var codigo_tipo = document.getElementById("codigo_tipo").value
			var firmadopor = document.getElementById("firmadopor").value
			var operacion = document.getElementById("operacion").value
			
			//alert(codigo_tipo);
			//alert(firmadopor);
			accion="R";
 			var strURL = "ValidaPaginas.do?tipo=instcontactos";
			
			strURL += "&codigo_tipo="+codigo_tipo;
			strURL += "&firmadopor="+firmadopor;
			strURL += "&accion="+accion;
			//alert(strURL);
			//strURL += "&valorct="+operacion;
			
 	 location=strURL;
	
	return;
	}
	
	
	function reportecriterioestado()
{
	var ls_accion = "";
	var estado = document.getElementById("estado").value
    var fecha_rpta= document.getElementById("fecha_rpta").value
  	var numero_referencia = document.getElementById("numero_referencia").value

	/***************codigo tipo, procedencia, firmadopor***********/

		//if(codigo_tipo!= 0 && procedencia=="" && firmadopor==""){
		
		 ls_accion = "U"
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?estado="+estado;
			strURL += "&fecha_rpta="+fecha_rpta;
			strURL += "&numero_referencia="+numero_referencia;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870")
		
		
}

	function ver_reporte()
		{
			
			var naturaleza = document.getElementById("naturaleza").value
			var tipodoc= document.getElementById("tipodoc").value
			var estado = document.getElementById("estado").value
			var usuario = document.getElementById("usuario").value
			var sle_fecha = document.getElementById("sle_fecha").value
			var codigo_oficina = document.getElementById("codigo_oficinaz").value
			var estado_criterio_usuario = document.getElementById("estado_criterio_usuario").value
			var codigo_tipo = document.getElementById("codigo_tipo").value
			var procedencia = document.getElementById("procedencia").value
			var firmadopor = document.getElementById("firmadopor").value
			var estado_criterio = document.getElementById("estado_criterio").value
			var fecha_rpta = document.getElementById("fecha_rpta").value
			var numero_referencia = document.getElementById("numero_referencia").value
			var codigo_oficina2 = document.getElementById("codigo_oficina").value;
			
			if (codigo_oficina == 0){
				codigo_oficina=codigo_oficina2
			
			}
							
				 var strURL = "ReportesTramite.do";
				 
					strURL += "?naturaleza="+naturaleza;
					strURL += "&tipodoc="+tipodoc;
					strURL += "&estado="+estado;
					strURL += "&usuario="+usuario;
					strURL += "&sle_fecha="+sle_fecha;
					strURL += "&codigo_oficina="+codigo_oficina;
					strURL += "&estado_criterio_usuario="+estado_criterio_usuario;
					strURL += "&codigo_tipo="+codigo_tipo;
					strURL += "&procedencia="+procedencia;
					strURL += "&firmadopor="+firmadopor;
					strURL += "&estado_criterio="+estado_criterio;
					strURL += "&fecha_rpta="+fecha_rpta;
					strURL += "&numero_referencia="+numero_referencia;
					
					strURL += "&h="+<%=System.currentTimeMillis()%>;
					//alert(strURL);
				window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870")
				
				
		}

	   </SCRIPT>
	   
<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
</HEAD>
<BODY bgColor="white" PSPARAMS="">
<form id="form_datos" name="form_datos" method="post" action="" >	
 <INPUT  type="hidden" id="operacion" name="operacion" value="<c:out value='${operacion}'/>">
 
  <TBODY>
  <TR >
        <TD align="left" valign="middle"  height="50" width="100%"><jsp:include page="/pag_menu.jsp"/></TD>
  </TR>
   
    <TR>
        <TD>
            <TABLE id="TABLE1" width="100%">
              <!--DWLayoutTable-->
             
          
          <TBODY id="TBODY1">
		   <TR class="cabeceratable">
        <TD height="26" colspan="4" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label">Reportes del Sistema </TD>
    <td width="4">&nbsp;</td>
		   </TR>
            <c:choose>
			 <c:when test='${operacion ==  "C"}'> 
			
            <TR class="formulario" id="TR2"> 
			 <INPUT  type="hidden" id="usuario" name="usuario" value="<c:out value='${usuario}'/>">
 <INPUT  type="hidden" id="sle_fecha" name="sle_fecha" value="<c:out value='${sle_fecha}'/>">
 <INPUT  type="hidden" id="codigo_oficina" name="codigo_oficina" value="<c:out value='${codigo_oficina}'/>">
 <INPUT  type="hidden" id="estado_criterio_usuario" name="estado_criterio_usuario" value="<c:out value='${estado_criterio_usuario}'/>">
 <INPUT  type="hidden" id="codigo_tipo" name="codigo_tipo" value="<c:out value='${codigo_tipo}'/>">
 <INPUT  type="hidden" id="procedencia" name="procedencia" value="<c:out value='${procedencia}'/>">
<INPUT  type="hidden" id="firmadopor" name="firmadopor" value="<c:out value='${firmadopor}'/>">
<INPUT  type="hidden" id="estado_criterio" name="estado_criterio" value="<c:out value='${estado_criterio}'/>">
<INPUT  type="hidden" id="fecha_rpta" name="fecha_rpta" value="<c:out value='${fecha_rpta}'/>">
<INPUT  type="hidden" id="numero_referencia" name="numero_referencia" value="<c:out value='${numero_referencia}'/>">

              <TD height="22" colspan="5" valign="middle" class="label3" bgcolor="BAD0EB">&nbsp;&nbsp;&nbsp;Naturaleza / Estado / Tipo Documento</TD>
            </TR>
            <TR class="formulario" id="TR2"> 
              <TD width="137" height="25" align="right" valign="middle"  class="label">Naturaleza 
                : </TD>
              <TD width="234" align="left" valign="middle" id="TD4"> &nbsp;&nbsp;&nbsp; 
                <SELECT class="caja" id="naturaleza" style="WIDTH: 130px" name="naturaleza">
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  <OPTION value="E">Entrada</OPTION>
                  <OPTION value="I">Interno</OPTION>
                </SELECT> </TD>
              <TD width="127" align="right" valign="middle" class="label">Tipo 
                Documento :</TD>
              <TD width="442" align="left" valign="middle"> &nbsp;&nbsp;&nbsp; 
                <select class="caja" id="tipodoc" style="WIDTH: 180px" name="tipodoc">
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  <c:choose> <c:when test='${not empty listatipodoc}'> <c:forEach items='${listatipodoc}' var='pa'>	<option value=<c:out value='${pa.codigo_tipo}'/>> 
                  <c:out value='${pa.descripcion_tipo}'/> </option> </c:forEach> 
                  </c:when> </c:choose> </select> </TD>
              <TD>&nbsp;</TD>
            </TR>
            <TR class="formulario" id="TR2"> 
              <TD height="25" align="right" valign="middle"  class="label">Estado 
                :</TD>
              <TD align="left" valign="middle" id="TD4"> &nbsp;&nbsp;&nbsp; 
                <SELECT class="caja" id="estado" style="WIDTH: 130px" name="estado">
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  <OPTION value="2">Por Recibir</OPTION>
                  <OPTION value="3">Tramite</OPTION>
                  <OPTION value="4">Archivado</OPTION>
                  <OPTION value="5">Derivado</OPTION>
                  <OPTION value="6">Preliquidado</OPTION>
                  <OPTION value="7">Liquidado</OPTION>
                  <OPTION value="9">Anulado</OPTION>
                </SELECT> </TD>
              <TD>&nbsp;</TD>
              <TD>&nbsp;</TD>
              <TD>&nbsp;</TD>
            </TR>
            </c:when> <c:when test='${operacion ==  "CU"}'> 
            <TR class="formulario" id="TR2">
			<INPUT  type="hidden" id="naturaleza" name="naturaleza" value="<c:out value='${naturaleza}'/>">
 <INPUT  type="hidden" id="tipodoc" name="tipodoc" value="<c:out value='${tipodoc}'/>">
 <INPUT  type="hidden" id="estado" name="estado" value="<c:out value='${estado}'/>">
 <INPUT  type="hidden" id="codigo_tipo" name="codigo_tipo" value="<c:out value='${codigo_tipo}'/>">
 <INPUT  type="hidden" id="procedencia" name="procedencia" value="<c:out value='${procedencia}'/>">
<INPUT  type="hidden" id="firmadopor" name="firmadopor" value="<c:out value='${firmadopor}'/>">
<INPUT  type="hidden" id="estado_criterio" name="estado_criterio" value="<c:out value='${estado_criterio}'/>">
<INPUT  type="hidden" id="fecha_rpta" name="fecha_rpta" value="<c:out value='${fecha_rpta}'/>">
<INPUT  type="hidden" id="numero_referencia" name="numero_referencia" value="<c:out value='${numero_referencia}'/>">
 
              <TD height="22" colspan="5" valign="middle" class="label3" bgcolor="BAD0EB">&nbsp;&nbsp;&nbsp;Por 
                Criterio:&nbsp; Usuarios/Areas/Fechas de Documentos Registrados</TD>
            </TR>
            <TR class="formulario" id="TR2"> 
			 <c:if test='${codigo_oficina ==  "2"}'>
              <TD height="26" align="right" valign="middle"  class="label">Usuarios 
                : </TD>
              <TD align="left" valign="middle" id="TD4">&nbsp;&nbsp; 
                <select id="usuario" name="usuario" class="caja" >
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  <c:choose> <c:when test='${not empty listausuarios}'> <c:forEach items='${listausuarios}' var='pa'> <option value=<c:out value='${pa.usuario}'/>> 
                    <c:out value='${pa.usuario}'/> </option> </c:forEach> </c:when> 
                  </c:choose> 
                </select> </TD>
			  </c:if>
              <TD align="right" valign="middle" class="label">Fecha Registro
                : </TD>
              <TD align="left" valign="middle" id="TD5">
                &nbsp;&nbsp;&nbsp;<INPUT language="JavaScript"  onKeyPress="validarCaracterFecha(this);" id="sle_fecha"  maxLength="10" size="11" name="sle_fecha"> 
                &nbsp; <a href="" id="lanzador"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a>              </TD>
              <TD>&nbsp;</TD>
            </TR>
            <TR class="formulario" id="TR2"> 
			 <c:if test='${codigo_oficina ==  "2"}'>
            <TD height="25" align="right" valign="middle"  class="label">Area: </TD>
              <TD align="left" valign="middle" id="TD4">&nbsp;&nbsp;                <select id="codigo_oficina" name="codigo_oficina" class="caja" >
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  <c:choose> <c:when test='${not empty listaoficinas}'> <c:forEach items='${listaoficinas}' var='pa'>
				   <option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/> 
					</option> </c:forEach> 
                    </c:when> </c:choose> 
                </select> </TD>
			  </c:if>
              <TD align="right" valign="middle" class="label">Estado:</TD>
              <TD valign="top">&nbsp;&nbsp;&nbsp;<SELECT class="caja" id="estado_criterio_usuario" style="WIDTH: 130px" name="estado_criterio_usuario">
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  
                  <OPTION value="2">Por Recibir</OPTION>
                  <OPTION value="3">Tramite</OPTION>
                  <OPTION value="4">Archivado</OPTION>
                  <OPTION value="5">Derivado</OPTION>
                  <OPTION value="6">Preliquidado</OPTION>
                  <OPTION value="7">Liquidado</OPTION>
                  <OPTION value="8">Entregado</OPTION>
				  <OPTION value="9">Anulado</OPTION>
                </SELECT> </TD>
              <TD>&nbsp;</TD>
            </TR>
            </c:when>
			<c:when test='${operacion ==  "CT"}'> 
            <TR class="formulario" id="TR2">
			<INPUT  type="hidden" id="usuario" name="usuario" value="<c:out value='${usuario}'/>">
 <INPUT  type="hidden" id="sle_fecha" name="sle_fecha" value="<c:out value='${sle_fecha}'/>">
 <INPUT  type="hidden" id="codigo_oficina" name="codigo_oficina" value="<c:out value='${codigo_oficina}'/>">
 <INPUT  type="hidden" id="estado_criterio_usuario" name="estado_criterio_usuario" value="<c:out value='${estado_criterio_usuario}'/>">

<INPUT  type="hidden" id="naturaleza" name="naturaleza" value="<c:out value='${naturaleza}'/>">
 <INPUT  type="hidden" id="tipodoc" name="tipodoc" value="<c:out value='${tipodoc}'/>">
 <INPUT  type="hidden" id="estado" name="estado" value="<c:out value='${estado}'/>">

<INPUT  type="hidden" id="estado_criterio" name="estado_criterio" value="<c:out value='${estado_criterio}'/>">
<INPUT  type="hidden" id="fecha_rpta" name="fecha_rpta" value="<c:out value='${fecha_rpta}'/>">
<INPUT  type="hidden" id="numero_referencia" name="numero_referencia" value="<c:out value='${numero_referencia}'/>"> 
              <TD height="22" colspan="5" valign="middle" class="label3" bgcolor="BAD0EB">&nbsp;&nbsp;&nbsp;Por 
                Criterio:&nbsp; Tipo Documento/Procedencia/Firma</TD>
            </TR>
            <TR class="formulario" id="TR2"> 
              <TD height="25" align="right" valign="middle"  class="label">Tipo 
                Documento : </TD>
              <TD align="left" valign="middle" id="TD4">&nbsp;&nbsp; 
                <select id="codigo_tipo" name="codigo_tipo" class="caja" >
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  <c:choose> <c:when test='${not empty listatipodoc}'> <c:forEach items='${listatipodoc}' var='pa'>	<option value=<c:out value='${pa.codigo_tipo}'/>> 
                  <c:out value='${pa.descripcion_tipo}'/> </option> </c:forEach> 
                  </c:when> </c:choose> </select> </TD>
              <TD align="right" valign="middle" class="label">Procedencia 
                : </TD>
              <TD align="left" valign="middle" id="TD5">&nbsp;&nbsp; <INPUT class="txt" maxLength="40" size="40" name="procedencia" id="procedencia" value="<c:out value='${descripcion_persona}'/>"  >            </TD>
              <TD>&nbsp;</TD>
            </TR>
            <TR class="formulario" id="TR2"> 
              <TD height="25" align="right" valign="middle"  class="label">Firma 
                : </TD>
              <TD align="left" valign="middle" id="TD4">&nbsp;&nbsp; 
                <INPUT class="txt" id="firmadopor" maxLength="35" size="35" name="firmadopor" >              </TD>
              <TD>&nbsp;</TD>
              <TD>&nbsp;</TD>
              <TD>&nbsp;</TD>
            </TR>
             </c:when>
			<c:otherwise> 
            <TR class="formulario" id="TR2"> 
<INPUT  type="hidden" id="codigo_tipo" name="codigo_tipo" value="<c:out value='${codigo_tipo}'/>">
<INPUT  type="hidden" id="procedencia" name="procedencia" value="<c:out value='${procedencia}'/>">
<INPUT  type="hidden" id="firmadopor" name="firmadopor" value="<c:out value='${firmadopor}'/>">

 <INPUT  type="hidden" id="usuario" name="usuario" value="<c:out value='${usuario}'/>">
 <INPUT  type="hidden" id="sle_fecha" name="sle_fecha" value="<c:out value='${sle_fecha}'/>">
 <INPUT  type="hidden" id="codigo_oficina" name="codigo_oficina" value="<c:out value='${codigo_oficina}'/>">
 <INPUT  type="hidden" id="estado_criterio_usuario" name="estado_criterio_usuario" value="<c:out value='${estado_criterio_usuario}'/>">

<INPUT  type="hidden" id="naturaleza" name="naturaleza" value="<c:out value='${naturaleza}'/>">
<INPUT  type="hidden" id="tipodoc" name="tipodoc" value="<c:out value='${tipodoc}'/>">
<input type="hidden" id="estado" name="estado" value="<c:out value='${estado}'/>">

 				<!-- AQUI EMPEZAMOS -->
              <TD height="22" colspan="5" valign="middle" class="label3" bgcolor="BAD0EB">&nbsp;&nbsp;&nbsp;Por 
                Criterio:&nbsp; Estado/Fecha Respuesta Legal</TD>
            </TR>
            <TR class="formulario" id="TR2"> 
                <TD height="25" align="right" valign="middle"  class="label">Area 
                  :</TD>
                <TD align="left" valign="middle" id="TD4">&nbsp;&nbsp;
                    <select id="codigo_oficinaz" name="codigo_oficinaz" class="caja12" style="WIDTH: 300px" >
                    <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                    <c:choose> 
                    <c:when test='${not empty listaoficinas}'> 
                    	<c:forEach items='${listaoficinas}' var='pa'>
					 		<option value="<c:out value='${pa.codigo_oficina}'/>">
					 		<c:out value='${pa.siglas_oficina}'/>  - <c:out value='${pa.descripcion_oficina}'/>
					 		</option> 
					 	</c:forEach> 
                    </c:when> 
                    </c:choose> 
                    </select> 
                </TD>

              </TR>
            
            <TR class="formulario" id="TR2"> 
              <TD height="25" align="right" valign="middle"  class="label">Estado 
                : </TD>
              <TD align="left" valign="middle" id="TD4">&nbsp;&nbsp; 
                <SELECT class="caja" id="estado_criterio" style="WIDTH: 130px" name="estado_criterio">
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  <OPTION value="2">Por Recibir</OPTION>
                  <OPTION value="3">Tramite</OPTION>
                  <OPTION value="4">Archivado</OPTION>
                  <OPTION value="5">Derivado</OPTION>
                  <OPTION value="6">Preliquidado</OPTION>
                  <OPTION value="7">Liquidado</OPTION>
                  <OPTION value="8">Entregado</OPTION>
				  <OPTION value="9">Anulado</OPTION>
                </SELECT>		      </TD>
              <TD align="right" valign="middle" class="label">Fecha Rpta. Legal
                : </TD>
              <TD align="left" valign="middle" id="TD5">  &nbsp;&nbsp;&nbsp;<INPUT language="JavaScript"  onKeyPress="validarCaracterFecha(this);" id="fecha_rpta"  maxLength="10" size="11" name="fecha_rpta"> 
                &nbsp; <a href="" id="lanzador1"><img src="img/cal.gif" alt="Fecha de Respuesta Legal (dd/mm/yyyy)" border="0"></a>            </TD>
              <TD>&nbsp;</TD>
            </TR>
            <TR class="formulario" id="TR2"> 
              <TD height="25" align="right" valign="middle"  class="label">N&deg; de Referencia:              </TD>
              <TD align="left" valign="middle" id="TD4">&nbsp;&nbsp; 
                <INPUT class="txt" id="numero_referencia" maxLength="20" size="20" name="numero_referencia" >              </TD>
              <TD>&nbsp;</TD>
              <TD>&nbsp;</TD>
              <TD>&nbsp;</TD>
            </TR>
            </c:otherwise>
		    </c:choose> 
            <TR id="TR4"> 
              <TD height="106" colSpan="5" align="center" id="TD10"> 
			 <c:if test='${operacion ==  "C" || operacion ==  "CU" || operacion ==  "CT" || operacion ==  "CE"}'>
                  <INPUT class="boton" style="WIDTH: 100px; HEIGHT: 20px" onClick="javascript:ver_reporte()" type="button" value="Ver Reporte" name="cmd_aceptar">   
			    </c:if>	              </TD>
            </TR>
            <TR align="center" valign="middle" class="formulario" id="TR2"> 
              <TD height="30" colspan="5" ><A HREF=" javascript: window.history.back() "><font size="2" color="#0033FF" face="Verdana, Arial, Helvetica, sans-serif">Ir 
                a la página anterior </font></a></TD>
            </TR>
          </TBODY>
       
      <tr>
        <td height="54">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </TABLE >
<c:if test='${operacion ==  "CU"}'>
<SCRIPT type=text/javascript>
    Calendar.setup({
        inputField     :    "sle_fecha",      // id del campo de texto
        ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
        button         :    "lanzador" 
		   // el id del botón que lanzará el calendario
    });
	
</SCRIPT>
</c:if>

<c:if test='${operacion ==  "CE"}'>
<SCRIPT type=text/javascript>
	Calendar.setup({
        inputField     :    "fecha_rpta",      // id del campo de texto
        ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
        button         :    "lanzador1" 
		   // el id del botón que lanzará el calendario
    });
	
</SCRIPT>
</c:if>
</form>
</BODY>
</HTML>
<% } %>