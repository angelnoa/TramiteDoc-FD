<%@ include file="taglibs.jsp" %>
<% 
//Verifica que exista una sesion
String nombreusuario = String.valueOf(session.getAttribute("nombreusuario"));
//System.out.println("el nombre de usuario es.."+nombreusuario);
if (nombreusuario==null || nombreusuario.equals("null")){
%> 
<script language="Javascript">
parent.document.location ="pag_expiracion.jsp";
</script>
<% 
}
else {
%>

<html>
	<head>
		<title>Lista Recepci&oacute;n de documentos</title>
		
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
		
		<!-- link href="theme/css3/style.css" rel="stylesheet" type="text/css" /-->
		
	<style type="text/css">
	
	h3 {
	 	text-transform:capitalize;
	  	font-family:Arial, Helvetica, sans-serif;
	}
	/*p {
		font-size:16px;
		font-family:Arial, Helvetica, sans-serif;
	}*/
	
	#mensajeok {
	display:none;
	position:relative;

	padding:0px;
	top: 50%;
	left: 50%; 
	text-align: center;
	font-family: Verdana, sans-serif;
	font-size: 13px;
	font-weight: bold;
	color: #003366;
	width: 780px;
	height: 60px;
	margin-left: -390px;
	background: #f1f4ac;
	overflow:auto;
	line-height: 12px;
	}
	
	#mensajeerror {
	display:none;
	position:relative;

	padding:0px;
	top: 50%;
	left: 50%; 
	text-align: center;
	font-family: Verdana, sans-serif;
	font-size: 13px;
	font-weight: bold;
	color: #003366;
	width: 780px;
	height: 60px;
	margin-left: -390px;
	background: #F6CECE;
	overflow:auto;
	line-height: 12px;
	}
	
	.rounded-small{
	border: 2px solid #acc835;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;	
	clear: both;
	}
	
	/*#busca.search-site { position: relative; width: 228px; }*/
	/*.input-busca { background: url("../img/header/search.png") no-repeat scroll 0 0 transparent; border: 0 none; color: #9F9F9F; height: 17px; margin: 10px 6px 10px 16px; padding: 4px 6px 4px 27px; width: 168px; }*/
	/*#busca.search-site button.image { border: 0 none; height: 17px; margin: 0; padding: 0; position: absolute; left: 20px; text-indent: -10000px; top: 14px; width: 17px; cursor: pointer; background: transparent; }*/
	
</style>
<style>
    .ui-menu { position: absolute; width: 100px; }
</style>
 
  
  <script>
  var refDialog;
  
  $(function() {
	 $("#dialog").dialog();
	});
  
  
	  $(function() {
	      $('a.showPopup').click(function(e) {
	          e.preventDefault();
	          var $this = $(this);
	          var horizontalPadding = 15;
	          var verticalPadding = 15;
	          refDialog = $('<iframe id="site" src="' + this.href + '" />').dialog({
	              title: ($this.attr('title')) ? $this.attr('title') : 'Site',
	              autoOpen: true,
	              width: 1000,
	              height: 300,
	              modal: true,
	              resizable: false,
	              autoResize: true,
	              overlay: {
	                  opacity: 0.5,
	                  background: "black"
	              }
	          }).width(1000 - horizontalPadding).height(300 - verticalPadding);
	      });
	});
  
  
  $(function() {
    $( "#rerun" )
      .button()
      .click(function() {
        alert( "Running the last action" );
      })
      .next()
        .button({
          text: false,
          icons: {
            primary: "ui-icon-triangle-1-s"
          }
        })
        .click(function() {
          var menu = $( this ).parent().next().show().position({
            my: "left top",
            at: "left bottom",
            of: this
          });
          $( document ).one( "click", function() {
            menu.hide();
          });
          return false;
        })
        .parent()
          .buttonset()
          .next()
            .hide()
            .menu();
  });
  </script>		 

		<script language="JavaScript">
		$(document).ready(function(){
		
		$("#mensajeok").click(function () {
		      $("#mensajeok").fadeOut("fast");
	    });
		
		$("#mensajeerror").click(function () {
		      $("#mensajeerror").fadeOut("fast");
	    });
		
		$( "#tabs" ).tabs();
		
		});
		
		
		
		function Observar(val1,val2,val3,val4,val5){
		
			if(confirm("Esta seguro de Observar el Registro Nº "+val1)){	
		var ab = val1;
		var bc = val2;
		var cd = val3;
		var de = val4;
		var ef = val5;
		
		
			   $("#valor1").val(ab);
			   $("#valor2").val(bc);
			   $("#valor3").val(cd);
			   $("#valor4").val(de);
			   $("#valor5").val(ef);
			//if(val5 == 0){
				//alert("Este documento no es observable");
			//}else{
				
			 //var strURL = "ListaDocumentos.do?tipo=observacion&operacion=ON&cod="+val1+"&codexp="+val2+"&codrep="+val3+"&secn="+val4+"&secold="+val5;

			with(document.forms[0]){
			vaction = action;
			action = action + '?=<c:out value="${param['']}"/>';
			tipo.value = 'observacion';	
			    //target = 'mainFrame';
			target = '';
			submit();
				
			}
			}
		}
			
		function MensajeOKey(){
			//var strURL="ValidaPaginas.do?tipo=mensajerecepcion";
			//window.open(strURL,"","HEIGHT=140,WIDTH=500,scrollbars=no");
			$(document).ready(function(){
				$("#mensajeok").fadeIn("slow");
			
			});
		}
		
		function MensajeError(){
			//var strURL="ValidaPaginas.do?tipo=mensajerecepcion";
			//window.open(strURL,"","HEIGHT=140,WIDTH=500,scrollbars=no");
			$(document).ready(function(){
				$("#mensajeerror").fadeIn("slow");
			
			});
		}
		
		function fn_onLoad(){

			cambiar_color_tabla("b", "tablaBackgray");
			with(document.forms[0]){
			    doc_respuesta.readOnly = true;
			    //keywords.focus();
			 	//codigo_documento_busqueda.focus();			
				if(trim(tipo.value) == "editar"){
				
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
				asunto_documento.readOnly = true;
				observa_documento.readOnly = true;
				
				
				fecha_registro.readOnly = true;
				hora_registro.readOnly = true;
				procedencia.readOnly = true;
				firmadopor.readOnly = true;
				dirigido.readOnly = true;
				codigo_solicitud.readOnly = true;
				medio.readOnly = true;
				
				//btnGrabar.style.display = '';
				//btnCancelar.style.display = 'none';		
				//dpto.style.display = 'none';
				//users.focus();
				}
				else{
					//dpto.style.display = 'none';
					
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
				asunto_documento.readOnly = true;
				//observa_movimiento.readOnly = true;
				
				fecha_registro.readOnly = true;
				hora_registro.readOnly = true;
				procedencia.readOnly = true;
				firmadopor.readOnly = true;
				dirigido.readOnly = true;
				observa_documento.readOnly = true;
				codigo_solicitud.readOnly = true;
				medio.readOnly = true;
				
				if(trim(tipo.value) == "nuevo"){
					btnGrabar.style.display = 'none';
					btnCancelar.style.display = 'none';
					btnEnviar.style.display = 'none';
				}
				//observa_movimiento.value = pobserva_movimiento;
					/*
					fecha.value = '';
					hora.value = '';
					//observacion.value = '';
					
					fecha_registro.value = '';
					hora_registro.value = '';
					procedencia.value = '';
					firmadopor.value = '';
					dirigido.value = '';
					
					codigo_solicitud.value = '';
					medio.value = '';
					observa_documento.value = '';
					//asunto_documento.value = '';
				
					
					*/
					
					
					
					/*btnGrabar.style.display = '';
					btnCancelar.style.display = '';
					btnEnviar.style.display = '';*/
					
				}
			}
		}
		
				
		function fn_grabar(){
			with(document.forms[0]){
		
				if(trim(fecha.value) == ""){
					alert("Se requiere un valor");
					fecha.focus();
					return;
				}
				
				if(trim(hora.value) == ""){
					alert("Seleccionar");
					hora.focus();
					return;
				}
					
				vaction = action;
				action = action + '?=<c:out value="${param['']}"/>';
		    //target = 'mainFrame';
				target = '';
				submit();
				//action = vaction;			
			}
		}
		
		function selectAll(combo, status){
			for(i=0; i<combo.options.length; i++){
				combo.options[i].selected = status;
			}
		}
		
		function cancel(){
			with(document.forms[0]){
				tipo.value = 'nuevo';	
				
				codigo_documento.value = '';
				codigo_expediente.value = '';
				descripcion_tipo.value = '';
				numero_documento.value = '';
				
				fecha.value = '';
				hora.value = '';
				//observacion.value = '';
				asunto_documento.value = '';
				//observa_movimiento.value = '';
				
				fecha_registro.value = '';
				hora_registro.value = '';
				procedencia.value = '';
				firmadopor.value = '';
				dirigido.value = '';
				observa_documento.value = '';
				
				codigo_solicitud.value = '';
				medio.value = '';
				
				//usuario.readOnly = false;
				
				//removeElements(perfilSeleccionados);
				btnEnviar.style.display = 'none';
				btnCancelar.style.display = 'none';
				fecha.focus();
			}
		}
		
		
		
		function fn_enviar(){
			
			var valor = document.getElementById("codigo_documento").value;
			if (Validarcampovacio(valor) == false)
            {              
                  alert("Selecione un Registro");
                  with(document.forms[0]){
                	  codigo_documento.focus();
                	  btnEnviar.value='Recepcion';
                	  btnEnviar.disabled=false;
                  return false;  
                  }
            } 
			
			if(confirm("Esta seguro de recepcionar este documento?")){
				with(document.forms[0]){				
					//agregado
					vaction = action;
					action = action + '?=<c:out value="${param['']}"/>';
			    tipo.value = 'enviar';	
			    //target = 'mainFrame';
				target = '';
					submit();
					//action = vaction;				
				}
			}else{
				with(document.forms[0]){
				codigo_documento.focus();
				btnEnviar.value='Recepcion';
          	  	btnEnviar.disabled=false;
				}
			}
			
		}
		
		/*function fn_editar(pcodigo_documento
		,pcodigo_expediente
		,pdescripcion_tipo,pnumero_documento
		,pcodigo_oficina,pestado_movimiento,pfecha_movimiento,phora_movimiento,pobserva_registro,pcodigo_recepcion,psecuencia_movimiento,pasunto_documento){*/
		/*function fn_editar(pcodigo_documento,pcodigo_expediente,pnumero_documento
		,pfecha_movimiento,phora_movimiento,pobserva_registro,pasunto_documento){*/
		function fn_editar_cop(pcodigo_documento,psecuencia_movimiento){
			//alert("hi");
			with(document.forms[0]){
			    
                codigo_documento.value = pcodigo_documento;
                secuencia_movimiento.value = psecuencia_movimiento;
								
				
				/*btnEnviar.style.display = '';
				btnCancelar.style.display = '';
				btnGrabar.style.display = 'none';*/
				
				vaction = action;
				action = action + '?=<c:out value="${param['']}"/>';
				
				target = '';
				
				tipo.value = 'editar_cop';	
				
				submit();
			}
			//alert('codigo_documento='+pcodigo_documento+' y secuencia_movimiento='+psecuencia_movimiento);
		}
		
		function fn_editar(pcodigo_documento,pcodigo_expediente,pdescripcion_tipo,pnumero_documento
		,pcodigo_oficina,pestado_movimiento,pfecha_movimiento,phora_movimiento,				   
		psecuencia_movimiento,pnaturaleza_documento,pasunto_documento,pcodigo_recepcion,pfecha_registro,phora_registro,pprocedencia,pfirmadopor,pdirigido,pcodigo_solicitud,pmedio,pobserva_documento, p_doc_resp){
			with(document.forms[0]){
			
		
				codigo_documento.value = pcodigo_documento;
				codigo_expediente.value = pcodigo_expediente;
				descripcion_tipo.value = pdescripcion_tipo;
				numero_documento.value = pnumero_documento;
				codigo_oficina.value = pcodigo_oficina;
				estado_movimiento.value = pestado_movimiento;//.replace("K0NCYT3K","x");
				fecha.value = pfecha_movimiento;
				hora.value = phora_movimiento;
				secuencia_movimiento.value = psecuencia_movimiento;
				
				asunto_documento.value = pasunto_documento;
     
				codigo_recepcion.value = pcodigo_recepcion;				
				fecha_registro.value = pfecha_registro;
				hora_registro.value = phora_registro;
				procedencia.value = pprocedencia;
				firmadopor.value = pfirmadopor;
				dirigido.value = pdirigido;
				codigo_solicitud.value = pcodigo_solicitud;
				medio.value = pmedio;
				observa_documento.value = pobserva_documento;
				doc_respuesta.value = p_doc_resp;
				
				
				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;
				asunto_documento.readOnly = true;
				
				fecha_registro.readOnly = true;
				hora_registro.readOnly = true;
				procedencia.readOnly = true;
				firmadopor.readOnly = true;
				dirigido.readOnly = true;
				observa_documento.readOnly = true;
				
				btnEnviar.style.display = '';	
					
			
			    vaction = action;
				action = action + '?=<c:out value="${param['']}"/>';
				
				target = '';
				
				tipo.value = 'editar';	
				
				submit();
			}		
		}
		
		
		
		
		function SeleccionarCampo(campo, valor){
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
		
		function fecha()
		{
				var mifecha;
				var mydate=new Date();
				var year=mydate.getYear();
				if (year < 1000)
				year+=1900;
				var day=mydate.getDay();
				var month=mydate.getMonth()+1;
				if (month<10)
				month="0"+month;
				var daym=mydate.getDate();
				if (daym<10)
				daym="0"+daym;
				mifecha=daym+"/"+month+"/"+year;
				//alert(mifecha);
				//document.form_datos.fecha_doc.value= mifecha;
				
				
				with(document.forms[0]){
				fecha.value = mifecha;
			
				}
				
		}

	function listadocumentos()
		{
		
		var strURL = "ListaDocumentos.do?tipo=modificar&operacion=MR";
				
		 location=strURL;
		
		return;
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
	
	
	//function VerArchivos(ls_codigo_documento,ls_codigo_expediente,ls_secuencia_movimiento){
	function VerArchivos(ls_codigo_documento,ls_codigo_expediente,ls_codigo_recepcion){
   
				var ls_operacion = "N";
				/*var ls_codigo_documento = document.getElementById("codigo_documento").value;
				var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
				var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;*/
				//alert(ls_codigo_documento);
				
				var strURL='MantSeguimiento.do?tipo=upload'+'&operacion='+ls_operacion+'&codigo_documento='+ls_codigo_documento+'&codigo_expediente='+ls_codigo_expediente+'&codigo_recepcion='+ls_codigo_recepcion;	
				window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes");
				
				
		}	
		
		function ver_frame(){
			
			var elem = document.getElementById("keywords");
			//alert("holaa "+elem.value);
				 //alert( "yeeee." );
				with(document.forms[0]){	
		
				codigo_documento_busqueda_ls.value = elem.value;
				//alert("fff "+codigo_documento_busqueda_ls.value);
				vaction = action;
				action = action + '?=<c:out value="${param['']}"/>';
			
			    tipo.value = 'buscar';	
			    
			    if(codigo_documento_busqueda_ls.value=="Buscar..."){
					tipo.value = 'recepcion';
				}
			    //target = 'mainFrame';
				target = '';
				
				submit();
					
				
			}
			
		
		}	
	
		
	function listar_todos(){
	
	
	//var strURL = "ListaDocumentos.do?tipo=modificar&operacion=ME";
	var strURL = "ListaDocumentos.do?tipo=recepcion&operacion=RN";
 	 location=strURL;
	return;

	}
		
		function checkNumber(elem ) {
			/*valida si contiene al menos un digito, se hizo pensando en el numero de registro de documento.
			 *Fecha:18-09-2011
			 * */
			    return !elem.value || /^\d+$/.test(elem.value);
			}
	
</script>
	</head>
	<body bgcolor="#F0F5FB" topmargin="0" leftmargin="0" onLoad="fn_onLoad();">
	
	<div class="derivaconta">
	<div id="top-bar" style="background-image:url(img/topnav_s.gif);">
	<jsp:include page="/pag_menu.jsp"/>
	</div>
		<html:form  action="/MantRecepcionNormal" method="POST"  styleClass="formulario-class">
			<html:hidden property="tipo" />
			<html:hidden property="codigo_oficina" />
			<html:hidden property="estado_movimiento" />
			<html:hidden property="descripcion_tipo"  />
			<html:hidden property="codigo_recepcion"  />
			<html:hidden property="secuencia_movimiento"  />
			
			<INPUT  type="hidden" id="valor1" name="valor1" value="">
			<INPUT  type="hidden" id="valor2" name="valor2" value=""> 
			<INPUT  type="hidden" id="valor3" name="valor3" value=""> 
			<INPUT  type="hidden" id="valor4" name="valor4" value="">
			<INPUT  type="hidden" id="valor5" name="valor5" value=""> 
			<INPUT  type="hidden" id="codigo_documento_busqueda_ls" name="codigo_documento_busqueda_ls" value="">
              
			<c:choose>
	   			<c:when test='${operacionpopup == "G"}'>
                <SCRIPT language="javascript">
                MensajeOKey();
				</SCRIPT>
				<c:remove var="operacionpopup" scope="session" />
       			</c:when>
       			<c:when test='${operacionpopup == "B"}'>
                <SCRIPT language="javascript">
				 MensajeError();
				</SCRIPT>
				<c:remove var="operacionpopup" scope="session" />
       			</c:when>
			</c:choose> 
			
	
	<div id="mensajeok" class="rounded-small">
	<div id="vvvv" style=" position: absolute;  top:18px; left:20%; ">
		<a  href="#"><img src="img/ok.png"  /></a>
	</div>
		   <br>
	       <c:out value='${mensaje}'></c:out><br>
	       <c:out value='${mensaje_reg}'></c:out><br>
	       <c:out value='${mensaje_exp}'></c:out><br>
	</div>
	<div id="mensajeerror" class="rounded-small">
	<div id="vvvv" style=" position: absolute;  top:18px; left:20%; ">
		<a  href="#"><img width="36px" height="36px" src="img/error.png"  /></a>
	</div>
		   <br>
	       <c:out value='${mensaje}'></c:out><br>
	       <c:out value='${mensaje_reg}'></c:out><br>
	       <c:out value='${mensaje_exp}'></c:out><br>
	</div>
	<div id="break" ></div>
	
	<div id="contactderiva" class="rounded"> 
	<div id="TDHeadCab">
				   		   	<span class="labelrounded">RECEPCION DE DOCUMENTOS </span>
				   		   	
				   		   	<input type="text" name="q" id="keywords"  class="input-search" onfocus="this.value=''" onkeypress="return validatecla('enteros',this)" value="Buscar..."/>
				   		   	<button type="button" class="image" onclick="ver_frame()"  title="Buscar por Nro de Registro" >Buscador</button>
				  			<!--div class="linklista"><a  href="javascript:listadocumentos()">
				  			<img src="img/modifica.gif" alt="Modificar Documentos" width="32" height="32" border="0" ></a>
				  			</div-->
				   		
	</div>
	<div id="TDHeadCabMensaje" >
	<table width="100%" cellspacing="0" cellpadding="0"  align="left">

				<tr>
					<td width="409" height="18" align="LEFT" valign="middle"  background="img/fondoplomo8.jpg" class="textomensaje10bold">
					<div style="color:red; font-size: 12px;">
								     <logic:present name="messageExito" >
									     <bean:message key="mensaje.numero"/><c:out value='${messageExito}'/>
		              					 <br><bean:message key="mensaje.recepcion"/>              					               					                 
	             					</logic:present>							    
					</div>
					</td>
				    <td width="568" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label1" ><!--DWLayoutEmptyCell-->&nbsp;</td>
			        <td width="47" align="center" valign="middle" background="img/fondoplomo8.jpg">&nbsp;&nbsp;&nbsp;</td>
				</tr>
	</table>
	</div>
	<!-- BUSQUEDA -->
	 
	 <!-- div id="TDHeadCabBusqueda" >
	 <div style="width: 50%; height: 50px; margin-left: auto; margin-right: auto; margin-top: 5px;
		text-align: right; float:left;">
     <p><label>Búsqueda por : &nbsp;&nbsp;&nbsp;&nbsp;</label><html:text property="codigo_documento_busqueda" styleId="codigo_documento_busqueda" onkeypress="return validatecla('enteros',this)" style="width: 150px;" styleClass="input-medium"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
     
     </div>
     <div style="width:50%; float:right ;">
     	<input  type="submit" name="button"  class="deriva" value="Buscar" onClick="ver_frame()" alt="buscar"  >
        <input type="button" name="button" class="deriva" value="Listar Todos" onClick="listar_todos()" alt="Listar Todos"  >
     </div>	
     <br>
     <br>
     <br>
	 </div-->
	 
			
	<div id="Lineaform2" ></div>
    			 
	<div id="left" class="columna-left2" > 
	<p><label>N&deg; Registro:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	<html:text property="codigo_documento"   styleClass="input-medium" maxlength="15" styleId="codigo_documento"/>
	</p>
	<p><label>Fecha Registro:</label>
	<html:text property="fecha_registro"  style="width: 90px;" styleClass="input-medium" />
	</p>
	<p><label>Hora Registro:&nbsp;</label>
	<html:text property="hora_registro" style="width:55px; " maxlength="11" styleClass="input-medium" />
	</p>
	<p><label>N&deg; Expediente:</label>
	<html:text property="codigo_expediente"  styleClass="input-medium"/>
	</p>
	<p><label>Medio:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	<html:text property="medio" style="width: 100px;" styleClass="input-medium"/>
	</p>
	<p><label>N&deg; Documento:</label>
	<html:text property="numero_documento"  styleClass="input" />
	</p>
	<p><label>Asunto:</label>
	<html:textarea property="asunto_documento"  style="width:348px; " styleClass="textarea"/>
	</p>
		
	</div>
	
	<div id="center"  class="columna-center3" >&nbsp;</div>
	 <div id="rigth" class ="columna-rigth3" >
	 <p><label>Procedencia:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	<html:text property="procedencia"   style="width:280px; " styleClass="input"/>
	</p>
	<p><label>Firmado por:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	 <html:text property="firmadopor"  style="width:280px; " styleClass="input"/>	
	</p>
	<p><label>Dirigido a :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	<html:text property="dirigido"  style="width:280px; " styleClass="input"/>
	</p>
	<p><label>Solicitud :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	<html:text property="codigo_solicitud"   style="width:280px; " styleClass="input"/>
	</p>
	<p><label>Doc. de Respuesta:&nbsp;&nbsp;</label>
	<html:text property="doc_respuesta"   styleClass="input-medium" />
	</p>
	<p><label>Fecha de Recepci&oacute;n :</label>
	<html:text property="fecha" style="width: 90px;" maxlength="11" styleClass="input-medium"  styleId="fecha" onkeypress="validarCaracterFecha(this);"  />			
	&nbsp;<a href="" id="lanzador"><img  src="img/cal.gif" title="Fecha (dd/mm/yyyy)" border="0"></a>
	&nbsp;&nbsp;<label>Hora Recepci&oacute;n:</label>
	<html:text property="hora" style="width:55px; " maxlength="15" styleClass="input-medium" />
	</p>
	
	<p><label>Observaci&oacute;n:</label>
	<html:textarea property="observa_documento"  style="width:324px; " styleClass="textarea"/>
	</p>
	<SCRIPT type=text/javascript>
					Calendar.setup({
						inputField     :    "fecha",      // id del campo de texto
						ifFormat       :    "%d/%m/%Y",       // formato de la fecha, cuando se escriba en el campo de texto
						button         :    "lanzador" 
						   // el id del botÃ³n que lanzarÃ¡ el calendario
					});
					
					
					//fecha();
					
	</SCRIPT>
	 </div>
	 <div class="center-big">
	 <c:choose>
	 <c:when test='${FFormMantRecepcion.codigo_documento == "" || FFormMantRecepcion.codigo_documento eq null}'>
	 
	 </c:when>
	 <c:otherwise>
		 <html:button property="btnEnviar" value="Recepcion"  styleClass="button"   onclick ="this.disabled=true; this.value='RECEPCIONANDO...'; fn_enviar()"/>										 
		 <html:button property="btnCancelar" value="Cancelar" onclick="cancel();" style="width: 90;" styleClass="button"/>
		 <html:button property="btnGrabar" style="display: none; width: 90;"  styleClass="button"/>	
	 </c:otherwise>
	 </c:choose>
	 
    	<!-- a href="javascript:Buscar()" class="button-new round blue image-right ic-right-arrow" title="Buscar" >BUSCAR</a-->
     </div>
     <br>	
			
	 </div>
			<div id="break" ></div>
				<div class="derivalista">
					<div id="tabs">
					  <ul>
					    <li><a href="#tabs-1">Documentos Pendientes por Recepcionar</a></li>
					   
					  </ul>
					  <div id="tabs-1">	
					  <table width="100%" align="center" cellpadding="0" cellspacing="0" >
							<tr>
								<td height="35" align="center" colspan="2" valign="top">
									<table width="100%"  cellpadding="0" cellspacing="0" >
									  <!--DWLayoutTable-->
									<tr>
									<td width="33%" height="30" valign="top"  class="textomensaje10bold">				</td>
								    <td width="33%" align="center" valign="middle"  class="label">
								    </td>
								    <td width="33%" align="center" valign="middle"  >
								    	<div>
											  <div>
											    <button id="rerun">Seleccione Lista de Recepcion</button>
											    <button id="select">Seleccione una lista</button>
											  </div>
											  <ul>
											    <li><a class="showPopup" href="mostrarlistasdebandejarecepcion.do?cambia=1" title="Atención Urgente">Atención Urgente</a></li>
											    <li><a class="showPopup" href="mostrarlistasdebandejarecepcion.do?cambia=2" title="Fecha rpta con plazo">Fecha rpta con plazo</a></li>
											    <li><a class="showPopup" href="mostrarlistasdebandejarecepcion.do?cambia=3" title="Fecha rpta limite">Fecha rpta limite</a></li>
											    <li><a class="showPopup" href="mostrarlistasdebandejarecepcion.do?cambia=4" title="Fecha rpta vencido">Fecha rpta Vencido</a></li>
											    
											  </ul>
										</div>
									</td>
								  	</tr>
									</table>
								</td>
						  	</tr>
							<tr>
								<td height="13" colspan="2" valign="top" align="center">
								<div id="lista-bandeja-recepcion-control">	
									<display:table name="sessionScope.rs_recepcion_doc" export="false" sort="list" id="b" pagesize="10"
										class="simple" style="width:100%">
										
										<display:column property="codigo_documento" title="N&deg; Registro" />
										<display:column property="codigo_expediente" title="N&deg; Expediente" />
										<display:column property="numero_documento" title="N&deg;  Documento" />
										<display:column property="oficina_origen" title="Oficina Origen" />
										<display:column property="oficina_deriva" title="Oficina Destino" />
										<display:column property="destinatario" title="Destinatario" />
										<display:column property="fecha_registro" title="Fecha Registro" />
										<display:column property="tipo_doc" title="Tipo Doc." />
										<display:column property="estado_movimiento" title="Estado" />
										<display:column property="view_archivo" align="center" title="Ver Documento" />
										<!-- display:column property="observar" align="center" title="Observar" /-->
									</display:table>
								</div>
								</td>
							</tr>
					  </table>
					  </div>
					</div>
						
				</div>
		<div id="break" ></div>			
	
		</html:form>
		</div>
		<!-- div id="dialog" title="Basic dialog">
        	<p>This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.</p>
		</div-->
		<!-- jsp:include page="/inc_footer.jsp"/-->
	</body>
</html>
<% } %>