package tramitedoc.concytec.action;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import tramitedoc.concytec.dao.*;
import tramitedoc.concytec.form.*;
import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionMantEntregarRecibir extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		System.out.println("Dentro de Entregar Recibir ");
		
		HttpSession session = request.getSession(true);
	  	
		String ls_operacion = request.getParameter("operacion");
		System.out.println("la ls_operacion: " + ls_operacion);
		
		String fecha;
		String hora;
		String observacion;
		String ls_oficina=null;
		String ls_usuario=null;
		String ls_documento=null;
		String ls_estado_doc=null;
		 String ls_codigo_inicia  = null;
		 String ls_codigoPadre = null;
		 String ls_naturaleza_documento = "";
		 String ls_fecha_rpta = "";
		 String ls_correlativorecepcion = "";
		 String ls_mensaje = "";
		 
		 
		 int li_retorno=0;
		 
		ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
		ls_usuario   = String.valueOf(session.getAttribute("nombreusuario"));
		ls_documento = String.valueOf(session.getAttribute("documento"));
		ls_estado_doc = String.valueOf(session.getAttribute("estadodoc"));
		ls_codigo_inicia   = String.valueOf(session.getAttribute("codigo_inicia"));
		ls_codigoPadre = String.valueOf(session.getAttribute("cod_padre")); 
	    ls_naturaleza_documento = String.valueOf(session.getAttribute("naturaleza_documento"));
	    ls_fecha_rpta = String.valueOf(session.getAttribute("fecha_rpta"));
	    ls_correlativorecepcion = String.valueOf(session.getAttribute("correlativorecepcion"));
	    
	    
	    
	    
		System.out.println("ls_oficina... "+ ls_oficina);
		System.out.println("ls_usuario... "+ ls_usuario);
		System.out.println("ls_documento... "+ ls_documento);
		System.out.println("ls_estado_doc... "+ ls_estado_doc);
		System.out.println("ls_codigo_inicia... "+ ls_codigo_inicia);
		System.out.println("ls_codigoPadre... "+ ls_codigoPadre);
		System.out.println("ls_naturaleza_documento... "+ ls_naturaleza_documento);
		System.out.println("ls_fecha_rpta... "+ ls_fecha_rpta);
		System.out.println("ls_correlativorecepcion... "+ ls_correlativorecepcion);
		
		fecha = ((FFormMantEntregarRecibir)form).getFecha();
		hora = ((FFormMantEntregarRecibir)form).getHora();
		observacion = ((FFormMantEntregarRecibir)form).getObservacion();
		
		System.out.println("fecha... "+ fecha);
		System.out.println("hora... "+ hora);
		System.out.println("observacion... "+ observacion);
		
		fecha=fecha.toUpperCase();
		hora=hora.toUpperCase();
		observacion=observacion.toUpperCase();
		
		System.out.println("fecha.UPERCASE.. "+ fecha);
		System.out.println("hora.UPERCASE.. "+ hora);
		System.out.println("observacion.UPERCASE.. "+ observacion);
		
		try {
			
			Connection cnx = getConnection(request, "principal");
			System.out.println("El cnx  ==> " + cnx);
			
			IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO(); 
			
        	int li_operacion =0;
        	
        	if (ls_operacion.equals("N")){
                li_operacion = 1;
            }else if (ls_operacion.equals("M")){
    	            li_operacion = 2;
    	    } else if (ls_operacion.equals("E")){
                li_operacion = 3;
    	    } 
        	
        	switch(li_operacion){
       	 case 1:
       	 	 	
       	     	session.setAttribute("operacion", ls_operacion);
       	     	System.out.println("Ingresando Recibir Documento.........." );
       	     
       	     if(ls_fecha_rpta.equals("") || ls_fecha_rpta==null){
       	    	 
       	    	System.out.println("Si no existe fecha de Respuesta" );
       	     
       	     if(ls_documento.equals(ls_codigoPadre)){
       	    	
       	    	System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son iguales" );
       	    	
       	    	if(ls_oficina.equals("1") && ls_naturaleza_documento.equals("I")){
       	    		
       	    		ls_mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
       	    		session.setAttribute("mensaje",ls_mensaje);
       	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son iguales" );	
       	    		daoIUsuarioDAO.of_recibir_doc_mesapartes(cnx,ls_oficina,ls_documento,ls_usuario,fecha,hora,observacion,ls_estado_doc,ls_correlativorecepcion);
   				
       	    		System.out.println("Despues del Ingreso Recibir Documento la oficina mesa de partes y el documento es I" );
       	    		
       	    	 }else{
       	    		 
       	    		ls_mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
       	    		session.setAttribute("mensaje",ls_mensaje);
       	    		
       	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son iguales" );	
         	    	 //daoIUsuarioDAO.of_recibir_doc(cnx,ls_oficina,ls_documento,ls_usuario,fecha,hora,observacion,ls_estado_doc,ls_correlativorecepcion);
  				
  				System.out.println("Despues del Ingreso Recibir Documento" );
  				
       	    	 }
       	    		
       	    	
       	     }else{
       	    	
       	    	System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );
       	    	
       	    	 if(ls_oficina.equals("2")){
       	    		 
       	    		ls_mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
       	    		session.setAttribute("mensaje",ls_mensaje);
       	    		
       	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
          	    	 //daoIUsuarioDAO.of_recibir_doc(cnx,ls_oficina,ls_documento,ls_usuario,fecha,hora,observacion,ls_estado_doc,ls_correlativorecepcion);
   				
       	    	 }
       	    	if(ls_oficina.equals("1")){
       	    		
       	    		ls_mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
       	    		session.setAttribute("mensaje",ls_mensaje);
       	    		
       	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
       	    		daoIUsuarioDAO.of_recibir_doc_mesapartes(cnx,ls_oficina,ls_documento,ls_usuario,fecha,hora,observacion,ls_estado_doc,ls_correlativorecepcion);
   				
       	    	 }
       	    	
       	    	 
				
				System.out.println("Despues del Ingreso Recibir Documento" );
       	     }
       	     
       	     }else{
       	    	 
       	    	System.out.println("Si existe fecha de Respuesta" );
       	    	if(ls_documento.equals(ls_codigoPadre)){
           	    	
           	    	System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son iguales" );
           	    	
           	    	if(ls_oficina.equals("1") && ls_naturaleza_documento.equals("I")){
           	    		
           	    		ls_mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
           	    		session.setAttribute("mensaje",ls_mensaje);
           	    		
           	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son iguales" );	
           	    		daoIUsuarioDAO.of_recibir_doc_mesapartes(cnx,ls_oficina,ls_documento,ls_usuario,fecha,hora,observacion,ls_estado_doc,ls_correlativorecepcion);
       				
           	    		System.out.println("Despues del Ingreso Recibir Documento la oficina mesa de partes y el documento es I" );
           	    		
           	    		daoIUsuarioDAO.of_modificar_fecharespuesta(cnx,ls_oficina,ls_documento);
           	    		System.out.println("Despues de Limpiar la fecha de Respuesta 1" );
           	    		
           	    	 }else{
           	    		 
           	    		 
           	    		ls_mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
           	    		session.setAttribute("mensaje",ls_mensaje);
           	    		
           	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son iguales" );	
             	    	 //daoIUsuarioDAO.of_recibir_doc(cnx,ls_oficina,ls_documento,ls_usuario,fecha,hora,observacion,ls_estado_doc,ls_correlativorecepcion);
             	    	 
      				System.out.println("Despues del Ingreso Recibir Documento" );
      				
      				daoIUsuarioDAO.of_modificar_fecharespuesta(cnx,ls_oficina,ls_documento);
       	    		System.out.println("Despues de Limpiar la fecha de Respuesta 2" );
       	    		
           	    	 }
           	    		
           	    	
           	     }else{
           	    	
           	    	System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );
           	    	
           	    	 if(ls_oficina.equals("2")){
           	    		 
           	    		ls_mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
           	    		session.setAttribute("mensaje",ls_mensaje);
           	    		
           	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
              	    	 //daoIUsuarioDAO.of_recibir_doc(cnx,ls_oficina,ls_documento,ls_usuario,fecha,hora,observacion,ls_estado_doc,ls_correlativorecepcion);
              	    	 
              	    	daoIUsuarioDAO.of_modificar_fecharespuesta(cnx,ls_oficina,ls_documento);
           	    		System.out.println("Despues de Limpiar la fecha de Respuesta 3" );
       				
           	    	 }
           	    	if(ls_oficina.equals("1")){
           	    		
           	    		ls_mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
           	    		session.setAttribute("mensaje",ls_mensaje);
           	    		
           	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
           	    		daoIUsuarioDAO.of_recibir_doc_mesapartes(cnx,ls_oficina,ls_documento,ls_usuario,fecha,hora,observacion,ls_estado_doc,ls_correlativorecepcion);
           	    		
           	    		daoIUsuarioDAO.of_modificar_fecharespuesta(cnx,ls_oficina,ls_documento);
           	    		System.out.println("Despues de Limpiar la fecha de Respuesta 4" );
       				
           	    	 }
           	    	
           	    	 
    				
    				System.out.println("Despues del Ingreso Recibir Documento" );
           	     }
       	    	 
       	     }
       	    	
				return (mapping.findForward("exito"));	
				
       	 case 2:
        	 	
                System.out.println("Modificacion de... Anexos.........." );
               
              
				return (mapping.findForward("exito"));	
       	 case 3:
         	 	
             
                
				return (mapping.findForward("exito"));	
       	 }
    	   
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
}
