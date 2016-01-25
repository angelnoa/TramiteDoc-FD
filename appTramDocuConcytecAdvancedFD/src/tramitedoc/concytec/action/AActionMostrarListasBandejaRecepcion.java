package tramitedoc.concytec.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.dao.interfaces.IControlBandejasService;
import tramitedoc.concytec.service.IControlBandejasServiceImplement;


public class AActionMostrarListasBandejaRecepcion extends Action {
	protected  Log log = LogFactory.getLog(AActionMostrarListasBandejaRecepcion.class);
	  public ActionForward execute(ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response)
			    throws Exception
			  {
	    	
	    	response.setContentType("text/html;charset=UTF-8");
	    	response.setCharacterEncoding("UTF-8");
	  		request.setCharacterEncoding("UTF-8");
	  		
	    	HttpSession session = request.getSession(false);        
	    	String valor = (String) request.getParameter("cambia");
	    	IControlBandejasService serviceBandejas = new IControlBandejasServiceImplement();
	    	
	    	
	    	/**
        	 * debo diferenciar entre secreatria, director y usuario normal
        	 */
	    	/*Verificar si la sesion se perdio*/
	        if (session==null){
	        	
	        	/*PrintWriter out = response.getWriter();
	        	out.printf("Nada");
	        	out.close();*/
	        	 
	        }else{
	        	//IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
	        	Collection rs_recepcion_doc_atencion_urgente = new ArrayList();
	        	if(valor.equals("1")){
	        		/***ATENCION URGENTE***/
	        		log.info("Entry.......1");

	        		rs_recepcion_doc_atencion_urgente = serviceBandejas.lista_recepcion_documentos_normal_bandejas((String) session.getAttribute("codigo_oficina"),1);
	        		session.setAttribute("rs_recepcion_doc_bandejas", rs_recepcion_doc_atencion_urgente);
	        		session.setAttribute("nombre_columna_tabla", "Fecha Registro");
	        	}
	        	
	        	if(valor.equals("2")){
	        		/***ATENCION PLAZO***/
	        		log.info("Entry.......2");

	        		rs_recepcion_doc_atencion_urgente = serviceBandejas.lista_recepcion_documentos_normal_bandejas((String) session.getAttribute("codigo_oficina"),2);
	        		session.setAttribute("rs_recepcion_doc_bandejas", rs_recepcion_doc_atencion_urgente);
	        		session.setAttribute("nombre_columna_tabla", "Fecha de Rpta");
	        	}
	        	if(valor.equals("3")){
	        		/***ATENCION LIMITE***/
	        		log.info("Entry.......3");

	        		rs_recepcion_doc_atencion_urgente = serviceBandejas.lista_recepcion_documentos_normal_bandejas((String) session.getAttribute("codigo_oficina"),3);
	        		session.setAttribute("rs_recepcion_doc_bandejas", rs_recepcion_doc_atencion_urgente);
	        		session.setAttribute("nombre_columna_tabla", "Fecha de Rpta");
	        	}
	        	if(valor.equals("4")){
	        		/***ATENCION VENCIDO***/
	        		log.info("Entry.......4");

	        		rs_recepcion_doc_atencion_urgente = serviceBandejas.lista_recepcion_documentos_normal_bandejas((String) session.getAttribute("codigo_oficina"),4);
	        		session.setAttribute("rs_recepcion_doc_bandejas", rs_recepcion_doc_atencion_urgente);
	        		session.setAttribute("nombre_columna_tabla", "Fecha de Rpta");
	        	}



	        }
	       
	        return mapping.findForward("exito");
	    }

	    
	}



