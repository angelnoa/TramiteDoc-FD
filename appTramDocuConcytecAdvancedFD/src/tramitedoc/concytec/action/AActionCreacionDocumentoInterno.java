package tramitedoc.concytec.action;



import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.lowagie.text.Image;

import softnet.signnet.procesofirma.procesarFirma;
import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BDatosFirmante;
import tramitedoc.concytec.bean.BDocumento;
import tramitedoc.concytec.bean.BInfoDocumento;
import tramitedoc.concytec.bean.BOficinas;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormDocumentoInterno;
import tramitedoc.concytec.form.FFormMantDocumento;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Constantes;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.IConstante;
import tramitedoc.concytec.util.PDFcreator;

public class AActionCreacionDocumentoInterno extends Action {
	protected  Log log = LogFactory.getLog(AActionCreacionDocumentoInterno.class);
	  public ActionForward execute(ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response)
			    throws Exception
			  {
		  		log.info("Entry AActionCreacionDocumentoInterno... ");
		  		ActionMessages mensajes = new ActionMessages();
		  		response.setContentType("application/pdf");
		  		response.setCharacterEncoding("UTF-8");
		  		request.setCharacterEncoding("UTF-8");
		  		
		  		 HttpSession session = request.getSession(false);        

			        /*Verificar si la sesion se perdio*/
			   if (session==null){
			        	
			        	return (mapping.findForward("expiracion"));
			        	
			   }   
		  		
			   
			    Collection rs_upload_doc_internos_temp = new ArrayList();
		  		DocumentosInternosService service = new DocumentosInternosServiceImplement();	
		  		FFormDocumentoInterno fformDocumentoInterno = (FFormDocumentoInterno) form;	
		  		Funciones funciones = new Funciones();
		  		BDatosFirmante datos_firmante= new BDatosFirmante();
		  		PDFcreator pdf = new PDFcreator();
		  		
		  		
		  		try{
		  		
		  		String codigo_persona_noborrar =   String.valueOf(session.getAttribute("codigo_persona_noborrar"));		  		
		  		String cargo_personal = (String) session.getAttribute("cargo_personal");
		  		String ls_respuesta = (String) session.getAttribute("ls_respuesta");
		  		String ls_num_registro = (String) session.getAttribute("ls_codigo_documento");
		  		String ls_codigo_expediente = (String) session.getAttribute("ls_codigo_expediente");
		  		String ls_codigo_recepcion = (String) session.getAttribute("ls_codigo_recepcion");
		  		String ls_secuencia = (String) session.getAttribute("ls_secuencia");
		  		String mensajeEnOtrasBandejas="";
		  		session.removeAttribute("es_observado");
		  		
		  		datos_firmante=service.getDatosFirmantexCodigo(codigo_persona_noborrar);
		  		/***
		  		 * AGREGADO PARA MANEJAR LOS OBSERVADO - ESTOS SOLO PUEDEN MODIFICAR ALGUNA DATA. - Y NO CAMBIARA SU NUMERACION 
		  		 * NI EL TIPO DE DOCUMENTO
		  		 */
		  		String es_observado = (String) request.getParameter("es_observado");
				log.info("la es_observado: " + es_observado);
				es_observado = (es_observado==null)? "0" : es_observado;
		  		
		  		/**
		  		 * FIN
		  		 */
		  		
		  		//String ls_tipo_envio = (String) session.getAttribute("ls_tipenv");
		  		
		  		// c:set var = "ls_secuencia" value="nada" scope="session"  
		  		//log.info("secuencia > "+ls_secuencia);
		  		
		  		int numero_registro = 0;
		  		int codigodeexpediente = 0;
		  		int codigoderecepcion = 0;
		  		int secuencia = 0;
		  		int anioActual = Integer.parseInt(getAnioActual());
		  		
		  		ls_respuesta=(ls_respuesta==null)?"":ls_respuesta;
		  		ls_num_registro=(ls_num_registro==null)?"":ls_num_registro;
		  		ls_codigo_expediente=(ls_codigo_expediente==null)?"":ls_codigo_expediente;
		  		ls_codigo_recepcion=(ls_codigo_recepcion==null)?"":ls_codigo_recepcion;
		  		ls_secuencia=(ls_secuencia==null)?"":ls_secuencia;
		  		//ls_tipo_envio=(ls_tipo_envio==null)?"":ls_tipo_envio;
		  		
		
		  		String none=null;
		  		boolean llevaIniciales = true;
		  		boolean esBorrador = true;
		  		boolean llevaFirmantes = false;
		  		boolean llevaVistoBuenos = false;
		  		int codigoDelDocumentoNuevo=0;

		  		Image img1 = null;
		  		
		  		
		  		fformDocumentoInterno.getDocumentoInterno().setDescripcion((String)request.getParameter("descripcion-iframe"));
		  		
		  		
		  		log.info("/*************INICIO DE OPERACION GUARDAR O ACTUALIZAR *******************/");
		  		log.info("==Recuperando datos del Formulario");
		  		FormFile fileSignature = fformDocumentoInterno.getFile();

		  		if(fileSignature != null && fileSignature.getFileName().length()>0){
		              log.info("Entry..")  ;
		              log.info("file name: " +fileSignature.getFileName())  ;
		              java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(fileSignature.getFileData());
				  	  img1 = Image.getInstance(awtImage, null);
		         }
		  		

		  		boolean estaactivado = fformDocumentoInterno.getChecked();
		  		log.info("Ejemplo check------> "+estaactivado);
		  		
		  		String listadedocfirmadosadjuntos="";  		
		
		  		  			
		  		log.info("/**********CARGO CODIGOS DE OFICINAS CON COPIA********************/");
		  		
		  		String codigos_copia_oficina_destino[]=fformDocumentoInterno.getCopia_oficinas_destino();
		  		StringBuffer codigos_copias_oficinas =new StringBuffer();
		  		
		  		if(codigos_copia_oficina_destino!=null && codigos_copia_oficina_destino.length>0 ){
		  		 for (int   i = 0;   i < codigos_copia_oficina_destino.length;   i++) {
		  			codigos_copias_oficinas.append(codigos_copia_oficina_destino[i]+",");
//		  			log.info("Oficinas copia> "+codigos_copias_oficinas);
				}
		  		 
	  			log.info("codigos oficinas destinos: " +codigos_copias_oficinas.toString().substring(0, codigos_copias_oficinas.length()-1) );
		  			fformDocumentoInterno.getDocumentoInterno().setCodigos_oficinas_destinos_copias(codigos_copias_oficinas.toString().substring(0, codigos_copias_oficinas.length()-1));
		  		
		  		}else{

		  			fformDocumentoInterno.getDocumentoInterno().setCodigos_oficinas_destinos_copias(none);
		  		}
		  		log.info("/**************FIN CARGA DE CODIGOS CON COPIA******************/");
		  		
		  		
		  		
		  		log.info("/**********CARGO CODIGOS DE OFICINAS FIRMANTES********************/");
		  		
		  		String codigos_firmantes_destino[]=fformDocumentoInterno.getFirmantes_destino();
		  		StringBuffer codigos_firmantes_destino_ls =new StringBuffer();
		  		
		  		if(codigos_firmantes_destino!=null && codigos_firmantes_destino.length>0 ){
		  			log.info("Enter CODIGOS DE OFICINAS FIRMANTES********************");
			  		 for (int   i = 0;   i < codigos_firmantes_destino.length;   i++) {
			  			codigos_firmantes_destino_ls.append(codigos_firmantes_destino[i]+",");
	//		  			log.info("Oficinas copia> "+codigos_copias_oficinas);
			  		 }
			  		llevaFirmantes=true; 
		  		 	log.info("codigos OFICINAS FIRMANTES: " +codigos_firmantes_destino_ls.toString().substring(0, codigos_firmantes_destino_ls.length()-1) );
		  			fformDocumentoInterno.getDocumentoInterno().setCodigos_firmantes_destino(codigos_firmantes_destino_ls.toString().substring(0, codigos_firmantes_destino_ls.length()-1));
		  		
		  		}else{

		  			fformDocumentoInterno.getDocumentoInterno().setCodigos_firmantes_destino(none);
		  		}
		  		log.info("/**************FIN CARGA DE CODIGOS FIRMANTES******************/");
		  		
		  		
		  		log.info("/**********CARGO CODIGOS DE OFICINAS VISTOBUENO********************/");
		  		
		  		String codigos_visto_bueno_destino[]=fformDocumentoInterno.getVisto_bueno_destino();
		  		StringBuffer codigos_visto_bueno_destino_ls =new StringBuffer();
		  		
		  		if(codigos_visto_bueno_destino!=null && codigos_visto_bueno_destino.length>0 ){
			  		 for (int   i = 0;   i < codigos_visto_bueno_destino.length;   i++) {
			  			codigos_visto_bueno_destino_ls.append(codigos_visto_bueno_destino[i]+",");
	//		  			log.info("Oficinas copia> "+codigos_copias_oficinas);
			  		 }
		  		 	llevaVistoBuenos=true;
		  		 	log.info("codigos oficinas VISTOBUENO: " +codigos_visto_bueno_destino_ls.toString().substring(0, codigos_visto_bueno_destino_ls.length()-1) );
		  			fformDocumentoInterno.getDocumentoInterno().setCodigos_visto_bueno_destino(codigos_visto_bueno_destino_ls.toString().substring(0, codigos_visto_bueno_destino_ls.length()-1));
		  		
		  		}else{

		  			fformDocumentoInterno.getDocumentoInterno().setCodigos_visto_bueno_destino(none);
		  		}
		  		log.info("/**************FIN CARGA DE CODIGOS VISTOBUENO******************/");
		  		

		  		
		  		ArrayList<BOficinas> areasAdministrativasList =	 (ArrayList<BOficinas>) request.getSession().getAttribute("areasAdministrativasList");
		  		ArrayList<String>  siglasOficinasCopias = new ArrayList<String>();
		  		
		  		if(codigos_copia_oficina_destino !=null && codigos_copia_oficina_destino.length>0){
		  			obtener_siglas_oficina(codigos_copia_oficina_destino,areasAdministrativasList, siglasOficinasCopias);
		  		 }

		  		
//		  		log.info("personassss" +fformDocumentoInterno.getPersonas());
		  		
		  		String cargo_persona_destinatario=service.getCargo(fformDocumentoInterno.getPersonas());
		  		String dirigido_a=service.getNombre_Personal(fformDocumentoInterno.getPersonas());
		  		fformDocumentoInterno.setDirigido_a(dirigido_a);
		  		log.info("***********************");
		  		log.info("A: HACIA >> " +fformDocumentoInterno.getCodigo_oficina());
		  		log.info("B: DESDE >> " +fformDocumentoInterno.getCodigo_oficina_pertenece());
		  		log.info("***********************");
		  		String nombre_oficina_destino=obtener_siglas_oficina(fformDocumentoInterno.getCodigo_oficina(),areasAdministrativasList);
		  		
		  		
		  		fformDocumentoInterno.setCargo_persona_destinatario(cargo_persona_destinatario);
		  		String nombreSelloOficina= service.getNombreSelloOficina(fformDocumentoInterno.getCodigo_oficina_pertenece());
		  		fformDocumentoInterno.setNombre_oficial_anio(service.getNombreOficialAnio(anioActual));
		  		
		  		fformDocumentoInterno.setNombreSelloOficina(nombreSelloOficina);
		  		
//		  		log.info("nombre sello de oficina: " +fformDocumentoInterno.getNombreSelloOficina());
		  		log.info("Determinar segun el tipo de documento y tambien segun la oficina el nombre del documento");
		  		String nombreOficina=service.getSiglasOficina(fformDocumentoInterno.getCodigo_oficina_pertenece());
		  		String nombreFile="";
		  		String filename="";	
		  		int numero_doc=0;
		  		
		  		
		  		log.info("/***INICIO DE ACTUALIZACION DE LA LISTA QUE PASARA A GRABARSE EN BASE DE DATOS***/");
		  		log.info("/***TAMBIEN SE SUBE EL ARCHIVO NUEVO AL SERVIDOR!!!***/");
		  		
		  		Collection listaDeArchivosAdjuntos = new ArrayList();
		  		Collection backup_rs_upload_doc_internos = new ArrayList();
		  		
		  		backup_rs_upload_doc_internos = (Collection) session.getAttribute("backup_rs_upload_doc_internos");
	  			rs_upload_doc_internos_temp = (Collection) session.getAttribute("rs_upload_doc_internos");
	  			
	  			log.info("Inicio carga de lista antigua");
	  			log.info("documentos que ya han sido cargados anteriormente");
	  			if(rs_upload_doc_internos_temp!=null && rs_upload_doc_internos_temp.size()>0){
	  				
	  				Iterator it=rs_upload_doc_internos_temp.iterator();
	  				while(it.hasNext()){
	  					BArchivo archivoenlista=(BArchivo)it.next();
	  					//log.info(archivoenlista.getNombre_archivo());
	  					
	  					archivoenlista.setNombre_firmante(fformDocumentoInterno.getFirmado_por());
	  					//archivoenlista.setRuta(Constantes.CarpetaArchivosDesarrolloenTramiteFirma.getNombre());
	  					
	  					//if(!archivoenlista.isIsvisado()){
	  					if(archivoenlista.isIschecked()){  						
		  						archivoenlista.setIsvisado(true);
	  					}else{
		  						archivoenlista.setIsvisado(false);
	  					}
	  					
	  				/*	archivoenlista.setRuta(Constantes.CarpetaArchivosDesarrolloenTramiteVisadosBackup.getNombre());
	  					
	  					System.out.println("Id_archivo_proyecto "+archivoenlista.getId_archivo_proyecto());
	  					if(archivoenlista.getId_archivo_proyecto()>0){
	  						System.out.println("aquittt");
	  							archivoenlista.setRuta(Constantes.CarpetaArchivosDesarrolloenTramiteFirmadosBackup.getNombre());
	  					}
	  					//}
	  				*/	
  					
	  					try {
	  						
	  						/***
					  		 * AGREGADO PARA MANEJAR DOCUMENTOS OBSERVADOS
					  		 */
							if(!es_observado.equals("1") && !es_observado.equals("2")){
								
								subir_archivo(archivoenlista);
								
							}
							/**
							 * FIN OBSERVADOS
							 */
							
	  					} catch (SQLException e) {
	  						// TODO Auto-generated catch block
	  						e.printStackTrace();
	  					} catch (IOException e) {
	  						// TODO Auto-generated catch block
	  						e.printStackTrace();
	  					} catch (Exception e) {
	  						// TODO Auto-generated catch block
	  						e.printStackTrace();
	  					}	
	  					
	  					
	  					listaDeArchivosAdjuntos.add(archivoenlista);
	  					listadedocfirmadosadjuntos=listadedocfirmadosadjuntos+archivoenlista.getNombre_archivo()+",";
	  					
	  				}
	  					  				
	  			}else{log.info("No hay ---");}	
	  			
	  			
	  			log.info("Inicio carga de lista nueva");
	  			log.info("nuevos en la lista");
	  			if (fformDocumentoInterno.getArchivos() != null && fformDocumentoInterno.getArchivos().size() > 0) {
	  					Iterator itin = fformDocumentoInterno.getArchivos().iterator();
	  					
	  					while (itin.hasNext()) {
	  						BArchivo archivo = (BArchivo) itin.next();
	  						archivo.setNombre_archivo_cifrado(funciones.of_valida_letras(archivo.getNombre_archivo()));

	  						if(archivo.isIschecked()){
		  							archivo.setIsvisado(true);
	  						}
		  					archivo.setRuta(Constantes.CarpetaArchivosDesarrolloenTramiteVisadosBackup.getNombre());
	  						
	
	  						try {
	  							
	  							/***
	  					  		 * AGREGADO PARA MANEJAR DOCUMENTOS OBSERVADOS
	  					  		 */
	  							if(!es_observado.equals("1") && !es_observado.equals("2")){
	  							
		  						subir_archivo(archivo);
		  						
	  							}
	  							/***
	  							 * FIN DE OBSERVADOS
	  							 */
	  							
		  					} catch (SQLException e) {
		  						// TODO Auto-generated catch block
		  						e.printStackTrace();
		  					} catch (IOException e) {
		  						// TODO Auto-generated catch block
		  						e.printStackTrace();
		  					} catch (Exception e) {
		  						// TODO Auto-generated catch block
		  						e.printStackTrace();
		  					}	
		  					
	  						listaDeArchivosAdjuntos.add(archivo);
	  						listadedocfirmadosadjuntos=listadedocfirmadosadjuntos+archivo.getNombre_archivo()+",";
	  					}
	  					
	  			}else{log.info("No hay ---");}	
	  			
	  			 				  			
	  			if(listadedocfirmadosadjuntos.equals("")){
	  				log.info("Total Ningun archivo adjunto");
	  				fformDocumentoInterno.setNombre_doc_adjuntos(none);
	  			}
	  			else{
	  				log.info("listadedocfirmadosadjuntos");
	  				fformDocumentoInterno.setNombre_doc_adjuntos(listadedocfirmadosadjuntos.substring(0, listadedocfirmadosadjuntos.length()-1));
	  				log.info("Doc adjuntos - > "+fformDocumentoInterno.getNombre_doc_adjuntos());
	  			}
			  		
		  		log.info("/******** FIN *************/");
		  		log.info("=================================");
		  		
		  		log.info("/******INICIO PROCESO DE ACTUALIZACION DE DB*****/");
		  		
		  		/***
		  		 * AGERGADO PARA CARGAR PROYECTO
		  		 */
			    String tipo_proyecto_ls = (String) request.getParameter("tipo_proyecto");
				
				tipo_proyecto_ls = (tipo_proyecto_ls==null)? "0" : tipo_proyecto_ls;
				tipo_proyecto_ls = (tipo_proyecto_ls.equals(""))? "0" : tipo_proyecto_ls;
				tipo_proyecto_ls = (tipo_proyecto_ls.equals("3"))? "0" : tipo_proyecto_ls;
				
				log.info("la tipo_proyecto_ls: " + tipo_proyecto_ls);
				if(tipo_proyecto_ls.equals("1") || tipo_proyecto_ls.equals("2")){
		  			fformDocumentoInterno.setCodigo_documento_interno(null);
		  		}
				/****
				 * FIN
				 */
		  		
				if(datos_firmante.isEsoficinamaxima()){
	  				llevaIniciales = false;
	  			}

				log.info("llevaFirmanteso "+llevaFirmantes);
				log.info("llevaVistoBuenos "+llevaVistoBuenos);
				
		  		if(fformDocumentoInterno.getCodigo_documento_interno()==null){ //se trata documento nuevo
		  			log.info("------ >>> Se trata de documento nuevo");	
		  			
		  			
		  			
		  			/*if(cargo_personal.equals("3") || cargo_personal.equals("10") || cargo_personal.equals("13") || cargo_personal.equals("11")){
		  				llevaIniciales = false;
		  			}*/

		  		/***
		  		 * MODIFICADO PARA LA CREACION DE PROYECTOS DE DOCUMENTOS	
		  		 */
		  		BInfoDocumento bInfoDocuEliminado = new BInfoDocumento();	
		  		if(fformDocumentoInterno.getChecked()){
		  			
		  			
		  			bInfoDocuEliminado = service.getNumeroDocumentoEliminado(fformDocumentoInterno.getCodigo_oficina_pertenece(),fformDocumentoInterno.getCodigo_tipo_documento_interno());
		  			numero_doc=service.getSiguienteNumeroDocumento(fformDocumentoInterno.getCodigo_oficina_pertenece(),fformDocumentoInterno.getCodigo_tipo_documento_interno());
		  			/***
		  			 * PARA CREAR DOCUMENTOS CON NUMEROS ELIMINADOS
		  			 */
		  			if(bInfoDocuEliminado.getNumero_doc()>0){
		  				 numero_doc=bInfoDocuEliminado.getNumero_doc();
		  			}
		  			/**
		  			 * FIN
		  			 */
		  			
					System.out.println("numero_doc >"+numero_doc);
			  		/***GENERO EL NOMBRE DE PDF PARA LA BD****/
			  		nombreFile=generarNombreArchivoPDF(nombreOficina,fformDocumentoInterno.getCodigo_tipo_documento_interno(),numero_doc,fformDocumentoInterno.getFirmado_por(),llevaIniciales,esBorrador);	
		  		}else{
		  			
		  			bInfoDocuEliminado = service.getNumeroDocumentoEliminado(fformDocumentoInterno.getCodigo_oficina_pertenece(),fformDocumentoInterno.getCodigo_tipo_documento_interno(),fformDocumentoInterno.getChecked());
		  			numero_doc=service.getSiguienteNumeroDocumento(fformDocumentoInterno.getCodigo_oficina_pertenece(),fformDocumentoInterno.getCodigo_tipo_documento_interno(),fformDocumentoInterno.getChecked());
		  			
		  			/***
		  			 * PARA CREAR DOCUMENTOS CON NUMEROS ELIMINADOS
		  			 */
		  			if(bInfoDocuEliminado.getNumero_doc()>0){
		  				 numero_doc=bInfoDocuEliminado.getNumero_doc();
		  			}
		  			/**
		  			 * FIN
		  			 */
					System.out.println("numero_doc proyecto>"+numero_doc);
			  		/***GENERO EL NOMBRE DEL PROYECTO PDF PARA LA BD****/
			  		nombreFile=generarNombreArchivoPDF(nombreOficina,fformDocumentoInterno.getCodigo_tipo_documento_interno(),numero_doc,fformDocumentoInterno.getFirmado_por(),llevaIniciales,esBorrador);
			  		nombreFile=nombreFile.substring(9,nombreFile.length());
			  		nombreFile="PROY_"+nombreFile;
		  		}
		  		
		  		/***
		  		 * FIN
		  		 */
		  		
			  	filename = IConstante.filePath+"\\"+nombreFile;
		  		log.info("nombreFile" +nombreFile);
			  	
			  //	File file= new File(filename);	
			  	
			  	fformDocumentoInterno.getDocumentoInterno().setNombre_arhivo(nombreFile);
		  		fformDocumentoInterno.setNumero_doc(numero_doc);

		  		
		  		if(!ls_codigo_expediente.equals("") && !ls_secuencia.equals("") && !ls_num_registro.equals("")){
		  			numero_registro = Integer.valueOf(ls_num_registro);
		  			codigodeexpediente = Integer.valueOf(ls_codigo_expediente);
		  			secuencia = Integer.valueOf(ls_secuencia);
		  			codigoderecepcion = Integer.valueOf(ls_codigo_recepcion);
		  			log.info("Numero de registro ->> "+numero_registro);
		  		}
		  		log.info("Numero de registro ->> "+numero_registro);
		  		
		  		/**
		  		 * AUMENTADO PARA PROYECTOS DERIVADOS
		  		 */
		  		/*if(!tipo_proyecto_ls.equals("2")){
		  			fformDocumentoInterno.setCodigo_documento(numero_registro);
			  		fformDocumentoInterno.setCodigo_expediente(codigodeexpediente);
			  		fformDocumentoInterno.setCodigo_recepcion(codigoderecepcion);
			  		fformDocumentoInterno.setSecuencia_movimiento(secuencia);
		  		}*/
		  		if(tipo_proyecto_ls.equals("1") || tipo_proyecto_ls.equals("0")){
		  			fformDocumentoInterno.setCodigo_documento(numero_registro);
			  		fformDocumentoInterno.setCodigo_expediente(codigodeexpediente);
			  		fformDocumentoInterno.setCodigo_recepcion(codigoderecepcion);
			  		fformDocumentoInterno.setSecuencia_movimiento(secuencia);
		  		}
		  		/***
		  		 * FIN
		  		 */
		  		
		  		
		  		if(fformDocumentoInterno.getCodigo_documento() != 0){
		  			if(fformDocumentoInterno.getTipo_envio().equals("R")){

			  		}else{
			  			fformDocumentoInterno.setTipo_envio("D");
			  		}
	  			}
		  		
		  		log.info("Tipo envio -> "+fformDocumentoInterno.getTipo_envio());
		  		
		  		
		  		log.info("service.saveDocumentoInterno");
		  		service.saveDocumentoInterno(fformDocumentoInterno.getDocumentoInterno(), bInfoDocuEliminado);	

  		  		codigoDelDocumentoNuevo = service.getCodigoDocumentoInterno(nombreFile,fformDocumentoInterno.getCodigo_oficina_pertenece());
		  		log.info("Codigo de nuevo documento interno >"+codigoDelDocumentoNuevo);
		  		
		  		/***AQUI COMIENZZA ERROR **/
		  		if(listaDeArchivosAdjuntos!=null && listaDeArchivosAdjuntos.size()>0){
		  				Iterator it=listaDeArchivosAdjuntos.iterator();
		  				while(it.hasNext()){
		  					BArchivo archivoenlista=(BArchivo)it.next();
		  						  					
		  					try {
		  						log.info("service.saveDocumentosAdjuntos("+archivoenlista.getNombre_archivo()+","+archivoenlista.getId_archivo()+")");
		  						service.saveDocumentosAdjuntos(codigoDelDocumentoNuevo,archivoenlista);
		  					} catch (SQLException e) {
		  						// TODO Auto-generated catch block
		  						e.printStackTrace();
		  					} catch (IOException e) {
		  						// TODO Auto-generated catch block
		  						e.printStackTrace();
		  					} catch (Exception e) {
		  						// TODO Auto-generated catch block
		  						e.printStackTrace();
		  					}	
		  							  					
		  				}
		  						  				
		  			}	
		  		
		  		/**
		  		 * ACTUALIZO LA TABLA DETUPLOAD PARA QUE NO VEAN EL PROYECTO
		  		 */

		  		if(tipo_proyecto_ls.equals("1") || tipo_proyecto_ls.equals("2")){
		  			log.info("Entre -> actualiza detupinternos");
		  			
		  			service.updateEstadoUsuariosTblDetUpinternos((String)session.getAttribute("codigo_documento_proyecto_editar"));
		  		}
		  		/**
		  		 * FIN
		  		 */
		  		
		  		mensajes.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("tramite.documento.creado.exito"));
		  		mensajeEnOtrasBandejas = "Documento creado con éxito.";
		  		log.info("tramite.documento.creado.exito");
		  		String variable = "M";
		  		session.setAttribute("cuadro_mensaje", variable);
		  		//session.setAttribute("mensaje_general", IConstante.documentoCreadoExito);
		  		}
		  		else //se trata de una actualizacion
		  		{	//no se puede actualizar el tipo ni el numero ni el nombre del documento 
		  			log.info("------ >>> Se trata de una actualizacion");	
		  			String confirma = (String) request.getSession().getAttribute("confirma");
		  			confirma=(confirma==null)?"":confirma;

		  			if(confirma.equals("firma")){
		  			String ls_para_firma="firma";
			  		request.getSession().setAttribute("para_firma",ls_para_firma);
		  			}

		  			mensajes.clear();
		  			
		  			log.info("*********OPERACIONES PARA ACTUALIZAR MENSAJE Y NOMBRE CORRECTO DE DOCUMENTO");
		  			if(fformDocumentoInterno.getCodigo_estado_documento()==3){
		  				    log.info("Se trata de un documento estado FIRMADO >> ");
		  					esBorrador=false;
		  					mensajes.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("tramite.documento.firmado.exito"));
		  					log.info("tramite.documento.firmado.exito");
		  					/***GENERO EL NUMERO PARA EL CAMPO NUMERO DOC EN BD ****/
		  					numero_doc=service.getSiguienteNumeroDocumento(fformDocumentoInterno.getCodigo_oficina_pertenece(),fformDocumentoInterno.getCodigo_tipo_documento_interno());
		  					log.info("numero_doc >"+numero_doc);
		  					/***GENERO EL NOMBRE DE PDF PARA LA BD****/
		  					nombreFile=generarNombreArchivoPDF(nombreOficina,fformDocumentoInterno.getCodigo_tipo_documento_interno(),numero_doc,fformDocumentoInterno.getFirmado_por(),llevaIniciales,esBorrador);
		  					log.info("nombreFile >"+nombreFile);
		  					fformDocumentoInterno.getDocumentoInterno().setNombre_arhivo(nombreFile);
				  			fformDocumentoInterno.setNumero_doc(numero_doc);
		  			}else{
		  				
		  				/***
				  		 * MODIFICADO PARA LA CREACION DE PROYECTOS DE DOCUMENTOS	
				  		 */
				  		if(fformDocumentoInterno.getChecked()){
				  			numero_doc=service.getSiguienteNumeroDocumento(fformDocumentoInterno.getCodigo_oficina_pertenece(),fformDocumentoInterno.getCodigo_tipo_documento_interno());
							System.out.println("numero_doc >"+numero_doc);
					  		/***GENERO EL NOMBRE DE PDF PARA LA BD****/
					  		nombreFile=generarNombreArchivoPDF(nombreOficina,fformDocumentoInterno.getCodigo_tipo_documento_interno(),numero_doc,fformDocumentoInterno.getFirmado_por(),llevaIniciales,esBorrador);	
				  		}else{
				  			numero_doc=service.getSiguienteNumeroDocumento(fformDocumentoInterno.getCodigo_oficina_pertenece(),fformDocumentoInterno.getCodigo_tipo_documento_interno(),fformDocumentoInterno.getChecked());
							System.out.println("numero_doc proyecto>"+numero_doc);
					  		/***GENERO EL NOMBRE DEL PROYECTO PDF PARA LA BD****/
					  		nombreFile=generarNombreArchivoPDF(nombreOficina,fformDocumentoInterno.getCodigo_tipo_documento_interno(),numero_doc,fformDocumentoInterno.getFirmado_por(),llevaIniciales,esBorrador);
					  		nombreFile=nombreFile.substring(9,nombreFile.length());
					  		nombreFile="PROY_"+nombreFile;
				  		}
				  		
				  		/***
				  		 * FIN
				  		 */
		  					log.info("Se trata de documento borrador o por firmar");
		  					mensajes.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("tramite.documento.actualizado.exito"));
		  					mensajeEnOtrasBandejas = "Documento actualizado con éxito.";
		  					log.info("tramite.documento.actualizado.exito");
		  					String variable = "M";
		  			  		session.setAttribute("cuadro_mensaje", variable);
		  			  		//session.setAttribute("mensaje_general", IConstante.documentoModificadoExito);
		  			}
		  			
		  			/***
				  		 * AGREGADO PARA MANEJAR DOCUMENTOS OBSERVADOS
				  		 */
					if(!es_observado.equals("1") && !es_observado.equals("2")){
						
		  			
		  			log.info("service.updateDocumentoInterno");
		  			service.updateDocumentoInterno(fformDocumentoInterno.getDocumentoInterno());
		  					  		
			  						  			
			  			if(listaDeArchivosAdjuntos!=null && listaDeArchivosAdjuntos.size()>0){
			  				Iterator it=listaDeArchivosAdjuntos.iterator();
			  				while(it.hasNext()){
			  					BArchivo archivoenlista=(BArchivo)it.next();
			  					
			  					if(archivoenlista.getId_archivo() == 0){
			  						try {
			  							log.info("service.saveDocumentosAdjuntos("+archivoenlista.getNombre_archivo()+")");
				  						service.saveDocumentosAdjuntos(fformDocumentoInterno.getCodigo_documento_interno(),archivoenlista);
				  					} catch (SQLException e) {
				  						// TODO Auto-generated catch block
				  						e.printStackTrace();
				  					} catch (IOException e) {
				  						// TODO Auto-generated catch block
				  						e.printStackTrace();
				  					} catch (Exception e) {
				  						// TODO Auto-generated catch block
				  						e.printStackTrace();
				  					}	
			  					}else{
			  						try {
			  							log.info("service.updateDocumentosAdjuntos("+archivoenlista.getNombre_archivo()+")");
				  						service.updateDocumentosAdjuntos(fformDocumentoInterno.getCodigo_documento_interno(),archivoenlista);
				  					} catch (SQLException e) {
				  						// TODO Auto-generated catch block
				  						e.printStackTrace();
				  					} catch (IOException e) {
				  						// TODO Auto-generated catch block
				  						e.printStackTrace();
				  					} catch (Exception e) {
				  						// TODO Auto-generated catch block
				  						e.printStackTrace();
				  					}	
				  						
			  					}

			  					if(backup_rs_upload_doc_internos!=null && backup_rs_upload_doc_internos.size()>0){
					  				Iterator it2=backup_rs_upload_doc_internos.iterator();
					  				while(it2.hasNext()){
					  					BArchivo backup_archivo=(BArchivo)it2.next();
					  					log.info(backup_archivo.getNombre_archivo());
					  					
					  					if(archivoenlista.getId_archivo()== backup_archivo.getId_archivo()){
					  						backup_archivo.setEstado("X");
					  					}
					  					
					  				}
			  					}
			  					
			  					  					
			  				}
			  						  				
			  			}	
			  			
			  			/***/
			  			if(backup_rs_upload_doc_internos!=null && backup_rs_upload_doc_internos.size()>0){
			  				Iterator it3=backup_rs_upload_doc_internos.iterator();
			  				while(it3.hasNext()){
			  					BArchivo backup_archivo=(BArchivo)it3.next();
			  								  					
			  					if(!backup_archivo.getEstado().equals("X")){
			  						try {
			  							log.info("service.deleteDocumentosAdjuntos("+backup_archivo.getNombre_archivo()+")");
				  						service.deleteDocumentosAdjuntos(fformDocumentoInterno.getCodigo_documento_interno(),backup_archivo);
				  					} catch (SQLException e) {
				  						// TODO Auto-generated catch block
				  						e.printStackTrace();
				  					} catch (IOException e) {
				  						// TODO Auto-generated catch block
				  						e.printStackTrace();
				  					} catch (Exception e) {
				  						// TODO Auto-generated catch block
				  						e.printStackTrace();
				  					}
			  					}
			  					
			  				}
	  					}
			  		
			  		/*****
			  		 * 
			  		 */
			  			
						}else{
							//MODIFICO COMO OBSERVADO
							
							
							log.info("service.updateDocumentoInternoObservado");
				  			service.updateDocumentoInternoObservado(fformDocumentoInterno.getDocumentoInterno(),es_observado);
							
							
						}
						/***
						 * FIN DE OBSERVADOS
						 */
	
		  		}
		  		
		  		log.info("/************ FIN ***********************/");
		  		log.info("==========================================");
		  		
		  		request.setAttribute("docInterno",fformDocumentoInterno.getDocumentoInterno());
		        request.setAttribute(Globals.MESSAGE_KEY, mensajes);
		        
		  		log.info("filename global" +fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo());
		  		
		  		log.info("/******************* INICIO CREACION DE DOCUMENTO PDF *********************/"); 
		  		ArrayList<EstandarBean> tipoDocumentoInternosList=service.getTipoDocumentosInternosLista();
		  		String nombre_tipo_documento = getNombreDocumentoTipo(fformDocumentoInterno.getCodigo_tipo_documento_interno(),tipoDocumentoInternosList);
		  		
		  		try{ 
		  			
		  			switch (fformDocumentoInterno.getCodigo_tipo_documento_interno()) {
		  			
		  			case 2:
		  				log.info("Oficio");
						pdf.createPdf_Oficio(fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo(), fformDocumentoInterno.getDocumentoInterno(),nombre_tipo_documento,nombreOficina,siglasOficinasCopias,nombre_oficina_destino,img1);
						break;
					case 5:
						log.info("Informe");
						pdf.createPdf_Informe(fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo(), fformDocumentoInterno.getDocumentoInterno(),nombre_tipo_documento,nombreOficina,siglasOficinasCopias,nombre_oficina_destino,img1);
						break;

					default:
						log.info("Memo, Resolucion, Carta, Memo_multiple");
						log.info(fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo()+","+fformDocumentoInterno.getDocumentoInterno()+","+nombre_tipo_documento+","+nombreOficina+","+siglasOficinasCopias+","+nombre_oficina_destino+","+llevaIniciales);
						pdf.createPdf(fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo(), fformDocumentoInterno.getDocumentoInterno(),nombre_tipo_documento,nombreOficina,siglasOficinasCopias,nombre_oficina_destino,img1,llevaIniciales);
						break;
					}

		  			/*
		  		 Desktop d=java.awt.Desktop.getDesktop ();
		         d.open (new java.io.File (new File(IConstante.filePath),fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo()));
		         */
		  		}
		  		 catch(FileNotFoundException e ){
		  			log.error("No se encuentra el archivo o esta abierto siendo utilizado por otro proceso");
		  		 }
		  		catch(NullPointerException e2){
		  			log.error("No se encuentra el archivo o es null");
		  		 }
		  		
		  		log.info("/************ FIN ***********************/");
		  		log.info("==========================================");
		  		
		  				  		
		  		
		  		
		  		//Mostra codigo de Investigadores
			    //colocamos la lista para ser accesada desde la jsp
			    // fformDocumentoInterno.setTipoDocumentoInternosList(tipoDocumentoInternosList);
			    // log.info("tamanio lista tipoDocumentoInternos:  "+fformDocumentoInterno.getTipoDocumentoInternosList().size());
			    // Forward control to this Action's input page.
		         
		  		//ahora mostraremos el archivo adjunto
		  		
		  		//File scfile = new File(IConstante.filePath, fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo());
		  		
		  	    //mostrarPDFGenerado(response, fformDocumentoInterno, scfile);
		  		
		  		log.info("/*********** INICIO OPERACION UTILITARIA **************/");
		  		int codigodelDocumento = fformDocumentoInterno.getCodigo_documento();
		  		log.info("-------------> "+codigodelDocumento);
		  		
		  		/***
		  		 * AGREGADO PARA PROYECTOS DERIVACION
		  		 */
		  		/*if(!tipo_proyecto_ls.equals("2")){
		  			log.info("Cambie-->0");
		  			codigodelDocumento=0;
		  		}*/
		  		if(tipo_proyecto_ls.equals("1")){
		  			log.info("Cambie-->0");
		  			codigodelDocumento=0;
		  		}
		  		
		  		/**
		  		 * FIN
		  		 */
		  		
		  		
		  		/***
		  		 * ACTUALIZO SI ES PROYECTO GENERAL
		  		*/ 
		  		if(llevaFirmantes  &&  fformDocumentoInterno.getCodigo_estado_documento()==5){
		  			String nombreArchivoInicial = fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo();
		  			String nombreArchivoFinal= nombreArchivoInicial.substring(9,nombreArchivoInicial.length()); // ESTOY QUITANDO EL PREFIJO -BORRADOR_
		  			BArchivo archivoenlista = new BArchivo();	
		  			
		  			archivoenlista.setNombre_archivo(nombreArchivoInicial);
		  			archivoenlista.setNombre_archivo_cifrado(nombreArchivoFinal);
		  			archivoenlista.setRuta(Constantes.CarpetaArchivosDesarrolloenTramiteFirmadosTemporalBackup.getNombre());
		  			archivoenlista.setIsvisado(false);
		  			archivoenlista.setIsfirmado(false);
		  			archivoenlista.setEstado_usuario_origen("C");
		  			archivoenlista.setEstado_usuario_fin("A");
		  			archivoenlista.setUsuario(String.valueOf(session.getAttribute("nombreusuario")));
		  			
		  			String rutaOrigenPrincipal=Constantes.CarpetaArchivosDesarrolloenTramiteFirmadosBackup.getNombre()+nombreArchivoInicial;
		  			String rutaDestinoPrincipal=Constantes.CarpetaArchivosDesarrolloenTramiteFirmadosTemporalBackup.getNombre()+nombreArchivoFinal;
		  			copiarDocumento(rutaOrigenPrincipal, rutaDestinoPrincipal);
		  			
		  			
		  			log.info("service.saveDocumentoPrincipalFirmantes("+fformDocumentoInterno.getCodigo_documento_interno()+","+archivoenlista+","+fformDocumentoInterno.getFirmantes_destino()+")");
					service.saveDocumentoPrincipalFirmantes((fformDocumentoInterno.getCodigo_documento_interno()==null)? codigoDelDocumentoNuevo:fformDocumentoInterno.getCodigo_documento_interno(),archivoenlista,fformDocumentoInterno.getFirmantes_destino());
					
					
					
					
					/*Para renombrar o mover un archivo Java usar los siguientes pasos 

					//Original file 
					File dataInputFile = new File("pathFile/fileName"); 
					//New path 
					File fileSendPath = new File("sendPath", dataInputFile.getName()); 
					//Moving the file. 
					dataInputFile.renameTo(fileSendPath);*/
		
		  		}
		  		/**
		  		 * FIN
		  		 */
		  		
		  		if(llevaVistoBuenos  &&  fformDocumentoInterno.getCodigo_estado_documento()==5){
		  			
					if(listaDeArchivosAdjuntos!=null && listaDeArchivosAdjuntos.size()>0){
		  				Iterator it=listaDeArchivosAdjuntos.iterator();
		  				while(it.hasNext()){
		  					BArchivo archivoenlista=(BArchivo)it.next();
			  							  			
				  			String rutaOrigenPrincipal=Constantes.CarpetaArchivosDesarrolloenTramiteVisadosBackup.getNombre()+archivoenlista.getNombre_archivo_cifrado();
				  			String rutaDestinoPrincipal=Constantes.CarpetaArchivosDesarrolloenTramiteVisadosTemporalBackup.getNombre()+archivoenlista.getNombre_archivo_cifrado();
				  			copiarDocumento(rutaOrigenPrincipal, rutaDestinoPrincipal);

				  			archivoenlista.setRuta(Constantes.CarpetaArchivosDesarrolloenTramiteVisadosTemporalBackup.getNombre());
				  			archivoenlista.setIsvisado(false);
				  			archivoenlista.setIsfirmado(false);
				  			archivoenlista.setEstado_usuario_origen("C");
				  			archivoenlista.setEstado_usuario_fin("A");
				  			archivoenlista.setUsuario(String.valueOf(session.getAttribute("nombreusuario")));
		  						  					
		  					try {
		  						if(archivoenlista.getId_archivo()==0){
		  							//NO SE QUE PONER
		  							//service.getListaArchivosAdjuntos(codigo_documento_interno);
		  						}
		  						log.info("service.saveDocumentoAdjuntoVistoBueno("+archivoenlista.getNombre_archivo()+","+archivoenlista.getId_archivo()+")");
		  						service.saveDocumentoAdjuntoVistoBueno((fformDocumentoInterno.getCodigo_documento_interno()==null)? codigoDelDocumentoNuevo:fformDocumentoInterno.getCodigo_documento_interno(),archivoenlista,fformDocumentoInterno.getVisto_bueno_destino());
		  						
		  					} catch (SQLException e) {
		  						// TODO Auto-generated catch block
		  						e.printStackTrace();
		  					} catch (IOException e) {
		  						// TODO Auto-generated catch block
		  						e.printStackTrace();
		  					} catch (Exception e) {
		  						// TODO Auto-generated catch block
		  						e.printStackTrace();
		  					}	
		  							  					
		  				}
		  						  				
		  			}	
					
					
		  		}
		  		

		  		if(codigodelDocumento != 0){
		  			
		  			request.removeAttribute("cargarproyecto");
		  			request.removeAttribute("es_proyecto");
		  			
		  			log.info("Entre 1");
		  			log.info("Registro nro > "+codigodelDocumento);
		  			
		  			//session.removeAttribute("cuadro_mensaje");
		  			
		  			if(fformDocumentoInterno.getCodigo_estado_documento()==3 && estaactivado){
			  			log.info("Operacion >>> DERIVACION DE DOCUMENTO INTERNO");
			  			
			  			/*****REMUEVO LA LISTA DE DOC FIRMADOS INTERNOS****/
			  			session.removeAttribute("backup_rs_upload_doc_internos");
				  		session.removeAttribute("rs_upload_doc_internos");
				  		session.removeAttribute("operacionpopup");
		       	    	session.removeAttribute("mensaje_reg");
		       	    	session.removeAttribute("mensaje_exp");	
		       	    	session.removeAttribute("mensaje");
				  		/**************************************************/
		       	    	String dato ="";
		       	    	dato = fformDocumentoInterno.getCodigo_documento_interno().toString();
			  			
			  			session.setAttribute("listaDeArchivosAdjuntos",listaDeArchivosAdjuntos);
			  			session.setAttribute("codigo_doc_para_grabar",dato);
			  			session.setAttribute("operacion_firma_documento","FDDI");
						session.removeAttribute("ls_respuesta");
						
						log.info("Codigo_documento_interno "+dato);
			  			log.info("tipo > FDDI");
						log.info("Sali del AActionCreacionDocumentoInterno direccionando a derivadocumento");
			  			return mapping.findForward("puentederivadocu");
	
		  			}else{
		  				log.info("Codigo del Estado > "+fformDocumentoInterno.getCodigo_estado_documento());
		  				session.removeAttribute("ls_codigo_documento");
		  				session.removeAttribute("ls_codigoDocumentoInterno");
		  				session.removeAttribute("ls_codigo_expediente");
				  		session.removeAttribute("ls_codigo_recepcion");
				  		session.removeAttribute("ls_secuencia");
		  				//String exitorespuesta="exito";
		  				
		  				
				  		
				  		
		  				if (ls_respuesta.equals("respuesta") || ls_respuesta.equals("estatico")){
		  					log.info("Tipo > "+fformDocumentoInterno.getTipo_envio());
		  					
		  					if(fformDocumentoInterno.getTipo_envio().equals("R")){

		  					    session.removeAttribute("ls_respuesta");
		  					    
		  					    session.setAttribute("nombreArchivoVer", fformDocumentoInterno.getNombre_arhivo());
		  		       	    	session.setAttribute("tipoDocumentoVer", fformDocumentoInterno.getCodigo_estado_documento().toString());
		  		       	    	session.setAttribute("codigoOficinaVer", fformDocumentoInterno.getCodigo_oficina_pertenece().toString());
		  		       	    	session.setAttribute("mensaje",mensajeEnOtrasBandejas);
		  		       	    	session.setAttribute("cuadro_mensaje", "BREI");

		  						log.info("Sali del AActionCreacionDocumentoInterno... me dirijo a bandeja reiterativo");
				                
				                return mapping.findForward("haciabreitera");
				                
		  					}else{
		  					    session.removeAttribute("ls_respuesta");
		  					   		  					    
		  		       	    	session.setAttribute("nombreArchivoVer", fformDocumentoInterno.getNombre_arhivo());
		  		       	    	session.setAttribute("tipoDocumentoVer", fformDocumentoInterno.getCodigo_estado_documento().toString());
		  		       	    	session.setAttribute("codigoOficinaVer", fformDocumentoInterno.getCodigo_oficina_pertenece().toString());
		  		       	    	session.setAttribute("mensaje",mensajeEnOtrasBandejas);
		  		       	    	session.setAttribute("cuadro_mensaje", "BDER");
		  		       	    	
		  		       	    	
		  						log.info("Sali del AActionCreacionDocumentoInterno... me dirijo a bandeja derivacion");
				                
				                return mapping.findForward("haciabderiva");
		  					}
		  						
		  						
		  				}else{
		  					session.removeAttribute("backup_rs_upload_doc_internos");
					  		session.removeAttribute("rs_upload_doc_internos");
		  					log.info("Sali del AActionCreacionDocumentoInterno");
			                log.info("---");
			                return mapping.findForward("exito");	
		  				}
		  				
		                
		  			}    
		  		}else{
		  			
		  			request.removeAttribute("cargarproyecto");
		  			request.removeAttribute("es_proyecto");
		  			
		  			
		  			log.info("Entre 2");
		  			log.info("Registro nro > "+codigodelDocumento);
		  			if(fformDocumentoInterno.getCodigo_estado_documento()==3 && estaactivado){
		  				log.info("Operacion >>> REGISTRO DE DOCUMENTO INTERNO");

			  			/*****REMUEVO LA LISTA DE DOC FIRMADOS INTERNOS****/
			  			session.removeAttribute("backup_rs_upload_doc_internos");
				  		session.removeAttribute("rs_upload_doc_internos");
				  		session.removeAttribute("operacionpopup");
		       	    	session.removeAttribute("mensaje_reg");
		       	    	session.removeAttribute("mensaje_exp");	
		       	    	session.removeAttribute("mensaje");
				  		
		       	    	String dato ="";
		       	    	dato = fformDocumentoInterno.getCodigo_documento_interno().toString();
			  			
			  			session.setAttribute("listaDeArchivosAdjuntos",listaDeArchivosAdjuntos);
			  			session.setAttribute("codigo_doc_para_grabar",dato);
			  			session.setAttribute("operacion_firma_documento","FDIBC");
			  			
			  			log.info("Codigo_documento_interno "+dato);
			  			log.info("tipo > FDIBC");
			  			log.info("Sali del AActionCreacionDocumentoInterno direccionando a mantdocumento");
			  			
			  			return mapping.findForward("puentemantdocu");
	
		  			}else{
		  				
		  				session.removeAttribute("ls_codigo_documento");
		  				session.removeAttribute("ls_codigoDocumentoInterno");
		  				
		  				session.removeAttribute("backup_rs_upload_doc_internos");
				  		session.removeAttribute("rs_upload_doc_internos");
				  		
				  		log.info("Codigo del Estado > "+fformDocumentoInterno.getCodigo_estado_documento());
		                log.info("Sali del AActionCreacionDocumentoInterno.............");
		                
		                return mapping.findForward("exito");
		  			}    
		  			
		  		}
		  		
		  				  		
			  }catch (Exception e) {
		            e.printStackTrace();
			  }
			  return mapping.findForward("error");
		  		
}
	
	  
	  private void copiarDocumento(String rutaOrigen, String rutaDestino){
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
	
/*	private void subirArchivosBloque(HttpSession session) {
		FFormDocumentoInterno oForm_ =(FFormDocumentoInterno)session.getAttribute("FFormDocumentoInterno");
		if(oForm_.getArchivos()!=null && oForm_.getArchivos().size()>0){
			Iterator it=oForm_.getArchivos().iterator();
			while(it.hasNext()){
				BArchivo archivo=(BArchivo)it.next();
				try {
					subir_archivo(archivo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}	
		}	
	}
*/	
			
	private void subir_archivo(BArchivo archivo) throws SQLException, IOException,Exception {

		Funciones funciones = new Funciones();
		String fileName = archivo.getNombre_archivo_cifrado();
		//String filePath = Constantes.CarpetaArchivosProduccion.getNombre();
			
		if (!fileName.equals("")) {

			int punto = fileName.lastIndexOf('.');
			log.info("el puntoLUUUU es.." + punto);
			log.info("el puntoLUUUU + 1 es.."
					+ fileName.substring(punto + 1));
			if (fileName.substring(punto + 1).equals("DOC")) {

				fileName = fileName.replace("DOC", "doc");
				System.out
						.println("El Cambiando el nombre a miniscula..XXXXXX:"
								+ fileName);
			}

			if (funciones.validaNombreDelArchivo(fileName)) {
				log.info("El ls_new_nombre_upload ees.."
						+ fileName);
				
				if(archivo.getId_archivo() == 0){ //este id es de la tabla tbl_cab_upload
					
					log.info("Es un archivo nuevo >>");
						//File fileToCreate = new File(Constantes.CarpetaArchivosDesarrolloenFirmadaDigitalBackup.getNombre(),ls_new_nombre_upload);
					
					if(archivo.getId_archivo_proyecto() > 0){
						//es un proyecto nuevo
						File originFile = new File(archivo.getRuta()+fileName);
					    File destinationFile = new File(Constantes.CarpetaArchivosDesarrolloenTramiteVisadosBackup.getNombre()+fileName);
					    System.out.println("Origen ->"+originFile.getPath());
					    System.out.println("Destino ->"+destinationFile.getPath());
					    
					   if (!originFile.exists()){ //|| destinationFile.exists()) {
					      System.out.println("umm");
						   return;
					   }
					   
						try {
							
						      FileInputStream fis = new FileInputStream(originFile);
								int longitud = fis.available();
								byte readData[] = new byte[longitud];
						    					      
						      FileOutputStream fos = new FileOutputStream(destinationFile);
						      int i = fis.read(readData);

						      while (i != -1) {
						        fos.write(readData, 0, i);
						        i = fis.read(readData);
						      }
						      fis.close();
						      fos.close();


						} catch( FileNotFoundException e ) {
							log.info("Archivo no encontrado..");
						    //Hacer algo 
						 }catch (IOException io){
							 log.info("Error de entrada Salida..");
						 }
						 
					}else{
						//no es un proyecto
						File fileToCreate = new File(archivo.getRuta(),fileName);
						if (!fileToCreate.exists()) {
							FileOutputStream fileOutStream = new FileOutputStream(
									fileToCreate);
							fileOutStream.write(archivo.getData());
							fileOutStream.flush();
							fileOutStream.close();
						}
					}
					
							
					/*if(archivo.isIsvisado()){
						
						File originFile = new File(Constantes.CarpetaArchivosDesarrolloenFirmadaDigitalBackup.getNombre()+ls_new_nombre_upload);
					    File destinationFile = new File(archivo.getRuta()+ls_new_nombre_upload);
					   if (!originFile.exists()){ //|| destinationFile.exists()) {
					      return;
					   }
					   
						try {
							
						      FileInputStream fis = new FileInputStream(originFile);
								int longitud = fis.available();
								byte readData[] = new byte[longitud];
						    					      
						      FileOutputStream fos = new FileOutputStream(destinationFile);
						      int i = fis.read(readData);

						      while (i != -1) {
						        fos.write(readData, 0, i);
						        i = fis.read(readData);
						      }
						      fis.close();
						      fos.close();


						} catch( FileNotFoundException e ) {
							log.info("Archivo no encontrado..");
						    //Hacer algo 
						 }catch (IOException io){
							 log.info("Error de entrada Salida..");
						 }
						
						
						
					}*/
					
				}else{
					//quiere decir que no tiene id tbl_cab_upload
					
					log.info("Es un archivo antiguo >> no es necesario subirlo pero igual lo subimos a la carpeta visados");
					log.info("si es que es necesario");
					/*if(archivo.isIsvisado()){*/
						
		/*				File originFile = new File(archivo.getRuta()+fileName);
					    File destinationFile = new File(Constantes.CarpetaArchivosDesarrolloenTramiteVisadosBackup.getNombre()+fileName);
					    System.out.println("Origen ->"+originFile.getPath());
					    System.out.println("Destino ->"+destinationFile.getPath());
					    
					   if (!originFile.exists()){ //|| destinationFile.exists()) {
					      System.out.println("umm");
						   return;
					   }
					   
						try {
							
						      FileInputStream fis = new FileInputStream(originFile);
								int longitud = fis.available();
								byte readData[] = new byte[longitud];
						    					      
						      FileOutputStream fos = new FileOutputStream(destinationFile);
						      int i = fis.read(readData);

						      while (i != -1) {
						        fos.write(readData, 0, i);
						        i = fis.read(readData);
						      }
						      fis.close();
						      fos.close();


						} catch( FileNotFoundException e ) {
							log.info("Archivo no encontrado..");
						    //Hacer algo 
						 }catch (IOException io){
							 log.info("Error de entrada Salida..");
						 }
						
			*/			
						
					/*}*/					
					
				}
							
			}

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
		case 2://OFICIO
			nombre1="OFI_";
			break;
		case 3://RESOLUCION
			nombre1="RESO_";
			break;
		case 4://CARTA
			nombre1="CARTA_";
			break;
		case 5://INFORME
			nombre1="INFORME_";
			break;
		case 8://MEMO MULTIPLE
			nombre1="MEMO_MULTIPLE_";
			break;
		default:
			break;
		}
		//String nombre_archivo_generado=	IConstante.filePath+"\\"+nombre1+numero_doc+"_"+anio+"_CONCYTEC_"+nombreOficina+".pdf";
		String nombre_archivo_generado=	nombre1+numero_doc+"_"+anio+"_CONCYTEC_"+nombreOficina+iniciales+".pdf";
		if(esBorrador){
			nombre_archivo_generado="Borrador_"+nombre1+numero_doc+"_"+anio+"_CONCYTEC_"+nombreOficina+iniciales+".pdf";
		}
		log.info("nombre documento:" + nombre_archivo_generado);
		return nombre_archivo_generado;
	}
	
	private String getAnioActual() {
		Calendar c = new GregorianCalendar(); 
		String anio = Integer.toString(c.get(Calendar.YEAR));
		return anio;
	}
	
}

