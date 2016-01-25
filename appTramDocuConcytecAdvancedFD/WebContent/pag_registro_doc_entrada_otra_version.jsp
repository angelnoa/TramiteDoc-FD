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
 <TITLE>Registro de Documento de Entrada</TITLE>
 
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


<!-- script LANGUAGE="JavaScript">
  var request;
   
  function AdjuntarDocumento(valor) {
  
  var form=document.getElementById('form_datos');
  
  
   var url = "http://localhost:8080/appTramDocuWhitDocInternos/MoverArchivoAADocumento.do";
   alert("url: " +url);
    // Perform the AJAX request using a non-IE browser.
    if (window.XMLHttpRequest) {
      request = new XMLHttpRequest();
   
      // Register callback function that will be called when
      // the response is generated from the server.
      request.onreadystatechange = updateWhitArchivos;
   
      try {
      request.open("GET", url, true);
      } catch (e) {
         alert("Unable to connect to server to retrieve count.");
      }
   
      request.send(null);
    // Perform the AJAX request using an IE browser.
    } else if (window.ActiveXObject) {
      request = new ActiveXObject("Microsoft.XMLHTTP");
   
      if (request) {
        // Register callback function that will be called when
        // the response is generated from the server.
        request.onreadystatechange = updateWhitArchivos;
   
        request.open("GET", url, true);
        request.send();
      }
    }
    
    
  }
  
  function updateWhitArchivos() {
    if (request.readyState == 4) {
      if (request.status == 200) {
        //var count = request.responseText;
   
        //document.getElementById('employeeCount').innerHTML = count;
        alert("REalizando lo pedido...");
        RecorrerForm();
      } else {
        alert("Unable to retrieve count from server.");
      }
    }
  }

</script--> 

<style type="text/css">
.buttoncss{
	/*margin:10px 55px 10px 0px;*/
	/*font-weight: bold;*/
	/*line-height: 1;*/
	padding: 6px 10px;
	cursor:pointer;   
	color: #fff;
	text-align: center;
	/*text-shadow: 0 -1px 1px #64799e;*/
	
	/* Background gradient */
	background: #ffffff;
	background: -moz-linear-gradient(top, #ffffff 0%, #c4c4c4 100%);
	background: -webkit-gradient(linear, 0% 5%, 0% 100%, from(#ffffff), to(#c4c4c4));
	
	/* Border style */
  	border: 1px solid #6d6d6d;  
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
  
	/* Box shadow */
	/*-moz-box-shadow: inset 0 1px 0 0 #aec3e5;
	-webkit-box-shadow: inset 0 1px 0 0 #aec3e5;
	box-shadow: inset 0 1px 0 0 #aec3e5;*/
	
	-moz-box-shadow: 0px 2px 2px #7e7e7e;
	-webkit-box-shadow: 0px 2px 2px #7e7e7e;
	box-shadow: 0px 2px 2px #7e7e7e;
}

.buttoncss:hover {
	/*background: #848FB2;*/
    cursor: pointer;
	}
	

#ventana_flotante {
    background: none repeat scroll 0 0 #FFFFFF;
    /* Border style */
  	top:200px;
    /*bottom: 50px;*/
    left: auto;
    margin-left: 200px;
    /*padding: 10px 0 0;*/
    position: fixed;
    text-align: center;
    width: 200px;
    /*height:200px*/
    z-index: 0;
}

	
	
</style>


<SCRIPT type=text/javascript>

$(document).ready(function(){

$("#botoninvisibleAnular").mouseover(function(event){
    $("#botoninvisibleAnular").addClass("buttoncss");
 });
 $("#botoninvisibleAnular").mouseout(function(event){
    $("#botoninvisibleAnular").removeClass("buttoncss");
});
 
 $('#botoninvisibleAnular').click(function() {
	 
		var strURL = "ListaDocumentos.do?tipo=modificar&operacion=ME&codigo_documento=0";
		location=strURL;	
		return;
	});

}); 


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
       
    
    $( "#dirigido" )
    // don't navigate away from the field on tab when selecting an item
    .bind( "keydown", function( event ) {
      if ( event.keyCode === $.ui.keyCode.TAB &&
          $( this ).data( "ui-autocomplete" ).menu.active ) {
        event.preventDefault();
      }
    })
    .autocomplete({
    	
        source: function( request, response ) {
        	var tipo_ls = $( "#codigo_intitucion" ).val();
        	//alert(tipo_ls);
        	$.getJSON("obtenerListaSeguimiento.do",{term: extractLast( request.term ),tipo:'personal',institucion:tipo_ls}, response);},
        search: function() {
          // custom minLength
          var term = extractLast( this.value );
          if ( term.length < 3 ) {
            return false;
          }
        },
        focus: function() {
          // prevent value inserted on focus
          //alert("ss");
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
          
          showInstituciones();
          return false;
        }
      });
  });



function showProducts(){
    //obtiene los objetos productCode, 
    var code=$("#codigo_oficina").val(); //.. y se obtiene el valor
   // alert(code);
    var val2 = 'ofiInterno';
    //llama al servlet con el parametro seleccionado
    $("#personas").load("obtenerListaDestinatarios.do", {cambia:val2,codigo_oficina:code});
}

function limpiaCampos(){
	document.forms[0].dirigido.value="";
	document.forms[0].codigo_oficina.selectedIndex=0;
	document.forms[0].codigo_oficina.length=1;
	document.forms[0].personas.selectedIndex=0;
	document.forms[0].personas.length=1;
}

function showInstituciones(){
    //obtiene los objetos productCode, 
    var nombreusuario=$("#dirigido").val(); //.. y se obtiene el valor
    var codigo_institucion=$("#codigo_intitucion").val();
    var val2 = 'nuevo_metodo';
    $("#codigo_oficina").load("obtenerListaDestinatarios.do", {cambia:val2,codigo_institucion:codigo_institucion,name:nombreusuario});
    refresca();
    //document.forms[0].personas.selectedIndex=0;
	//document.forms[0].personas.length=1;
    
}

function refresca(){
	
	timer = setTimeout('showProducts()', 800);
    
}

	function RecorrerForm()
	{
		var sAux="";
		var frm = document.getElementById("form_datos");
		alert("TAMANIO FORM "+frm.elements.length);
		for (i=0;i<frm.elements.length;i++)
		{
			sAux += "NOMBRE: " + frm.elements[i].name + " ";
			sAux += "TIPO :  " + frm.elements[i].type + " "; ;
			sAux += "VALOR: " + frm.elements[i].value + "\n" ;
		}
		alert(sAux);
	}

    function cambiaCheck(value,form){
	var rbuton = form.rbuton;

	var seleccion = document.getElementById('rb_seleccion');
	seleccion.value = value;
	if(value = 'E'){
	 rbuton.checked = true;

	}else{
     rbuton.checked = false;
	}
		
	}
    function ValidarEntero(valor){
	if (isNaN(valor)) { 
            //entonces (no es numero) devuelvo el valor cadena vacia 
            return ''; 
      }else{ 
            //En caso contrario (Si era un nÃºmero) devuelvo el valor 
            return valor;
      } 

	}
	function Cerrar(){
	 var strURL = "ValidaPaginas.do?tipo=inicio";
	//alert(strURL);
	window.location.href=strURL;
	//window.close()
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
	
			medio = document.getElementById("medio").value;
			codigo_tipo = document.getElementById("codigo_tipo").value;
			numero_documento = document.getElementById("numero_documento").value;
			
			firmadopor = document.getElementById("firmadopor").value;
			fecha_registro = document.getElementById("fecha_registro").value;
			dirigido = document.getElementById("dirigido").value;
			hora = document.getElementById("hora").value;
			folios_documento = document.getElementById("folios_documento").value;
			asunto_documento = document.getElementById("asunto_documento").value;
			codigo_solicitud = document.getElementById("codigo_solicitud").value;
			observa_documento = document.getElementById("observa_documento").value;
			cbo_fecharpta = document.getElementById("cbo_fecharpta").value;
			fecha_rpta = document.getElementById("fecha_rpta").value;
			numero_referencia = document.getElementById("numero_referencia").value;
			personas = document.getElementById("personas").value;
			accion="R";

			var rb_seleccion = document.form_datos.rb_seleccion.value;
						

 			var strURL = "ValidaPaginas.do?tipo=instcontactos";
			
			strURL += "&medio="+medio;
			strURL += "&rb_seleccion="+rb_seleccion;
			strURL += "&codigo_tipo="+codigo_tipo;
			strURL += "&numero_documento="+numero_documento;
			strURL += "&firmadopor="+firmadopor;
			strURL += "&fecha_registro="+fecha_registro;
			strURL += "&dirigido="+dirigido;
			strURL += "&hora="+hora;
			strURL += "&folios_documento="+folios_documento;
			strURL += "&asunto_documento="+asunto_documento;
			strURL += "&codigo_solicitud="+codigo_solicitud;
			strURL += "&observa_documento="+observa_documento;
			strURL += "&cbo_fecharpta="+cbo_fecharpta;
			strURL += "&fecha_rpta="+fecha_rpta;
			strURL += "&numero_referencia="+numero_referencia;
			strURL += "&personas="+personas;
			strURL += "&accion="+accion;
	
 	 location=strURL;
	
	return;
	}

function buscar_persona_dirigido()
	{
	
			medio = document.getElementById("medio").value;
			codigo_tipo = document.getElementById("codigo_tipo").value;
			numero_documento = document.getElementById("numero_documento").value;
			
			firmadopor = document.getElementById("firmadopor").value;
			fecha_registro = document.getElementById("fecha_registro").value;
			dirigido = document.getElementById("dirigido").value;
			hora = document.getElementById("hora").value;
			folios_documento = document.getElementById("folios_documento").value;
			asunto_documento = document.getElementById("asunto_documento").value;
			codigo_solicitud = document.getElementById("codigo_solicitud").value;
			observa_documento = document.getElementById("observa_documento").value;
			cbo_fecharpta = document.getElementById("cbo_fecharpta").value;
			fecha_rpta = document.getElementById("fecha_rpta").value;
			numero_referencia = document.getElementById("numero_referencia").value;
			codigo_oficina_destino = document.getElementById("codigo_oficina").value;
			codigo_persona = document.getElementById("personas").value;
			//selectedItems = document.getElementById("selectedItems").value;
			accion="RD";
			var rb_seleccion=document.form_datos.rb_seleccion.value;

 			var strURL = "ValidaPaginas.do?tipo=dirigidos";
			
			strURL += "&medio="+medio;

			strURL += "&codigo_tipo="+codigo_tipo;
			strURL += "&rb_seleccion="+rb_seleccion;
			strURL += "&numero_documento="+numero_documento;
			strURL += "&firmadopor="+firmadopor;
			strURL += "&fecha_registro="+fecha_registro;
			strURL += "&dirigido="+dirigido;
			strURL += "&hora="+hora;
			strURL += "&folios_documento="+folios_documento;
			strURL += "&asunto_documento="+asunto_documento;
			strURL += "&codigo_solicitud="+codigo_solicitud;
			strURL += "&observa_documento="+observa_documento;
			strURL += "&cbo_fecharpta="+cbo_fecharpta;
			strURL += "&fecha_rpta="+fecha_rpta;
			strURL += "&numero_referencia="+numero_referencia;
			strURL += "&accion="+accion;
			strURL += "&codes="+codigo_oficina_destino;
			strURL += "&codper="+codigo_persona;
			//strURL += "&selectedItems="+selectedItems;
	
 	 location=strURL;
	
	return;
	}
	
function AgregarDocInternos() {

	//alert("Internos");
	
	var strURL="MantenimientoDocumentoInterno.do";
	winPopup = window.open(strURL,"","scrollbars,HEIGHT=540,WIDTH=700");
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
	
function Validar(){
 	
			 if (Validarcampovacio(document.form_datos.numero_documento.value) == false)
	              {              
	                    alert("Ingresar Numero de Documento");
	                    document.form_datos.numero_documento.focus();
	                    document.form_datos.cmb_grabar.value='Registrar';
	                    document.form_datos.cmb_grabar.disabled=false;
	                    return false;           
	              } 
			 
			 if(document.form_datos.codigo_intitucion.value=="0"){
					alert("Ingresar Institucion");
					document.form_datos.codigo_intitucion.focus();
					document.form_datos.cmb_grabar.value='Registrar';
	                document.form_datos.cmb_grabar.disabled=false;
					return false;
				}

			 if ((Validarcampovacio(document.form_datos.fecha_registro.value) == false) || (validaFechaDDMMAAAA(document.form_datos.fecha_registro.value)==false))
              {              
                    alert("Ingresar Fecha Registro");
                    document.form_datos.fecha_registro.focus();
                    document.form_datos.cmb_grabar.value='Registrar';
                    document.form_datos.cmb_grabar.disabled=false;
                    return false;           
              } 
			 
			 if ((Validarcampovacio(document.form_datos.fecha_rpta.value) == true) && (validaFechaDDMMAAAA(document.form_datos.fecha_rpta.value)==false))
             {              
                   alert("Ingresar Fecha Rpta Valida");
                   document.form_datos.fecha_rpta.focus();
                   document.form_datos.cmb_grabar.value='Registrar';
                   document.form_datos.cmb_grabar.disabled=false;
                   return false;           
             }

			/*   if (Validarcampovacio(document.form_datos.sle_nombre_institucion.value) == false)
              {              
                    alert("Ingresar Origen Documento");
                    document.form_datos.desc_origen.focus();
                    return false;           
              } */
			 

			   if (Validarcampovacio(document.form_datos.hora.value) == false)
              {              
                    alert("Ingresar Hora");
                    document.form_datos.hora.focus();
                    document.form_datos.cmb_grabar.value='Registrar';
                    document.form_datos.cmb_grabar.disabled=false;
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.folios_documento.value) == false)
              {              
                    alert("Ingresar Folios del Documento");
                    document.form_datos.folios_documento.focus();
                    document.form_datos.cmb_grabar.value='Registrar';
                    document.form_datos.cmb_grabar.disabled=false;
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.asunto_documento.value) == false)
              {              
                    alert("Ingresar Asunto del Documento");
                    document.form_datos.asunto_documento.focus();
                    document.form_datos.cmb_grabar.value='Registrar';
                    document.form_datos.cmb_grabar.disabled=false;
                    return false;           
              } 
			   if (Validarcampovacio(document.form_datos.firmadopor.value) == false)
              {              
                    alert("Ingresar Persona  que Firma Documento");
                    document.form_datos.firmadopor.focus();
                    document.form_datos.cmb_grabar.value='Registrar';
                    document.form_datos.cmb_grabar.disabled=false;
                    return false;           
              } 
			 
			    
				 if(document.form_datos.medio.value=="0"){
						  alert("Ingresar  Medio");
				document.form_datos.medio.focus();
				document.form_datos.cmb_grabar.value='Registrar';
                document.form_datos.cmb_grabar.disabled=false;
				return false;
				}
				
				 if(document.form_datos.codigo_tipo.value=="0"){
						  alert("Ingresar Codigo Tipo");
							document.form_datos.codigo_tipo.focus();
							document.form_datos.cmb_grabar.value='Registrar';
			                document.form_datos.cmb_grabar.disabled=false;
							return false;
				}
				
				
				if(document.form_datos.codigo_oficina.value=="0"){
					alert("Ingresar  Codigo Oficina");
					document.form_datos.codigo_oficina.focus();
					document.form_datos.cmb_grabar.value='Registrar';
	                document.form_datos.cmb_grabar.disabled=false;
					return false;
				}
				 if(document.form_datos.personas.value=="0"){
						  alert("Ingresar  Personas");
				document.form_datos.personas.focus();
				document.form_datos.cmb_grabar.value='Registrar';
                document.form_datos.cmb_grabar.disabled=false;
				return false;
				}
				
				if(document.form_datos.codigo_solicitud.value=="0"){
						  alert("Ingresar  Solicitud");
				document.form_datos.codigo_solicitud.focus();
				document.form_datos.cmb_grabar.value='Registrar';
                document.form_datos.cmb_grabar.disabled=false;
				return false;
				}
			/**********************************/	
			if (Validarcampovacio(document.form_datos.desc_origen.value) == false )
			  {              
					alert("Ingresar Procedencia");
					document.form_datos.desc_origen.focus();
					document.form_datos.cmb_grabar.value='Registrar';
                    document.form_datos.cmb_grabar.disabled=false;
					return false;           
			  } 
			   if (Validarcampovacio(document.form_datos.dirigido.value) == false)
			  {              
					alert("Ingresar Documento Dirigido");
					document.form_datos.dirigido.focus();
					document.form_datos.cmb_grabar.value='Registrar';
                    document.form_datos.cmb_grabar.disabled=false;
					return false;           
			  } 
				
				//alert(document.getElementById("origen_documento").value);
				/*if(document.form_datos.origen_documento.value==null){
						  alert("Ingresar  Procedencia");
				document.form_datos.desc_origen.focus();
				return false;
				}*/
		
	medio = document.getElementById("medio").value;
	codigo_tipo = document.getElementById("codigo_tipo").value;
	destinatario = document.getElementById("personas").value;
	//alert(destinatario);
	codigo_solicitud = document.getElementById("codigo_solicitud").value;
	numero_documento = document.getElementById("numero_documento").value;
	fecha_registro = document.getElementById("fecha_registro").value;
	hora = document.getElementById("hora").value;
	folios_documento = document.getElementById("folios_documento").value;
	asunto_documento = document.getElementById("asunto_documento").value;
	observa_documento = document.getElementById("observa_documento").value;
	firmadopor = document.getElementById("firmadopor").value;

	codigo_oficina = document.getElementById("codigo_oficina").value;
	/*********************************/
	desc_origen = document.getElementById("desc_origen").value;
	tipo_persona = document.getElementById("tipo_persona").value;
	origen_documento = document.getElementById("origen_documento").value;
	/**************************************/
	cbo_fecharpta = document.getElementById("cbo_fecharpta").value;
	fecha_rpta = document.getElementById("fecha_rpta").value;
	numero_referencia = document.getElementById("numero_referencia").value;
	dirigido = document.getElementById("dirigido").value;

	//JAZANERO
	codigo_intitucion= document.getElementById("pertenece_a").value;
	
	//alert(cbo_fecharpta);
	//alert(fecha_rpta);
	
	var rb_seleccion= document.form_datos.rb_seleccion.value;


	/*document.form_datos.action="MantDocumento.do?operacion=N"+"&medio="+medio
	+"&codigo_tipo="+codigo_tipo+"&codigo_oficina="+codigo_oficina+"&destinatario="+destinatario+"&codigo_solicitud="+
	codigo_solicitud+"&numero_documento="+numero_documento+"&fecha_registro="+fecha_registro+"&hora="+hora+"&folios_documento="+folios_documento+
	"&asunto_documento="+asunto_documento+"&observa_documento="+observa_documento+"&firmadopor="+firmadopor+"&origen_documento="+origen_documento+
	"&desc_origen="+desc_origen+"&tipo_persona="+tipo_persona+"&cbo_fecharpta="+cbo_fecharpta+"&fecha_rpta="+fecha_rpta+"&numero_referencia="
	+numero_referencia+	"&rb_seleccion="+rb_seleccion+"&dirigido="+dirigido+"&codigo_intitucion="+codigo_intitucion;
	
	document.form_datos.submit();	*/
	
}

function AgregarArchivos(){
   
    medio = document.getElementById("medio").value;
	codigo_tipo = document.getElementById("codigo_tipo").value;
	destinatario = document.getElementById("personas").value;
	//alert(destinatario);
	codigo_solicitud = document.getElementById("codigo_solicitud").value;
	numero_documento = document.getElementById("numero_documento").value;
	fecha_registro = document.getElementById("fecha_registro").value;
	hora = document.getElementById("hora").value;
	folios_documento = document.getElementById("folios_documento").value;
	asunto_documento = document.getElementById("asunto_documento").value;
	observa_documento = document.getElementById("observa_documento").value;
	firmadopor = document.getElementById("firmadopor").value;
	operacionper = document.getElementById("operacion").value;
	dirigido = document.getElementById("dirigido").value;
	
	
	alert(dirigido);
	
	var sle_nombre_institucion="";
	var tipo="";
	var origen_documento="";
	var desc_origen="";
	var tipo_persona="";
	var naturaleza_documento="";
	
	/*if(operacionper==""){
	//alert("Dentro de operacion vacio");
	sle_nombre_institucion = document.getElementById("sle_nombre_institucion").value
	tipo = document.getElementById("tipo").value
	}else{
	
		//alert("Dentro de operacion diferente de vacio");
	origen_documento = document.getElementById("origen_documento").value
	desc_origen = document.getElementById("desc_origen").value
	tipo_persona = document.getElementById("tipo_persona").value
	naturaleza_documento = document.getElementById("naturaleza_documento").value
	
	}*/
	
	if(operacionper!=""){
	
	//alert("Dentro de operacion diferente de vacio");
	origen_documento = document.getElementById("origen_documento").value;
	desc_origen = document.getElementById("desc_origen").value;
	tipo_persona = document.getElementById("tipo_persona").value;
	naturaleza_documento = document.getElementById("naturaleza_documento").value;
	//alert(origen_documento+"-"+desc_origen+"-"+tipo_persona+"-"+naturaleza_documento);
	}
	
	//alert(origen_documento);
	codigo_oficina = document.getElementById("codigo_oficina").value;
	
	cbo_fecharpta = document.getElementById("cbo_fecharpta").value;
	fecha_rpta = document.getElementById("fecha_rpta").value;
	numero_referencia = document.getElementById("numero_referencia").value;

				var ls_operacion = "A";
				
				var strURL="MantDocumento.do?"+"operacion="+ls_operacion+"&medio="+medio
	+"&codigo_tipo="+codigo_tipo+"&codigo_oficina="+codigo_oficina+"&destinatario="+destinatario+"&codigo_solicitud="+codigo_solicitud+"&numero_documento="+numero_documento+"&fecha_registro="+fecha_registro+"&hora="+hora+"&folios_documento="+folios_documento+"&asunto_documento="+asunto_documento+"&observa_documento="+observa_documento+"&firmadopor="+firmadopor+"&origen_documento="+origen_documento+"&desc_origen="+desc_origen+"&tipo_persona="+tipo_persona+"&cbo_fecharpta="+cbo_fecharpta+"&fecha_rpta="+fecha_rpta+"&numero_referencia="+numero_referencia+"&desc_origen="+desc_origen+"&naturaleza_documento="+naturaleza_documento
	+"&tipo="+tipo+"&operacionper="+operacionper+"&dirigido="+dirigido;	
				//window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes")
				winPopup = window.open(strURL,"","scrollbars,HEIGHT=250,WIDTH=580");
			
		}

function MostrarAdjunto(codigo){
 			
			//nombre_doc.value = codigo;
			//document.getElementById("doc_adjunto_img").value=codigo;
			//cod_emp.value = operacion;
			//					ActionSeleccion.do?rb_seleccion=E
			//alert(codigo);
			
					
					//var strURL = "MantDocumento.do?operacion=MA&codigo="+codigo;
					var strURL = "ValidaPaginas.do?tipo=seleccion&operacion=T&codigocontador="+codigo;
					location=strURL;
					//alert(strURL);
				
				
}
		
function MensajeRegistro(){
		var strURL="ValidaPaginas.do?tipo=mensajerecepcion";
 		window.open(strURL,"","HEIGHT=140,WIDTH=500,scrollbars=no");
		}
				
function cambiaDestino(){

  document.forms[0].provincia.selectedIndex=0;
  document.forms[0].provincia.length=1;

}

function HabilitaText()
{
if (document.form_datos.cbo_fecharpta.checked)
{ document.form_datos.fecha_rpta.disabled = false; 
}
else
{document.form_datos.fecha_rpta.disabled = true;

}
}

function SeleccionarCampo(campo, valor){
	//alert("campo -"+campo+", valor -"+valor);
  for(var i = 0; i < document.forms[0].elements.length; i++){
    if (document.forms[0].elements[i].name==campo){
      for(var j=0; j< document.forms[0].elements[i].length; j++){      
        if (document.forms[0].elements[i].options[j].value==valor){
          document.forms[0].elements[i].options[j].selected=true;
        }
      }
    }    
  }
}


var winPopup = 0;
function ayuda_doc_pend()
	{
	
		 var strURL = 'ValidaPaginas.do?tipo=ayudamedios';
		//strURL += '&num_doc='+num_doc;
			
	   winPopup = window.open(strURL,"","scrollbars,HEIGHT=540,WIDTH=700")
	}
	
	
function HabilitaText()
		{
		with(document.forms[0]){
						
				if (cbo_fecharpta.checked)
				{
				 
				fecha_rpta.disabled = false; 
				lanzador1.style.display = '';
				
				}
				else
				{
				
				fecha_rpta.disabled = true;
				lanzador1.style.display = 'none';
				fecha_rpta.value="";
				
				}
								}
		}
		
		
		function listadocumentos()
		{
		
		var strURL = "ListaDocumentos.do?tipo=modificar&operacion=ME&codigo_documento=0";
				
		 location=strURL;
		
		return;
		}
	
	
	function HabilitaCombo(valor){
	   var valor_ls=valor;
	   if(valor_ls==1){
		   var scroll_chks = document.getElementById('scroll');
				with(document.forms[0]){
				   if (chk_copia.checked) {
						scroll_chks.style.visibility = 'visible';
				   }
				   else{			
					scroll_chks.style.visibility = 'hidden'; 
				   }
				 }
	   }else{
		   var scroll_chks_s = document.getElementById('scroll_2');
			with(document.forms[0]){
			   if (chk_copia_other.checked) {
				   scroll_chks_s.style.visibility = 'visible';
			   }
			   else{			
				   scroll_chks_s.style.visibility = 'hidden'; 
			   }
			 }
	   }
	}
					
</SCRIPT>
</HEAD>

<BODY bgColor="#F0F5FB" bottommargin="0" leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" >
	
	<div class="derivaconta">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
	<c:choose>
		<c:when test='${operacionpopup ==  "X"}'>
                <SCRIPT language="javascript">
				 MensajeRegistro();
				</SCRIPT>
				<c:remove var="operacionpopup" scope="session"/>
       	</c:when>
       	
	</c:choose> 
	
	<form id="form_datos" name="form_datos" method="post" action="" enctype='multipart/form-data'>	
	<INPUT  type="hidden" id="operacion" name="operacion" value="<c:out value='${operacion}'/>">
	<INPUT  type="hidden" id="pertenece_a" name="pertenece_a" value="<c:out value='${pertenece_a}'/>">
	
		<div id="break" ></div>
		<!-- div id="ventana_flotante" class="rounded">
	nnnnndfgdsdgsgsdghsd<br>hhfdhfgh
	<br>hhfdhfgh
	<br>hhfdhfgh
	<br>hhfdhfgh
	<br>hhfdhfgh
	<br>hhfdhfgh
	<br>hhfdhfgh
	<br>hhfdhfgh
	
	</div-->
			<div id="contactderiva" class="rounded"> 
			
			<c:choose>
				    <c:when test='${codigo_oficina ==  "1"}'>
				    <div id="TDHeadCab">
						   		   	<span class="labelrounded">REGISTRO DOCUMENTO</span>
						   		   	
						   		   	<div  id ="botoninvisibleAnular" title="Modificar Registro" style="width:80px; height:20px; position: absolute; top: 6px; padding: 3px 10px; text-align: center;  FONT-SIZE: 8pt; font-weight:normal;  left:86%; color: #003366;  line-height: 20px;" >&nbsp;<img  src="img/edit_doc.png"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp; Modificar</div>
						  			
						   		
					</div>
					<div id="TDHeadCabRadios" >
				     
				    <input type="hidden" name="rb_seleccion" id="rb_seleccion" value="<c:out value='${rb_seleccion}'/>" />
					<c:choose>
					<c:when test="${rb_seleccion=='I'}">
					  <INPUT name="rbuton" type="radio" value="E" onClick="javascript:cambiaCheck('E',this.form);">
					  Externo&nbsp;
					  <INPUT name="rbuton" type="radio" value="I" checked onClick="javascript:cambiaCheck('I',this.form);">
					                Interno				
					</c:when>
					<c:otherwise>
					 <INPUT name="rbuton" type="radio" value="E" checked onClick="javascript:cambiaCheck('E',this.form);">
					  Externo&nbsp;
					  <INPUT name="rbuton" type="radio" value="I"  onClick="javascript:cambiaCheck('I',this.form);">Interno				
					</c:otherwise>
					</c:choose>
					
				     </div>
					<div id="Lineaform2" ></div>
			
				    <div id="left" class="columna-left2" > 
				    <p><label>Medio:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    <select id="medio" name="medio" class="input-medium" style="width:100" value=''>
	                  <option value="0">--Selec--</option>
	                  <option value="OR">Original</option>
	                  <option value="FA">Fax </option>
	                  <option value="CO">Copia Inf.</option>
	                  <option value="EM">Email</option>
	                </select>&nbsp;&nbsp;<A  href="javascript:ayuda_doc_pend()">Documentos Pendientes</A>
				
				    </p>
				    <p><label>Tipo Doc:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    <select id="codigo_tipo" name="codigo_tipo" class="input-medium" >
	                  <OPTION value="0" selected> -- Seleccionar -- </OPTION>
	                  <c:choose> <c:when test='${not empty rs_tipodoc}'>
	                    <c:forEach items='${rs_tipodoc}' var='pa'>
	                      <option value=<c:out value='${pa.codigo_tipo}'/>> 
	                      <c:out value='${pa.descripcion_tipo}'/> 
	                      </option>
	                      </c:forEach> 
	                    </c:when> </c:choose> 
	                  </select> 
				    </p>
				    <p><label>N&uacute;mero Doc.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
               			<INPUT class="input" id="numero_documento" maxLength="40" size="40" name="numero_documento" style="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();"  value="<c:out value='${numero_documento}'/>">
				    </p>
				    <p><label>Fecha Doc.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				   		<INPUT  class="input-medium" onKeyPress="validarCaracterFecha(this);" id="fecha_registro"  style="width: 90px;" maxLength="10" size="11" name="fecha_registro" value="<c:out value='${fecha}'/>"> 
                		&nbsp; <a href="" id="lanzador"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a>
				    </p>
				    <p><label>Hora:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    	<INPUT  class="input-medium"  style="width: 90px;" id="hora" maxLength="8" size="9" name="hora" value="<c:out value='${hora}'/>">
				    	&nbsp;&nbsp;&nbsp;
				    	<label>Folios:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    	<INPUT class="input-medium" id="folios_documento" style="width: 68px;" maxLength="3" size="3" name="folios_documento" onKeyPress="return validatecla('enteros', this);" value="<c:out value='${folios_documento}'/>">
				    </p>
				    
				    <p><label>Solicitud:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					    <select id="codigo_solicitud" name="codigo_solicitud" class="input" >
	                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
	                    <c:choose> <c:when test='${not empty rs_solicitud}'> <c:forEach items='${rs_solicitud}' var='pa'>	<option value=<c:out value='${pa.codigo_solicitud}'/>> 
	                    <c:out value='${pa.nombre_solicitud}'/> </option> </c:forEach> 
	                    </c:when> </c:choose> 
	                	</select> 
				    </p>
				    
				    <p><label>Asunto:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    	<TEXTAREA id="asunto_documento" class="textarea" style="WIDTH: 330px; HEIGHT: 70px" name="asunto_documento" rows="4" cols="71"><c:out value='${asunto_documento}'/></TEXTAREA>
				    </p>
				    <p><label>Observaci&oacute;n:</label>
				    	<TEXTAREA id="observa_documento" class="textarea" style="WIDTH: 330px; HEIGHT: 70px" name="observa_documento" rows="4" cols="50"><c:out value='${observa_documento}'/></TEXTAREA>
				    </p>
				    
				    
					</div>
					
				    <div id="center"  class="columna-center3" >&nbsp;</div>
				    
			 		<div id="rigth" class ="columna-rigth3" >
				    <p><label>Procedencia:</label>
				   	<c:choose>
						<c:when test='${codigo_oficina ==  "1"}'>
						 <c:choose> 
							<c:when test='${operacion == "T" }' > 
			                  <INPUT class="input"  maxLength="40" size="40" id="desc_origen"  name="desc_origen" readonly="true" value="<c:out value='${descripcion_persona}'/>"> 
			                  <INPUT  type="hidden" maxLength="6" size="6" name="origen_documento" id="origen_documento" value='<c:out value='${codigo}'/>'> 
			                  <INPUT  type="hidden" maxLength="6" size="6" name="tipo_persona" id="tipo_persona" value='<c:out value='${tipopersona}'/>'> 
		                  	</c:when> 
		                  	<c:otherwise> 
		                    <INPUT class="input"  maxLength="40" size="40" name="desc_origen" id="desc_origen"  readonly="true"  value="<c:out value='${descripcion_persona}'/>">
		                    </c:otherwise> 
		                </c:choose>
						</c:when>
						<c:otherwise>
						<c:choose> 
						<c:when test='${operacion == "T" }' > 
							<INPUT class="input" maxLength="40" size="40" id="desc_origen"  name="desc_origen" readonly="true" value="<c:out value='${desc_origen}'/>">
							<INPUT  type="hidden" maxLength="6" size="6" name="tipo_persona" id="tipo_persona" value="<c:out value='${tipo_persona}'/>">
							<INPUT  type="hidden" maxLength="6" size="6" name="origen_documento" id="origen_documento" value="<c:out value='${origen_documento}'/>" >
						</c:when> 
						<c:otherwise> 
							 <INPUT class="txt"  maxLength="40" size="40" name="desc_origen" id="desc_origen"  readonly="true"  value="<c:out value='${desc_origen}'/>">
		                </c:otherwise> 
		                </c:choose>
					</c:otherwise>
			        </c:choose>	&nbsp;	
					<A  href="javascript:buscar_persona()"><IMG style="WIDTH: 20px; HEIGHT: 14px" height="20"  src="img/flechadwnazul_on.gif" width="20" border="0" title="Seleccionar Procedencia"></A>		
				    </p>
				    <p><label>Tipo:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    	<c:choose>
							<c:when test='${codigo_oficina ==  "1"}'>
				 				<c:choose> <c:when test='${operacion == "T" }' > 
				                  <INPUT class="input-medium" id="naturaleza_documento" maxLength="40" size="20" name="naturaleza_documento" readonly="readonly" value='<c:out value='${tipopersona}'/>'> 
				                  </c:when> <c:otherwise> 
								    <INPUT class="input-medium" type="hidden" id="naturaleza_documento" maxLength="40" size="20" name="naturaleza_documento" readonly="readonly" value='<c:out value='${tipopersona}'/>'> 
				                    <INPUT class="input-medium" id="tipo" maxLength="40" size="20" name="tipo" readonly="readonly" value="<c:out value='${tipopersona}'/>">
				                    </c:otherwise> 
				                </c:choose>
							</c:when>
							<c:otherwise>
							
							<c:choose> <c:when test='${operacion == "T" }' > 
			                 <INPUT class="input-medium" id="tipo" maxLength="40" size="20" name="tipo" readonly="readonly" value="<c:out value='${tipo_persona}'/>">
							   <INPUT class="txt" type="hidden" id="naturaleza_documento" maxLength="40" size="20" name="naturaleza_documento" readonly="readonly" value='<c:out value='${tipopersona}'/>'> 
							  </c:when> <c:otherwise> 
			                     <INPUT class="input-medium" id="tipo" maxLength="40" size="20" name="tipo" readonly="readonly" value="<c:out value='${tipo_persona}'/>">
			                    </c:otherwise> 
			                </c:choose>
							</c:otherwise>
		        		</c:choose>
				    </p>
				    <p><label>Firmado por:</label>
				    <c:choose>
						<c:when test='${codigo_oficina ==  "1"}'>
						 <INPUT class="input" id="firmadopor" maxLength="40" size="40" name="firmadopor" style="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" value="<c:out value='${firmadopor}'/>" > 
		                  <INPUT id="INPUT4" type="hidden" maxLength="6" size="6" name="sle_codigo_contacto">    
						  </c:when>
						<c:otherwise>
						  <INPUT class="input" id="firmadopor" maxLength="40" size="40" name="firmadopor" style="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();" value="<c:out value='${firmado_por}'/>" > 
		                  <INPUT id="INPUT4" type="hidden" maxLength="6" size="6" name="sle_codigo_contacto"> 
						 </c:otherwise>
		        	</c:choose>	
				    </p>
				    <p><label>Dirigido a:&nbsp;&nbsp;&nbsp;</label>
				   	 <c:choose> 
				   	 <c:when test='${operacion_dir == "D"}' > 
                  		<INPUT class="input" id="dirigido" readonly="true" maxLength="40" size="40" name="dirigido" value="<c:out value='${descripcion_persona_dir}'/>" >
                   	</c:when> 
                   	<c:otherwise> 
                    	<INPUT class="input" id="dirigido" readonly="true" maxLength="40" size="40" name="dirigido" >
                    </c:otherwise> 
                	</c:choose>&nbsp;
                	<A  href="javascript:buscar_persona_dirigido()"><IMG style="WIDTH: 20px; HEIGHT: 14px" height="20"  src="img/flechadwnazul_on.gif" width="20" border="0" alt="Seleccionar Persona"></A>				 
				    </p>
				    <p><label>Destino:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				      <select id="codigo_oficina" name="codigo_oficina" class="input" onchange="showProducts()" >
	                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
	                    <c:choose> <c:when test='${not empty rs_oficina}'>
						 <c:forEach items='${rs_oficina}' var='pa'>
							<option value=<c:out value='${pa.codigo_oficina}'/>> 
							<c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/>
							 </option> 
							</c:forEach> 
	                    </c:when> </c:choose> 
			          </select>
				    </p>
				    <p><label>Destinatario:</label>
				    
					  <c:choose>
					    <c:when test='${operacion ==  "N"}'>
					      <select id="personas" name="personas" class="input-medium" >
					        <option value="0">:: Personas ::</option>
				          </select>
				        </c:when>
					    <c:otherwise>
					      <select id="personas" name="personas" class="input-medium" >
					        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
					        <c:choose> <c:when test='${not empty rs_destinatario}'>
							 <c:forEach items='${rs_destinatario}' var='pa'>
							 	<option value=<c:out value='${pa.codigo_personal}'/>> 
					          <c:out value='${pa.nombre_personal}'/>
							   </option> 
							   </c:forEach> 
					          </c:when>
				            </c:choose> 
				          </select>
				        </c:otherwise>
			          </c:choose>
				    </p>
				    
				    <p><label>Fecha de <br>Respuesta Legal:</label>
						<input  type="checkbox" class="caja" name="cbo_fecharpta" id="cbo_fecharpta" value="checkbox" onClick="javascript:HabilitaText();">
						&nbsp;<INPUT  class="input-medium" style="width: 90px;" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta"  maxLength="10" size="11" name="fecha_rpta"> 
		                &nbsp; <a href="" id="lanzador1"  style="display: none; width: 20;" ><img src="img/cal.gif" alt="Fecha de Respuesta Legal (dd/mm/yyyy)" border="0"></a>
				    </p>
				    <p><label>N&deg; de <br>Referencia:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    	<INPUT class="input" id="numero_referencia" maxLength="10" size="10" name="numero_referencia" style="width: 90px;"  onKeyPress="return validatecla('enteros', this);" value="<c:out value='${numero_referencia}'/>">
				    </p>
				    <p><label>Adjuntar Doc:&nbsp;&nbsp;&nbsp;&nbsp;</label>
					    <input type="button"  id="btnUpload" class="button" name="btnUpload"  value="Agregar Archivos" onClick="AgregarArchivos()" title="Agregar Archivos" class="boton" >&nbsp; 
						<c:choose>
						  <c:when test='${operacion ==  "T" && FFormMantDocumento.numeroArchivos > 0 }'>
						    <img src="img/attach2icon.png" title="Documentos Adjuntos">				
						    </c:when>
					    </c:choose>
				    </p>
				   
				    </div>
				    
				    </c:when>
					<c:otherwise>
					
					<div id="TDHeadCab">
						   		   	<span class="labelrounded">REGISTRO DOCUMENTO INTERNO</span>
						   		   	
						   		   	<!-- div  id ="botoninvisibleAnular" title="Modificar Registro" style="width:80px; height:20px; position: absolute; top: 6px; padding: 3px 10px; text-align: center;  FONT-SIZE: 8pt; font-weight:normal;  left:86%; color: #003366;  line-height: 20px;" >&nbsp;<img  src="img/edit_doc.png"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp; Modificar</div-->
						   		   	<div  id ="botoninvisibleAnular" title="Modificar Registro" style="width:80px; height:20px; position: absolute; top: 6px; padding: 3px 10px; text-align: center;  FONT-SIZE: 8pt; font-weight:normal;  left:86%; color: #003366;  line-height: 20px;" >&nbsp;<img  src="img/edit_doc.png"  style=" vertical-align: middle; width:20px; height:20px;  border:0px; ">&nbsp;&nbsp; Modificar</div>
						  			
						   		
					</div>
					<div id="TDHeadCabRadios" >
					<c:choose>
				    <c:when test='${codigo_oficina ==  "1"}'>
				      <INPUT id="rb_seleccion" type="radio" value="E" name="rb_seleccion"  checked>
					      	Externo&nbsp;
					      	<INPUT id="rb_seleccion" type="radio" value="I" name="rb_seleccion">
				        	Interno				
			        </c:when>
				    <c:otherwise>
			        	<INPUT id="rb_seleccion" type="radio" value="I" name="rb_seleccion" checked>Interno				
			        </c:otherwise>
			        </c:choose>
					</div>
					<div id="Lineaform2" ></div>
			
				    <div id="left" class="columna-left2" > 
				    	<p><label>Medio:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    	
							<select id="medio" name="medio" class="input-medium" style="width:100" value=''>
			                  <option value="0">--Selec--</option>
			                  <option value="OR">Original</option>
			                  <option value="FA">Fax </option>
			                  <option value="CO">Copia Inf.</option>
			                  <option value="EM">Email</option>
			                </select>&nbsp;&nbsp;<A  href="javascript:ayuda_doc_pend()">Documentos Pendientes</A>
				    	</p>
				    	<p><label>Tipo Doc:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
						  <select id="codigo_tipo" name="codigo_tipo" class="input-medium" >
		                  <option value="0" selected> -- Seleccionar -- </option>
		                  <c:choose> <c:when test='${not empty rs_tipodoc}'>
		                    <c:forEach items='${rs_tipodoc}' var='pa'>
		                      <option value=<c:out value='${pa.codigo_tipo}'/>> 
		                      <c:out value='${pa.descripcion_tipo}'/> 
		                      </option>
		                      </c:forEach> 
		                    </c:when> </c:choose> 
		                  </select> 
				    	</p>
				    	<p><label>N&uacute;mero Doc.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    		<INPUT class="input" id="numero_documento"  maxLength="40" size="40" name="numero_documento"  style="text-transform:uppercase;" onkeyup="javascript:this.value=this.value.toUpperCase();"  value="<c:out value='${numero_documento}'/>">                
				    	</p>
				    	<p><label>Fecha Doc.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    		<INPUT  class="input-medium" style="width: 90px;" onKeyPress="validarCaracterFecha(this);" id="fecha_registro"  maxLength="10" size="11" name="fecha_registro" value="<c:out value='${fecha}'/>"> 
                			&nbsp; <a href="" id="lanzador"><img src="img/cal.gif" alt="Fecha de nacimiento (dd/mm/yyyy)" border="0"></a>
				    	</p>
				    	<p><label>Hora:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    		<INPUT  class="input-medium"  style="width: 90px;" id="hora" maxLength="8" size="9" name="hora" value="<c:out value='${hora}'/>">
				    		&nbsp;&nbsp;&nbsp;
				    		<label>Folios:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    		<INPUT class="input-medium" style="width:68px" onKeyPress="return validatecla('enteros', this);" id="folios_documento" maxLength="3" size="3" name="folios_documento" value="<c:out value='${folios_documento}'/>">
				    	</p>
				    	
				    	<p><label>Solicitud:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    	
			               <select id="codigo_solicitud" name="codigo_solicitud" class="input" >
			                    <option value="0" selected> -- Selecc Uno -- </option>
			                    <c:choose> <c:when test='${not empty rs_solicitud}'> <c:forEach items='${rs_solicitud}' var='pa'>	<option value=<c:out value='${pa.codigo_solicitud}'/>> 
			                    <c:out value='${pa.nombre_solicitud}'/> </option> </c:forEach> 
			                    </c:when> </c:choose> 
			                </select>
				    	</p>
				    	<p><label>Fecha de <br>Respuesta Legal:</label>
				    	<input  type="checkbox" class="caja" name="cbo_fecharpta" id="cbo_fecharpta" value="checkbox" onClick="javascript:HabilitaText();">&nbsp;
						<input  class="input-medium" style="width: 90px;" onKeyPress="validarCaracterFecha(this);" id="fecha_rpta"  maxlength="10" size="11" name="fecha_rpta">
						&nbsp; <a href="" id="lanzador1"  style="display: none; width: 20;" ><img src="img/cal.gif" alt="Fecha de Respuesta Legal (dd/mm/yyyy)" border="0"></a>

				    	</p>
				    	<p><label>N&deg; de <br>Referencia:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    	<input class="input" id="numero_referencia" style="width: 90px;" onKeyPress="return validatecla('enteros', this);" maxlength="10" size="10" name="numero_referencia" value="<c:out value='${numero_referencia}'/>" >
				    	</p>
				    	<p><label>Adjuntar Doc:&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    		<input type="button"  id="btnUpload" name="btnUpload"  value="Agregar Archivos" onClick="AgregarArchivos()" title="Agregar Archivos" class="button" >&nbsp;
							<c:choose>
							  <c:when test='${operacion ==  "T" && FFormMantDocumento.numeroArchivos > 0 }'><img src="img/attach2icon.png" title="Documentos Adjuntos"> </c:when>
							</c:choose>
				    	</p>
				    	<p><label>Asunto:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			                  <textarea id="asunto_documento" style="WIDTH: 330px; HEIGHT: 70px" class="textarea" name="asunto_documento" rows="2" cols="71"><c:out value='${asunto_documento}'/></textarea>
				    	</p>
				    	<p><label>Observaci&oacute;n:</label>
				    		  <textarea id="observa_documento" style="WIDTH: 330px; HEIGHT: 70px"  class="textarea" name="observa_documento" rows="2" cols="50"><c:out value='${observa_documento}'/></textarea>
				    	</p>
				    	
				    </div>
				    <div id="center"  class="columna-center3" >&nbsp;</div>
				    
			 		<div id="rigth" class ="columna-rigth3" >
			 			<p><label>Procedencia:</label>
			 						  
								<c:choose>
								<c:when test='${codigo_oficina ==  "1"}'>
				
								 <c:choose> 
									<c:when test='${operacion == "T" }' > 
						                  <INPUT class="input" style="width:300px" maxLength="40" size="40" id="desc_origen"  name="desc_origen" readonly="true" value="<c:out value='${descripcion_persona}'/>"> 
						                  <INPUT  type="hidden" maxLength="6" size="6" name="origen_documento" id="origen_documento" value='<c:out value='${codigo}'/>'> 
						                  <INPUT  type="hidden" maxLength="6" size="6" name="tipo_persona" id="tipo_persona" value='<c:out value='${tipopersona}'/>'> 
						            </c:when> 
						            <c:otherwise> 
				                    	<INPUT class="input"  maxLength="40" size="40" name="desc_origen" id="desc_origen"  readonly="true"  value="<c:out value='${descripcion_persona}'/>">
				                    </c:otherwise> 
				                </c:choose>
				                &nbsp;
								<A  href="javascript:buscar_persona()"><IMG width="16px" height="16px"  border="0" src="img/flechadwnazul_on.gif"  title="Seleccionar Procedencia"></A>
								</c:when>
								<c:otherwise>
									<c:choose> 
									<c:when test='${operacion == "T" }' > 
										<INPUT class="input" style="width:300px" maxLength="40" size="40" id="desc_origen"  name="desc_origen" readonly="true" value="<c:out value='${desc_origen}'/>">
										<INPUT  type="hidden" maxLength="6" size="6" name="tipo_persona" id="tipo_persona" value="<c:out value='${tipo_persona}'/>">
										<INPUT  type="hidden" maxLength="6" size="6" name="origen_documento" id="origen_documento" value="<c:out value='${origen_documento}'/>" >
									</c:when> 
									<c:otherwise> 
										 <INPUT class="input" style="width:300px"  maxLength="40" size="40" name="desc_origen" id="desc_origen"  readonly="true"  value="<c:out value='${desc_origen}'/>">
									</c:otherwise> 
					                </c:choose>
								</c:otherwise>
						        </c:choose>		
								
				    	</p>
				    	<p><label>Tipo:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
							<c:choose>
							<c:when test='${codigo_oficina ==  "1"}'>
			 				<c:choose> 
			 					<c:when test='${operacion == "T" }' > 
				                  <INPUT class="input-medium" id="naturaleza_documento" maxLength="40" size="20" name="naturaleza_documento" readonly="true" value='<c:out value='${tipopersona}'/>'> 
				                 </c:when> 
				                 <c:otherwise> 
								    <INPUT class="txt" type="hidden" id="naturaleza_documento" maxLength="40" size="20" name="naturaleza_documento" readonly="true" value='<c:out value='${tipopersona}'/>'> 
				                    <INPUT class="input-medium" id="tipo" maxLength="40" size="20" name="tipo" readonly="true" value="<c:out value='${tipopersona}'/>">
				                 </c:otherwise> 
				             </c:choose>
							</c:when>
							<c:otherwise>
								<c:choose> <c:when test='${operacion == "T" }' > 
				                 <INPUT class="input-medium" id="tipo" maxLength="40" size="20" name="tipo" readonly="true" value="<c:out value='${tipo_persona}'/>">
								  <INPUT class="input-medium" type="hidden" id="naturaleza_documento" maxLength="40" size="20" name="naturaleza_documento" readonly="true" value='<c:out value='${tipopersona}'/>'> 
								  </c:when><c:otherwise> 
				                     <INPUT class="input-medium" id="tipo" maxLength="40" size="20" name="tipo" readonly="true" value="<c:out value='${tipo_persona}'/>">
				                  </c:otherwise> 
				                </c:choose>
							</c:otherwise>
					        </c:choose>		
				    	</p>
				    	<p><label>Firmado por:</label>
							<c:choose>
							<c:when test='${codigo_oficina ==  "1"}'>
							 	<INPUT class="input" id="firmadopor" maxLength="40" size="40" name="firmadopor" value="<c:out value='${firmadopor}'/>" > 
			                  	<INPUT id="INPUT4" type="hidden" maxLength="6" size="6" name="sle_codigo_contacto">    
							  </c:when>
							<c:otherwise>
							  <INPUT class="input" id="firmadopor" maxLength="40" size="40" name="firmadopor" value="<c:out value='${firmado_por}'/>" > 
			                  <INPUT id="INPUT4" type="hidden" maxLength="6" size="6" name="sle_codigo_contacto"> 
							  </c:otherwise>
					        </c:choose>	
				    	</p>
				    	
				    	<!-- JAZANERO -->
					     <p><label>Institucion <br>Destino:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
						     <select id="codigo_intitucion" name="codigo_intitucion" class="input-medium"  onchange="limpiaCampos()">
												<OPTION value="0" selected> -- Selecc Uno -- </OPTION>
												<OPTION value="1" >CONCYTEC</OPTION>
												<OPTION value="2" >FONDECYT</OPTION>
							</select> 
					     </p>
				    	
				    	<p><label>Dirigido a:&nbsp;&nbsp;&nbsp;</label>
							  <c:choose> <c:when test='${operacion_dir == "D"}' > 
			                  	<INPUT class="input" style="width:290px;" id="dirigido"  maxLength="40" size="40" name="dirigido" value="<c:out value='${descripcion_persona_dir}'/>" >
			                   </c:when> <c:otherwise> 
			                    <INPUT class="input" style="width:290px;" id="dirigido"  maxLength="40" size="40" name="dirigido" >
			                    </c:otherwise> 
			                	</c:choose>
								<!-- &nbsp;<A  href="javascript:buscar_persona_dirigido()"><IMG width="16px" height="16px"  border="0" src="img/flechadwnazul_on.gif"  title="Seleccionar Persona"></A> -->				
				    	</p>
				    					    	
				    	
				    	<p><label>Destino:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    	
							  <select id="codigo_oficina" name="codigo_oficina" class="input"  >
			                    <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
			                    <c:choose> <c:when test='${not empty rs_oficina}'>
								 <c:forEach items='${rs_oficina}' var='pa'>
									<option value=<c:out value='${pa.codigo_oficina}'/>> 
									<c:out value='${pa.siglas_oficina}'/> - <c:out value='${pa.descripcion_oficina}'/>
									 </option> 
									</c:forEach> 
			                    </c:when> </c:choose> 
					          </select> 
				    	</p>
				    	<p><label>Destinatario:</label>
				    	
							  <c:choose>
							    <c:when test='${operacion ==  "N"}'>
							      <select id="personas" name="personas" class="input" >
							        <option value="0">:: Personas ::</option>
						          </select>
						        </c:when>
							    <c:otherwise>
							      <select id="personas" name="personas" class="input" >
							        <OPTION value="0" selected> -- Selecc Uno -- </OPTION>
							        <c:choose> <c:when test='${not empty rs_destinatario}'>
									 <c:forEach items='${rs_destinatario}' var='pa'>
									 	<option value=<c:out value='${pa.codigo_personal}'/>> 
							          <c:out value='${pa.nombre_personal}'/>
									   </option> 
									   </c:forEach> 
							          </c:when>
						            </c:choose> 
						          </select>
						        </c:otherwise>
					          </c:choose>	
				    	</p>
				    	<p><label><c:choose><c:when test='${pertenece_a ==  "1"}'>Con Copia CONCYTEC</c:when><c:otherwise>Con Copia FONDECYT</c:otherwise></c:choose></label>
			                <input  id="chk_copia" checked="checked" class="caja" onClick="javascript:HabilitaCombo(1);" type="checkbox" name="chk_copia"></p>
			                <div id="scroll" class="input" style="WIDTH: 370px; height:100px; " >
			                  <table>
			                  
			                    <c:forEach items='${rs_oficina}' var='pa'>
								<tr>
			                      <td><input  class="caja" type="checkbox" name="selectedItems" value="<c:out value='${pa.codigo_oficina}'/>"></td>
			                      <td class="texto_small">	
			                            <c:out value='${pa.siglas_oficina}'/>
			                           - 
			                          <c:out value='${pa.descripcion_oficina}'/></td>
			                    </tr></c:forEach>
			      				</table>
			      			</div>
			      		<br>
			      		<p><label><c:choose><c:when test='${pertenece_a ==  "1"}'>Con Copia FONDECYT</c:when><c:otherwise>Con Copia CONCYTEC</c:otherwise></c:choose></label>
			                <input  id="chk_copia_other" checked="checked" class="caja" onClick="javascript:HabilitaCombo(0);" type="checkbox" name="chk_copia_other"></p>
			                <div id="scroll_2" class="input" style="WIDTH: 370px; height:100px; " >
			                  <table>
			                  
			                    <c:forEach items='${rs_oficina_other}' var='da'>
								<tr>
			                      <td><input  class="caja" type="checkbox" name="selectedItems_other" value="<c:out value='${da.codigo_oficina}'/>"></td>
			                      <td class="texto_small">	
			                            <c:out value='${da.siglas_oficina}'/>
			                           - 
			                          <c:out value='${da.descripcion_oficina}'/></td>
			                    </tr></c:forEach>
			      				</table>
			      			</div>
				    	
				    	
			 		</div>
			 		
					</c:otherwise>
			</c:choose>
			<div class="center-big">
				<INPUT  class="button"  id="cmb_grabar" onclick ="this.disabled=true; this.value='REGISTRANDO...'; Validar()"  type="button" value="Registrar" >
			</div>
			<SCRIPT language="javascript">
			Calendar.setup({
    			inputField     :    "fecha_registro",      // id del campo de texto
    			ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
   				 button         :    "lanzador" 

			});

 			Calendar.setup({
					inputField     :    "fecha_rpta",      // id del campo de texto
					ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
					button         :    "lanzador1" 

				});
 			
					  SeleccionarCampo("medio","<c:out value='${medio_rq}'/>");
					  SeleccionarCampo("codigo_tipo","1");
					  SeleccionarCampo("codigo_oficina","<c:out value='${codigo_oficina_rq}'/>");
					  SeleccionarCampo("personas","<c:out value='${destinatario}'/>");
					  SeleccionarCampo("codigo_solicitud","<c:out value='${codigo_solicitud}'/>");
					  SeleccionarCampo("codigo_intitucion","<c:out value='${ls_codigo_institucion}'/>");
					 /*<c:choose>
					    <c:when test='${operacion ==  "T"}'>
					   SeleccionarCampo("personas","<c:out value='${destinatario}'/>");
					   </c:when>
					 </c:choose>*/
					//SeleccionarCampo("codigo_tipo","<c:out value='${codigo_tipo}'/>");
					inicio();
					function inicio(){
					//var scroll_chks = document.getElementById('scroll');
					with(document.forms[0]){
							fecha_rpta.disabled = "true"; 
							//scroll_chks.style.visibility = 'hidden'; 
							//document.form_datos.fecha_rpta.disabled = "true"; 
							}
					   
					};
					
			</SCRIPT>
			<br>
			</div>
	
		 		  
   
     <!-- ajax:select baseUrl="${pageContext.request.contextPath}/obtenerLista.do" parameters="cambia=ofi&codigo_oficina={codigo_oficina}" source="codigo_oficina" target="personas"  /-->
			
  <!-- 
  <table width="100%">
			 <tr>
				  <td  align="left" valign="middle" width="10%">
				    			  
				  </td>
				   <td  align="left" valign="middle" width="80%">
				   
				   		<table border="0" width="100%">
				   		
				   		 <tr>
				  <td align="center" colspan="2" height="50"  background="img/fondoplomo8.jpg" class="label"><bean:message key="sistema.documento.internos.creados" /></td>
				  
			 </tr>
			 <tr>
				  <td align="center" colspan="2">				  
				  <display:table name="sessionScope.listaDocumentosInternos" export="false" sort="list" id="b" pagesize="10"  class="simple" style="width: 100%">
				  						
				  						<display:column title="Nro Documento" property="codigo_documento_interno" sort="true" />
											
				  						<display:column property="asunto" title="Asunto" sort="true" width="350"/>								
					  						
				  						<display:column  title="Oficina Destino" sort="true">
				  						
					  						<logic:iterate id="oficina" name="areasAdministrativasList">					  										  						
						  							<c:if test="${b.codigo_oficina==oficina.codigo_oficina}">
						  								<c:out value="${oficina.descripcion_oficina}"/>
						  							</c:if>
						  						</logic:iterate>
				  						</display:column>
				  						<display:column property="dirigido_a" title="Destinatario" sort="true"/>
				  						
				  						<display:column   title="Tipo de Documento" sort="true">
					  						<logic:iterate id="estado" name="tipoDocumentoInternosList">					  										  						
					  							<c:if test="${b.codigo_tipo_documento_interno==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  						
				  						<display:column property="nombre_arhivo" title="Nombre de Documento" sort="true" />
				  						
				  						
				  						
				  						<display:column   title="Estado" sort="true">
					  						<logic:iterate id="estado" name="estadoDocumentoInternoList">					  										  						
					  							<c:if test="${b.codigo_estado_documento==estado.idBean}">
					  								<c:out value="${estado.nombreBean}"/>
					  							</c:if>
					  						</logic:iterate>
				  						</display:column>
				  										  						
				  						<display:column  sort="true" title="Fecha de creacion" >
				  							<fmt:formatDate dateStyle="short"  timeStyle="medium" type="both"  value="${b.fecha_creacion}" />
				  						</display:column>
				  						
				  						<display:column property="link"  title="Operaciones" />
				  							
				  						
				  						
				  </display:table>
				 
				  </td>
				  
			 </tr>
				   		
				   		
				   		
				   		</table>
				    			  
				  </td>
				   <td  align="left" valign="middle" width="10%">
				    			  
				  </td>
			 </tr>
			
	</table>
   -->

</form>
</div>
</BODY>
</HTML>
<% } %>