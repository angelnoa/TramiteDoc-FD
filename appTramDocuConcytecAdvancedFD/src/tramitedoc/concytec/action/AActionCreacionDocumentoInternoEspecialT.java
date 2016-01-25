 
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

	import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BDatosFirmante;
import tramitedoc.concytec.bean.BInfoDocumento;
	import tramitedoc.concytec.bean.BOficinas;
	import tramitedoc.concytec.bean.EstandarBean;
	import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
	import tramitedoc.concytec.form.FFormDocumentoInternoEspecialT;
	import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Constantes;
	import tramitedoc.concytec.util.Funciones;
	import tramitedoc.concytec.util.IConstante;
import tramitedoc.concytec.util.PDFcreator;

	public class AActionCreacionDocumentoInternoEspecialT extends Action {
		protected  Log log = LogFactory.getLog(AActionCreacionDocumentoInternoEspecialT.class);
		  public ActionForward execute(ActionMapping mapping,
				    ActionForm form,
				    HttpServletRequest request,
				    HttpServletResponse response)
				    throws Exception
				  {
			  		log.info("Entry AActionCreacionDocumentoInternoEspecialT... ");
			  		ActionMessages mensajes = new ActionMessages();
			  		response.setContentType("application/pdf");
			  		response.setCharacterEncoding("UTF-8");
			  		request.setCharacterEncoding("UTF-8");
			  		
			  		 HttpSession session = request.getSession(false);        

				        /*Verificar si la sesion se perdio*/
				        if (session==null){
				        	
				        	return (mapping.findForward("expiracion"));
				        	
				        }   
				        
				    session.removeAttribute("es_proyecto");
			  		
				    Collection rs_upload_doc_internos_temp = new ArrayList();
			  		DocumentosInternosService service = new DocumentosInternosServiceImplement();	
			  		FFormDocumentoInternoEspecialT fformDocumentoInterno = (FFormDocumentoInternoEspecialT) form;	
			  		Funciones funciones = new Funciones();
			  		PDFcreator pdf = new PDFcreator();
			  		BDatosFirmante datos_firmante= new BDatosFirmante();
			  		
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
				  		fformDocumentoInterno.setChecked(false);
				  		
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
				  		ls_secuencia=(ls_respuesta==null)?"":ls_secuencia;
				  		
				  		String none=null;
				  		boolean esBorrador = true;
				  		boolean llevaIniciales = true;
				  		int codigoDelDocumentoNuevo;
				  		
			  		Image img1 = null;
			  		
			  		log.info("/*************INICIO DE OPERACION GUARDAR O ACTUALIZAR *******************/");
			  		log.info("==Recuperando datos del Formulario");
			  		
			  		/*FormFile fileSignature = fformDocumentoInterno.getFile();

			  		if(fileSignature != null && fileSignature.getFileName().length()>0){
			             // log.info("Entry..")  ;
			             // log.info("file name: " +fileSignature.getFileName())  ;
			              java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(fileSignature.getFileData());
					  	  img1 = Image.getInstance(awtImage, null);
			         }
			  		 */
			  		
			  		boolean estaactivado = fformDocumentoInterno.getChecked();
			  		fformDocumentoInterno.setCodigo_tipo_documento_interno(7);
			  		String listadedocfirmadosadjuntos="";  		
			  		log.info("Valor de Check > "+fformDocumentoInterno.getChecked());
			  		
			  		log.info("/**********CARGO CODIGOS DE OFICINAS CON COPIA********************/");
			  		
			  		//String codigos_copia_oficina_destino[]=fformDocumentoInterno.getCopia_oficinas_destino();
			  		fformDocumentoInterno.getDocumentoInterno().setCodigos_oficinas_destinos_copias(none);
			  		
			  		log.info("/**************FIN CARGA DE CODIGOS CON COPIA******************/");
			  		
			  		ArrayList<BOficinas> areasAdministrativasList =	 (ArrayList<BOficinas>) request.getSession().getAttribute("areasAdministrativasList");
			  		ArrayList<String>  siglasOficinasCopias= new ArrayList<String>();
			  		
			  		/*if(codigos_copia_oficina_destino !=null && codigos_copia_oficina_destino.length>0){
			  			obtener_siglas_oficina(codigos_copia_oficina_destino,areasAdministrativasList, siglasOficinasCopias);
			  		 }
			  		*/
			  		
			  		String cargo_persona_destinatario=service.getCargo(fformDocumentoInterno.getPersonas());
			  		String dirigido_a=service.getNombre_Personal(fformDocumentoInterno.getPersonas());
			  		fformDocumentoInterno.setDirigido_a(dirigido_a);
			  		log.info("***********************");
			  		log.info("A: " +fformDocumentoInterno.getCodigo_oficina());
			  		log.info("B: " +fformDocumentoInterno.getCodigo_oficina_pertenece());
			  		log.info("***********************");
			  		String nombre_oficina_destino=obtener_siglas_oficina(fformDocumentoInterno.getCodigo_oficina(),areasAdministrativasList);
			  		
			  		
			  		fformDocumentoInterno.setNombre_oficial_anio(service.getNombreOficialAnio(anioActual));
			  		fformDocumentoInterno.setCargo_persona_destinatario(cargo_persona_destinatario);
			  		String nombreSelloOficina= service.getNombreSelloOficina(fformDocumentoInterno.getCodigo_oficina_pertenece());
			  		
			  		fformDocumentoInterno.setNombreSelloOficina(nombreSelloOficina);
			  		
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
		  			
		  			log.info("inicio carga de lista antigua");
		  			if(rs_upload_doc_internos_temp!=null && rs_upload_doc_internos_temp.size()>0){
		  				Iterator it=rs_upload_doc_internos_temp.iterator();
		  				while(it.hasNext()){
		  					BArchivo archivoenlista=(BArchivo)it.next();
		  					archivoenlista.setNombre_firmante(fformDocumentoInterno.getFirmado_por());
		  					
		  						if(archivoenlista.isIschecked())  						
			  						archivoenlista.setIsvisado(true);
			  					else
			  						archivoenlista.setIsvisado(false);

		  						archivoenlista.setRuta(Constantes.CarpetaArchivosDesarrolloenTramiteVisadosBackup.getNombre());
		  					
		  					listaDeArchivosAdjuntos.add(archivoenlista);
		  					listadedocfirmadosadjuntos=listadedocfirmadosadjuntos+archivoenlista.getNombre_archivo()+",";
		  					
		  				}
		  						  				
		  			}	
		  			log.info("inicio carga de lista nueva");
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
			  					
		  						listaDeArchivosAdjuntos.add(archivo);
		  						listadedocfirmadosadjuntos=listadedocfirmadosadjuntos+archivo.getNombre_archivo()+",";
		  					}
		  					
		  			}
		  			
		  			if(listadedocfirmadosadjuntos.equals("")){
		  				log.info("Ningun archivo adjunto");
		  				fformDocumentoInterno.setNombre_doc_adjuntos(none);
		  			}
		  			else{
		  				log.info("listadedocfirmadosadjuntos");
		  				fformDocumentoInterno.setNombre_doc_adjuntos(listadedocfirmadosadjuntos.substring(0, listadedocfirmadosadjuntos.length()-1));
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
			  		
					
			  		if(fformDocumentoInterno.getCodigo_documento_interno()==null){ //se trata documento nuevo
			  			log.info("Se trata de documento nuevo");	
			  			
			  		
			  			if(datos_firmante.isEsoficinamaxima()){
			  				llevaIniciales = false;
			  			}
			  			
			  		/***GENERO EL NUMERO PARA EL CAMPO NUMERO TEMPORAL EN BD ****
			  		numero_doc=service.getSiguienteNumeroDocumento(fformDocumentoInterno.getCodigo_oficina_pertenece(),fformDocumentoInterno.getCodigo_tipo_documento_interno());
			  		log.info("numero temporal > "+numero_doc);
			  		
			  		/***GENERO EL NOMBRE DE PDF PARA LA BD****
			  		nombreFile=generarNombreArchivoPDF(nombreOficina,fformDocumentoInterno.getCodigo_tipo_documento_interno(),numero_doc,llevaIniciales,esBorrador);
				  	//String filename=IConstante.filePath+"\\MEMO_01.pdf";*/
			  		
			  		
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
			  		log.info("filename" +filename);
				  	
				  	File file= new File(filename);	
				  	
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
			  		
			  		/*if(listaDeArchivosAdjuntos!=null && listaDeArchivosAdjuntos.size()>0){
			  				Iterator it=listaDeArchivosAdjuntos.iterator();
			  				while(it.hasNext()){
			  					BArchivo archivoenlista=(BArchivo)it.next();
			  						  					
			  					try {
			  						log.info("service.saveDocumentosAdjuntos("+archivoenlista.getNombre_archivo()+")");
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
			  		*/
			  		mensajes.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("tramite.documento.creado.exito"));
			  		mensajeEnOtrasBandejas = "Documento creado con éxito.";
			  		String variable = "M";
			  		session.setAttribute("cuadro_mensaje", variable);
			  		log.info("tramite.documento.creado.exito");

			  		}else //se trata de una actualizacion
			  		{//no se puede actualizar el tipo ni el numero ni el nombre del documento 
			  			
			   			String confirma = (String) request.getSession().getAttribute("confirma");
			  			confirma=(confirma==null)?"":confirma;
			  			log.info("Esta actualizando >> "+confirma);
			  			
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
			  					/***GENERO EL NUMERO PARA EL CAMPO NUMERO DOC EN BD ***
			  					numero_doc=service.getSiguienteNumeroDocumento(fformDocumentoInterno.getCodigo_oficina_pertenece(),fformDocumentoInterno.getCodigo_tipo_documento_interno());
			  					log.info("numero_doc >"+numero_doc);
			  					/***GENERO EL NOMBRE DE PDF PARA LA BD***
			  					nombreFile=generarNombreArchivoPDF(nombreOficina,fformDocumentoInterno.getCodigo_tipo_documento_interno(),numero_doc,llevaIniciales,esBorrador);
			  					log.info("nombreFile >"+nombreFile);
			  					fformDocumentoInterno.getDocumentoInterno().setNombre_arhivo(nombreFile);
					  			fformDocumentoInterno.setNumero_doc(numero_doc);*/

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
			  			}
			  			
			  			/***
				  		 * AGREGADO PARA MANEJAR DOCUMENTOS OBSERVADOS
				  		 */
					if(!es_observado.equals("1") && !es_observado.equals("2")){
			  			
			  			log.info("service.updateDocumentoInterno");
			  			service.updateDocumentoInterno(fformDocumentoInterno.getDocumentoInterno());
			  			
			  			/*if(listaDeArchivosAdjuntos!=null && listaDeArchivosAdjuntos.size()>0){
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
			  						  				
			  			}	*/
			  			
			  			/***/
			  			/*
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
			  		*/
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
			  			//log.info(fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo()+","+fformDocumentoInterno.getDocumentoInterno()+","+nombre_tipo_documento+","+nombreOficina+","+siglasOficinasCopias+","+nombre_oficina_destino);
			  			log.info("TDR");
			  			pdf.createPdf_Tdr(fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo(), fformDocumentoInterno.getDocumentoInterno(),nombre_tipo_documento,nombreOficina,siglasOficinasCopias,nombre_oficina_destino,img1);

			  		}
			  		 catch(FileNotFoundException e ){
			  			log.error("No se encuentra el archivo o esta abierto siendo utilizado por otro proceso");
			  		 }
			  		catch(NullPointerException e2){
			  			log.error("No se encuentra el archivo o es null");
			  		 }
			  		log.info("/************ FIN ***********************/");
			  		log.info("==========================================");
			  		
			  		log.info("/*********** INICIO OPERACION UTILITARIA **************/");
			  		int codigodelDocumento = fformDocumentoInterno.getCodigo_documento();
			  		log.info("-------------> "+codigodelDocumento);
			  		
			  		/***
			  		 * AGREGADO PARA PROYECTOS DERIVACION
			  		 */
			  		if(tipo_proyecto_ls.equals("1")){
			  			codigodelDocumento=0;
			  		}
			  		/**
			  		 * FIN
			  		 */
			  		
			  		
			  		if(codigodelDocumento != 0){
			  			log.info("Registro nro > "+codigodelDocumento);
			  			
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
			  			log.info("Sali del AActionCreacionDocumentoInternoEspecialT direccionando a mantdocumento");
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

		  						log.info("Sali del AActionCreacionDocumentoInternoEspecialT... me dirijo a bandeja reiterativo");
				                
				                return mapping.findForward("haciabreitera");
				                
		  					}else{
		  						
		  						session.removeAttribute("ls_respuesta");
 		  					    
	  		       	    	session.setAttribute("nombreArchivoVer", fformDocumentoInterno.getNombre_arhivo());
	  		       	    	session.setAttribute("tipoDocumentoVer", fformDocumentoInterno.getCodigo_estado_documento().toString());
	  		       	    	session.setAttribute("codigoOficinaVer", fformDocumentoInterno.getCodigo_oficina_pertenece().toString());
	  		       	    	session.setAttribute("mensaje",mensajeEnOtrasBandejas);
	  		       	    	session.setAttribute("cuadro_mensaje", "BDER");
	  		       	    	
	  		       	    	
	  						log.info("Sali del AActionCreacionDocumentoInternoEspecialT... me dirijo a bandeja derivacion");
			                
			                return mapping.findForward("haciabderiva");
		  					}
		  						
		  						
		  				}else{
		  					session.removeAttribute("backup_rs_upload_doc_internos");
					  		session.removeAttribute("rs_upload_doc_internos");
		  					log.info("Sali del AActionCreacionDocumentoInternoEspecialT");
		  					 log.info("---");
			                
			                return mapping.findForward("exito");	
		  				}
		  			}
			  		}else{
			  			log.info("Registro nro > "+codigodelDocumento);
			  			if(fformDocumentoInterno.getCodigo_estado_documento()==3 && estaactivado){
			  				/******registro documento firmado****/
				  			
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
				  			session.setAttribute("operacion_firma_documento","FDIBC");
				  			
				  			log.info("Codigo_documento_interno "+dato);
				  			log.info("tipo > FDIBC");
				  			log.info("Sali del AActionCreacionDocumentoInternoEspecialT direccionando a mantdocumento");
				  			
			  			
				  			return mapping.findForward("puentemantdocu");
				  			
				  			
			  			}else{
			  				session.removeAttribute("ls_codigo_documento");
			  				session.removeAttribute("ls_codigoDocumentoInterno");
			  				
			  				session.removeAttribute("backup_rs_upload_doc_internos");
					  		session.removeAttribute("rs_upload_doc_internos");
					  		
			  				log.info("Codigo del Estado > "+fformDocumentoInterno.getCodigo_estado_documento());
			  				log.info("Sali del AActionCreacionDocumentoInternoEspecialT.............");
		                
		                return mapping.findForward("exito");
			  			}
			  		}
				  }catch (Exception e) {
			            e.printStackTrace();
				  }
				  return mapping.findForward("error");
			  		
			  		
	}
		  

			private void subir_archivo(BArchivo archivo) throws SQLException, IOException,Exception {

				Funciones funciones = new Funciones();
				String fileName = archivo.getNombre_archivo_cifrado();
				
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
					
					if(archivo.getId_archivo() == 0){
						
						log.info("Es un archivo nuevo >>");
							
						File fileToCreate = new File(archivo.getRuta(),fileName);

							if (!fileToCreate.exists()) {
								FileOutputStream fileOutStream = new FileOutputStream(
										fileToCreate);
								fileOutStream.write(archivo.getData());
								fileOutStream.flush();
								fileOutStream.close();
							}

					}else{
						log.info("Es un archivo antiguo >> no es necesario subirlo");

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
			String iniciales="";
		    String anio = getAnioActual();
			String nombre1="TDR_";
			Funciones funciones = new Funciones();
			
			if(llevaIniciales){
				iniciales = "_"+funciones.iniciales(firmadopor);
			}
			
			String nombre_archivo_generado=nombre1+numero_doc+"_"+anio+"_"+nombreOficina+".pdf";
			if(esBorrador){
				nombre_archivo_generado="Borrador_"+nombre1+numero_doc+"_"+anio+"_"+nombreOficina+iniciales+".pdf";
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

