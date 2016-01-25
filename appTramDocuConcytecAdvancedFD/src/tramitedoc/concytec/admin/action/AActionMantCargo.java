package tramitedoc.concytec.admin.action;
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
import tramitedoc.concytec.admin.form.*;
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

public class AActionMantCargo extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx............" + cnx);	    
	  	
		IAdministradorDAO daoIAdministradorDAO = new SqlAdministradorDAO();
		BCargo detalleCargo=new BCargo();
		
		FFormMantCargo oForm = (FFormMantCargo) form;
		String tipo = oForm.getTipo() == null ? "init" : oForm.getTipo().trim();
		System.out.println("El tipo 1 "+ tipo);
		tipo = tipo.equals("") ? "init" : tipo;
		System.out.println("El tipo 2 "+ tipo);
		
		String codigo_cargo;
		String nombrecargo;
		
		codigo_cargo = ((FFormMantCargo)form).getCodigo_cargo();
		nombrecargo = ((FFormMantCargo)form).getNombrecargo();
		
		System.out.println("codigo_cargo... "+ codigo_cargo);
		System.out.println("nombrecargo... "+ nombrecargo);
		
		
		try {
			
			
			if (tipo.equals("cargo") ){
				
				System.out.println("Si tipo es init" + tipo);
				System.out.println("Listar Cargos....." );
				
					Collection listacargos = daoIAdministradorDAO.of_lista_cargos(cnx);
					
					System.out.println("listacargos" + listacargos);
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("listacargos",listacargos);
					
					
			}else if (tipo.equalsIgnoreCase("nuevo")){
			//li_operacion = 2;
				try {
		
					System.out.println("Ingresando Cargo .........." );
					nombrecargo=nombrecargo.toUpperCase();
					
					
					daoIAdministradorDAO.CargoIng(cnx,nombrecargo);
									
					Collection listacargos = daoIAdministradorDAO.of_lista_cargos(cnx);
					
					session.setAttribute("listacargos",listacargos);
					
								
				}catch (SQLException e) {
					e.printStackTrace();
					closeConnection(cnx);
					//return mapping.getInputForward();
		
				}
					
					//session.removeAttribute("operacioncs");
			      			
		}else if (tipo.equalsIgnoreCase("editar")){
			try {
	
	
				System.out.println("Modificacion de... CArgos .........." );
				
				nombrecargo=nombrecargo.toUpperCase();
				
				daoIAdministradorDAO.CargoMod(cnx,codigo_cargo,nombrecargo);
		
				Collection listacargos = daoIAdministradorDAO.of_lista_cargos(cnx);
				
				System.out.println("listacargos" + listacargos);
			
				session.setAttribute("operacion",tipo);
				session.setAttribute("listacargos",listacargos);
				
				
		
			}catch (SQLException e) {
				rollbackConnection(cnx);
				e.printStackTrace();
				session.setAttribute("MsjeError", "Error al actualizar al empleado: " + e.getMessage());
				//closeConnection(cnx);
				//return mapping.getInputForward();
	
			}
					
			return mapping.getInputForward();
	
		} else if (tipo.equalsIgnoreCase("eliminar")){

			try {
	
				System.out.println("Eliminar  Cargo.........." );
	
				daoIAdministradorDAO.CargoElim(cnx,codigo_cargo);
				
				Collection listacargos = daoIAdministradorDAO.of_lista_cargos(cnx);
				
				System.out.println("listacargos" + listacargos);
				session.setAttribute("operacion",tipo);
				session.setAttribute("listacargos",listacargos);
				
			}catch (SQLException e) {
				e.printStackTrace();
				closeConnection(cnx);
				//return mapping.getInputForward();
	
			}
			

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
}
