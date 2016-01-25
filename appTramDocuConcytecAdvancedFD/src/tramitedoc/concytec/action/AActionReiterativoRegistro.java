package tramitedoc.concytec.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BInfo;
import tramitedoc.concytec.bean.BMesaPartes;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IReiterativoDAO;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlReiterativoDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormReiterativoRegistro;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.service.ReiterativoService;
import tramitedoc.concytec.util.Constantes;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.IConstante;
import tramitedoc.concytec.util.ValidaSessionAction;


public class AActionReiterativoRegistro extends ValidaSessionAction implements IConstante{
	protected ArrayList<String> myListaCodigoPersonal = new ArrayList<String>();//empleado dentro de los checks
	protected ArrayList<Integer>  myListCodigos= new ArrayList<Integer>();//para envios de correos
	protected ArrayList<String> myListaCorreosDestinatarios= new ArrayList<String>();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		FFormReiterativoRegistro fReRe = (FFormReiterativoRegistro) form;
		IReiterativoDAO rDao = new SqlReiterativoDAO();
		Funciones f = new Funciones();

		String mensaje_usuario = null;
		
		/***************************************************/
		String operacion_firma_documento = (String) session.getAttribute("operacion_firma_documento");
		operacion_firma_documento = (operacion_firma_documento==null)? "" : operacion_firma_documento;
		if(operacion_firma_documento.equals("FRDI")){
			fReRe.setOpcion("registrar");
		}
		
		log.info("fReRe.getOpcion() --> "+fReRe.getOpcion());
		
		if (!f.esNulo(fReRe.getOpcion())
				&& fReRe.getOpcion().equals("registrar")) {
			return registrar(request, response, form, mapping);

		}

		return (mapping.findForward("registro"));
	}

	private ActionForward registrar(HttpServletRequest request,
			HttpServletResponse response, ActionForm form, ActionMapping mapping) {
		// TODO Auto-generated method stub
		
		Connection cnx = getConnection(request, "principal");
		
		Properties props = System.getProperties( );
		props.put("mail.smtp.host","smtp.concytec.gob.pe");	
		Session sessionMail = Session.getDefaultInstance(props,null);		
		
		HashMap<String, String> hRemitente=new HashMap<String, String>();
		HashMap<String, String> hDestinatario= new HashMap<String, String>();
		
		/*************PARA RESCATAR EL DOCUMENTO INTERNO********/
		DocumentosInternosService servicereiterativo = new DocumentosInternosServiceImplement();
		/*****************/
		System.out.println("Entry AActionReiterativoRegistro -registrar");
		
		HttpSession session = request.getSession();
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		IReiterativoDAO iRDao = new SqlReiterativoDAO();
		
		ReiterativoService service = new ReiterativoService();
		FFormReiterativoRegistro reg = (FFormReiterativoRegistro) form;
		Funciones f= new Funciones();
		
		/**************************************************/
		
		String operacion_firma_documento = (String) session.getAttribute("operacion_firma_documento");
		operacion_firma_documento = (operacion_firma_documento==null)? "" : operacion_firma_documento;
		String tipo="reiterar";
		
		if(operacion_firma_documento.equals("FRDI")){
			tipo = "reiterardocumentointerno";
		}
		
		/****************************************************/
		log.info("tipo > "+tipo);
		
		try {
			
			
			if(tipo.equals("reiterar")){

				log.info("Inicio de Reiterativo >>>");
				Funciones funciones = new Funciones();
				session.removeAttribute("personas");
				session.removeAttribute("codigo_oficina");
				session.removeAttribute("codigo_motivo");

				String mensaje = "";
				String ls_codigo_oficina_user = String.valueOf(session
						.getAttribute("codigo_oficina_user"));
				String ls_oficina = String.valueOf(session
						.getAttribute("codigo_oficina"));
				String ls_cargo_personal = String.valueOf(session
						.getAttribute("cargo_personal"));
				String ls_codigo_persona = String.valueOf(session
						.getAttribute("codigo_persona"));
				String ls_descripcion_oficina = "";
				String ls_flag = "";

				String codigo_documento = reg.getCodigo_documento();
				int codigo_expediente = reg.getCodigo_expediente();
				String descripcion_tipo = reg.getDescripcion_tipo();
				String numero_documento = reg.getNumero_documento();

				String fecha = reg.getFecha();

				String codigo_oficina = reg.getCodigo_oficina();
				String destinatario = reg.getDestinatario();
				String codigo_motivo = reg.getCodigo_motivo();
				String observacion = reg.getObservacion();
				observacion = funciones.convertir_comillas_simples(observacion);
				String fecha_rpta = reg.getFecha_rpta();

				String personas = reg.getPersonas();
				String cbo_fecharpta = reg.getCbo_fecharpta();
				String chk_copia = reg.getChk_copia();
				String cbo_copia[] = reg.getCbo_copia();
				
				String chk_copia_other = reg.getChk_copia_other();
				String cbo_copia_other[] = reg.getCbo_copia_other();

				reg.setSecuencia_movimiento(iRDao.obtenerMaxSecuencia(reg
						.getCodigo_documento()));
				String secuencia_movimiento = reg.getSecuencia_movimiento();
				String codigo_inicia = reg.getCodigo_inicia();
				String naturaleza_documento = reg.getNaturaleza_documento();
				int codigo_recepcion = reg.getCodigo_recepcion();
				String doc_resp = reg.getDoc_resp();
				doc_resp = funciones.convertir_comillas_simples(doc_resp);
				String fecha_rpta_rq = reg.getFecha_rpta_rq();

				String usuario = String.valueOf(session
						.getAttribute("nombreusuario"));

				BMesaPartes BMesaPartesVO = daoIUsuarioDAO.of_obtener_padre(cnx,
						codigo_documento);

				log.info(" codigo_documento.. " + codigo_documento);

				String ls_documento = codigo_documento;
				String ls_cod_padre = BMesaPartesVO.getCodigo_inicia();
				log.info(" ls_cod_padre.. " + ls_cod_padre);
				String ls_usuario = String.valueOf(session
						.getAttribute("nombreusuario"));

				log.info("Modificacion de... MantRecepcion ..........");

				int li_retorno = daoIUsuarioDAO.parametro(cnx);

				log.info("li_retorno" + li_retorno);

				Date fecha_recep = new Date(); // fecha y hora locales
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
				log.info("la fecha es.."
						+ formatoFecha.format(fecha_recep));
				System.out
						.println("la hora es.." + formatoHora.format(fecha_recep));
				log.info("la fecha_rpta es.." + fecha_rpta);

				fecha_rpta = (fecha_rpta == null ? "" : fecha_rpta);
				
				
				
				/*
	   	    	 * Obteniendo el Datos de la persona remitente agregado por mpelaez 26-10-2011 
	   	    	 * */	
	   	    			hRemitente=daoIUsuarioDAO.obtenerDatosUsuario(ls_codigo_persona);
	   	    			hDestinatario=daoIUsuarioDAO.obtenerDatosUsuario(personas);
		   				//hDestinatario.put("asunto_documento",asunto_documento);
		   				hDestinatario.put("remitente",hRemitente.get("nombre_personal"));
		   				hDestinatario.put("asunto_documento",observacion);
		   				//enviarCorreoElectronico(hDestinatario,codigo_documento,sessionMail);
		   				
		   				myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,codigo_oficina);
		   			//ponemos en una lista de string
	   	   				ArrayList<String> misListaCodigosEnString= new ArrayList<String>();
	   	   				for (int codigo : myListCodigos) {
	   	   					misListaCodigosEnString.add(String.valueOf(codigo));
						}
	   	   				
	   	   				//ahora obtendremos los correos   	   			
	   	   				if(misListaCodigosEnString.size()>0)
	   	   				{ myListaCorreosDestinatarios = daoIUsuarioDAO.obtenerDatosUsuario(misListaCodigosEnString);
	   	   				} 
	   	   		//		enviamos los correos	 
	   	   				if(myListaCorreosDestinatarios.size()>0)   	   				  	   			
		   	   			{
//DESACTIVADO	   	   					enviarCorreoElectronico(myListaCorreosDestinatarios,codigo_documento,sessionMail,hDestinatario, IConstante.correoTO_Principal);
		   	   			}	
	   	    	/*
	   	    	 * Fin
	   	    	 * */
		   				
	   	   		myListCodigos= new ArrayList<Integer>()	;   	   	
	   	   		myListaCodigoPersonal = new ArrayList<String>();
	   	   		int secuencia_origen = Integer.parseInt(secuencia_movimiento);
	   	   		
				if (naturaleza_documento.equals("I")) {
					log.info("Entry I");
					
					codigo_oficina = codigo_oficina == null ? "" : codigo_oficina;
					
					if (chk_copia != null) {

						log.info("El chk_copia es diferente de null.."
								+ chk_copia);

						for (int i = 0; cbo_copia != null && i < cbo_copia.length; i++) {
							
							log.info("las cbo_copia son :" + cbo_copia[i]);
							String cbo_copia_aux=cbo_copia[i];
							String ls_cod_persona = funciones.of_codigo_oficina_persona(cnx, cbo_copia_aux);
							log.info("El ls_cod_persona es..:"+ ls_cod_persona);
							//linea agregada por mpelaezs 26-11-2011
							//agregar_a_ListaCodigoPersonal(myListaCodigoPersonal,ls_cod_persona); //agregado por mpelaezs 26-11-2011
							
							myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,cbo_copia_aux);			    			
			    			if(myListCodigos.size()>0){
			    				for (int cod : myListCodigos) {
			    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
							}   					    			
			    			}
							
							

							if (!codigo_oficina.trim().equals(cbo_copia[i].trim())) {
								daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
										ls_codigo_oficina_user, cbo_copia[i],
										codigo_motivo, observacion, ls_cod_persona,
										ls_documento, secuencia_movimiento,
										ls_usuario, ls_documento, li_retorno,
										fecha_rpta, codigo_expediente,
										ls_descripcion_oficina, codigo_recepcion,
										doc_resp, fecha_rpta_rq, 'C',secuencia_origen);
								
							/***
							 * RECEPCION DE COPIAS	
							 */
							/*
								Date fechaz = new Date(); // fecha y hora locales 
					 	    		SimpleDateFormat fechaRecepcion = new SimpleDateFormat("dd/MM/yyyy"); 
					 	    		SimpleDateFormat horaRecepcion = new SimpleDateFormat("HH:mm"); 
					 	    		log.info("la fecha es.."+ fechaRecepcion.format(fechaz) ); 
					 	    		log.info("la hora es.."+ horaRecepcion.format(fechaz) ); 
					 				String ls_fechaRecepcion=formatoFecha.format(fechaz);
					 				String ls_horaRecepcion=formatoHora.format(fechaz);
					 				
					 				String usuario_destino = funciones.of_usuario_destino(cnx, ls_cod_persona);
					 				String cargo_personal_recibe=funciones.of_cargo_persona(cnx, usuario_destino);
					 				String secuenciaQsigue = funciones.of_numero_secuencia(cnx,String.valueOf(li_retorno));
					 				String estado_movimiento_registro="2";
								
															
								daoIUsuarioDAO.of_recibir_doc(cnx,
										cbo_copia_aux.trim(),
						 				ls_documento,
						 					usuario_destino,
						 						ls_fechaRecepcion,
						 						ls_horaRecepcion,
						 						observacion,
						 						estado_movimiento_registro,
						 					String.valueOf(li_retorno),
						 					secuenciaQsigue,
						 					observacion,
						 					cargo_personal_recibe);
						 			
						 						
						 				if(cnx.isClosed())
						 						{cnx = getConnection(request, "principal");}	
						 						*/
								/**
								 * FIN DE RECEPCION
								 */
							}

							session.setAttribute("codigo_oficina_copia",
									cbo_copia[i]);

						}

					}
					
					
					/**
					 * JAZANERO
					 */
					if (chk_copia_other != null) {

						log.info("El chk_copia_other es diferente de null.."
								+ chk_copia_other);

						for (int i = 0; cbo_copia_other != null && i < cbo_copia_other.length; i++) {
							
							log.info("las cbo_copia son :" + cbo_copia_other[i]);
							String cbo_copia_aux=cbo_copia_other[i];
							String ls_cod_persona = funciones.of_codigo_oficina_persona(cnx, cbo_copia_aux);
							log.info("El ls_cod_persona es..:"+ ls_cod_persona);
							//linea agregada por mpelaezs 26-11-2011
							//agregar_a_ListaCodigoPersonal(myListaCodigoPersonal,ls_cod_persona); //agregado por mpelaezs 26-11-2011
							
							myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,cbo_copia_aux);			    			
			    			if(myListCodigos.size()>0){
			    				for (int cod : myListCodigos) {
			    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
							}   					    			
			    			}
							
							

							if (!codigo_oficina.trim().equals(cbo_copia_other[i].trim())) {
								daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
										ls_codigo_oficina_user, cbo_copia_other[i],
										codigo_motivo, observacion, ls_cod_persona,
										ls_documento, secuencia_movimiento,
										ls_usuario, ls_documento, li_retorno,
										fecha_rpta, codigo_expediente,
										ls_descripcion_oficina, codigo_recepcion,
										doc_resp, fecha_rpta_rq, 'C',secuencia_origen);
								
							/***
							 * RECEPCION DE COPIAS	
							 */
							/*
								Date fechaz = new Date(); // fecha y hora locales 
					 	    		SimpleDateFormat fechaRecepcion = new SimpleDateFormat("dd/MM/yyyy"); 
					 	    		SimpleDateFormat horaRecepcion = new SimpleDateFormat("HH:mm"); 
					 	    		log.info("la fecha es.."+ fechaRecepcion.format(fechaz) ); 
					 	    		log.info("la hora es.."+ horaRecepcion.format(fechaz) ); 
					 				String ls_fechaRecepcion=formatoFecha.format(fechaz);
					 				String ls_horaRecepcion=formatoHora.format(fechaz);
					 				
					 				String usuario_destino = funciones.of_usuario_destino(cnx, ls_cod_persona);
					 				String cargo_personal_recibe=funciones.of_cargo_persona(cnx, usuario_destino);
					 				String secuenciaQsigue = funciones.of_numero_secuencia(cnx,String.valueOf(li_retorno));
					 				String estado_movimiento_registro="2";
								
															
								daoIUsuarioDAO.of_recibir_doc(cnx,
										cbo_copia_aux.trim(),
						 				ls_documento,
						 					usuario_destino,
						 						ls_fechaRecepcion,
						 						ls_horaRecepcion,
						 						observacion,
						 						estado_movimiento_registro,
						 					String.valueOf(li_retorno),
						 					secuenciaQsigue,
						 					observacion,
						 					cargo_personal_recibe);
						 			
						 						
						 				if(cnx.isClosed())
						 						{cnx = getConnection(request, "principal");}	
						 						*/
								/**
								 * FIN DE RECEPCION
								 */
							}

							session.setAttribute("codigo_oficina_copia",
									cbo_copia_other[i]);

						}

					}
					
					System.out.println("Despues del Ingreso Documento,si codigo inicial es igual a cod documento");
					System.out.println("El ls_Documento es igual a codigo padre y el documento es Interno");
					//Reiterativo para documento Interno
					daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
							ls_codigo_oficina_user, codigo_oficina, codigo_motivo,
							observacion, personas, ls_documento,
							secuencia_movimiento, ls_usuario, ls_documento,
							li_retorno, fecha_rpta, codigo_expediente,
							ls_descripcion_oficina, codigo_recepcion, doc_resp,
							fecha_rpta_rq, 'R',secuencia_origen);
					
										

					/***
					 * RECEPCION PRINCIPAL
					 */
					/*
					Date fechaz = new Date(); // fecha y hora locales 
					SimpleDateFormat fechaRecepcion = new SimpleDateFormat("dd/MM/yyyy"); 
					SimpleDateFormat horaRecepcion = new SimpleDateFormat("HH:mm"); 
					String ls_fechaRecepcion=formatoFecha.format(fechaz);
					String ls_horaRecepcion=formatoHora.format(fechaz);
					String estado_movimiento_registro="2";
					String usuario_destino = funciones.of_usuario_destino(cnx, personas);
					String cargo_personal_recibe=funciones.of_cargo_persona(cnx, usuario_destino);
					String secuenciaQsigue = funciones.of_numero_secuencia(cnx,String.valueOf(li_retorno));

					iRDao.recibirDocumentoReiterado(codigo_oficina,ls_documento,   						 					
							usuario_destino,
							ls_fechaRecepcion,
							ls_horaRecepcion,
							observacion,
							estado_movimiento_registro,
							String.valueOf(li_retorno),
							secuenciaQsigue,
							observacion,
							cargo_personal_recibe);


					if(cnx.isClosed())
					{cnx = getConnection(request, "principal");}
					*/
					/***
					 * FIN DE RECEPCION
					 */
			

				} else { //si no es I, osea E

					log.info("Entry E");
					if (chk_copia != null) {

						log.info("El chk_copia es diferente de null.."
								+ chk_copia);

						for (int i = 0; cbo_copia != null && i < cbo_copia.length; i++) {
							System.out
									.println("El PAR_DES_LAR es diferente de null..");
							log.info("Antes del insert Clases..");
							// daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
							System.out
									.println("las cbo_copia son :" + cbo_copia[i]);
							
							
							String chk_copias_aux=cbo_copia[i];
				    			String ls_cod_persona=funciones.of_codigo_oficina_persona(cnx,chk_copias_aux);   					    			
				    			
				    			
				    			
							log.info("El ls_cod_persona es...LLL :"
									+ ls_cod_persona);
							
							//linea agregada por mpelaezs 26-11-2011
						//	agregar_a_ListaCodigoPersonal(myListaCodigoPersonal,ls_cod_persona);
							
									myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,chk_copias_aux);			    			
					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
					    			if(myListCodigos.size()>0){
					    				for (int cod : myListCodigos) {
					    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
									}   					    			
					    			}
							
							
							

							if (!codigo_oficina.trim().equals(cbo_copia[i].trim())) {
								daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
										ls_codigo_oficina_user, cbo_copia[i],
										codigo_motivo, observacion, ls_cod_persona,
										ls_documento, secuencia_movimiento,
										ls_usuario, ls_documento, li_retorno,
										fecha_rpta, codigo_expediente,
										ls_descripcion_oficina, codigo_recepcion,
										doc_resp, fecha_rpta_rq, 'C',secuencia_origen);

							}
							session.setAttribute("codigo_oficina_copia",
									cbo_copia[i]);

						}

					}
					
					
					/**
					 * JAZANERO
					 */
					
					if (chk_copia_other != null) {

						log.info("El chk_copia es diferente de null.."
								+ chk_copia_other);

						for (int i = 0; cbo_copia_other != null && i < cbo_copia_other.length; i++) {
							System.out
									.println("El PAR_DES_LAR es diferente de null..");
							log.info("Antes del insert Clases..");
							// daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
							System.out
									.println("las cbo_copia son :" + cbo_copia_other[i]);
							
							
							String chk_copias_aux=cbo_copia_other[i];
				    			String ls_cod_persona=funciones.of_codigo_oficina_persona(cnx,chk_copias_aux);   					    			
				    			
				    			
				    			
							log.info("El ls_cod_persona es...LLL :"
									+ ls_cod_persona);
							
							//linea agregada por mpelaezs 26-11-2011
						//	agregar_a_ListaCodigoPersonal(myListaCodigoPersonal,ls_cod_persona);
							
									myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,chk_copias_aux);			    			
					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
					    			if(myListCodigos.size()>0){
					    				for (int cod : myListCodigos) {
					    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
									}   					    			
					    			}
							
							
							

							if (!codigo_oficina.trim().equals(cbo_copia_other[i].trim())) {
								daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
										ls_codigo_oficina_user, cbo_copia_other[i],
										codigo_motivo, observacion, ls_cod_persona,
										ls_documento, secuencia_movimiento,
										ls_usuario, ls_documento, li_retorno,
										fecha_rpta, codigo_expediente,
										ls_descripcion_oficina, codigo_recepcion,
										doc_resp, fecha_rpta_rq, 'C',secuencia_origen);

							}
							session.setAttribute("codigo_oficina_copia",
									cbo_copia_other[i]);

						}

					}
					
					log.info("El chk_copia es == null");
					System.out
							.println("El ls_Documento es igual a codigo padre y el documento es de Entrada");

					if (ls_oficina.equals(codigo_oficina)) {

						ls_flag = "I";
						System.out
								.println("Si las oficinas son iguales CCCCCCCC XXXXX..");

						if (ls_cargo_personal.equals("3")
								|| ls_cargo_personal.equals("10")) {

							System.out
									.println("Si el cargo de personal es igual 3 o 10 ......");

							System.out
									.println("Si las oficinas son diferentes y el cargo es diferente 3 o 10");

							daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
									ls_codigo_oficina_user, codigo_oficina,
									codigo_motivo, observacion, personas,
									ls_documento, secuencia_movimiento, ls_usuario,
									ls_documento, li_retorno, fecha_rpta,
									codigo_expediente, ls_descripcion_oficina,
									codigo_recepcion, doc_resp, fecha_rpta_rq,
									'R',secuencia_origen);
							
							/***recepion de reiterativo****/
							
							/****fin de recepcion******/

						} else {

							System.out
									.println("Si el cargo de personal es diferente 3 o 10 ......");
							daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
									ls_codigo_oficina_user, codigo_oficina,
									codigo_motivo, observacion, personas,
									ls_documento, secuencia_movimiento, ls_usuario,
									ls_documento, li_retorno, fecha_rpta,
									codigo_expediente, ls_descripcion_oficina,
									codigo_recepcion, doc_resp, fecha_rpta_rq, 'R',secuencia_origen);
							
							
							/***recepion de reiterativo****/
							
							/****fin de recepcion******/

						}

					} else {

						System.out
								.println("Si las oficinas son diferentes y el cargo es diferente 3 o 10");
						daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
								ls_codigo_oficina_user, codigo_oficina,
								codigo_motivo, observacion, personas, ls_documento,
								secuencia_movimiento, ls_usuario, ls_documento,
								li_retorno, fecha_rpta, codigo_expediente,
								ls_descripcion_oficina, codigo_recepcion, doc_resp,
								fecha_rpta_rq, 'R',secuencia_origen);
						
						/***recepion de reiterativo****/
						
						/****fin de recepcion******/

					}

					
					System.out
							.println("Despues del Ingreso Documento,si codigo inicial es igual a cod documento");

				}
				/*
				 * agregado por mpelaez 26-11-2011
				 * */
				log.info("tamanio listaCodigoPersonal: "+myListaCodigoPersonal.size());
		     		if(myListaCodigoPersonal.size()>0){
		     			ArrayList<String> myListaCorreos = daoIUsuarioDAO.obtenerDatosUsuario(myListaCodigoPersonal);
		     			log.info("tamanio listacorreos: "+myListaCorreos.size());
		     			// con la lista ahora enviamos los correos respectivos:	
//DESACTIVADO		     			enviarCorreoElectronico(myListaCorreos,codigo_documento,sessionMail,hDestinatario,IConstante.correoCC_Copias);
		     		}
		     		
		     	/*fin
		     	 * */	

		     		
		     		/**
					 * INICIO DE UPLOAD INTERNOS
					 */
					log.info(">>>>>>>>>>>>>ADJUNTA DOCUMENTOS INTERNOS FIRMADOS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
					Collection rs_upload_doc_internos_temp = new ArrayList();    	 	
					rs_upload_doc_internos_temp = (Collection) session.getAttribute("rs_upload_doc_internos");
					if(rs_upload_doc_internos_temp!=null && rs_upload_doc_internos_temp.size()>0){
						log.info("El ls_codigo_documento..." + ls_documento );
						log.info("El ls_codigo_expediente..." + codigo_expediente);
						log.info("El ls_secuencia_movimiento..." + secuencia_movimiento );

						subirArchivosBloque(ls_documento, rs_upload_doc_internos_temp, String.valueOf(codigo_expediente), secuencia_movimiento);
					}   
					
					/**
					 * INICIO DE UPLOAD DOCUMENTOS EN TEMPORALES
					 */
					log.info(">>>>>>>>>>>>>ADJUNTA DOCUMENTOS INTERNOS FIRMADOS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
					Collection rs_upload_doc_internos = new ArrayList();    	 	
					rs_upload_doc_internos = (Collection) session.getAttribute("rs_upload_doc");
					if(rs_upload_doc_internos!=null && rs_upload_doc_internos.size()>0){
						log.info("El ls_codigo_documento..." + ls_documento );
						log.info("El ls_codigo_expediente..." + codigo_expediente);
						log.info("El ls_secuencia_movimiento..." + secuencia_movimiento );

						moverArchivosBloque(ls_documento, rs_upload_doc_internos, String.valueOf(codigo_expediente), secuencia_movimiento);
					}

					/**
					 * FIN UPLOAD INTERNOS
					 */	
		     		
		     		
		     		
				System.out.println("Despues del Ingreso Documento y Direccionando,si codigo inicial es diferente a cod documento");

				Collection rs_derivacion_doc = new ArrayList();
				
				String accesoaoficinas=servicereiterativo.getAccesosaOficinas(ls_codigo_persona);
				if(accesoaoficinas != null){
					accesoaoficinas=ls_oficina+","+accesoaoficinas;
				}else{
					accesoaoficinas=ls_codigo_oficina_user;
				}
				if (ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")) {

					System.out.println("Si el cargo de personal es igual a 3 o 10 ......");
					rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(
							cnx, ls_codigo_oficina_user,accesoaoficinas,"",0);

				} else {
					log.info("Si el cargo de personal es 3 o 10 ......");
					rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(
							cnx, ls_codigo_oficina_user,accesoaoficinas, ls_codigo_persona,"",0);

				}
				
				
				if(daoIUsuarioDAO.ValidaListaDocEntradaDetUpload(codigo_documento,secuencia_movimiento)>0){
					service.actualizarEstadoArchivos(ls_codigo_oficina_user,0,Integer.parseInt(reg.getCodigo_documento()),codigo_expediente,Integer.parseInt(secuencia_movimiento),usuario,li_retorno);
				}
								
				Collection listaDocsDetalles = service.listarSecuencias(reg.getCodigo_documento());
				session.setAttribute("listadoSecuenciasRegistro", listaDocsDetalles);
				FFormReiterativoRegistro reg2 = new FFormReiterativoRegistro();
				reg2.setCodigo_documento(reg.getCodigo_documento());
				reg2.setCodigo_expediente(reg.getCodigo_expediente());
				reg2.setNumero_documento(reg.getNumero_documento());
				reg2.setProcedencia(reg.getProcedencia());
				reg2.setNaturaleza_documento(reg.getNaturaleza_documento());
				fecha = f.convertirFecha(new Date());
				reg2.setFecha(fecha);
				
				request.setAttribute("mensaje_usuario", "Registro "+ reg.getCodigo_documento() +" reiterado con Éxito");
				request.setAttribute("FFormReiterativoRegistro", reg2);
				session.setAttribute("listaFrameBusquedaTemp",null);
				
		
				
			}else if(tipo.equals("reiterardocumentointerno")){
				

				log.info("Inicio Reiterativo de documento firmado");
				
				/*****TENGO QUE RECUPERAR LOS DATOS SOLICITADOS EN EL FORMULARIO REGISTRO*****/
	        	/*****DESDE EL DOCUMENTO INTERNO *****/
				String usuario   = String.valueOf(session.getAttribute("nombreusuario"));
	        	Collection listaDeArchivosAdjuntos = new ArrayList();
	        	listaDeArchivosAdjuntos = (Collection) session.getAttribute("listaDeArchivosAdjuntos");
	        	
	        	String[] chk_copiasz=null;
	        	String[] chk;
	        	
				
				String pagina = (String)session.getAttribute("pagina");
				String observacion_ls = (String)session.getAttribute("observacion");
				String tipo_envio_ls = String.valueOf(session.getAttribute("tipoenv"));
				String firmaManual = (String)session.getAttribute("firmaManual");
				observacion_ls=(observacion_ls==null)?"":observacion_ls;
				tipo_envio_ls=(tipo_envio_ls==null)?"":tipo_envio_ls;
				pagina=(pagina==null)?"":pagina;
		  		log.info("------------- tipo de envio > "+tipo_envio_ls);
	        	
	        	int  codigo_doc_para_grabar=Integer.parseInt((String)session.getAttribute("codigo_doc_para_grabar"));
	        	String cdnb = (String) session.getAttribute("codigo_persona_noborrar");
	        	BUsuario userSystem =(BUsuario) session.getAttribute("nombre_usuario");
		  		if(userSystem==null){
		  			userSystem = new BUsuario();
		  		}

		  		log.info("usuario_actual >>> "+usuario);
		  		userSystem.setC_usuario(usuario);
		  		userSystem=servicereiterativo.obtenerResponsabilidad(userSystem);
		  		log.info("es reponsable:"+userSystem.getResponsable());

		  		/***FIN****/
		  		DocumentoInternoBean documento_interno_deriva = servicereiterativo.getDocumentoInterno(codigo_doc_para_grabar,"graba");

				
				log.info("Inicio de Reiterativo >>>");
				Funciones funciones = new Funciones();
				session.removeAttribute("personas");
				//session.removeAttribute("codigo_oficina");
				session.removeAttribute("codigo_motivo");

				String mensaje = "";
				String ls_codigo_oficina_user = String.valueOf(session
						.getAttribute("codigo_oficina_user"));
				String ls_oficina = String.valueOf(session
						.getAttribute("codigo_oficina"));
				String ls_cargo_personal = String.valueOf(session
						.getAttribute("cargo_personal"));
				String ls_codigo_persona = String.valueOf(session
						.getAttribute("codigo_persona"));
				String ls_descripcion_oficina = "";
				String ls_flag = "";

				String codigo_documento = String.valueOf(documento_interno_deriva.getCodigo_documento()); //reg.getCodigo_documento();
				int codigo_expediente =  documento_interno_deriva.getCodigo_expediente(); //reg.getCodigo_expediente();
				//String descripcion_tipo = reg.getDescripcion_tipo();
				//String numero_documento = reg.getNumero_documento();

//				String fecha = null; reg.getFecha();

				String codigo_oficina = String.valueOf(documento_interno_deriva.getCodigo_oficina()); //reg.getCodigo_oficina();
				//String destinatario = reg.getDestinatario();
				String codigo_motivo = tipo_envio_ls; //reg.getCodigo_motivo();
				String observacion = observacion_ls; //reg.getObservacion();
				observacion = funciones.convertir_comillas_simples(observacion);
				String fecha_rpta = ""; //reg.getFecha_rpta();

				String personas = String.valueOf(documento_interno_deriva.getPersonas());//reg.getPersonas();
				//String cbo_fecharpta = null; //reg.getCbo_fecharpta();
				String chk_copia = null; //reg.getChk_copia();
				String cbo_copia[] = null; //reg.getCbo_copia();
				
				if(documento_interno_deriva.getCodigos_oficinas_destinos_copias()!=null && documento_interno_deriva.getCodigos_oficinas_destinos_copias().length()>0)
		  		{
					chk_copia = "1";
					cbo_copia = (documento_interno_deriva.getCodigos_oficinas_destinos_copias().split(","));
					
		  		}
				

				String secuencia_movimiento = iRDao.obtenerMaxSecuencia(codigo_documento);
				//String codigo_inicia = reg.getCodigo_inicia();
				String naturaleza_documento = ""; //reg.getNaturaleza_documento();   sacar de donde sea la naturaleza
				naturaleza_documento = (codigo_expediente == 0)? "I":"E";
				
				int codigo_recepcion = documento_interno_deriva.getCodigo_recepcion(); //reg.getCodigo_recepcion();
				String doc_resp = "";//reg.getDoc_resp();
				doc_resp = funciones.convertir_comillas_simples(doc_resp);
				String fecha_rpta_rq = ""; //reg.getFecha_rpta_rq();

				//String usuario = String.valueOf(session.getAttribute("nombreusuario"));

				BMesaPartes BMesaPartesVO = daoIUsuarioDAO.of_obtener_padre(cnx,
						codigo_documento);

				log.info(" codigo_documento.. " + codigo_documento);

				String ls_documento = codigo_documento;
				String ls_cod_padre = BMesaPartesVO.getCodigo_inicia();
				log.info(" ls_cod_padre.. " + ls_cod_padre);
				String ls_usuario = String.valueOf(session
						.getAttribute("nombreusuario"));
				
				log.info("INICIO DE REITERATIVO DE DOCUEMNTO FIRMADO");
				log.info("==========================================");
				int li_retorno = daoIUsuarioDAO.parametro(cnx);

				log.info("li_retorno" + li_retorno);

				Date fecha_recep = new Date(); // fecha y hora locales
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
				log.info("la fecha es.."+ formatoFecha.format(fecha_recep));
				log.info("la hora es.." + formatoHora.format(fecha_recep));
				log.info("la fecha_rpta es.." + fecha_rpta);

				fecha_rpta = (fecha_rpta == null ? "" : fecha_rpta);
				String codigo_expedienteee = String.valueOf(codigo_expediente);
				/**
	    		 * INICIO DE UPLOAD INTERNOS
	    		 */
	    		log.info(">>>>>>>>>>>>>ADJUNTA DOCUMENTOS INTERNOS FIRMADOS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	
	    		Collection listaDeArchivosAdjuntosx = new ArrayList();
	    		    listaDeArchivosAdjuntosx = servicereiterativo.getListaArchivosAdjuntos(codigo_doc_para_grabar);
					BArchivo archivoenlista = new BArchivo();
	  			  	archivoenlista.setNombre_archivo(documento_interno_deriva.getNombre_arhivo());
	  			  	archivoenlista.setNombre_archivo_cifrado(documento_interno_deriva.getNombre_arhivo());
	  			  	archivoenlista.setIsfirmado(true);
	  			  	archivoenlista.setRuta(filePathFirmados);
	  			  	archivoenlista.setUsuario(usuario);
	  			  	
	  			  if(((firmaManual==null)? "":firmaManual).equals("1")){
			  			BInfo binfo = new BInfo();
			  			binfo = servicereiterativo.getInfoSobreDocumentoFirmadoManualmente(codigo_doc_para_grabar);
			  			archivoenlista.setIsfirmadomanual(true);
			  			archivoenlista.setId_detalle_archivo(binfo.getId_det_upload());
			  		
			  		}
	  			  	
			  	if(listaDeArchivosAdjuntosx != null && !listaDeArchivosAdjuntosx.isEmpty()){
			  			log.info("Registro Documento Principal y adjuntos");
			  			listaDeArchivosAdjuntosx.add(archivoenlista);
			  	}
			  	else{
			  			log.info("Registro Documento Principal");
			  			listaDeArchivosAdjuntosx = new ArrayList();
			  			listaDeArchivosAdjuntosx.add(archivoenlista);
			  	}	

		  		log.info("El ls_codigo_documento..." + ls_documento );
		  		log.info("El ls_codigo_expediente..." + codigo_expediente);
		  		log.info("El ls_secuencia_movimiento..." + secuencia_movimiento );
		  		
		  		log.info("subirArchivosBloque(ls_documento, listaDeArchivosAdjuntosx, codigo_expedienteee, secuencia_movimiento);");
		  		subirArchivosBloque(ls_documento, listaDeArchivosAdjuntosx, codigo_expedienteee, secuencia_movimiento);
		  		
	    		/**
	    		 * FIN UPLOAD INTERNOS
	    		 */
				
				/*
	   	    	 * Obteniendo el Datos de la persona remitente agregado por mpelaez 26-10-2011 
	   	    	 * */	
	   	    			hRemitente=daoIUsuarioDAO.obtenerDatosUsuario(ls_codigo_persona);
	   	    			hDestinatario=daoIUsuarioDAO.obtenerDatosUsuario(personas);
		   				//hDestinatario.put("asunto_documento",asunto_documento);
		   				hDestinatario.put("remitente",hRemitente.get("nombre_personal"));
		   				hDestinatario.put("asunto_documento",observacion);
		   				//enviarCorreoElectronico(hDestinatario,codigo_documento,sessionMail);
		   				
		   				myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,codigo_oficina);
		   			//ponemos en una lista de string
	   	   				ArrayList<String> misListaCodigosEnString= new ArrayList<String>();
	   	   				for (int codigo : myListCodigos) {
	   	   					misListaCodigosEnString.add(String.valueOf(codigo));
						}
	   	   				
	   	   				//ahora obtendremos los correos   	   			
	   	   				if(misListaCodigosEnString.size()>0)
	   	   				{ myListaCorreosDestinatarios = daoIUsuarioDAO.obtenerDatosUsuario(misListaCodigosEnString);
	   	   				} 
	   	   		//		enviamos los correos	 
	   	   				if(myListaCorreosDestinatarios.size()>0) {  	   				  	   			
	//desactivado	   	   			enviarCorreoElectronico(myListaCorreosDestinatarios,codigo_documento,sessionMail,hDestinatario, IConstante.correoTO_Principal);
		   	   			}	
	   	    	/*
	   	    	 * Fin
	   	    	 * */
		   				
	   	   		myListCodigos= new ArrayList<Integer>()	;   	   	
	   	   		myListaCodigoPersonal = new ArrayList<String>();
	   	   		
	   	   		
	   	   		int secuencia_origen = documento_interno_deriva.getSecuencia_movimiento();
	   	   		
				if (naturaleza_documento.equals("I")) {
					log.info("Entry I ...");
					
					codigo_oficina = codigo_oficina == null ? "" : codigo_oficina;
					if (chk_copia != null) {
						
						log.info("El chk_copia es diferente de null.."+ chk_copia);

						for (int i = 0; cbo_copia != null && i < cbo_copia.length; i++) {
							log.info("El PAR_DES_LAR es diferente de null..");
							log.info("Antes del insert Clases..");
							
							log.info("las cbo_copia son :" + cbo_copia[i]);
							String cbo_copia_aux=cbo_copia[i];
							String ls_cod_persona = funciones.of_codigo_oficina_persona(cnx, cbo_copia_aux);
							log.info("El ls_cod_persona es...LLL :"+ ls_cod_persona);
							//linea agregada por mpelaezs 26-11-2011
							//agregar_a_ListaCodigoPersonal(myListaCodigoPersonal,ls_cod_persona); //agregado por mpelaezs 26-11-2011
							
							myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,cbo_copia_aux);			    			
			    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
			    			if(myListCodigos.size()>0){
			    				for (int cod : myListCodigos) {
			    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
							}   					    			
			    			}
							
							

							if (!codigo_oficina.trim().equals(cbo_copia[i].trim())) {
								daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
										ls_codigo_oficina_user, cbo_copia[i],
										codigo_motivo, observacion, ls_cod_persona,
										ls_documento, secuencia_movimiento,
										ls_usuario, ls_documento, li_retorno,
										fecha_rpta, codigo_expediente,
										ls_descripcion_oficina, codigo_recepcion,
										doc_resp, fecha_rpta_rq, 'C',secuencia_origen);
								
							/***
							 * RECEPCION DE COPIAS	
							 */
							/*
								Date fechaz = new Date(); // fecha y hora locales 
					 	    		SimpleDateFormat fechaRecepcion = new SimpleDateFormat("dd/MM/yyyy"); 
					 	    		SimpleDateFormat horaRecepcion = new SimpleDateFormat("HH:mm"); 
					 	    		log.info("la fecha es.."+ fechaRecepcion.format(fechaz) ); 
					 	    		log.info("la hora es.."+ horaRecepcion.format(fechaz) ); 
					 				String ls_fechaRecepcion=formatoFecha.format(fechaz);
					 				String ls_horaRecepcion=formatoHora.format(fechaz);
					 				
					 				String usuario_destino = funciones.of_usuario_destino(cnx, ls_cod_persona);
					 				String cargo_personal_recibe=funciones.of_cargo_persona(cnx, usuario_destino);
					 				String secuenciaQsigue = funciones.of_numero_secuencia(cnx,String.valueOf(li_retorno));
					 				String estado_movimiento_registro="2";
								
															
								daoIUsuarioDAO.of_recibir_doc(cnx,
										cbo_copia_aux.trim(),
						 				ls_documento,
						 					usuario_destino,
						 						ls_fechaRecepcion,
						 						ls_horaRecepcion,
						 						observacion,
						 						estado_movimiento_registro,
						 					String.valueOf(li_retorno),
						 					secuenciaQsigue,
						 					observacion,
						 					cargo_personal_recibe);
						 			
						 						
						 				if(cnx.isClosed())
						 						{cnx = getConnection(request, "principal");}	
						 						*/
								/**
								 * FIN DE RECEPCION
								 */
							}

							session.setAttribute("codigo_oficina_copia",
									cbo_copia[i]);

						}

					}
					
					log.info("Despues del Ingreso Documento,si codigo inicial es igual a cod documento");
					log.info("El ls_Documento es igual a codigo padre y el documento es Interno");
					//Reiterativo para documento Interno
					daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
							ls_codigo_oficina_user, codigo_oficina, codigo_motivo,
							observacion, personas, ls_documento,
							secuencia_movimiento, ls_usuario, ls_documento,
							li_retorno, fecha_rpta, codigo_expediente,
							ls_descripcion_oficina, codigo_recepcion, doc_resp,
							fecha_rpta_rq, 'R',secuencia_origen);

					/***
					 * RECEPCION PRINCIPAL
					 */
					/*
					Date fechaz = new Date(); // fecha y hora locales 
					SimpleDateFormat fechaRecepcion = new SimpleDateFormat("dd/MM/yyyy"); 
					SimpleDateFormat horaRecepcion = new SimpleDateFormat("HH:mm"); 
					String ls_fechaRecepcion=formatoFecha.format(fechaz);
					String ls_horaRecepcion=formatoHora.format(fechaz);
					String estado_movimiento_registro="2";
					String usuario_destino = funciones.of_usuario_destino(cnx, personas);
					String cargo_personal_recibe=funciones.of_cargo_persona(cnx, usuario_destino);
					String secuenciaQsigue = funciones.of_numero_secuencia(cnx,String.valueOf(li_retorno));

					iRDao.recibirDocumentoReiterado(codigo_oficina,ls_documento,   						 					
							usuario_destino,
							ls_fechaRecepcion,
							ls_horaRecepcion,
							observacion,
							estado_movimiento_registro,
							String.valueOf(li_retorno),
							secuenciaQsigue,
							observacion,
							cargo_personal_recibe);


					if(cnx.isClosed())
					{cnx = getConnection(request, "principal");}
					*/
					/***
					 * FIN DE RECEPCION
					 */
			

				} else { //si no es I, osea E

					if (chk_copia != null) {

						log.info("El chk_copia es diferente de null.."+ chk_copia);

						for (int i = 0; cbo_copia != null && i < cbo_copia.length; i++) {
							log.info("El PAR_DES_LAR es diferente de null..");
							log.info("Antes del insert Clases..");
							// daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
							log.info("las cbo_copia son :" + cbo_copia[i]);
							
							
							String chk_copias_aux=cbo_copia[i];
				    			String ls_cod_persona=funciones.of_codigo_oficina_persona(cnx,chk_copias_aux);   					    			
				    			
				    			
				    			
							log.info("El ls_cod_persona es...LLL :"+ ls_cod_persona);
							
							//linea agregada por mpelaezs 26-11-2011
						//	agregar_a_ListaCodigoPersonal(myListaCodigoPersonal,ls_cod_persona);
							
									myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,chk_copias_aux);			    			
					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
					    			if(myListCodigos.size()>0){
					    				for (int cod : myListCodigos) {
					    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
									}   					    			
					    			}
							
							
							

							if (!codigo_oficina.trim().equals(cbo_copia[i].trim())) {
								daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
										ls_codigo_oficina_user, cbo_copia[i],
										codigo_motivo, observacion, ls_cod_persona,
										ls_documento, secuencia_movimiento,
										ls_usuario, ls_documento, li_retorno,
										fecha_rpta, codigo_expediente,
										ls_descripcion_oficina, codigo_recepcion,
										doc_resp, fecha_rpta_rq, 'C',secuencia_origen);

							}
							session.setAttribute("codigo_oficina_copia",
									cbo_copia[i]);

						}

					}
					

					log.info("El chk_copia es == null");
					log.info("El ls_Documento es igual a codigo padre y el documento es de Entrada");

					if (ls_oficina.equals(codigo_oficina)) {

						ls_flag = "I";
						log.info("Si las oficinas son iguales CCCCCCCC XXXXX..");

						if (ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")) {

							log.info("Si el cargo de personal es igual 3 o 10 ......");
							log.info("Si las oficinas son diferentes y el cargo es diferente 3 o 10");

							daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
									ls_codigo_oficina_user, codigo_oficina,
									codigo_motivo, observacion, personas,
									ls_documento, secuencia_movimiento, ls_usuario,
									ls_documento, li_retorno, fecha_rpta,
									codigo_expediente, ls_descripcion_oficina,
									codigo_recepcion, doc_resp, fecha_rpta_rq,
									'R',secuencia_origen);
							
							/***recepion de reiterativo****/
							
							/****fin de recepcion******/

						} else {

							log.info("Si el cargo de personal es diferente 3 o 10 ......");
							daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
									ls_codigo_oficina_user, codigo_oficina,
									codigo_motivo, observacion, personas,
									ls_documento, secuencia_movimiento, ls_usuario,
									ls_documento, li_retorno, fecha_rpta,
									codigo_expediente, ls_descripcion_oficina,
									codigo_recepcion, doc_resp, fecha_rpta_rq, 'R',secuencia_origen);
							
							
							/***recepion de reiterativo****/
							
							/****fin de recepcion******/

						}

					} else {

						log.info("Si las oficinas son diferentes y el cargo es diferente 3 o 10");
						daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,
								ls_codigo_oficina_user, codigo_oficina,
								codigo_motivo, observacion, personas, ls_documento,
								secuencia_movimiento, ls_usuario, ls_documento,
								li_retorno, fecha_rpta, codigo_expediente,
								ls_descripcion_oficina, codigo_recepcion, doc_resp,
								fecha_rpta_rq, 'R',secuencia_origen);
						
						/***recepion de reiterativo****/
						
						/****fin de recepcion******/

					}
					
					log.info("Despues del Ingreso Documento,si codigo inicial es igual a cod documento");

				}
				/*
				 * agregado por mpelaez 26-11-2011
				 * */
				log.info("tamanio listaCodigoPersonal: "+myListaCodigoPersonal.size());
		     		if(myListaCodigoPersonal.size()>0){
		     			ArrayList<String> myListaCorreos = daoIUsuarioDAO.obtenerDatosUsuario(myListaCodigoPersonal);
		     			log.info("tamanio listacorreos: "+myListaCorreos.size());
		     			// con la lista ahora enviamos los correos respectivos:	
	//desactivado	     			enviarCorreoElectronico(myListaCorreos,codigo_documento,sessionMail,hDestinatario,IConstante.correoCC_Copias);
		     		}
		     		
		     	/*fin
		     	 * */	

				log.info("Despues del Ingreso Documento y Direccionando,si codigo inicial es diferente a cod documento");

			/*	Collection rs_derivacion_doc = new ArrayList();

				if (ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")) {

					System.out
							.println("Si el cargo de personal es diferente 3 o 10 ......");

					rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(
							cnx, ls_codigo_oficina_user,"");

				} else {

					log.info("Si el cargo de personal es 3 o 10 ......");

					rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(
							cnx, ls_codigo_oficina_user, ls_codigo_persona,"");

				}*/

				if(daoIUsuarioDAO.ValidaListaDocEntradaDetUpload(codigo_documento,secuencia_movimiento)>0){
					service.actualizarEstadoArchivos(ls_codigo_oficina_user,0,Integer.parseInt(reg.getCodigo_documento()),codigo_expediente,secuencia_origen,usuario,li_retorno);
				}
				
				
				Collection listaDocsDetalles = service.listarSecuencias(codigo_documento);
				session.setAttribute("listadoSecuenciasRegistro", listaDocsDetalles);
				/*FFormReiterativoRegistro reg2 = new FFormReiterativoRegistro();
				reg2.setCodigo_documento(reg.getCodigo_documento());
				reg2.setCodigo_expediente(reg.getCodigo_expediente());
				reg2.setNumero_documento(reg.getNumero_documento());
				reg2.setProcedencia(reg.getProcedencia());
				reg2.setNaturaleza_documento(reg.getNaturaleza_documento());
				fecha = f.convertirFecha(new Date());
				reg2.setFecha(fecha);
				
				request.setAttribute("mensaje_usuario", "Registro "+ reg.getCodigo_documento() +" reiterado con Éxito");
				
				request.setAttribute("FFormReiterativoRegistro", reg2);*/
	  			/*****REMUEVO LA LISTA DE DOC FIRMADOS INTERNOS****/
	  			
		  		session.removeAttribute("operacionpopup");
       	    	session.removeAttribute("mensaje_reg");
       	    	session.removeAttribute("mensaje_exp");	
       	    	session.removeAttribute("mensaje");
		  		session.removeAttribute("codigo_doc_para_grabar");
				session.removeAttribute("operacion_firma_documento");
				session.removeAttribute("rs_upload_doc_internos");
				session.removeAttribute("observacion");
				session.removeAttribute("tipoenv");
				session.removeAttribute("pagina");
				session.removeAttribute("firmaManual");
				
				String mensaje_recepcion =Constantes.DocumentoFirmadoExito.getNombre(); //"Documento Firmado con Exito!!!";
				
				String mensaje_numreg ="N° de Registro: "+ codigo_documento;
				String mensaje_numexp ="N° de Expediente: "+ codigo_expediente;
				log.info("El mensaje_recepcion es.." + mensaje_recepcion);
			
				
				
				session.setAttribute("mensaje",mensaje_recepcion);
				session.setAttribute("mensaje_reg",mensaje_numreg);
				session.setAttribute("mensaje_exp",mensaje_numexp);
				
				session.setAttribute("listaFrameBusquedaTemp",null);
				session.setAttribute("para_firma",null);
				/***********************/
				log.info("Retorno a Modulo Reiterativo");
				
				if(pagina.equals("haciabreitera")){
					session.setAttribute("operacionpopup","Y");
					session.setAttribute("nombreArchivoVer", documento_interno_deriva.getNombre_arhivo());
	       	    	session.setAttribute("tipoDocumentoVer", "3");
	       	    	session.setAttribute("codigoOficinaVer", documento_interno_deriva.getCodigo_oficina_pertenece().toString());
					return (mapping.findForward("haciabreitera"));
				}else{
					session.setAttribute("operacionpopup","X");
					request.setAttribute("nombreArchivoVer", documento_interno_deriva.getNombre_arhivo());
	       	    	request.setAttribute("tipoDocumentoVer", "3");
	       	    	request.setAttribute("codigoOficinaVer", documento_interno_deriva.getCodigo_oficina_pertenece().toString());
					return (mapping.findForward("exitoreiterativo"));

				}

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			//closeConnection(cnx);
			// return mapping.getInputForward();

		} catch (Exception ex) {
			ex.printStackTrace();
			//closeConnection(cnx);
		}
		finally{
			closeConnection(cnx);
		}

		return (mapping.findForward("registro"));
	
}

	private void subirArchivosBloque(String ls_codigo_documento, Collection rs_upload_doc_internos_adj, String ls_codigo_expediente, String ls_secuencia_movimiento) {
		log.info(">>>>>>>>>>>>>>>>>>>>>>ENTRE EN SUBIR DOCUMENTOS EN BLOQUE >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		log.info(">>>>>>>>>>>>>>>>>>>>>> DOCUMENTOS INTERNOS FIRMADOS>>>>>>>>>>>>>>>>>>>");
				
		
		if(rs_upload_doc_internos_adj!=null && rs_upload_doc_internos_adj.size()>0){
			Iterator it=rs_upload_doc_internos_adj.iterator();
			while(it.hasNext()){
				BArchivo archivo=(BArchivo)it.next();
				try {
					log.info("subir_archivo_firmado(ls_codigo_documento, archivo,ls_codigo_expediente, ls_secuencia_movimiento);");
					subir_archivo_firmado(ls_codigo_documento, archivo,ls_codigo_expediente, ls_secuencia_movimiento);
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
		log.info(">>>>>>>>>>>>>>>>>>>>>>FIN EN SUBIR DOCUMENTOS FIRMADOS EN BLOQUE >>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}


	private void subir_archivo_firmado(String documento,BArchivo archivo, String ls_codigo_expediente, String ls_secuencia_movimiento) throws SQLException, IOException,Exception {

	/*	IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		Funciones funciones = new Funciones();
		String fileName = archivo.getNombre_archivo();
	//	String filePath = Constantes.CarpetaArchivosProduccion.getNombre();
		
		String nombre_original=fileName;
		
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

			log.info("El Case es 5..");

			String ls_new_nombre_upload = funciones.of_valida_letras(fileName);
			log.info("<<<<<<<<<<<<<<<<<<<<<SUBIENDO ARCHIVO "+nombre_original);
			
			if (funciones.validaNombreDelArchivo(ls_new_nombre_upload)) {
				log.info("El ls_new_nombre_upload ees.."
						+ ls_new_nombre_upload);
								
				File fileToCreate = new File(filePath, ls_new_nombre_upload);

				if (!fileToCreate.exists()) {
					/****LEO EL DOCUMENTO**********
					FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
					
					FileInputStream file = new FileInputStream(nombre_original);
					int longitud = file.available();
					byte b[] = new byte[longitud];
										
					fileOutStream.write(file.read(b));
					fileOutStream.flush();
					fileOutStream.close();
					file.close(); 
					
				}
								
				daoIUsuarioDAO.DerivarDocumentoDetUploadIng(
						Integer.parseInt(documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),
						Integer.parseInt(ls_secuencia_movimiento.trim()), ls_new_nombre_upload, filePath,"I","P","O");

			}

		}*/
		
		log.info("<<<<<<<<<<<<<<<<<<<<<SUBIENDO ARCHIVOS FIRMADOS DESDE LA LISTA>>>>>>>>>>>>>>>>>>>>");
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		String fileName = archivo.getNombre_archivo_cifrado();

			int punto = fileName.lastIndexOf('.');
			log.info("el puntoLUUUU es.." + punto);
			log.info("el puntoLUUUU + 1 es.."
					+ fileName.substring(punto + 1));
			if (fileName.substring(punto + 1).equals("DOC")) {
				fileName = fileName.replace("DOC", "doc");
				log.info("El Cambiando el nombre a miniscula..XXXXXX: "+fileName);
			}

			log.info("<<<<<<<<<<<<<<<<<<<<<REGISTRANDO ARCHIVO :"+fileName);
			
			if(archivo.isIsfirmadomanual()){
				log.info("--> M");
				daoIUsuarioDAO.ActualizaDocumentoDocEntradaDetUploadIng(Integer.parseInt(documento.trim()),archivo.getUsuario(), archivo.getId_detalle_archivo());
				
			}else{
				if(archivo.isIsfirmado()){
					log.info("1");
					log.info("daoIUsuarioDAO.DerivarDocumentoDetUploadIng("+documento+","+ls_codigo_expediente+","+ls_secuencia_movimiento+","+fileName+","+archivo.getRuta()+",A,P,F)");
					daoIUsuarioDAO.DerivarDocumentoDetUploadIng(
							Integer.parseInt(documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),
							Integer.parseInt(ls_secuencia_movimiento.trim()), fileName, archivo.getRuta(),"A","P","F");
				}
				if(archivo.isIsvisado()){
					log.info("2");
					daoIUsuarioDAO.DerivarDocumentoDetUploadIng(
							Integer.parseInt(documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),
							Integer.parseInt(ls_secuencia_movimiento.trim()), fileName, archivo.getRuta(),"A","P","V");
				}
				if(!archivo.isIsfirmado() && !archivo.isIsvisado()){
					log.info("3");
					daoIUsuarioDAO.DerivarDocumentoDetUploadIng(
							Integer.parseInt(documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),
							Integer.parseInt(ls_secuencia_movimiento.trim()), fileName, IConstante.filePathVisados,"A","P","V");
				}
			}
			
		
		log.info(">>>>>>>>>>>>>>>>>>>>>>FIN SUBIR ARCHIVO UNITARIO >>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	private void agregar_a_ListaCodigoPersonal(
			ArrayList<String> myListaCodigoPersonal, String ls_cod_persona) {
		if(ls_cod_persona!=null&& ls_cod_persona!=""){
				myListaCodigoPersonal.add(ls_cod_persona);//obteniendo la lista de codigos de usuarios			    		
		}
	}
	
	private void moverArchivosBloque(String ls_codigo_documento, Collection rs_upload_doc_internos_adj, String ls_codigo_expediente, String ls_secuencia_movimiento) {
		log.info(">>>>>>>>>>>>>>>>>>>>>>ENTRE EN MOVER DOCUMENTOS EN BLOQUE >>>>>>>>>>>>>>>>>>>>>>>>>>>");
				
		if(rs_upload_doc_internos_adj!=null && rs_upload_doc_internos_adj.size()>0){
			Iterator it=rs_upload_doc_internos_adj.iterator();
			while(it.hasNext()){
				BArchivo archivo=(BArchivo)it.next();
				try {
					log.info("subir_archivo_firmado(ls_codigo_documento, archivo,ls_codigo_expediente, ls_secuencia_movimiento);");
					mover_archivo(ls_codigo_documento, archivo,ls_codigo_expediente, ls_secuencia_movimiento);
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
		log.info(">>>>>>>>>>>>>>>>>>>>>>FIN EN SUBIR DOCUMENTOS FIRMADOS EN BLOQUE >>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	private void mover_archivo(String documento,BArchivo archivo, String ls_codigo_expediente, String ls_secuencia_movimiento) throws SQLException, IOException,Exception {
			
			log.info("<<<<<<<<<<<<<<<<<<<<<mover_archivo>>>>>>>>>>>>>>>>>>>>");
			IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			Funciones funciones = new Funciones();
			String fileName = archivo.getNombre_archivo_cifrado();

				int punto = fileName.lastIndexOf('.');
				log.info("el puntoLUUUU es.." + punto);
				log.info("el puntoLUUUU + 1 es.."
						+ fileName.substring(punto + 1));
				if (fileName.substring(punto + 1).equals("DOC")) {
					fileName = fileName.replace("DOC", "doc");
					log.info("El Cambiando el nombre a miniscula..XXXXXX: "+fileName);
				}

				log.info("<<<<<<<<<<<<<<<<<<<<<MOVIENDO ARCHIVO :"+fileName);
				log.info("copiarDocumento("+archivo.getRutaTemporal()+"/"+archivo.getFecha_hora_actual()+archivo.getNombre_archivo_cifrado()+","+filePath+archivo.getRutaDetalleUpload()+funciones.getAnioActualTexto()+archivo.getFecha_hora_actual()+archivo.getNombre_archivo_cifrado()+");");
				copiarDocumento(archivo.getRutaTemporal()+"/"+archivo.getFecha_hora_actual()+archivo.getNombre_archivo_cifrado(),filePath+archivo.getRutaDetalleUpload()+funciones.getAnioActualTexto()+"/"+archivo.getFecha_hora_actual()+archivo.getNombre_archivo_cifrado());
									
				log.info("<<<<<<<<<<<<<<<<<<<<<REGISTRANDO ARCHIVO :"+fileName);
				archivo.setNoFirmado(true);
				
				if(archivo.isIsfirmadomanual()){
					log.info("--> M");
					daoIUsuarioDAO.ActualizaDocumentoDocEntradaDetUploadIng(Integer.parseInt(documento.trim()),archivo.getUsuario(), archivo.getId_detalle_archivo());
					
				}else{
					if(archivo.isIsfirmado()){
						log.info("1");
						log.info("daoIUsuarioDAO.DerivarDocumentoDetUploadIng("+documento+","+ls_codigo_expediente+","+ls_secuencia_movimiento+","+fileName+","+archivo.getRuta()+",A,P,F)");
						daoIUsuarioDAO.DerivarDocumentoDetUploadIng(
								Integer.parseInt(documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),
								Integer.parseInt(ls_secuencia_movimiento.trim()), fileName, archivo.getRuta(),"A","P","F");
					}
					if(archivo.isIsvisado()){
						log.info("2");
						daoIUsuarioDAO.DerivarDocumentoDetUploadIng(
								Integer.parseInt(documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),
								Integer.parseInt(ls_secuencia_movimiento.trim()), fileName, archivo.getRuta(),"A","P","V");
					}
					if(!archivo.isIsfirmado() && !archivo.isIsvisado() && !archivo.isNoFirmado()){
						log.info("3");
						daoIUsuarioDAO.DerivarDocumentoDetUploadIng(
								Integer.parseInt(documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),
								Integer.parseInt(ls_secuencia_movimiento.trim()), fileName, IConstante.filePathVisados,"A","P","V");
					}
					if(archivo.isNoFirmado()){
						log.info("archivo.isNoFirmado()");
						daoIUsuarioDAO.CrearDocumentoDocEntradaDetUploadIng(
								Integer.parseInt(documento.trim()),archivo,
								Integer.parseInt(ls_codigo_expediente.trim()),Integer.parseInt(ls_secuencia_movimiento.trim()),
								"A", "P", "O");
												
					}
				}
				
			
			log.info(">>>>>>>>>>>>>>>>>>>>>>FIN SUBIR ARCHIVO UNITARIO >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
	
	private void copiarDocumento(String rutaOrigen, String rutaDestino){
		File inFile = new File(rutaOrigen);
	    File outFile = new File(rutaDestino);
	    FileInputStream in = null;
	    FileOutputStream out = null;
		try {
			
			if (makeSureDirectoryExists(parent(inFile)) && makeSureDirectoryExists(parent(outFile))) {
				if (outFile.exists())
					outFile.delete();
				
				
				in = new FileInputStream(inFile);
				out = new FileOutputStream(outFile);

			    int c;
			    while ((c = in.read()) != -1)
			     out.write(c);	
			}
			
		    
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
