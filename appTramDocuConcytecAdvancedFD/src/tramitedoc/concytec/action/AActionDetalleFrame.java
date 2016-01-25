package tramitedoc.concytec.action;
import java.io.PrintWriter;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

public class AActionDetalleFrame extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(AActionDetalleFrame.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
		 HttpSession session = request.getSession(false);        

	        /*Verificar si la sesion se perdio*/
	        if (session==null){
	        	
	        	return (mapping.findForward("expiracion"));
	        	
	        }   
		
	        int li_retorno=0;
	        String ls_tipo="";
	        String ls_operacion="";
	        String ls_argumento="";
	        String ls_descripcion="";
	        String ls_codigo="";
	        String ls_tipo_doc="";
	        String ls_accion_p="";
	        String ls_accion="";
	        
	        ls_tipo = request.getParameter("tipo");
	        
	     
		try {
			
			if(ls_tipo.equals("procedencia")){
				
				log.info("Dentro de Detalle Frame Procedencia...! ");
				session.removeAttribute("operacion");
				session.removeAttribute("codigo");
				session.removeAttribute("descripcion");
				session.removeAttribute("tipodoc");
				
				ls_operacion = request.getParameter("operacion");
				ls_accion = request.getParameter("accion");
				
				log.info("El ls_operacion " + ls_operacion);
				log.info("El ls_accion " + ls_accion);
				
				ls_tipo_doc = request.getParameter("tipodoc").trim();
				ls_descripcion = request.getParameter("nombre_persona").trim();
				ls_codigo = request.getParameter("codigo_persona").trim();
				//ls_accion_p = request.getParameter("accion_p").trim();
				
				 log.info("El ls_tipo_doc " + ls_tipo_doc);
			     log.info("El ls_descripcion " + ls_descripcion);
			     log.info("El ls_codigo " + ls_codigo);
			    // log.info("El ls_accion_p " + ls_accion_p);
			        
		        session.setAttribute("operacion",ls_operacion);
		        session.setAttribute("codigo",ls_codigo);
		        session.setAttribute("descripcion",ls_descripcion);
		        session.setAttribute("tipodoc",ls_tipo_doc);
		        session.setAttribute("accion",ls_accion);
		        
	            return (mapping.findForward("procedencia"));
		            
			}
			else if(ls_tipo.equals("dirigidos")){
				
				log.info("Dentro de Detalle Frame Dirigidos...! ");
				session.removeAttribute("operacion");
				session.removeAttribute("codigo");
				session.removeAttribute("descripcion");
				session.removeAttribute("tipodoc");
				
				ls_operacion = request.getParameter("operacion");
				ls_accion = request.getParameter("accion");
				
				log.info("El ls_operacion " + ls_operacion);
				log.info("El ls_accion " + ls_accion);
				
				ls_tipo_doc = request.getParameter("tipodoc").trim();
				ls_descripcion = request.getParameter("nombre_persona").trim();
				ls_codigo = request.getParameter("codigo_persona").trim();
				//ls_accion_p = request.getParameter("accion_p").trim();
				
				 log.info("El ls_tipo_doc " + ls_tipo_doc);
			     log.info("El ls_descripcion " + ls_descripcion);
			     log.info("El ls_codigo " + ls_codigo);
			    // log.info("El ls_accion_p " + ls_accion_p);
			        
		        session.setAttribute("operacion",ls_operacion);
		        session.setAttribute("codigo",ls_codigo);
		        session.setAttribute("descripcion",ls_descripcion);
		        session.setAttribute("tipodoc",ls_tipo_doc);
		        session.setAttribute("accion",ls_accion);
		        
	            return (mapping.findForward("dirigidos"));
		            
			}
			
			
	            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
}
