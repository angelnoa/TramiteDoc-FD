<%@ include file="taglibs.jsp"%>
<%
	//Verifica que exista una sesion
	String nombreusuario = String.valueOf(session
			.getAttribute("nombreusuario"));
	//System.out.println("el nombre de usuario es.."+nombreusuario);
	if (nombreusuario == null || nombreusuario.equals("null")) {
%>
<SCRIPT language="Javascript">
	parent.document.location = "pag_expiracion.jsp";
</SCRIPT>
<%
	} else {
%>
<html>
<head>
<title>Derivar Documentos</title>

<link rel="stylesheet" href="css/avatec.css" type="text/css" />
<link rel="stylesheet" href="css/table.css" type="text/css" />
<link rel="stylesheet" href="css/modelo.css" type="text/css" />
<LINK href="css/calendar-green.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/tipografias.css" type="text/css">

<script type="text/javascript" src="js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="js/jquery-ui-1.10.0.custom.min.js"></SCRIPT>
<link rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css"
	type="text/css" />

<script src="js/funciones.js" type="text/javascript"></script>
<script src="js/scriptaculous.js" type="text/javascript"></script>
<script src="js/effects.js" type="text/javascript"></script>

<script type="text/javascript" src="js/ajaxtags.js"></script>
<script language="JavaScript" src="js/avatec.js"></script>

<SCRIPT src="js/calendar.js" type=text/javascript></SCRIPT>
<!-- librer�a para cargar el lenguaje deseado -->
<SCRIPT src="js/calendar-es.js" type=text/javascript></SCRIPT>
<!-- librer�a que declara la funci�n Calendar.setup, que ayuda a generar un calendario en unas pocas l�neas de c�digo -->
<SCRIPT src="js/calendar-setup.js" type=text/javascript></SCRIPT>

<!-- link href="theme/css3/style.css" rel="stylesheet" type="text/css" /-->

<style type="text/css">

/*h3 {
			 	text-transform:capitalize;
			  	font-family:Arial, Helvetica, sans-serif;
			}
			p {
				font-size:16px;
				font-family:Arial, Helvetica, sans-serif;
			}*/
#mensajegeneral {
	display: none;
	position: relative;
	line-height: 12px;
	padding: 0px;
	top: 50%;
	left: 50%;
	text-align: center;
	font-family: Verdana, sans-serif;
	font-size: 13px;
	font-weight: bold;
	color: #003366;
	width: 780px;
	height: 80px;
	margin-left: -390px;
	background: #f1f4ac;
	overflow: auto;
	border: 2px solid #acc835;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	clear: both;
}

#cuadro_texto {
	display: none;
}

#cuadro_texto_p {
	display: none;
}

#cuadro_combo {
	display: none;
}

#cuadro_combo_p {
	display: none;
}

#mensajeok {
	display: none;
	position: relative;
	padding: 0px;
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
	overflow: auto;
	line-height: 12px;
	border: 2px solid #acc835;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	clear: both;
}

#mensajeerror {
	display: none;
	position: relative;
	padding: 0px;
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
	overflow: auto;
	line-height: 12px;
	border: 2px solid #FF0000;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	clear: both;
}
</style>

<style type="text/css">
.buttoncss {
	/*margin:10px 55px 10px 0px;*/
	/*font-weight: bold;*/
	/*line-height: 1;*/
	padding: 6px 10px;
	cursor: pointer;
	color: #fff;
	text-align: center;
	/*text-shadow: 0 -1px 1px #64799e;*/
	/* Background gradient */
	background: #ffffff;
	background: -moz-linear-gradient(top, #ffffff 0%, #c4c4c4 100%);
	background: -webkit-gradient(linear, 0% 5%, 0% 100%, from(#ffffff),
		to(#c4c4c4));
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
</style>

<script language="JavaScript">
	$(document)
			.ready(
					function() {

						/*$("#mensajegeneral").click(function () {
						      $("#mensajegeneral").fadeOut("fast");
						});*/
						$("#mensajeok").click(function() {
							$("#mensajeok").fadeOut("fast");
						});

						$("#mensajeerror").click(function() {
							$("#mensajeerror").fadeOut("fast");
						});

						$("#cerrar-panel").click(function() {
							$("#panel").slideUp("fast");
						});

						$("#cerrar-mensajegeneral").click(function() {
							$("#mensajegeneral").slideUp("fast");
						});

						$("#tabs").tabs();

						$("#botoninvisibleAnular").mouseover(function(event) {
							$("#botoninvisibleAnular").addClass("buttoncss");
						});
						$("#botoninvisibleAnular").mouseout(
								function(event) {
									$("#botoninvisibleAnular").removeClass(
											"buttoncss");
								});

						$('#botoninvisibleAnular')
								.click(
										function() {

											var strURL = "ListaDocumentos.do?tipo=modificar&operacion=MD&inicia=SI";
											location = strURL;
											return;
										});

					});

	function validaFechaDDMMAAAA(fecha) {
		var dtCh = "/";
		var minYear = 1900;
		var maxYear = 2100;
		function isInteger(s) {
			var i;
			for (i = 0; i < s.length; i++) {
				var c = s.charAt(i);
				if (((c < "0") || (c > "9")))
					return false;
			}
			return true;
		}
		function stripCharsInBag(s, bag) {
			var i;
			var returnString = "";
			for (i = 0; i < s.length; i++) {
				var c = s.charAt(i);
				if (bag.indexOf(c) == -1)
					returnString += c;
			}
			return returnString;
		}
		function daysInFebruary(year) {
			return (((year % 4 == 0) && ((!(year % 100 == 0)) || (year % 400 == 0))) ? 29
					: 28);
		}
		function DaysArray(n) {
			for (var i = 1; i <= n; i++) {
				this[i] = 31;
				if (i == 4 || i == 6 || i == 9 || i == 11) {
					this[i] = 30;
				}
				if (i == 2) {
					this[i] = 29;
				}
			}
			return this;
		}
		function isDate(dtStr) {
			var daysInMonth = DaysArray(12);
			var pos1 = dtStr.indexOf(dtCh);
			var pos2 = dtStr.indexOf(dtCh, pos1 + 1);
			var strDay = dtStr.substring(0, pos1);
			var strMonth = dtStr.substring(pos1 + 1, pos2);
			var strYear = dtStr.substring(pos2 + 1);
			strYr = strYear;
			if (strDay.charAt(0) == "0" && strDay.length > 1)
				strDay = strDay.substring(1);
			if (strMonth.charAt(0) == "0" && strMonth.length > 1)
				strMonth = strMonth.substring(1);
			for (var i = 1; i <= 3; i++) {
				if (strYr.charAt(0) == "0" && strYr.length > 1)
					strYr = strYr.substring(1);
			}
			month = parseInt(strMonth);
			day = parseInt(strDay);
			year = parseInt(strYr);
			if (pos1 == -1 || pos2 == -1) {
				return false;
			}
			if (strMonth.length < 1 || month<1 || month>12) {
				return false;
			}
			if (strDay.length < 1 || day<1 || day>31
					|| (month == 2 && day > daysInFebruary(year))
					|| day > daysInMonth[month]) {
				return false;
			}
			if (strYear.length != 4 || year == 0
					|| year<minYear || year>maxYear) {
				return false;
			}
			if (dtStr.indexOf(dtCh, pos2 + 1) != -1
					|| isInteger(stripCharsInBag(dtStr, dtCh)) == false) {
				return false;
			}
			return true;
		}
		if (isDate(fecha)) {
			return true;
		} else {
			return false;
		}
	}

	function Observar(val1, val2, val3, val4, val5) {

		if (confirm("Esta seguro de Observar el Registro N� " + val1)) {
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

			with (document.forms[0]) {
				vaction = action;
				action = action
						+ '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
				tipo.value = 'observacion';
				//target = 'mainFrame';
				target = '';
				submit();

			}
		}
	}

	function showProducts() {
		//obtiene los objetos productCode, 
		var code = $("#codigo_oficina").val(); //.. y se obtiene el valor
		// alert(code);
		var val2 = 'ofiInterno';
		var val3 = 'ofiInterno2';
		//llama al servlet con el parametro seleccionado
		$("#personas").load("obtenerListaDestinatarios.do", {
			cambia : val2,
			codigo_oficina : code
		});
		//lista_personal.style.display = '';
		//$("#lista_personal2").load("obtenerListaDestinatarios.do", {
		//	cambia : val3,
		//	codigo_oficina : code
		//});
	}

	function limpiaCampos() {
		document.forms[0].dirigido.value = "";
		document.forms[0].codigo_oficina.selectedIndex = 0;
		document.forms[0].codigo_oficina.length = 1;
		document.forms[0].personas.selectedIndex = 0;
		document.forms[0].personas.length = 1;
		showInstituciones();
	}

	function showInstituciones() {
		//obtiene los objetos productCode, 
		var code = $("#codigo_intitucion").val(); //.. y se obtiene el valor
		var pertenece_a_ls = $("#pertenece_a").val();
		var val2 = 'institucion';
		var externo = 'I';
		//llama al servlet con el parametro seleccionado
		$("#codigo_oficina").load("obtenerListaDestinatarios.do", {
			cambia : val2,
			codigo_institucion : code,
			pertenece : pertenece_a_ls,
			externo_ls : externo
		});
		refresca();

		//document.forms[0].personas.selectedIndex=0;
		//document.forms[0].personas.length=1;

	}

	function refresca() {

		timer = setTimeout('showProducts()', 800);

	}

	function fn_onLoad() {
		cambiar_color_tabla("b", "tablaBackgray");

		cambiar_color_tabla("g", "tablaBackgray");

		var scroll_chks = document.getElementById('scroll');

		with (document.forms[0]) {

			if (trim(tipo.value) == "") {
				scroll_chks.style.visibility = 'visible';
			} else if (trim(tipo.value) == "editar") {

				btnEnviar.style.display = '';
				btnGrabar.style.display = 'none';
				btnCancelar.style.display = '';
				btnUpload.style.display = '';
				btnUploadInt.style.display = '';

				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;

				//btnHojaEnvio.style.display = '';
				//btnHojaEnvio.style.display = '';

				//btnCancelar.style.display = 'none';		
				//dpto.style.display = 'none';
				//users.focus();
			} else if (trim(tipo.value) == "contadjunto") {

				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;

				btnEnviar.style.display = '';
				btnGrabar.style.display = 'none';
				btnUpload.style.display = '';
				btnUploadInt.style.display = '';
				//btnHojaEnvio.style.display = '';
				//btnHojaEnvio.style.display = '';

				//btnCancelar.style.display = 'none';		
				//dpto.style.display = 'none';
				//users.focus();
			}

			else if (trim(tipo.value) == "derivar") {
				//dpto.style.display = 'none';
				//alert('entro a derivar --');

				//scroll_chks.style.visibility = 'hidden';
				//v_scroll.visi.display = 'none'; 
				btnEnviar.style.display = 'none';
				btnGrabar.style.display = 'none';
				btnCancelar.style.display = 'none';
				btnUpload.style.display = 'none';
				btnUploadInt.style.display = 'none';

				codigo_documento.readOnly = true;
				codigo_expediente.readOnly = true;
				descripcion_tipo.readOnly = true;
				numero_documento.readOnly = true;

			}
		}
	}

	function MensajeFrame() {
		$(document).ready(function() {
			$("#panel").slideDown("slow");
		});
	}

	function fn_grabar() {
		with (document.forms[0]) {

			if (trim(fecha.value) == "") {
				alert("Se requiere un valor");
				fecha.focus();
				return;
			}

			if (trim(hora.value) == "") {
				alert("Seleccionar");
				hora.focus();
				return;
			}

			//cbo_copia=""

			/*for(var i=0 ; i<document.getElementById("cbo_copia").length ;i++)
			{
			
			 if(document.getElementById("cbo_copia").options[i].selected)
			 {
				cbo_copia=cbo_copia + document.getElementById("cbo_copia").options[i].value +"|"		 
			 }
			
			}*/

			if (chk_copia.checked) {
				chk_copia.value = "S";
			} else {
				chk_copia.value = "N";
			}

			vaction = action;
			action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			//target = 'mainFrame';
			target = '';
			submit();

		}
	}

	function fn_enviar_obs() {
		with (document.forms[0]) {
			//agregado

			if (trim(codigo_oficina.value) == 0) {
				alert("Se requiere un valor");
				codigo_oficina.focus();
				btnEnviar.value = 'Derivar';
				btnEnviar.disabled = false;
				return;
			}
			if (trim(codigo_motivo.value) == 0) {
				alert("Se requiere un valor");
				codigo_motivo.focus();
				btnEnviar.value = 'Derivar';
				btnEnviar.disabled = false;
				return;
			}
			if (trim(personas.value) == 0) {
				alert("Se requiere un valor");
				personas.focus();
				btnEnviar.value = 'Derivar';
				btnEnviar.disabled = false;
				return;
			}
			if (trim(observacion.value) == 0) {
				alert("Se requiere un valor");
				observacion.focus();
				btnEnviar.value = 'Derivar';
				btnEnviar.disabled = false;
				return;
			}
			//codigo_persona

			if (confirm("Est� seguro de derivar este documento observado?")) {
				var ls_codigo_persona_session = <c:out value='${codigo_persona}'/>;
				personas.value = '';
				var ls_codigo_personal_pag = personas.value;
				//alert("codigos persona...!"+ls_codigo_persona_session+"-"+ls_codigo_personal_pag);				
				if (ls_codigo_persona_session == ls_codigo_personal_pag) {
					alert("El documento no puede ser derivado a la misma persona...!"
							+ ls_codigo_persona_session
							+ "-"
							+ ls_codigo_personal_pag);
					personas.focus();
					btnEnviar.value = 'Derivar';
					btnEnviar.disabled = false;
					return;
				}

				vaction = action;
				action = action
						+ '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
				tipo.value = 'enviar_doc_observado';

				target = '';

				submit();

			} else {
				btnEnviar.value = 'Derivar';
				btnEnviar.disabled = false;
			}

		}

	}

	function selectAll(combo, status) {
		for (i = 0; i < combo.options.length; i++) {
			combo.options[i].selected = status;
		}
	}

	function cancel() {

		$("#cuadro-docs-adjuntos").load("actualizaDocumentoAdjunto.do", {
			cambia : 'limpiaderivaall'
		});
		//$("#cuadro-img").load("actualizaDocumentoAdjunto.do", {cambia:'limpiaderiva'});
		with (document.forms[0]) {
			tipo.value = 'nuevo';

			codigo_documento.value = '';
			codigo_expediente.value = '';
			descripcion_tipo.value = '';
			numero_documento.value = '';

			fecha.value = '';
			hora.value = '';
			observacion.value = '';

			//usuario.readOnly = false;

			//removeElements(perfilSeleccionados);
			btnEnviar.style.display = 'none';
			btnCancelar.style.display = 'none';
			btnUpload.style.display = 'none';
			btnUploadInt.style.display = 'none';
			fecha.focus();
		}
	}

	function cancel_obs() {
		$(document).ready(function() {
			$("#cuadro_texto").hide();
			$("#cuadro_texto_p").hide();
			$("#cuadro_combo").show();
			$("#cuadro_combo_p").show();
			$("#cuadro-docs-adjuntos").load("actualizaDocumentoAdjunto.do", {
				cambia : 'limpiaderivaall'
			});
			//$("#cuadro-img").load("actualizaDocumentoAdjunto.do", {cambia:'limpiaderiva'});

			with (document.forms[0]) {
				tipo.value = 'nuevo';

				codigo_documento.value = '';
				codigo_expediente.value = '';
				descripcion_tipo.value = '';
				numero_documento.value = '';
				codigo_motivo.value = '';
				personas.value = '';
				codigo_oficina.value = '';
				observacion.value = '';

				fecha.value = '';
				hora.value = '';

				//usuario.readOnly = false;

				//removeElements(perfilSeleccionados);
				btnEnviar.style.display = 'none';
				btnCancelar.style.display = 'none';
				btnUpload.style.display = 'none';
				btnUploadInt.style.display = 'none';
				fecha.focus();
			}
		});
	}

	function fn_enviar() {

		with (document.forms[0]) {
			//agregado

			if (trim(codigo_oficina.value) == 0) {
				alert("Se requiere un valor");
				codigo_oficina.focus();
				btnEnviar.value = 'Derivar';
				btnEnviar.disabled = false;
				return;
			}
			if (trim(codigo_motivo.value) == 0) {
				alert("Se requiere un valor");
				codigo_motivo.focus();
				btnEnviar.value = 'Derivar';
				btnEnviar.disabled = false;
				return;
			}
			if (trim(personas.value) == 0) {
				alert("Se requiere un valor");
				personas.focus();
				btnEnviar.value = 'Derivar';
				btnEnviar.disabled = false;
				return;
			}
			if (trim(observacion.value) == 0) {
				alert("Se requiere un valor");
				observacion.focus();
				btnEnviar.value = 'Derivar';
				btnEnviar.disabled = false;
				return;
			}

			if ((Validarcampovacio(trim(fecha_rpta.value)) == true)
					&& (validaFechaDDMMAAAA(trim(fecha_rpta.value)) == false)) {
				alert("Ingresar Fecha Rpta Valida");
				fecha_rpta.focus();
				btnEnviar.value = 'Derivar';
				btnEnviar.disabled = false;
				return;
			}
			//codigo_persona

			if (confirm("Est� seguro de derivar este documento?")) {
				var ls_codigo_persona_session = <c:out value='${codigo_persona}'/>;
				var ls_codigo_personal_pag = personas.value;

				if (ls_codigo_persona_session == ls_codigo_personal_pag) {
					alert("El documento no puede ser derivado a la misma persona...!");
					personas.focus();
					btnEnviar.value = 'Derivar';
					btnEnviar.disabled = false;
					return;
				}

				vaction = action;
				action = action
						+ '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
				tipo.value = 'enviar';

				target = '';

				submit();
			} else {
				codigo_documento.focus();
				btnEnviar.value = 'Derivar';
				btnEnviar.disabled = false;
			}

		}
	}

	function fn_editar_copia(pcodigo_documento, psecuencia_movimiento) {
		//$(document).ready(function(){
		$("#cuadro-docs-adjuntos").load("actualizaDocumentoAdjunto.do", {
			cambia : 'actualizaderiva'
		});
		//});

		with (document.forms[0]) {
			var x = pcodigo_documento;
			var y = psecuencia_movimiento;
			codigo_documento.value = pcodigo_documento;
			secuencia_movimiento.value = psecuencia_movimiento;

			codigo_documento.readOnly = true;
			numero_documento.readOnly = true;
			btnEnviar.style.display = '';

			//alert("aqui "+action);
			vaction = action;
			action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';

			target = '';

			tipo.value = 'editar';

			submit();

		}
	}

	function fn_editar_copia_obs(pcodigo_documento, psecuencia_movimiento,
			psecuencia_origen) {
		//VERIFICO SI LA SECUENCIA TIENE ARCHIVOS Y SI YA TIENE LLENADO EL CAMPO DOC RESPUESTA
		alert(pcodigo_documento + "," + psecuencia_movimiento + ","
				+ psecuencia_origen);
		$("#cuadro-docs-adjuntos").load("actualizaDocumentoAdjunto.do", {
			cambia : 'iniciaactualizaderiva',
			pcodigo_documento : pcodigo_documento,
			psecuencia_movimiento : psecuencia_movimiento
		});
		with (document.forms[0]) {
			codigo_documento.value = pcodigo_documento;
			secuencia_movimiento.value = psecuencia_movimiento;
			codigo_inicia.value = psecuencia_origen;

			codigo_documento.readOnly = true;
			numero_documento.readOnly = true;
			codigo_oficina.disabled = true;
			//fecha.readOnly = true;
			btnEnviar.style.display = '';

			//alert(action);
			vaction = action;
			action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';

			target = '';

			tipo.value = 'editar_obs';

			submit();

		}
	}

	function fn_editar(pcodigo_documento, pcodigo_expediente,
			pdescripcion_tipo, pnumero_documento, pcodigo_oficina,
			pestado_movimiento, pfecha_movimiento, phora_movimiento,
			psecuencia_movimiento, pcodigo_inicia, pnaturaleza_documento,
			pcodigo_recepcion) {

		with (document.forms[0]) {
			//alert("alert ");

			codigo_documento.value = pcodigo_documento;
			codigo_expediente.value = pcodigo_expediente;
			descripcion_tipo.value = pdescripcion_tipo;
			numero_documento.value = pnumero_documento;
			codigo_oficina.value = pcodigo_oficina;
			estado_movimiento.value = pestado_movimiento;
			fecha.value = pfecha_movimiento;
			hora.value = phora_movimiento;

			secuencia_movimiento.value = psecuencia_movimiento;
			codigo_inicia.value = pcodigo_inicia;
			naturaleza_documento.value = pnaturaleza_documento;
			codigo_recepcion.value = pcodigo_recepcion;

			codigo_documento.readOnly = true;
			codigo_expediente.readOnly = true;
			descripcion_tipo.readOnly = true;
			numero_documento.readOnly = true;

			btnEnviar.style.display = '';

			vaction = action;
			action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';

			target = '';

			tipo.value = 'editar';

			submit();
		}
	}

	function SeleccionarCampo(campo, valor) {
		for (var i = 0; i < document.forms[0].elements.length; i++) {
			if (document.forms[0].elements[i].name == campo) {
				for (var j = 0; j < document.forms[0].elements[i].length; j++) {
					if (document.forms[0].elements[i].options[j].value == valor) {
						document.forms[0].elements[i].options[j].selected = true;
					}
				}
			}
		}
	}

	function SetearCampo(valor1, valor2) {
		var v1 = valor1;
		var v2 = valor2;
		//alert(v1+", "+v2);
		$(document).ready(function() {
			$("#cuadro_texto").show();
			$("#cuadro_texto_p").show();
			$("#cuadro_combo").hide();
			$("#cuadro_combo_p").hide();
			$("#oficina_destino").val(valor1);
			$("#persona_destino").val(valor2);
		});
	}

	function fecha() {
		var mifecha;
		var mydate = new Date();
		var year = mydate.getYear();
		if (year < 1000)
			year += 1900;
		var day = mydate.getDay();
		var month = mydate.getMonth() + 1;
		if (month < 10)
			month = "0" + month;
		var daym = mydate.getDate();
		if (daym < 10)
			daym = "0" + daym;
		mifecha = daym + "/" + month + "/" + year;
		//alert(mifecha);
		//document.form_datos.fecha_doc.value= mifecha;

		with (document.forms[0]) {
			fecha.value = mifecha;
			//alert(mifecha);
			/*fechaProceso.disabled = true;
			imgFechaProceso.style.display = 'none';*/
		}
		//document.form1.FechaActualizacion.value = mifecha; 
	}

	function MensajeOKey() {
		//var strURL="ValidaPaginas.do?tipo=mensajerecepcion";
		//window.open(strURL,"","HEIGHT=140,WIDTH=500,scrollbars=no");
		$(document).ready(function() {
			$("#mensajeok").fadeIn("slow");

		});
	}

	function MensajeError() {
		//var strURL="ValidaPaginas.do?tipo=mensajerecepcion";
		//window.open(strURL,"","HEIGHT=140,WIDTH=500,scrollbars=no");
		$(document).ready(function() {
			$("#mensajeerror").fadeIn("slow");

		});
	}

	function HabilitaText() {
		with (document.forms[0]) {

			if (cbo_fecharpta.checked) {

				fecha_rpta.disabled = false;
				lanzador1.style.display = '';

			} else {

				fecha_rpta.disabled = true;
				lanzador1.style.display = 'none';
				fecha_rpta.value = "";

			}
		}
	}

	function HabilitaCombo(valor) {
		var valor_ls = valor;
		if (valor_ls == 1) {
			var scroll_chks = document.getElementById('scroll_derivar');
			with (document.forms[0]) {
				if (chk_copia.checked) {

					scroll_chks.style.visibility = 'visible';
				} else {
					scroll_chks.style.visibility = 'hidden';
				}
			}
		} else {
			var scroll_chks_s = document.getElementById('scroll_2');

			with (document.forms[0]) {
				if (chk_copia_other.checked) {
					
					scroll_chks_s.style.visibility = 'visible';
				} else {
					
					scroll_chks_s.style.visibility = 'hidden';
				}
			}
		}
	}

	function AgregarArchivos() {

		var ls_operacion = "N";

		if (Validarcampovacio(document.getElementById("codigo_documento").value) == false) {
			alert("Seleccione un Registro");
			return;
		}

		var ls_codigo_documento = document.getElementById("codigo_documento").value;
		var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
		var ls_secuencia_movimiento = document
				.getElementById("secuencia_movimiento").value;
		var ls_modo_observacion = document.getElementById("modo_observacion").value;
		//alert(ls_codigo_documento);

		var strURL = 'MantDerivar.do?tipo=upload' + '&operacion='
				+ ls_operacion + '&codigo_documento=' + ls_codigo_documento
				+ '&codigo_expediente=' + ls_codigo_expediente
				+ '&secuencia_movimiento=' + ls_secuencia_movimiento + "&modo="
				+ ls_modo_observacion;
		//window.open(strURL,"","HEIGHT=250,WIDTH=580,scrollbars=yes")
		winPopup = window.open(strURL, "", "scrollbars,HEIGHT=250,WIDTH=580");

	}

	function AgregarDocInternos() {

		//alert("Internos");

		var strURL = "MantenimientoDocumentoInterno.do";
		winPopup = window.open(strURL, "", "scrollbars,HEIGHT=540,WIDTH=700");
	}

	function VerArchivos(ls_codigo_documento, ls_codigo_expediente,
			ls_codigo_recepcion) {

		var ls_operacion = "N";
		/*var ls_codigo_documento = document.getElementById("codigo_documento").value;
		var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
		var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;*/
		//alert(ls_codigo_documento);
		var strURL = 'MantSeguimiento.do?tipo=upload' + '&operacion='
				+ ls_operacion + '&codigo_documento=' + ls_codigo_documento
				+ '&codigo_expediente=' + ls_codigo_expediente
				+ '&codigo_recepcion=' + ls_codigo_recepcion;
		window.open(strURL, "", "HEIGHT=250,WIDTH=580,scrollbars=yes");

	}

	function DevolverRecepcion(pcodigo_documento, pcodigo_expediente,
			pcodigo_recepcion, psecuencia) {

		var ls_operacion = "N";
		/*var ls_codigo_documento = document.getElementById("codigo_documento").value;
		var ls_codigo_expediente = document.getElementById("codigo_expediente").value;
		var ls_secuencia_movimiento = document.getElementById("secuencia_movimiento").value;*/
		//alert(ls_codigo_documento);
		if (confirm("Desea Revertir la Recepcion?")) {

			var strURL = "ListaDocumentos.do?tipo=derivar&file="
					+ pcodigo_documento + "&operacion=D&sec=" + psecuencia
					+ "&inicia=SI";
			location = strURL;
			return;

		}

	}

	function VerHojaEnvio(pcodigo_documento, pcodigo_expediente) {

		//alert(pcodigo_documento);
		//alert(pcodigo_expediente);
		//if(confirm("Desea Imprimir hoja de envio?")){

		var strURL = "ValidaPaginas.do?tipo=hojaenvio";
		strURL += "&codigo_documento=" + pcodigo_documento
				+ "&codigo_expediente=" + pcodigo_documento;
		//window.location.href=strURL
		window.open(strURL, "", "HEIGHT=440,WIDTH=770,scrollbars=yes");
		//										}
	}

	function HojaEnvio(pcodigo_documento_env, pcodigo_expediente_env) {

		//alert(pcodigo_documento);
		//alert(pcodigo_expediente);

		alert("Dentro de Hoja de Envio...");
		if (confirm("Desea Imprimir hoja de envio?")) {

			//VerHojaEnvio(pcodigo_documento_env,pcodigo_expediente_env);
			with (document.forms[0]) {

				btnHojaEnvio.style.display = 'none';

			}
		}
	}

	function MostrarAdjunto() {
		/*with(document.forms[0])	{						
			codigocontador.value = codigo;

			if(winPopup){
				if(!winPopup.closed) 
			    	winPopup.close();
			}		
			
			vaction = action;
			action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			tipo.value = 'contadjunto';
			target = '';
			submit();
		}*/
		//alert("Entre");
		$(document).ready(function() {
			$("#cuadro-docs-adjuntos").load("actualizaDocumentoAdjunto.do", {
				cambia : 'actualizaderiva'
			});
		});
		return;
	}

	function fn_editar(pcodigo_documento, pcodigo_expediente,
			pdescripcion_tipo, pnumero_documento, pcodigo_oficina,
			pestado_movimiento, pfecha_movimiento, phora_movimiento,
			psecuencia_movimiento, pcodigo_inicia, pnaturaleza_documento,
			pcodigo_recepcion) {

		with (document.forms[0]) {

			codigo_documento.value = pcodigo_documento;
			codigo_expediente.value = pcodigo_expediente;
			descripcion_tipo.value = pdescripcion_tipo;
			numero_documento.value = pnumero_documento;
			/*fecha.value = pfecha_def;
			hora.value = phora_def;*/
			/*fecha.value = pfecha;
			hora.value = phora;
			observacion.value = pobservacion;*/

			codigo_oficina.value = pcodigo_oficina;
			estado_movimiento.value = pestado_movimiento;
			fecha.value = pfecha_movimiento;
			hora.value = phora_movimiento;

			secuencia_movimiento.value = psecuencia_movimiento;
			codigo_inicia.value = pcodigo_inicia;
			naturaleza_documento.value = pnaturaleza_documento;
			codigo_recepcion.value = pcodigo_recepcion;
			//alert(pcodigo_recepcion);
			/*alert(psecuencia_movimiento);
			alert(pcodigo_inicia);
			alert(pnaturaleza_documento);*/
			codigo_documento.readOnly = true;
			codigo_expediente.readOnly = true;
			descripcion_tipo.readOnly = true;
			numero_documento.readOnly = true;

			btnEnviar.style.display = '';

			//agregado

			vaction = action;
			action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			//alert(action);
			target = '';
			//alert(target);
			tipo.value = 'editar';
			//alert(tipo);	
			submit();
		}
	}

	function listadocumentos() {

		var strURL = "ListaDocumentos.do?tipo=modificar&operacion=MD&inicia=SI";

		location = strURL;

		return;
	}

	function validarCaracterFecha() {
		var objeto = validarCaracterFecha.arguments[0];
		var wKey = window.event.keyCode;
		valor = objeto.value;
		var nAnt = 0;
		n = valor.length;
		if (n > 0) {
			for (var i = 0; i < n; i++) {
				if (nAnt < n) {
					if (n == 2 && i == 1)
						objeto.value = valor + "/";
					if (n == 5 && i == 3)
						objeto.value = valor + "/";
				}
			}
		}
		nAnt = n;
	}

	function ver_frame() {

		var elem = document.getElementById("keywords");
		with (document.forms[0]) {

			//var elem = document.getElementById("codigo_documento_busqueda");
			//elem=elem.trim();
			//alert("Entre aqui");
			// Check to see if the field contains a valid date

			codigo_documento_busqueda_ls.value = elem.value;

			/*if ( ! checkNumber(elem)) {
				//alert("Entre aqui 2");
				    alert( "Este campo no es un n�mero." );
				    elem.value="";
				    elem.focus();
				    return false;
				}*/

			vaction = action;
			action = action + '?d-1772-p=<c:out value="${param['d-1772-p']}"/>';
			tipo.value = 'buscar';
			//target = 'mainFrame';

			if (codigo_documento_busqueda_ls.value == "Buscar...") {
				tipo.value = 'derivar';
			}

			target = '';

			submit();

			/*		var strURL = "MantDerivar.do?tipo=buscar";
				 	 location=strURL;
					return;*/

		}

	}

	/*function ver_frame(){
	
	
	//var ls_numero_documento = document.getElementById("numero_documento").value;
	var ls_codigo_expediente = document.getElementById("codigo_expediente_busqueda").value;
	//var ls_codigo_oficina = document.getElementById("codigo_oficina_busqueda").value;
	var ls_codigo_documento = document.getElementById("codigo_documento_busqueda").value;
	
	
	 var strURL = "ListaDocumentos.do?tipo=derivar&operacion=B";
	 strURL += "&codigo_expediente_busqueda="+ls_codigo_expediente+"&codigo_documento_busqueda="+ls_codigo_documento;
	
	 location=strURL;
	return;

	}*/

	function listar_todos() {

		//var strURL = "ListaDocumentos.do?tipo=modificar&operacion=ME";
		var strURL = "ListaDocumentos.do?tipo=derivar&operacion=D";
		location = strURL;
		return;

	}

	function checkNumber(elem) {
		/*valida si contiene al menos un digito, se hizo pensando en el numero de registro de documento.
		 *Fecha:18-09-2011
		 * */
		return !elem.value || /^\d+$/.test(elem.value);
	}

	function MensajeRegistro() {
		$(document).ready(function() {
			$("#mensajegeneral").fadeIn("slow");

		});
	}
</script>
<style type="text/css">
<!--
.style1 {
	font-size: 12px
}
-->
</style>
</head>
<body bgColor="#F0F5FB" topmargin="0" leftmargin="0"
	onLoad="fn_onLoad();">


	<div class="derivaconta">

		<div id="top-bar" style="background-image: url(img/topnav_s.gif);">
			<jsp:include page="/pag_menu.jsp" />
		</div>
		<html:form action="/MantDerivar" method="POST">
			<html:hidden property="tipo" />
			<html:hidden property="estado_movimiento" />
			<html:hidden property="descripcion_tipo" />
			<html:hidden property="hora" />
			<html:hidden property="secuencia_movimiento"
				styleId="secuencia_movimiento" />
			<html:hidden property="codigo_inicia" />
			<html:hidden property="naturaleza_documento" />
			<html:hidden property="codigo_recepcion" />
			<html:hidden property="codigocontador" />
			<INPUT type="hidden" id="valor1" name="valor1" value="">
			<INPUT type="hidden" id="valor2" name="valor2" value="">
			<INPUT type="hidden" id="valor3" name="valor3" value="">
			<INPUT type="hidden" id="valor4" name="valor4" value="">
			<INPUT type="hidden" id="valor5" name="valor5" value="">

			<INPUT type="hidden" id="ls_codigo_persona_que_observa"
				name="ls_codigo_persona_que_observa"
				value="<c:out value='${ls_codigo_persona_que_observa}'></c:out>">
			<INPUT type="hidden" id="ls_codigo_oficina_que_observa"
				name="ls_codigo_oficina_que_observa"
				value="<c:out value='${ls_codigo_oficina_que_observa}'></c:out>">
			<INPUT type="hidden" id="ls_codigo_motivo" name="ls_codigo_motivo"
				value="<c:out value='${ls_codigo_motivo}'></c:out>">
			<INPUT type="hidden" id="ls_persona_destino"
				name="ls_persona_destino"
				value="<c:out value='${ls_persona_destino}'></c:out>">
			<INPUT type="hidden" id="ls_oficina_destino"
				name="ls_oficina_destino"
				value="<c:out value='${ls_oficina_destino}'></c:out>">

			<INPUT type="hidden" id="modo_observacion" name="modo_observacion"
				value="<c:out value='${modo_observacion}'></c:out>">
			<INPUT type="hidden" id="ls_secuencia_movimiento_destino"
				name="ls_secuencia_movimiento_destino"
				value="<c:out value='${ls_secuencia_movimiento_destino}'></c:out>">

			<INPUT type="hidden" id="contador" name="contador" value="0">

			<INPUT type="hidden" id="codigo_documento_busqueda_ls"
				name="codigo_documento_busqueda_ls" value="">

			<INPUT type="hidden" id="pertenece_a" name="pertenece_a"
				value="<c:out value='${pertenece_a}'/>">

			<c:remove var="pagina" />

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
			<c:choose>
				<c:when test='${operacionpopup == "Y"}'>
					<SCRIPT language="javascript">
						MensajeRegistro();
					</SCRIPT>
					<c:remove var="operacionpopup" scope="request" />
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test='${cuadro_mensaje ==  "BDER"}'>
					<SCRIPT language="javascript">
						MensajeFrame();
					</SCRIPT>
					<c:remove var="cuadro_mensaje" scope="request" />
				</c:when>
			</c:choose>


			<div id="mensajeok" class="rounded">
				<div id="vvvv" style="position: absolute; top: 18px; left: 20%;">
					<a href="#"><img src="img/ok.png" /></a>
				</div>
				<br>
				<c:out value='${mensaje}'></c:out>
				<br>
				<c:out value='${mensaje_reg}'></c:out>
				<br>
				<c:out value='${mensaje_exp}'></c:out>
				<br>
			</div>
			<div id="mensajeerror" class="rounded">
				<div id="vvvv" style="position: absolute; top: 18px; left: 20%;">
					<a href="#"><img width="36px" height="36px" src="img/error.png" /></a>
				</div>
				<br>
				<c:out value='${mensaje}'></c:out>
				<br>
				<c:out value='${mensaje_reg}'></c:out>
				<br>
				<c:out value='${mensaje_exp}'></c:out>
				<br>
			</div>
			<div id="panel">
				<div id="vvvv" style="position: absolute; top: 18px; left: 20%;">
					<a href="#"><img src="img/ok.png" /></a>
				</div>
				<div id="cerrar-panel"
					style="cursor: pointer; position: absolute; width: 10%; height: 40px; top: 15px; left: 90%; text-align: left;">
				</div>
				<br>
				<c:out value='${mensaje}' />
				<br> <a
					href="VerArchivoPDFDocumentoInterno.do?filename=<c:out value='${nombreArchivoVer_}'/>&codigo_estado=<c:out value='${tipoDocumentoVer_}'/>&codigo_oficina_pertenece=<c:out value='${codigoOficinaVer_}'/>"><c:out
						value='${nombreArchivoVer_}' /></a><br> <br> &nbsp;&nbsp;
			</div>
			<div id="mensajegeneral" class="rounded">
				<div id="vvvv" style="position: absolute; top: 5px; left: 20%;">
					<a href="#"><img src="img/signatureok.png" /></a>
				</div>
				<div id="cerrar-mensajegeneral"
					style="cursor: pointer; position: absolute; width: 10%; height: 40px; top: 15px; left: 90%; text-align: left;">
				</div>
				<br>
				<c:out value='${mensaje}' />
				<br> <a
					href="VerArchivoPDFDocumentoInterno.do?filename=<c:out value='${nombreArchivoVer_}'/>&codigo_estado=<c:out value='${tipoDocumentoVer_}'/>&codigo_oficina_pertenece=<c:out value='${codigoOficinaVer_}'/>"><c:out
						value='${nombreArchivoVer_}' /></a><br>
				<c:out value='${mensaje_reg}'></c:out>
				<br>
				<c:out value='${mensaje_exp}'></c:out>
				<br>

			</div>
			<div id="break"></div>
			<div id="contactderiva" class="rounded">
				<div id="TDHeadCab">
					<span class="labelrounded">DERIVAR DOCUMENTOS </span>


					<div id="botoninvisibleAnular" title="Reversion de la Derivacion"
						style="width: 80px; height: 20px; position: absolute; top: 6px; padding: 3px 10px; text-align: center; FONT-SIZE: 8pt; font-weight: normal; left: 72%; color: #003366; line-height: 20px;">
						&nbsp;<img src="img/edit_doc.png"
							style="vertical-align: middle; width: 20px; height: 20px; border: 0px;">&nbsp;&nbsp;
						Modificar
					</div>
					<input type="text" name="q" id="keywords" class="input-search"
						onfocus="this.value=''"
						onkeypress="return validatecla('enteros',this)" value="Buscar..." />
					<button class="image" onclick="ver_frame()"
						title="Buscar por Nro de Registro">Buscador</button>

				</div>
				<div id="TDHeadCabMensaje">
					<table width="100%" cellspacing="0" cellpadding="0" align="left">

						<tr>
							<td width="409" height="18" align="LEFT" valign="middle"
								background="img/fondoplomo8.jpg" class="textomensaje10bold">
								<c:out value='${mensaje_recepcion}' /> <br> <c:out
									value='${mensaje_numreg}' />
							</td>
							<td width="568" align="center" valign="middle"
								background="img/fondoplomo8.jpg" class="label1">
								<!--DWLayoutEmptyCell-->&nbsp;
							</td>
							<td width="47" align="center" valign="middle"
								background="img/fondoplomo8.jpg">&nbsp;&nbsp;&nbsp;</td>
						</tr>
					</table>
				</div>

				<!-- div id="TDHeadCabBusqueda" >
	 <div style="width: 50%; height: 50px; margin-left: auto; margin-right: auto; margin-top: 5px;
		text-align: right; float:left;">
     <p><label>B�squeda por : &nbsp;&nbsp;&nbsp;&nbsp;</label><html:text property="codigo_documento_busqueda" styleId="codigo_documento_busqueda" onkeypress="return validatecla('enteros',this)" style="width: 150px;" styleClass="input-medium"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
     
     </div>
     <div style="width:50%; float:right ;">
     	<input  type="submit" name="button"  class="deriva" value="Buscar" onClick="ver_frame()" alt="buscar"  >
        <input type="button" name="button" class="deriva" value="Listar Todos" onClick="listar_todos()" alt="Listar Todos"  >
     </div>	
     <br>
     <br>
     <br>
	 </div-->
				<div id="Lineaform2"></div>

				<div id="left" class="columna-left-small">
					<p>
						<label>N&deg;
							Registro:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<html:text property="codigo_documento" styleId="codigo_documento"
							readonly="true" style="width: 150px;" styleClass="input-medium" />
					</p>
					<p>
						<label>N&deg;
							Doc.:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<html:text property="numero_documento" styleId="numero_documento"
							style="width: 270px;" styleClass="input-medium" />
					</p>

					<!-- JAZANERO -->
					<p>
						<label>Institucion <br>Destino:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</label> <select id="codigo_intitucion" name="codigo_intitucion"
							class="input-medium" onchange="showInstituciones()">
							<OPTION value="0" selected>-- Selecc Uno --</OPTION>
							<OPTION value="2">CONCYTEC</OPTION>

						</select>
					</p>

					<c:choose>

						<c:when test='${operacion ==  "derivar"}'>
							<p>
								<label>Oficina:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<select id="codigo_oficina" name="codigo_oficina" class="input"
									style="WIDTH: 270px;" onchange="showProducts()">
									<OPTION value="0" selected>-- Selecc Uno --</OPTION>
									<c:choose>
										<c:when test='${not empty rs_oficina}'>
											<c:forEach items='${rs_oficina}' var='pa'>
												<c:choose>
													<c:when test='${es_padre == "0" }'>
														<c:choose>
															<c:when
																test="${pa.padre == 0 || pa.grupo_oficina_combo!=1}">
																<option value=<c:out value='${pa.codigo_oficina}'/>><c:out
																		value='${pa.siglas_oficina}' /> -
																	<c:out value='${pa.descripcion_oficina}' /></option>
															</c:when>
														</c:choose>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${pa.grupo_oficina_combo!=1}">
																<option value=<c:out value='${pa.codigo_oficina}'/>><c:out
																		value='${pa.siglas_oficina}' /> -
																	<c:out value='${pa.descripcion_oficina}' /></option>
															</c:when>
														</c:choose>
													</c:otherwise>

												</c:choose>

											</c:forEach>
										</c:when>
									</c:choose>
								</select>
							</p>
						</c:when>
						<c:when test='${operacion ==  "editar"}'>
							<p>
								<label>Oficina:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<select id="codigo_oficina" name="codigo_oficina" class="input"
									style="WIDTH: 270px;" onchange="showProducts()">
									<OPTION value="0" selected>-- Selecc Uno --</OPTION>
									<c:choose>
										<c:when test='${not empty rs_oficina}'>
											<c:forEach items='${rs_oficina}' var='pa'>
												<c:choose>
													<c:when test='${es_padre == "0" }'>
														<c:choose>
															<c:when
																test="${pa.padre == 0 || pa.grupo_oficina_combo!=1}">
																<option value=<c:out value='${pa.codigo_oficina}'/>><c:out
																		value='${pa.siglas_oficina}' /> -
																	<c:out value='${pa.descripcion_oficina}' /></option>
															</c:when>
														</c:choose>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${pa.grupo_oficina_combo!=1}">
																<option value=<c:out value='${pa.codigo_oficina}'/>><c:out
																		value='${pa.siglas_oficina}' /> -
																	<c:out value='${pa.descripcion_oficina}' /></option>
															</c:when>
														</c:choose>
													</c:otherwise>

												</c:choose>

											</c:forEach>
										</c:when>
									</c:choose>
								</select>
							</p>
						</c:when>
						<c:when test='${modo_observacion == "1" }'>
							<div id="cuadro_texto">
								<p>
									<label>Oficina:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									<input type="text" class="input" style="WIDTH: 270px;"
										name="oficina_destino" id="oficina_destino"
										readonly="readonly">
								</p>
							</div>
							<div id="cuadro_combo">
								<p>
									<label>Oficina:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									<select id="codigo_oficina" name="codigo_oficina" class="input"
										style="WIDTH: 270px;" onchange="showProducts()">
										<OPTION value="0" selected>-- Selecc Uno --</OPTION>
										<c:choose>
											<c:when test='${not empty rs_oficina}'>
												<c:forEach items='${rs_oficina}' var='pa'>
													<option value=<c:out value='${pa.codigo_oficina}'/>>
														<c:out value='${pa.siglas_oficina}' /> -
														<c:out value='${pa.descripcion_oficina}' />
													</option>
												</c:forEach>
											</c:when>
										</c:choose>
									</select>
								</p>
							</div>

						</c:when>
						<c:otherwise>
							<p>
								<label>Oficina:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<select id="codigo_oficina" name="codigo_oficina" class="input"
									style="WIDTH: 270px;" onchange="showProducts()">
									<OPTION value="0" selected>-- Selecc Uno --</OPTION>
									<c:choose>
										<c:when test='${not empty rs_oficina}'>
											<c:forEach items='${rs_oficina}' var='pa'>
												<c:choose>
													<c:when test='${es_padre == "0" }'>
														<c:choose>
															<c:when
																test="${pa.padre == 0 || pa.grupo_oficina_combo==1}">
																<option value=<c:out value='${pa.codigo_oficina}'/>><c:out
																		value='${pa.siglas_oficina}' /> -
																	<c:out value='${pa.descripcion_oficina}' /></option>
															</c:when>
														</c:choose>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${pa.grupo_oficina_combo==1}">
																<option value=<c:out value='${pa.codigo_oficina}'/>><c:out
																		value='${pa.siglas_oficina}' /> -
																	<c:out value='${pa.descripcion_oficina}' /></option>
															</c:when>
														</c:choose>
													</c:otherwise>

												</c:choose>

											</c:forEach>
										</c:when>
									</c:choose>

								</select>
							</p>
						</c:otherwise>

					</c:choose>

					<INPUT type="hidden" id="oper" name="oper"
						value="<c:out value='${operacion}'/>"> <INPUT
						type="hidden" id="per" name="per"
						value="<c:out value='${personas}'/>"> <INPUT type="hidden"
						id="ofi" name="ofi" value="<c:out value='${codigo_oficina}'/>">
					<INPUT type="hidden" id="contador" name="contador"
						value="<c:out value='${codigocontador}'/>">
					<c:choose>
						<c:when test='${modo_observacion == "1" }'>
							<div id="cuadro_texto_p">
								<p>
									<label>Personal:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									<input type="text" class="input" style="WIDTH: 270px;"
										name="persona_destino" id="persona_destino"
										readonly="readonly">
								</p>
							</div>
							<div id="cuadro_combo_p">
								<p>
									<label>Personal:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									<select id="personas" name="personas" class="input"
										style="WIDTH: 270px;">
										<c:choose>
											<c:when test='${not empty rs_personas}'>
												<c:forEach items='${rs_personas}' var='pa'>
													<option value=<c:out value='${pa.codigo_personal}'/>>
														<c:out value='${pa.nombre_personal}' />
													</option>
												</c:forEach>
											</c:when>
										</c:choose>
									</select>
								</p>
							</div>
						</c:when>

						<c:when test='${operacion ==  "derivar"}'>
							<p>
								<label>Personal:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<select id="personas" name="personas" class="input"
									style="WIDTH: 270px;">
									<option value="0">:: Personas ::</option>
								</select>
							</p>
						</c:when>
						<c:when test='${codigocontador ==  "1"}'>
							<p>
								<label>Personal:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<select id="personas" name="personas" class="input"
									style="WIDTH: 270px;">
									<option value="0">:: Personas ::</option>
									<c:choose>
										<c:when test='${not empty rs_personas}'>
											<c:forEach items='${rs_personas}' var='pa'>
												<option value=<c:out value='${pa.codigo_personal}'/>>
													<c:out value='${pa.nombre_personal}' />
												</option>
											</c:forEach>
										</c:when>
									</c:choose>
								</select>
							</p>
						</c:when>
						<c:when test='${operacion ==  "editar"}'>
							<c:choose>
								<c:when test='${cargo_personal=="3" || cargo_personal=="10"}'>
									<p>
										<label>Personal:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
										<select id="personas" name="personas" class="input"
											style="WIDTH: 270px;">
											<option value="0">:: Personas ::</option>
										</select>
									</p>
								</c:when>
								<c:otherwise>
									<p>
										<label>Personal:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
										<select id="personas" name="personas" class="input"
											style="WIDTH: 270px;">
											<option value="0">:: Personas ::</option>
											<c:choose>
												<c:when test='${not empty rs_personas}'>
													<c:forEach items='${rs_personas}' var='pa'>
														<option value=<c:out value='${pa.codigo_personal}'/>>
															<c:out value='${pa.nombre_personal}' />
														</option>
													</c:forEach>
												</c:when>
											</c:choose>
										</select>
									</p>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<p>
								<label>Personal:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<select id="personas" name="personas" class="input"
									style="WIDTH: 270px;">
									<c:choose>
										<c:when test='${not empty rs_personas}'>
											<c:forEach items='${rs_personas}' var='pa'>
												<option value=<c:out value='${pa.codigo_personal}'/>>
													<c:out value='${pa.nombre_personal}' />
												</option>
											</c:forEach>
										</c:when>
									</c:choose>
								</select>
							</p>
						</c:otherwise>
					</c:choose>

					<p>
						<label>Requiere Prioridad:</label> <input type="checkbox"
							class="caja" name="cbo_fecharpta" id="cbo_fecharpta"
							value="checkbox" onClick="javascript:HabilitaText();">
						&nbsp;&nbsp;<label>Fecha Requerida</label>
						<c:choose>
							<c:when test='${codigocontador ==  "1"}'>
								<INPUT class="input-medium"
									onKeyPress="validarCaracterFecha(this);" style="width: 90px;"
									id="fecha_rpta" maxLength="10" size="11" name="fecha_rpta"
									value="<c:out value='${fecha_rpta}'/>">
							</c:when>
							<c:otherwise>
								<INPUT class="input-medium"
									onKeyPress="validarCaracterFecha(this);" style="width: 90px;"
									id="fecha_rpta" maxLength="10" size="11" name="fecha_rpta">
							</c:otherwise>
						</c:choose>
						&nbsp; <a href="" id="lanzador1" style="display: none; width: 20;"><img
							src="img/cal.gif" title="Fecha de Respuesta (dd/mm/yyyy)"
							border="0"></a>
					</p>
					<p>
						<label>Observaci&oacute;n: </label>
						<html:textarea property="observacion" styleId="observacion"
							style="width: 300px;height: 70px;" styleClass="textarea" />
					</p>
					<p>
						<label>Fecha Rpta.:</label> <input class="input-medium"
							style="width: 90px;" onKeyPress="validarCaracterFecha(this);"
							id="fecha_rpta_rq" maxlength="10" size="11" name="fecha_rpta_rq">
						&nbsp; <a href="" id="lanzador2"><img src="img/cal.gif"
							title="Fecha de Rpta (dd/mm/yyyy)" border="0"></a>
					</p>
					<div id="cuadro-docs-adjuntos" style="float: left;">
						<p>
							<label>Doc. de Rpta:</label> <input class="input-medium"
								id="doc_resp" maxlength="60" size="40" name="doc_resp"
								value="<c:out value='${doc_resp_ls}'/>"> <input
								type="button" id="btnUpload" name="btnUpload"
								value="Agregar Archivos" onClick="AgregarArchivos()"
								title="Agregar Archivos" class="button">

							<c:choose>
								<c:when
									test='${operacion ==  "contadjunto" && codigocontador ==  "1"}'>
									<img src="img/docadjuntos.gif" title="Documentos Adjuntos">
								</c:when>
							</c:choose>
						</p>
					</div>



				</div>

				<div id="center" class="columna-center-small">&nbsp;</div>

				<div id="rigth" class="columna-rigth-small">
					<p>
						<label>N&deg; Expediente:&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<html:text property="codigo_expediente"
							styleId="codigo_expediente" readonly="true" style="width: 150px;"
							styleClass="input-medium" />
					</p>
					<p>
						<label>Fecha Derivaci&oacute;n:</label>
						<html:text property="fecha" styleId="fecha" style="width: 90px;"
							maxlength="11" styleClass="input-medium"
							onkeypress="validarCaracterFecha(this);" />
						&nbsp; <a href="" id="lanzador"><img src="img/cal.gif"
							title="Fecha de Derivacion(dd/mm/yyyy)" border="0"></a>
					</p>

					<p>
						<label>Acci&oacute;n a Realizar:&nbsp;&nbsp;&nbsp;</label>
						<c:choose>
							<c:when test='${operacion ==  "derivar"}'>
								<select id="codigo_motivo" name="codigo_motivo" class="input"
									style="WIDTH: 270px;">
									<OPTION value="0" selected>-- Selecc Uno --</OPTION>
									<c:choose>
										<c:when test='${not empty rs_motivos}'>
											<c:forEach items='${rs_motivos}' var='pa'>
												<option value=<c:out value='${pa.codigo_motivo}'/>>
													<c:out value='${pa.descripcion_motivo}' />
												</option>
											</c:forEach>
										</c:when>
									</c:choose>
								</select>
							</c:when>
							<c:when test='${operacion ==  "editar"}'>
								<select id="codigo_motivo" name="codigo_motivo" class="input"
									style="WIDTH: 270px;">
									<OPTION value="0" selected>-- Selecc Uno --</OPTION>
									<c:choose>
										<c:when test='${not empty rs_motivos}'>
											<c:forEach items='${rs_motivos}' var='pa'>
												<option value=<c:out value='${pa.codigo_motivo}'/>>
													<c:out value='${pa.descripcion_motivo}' />
												</option>
											</c:forEach>
										</c:when>
									</c:choose>
								</select>
							</c:when>
							<c:otherwise>

								<select id="codigo_motivo" name="codigo_motivo" class="input"
									style="WIDTH: 270px;">
									<OPTION value="0" selected>-- Selecc Uno --</OPTION>
									<c:choose>
										<c:when test='${not empty rs_motivos}'>
											<c:forEach items='${rs_motivos}' var='pa'>
												<option value=<c:out value='${pa.codigo_motivo}'/>>
													<c:out value='${pa.descripcion_motivo}' />
												</option>
											</c:forEach>
										</c:when>
									</c:choose>
								</select>
							</c:otherwise>
						</c:choose>
					</p>



					<c:choose>
						<c:when test='${modo_observacion == "1" }'>

						</c:when>
						<c:otherwise>
							<p>
								<label><c:choose>
										<c:when test='${pertenece_a ==  "2"}'>Con Copia CONCYTEC</c:when>
										<c:otherwise>Con Copia FONDECYT</c:otherwise>
									</c:choose></label> <input id="chk_copia" checked="checked" class="caja"
									onClick="javascript:HabilitaCombo(1);" type="checkbox"
									name="chk_copia">
							</p>
							<div id="scroll_derivar" class="input"
								style="WIDTH: 370px; height: 100px;">
								<table>
									<c:forEach items='${rs_oficina}' var='pa'>
										<tr>
											<td><input class="caja" type="checkbox" name="cbo_copia"
												id="cbo_copia" value="<c:out value='${pa.codigo_oficina}'/>"></td>
											<td class="texto_small"><c:out
													value='${pa.siglas_oficina}' /> - <c:out
													value='${pa.descripcion_oficina}' /></td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<br>
							<p>
							<div style="display: none" id="lista_personal">
								<label>Enviar al Personal</label> <input id="chk_copia_other"
									class="caja" onClick="javascript:HabilitaCombo(0);"
									type="checkbox" name="chk_copia_other">
								</p>

								<div id="scroll_2" class="input"
									style="WIDTH: 370px; height: 100px; visibility:hidden">
									<table>

										<div id="lista_personal2"></div>
									</table>
								</div>
							</div>

						</c:otherwise>
					</c:choose>



					<SCRIPT type=text/javascript>
						Calendar.setup({
							inputField : "fecha", // id del campo de texto
							ifFormat : "%d/%m/%Y", // formato de la fecha, cuando se escriba en el campo de texto
							button : "lanzador"
						// el id del bot�n que lanzar� el calendario
						});

						Calendar.setup({
							inputField : "fecha_rpta", // id del campo de texto
							ifFormat : "%d/%m/%Y", // formato de la fecha, cuando se escriba en el campo de texto
							button : "lanzador1"
						// el id del bot�n que lanzar� el calendario
						});

						Calendar.setup({
							inputField : "fecha_rpta_rq", // id del campo de texto
							ifFormat : "%d/%m/%Y", // formato de la fecha, cuando se escriba en el campo de texto
							button : "lanzador2"
						// el id del bot�n que lanzar� el calendario
						});

						inicio();
						MostrarAdjunto();
						function inicio() {

							with (document.forms[0]) {
								fecha_rpta.disabled = "true";
								//scroll_chks.style.visibility = 'hidden'; 
								//document.form_datos.fecha_rpta.disabled = "true"; 
							}

						};

						<c:choose>
						<c:when test='${codigocontador ==  "1"}'>
						<c:choose>
						<c:when test='${modo_observacion ==  "1"}'>
						SeleccionarCampo("personas",
								"<c:out value='${personas}'/>");
						SeleccionarCampo("codigo_personal",
								"<c:out value='${ls_codigo_persona_que_observa}'/>");
						SeleccionarCampo("codigo_oficina",
								"<c:out value='${ls_codigo_oficina_que_observa}'/>");
						SeleccionarCampo("codigo_motivo",
								"<c:out value='${ls_codigo_motivo}'/>");

						SetearCampo("<c:out value='${ls_oficina_destino}'/>",
								"<c:out value='${ls_persona_destino}'/>");
						</c:when>
						<c:otherwise>
						SeleccionarCampo("personas",
								"<c:out value='${personas}'/>");
						SeleccionarCampo("codigo_oficina",
								"<c:out value='${codigo_oficina}'/>");
						SeleccionarCampo("codigo_motivo",
								"<c:out value='${codigo_motivo}'/>");

						</c:otherwise>
						</c:choose>
						</c:when>
						<c:when test='${modo_observacion ==  "1"}'>
						SeleccionarCampo("personas",
								"<c:out value='${personas}'/>");
						SeleccionarCampo("codigo_personal",
								"<c:out value='${ls_codigo_persona_que_observa}'/>");
						SeleccionarCampo("codigo_oficina",
								"<c:out value='${ls_codigo_oficina_que_observa}'/>");
						SeleccionarCampo("codigo_motivo",
								"<c:out value='${ls_codigo_motivo}'/>");
						SetearCampo("<c:out value='${ls_oficina_destino}'/>",
								"<c:out value='${ls_persona_destino}'/>");
						//MostrarAdjunto();
						</c:when>
						<c:otherwise>
						SeleccionarCampo("codigo_intitucion",
								"<c:out value='${ls_codigo_institucion}'/>");
						</c:otherwise>
						</c:choose>
					</SCRIPT>

				</div>
				<div class="center-big">
					<c:choose>
						<c:when test='${modo_observacion == "1" }'>
							<html:button property="btnEnviar" styleId="btnEnviar"
								value="Derivar" styleClass="button"
								onclick="this.disabled=true; this.value='DERIVANDO...'; fn_enviar_obs()" />
							<html:button property="btnCancelar" styleId="btnCancelar"
								value="Cancelar" onclick="cancel_obs();" style="width: 90;"
								styleClass="button" />
							<!-- table>
				<tr>
					<td>
					<html:button property="btnEnviar" styleId="btnEnviar" value="Derivar" onclick="fn_enviar_obs();"
							style=" width: 90;" styleClass="boton"/></td>
					<td>
						<html:button property="btnCancelar" styleId="btnCancelar" value="Cancelar" onclick="cancel_obs();" style="width: 90;" styleClass="boton"/>											
					</td>
					
				</tr>
			 </table-->
						</c:when>
						<c:otherwise>
							<html:button property="btnEnviar" styleId="btnEnviar"
								value="Derivar" styleClass="button"
								onclick="this.disabled=true; this.value='DERIVANDO...'; fn_enviar()" />
							<html:button property="btnCancelar" styleId="btnCancelar"
								value="Cancelar" onclick="cancel();" style="width: 90;"
								styleClass="button" />
							<html:button property="btnGrabar" styleId="btnGrabar"
								value="Grabar" onclick="fn_grabar();"
								style=" display: none;  width: 90;" styleClass="button" />
							<!-- table>
						<tr>
							<td>
							<html:button property="btnEnviar" styleId="btnEnviar" value="Derivar" onclick="fn_enviar();"
									style=" width: 90;" styleClass="button"/></td>
							<td>
								<html:button property="btnCancelar" styleId="btnCancelar" value="Cancelar" onclick="cancel();" style="width: 90;" styleClass="button"/></td>
							<td>
								<html:button property="btnGrabar" styleId="btnGrabar" value="Grabar" onclick="fn_grabar();" style=" display: none;  width: 90;" styleClass="button"/></td>
						</tr>
					</table-->
						</c:otherwise>
					</c:choose>
				</div>
				<br>
			</div>


			<div id="break"></div>
			<div class="derivalista">
				<div id="tabs">
					<ul>
						<li><a href="#tabs-1">Documentos Pendientes por Derivar</a></li>
						<!-- li><a href="#tabs-2">Documentos Observados</a></li-->

					</ul>
					<div id="tabs-1">
						<table width="100%" cellpadding="0" cellspacing="0">
							<tr>
								<td height="35" align="center" colspan="2" valign="top">
									<table width="100%" cellpadding="0" cellspacing="0"
										background="img/fondoplomo8.jpg">
										<!--DWLayoutTable-->
										<tr>
											<td width="33%" height="30" valign="top"
												class="textomensaje10bold"></td>
											<td width="33%" align="center" valign="middle"
												background="img/fondoplomo8.jpg" class="label">Documentos
												Pendientes por Derivar</td>
											<td width="33%"></td>
										</tr>
									</table>
								</td>
							</tr>

							<tr>
								<td height="13" colspan="2" valign="top" align="center"><display:table
										name="sessionScope.rs_derivacion_doc" export="false"
										sort="list" id="b" pagesize="10" class="simple"
										style="width:100%">
										<display:column property="codigo_documento" media="html"
											title="N&deg; Registro" />
										<display:column property="codigo_expediente" media="html"
											title="N&deg; Expediente" />
										<display:column property="fecha_movimiento" media="html"
											title="Fecha Registro" />
										<display:column property="numero_documento" media="html"
											title="N&deg;  Documento" />
										<display:column property="oficina_origen" media="html"
											title="Oficina Origen" />
										<display:column property="estado_movimiento" title="Estado" />
										<display:column property="view_archivo" align="center"
											title="Ver Documento" />
										<c:if test="${modo_tramite eq '1'}">
											<display:column property="documento_respuesta" align="center"
												title="Operacion" />
										</c:if>
										<!-- display:column property="observar" align="center" title="Observar" /-->
									</display:table></td>
							</tr>
						</table>
					</div>
					<!--  
  <div id="tabs-2">
    <table width="100%"  cellpadding="0" cellspacing="0" >
						  <tr>
								<td height="35" align="center" colspan="2" valign="top">
							<table width="100%"  cellpadding="0" cellspacing="0" background="img/fondoplomo8.jpg">
							  
							<tr>
							<td width="33%" height="30" valign="top"  class="textomensaje10bold">				</td>
						    <td width="33%" align="center" valign="middle" background="img/fondoplomo8.jpg" class="label">Documentos Observados Pendientes por Derivar					</td>
						    <td width="33%" >
							</td>
						  </tr>
							</table>							</td>
						  </tr>
						
							<tr>
								<td height="13" colspan="2" valign="top" align="center" >
							<display:table name="sessionScope.rs_observados_doc" export="false" sort="list" id="g" pagesize="10" class="simple" style="width:100%">
										
										<display:column property="codigo_documento" media="html" title="N&deg; Registro" />
										<display:column property="codigo_expediente" media="html" title="N&deg; Expediente" />
										<display:column property="fecha_movimiento" media="html" title="Fecha Registro" />
										<display:column property="numero_documento" media="html" title="N&deg;  Documento" />
										<display:column property="oficina_origen" media="html"  title="Oficina Origen" />
										<display:column property="estado_movimiento" title="Estado" />
										<display:column property="view_archivo" align="center" title="Ver Documento" />
										<display:column property="documento_respuesta" align="center" title="Operacion" />
										
								</display:table>								</td>
							</tr>
		</table>
  </div>-->

				</div>

			</div>
			<div id="break"></div>

		</html:form>


	</div>
	<!-- jsp:include page="/inc_footer.jsp"/-->
</body>
</html>
<%
	}
%>
