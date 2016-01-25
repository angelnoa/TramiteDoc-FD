package tramitedoc.concytec.action;
/*import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import tramitedoc.concytec.dao.*;
import tramitedoc.concytec.admin.form.*;
import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;
import tramitedoc.concytec.form.FFormDocumentoInterno;
*/
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BInfo;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormDocumentoInterno;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;

/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionMantenimientoDocumentoInterno extends Action {
	protected  Log log = LogFactory.getLog(AActionMantenimientoDocumentoInterno.class);
	  public ActionForward execute(ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response)
			    throws Exception
			  {
		  		log.info("Entry AActionMantenimientoDocumentoInterno... ");
		  		response.setCharacterEncoding("UTF-8");
		  		request.setCharacterEncoding("UTF-8");

		  		 HttpSession session = request.getSession(false);   
		  		

			        /*Verificar si la sesion se perdio*/
			        if (session==null){
			        	
			        	return (mapping.findForward("expiracion"));
			        	
			        }   
		  		/*********recupero usuario************/
			    BUsuario userSystem =(BUsuario) session.getAttribute("nombre_usuario");
			    String usuario_actual   =   userSystem.getC_usuario();
			    
			    usuario_actual = (usuario_actual==null)? String.valueOf(session.getAttribute("nombreusuario")):usuario_actual;
			    
			   // usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
				//cargo_personal   =   String.valueOf(session.getAttribute("cargo_personal"));
		  		
		  		//IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		  		DocumentosInternosService service = new DocumentosInternosServiceImplement();	
		  	//	FFormDocumentoInterno fformDocumentoInterno = (FFormDocumentoInterno) form; //TDD
		  		String recibe_codigo="";
		  		String codigo_persona_noborrar = (String) session.getAttribute("codigo_persona_noborrar");
		  		
		  				  		
		  		int  codigo_documento_interno;
		  		
		  		recibe_codigo = ((String)request.getParameter("cod_doc_interno"));
		  		recibe_codigo = (recibe_codigo == null)? "":recibe_codigo;
		  		
		  		if(recibe_codigo.equals("")){
		  			
		  		//String valor = (String) session.getAttribute("existendocumentos");
	    			//valor = (valor == null)? "" : valor;
			  		  		
			  		/*****************************************************************************************************/
	                     int bandeja=1;
	                      int tipo=3;
	                      int codigo_oficina=service.of_codigo_oficina(usuario_actual);
	                      log.info("oficina >"+codigo_oficina);
	                      log.info("codigo personal >"+codigo_persona_noborrar);
	                      ArrayList<DocumentoInternoBean> listaDocumentosInternos=service.getlistaDocumentosInternos(bandeja,codigo_oficina,tipo);
	                      request.getSession().setAttribute("listaDocumentosInternosFirmados",listaDocumentosInternos);
	                     
	                      Collection areasAdministrativasList=service.getLista_oficinas();
	                      request.getSession().setAttribute("areasAdministrativasList",areasAdministrativasList);
	                     
		     			  ArrayList<EstandarBean> tipoDocumentoInternosList=service.getTipoDocumentosInternosLista();	
		     			  log.info("tamnio lista tipoDocumentoInternos:  "+tipoDocumentoInternosList.size());
		     			  request.getSession().setAttribute("tipoDocumentoInternosList",tipoDocumentoInternosList);
		     			   
		     			  ArrayList<EstandarBean> estadoDocumentoInternoList=service.getEstadoDocumentoInternoLista();
		  			      request.getSession().setAttribute("estadoDocumentoInternoList",estadoDocumentoInternoList);
		  			      log.info("tamnio lista estadoDocumentoInternoList:  "+estadoDocumentoInternoList.size());
		  			    //agregado mpelaez
			  		
			  		/************************************************************************************************/
			  		
			  		
	    			//rs_upload_doc_internos_adj = (Collection) session.getAttribute("rs_upload_doc_internos");
	    			
		  			  //request.setAttribute("veruploadarchivo","1");
		  		  		//request.setAttribute("rs_upload_doc_internos",rs_upload_doc_internos_adj);
		  		  		session.setAttribute("codigo_de_oficina_pertenece", codigo_oficina);
		  		  		//session.setAttribute("operacion", "O");
		  		  		session.setAttribute("operacioninterno", "O");
		  		  		session.setAttribute("existendocumentos", "1");
		  		  		log.info("Sali del AActionModificarDocumentoInterno ------ me dirijo a pagina upload");
				    return mapping.findForward("upload");
		  			
		  			
		  			
		  		}else{
		  		
		  		String tipofirma = ((String)request.getParameter("tipofirma"));
		  		String docuinterno = ((String)request.getParameter("interno"));
		  		
		  		tipofirma = (tipofirma == null)? "":tipofirma;
		  		docuinterno = (docuinterno == null)? "":docuinterno;
		  		
		  		codigo_documento_interno = Integer.parseInt(recibe_codigo);
		  				  		
		  		log.info("codigo_documento_interno: "+codigo_documento_interno);
		  		log.info("tipofirma: "+tipofirma);
		  		//DocumentoInternoBean documentoInterno = service.getDocumentoInterno(codigo_documento_interno);
		  		int tipodoc = service.of_tipo_documento_interno(codigo_documento_interno);
		  		log.info("tipodoc = "+tipodoc);
		  		
		  		
		  		if(docuinterno.equals("SI")){
		  			BInfo binfodocu = new BInfo();
		  			BInfo binfodocumanual = new BInfo();
		  			binfodocu = service.getInfoSobreDocumento(codigo_documento_interno);
		  			binfodocumanual=service.getInfoSobreDocumentoFirmadoManualmente(codigo_documento_interno);
		  			
		  			log.info("1.-"+binfodocu.getNombrearchivo());
		  			log.info("2.-"+binfodocumanual.getNombrearchivo());
		  			
		  			if(binfodocumanual.getNombrearchivo().equals(binfodocu.getNombrearchivo().substring(9, binfodocu.getNombrearchivo().length()))){
		  				service.deleteFirmadoManual(binfodocumanual.getId_det_upload());
		  			}
		  			
		  			
		  		}else{
		  		
		  			service.deleteDocumentoInterno(codigo_documento_interno);
		  		
		  		}
		  		/*
		  		if(documentoInterno.getCodigos_oficinas_destinos_copias()!=null && documentoInterno.getCodigos_oficinas_destinos_copias().length()>0)
		  		{
		  			documentoInterno.setCopia_oficinas_destino(documentoInterno.getCodigos_oficinas_destinos_copias().split(","));
		  		}
		  		fformDocumentoInterno.setDocumentoInterno(documentoInterno);
		  		Collection rs_destinatario = daoIUsuarioDAO.of_lista_personas_oficinas(fformDocumentoInterno.getCodigo_oficina().toString());
		  		
		  		request.getSession().setAttribute("rs_destinatario", rs_destinatario);
		  		*/
		  		//log.info("Asunto: "+ fformDocumentoInterno.getAsunto());
		  		
		  		log.info("Sali del AActionMantenimientoDocumentoInterno ... me dirijo a setup");

		  		String pagina="exito";
		  				  		
		  		if(tipofirma.equals("si")){
		  			pagina="exitob";
		  		}
		  		
		  		if(tipodoc==6){
		  			pagina="exitoccp";
		  			if(tipofirma.equals("si")){
			  			pagina="exitoccpb";
		  			}
		  		}
		  		if(tipodoc==7){	  		
		  			pagina="exitotdr";
		  			if(tipofirma.equals("si")){
			  			pagina="exitotdrb";
		  			}
		  		}
		  		
		  		return mapping.findForward(pagina);
		  		
			  }
	
}
}


