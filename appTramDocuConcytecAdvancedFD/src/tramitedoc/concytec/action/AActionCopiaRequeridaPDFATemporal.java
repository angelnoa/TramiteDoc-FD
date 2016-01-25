package tramitedoc.concytec.action;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Collection;
//import java.util.GregorianCalendar;
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
//import org.jfree.util.Log;
import com.lowagie.text.Image;

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BDatosFirmante;
import tramitedoc.concytec.bean.BOficinas;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Constantes;
import tramitedoc.concytec.util.PDFcreator;

public class AActionCopiaRequeridaPDFATemporal extends Action {
	protected  Log log = LogFactory.getLog(AActionCopiaRequeridaPDFATemporal.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=UTF-8;application/pdf");
		response.setCharacterEncoding("UTF-8");
  		request.setCharacterEncoding("UTF-8");
  		
		HttpSession session = request.getSession(false);        

        /*Verificar si la sesion se perdio*/
        if (session==null){
        	
        	return (mapping.findForward("expiracion"));
        	
        }  
        int coDoc = Integer.parseInt(request.getParameter("codDoc"));
        int coTip = Integer.parseInt(request.getParameter("codTip"));
        int tipo_envio = Integer.parseInt(request.getParameter("tipoenvio"));
        String pagina = (String)request.getParameter("pagina");
      
        
        String nombrepfx="";
        String nombreArchivoInicial ="";
        String nombreArchivoFinal="";
        Collection rs_upload_doc_internos = new ArrayList();
        Collection rs_upload_doc_internosxCambiarNombres = new ArrayList();
        
        String nombreArchivos="";
		String nombreAnexos="";
		String nombreArchivosTotalParaFirmaMasiva="";
        
        try{

        String usuario_actual =   String.valueOf(session.getAttribute("nombreusuario"));
        String codigo_persona_noborrar =   String.valueOf(session.getAttribute("codigo_persona_noborrar"));
        
        String tipo_documento_interno="";
        DocumentosInternosService service = new DocumentosInternosServiceImplement();
        DocumentoInternoBean documentointerno = new DocumentoInternoBean();
        BDatosFirmante datos_firmante= new BDatosFirmante();
        
        datos_firmante=service.getDatosFirmantexCodigo(codigo_persona_noborrar);  //BEAN CREADO CON LOS DATOS PARA LA FIRMA DIGITAL 
        log.info("DATOS ->>>");
        log.info("-FIRMADO POR "+datos_firmante.getFirmado_por());
        log.info("-CARGO "+datos_firmante.getCargo());
        log.info("-OFICINA "+datos_firmante.getOficina());
        log.info("-ES OFICINA MAXIMA "+datos_firmante.isEsoficinamaxima());
        log.info("-ES RESPONSABLE DE OFICINA "+datos_firmante.isEsresponsable());
        log.info("-ES tipo firma "+datos_firmante.getTipo_firma());
        log.info("-DNI "+datos_firmante.getDni());
        
        if(tipo_envio==1){
        	/**
        	 * FIRMA SOLICITADA
        	 */
        	if((coTip > 0  &&  coTip < 6) || coTip == 8){
            	tipo_documento_interno = "normal";
            }
            if(coTip == 6){
            	tipo_documento_interno = "ccp";
            }
            if(coTip == 7){
            	tipo_documento_interno = "tdr";
            }
            
            System.out.println("coDoc "+coDoc+" tipo_documento_interno "+tipo_documento_interno);
            documentointerno = service.getDocumentoInterno(coDoc, tipo_documento_interno);
            nombreArchivoInicial=documentointerno.getNombre_arhivo().trim();
            
    			/**
    			 * INICIO VALORES
    			 */
    			nombreArchivoFinal="";
           
    	        /***
    	  		 * AGREGADO PARA MANEJAR DOCUMENTOS OBSERVADOS
    	  		 */
    	        
    			if(documentointerno.getCodigo_estado_documento()==10){
    				nombreArchivoFinal= nombreArchivoInicial;
    			}else{
    					nombreArchivoFinal= nombreArchivoInicial.substring(9,nombreArchivoInicial.length()); // ESTOY QUITANDO EL PREFIJO -BORRADOR_
    				
    			}
    			/**
    			 * FIN
    			 */
    			
    			log.info("INICIO DE PROCESO>>>");
    			

    			log.info("Moviendo file principal a HTTP");
    			String rutaOrigen=Constantes.CarpetaArchivosDesarrolloenTramiteFirmadosTemporalBackup.getNombre()+nombreArchivoFinal;
    			String rutaDestino=servlet.getServletContext().getRealPath("/TempPdf")+File.separator+nombreArchivoFinal;
    		
    			copiarDocumento(rutaOrigen, rutaDestino);
    			
    			log.info(".....Copie el Documento Principal a Memoria");
    			log.info("...........................................");
    			  				
    	
    		  	nombreArchivos=nombreArchivoFinal+",1;";
    		    session.setAttribute("valorTipoFirma", "1");
    		  	
    		  	/***
    		  	 * FIN DE DOCUMENTO PRINCIPAL
    		  	 */
        }
        
        if(tipo_envio==0){
        	/***
        	 * VISTO BUENO SOLICITADO
        	 */
        	nombreAnexos="";
        	String nombreArchivoAnexo="";
        	rs_upload_doc_internos =  (Collection) session.getAttribute("rs_upload_doc_internos_vb_requerido");
        	
        	if(rs_upload_doc_internos!=null && rs_upload_doc_internos.size()>0){
					Iterator it3=rs_upload_doc_internos.iterator();
					
						while(it3.hasNext()){
							String rutaOrigenAnexo=Constantes.CarpetaArchivosDesarrolloenTramiteVisadosTemporalBackup.getNombre();
				  			String rutaDestinoAnexo=servlet.getServletContext().getRealPath("/TempPdf")+File.separator;
							BArchivo archivoenlista=(BArchivo)it3.next();
							nombreArchivoAnexo = archivoenlista.getNombre_archivo_cifrado();
							
							log.info("Moviendo file adjunto a HTTP");
							rutaOrigenAnexo=rutaOrigenAnexo+archivoenlista.getNombre_archivo_cifrado();
							rutaDestinoAnexo=rutaDestinoAnexo+archivoenlista.getNombre_archivo_cifrado();

							copiarDocumento(rutaOrigenAnexo, rutaDestinoAnexo);
							nombreAnexos=nombreAnexos+nombreArchivoAnexo+",0;";
							

						}
				}
        	nombreArchivos=nombreArchivos+nombreAnexos;
        	session.setAttribute("valorTipoFirma", "0");
        }
        
        		  	
		/***
  		 * AGREGADO PARA MANEJAR DOCUMENTOS OBSERVADOS
  		 
		if(documentointerno.getCodigo_estado_documento()==10){
			nombreAnexos="";
		}
		/**
		 * FIN
		 */
	
		log.info(nombreArchivos);
		
		nombreArchivosTotalParaFirmaMasiva = nombreArchivosTotalParaFirmaMasiva+nombreArchivos;

		
        log.info("--->"+nombreArchivosTotalParaFirmaMasiva);
        
        if(!nombreArchivosTotalParaFirmaMasiva.equals("")){
		
		log.info("Moviendo imagenes para sello principal a HTTP");
		String nombreImagen=datos_firmante.getNombre_sello();
		String rutaOrigenImagen=Constantes.CarpetaArchivosDesarrolloSellos.getNombre()+nombreImagen;
		String rutaDestinoImagen=servlet.getServletContext().getRealPath("/TempPdf")+File.separator+nombreImagen;
		copiarDocumento(rutaOrigenImagen, rutaDestinoImagen);
		
		log.info("Moviendo imagenes para visado a HTTP");
		String nombreSelloVisto=datos_firmante.getNombre_sello_visto();
		String rutaOrigenSelloVisto=Constantes.CarpetaArchivosDesarrolloSellos.getNombre()+nombreSelloVisto;
		String rutaDestinoSelloVisto=servlet.getServletContext().getRealPath("/TempPdf")+File.separator+nombreSelloVisto;
		copiarDocumento(rutaOrigenSelloVisto, rutaDestinoSelloVisto);
		
		
		if(datos_firmante.getTipo_firma()==0){
			log.info("Moviendo PFX a HTTP");
			nombrepfx=datos_firmante.getNombre_pfx();
			String rutaOrigenPfx=Constantes.CarpetaArchivosDesarrolloPfx.getNombre()+nombrepfx;
			String rutaDestinoPfx=servlet.getServletContext().getRealPath("/TempPdf")+File.separator+nombrepfx;
			copiarDocumento(rutaOrigenPfx, rutaDestinoPfx);
			
		}
		
		
		// obtener rutas relativas
		String rutaServidor = request.getScheme().toLowerCase()+ "://" + request.getServerName()+":"+ request.getServerPort()+request.getContextPath()+"/TempPdf/";
		log.info("rutaServidor "+rutaServidor);
		
		request.setAttribute("rutaServidor", rutaServidor);
		request.setAttribute("nombreArchivo",nombreArchivosTotalParaFirmaMasiva.substring(0,nombreArchivosTotalParaFirmaMasiva.length()-1));
		request.setAttribute("nombreImagen", nombreImagen);
		request.setAttribute("coDoc", ""+coDoc);
		
		/***
		session.setAttribute("nombreArchivo", nombreArchivosTotalParaFirmaMasiva);
		session.setAttribute("numero_doc_pdf", documentointerno.getNumero_doc());
		session.setAttribute("documentointernotemp", documentointerno);
		session.setAttribute("rs_upload_doc_internos_ls", rs_upload_doc_internos);
		session.setAttribute("datos_firmante_ls", datos_firmante);
		***/
		
		/*session.setAttribute("observacion", observacion);
		session.setAttribute("tipoenv", String.valueOf(tipo_envio));
		
		session.setAttribute("pagina", pagina);*/
		
		request.setAttribute("aliasCertificadoToken_ls", datos_firmante.getDni().trim());
		//request.setAttribute("aliasCertificadoToken_ls", "DEL CARPIO SALINAS Jorge Alberto (FAU20135727394)");
		//request.setAttribute("aliasCertificadoToken_ls", "AZAÑERO ELGUERA Jesus Manuel (FAU20135727394)");
		request.setAttribute("pfx_ls", nombrepfx);
		request.setAttribute("usarToken_ls", datos_firmante.getTipo_firma());
				
		request.setAttribute("oficina_firmante_ls", datos_firmante.getOficina());
		request.setAttribute("cargo_firmante_ls", datos_firmante.getCargo());
		
		
		request.setAttribute("nombreImagenVisto", nombreSelloVisto);

		return (mapping.findForward("exito"));
		//return (mapping.findForward("test"));
		
        }else{
        	return (mapping.findForward("mensajevacio"));
        }
		
		}catch (Exception e) {
	        e.printStackTrace();
	  }
	  return mapping.findForward("error");
		
		
	}
	
	/*private String generarNombreArchivoPDF(String nombreOficina,
			Integer codigo_tipo_documento_interno, int numero_doc, String firmadopor, boolean llevaIniciales, boolean esBorrador) {
		// TODO Auto-generated method stub
		
		Funciones funciones = new Funciones();
		String anio = getAnioActual();
		String iniciales="";
		
		if(llevaIniciales){
			iniciales = "_"+funciones.iniciales(firmadopor);
		}
		
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
		String nombre_archivo_generado=	nombre1+numero_doc+"_"+anio+"_CONCYTEC_"+nombreOficina+iniciales+".pdf";
		if(esBorrador){
			nombre_archivo_generado=nombre1+numero_doc+"B_"+anio+"_CONCYTEC_"+nombreOficina+iniciales+".pdf";
		}
		log.info("nombre documento:" + nombre_archivo_generado);
		return nombre_archivo_generado;
	}
	
	private String getAnioActual() {
		Calendar c = new GregorianCalendar(); 
		String anio = Integer.toString(c.get(Calendar.YEAR));
		return anio;
	}*/

	
	private void copiarDocumento(String rutaOrigen, String rutaDestino){
		log.info("Origen "+rutaOrigen);
		log.info("Destino "+rutaDestino);
		File inFile = new File(rutaOrigen);
	    File outFile = new File(rutaDestino);
	    FileInputStream in = null;
	    FileOutputStream out = null;
		try {
			if (outFile.exists())
				outFile.delete();
			
			in = new FileInputStream(inFile);
			out = new FileOutputStream(outFile);

		    int c;
		    while ((c = in.read()) != -1)
		     out.write(c);		
		    
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (in!=null){
				try {
					in.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	    
	}
	
}
