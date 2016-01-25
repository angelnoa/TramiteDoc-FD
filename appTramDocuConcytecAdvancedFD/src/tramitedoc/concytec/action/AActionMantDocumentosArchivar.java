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

public class AActionMantDocumentosArchivar extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		System.out.println("Dentro de Archivar documentos");
		
		HttpSession session = request.getSession(true);
		Connection cnx = null;
		
		String ls_operacion = request.getParameter("operacion");
		System.out.println("la ls_operacion: " + ls_operacion);
		
		String fecha;
		String hora;
		String ls_observacion;
		String ls_secuencia;
		String ls_oficina=null;
		String ls_usuario=null;
		String ls_documento=null;
		 int li_retorno=0;
		 
		 ls_documento = String.valueOf(session.getAttribute("documento"));
        ls_secuencia = String.valueOf(session.getAttribute("secuencia"));
        ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
        
        ls_observacion = ((FFormMantDocumentosArchivar)form).getSle_observacion();
		 
		System.out.println("ls_documento... "+ ls_documento);
		System.out.println("ls_secuencia... "+ ls_secuencia);
		System.out.println("ls_oficina... "+ ls_oficina);
		System.out.println("ls_observacion... "+ ls_observacion);
		
		ls_observacion=ls_observacion.toUpperCase();
		
		System.out.println("ls_observacion.UPERCASE.. "+ ls_observacion);
		
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
       	     	System.out.println("Archivar Documento.........." );
       	     	
       	     daoIUsuarioDAO.of_archivar(cnx,ls_documento,ls_oficina,ls_secuencia,ls_observacion,"" );
				
				System.out.println("Despues de Archivar Documento" );	
				        	     	      			
				return (mapping.findForward("exito"));	
       	   
       	 case 2:
        	 	
                System.out.println("Modificacion de... Anexos.........." );
               
              
				return (mapping.findForward("exito"));	
       	 case 3:
         	 	
             
                
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
