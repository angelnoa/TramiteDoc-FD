package tramitedoc.concytec.util;

import java.util.Date;

public class Prueba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Date fecha_derivacion=new Date(110,10,12,11,10);
		Date fecha_recepcion=new Date(110,10,14,10,15);
		long fd=fecha_derivacion.getTime();
		long fr=fecha_recepcion.getTime();
		long tiempo =fr-fd;
		int dias = (int)Math.floor(tiempo / (1000 * 60 * 60 * 24));
		long dif= tiempo%(1000 * 60 * 60 * 24);
		int horas = (int)Math.floor(dif/(1000 * 60 * 60) );
		
		System.out.println("fecha derivacion:"+fecha_derivacion);
		System.out.println("fecha recepcion:"+fecha_recepcion);
		/*
		System.out.println("Dias:"+dias);
		System.out.println("dif:"+dif);
		System.out.println("Horas:"+horas);
		*/
		String tiempo_bandeja = ""+dias+" dias, "+horas+ " horas";
		System.out.println("Tiempo en bandeja:"+tiempo_bandeja);
				
	}

}
