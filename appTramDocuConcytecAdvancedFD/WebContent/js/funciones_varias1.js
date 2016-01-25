/*
Funciones del Archivo
----------------------
1. f_obtener_fechaactual
2. Cambia_Dependencia
3. chkNumeric
4. Delito
5. Limpiarlista
6. Validarcampovacio
7. DeshabilitarCampo
8. CargarDelito
9. ValidarCampoVacio
10. ValidarCaracterFecha
11. Cambia_Dependencia_AsistenciaLegal
12. AgregarObjetivo
13. CerrarPagina
14. CompararFechas
15. MaximizarVentana
*/

function MaximizarVentana(){  
  window.resizeTo(screen.availWidth, screen.availHeight); 
  window.moveTo(0,0);   
}  

function CerrarPagina(){
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

function f_obtener_fechaactual(){
  now = new Date();
  dia = now.getDay()
  if (dia==0){dia="Domingo"};
  if (dia==1){dia="Lunes"};
  if (dia==2){dia="Martes"};
  if (dia==3){dia="Miercoles"};
  if (dia==4){dia="Jueves"};
  if (dia==5){dia="Viernes"};
  if (dia==6){dia="Sabado"};

  
  fecha = now.getDate();
  mes = now.getMonth();
  
  if (mes==0){mes="Enero"};
  if (mes==1){mes="Febrero"};
  if (mes==2){mes="Marzo"};
  if (mes==3){mes="Abril"};
  if (mes==4){mes="Mayo"};
  if (mes==5){mes="Junio"};
  if (mes==6){mes="Julio"};
  if (mes==7){mes="Agosto"};
  if (mes==8){mes="Septiembre"};
  if (mes==9){mes="Octubre"};
  if (mes==10){mes="Noviembre"};
  if (mes==11){mes="Diciembre"};
 
  annio = now.getYear();
  document.write(dia + " " + fecha + " de " + mes + " del " + annio)
}

/************************************/

function Cambia_Dependencia()
{
  var ls_dependencia;
  ls_dependencia = document.forms[0].sel_distrito[document.forms[0].sel_distrito.selectedIndex].value
      
  if (ls_dependencia != 0)
  {
    mis_dependeneciascodigo = eval("codigo_" + ls_dependencia)
    mis_dependenecias = eval("dependencia_" + ls_dependencia)
    num_dependencias = mis_dependenecias.length
        
    document.forms[0].sel_dependencia.length = num_dependencias
    for (i=0;i<num_dependencias;i++)
    {
      document.forms[0].sel_dependencia.options[i].value = mis_dependeneciascodigo[i]
      document.forms[0].sel_dependencia.options[i].text = mis_dependenecias[i]
    }     
  } 
  else
  {
    document.forms[0].sel_dependencia.length = 1
    document.forms[0].sel_dependencia.options[0].value = "-"
    document.forms[0].sel_dependencia.options[0].text = "-"
   }
   document.forms[0].sel_dependencia.options[0].selected = true
} 

/************************************/

function chkNumeric(pFld,pSize,pDec,pSig) {
				var wKey = window.event.keyCode;
				var wFld = pFld.value;
				var wSize = wFld.length;
				if( wSize<pSize ) {
					if( wKey>47 && wKey<58 ) return( false );
					if( wKey==46 && pDec==true && wFld.indexOf(".")<0 ) return( false );
					if( wKey==45 && pSig==true && wFld.indexOf("-")<0 ) return( false );
				}			
				window.event.returnValue = null;
				return( true );
}   

    /******************************************/

    function Limpiarlista(OptionList, TitleName) 
    {
        OptionList.length = 0;
    }

    /******************************************/

    function Delito(side)
    {   
     	var temp1 = new Array();
     	var temp2 = new Array();
    	var tempa = new Array();
     	var tempb = new Array();
      var class1 = new Array();
      var class2 = new Array();
      
 	    var current1 = 0;
 	    var current2 = 0;
 	    var y=0;
 	    var attribute;
	    	
      //Asigna select que se convierte como atributo
         	      
 	    if (side == "right")
 	    {  
 	    	attribute1 = document.forms[0].cmb_delito; 
 	    	attribute2 = document.forms[0].cmb_seldelito;
    	}
 	    else
 	    {  
 	    	attribute1 = document.forms[0].cmb_seldelito;
 	    	attribute2 = document.forms[0].cmb_delito;  
    	}

 	//Llena un arreglo con antiguos valores      
 	for (var i = 0; i < attribute2.length; i++)
  {  
 		y=current1++
 		temp1[y] = attribute2.options[i].value;
 		tempa[y] = attribute2.options[i].text;          
    class1[y] = attribute2.options[i].className;
 	}

 	//Asignando nuevos valores al arreglo
        
 	for (var i = 0; i < attribute1.length; i++)
 	{
     if ( attribute1.options[i].selected )
     {  
    	y=current1++
		  temp1[y] = attribute1.options[i].value;
    	tempa[y] = attribute1.options[i].text;      
      class1[y] = attribute1.options[i].className;
		  }
    	else
		  {  
    	y=current2++
		  temp2[y] = attribute1.options[i].value; 
    	tempb[y] = attribute1.options[i].text;
      class2[y] = attribute1.options[i].className;
		   }
   }

 	//Generando nuevas opciones
      
  for (var i = 0; i < temp1.length; i++)
  {  
    attribute2.options[i] = new Option();
    attribute2.options[i].value = temp1[i];
		attribute2.options[i].text =  tempa[i];
    attribute2.options[i].className = class1[i];
  }

  //Generando nuevas opciones      
  Limpiarlista(attribute1,attribute1);
  
  if (temp2.length>0)
  {	
    for (var i = 0; i < temp2.length; i++)
		{   
    attribute1.options[i] = new Option();
		attribute1.options[i].value = temp2[i];
    attribute1.options[i].text =  tempb[i];
    attribute1.options[i].className = class2[i];
		}
  }
}


function Validarcampovacio(ls_campovacio)
{
  for ( ls_indicex = 0; ls_indicex < ls_campovacio.length; ls_indicex++ ) {
    if ( ls_campovacio.charAt(ls_indicex) != " " ) {
      return true
    }
  }
  return false
}

//function CargarDelito(delito){
//var delitos = document.forms[0].cmb_delito;
//for (var i = 0; i < delitos.length; i++)
//{  
//  if (delitos.options[i].value == delito){
//    delitos.options[i].selected = true;
//    Delito('right');
//  }
// }      
//} 


function Validarcampovacio(ls_campovacio)
{
    for ( ls_indicex = 0; ls_indicex < ls_campovacio.length; ls_indicex++ ) {
        if ( ls_campovacio.charAt(ls_indicex) != " " ) {
          return true
        }
    }
    return false
}

function Cambia_Dependencia_AsistenciaLegal()
{
  var ls_dependencia;
  ls_dependencia = document.forms[0].sel_distrito_exp[document.forms[0].sel_distrito_exp.selectedIndex].value
      
  if (ls_dependencia != 0)
  {
    mis_dependeneciascodigo = eval("codigoo_" + ls_dependencia)
    mis_dependenecias = eval("dependenciaa_" + ls_dependencia)
    num_dependencias = mis_dependenecias.length
        
    document.forms[0].sel_dependencia_exp.length = num_dependencias
    for (i=0;i<num_dependencias;i++)
    {
      document.forms[0].sel_dependencia_exp.options[i].value = mis_dependeneciascodigo[i]
      document.forms[0].sel_dependencia_exp.options[i].text = mis_dependenecias[i]
    }     
  } 
  else
  {
    document.forms[0].sel_dependencia_exp.length = 1
    document.forms[0].sel_dependencia_exp.options[0].value = "-"
    document.forms[0].sel_dependencia_exp.options[0].text = "-"
  }
  document.forms[0].sel_dependencia_exp.options[0].selected = true
} 

function AgregarObjetivo(secuencia,codobj,dscobj)
{
  var ls_secuencia = secuencia;
 	var ls_codobj = codobj;
 	var ls_dscobj = dscobj;
 
  parent.frm_asistencialegal.sel_sobj.value = ls_codobj;
  parent.frm_asistencialegal.hd_sel_sobj.value = ls_dscobj;
  parent.frm_asistencialegal.hd_sec_sobj.value = ls_secuencia;
 
  parent.document.frames[1].frm_actuacion.sel_objetivo.value = ls_codobj;
  parent.document.frames[1].frm_actuacion.hd_sel_objetivo.value = ls_dscobj;
  parent.document.frames[1].frm_actuacion.hd_sec_obj.value = ls_secuencia;
}

function y2k(number) { return (number < 1000) ? number + 1900 : number; }

function isValidDate (myDate,sep) {
// checks if date passed is in valid dd/mm/yyyy format
    var hoy = new Date();    
    
    if (myDate.length == 10) {
        
        if (myDate.substring(2,3) == sep && myDate.substring(5,6) == sep) {
            var date  = myDate.substring(0,2);
            var month = myDate.substring(3,5);
            var year  = myDate.substring(6,10);

            var test = new Date(year,month-1,date);
            //alert(test);
            
            //if ( parseInt(year) > parseInt(hoy.getYear()) ){
            if ( test > hoy ){
              //alert("El año no puede mayor al actual");
              return false;
            }

            if (year == y2k(test.getYear()) && (month-1 == test.getMonth()) && (date == test.getDate())) {
                reason = '';
                //alert("perfecto");
                return true;
            }
            else {
                //alert("valid format but an invalid date");
                return false;
            }
        }
        else {
            //alert("invalid spearators");
            return false;
        }
    }
    else {
        //alert("invalid length");
        return false;
    }
}


function CompararFechas(date1, date2){
    var fecha_menor;
    var fecha_mayor;
    
    var day  = date1.substring(0,2);
    var month = date1.substring(3,5);
    var year  = date1.substring(6,10);
    
    fecha_menor = new Date(year, month - 1, day);
    
    day  = date2.substring(0,2);
    month = date2.substring(3,5);
    year  = date2.substring(6,10);
    
    fecha_mayor = new Date(year, month - 1, day);    
    
    if (fecha_menor <= fecha_mayor){
      return true;
    }  
    else {        
      return false;
    }
}