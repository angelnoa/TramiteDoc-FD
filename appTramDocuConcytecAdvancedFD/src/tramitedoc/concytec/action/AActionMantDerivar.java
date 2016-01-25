package tramitedoc.concytec.action;
import java.io.File;
import java.io.FileInputStream;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BDatosFirmante;
import tramitedoc.concytec.bean.BInfo;
import tramitedoc.concytec.bean.BMesaPartes;
import tramitedoc.concytec.bean.BPojo;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormMantDerivar;
import tramitedoc.concytec.form.FFormMantDocumento;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Constantes;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.IConstante;
import tramitedoc.concytec.util.ValidaSessionAction;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionMantDerivar extends ValidaSessionAction implements IConstante{
	protected  Log log = LogFactory.getLog(AActionMantDerivar.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Properties props = System.getProperties( );
		props.put("mail.smtp.host","smtp.concytec.gob.pe");	
		Session sessionMail = Session.getDefaultInstance(props,null);
		ArrayList<String> myListaCodigoPersonal = new ArrayList<String>();
		ArrayList<Integer>  myListCodigos= new ArrayList<Integer>();//para envios de correos
		ArrayList<String> myListaCorreosDestinatarios= new ArrayList<String>();
		
		/*************PARA RESCATAR EL DOCUMENTO INTERNO********/
		DocumentosInternosService service = new DocumentosInternosServiceImplement();
		
		/*****************/
		
		log.info("DENTRO DEL MANTDERIVAR DOC..");
		HashMap<String, String> hDestinatario= new HashMap<String, String>();
		HashMap<String, String> hRemitente= new HashMap<String, String>();
		
		HttpSession session = request.getSession(true);
		
		if (session==null){
        	return (mapping.findForward("expiracion"));
        } 
				
		Connection cnx = getConnection(request, "principal");
		log.info("El cnx............" + cnx);	    
	  	
		//session.removeAttribute("cod_padre");
	    //session.removeAttribute("derivar");
	       
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		
		String ls_inicia_ls = String.valueOf(request.getAttribute("ls_inicia"));
		
		FFormMantDerivar oForm = (FFormMantDerivar) form;
		log.info("Antes del FFormMantDerivar...");
		
		log.info("-->   "+oForm.getTipo());
		String tipo = oForm.getTipo() == null ? "init" : oForm.getTipo().trim();
		
		log.info("Despues del tipo...");	 
		log.info("El tipo 1 "+ tipo);
		tipo = tipo.equals("") ? "init" : tipo;
		log.info("El tipo 2 "+ tipo);
		
		/***/
		if(tipo.equals("regresarbd")){
			tipo="derivar";
		}
		/***/
				
		String operacion_firma_documento = (String) session.getAttribute("operacion_firma_documento");
		operacion_firma_documento = (operacion_firma_documento==null)? "" : operacion_firma_documento;
		
		
		if(operacion_firma_documento.equals("FDDI")){
			tipo = "enviardocumentointerno";
		}

		/**************/
		
		
		
		
		String codigo_documento;
		int codigo_expediente=0;
		String descripcion_tipo;
		String numero_documento;
		String fecha;
		String hora;
		String observacion;
		String mensaje;
		String usuario;
		String codigo_oficina;
		String estado_movimiento;
		String codigo_expedienteee = null;
		
		String ls_codigoPadre = null;
        String ls_documento = null;
        String ls_secuencia = null;
        String ls_usuario = null;
        String ls_oficina = null;
        String ls_pagina  = null;
        String ls_correla  = null;
        String ls_codigo_inicia  = null;
        String ls_codigo_expediente="";
        String ls_naturaleza_documento="";
        String ls_correlativo_deriva="";
        String ls_descripcion_oficina="";
        
        BDatosFirmante resultado= new BDatosFirmante();
			
        
        String destinatario;
		String codigo_motivo;
		String ls_cod_padre;
		String personas;
		String cbo_fecharpta;
		String chk_copia;
		String[] cbo_copia;
		//String[] PAR_DES_LAR=null
		String secuencia_movimiento;
		String codigo_inicia;
		String naturaleza_documento;
		
		String fecha_rpta;
		String correlativorecepcion;
		int codigo_recepcion=0;
		String mensaje_recepcion;
		String doc_resp;
        int ls_correla_conv  = 0;
        String fecha_rpta_rq;
        String cargo_personal="";
		String ls_correlativorecepcion;
		int li_retorno=0;
		String ls_nombre_oficina;
		String ls_codigo_persona="";
		String ls_cargo_personal="";
		String ls_codigo_oficina_user="";
		String codigo_documento_busqueda="";
		String codigo_expediente_busqueda="";
		usuario   = String.valueOf(session.getAttribute("nombreusuario"));
		ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
		//log.info("codigo_oficina_moises pelaez");
		//ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina_user"));
		ls_codigo_persona   = String.valueOf(session.getAttribute("codigo_persona"));
		ls_cargo_personal   = String.valueOf(session.getAttribute("cargo_personal"));
		int valor=0;
		  /**
		   * JAZANERO
		   */
		  String chk_copia_other = null;
		  String[] cbo_copia_other = null;
		  String[] chk_copias_other=null;
		
		log.info("El ls_codigo_persona es... " + ls_codigo_persona);
		log.info("El ls_cargo_personal es... " + ls_cargo_personal);
		
	
		Funciones funciones=new Funciones();
		
		resultado = service.getDatosFirmante(usuario);
		
		if(resultado!=null){
		 valor=(resultado.getTipo_firma() == 2)? 0:1; //1:tiene firma, 0:notiene firma
		}
		String codigocontador;
		
		session.removeAttribute("mensaje_recepcion");
		session.removeAttribute("mensaje_numreg");
		session.removeAttribute("hojaenvio");

		String codigo_documento_busqueda_ls = request.getParameter("codigo_documento_busqueda_ls");
		codigo_documento_busqueda_ls = (codigo_documento_busqueda_ls==null)? "":codigo_documento_busqueda_ls;
		
		/***
		 * INICIO VERIFICACION SI ES DOCUMENTO FIRMADO DIGITALMENTE
		 **/

	
	  	if(tipo.equals("editar_obs")){
	 
			secuencia_movimiento = ((FFormMantDerivar)form).getSecuencia_movimiento();
			codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
			if(service.esRegistroDocumentoInternoObservado(codigo_documento, ls_oficina, secuencia_movimiento)){
				log.info("Es con firma digital");
				tipo="nuevo";
				session.setAttribute("operacion",tipo);
				request.setAttribute("mensaje_recepcion", "El Nro Registro "+codigo_documento+" contiene documentos");
				request.setAttribute("mensaje_numreg", "con Firma Digital, y no puede ser modificado por este medio.");
			}else{
				log.info("No es");
			}
			
		}
	  	
	  	/**
		 * JAZANERO
		 */
		String ls_codigo_institucion = String.valueOf(session.getAttribute("ls_codigo_institucion"));
		String ls_grupo_oficina = String.valueOf(session.getAttribute("grupo_oficina"));
		String es_padre=null;
		
		ls_codigo_institucion=(ls_codigo_institucion==null)?"":ls_codigo_institucion;
		ls_grupo_oficina=(ls_grupo_oficina==null)?"":ls_grupo_oficina;
		

		log.info("-> "+ls_codigo_institucion);
		log.info("-> "+ls_grupo_oficina);
		
		/***
		 * FIN
		 */
		
		try {
			
			
			if (tipo.equals("buscar") ){
				
				log.info("Si tipo es init " + tipo);
				log.info("Listar Buscar Documentos....." );
				
				Collection rs_derivacion_doc = new ArrayList();
				Collection rs_oficinas = new ArrayList();
				Collection rs_motivos = new ArrayList();
				
					//if(ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")){
				codigo_documento_busqueda = ((FFormMantDerivar)form).getCodigo_documento_busqueda();
				codigo_expediente_busqueda = ((FFormMantDerivar)form).getCodigo_expediente_busqueda();
				
				codigo_documento_busqueda = codigo_documento_busqueda_ls.trim();
				
				log.info("El codigo_documento_busqueda es...." + codigo_documento_busqueda);
				log.info("El codigo_expediente_busqueda es...." + codigo_expediente_busqueda);
				
				String accesoaoficinas=service.getAccesosaOficinas(ls_codigo_persona);
				if(accesoaoficinas != null){
					accesoaoficinas=ls_oficina+","+accesoaoficinas;
				}else{
					accesoaoficinas=ls_oficina;
				}
				
				rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos_buscar(cnx,ls_oficina,accesoaoficinas,codigo_documento_busqueda,codigo_expediente_busqueda);
				
				//rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_oficina,ls_codigo_persona);
				rs_oficinas = daoIUsuarioDAO.of_lista_oficinas(cnx);
				rs_motivos = daoIUsuarioDAO.of_lista_motivos(cnx);
					//log.info("el rs_derivacion_doc es ......"+ rs_derivacion_doc);
					
				session.setAttribute("operacion",tipo);
				session.setAttribute("rs_derivacion_doc",rs_derivacion_doc);
				session.setAttribute("rs_oficina",rs_oficinas);
				session.setAttribute("rs_motivos",rs_motivos);
					
			}

			else if (tipo.equals("derivar") ){
				
				
				log.info("Si tipo es init " + tipo);
				log.info("Listar Derivacion Documentos.....0" );
				
				
				Collection rs_derivacion_doc = new ArrayList();
				Collection rs_observados_doc = new ArrayList();
				Collection rs_oficinas = new ArrayList();
				Collection rs_motivos = new ArrayList();
				Collection rs_oficina_other= new ArrayList();
				
				try{
				
						rs_oficinas = daoIUsuarioDAO.of_lista_oficinas(cnx);
						
						
				        if(!ls_codigo_institucion.equals("")){
				        	rs_oficinas= daoIUsuarioDAO.of_lista_oficinas_institucion(ls_codigo_institucion,true);
				        	//rs_oficinas= daoIUsuarioDAO.of_lista_oficinas_institucion(ls_codigo_institucion,ls_grupo_oficina,true);
				        	rs_oficina_other= daoIUsuarioDAO.of_lista_oficinas_institucion(ls_codigo_institucion,false);
				        	es_padre=daoIUsuarioDAO.of_es_padre(ls_oficina);
			    		}
		
						
						String  cuadro_mensaje = (String) session.getAttribute("cuadro_mensaje");
						String  operacionpopup = (String) session.getAttribute("operacionpopup");
						
						cuadro_mensaje = (cuadro_mensaje == null)? "":cuadro_mensaje;
						operacionpopup = (operacionpopup == null)? "":operacionpopup;
						
						if(cuadro_mensaje.equals("BDER") || operacionpopup.equals("Y") ){
						String 	nombreArchivoVer = (String) session.getAttribute("nombreArchivoVer");
			       	    String 	tipoDocumentoVer = (String) session.getAttribute("tipoDocumentoVer");
			       	    String 	codigoOficinaVer = (String) session.getAttribute("codigoOficinaVer");
			       	    String  mensaje_d = (String) session.getAttribute("mensaje");
			       	    
		 	       	    session.removeAttribute("nombreArchivoVer");
			       	    session.removeAttribute("tipoDocumentoVer");
			       	    session.removeAttribute("codigoOficinaVer");
			       	    session.removeAttribute("mensaje");
			       	    session.removeAttribute("cuadro_mensaje");
			       	    session.removeAttribute("operacionpopup");
			       	    
			       	    request.setAttribute("nombreArchivoVer_", nombreArchivoVer);
			       	    request.setAttribute("tipoDocumentoVer_", tipoDocumentoVer);
			       	    request.setAttribute("codigoOficinaVer_", codigoOficinaVer);
			       	    request.setAttribute("mensaje", mensaje_d);
			       	    request.setAttribute("cuadro_mensaje", cuadro_mensaje);
			       	    request.setAttribute("operacionpopup", operacionpopup);
						}
						
						System.out.println("codigo_documento_busqueda_ls "+codigo_documento_busqueda_ls);
						if(codigo_documento_busqueda_ls.equals("Buscar...") || codigo_documento_busqueda_ls.equals("")){
							ls_inicia_ls = "SI";
						}
						log.info("El ls_codigo_persona es... " + ls_codigo_persona);
						log.info("El ls_cargo_personal es... " + ls_cargo_personal);
						log.info("------------->info"+valor);
						String accesoaoficinas=service.getAccesosaOficinas(ls_codigo_persona);
						if(accesoaoficinas != null){
							accesoaoficinas=ls_oficina+","+accesoaoficinas;
						}else{
							accesoaoficinas=ls_oficina;
						}
						
						if(ls_cargo_personal.equals("11") || ls_cargo_personal.equals("13") || ls_cargo_personal.equals("10") || ls_cargo_personal.equals("1") || ls_cargo_personal.equals("3") || ls_cargo_personal.equals("2")){
							//rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_oficina,ls_inicia_ls,valor);
							rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_oficina,accesoaoficinas,ls_inicia_ls,valor);
						}else{
							//rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_oficina,ls_codigo_persona,ls_inicia_ls,valor);
							rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_oficina,accesoaoficinas,ls_codigo_persona,ls_inicia_ls,valor);
						}
		  				 				
		  				
		  				//rs_observados_doc = daoIUsuarioDAO.lista_observacion_documentos(cnx,ls_oficina,ls_inicia_ls);
						//observacion_documento
						log.info("-->length-->  "+rs_derivacion_doc.size());
						
						rs_motivos = daoIUsuarioDAO.of_lista_motivos(cnx);
		
						session.setAttribute("operacion",tipo);
						session.setAttribute("rs_derivacion_doc",rs_derivacion_doc);
						session.setAttribute("rs_observados_doc",rs_observados_doc);
						session.setAttribute("rs_oficina",rs_oficinas);
						session.setAttribute("rs_oficina_other",rs_oficina_other);
						session.setAttribute("rs_motivos",rs_motivos);
		                session.setAttribute("es_padre", es_padre);
		                
				}catch (SQLException e) {
					e.printStackTrace();
				} /*finally{
		        	closeConnection(cnx);	
		        }  */    
					
			}
			
			else if(tipo.equalsIgnoreCase("observacion")){ 

				
				log.info("Entry a observacion ....");

				session.removeAttribute("mensaje_numreg");
				session.removeAttribute("mensaje_numexp");
				session.removeAttribute("mensaje_numenv");
				
				String pcodigo_documento = request.getParameter("valor1");				
				String pcodigo_expediente = request.getParameter("valor2");
				String pcodigo_recepcion = request.getParameter("valor3");
				String psecuencia = request.getParameter("valor4");
				String psecuencia_origen = request.getParameter("valor5");

				
				pcodigo_documento=(pcodigo_documento==null)?"":pcodigo_documento;
				pcodigo_expediente=(pcodigo_expediente==null)?"":pcodigo_expediente;
				pcodigo_recepcion=(pcodigo_recepcion==null)?"":pcodigo_recepcion;
				psecuencia=(psecuencia==null)?"":psecuencia;
				psecuencia_origen=(psecuencia_origen==null)?"":psecuencia_origen;
				
				log.info(pcodigo_documento+","+pcodigo_expediente+","+pcodigo_recepcion+","+psecuencia+","+psecuencia_origen);


			try {
				observacion="";
				estado_movimiento="2";
				System.out.println("Si tipo es editar init" + tipo);
   	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
   	    		System.out.println("La observacion es...."+ observacion);	
   	    		
   	    		if(observacion.equals("null")){
   	 			observacion="";
   	 				System.out.println("La observacion es null de lo contrario es igual a vacio.");
   	    		}
   	    		System.out.println("Modificando la secuencia="+psecuencia);
   	    		
   	    		fecha=((FFormMantDerivar)form).getFecha();
   	    		hora = ((FFormMantDerivar)form).getHora();
   	    		
   	    		String ls_mensaje=daoIUsuarioDAO.of_observar_doc_deriva(cnx,ls_oficina,pcodigo_documento,usuario,fecha,hora,observacion,estado_movimiento,psecuencia);
   	    		//String ls_mensaje="SI";
   	    		Collection rs_recepcion_doc = new ArrayList();
  	    		
  	    		if(ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")){
					
					rs_recepcion_doc = null; //daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,ls_oficina,"SI");
				      
					log.info("rs_recepcion_doc Cargo Secretaria, Director" + rs_recepcion_doc);
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);	
					if(ls_mensaje.equals("SI")){
						request.setAttribute("operacionpopup", "G");
						request.setAttribute("mensaje", "Documento Observado con exito...!");
						request.setAttribute("mensaje_reg", "Nº de Reg: "+pcodigo_documento);
						request.setAttribute("mensaje_exp", "Nº de Exp: "+pcodigo_expediente);
					}else{
						request.setAttribute("operacionpopup", "B");
						request.setAttribute("mensaje", "Documento no se puede Observar...!");
						request.setAttribute("mensaje_reg", "Nº de Reg: "+pcodigo_documento);
						request.setAttribute("mensaje_exp", "Nº de Exp: "+pcodigo_expediente);
					}
					
					
				}else{
					
					rs_recepcion_doc = null; //daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,ls_oficina,ls_codigo_persona,"SI");
				      
					log.info("rs_recepcion_doc Cargo No es Secretaria o Director" + rs_recepcion_doc);
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);
					
					if(ls_mensaje.equals("SI")){
						request.setAttribute("operacionpopup", "G");
						request.setAttribute("mensaje", "Documento Observado con exito...!");
						request.setAttribute("mensaje_reg", "Nº de Reg: "+pcodigo_documento);
						request.setAttribute("mensaje_exp", "Nº de Exp: "+pcodigo_expediente);
					}else{
						request.setAttribute("operacionpopup", "B");
						request.setAttribute("mensaje", "Documento no se puede Observar...!");
						request.setAttribute("mensaje_reg", "Nº de Reg: "+pcodigo_documento);
						request.setAttribute("mensaje_exp", "Nº de Exp: "+pcodigo_expediente);
					}
				}
				request.setAttribute("FFormMantRecepcion", null);
				
   	    		
			}catch (SQLException e) {
				e.printStackTrace();
			} /*finally{
	        	closeConnection(cnx);	
	        }*/
	
				
			}else	
				
				if (tipo.equalsIgnoreCase("editar")){
					
				try {
					session.removeAttribute("contador");
					session.removeAttribute("rs_upload_doc_internos");
					//session.removeAttribute("rs_personas");
					log.info("Si tipo es editar init" + tipo);
					log.info("Dentro de Editar en MantDerivar");	
					session.removeAttribute("mensaje_numreg");
					session.removeAttribute("mensaje_numexp");
					session.removeAttribute("mensaje_numenv");
					//session.removeAttribute("codigocontador");
					
					//session.removeAttribute("personas");
					//session.removeAttribute("cargo_personal");
					//session.removeAttribute("as_codigo_oficina");
					cargo_personal   =   String.valueOf(session.getAttribute("cargo_personal"));
					log.info(" El cargo_personal es .. "+ cargo_personal);
					
					codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
					codigo_expediente = ((FFormMantDerivar)form).getCodigo_expediente();
					descripcion_tipo = ((FFormMantDerivar)form).getDescripcion_tipo();
					numero_documento = ((FFormMantDerivar)form).getNumero_documento();
					
					fecha = ((FFormMantDerivar)form).getFecha();
					/*hora = ((FFormMantDerivar)form).getHora();
					observacion = ((FFormMantDerivar)form).getObservacion();
					estado_movimiento = ((FFormMantDerivar)form).getEstado_movimiento();*/
					
					codigo_oficina = ((FFormMantDerivar)form).getCodigo_oficina();
					destinatario = ((FFormMantDerivar)form).getDestinatario();
					codigo_motivo = ((FFormMantDerivar)form).getCodigo_motivo();
					observacion = ((FFormMantDerivar)form).getObservacion();
					fecha_rpta = ((FFormMantDerivar)form).getFecha_rpta();
					
					personas = ((FFormMantDerivar)form).getPersonas();
					cbo_fecharpta = ((FFormMantDerivar)form).getCbo_fecharpta();
					chk_copia = ((FFormMantDerivar)form).getChk_copia();
					cbo_copia = ((FFormMantDerivar)form).getCbo_copia();
					
					secuencia_movimiento = ((FFormMantDerivar)form).getSecuencia_movimiento();
					codigo_inicia = ((FFormMantDerivar)form).getCodigo_inicia();
					naturaleza_documento = ((FFormMantDerivar)form).getNaturaleza_documento();
					codigo_recepcion = ((FFormMantDerivar)form).getCodigo_recepcion();
					//codigocontador= ((FFormMantDerivar)form).getCodigocontador();
					codigocontador= ((FFormMantDerivar)form).getCodigocontador();
					
					
					log.info(" usuario.. "+ usuario);
					log.info(" codigo_documento.. "+ codigo_documento);
					log.info(" codigo_expediente.. "+ codigo_expediente);
					log.info(" descripcion_tipo.. "+ descripcion_tipo);
					log.info(" numero_documento.. "+ numero_documento);
					log.info(" fecha.. "+ fecha);
					log.info(" destinatario.. "+ destinatario);
					log.info(" codigo_motivo.. "+ codigo_motivo);
					log.info(" observacion.. "+ observacion);
					log.info(" fecha_rpta.. "+ fecha_rpta);
					log.info(" chk_copia.. "+ chk_copia);
					log.info(" cbo_copia.. "+ cbo_copia);
					log.info(" codigo_recepcion.. "+ codigo_recepcion);
					log.info(" secuencia_movimiento.. "+ secuencia_movimiento);
					log.info(" codigo_oficina.. "+ codigo_oficina);
					log.info(" codigocontador.. "+ codigocontador);
					log.info(" personas.. "+ personas);
					
					
	   	    		log.info("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
	   	    	
	   	    		if(observacion.equals("null")){
	   	   	 			observacion="";
	   	   	 			log.info("La observacion es null de lo contrario es igual a vacio.");
	   	   	    	}
	   	    		
	   	    		
	   	    		
	   	    		log.info("El ls_oficina editar XX es..." + ls_oficina);
	   	    		//ls_nombre_oficina=funciones.of_nombre_oficina(cnx,ls_oficina);
	   	    		//log.info("El ls_nombre_oficina es..." + ls_nombre_oficina);
	      	    	
					
	      	    	Collection rs_oficinas = new ArrayList();
	      	    	Collection rs_oficina_other = null;
	   	    		
	   	    		rs_oficinas = daoIUsuarioDAO.of_lista_oficinas(cnx);
	   	    		
	   	    		/**
					 * JAZANERO
					 */

					log.info("Pertenece a "+ls_codigo_institucion);
			        if(!ls_codigo_institucion.equals("")){
			        	//rs_oficinas= daoIUsuarioDAO.of_lista_oficinas_institucion(ls_codigo_institucion,true);
			        	rs_oficinas= daoIUsuarioDAO.of_lista_oficinas_institucion(ls_codigo_institucion,true);
			        	rs_oficina_other= daoIUsuarioDAO.of_lista_oficinas_institucion(ls_codigo_institucion,false);
			        	es_padre=daoIUsuarioDAO.of_es_padre(ls_oficina);
			        	
		    		}
	   	    	
	   	    		FFormMantDerivar formulario=(FFormMantDerivar)daoIUsuarioDAO.obtenerDetalleBandejaDerivacion(cnx, codigo_documento,secuencia_movimiento,secuencia_movimiento,"N");
	   	    		//((FFormMantDerivar)form).setNumero_documento("kdjkdjgdg");
	   	    		
	   	    		((FFormMantDerivar)form).setear(formulario);
	   	    		
	   	    		//request.setAttribute("FFormMantDerivar", formulario);
					//rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_oficina);
							
					session.setAttribute("operacion",tipo);
					//session.setAttribute("rs_derivacion_doc",rs_derivacion_doc);
					session.setAttribute("rs_oficina",rs_oficinas);
					session.setAttribute("rs_oficina_other",rs_oficina_other);
					session.setAttribute("cargo_personal",cargo_personal);
					session.setAttribute("as_codigo_oficina",ls_oficina);
					//session.setAttribute("nombre_oficina",ls_nombre_oficina);
					session.setAttribute("codigo_oficina", ls_oficina);
					session.setAttribute("codigocontador", "0");
					session.setAttribute("nombre_file_adjunto",null);
					session.setAttribute("es_padre", es_padre);
					
	   	    		
				}catch (SQLException e) {
					//rollbackConnection(cnx);
					e.printStackTrace();
					session.setAttribute("MsjeError", "Error al actualizar al empleado: " + e.getMessage());
					
				}/*finally{
		        	closeConnection(cnx);	
		        }*/
					
			return mapping.getInputForward();
	
		} else if (tipo.equalsIgnoreCase("editar_obs")){
			try {
				session.removeAttribute("rs_upload_doc_internos");

				log.info("Si tipo es editar init" + tipo);
				log.info("Dentro de Editar en MantDerivar");	
				session.removeAttribute("mensaje_numreg");
				session.removeAttribute("mensaje_numexp");
				session.removeAttribute("mensaje_numenv");

				cargo_personal   =   String.valueOf(session.getAttribute("cargo_personal"));
				log.info(" El cargo_personal es "+ cargo_personal);
				
				codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
				codigo_expediente = ((FFormMantDerivar)form).getCodigo_expediente();
				descripcion_tipo = ((FFormMantDerivar)form).getDescripcion_tipo();
				numero_documento = ((FFormMantDerivar)form).getNumero_documento();
				
				fecha = ((FFormMantDerivar)form).getFecha();
				/*hora = ((FFormMantDerivar)form).getHora();
				observacion = ((FFormMantDerivar)form).getObservacion();
				estado_movimiento = ((FFormMantDerivar)form).getEstado_movimiento();*/
				
				codigo_oficina = ((FFormMantDerivar)form).getCodigo_oficina();
				destinatario = ((FFormMantDerivar)form).getDestinatario();
				codigo_motivo = ((FFormMantDerivar)form).getCodigo_motivo();
				observacion = ((FFormMantDerivar)form).getObservacion();
				fecha_rpta = ((FFormMantDerivar)form).getFecha_rpta();
				
				personas = ((FFormMantDerivar)form).getPersonas();
				cbo_fecharpta = ((FFormMantDerivar)form).getCbo_fecharpta();
				chk_copia = ((FFormMantDerivar)form).getChk_copia();
				cbo_copia = ((FFormMantDerivar)form).getCbo_copia();
				
				secuencia_movimiento = ((FFormMantDerivar)form).getSecuencia_movimiento();
				codigo_inicia = ((FFormMantDerivar)form).getCodigo_inicia();
				naturaleza_documento = ((FFormMantDerivar)form).getNaturaleza_documento();
				codigo_recepcion = ((FFormMantDerivar)form).getCodigo_recepcion();
				//codigocontador= ((FFormMantDerivar)form).getCodigocontador();
				codigocontador= ((FFormMantDerivar)form).getCodigocontador();
				
				String ls_secuencia_origen = codigo_inicia;
				
				log.info(" usuario.. "+ usuario);
				log.info(" codigo_documento.. "+ codigo_documento);
				log.info(" codigo_expediente.. "+ codigo_expediente);
				log.info(" descripcion_tipo.. "+ descripcion_tipo);
				log.info(" numero_documento.. "+ numero_documento);
				log.info(" fecha.. "+ fecha);
				log.info(" destinatario.. "+ destinatario);
				log.info(" codigo_motivo.. "+ codigo_motivo);
				log.info(" observacion.. "+ observacion);
				log.info(" fecha_rpta.. "+ fecha_rpta);
				log.info(" chk_copia.. "+ chk_copia);
				log.info(" cbo_copia.. "+ cbo_copia);
				log.info(" codigo_recepcion.. "+ codigo_recepcion);
				log.info(" secuencia_movimiento.. "+ secuencia_movimiento);
				log.info(" codigo_oficina.. "+ codigo_oficina);
				log.info(" codigocontador.. "+ codigocontador);
				log.info(" personas.. "+ personas);
				log.info(" secuencia_origen = "+ls_secuencia_origen);
				
   	    		log.info("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
   	    	
   	    		if(observacion.equals("null")){
   	   	 			observacion="";
   	   	 			log.info("La observacion es null de lo contrario es igual a vacio.");
   	   	    	}
   	    		
   	    		
   	    		
   	    		log.info("El ls_oficina editar XX es..." + ls_oficina);

      	    	Collection rs_oficinas = new ArrayList();
      	    	Collection rs_personas = new ArrayList();
   	    		Collection rs_derivacion_doc = new ArrayList();

   	    		rs_oficinas = daoIUsuarioDAO.of_lista_oficinas(cnx);
   	    		rs_personas = daoIUsuarioDAO.of_lista_personas_oficinas(ls_oficina);
   	    		
   	    		log.info("---> daoIUsuarioDAO.obtenerDetalleBandejaDerivacion(cnx,"+codigo_documento+","+secuencia_movimiento+","+ls_secuencia_origen+",O)");
   	    		FFormMantDerivar formulario=(FFormMantDerivar)daoIUsuarioDAO.obtenerDetalleBandejaDerivacion(cnx, codigo_documento,secuencia_movimiento,ls_secuencia_origen,"O");
   	    		
   	    				//formulario.setCodigocontador("2");
   	    				request.setAttribute("FFormMantDerivar", formulario);
						
						session.setAttribute("operacion",tipo);
						session.setAttribute("rs_oficina",rs_oficinas);
						session.setAttribute("cargo_personal",cargo_personal);
						session.setAttribute("as_codigo_oficina",ls_oficina);
						session.setAttribute("codigo_oficina", ls_oficina);
						
						String ls_oficina_destino = funciones.of_nombre_oficina(cnx, formulario.getCodigo_oficina_que_observa());
						String ls_persona_destino = funciones.of_nombre_persona(cnx, formulario.getCodigo_persona_que_observa());
						
						request.setAttribute("rs_personas", rs_personas);
						
						request.setAttribute("ls_oficina_destino", ls_oficina_destino);
						request.setAttribute("ls_persona_destino", ls_persona_destino);
						
						request.setAttribute("ls_codigo_motivo", formulario.getCodigo_motivo());
						request.setAttribute("ls_codigo_persona_que_observa", formulario.getCodigo_persona_que_observa());
						request.setAttribute("ls_codigo_oficina_que_observa", formulario.getCodigo_oficina_que_observa());
						request.setAttribute("ls_secuencia_movimiento_destino", secuencia_movimiento);
						
						String name_documento_repuesta = formulario.getDoc_resp();
						name_documento_repuesta = (name_documento_repuesta==null)? "":name_documento_repuesta;
						
						if(!name_documento_repuesta.equals("")){
							session.setAttribute("nombre_file_adjunto",name_documento_repuesta);
							session.setAttribute("contador", "1");	
						}
						
						request.setAttribute("modo_observacion", "1");
						
				
   	    		
			}catch (SQLException e) {
				rollbackConnection(cnx);
				e.printStackTrace();
				session.setAttribute("MsjeError", "Error al actualizar al empleado: " + e.getMessage());
				
			}
					
			return mapping.getInputForward();
	
		} 

			else if (tipo.equalsIgnoreCase("enviar")){
				log.info("Entry enviar ...");
			try {
				
				session.removeAttribute("personas");
				//session.removeAttribute("codigo_oficina");
				session.removeAttribute("codigo_motivo");
				//session.removeAttribute("codigocontador");
				
				//ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
				//ls_codigo_persona   = String.valueOf(session.getAttribute("codigo_persona"));
				
				ls_codigo_oficina_user   = String.valueOf(session.getAttribute("codigo_oficina_user"));
				
				log.info("ls_codigo_oficina_user: "+ls_codigo_oficina_user);
				
				String ls_flag="";
				
				codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
				codigo_expediente = ((FFormMantDerivar)form).getCodigo_expediente();
				descripcion_tipo = ((FFormMantDerivar)form).getDescripcion_tipo();
				numero_documento = ((FFormMantDerivar)form).getNumero_documento();
				
				fecha = ((FFormMantDerivar)form).getFecha();
				/*hora = ((FFormMantDerivar)form).getHora();
				observacion = ((FFormMantDerivar)form).getObservacion();
				estado_movimiento = ((FFormMantDerivar)form).getEstado_movimiento();*/
				
				codigo_oficina = ((FFormMantDerivar)form).getCodigo_oficina();
				log.info("codigo_oficina:" +codigo_oficina);
				destinatario = ((FFormMantDerivar)form).getDestinatario();
				log.info("destinatario:" +destinatario);
				codigo_motivo = ((FFormMantDerivar)form).getCodigo_motivo();
				observacion = ((FFormMantDerivar)form).getObservacion();
				observacion = funciones.convertir_comillas_simples(observacion);
				fecha_rpta = ((FFormMantDerivar)form).getFecha_rpta();
				
				personas = ((FFormMantDerivar)form).getPersonas();
				log.info("destinatario2:" +personas);
				cbo_fecharpta = ((FFormMantDerivar)form).getCbo_fecharpta();
				chk_copia = ((FFormMantDerivar)form).getChk_copia();
				cbo_copia = ((FFormMantDerivar)form).getCbo_copia();
				
	   	        chk_copia_other = ((FFormMantDerivar)form).getChk_copia_other();
				chk_copias_other = ((FFormMantDerivar)form).getSelectedItems_other();
				
				secuencia_movimiento = ((FFormMantDerivar)form).getSecuencia_movimiento();
				codigo_inicia = ((FFormMantDerivar)form).getCodigo_inicia();
				naturaleza_documento = ((FFormMantDerivar)form).getNaturaleza_documento();
				codigo_recepcion = ((FFormMantDerivar)form).getCodigo_recepcion();
				doc_resp = ((FFormMantDerivar)form).getDoc_resp();
				doc_resp=funciones.convertir_comillas_simples(doc_resp);
				fecha_rpta_rq = ((FFormMantDerivar)form).getFecha_rpta_rq();
				
				log.info("Datos del usuario que deriva:");
				log.info("============================");
				log.info(" usuario.. "+ usuario);
				log.info(" codigo_documento.. "+ codigo_documento);
				log.info(" codigo_expediente.. "+ codigo_expediente);
				log.info(" descripcion_tipo.. "+ descripcion_tipo);
				log.info(" numero_documento.. "+ numero_documento);
				log.info(" fecha.. "+ fecha);
				log.info("Datos del destino principal de derivacion:");
				log.info("============================");
				log.info(" codigo_oficina.. "+ codigo_oficina);
				log.info(" destinatario.. "+ destinatario);
				log.info(" codigo_motivo.. "+ codigo_motivo);
				log.info(" observacion.. "+ observacion);
				log.info(" fecha_rpta.. "+ fecha_rpta);
				log.info(" chk_copia.. "+ chk_copia);
				log.info(" cbo_copia.. "+ cbo_copia);
				log.info(" codigo_recepcion.. "+ codigo_recepcion);
				log.info(" secuencia_movimiento.. "+ secuencia_movimiento);
				log.info(" doc_resp.. "+ doc_resp);
				
				log.info("============================");
				BMesaPartes BMesaPartesVO = daoIUsuarioDAO.of_obtener_padre(cnx,codigo_documento);
				
				 log.info(" codigo_documento.. "+ codigo_documento);
				 log.info("============================"); 
		        ls_documento = codigo_documento; 
		        log.info("Se saca el Codigo Padre");
		        log.info("=========================");
		        ls_cod_padre = BMesaPartesVO.getCodigo_inicia(); ////SACA EL CODIGO PADRE
		        log.info(" ls_cod_padre.. "+ ls_cod_padre);
		        ls_usuario   = String.valueOf(session.getAttribute("nombreusuario"));
		        log.info("==============================");
		        log.info("Tenemos el usuario actual > "+ls_usuario);
			log.info("Modificacion de... MantRecepcion .........." );
			
			li_retorno = daoIUsuarioDAO.parametro(cnx);
			log.info("Tenemos >");
	   	 	log.info("li_retorno" + li_retorno);
	   	 	
	   	 	/*ls_correlativorecepcion=String.valueOf(li_retorno);
	   	 	log.info("ls_correlativorecepcion" + ls_correlativorecepcion);
			*/
	   	 	log.info("Fecha de Derivacion >");
	   	 	Date fecha_recep = new Date(); // fecha y hora locales 
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
			SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
			log.info("la fecha es.."+ formatoFecha.format(fecha_recep) ); 
			log.info("la hora es.."+ formatoHora.format(fecha_recep) ); 
			log.info("la fecha_rpta es.."+ fecha_rpta); 
			
				mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correla;
   	    		session.setAttribute("mensaje",mensaje);
   	    		log.info(mensaje);
   	    		codigo_expedienteee = String.valueOf(codigo_expediente);
   	    		
   	    		
   	    		
   	    		
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
			  				
			  				subirArchivosBloque(ls_documento, rs_upload_doc_internos_temp, codigo_expedienteee, secuencia_movimiento);
			  			}   	 	  		

   	    		/**
   	    		 * FIN UPLOAD INTERNOS
   	    		 */
   	    		
   	    		
   	    	/**
   	    	 * Obteniendo el Datos de la persona remitente
   	    	 * */	
   	    			hRemitente=daoIUsuarioDAO.obtenerDatosUsuario(ls_codigo_persona);//USER ORIGEN
   	    			hDestinatario=daoIUsuarioDAO.obtenerDatosUsuario(personas);//USER DESTINO
	   				//hDestinatario.put("asunto_documento",asunto_documento);
	   				hDestinatario.put("remitente",hRemitente.get("nombre_personal"));
	   				hDestinatario.put("asunto_documento",observacion);	
	   				
	   				log.info("Datos de Correo a enviar al Destinatario");
	   				log.info("=========REMITENTE "+hDestinatario.get("remitente"));
	   				log.info("=========ASUNTO "+hDestinatario.get("asunto_documento"));
	   				
	   		//	enviarCorreoElectronico(hDestinatario,codigo_documento,sessionMail);
	   				
	  				//recuperarCorreo de destinatario.   	   				 	   				
   	   				log.info("codigo_destino_oficina_documento (DESTINATARIO):"+codigo_oficina);   
   	   				//obtenemos los codigos de las persona de la oficina destino
   	   			log.info("=========cODIGOS PERSONALES ASOCIADOS AL DESTINATARIO");
   	   				myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,codigo_oficina);
   	   				
   	   				//ponemos en una lista de string
   	   				ArrayList<String> misListaCodigosEnString= new ArrayList<String>();
   	   				for (int codigo : myListCodigos) {
   	   					misListaCodigosEnString.add(String.valueOf(codigo));
					}
   	   			log.info("=========DATOS PERSONALES ASOCIADOS AL DESTINATARIO");
   	   				//ahora obtendremos los correos   	   			
   	   				if(misListaCodigosEnString.size()>0)
   	   				{ myListaCorreosDestinatarios = daoIUsuarioDAO.obtenerDatosUsuario(misListaCodigosEnString);
   	   				} 
   	   		//		enviamos los correos	 
   	   				if(myListaCorreosDestinatarios.size()>0)   	   				  	   			
	   	   			{	
   //correo desactivado		enviarCorreoElectronico(myListaCorreosDestinatarios,codigo_documento,sessionMail,hDestinatario,IConstante.correoTO_Principal);
	   	   			}		
	   				
	   				
   	    	/*
   	    	 * Fin
   	    	 * */	
   	    		if(fecha_rpta==null){
   	    			fecha_rpta="";
   	   	 			log.info("La fecha_rpta es null de lo contrario es igual a vacio.");
   	   	    		}
   	    		
   	    		
   	    			//if(ls_documento.equals(ls_cod_padre)){
   	       	     		
   	    		log.info("=========VERIFICAMOS SI HAY COPIAS");
   	    		log.info("=========SI HAY ENVIAMOS");
 //  	    		boolean esfirmado=false;
 //  	    		if(service.esRegistroDocumentoInterno(codigo_documento)){esfirmado=true;}
   	       	     		if(naturaleza_documento.equals("I")){
   	       	     			/******************AQUI DEBERIA HACER QUE EL DOCUMENTO INTERNO SE RECIBA AUTOMATICAMENTE O PREGUNTAR SI ES UN DOC FIRMADO******/
   	       	     		log.info("Entry I ...");
   	       	     		codigo_oficina=codigo_oficina==null?"":codigo_oficina;
   	       	     		int secuencia_origen = Integer.parseInt(secuencia_movimiento);
   	       	     		
   	       	     		if(chk_copia!=null){
   	       	     			/****derivacion y recepcion de copias*************/
   	   	       	     		log.info("El chk_copia es diferente de null.." +chk_copia);
   	   	       	     		
   					   	       	     	for(int i=0; cbo_copia!=null && i<  cbo_copia.length; i++) 
   						    			{ 
   					        			log.info("El PAR_DES_LAR es diferente de null.." );	
   						    			log.info("Antes del insert Clases.." );	
   						    			//daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
   						    			log.info("las cbo_copia son :" + cbo_copia[i]);
   						    			String str_cbo_copia=cbo_copia[i];
   						    			log.info("=========cODIGOS PERSONALES ASOCIADOS A LAS OFICINAS CON COPIAS");
   						    			String ls_cod_persona=funciones.of_codigo_oficina_persona(cnx,str_cbo_copia);
   						    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
   						    			
   						    		/*	if(ls_cod_persona!=null&& ls_cod_persona!=""){
   		   					    			myListaCodigoPersonal.add(ls_cod_persona);//obteniendo la lista de codigos de usuarios
   		   					    		
   						    			}*/
   						    			
   						    			log.info("=========DATOS PERSONALES ASOCIADOS A LAS OFICINAS CON COPIAS");
   						    			myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,str_cbo_copia);   					    			
   	   					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
   	   					    			if(myListCodigos.size()>0){
   	   					    				for (int cod : myListCodigos) {
   	   					    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
   											}   					    			
   	   					    			}
   						    			
   	   					    		
   						    			if(!codigo_oficina.trim().equals(cbo_copia[i].trim())){
   						    				log.info("SI EN UNA DE LAS COPIAS ES LA MISMA OFICINA SE DERIVA CON daoIUsuarioDAO.of_registrar_derivacion_mismo");
   						    			daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,cbo_copia[i],codigo_motivo,observacion
   				   	           	    			,ls_cod_persona,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,
   				   	           	    			fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,'C',secuencia_origen);
   						    			}
   				   	       	     		
   						    			/****/
   						    			/***
   						    			 * RECEPCION DE COPIAS
   						    			 */
   						    			/*
   						    			if(esfirmado){
   						        			
   						   	   			log.info("Entre aqui yaaaaaaaaaaaaaaaaaaaaaaaa");
   						 				
   						 				
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
   						 				//int nuevasecuencia =  Integer.parseInt(secuencia_movimiento)+1;
   						 				//obtener la secuencia actualizada ls_numero_secuencia
   						 				
  						 				
   						 				daoIUsuarioDAO.of_recibir_doc(cnx,
   						 					str_cbo_copia.trim(),
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
   						 				
   						     	    	//daoIUsuarioDAO.of_modificar_fecharespuesta(cnx,codigo_oficina,codigo_documento);
   						  	    		//log.info("Despues de Limpiar la fecha de Respuesta 3" );
   						    			}//fin de recibe derivado			 	
   						    			//hDestinatario.get("c_usuario")
   						    			//String cargo_personal_recibe=funciones.of_cargo_persona(cnx, hDestinatario.get("c_usuario"));
   						    			//session.setAttribute("codigo_oficina_copia",cbo_copia[i]);
   						    			 */
   						    	/**
   						    	 * FIN DE RECEPCION		
   						    	 */
   				   	       	     	}//fin -for
   					   	       	     	
   	       	     		  
   					   	      /****fin de derivacion y recepcion de copias*****/ 	     	
   		   	       	     	}
   	       	     	log.info("=========FIN RECOPILACION DE DATOS PARA COPIAS");
   	       	     		
   	       	  if(chk_copia_other!=null){
    	     			/****derivacion y recepcion de copias*************/
	       	     		log.info("El chk_copia_other es diferente de null.." +chk_copia_other);
	       	     		
				   	       	     	for(int i=0; chk_copias_other!=null && i<  chk_copias_other.length; i++) 
					    			{ 
				        			log.info("El PAR_DES_LAR es diferente de null.." );	
					    			log.info("Antes del insert Clases.." );	
					    			//daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
					    			log.info("las cbo_copia son :" + chk_copias_other[i]);
					    			String str_cbo_copia=chk_copias_other[i];
					    			log.info("=========cODIGOS PERSONALES ASOCIADOS A LAS OFICINAS CON COPIAS");
					    			String ls_cod_persona=str_cbo_copia;
					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
					    			
					    		/*	if(ls_cod_persona!=null&& ls_cod_persona!=""){
	   					    			myListaCodigoPersonal.add(ls_cod_persona);//obteniendo la lista de codigos de usuarios
	   					    		
					    			}*/
					    			
					    			log.info("=========DATOS PERSONALES ASOCIADOS A LAS OFICINAS CON COPIAS");
					    			myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,str_cbo_copia);   					    			
					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
					    			if(myListCodigos.size()>0){
					    				for (int cod : myListCodigos) {
					    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
										}   					    			
					    			}
					    			
					    		
					    			if(!codigo_oficina.trim().equals(chk_copias_other[i].trim())){
					    				log.info("SI EN UNA DE LAS COPIAS ES LA MISMA OFICINA SE DERIVA CON daoIUsuarioDAO.of_registrar_derivacion_mismo");
					    			daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,chk_copias_other[i],codigo_motivo,observacion
			   	           	    			,ls_cod_persona,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,
			   	           	    			fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,'P',secuencia_origen);
					    			}
			   	       	     		
					    			/****/
					    			/***
					    			 * RECEPCION DE COPIAS
					    			 */
					    			/*
					    			
					    	/**
					    	 * FIN DE RECEPCION		
					    	 */
			   	       	     	}//fin -for
				   	       	     	
    	     		  
				   	      /****fin de derivacion y recepcion de copias*****/ 	     	
	   	       	     	}
   	       	     	
   	       	     	
   	       	     	
   	       	     	
   	       	     	
   	       	     		
   	  //     	     		log.info("El ls_Documento es igual a codigo padre y el documento es Interno" );	
   	           	     	/*daoIUsuarioDAO.of_registrar_derivacion_interno(cnx,ls_codigo_oficina_user,codigo_oficina,codigo_motivo,observacion
   	           	    			,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,fecha_rpta,
   	           	    			codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq);*/
   	       	     	log.info("Derivacion I of_registrar_derivacion_mismo");
   	       	     	log.info("ls_codigo_oficina_user: "+ls_codigo_oficina_user);
   	       	     	log.info("personas: "+personas);
   	       	     	log.info("codigo_oficina: "+codigo_oficina);
   	       	     	
   	       	     	log.info("DERIVACION DE DOC INTERNOS CON daoIUsuarioDAO.of_registrar_derivacion_mismo");
   	       	     				daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,codigo_oficina,codigo_motivo,observacion
  	           	    			,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,
  	           	    			fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,'P',secuencia_origen);
   	       	     		log.info("Despues de Derivacion I of_registrar_derivacion_mismo");
   	           	    	log.info("Despues del Ingreso Documento,si codigo inicial es igual a cod documento" );
   	           	    	
   	           	    	
   	           	    	/***
   	           	    	 * ****RECEPCION PRINCIPAL*****
   	           	    	 */
   	           	    	/*
   	           	    	*/
   	           	    	/***
   	           	    	 * FIN RECEPCION PRINCIPAL
   	           	    	 */
   	           	    	
   	           	    	
   	           	    	
   	           	    	log.info("Finally I ...");
   	           	    	/********************FIN GRABACION INTERNOS********************/
   	       	     		}//fin if I
   	       	     		else{
   	       	     		log.info(" INICIO DERIVACION DE DOC EXTERNO ");	
   	       	     		log.info("else I");
   	       	     	int secuencia_origen = Integer.parseInt(secuencia_movimiento);
   	       	     	
   	       	     	if(chk_copia!=null){
   	       	     		
   	       	     		log.info("El chk_copia es diferente de null.." +chk_copia);
   	       	     		
				   	       	     	for(int i=0; cbo_copia!=null && i< cbo_copia.length; i++) 
					    			{ 
				        			log.info("El PAR_DES_LAR es diferente de null.." );	
					    			log.info("Antes del insert Clases.." );	
					    			//daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
					    			log.info("las cbo_copia son :" + cbo_copia[i]);
					    			String str_cbo_copia=cbo_copia[i];
						    		String ls_cod_persona=funciones.of_codigo_oficina_persona(cnx,str_cbo_copia);
					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
					    			/*agregado por mpelaez
					    			 * fecha:25-10-2011
					    			 * */
					    			/*if(ls_cod_persona!=null&& ls_cod_persona!=""){
		   					    			myListaCodigoPersonal.add(ls_cod_persona);//obteniendo la lista de codigos de usuarios
		   					    		
						    			}*/
					    			
					    				myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,str_cbo_copia);   					    			
	   					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
	   					    			if(myListCodigos.size()>0){
	   					    				for (int cod : myListCodigos) {
	   					    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
											}   					    			
	   					    			}
					    			
					    			
					    			/*fin
					    			 * */
					    			
					    			
					    			
					    			if(!codigo_oficina.trim().equals(cbo_copia[i].trim())){
					    				log.info("SI EN UNA DE LAS COPIAS ES LA MISMA OFICINA SE DERIVA CON daoIUsuarioDAO.of_registrar_derivacion_mismo");
					    			daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,cbo_copia[i],codigo_motivo,observacion
			   	           	    			,ls_cod_persona,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,
			   	           	    			fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,'C',secuencia_origen);
			   	           	    	
					    			}
					    			session.setAttribute("codigo_oficina_copia",cbo_copia[i]);
					    			
			   	       	     		}
				   	       	     	
	   	       	     	}
   	       	     		
   	       	     		if(chk_copia_other!=null){
   	       	     		
   	       	     		log.info("El chk_copia_other es diferente de null.." +chk_copia_other);
   	       	     		
				   	       	     	for(int i=0; chk_copias_other!=null && i< chk_copias_other.length; i++) 
					    			{ 
				        			log.info("El PAR_DES_LAR es diferente de null.." );	
					    			log.info("Antes del insert Clases.." );	
					    			//daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
					    			log.info("las cbo_copia son :" + chk_copias_other[i]);
					    			String str_cbo_copia=chk_copias_other[i];
						    		String ls_cod_persona=str_cbo_copia;
					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
					    			/*agregado por mpelaez
					    			 * fecha:25-10-2011
					    			 * */
					    			/*if(ls_cod_persona!=null&& ls_cod_persona!=""){
		   					    			myListaCodigoPersonal.add(ls_cod_persona);//obteniendo la lista de codigos de usuarios
		   					    		
						    			}*/
					    			
					    				myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,str_cbo_copia);   					    			
	   					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
	   					    			if(myListCodigos.size()>0){
	   					    				for (int cod : myListCodigos) {
	   					    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
											}   					    			
	   					    			}
					    			
					    			
					    			/*fin
					    			 * */
					    			
					    			
					    			
					    			if(!codigo_oficina.trim().equals(chk_copias_other[i].trim())){
					    				log.info("SI EN UNA DE LAS COPIAS ES LA MISMA OFICINA SE DERIVA CON daoIUsuarioDAO.of_registrar_derivacion_mismo");
					    			daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,codigo_oficina,codigo_motivo,observacion
			   	           	    			,ls_cod_persona,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,
			   	           	    			fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,'P',secuencia_origen);
			   	           	    	
					    			}
					    			session.setAttribute("codigo_oficina_copia",codigo_oficina);
					    			
			   	       	     		}
				   	       	     	
	   	       	     	}
	   	       	     	
	   	       	     	log.info("NO HAY COPIAS El chk_copia es == null" );
	       	     		log.info("El ls_Documento es igual a codigo padre y el documento es de Entrada" );
	       	     		
	       	     		log.info("CODIGO OFICINA ORIGEN El ls_oficina es YYYYYYY..." + ls_oficina );
	       	     		log.info("CODIGO OFICINA DESTINOEl codigo_oficina es OOOOOOO ..." + codigo_oficina );
	       	     		
	       	     		if(ls_oficina.equals(codigo_oficina) ){
	       	     		ls_flag="I";
	       	     		log.info("Si las oficinas son iguales CCCCCCCC XXXXX.." );
	       	     		
			       	     	if(ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")){
			       	  	    	
			       	  	    	log.info("SI SON SECREATRIAS O JEFES Si el cargo de personal es igual 3 o 10 ......");
			       	  	    	log.info("Si las oficinas son IGUALES y el cargo es 3 o 10 DERIVO CON of_registrar_derivacion_mismo---flag");
			       	  	   
			       	  	   		daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,codigo_oficina,codigo_motivo,observacion
    		    	           	,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,ls_flag,secuencia_origen,0);
			    					
		    				}else{
		    					
		    					log.info("Si el cargo de personal es diferente 3 o 10 ......");
		    					log.info("Si las oficinas son diferentes y el cargo es diferente a 3 o 10 DERIVO CON of_registrar_derivacion_mismo---secuencia");
			       	     		daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,codigo_oficina,codigo_motivo,observacion
					           	    	,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,'P',secuencia_origen);
		    						    						
		    				}

	       	     
	       	     		
	       	     		}else{
	       	     		
	       	     		log.info("Si las oficinas son diferentes SE DERIVA CON of_registrar_derivacion_mismo");
	       	     		
	       	     		daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,codigo_oficina,codigo_motivo,observacion
	           	    	,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,'P',secuencia_origen);
	       	     		
	       	     		}
	       	     	
	       	     /*daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_oficina,codigo_oficina,codigo_motivo,observacion
		           	    	,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_cod_padre,li_retorno,fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq);*/	           	     	
	           	    	
	           	    	//log.info("Despues del Ingreso Documento,si codigo inicial es igual a cod documento" );	
	           	    	log.info(" FIN DERIVACION DE DOC EXTERNO ");		
   	       	     		} //fin else I
   	       	     	
   	       	     		
   	       	     		/**
   	       	     		 * SUBIR DOCUMENTOS
   	       	     		 */
   	       	     			subirArchivosBloque(ls_documento,session,String.valueOf(codigo_expediente),secuencia_movimiento);
   	       	     		   	       	     		
   	       	     		/**
   	       	     		 * FIN SUBIR DOCUMENTOS
   	       	     		 */
   	       	     		
   	       	     		
   	       	     	/**
   	       	     	 * codigo que a partir de myListaCodigoPersonal envia correos con copia, ya sea
   	       	     	 * documento Interno o Externo
   	       	     	 * */
   	       	     		log.info("tamanio listaCodigoPersonal: "+myListaCodigoPersonal.size());
	       	     		if(myListaCodigoPersonal.size()>0){
	       	     			ArrayList<String> myListaCorreos = daoIUsuarioDAO.obtenerDatosUsuario(myListaCodigoPersonal);
	       	     			log.info("tamanio listacorreos: "+myListaCorreos.size());
	       	     			// con la lista ahora enviamos los correos respectivos:	       	     			
	       	     		//	enviarCorreoElectronico(myListaCorreos,codigo_documento,sessionMail,hDestinatario, IConstante.correoCC_Copias);
	       	     		}
   	       	     	/**
   	       	     	 * Fin del codigo para enviar correos 
   	       	     	 **/
   	       	    	log.info("Despues del Ingreso Documento y Direccionando,si codigo inicial es diferente a cod documento" );	
   	       	    	
   	       	    	
   	       	    	
   	  	    	Collection rs_derivacion_doc = new ArrayList();
   	  	    	Collection rs_observados_doc = new ArrayList();   
   	  	    	
		   	  	String accesoaoficinas=service.getAccesosaOficinas(ls_codigo_persona);
		   	  	if(accesoaoficinas != null){
		   	  		accesoaoficinas=ls_oficina+","+accesoaoficinas;
		   	  	}else{
		   	  		accesoaoficinas=ls_oficina;
		   	  	}
   	  	
   	  	    	if(ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")){
   	  	    	
	   	  	    	log.info("Si el cargo de personal es igual a  3 o 10 ......");
					rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_codigo_oficina_user,accesoaoficinas,String.valueOf(request.getAttribute("ls_inicia")),valor);
					//observacion_documento
					rs_observados_doc = daoIUsuarioDAO.lista_observacion_documentos(cnx,ls_oficina,String.valueOf(request.getAttribute("ls_inicia")));
   	  	    		
				}else{
					
					log.info("Si el cargo de personal no es 3 o 10 ......");
					rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_codigo_oficina_user,accesoaoficinas,ls_codigo_persona,String.valueOf(request.getAttribute("ls_inicia")),valor);
						
				}
   	  	    	
   					//log.info("el rs_derivacion_doc es ......"+ rs_derivacion_doc);
   					
   	  	    	/***************************Actualizando la Cabecera Upload Ficheros************************/
   			      
   			 //ELIMINADO POR YA EXUSTE SECUENCIA	int parameter_upload=funciones.parametroDocumentosUpload();
   			 //log.info("Parameter dentro de MANT Derivar Documento parameter_upload.."+parameter_upload );
   					
   					//IResponsableColaboradoresDAO daoIResponsableColaboradoresDAO = new SqlResponsableColaboradoresDAO();
   			 	log.info("El codigo_documento TTTT.."+codigo_documento );
   			 	log.info("El codigo_expediente TTTT.."+codigo_expediente );
   			 	log.info("El usuario TTTT.."+usuario );
   			 	
   			 	log.info("ACTUALIZANDO LA CABECERA UPLOADING POR LA DERIVACION CON DerivarDocumentoCabUploadIng");
   			     //daoIUsuarioDAO.DerivarDocumentoCabUploadIng(parameter_upload,Integer.parseInt(codigo_documento.trim()),codigo_expediente,Integer.parseInt(secuencia_movimiento .trim()) ,usuario,li_retorno);
   			 	
   			 	/**
   			 	 * SI SE HA CREADO LA CABECERA DETALLE ENTONCES SE CREA LA CABECERA
   			 	 */
   			 	       	    	
       	    	if(daoIUsuarioDAO.ValidaListaDocEntradaDetUpload(codigo_documento,secuencia_movimiento)>0){
       	    		daoIUsuarioDAO.DerivarDocumentoCabUploadIng(ls_codigo_oficina_user,0,Integer.parseInt(codigo_documento.trim()),codigo_expediente,Integer.parseInt(secuencia_movimiento .trim()) ,usuario,li_retorno);
       	    	}
   			 	   			 	
   			    /***************************Fin Actualizando la Cabecera Upload Ficheros************************/
   			  
   			     
   					mensaje_recepcion ="DOCUMENTO DERIVADO CON EXITO:";
   					
   					String mensaje_numreg ="N° Registro:"+ codigo_documento;
   					String mensaje_numexp ="N° Expediente:"+ codigo_expediente;
   					String mensaje_numenv ="N° Envio:"+ li_retorno;
					log.info("El mensaje_recepcion es.." + mensaje_recepcion);
					
   					session.setAttribute("operacion",tipo);
   					session.setAttribute("rs_derivacion_doc",rs_derivacion_doc);
   					session.setAttribute("rs_observados_doc",rs_observados_doc);
   					//session.setAttribute("correlativorecepcion",ls_correla_conv);
   					session.setAttribute("mensaje",mensaje);
   					session.setAttribute("mensaje_recepcion",mensaje_recepcion);
   					
   					session.setAttribute("mensaje_numreg",mensaje_numreg);
   					session.setAttribute("mensaje_numexp",mensaje_numexp);
   					session.setAttribute("mensaje_numenv",mensaje_numenv);
   					
   					
   					session.setAttribute("codigo_recepcion_env",codigo_recepcion);
   					session.setAttribute("codigo_documento_env",codigo_documento);
   					session.setAttribute("codigo_expediente_env",codigo_expediente);
   					//session.setAttribute("numero_env",li_retorno);
   					session.setAttribute("numerodocumento_env",ls_documento);
   					session.setAttribute("fecha_env",fecha);
   					session.setAttribute("codigo_motivo_env",codigo_motivo);
   					session.setAttribute("codigo_oficina_env",codigo_oficina);
   					session.setAttribute("observacion_env",observacion);
   					session.setAttribute("hojaenvio","H");
   					session.setAttribute("codigocontador","0");
   					session.removeAttribute("rs_upload_doc_internos");
   					
   					session.setAttribute("listaFrameBusquedaTemp",null);
   					
			}catch (SQLException e) {
				e.printStackTrace();
			//	closeConnection(cnx);
				//return mapping.getInputForward();
	
			}
			

		} else if (tipo.equalsIgnoreCase("enviardocumentointerno")){
			log.info("Entry enviardocumentointerno ...");
		try {
			/*****TENGO QUE RECUPERAR LOS DATOS SOLICITADOS EN EL FORMULARIO REGISTRO*****/
        	/*****DESDE EL DOCUMENTO INTERNO *****/
        	Collection listaDeArchivosAdjuntos = new ArrayList();
        	listaDeArchivosAdjuntos = (Collection) session.getAttribute("listaDeArchivosAdjuntos");
        	
        	String[] chk_copiasz=null;
        	String[] chk;
        	
			String observacion_ls = (String)session.getAttribute("observacion");
			String pagina = (String)session.getAttribute("pagina");
			String firmaManual = (String)session.getAttribute("firmaManual");
			
			String tipo_envio_ls = String.valueOf(session.getAttribute("tipoenv"));
			observacion_ls=(observacion_ls==null)?"":observacion_ls;
			pagina=(pagina==null)?"":pagina;
			tipo_envio_ls=(tipo_envio_ls==null)?"":tipo_envio_ls;
	  		System.out.println("------------- tipo de envio > "+tipo_envio_ls);
        	
        	int  codigo_doc_para_grabar=Integer.parseInt((String)session.getAttribute("codigo_doc_para_grabar"));
        	String cdnb = (String) session.getAttribute("codigo_persona_noborrar");
        	BUsuario userSystem =(BUsuario) session.getAttribute("nombre_usuario");
	  		if(userSystem==null){
	  			userSystem = new BUsuario();
	  		}
	  		
	  		
	  		log.info("usuario_actual >>> "+usuario);
	  		userSystem.setC_usuario(usuario);
	  		userSystem=service.obtenerResponsabilidad(userSystem);
	  		log.info("es reponsable:"+userSystem.getResponsable());

	  		/***FIN****/
	  		
	  		DocumentoInternoBean documento_interno_deriva = service.getDocumentoInterno(codigo_doc_para_grabar,"graba");
	  		
	  		codigo_documento = String.valueOf(documento_interno_deriva.getCodigo_documento());
			secuencia_movimiento = String.valueOf(documento_interno_deriva.getSecuencia_movimiento());
			
			FFormMantDerivar formularioDerivar=(FFormMantDerivar)daoIUsuarioDAO.obtenerDetalleBandejaDerivacion(cnx, codigo_documento,secuencia_movimiento,secuencia_movimiento,"N");
	  		
        	
        //	request.setAttribute("FFormMantDerivar", formulario);
			session.removeAttribute("personas");
			session.removeAttribute("codigo_motivo");
						
			ls_codigo_oficina_user   = String.valueOf(session.getAttribute("codigo_oficina_user"));
			log.info("ls_codigo_oficina_user: "+ls_codigo_oficina_user);
			
			String ls_flag="";
			
			
	  		//session.setAttribute("ls_codigo_recepcion", codigo_recepcion);
			codigo_expediente = formularioDerivar.getCodigo_expediente(); 
			descripcion_tipo = formularioDerivar.getDescripcion_tipo();
			numero_documento = formularioDerivar.getNumero_documento();
			
			fecha = ((FFormMantDerivar)form).getFecha(); //fecha actual
				
			codigo_oficina = String.valueOf(documento_interno_deriva.getCodigo_oficina()); 
			log.info("codigo_oficina_destino:" +codigo_oficina);
			destinatario = String.valueOf(documento_interno_deriva.getPersonas());
			codigo_motivo = tipo_envio_ls;//pedir
			observacion = observacion_ls;//pedir
			observacion = funciones.convertir_comillas_simples(observacion);
			fecha_rpta = "";//pedir
			
			//personas = ((FFormMantDerivar)form).getPersonas();//rescatardestinatario
			personas = destinatario;
			cbo_fecharpta = ((FFormMantDerivar)form).getCbo_fecharpta();
			
			chk_copia = null;
			cbo_copia = null;
			if(documento_interno_deriva.getCodigos_oficinas_destinos_copias()!=null && documento_interno_deriva.getCodigos_oficinas_destinos_copias().length()>0)
	  		{
				chk_copia = "1";
				cbo_copia = (documento_interno_deriva.getCodigos_oficinas_destinos_copias().split(","));
				
	  		}
			
			secuencia_movimiento = formularioDerivar.getSecuencia_movimiento(); 
			codigo_inicia = formularioDerivar.getCodigo_inicia(); 
			log.info("codigo_inicia >"+codigo_inicia);
			naturaleza_documento = formularioDerivar.getNaturaleza_documento();
			log.info("naturaleza_documento >"+naturaleza_documento);
			codigo_recepcion = formularioDerivar.getCodigo_recepcion(); 
			doc_resp = ""; //no necesito
			doc_resp=funciones.convertir_comillas_simples(doc_resp);
			fecha_rpta_rq = ((FFormMantDerivar)form).getFecha_rpta_rq();//pedir
			fecha_rpta_rq = null;
			
			log.info("Datos del usuario que deriva:");
			log.info("============================");
			log.info(" usuario.. "+ usuario);
			log.info(" codigo_documento.. "+ codigo_documento);
			log.info(" codigo_expediente.. "+ codigo_expediente);
			log.info(" descripcion_tipo.. "+ descripcion_tipo);
			log.info(" numero_documento.. "+ numero_documento);
			log.info(" fecha.. "+ fecha);
			
			log.info("Datos del destino principal de derivacion:");
			log.info("============================");
			log.info(" codigo_oficina.. "+ codigo_oficina);
			log.info(" destinatario.. "+ destinatario);
			log.info(" codigo_motivo.. "+ codigo_motivo);
			log.info(" observacion.. "+ observacion);
			log.info(" fecha_rpta.. "+ fecha_rpta);
			log.info(" chk_copia.. "+ chk_copia);
			log.info(" cbo_copia.. "+ cbo_copia);
			log.info(" codigo_recepcion.. "+ codigo_recepcion);
			log.info(" secuencia_movimiento.. "+ secuencia_movimiento);
			log.info(" doc_resp.. "+ doc_resp);
			
			log.info("============================");
			BMesaPartes BMesaPartesVO = daoIUsuarioDAO.of_obtener_padre(cnx,codigo_documento);
			
			 log.info(" codigo_documento.. "+ codigo_documento);
			 log.info("============================"); 
	        ls_documento = codigo_documento; 
	        log.info("Se saca el Codigo Padre");
	        log.info("=========================");
	        ls_cod_padre = BMesaPartesVO.getCodigo_inicia(); ////SACA EL CODIGO PADRE
	        log.info(" ls_cod_padre.. "+ ls_cod_padre);
	        ls_usuario   = usuario; //String.valueOf(session.getAttribute("nombreusuario"));
	        log.info("==============================");
	        log.info("Tenemos el usuario actual > "+ls_usuario);
	        
		log.info("INICIO de DERIVACION FIRMADO ......" );
		
		li_retorno = daoIUsuarioDAO.parametro(cnx);
		log.info("Tenemos > li_retorno >" + li_retorno);

   	 	Date fecha_recep = new Date(); // fecha y hora locales 
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
		log.info("la fecha es.."+ formatoFecha.format(fecha_recep) ); 
		log.info("la hora es.."+ formatoHora.format(fecha_recep) ); 
		log.info("la fecha_rpta es.."+ fecha_rpta); 
		
			mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correla;
	    		session.setAttribute("mensaje",mensaje);
	    		log.info(mensaje);
	    		codigo_expedienteee = String.valueOf(codigo_expediente);
	    		/**
	    		 * INICIO DE UPLOAD INTERNOS
	    		 */
	    		log.info(">>>>>>>>>>>>>ADJUNTA DOCUMENTOS INTERNOS FIRMADOS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	
	    		Collection listaDeArchivosAdjuntosx = new ArrayList();
	    		    listaDeArchivosAdjuntosx = service.getListaArchivosAdjuntos(codigo_doc_para_grabar);
					BArchivo archivoenlista = new BArchivo();
	  			  	archivoenlista.setNombre_archivo(documento_interno_deriva.getNombre_arhivo());
	  			  	archivoenlista.setNombre_archivo_cifrado(documento_interno_deriva.getNombre_arhivo());
	  			  	archivoenlista.setIsfirmado(true);
	  			  	archivoenlista.setRuta(filePathFirmados);
	  			  	archivoenlista.setUsuario(usuario);
	  			  	
	  			  if(((firmaManual==null)? "":firmaManual).equals("1")){
			  			BInfo binfo = new BInfo();
			  			binfo = service.getInfoSobreDocumentoFirmadoManualmente(codigo_doc_para_grabar);
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
		  		
		  		subirArchivosBloque(ls_documento, listaDeArchivosAdjuntosx, codigo_expedienteee, secuencia_movimiento);
		  		
	    		/**
	    		 * FIN UPLOAD INTERNOS
	    		 */
	    		
	    		
	    	/**
	    	 * Obteniendo el Datos de la persona remitente
	    	 * */	
	    		hRemitente=daoIUsuarioDAO.obtenerDatosUsuario(ls_codigo_persona);//USER ORIGEN
	    		hDestinatario=daoIUsuarioDAO.obtenerDatosUsuario(personas);//USER DESTINO
   				//hDestinatario.put("asunto_documento",asunto_documento);
   				hDestinatario.put("remitente",hRemitente.get("nombre_personal"));
   				hDestinatario.put("asunto_documento",observacion);	
   				
   				log.info("Datos de Correo a enviar al Destinatario");
   				log.info("=========REMITENTE "+hDestinatario.get("remitente"));
   				log.info("=========ASUNTO "+hDestinatario.get("asunto_documento"));
   				
   		//deactivado	enviarCorreoElectronico(hDestinatario,codigo_documento,sessionMail);
   				
  				//recuperarCorreo de destinatario.   	   				 	   				
	   			log.info("codigo_destino_oficina_documento (DESTINATARIO):"+codigo_oficina);   
	   			//obtenemos los codigos de las persona de la oficina destino
	   			log.info("=========cODIGOS PERSONALES ASOCIADOS AL DESTINATARIO");
	   				myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,codigo_oficina);
	   				
	   				//ponemos en una lista de string
	   				ArrayList<String> misListaCodigosEnString= new ArrayList<String>();
	   				for (int codigo : myListCodigos) {
	   					misListaCodigosEnString.add(String.valueOf(codigo));
				}
	   			log.info("=========DATOS PERSONALES ASOCIADOS AL DESTINATARIO");
	   				//ahora obtendremos los correos   	   			
	   				if(misListaCodigosEnString.size()>0)
	   				{ myListaCorreosDestinatarios = daoIUsuarioDAO.obtenerDatosUsuario(misListaCodigosEnString);
	   				} 
	   		//		enviamos los correos	 
	   				if(myListaCorreosDestinatarios.size()>0)   	   				  	   			
   	   			{	
//correo desactivado		enviarCorreoElectronico(myListaCorreosDestinatarios,codigo_documento,sessionMail,hDestinatario,IConstante.correoTO_Principal);
   	   			}		
   				
   				
	    	/*
	    	 * Fin
	    	 * */	
	   				
	   			fecha_rpta = (fecha_rpta==null)? "":fecha_rpta;
	    		fecha_rpta_rq = (fecha_rpta_rq==null)? "":fecha_rpta_rq;
	    		
	    			//if(ls_documento.equals(ls_cod_padre)){
	       	     		
	    		log.info("=========VERIFICAMOS SI HAY COPIAS");
	    		log.info("=========SI HAY ENVIAMOS");
//  	    		boolean esfirmado=false;
//  	    		if(service.esRegistroDocumentoInterno(codigo_documento)){esfirmado=true;}
	       	     		if(naturaleza_documento.equals("I")){
	       	     			/******************AQUI DEBERIA HACER QUE EL DOCUMENTO INTERNO SE RECIBA AUTOMATICAMENTE O PREGUNTAR SI ES UN DOC FIRMADO******/
	       	     		log.info("Entry I ...");
	       	     		codigo_oficina=codigo_oficina==null?"":codigo_oficina;
	       	     		int secuencia_origen = Integer.parseInt(secuencia_movimiento);
	       	     	
	       	     		if(chk_copia!=null){
	       	     			/****derivacion y recepcion de copias*************/
	   	       	     		log.info("El chk_copia es diferente de null.." +chk_copia);
	   	       	     		
					   	       	     	for(int i=0; cbo_copia!=null && i<  cbo_copia.length; i++) 
						    			{ 
					        			log.info("El PAR_DES_LAR es diferente de null.." );	
						    			log.info("Antes del insert Clases.." );	
						    			//daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
						    			log.info("las cbo_copia son :" + cbo_copia[i]);
						    			String str_cbo_copia=cbo_copia[i];
						    			log.info("=========cODIGOS PERSONALES ASOCIADOS A LAS OFICINAS CON COPIAS");
						    			String ls_cod_persona=funciones.of_codigo_oficina_persona(cnx,str_cbo_copia);
						    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
						    			
						    		/*	if(ls_cod_persona!=null&& ls_cod_persona!=""){
		   					    			myListaCodigoPersonal.add(ls_cod_persona);//obteniendo la lista de codigos de usuarios
		   					    		
						    			}*/
						    			
						    			log.info("=========DATOS PERSONALES ASOCIADOS A LAS OFICINAS CON COPIAS");
						    			myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,str_cbo_copia);   					    			
	   					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
	   					    			if(myListCodigos.size()>0){
	   					    				for (int cod : myListCodigos) {
	   					    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
											}   					    			
	   					    			}
						    			
	   					    		
						    			if(!codigo_oficina.trim().equals(cbo_copia[i].trim())){
						    				log.info("SI NO ES LA MISMA OFICINA SE DERIVA CON daoIUsuarioDAO.of_registrar_derivacion_mismo");
						    			daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,cbo_copia[i],codigo_motivo,observacion
				   	           	    			,ls_cod_persona,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,
				   	           	    			fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,'C',secuencia_origen);
						    			}
				   	       	     		
						    			/****/
						    			/***
						    			 * RECEPCION DE COPIAS
						    			 */
						    			/*
						    			if(esfirmado){
						        			
						   	   			log.info("Entre aqui yaaaaaaaaaaaaaaaaaaaaaaaa");
						 				
						 				
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
						 				//int nuevasecuencia =  Integer.parseInt(secuencia_movimiento)+1;
						 				//obtener la secuencia actualizada ls_numero_secuencia
						 				
						 				
						 				daoIUsuarioDAO.of_recibir_doc(cnx,
						 					str_cbo_copia.trim(),
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
						 				
						     	    	//daoIUsuarioDAO.of_modificar_fecharespuesta(cnx,codigo_oficina,codigo_documento);
						  	    		//log.info("Despues de Limpiar la fecha de Respuesta 3" );
						    			}//fin de recibe derivado			 	
						    			//hDestinatario.get("c_usuario")
						    			//String cargo_personal_recibe=funciones.of_cargo_persona(cnx, hDestinatario.get("c_usuario"));
						    			//session.setAttribute("codigo_oficina_copia",cbo_copia[i]);
						    			 */
						    	/**
						    	 * FIN DE RECEPCION		
						    	 */
				   	       	     	}//fin -for
					   	       	     	
	       	     		  
					   	      /****fin de derivacion y recepcion de copias*****/ 	     	
		   	       	     	}
	       	     	log.info("=========FIN RECOPILACION DE DATOS PARA COPIAS");
	       	     		
     		
	       	     		
	  //     	     		log.info("El ls_Documento es igual a codigo padre y el documento es Interno" );	
	           	     	/*daoIUsuarioDAO.of_registrar_derivacion_interno(cnx,ls_codigo_oficina_user,codigo_oficina,codigo_motivo,observacion
	           	    			,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,fecha_rpta,
	           	    			codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq);*/
	       	     	log.info("Derivacion I of_registrar_derivacion_mismo");
	       	     	log.info("ls_codigo_oficina_user: "+ls_codigo_oficina_user);
	       	     	log.info("personas: "+personas);
	       	     	log.info("codigo_oficina: "+codigo_oficina);
	       	     	
	       	     	
	       	     	
	       	     	log.info("DERIVACION DE DOC INTERNOS CON daoIUsuarioDAO.of_registrar_derivacion_mismo");
     				daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,codigo_oficina,codigo_motivo,observacion
   	    			,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,
   	    			fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,'P',secuencia_origen);
	       	     	
	       	     	log.info("Despues de Derivacion I of_registrar_derivacion_mismo");
	           	    log.info("Despues del Ingreso Documento,si codigo inicial es igual a cod documento" );
	           	    	
	           	    	
	           	    	
	           	    	
	           	    	/***
	           	    	 * ****RECEPCION PRINCIPAL*****
	           	    	 */
	           	    	/*
	           	    	if(esfirmado){
						        			
						   	   			log.info("Entre aqui yaaaaaaaaaaaaaaaaaaaaaaaa");
						 				
						 				
						 				Date fechaz = new Date(); // fecha y hora locales 
						 	    		SimpleDateFormat fechaRecepcion = new SimpleDateFormat("dd/MM/yyyy"); 
						 	    		SimpleDateFormat horaRecepcion = new SimpleDateFormat("HH:mm"); 
						 	    		log.info("la fecha es.."+ fechaRecepcion.format(fechaz) ); 
						 	    		log.info("la hora es.."+ horaRecepcion.format(fechaz) ); 
						 				String ls_fechaRecepcion=formatoFecha.format(fechaz);
						 				String ls_horaRecepcion=formatoHora.format(fechaz);
						 				
						 				String usuario_destino = funciones.of_usuario_destino(cnx, personas);
						 				String cargo_personal_recibe=funciones.of_cargo_persona(cnx, usuario_destino);
						 				String secuenciaQsigue = funciones.of_numero_secuencia(cnx,String.valueOf(li_retorno));
						 				String estado_movimiento_registro="2";
						 				//int nuevasecuencia =  Integer.parseInt(secuencia_movimiento)+1;
						 				//obtener la secuencia actualizada ls_numero_secuencia
						 				
						 				
						 				daoIUsuarioDAO.of_recibir_doc(cnx,
						 					codigo_oficina,
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
						 				
						     	    	//daoIUsuarioDAO.of_modificar_fecharespuesta(cnx,codigo_oficina,codigo_documento);
						  	    		//log.info("Despues de Limpiar la fecha de Respuesta 3" );
						    			}//fin de recibe derivado	
						    			*/
	           	    	/***
	           	    	 * FIN RECEPCION PRINCIPAL
	           	    	 */
	           	    	
	           	    	
	           	    	
	           	    	log.info("Finally I ...");
	           	    	/********************FIN GRABACION INTERNOS********************/
	       	     		}//fin if I
	       	     		else{
	       	     		log.info(" INICIO DERIVACION DE DOC EXTERNO ");	
	       	     		log.info("else I");
	       	     		int secuencia_origen = Integer.parseInt(secuencia_movimiento);
	       	     		if(chk_copia!=null){
	       	     		
	       	     		log.info("El chk_copia es diferente de null.." +chk_copia);
	       	     		
			   	       	     	for(int i=0; cbo_copia!=null && i< cbo_copia.length; i++) 
				    			{ 
			        			log.info("El PAR_DES_LAR es diferente de null.." );	
				    			log.info("Antes del insert Clases.." );	
				    			//daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
				    			log.info("las cbo_copia son :" + cbo_copia[i]);
				    			String str_cbo_copia=cbo_copia[i];
					    		String ls_cod_persona=funciones.of_codigo_oficina_persona(cnx,str_cbo_copia);
				    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
				    			/*agregado por mpelaez
				    			 * fecha:25-10-2011
				    			 * */
				    			/*if(ls_cod_persona!=null&& ls_cod_persona!=""){
	   					    			myListaCodigoPersonal.add(ls_cod_persona);//obteniendo la lista de codigos de usuarios
	   					    		
					    			}*/
				    			
				    				myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,str_cbo_copia);   					    			
   					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
   					    			if(myListCodigos.size()>0){
   					    				for (int cod : myListCodigos) {
   					    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
										}   					    			
   					    			}
				    			
				    			
				    			/*fin
				    			 * */
				    			
				    			
				    			
				    			if(!codigo_oficina.trim().equals(cbo_copia[i].trim())){
				    				log.info("SI EN UNA DE LAS COPIAS ES LA MISMA OFICINA SE DERIVA CON daoIUsuarioDAO.of_registrar_derivacion_mismo");
				    			daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,cbo_copia[i],codigo_motivo,observacion
		   	           	    			,ls_cod_persona,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,
		   	           	    			fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,'C',secuencia_origen);
		   	           	    	
				    			}
				    			session.setAttribute("codigo_oficina_copia",cbo_copia[i]);
				    			
		   	       	     		}
			   	       	     	
   	       	     	}
	       	     		
	       	     		
   	       	     	
   	       	     	log.info("NO HAY COPIAS El chk_copia es == null" );
       	     		log.info("El ls_Documento es igual a codigo padre y el documento es de Entrada" );
       	     		
       	     		log.info("CODIGO OFICINA ORIGEN El ls_oficina es YYYYYYY..." + ls_oficina );
       	     		log.info("CODIGO OFICINA DESTINOEl codigo_oficina es OOOOOOO ..." + codigo_oficina );
       	     		
       	     		if(ls_oficina.equals(codigo_oficina) ){
	       	     		log.info("SI SON IGUALES");
	       	     		ls_flag="I";
	       	     		log.info("Si las oficinas son iguales CCCCCCCC XXXXX.." );
	       	     		
			       	     	if(ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")){
			       	  	    	
			       	  	    	log.info("SI SON SECRETARIAS O JEFES Si el cargo de personal es igual 3 o 10 ......");
			       	  	    	log.info("Si las oficinas son IGUALES y el cargo es 3 o 10 DERIVO CON of_registrar_derivacion_mismo---flag");
			       	  	   
			       	  	   		daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,codigo_oficina,codigo_motivo,observacion
			    	           	,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,ls_flag,secuencia_origen,0);
			    					
		    				}else{
		    					
		    					log.info("Si el cargo de personal es diferente 3 o 10 ......");
		    					log.info("Si las oficinas son IGUALES y el cargo es diferente a 3 o 10 DERIVO CON of_registrar_derivacion_mismo---secuencia");
			       	     		daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,codigo_oficina,codigo_motivo,observacion
					           	    	,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,'P',secuencia_origen);
		    						    						
		    				}

       	     		}else{
       	     		
       	     		log.info("Si las oficinas son diferentes SE DERIVA CON of_registrar_derivacion_mismo");
       	     		
       	     		daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_codigo_oficina_user,codigo_oficina,codigo_motivo,observacion
           	    	,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_documento,li_retorno,fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq,'P',secuencia_origen);
       	     		
       	     		}
       	     
       	     /*daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_oficina,codigo_oficina,codigo_motivo,observacion
	           	    	,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_cod_padre,li_retorno,fecha_rpta,codigo_expediente,ls_descripcion_oficina,codigo_recepcion,doc_resp,fecha_rpta_rq);*/	           	     	
           	    	
           	    	//log.info("Despues del Ingreso Documento,si codigo inicial es igual a cod documento" );	
           	    	log.info(" FIN DERIVACION DE DOC EXTERNO ");		
	       	     		} //fin else I
	       	     	
	       	     	String maxima_secuencia = funciones.of_max_secuencia_documento(cnx, codigo_documento);
	       	     		
	       	     	/**
	       	     	 * codigo que a partir de myListaCodigoPersonal envia correos con copia, ya sea
	       	     	 * documento Interno o Externo
	       	     	 * */
	       	     		log.info("tamanio listaCodigoPersonal: "+myListaCodigoPersonal.size());
       	     		if(myListaCodigoPersonal.size()>0){
       	     			ArrayList<String> myListaCorreos = daoIUsuarioDAO.obtenerDatosUsuario(myListaCodigoPersonal);
       	     			log.info("tamanio listacorreos: "+myListaCorreos.size());
       	     			// con la lista ahora enviamos los correos respectivos:	       	     			
//correo desactivado  			enviarCorreoElectronico(myListaCorreos,codigo_documento,sessionMail,hDestinatario, IConstante.correoCC_Copias);
       	     		}
	       	     	/**
	       	     	 * Fin del codigo para enviar correos 
	       	     	 **/
	       	    	log.info("Despues del Ingreso Documento y Direccionando,si codigo inicial es diferente a cod documento" );	
	       	    	
	       	    	
	       	    	
	  	    		Collection rs_derivacion_doc = new ArrayList();
	  	    		Collection rs_observados_doc = new ArrayList();
			        
	  	    		String accesoaoficinas=service.getAccesosaOficinas(ls_codigo_persona);
	  	    		if(accesoaoficinas != null){
	  	    			accesoaoficinas=ls_oficina+","+accesoaoficinas;
	  	    		}else{
	  	    			accesoaoficinas=ls_oficina;
	  	    		}
	  	    		
	  	    	if(ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")){
	  	    	
		  	    	log.info("Si el cargo de personal es igual a 3 o 10 ......");
		  	    	rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_codigo_oficina_user,accesoaoficinas,String.valueOf(request.getAttribute("ls_inicia")),valor);
		  	    	//observacion_documento
		  	    	rs_observados_doc = daoIUsuarioDAO.lista_observacion_documentos(cnx,ls_oficina,String.valueOf(request.getAttribute("ls_inicia")));
	  	    		
				}else{
					
					log.info("Si el cargo de personal es 3 o 10 ......");
					rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_codigo_oficina_user,accesoaoficinas,ls_codigo_persona,String.valueOf(request.getAttribute("ls_inicia")),valor);
						
				}
	  	    	
					//log.info("el rs_derivacion_doc es ......"+ rs_derivacion_doc);
					
					/***************************Actualizando la Cabecera Upload Ficheros************************/
			      
	//ELIMINADO POR SECUENCIA		       	int parameter_upload=funciones.parametroDocumentosUpload();
	//		      	log.info("Parameter dentro de MANT Derivar Documento parameter_upload.."+parameter_upload );
					
					//IResponsableColaboradoresDAO daoIResponsableColaboradoresDAO = new SqlResponsableColaboradoresDAO();
			 	log.info("El codigo_documento TTTT.."+codigo_documento );
			 	log.info("El codigo_expediente TTTT.."+codigo_expediente );
			 	log.info("El usuario TTTT.."+usuario );
			 	
			 	log.info("ACTUALIZANDO LA CABECERA UPLOADING POR LA DERIVACION CON DerivarDocumentoCabUploadIng");
			     daoIUsuarioDAO.DerivarDocumentoCabUploadIng(ls_codigo_oficina_user,0,Integer.parseInt(codigo_documento.trim()),codigo_expediente,Integer.parseInt(secuencia_movimiento .trim()) ,usuario,li_retorno);

			     /***************************Fin Actualizando la Cabecera Upload Ficheros************************/
			  
			     /***
			      * update tabla 
			      */
			    
			     service.modificaTablaDocumentosInternos(codigo_doc_para_grabar,documento_interno_deriva.getCodigo_documento(),Integer.parseInt(maxima_secuencia.trim()),"D"); 
			     
			     
			     /**
			      * fin
			      */
					mensaje_recepcion =Constantes.DocumentoFirmadoExito.getNombre(); //"Documento Firmado con Exito!!!";
					
					String mensaje_numreg ="N° de Registro: "+ codigo_documento;
					String mensaje_numexp ="N° de Expediente: "+ codigo_expediente;
					//String mensaje_numenv ="N° Envio: "+ li_retorno;
					log.info("El mensaje_recepcion es.." + mensaje_recepcion);
				
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_derivacion_doc",rs_derivacion_doc);
					session.setAttribute("rs_observados_doc",rs_observados_doc);
					//session.setAttribute("correlativorecepcion",ls_correla_conv);
					session.setAttribute("mensaje",mensaje);
					
					
					
					//session.setAttribute("titulo", "Mensaje de Derivacion Doc.");
					session.setAttribute("mensaje",mensaje_recepcion);
					session.setAttribute("mensaje_reg",mensaje_numreg);
					session.setAttribute("mensaje_exp",mensaje_numexp);
					
					
	       	    	
					//session.setAttribute("mensaje_numenv",mensaje_numenv);
					
					
					/*session.setAttribute("codigo_recepcion_env",codigo_recepcion);
					session.setAttribute("codigo_documento_env",codigo_documento);
					session.setAttribute("codigo_expediente_env",codigo_expediente);
					session.setAttribute("numero_env",li_retorno);
					session.setAttribute("numerodocumento_env",ls_documento);
					session.setAttribute("fecha_env",fecha);
					session.setAttribute("codigo_motivo_env",codigo_motivo);
					session.setAttribute("codigo_oficina_env",codigo_oficina);
					session.setAttribute("observacion_env",observacion);
					session.setAttribute("hojaenvio","H");
					session.setAttribute("codigocontador","0");*/
					
					session.removeAttribute("operacion_firma_documento");
					session.removeAttribute("rs_upload_doc_internos");
					session.removeAttribute("observacion");
					session.removeAttribute("tipoenv");
					session.removeAttribute("pagina");
					session.removeAttribute("firmaManual");
					session.setAttribute("para_firma",null);
					
					session.setAttribute("listaFrameBusquedaTemp",null);
					
					if(pagina.equals("haciabderiva")){
						session.setAttribute("operacionpopup","Y");
						session.setAttribute("nombreArchivoVer", documento_interno_deriva.getNombre_arhivo());
		       	    	session.setAttribute("tipoDocumentoVer", "3");
		       	    	session.setAttribute("codigoOficinaVer", documento_interno_deriva.getCodigo_oficina_pertenece().toString());
						return (mapping.findForward("haciabderiva"));
					}else{
						session.setAttribute("operacionpopup","X");
						request.setAttribute("nombreArchivoVer", documento_interno_deriva.getNombre_arhivo());
		       	    	request.setAttribute("tipoDocumentoVer", "3");
		       	    	request.setAttribute("codigoOficinaVer", documento_interno_deriva.getCodigo_oficina_pertenece().toString());
						return (mapping.findForward("exitoderivacion"));
					}
					
		}catch (SQLException e) {
			e.printStackTrace();
		
		} /*finally{
        	closeConnection(cnx);	
        }*/
		return (mapping.findForward("error"));

	} else if (tipo.equalsIgnoreCase("enviar_doc_observado")){
		log.info("Entry enviar ...");
		try {
			 log.info("Actualizando documento observado." );
			session.removeAttribute("personas");
			session.removeAttribute("codigo_motivo");
			log.info("Datos del usuario que deriva:");
			log.info("============================");
			
			ls_codigo_oficina_user   = String.valueOf(session.getAttribute("codigo_oficina_user"));
			log.info("ls_codigo_oficina_user: "+ls_codigo_oficina_user);
			
		
			codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
			log.info("codigo doc ->"+codigo_documento);
			
			codigo_expediente = ((FFormMantDerivar)form).getCodigo_expediente();
			log.info("codigo_expediente ->"+codigo_expediente);
			
			codigo_recepcion = ((FFormMantDerivar)form).getCodigo_recepcion();
			log.info("codigo_recepcion ->"+codigo_recepcion);
			
			secuencia_movimiento = ((FFormMantDerivar)form).getSecuencia_movimiento();
			log.info("secuencia_movimiento ->"+secuencia_movimiento);
			
			String xxxx = (String) request.getParameter("ls_secuencia_movimiento_destino");
			log.info("secuencia_destino "+xxxx);
			
			codigo_motivo = ((FFormMantDerivar)form).getCodigo_motivo();
			log.info("codigo_motivo ->"+codigo_motivo);
			
			observacion = ((FFormMantDerivar)form).getObservacion();
			observacion = (observacion==null)? "":observacion;
	   	    log.info("observacion ->"+observacion);
			
			doc_resp = ((FFormMantDerivar)form).getDoc_resp();
			doc_resp = (doc_resp==null)? "":funciones.convertir_comillas_simples(doc_resp);
			log.info("doc_resp ->"+doc_resp);
			
			fecha_rpta_rq = ((FFormMantDerivar)form).getFecha_rpta_rq();
			fecha_rpta_rq = (fecha_rpta_rq==null)? "":fecha_rpta_rq;
			log.info("fecha_rpta_rq ->"+fecha_rpta_rq);
					
						
			log.info("============================");
			BMesaPartes BMesaPartesVO = daoIUsuarioDAO.of_obtener_padre(cnx,codigo_documento);
			
			 log.info(" codigo_documento.. "+ codigo_documento);
			 log.info("============================"); 
	        ls_documento = codigo_documento; 
	        log.info("Se saca el Codigo Padre");
	        log.info("=========================");
	        ls_cod_padre = BMesaPartesVO.getCodigo_inicia(); ////SACA EL CODIGO PADRE
	        log.info(" ls_cod_padre.. "+ ls_cod_padre);
	        ls_usuario   = String.valueOf(session.getAttribute("nombreusuario"));
	        log.info("==============================");
	        log.info("Tenemos el usuario actual > "+ls_usuario);
	       
	        BPojo resultadoPojo = new BPojo();
	        resultadoPojo=funciones.of_codigo_recepcion_of_id_cab_upload(cnx,codigo_documento,xxxx);
	        /***
	         * SI EL CODIGO ES CERO MEJOR ES CREAR UN NUEVO CODIGO DE RECEPCION
	         */
	        String ls_mensaje=daoIUsuarioDAO.of_ingresar_doc_observardo(cnx,codigo_documento,secuencia_movimiento,xxxx,observacion,codigo_motivo,doc_resp,fecha_rpta_rq,resultadoPojo.getCodigo_recepcion(),resultadoPojo.getId_cab_upload());
	        //String ls_mensaje ="SI";

	        if(ls_mensaje.equals("SI")){
				session.setAttribute("operacionpopup", "G");
				request.setAttribute("mensaje", "Documento Derivado con exito...!");
				request.setAttribute("mensaje_reg", "Nº de Reg: "+codigo_documento);
				request.setAttribute("mensaje_exp", "Nº de Exp: "+codigo_expediente);
			}else{
				session.setAttribute("operacionpopup", "B");
				request.setAttribute("mensaje", "Documento no se puede Derivar...!");
				request.setAttribute("mensaje_reg", "Nº de Reg: "+codigo_documento);
				request.setAttribute("mensaje_exp", "Nº de Exp: "+codigo_expediente);
			}
	        
	        oForm.setCodigo_documento("");
	        oForm.setNumero_documento("");
	        oForm.setObservacion("");
	        oForm.setFecha("");
	        
	       //return (mapping.findForward("haciabderiva"));
			
			
		}catch (SQLException e) {
			e.printStackTrace();

		}/* finally{
        	closeConnection(cnx);	
        }*/
				
		return mapping.getInputForward();

	} 
		else if (tipo.equals("upload") ){

			log.info("Entry Upload Archivos");
						
			String ls_codigo_documento=request.getParameter("codigo_documento");
			String ls_operacion=request.getParameter("operacion");
			ls_codigo_expediente=request.getParameter("codigo_expediente");
			String ls_secuencia_movimiento=request.getParameter("secuencia_movimiento");
			String ls_modo=request.getParameter("modo");
			
			ls_modo=(ls_modo==null)? "":ls_modo;
			
			log.info("EL ls_codigo_documento upload..."+ls_codigo_documento );
			log.info("EL ls_operacion upload..."+ls_operacion );
			log.info("EL ls_codigo_expediente upload..."+ls_codigo_expediente );
		    log.info("EL ls_secuencia_movimiento upload..."+ls_secuencia_movimiento );
		    log.info("EL ls_modo upload..."+ls_modo );
			
			/**
			 * CARGO LA LISTA DE DOCS ADJUNTADOS POR EL USUARIO ACTUAL DESDE TABLA DET
			 
			Collection rs_upload_doc = new ArrayList();
			rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos(cnx,Integer.parseInt(ls_codigo_documento.trim()));
			//log.info("el rs_upload_doc es ......"+ rs_upload_doc);
			//String ls_contador=funciones.of_contador_upload_doc(cnx, Integer.parseInt(ls_codigo_documento.trim())); MODIFICADO
			if(rs_upload_doc!=null && rs_upload_doc.size()>0){
					log.info("Contador diferente de vacio e igual a 1..." );
				session.setAttribute("contador", "1");
			}else{
					log.info("Contador igual a vacio e igual a 0..." );
				session.setAttribute("contador", "0");
			}
			*/
			
				//log.info("El ls_contador es..."+ ls_contador );
			/*	
				if(ls_contador.equals("0")){ MODIFICADO
					log.info("Contador igual a vacio e igual a 0..." );
					session.setAttribute("contador", "0");  //PARA LA FUNCION CERRAR
					
				}else{
					log.info("Contador diferente de vacio e igual a 1..." );
					session.setAttribute("contador", "1");  //PARA LA FUNCION CERRAR
				}
			*/	
				
			session.setAttribute("operacion",ls_operacion);
			//session.setAttribute("rs_upload_doc",rs_upload_doc);
			session.setAttribute("codigo_documento",ls_codigo_documento);
			session.setAttribute("codigo_expediente",ls_codigo_expediente);
			session.setAttribute("secuencia_movimiento",ls_secuencia_movimiento);
			request.setAttribute("modo_observacion", ls_modo);
			
			return (mapping.findForward("upload"));
			
		}
		else if (tipo.equals("uploadficheros") ){
			
			log.info("Entry Uploadficheros Archivos");
			
			/*********************Upload de Ficheros*****************/
			String ls_codigo_documento=request.getParameter("codigo_documento");
			ls_codigo_expediente=request.getParameter("codigo_expediente");
			String ls_secuencia_movimiento=request.getParameter("secuencia_movimiento");
			String ls_modo=request.getParameter("modo");
			
			ls_modo=(ls_modo==null)? "":ls_modo;
			
			log.info("El ls_codigo_documento..." + ls_codigo_documento );
			log.info("El ls_codigo_expediente..." + ls_codigo_expediente );
			log.info("El ls_secuencia_movimiento..." + ls_secuencia_movimiento );
			log.info("EL ls_modo upload..."+ls_modo );
			
			FFormMantDerivar myForm = (FFormMantDerivar)form;
			codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
			log.info("El codigo_documento FOR..." + codigo_documento);
			String nombre_archivo_back= (String) session.getAttribute("nombre_file_adjunto");
			nombre_archivo_back=nombre_archivo_back==null? "":nombre_archivo_back;
			
			/**
			 * revisar
			 */
			BArchivo archivo = new BArchivo();
			archivo.setUsuario(usuario);

			FormFile myFile = myForm.getTheFile();
			
			String contentType = myFile.getContentType();
			log.info("el contentType FILE UPLOAd es.."+ contentType);
			
			
			try {
				if(!myFile.getFileName().equals("")){
					archivo.setNombre_archivo(myFile.getFileName());
					log.info("el fileName es.."
							+ archivo.getNombre_archivo());

					archivo.setData(myFile.getFileData());
					
					/**
					 * jazanero
					 */
					String ruta_detalle_upload=service.getRutaDetalleUpload(ls_oficina);
					
					archivo.setRutaDetalleUpload(ruta_detalle_upload);
					
					if (myForm.getArchivos() == null) {
						Collection archivos = new ArrayList();
						archivos.add(archivo);
						myForm.setArchivos(archivos);
					} else {

						myForm.getArchivos().add(archivo);
					}
					
					session.setAttribute("contador","1");
					
					if(nombre_archivo_back.equals("")){
						session.setAttribute("nombre_file_adjunto",archivo.getNombre_archivo());
					}else{
						session.setAttribute("nombre_file_adjunto",nombre_archivo_back+","+archivo.getNombre_archivo());
					}
	            						
					
				}else{
					session.setAttribute("contador",null);
				}
			} catch (Exception ex) {
			    throw new Exception(ex);
			  } finally {
			    //destroy the temporary file created
				myFile.destroy();
				myForm.getTheFile().destroy();
			  }

			
			//FFormMantDerivar
			
			/**
			 * fin
			 */
			
 		        /* Process the FormFile
 				      FormFile myFile = myForm.getTheFile();
 				      String contentType = myFile.getContentType();
 				      log.info("el contentType FILE UPLOAd es.." + contentType);
 	   
 			       	  String fileName    = myFile.getFileName();
 			       	  log.info("el fileName es.." + fileName);
 			       	  //int fileSize       = myFile.getFileSize();
 			          byte[] fileData    = myFile.getFileData();
 			          log.info("el fileData es.." + fileData);
 				      
 				     int ls_id_proyecto=0;
 				     String ls_new_nombre_upload="";
 				      // Save file on the server
 				      if(!fileName.equals("")){  
 				       
 				          //Create file
 				         int punto = fileName.lastIndexOf('.');
 				         
 				         log.info("el puntoLUUUU+1 es.." + fileName.substring(punto + 1));
 				        
	  				       if(fileName.substring(punto + 1).equals("DOC")){
	 				        	
	  				    	  fileName = fileName.replace("DOC","doc");
	 				        	log.info("El Cambiando el nombre a miniscula..XXXXXX:"+fileName);
	 				        }

	  				       ls_new_nombre_upload=funciones.of_valida_letras(fileName);
				         	
	  				       File fileToCreate = new File(filePath, ls_new_nombre_upload);
 				        
 				          //If file does not exists create file                      
 				          if(!fileToCreate.exists()){
	 				        FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
	 			           
	 			            fileOutStream.write(myFile.getFileData());
	 			            fileOutStream.flush();
	 			            fileOutStream.close();
	 			          } 
 				         
 				     daoIUsuarioDAO.DerivarDocumentoDetUploadIng(Integer.parseInt(ls_codigo_documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),Integer.parseInt(ls_secuencia_movimiento.trim()), ls_new_nombre_upload, filePath,"A","P","O");
 				    
 			      }
 				  /***************************FIn upload*****************************
 				
 				/***
 				 * ACTUALIZO LA LISTA DE DOCUMENTOS ADJUNTOS   
 				 *
	 				Collection rs_upload_doc = new ArrayList();
	 				rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos(cnx,Integer.parseInt(ls_codigo_documento.trim()));
	 				//String ls_contador=funciones.of_contador_upload_doc(cnx, Integer.parseInt(ls_codigo_documento.trim())); MODIFICADO
	
	 				if(rs_upload_doc!=null && rs_upload_doc.size()>0){
	 					log.info("Contador diferente de vacio e igual a 1..." );
						session.setAttribute("contador", "1");
						session.setAttribute("nombre_file_adjunto",ls_new_nombre_upload);
	 				}else{
	 					log.info("Contador igual a vacio e igual a 0..." );
						session.setAttribute("contador", "0");
	 				}
 					
 				
 				/***
 				 * FIN	
 				 */
 			 					
	 			session.setAttribute("operacion", "N");
	 			//session.setAttribute("rs_upload_doc",rs_upload_doc);
	 			
				return (mapping.findForward("upload"));
		}	
			
		else if (tipo.equals("eliminar") ){
			
			log.info("Si tipo es init es.." + tipo);
			log.info("Listar Eliminar Upload Archivos..." );
			
			//codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
			String ls_id_det_upload=request.getParameter("id_det_upload");
			String ls_codigo_documento=request.getParameter("codigo_documento");
			String nombre_file_adjunto = (String) session.getAttribute("nombre_file_adjunto");
			nombre_file_adjunto=(nombre_file_adjunto==null)? "":nombre_file_adjunto;
			
			log.info("EL ls_id_det_upload upload..."+ls_id_det_upload );
			
			
			String nombre_archivo=daoIUsuarioDAO.obtenerNombreArchivo(cnx,ls_id_det_upload,ls_codigo_documento);
			//Falta eliminar Fisicamente.
			log.info("nombre de archivo a eliminar ..." +nombre_archivo);
			
			
			
			daoIUsuarioDAO.EliminarDocumentoDetUpload(cnx,Integer.parseInt(ls_id_det_upload.trim()));
			eliminarArchivoFisicamente(nombre_archivo);
			log.info("se elimino el archivo: " +nombre_archivo);
			
			Collection rs_upload_doc = new ArrayList();
			rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos(cnx,Integer.parseInt(ls_codigo_documento.trim()));
			
			if(rs_upload_doc!=null && rs_upload_doc.size()>0){
				log.info("Contador diferente de vacio e igual a 1..." );
				session.setAttribute("contador", "1");
				if(nombre_file_adjunto.equals(nombre_archivo)){
					session.removeAttribute("nombre_file_adjunto");
				}
			}else{
				log.info("Contador igual a vacio e igual a 0..." );
				session.setAttribute("contador", "0");
			}
			
			
			log.info("el rs_upload_doc es ......"+ rs_upload_doc);
			session.setAttribute("operacion", "N");
			session.setAttribute("rs_upload_doc",rs_upload_doc);
				
			return (mapping.findForward("upload"));
		}
			
		else if (tipo.equalsIgnoreCase("contadjunto")){
			try {
				//session.removeAttribute("rs_personas");
				String modo_observacion= request.getParameter("modo_observacion");
				modo_observacion = (modo_observacion==null)? "":modo_observacion;
				
				log.info("Si tipo es contadjunto FFFFFFF.." + tipo);
				log.info("Dentro de ContaAdjunto en MantDerivar");	
				session.removeAttribute("mensaje_numreg");
				session.removeAttribute("mensaje_numexp");
				session.removeAttribute("mensaje_numenv");
				//session.removeAttribute("codigocontador");
				
				//session.removeAttribute("personas");
				//session.removeAttribute("cargo_personal");
				//session.removeAttribute("as_codigo_oficina");
				cargo_personal   =   String.valueOf(session.getAttribute("cargo_personal"));
				log.info(" El cargo_personal es ÑÑ.. "+ cargo_personal);
				
				codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
				codigo_expediente = ((FFormMantDerivar)form).getCodigo_expediente();
				descripcion_tipo = ((FFormMantDerivar)form).getDescripcion_tipo();
				numero_documento = ((FFormMantDerivar)form).getNumero_documento();
				
				fecha = ((FFormMantDerivar)form).getFecha();
				/*hora = ((FFormMantDerivar)form).getHora();
				observacion = ((FFormMantDerivar)form).getObservacion();
				estado_movimiento = ((FFormMantDerivar)form).getEstado_movimiento();*/
				
				codigo_oficina = ((FFormMantDerivar)form).getCodigo_oficina();
				destinatario = ((FFormMantDerivar)form).getDestinatario();
				codigo_motivo = ((FFormMantDerivar)form).getCodigo_motivo();
				observacion = ((FFormMantDerivar)form).getObservacion();
				fecha_rpta = ((FFormMantDerivar)form).getFecha_rpta();
				
				personas = ((FFormMantDerivar)form).getPersonas();
				cbo_fecharpta = ((FFormMantDerivar)form).getCbo_fecharpta();
				chk_copia = ((FFormMantDerivar)form).getChk_copia();
				cbo_copia = ((FFormMantDerivar)form).getCbo_copia();
				
				secuencia_movimiento = ((FFormMantDerivar)form).getSecuencia_movimiento();
				codigo_inicia = ((FFormMantDerivar)form).getCodigo_inicia();
				naturaleza_documento = ((FFormMantDerivar)form).getNaturaleza_documento();
				codigo_recepcion = ((FFormMantDerivar)form).getCodigo_recepcion();
				//codigocontador= ((FFormMantDerivar)form).getCodigocontador();
				codigocontador= ((FFormMantDerivar)form).getCodigocontador();
				//String vvv = ((FFormMantDerivar)form).getCodigocontador();
				
				log.info(" usuario.. "+ usuario);
				log.info(" codigo_documento.. "+ codigo_documento);
				log.info(" codigo_expediente.. "+ codigo_expediente);
				log.info(" descripcion_tipo.. "+ descripcion_tipo);
				log.info(" numero_documento.. "+ numero_documento);
				log.info(" fecha.. "+ fecha);
				log.info(" destinatario.. "+ destinatario);
				log.info(" codigo_motivo.. "+ codigo_motivo);
				log.info(" observacion.. "+ observacion);
				log.info(" fecha_rpta.. "+ fecha_rpta);
				log.info(" chk_copia.. "+ chk_copia);
				log.info(" cbo_copia.. "+ cbo_copia);
				log.info(" codigo_recepcion.. "+ codigo_recepcion);
				log.info(" secuencia_movimiento.. "+ secuencia_movimiento);
				log.info(" codigo_oficina.. "+ codigo_oficina);
				log.info(" codigocontador.. "+ codigocontador);
				log.info(" personas.. "+ personas);
				log.info(" codigo_inicia ... "+codigo_inicia);
				
   	    		log.info("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
   	    	
   	    		if(observacion.equals("null")){
   	   	 			observacion="";
   	   	 			log.info("La observacion es null de lo contrario es igual a vacio.");
   	   	    	}
   	    		
   	    		//ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
   	    		
   	    		log.info("El ls_oficina editar XX es..." + ls_oficina);
   	    		//ls_nombre_oficina=funciones.of_nombre_oficina(cnx,ls_oficina);
   	    		//log.info("El ls_nombre_oficina es..." + ls_nombre_oficina);
      	    	// daoIUsuarioDAO.of_derivar_doc_edit(cnx,codigo_oficina,codigo_documento,usuario,fecha,hora,observacion,estado_movimiento,ls_correlativorecepcion);
				
      	    	Collection rs_oficinas = new ArrayList();
      	    	Collection rs_personas = new ArrayList();
   	    		Collection rs_derivacion_doc = new ArrayList();
   	    		Collection rs_observados_doc = new ArrayList();
   	    		
   	    		
   	    		rs_oficinas = daoIUsuarioDAO.of_lista_oficinas(cnx);
   	    		//rs_personas = daoIUsuarioDAO.of_lista_personal(cnx);
   	    		
   	    		if(!cargo_personal.equals("3") || !cargo_personal.equals("10")){
   	    			rs_personas = daoIUsuarioDAO.of_lista_personas_oficinas(ls_oficina);
   	    			session.setAttribute("rs_personas",rs_personas);
   	    		}
   	    		
				log.info("el rs_derivacion_doc es ......"+ rs_derivacion_doc);
				log.info("el rs_personas es ......"+ rs_personas);
				String accesoaoficinas=service.getAccesosaOficinas(ls_codigo_persona);
				if(accesoaoficinas != null){
					accesoaoficinas=ls_oficina+","+accesoaoficinas;
				}else{
					accesoaoficinas=ls_oficina;
				}
				
				rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_oficina,accesoaoficinas,String.valueOf(request.getAttribute("ls_inicia")),valor);
				//observacion_documento
				rs_observados_doc = daoIUsuarioDAO.lista_observacion_documentos(cnx,ls_oficina,String.valueOf(request.getAttribute("ls_inicia")));
				
				session.setAttribute("operacion",tipo);
				session.setAttribute("rs_derivacion_doc",rs_derivacion_doc);
				session.setAttribute("rs_observados_doc",rs_observados_doc);
				session.setAttribute("rs_oficina",rs_oficinas);
				session.setAttribute("cargo_personal",cargo_personal);
				session.setAttribute("as_codigo_oficina",ls_oficina);
				//session.setAttribute("nombre_oficina",ls_nombre_oficina);
				session.setAttribute("codigocontador", codigocontador);
				
				
				if(codigocontador.equals("1")){
					
					rs_personas = daoIUsuarioDAO.of_lista_personas_oficinas(codigo_oficina);
					
   	    			session.setAttribute("rs_personas",rs_personas);
					session.setAttribute("codigo_oficina", codigo_oficina);
					session.setAttribute("personas", personas);
					session.setAttribute("codigo_motivo", codigo_motivo);
					session.setAttribute("fecha_rpta", fecha_rpta);
					
				}
				
				if(modo_observacion.equals("1")){
					
	   	    		  				
					String ls_codigo_persona_que_observa= request.getParameter("ls_codigo_persona_que_observa");
					String ls_codigo_oficina_que_observa= request.getParameter("ls_codigo_oficina_que_observa");
					String ls_codigo_motivo= request.getParameter("ls_codigo_motivo");
					String ls_persona_destino= request.getParameter("ls_persona_destino");
					String ls_oficina_destino= request.getParameter("ls_oficina_destino");
					String ls_secuencia_movimiento_destino = request.getParameter("ls_secuencia_movimiento_destino");
					
					log.info("ls_codigo_persona_que_observa "+ls_codigo_persona_que_observa);
					log.info("ls_codigo_oficina_que_observa "+ls_codigo_oficina_que_observa);
					log.info("ls_codigo_motivo "+ls_codigo_motivo);
					log.info("ls_persona_destino "+ls_persona_destino);
					log.info("ls_oficina_destino "+ls_oficina_destino);
					log.info("ls_secuencia_movimiento_destino "+ls_secuencia_movimiento_destino);
					//modo_observacion = (modo_observacion==null)? "":modo_observacion;

					request.setAttribute("ls_oficina_destino", ls_oficina_destino);
					request.setAttribute("ls_persona_destino", ls_persona_destino);
					
					request.setAttribute("ls_codigo_motivo", ls_codigo_motivo);
					request.setAttribute("ls_codigo_persona_que_observa", ls_codigo_persona_que_observa);
					request.setAttribute("ls_codigo_oficina_que_observa", ls_codigo_oficina_que_observa);
					request.setAttribute("ls_secuencia_movimiento_destino", ls_secuencia_movimiento_destino);
					request.setAttribute("modo_observacion", modo_observacion);
				}
				
				
				//session.setAttribute("correlativorecepcion",ls_correla_conv);
//				session.setAttribute("correlativorecepcion",ls_correla_conv);
				//session.setAttribute("rs_personas",rs_personas);
   	    		
			}catch (SQLException e) {
				rollbackConnection(cnx);
				e.printStackTrace();
				session.setAttribute("MsjeError", "Error al actualizar al empleado: " + e.getMessage());
				//closeConnection(cnx);
				//return mapping.getInputForward();
	
			}
					
			return mapping.getInputForward();
	
		} 
		
		/*else if (tipo.equalsIgnoreCase("hojaenvio")){
			try {
				//session.removeAttribute("rs_personas");
				log.info("Si tipo es init dentro de hoja de envio es.." + tipo);
				
				
				//codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
				/*String ls_codigo_documento=request.getParameter("codigo_documento");
				String ls_operacion=request.getParameter("operacion");
				 ls_codigo_expediente=request.getParameter("codigo_expediente");
				String ls_secuencia_movimiento=request.getParameter("secuencia_movimiento");*/
				
			/*	codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
				codigo_expediente = ((FFormMantDerivar)form).getCodigo_expediente();
				
				log.info("EL codigo_documento upload..."+codigo_documento );
				log.info("EL codigo_expediente upload..."+codigo_expediente );
				
				
				session.setAttribute("hojaenvio","X");
				session.setAttribute("codigo_documento_env",codigo_documento);
				session.setAttribute("codigo_expediente_env",codigo_expediente);
				
   	    		
			}catch (SQLException e) {
				rollbackConnection(cnx);
				e.printStackTrace();
				session.setAttribute("MsjeError", "Error al actualizar al empleado: " + e.getMessage());
				//closeConnection(cnx);
				//return mapping.getInputForward();
	
			}
					
			return mapping.getInputForward();
	
		} */
			
		//closeConnection(cnx);
		oForm.reset(mapping, request);
		oForm.setTipo("derivar");
		return (mapping.findForward("exito"));
    	   
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
        	closeConnection(cnx);	
        }
       	return (mapping.findForward("error"));
	}
	
	private void subirArchivosBloque(String ls_codigo_documento, Collection rs_upload_doc_internos_adj, String ls_codigo_expediente, String ls_secuencia_movimiento) {
		log.info(">>>>>>>>>>>>>>>>>>>>>>ENTRE EN SUBIR DOCUMENTOS EN BLOQUE DOCUMENTOS INTERNOS FIRMADOS>>>>>>>>>>>>>>>>>>>");
				
			Iterator it=rs_upload_doc_internos_adj.iterator();
			while(it.hasNext()){
				BArchivo archivo=(BArchivo)it.next();
				try {
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
		
		
		log.info(">>>>>>>>>>>>>>>>>>>>>>FIN EN SUBIR DOCUMENTOS FIRMADOS EN BLOQUE >>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}


	private void subir_archivo_firmado(String documento,BArchivo archivo, String ls_codigo_expediente, String ls_secuencia_movimiento) throws SQLException, IOException,Exception {

/*		log.info("<<<<<<<<<<<<<<<<<<<<<SUBIENDO ARCHIVOS FIRMADOS DESDE LA LISTA>>>>>>>>>>>>>>>>>>>>");
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
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
						Integer.parseInt(ls_secuencia_movimiento.trim()), ls_new_nombre_upload, filePath,"I","P");

			}

		}
		log.info(">>>>>>>>>>>>>>>>>>>>>>FIN SUBIR ARCHIVO UNITARIO >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		*/
		log.info("<<<<<<<<<<<<<<<<<<<<<SUBIENDO ARCHIVOS FIRMADOS DESDE LA LISTA>>>>>>>>>>>>>>>>>>>>");
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		Funciones funciones = new Funciones();
		/**ARCHIVO FIRMADO**/
		//String fileName = archivo.getNombre_archivo_cifrado();
		/**ARCHIVO NORMAL**/
		String fileName = archivo.getNombre_archivo();
		//String filePath = Constantes.CarpetaArchivosProduccion.getNombre();

			int punto = fileName.lastIndexOf('.');
			log.info("el puntoLUUUU es.." + punto);
			log.info("el puntoLUUUU + 1 es.."+ fileName.substring(punto + 1));
			
			if (fileName.substring(punto + 1).equals("DOC")) {
				fileName = fileName.replace("DOC", "doc");
				log.info("El Cambiando el nombre a miniscula..XXXXXX: "+fileName);
			}

			log.info("<<<<<<<<<<<<<<<<<<<<<REGISTRANDO ARCHIVO :"+fileName);
			/**
			 * copy file from memory in the disk
			 */
			String ls_new_nombre_upload = funciones.of_valida_letras(fileName);
			log.info("El ls_new_nombre_upload ees.."+ ls_new_nombre_upload);
			//FALTA UNA VALIDACION
			
			File fileToCreate = new File(filePath+archivo.getRutaDetalleUpload()+funciones.getAnioActualTexto(), funciones.getFechaActualyHoraActualTexto()+ls_new_nombre_upload);

			if (makeSureDirectoryExists(parent(fileToCreate))) {//sino existe directorio..lo crea
				if (!fileToCreate.exists()) { //sino existe el archivo .. graba
					FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
					fileOutStream.write(archivo.getData());
					fileOutStream.flush();
					fileOutStream.close();
					
				}
				
			}
					
			archivo.setNoFirmado(true);
			
			/**
			 * fin
			 */
						
			
			if(archivo.isIsfirmadomanual()){
				log.info("--> M");
				daoIUsuarioDAO.ActualizaDocumentoDocEntradaDetUploadIng(Integer.parseInt(documento.trim()),archivo.getUsuario(), archivo.getId_detalle_archivo());
				
			}else{
				if(archivo.isIsfirmado()){
					daoIUsuarioDAO.DerivarDocumentoDetUploadIng(
							Integer.parseInt(documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),
							Integer.parseInt(ls_secuencia_movimiento.trim()), fileName, archivo.getRuta(),"A","P","F");
				}
				if(archivo.isIsvisado()){
					daoIUsuarioDAO.DerivarDocumentoDetUploadIng(
							Integer.parseInt(documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),
							Integer.parseInt(ls_secuencia_movimiento.trim()), fileName, archivo.getRuta(),"A","P","V");
				}
				if(!archivo.isIsfirmado() && !archivo.isIsvisado() && !archivo.isNoFirmado()){
					daoIUsuarioDAO.DerivarDocumentoDetUploadIng(
							Integer.parseInt(documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),
							Integer.parseInt(ls_secuencia_movimiento.trim()), fileName, IConstante.filePathVisados,"A","P","V");
				}
				if(archivo.isNoFirmado()){
					
					/*usado ahora
					daoIUsuarioDAO.DerivarDocumentoDetUploadIng(Integer.parseInt(documento.trim()),
							Integer.parseInt(ls_codigo_expediente.trim()),Integer.parseInt(ls_secuencia_movimiento.trim()), 
							ls_new_nombre_upload, filePath,"A","P","O");
					*/		
					
					//modificado para detupload registro
					
					log.info("daoIUsuarioDAO.DerivarDocumentoDetUploadIng("+Integer.parseInt(documento.trim())+","+
							Integer.parseInt(ls_codigo_expediente.trim())+","+Integer.parseInt(ls_secuencia_movimiento.trim())+","+
							ls_new_nombre_upload+","+filePath+archivo.getRutaDetalleUpload()+funciones.getAnioActualTexto()+", A, P,"+ 
							archivo.getUsuario()+",O);	");
					
					daoIUsuarioDAO.DerivarDocumentoDetUploadIng(Integer.parseInt(documento.trim()),
							Integer.parseInt(ls_codigo_expediente.trim()),Integer.parseInt(ls_secuencia_movimiento.trim()),
							ls_new_nombre_upload, 
							filePath+archivo.getRutaDetalleUpload()+funciones.getAnioActualTexto(), "A", "P", 
							archivo.getUsuario(),"O");					
					
					
				}
			}
			
			
		log.info(">>>>>>>>>>>>>>>>>>>>>>FIN SUBIR ARCHIVO UNITARIO >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}
	
	private void subirArchivosBloque(String ls_codigo_documento, HttpSession session, String ls_codigo_expediente, String ls_secuencia_movimiento){

		FFormMantDerivar oForm_ =(FFormMantDerivar)session.getAttribute("FFormMantDerivar");
		if(oForm_.getArchivos()!=null && oForm_.getArchivos().size()>0){
			Iterator it=oForm_.getArchivos().iterator();
			while(it.hasNext()){
				BArchivo archivo=(BArchivo)it.next();
				try {
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
	
	}
	
	private void subirArchivosBloque(String ls_codigo_documento,HttpSession session) {
		FFormMantDerivar oForm_ =(FFormMantDerivar)session.getAttribute("FFormMantDerivar");
		if(oForm_.getArchivos()!=null && oForm_.getArchivos().size()>0){
			Iterator it=oForm_.getArchivos().iterator();
			while(it.hasNext()){
				BArchivo archivo=(BArchivo)it.next();
				try {
					subir_archivo(ls_codigo_documento, archivo);
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
	

	private void subir_archivo(String documento,BArchivo archivo) throws SQLException, IOException,Exception {

		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		Funciones funciones = new Funciones();
		String fileName = archivo.getNombre_archivo();
		
		if (!fileName.equals("")) {

			int punto = fileName.lastIndexOf('.');
			log.info("el puntoLUUUU es.." + punto);
			log.info("el puntoLUUUU + 1 es.."+ fileName.substring(punto + 1));
			
			if (fileName.substring(punto + 1).equals("DOC")) {
				fileName = fileName.replace("DOC", "doc");
				log.info("El Cambiando el nombre a miniscula..XXXXXX:"
								+ fileName);
			}

			String ls_new_nombre_upload = funciones.of_valida_letras(fileName);
			
			if (funciones.validaNombreDelArchivo(ls_new_nombre_upload)) {
				log.info("El ls_new_nombre_upload ees.."+ ls_new_nombre_upload);
				
				
				File fileToCreate = new File(filePath+archivo.getRutaDetalleUpload()+funciones.getAnioActualTexto(), funciones.getFechaActualyHoraActualTexto()+ls_new_nombre_upload);

				if (makeSureDirectoryExists(parent(fileToCreate))) {//sino existe directorio..lo crea
					if (!fileToCreate.exists()) { //sino existe el archivo .. graba
						FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
						fileOutStream.write(archivo.getData());
						fileOutStream.flush();
						fileOutStream.close();
					}
					
				}
								
				daoIUsuarioDAO.CrearDocumentoDocEntradaDetUploadIng(Integer.parseInt(documento.trim()),ls_new_nombre_upload, 
						filePath+archivo.getRutaDetalleUpload()+funciones.getAnioActualTexto(), "A", "P", archivo.getUsuario(),"O");
							
			}

		}
	}
	
	/*
	 * agregado por Moises Pelaez
	 * Fecha: 01-07-2011
	 * Descripcion: Elimina el archivo fisicamente
	 * Antecedentes:usuario elimina arrchivo, y cuando el usuario adjuntaba un archivo con el mismo nonbre
	 * al final cuando abria el archivo resultaba ser el antiguo.
	 * */
	private void eliminarArchivoFisicamente(String nombre_archivo) {
		File tem= new File(filePath,nombre_archivo);       		
		if(tem.exists()&& tem.isFile()){
			if(tem.delete())
			{
				log.info("archivo: "+nombre_archivo +"Elimando con exito");
			}else
				log.info("archivo: "+nombre_archivo +"no pudo ser eliminado");
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
