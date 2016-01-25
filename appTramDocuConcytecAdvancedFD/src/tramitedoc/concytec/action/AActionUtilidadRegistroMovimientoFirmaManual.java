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
import org.apache.poi.hssf.record.formula.functions.Value;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.omg.CORBA.portable.ValueOutputStream;
import com.lowagie.text.Image;
import softnet.signnet.procesofirma.procesarFirma;
import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BDatosFirmante;
import tramitedoc.concytec.bean.BDocumento;
import tramitedoc.concytec.bean.BOficinas;
import tramitedoc.concytec.bean.DocumentoInternoBean;
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

	public class AActionUtilidadRegistroMovimientoFirmaManual extends Action {
		protected  Log log = LogFactory.getLog(AActionUtilidadRegistroMovimientoFirmaManual.class);
		  public ActionForward execute(ActionMapping mapping,
				    ActionForm form,
				    HttpServletRequest request,
				    HttpServletResponse response)
				    throws Exception
				  {
			  		log.info("Entry AActionUtilidadRegistroMovimientoFirmaManual... ");
			  		ActionMessages mensajes = new ActionMessages();
			  		response.setContentType("application/pdf");
			  		response.setCharacterEncoding("UTF-8");
			  		request.setCharacterEncoding("UTF-8");
			  		
			  		 HttpSession session = request.getSession(false);        

				        /*Verificar si la sesion se perdio*/
				   if (session==null){ return (mapping.findForward("expiracion"));}   
			  		
				   /********
				    * AGREGADO DEL COPIARPDFTEMP PARA QUE NO HAYA FIRMA DIGITAL
				    
				   **/
				   	String codigo_persona_noborrar =   String.valueOf(session.getAttribute("codigo_persona_noborrar"));
				    int coDoc = Integer.parseInt(request.getParameter("codDoc"));
			        int coTip = Integer.parseInt(request.getParameter("codTip"));
			        String observacion = (String) request.getParameter("observacion");
			        String tipo_envio = (String)request.getParameter("tipoenvio");
			        Collection rs_upload_doc_internos = new ArrayList();
			        observacion=(observacion==null)?"":observacion;
			        
			        byte ptext[] = observacion.getBytes();
			        observacion = new String(ptext, "UTF-8");
			        
			        System.out.println("-------------"+observacion);
			        System.out.println("-------------"+tipo_envio);
			        
			        
			        String usuario_actual =   String.valueOf(session.getAttribute("nombreusuario"));
			        String tipo_documento_interno="";

			        DocumentosInternosService service = new DocumentosInternosServiceImplement();
			        DocumentoInternoBean documentointerno = new DocumentoInternoBean();
			        BDatosFirmante datos_firmante= new BDatosFirmante();
			        
			        if((coTip > 0  &&  coTip < 6) || coTip == 8){
			        	tipo_documento_interno = "normal";
			        }
			        if(coTip == 6){
			        	tipo_documento_interno = "ccp";
			        }
			        if(coTip == 7){
			        	tipo_documento_interno = "tdr";
			        }
			        
			        
					boolean esBorrador = false;
					boolean llevaIniciales = true;
					
					log.info("INICIO DE PROCESO>>>");
			  		
			  		try{

			  			System.out.println("coDoc "+coDoc+" tipo_documento_interno "+tipo_documento_interno);
				        documentointerno = service.getDocumentoInterno(coDoc, tipo_documento_interno);
				        	
				        String nombreArchivo=documentointerno.getNombre_arhivo().trim();
						String nombreArchivoFinal="";
						String nombreOficina=service.getSiglasOficina(documentointerno.getCodigo_oficina_pertenece());
			  	        datos_firmante=service.getDatosFirmantexCodigo(codigo_persona_noborrar);  //BEAN CREADO CON LOS DATOS PARA LA FIRMA DIGITAL 
			  	        int numero_doc_pdf=documentointerno.getNumero_doc();
			  			
			  	        System.out.println("DATOS ->>>");
			  	        System.out.println("-FIRMADO POR "+datos_firmante.getFirmado_por());
			  	        System.out.println("-CARGO "+datos_firmante.getCargo());
			  	        System.out.println("-OFICINA "+datos_firmante.getOficina());
			  	        System.out.println("-ES OFICINA MAXIMA "+datos_firmante.isEsoficinamaxima());
			  	        System.out.println("-ES RESPONSABLE DE OFICINA "+datos_firmante.isEsresponsable());
			  	        System.out.println("-ES tipo firma "+datos_firmante.getTipo_firma());
			  	        /***
			  	  		 * AGREGADO PARA MANEJAR DOCUMENTOS OBSERVADOS
			  	  		 */
			  		
			  		/**fin**/
			  		// c:set var = "ls_secuencia" value="nada" scope="session"  
			  		//log.info("secuencia > "+ls_secuencia);

			  		log.info("/******PRIMERO SUBO GRABO LOS FILES NO VISADOS EN LA CARPETA RESPECTIVA****/");
			  		
			  		log.info("/*********** SOLO TENGO QUE ACTUALIZAR LA BASE DE DATOS Y PASAR EL PARAMETRO A MANT DOCUMENTO****/");
			  		log.info("/***  O A MANT DERIVACION SEGUN SEA EL CASO ***/");
			  		
			  					  		
			  		log.info("***********************");
			  		log.info("A: HACIA >> " +documentointerno.getCodigo_oficina());
			  		log.info("B: DESDE >> " +documentointerno.getCodigo_oficina_pertenece());
			  		log.info("***********************");
			  		

			  		log.info("/******INICIO PROCESO DE ACTUALIZACION DE DB*****/");

			  			String confirma = (String) request.getSession().getAttribute("confirma");
			  			confirma=(confirma==null)?"":confirma;

			  			if(confirma.equals("firma")){
			  			String ls_para_firma="firma";
				  		request.getSession().setAttribute("para_firma",ls_para_firma);
			  			}

			  			mensajes.clear();
			  			
			  		log.info("*********OPERACIONES PARA ACTUALIZAR MENSAJE Y NOMBRE CORRECTO DE DOCUMENTO");
			  			
			  		log.info("Se trata de un documento estado FIRMADO >> ");
			  		
			  		/***
			  		 * AGREGADO PARA MANEJAR LOS OBSERVADOS 
			  		 */
			  		
			  		
			  		
			  		if(documentointerno.getCodigo_estado_documento()==10){
			  			
			  			session.removeAttribute("operacionpopup");
			  			session.removeAttribute("mensaje_reg");
		       	    	session.removeAttribute("mensaje_exp");	
		       	    	session.removeAttribute("mensaje");
		       	    	
			  			log.info("Actualizando tablas involucradas con la Observación");
			  			String observacion2 = (String) session.getAttribute("observacion");
				        String codigo_motivo = (String)session.getAttribute("tipoenv");
			  			
			  			
			  			int secuencia_movimiento_destino = service.getcodigo_movimiento_destino(documentointerno.getCodigo_documento_interno());
			  			int codigo_recepcion_destino = service.getcodigo_recepcion(documentointerno.getCodigo_documento(),secuencia_movimiento_destino);
			  			
			  			String ls_mensaje=service.of_ingresar_doc_observardo(documentointerno.getCodigo_documento(),documentointerno.getSecuencia_movimiento(),secuencia_movimiento_destino,observacion2,codigo_motivo,codigo_recepcion_destino);
			  			
			  			if(ls_mensaje.equals("SI")){
			  			//RETORNO A LA PAGINA MOSTRAR DOCUMENTOS INTERNOS
			  				
			  				log.info("service.updateEstadosRegistroObservado("+documentointerno.getCodigo_documento_interno()+","+documentointerno.getCodigo_estado_documento()+")");
					  		service.updateEstadoRegistroObservado(documentointerno.getCodigo_documento_interno(),documentointerno.getCodigo_estado_documento());
					  		session.setAttribute("operacionpopup", "Y");
							request.setAttribute("mensaje", "Documento Derivado con exito...!");
							request.setAttribute("mensaje_reg", "Nº de Reg: "+documentointerno.getCodigo_documento_interno());
							request.setAttribute("mensaje_exp", "Nº de Exp: "+0);
			  				
					  		//REMUEVO LA LISTA DE DOC FIRMADOS INTERNOS
				  			session.removeAttribute("backup_rs_upload_doc_internos");
					  		session.removeAttribute("rs_upload_doc_internos");
			       	    	session.removeAttribute("ls_respuesta");
							session.removeAttribute("nombreArchivo");
			       			session.removeAttribute("numero_doc_pdf");
			       			session.removeAttribute("documentointernotemp");
		  					session.removeAttribute("ls_tipenv");
		  					session.removeAttribute("observacion");
					  		session.removeAttribute("es_observado");
					  		session.removeAttribute("tipoenv");
					  		
					  		session.removeAttribute("ls_codigo_documento");
					  		session.removeAttribute("ls_codigo_expediente");
					  		session.removeAttribute("ls_codigo_recepcion");
					  		session.removeAttribute("ls_secuencia");
					  		session.removeAttribute("nombreAnexosSinVisado");
					  		session.removeAttribute("rs_upload_doc_internos_ls");
					  		//FIN
					  		
							
						}else{
							session.setAttribute("operacionpopup", "Y");
							request.setAttribute("mensaje", "Documento no se puede Derivar...!");
							request.setAttribute("mensaje_reg", "Nº de Reg: "+documentointerno.getCodigo_documento_interno());
							request.setAttribute("mensaje_exp", "Nº de Exp: "+0);
						}

				  		return mapping.findForward("exitoporfirmar");
				  		
			  		}else{
			  			log.info("nombreArchivo");
			  			nombreArchivoFinal= nombreArchivo.substring(9,nombreArchivo.length()); // ESTOY QUITANDO EL PREFIJO -BORRADOR_
				  		log.info("service.updateEstadosRegistro("+documentointerno.getCodigo_documento_interno()+","+numero_doc_pdf+","+nombreArchivoFinal+")");
				  		service.updateEstadoRegistro(documentointerno.getCodigo_documento_interno(),numero_doc_pdf,nombreArchivoFinal);
				  		rs_upload_doc_internos=service.getListaArchivosAdjuntos(coDoc);
				  		
				  		if(rs_upload_doc_internos!=null && rs_upload_doc_internos.size()>0){
							Iterator it=rs_upload_doc_internos.iterator();
								while(it.hasNext()){
									String rutaOrigenAnexo=Constantes.CarpetaArchivosDesarrolloenTramiteVisadosBackup.getNombre();
						  			String rutaDestinoAnexo=Constantes.CarpetaArchivosDesarrolloenTramiteVisados.getNombre();
									
									BArchivo archivoenlista=(BArchivo)it.next();
									service.updateRutaDocumentosAdjuntos(archivoenlista);
									String nombreArchivoAnexo = archivoenlista.getNombre_archivo_cifrado();
									
									if(archivoenlista.getEstado().equals("A")){
										
										if(datos_firmante.isEsoficinamaxima()){
											int cd = service.of_tipo_documento_interno(archivoenlista.getId_archivo_proyecto());
											if(cd ==6 || cd ==7){
												rutaOrigenAnexo=rutaOrigenAnexo+archivoenlista.getNombre_archivo_cifrado();
												nombreArchivoAnexo=nombreArchivoAnexo.substring(5, nombreArchivoAnexo.length());
												rutaDestinoAnexo=rutaDestinoAnexo+nombreArchivoAnexo;
												/**
												 * UPDATE A TABLA CABUPLOAD
												 */
												//service.updateNombresDocumentosAdjuntos(archivoenlista);
												
											}else{
												rutaOrigenAnexo=rutaOrigenAnexo+archivoenlista.getNombre_archivo_cifrado();
												rutaDestinoAnexo=rutaDestinoAnexo+archivoenlista.getNombre_archivo_cifrado();
											}
										}else{
											rutaOrigenAnexo=rutaOrigenAnexo+archivoenlista.getNombre_archivo_cifrado();
											rutaDestinoAnexo=rutaDestinoAnexo+archivoenlista.getNombre_archivo_cifrado();
										}
										
										copiarDocumento(rutaOrigenAnexo, rutaDestinoAnexo);
									}
								}
						}
			  		}
			  				
			  		log.info("/************ FIN ***********************/");
			  		log.info("==========================================");
		  			
			  		
			  		log.info("/*********** INICIO OPERACION UTILITARIA **************/");
			  		int codigodelDocumento = documentointerno.getCodigo_documento();
			  		log.info("-------------> "+codigodelDocumento);
			  		
			  		session.setAttribute("observacion", observacion);//para MANT_DOCUMENTO MANT_DERIVAR MANT_REITERATIVO
			  		
			  		
			  		if(codigodelDocumento != 0){
			  			log.info("Registro nro > "+codigodelDocumento);
			  			
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
			       	    	String pagina="puentederivadocu";
			  							       	    	      	    	
			       	    	
			       	    	dato = documentointerno.getCodigo_documento_interno().toString();
				  			
				  			//session.setAttribute("listaDeArchivosAdjuntos",listadeDocAdjuntos);
				  			session.setAttribute("codigo_doc_para_grabar",dato);
				  			session.setAttribute("tipoenv", tipo_envio);//para MANT_DERIVAR MANT_REITERATIVO
				  			session.setAttribute("firmaManual", "1");

							session.removeAttribute("ls_respuesta");
							session.removeAttribute("nombreArchivo");
			       			session.removeAttribute("numero_doc_pdf");
			       			session.removeAttribute("documentointernotemp");
			       			
			       			if(documentointerno.getTipo_envio().equals("R")){
			  					session.removeAttribute("ls_tipenv");
			  					session.setAttribute("operacion_firma_documento","FRDI");
			  					pagina="puentereiteradocu";
			  					log.info("Codigo_documento_interno "+dato);
			  					log.info("tipo > FRDI");
			  					log.info("Sali del AActionCreacionDocumentoInterno direccionando a reiteradocumento");
			  				}else{
			  					session.setAttribute("operacion_firma_documento","FDDI");
				       			log.info("Codigo_documento_interno "+dato);
					  			log.info("tipo > FDDI");
								log.info("Sali del AActionCreacionDocumentoInterno direccionando a derivadocumento");
			  				}
			       			
				  			return mapping.findForward(pagina);
		
			  			
			  		}else{
			  			log.info("Registro nro > "+codigodelDocumento);
			  			
			  				log.info("Operacion >>> REGISTRO DE DOCUMENTO INTERNO");

				  			/*****REMUEVO LA LISTA DE DOC FIRMADOS INTERNOS****/
				  			session.removeAttribute("backup_rs_upload_doc_internos");
					  		session.removeAttribute("rs_upload_doc_internos");
					  		session.removeAttribute("operacionpopup");
					  		session.removeAttribute("titulo");
			       	    	session.removeAttribute("mensaje_reg");
			       	    	session.removeAttribute("mensaje_exp");	
			       	    	session.removeAttribute("mensaje");
					  		
			       	    	String dato ="";
			       	    	dato = documentointerno.getCodigo_documento_interno().toString();
				  			
				  			
				  			session.setAttribute("codigo_doc_para_grabar",dato);
				  			session.setAttribute("firmaManual", "1");
				  			session.setAttribute("operacion_firma_documento","FDIBC");
				  			
				  			session.removeAttribute("ls_respuesta");
							session.removeAttribute("nombreArchivo");
			       			session.removeAttribute("numero_doc_pdf");
			       			session.removeAttribute("documentointernotemp");
				  			
				  			log.info("Codigo_documento_interno "+dato);
				  			log.info("tipo > FDIBC");
				  			log.info("Sali del AActionCreacionDocumentoInterno direccionando a mantdocumento");
				  			
				  			return mapping.findForward("puentemantdocu");

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
				System.out.println("nombre documento:" + nombre_archivo_generado);
				return nombre_archivo_generado;
			}
			
			private String getAnioActual() {
				Calendar c = new GregorianCalendar(); 
				String anio = Integer.toString(c.get(Calendar.YEAR));
				return anio;
			}*/
			
			
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
		  
		  
		
	}

