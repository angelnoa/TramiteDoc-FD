package tramitedoc.concytec.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.struts.upload.FormFile;

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BOficinas;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.form.FFormDocumentoInterno;
import tramitedoc.concytec.form.FFormMantDocumento;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Constantes;

public class AActionAdjuntarDocumentoInterno extends Action {
	protected  Log log = LogFactory.getLog(AActionAdjuntarDocumentoInterno.class);
	  public ActionForward execute(ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response)
			    throws Exception {
	  		log.info("Entry AActionAdjuntarDocumentoInterno... ");
	  		response.setCharacterEncoding("UTF-8");
	  		request.setCharacterEncoding("UTF-8");
	  		
	  		HttpSession session = request.getSession(true);   
	  		

		        /*Verificar si la sesion se perdio*/
		        if (session==null){
		        	
		        	return (mapping.findForward("expiracion"));
		        	
		        }   
		   
		    try{   
	  		/*********recupero usuario************/
		    BUsuario userSystem =(BUsuario) session.getAttribute("nombre_usuario");
		    String usuario_actual   =   userSystem.getC_usuario();
		    
		    usuario_actual = (usuario_actual==null)? String.valueOf(session.getAttribute("nombreusuario")):usuario_actual;
		    
		   // usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
			//cargo_personal   =   String.valueOf(session.getAttribute("cargo_personal"));
	  		/***cargo operacion ****/
		    String ls_operacion = request.getParameter("operacion");
			log.info("la ls_operacion: " + ls_operacion);
						
			ls_operacion = (ls_operacion==null)? "" : ls_operacion;
			/********************/

			String recibe_codigo="";
	  		int li_operacion=0;
	  		int  codigo_documento_interno;
	  		
	  		DocumentosInternosService service = new DocumentosInternosServiceImplement();
	  		String codigo_persona_noborrar = (String) session.getAttribute("codigo_persona_noborrar");
	  		recibe_codigo = ((String)request.getParameter("cod_doc_interno"));
	  		
	  		recibe_codigo = (recibe_codigo == null)? "":recibe_codigo;
	  		
	  		if(recibe_codigo.equals("") && ls_operacion.equals("")){
	  			
	  		//String valor = (String) session.getAttribute("existendocumentos");
    			//valor = (valor == null)? "" : valor;

                      int bandeja=2;
                      int tipo=30;
                      int codigo_oficina=service.of_codigo_oficina(usuario_actual);
                      log.info("oficina >"+codigo_oficina);
                      log.info("codigo personal >"+codigo_persona_noborrar);
                      
                      ArrayList<DocumentoInternoBean> listaDocumentosInternos=service.getlistaDocumentosInternos(bandeja,codigo_oficina,tipo);
                      request.getSession().setAttribute("listaDocumentosInternosProyectos",listaDocumentosInternos);
                     
                      Collection areasAdministrativasList=service.getLista_oficinas();
                      request.getSession().setAttribute("areasAdministrativasList",areasAdministrativasList);
                     
	     			  ArrayList<EstandarBean> tipoDocumentoInternosList=service.getTipoDocumentosInternosLista();	
	     			  request.getSession().setAttribute("tipoDocumentoInternosList",tipoDocumentoInternosList);
	     			   
	     			  ArrayList<EstandarBean> estadoDocumentoInternoList=service.getEstadoDocumentoInternoLista();
	  			      request.getSession().setAttribute("estadoDocumentoInternoList",estadoDocumentoInternoList); 
    			
	  			  //request.setAttribute("veruploadarchivo","1");
	  		  		//request.setAttribute("rs_upload_doc_internos",rs_upload_doc_internos_adj);
	  		  		session.setAttribute("codigo_de_oficina_pertenece", codigo_oficina);
	  		  		//session.setAttribute("operacion", "O");
	  		  		session.setAttribute("operacioninterno", "O");
	  		  		session.setAttribute("existendocumentos", "1");
	  		  		log.info("Sali del AActionAdjuntarDocumentoInterno ------ me dirijo a pagina upload");
			    return mapping.findForward("upload");

	  		}else{
	  		
	  			if (ls_operacion.equals("DEL")){
	  				li_operacion = 1;
	  			}else if (ls_operacion.equals("UA")){
	  				li_operacion = 2;
	  			}else if (ls_operacion.equals("RI-MODIFICADOPARADOCUMENTOS-PROYECTOS")){
	  				li_operacion = 3;
	  			}else if (ls_operacion.equals("EDIF")){
	  				li_operacion = 4;
	  			}else if (ls_operacion.equals("MOD")){
	  				li_operacion = 5;
	  			}else if (ls_operacion.equals("RI")){
	  				li_operacion = 6;
	  			}    

	  			switch(li_operacion){
	  			case 1:
	  				codigo_documento_interno = Integer.parseInt(recibe_codigo);

	  				log.info("codigo_documento_interno: "+codigo_documento_interno);
	  				int tipodoc = service.of_tipo_documento_interno(codigo_documento_interno);
	  				log.info("tipodoc = "+tipodoc);
	  				service.deleteDocumentoInterno(codigo_documento_interno);
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

	  				log.info("Sali del AActionAdjuntarDocumentoInterno ... me dirijo a setup");


	  				String pagina="exito";

	  				if(tipodoc==6){
	  					pagina="exitoccp";
	  				}
	  				if(tipodoc==7){	  		
	  					pagina="exitotdr";
	  				}

	  				return mapping.findForward(pagina);

	  			case 2:
	  				log.info("Entre aqui a adjuntar documento");
	  	       		//request.setAttribute("caso", "caso__5");
	  	       		log.info("Adjuntar multiples documentos.." );
	  	       		log.info("Listar UploadMultiples Ficheros Archivos..." );			
	  	
	  				 
	  	       	/*********************Upload de Ficheros*****************/
	  				 BArchivo archivo = new BArchivo();
	  					
	  				 try{
	  					archivo.setUsuario(usuario_actual);
	  					FFormDocumentoInterno myForm = (FFormDocumentoInterno) form;
	  					
	  					FormFile myFile = myForm.getFileupload();
	  					String contentType = myFile.getContentType();
	  					log.info("el contentType FILE UPLOAd es.."
	  							+ contentType);

	  					if(!myFile.getFileName().equals("")){
	  						archivo.setNombre_archivo(myFile.getFileName());
	  						log.info("el fileName es.."
	  								+ archivo.getNombre_archivo());

	  						archivo.setData(myFile.getFileData());
	  						if (myForm.getArchivos() == null) {
	  							log.info("opcion1");
	  							Collection archivos = new ArrayList();
	  							archivos.add(archivo);
	  							myForm.setArchivos(archivos);
	  						} else {
	  							log.info("Opcion2");
	  							myForm.getArchivos().add(archivo);
	  						}
	  						request.setAttribute("hayDocumentosAdjuntos", "1");
	  					}
	  				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
	  				return (mapping.findForward("upload"));
	  				
	  			case 3:
	  				
	  				log.info(">>>>>>>>>>>>>DENTRO DE ADJUNTAR DOCUMENTO INTERNO FIRMADO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  	        	log.info(">>>>>>>>>>>>>ESTE PROCESO SE REALIZARA INTERNAMENTE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  	        	log.info(">>>>>>>>>>>>>LLEGAMOS AQUI AL ESCOJER EL DOCUMENTO DE LA BANDEJA POPUP DE CREACION DE DOCUMENTOS INTERNOS<<<<<<<<<<<<<<<<<");
	  	        	
	  	        	//request.setAttribute("caso", "caso__10");
	  	       		log.info(" Entre a Adjuntar Documentos Internos....................... " );
	  	       	
	  	       		/*********************************/
	  	       		/*********************Upload de Ficheros*****************/
	  				 BArchivo archivodocinterno = new BArchivo();
	  				 int  codigo_documento_interno_firmado=Integer.parseInt((String)request.getParameter("codigo"));
	  				 DocumentoInternoBean documento_interno_firm = service.getDocumentoInterno(codigo_documento_interno_firmado,"adjunta");
	  				 int conta=0;
	  				 
	  				 archivodocinterno.setUsuario(usuario_actual); //CARGO EL BEAN QUE GUARDARA LOS DATOS QUE GRABARE DESPUES
	  				 archivodocinterno.setNombre_archivo(documento_interno_firm.getNombre_arhivo()); //grabo el nombre en el bean archivo
	  				 archivodocinterno.setIsfirmado(true);
	  				 archivodocinterno.setTipo_archivo(documento_interno_firm.getCodigo_documento_interno().toString());
	  				 log.info("el fileName es.."+ archivodocinterno.getNombre_archivo());
	  					
	  				 Collection rs_upload_doc_internos_temp = new ArrayList();	
	  		  		
	  		  		 rs_upload_doc_internos_temp = (Collection) session.getAttribute("rs_upload_doc_internos");
	  		  			
	  		  		if(rs_upload_doc_internos_temp!=null && rs_upload_doc_internos_temp.size()>0){
	  		  				Iterator it=rs_upload_doc_internos_temp.iterator();
	  		  				while(it.hasNext()){
	  		  					BArchivo archivoenlista=(BArchivo)it.next();
	  		  					if(archivoenlista.getNombre_archivo().equals(archivodocinterno.getNombre_archivo())){
	  		  						conta+=1;
	  		  					}
	  		  				
	  		  				}
	  		  			}
	  		  		if(rs_upload_doc_internos_temp==null){
	  		  			Collection nuevo_doc_internos = new ArrayList();
	  		  			nuevo_doc_internos.add(archivodocinterno);
	  		  			session.setAttribute("rs_upload_doc_internos", nuevo_doc_internos);
	  		  			session.setAttribute("cantidad_lista",nuevo_doc_internos.size());
	  		  		}else{
	  		  			if(conta==0){
	  		  				rs_upload_doc_internos_temp.add(archivodocinterno); //agrego el bean archivo en la lista
	  		  				session.setAttribute("rs_upload_doc_internos", rs_upload_doc_internos_temp);
	  		  				session.setAttribute("cantidad_lista",rs_upload_doc_internos_temp.size());
	  		  			}
	  		  		}
	  		  			
	  		  		/***********************FIN****************************/
	  		  	
	  	    	log.info(">>>>>>>>>>>>>FIN DE PROCESO INTERNO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  				
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
	  		  					
	  		  					request.setAttribute("hayDocumentosAdjuntos", null);
	  		  				}else{
	  		  				log.info("aqui6");
	  		  					session.setAttribute("rs_upload_doc_internos", temporal);
	  		  					session.setAttribute("cantidad_lista", temporal.size());
	  		  					
	  		  					
	  		  				}
	  		  				
	  		  			}
	  		  		
	  				return (mapping.findForward("upload"));
	  				
	  				
	  			case 5:
	  				//request.setAttribute("caso", "caso__11");
	  	        	log.info(">>>>>>>>>>>>>DENTRO DE MODIFICAR ESTADO DOCUMENTO INTERNO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  	        	log.info(">>>>>>>>>>>>>ESTE PROCESO SE REALIZARA INTERNAMENTE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  	        	log.info(">>>>>>>>>>>>>LLEGAMOS AQUI AL SELECCIONAR EL CHECK DE VISTO <<<<<<");
	  	        	
	  	        	//BArchivo archivodocinternomodificar = new BArchivo();
	  	        	String nombrex=(String)request.getParameter("nombre");
	  	        	
	  				log.info("nombre bean a modificar>"+nombrex);
	  	        	Collection rs_upload_doc_internos_modificar = new ArrayList();	
	  	        	Collection temporaln = new ArrayList();
	  	        	
	  	        	rs_upload_doc_internos_modificar = (Collection) session.getAttribute("rs_upload_doc_internos");
  	        	
	  		  		if(rs_upload_doc_internos_modificar!=null && rs_upload_doc_internos_modificar.size()>0){
	  		  			log.info("aqui1");
	  		  			Iterator it=rs_upload_doc_internos_modificar.iterator();
	  		  		log.info("aqui2");
	  		  				while(it.hasNext()){
	  		  				log.info("aqui8");
	  		  					BArchivo archivoenlista=(BArchivo)it.next();
	  		  							
	  		  					if(archivoenlista.getNombre_archivo().equals(nombrex)){
	  		  					log.info("aqui9");
	  		  					log.info("nombre bean a modificar >"+archivoenlista.getNombre_archivo());
	  		  						boolean valor = archivoenlista.isIschecked();
	  		  						
	  		  						if(valor){
	  		  							System.out.println("1");
	  		  							archivoenlista.setIschecked(false);
	  		  						
	  		  						}else{
	  		  							System.out.println("2");
	  		  							archivoenlista.setIschecked(true);
	  		  						}

	  		  						log.info(archivoenlista.getNombre_archivo());
	  		  					}
	  		  					temporaln.add(archivoenlista);	
	  		  				}
	  		  			session.setAttribute("rs_upload_doc_internos", temporaln);	  	

	  		  			}
	  		  		
	  				return (mapping.findForward("upload"));
	  				
	  			case 6:
	  				
	  				log.info(">>>>>>>>>>>>>DENTRO DE ADJUNTAR PROYECTOSSS DE DOCUMENTO INTERNO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  	        	log.info(">>>>>>>>>>>>>ESTE PROCESO SE REALIZARA INTERNAMENTE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  	        	log.info(">>>>>>>>>>>>>LLEGAMOS AQUI AL ESCOJER EL DOCUMENTO DE LA BANDEJA POPUP DE CREACION DE DOCUMENTOS INTERNOS<<<<<<<<<<<<<<<<<");
	  	        	
	  	        	//request.setAttribute("caso", "caso__10");
	  	       		log.info(" Entre a Adjuntar Documentos Internos....................... " );
	  	       	
	  	       		/*********************************/
	  	       		/*********************Upload de Ficheros*****************/
	  				 BArchivo archivodocinternoProyecto = new BArchivo();
	  				 int  codigo_documento_interno_proyecto=Integer.parseInt((String)request.getParameter("codigo"));
	  				 DocumentoInternoBean documento_interno_proyecto = service.getDocumentoInterno(codigo_documento_interno_proyecto,"adjunta");
	  				 int contaproy=0;
	  				
	  				archivodocinternoProyecto.setId_archivo_proyecto(codigo_documento_interno_proyecto); 
	  				archivodocinternoProyecto.setUsuario(usuario_actual); //CARGO EL BEAN QUE GUARDARA LOS DATOS QUE GRABARE DESPUES
	  				archivodocinternoProyecto.setNombre_archivo(documento_interno_proyecto.getNombre_arhivo()); //grabo el nombre en el bean archivo
	  				archivodocinternoProyecto.setIsvisado(false);
	  				archivodocinternoProyecto.setIsfirmado(false);
	  				archivodocinternoProyecto.setTipo_archivo(documento_interno_proyecto.getCodigo_documento_interno().toString()); //MEMO, INFO,...
	  				archivodocinternoProyecto.setNombre_archivo_cifrado(documento_interno_proyecto.getNombre_arhivo());
	  				
	  				if(codigo_documento_interno_proyecto > 0){
	  					archivodocinternoProyecto.setRuta(Constantes.CarpetaArchivosDesarrolloenTramiteFirmadosBackup.getNombre());
	  				}else{
	  					archivodocinternoProyecto.setRuta(Constantes.CarpetaArchivosDesarrolloenTramiteVisadosBackup.getNombre());
	  				}
	  				 log.info("el fileName es.."+ archivodocinternoProyecto.getNombre_archivo());
	  					
	  				 Collection rs_upload_doc_internos_proyecto_temp = new ArrayList();	
	  		  		
	  				rs_upload_doc_internos_proyecto_temp = (Collection) session.getAttribute("rs_upload_doc_internos");
	  		  			
	  		  		if(rs_upload_doc_internos_proyecto_temp!=null && rs_upload_doc_internos_proyecto_temp.size()>0){
	  		  				Iterator it=rs_upload_doc_internos_proyecto_temp.iterator();
	  		  				while(it.hasNext()){
	  		  					BArchivo archivoenlista=(BArchivo)it.next();
	  		  					if(archivoenlista.getNombre_archivo().equals(archivodocinternoProyecto.getNombre_archivo())){
	  		  					contaproy+=1;
	  		  					}
	  		  				
	  		  				}
	  		  			}
	  		  		if(rs_upload_doc_internos_proyecto_temp==null){
	  		  			Collection nuevo_doc_internos = new ArrayList();
	  		  			nuevo_doc_internos.add(archivodocinternoProyecto);
	  		  			session.setAttribute("rs_upload_doc_internos", nuevo_doc_internos);
	  		  			session.setAttribute("cantidad_lista",nuevo_doc_internos.size());
	  		  		}else{
	  		  			if(contaproy==0){
	  		  			rs_upload_doc_internos_proyecto_temp.add(archivodocinternoProyecto); //agrego el bean archivo en la lista
	  		  				session.setAttribute("rs_upload_doc_internos", rs_upload_doc_internos_proyecto_temp);
	  		  				session.setAttribute("cantidad_lista",rs_upload_doc_internos_proyecto_temp.size());
	  		  			}
	  		  		}
	  		  			
	  		  		/***********************FIN****************************/
	  		  	
	  	    	log.info(">>>>>>>>>>>>>FIN DE PROCESO INTERNO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	  				
	  				return (mapping.findForward("upload"));
	  			}

	  			return (mapping.findForward("error"));

		  }

		    }catch (Exception e) {
	            e.printStackTrace();
		    }
		  return mapping.findForward("error");		
	  		
}
	  
	public void mostrarPDFGenerado(HttpServletResponse response,
			FFormDocumentoInterno fformDocumentoInterno, File scfile) {
		try {
			FileInputStream file = new FileInputStream(scfile);
			int longitud = file.available();
			byte b[] = new byte[longitud];
			file.read(b);
			file.close();            		
			response.setHeader("Content-Disposition",
		    		"attachment;filename="+ fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo() );

		    		ServletOutputStream salida = response.getOutputStream();
		    		salida.write(b);
		    		salida.flush();
		    		salida.close();
		    		//response.sendRedirect("/MostrarFormularioCrearcionDocumentoInterno.do");
		    		
		    		
		} catch( FileNotFoundException e ) {
			log.info("Archivo no encontrado..");
		    /* Hacer algo */
		 }catch (IOException io){
			 log.info("Error de entrada Salida..");
		 }
		


	}
	private String obtener_siglas_oficina(Integer codigo_oficina,ArrayList<BOficinas> areasAdministrativasList) {
		// TODO Auto-generated method stub
		String nombre_oficina_destino="";
		String _codigo_oficina=String.valueOf(codigo_oficina);
		for (BOficinas item:areasAdministrativasList) {
			if(_codigo_oficina.equals(item.getCodigo_oficina()))
					{
				nombre_oficina_destino=item.getOficina();
					}
		}
		
		return nombre_oficina_destino;
		
	}
	public void obtener_siglas_oficina(String[] codigos_copia_oficina_destino,
			ArrayList<BOficinas> areasAdministrativasList,
			ArrayList<String> siglasOficinasCopias) {
		for (String codigo_oficina:codigos_copia_oficina_destino){
			 
			 for (BOficinas item:areasAdministrativasList) {
				if(codigo_oficina.equals(item.getCodigo_oficina()))
						{
					siglasOficinasCopias.add(item.getSiglas_oficina());
						}
			}
		}
	}
	
	
	private String getNombreDocumentoTipo(
			int tipo_documento_interno,
			ArrayList<EstandarBean> tipoDocumentoInternosList) {
		String nombre_tipo_documento="";
		for (Iterator<EstandarBean> iterator = tipoDocumentoInternosList.iterator(); iterator
				.hasNext();) {
			EstandarBean estandarBean = iterator.next();
			if(estandarBean.getIdBean()==tipo_documento_interno)
			{	nombre_tipo_documento=estandarBean.getNombreBean();
				break;
				}
		}
		return nombre_tipo_documento;
	}
	private String generarNombreArchivoPDF(String nombreOficina,
			Integer codigo_tipo_documento_interno, int numero_doc) {
		// TODO Auto-generated method stub
		
	
		 String anio = getAnioActual();
		
		String nombre1="";
		switch (codigo_tipo_documento_interno) {
		case 1://MEMO
			nombre1="MEMO_";
			break;
		case 2://MEMO
			nombre1="OFI_";
			break;
		case 3://MEMO
			nombre1="RESO_";
			break;
		case 4://MEMO
			nombre1="CARTA_";
			break;
		case 5://MEMO
			nombre1="INFORME_";
			break;

		default:
			break;
		}
		//String nombre_archivo_generado=	IConstante.filePath+"\\"+nombre1+numero_doc+"_"+anio+"_CONCYTEC_"+nombreOficina+".pdf";
		String nombre_archivo_generado=	nombre1+numero_doc+"_"+anio+"_CONCYTEC_"+nombreOficina+".pdf";
		log.info("nombre documento:" + nombre_archivo_generado);
		return nombre_archivo_generado;
	}
	private String getAnioActual() {
		Calendar c = new GregorianCalendar(); 
		String anio = Integer.toString(c.get(Calendar.YEAR));
		return anio;
	}
	
	
	//}
	
}


