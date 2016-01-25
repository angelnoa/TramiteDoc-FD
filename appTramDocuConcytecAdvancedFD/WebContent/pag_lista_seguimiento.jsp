<%@ include file="taglibs.jsp" %>
<%@ page buffer = "16kb" %>
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

<html>
	<head>
		<title>Seguimiento de Documentos</title>
		
		<link rel="stylesheet" href="css/avatec.css" type="text/css" />
		<link rel="stylesheet" href="css/table.css" type="text/css" />
		<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="css/modelo.css" type="text/css" />
		<link rel="stylesheet" href="css/tipografias.css"  type="text/css">
		
		<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
		<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
		<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css" type="text/css" />
		
		
        <script src="js/funciones.js" type="text/javascript"></script>
        <!-- script type="text/javascript" src="js/scriptaculous.js"></script-->
        
        <!-- script type="text/javascript" src="js/ajaxtags.js"></script-->
        <script language="JavaScript" src="js/avatec.js"></script>
		
		
		<SCRIPT src="js/calendar.js"   type=text/javascript></SCRIPT>
		<!-- librería para cargar el lenguaje deseado -->
		<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
		<!-- librería que declara la función Calendar.setup, que ayuda a generar un calendario en unas pocas líneas de código -->
		<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>
		
		<style type="text/css">
			.cuadrocss{
				position:relative;
				z-index:2;
				display:inline;
				width: 200px; 
				overflow:auto;
				
			}
			
			.cuadrocss:hover {
				/*background: #848FB2;*/
			    cursor: pointer;
				}
				
			</style>
		
		
		
		<SCRIPT type=text/javascript>

		$(document).ready(function(){
		
			$( "#tabs" ).tabs();
			
		});
		
	function ver_frame(){
		
		/*var theForm = document.forms['form_datos'];
	     if (!theForm) {
	         //theForm = document.form1;
	         alert("Entry");
	         return;
	     }else{
	    	 alert("2 "+theForm.sle_fecha_inicio.value);
	     	 return false;
	     	return true;
	     	 theForm.submit();
	     }*/
	     var conteo = 0;
	     if (Validarcampovacio(document.getElementById("codigo_documento").value) == false ){              
				//COMO NUM REGISTRO ES VACIO
				//VERIFICO OTROS CAMPOS
				conteo=1;
				if (Validarcampovacio(document.getElementById("procedencia").value) == false ){
					conteo=2;
					if (Validarcampovacio(document.getElementById("firmadopor").value) == false ){
						conteo=3;
						if (Validarcampovacio(document.getElementById("dirigido").value) == false ){
							conteo=4;
						}
					}
				}
				//return false;
		 } 
	    //alert(document.getElementById("procedencia").value);
	    // alert(conteo);
	    
		if((document.getElementById("sle_fecha_inicio").value !='') && (validaFechaDDMMAAAA(document.getElementById("sle_fecha_inicio").value)==false)){
			alert("Ingrese una Fecha Inicio Valida");
			document.form_datos.sle_fecha_inicio.focus();
			return false;
		}
	     
		if((document.getElementById("sle_fecha_fin").value !='') && (validaFechaDDMMAAAA(document.getElementById("sle_fecha_fin").value)==false)){
			alert("Ingrese una Fecha Fin valida");
			document.form_datos.sle_fecha_fin.focus();
			return false;
		}
		
		if((document.getElementById("sle_fecha").value !='') && (validaFechaDDMMAAAA(document.getElementById("sle_fecha").value)==false)){
			alert("Ingrese una Fecha de Registro valida");
			document.form_datos.sle_fecha.focus();
			return false;
		}

		document.form_datos.action='ListaDocumentos.do?tipo=seguimiento&operacion=S';
	  	document.form_datos.target='';
		document.form_datos.submit();
		
		}
	
	function validarCaracterFecha(){
	    var objeto = validarCaracterFecha.arguments[0];
	    var wKey = window.event.keyCode;
	    var numeros = "0123456789";
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
	
	 function validaFechaDDMMAAAA(fecha){
			var dtCh= "/";
			var minYear=1900;
			var maxYear=2100;
			function isInteger(s){
				var i;
				for (i = 0; i < s.length; i++){
					var c = s.charAt(i);
					if (((c < "0") || (c > "9"))) return false;
				}
				return true;
			}
			function stripCharsInBag(s, bag){
				var i;
				var returnString = "";
				for (i = 0; i < s.length; i++){
					var c = s.charAt(i);
					if (bag.indexOf(c) == -1) returnString += c;
				}
				return returnString;
			}
			function daysInFebruary (year){
				return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
			}
			function DaysArray(n) {
				for (var i = 1; i <= n; i++) {
					this[i] = 31;
					if (i==4 || i==6 || i==9 || i==11) {this[i] = 30;}
					if (i==2) {this[i] = 29;}
				}
				return this;
			}
			function isDate(dtStr){
				var daysInMonth = DaysArray(12);
				var pos1=dtStr.indexOf(dtCh);
				var pos2=dtStr.indexOf(dtCh,pos1+1);
				var strDay=dtStr.substring(0,pos1);
				var strMonth=dtStr.substring(pos1+1,pos2);
				var strYear=dtStr.substring(pos2+1);
				strYr=strYear;
				if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1);
				if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1);
				for (var i = 1; i <= 3; i++) {
					if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
				}
				month=parseInt(strMonth);
				day=parseInt(strDay);
				year=parseInt(strYr);
				if (pos1==-1 || pos2==-1){
					return false;
				}
				if (strMonth.length<1 || month<1 || month>12){
					return false;
				}
				if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
					return false;
				}
				if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
					return false;
				}
				if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
					return false;
				}
				return true;
			}
			if(isDate(fecha)){
				return true;
			}else{
				return false;
			}
		}
	 
	function listar_todos(){
		var strURL = "ListaDocumentos.do?tipo=seguimiento&operacion=S";
		
	 	 location=strURL;
		return;

	}
	
		
	function regresar(){
	
	 document.location.href="ValidaPaginas.do?tipo=regresar&pagina=docentrada";
	return;
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
		
		function VerHojaEnvio(codigo_documento,codigo_expediente,codigo_recepcion){
		
		//alert(pcodigo_documento);
		//alert(pcodigo_expediente);
		
				 var strURL="ValidaPaginas.do?tipo=hojaenvio";
					strURL+="&codigo_recepcion="+codigo_recepcion;
					strURL+="&codigo_documento="+codigo_documento;
					strURL+="&codigo_expediente="+codigo_expediente;
					//window.location.href=strURL
					window.open(strURL,"","HEIGHT=440,WIDTH=770,scrollbars=yes");
	
		}
		
		
function exportar_excel(){

	//document.form_datos.action='VerDatosPatente.do?tipo=reporte_excel';
//	var strURL = "ListaDocumentos.do?tipo=seguimiento&operacion=S";
	document.form_datos.action='ListaDocumentos.do?tipo=reporte_excel';
  	document.form_datos.target='';
	document.form_datos.submit();
}

function exportar_excel2(){

	//document.form_datos.action='VerDatosPatente.do?tipo=reporte_excel';
//	var strURL = "ListaDocumentos.do?tipo=seguimiento&operacion=S";
	document.form_datos.action='generateReport.do';
  	document.form_datos.target='_blank';
	document.form_datos.submit();
}

function mostrar_lista(){
	
	$("#div_frame_listas").addClass("cuadrocss");
	//$("#cuadro-procedencia").addClass("cuadrocss");
	
	$(document).ready(function(){
	    var value = $("#procedencia").val();
	    var ls_operacion = $("#sel_opcion").val();
	    var ls_accion = $("#accion").val();

	    var operacion = 'dirigidoa';
	    //llama al servlet con el parametro seleccionado
	    $("#div_frame_listas").load("obtenerLista.do",{cambia:operacion,ls_texto:value,operacion:ls_operacion,accion:ls_accion});

});

}

function habilita(form){ 
	document.getElementById("usu_tipo_valor").value='dirigido';
	document.getElementById("firmadopor").value='';
}

function deshabilita(form){ 
	document.getElementById("usu_tipo_valor").value='no_dirigido';
	document.getElementById("firmadopor").value='';
}

$(function() {
	/*var availableTags = [
	                     "Sr.",
	                     "Sra.",
	                     "Dr.",
	                     "Dra.",
	                     "Eco.",
	                     "Abo.",
	                     "Ing.",
	                     "Mg."
	                   ];
	*/
	
	
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#procedencia" )
      // don't navigate away from the field on tab when selecting an item
      .bind( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).data( "ui-autocomplete" ).menu.active ) {
          event.preventDefault();
        }
      })
      .autocomplete({
          source: function( request, response ) {$.getJSON("obtenerListaSeguimiento.do",{term: extractLast( request.term )}, response);},
          search: function() {
            // custom minLength
            var term = extractLast( this.value );
            if ( term.length < 3 ) {
              return false;
            }
          },
          focus: function() {
            // prevent value inserted on focus
            //alert("rr");
            return false;
          },
          select: function( event, ui ) {
            var terms = split( this.value );
            // remove the current input
            terms.pop();
            // add the selected item
            terms.push( ui.item.value );
            // add placeholder to get the comma-and-space at the end
    //        terms.push( "" );
    //      this.value = terms.join( ", " );
            this.value = terms;
            
            return false;
          }
        });
    
    $( "#firmadopor" )
    // don't navigate away from the field on tab when selecting an item
    .bind( "keydown", function( event ) {
      if ( event.keyCode === $.ui.keyCode.TAB &&
          $( this ).data( "ui-autocomplete" ).menu.active ) {
        event.preventDefault();
      }
    })
    .autocomplete({
        source: function( request, response ) {
      	  //var tipo_ls = $( "#usu_tipo_valor" ).val();
        	$.getJSON("obtenerListaSeguimiento.do",{term: extractLast( request.term ),tipo:'firmadox'}, response);},
        search: function() {
          // custom minLength
          var term = extractLast( this.value );
          if ( term.length < 3 ) {
            return false;
          }
        },
        focus: function() {
          // prevent value inserted on focus
          return false;
        },
        select: function( event, ui ) {
          var terms = split( this.value );
          // remove the current input
          terms.pop();
          // add the selected item
          terms.push( ui.item.value );
          // add placeholder to get the comma-and-space at the end
  //        terms.push( "" );
  //      this.value = terms.join( ", " );
          this.value = terms;
          return false;
        }
      });
    
    
    $( "#dirigido" )
    // don't navigate away from the field on tab when selecting an item
    .bind( "keydown", function( event ) {
      if ( event.keyCode === $.ui.keyCode.TAB &&
          $( this ).data( "ui-autocomplete" ).menu.active ) {
        event.preventDefault();
      }
    })
    .autocomplete({
        source: function( request, response ) {$.getJSON("obtenerListaSeguimiento.do",{term: extractLast( request.term ),tipo:'dirigido'}, response);},
        search: function() {
          // custom minLength
          var term = extractLast( this.value );
          if ( term.length < 3 ) {
            return false;
          }
        },
        focus: function() {
          // prevent value inserted on focus
          return false;
        },
        select: function( event, ui ) {
          var terms = split( this.value );
          // remove the current input
          terms.pop();
          // add the selected item
          terms.push( ui.item.value );
          // add placeholder to get the comma-and-space at the end
  //        terms.push( "" );
  //      this.value = terms.join( ", " );
          this.value = terms;
          return false;
        }
      });
  });

			
</SCRIPT>

	</head>
	<body bgcolor="#F0F5FB" topmargin="0" leftmargin="0" onLoad="fn_onLoad();">
	<div class="derivaconta">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
		<form id="form_datos" name="form_datos" method="post" action="">
		<INPUT  type="hidden" id="usu_tipo_valor" name="usu_tipo_valor" value="dirigido">
		
		<div id="break" ></div>
			<div id="contactderiva" class="rounded"> 
			<div id="TDHeadCab">
						   		   	<span class="labelrounded">SEGUIMIENTO DE DOCUMENTOS </span>
						   		   	
						  			<!--div class="linklista"><a  href="javascript:listadocumentos()">
						  			<img src="img/modifica.gif" alt="Modificar Documentos" width="32" height="32" border="0" ></a>
						  			</div-->
						   		
			</div>
			<div id="Lineaform2" ></div>
						
			
			<div id="left" class="columna-left2" > 
			
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;N&deg; Registro:</label>
			<input type="text" name="codigo_documento" id="codigo_documento" size="10" class="input-medium"   onkeypress="return validatecla('enteros',this)" >
			 <script language="JavaScript" type="text/javascript">if(document.getElementById) document.getElementById('codigo_documento').focus();</script>
			</p>
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fecha Inicio:</label>
				 <INPUT  id="sle_fecha_inicio"  name="sle_fecha_inicio"  value="" style="width: 90px;"   class="input-medium"  onkeypress="validarCaracterFecha(this);" size="12" maxlength="10"> 
                  &nbsp; <a href="" id="lanzador1"><img src="img/cal.gif" title="Fecha de Inicio (dd/mm/yyyy)" border="0"></a>
			</p>
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Procedencia:</label>
			  <INPUT class="input" maxLength="40" size="40" name="procedencia" id="procedencia">
			</p>
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Medio:</label>
			               <select id="medio" name="medio"  style="width: 100px;" class="input-medium">
									<option value="0">--Selec--</option>
									<option value="OR">Original</option>
									<option value="FA">Fax </option>
									<option value="CO">Copia Inf.</option>
									<option value="EM">Email</option>
							</select>
			</p>
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Area/Oficinas:</label>
			   <select id="codigo_oficina" name="codigo_oficina" class="input" >
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty rs_oficina_lista}'>
					 <c:forEach items='${rs_oficina_lista}' var='pa'>
					 	<option value=<c:out value='${pa.codigo_oficina}'/>> 
                    <c:out value='${pa.siglas_oficina}'/>  - <c:out value='${pa.descripcion_oficina}'/> 
					</option> </c:forEach> 
                    </c:when> </c:choose> </select>
			</p>
			<!-- p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<input type="radio" name="boletin" value="si" checked onClick="habilita(this.form)"><label> Persona Interna.</label> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="boletin" value="no" onClick="deshabilita(this.form)"><label> Persona Externa.</label> </p-->
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Firmado por:</label>
				  <input type="text" name="firmadopor" id="firmadopor" size="20" class="input">
			</p>
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Estado:</label>
				  <SELECT class="input-medium" id="estado"  name="estado">
						  
						  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
						  <c:forEach items="${estados}" var="e">
						  <OPTION value="<c:out value='${e.codigo}'/>"><c:out value='${e.descripcion}'/></OPTION>						  
						  </c:forEach>
				 </SELECT>
			</p>
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Doc. de Rpta:</label>
				  <INPUT class="input" maxLength="70" size="40" name="doc_resp" id="doc_resp"  >
			</p>
			<p><label>Acci&oacute;n a Realizar:</label>
				 <select id="codigo_motivo" name="codigo_motivo" class="input" >
                        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                        <c:choose> <c:when test='${not empty rs_motivos}'> <c:forEach items='${rs_motivos}' var='pa'>	<option value=<c:out value='${pa.codigo_motivo}'/>> 
                        <c:out value='${pa.descripcion_motivo}'/> </option> </c:forEach> 
                        </c:when> </c:choose> 
                </select>
             </p>
			</div>
	
			<div id="center"  class="columna-center3" >&nbsp;</div>
			 <div id="rigth" class ="columna-rigth3" >
			<p><label>Fecha de Registro:</label>
			     <INPUT onKeyPress="validarCaracterFecha(this);" id="sle_fecha"  style="width: 90px;" maxLength="10" size="11" name="sle_fecha" class="input-medium"> 
                  &nbsp; <a href="" id="lanzador"><img src="img/cal.gif" title="Fecha de Registro (dd/mm/yyyy)" border="0"></a>
			</p>
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fecha Fin:</label>
			      <INPUT   onKeyPress="validarCaracterFecha(this);" id="sle_fecha_fin" style="width: 90px;" maxLength="10" size="11" name="sle_fecha_fin" class="input-medium"> 
                  &nbsp; <a href="" id="lanzador2"><img src="img/cal.gif" title="Fecha de Fin (dd/mm/yyyy)" border="0"></a>
			</p>
			<p><label>&nbsp;Tipo Documento :</label>
			<select id="codigo_tipo" name="codigo_tipo" class="input-medium" >
                    <OPTION value="0" selected> -- Seleccionar -- </OPTION>
                    <c:choose> 
                    <c:when test='${not empty listatipodoc}'> 
                    <c:forEach items='${listatipodoc}' var='pa'><option value=<c:out value='${pa.codigo_tipo}'/>> 
                    <c:out value='${pa.descripcion_tipo}'/> </option> 
                    </c:forEach> 
                    </c:when> </c:choose> </select>
			</p>
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Solicitud:</label>
			<select id="codigo_solicitud" name="codigo_solicitud" class="input-medium" style="WIDTH: 280px;">
                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
                    <c:choose> <c:when test='${not empty listasolicitud}'> <c:forEach items='${listasolicitud}' var='pa'>	<option value=<c:out value='${pa.codigo_solicitud}'/>> 
                    <c:out value='${pa.nombre_solicitud}'/> </option> </c:forEach> 
                    </c:when> </c:choose> 
             </select>
			</p>
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dirigido a:</label>
				<input type="text" name="dirigido" id="dirigido" size="20" class="input"   >
			</p>
			<p><label>N&deg; de Expediente:</label>
				<input type="text" name="codigo_expediente" id="codigo_expediente" size="8" class="input-medium" onkeypress="return validatecla('enteros',this)" >
			</p>
			<p><label>N&deg; de Documento:</label>
			<input type="text" name="numero_documento" id="numero_documento" size="40" class="input-medium"   >
			</p>
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Asunto:</label>
			<input type="text" name="asunto_documento" id="asunto_documento" size="80" class="input"   >
			</p>
			<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Observación:</label>
			<textarea id="observacion" style="WIDTH: 300px; HEIGHT: 60px"  class="textarea" name="observacion" rows="2" cols="80"></textarea>
			<!-- <input type="text" name="observacion" id="observacion" size="80" class="input"   > -->
			</p>
						
			</div>
			<div class="center-big">
			<input  type="button" id="btnBuscar" name="btnBuscar" class="button" style="background: #156bb9; background-image:url(img/ic_zoomv2.png); background-repeat:no-repeat; background-position:left; width: 110px;" value="Buscar" onClick="ver_frame()" title="Buscar" >
			
			<!-- input type="button" name="button" class="button" value="Listar Todos" onClick="listar_todos()" alt="Listar Todos"-->
			</div>
			<br>
			<SCRIPT type=text/javascript>
				Calendar.setup({
					inputField     :    "sle_fecha",      // id del campo de texto
					ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
					button         :    "lanzador" 
					   // el id del botón que lanzará el calendario
				});
				
				Calendar.setup({
					inputField     :    "sle_fecha_inicio",      // id del campo de texto
					ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
					button         :    "lanzador1" 
					   // el id del botón que lanzará el calendario
				});
				
				Calendar.setup({
					inputField     :    "sle_fecha_fin",      // id del campo de texto
					ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
					button         :    "lanzador2" 
					   // el id del botón que lanzará el calendario
				});
			</SCRIPT>
			</div>
		
		
		
		<div id="break" ></div>
				<div class="derivalista">
				<!-- p>requestURI="/ListaDocumentos.do?tipo=seguimiento&operacion=S"</p-->
					<div id="tabs">
					  <ul>
					    <li><a href="#tabs-1">Lista de documentos</a></li>
					   
					  </ul>
					  <div id="tabs-1">	
					  <table width="100%" align="center" cellpadding="0" cellspacing="0">
						  <!--DWLayoutTable-->
						<tr>
							<td width="50%" height="25" align="right" valign="middle" background="img/fondoplomo8.jpg" class="label">
							Lista de documentos								</td>
						  <td width="50%" align="right" valign="middle" background="img/fondoplomo8.jpg">Exportar a:&nbsp;
						   <A  href="javascript:exportar_excel2()" ><img src="img/xls.gif" border="0" height="20" alt="Exportar a Excel"></A>
						   </td>
						</tr>
						
						
							<tr>
								<td colspan="2">
										<display:table id="b" name="sessionScope.FFormMantSeguimiento.seguimientoList" 
										pagesize="10" export="false" style="width:100%" class="simple" > 
										<display:column property="codigo_documento" sortable="true"  title="N&deg; Registro"   />
										<display:column property="codigo_expediente" sortable="true"  title="N&deg; Expediente"  />
										<display:column property="numero_documento" sortable="true"  title="N&deg;  Documento"  />
										<display:column property="fecha_registro" sortable="true"  title="Fecha Registro"  />
										<display:column property="firmadopor" sortable="true"  title="Dependencia Origen - Firmado por"  />
									
										<display:column property="oficina_origen" sortable="true"  title="Oficina Origen"  />
										<display:column property="oficina_deriva" sortable="true"  title="Oficina Destino"  />
										<display:column property="estado_movimiento" sortable="true"  title="Estado"  />
										<display:column property="view_archivo" align="center"  title="Ver Documento" />
										<display:column property="asunto_documento" sortable="true"  title="Asunto Documento" />
										</display:table>
										</td>
							</tr>
						</table>
					</div>	
					</div>
				</div>

		</FORM>
		</div>
	</body>
</html>
<% } %>