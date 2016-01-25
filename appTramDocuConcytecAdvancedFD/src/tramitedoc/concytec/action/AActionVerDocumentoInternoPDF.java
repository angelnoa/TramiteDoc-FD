package tramitedoc.concytec.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BInfo;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Constantes;
import tramitedoc.concytec.util.IConstante;


public class AActionVerDocumentoInternoPDF extends Action {
	private static Logger logger = Logger.getLogger(AActionVerDocumentoInternoPDF.class);
	protected  Log log = LogFactory.getLog(AActionVerDocumentoInternoPDF.class);
	  public ActionForward execute(ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response)
			    throws Exception
			  {
		  		logger.debug("Entry AActionVerDocumentoInternoPDF...");
		  		
		  		response.setCharacterEncoding("UTF-8");
		  		request.setCharacterEncoding("UTF-8");
		  		response.setContentType("application/pdf");

		  		
		  		 HttpSession session = request.getSession(false);        

			        /*Verificar si la sesion se perdio*/
			        if (session==null){
			        	
			        	return (mapping.findForward("expiracion"));
			        	
			        }  
			        
			        
		  		
		  		DocumentosInternosService service = new DocumentosInternosServiceImplement();
		  		DocumentoInternoBean documentoInterno=null;
		  		File scfile=null;
		  		int  codigo_documento_interno=0;
		  		int codigo_estado_documento=0;
		  		String codigo_firma_solicitada ="";
		  		String codigo_visto_bueno_solicitada ="";
		  		String tipo_documento ="todo";
		  		String ruta="";
		  		String nombreTitulo="basic";
		  		String nombreTituloProyecto="";
		  		
		  		if(request.getParameter("cod_doc_interno")!=null){
		  			
		  			codigo_documento_interno=Integer.parseInt((String)request.getParameter("cod_doc_interno"));
		  			documentoInterno = service.getDocumentoInterno(codigo_documento_interno,tipo_documento);
		  			codigo_estado_documento = documentoInterno.getCodigo_estado_documento();
		  			codigo_firma_solicitada = documentoInterno.getCodigos_firmantes_destino();
		  			codigo_visto_bueno_solicitada = documentoInterno.getCodigos_visto_bueno_destino();
		  			log.info("codigo_documento_interno: "+codigo_documento_interno);
		  			
		  		}else{
		  			
		  			log.info("codigo_documento_interno "+codigo_documento_interno);
		  			if(request.getParameter("filename")!=null && request.getParameter("codigo_oficina_pertenece")!=null  && request.getParameter("codigo_estado") !=null ){
		  				codigo_estado_documento = Integer.parseInt(request.getParameter("codigo_estado"));
				  		documentoInterno = service.getDocumentoInterno((String)request.getParameter("filename"),Integer.parseInt((String)request.getParameter("codigo_oficina_pertenece")));
				  		
				  	}else{
				  		ruta = request.getParameter("fileroute");
				  		ruta=(ruta==null)?"":ruta;
				  		log.info("filerout > "+ruta);
				  	}
		  		}
		  		
		  		String nombre_archivo = request.getParameter("filename");
		  		nombre_archivo=(nombre_archivo==null)?"":nombre_archivo;
		  		
		  		String index_ = request.getParameter("index");
		  		index_=(index_==null)?"":index_;
		  		
		  		if(codigo_estado_documento == 0 && ruta.equals("xipxx")){
		  			log.info("-estado 0");
		  			if(index_.equals("0")){
		  				if(!nombre_archivo.equals(""))
			  				scfile = new File(IConstante.filePathFirmadosBackup, nombre_archivo);
			  				//scfile = new File(IConstante.filePathFirmadosBackup, nombre_archivo);
		  			}else{
		  				if(!nombre_archivo.equals(""))
		  					scfile = new File(IConstante.filePathVisadosBackup, nombre_archivo);
		  				if(index_.equals("P"))
		  					scfile = new File(IConstante.filePathFirmadosTemporalBackup, nombre_archivo);
		  				if(index_.equals("A") || index_.equals("V"))
		  					scfile = new File(IConstante.filePathVisadosTemporalBackup, nombre_archivo);
		  				
		  			}
		  		}
		  		
		  		
		  		
		  		if(codigo_estado_documento == 1 || codigo_estado_documento == 5){
		  			log.info("-estado 1 o 5");
		  			
		  			nombre_archivo = documentoInterno.getNombre_arhivo();
		  			scfile = new File(IConstante.filePathFirmadosBackup, nombre_archivo);
		  			
		  			if(codigo_estado_documento == 5 && codigo_firma_solicitada != null  && codigo_firma_solicitada.length()>0){
		  				nombreTituloProyecto=nombre_archivo.substring(9, nombre_archivo.length());
		  				scfile = new File(IConstante.filePathFirmadosTemporalBackup, nombreTituloProyecto);
		  			}
		  			
		  		}
		  		if(codigo_estado_documento == 3){
		  			log.info("-estado 3");
		  			nombre_archivo = documentoInterno.getNombre_arhivo();
		  			log.info("nombre de archivo"+nombre_archivo);
		  			String rutaDestino=Constantes.CarpetaArchivosDesarrolloTemporalDestino.getNombre();
		  			
		  			scfile = new File(rutaDestino, nombre_archivo);
		  			
        			if (!scfile.exists()){
        				scfile = new File(IConstante.filePath, nombre_archivo);
        			}
		  			
		  		}
		  		
		  		if(codigo_estado_documento == 4){
		  			log.info("-estado 4");
		  			scfile = new File(IConstante.filePathFirmados, nombre_archivo);

        			if (!scfile.exists()){
        				scfile = new File(IConstante.filePath, nombre_archivo);
        			}
		  			
		  		}
		  		
		  		nombreTitulo = nombre_archivo;
		  		/**
		  		 * con el codigo tengo que sacar el nombre del archivo
		  		 * y con eso saco el 
		  		 */
		  		BInfo binfo = new BInfo();
		  		if(request.getParameter("interno")!=null && request.getParameter("interno").equals("SI")){
		  			log.info("-estado interno SI"); 
		  			//aqui cambio el nombre del archivo
		  			//o hago la busqueda en la carpeta y contrasto como la aplicacion
		  			binfo=service.getInfoSobreDocumentoFirmadoManualmente(codigo_documento_interno);
		  			log.info("1.-"+binfo.getNombrearchivo());
		  			log.info("2.-"+nombre_archivo);
		  			
		  			if(!binfo.getNombrearchivo().equals(nombre_archivo.substring(9, nombre_archivo.length()))){
		  				return mapping.findForward("noencontrado");
		  			}
		  			
		  			scfile = new File(IConstante.filePath, binfo.getNombrearchivo());
		  			nombreTitulo = binfo.getNombrearchivo();
		  		 }

        		log.info("rutaBase:" +scfile);
        		if(codigo_estado_documento == 5 && codigo_firma_solicitada!=null  &&  codigo_firma_solicitada.length()>0){
        			nombreTitulo=nombreTituloProyecto;
        		}
        		
        		
        		try {
        			FileInputStream file = new FileInputStream(scfile);

        			int longitud = file.available();
        			byte b[] = new byte[longitud];
            		file.read(b);
            		file.close();            		
            		response.setHeader("Content-Disposition","attachment;filename="+ nombreTitulo);

                    		ServletOutputStream salida = response.getOutputStream();
                    		salida.write(b);
                    		salida.flush();
                    		salida.close();

                    		
                } catch( FileNotFoundException e ) {
                	log.info("Archivo no encontrado..");
                    /* Hacer algo */
                 }catch (IOException io){
                	 log.info("Error de entrada Salida..");
                 }
                 finally{

                 }

				log.info("Si la fecha es null o vacio..");
			     
		  		
				return null;
			   // return mapping.findForward("exito");
			  }
	
			}

