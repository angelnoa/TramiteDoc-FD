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

public class AActionPuenteDocumento extends ValidaSessionAction{
	
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
	        String ls_accion=null;
	        String ls_cod_doc=null;
	        String ls_estado=null;
	        String ls_secuencia=null;
	        String ls_pagina=null;
	        String ls_msg_error=null;
	        String ls_cod_padre=null;
	        String ls_codigo_inicia=null;
	        String codigo_oficina=null;
	        String ls_fecha_rpta=null;
	        String ls_codigo_expediente="";
	        String ls_descripcion_tipo="";
	        String ls_numero_documento="";
	        String ls_naturaleza_documento="";
	        String ls_correlativo_deriva="";
	        String ls_descripcion_oficina="";
	        String ls_oficina="";
	        
	        codigo_oficina   =   String.valueOf(session.getAttribute("codigo_oficina"));
	        ls_accion = request.getParameter("operacion");
	        ls_cod_doc = request.getParameter("codigo");
	        ls_secuencia = request.getParameter("secuencia");
		    ls_estado = request.getParameter("estado");
		    ls_codigo_inicia = request.getParameter("codigo_inicia");
		    ls_fecha_rpta = request.getParameter("fecha_rpta");
		    ls_codigo_expediente = request.getParameter("codigo_expediente");
		    ls_descripcion_tipo = request.getParameter("descripcion_tipo");
		    ls_numero_documento = request.getParameter("numero_documento");
		    ls_naturaleza_documento = request.getParameter("naturaleza_documento");
		    ls_correlativo_deriva = request.getParameter("correlativo_deriva");
		    ls_descripcion_oficina = request.getParameter("descripcion_oficina");
		    ls_oficina = request.getParameter("oficina");
		    
		    System.out.println("el ls_accion" + ls_accion);
		    System.out.println("el ls_cod_doc" + ls_cod_doc);
		    System.out.println("el ls_secuencia" + ls_secuencia);
		    System.out.println("el ls_estado" + ls_estado);
		    System.out.println("el ls_codigo_inicia" + ls_codigo_inicia);
		    System.out.println("el ls_fecha_rpta" + ls_fecha_rpta);
		    System.out.println("el ls_codigo_expediente" + ls_codigo_expediente);
		    System.out.println("el ls_descripcion_tipo" + ls_descripcion_tipo);
		    System.out.println("el ls_numero_documento" + ls_numero_documento);
		    System.out.println("el ls_naturaleza_documento" + ls_naturaleza_documento);
		    System.out.println("el ls_correlativo_deriva" + ls_correlativo_deriva);
		    System.out.println("el ls_nombre_personal" + ls_descripcion_oficina);
		    System.out.println("el ls_oficina" + ls_oficina);
		    
		try {
			
			Connection cnx = getConnection(request, "principal");
			System.out.println("El cnx  ==> " + cnx);
			
			IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			//BMesaPartes BMesaPartesVO=new BMesaPartes();
			 	session.removeAttribute("accion_mp");
	            session.removeAttribute("documento");
	            session.removeAttribute("secuencia");
	            session.removeAttribute("codigo_inicia");
	            session.removeAttribute("codigo_expediente");
	            session.removeAttribute("descripcion_tipo");
	            session.removeAttribute("numero_documento");
	            session.removeAttribute("naturaleza_documento");
	            session.removeAttribute("fecha_rpta");
	            session.removeAttribute("correlativo_deriva");
	            session.removeAttribute("descripcion_oficina");
	            session.removeAttribute("detalleConsulta");
	            session.removeAttribute("mensaje");
	            
	            
	            session.setAttribute("accion_mp", ls_accion);
	            session.setAttribute("documento", ls_cod_doc);
	            session.setAttribute("secuencia",ls_secuencia);
	            session.setAttribute("codigo_inicia", ls_codigo_inicia);
	            session.setAttribute("codigo_expediente", ls_codigo_expediente);
	            session.setAttribute("descripcion_tipo", ls_descripcion_tipo);
	            session.setAttribute("numero_documento", ls_numero_documento);
	            session.setAttribute("naturaleza_documento", ls_naturaleza_documento);
	            session.setAttribute("fecha_rpta", ls_fecha_rpta);
	            session.setAttribute("correlativo_deriva", ls_correlativo_deriva);
	            session.setAttribute("descripcion_oficina", ls_descripcion_oficina);
	            session.setAttribute("oficina", ls_oficina);
	            
	            System.out.println("ls_accion" + ls_accion);
	            if (ls_accion == null) {  
	                ls_accion = "";
	            }
	            if (ls_accion.equals("R")) { 
	                // Activamos el Action de Recorrido ....
	            	if(ls_fecha_rpta.equals("") || ls_fecha_rpta==null ){
	            		
	            		return (mapping.findForward("recorridofecnull"));
	            		
	            	}else{
	          
	            		return (mapping.findForward("recorrido"));
	            	}
	            	 
	            }
	            if (ls_accion.equals("D")) { 
	                // Activamos La Pagina de Derivación ....
	                if (ls_estado.equals("3") || ls_estado.equals("1"))
	                    {
	                    // Solo se puede Derivar cuando el Estado es Tramite .... 
	                	BMesaPartes BMesaPartesVO = daoIUsuarioDAO.of_obtener_padre(cnx,ls_cod_doc);
	                     
	                     ls_cod_padre = BMesaPartesVO.getCodigo_inicia();
	                     System.out.println("ls_cod_padre" + ls_cod_padre);
	                     
	                    session.removeAttribute("cod_padre");
	                    session.removeAttribute("derivar");
	                    
	                    
	                    session.setAttribute("cod_padre", ls_cod_padre);
	                    session.setAttribute("derivar", "derivar");
	                    
	                    return (mapping.findForward("derivacion"));
	                    
	              
	                }
	                else
	                {
	                    ls_msg_error = "Para Derivar un Documento el estado debe ser Recibido..!";
	                    session.setAttribute("error",ls_msg_error);       
	                    return (mapping.findForward("mensaje"));
	                }
	            }
	            if (ls_accion.equals("A")) { 
	                // Activamos pagina de Archivar ....
	                if (ls_estado.equals("3") || ls_estado.equals("4")) 
	                {
	                	return (mapping.findForward("archivar"));
	                   //ls_pagina = "pag_archivar.jsp";
	                }
	                else
	                {
	                    ls_msg_error = "El Estado del Documento debe ser Recibido ó Derivado";
	                    session.setAttribute("error", ls_msg_error);       
	                    return (mapping.findForward("mensaje"));
	                }
	            }
	            if (ls_accion.equals("E")) { 
	                // Activamos pagina de Recibir un Doc ....
	                // ls_pagina = "pag_serie_expediente.jsp"; 
	                if (ls_estado.equals("2") || ls_estado.equals("3")|| ls_estado.equals("6"))
	                {
	                	
	                	 li_retorno = daoIUsuarioDAO.parametro(cnx);
	    				
	                	 System.out.println("li_retorno" + li_retorno);
	                	 
	    				session.setAttribute("correlativorecepcion",String.valueOf(li_retorno));
	    				
	                	BMesaPartes BMesaPartesVO = daoIUsuarioDAO.of_obtener_padre(cnx,ls_cod_doc);
	                     
	                     ls_cod_padre = BMesaPartesVO.getCodigo_inicia();
	                     System.out.println("ls_cod_padre" + ls_cod_padre);
	                     ls_codigo_inicia = request.getParameter("codigo_inicia");
	                     System.out.println("ls_codigo_inicia" + ls_codigo_inicia);
	                     
	                     session.removeAttribute("cod_padre");
	 	                 session.removeAttribute("derivar"); 
	 	                 session.removeAttribute("estadodoc");
	 	                    
	 	                session.setAttribute("cod_padre",ls_cod_padre);
 	                    session.setAttribute("derivar","derivar");
 	                	session.setAttribute("estadodoc", ls_estado);
 	                	
	                    if(ls_cod_doc.equals(ls_cod_padre)){
	                    	
	                    	System.out.println("si el codigo inicia es igual al codigo padre");
	                    	
	                    	if(codigo_oficina.equals("1")&& ls_naturaleza_documento.equals("I")){
	                    		System.out.println("si el codigo de oficina es mesa de partes");
	                    		return (mapping.findForward("preliquidardoc"));
	                    	}
	                    	
	 	                	 return (mapping.findForward("recibirdoc"));
	 	                	 
	                    }else{
	             
	                    	System.out.println("si el codigo inicia es diferente al codigo padre");
	                    	
	                    	if(codigo_oficina.equals("2")){
	                    		System.out.println("si el codigo de oficina es secretaria");
	                    		return (mapping.findForward("recibirdoc"));
	                    	}
	                    	
	                    	if(codigo_oficina.equals("1")){
	                    		System.out.println("si el codigo de oficina es mesa de partes");
	                    		return (mapping.findForward("preliquidardoc"));
	                    	}
	                    	 
	                    }
	               
	                }
	                else
	                {
	                    ls_msg_error = "El Estado del Documento debe ser Pendiente";
	                    session.setAttribute("error",ls_msg_error);   
	                    return (mapping.findForward("mensaje"));
	                  
	                }
	            
			
	            }
	            
	            if (ls_accion.equals("S")) { 
	                
	                // la accion es salir del sistema
	              
	               return (mapping.findForward("cerrarsession"));
	                 
	            }
	            
	            if (ls_accion.equals("W")) { 
	                // Activamos pagina de Recibir un Doc ....
	                // ls_pagina = "pag_serie_expediente.jsp"; 
	                if (ls_estado.equals("3"))
	                {
	                	
	                	BMesaPartes BMesaPartesVO = daoIUsuarioDAO.of_obtener_padre(cnx,ls_cod_doc);
	                     
	                     ls_cod_padre = BMesaPartesVO.getCodigo_inicia();
	                     System.out.println("ls_cod_padre" + ls_cod_padre);
	                     
	                    session.removeAttribute("cod_padre");
	                    session.removeAttribute("derivar"); 
	                    session.setAttribute("cod_padre",ls_cod_padre);
	                    session.setAttribute("derivar","derivar");
	                    
	                	 return (mapping.findForward("asociarintent"));
	                  
	                }
	                else
	                {
	                    ls_msg_error = "El Estado del Documento debe ser Recibido";
	                    session.setAttribute("error",ls_msg_error);   
	                    return (mapping.findForward("mensaje"));
	                  
	                }
	                
	            }
	            
	            
	            if (ls_accion.equals("M")) { 
	                // Activamos pagina de Modificar Documento ....
	            	
	            	
	            	BMesaPartes detalleConsulta = daoIUsuarioDAO.of_detalle_documento(cnx,ls_oficina,ls_cod_doc,ls_secuencia);
	            	 System.out.println("detalleConsulta" + detalleConsulta);
	            	
	                    session.setAttribute("detalleConsulta", detalleConsulta);       
	                    return (mapping.findForward("modificardoc"));
	                
	            }
	            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
}
