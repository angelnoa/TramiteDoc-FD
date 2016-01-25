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

public class AActionCopiarPDFTemp extends Action {
	protected  Log log = LogFactory.getLog(AActionCopiarPDFTemp.class);
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
        	
        	//return (mapping.findForward("expiracion"));
        	
        }  
        int coDoc = Integer.parseInt(request.getParameter("codDoc"));
        int coTip = Integer.parseInt(request.getParameter("codTip"));
        String observacion = (String) request.getParameter("observacion");
        int tipo_envio = Integer.parseInt(request.getParameter("tipoenvio"));
        String pagina = (String)request.getParameter("pagina");
        String nombrepfx="";
        String nombreArchivoFinal="";
        Collection rs_upload_doc_internos = new ArrayList();
        Collection rs_upload_doc_internosxCambiarNombres = new ArrayList();
		String nombreAnexos="";
        
        observacion=(observacion==null)?"":observacion;
        pagina=(pagina==null)?"":pagina;
        byte ptext[] = observacion.getBytes();
        observacion = new String(ptext, "UTF-8");
        
        try{
        
        System.out.println("-------------"+observacion);
        System.out.println("------------- tipo de envio > "+tipo_envio);
        String usuario_actual =   String.valueOf(session.getAttribute("nombreusuario"));
        String codigo_persona_noborrar =   String.valueOf(session.getAttribute("codigo_persona_noborrar"));
        
        String tipo_documento_interno="";

        DocumentosInternosService service = new DocumentosInternosServiceImplement();
        DocumentoInternoBean documentointerno = new DocumentoInternoBean();
        BDatosFirmante datos_firmante= new BDatosFirmante();
        PDFcreator pdf = new PDFcreator();
        
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
        
        datos_firmante=service.getDatosFirmantexCodigo(codigo_persona_noborrar);  //BEAN CREADO CON LOS DATOS PARA LA FIRMA DIGITAL 
        String nombreArchivoInicial=documentointerno.getNombre_arhivo().trim();
		
        System.out.println("DATOS ->>>");
        System.out.println("-FIRMADO POR "+datos_firmante.getFirmado_por());
        System.out.println("-CARGO "+datos_firmante.getCargo());
        System.out.println("-OFICINA "+datos_firmante.getOficina());
        System.out.println("-ES OFICINA MAXIMA "+datos_firmante.isEsoficinamaxima());
        System.out.println("-ES RESPONSABLE DE OFICINA "+datos_firmante.isEsresponsable());
        System.out.println("-ES tipo firma "+datos_firmante.getTipo_firma());
        System.out.println("-DNI "+datos_firmante.getDni());
        /***
  		 * AGREGADO PARA MANEJAR DOCUMENTOS OBSERVADOS
  		 */
        
        tipo_envio=datos_firmante.getTipo_firma();
        
		if(documentointerno.getCodigo_estado_documento()==10){
			nombreArchivoFinal= nombreArchivoInicial;
		}else{
			if(coTip == 6){
				nombreArchivoFinal= nombreArchivoInicial.substring(5,nombreArchivoInicial.length()); // ESTOY QUITANDO EL PREFIJO -PROY_
			}else{
				nombreArchivoFinal= nombreArchivoInicial.substring(9,nombreArchivoInicial.length()); // ESTOY QUITANDO EL PREFIJO -BORRADOR_
			}
			
		}
		/**
		 * FIN
		 */
        
		
		String nombreArchivos;
		String nombreOficina=service.getSiglasOficina(documentointerno.getCodigo_oficina_pertenece());
		//boolean esBorrador = false;
		//boolean llevaIniciales = true;
		
		System.out.println("INICIO DE PROCESO>>>");
		
		System.out.println("Moviendo file principal a HTTP");
		String rutaOrigen="";
		
		//String rutaDestino=servlet.getServletContext().getRealPath("/TempPdf")+File.separator+nombreArchivoFinal;
		
		String rutaDestino=Constantes.CarpetaArchivosDesarrolloTemporalOrigen.getNombre()+nombreArchivoFinal;
		
		if(documentointerno.getCodigos_firmantes_destino()==null || documentointerno.getCodigos_firmantes_destino().equals("") || documentointerno.getCodigos_firmantes_destino().length()==0)
			rutaOrigen=Constantes.CarpetaArchivosDesarrolloenTramiteFirmadosBackup.getNombre()+nombreArchivoInicial;
		else
			rutaOrigen=Constantes.CarpetaArchivosDesarrolloenTramiteFirmadosTemporalBackup.getNombre()+nombreArchivoFinal;

		/***
		 * ANTES DE COPIAR
		 * PROCEDO A VERIFICAR SI ES OFICINA MAXIMA Y SI HAY ADJUNTOS 6 O 7
		 */
		int cd = 0;
		if(datos_firmante.isEsoficinamaxima()){
			if(service.siHayArchivosAdjuntosxCambiarNombres(coDoc)){
				rs_upload_doc_internosxCambiarNombres = service.getListaArchivosAdjuntos(coDoc);
				if(rs_upload_doc_internosxCambiarNombres!=null && rs_upload_doc_internosxCambiarNombres.size()>0){
					Iterator it=rs_upload_doc_internosxCambiarNombres.iterator();
						while(it.hasNext()){
							BArchivo archivoenlista=(BArchivo)it.next();
							cd = service.of_tipo_documento_interno(archivoenlista.getId_archivo_proyecto());
							if(cd ==6 || cd ==7){
								nombreAnexos = nombreAnexos+archivoenlista.getNombre_archivo().substring(5, archivoenlista.getNombre_archivo().length())+",";
							}else{
								//service.updateNombresDocumentosAdjuntos(archivoenlista);
								
								nombreAnexos = nombreAnexos+archivoenlista.getNombre_archivo_cifrado()+",";
							}
						}
				}

						
					nombreAnexos = nombreAnexos.substring(0,nombreAnexos.length()-1);
	  				System.out.println("Doc adjuntos - > "+nombreAnexos);
	  				/***
					 * ACTUALIZO EL CAMPO DE DOCUMENTOS ADJUNTOS Y GENERO NUEVAMENTE EL PDF
					 */
					documentointerno.setNombre_doc_adjuntos(nombreAnexos);
			  		ArrayList<EstandarBean> tipoDocumentoInternosList=service.getTipoDocumentosInternosLista();
			  		String nombre_tipo_documento = getNombreDocumentoTipo(coTip,tipoDocumentoInternosList);
			  		ArrayList<BOficinas> areasAdministrativasList =	 (ArrayList<BOficinas>) session.getAttribute("areasAdministrativasList");
			  		ArrayList<String>  siglasOficinasCopias = new ArrayList<String>();
			  		String nombre_oficina_destino=obtener_siglas_oficina(documentointerno.getCodigo_oficina(),areasAdministrativasList);
			  		String nombreSelloOficina= service.getNombreSelloOficina(documentointerno.getCodigo_oficina_pertenece());
			  		documentointerno.setNombreSelloOficina(nombreSelloOficina);
			  		documentointerno.setNombre_oficial_anio(service.getNombreOficialAnio(documentointerno.getAnio_doc()));
			  		documentointerno.setCargo_persona_destinatario(service.getCargo(documentointerno.getPersonas()));
			  		
			  		boolean llevaIniciales = true;
			  		System.out.println("Entry a modificar Nombre PDF");
					try{ 
			  			
			  			switch (coTip) {
			  			
			  			case 2:
			  				System.out.println("Oficio");
							pdf.createPdf_Oficio(nombreArchivoInicial, documentointerno,nombre_tipo_documento,nombreOficina,siglasOficinasCopias,nombre_oficina_destino,null);
							break;
						case 5:
							System.out.println("Informe");
							pdf.createPdf_Informe(nombreArchivoInicial, documentointerno,nombre_tipo_documento,nombreOficina,siglasOficinasCopias,nombre_oficina_destino,null);
							break;

						default:
							System.out.println("Memo, Resolucion, Carta, Memo_multiple");
							System.out.println(nombreArchivoInicial+",fformDocumentoInterno.getDocumentoInterno(),"+nombre_tipo_documento+","+nombreOficina+","+siglasOficinasCopias+","+nombre_oficina_destino);
							if(datos_firmante.isEsoficinamaxima()){
				  				llevaIniciales = false;
				  			}
							pdf.createPdf(nombreArchivoInicial, documentointerno,nombre_tipo_documento,nombreOficina,siglasOficinasCopias,nombre_oficina_destino,null,llevaIniciales);
							break;
						}

			  		}
			  		 catch(FileNotFoundException e ){
			  			log.error("No se encuentra el archivo o esta abierto siendo utilizado por otro proceso");
			  		 }
			  		catch(NullPointerException e2){
			  			log.error("No se encuentra el archivo o es null");
			  		 }
					
	  			}
				
		}
		/***
		 * FIN
		 */
	
		copiarDocumento(rutaOrigen, rutaDestino);
		nombreAnexos="";
		
		
		/***
		 * SI Y SOLO SI EL ENVIO ES DESDE UNA OFICINA MAXIMA
		 * LOS ARCHIVOS ADJUNTOS DEBEN DE CAMBIAR DE NOMBRE QUITANDOSELES "PROY_" 
		 * A LOS SIGUIENTES TIPO DE DOCUMENTOS:
		 * 
		 * MEMO DE REQUERIMIENTO
		 * TDR
		 */
		
		String nombreArchivoAnexo="";
		
		rs_upload_doc_internos = service.getListaArchivosAdjuntos(coDoc);
		if(rs_upload_doc_internos!=null && rs_upload_doc_internos.size()>0){
			Iterator it=rs_upload_doc_internos.iterator();
			
				while(it.hasNext()){
					String rutaOrigenAnexo=Constantes.CarpetaArchivosDesarrolloenTramiteVisadosBackup.getNombre();
		  			String rutaDestinoAnexo=servlet.getServletContext().getRealPath("/TempPdf")+File.separator;
					BArchivo archivoenlista=(BArchivo)it.next();
					nombreArchivoAnexo = archivoenlista.getNombre_archivo_cifrado();
					if(archivoenlista.getEstado().equals("V")){
						
						if(datos_firmante.isEsoficinamaxima()){
							cd = service.of_tipo_documento_interno(archivoenlista.getId_archivo_proyecto());
							if(cd ==6 || cd ==7){
								rutaOrigenAnexo=rutaOrigenAnexo+archivoenlista.getNombre_archivo_cifrado();
								nombreArchivoAnexo=nombreArchivoAnexo.substring(5, nombreArchivoAnexo.length());
								rutaDestinoAnexo=rutaDestinoAnexo+nombreArchivoAnexo;
								/**
								 * UPDATE A TABLA CABUPLOAD
								 */
								service.updateNombresDocumentosAdjuntos(archivoenlista);
								
							}else{
								rutaOrigenAnexo=rutaOrigenAnexo+archivoenlista.getNombre_archivo_cifrado();
								rutaDestinoAnexo=rutaDestinoAnexo+archivoenlista.getNombre_archivo_cifrado();
							}
						}else{
							rutaOrigenAnexo=rutaOrigenAnexo+archivoenlista.getNombre_archivo_cifrado();
							rutaDestinoAnexo=rutaDestinoAnexo+archivoenlista.getNombre_archivo_cifrado();
						}
						
						copiarDocumento(rutaOrigenAnexo, rutaDestinoAnexo);
						nombreAnexos=nombreAnexos+nombreArchivoAnexo+",0;";
					}

				}
		}
		if(nombreAnexos.equals("")){
	  		System.out.println("Ningun archivo adjunto");
	  		//nombreArchivos=nombreArchivoFinal+",1";
	  		nombreArchivos=nombreArchivoFinal;
	  	}
	  	else{
	  		nombreAnexos=nombreAnexos.substring(0,nombreAnexos.length()-1);
	  		//nombreArchivos=nombreArchivoFinal+",1;"+nombreAnexos;
	  		nombreArchivos=nombreArchivoFinal+nombreAnexos;
	  	}
		
		/***
  		 * AGREGADO PARA MANEJAR DOCUMENTOS OBSERVADOS
  		 */
		if(documentointerno.getCodigo_estado_documento()==10){
			nombreAnexos="";
		}
		/**
		 * FIN
		 */
		
/*		if(nombreAnexos.equals("")){
	  		System.out.println("Ningun archivo adjunto");
	  		nombreArchivos=nombreArchivoFinal+",1";
	  	}
	  	else{
	  		nombreAnexos=nombreAnexos.substring(0,nombreAnexos.length()-1);
	  		System.out.println("Moviendo adjuntos a HTTP");
		
	  		String[] Anexos = nombreAnexos.split(",");
	  		nombreAnexos="";
	  		for(String anexo: Anexos){
	  			String rutaOrigenAnexo=Constantes.CarpetaArchivosDesarrolloenTramiteVisadosBackup.getNombre()+anexo;
	  			String rutaDestinoAnexo=servlet.getServletContext().getRealPath("/TempPdf")+File.separator+anexo;
	  			//String rutaDestinoAnexo="C:"+Constantes.CarpetaArchivosDesarrolloenTramiteVisados.getNombre()+anexo;
	  			copiarDocumento(rutaOrigenAnexo, rutaDestinoAnexo);

		  		nombreAnexos=nombreAnexos+anexo+",0;";
	  		}
	  		nombreAnexos=nombreAnexos.substring(0,nombreAnexos.length()-1);
	  		nombreArchivos=nombreArchivoFinal+",1;"+nombreAnexos;
	  	}
*/	
		
		log.info(nombreArchivos);
		
		System.out.println("Moviendo imagenes para sello principal a HTTP");
		String nombreImagen=datos_firmante.getNombre_sello_visto();
		log.info("NOMBRE Firmante JM -> "+datos_firmante.getUsuario());
		log.info("NOMBRE IMAGEN JM -> "+nombreImagen);
		String rutaOrigenImagen=Constantes.CarpetaArchivosDesarrolloSellos.getNombre()+nombreImagen;
		log.info("Ruta Origen JM -> "+rutaOrigenImagen);
		String rutaDestinoImagen=servlet.getServletContext().getRealPath("/TempPdf")+File.separator+nombreImagen;
		log.info("IMAGEN DESTINO JM -> "+rutaDestinoImagen);
		copiarDocumento(rutaOrigenImagen, rutaDestinoImagen);
		
		System.out.println("Moviendo imagenes para visado a HTTP");
		String nombreSelloVisto=datos_firmante.getNombre_sello_visto();
		String rutaOrigenSelloVisto=Constantes.CarpetaArchivosDesarrolloSellos.getNombre()+nombreSelloVisto;
		String rutaDestinoSelloVisto=servlet.getServletContext().getRealPath("/TempPdf")+File.separator+nombreSelloVisto;
		copiarDocumento(rutaOrigenSelloVisto, rutaDestinoSelloVisto);
		
		
		if(datos_firmante.getTipo_firma()==0){
			System.out.println("Moviendo PFX a HTTP");
			nombrepfx=datos_firmante.getNombre_pfx();
			String rutaOrigenPfx=Constantes.CarpetaArchivosDesarrolloPfx.getNombre()+nombrepfx;
			String rutaDestinoPfx=servlet.getServletContext().getRealPath("/TempPdf")+File.separator+nombrepfx;
			copiarDocumento(rutaOrigenPfx, rutaDestinoPfx);
			
		}
		
		
		
		// obtener rutas relativas
		String rutaServidor = request.getScheme().toLowerCase()+ "://" + request.getServerName()+":"+ request.getServerPort()+request.getContextPath()+"/TempPdf/";
		System.out.println("rutaServidor "+rutaServidor);
		
		String rutaOrigenFD= Constantes.CarpetaArchivosDesarrolloTemporalOrigen.getNombre();
		request.setAttribute("rutaOrigen", rutaOrigenFD);
		
		String rutaDestinoFD=Constantes.CarpetaArchivosDesarrolloTemporalDestino.getNombre();
		
		request.setAttribute("rutaDestino", rutaDestinoFD);
		String rutaOrigenImagenFD=Constantes.CarpetaArchivosDesarrolloTemporalImagen.getNombre();
		
		request.setAttribute("rutaImagen", rutaOrigenImagenFD);
		
		
		
		request.setAttribute("rutaServidor", rutaServidor);
		request.setAttribute("nombreArchivo",nombreArchivos);
		request.setAttribute("nombreImagen", nombreImagen);
		request.setAttribute("coDoc", ""+coDoc);
		
		
		session.setAttribute("nombreArchivo", nombreArchivoFinal);
		session.setAttribute("numero_doc_pdf", documentointerno.getNumero_doc());
		session.setAttribute("documentointernotemp", documentointerno);
		session.setAttribute("rs_upload_doc_internos_ls", rs_upload_doc_internos);
		session.setAttribute("datos_firmante_ls", datos_firmante);
		
		session.setAttribute("observacion", observacion);
		session.setAttribute("tipoenv", String.valueOf(tipo_envio));
		
		session.setAttribute("pagina", pagina);
		
		request.setAttribute("aliasCertificadoToken_ls", datos_firmante.getDni().trim());
		//request.setAttribute("aliasCertificadoToken_ls", "DEL CARPIO SALINAS Jorge Alberto (FAU20135727394)");
		//request.setAttribute("aliasCertificadoToken_ls", "AZAÑERO ELGUERA Jesus Manuel (FAU20135727394)");
		request.setAttribute("pfx_ls", nombrepfx);
		request.setAttribute("usarToken_ls", datos_firmante.getTipo_firma());
				
		request.setAttribute("oficina_firmante_ls", datos_firmante.getOficina());
		request.setAttribute("cargo_firmante_ls", datos_firmante.getCargo());
		
		
		request.setAttribute("nombreImagenVisto", nombreSelloVisto);
		
		 String randonID = request.getParameter("randonid");

		//logger.info("Preparando los parametros para realizar la firma Digital");
		
		String pagina2 = "http://192.168.5.172:8080/SignnetSignature/firmaAppletPDF.htm?";		
		String comentario = observacion;
		String cargo = datos_firmante.getCargo();
		String ubicacion = datos_firmante.getOficina();
		String nombreArchivos2 = nombreArchivoFinal;
		String imagen = ""; //"firma.jpg";
		
		String rutaOrigen2= Constantes.CarpetaArchivosDesarrolloTemporalOrigen.getNombre();
		String rutaDestino2=Constantes.CarpetaArchivosDesarrolloTemporalDestino.getNombre();
		String rutaImagen2=Constantes.CarpetaArchivosDesarrolloTemporalImagen.getNombre();
		
	
		
		String razon = "Firma de documento";
		String nombreTag = "";
		String listarArchivos = "1";
		String firmarPfxAlmacen = "1";
		String actiStringDescripcion = "1";
		String posicionFirma = "SD";
		String estiloFirma = "ID";
		String ubicacionPagina = "PP";
		String altoRubrica = "50";
		String anchoRubrica = "160";
		String aplicarImagen = "1";
		String usarPersonalizado = "1";
		String nomarch = randonID;
		String url = pagina2 + "cargo=" + cargo + 
					"&comentario=" + comentario + 
					"&razon=" + razon + 
					"&ubicacion=" + ubicacion + 
					"&imagen=" + imagen + 
					"&nombreArchivos=" + nombreArchivos2 + 
					"&rutaOrigen=" + rutaOrigen2 + 
					"&rutaDestino=" + rutaDestino2 + 
					"&rutaImagen=" + rutaImagen2 + 
					"&nombreTag=" + nombreTag + 
					"&listarArchivos=" + listarArchivos + 
					"&firmarPfxAlmacen=" + firmarPfxAlmacen + 
					"&activarDescripcion=" + actiStringDescripcion + 
					"&posicionFirma=" + posicionFirma + 
					"&estiloFirma=" + estiloFirma + 
					"&ubicacionPagina=" + ubicacionPagina + 
					"&altoRubrica=" + altoRubrica + 
					"&anchoRubrica=" + anchoRubrica + 
					"&aplicarImagen=" + aplicarImagen + 
					"&usarPersonalizado=" + usarPersonalizado +
					"&nomarch=" + nomarch;
		
		request.setAttribute("srcIframe", url);
		request.setAttribute("archID", randonID);
		
		
	//	return (mapping.findForward("exitoFirma"));
		

		return (mapping.findForward("exito"));
		//return (mapping.findForward("test"));
		
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
		System.out.println("nombre documento:" + nombre_archivo_generado);
		return nombre_archivo_generado;
	}
	
	private String getAnioActual() {
		Calendar c = new GregorianCalendar(); 
		String anio = Integer.toString(c.get(Calendar.YEAR));
		return anio;
	}*/
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
	
	private void copiarDocumento(String rutaOrigen, String rutaDestino){
		System.out.println("Origen "+rutaOrigen);
		System.out.println("Destino "+rutaDestino);
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
