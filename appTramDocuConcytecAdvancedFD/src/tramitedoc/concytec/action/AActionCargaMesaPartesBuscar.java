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

public class AActionCargaMesaPartesBuscar extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("Dentro de la Carga Mesa de Partes Buscar");
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
        String ls_accion="";
        int li_retorno=0;
        
		try {
			
			 
			 
			Connection cnx = getConnection(request, "principal");
			System.out.println("El cnx  ==> " + cnx);
			
			 HttpSession session = request.getSession(true);        

		        /*Verificar si la sesion se perdio*/
		        
		         ls_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
		         ls_persona = String.valueOf(session.getAttribute("rs_persona"));
		         ls_seleccion_nat = String.valueOf(session.getAttribute("naturaleza_doc"));
		         
		  
		         ls_fecha_inicio = request.getParameter("fecha_ini");
		         ls_fecha_fin    = request.getParameter("fecha_fin");
		         ls_operacion = request.getParameter("operacion");
		         ls_accion = request.getParameter("accion");
		        
		        IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		        
		        //Collection rs_mesa_partes=new ArrayList();
		        
		        session.removeAttribute("rs_mesa_partes");
		        
		        
		        if(ls_accion.equals("N")){
		        	System.out.println("Si la accion es N de Naturaleza");
		        	
		        	 ls_naturaleza = request.getParameter("naturaleza");
		        	 
			         System.out.println("ls_naturaleza" + ls_naturaleza);
			         
		        	Collection  rs_mesa_partes = daoIUsuarioDAO.mesa_partes_naturaleza_buscar(cnx,ls_naturaleza,ls_oficina);
		        	
		        	System.out.println("El rs_mesa_partes"+ rs_mesa_partes);
			        //session.removeAttribute("rs_mesa_partes");
		            session.setAttribute("rs_mesa_partes",rs_mesa_partes);
		           
		            
		        }else
		       
		        if(ls_accion.equals("NT"))
		        {
		        	System.out.println("Si la accion es Natruraleza  y Tipo de Documento");
		        	
		        	ls_naturaleza = request.getParameter("naturaleza");
		        	ls_tipodoc = request.getParameter("tipodoc");
			         
		        	System.out.println("ls_naturaleza" + ls_naturaleza);
		        	System.out.println("ls_tipodoc" + ls_tipodoc);
			         
		        	 Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_naturaleza_tipodoc_buscar(cnx,
		        			 ls_naturaleza,ls_tipodoc,ls_oficina);
		        	  
		        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);
		        	 
		        }else
		       
		        if(ls_accion.equals("NR"))
		        {
		        	System.out.println("Si la accion es NAturaleza, Numero de Registro");
		        	
		        	ls_naturaleza = request.getParameter("naturaleza");
		        	 ls_numero_registro = request.getParameter("numero_registro");
			         System.out.println("ls_naturaleza" + ls_naturaleza);
			         System.out.println("ls_numero_registro" + ls_numero_registro);
			         
		        	Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_naturaleza_numeroreg_buscar(cnx,
		        			ls_naturaleza,ls_numero_registro,ls_oficina);
		        		
		        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);
		        	 
		        }else
				       
		        if(ls_accion.equals("NFF"))
		        {
		        	 System.out.println("Si la accion es Naturaleza, Fecha Inicio, Fecha Fin");
		        	 
		        	 ls_naturaleza = request.getParameter("naturaleza");
		        	 ls_fecha_inicio = request.getParameter("fecha_ini");
		        	 ls_fecha_fin = request.getParameter("fecha_fin");
			         System.out.println("ls_naturaleza" + ls_naturaleza);
			         System.out.println("ls_fecha_inicio" + ls_fecha_inicio);
			         System.out.println("ls_fecha_fin" + ls_fecha_fin);
			         
		        	 Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_naturaleza_fechaini_fechafin_buscar(cnx,ls_naturaleza,
		        			 ls_fecha_inicio,ls_fecha_fin,ls_oficina);
		        	 
		        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);
		        }
		        else
				       
		        if(ls_accion.equals("NE"))
		        {
		        	 System.out.println("Si la accion es Naturaleza, estado");
		        	 
		        	 ls_naturaleza = request.getParameter("naturaleza");
		        	 ls_estado = request.getParameter("estado");
			         System.out.println("ls_naturaleza" + ls_naturaleza);
			         System.out.println("ls_estado" + ls_estado);
			         
		        	 Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_naturaleza_estado_buscar(cnx,ls_naturaleza,
		        			 ls_estado,ls_oficina);
		        	 
		        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);

		        }
		        
		        else
				       
			        if(ls_accion.equals("NX"))
			        {
			        	 System.out.println("Si la accion es Naturaleza, Expediente");
			        	 
			        	 ls_naturaleza = request.getParameter("naturaleza");
			        	 ls_numero_expediente = request.getParameter("numero_expediente");
				         System.out.println("ls_naturaleza" + ls_naturaleza);
				         System.out.println("ls_numero_expediente" + ls_numero_expediente);
				         
			        	 Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_naturaleza_expediente_buscar(cnx,ls_naturaleza,
			        			 ls_numero_expediente,ls_oficina);
			        	 
			        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);

			        }
		        
			        else
					       
			        if(ls_accion.equals("T"))
			        {
			        	 System.out.println("Si la accion es Tipo doc");
			        	 
			        	 ls_tipodoc = request.getParameter("tipodoc");
				         System.out.println("ls_tipodoc" + ls_tipodoc);
				         
				         Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_tipodoc_buscar(cnx,ls_tipodoc,ls_oficina);
			        	 
			        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);

			        }
	        
			        else
					       
			        if(ls_accion.equals("TN"))
			        {
			        	 System.out.println("Si la accion es Tipo doc,Numero Registro");
			        	 
			        	 ls_tipodoc = request.getParameter("tipodoc");
			        	 ls_numero_registro = request.getParameter("numero_registro");
				         System.out.println("ls_tipodoc" + ls_tipodoc);
				         System.out.println("ls_numero_registro" + ls_numero_registro);
				         
				         Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_tipodoc_numreg_buscar(cnx,ls_tipodoc,ls_numero_registro,ls_oficina);
			        	 
			        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);

			        }
		        
			        else
					       
				        if(ls_accion.equals("TFF"))
				        {
				        	 System.out.println("Si la accion es Tipo doc,Numero Registro");
				        	 
				        	 ls_tipodoc = request.getParameter("tipodoc");
				        	 ls_fecha_inicio = request.getParameter("fecha_ini");
				        	 ls_fecha_fin = request.getParameter("fecha_fin");
				        	 
					         System.out.println("ls_tipodoc" + ls_tipodoc);
					         System.out.println("ls_fecha_inicio" + ls_fecha_inicio);
					         System.out.println("ls_fecha_fin" + ls_fecha_fin);
					         
					         Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_tipodoc_fecini_fecfin_buscar(cnx,ls_tipodoc,ls_fecha_inicio,ls_fecha_fin,ls_oficina);
				        	 
				        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);

				        }
		        
				        else
						       
					        if(ls_accion.equals("TE"))
					        {
					        	 System.out.println("Si la accion es Tipo doc,Estado");
					        	 
					        	 ls_tipodoc = request.getParameter("tipodoc");
					        	 ls_estado = request.getParameter("estado");
					        	 
						         System.out.println("ls_tipodoc" + ls_tipodoc);
						         System.out.println("ls_estado" + ls_estado);
						        
						         
						         Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_tipodoc_estado_buscar(cnx,ls_tipodoc,ls_estado,ls_oficina);
					        	 
					        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);

					        }
		        		
		        
					        else
							       
						        if(ls_accion.equals("TP"))
						        {
						        	 System.out.println("Si la accion es Tipo doc,Numero Expediente");
						        	 
						        	 ls_tipodoc = request.getParameter("tipodoc");
						        	 ls_numero_expediente = request.getParameter("numero_expediente");
						        	 
							         System.out.println("ls_tipodoc" + ls_tipodoc);
							         System.out.println("ls_numero_expediente" + ls_numero_expediente);
							        
							         
							         Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_tipodoc_numexp_buscar(cnx,ls_tipodoc,ls_numero_expediente,ls_oficina);
						        	 
						        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);

						        }
		        
						        else
								       
					        if(ls_accion.equals("R"))
					        {
					        	 System.out.println("Si la accion es Numero Registro");
					        	 
					        	 ls_numero_registro = request.getParameter("numero_registro");
					     
						         System.out.println("ls_numero_registro" + ls_numero_registro);
						     
						         Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_numreg_buscar(cnx,ls_numero_registro,ls_oficina);
					        	 
					        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);

					        }
		        
					        else
							       
					        if(ls_accion.equals("RFF"))
					        {
					        	 System.out.println("Si la accion es Numero Registro,Fecha Ini,Fecha Fin");
					        	 
					        	 ls_numero_registro = request.getParameter("numero_registro");
					        	 ls_fecha_inicio = request.getParameter("fecha_ini");
					        	 ls_fecha_fin = request.getParameter("fecha_fin");
					     
						         System.out.println("ls_numero_registro" + ls_numero_registro);
						         System.out.println("ls_fecha_inicio" + ls_fecha_inicio);
						         System.out.println("ls_fecha_fin" + ls_fecha_fin);
						     
						         Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_numreg_fechaini_fechafin_buscar(cnx,ls_numero_registro,ls_fecha_inicio,ls_fecha_fin,ls_oficina);
					        	 
					        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);

					        }
					        else
					        if(ls_accion.equals("RE"))
					        {
					        	 System.out.println("Si la accion es Numero Registro,Estado");
					        	 
					        	 ls_numero_registro = request.getParameter("numero_registro");
					        	 ls_estado = request.getParameter("estado");
					     
						         System.out.println("ls_numero_registro" + ls_numero_registro);
						         System.out.println("ls_estado" + ls_estado);
						     
						         Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_numreg_estado_buscar(cnx,ls_numero_registro,ls_estado,ls_oficina);
					        	 
					        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);
			
					        }
					        else
						        if(ls_accion.equals("RP"))
						        {
						        	 System.out.println("Si la accion es Numero Registro,Numero Expediente");
						        	 
						        	 ls_numero_registro = request.getParameter("numero_registro");
						        	 ls_numero_expediente = request.getParameter("numero_expediente");
						     
							         System.out.println("ls_numero_registro" + ls_numero_registro);
							         System.out.println("ls_numero_expediente" + ls_numero_expediente);
							     
							         Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_numreg_numexp_buscar(cnx,ls_numero_registro,ls_numero_expediente,ls_oficina);
						        	 
						        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);
				
						        }
		        
						        else
						        if(ls_accion.equals("E"))
						        {
						        	 System.out.println("Si la accion es Estado");
						        	 
						        	 ls_estado = request.getParameter("estado");
						        	 
							         System.out.println("ls_estado" + ls_estado);
							         
							         Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_estado_buscar(cnx,ls_estado,ls_oficina);
						        	 
						        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);
				
						        }
						        else
							        if(ls_accion.equals("EP"))
							        {
							        	 System.out.println("Si la accion es Estado,Numero Expediente");
							        	 
							        	 ls_estado = request.getParameter("estado");
							        	 ls_numero_expediente = request.getParameter("numero_expediente");
							        	 
								         System.out.println("ls_estado" + ls_estado);
								         System.out.println("ls_numero_expediente" + ls_numero_expediente);
								         
								         Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_estado_numexp_buscar(cnx,ls_estado,ls_numero_expediente,ls_oficina);
							        	 
							        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);
					
							        }
							        else
								        if(ls_accion.equals("P"))
								        {
								        	 System.out.println("Si la accion es ,Numero Expediente");
								        	 
								        	 ls_numero_expediente = request.getParameter("numero_expediente");
								        	 
									         System.out.println("ls_numero_expediente" + ls_numero_expediente);
									         
									         Collection rs_mesa_partes = daoIUsuarioDAO.mesa_partes_numexp_buscar(cnx,ls_numero_expediente,ls_oficina);
								        	 
								        	 session.setAttribute("rs_mesa_partes",rs_mesa_partes);
						
								        }
		        
		        
		        
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
