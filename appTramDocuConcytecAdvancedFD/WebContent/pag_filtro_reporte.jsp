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
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">    
<TITLE>Filtro del Reporte</TITLE>

<SCRIPT LANGUAGE=javascript src="js/overlibmws.js"></SCRIPT>
<script LANGUAGE="JavaScript" src="js/avatec.js"></script>

<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />
<link rel="stylesheet" href="css/modelo.css" type="text/css" />
<link rel="stylesheet" href="css/tipografias.css"  type="text/css">

<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
<!-- librería para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>

    
<SCRIPT language="JavaScript">

function reportegenerales()
{
	var ls_accion = "";
	var naturaleza = document.getElementById("naturaleza").value;
	var estado = document.getElementById("estado").value;
	var tipodoc = document.getElementById("tipodoc").value;
	
  
  			
  		if(naturaleza!= 0 && estado==0 && tipodoc==0){
		
		 ls_accion = "N";
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?naturaleza="+naturaleza;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
		
		}
		
		if(estado!= 0 && naturaleza==0 && tipodoc==0){
		
		 ls_accion = "E";
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?estado="+estado;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
		
		}
		
		if(tipodoc!= 0 && naturaleza==0 && estado==0){
		
		 ls_accion = "T";
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?tipodoc="+tipodoc;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
		
		}
		
		if(tipodoc!= 0 && estado!=0 && naturaleza==0){
		
		 ls_accion = "TE";
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?tipodoc="+tipodoc;
			strURL += "&estado="+estado;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
		
		}
		
		if(naturaleza!= 0 && estado!=0 && tipodoc==0){
		
		 ls_accion = "NE";
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?naturaleza="+naturaleza;
			strURL += "&estado="+estado;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
		
		}
		if(naturaleza!= 0 && tipodoc!=0 && estado==0 ){
		
		 ls_accion = "NT";
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?naturaleza="+naturaleza;
			strURL += "&tipodoc="+tipodoc;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
		
		}
		
		if(naturaleza!= 0 && estado!=0 && tipodoc!=0){
		
		 ls_accion = "NET";
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?naturaleza="+naturaleza;
			strURL += "&estado="+estado;
			strURL += "&tipodoc="+tipodoc;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
		
		}
		

		
		
}

function reportecriteriousuario()
{
	var ls_accion = "";
	
    var fecha= document.getElementById("sle_fecha").value;
	var estado = document.getElementById("estado").value;
	
	 <c:choose> 
	 <c:when test='${codigo_oficina ==  "2"}' > 
	var codigo_oficina = document.getElementById("codigo_oficina").value;
	var usuario = document.getElementById("usuario").value;
	 </c:when> 
	 <c:otherwise> 
	 var codigo_oficina = "0";
	 var usuario = "0";
	 </c:otherwise> 
	 </c:choose> 
	/***************usuario fecha codigo_oficina***********/

		//if(usuario!= 0 && fecha=="" && codigo_oficina==0){
		
		 ls_accion = "U";
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?usuario="+usuario;
			strURL += "&fecha="+fecha;
			strURL += "&codigo_oficina="+codigo_oficina;
			strURL += "&estado="+estado;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
		
}



function reportecriteriotipodoc()
{
	var ls_accion = "";
	var codigo_tipo = document.getElementById("codigo_tipo").value;
    var procedencia= document.getElementById("procedencia").value;
  	var firmadopor = document.getElementById("firmadopor").value;

	/***************codigo tipo, procedencia, firmadopor***********/

		//if(codigo_tipo!= 0 && procedencia=="" && firmadopor==""){
		
		 ls_accion = "U";
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?codigo_tipo="+codigo_tipo;
			strURL += "&procedencia="+procedencia.toUpperCase();
			strURL += "&firmadopor="+firmadopor.toUpperCase();
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
		
		
}

function Cerrar(){
	 var strURL = "CargaMesaPartes.do?operacion=buscar";
	
	opener.document.getElementById("ifra_listadocumentos").Document.location.href=strURL;
	window.close();
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
			var codigo_tipo = document.getElementById("codigo_tipo").value;
			var firmadopor = document.getElementById("firmadopor").value;
			var operacion = document.getElementById("operacion").value;
			
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
	var estado = document.getElementById("estado").value;
    var fecha_rpta= document.getElementById("fecha_rpta").value;
  	var numero_referencia = document.getElementById("numero_referencia").value;

	/***************codigo tipo, procedencia, firmadopor***********/

		//if(codigo_tipo!= 0 && procedencia=="" && firmadopor==""){
		
		 ls_accion = "U";
		 var strURL = "ReportesTramite.do";
		 
			strURL += "?estado="+estado;
			strURL += "&fecha_rpta="+fecha_rpta;
			strURL += "&numero_referencia="+numero_referencia;
			strURL += "&accion="+ls_accion;
			strURL += "&h="+<%=System.currentTimeMillis()%>;
			
		window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
		
		
}

	function ver_reporte()
		{
			
			var naturaleza = document.getElementById("naturaleza").value;
			var tipodoc= document.getElementById("tipodoc").value;
			var estado = document.getElementById("estado").value;
			var usuario = document.getElementById("usuario").value;
			var sle_fecha = document.getElementById("sle_fecha").value;
			var codigo_oficina = document.getElementById("codigo_oficinaz").value;
			var estado_criterio_usuario = document.getElementById("estado_criterio_usuario").value;
			var codigo_tipo = document.getElementById("codigo_tipo").value;
			var procedencia = document.getElementById("procedencia").value;
			var firmadopor = document.getElementById("firmadopor").value;
			var estado_criterio = document.getElementById("estado_criterioz").value;
			var fecha_rpta = document.getElementById("fecha_rptaz").value;
			var numero_referencia = document.getElementById("numero_referenciaz").value;
			var codigo_oficina2 = document.getElementById("codigo_oficina").value;
			//alert("hola");
			if (codigo_oficina == 0){
				codigo_oficina=codigo_oficina2;
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
				window.open(strURL,"","scrollbars,HEIGHT=500,WIDTH=870");
				
				
		}

	   </SCRIPT>

</HEAD>

<BODY bgColor="#F0F5FB" >
<div class="container">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
	<form id="form_datos" name="form_datos" method="post" action="" >
	<INPUT  type="hidden" id="operacion" name="operacion" value="<c:out value='${operacion}'/>">
	
	<INPUT  type="hidden" id="naturaleza" name="naturaleza" value="<c:out value='${naturaleza}'/>">
	 <INPUT  type="hidden" id="tipodoc" name="tipodoc" value="<c:out value='${tipodoc}'/>">
	 <INPUT  type="hidden" id="estado" name="estado" value="<c:out value='${estado}'/>">
	 <INPUT  type="hidden" id="codigo_tipo" name="codigo_tipo" value="<c:out value='${codigo_tipo}'/>">
	 <INPUT  type="hidden" id="procedencia" name="procedencia" value="<c:out value='${procedencia}'/>">
	<INPUT  type="hidden" id="firmadopor" name="firmadopor" value="<c:out value='${firmadopor}'/>">
	<INPUT  type="hidden" id="estado_criterio" name="estado_criterio" value="<c:out value='${estado_criterio}'/>">
	<INPUT  type="hidden" id="fecha_rpta" name="fecha_rpta" value="<c:out value='${fecha_rpta}'/>">
	<INPUT  type="hidden" id="numero_referencia" name="numero_referencia" value="<c:out value='${numero_referencia}'/>">
	
 <INPUT  type="hidden" id="usuario" name="usuario" value="<c:out value='${usuario}'/>">
 <INPUT  type="hidden" id="sle_fecha" name="sle_fecha" value="<c:out value='${sle_fecha}'/>">
 <INPUT  type="hidden" id="codigo_oficina" name="codigo_oficina" value="<c:out value='${codigo_oficina}'/>">
 <INPUT  type="hidden" id="estado_criterio_usuario" name="estado_criterio_usuario" value="<c:out value='${estado_criterio_usuario}'/>">


	<div id="break" ></div>
	
	<div id="contactform-busqueda" class="rounded" >
	<div id="TDHeadCab">

				    		<span class="labelrounded">Criterio: Usuarios/Areas/Fechas de Documentos Registrados</span> 
	<div  id ="botoninvisibleAnular" style="width:180px; height:20px; position: absolute; top: 6px; padding: 3px 10px; text-align: center;  FONT-SIZE: 8pt; font-weight:normal;  left:80%; color: #003366;  line-height: 20px;" >
	<A HREF=" javascript: window.history.back() "><font size="2" color="#0033FF" face="Verdana, Arial, Helvetica, sans-serif">Ir a la página anterior </font></a>
	</div>
	</div>
	
     <div id="Lineaform" ></div>
    
     <div id="left" class="columna-left-small" > 
     <p><label>Area:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
         <select id="codigo_oficinaz" name="codigo_oficinaz" class="input" >
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
     </p>
     <p><label>Estado:&nbsp;&nbsp;</label>
         <SELECT class="input" id="estado_criterioz"  name="estado_criterioz" style="WIDTH: 150px">
                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                  <OPTION value="2">Por Recibir</OPTION>
                  <OPTION value="3">Tramite</OPTION>
                  <OPTION value="4">Archivado</OPTION>
                  <OPTION value="5">Derivado</OPTION>
                  <OPTION value="6">Preliquidado</OPTION>
                  <OPTION value="7">Liquidado</OPTION>
                  <OPTION value="8">Entregado</OPTION>
				  <OPTION value="9">Anulado</OPTION>
         </SELECT>
     </p>
     
     
     </div>
     <div id="center"  class="columna-center-small" >
     &nbsp;
     </div>
     <div id="rigth" class ="columna-rigth-small" >
     <p><label>Fecha Rpta. Legal:&nbsp;</label>
 		<INPUT   class="input-medium" onKeyPress="validarCaracterFecha(this);" id="fecha_rptaz"  name="fecha_rptaz" maxLength="10" size="11" > 
                &nbsp; <a href="" id="lanzador1"><img src="img/cal.gif" title="Fecha de Respuesta Legal (dd/mm/yyyy)" border="0"></a>
     </p>
     <p><label>N&deg; de Referencia:&nbsp;&nbsp;</label>
         <INPUT class="input-medium" id="numero_referenciaz" maxLength="20" size="20" name="numero_referenciaz" >
     </p>
     </div>
     <div class="center-big">
     <c:if test='${operacion ==  "C" || operacion ==  "CU" || operacion ==  "CT" || operacion ==  "CE"}'>
                  <INPUT class="button"  onClick="javascript:ver_reporte()" type="button" value="Ver Reporte" name="cmd_aceptar">   
	 </c:if>
           
      
     </div>
     <br>
    
     </div>

</form>
</div>

<c:if test='${operacion ==  "CE"}'>
<SCRIPT type=text/javascript>
	Calendar.setup({
        inputField     :    "fecha_rptaz",      // id del campo de texto
        ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
        button         :    "lanzador1" 
		   // el id del botón que lanzará el calendario
    });
	
</SCRIPT>
</c:if>

</BODY>
</HTML>
<% } %>