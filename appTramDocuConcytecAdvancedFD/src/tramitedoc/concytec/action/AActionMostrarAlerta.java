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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BMotivo;
import tramitedoc.concytec.bean.BPersona;
import tramitedoc.concytec.bean.BPersonal;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;

public class AActionMostrarAlerta extends BaseAjaxAction {
		protected  Log log = LogFactory.getLog(AActionMostrarAlerta.class);
	    public String getXmlContent(ActionMapping actionMapping,
	                                ActionForm actionForm,
	                                HttpServletRequest request,
	                                HttpServletResponse response) throws Exception {
	    	
	    	response.setContentType("text/html;charset=UTF-8");
	    	response.setCharacterEncoding("UTF-8");
	  		request.setCharacterEncoding("UTF-8");
	    	HttpSession session = request.getSession(false);        
	    	String valor = (String) request.getParameter("valorBandeja");
	    	
	    	/*************PARA RESCATAR EL DOCUMENTO INTERNO********/
			
			DocumentosInternosService service = new DocumentosInternosServiceImplement();
			
			/*****************/
	    	/**
        	 * debo diferenciar entre secreatria, director y usuario normal
        	 */
	    	/*Verificar si la sesion se perdio*/
	        if (session==null){
	        	
	        	PrintWriter out = response.getWriter();
	        	out.printf("Nada");
	        	out.close();
	        	 
	        }else{
	        	IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
	        	PrintWriter out = response.getWriter();
	        	
	        	if(valor.equals("0")){
	        		
	        		int[] cantidades =new int[6];
	        		String ls_cargo_personal = (String)session.getAttribute("cargo_personal");
	        		ls_cargo_personal=(ls_cargo_personal==null)? "":ls_cargo_personal.trim();
	        		//log.info("--ls_cargo_personal "+ls_cargo_personal);
	        		String accesoaoficinas=service.getAccesosaOficinas((String)session.getAttribute("codigo_persona"));
					if(accesoaoficinas != null){
						accesoaoficinas=(String)session.getAttribute("codigo_oficina")+","+accesoaoficinas;
					}else{
						accesoaoficinas=(String)session.getAttribute("codigo_oficina");
					}
	        		
		        	cantidades = daoIUsuarioDAO.total_control_recepcion_documentos((String) session.getAttribute("codigo_oficina"), (String)session.getAttribute("codigo_persona"),accesoaoficinas);
		        	out.print(cantidades[0]+"&nbsp;<img  src='img/email-receive-icon.png' onclick='enlazaporrecibir();' style='vertical-align: middle; width:64px; height:64px;  border:0px;'>&nbsp; Por Recibir");
		        	//int cantidadBandejaRecepcion = daoIUsuarioDAO.total_recepcion_documentos((String) session.getAttribute("codigo_oficina"));
		        	
    				session.setAttribute("cantidadBandejaRecepcion", cantidades[0]);
    				session.setAttribute("cantidadBandejaTramite", cantidades[1]);
    				session.setAttribute("cantidadBandejaAtencionUrgente", cantidades[2]);
    				session.setAttribute("cantidadBandejaFechaRptaPlazo", cantidades[3]);
    				session.setAttribute("cantidadBandejaFechaRptaLimite", cantidades[4]);
    				session.setAttribute("cantidadBandejaFechaRptaFuera", cantidades[5]);
    				
	                
	        	}
	        	if(valor.equals("1")){
	        		out.print(session.getAttribute("cantidadBandejaAtencionUrgente") +"&nbsp;<img  src='img/email-alert-icon.png'  style='vertical-align: middle; width:64px; height:64px;  border:0px;'>&nbsp; Atencion Urgente");
	        	}
	        	if(valor.equals("2")){
	        		out.print(session.getAttribute("cantidadBandejaFechaRptaPlazo")+"&nbsp;<img  src='img/icon-calendar-verde-2013.png'  style='vertical-align: middle; width:48px; height:48px;  border:0px;'>&nbsp; Plazo");
	        	}
	        	if(valor.equals("3")){
	        		out.print(session.getAttribute("cantidadBandejaFechaRptaLimite")+"&nbsp;<img  src='img/icon-calendar-amarillo-2013.png'  style='vertical-align: middle; width:48px; height:48px;  border:0px;'>&nbsp; Limite");
	        	}
	        	if(valor.equals("4")){
	        		out.print(session.getAttribute("cantidadBandejaFechaRptaFuera")+"&nbsp;<img  src='img/icon-calendar-2013.png'  style='vertical-align: middle; width:48px; height:48px;  border:0px;'>&nbsp; Vencido");
	        	}
	        	if(valor.equals("5")){
	        		out.print(session.getAttribute("cantidadBandejaTramite")+"&nbsp;<img  src='img/email-info-icon.png'  onclick='enlazaentramite();' style='vertical-align: middle; width:64px; height:64px;  border:0px;'>&nbsp; en Tramite");
	        	}

                out.close();

	        }
	        return "";
	    }

	    
	}

