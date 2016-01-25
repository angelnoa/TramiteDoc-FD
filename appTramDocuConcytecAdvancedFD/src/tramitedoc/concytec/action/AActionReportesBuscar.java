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

public class AActionReportesBuscar extends ValidaSessionAction{
	
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
        String ls_procedencia="";
        String ls_sle_fecha="";
        String ls_codigo_oficina="";
        String ls_codigo_solicitud="";
       
        String ls_medio="";
        String ls_codigo_tipo="";
        String ls_reporte="";
        
        String ls_dirigido="";
        String ls_firmadopor="";
       
        String ls_accion="";
        String ls_mensaje="";
        String usuario_actual="";
        String codigo_oficina="";
        int li_retorno=0;
        String ls_numero_documento="";
		String ls_doc_resp="";
		String ls_asunto_documento="";
		String ls_codigo_motivo="";
		String ls_sle_fecha_inicio="";
		String ls_sle_fecha_fin="";
		
		try {
			
			 
			 
			Connection cnx = getConnection(request, "principal");
			System.out.println("El cnx  ==> " + cnx);
			
			 HttpSession session = request.getSession(true);        
			 Funciones funciones=new Funciones();
		        /*Verificar si la sesion se perdio*/
		        
				 usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
	     		 System.out.println("El usuario actual es.." + usuario_actual);
	     		 codigo_oficina = funciones.of_codigo_oficina(cnx, usuario_actual);
	     		 System.out.println("El codigo de Oficina es.." + codigo_oficina);
     		
	     		 
		         ls_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
		         ls_persona = String.valueOf(session.getAttribute("rs_persona"));
		         ls_seleccion_nat = String.valueOf(session.getAttribute("naturaleza_doc"));
		         
		         ls_accion=request.getParameter("accion");
		         ls_reporte=request.getParameter("reporte");
		         
		         System.out.println("El ls_accion"+ ls_accion);
		         
		        	 System.out.println("Si la accion es DDDDDD...." + ls_accion);
		        	 ls_procedencia=request.getParameter("procedencia");
			         ls_sle_fecha=request.getParameter("sle_fecha");
			         ls_codigo_oficina=request.getParameter("codigo_oficina");
			         ls_codigo_solicitud=request.getParameter("codigo_solicitud");
			         ls_numero_expediente=request.getParameter("numero_expediente");
			         ls_medio=request.getParameter("medio");
			         ls_codigo_tipo=request.getParameter("codigo_tipo");
			         ls_numero_registro=request.getParameter("numero_registro");
			         
			         ls_dirigido=request.getParameter("dirigido");
			         ls_estado=request.getParameter("estado");
			         ls_firmadopor=request.getParameter("firmadopor");
			         
			         System.out.println("El ls_accion"+ ls_accion);
			         System.out.println("El ls_reporte"+ ls_reporte);
			         System.out.println("El ls_oficina"+ ls_oficina);
			         System.out.println("El ls_persona"+ ls_persona);
			         System.out.println("El ls_seleccion_nat"+ ls_seleccion_nat);
			         
			         System.out.println("El ls_procedencia"+ ls_procedencia);
			         System.out.println("El ls_sle_fecha"+ ls_sle_fecha);
			         System.out.println("El ls_codigo_oficina"+ ls_codigo_oficina);
			         System.out.println("El ls_codigo_solicitud"+ ls_codigo_solicitud);
			         System.out.println("El ls_numero_expediente"+ ls_numero_expediente);
			         System.out.println("El ls_medio"+ ls_medio);
			         System.out.println("El ls_codigo_tipo"+ ls_codigo_tipo);
			         System.out.println("El ls_numero_registro"+ ls_numero_registro);
			         
			         System.out.println("El ls_dirigido"+ ls_dirigido);
			         System.out.println("El ls_estado"+ ls_estado);
			         System.out.println("El ls_firmadopor"+ ls_firmadopor);
			         
			        IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			        
			        
			        Collection listaFrameBusqueda = daoIUsuarioDAO.of_busca_documentos_report_general(cnx, codigo_oficina, ls_procedencia,
							ls_sle_fecha, ls_medio, ls_codigo_tipo, ls_codigo_oficina, ls_codigo_solicitud, ls_numero_registro,
							ls_numero_expediente,ls_firmadopor,ls_dirigido,ls_estado, ls_numero_documento, ls_doc_resp, 
							ls_asunto_documento, ls_sle_fecha_inicio, ls_sle_fecha_fin, ls_codigo_motivo);
			        	
			      
			       // session.removeAttribute("rs_mesa_partes");
			        
			        
			        //session.setAttribute("codigo_oficina", codigo_oficina);
			        
			        if(ls_accion.equals("R")){
			        	
			        	session.setAttribute("rs_reporte", listaFrameBusqueda);
			        	return (mapping.findForward("reporte"));
			        }else{
			        	
			        	session.setAttribute("rs_listaDocumentos", listaFrameBusqueda);
			        	return (mapping.findForward("reportesgenerales"));	
			        }
			        

			  
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
}
