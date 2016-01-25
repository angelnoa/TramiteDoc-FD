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

public class AActionMantProcedencia extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true);
	  	
		String ls_operacion = request.getParameter("operacion");
		String ls_accion = request.getParameter("accion");
		ls_operacion=ls_operacion.trim();
		ls_accion=ls_accion.trim();
		System.out.println("la ls_operacion: " + ls_operacion);
		System.out.println("la ls_accion: " + ls_accion);
		
		String descripcion;
		String tipopersona;
		String codigo;
		
		codigo = ((FFormMantProcedencia)form).getCodigo();
		descripcion = ((FFormMantProcedencia)form).getDescripcion();
		tipopersona = ((FFormMantProcedencia)form).getTipopersona();
		
		System.out.println("codigo... "+ codigo);
		System.out.println("descripcion... "+ descripcion);
		System.out.println("tipopersona... "+ tipopersona);
		
		descripcion=descripcion.toUpperCase();
		tipopersona=tipopersona.toUpperCase();
		
		System.out.println("descripcion.UPERCASE.. "+ descripcion);
		System.out.println("tipopersona.UPERCASE.. "+ tipopersona);
		
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
       	     	System.out.println("Ingresando Procedencia.........." );
       	     
       	     daoIUsuarioDAO.of_ingresar_procedencia(cnx,descripcion.trim(),tipopersona.trim());
				
				System.out.println("Despues del Ingreso Procedencia y Direccionando" );	
				
				if(ls_accion.equals("R")){
					
					session.setAttribute("accion", ls_accion);
					return (mapping.findForward("exito"));
					
				}else if(ls_accion.equals("RD")){
					
					session.setAttribute("accion", ls_accion);
					return (mapping.findForward("exito1"));	
				}
					
       	   
       	 case 2:
        	 	
                System.out.println("Modificacion de... procedencias.........." );
               
                System.out.println("daoIUsuarioDAO.of_modificar_procedencia(cnx,"+codigo+","+descripcion+","+tipopersona+")");
                daoIUsuarioDAO.of_modificar_procedencia(cnx,codigo.trim(),descripcion.trim(),tipopersona.trim());
                
                if(ls_accion.equals("R")){
					
					session.setAttribute("accion", ls_accion);
					return (mapping.findForward("exito"));
					
				}else if(ls_accion.equals("RD")){
					
					session.setAttribute("accion", ls_accion);
					return (mapping.findForward("exito1"));	
				}	
                
       	 case 3:
         	 	
             
       		daoIUsuarioDAO.of_eliminar_procedencia(cnx,codigo,tipopersona);
       		
       		if(ls_accion.equals("R")){
				
				session.setAttribute("accion", ls_accion);
				return (mapping.findForward("exito"));
				
			}else if(ls_accion.equals("RD")){
				
				session.setAttribute("accion", ls_accion);
				return (mapping.findForward("exito1"));	
			}
       		
       	 }
    	   
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
}
