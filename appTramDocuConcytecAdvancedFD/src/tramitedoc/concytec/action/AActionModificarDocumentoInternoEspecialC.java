package tramitedoc.concytec.action;



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
import tramitedoc.concytec.bean.BInfoDocumentoProyecto;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormDocumentoInternoEspecialC;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.BarradeProgreso;


public class AActionModificarDocumentoInternoEspecialC extends Action {
	protected  Log log = LogFactory.getLog(AActionModificarDocumentoInternoEspecialC.class);
	  public ActionForward execute(ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response)
			    throws Exception
			  {
		  		log.info("Entry AActionModificarDocumentoInternoEspecialC... ");
		  		response.setCharacterEncoding("UTF-8");
		  		request.setCharacterEncoding("UTF-8");
		  		 HttpSession session = request.getSession(false);        

			        /*Verificar si la sesion se perdio*/
			        if (session==null){
			        	
			        	return (mapping.findForward("expiracion"));
			        	
			        }   
			  	/*****REMUEVO LA LISTA DE DOC FIRMADOS INTERNOS y OTROS****/
			  	session.removeAttribute("rs_upload_doc_internos");
			  	session.removeAttribute("cantidad_lista");
			  	
            	session.setAttribute("verLista", "0");
           		session.setAttribute("contador", "0");
           		String usuario_actual =   String.valueOf(session.getAttribute("nombreusuario"));
			  	/**************************************************/
		  		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		  		DocumentosInternosService service = new DocumentosInternosServiceImplement();	
		  		FFormDocumentoInternoEspecialC fformDocumentoInterno = (FFormDocumentoInternoEspecialC) form; //TDD
		  		fformDocumentoInterno.setArchivos(null);
		  		
		  		Collection rs_upload_doc_internos = new ArrayList();
		  		
		  		int  codigo_documento_interno = 0;
		  		int  es_admitido=0;
				
		  		ArrayList<EstandarBean> tipoRequerimientoList=null;
		  		ArrayList<EstandarBean> estadoDocumentoInternoListSecretaria=null;
			    tipoRequerimientoList=service.getTipoRequerimientoInternoLista();
			    estadoDocumentoInternoListSecretaria=service.getEstadoDocumentoInternoListaSecretarias();

			    
			    
			    
		  		try {
		  		
		  		String variable =((String)request.getParameter("cod_doc_interno"));
		  		variable=(variable==null)?"":variable;
		  		if(!variable.equals("") && variable.length() > 0){
		  			codigo_documento_interno = Integer.valueOf(variable);
		  			log.info("codigo_documento_interno --> "+codigo_documento_interno);
		  		}
		  		
		  		
		  		String tipo_documento ="ccp";
		  		
		  		String codigo_documento_interno_respuesta = (String) session.getAttribute("ls_codigoDocumentoInterno"); 
		  		codigo_documento_interno_respuesta=(codigo_documento_interno_respuesta==null)?"":codigo_documento_interno_respuesta;
		  		
		  		if(codigo_documento_interno == 0){
		  		if(!codigo_documento_interno_respuesta.equals("") && codigo_documento_interno_respuesta.length() > 0){
		  			codigo_documento_interno = Integer.valueOf(codigo_documento_interno_respuesta);
		  			log.info("codigo_documento_interno --> "+codigo_documento_interno);
		  		}
		  		}
		  		
		  		/***cargo operacion ****/
			    String ls_operacion = request.getParameter("operacion");
				log.info("la ls_operacion: " + ls_operacion);
				int li_operacion=0;			
				ls_operacion = (ls_operacion==null)? "" : ls_operacion;
				/********************/

	  			if (ls_operacion.equals("DDI")){
	  				li_operacion = 1;
	  			}
	  			else if (ls_operacion.equals("")){
	  				li_operacion = 2;
	  			}
	  			
	  			DocumentoInternoBean documentoInterno = new DocumentoInternoBean();
	  			Collection rs_destinatario = new ArrayList();
	  			
	  			switch(li_operacion){
	  			case 1:
	  				log.info("codigo_documento_interno: "+codigo_documento_interno);
		  		
	  				log.info("/*****RESCATAR VARIABLES....1 ****/");
		  		documentoInterno = service.getDocumentoInterno(codigo_documento_interno,tipo_documento);
		  	
		  		if(documentoInterno.getCodigos_oficinas_destinos_copias()!=null && documentoInterno.getCodigos_oficinas_destinos_copias().length()>0){
		  			documentoInterno.setCopia_oficinas_destino(documentoInterno.getCodigos_oficinas_destinos_copias().split(","));
		  		}

		  		/*******OBTENGO DATOS DE TABLA DINAMICA********/
		  		
		  		
		  		/**************FIN TABLAS DINAMICAS ***********/
		  		
		  		fformDocumentoInterno.setDocumentoInterno(documentoInterno);
		  		rs_destinatario = daoIUsuarioDAO.of_lista_personas_oficinas(fformDocumentoInterno.getCodigo_oficina().toString());
		  		
		  		/*****CONTINUO CON CARGA DE TABLAS *********/
		  		
		  		
		  		
		  		
		  		/**************FIN *************************/

		  		request.getSession().setAttribute("rs_destinatario", rs_destinatario);
		  		
		  		log.info("/****************************OBTENGO LA LISTA DE DOC ADJUNTOS***************/");
		  		rs_upload_doc_internos = service.getListaArchivosAdjuntos(codigo_documento_interno);
		  		
		  		if(rs_upload_doc_internos!=null && rs_upload_doc_internos.size()>0){
		  					session.setAttribute("backup_rs_upload_doc_internos", rs_upload_doc_internos);
		  			  	    session.setAttribute("rs_upload_doc_internos",rs_upload_doc_internos);
		  			  	    session.setAttribute("cantidad_lista",rs_upload_doc_internos.size());
		  			  	    request.setAttribute("actualizadjuntos","1");
		  		}
		  		else
		  				session.setAttribute("cantidad_lista",0);
		  		
		  		
		  		log.info("Cheeck es > "+fformDocumentoInterno.getChecked());
		  	   /****************************************************************************/
		  		
		  		
		  		
		  		request.setAttribute("verbotonescreacion", "1");//para la vista pag_creaciondocinterno
		  		session.setAttribute("ls_respuesta", "xxxx"); //estatico
		  		request.getSession().setAttribute("tipoRequerimientoList", tipoRequerimientoList);//LISTA DE REQUERIMIENTOS
		  		request.getSession().setAttribute("estadoDocumentoInternoListSecretaria",estadoDocumentoInternoListSecretaria);
		  		
		  		log.info("Sali del AActionModificarDocumentoInternoEspecialC opearcion");
			    return mapping.findForward("exito");
			 
	  			case 2:
	  				/***
	  				 * AGREGADO PARA PROYECTO
	  				 */
				    String tipo_pi_ls = request.getParameter("tipo_pi");
					log.info("la tipo_pi_ls: " + tipo_pi_ls);
					tipo_pi_ls = (tipo_pi_ls==null)? "0" : tipo_pi_ls;
					
					if(tipo_pi_ls.equals("1") || tipo_pi_ls.equals("2")){
						request.setAttribute("es_admiitido", es_admitido);
					}
					
					/***
					 * FIN
					 */
					
					/***
					 * PROYECTOS A EDITAR DERIVACION
					 */
					String cod_proy_ls = request.getParameter("cod_proy");
					/***
					 * FIN
					 */
					
					log.info("/*****RESCATAR VARIABLES ....2 ****/");
			  		
	  				if(tipo_pi_ls.equals("2")){
	  					
	  					codigo_documento_interno= Integer.valueOf(cod_proy_ls.trim());
	  					log.info("rescatando proyecto derivacion "+codigo_documento_interno);
	  				}
	  				documentoInterno = service.getDocumentoInterno(codigo_documento_interno,tipo_documento);
	  			  	
			  		if(documentoInterno.getCodigos_oficinas_destinos_copias()!=null && documentoInterno.getCodigos_oficinas_destinos_copias().length()>0){
			  			documentoInterno.setCopia_oficinas_destino(documentoInterno.getCodigos_oficinas_destinos_copias().split(","));
			  		}
			  		
			  		
			  		/*******OBTENGO DATOS DE TABLA DINAMICA********/
			  		
			  		
			  		/**************FIN TABLAS DINAMICAS ***********/
			  		
			  		fformDocumentoInterno.setDocumentoInterno(documentoInterno);
			  		rs_destinatario = daoIUsuarioDAO.of_lista_personas_oficinas(fformDocumentoInterno.getCodigo_oficina().toString());
			  		
			  		/*****CONTINUO CON CARGA DE TABLAS *********/
			  		
			  		
			  	request.getSession().setAttribute("rs_destinatario", rs_destinatario);	
	  			
			  	log.info("/****************************OBTENGO LA LISTA DE DOC ADJUNTOS***************/");
		  		rs_upload_doc_internos = service.getListaArchivosAdjuntos(codigo_documento_interno);
		  		
		  		if(rs_upload_doc_internos!=null && rs_upload_doc_internos.size()>0){
		  					session.setAttribute("backup_rs_upload_doc_internos", rs_upload_doc_internos);
		  			  	    session.setAttribute("rs_upload_doc_internos",rs_upload_doc_internos);
		  			  	    session.setAttribute("cantidad_lista",rs_upload_doc_internos.size());
		  			  	    request.setAttribute("actualizadjuntos","1");
		  		}
		  		else
		  				session.setAttribute("cantidad_lista",0);
		  		
		  		
		  		log.info("Cheeck es > "+fformDocumentoInterno.getChecked());
		  	   /****************************************************************************/
		  		/***
		  		 * AGREGADO PARA PROYECTO
		  		 */
		  		if(fformDocumentoInterno.getChecked()){
		  			request.setAttribute("valorchecked", "1");
		  		}else{
		  			request.setAttribute("valorchecked", "0");
		  		}
		  		request.setAttribute("valoropciones", "1");
		  		
		  		int codigo_oficina=service.of_codigo_oficina(usuario_actual);
		  		if(tipo_pi_ls.equals("1")){
		  			fformDocumentoInterno.setChecked(true);
		  			request.setAttribute("valorchecked", "1");
		  			
				    String firmado_por=service.of_nombre_director_oficina(codigo_oficina);	
		  			fformDocumentoInterno.setFirmado_por(firmado_por);
		  			fformDocumentoInterno.setCodigo_oficina_pertenece(codigo_oficina);
		  		}
		  		
		  		if(tipo_pi_ls.equals("2")){
		  			/***
		  			 * RECUPERANDO VALORES CODIGOS, SECUENCIA
		  			 */
		  			String numreg_ls = request.getParameter("numreg");
		  			ArrayList<BInfoDocumentoProyecto> myLista = new ArrayList<BInfoDocumentoProyecto>();
		  			BInfoDocumentoProyecto resultado= new BInfoDocumentoProyecto();
		  			
		  			myLista = service.lista_derivacion_documentos_buscar(codigo_oficina,numreg_ls);
		  			
		  			if(!myLista.isEmpty()){
		  				Iterator it=myLista.iterator();
		  				while(it.hasNext()){
		  					resultado =(BInfoDocumentoProyecto)it.next();
		  				}	
		  			}	
		  			/**
		  			 * FIN		
		  			 */
		  			log.info("ls_codigo_documento "+resultado.getCodigo_documento());
		  			log.info("ls_codigo_expediente "+resultado.getCodigo_expediente());
		  			log.info("ls_codigo_recepcion "+ resultado.getCodigo_recepcion());
		  			log.info("ls_secuencia "+ resultado.getSecuencia_movimiento());
		  			
		  			fformDocumentoInterno.setCodigo_documento(Integer.valueOf(resultado.getCodigo_documento().trim()));
		  			fformDocumentoInterno.setCodigo_expediente(Integer.valueOf(resultado.getCodigo_expediente().trim()));
		  			fformDocumentoInterno.setCodigo_recepcion(Integer.valueOf(resultado.getCodigo_recepcion().trim()));
		  			fformDocumentoInterno.setSecuencia_movimiento(Integer.valueOf(resultado.getSecuencia_movimiento().trim()));
		  			
		  			fformDocumentoInterno.setChecked(true);
		  			request.setAttribute("valorchecked", "1");

				    String firmado_por=service.of_nombre_director_oficina(codigo_oficina);	
		  			fformDocumentoInterno.setFirmado_por(firmado_por);
		  			fformDocumentoInterno.setCodigo_oficina_pertenece(codigo_oficina);
		  			
		  			
				    
		  		}
		  		
		  		request.setAttribute("es_proyecto", tipo_pi_ls);
		  		/**
		  		 * FIN	
		  		 */
		  		
		  		/*if(numerofilas>1){
		  			request.setAttribute("numerofilas", numerofilas-1);//para la vista pag_creaciondocinterno
		  		}*/
		  		
		  		request.setAttribute("verbotonescreacion", "1");//para la vista pag_creaciondocinterno
		  		request.getSession().setAttribute("tipoRequerimientoList", tipoRequerimientoList);//LISTA DE REQUERIMIENTOS
		  		request.getSession().setAttribute("estadoDocumentoInternoListSecretaria",estadoDocumentoInternoListSecretaria);
		  		
		  		log.info("Sali del AActionModificarDocumentoInternoEspecialC");
			    return mapping.findForward("exito");
			    
	  			} 
			  }catch (Exception e) {
		            e.printStackTrace();
			 }
	
	  		return mapping.findForward("exito");
	}
}

