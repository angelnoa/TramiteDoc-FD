package tramitedoc.concytec.action;

import java.util.ArrayList;
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

public class CrearListasAction extends BaseAjaxAction {
	
    public String getXmlContent(ActionMapping actionMapping,
                                ActionForm actionForm,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
    	
    	
    	
        String cambia = request.getParameter("cambia");
        
        
        if (!esVacio(cambia)) {
        	
        	IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			
            if (cambia.equals("ofi")) {
                String pOficina = request.getParameter("codigo_oficina");
                System.out.println("Listando todos los oficinas"+pOficina);
                return new AjaxXmlBuilder().addItems(daoIUsuarioDAO.of_lista_personas_oficinas(pOficina),
				"nombre_personal", "codigo_personal").toString();
            } 
            
           /* else if (cambia.equals("ruta")) {
                String pcod_ruta = request.getParameter("cod_ruta");
                System.out.println("Listando todos las rutas"+pcod_ruta);
                return new AjaxXmlBuilder().addItems(daoIUsuarioDAO.of_lista_modalidad_rutas(pcod_ruta),
				"nombre_modalidad", "cod_modalidad").toString();
            }*/ 
        }
        return "";
    }

    static boolean esVacio(String cad) {
        return cad == null || cad.trim().equals("");
    }


}
