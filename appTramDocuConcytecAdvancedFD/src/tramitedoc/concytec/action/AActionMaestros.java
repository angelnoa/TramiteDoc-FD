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

public class AActionMaestros extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		try {
			
		  	String ls_operacion="";
		  	String tipo = request.getParameter("tipo");
		  	System.out.println("El tipo " + tipo);
		  	
		  	HttpSession session = request.getSession(true);
		  	
			Connection cnx = getConnection(request, "principal");
			System.out.println("El cnx  ==> " + cnx);
			
			if (tipo.equals("usuario")){
				return (mapping.findForward("usuario"));
			}
			else if (tipo.equals("oficina")){
				
			return (mapping.findForward("oficina"));
			}
			else if (tipo.equals("personal")){
							
			return (mapping.findForward("personal"));
			}
			else if (tipo.equals("cargo")){
				
				return (mapping.findForward("cargo"));
				}
			else if (tipo.equals("tipodocumento")){
				
				return (mapping.findForward("tipodocumento"));
				}
			else if (tipo.equals("solicitud")){
				
				return (mapping.findForward("solicitud"));
				}
else if (tipo.equals("documento")){
				
				return (mapping.findForward("documento"));
				}
else if (tipo.equals("conexionusuario")){
	
	return (mapping.findForward("conexionusuario"));
	}



			
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
}
