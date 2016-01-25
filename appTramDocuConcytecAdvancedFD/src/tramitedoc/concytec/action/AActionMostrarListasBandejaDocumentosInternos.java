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

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BDatosFirmante;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IControlBandejasService;
import tramitedoc.concytec.form.FFormBusquedaDocumentoInterno;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.service.IControlBandejasServiceImplement;


public class AActionMostrarListasBandejaDocumentosInternos extends Action {
	protected  Log log = LogFactory.getLog(AActionMostrarListasBandejaDocumentosInternos.class);
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
	    	if (session==null){	        	
	        	return (mapping.findForward("expiracion"));
	        }
	    	
	    	String valor = (String) request.getParameter("cambia");
	    	String codigo_documento_internop = (String) request.getParameter("codigo_documento_interno");
	    	String tipo_accion = (String) request.getParameter("tipo_accion");
	    	
	    	IControlBandejasService serviceBandejas = new IControlBandejasServiceImplement();
	    	DocumentosInternosService service = new DocumentosInternosServiceImplement();
	    	int bandeja=0;
	    	String 	usuario_actual =   String.valueOf(session.getAttribute("nombreusuario"));
	    	
	    	/**
        	 * debo diferenciar entre secreatria, director y usuario normal
        	 */
	    	/*Verificar si la sesion se perdio*/
	        //else{

	        	Collection areasAdministrativasList=service.getLista_oficinas();
			    request.getSession().setAttribute("areasAdministrativasList",areasAdministrativasList);
			    ArrayList<EstandarBean> tipoDocumentoInternosList=service.getTipoDocumentosInternosLista();	
			    request.getSession().setAttribute("tipoDocumentoInternosList",tipoDocumentoInternosList);
			    
			    ArrayList<EstandarBean> estadoDocumentoInternoList=null;
			    estadoDocumentoInternoList=service.getEstadoDocumentoInternoLista();
			    request.getSession().setAttribute("estadoDocumentoInternoList",estadoDocumentoInternoList);//PARA LOS JEFES
	        	
	        	
	        	
	        	if(valor.equals("1")){
	        		/***BANDEJA DOCUMENTOS INTERNOS NORMALES CREADOS***/
	        		log.info("Entry.......1");

	        		//rs_recepcion_doc_atencion_urgente = serviceBandejas.lista_recepcion_documentos_normal_bandejas((String) session.getAttribute("codigo_oficina"),1);
	        		request.getSession().setAttribute("tipo_bandeja","1");
	        		ArrayList<DocumentoInternoBean> listaDocumentosInternosFull=service.getlistaDocumentosInternos(bandeja,Integer.parseInt((String) session.getAttribute("codigo_oficina")),901);
		  			request.getSession().setAttribute("listaDocumentosInternosFull",listaDocumentosInternosFull);
	        	}
	        	
	        	if(valor.equals("2")){
	        		/***BANDEJA DOCUMENTOS INTERNOS NORMALES PROYECTOS***/
	        		log.info("Entry.......2");

	        		ArrayList<DocumentoInternoBean> listaDocumentosInternosMisProyectos=service.getlistaDocumentosInternos(bandeja,Integer.parseInt((String) session.getAttribute("codigo_oficina")),27);
		  			request.getSession().setAttribute("listaDocumentosInternosProyectos",listaDocumentosInternosMisProyectos);
		  			ArrayList<DocumentoInternoBean> listaDocumentosInternosProyectos=service.getlistaDocumentosInternos(7,Integer.parseInt((String) session.getAttribute("codigo_oficina")),31);
		  			request.getSession().setAttribute("listaDocumentosInternosProyectosRecibidos",listaDocumentosInternosProyectos);
		  			request.getSession().setAttribute("tipo_bandeja","2");
	        	}
	        	if(valor.equals("3")){
	        		/***BANDEJA DOCUMENTOS INTERNOS REQUERIMIENTO CREADOS***/
	        		log.info("Entry.......3");
	        		
	        		request.getSession().setAttribute("tipo_bandeja","1");
	        		ArrayList<DocumentoInternoBean> listaDocumentosInternosFull=service.getlistaDocumentosInternos(bandeja,Integer.parseInt((String) session.getAttribute("codigo_oficina")),801);
		  			request.getSession().setAttribute("listaDocumentosInternosFull",listaDocumentosInternosFull);
	        		
	        		
	  				/*ArrayList<DocumentoInternoBean> listaDocumentosInternosN=service.getlistaDocumentosInternos(bandeja,Integer.parseInt((String) session.getAttribute("codigo_oficina")),15);
		  			request.getSession().setAttribute("listaDocumentosInternosN",listaDocumentosInternosN);
		  			ArrayList<DocumentoInternoBean> listaDocumentosInternosD=service.getlistaDocumentosInternos(bandeja,Integer.parseInt((String) session.getAttribute("codigo_oficina")),16);
		  			request.getSession().setAttribute("listaDocumentosInternosD",listaDocumentosInternosD);
		  			ArrayList<DocumentoInternoBean> listaDocumentosInternosR=service.getlistaDocumentosInternos(bandeja,Integer.parseInt((String) session.getAttribute("codigo_oficina")),17);
		  			request.getSession().setAttribute("listaDocumentosInternosR",listaDocumentosInternosR);*/
		  			

	        	}
	        	if(valor.equals("4")){
	        		/***BANDEJA DOCUMENTOS INTERNOS REQUERIMIENTO PROYECTOS***/
	        		log.info("Entry.......4");

	        		ArrayList<DocumentoInternoBean> listaDocumentosInternosMisProyectos=service.getlistaDocumentosInternos(bandeja,Integer.parseInt((String) session.getAttribute("codigo_oficina")),28);
		  			request.getSession().setAttribute("listaDocumentosInternosProyectos",listaDocumentosInternosMisProyectos);
		  			ArrayList<DocumentoInternoBean> listaDocumentosInternosProyectos=service.getlistaDocumentosInternos(7,Integer.parseInt((String) session.getAttribute("codigo_oficina")),32);
		  			request.getSession().setAttribute("listaDocumentosInternosProyectosRecibidos",listaDocumentosInternosProyectos);
		  			request.getSession().setAttribute("tipo_bandeja","2");
	        	}
	        	if(valor.equals("upload")){
	        		/***BANDEJA DOCUMENTOS INTERNOS REQUERIMIENTO PROYECTOS***/
	        		log.info("Entry.......upload "+codigo_documento_internop);
	        		Collection rs_upload_doc_internos = new ArrayList();
	        		String valorVerEstado = (String) request.getParameter("estado");
	        		valorVerEstado=(valorVerEstado==null)? "":valorVerEstado;
	        		if(valorVerEstado.equals("ver")){
	        			request.setAttribute("verEstado", "1");
	        		}
	        		if(valorVerEstado.equals("verfirmarequerida")){
	        			request.setAttribute("verEstado", "3");
	        		}
	        		if(valorVerEstado.equals("vervbrequerida")){
	        			request.setAttribute("verEstado", "4");
	        			request.setAttribute("codigo_documento_vb_requerido", codigo_documento_internop);
	        		}
	        		
	        		rs_upload_doc_internos = service.getListaArchivosAdjuntos(Integer.parseInt(codigo_documento_internop));
			  		
			  		if(rs_upload_doc_internos!=null && rs_upload_doc_internos.size()>0){
			  					//session.setAttribute("backup_rs_upload_doc_internos", rs_upload_doc_internos);
			  			  	    request.setAttribute("rs_upload_doc_internos",rs_upload_doc_internos);
			  			  	    request.setAttribute("cantidad_lista",rs_upload_doc_internos.size());
			  			  	    //request.setAttribute("actualizadjuntos","1");
			  			  	    
			  			  	if(valorVerEstado.equals("vervbrequerida")){
			  			  		session.setAttribute("rs_upload_doc_internos_vb_requerido", rs_upload_doc_internos);
			  			  	}
			  		}
			  		else
			  			session.setAttribute("cantidad_lista",0);
			  		

	        		return mapping.findForward("upload");
	        	}
	        	if(valor.equals("uploadprincipal")){
	        		/***BANDEJA DOCUMENTOS INTERNOS REQUERIMIENTO PROYECTOS***/
	        		log.info("Entry.......uploadprincipal "+codigo_documento_internop);
	        		Collection rs_upload_doc_internos = new ArrayList();
	        		String valorVerEstado = (String) request.getParameter("estado");
	        		valorVerEstado=(valorVerEstado==null)? "":valorVerEstado;
	        		if(valorVerEstado.equals("ver")){
	        			request.setAttribute("verEstado", "2");
	        		}
	        		
	        		rs_upload_doc_internos = service.getArchivoPrincipal(Integer.parseInt(codigo_documento_internop),0);
			  		
			  		if(rs_upload_doc_internos!=null && rs_upload_doc_internos.size()>0){
			  					//session.setAttribute("backup_rs_upload_doc_internos", rs_upload_doc_internos);
			  			  	    request.setAttribute("rs_upload_doc_internos",rs_upload_doc_internos);
			  			  	    request.setAttribute("cantidad_lista",rs_upload_doc_internos.size());
			  			  	    //request.setAttribute("actualizadjuntos","1");
			  		}
			  		else
			  			session.setAttribute("cantidad_lista",0);
			  		

	        		return mapping.findForward("upload");
	        	}
	        	if(valor.equals("firma")){
	        		/***BANDEJA DOCUMENTOS INTERNOS REQUERIMIENTO PROYECTOS***/
	        		log.info("Entry.......firma ");
	        		int valor_vandeja = 5;
			  		log.info("usuario_actual >>> "+usuario_actual);
		  			/***
		  			 * CREADO PARA SABER SU CONFIGURACION PERSONAL DE FIRMA DIGITAL
		  			 */
		  			BDatosFirmante resultado= new BDatosFirmante();
		  			resultado = service.getDatosFirmante(usuario_actual);
		  			
		  			valor_vandeja = (resultado.getTipo_firma() == 2)? 8:valor_vandeja;
		  			
		  			if(valor_vandeja==5){
		  				valor_vandeja=9;
		  			}
		  			
		  			tipo_accion=(tipo_accion==null)? "":tipo_accion;
		  			
		  			
		  			
		  			if(tipo_accion.equals("modifica")){
		  				log.info("Entry.......modifica "+codigo_documento_internop);
		  				ArrayList<DocumentoInternoBean> listaDocumentosInternosFullTemp = (ArrayList<DocumentoInternoBean>) request.getSession().getAttribute("listaDocumentosInternosFullTempIFrame");
		  				ArrayList<DocumentoInternoBean> temporaln = new ArrayList<DocumentoInternoBean>();
		  				
		  				if(listaDocumentosInternosFullTemp!=null && listaDocumentosInternosFullTemp.size()>0){
		  		  			log.info("aqui1");
		  		  			Iterator it=listaDocumentosInternosFullTemp.iterator();
		  		  			log.info("aqui2");
		  		  				while(it.hasNext()){
		  		  				log.info("aqui8");
		  		  				DocumentoInternoBean archivoenlista=(DocumentoInternoBean)it.next();
		  		  							
		  		  					if(archivoenlista.getCodigo_documento_interno()==Integer.parseInt(codigo_documento_internop)){
		  		  					log.info("aqui9");
		  		  					log.info("nombre bean a modificar >"+archivoenlista.getCodigo_documento_interno());
		  		  						boolean firma_previa = archivoenlista.isPara_firma_previa();
		  		  						
		  		  						if(firma_previa){
		  		  							
		  		  							log.info("nombre bean a modificar >"+archivoenlista.getCodigo_documento_interno()+"  false");
		  		  							archivoenlista.setPara_firma_previa(false);
		  		  							archivoenlista.setLink3("<input  type='checkbox' name='selectedItems' value='"+archivoenlista.getCodigo_documento_interno()+"' onclick=ModificarEstado('"+archivoenlista.getCodigo_documento_interno()+"') >");
		  		  						}else{
		  		  							
		  		  						log.info("nombre bean a modificar >"+archivoenlista.getCodigo_documento_interno()+"  true");
		  		  							archivoenlista.setPara_firma_previa(true);
		  		  							archivoenlista.setLink3("<input  type='checkbox' name='selectedItems' checked value='"+archivoenlista.getCodigo_documento_interno()+"' onclick=ModificarEstado('"+archivoenlista.getCodigo_documento_interno()+"') >");
		  		  						}

		  		  						//log.info(archivoenlista.getNombre_arhivo());
		  		  					}
		  		  					temporaln.add(archivoenlista);	
		  		  				}
		  		  			request.getSession().setAttribute("listaDocumentosInternosFullIFrame", temporaln);
		  		  			request.getSession().setAttribute("listaDocumentosInternosFullTempIFrame",temporaln);
		  		  		}
		  				request.getSession().setAttribute("inicia_firma", "0");
		  			}else{
		  				if(tipo_accion.equals("habilitaTodo")){

			  				log.info("Entry.......modifica habilitaTodo");
			  				ArrayList<DocumentoInternoBean> listaDocumentosInternosFullTemp = (ArrayList<DocumentoInternoBean>) request.getSession().getAttribute("listaDocumentosInternosFullTempIFrame");
			  				ArrayList<DocumentoInternoBean> temporaln = new ArrayList<DocumentoInternoBean>();
			  				
			  				if(listaDocumentosInternosFullTemp!=null && listaDocumentosInternosFullTemp.size()>0){
			  		  			log.info("aqui1");
			  		  			Iterator it=listaDocumentosInternosFullTemp.iterator();
			  		  			log.info("aqui2");
			  		  				while(it.hasNext()){
				  		  				log.info("aqui8");
				  		  				DocumentoInternoBean archivoenlista=(DocumentoInternoBean)it.next();
		  		  						boolean firma_previa = archivoenlista.isPara_firma_previa();
		  		  						
		  		  						if(!firma_previa){
		  		  						log.info("nombre bean a modificar >"+archivoenlista.getCodigo_documento_interno()+"  true");
	  		  							archivoenlista.setPara_firma_previa(true);
	  		  							archivoenlista.setLink3("<input  type='checkbox' name='selectedItems' checked value='"+archivoenlista.getCodigo_documento_interno()+"' onclick=ModificarEstado('"+archivoenlista.getCodigo_documento_interno()+"') >");
		  		  						}
		  		  						//log.info(archivoenlista.getNombre_arhivo());

			  		  					temporaln.add(archivoenlista);	
			  		  				}
			  		  			request.getSession().setAttribute("listaDocumentosInternosFullIFrame", temporaln);
			  		  			request.getSession().setAttribute("listaDocumentosInternosFullTempIFrame",temporaln);
			  		  		}
			  				request.getSession().setAttribute("inicia_firma", "1");
			  			
		  				}else{
		  					if(tipo_accion.equals("deshabilitaTodo")){

				  				log.info("Entry.......modifica deshabilitaTodo");
				  				ArrayList<DocumentoInternoBean> listaDocumentosInternosFullTemp = (ArrayList<DocumentoInternoBean>) request.getSession().getAttribute("listaDocumentosInternosFullTempIFrame");
				  				ArrayList<DocumentoInternoBean> temporaln = new ArrayList<DocumentoInternoBean>();
				  				
				  				if(listaDocumentosInternosFullTemp!=null && listaDocumentosInternosFullTemp.size()>0){
				  		  			log.info("aqui1");
				  		  			Iterator it=listaDocumentosInternosFullTemp.iterator();
				  		  			log.info("aqui2");
				  		  				while(it.hasNext()){
					  		  				log.info("aqui8");
					  		  				DocumentoInternoBean archivoenlista=(DocumentoInternoBean)it.next();
			  		  						boolean firma_previa = archivoenlista.isPara_firma_previa();
			  		  						
			  		  						if(firma_previa){
			  		  						log.info("nombre bean a modificar >"+archivoenlista.getCodigo_documento_interno()+"  false");
			  		  							archivoenlista.setPara_firma_previa(false);
			  		  							archivoenlista.setLink3("<input  type='checkbox' name='selectedItems' value='"+archivoenlista.getCodigo_documento_interno()+"' onclick=ModificarEstado('"+archivoenlista.getCodigo_documento_interno()+"') >");
			  		  						}
			  		  						//log.info(archivoenlista.getNombre_arhivo());
				  		  					temporaln.add(archivoenlista);	
				  		  				}
				  		  			request.getSession().setAttribute("listaDocumentosInternosFullIFrame", temporaln);
				  		  			request.getSession().setAttribute("listaDocumentosInternosFullTempIFrame",temporaln);
				  		  		}
				  				request.getSession().setAttribute("inicia_firma", "0");
				  			
		  					}else{
		  						log.info("Valor de bandeja ....firma_masiva: "+valor_vandeja);
		  						ArrayList<DocumentoInternoBean> listaDocumentosInternosFull=service.getlistaDocumentosInternos(valor_vandeja,Integer.parseInt((String) session.getAttribute("codigo_oficina")),900,1);
				  				request.getSession().setAttribute("listaDocumentosInternosFullIFrame",listaDocumentosInternosFull);
				  				request.getSession().setAttribute("listaDocumentosInternosFullTempIFrame",listaDocumentosInternosFull);
				  				request.getSession().setAttribute("inicia_firma", "0");
				  				log.info("Out bandeja ....firma_masiva: ");
		  					}
		  				}
		  				
		  			}
		  			
	  							  		
	  				log.info("Out firmma_masiva...");
	        		return mapping.findForward("firmma_masiva");
	        	}

	        //}
	       
	        return mapping.findForward("exito");
	    }

	    
	}




