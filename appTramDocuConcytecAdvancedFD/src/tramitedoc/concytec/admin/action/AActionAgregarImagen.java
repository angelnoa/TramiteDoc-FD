package tramitedoc.concytec.admin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import tramitedoc.concytec.admin.form.FFormCargaArchivo;
import tramitedoc.concytec.admin.form.FFormMantPersonal;
import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IAdministradorDAO;
import tramitedoc.concytec.dao.sql.SqlAdministradorDAO;
import tramitedoc.concytec.form.FFormMantDocumento;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Constantes;
import tramitedoc.concytec.util.UMD5;
import tramitedoc.concytec.util.ValidaSessionAction;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion Mantenimiento de Usuarios  
 */

public class AActionAgregarImagen extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(ValidaSessionAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx............" + cnx);	    
	  	
		IAdministradorDAO daoIUsuarioDAO = new SqlAdministradorDAO();
			
						
		String path = Constantes.CarpetaArchivosDesarrolloSellos.getNombre();
		boolean existeFirma = false;
		
		
		Collection tipo_usuarios =daoIUsuarioDAO.of_lista_tipo_usuarios(cnx);
		session.setAttribute("tipo_usuarios", tipo_usuarios);
		
		try {
			/***cargo operacion ****/
			
		    String ls_operacion = request.getParameter("operacion");
			ls_operacion = (ls_operacion==null)? "" : ls_operacion;
			String inicia = request.getParameter("tipo");
			inicia = (inicia==null)? "" : inicia;
			String vista = "OPE";
			log.info("la ls_operacion: " + ls_operacion);
			/********************/
	  		
	  		int li_operacion=0;
	  		String tipoVistaw;
	  		String oficina_pw;
	  		String c_usuariow;
	  		tipoVistaw = request.getParameter("tipoVistaw");
	       	tipoVistaw = (tipoVistaw==null)? "":tipoVistaw;
	       		
	       	oficina_pw = request.getParameter("oficina_pw");
	       	oficina_pw = (oficina_pw==null)? "":oficina_pw;
     		
	       	c_usuariow = request.getParameter("c_usuariow");
	       	c_usuariow = (c_usuariow==null)? "":c_usuariow;
	       	
	       
	  		
	  		if(inicia.equals("I")){	
	  			String codigodelaoficina = request.getParameter("cod");
	  			codigodelaoficina = (codigodelaoficina==null)? "":codigodelaoficina;
	  			System.out.println("namecodigodelaoficina "+codigodelaoficina);
	  			
	  			
	  			FFormCargaArchivo myForm = (FFormCargaArchivo) form;
	  			
	  			myForm.setTheFile(null);
	  			myForm.setTheFilePfx(null);
	  			session.removeAttribute("nuevo_nombre_sello_oficina");
	  				//
	  			String c_usuario_ = (String) session.getAttribute("c_usuario_p"); 
	  			request.setAttribute("c_usuario_", c_usuario_);
	  			
	  		    log.info("Sali del AActionAgregarImagen ------ me dirijo a pagina upload");
	  		  	if(ls_operacion.equals("UPO")){
	  		  		vista = "OFI";
	  		  		
	  		  		String oficina_p_ = (String) session.getAttribute("nombre_corto_ls"); 
	  		  		request.setAttribute("oficina_p_", oficina_p_);
	  		  		session.setAttribute("locked", codigodelaoficina);

	  		  	}

	  		  	request.setAttribute("tipoVista", vista);
	  		  	
			    return mapping.findForward("upload");

	  		}else{
	  		
	  			if (ls_operacion.equals("DEL")){
	  				li_operacion = 1;
	  			}else if (ls_operacion.equals("UA")){
	  				li_operacion = 2;
	  			}else if (ls_operacion.equals("UAPx")){
	  				li_operacion = 3;
	  			}else if (ls_operacion.equals("EDIF")){
	  				li_operacion = 4;
	  			}else if (ls_operacion.equals("MOD")){
	  				li_operacion = 5;
	  			}    

	  			switch(li_operacion){
	  			case 1:
	  				

	  				return mapping.findForward("exito");

	  			case 2:
	  				log.info("Entre aqui a adjuntar documento");
	  	       		log.info("Adjuntar multiples documentos.." );
	  	       		log.info("Listar UploadMultiples Ficheros Archivos..." );			
	  					
	  	       			FFormCargaArchivo myForm = (FFormCargaArchivo) form;
	  					FormFile myFile = myForm.getTheFile();
	  	       		
	  	       			  	       		
	  					String contentType = myFile.getContentType();
	  					log.info("el contentType FILE UPLOAd es.."
	  							+ contentType);

	  					if(!myFile.getFileName().equals("")){
	  						log.info("el fileName es.."+ myFile.getFileName());
	  						
	  					}
	  					session.setAttribute("vaRmyFile", myFile);
	  					session.setAttribute("nuevo_nombre_sello_oficina", myFile.getFileName());
	  					request.setAttribute("tipoVista", tipoVistaw);
	  					request.setAttribute("oficina_p", oficina_pw);
	  					request.setAttribute("c_usuario_", c_usuariow);

	  				return (mapping.findForward("upload"));
	  				
	  			case 3:
	  				log.info("Entre a opcion UAPx .........");
	  				tipoVistaw = request.getParameter("tipoVistaw");
	  	       		tipoVistaw = (tipoVistaw==null)? "":tipoVistaw;
	  	       		
	  				FFormCargaArchivo myFormPx = (FFormCargaArchivo) form;
  					
  					FormFile myFilePx = myFormPx.getTheFilePfx();
  					String contentTypepx = myFilePx.getContentType();
  					log.info("el contentType FILE UPLOAd es.."
  							+ contentTypepx);

  					if(!myFilePx.getFileName().equals("")){
  						log.info("el fileName es.."+ myFilePx.getFileName());
  						
  					}
  					session.setAttribute("vaRmyFilePx", myFilePx);
  					request.setAttribute("tipoVista", tipoVistaw);
  					request.setAttribute("oficina_p", oficina_pw);
  					request.setAttribute("c_usuario_", c_usuariow);
  					
	  				return (mapping.findForward("upload"));
	  				
	  			case 4:
	  				//request.setAttribute("caso", "caso__11");
	  	        	log.info(">>>>>>>>>>>>>DENTRO DE ELIMINAR DOCUMENTO INTERNO FIRMADO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  	        	log.info(">>>>>>>>>>>>>ESTE PROCESO SE REALIZARA INTERNAMENTE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  	        	log.info(">>>>>>>>>>>>>LLEGAMOS AQUI AL ESCOJER EL DOCUMENTO DE LA LISTA IMAGINARIA EN BANDEJA POPUP DE CREACION DE DOCUMENTOS INTERNOS<<<<<<");
	  	        	BArchivo archivodocinternoeliminar = new BArchivo();
	  	        	String nombre=(String)request.getParameter("nombre");
	  	        	//String codigo_documento_eliminar=(String)request.getParameter("cod_doc_interno");
	  							 log.info("nombre >"+nombre);
	  	        	Collection rs_upload_doc_internos_eliminar = new ArrayList();	
	  	        	Collection temporal = new ArrayList();
	  	        	
	  	        	rs_upload_doc_internos_eliminar = (Collection) session.getAttribute("rs_upload_doc_internos");
	  	        		
	  	        	
	  		  		if(rs_upload_doc_internos_eliminar!=null && rs_upload_doc_internos_eliminar.size()>0){
	  		  			log.info("aqui1");
	  		  			Iterator it=rs_upload_doc_internos_eliminar.iterator();
	  		  		log.info("aqui2");
	  		  				while(it.hasNext()){
	  		  				log.info("aqui3");
	  		  					BArchivo archivoenlista=(BArchivo)it.next();
	  		  										
	  		  					if(!archivoenlista.getNombre_archivo().equals(nombre)){
	  		  					log.info("aqui4");
	  		  						temporal.add(archivoenlista);	
	  		  						log.info(archivoenlista.getNombre_archivo());
	  		  					}
	  		  				}
	  		  				if(temporal.isEmpty()){
	  		  					log.info("aqui5");
	  		  					session.setAttribute("rs_upload_doc_internos", null);
	  		  					session.setAttribute("cantidad_lista",0);
	  		  				}else{
	  		  				log.info("aqui6");
	  		  					session.setAttribute("rs_upload_doc_internos", temporal);
	  		  					session.setAttribute("cantidad_lista", temporal.size());
	  		  				}
	  		  				
	  		  			}
	  		  		
	  				return (mapping.findForward("upload"));
	  				
	  				
	  			case 5:
	  				
	  		  		
	  				return (mapping.findForward("upload"));
	  			}

	  			return (mapping.findForward("error"));

	  		} 
	  		}
        catch (Exception e) {
            e.printStackTrace();
        }finally{
        	
            	closeConnection(cnx);	
            
        }
        
       	return (mapping.findForward("error"));
	}
	private String encriptarClave(String clave, UMD5 mo) {
		//aqui se tiene que encriptar la clave:
		/*
		 * agregado por mpelaezs:05_01_2012
		 * */
		try{
			//UMD5 md = UMD5.getInstance();
			
			log.info("clave:"+clave);
			clave=mo.hashData(clave.getBytes()).toLowerCase();
			log.info("clave:"+clave);
			}catch(Exception e){
				e.printStackTrace();
			}
		/*fin
		 * */
		return clave;
	}
	
	private File parent(File f) {
		String dirname = f.getParent();
		if (dirname == null) {
			return new File(File.separator);
		}
		return new File(dirname);

	}
	
	private boolean makeSureDirectoryExists(File dir) {

		if (!dir.exists()) {
			if (makeSureDirectoryExists(parent(dir)))
				dir.mkdir();
			else
				return false;
		}
		return true;
	}
}