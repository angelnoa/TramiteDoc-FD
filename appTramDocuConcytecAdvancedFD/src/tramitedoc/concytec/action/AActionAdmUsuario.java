// original
package tramitedoc.concytec.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import tramitedoc.concytec.dao.interfaces.ILoginDAO;
import tramitedoc.concytec.dao.sql.SqlLoginDAO;
import tramitedoc.concytec.util.UMD5;
import tramitedoc.concytec.util.ValidaSessionAction;





public class AActionAdmUsuario 	extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(AActionAdmUsuario.class);	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UMD5 md5 = UMD5.getInstance();
		   String tipo=(String)request.getParameter("tipo");
		   HttpSession session = request.getSession(true);
		   if(tipo.equals("salir")){
			   //System.out.println("Ingreso a salir");
			   session.invalidate();
			   //return (mapping.findForward("pagina_index"));
			   return (mapping.findForward("pagina_init"));
			   
		   }else if(tipo.equals("inicio")){
		    	return (mapping.findForward("pagina_inicio"));
		    		
		    }else if(tipo.equals("cambiar_password")){
		    	log.info("Entry cambiar_password ...");
		    	String usuario=request.getParameter("usuario");
		    	String password=request.getParameter("password");
		    	String new_password=request.getParameter("new_password");
		    	String confirm_new_password=request.getParameter("confirm_new_password");
		    	String mensaje ="";
		    	
		    	System.out.println(usuario+password+new_password+confirm_new_password);
		    	
		    	ILoginDAO dao=new SqlLoginDAO();
		    	new_password=md5.hashData(new_password.getBytes()).toLowerCase();
		    	int resultado = dao.cambiarPassword(usuario,new_password);
		    	
		    	if(resultado>0){
		    		mensaje ="Se ha realizado correctamente el cambio de contraseña";
		    	}else{
		    		mensaje ="No se ha realizado correctamente el cambio de contraseña";
		    	}
		    	
		    	request.setAttribute("mensaje_sistema",mensaje);
		    	return (mapping.findForward("pagina_mensaje"));
		    }
		    return (mapping.findForward("default"));
			
    }
	
	
}
