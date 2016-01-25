//window.name='MainPage'
var popUpWin = 0;
function popUpWindow(URLStr, left, top, width, height) {
	if (popUpWin) {
		if (!popUpWin.closed) {
			popUpWin.close();
		}
	}
	popUpWin = open(URLStr, "popUpWin", "toolbar=no,location=no,directories=no,status=no,menub ar=no,scrollbar=no,resizable=no,copyhistory=yes,width=" + width + ",height=" + height + ",left=" + left + ", top=" + top + ",screenX=" + left + ",screenY=" + top + "");
	return popUpWin;
}
function openWindow(winName, features) {
	return window.open("", winName, features);
}
function trim(sCad) {
		// Quita espacios en blanco a la izquierda
	if (sCad.length == 1 && sCad == " ") {
		sCad = "";
	} else {
		while (sCad.substring(0, 1) == " ") {
			sCad = sCad.substring(1, sCad.length);
		}
			// Quita espacios en blanco a la derecha
		while (sCad.substring(sCad.length - 1, sCad.length) == " ") {
			sCad = sCad.substring(0, sCad.length - 1);
		}
	}
	return sCad;
}



function validatecla(tipo, textbox) {
	var tecla;
	if (navigator.appName.indexOf("Netscape") != -1) {
		tecla = event.which;
	} else {
		tecla = event.keyCode;
	}
	var key = String.fromCharCode(tecla);
	var numeros = "0123456789";
	var letras = "AaBbCcDdEeFfGgHhIiJjKkLlMm\xd1\xf1NnOoPpQqRrSsTtUuVvWwXxYyZz ";
	var abreviaturas = "AaBbCcDdEeFfGgHhIiJjKkLlMm\xd1\xf1NnOoPpQqRrSsTtUuVvWwXxYyZ.z";
	
	
	var texto = letras + ",;._-";
	var codigo = letras + numeros + "-_#.@º";
	var alfanum = letras + numeros;
	var textual = codigo;//+"()[]$!ªº";
	//var textual = alfanum+ "-_#.@()[]&$!ªº";
	/*alert("tecla : " + tecla);
		alert("key : " + key);*/
	if (tipo == "textual") {
		if (textual.indexOf(key) != -1) {
			return true;
		} else {
			return false;
		}
	}
	if (tipo == "letras") {
		if (tecla == 32) {
			return true;
		}
		if (letras.indexOf(key) != -1) {
			return true;
		}
		return false;
	}
	if (tipo == "abreviaturas") {
		if (tecla == 32) {
			return true;
		}
		if (abreviaturas.indexOf(key) != -1) {
			return true;
		}
		return false;
	}
	if (tipo == "texto") {
		if (tecla == 32) {
			return true;
		}
		if (texto.indexOf(key) != -1) {
			return true;
		}
		return false;
	}
	
	if (tipo == "enteros") {
		if (numeros.indexOf(key) != -1) {
			return true;
		} else {
			return false;
		}
	}
	if (tipo == "alfanumerico") {
		if (alfanum.indexOf(key) != -1) {
			return true;
		} else {
			return false;
		}
	}
	if (tipo == "codigo") {
		if (codigo.indexOf(key) != -1) {
			return true;
		} else {
			return false;
		}
	}
	if (tipo == "decimales") {
		if (numeros.indexOf(key) != -1) {
			return true;
		}
		var cadena = textbox.value;
			//if (cadena.indexOf(',') != -1 )
		if (cadena.indexOf(".") != -1) {
			return false;
		} 
			//if (tecla == 44) // ","
		if (tecla == 46) { // "."
		}
		return true;
		return false;
	}
	if (tipo == "NoNumeros") {
		if (tecla == 32) {
			return true;
		}
		if (numeros.indexOf(key) != -1) {
			return false;
		}
		return true;
	}
}
function checkMail(cadena) {
	var plant = /[^\w^@^\.^-]+/gi;
	if (plant.test(cadena)) {
	     	//alert(cadena + " contiene caracteres extra?os.")
		return false;
	} else {
		plant = /(^\w+)(@{1})([\w\.-]+$)/i;
		if (plant.test(cadena)) {
	        	 //alert(cadena + " es correcta.") 
			return true;
		} else {
	        	 //alert(cadena + " no es v?lida.") 
			return false;
		}
	}
}


function validaRUC(cadena) {
	var pos = new Array(11);
	var valor = 0;
	var valorB = 0;
	var verificaRuc = true;
	cadena = trim(cadena);
	for (i = 0; i < 11; i++) {
		pos[i] = eval(cadena.charAt(i));
	}
	valor = pos[0] * 5 + pos[1] * 4 + pos[2] * 3 + pos[3] * 2 + pos[4] * 7 + pos[5] * 6 + pos[6] * 5 + pos[7] * 4 + pos[8] * 3 + pos[9] * 2;
	valorB = valor / 11;
	valorB = parseInt(valorB);
	valor = valor - (valorB * 11);
	valor = 11 - valor;
	if (valor == 10) {
		valor = 0;
	} else {
		if (valor == 11) {
			valor = 1;
		}
	}
	if (pos[10] != valor) {
		verificaRuc = false;
	}
	return verificaRuc;
}
function checkLength(caja, length) {
	if (caja.value.length > length) {
		caja.value = caja.value.substring(0, length);
	}
}
function isNumeric(x) {
		// Expresion Regular que valida si es un valor numerico			
	var RegExp = /^[-+]?\d*\.?\d+(?:[eE][-+]?\d+)?$/;
			
		// compare the argument to the RegEx
		// the 'match' function returns 0 if the value didn't match 
	var result = x.match(RegExp);
	return result;
}

function cambiar_color_tabla(tableId, rowClassName) {
	var previousClass = null;
	var table = document.getElementById(tableId);
	if (table != null) {
		var rows = table.getElementsByTagName("tr");
		for (i = 1; i < rows.length; i++) {
			rows[i].onmouseover = function () {
				previousClass = this.className;
				this.className = this.className + " " + rowClassName;
				
			};
			rows[i].onmouseout = function () {
				this.className = previousClass;
				
			};
		}
	}
}

function cambiar_color_over(celda) {
	celda.style.backgroundColor = "#f7f7f7";
	celda.style.bordercolor = "#e7e7e7";
}

function cambiar_color_out(celda) {
	celda.style.backgroundColor = "#ffffff";
	celda.style.bordercolor = "#e7e7e7";
}

function ponerEnMayuscula(caja){
	if(caja.type == 'text'){
		caja.value = caja.value.toUpperCase();
	}
}

function numeroFormateado(numero, numDecimales){
	//alert("" + numero  + ", " + numDecimales);
	return numero.toFixed(numDecimales);
}
