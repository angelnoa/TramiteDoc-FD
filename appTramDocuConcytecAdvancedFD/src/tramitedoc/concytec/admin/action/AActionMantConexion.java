package tramitedoc.concytec.admin.action;
import java.io.File;
import java.io.FileOutputStream;
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
import org.apache.struts.upload.FormFile;

import tramitedoc.concytec.dao.*;
import tramitedoc.concytec.admin.form.*;
import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion Mantenimiento de Usuarios  
 */

public class AActionMantConexion extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx............" + cnx);	    
	  	
		IAdministradorDAO daoIAdministradorDAO = new SqlAdministradorDAO();
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		BPersonal detallePersonal=new BPersonal();
		BUsuario detalleUsuario=new BUsuario();
		
		FFormMantConexion oForm = (FFormMantConexion) form;
		
		
		String tipo = oForm.getTipo() == null ? "init" : oForm.getTipo().trim();
		System.out.println("El tipo 1 "+ tipo);
		tipo = tipo.equals("") ? "init" : tipo;
		System.out.println("El tipo 2 "+ tipo);
		
		Collection rs_lista_conexion=null;
		rs_lista_conexion = new ArrayList();
		String usuario;
		String fecha_in;
		String fecha_fin;
		String host;
		String ip;
		
		
		
		session.removeAttribute("rs_lista_conexion");
		
		
		BUsuario usuario_sistema = (BUsuario)session.getAttribute("usuario_sistema");
		fecha_in = ((FFormMantConexion)form).getFecha_conexion();

		
		System.out.println("FEcha de Inicio y Fin "+fecha_in);
		
		fecha_fin = ((FFormMantConexion)form).getFecha_conexion_fin();
	
		
		System.out.println("FEcha de Fin "+fecha_fin);
		
		
		
		try {
			
			if(tipo.equals("buscar")){
				System.out.println("buscar");
				
				rs_lista_conexion = daoIUsuarioDAO.lista_conexion(cnx, fecha_in, fecha_fin);
				
				session.setAttribute("rs_lista_conexion",rs_lista_conexion);
				
			}else if (tipo.equalsIgnoreCase("nuevo")){
			//li_operacion = 2;
				
					
					//session.removeAttribute("operacioncs");
			      			
		}else if (tipo.equalsIgnoreCase("editar")){
			
					
			return mapping.getInputForward();
	
		} else if (tipo.equalsIgnoreCase("eliminar")){

			
			

		} 

		closeConnection(cnx);
		oForm.reset(mapping, request);
		oForm.setTipo("nuevo");
		return (mapping.findForward("exito"));
 
    	   
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
	
	private File parent(File f) {
		String dirname = f.getParent();
		if (dirname == null) {
			return new File(File.separator);
		}
		return new File(dirname);

	}
	
	private boolean makeSureDirectoryExists(File dir) {

		if (!dir.exists()) {
			if (makeSureDirectoryExists(parent(dir)))
				dir.mkdir();
			else
				return false;
		}
		return true;
	}
}
