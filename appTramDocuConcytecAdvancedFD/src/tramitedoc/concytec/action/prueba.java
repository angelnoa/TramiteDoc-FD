package tramitedoc.concytec.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.io.*;
import java.util.*;
import java.text.*;

public class prueba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			SimpleDateFormat fechaActual  = new SimpleDateFormat ("dd/MM/yyyy");
		SimpleDateFormat fechaConsulta = new SimpleDateFormat ("yyyyMMdd");
		SimpleDateFormat year = new SimpleDateFormat ("yyyy");
		
		Date d= new Date(); 
		String fecha  = fechaActual.format(d);
		String fechaconsulta = fechaConsulta.format(d);
		String yearactual = year.format(d);
		int li_retorno=0;
		System.out.println("FECHA fecha.... : " + fecha);
		System.out.println("FECHA fechaconsulta.... : " + fechaconsulta);
		System.out.println("Año yearactual.... : " + yearactual);
		
		String cantidad="20081252";
		String ls_extraer_year = cantidad.substring(4);
		li_retorno = Integer.valueOf(ls_extraer_year).intValue() + 1;
		
		 System.out.println("El ls_extraer_year es" + ls_extraer_year);
		 
		
		String g_codigo_documento = yearactual+ li_retorno;
   	 
   	 System.out.println("El nuevo codigo documento es" + g_codigo_documento);
   	 
   	 
   	String str="2008125";
	String subStr=str.substring(4);
	
	System.out.println("El subStr es" + subStr);
	
	/*Calendar calendario = new GregorianCalendar();
	
	String hora, minutos, segundos;
	
	hora =calendario.get(Calendar.HOUR_OF_DAY);
	minutos = calendario.get(Calendar.MINUTE);
	segundos = calendario.get(Calendar.SECOND);
	
	System.out.println("hora.... : " + hora);
	System.out.println("minutos.... : " + minutos);
	System.out.println("segundos... : " + segundos);*/
	
	String horaActual = "00:00:00";
	Calendar cal = Calendar.getInstance();
    Date hora1 = cal.getTime();
    DateFormat df = DateFormat.getTimeInstance();
    horaActual = df.format(hora1);
    
    System.out.println("horaActual.... : " + horaActual);
    System.out.println("hora1.... : " + hora1);
    System.out.println("df.... : " + df);
    
    
    //String[] otras_oficinas = request.getParameterValues("cbo_copia"); 
    
    String[] jlf = {"Muhammad","Fahim","Aamir"}; 
    for(int loopIndex = 0; loopIndex < jlf.length; loopIndex++){
		 
		 //Vector fila = (Vector)(jlf.get(loopIndex));
        // String ls_codigo_ins="";
        // String ls_codigo_con="";
        //as_destino      = (String)(fila.get(0));
        //as_destinatario = (String)(fila.get(2));
        
        System.out.println(jlf[loopIndex]);
        
        
        
    }
    
	}

}
