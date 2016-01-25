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
import tramitedoc.concytec.admin.form.FFormMantCargo;
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

public class AActionMantUsuarioClave extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true);
		Connection cnx = null;
		
		String ls_operacion = request.getParameter("operacion");
		System.out.println("la ls_operacion: " + ls_operacion);
		
		String usuario;
		String clave;
		String clavenueva;
		String confirmarclavenueva;
		String ls_mensaje="";
		
		
		usuario = ((FFormMantUsuarioClave)form).getUsuario().trim();
		clave = ((FFormMantUsuarioClave)form).getClave().trim();
		clavenueva = ((FFormMantUsuarioClave)form).getClavenueva();
		confirmarclavenueva = ((FFormMantUsuarioClave)form).getConfirmarclavenueva();
		
		System.out.println("usuario... "+ usuario);
		System.out.println("clave... "+ clave);
		System.out.println("clavenueva... "+ clavenueva);
		System.out.println("confirmarclavenueva... "+ confirmarclavenueva);
		
		
		try {
			
			cnx = getConnection(request, "principal");
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
       	     
       	     //daoIUsuarioDAO.of_modificar_usuarioclave(cnx,descripcion,tipopersona);
				
				System.out.println("Despues del Ingreso Procedencia y Direccionando" );	
				        	     	      			
				return (mapping.findForward("exito"));	
       	   
       	 case 2:
        	 	
                System.out.println("Modificacion de... procedencias.........." );
               
                
                	System.out.println("Si las claves son iguales");
                	daoIUsuarioDAO.of_modificar_usuarioclave(cnx,usuario,clave,clavenueva,confirmarclavenueva);
                    
     				return (mapping.findForward("exito"));
               
               	
       	 case 3:
         	 	
             
       		//daoIUsuarioDAO.of_eliminar_procedencia(cnx,codigo,tipopersona);
       		
				return (mapping.findForward("exito"));	
       	 }
    	   
        } 
        catch (Exception e) {
            e.printStackTrace();
        }finally{
        	closeConnection(cnx);	
        }
       	return (mapping.findForward("error"));
	}
}
