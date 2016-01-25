function esEntero(numero){
  tokens = StringTokenizer(numero, '.');
  if ( tokens.length>1 ){
    return false
  }
  return true
}

function validaDecimal(numero, dec){
  tokens = StringTokenizer(numero, '.');
  if ( tokens.length>1 ){
    if ( !(tokens[tokens.length-1].length > 0 && tokens[tokens.length-1].length <= dec) ){
      return false
    }
  }
  return true
}

function esdecimal(valor){
  var pattern = "/\\b(^(\\d+)(\\.\\d+)$)\\b/gi";
	var rpt = valor.match(eval(pattern));
	if (!rpt) {
 		return false
	}	else {
 		return true
	}
}

function validacorreo(myString) {
	var newString = myString.match(/\b(^(\S+@).+((\.gob)|(\.com)|(\.net)|(\.edu)|(\.mil)|(\.gov)|(\.org)|(\..{2,2}))$)\b/gi)
	if (!newString) {
 		return false
	}	else {
 		return true
	}
}
/* deprecated
function validacorreo(buzon){
  tokens = StringTokenizer(buzon, '@')
  if ( tokens.length != 2 )
    return false
  else {
    if ( tokens[1].length == 0 ){
      return false
    } else {
      return true
    }
  }
}
*/
/*
  Recibe el nombre del archivo y la extension a validar
  Retorna un True o False dependiendo si el nombre el valido o No
*/
function validanombrearchivo(nombre, ext){
  var pattern = "/\\b(^(((\\S)|(\\s))+)(\\."+ext+")$)\\b/gi";
	var rpt = nombre.match(eval(pattern));
	if (!rpt) {
 		return false
	}	else {
 		return true
	}
}
/* deprecated
function validanombrearchivo(nombre, ext){
  tokens = StringTokenizer(nombre, '.');
  if ( tokens.length>1 ){
    if ( tokens[tokens.length-1].toLowerCase() != ext ){
      return false
    }
  } else {
    return false
  }
  return true
}
*/
/*
  Recibe una cadena y la separa basado en el caracter de la variable delim.
  Retorna un Array de cadenas
*/
function StringTokenizer(cad, delim){
  var cads = new Array();
  var n = cad.length;
  var j = 0;
  var ic = 0;
  for (i=0;i<n;i++){
    if ( cad.charAt(i)==delim ){
      cads[j] = cad.substring(ic, i);
      ic = i+1;
      j++;
    }
  }
  cads[j] = cad.substring(ic, n);
  return cads;
}
/*
  Retorna el mes en Letras
  Donde mes es el numero del mes en el rango 1 hasta al 12
        may 1 o 0 para MAYUSCULAS o minusculas
        cap 1 o 0 para poner en mayusculas la primera letra o no
*/
function mesenletras(mes, may, cap){
  var imes = parseInt(mes, 10);
  var tmes = "-";
  if ( imes == 1 ){
    tmes = "enero";
  } else if ( imes == 2 ){
    tmes = "febrero";
  } else if ( imes == 3 ){
    tmes = "marzo";
  } else if ( imes == 4 ){
    tmes = "abril";
  } else if ( imes == 5 ){
    tmes = "mayo";
  } else if ( imes == 6 ){
    tmes = "junio";
  } else if ( imes == 7 ){
    tmes = "julio";
  } else if ( imes == 8 ){
    tmes = "agosto";
  } else if ( imes == 9 ){
    tmes = "setiembre";
  } else if ( imes == 10 ){
    tmes = "octubre";
  } else if ( imes == 11 ){
    tmes = "noviembre";
  } else if ( imes == 12 ){
    tmes = "diciembre";
  }

  if ( may == 1){
    tmes = tmes.toUpperCase();
  }

  if ( cap == 1){
    tmes = tmes.substring(0,1).toUpperCase() + tmes.substring(1, tmes.length);
  }

  return tmes;
}

/*
  Retorna
   -1: en caso de errores de formatos
    1: en caso de que fecha1 sea mayor a la fecha2
    2: en caso de que fecha1 sea menor a la fecha2
    0: en caso de que fecha1 sea igual a la fecha2
*/
function comparafecha(fecha1, fecha2){
  if ( !checkdate(fecha1) || !checkdate(fecha2) ){
    return -1
  }
  dia = fecha1.substring(0,2) //dia
  mes = fecha1.substring(3,5) //mes
  anho = fecha1.substring(6,10) //anho
  fecha1x = anho + mes + dia

  dia = fecha2.substring(0,2) //dia
  mes = fecha2.substring(3,5) //mes
  anho = fecha2.substring(6,10) //anho
  fecha2x = anho + mes + dia
  
  if ( fecha1x > fecha2x ){
    return 1
  } else if ( fecha1x < fecha2x ){
    return 2
  } else {
    return 0
  }
}

function nada(){
}

function corta(campo, longitud, cars) {
  if (campo.value.length>longitud)
   campo.value=campo.value.substring(0,longitud);
  cuenta(campo, cars);
}

function cuenta(campo, cars) {
  cars.value=campo.value.length;
}

/*
*/
function rellena(dato, caracter, tamanho){
  dato_trim = trim(dato)
  len = dato_trim.length
  dato_fill = ""
  for (var i=0;i<tamanho-len;i++){
    dato_fill = dato_fill + caracter;
  }
  dato_fill = dato_fill + dato_trim;
  return dato_fill;
}

/*
-------------------------------
Validacion de fecha
-------------------------------
-------------------------------
*/

function checkdate(fecha){
  var err=0
  if ( fecha.length != 10) err=1
  dia = fecha.substring(0,2) //dia
  slash1 = fecha.substring(2,3) // '/'
  mes = fecha.substring(3,5) //mes
  slash2 = fecha.substring(5,6) // '/'
  anho = fecha.substring(6,10) //anho

  // chequeo de errores basicos
  if ( dia<1 || dia>31) err = 1
  if ( slash1 != '/' ) err = 1
  if ( mes<1 || mes>12) err = 1
  if ( slash1 == '/' && slash2 != '/' ) err = 1
  if ( anho < 0 || anho > 2200 ) err = 1

  // verificacion avanzada
  // meses con 30 dias
  if ( mes == 4 || mes == 6 || mes == 9 || mes == 11 ){
    if (dia==31) err=1
  }
  // febrero
  if (mes == 2){
    var g = parseInt(anho/4)
    if (isNaN(g)){
      err = 1
    }
    if (dia >29) err =1
    if (dia ==29 && ((anho/4)!=parseInt(anho/4))) err=1
  }
  if (err==1){
    return false
  } else {
    return true
  }
}

function esnulo(campo){
  if ( campo == null ){
    return true
  } else {
    return false
  }
}

function esnulooguion(campo){
  if ( esnulo( campo ) ){
    return true
  } else {
    if ( trim( campo ) == "-" ) {
	  return true
	} else {
      return false
	}
  }
}

function esnumero(campo){
  if ( isNaN( campo ) ){
    return false
  } else {
    return true
  }
}

function longitudcorrecta( campo, len ){
  if ( campo != null ){
    if ( campo.length == len ){
      return true
    } else {
      return false
    }
  } else {
    return false
  } 
}

function mayuscula(campo){
  return campo.toUpperCase()
}
function minuscula(campo){
  return campo.toLowerCase()
}
function eslongrucok(ruc){
/*
Cambio el 04/01/2000 por modificacion de longitud del ruc
if ( ruc.length == 8 || ruc.length == 11 ){
*/
  if ( ruc.length == 11 ){
    return true
  } else {
    return false
  }
}
function eslongcontrasenhaok(contrasenha){
  if ( contrasenha.length < longcontrasenhaok() ){
    return false
  } else {
    return true
  }
}
function longcontrasenhaok(){
  return 6
}
function esnegativo(valor){
  if (valor < 0){
    return true
  } else {
    return false
  }
}
function esrucok(ruc){
  if ( esnulo(ruc) || !esnumero(ruc) || !eslongrucok(ruc) || !valruc(ruc) ){
    return false
  } else {
    return true
  }
}
function valruc(valor){
  valor = trim(valor)
  if ( esnumero( valor ) ) {
    if ( valor.length == 8 ){
      suma = 0
      for (i=0; i<valor.length-1;i++){
        digito = valor.charAt(i) - '0';
        if ( i==0 ) suma += (digito*2)
        else suma += (digito*(valor.length-i))
      }
      resto = suma % 11;
      if ( resto == 1) resto = 11;
      if ( resto + ( valor.charAt( valor.length-1 ) - '0' ) == 11 ){
        return true
      }
    } else if ( valor.length == 11 ){
      suma = 0
      x = 6
      for (i=0; i<valor.length-1;i++){
        if ( i == 4 ) x = 8
        digito = valor.charAt(i) - '0';
        x--
        if ( i==0 ) suma += (digito*x)
        else suma += (digito*x)
      }
      resto = suma % 11;
      resto = 11 - resto
      
      if ( resto >= 10) resto = resto - 10;
      if ( resto == valor.charAt( valor.length-1 ) - '0' ){
        return true
      }      
    }
  }
  return false
}

function longitudmayor( campo, len ){
  if ( campo != null ){
    if ( campo.length > len ){
      return true
    } else {
      return false
    }
  } else {
    return false
  } 
}

function estaentre(campo, inicio, fin){
  if ( campo != null ){
    if ( campo.length >= inicio && campo.length <= fin ){
      return true
    } else {
      return false
    }
  } else {
    return false
  }
}

var sorry="TECHERA - CHRISTIAN© 2008"
function click(e){
   if (document.all){
      if (event.button == 2){
         alert(sorry);
         return false;
      }
   }
   if (document.layers){
      if (e.which == 3){
         alert(sorry);
         return false;
      }
   }
}
if (document.layers){
   document.captureEvents(Event.MOUSEDOWN);
}
document.onmousedown=click;
function abreventana(i, j) {
  window.open(i, j, "toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=yes,copyhistory=0,width=600,height=450")
}
/*
impresion 
*/
var da = (document.all) ? 1 : 0;
var pr = (window.print) ? 1 : 0;
var mac = (navigator.userAgent.indexOf("Mac") != -1); 

function printPage(frame, arg) {
  if (frame == window) {
    printThis();
  } else {
    link = arg; // variable global 
    printFrame(frame);
  }
  return false;
}

function printThis() {
  if (pr) { // NS4, IE5
    window.print();
  } else if (da && !mac) { // IE4 (Windows)
    vbPrintPage();
  } else { // other browsers
    alert("Disculpe, su browser no soporta esta aplicacion.");
  }
}

function trim(cadena){
  cadena2 = "";
  len = cadena.length;
  for ( var i=0; i <= len ; i++ ){
  	if ( cadena.charAt(i) != " " ){
	  cadena2 = cadena2 + cadena.charAt(i);
	}
  }
  return cadena2;
}

function printFrame(frame) {
  if (pr && da) { // IE5
    frame.focus();
    window.print();
    link.focus();
  } else if (pr) { // NS4
    frame.print();
  } else if (da && !mac) { // IE4 (Windows)
    frame.focus();
    setTimeout("vbPrintPage(); link.focus();", 100);
  } else { // other browsers
    alert("Disculpe, su browser no soporta esta aplicaci&oacute;n.");
  }
}

if (da && !pr && !mac) with (document) {
  writeln('<OBJECT ID="WB" WIDTH="0" HEIGHT="0" CLASSID="clsid:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>');
  writeln('<' + 'SCRIPT LANGUAGE="VBScript">');
  writeln('Sub window_onunload');
  writeln('  On Error Resume Next');
  writeln('  Set WB = nothing');
  writeln('End Sub');
  writeln('Sub vbPrintPage');
  writeln('  OLECMDID_PRINT = 6');
  writeln('  OLECMDEXECOPT_DONTPROMPTUSER = 2');
  writeln('  OLECMDEXECOPT_PROMPTUSER = 1');
  writeln('  On Error Resume Next');
  writeln('  WB.ExecWB OLECMDID_PRINT, OLECMDEXECOPT_DONTPROMPTUSER');
  writeln('End Sub');
  writeln('<' + '/SCRIPT>');
}
/*
*/

