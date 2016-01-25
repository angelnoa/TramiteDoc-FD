package tramitedoc.concytec.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;

import tramitedoc.concytec.dao.*;
import tramitedoc.concytec.form.*;
import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;

public class AActionCrearListasSeguimiento extends BaseAjaxAction {
	protected  Log log = LogFactory.getLog(AActionCrearListasSeguimiento.class);
    @SuppressWarnings("unused")
	public String getXmlContent(ActionMapping actionMapping,
                                ActionForm actionForm,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
    	
    	response.setContentType("text/html;charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");
  		request.setCharacterEncoding("UTF-8");
  		HttpSession session = request.getSession(false); 
  		
  		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
  		JSONObject obj = new JSONObject();
        String variableInput2= request.getParameter("term");
        String variableInputTipo= request.getParameter("tipo");
        String variableInputInstitucion= request.getParameter("institucion");
        
        
        variableInput2=(variableInput2==null)? "":variableInput2;
        variableInputTipo=(variableInputTipo==null)? "":variableInputTipo;
        variableInputInstitucion=(variableInputInstitucion==null)? "":variableInputInstitucion;
        
        log.info("variableInput2 "+variableInput2);
        log.info("variableInput2 "+variableInputTipo);
        log.info("variableInputInstitucion "+variableInputInstitucion);

		//log.info("daoIUsuarioDAO.of_procedencia_listar("+variableInput2+");");
        if(variableInputTipo.equals("dirigido")){
        	obj = daoIUsuarioDAO.of_dirigido_listar(variableInput2);
        }else{
        	if(variableInputTipo.equals("firmadox")){
            	obj = daoIUsuarioDAO.of_firmadox_listar(variableInput2);
            }else{
            	if(variableInputTipo.equals("personal")){
            		obj = daoIUsuarioDAO.of_dirigido_listar(variableInput2,variableInputInstitucion);
            	}else{
            		obj = daoIUsuarioDAO.of_procedencia_listar(variableInput2);	
            	}
            	
            }
        	//obj = daoIUsuarioDAO.of_procedencia_listar(variableInput2);	
        }
        
        PrintWriter out = response.getWriter();
        out.printf(obj.toString());
        out.close();
        
        return "";
    }

    static boolean esVacio(String cad) {
        return cad == null || cad.trim().equals("");
    }


}