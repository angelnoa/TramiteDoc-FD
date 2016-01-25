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

public class AActionCargaMesaPartes extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("Dentro de la Carga Mesa de Partes");
		String ls_oficina=null;
        String ls_persona=null;
        String ls_naturaleza=null;
        String ls_estado=null;
        String ls_fecha_inicio=null;
        String ls_fecha_fin=null;
        String ls_filtro, ls_valorfiltro, ls_pagina=null;
        String ls_msg_error=null;
        String ls_operacion=null;
        String ls_tipodoc=null;
        String ls_seleccion_nat=null;
        String ls_numero_registro=null;
        String ls_numero_expediente=null;
        int li_retorno=0;
        
		try {
			
			 
			HttpSession tempSession = request.getSession(false);
			
			if (tempSession==null){
				
				return (mapping.findForward("expiracion"));
			}
			
			Connection cnx = getConnection(request, "principal");
			System.out.println("El cnx  ==> " + cnx);
			
			 HttpSession session = request.getSession(true);        

		        /*Verificar si la sesion se perdio*/
		        
		         ls_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
		         ls_persona = String.valueOf(session.getAttribute("rs_persona"));
		         ls_seleccion_nat = String.valueOf(session.getAttribute("naturaleza_doc"));
		         
		         ls_naturaleza = request.getParameter("naturaleza");
		         System.out.println("ls_naturaleza" + ls_naturaleza);
		         ls_estado = request.getParameter("estado");
		         System.out.println("ls_estado" + ls_estado);
		         ls_fecha_inicio = request.getParameter("fecha_ini");
		         System.out.println("ls_fecha_inicio" + ls_fecha_inicio);
		         ls_fecha_fin    = request.getParameter("fecha_fin");
		         System.out.println("ls_fecha_fin" + ls_fecha_fin);
		         ls_filtro = request.getParameter("filtro");
		         System.out.println("ls_filtro" + ls_filtro);
		         ls_operacion = request.getParameter("operacion");
		         System.out.println("ls_operacion" + ls_operacion);
		         ls_tipodoc = request.getParameter("tipodoc");
		         System.out.println("ls_tipodoc" + ls_tipodoc);
		         ls_valorfiltro = request.getParameter("valorfiltro");
		         System.out.println("ls_valorfiltro" + ls_valorfiltro);
		         ls_numero_registro = request.getParameter("numero_registro");
		         System.out.println("ls_numero_registro" + ls_numero_registro);
		         ls_numero_expediente = request.getParameter("numero_expediente");
		         System.out.println("ls_numero_expediente" + ls_numero_expediente);
		         
		       
		       
		       
		        IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
				
		        Collection rs_mesa_partes = new ArrayList();
		        
		        session.removeAttribute("rs_mesa_partes");
		        
		 
		        	  rs_mesa_partes = daoIUsuarioDAO.mesa_partes_normal(cnx,ls_oficina, ls_fecha_inicio,
				    		 ls_fecha_fin, ls_naturaleza, ls_estado, ls_tipodoc,ls_filtro,ls_valorfiltro,
				    		 ls_numero_registro,ls_numero_expediente);
						
		    
		       System.out.println("El rs_mesa_partes"+ rs_mesa_partes);
		       // session.removeAttribute("rs_mesa_partes");
	            session.setAttribute("rs_mesa_partes",rs_mesa_partes);
	            session.setAttribute("oficina",ls_oficina);
	            session.setAttribute("persona",ls_persona);
	            session.setAttribute("naturaleza",ls_naturaleza);
	            session.setAttribute("estado",ls_estado);
	            session.setAttribute("fecha_inicio",ls_fecha_inicio);
	            session.setAttribute("fecha_fin",ls_fecha_fin);
	            
	            System.out.println("operacion" + ls_operacion);
	            if (ls_operacion == null) { 
	                ls_operacion = "";
	            }
	            if (ls_operacion.equals("buscar")) { 
	            	System.out.println("entro a buscar en la lista de documentos");
	           
	            	return (mapping.findForward("listadocumentos"));
	                
	            }
	            else
	            {
	                System.out.println("entro a la pagina mesa de partes");
	                return (mapping.findForward("mesapartes"));
	                
	            }
	            
	            
		        
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
}
