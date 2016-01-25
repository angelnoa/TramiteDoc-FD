// JavaScript Document
function f_fecha_actual()
{
	//Calculates the day of the week, month, day of month and year
	var itsDay        = new Array("Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sábado");
	var itsMonth      = new Array("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Setiembre","Octubre","Noviembre","Diciembre");

	var now = new Date;

	var theDay        = now.getDay();
	var theMonth      = now.getMonth();
	var theDayOfMonth = now.getDate();
	var theYear       = now.getFullYear();

	// Assigns st,nd,rd,th to the day of the month so 11 would be 11th
	if(theDayOfMonth > 3 && theDayOfMonth < 21 || theDayOfMonth > 23 && theDayOfMonth < 31) {
	  theSuffix = "th";
	}
	if(theDayOfMonth == 1 || theDayOfMonth == 21 || theDayOfMonth == 31) {
	  theSuffix = "st";
	}
	if(theDayOfMonth == 2 || theDayOfMonth == 22 || theDayOfMonth == 32) {
	  theSuffix = "nd";
	}
	if(theDayOfMonth == 3 || theDayOfMonth == 23) {
	  theSuffix = "rd";
	}
	//*********************************************************************
var theDateOutput = itsDay[theDay] + " " +  theDayOfMonth + "  "+itsMonth[theMonth] + " "  + ", " + theYear + "    ";	
return theDateOutput

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
