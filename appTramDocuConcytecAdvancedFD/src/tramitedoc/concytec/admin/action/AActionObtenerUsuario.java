package tramitedoc.concytec.admin.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import tramitedoc.concytec.dao.*;
import tramitedoc.concytec.form.*;
import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;

public class AActionObtenerUsuario extends BaseAjaxAction {
	
    public String getXmlContent(ActionMapping actionMapping,
                                ActionForm actionForm,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
    	
    	
    	
        String cambia = request.getParameter("cambia");
        
        
        if (!esVacio(cambia)) {
        	
        	IAdministradorDAO daoIAdministradorDAO = new SqlAdministradorDAO();
        	
            if (cambia.equals("usu")) {
                String pUsuarios = request.getParameter("c_usuario");
                System.out.println("Listando todos los Usuarios" + pUsuarios);
                return new AjaxXmlBuilder().addItems(daoIAdministradorDAO.of_lista_usuarios(pUsuarios),
                "nombres", "nombres").toString();
            } 
        	if (cambia.equals("lista_nombres")) {
                String pUsuarios = request.getParameter("c_usuario");
                System.out.println("Listando todos los Usuarios" + pUsuarios);
            	Collection listaUsuarios = new ArrayList();
            	PrintWriter out = response.getWriter();
            	
            	listaUsuarios=daoIAdministradorDAO.of_lista_usuarios_nuevo(pUsuarios);
            	if(listaUsuarios!=null && listaUsuarios.size()>0){
	  				Iterator it=listaUsuarios.iterator();
	  				
	  				while(it.hasNext()){
	  					BUsuario userVO =(BUsuario)it.next();
	  					out.printf("<option value='%1s'>%2s</option>", userVO.getC_usuario(), userVO.getNombres().trim());
	  			    }
				}
 
                out.close();
                
            }
        }
        return "";
    }

    static boolean esVacio(String cad) {
        return cad == null || cad.trim().equals("");
    }


}
