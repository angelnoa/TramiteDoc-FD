/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tramitedoc.concytec.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import tramitedoc.concytec.form.*;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.dao.sql.*;
import tramitedoc.concytec.util.*;
/**
 *
 * @author eswar@vaannila.com
 */
public class UserAction extends  ValidaSessionAction {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success1";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //UserForm userForm = (UserForm) form;
        FFormMantSeguimiento formMantSeg = (FFormMantSeguimiento) form;
        
        IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
        Funciones funciones=new Funciones();
        
       
        
        Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx  ==> " + cnx);
		
		//String codigo_oficina = funciones.of_codigo_oficina(cnx, usuario_actual);
		
		String ls_procedencia=request.getParameter("procedencia");
		String ls_sle_fecha=request.getParameter("sle_fecha");
		String ls_medio=request.getParameter("medio");
		String ls_codigo_tipo=request.getParameter("codigo_tipo");
		
		String ls_codigo_oficina=request.getParameter("codigo_oficina");
		String ls_codigo_solicitud=request.getParameter("codigo_solicitud");
		String ls_codigo_documento=request.getParameter("codigo_documento");
		String ls_codigo_expediente=request.getParameter("codigo_expediente");
		String ls_firmadopor=request.getParameter("firmadopor");
		String ls_dirigido=request.getParameter("dirigido");
		String ls_estado=request.getParameter("estado");
		String ls_numero_documento=request.getParameter("numero_documento");
		String ls_doc_resp=request.getParameter("doc_resp");
        
        Collection listaFrameBusqueda = daoIUsuarioDAO.of_busca_documentos(cnx, "2", ls_procedencia,
				ls_sle_fecha, ls_medio, ls_codigo_tipo, ls_codigo_oficina, ls_codigo_solicitud, ls_codigo_documento,
				ls_codigo_expediente,ls_firmadopor,ls_dirigido,ls_estado,ls_numero_documento,ls_doc_resp,"","","","","","");			 
		
				
				//System.out.println("El listaFrameBusqueda es .."+ listaFrameBusqueda);
        
        formMantSeg.setSeguimientoList(listaFrameBusqueda);
        
        /*ActorData actorData = new ActorData();
        userForm.setActorList(actorData.loadData());*/
        
				//session.setAttribute("rs_lista_todos",listaFrameBusqueda);
        
        
        
        return mapping.findForward(SUCCESS);
    }
}
