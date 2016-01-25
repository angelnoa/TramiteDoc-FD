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

public class AActionCargaInicial extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
	
		try {
			
			String ls_oficina = request.getParameter("oficina");
			String ls_persona = request.getParameter("persona");
			
			 System.out.println("El ls_oficina"+ ls_oficina);
			 System.out.println("El ls_persona"+ ls_persona);
			 
			Connection cnx = getConnection(request, "principal");
			System.out.println("El cnx  ==> " + cnx);
			
			IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			
			BUsuario userSystem = new BUsuario();
			
			 HttpSession session = request.getSession(false);        

		        /*Verificar si la sesion se perdio*/
		        if (session==null){
		        	
		        	return (mapping.findForward("expiracion"));
		        	
		        }   
		        
		        //HttpSession session = request.getSession(true);  
		        
		        Collection rs_series = daoIUsuarioDAO.of_lista_series(cnx,ls_oficina);
		        System.out.println("El rs_series"+ rs_series);
		        Collection rs_tipodoc = daoIUsuarioDAO.of_lista_documentos(cnx, ls_oficina);
		        Collection rs_tipodoc_todos = daoIUsuarioDAO.of_lista_tipdoc_todos(cnx);
		        BOficinas NombreOficina = daoIUsuarioDAO.of_nombre_oficina(cnx,ls_oficina);
		        
		        String ls_codigo_oficina = NombreOficina.getCodigo_oficina();
		        String ls_descripcion_oficina = NombreOficina.getDescripcion_oficina();
		        System.out.println("El ls_codigo_oficina"+ ls_codigo_oficina);
		        System.out.println("El ls_descripcion_oficina"+ ls_descripcion_oficina);
		        
		        BPersonal DatosUsuario = daoIUsuarioDAO.of_datos_usuario(cnx,ls_persona,ls_codigo_oficina);
		        
		        String usuario= DatosUsuario.getC_usuario();
		        String clave= DatosUsuario.getClave();
		        System.out.println("El usuario"+ usuario);
		        System.out.println("El clave"+ clave);
		        Collection rs_personas_nuestraoficina = daoIUsuarioDAO.of_lista_personas_oficinas(ls_oficina);
		        Collection rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx);
		        Collection rs_motivos = daoIUsuarioDAO.of_lista_motivos(cnx);
		        Collection rs_correlativos = daoIUsuarioDAO.of_lista_correlativos(cnx,ls_oficina);
		        Collection rs_solicitud = daoIUsuarioDAO.of_lista_solicitud(cnx);
		        
		        
		        session.removeAttribute("rs_accion");
		        session.removeAttribute("rs_series");
	            session.removeAttribute("rs_tipodoc");
	            session.removeAttribute("rs_tipodoc_todos");
	            session.removeAttribute("codigo_oficina"); 
	            session.removeAttribute("nombre_oficina"); 
	            session.removeAttribute("nombre_personal"); 
	            session.removeAttribute("rs_personas_nuestraoficina"); 
	            session.removeAttribute("rs_oficina"); 
	            session.removeAttribute("rs_motivos"); 
	            session.removeAttribute("rs_correlativos"); 
	            session.removeAttribute("rs_solicitud");
	            session.removeAttribute("rs_persona");
	            session.removeAttribute("rs_persona");
	            session.removeAttribute("valorsecret");
	            session.removeAttribute("clave");
	            
	            if(ls_codigo_oficina.equals("2")){
		        	
	            	System.out.println("El codigo de oficina es secretaria" + ls_codigo_oficina);
	            	
		        	String ls_valor = "S";
		        	session.setAttribute("valorsecret",ls_valor);
		        	
		        }

	            session.setAttribute("rs_series", rs_series);
	            session.setAttribute("rs_tipodoc", rs_tipodoc);
	            session.setAttribute("rs_tipodoc_todos", rs_tipodoc_todos);
	            session.setAttribute("codigo_oficina", ls_codigo_oficina);
	            session.setAttribute("nombre_oficina", ls_descripcion_oficina);
	            session.setAttribute("nombre_personal", DatosUsuario.getNombre_personal() );
	            session.setAttribute("rs_personas_nuestraoficina", rs_personas_nuestraoficina);
	            session.setAttribute("rs_oficina", rs_oficina);
	            session.setAttribute("rs_motivos", rs_motivos);
	            session.setAttribute("rs_correlativos", rs_correlativos);
	            session.setAttribute("rs_solicitud", rs_solicitud);
	            session.setAttribute("nombreusuario",usuario);
	            session.setAttribute("clave",clave);
	            session.setAttribute("rs_persona",ls_persona);
	            
	            
	        	return (mapping.findForward("mesapartes"));
		        
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
}
