package tramitedoc.concytec.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

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
import tramitedoc.concytec.bean.BInfo;
import tramitedoc.concytec.bean.BMesaPartes;
import tramitedoc.concytec.bean.BPersona;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IReiterativoDAO;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlReiterativoDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormDocumentoInterno;
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

public class AActionMantDocumento extends ValidaSessionAction implements IConstante{
	protected  Log log = LogFactory.getLog(AActionMantDocumento.class);
	@SuppressWarnings("null")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		Properties props = System.getProperties( );
		props.put("mail.smtp.host","smtp.concytec.gob.pe");	
		Session sessionMail = Session.getDefaultInstance(props,null);		
		ArrayList<String> myListaCodigoPersonal = new ArrayList<String>();
		ArrayList<Integer>  myListCodigos= new ArrayList<Integer>();//para envios de correos
		ArrayList<String> myListaCorreosDestinatarios= new ArrayList<String>();
		
		String cadenaAleatoria=""; 	
		HttpSession session = request.getSession(true);
		Connection cnx=null;
		log.info("Dentro de AActionMantDocumento");
		String correo_electronico="";
	
		String ls_operacion = request.getParameter("operacion");
		log.info("la ls_operacion: " + ls_operacion);
		
		String operacion_firma_documento = (String) session.getAttribute("operacion_firma_documento");
		ls_operacion = (ls_operacion==null)? operacion_firma_documento : ls_operacion;
		
		FFormMantDocumento oForm = (FFormMantDocumento) form;
		/*************PARA RESCATAR EL DOCUMENTO INTERNO********/
		DocumentosInternosService service = new DocumentosInternosServiceImplement();
		
		/*****************/
		log.info("Antes del FFormMantDocumento...");	  
		String tipo = oForm.getTipo() == null ? "init" : oForm.getTipo().trim();
		log.info("Despues del tipo...");	 
		log.info("El tipo 1 "+ tipo);
		tipo = tipo.equals("") ? "init" : tipo;
		log.info("El tipo 2 "+ tipo);
		String medio;
		String codigo_tipo;
		String codigo_oficina;
		String codigo_oficina_session;
		String destinatario;
		String codigo_solicitud;
		String numero_documento;
		String fecha_registro;
		String hora;
		String folios_documento;
		String asunto_documento;
		String observa_documento;
		String firmadopor;
		String origen_documento="";
		String desc_origen;
		String tipo_persona="";
		String ls_seleccion;
		String usuario_actual;
		String usuario_actual_session;
		String destino_documento;
		String remitente;
		String copia;
		String ls_codigoPadre = "";
        String ls_documento = "";
        String ls_secuencia_doc = "";
        String ls_correla  = "";
        String fecha_rpta  = "";
        String ls_nombre_oficina  = "";
        String ls_codigo_expediente  = "";
        String ls_correlativo_deriva  = "";
        String codigo_oficina_rq="";
        String codigo_documento="";
        String fecha_movimiento="";
        String observa_movimiento="";
        String naturaleza_documento="";
        String estado_movimiento="";
        String codigo_motivo="";
        String medio_rq="";
        String documento="";
   		String oficina="";
   		String secuencia="";
		//String otras_oficinas;
		String ls_persona;
		String mensaje;
		String correlativorecepcion;
		String cbo_fecharpta;
		String numero_referencia;
		String numero_registro="";
		String ls_numero_registro="";
		String usuario="";
		String ls_codigo_documento="";
		String operacionper="";
		int codigo_expediente=0;
		String secuencia_movimiento="";
		int codigo_recepcion=0;
		int li_retorno=0;
		String ls_cabecera_upload="";
		String ls_cabecera_secuencia="";
		String ls_numero_secuencia="";
		String dirigido="";
		Collection rs_upload_doc_internos_adj = new ArrayList();
		
		String codigo_intitucion="";
		 
		  String chk_copia = null;
		  String[] cbo_copia = null;
		  String[] chk_copias=null;
		  
		  /**
		   * JAZANERO
		   */
		  String chk_copia_other = null;
		  String[] cbo_copia_other = null;
		  String[] chk_copias_other=null;
		  String n_correo_electronico=null;
		  String dominio_correo=null;
		 
		 
		ls_seleccion        =   request.getParameter("rb_seleccion");
        log.info("la Seleccion es :" + ls_seleccion);
       
        usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        codigo_oficina   =   String.valueOf(session.getAttribute("codigo_oficina"));
       
        log.info("El session codigo_oficina es :" + codigo_oficina);
        log.info("El session usuario_actual es :" + usuario_actual);
        ls_persona   =   String.valueOf(session.getAttribute("rs_persona"));
        log.info("El session ls_persona es :" + ls_persona);
        
        Funciones funciones=new Funciones();
        
        documento = String.valueOf(session.getAttribute("documento"));
  		 oficina = String.valueOf(session.getAttribute("oficina"));
  		 secuencia = String.valueOf(session.getAttribute("secuencia"));
  		usuario   = String.valueOf(session.getAttribute("nombreusuario"));
  		
  		
  		session.removeAttribute("mensajeregistro");
  		session.removeAttribute("contador");
  		
		try {
			
			cnx = getConnection(request, "principal");
			log.info("El cnx  ==> " + cnx);
			
			IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			IReiterativoDAO rDAO=new SqlReiterativoDAO();
			
			
			HashMap<String, String> hDestinatario= new HashMap<String, String>();
			Collection rs_upload_doc = new ArrayList();
        	int li_operacion =0;
        	
        	log.info("LS OPERACION ES el siguiente:"+ls_operacion);
        	//ls_operacion;
        	
        	if (ls_operacion.equals("N")){
                li_operacion = 1;
            }else if (ls_operacion.equals("M")){
    	            li_operacion = 2;
    	    } else if (ls_operacion.equals("E")){
                li_operacion = 3;
    	    }  else if (ls_operacion.equals("A")){
                li_operacion = 4;
    	    }  else if (ls_operacion.equals("UA")){
                li_operacion = 5;
    	    }   else if (ls_operacion.equals("UADMIN")){//MODIFICAR DOCUMENTO 
                li_operacion = 51;
    	    }else if (ls_operacion.equals("AM")){//adjuntar documento
                li_operacion = 6;
    	    } else if (ls_operacion.equals("AMU")){
                li_operacion = 7;
    	    }else if (ls_operacion.equals("EU")){
                li_operacion = 8;
    	    }else if (ls_operacion.equals("DI")){//ADJUNTAR DOCUMENTO INTERNO
                li_operacion = 9;
    	    }else if (ls_operacion.equals("RI")){//ADJUNTAR DOCUMENTO INTERNO desde creacion de documentos
                li_operacion = 10;
    	    }else if (ls_operacion.equals("EDIF")){//ELIMINAR DOCUMENTO INTERNO DE LA LISTA EN MEMORIA EN POPUP desde creacion de documentos
                li_operacion = 11;
    	    }else if (ls_operacion.equals("FDIBC")){//REGISTRO Y RECEPCION DE DOCUMENTO INTERNO DESDE BANDEJA DE FIRMA
                li_operacion = 12;
    	    }  
        	
        	switch(li_operacion){
       	 case 1:
       	 	 	log.info("Entry ... case 1");
       	     	session.setAttribute("operacion", ls_operacion);
       	     	log.info("Ingresando Documento.........." );
       	     	HashMap<String, String> hRemitente=daoIUsuarioDAO.obtenerDatosUsuario(ls_persona);
       	     	
       	     if (ls_seleccion.equals("E")) {
       	    	String ls_mensaje="";
        	        dirigido     =   request.getParameter("dirigido");
        	        dirigido=dirigido.toUpperCase();
           	        log.info("Dirigido a ->"+dirigido);
           	        
         	    	 
           	    	log.info("Aqui entra Mesa de Partes");
           	    	
           	    	log.info("Entry ...E (Externo)"); 
           	    	session.removeAttribute("operacion");
           	    	session.removeAttribute("operacionpopup");
           	    	 
           	    	session.removeAttribute("medio");
           	  		session.removeAttribute("codigo_tipo");
           	  		//session.removeAttribute("codigo_oficina");
           	  		session.removeAttribute("destinatario");
           	  		session.removeAttribute("codigo_solicitud");
           	  		session.removeAttribute("numero_documento");
           	  		session.removeAttribute("fecha_registro");
           	  		//session.removeAttribute("hora");
           	  		session.removeAttribute("folios_documento");
           	  		session.removeAttribute("asunto_documento");
           	  		session.removeAttribute("observa_documento");
           	  		session.removeAttribute("firmadopor");
           	  		session.removeAttribute("origen_documento");
           	  		session.removeAttribute("desc_origen");
           	  		session.removeAttribute("tipo_persona");
           	  		session.removeAttribute("cbo_fecharpta");
           	  		session.removeAttribute("fecha_rpta");
           	  		session.removeAttribute("numero_referencia");
           	  		session.removeAttribute("operacion");
           	  		session.removeAttribute("descripcion_persona");
           	  		session.removeAttribute("descripcion_persona_dir");
           	  	
           	  		session.removeAttribute("tipopersona");
           	  		session.removeAttribute("dirigido");
           	  		session.removeAttribute("rs_upload_doc");
           	  		
           	  		session.setAttribute("rb_seleccion", ls_seleccion);
           	  	
           	    	li_retorno = daoIUsuarioDAO.parametro(cnx);
    		   	 	log.info("li_retorno" + li_retorno);
    		   	 	
           	    	correlativorecepcion = String.valueOf(li_retorno);
           	  		log.info("El correlativorecepcion es :" + correlativorecepcion);
           	  		 
           	    	medio     =   request.getParameter("medio");
           	        codigo_tipo     =   request.getParameter("codigo_tipo");
           	        destino_documento     =   request.getParameter("codigo_oficina");
           	        destinatario     =   request.getParameter("destinatario");
           	        codigo_solicitud     =   request.getParameter("codigo_solicitud");
           	        numero_documento     =   request.getParameter("numero_documento");
           	        fecha_registro     =   request.getParameter("fecha_registro");
           	        hora     =   request.getParameter("hora");
           	        folios_documento     =   request.getParameter("folios_documento");
           	        asunto_documento     =   request.getParameter("asunto_documento");
           	        observa_documento     =   request.getParameter("observa_documento");
           	        firmadopor     =   request.getParameter("firmadopor");
           	        origen_documento     =   request.getParameter("origen_documento");
           	        desc_origen     =   request.getParameter("desc_origen");
           	        tipo_persona     =   request.getParameter("tipo_persona");
           	        
           	        //cbo_fecharpta     =   request.getParameter("cbo_fecharpta");
           	        
           	        fecha_rpta     =   request.getParameter("fecha_rpta");
           	        numero_referencia     =   request.getParameter("numero_referencia");

           	        chk_copia = ((FFormMantDocumento)form).getChk_copia();
     			    cbo_copia = ((FFormMantDocumento)form).getSelectedItems();
           	        
           			medio=medio.toUpperCase();
           			codigo_tipo=codigo_tipo.toUpperCase();
           			codigo_oficina=codigo_oficina.toUpperCase();
           			destinatario=destinatario.toUpperCase();
           			codigo_solicitud=codigo_solicitud.toUpperCase();
           			numero_documento=numero_documento.toUpperCase();
           			fecha_registro=fecha_registro.toUpperCase();
           			hora=hora.toUpperCase();
           			folios_documento=folios_documento.toUpperCase();
           			asunto_documento=asunto_documento.toUpperCase();
           			observa_documento=observa_documento.toUpperCase();
           			firmadopor=firmadopor.toUpperCase();
           			desc_origen=desc_origen.toUpperCase();
           			tipo_persona=tipo_persona.toUpperCase();
           		
           			/**
           	         * JAZANERO
           	         */
           			
           	        codigo_intitucion=request.getParameter("codigo_intitucion");
           	        n_correo_electronico=request.getParameter("correo_electronico");
           	        dominio_correo=request.getParameter("dominio_correo");
           	        
           	        n_correo_electronico=(n_correo_electronico==null)? "":n_correo_electronico;
           	        dominio_correo=(dominio_correo==null)? "":dominio_correo;
           	        
           	        
           	        
           			
           			String ls_numdoc_valor="";
           			
           			String ls_numero_expediente ="";
           			
           				log.info("El ls_numdoc_valor es igual a cero.. "+ ls_numdoc_valor);
           				
           				//Funciones funciones=new Funciones();
    				    
           				if(fecha_rpta==null){
           	    			fecha_rpta="";
           	   	 			//log.info("La fecha_rpta es null de lo contrario es igual a vacio.");
           	   	    		}
           				if(numero_referencia==null || numero_referencia.equals("")){
           					numero_referencia="0";
           	   	 			//log.info("La fecha_rpta es null de lo contrario es igual a cero." +numero_referencia);
           	   	    		}
           				cadenaAleatoria=getCadenaAlfanumAleatoria(IConstante.numeroCaracters_Clave);
           				
           				 ls_codigo_documento = daoIUsuarioDAO.of_ingresar_documento(cnx,codigo_oficina,medio,codigo_tipo,ls_seleccion,destino_documento,
                   	    		origen_documento,destinatario,codigo_solicitud,numero_documento,
                				fecha_registro,hora,folios_documento,asunto_documento,
                				observa_documento,usuario_actual,firmadopor,origen_documento
                				,desc_origen,tipo_persona,ls_persona,fecha_rpta,correlativorecepcion,numero_referencia,dirigido,cadenaAleatoria,codigo_intitucion);
               	    	/*
               	    	 * agregado para el envio de correo electronico
               	    	 * **/
    	       				hDestinatario=daoIUsuarioDAO.obtenerDatosUsuario(destinatario);
    	   	   				hDestinatario.put("asunto_documento",asunto_documento);
    	   	   				hDestinatario.put("remitente", hRemitente.get("nombre_personal"));
    	   	   			    //ArrayList<String> myListaCorreos = new ArrayList<String>();
    	   	   			log.info(">>>>>>>>>>>>>>>>> 1");
       	   				myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,destino_documento);
       	   				
       	   			log.info(">>>>>>>>>>>>>>>>> 2");
       	   				//ponemos en una lista de string
       	   				ArrayList<String> misListaCodigosEnString= new ArrayList<String>();
       	   				for (int codigo : myListCodigos) {
       	   					log.info(" ----> "+codigo);
       	   					misListaCodigosEnString.add(String.valueOf(codigo));
       	   					
    					}
       	   			log.info(">>>>>>>>>>>>>>>>> 3");
       	   				//ahora obtendremos los correos
       	   			log.info("Tamaño > "+misListaCodigosEnString.size());
       	   				if(misListaCodigosEnString.size()>0)
       	   				{ myListaCorreosDestinatarios = daoIUsuarioDAO.obtenerDatosUsuario(misListaCodigosEnString);
       	   				} 
       	   			log.info(">>>>>>>>>>>>>>>>> 4");
       	   		//		enviamos los correos	 
       	   				if(myListaCorreosDestinatarios.size()>0){
       	   					//enviarCorreoElectronico(myListaCorreosDestinatarios,ls_codigo_documento,sessionMail,hDestinatario,IConstante.correoTO_Principal);
    	   	   			}
    	   	   			    
    	   	   			//enviarCorreoElectronico(hDestinatario,ls_codigo_documento,sessionMail);
       	   				/*fin
       	   				 * */
           				 
           				 subirArchivosBloque(ls_codigo_documento,session);
           				
           				log.info("La ls_codigo_documento ...YY" + ls_codigo_documento);
           				
           				String ls_codigo_sum=funciones.of_codigo_documento_valida(cnx,ls_codigo_documento);
           				log.info("La ls_codigo_sum ...YY" + ls_codigo_sum);
           				//log.info("Borrando los archivos en sesion");
           				session.removeAttribute("FFormMantDocumento");
           				
           				if(ls_codigo_sum.equals("4")){
           					
           					if(chk_copia!=null){
        		   	     		
        		   	     		log.info("El chk_copia es diferente de null.." +chk_copia);
        		   	     		
        			   	       	     	for(int i=0; cbo_copia!=null && i<cbo_copia.length; i++) 
        				    			{ 
        			        			log.info("El PAR_DES_LAR es diferente de null.." );	
        				    			log.info("Antes del insert Clases.." );	
        				    			//daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
        				    			log.info("las cbo_copia son :" + cbo_copia[i]);
        				    			String ls_cod_persona=funciones.of_codigo_oficina_persona(cnx,cbo_copia[i]);
        				    			
        				    			
        				    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
        				    			
        				    			
        				    			
        				    			if(!codigo_oficina.trim().equals(cbo_copia[i].trim())){
        				    			daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,codigo_oficina,cbo_copia[i],"15",observa_documento
        		   	           	    			,ls_cod_persona,ls_documento,"0",usuario_actual,ls_codigo_documento,li_retorno,
        		   	           	    			fecha_rpta,0,"",codigo_recepcion,"",fecha_rpta,'C',0); ///agregar aqui secuencia_origen
        				    			}
        		   	       	     		}
        			   	       	     	
        		      	     	}
           					
           					ls_numero_expediente   = funciones.of_numero_expediente(cnx, correlativorecepcion);
           					String fecha_correo   = funciones.of_fecha_expediente(cnx, correlativorecepcion);
               				//ls_numero_secuencia   = funciones.of_numero_secuencia(cnx, correlativorecepcion);
           					ls_numero_secuencia ="1";
           					
               				log.info("cadenaAleatoria: "+cadenaAleatoria);
        				    ls_mensaje ="REGISTRO OK :";
               				String ls_mensaje_reg ="Nº DE REGISTRO : "+ls_codigo_documento;
               				String ls_mensaje_exp ="Nº DE EXPEDIENTE : "+ls_numero_expediente;
        					log.info("El ls_mensaje es.." + ls_mensaje);
        					log.info("El ls_fecha es.." + fecha_correo);
                   	    	//log.info("Despues del Ingreso Documento y Direccionando" );
                   	    	
                   	    	/***************************Actualizando la Cabecera Upload Ficheros************************/
              		      
                   	    	String ls_num_doc_upload=daoIUsuarioDAO.ValidaListaDocEntradaDetUpload(ls_codigo_documento);
                   	    	log.info("El ls_num_doc_upload es.." + ls_num_doc_upload);
                   	    	
                   	    	if(Integer.parseInt(ls_num_doc_upload.trim())>=1){
                   	    		//log.info("si es mayor a uno se crea cabecera..");
                   	    		daoIUsuarioDAO.CrearCabDocEntradaUploadIng(codigo_oficina,ls_codigo_documento,ls_numero_expediente.trim(),ls_numero_secuencia,String.valueOf(correlativorecepcion),usuario);    		
                   	    	}
                   	    
                			    
                       	  /*********************Upload de Ficheros*****************/
                       	  
                   	    	
                			
                 			       	
                   	    	session.setAttribute("operacionpopup","2");
                   	    	//session.setAttribute("mensaje",ls_mensaje);
                   	    	session.setAttribute("mensaje_reg",ls_mensaje_reg);
                   	    	session.setAttribute("numero_expediente",ls_numero_expediente);
                   	    	
                   	    	session.setAttribute("mensaje_exp",ls_mensaje_exp);
                   	    	session.setAttribute("mensaje_clave","CLAVE: " + cadenaAleatoria);
                   	    	
                   	    	session.setAttribute("listaFrameBusquedaTemp",null);
                   	    	
                   	    	
                   	    	/**
                   	    	 * envio de correo a usuario que dejo el documento en mesa de partes
                   	    	 */
                   	    	if(!n_correo_electronico.equals("") && n_correo_electronico.length()>0 && !dominio_correo.equals("") && dominio_correo.length()>0 ){
                   	    		n_correo_electronico=n_correo_electronico+"@"+dominio_correo;
                   	    		
                     	    	enviarCorreoElectronicoExterno(fecha_correo,n_correo_electronico,ls_numero_expediente,cadenaAleatoria,sessionMail,IConstante.correoTO_Principal, hDestinatario);
                   	    	}
                   	    		
                   	    	
                   	    	/**
                   	    	 * fin
                   	    	 */

                   	    	return (mapping.findForward("exito"));
                   	    	
           				}else{
           					log.info("Si si la suma es diferente de 3..");
           					funciones.of_codigo_documento_delete(cnx,ls_codigo_documento);
           					
           					ls_mensaje="EL DOCUMENTO NO PUDO REGISTRARSE, SALIR DEL SISTEMA E INGRESAR NUEVAMENTE...!";
               				
               				//log.info("El ls_numdoc_valor es diferente de cero.. "+ ls_numdoc_valor);
               				
               				session.setAttribute("mensajeregistro", ls_mensaje);
               				session.setAttribute("operacion",ls_seleccion);
               				
               				return (mapping.findForward("exito"));
           					                   				
           				}
           				
               	          	    	 
       	     }
       	     
       	  if (ls_seleccion.equals("I")) {
    	    
       		log.info("Aqui Entry I ...");
       		log.info("El origen del documento es interno" );
       			
       		session.removeAttribute("operacion");
   	    	session.removeAttribute("operacionpopup");
   	    	 
   	    	session.removeAttribute("medio");
   	  		session.removeAttribute("codigo_tipo");
   	  		//session.removeAttribute("codigo_oficina");
   	  		session.removeAttribute("destinatario");
   	  		session.removeAttribute("codigo_solicitud");
   	  		session.removeAttribute("numero_documento");
   	  		session.removeAttribute("fecha_registro");
   	  		//session.removeAttribute("hora");
   	  		session.removeAttribute("folios_documento");
   	  		session.removeAttribute("asunto_documento");
   	  		session.removeAttribute("observa_documento");
   	  		session.removeAttribute("firmadopor");
   	  		session.removeAttribute("origen_documento");
   	  		session.removeAttribute("desc_origen");
   	  		session.removeAttribute("tipo_persona");
   	  		session.removeAttribute("cbo_fecharpta");
   	  		session.removeAttribute("fecha_rpta");
   	  		session.removeAttribute("numero_referencia");
   	  		session.removeAttribute("operacion");
   	  		session.removeAttribute("descripcion_persona");
   	  		session.removeAttribute("descripcion_persona_dir");
   	  	
   	  		session.removeAttribute("tipopersona");
   	  		session.removeAttribute("dirigido");
   	  		session.removeAttribute("rs_upload_doc");
   	    	 
   	    	li_retorno = daoIUsuarioDAO.parametro(cnx);
			
	   	 	log.info("li_retorno" + li_retorno);
	   	 	
   	    	correlativorecepcion = String.valueOf(li_retorno);
   	  		log.info("El correlativorecepcion es :" + correlativorecepcion);
   	  		 
   	    	medio     =   request.getParameter("medio");
   	        codigo_tipo     =   request.getParameter("codigo_tipo");
   	        destino_documento     =   request.getParameter("codigo_oficina");
   	        destinatario     =   request.getParameter("destinatario");
   	        codigo_solicitud     =   request.getParameter("codigo_solicitud");
   	        numero_documento     =   request.getParameter("numero_documento");
   	        fecha_registro     =   request.getParameter("fecha_registro");
   	        hora     =   request.getParameter("hora");
   	        folios_documento     =   request.getParameter("folios_documento");
   	        asunto_documento     =   request.getParameter("asunto_documento");
   	        observa_documento     =   request.getParameter("observa_documento");
   	        firmadopor     =   request.getParameter("firmadopor");
   	        origen_documento     =   request.getParameter("origen_documento");
   	        desc_origen     =   request.getParameter("desc_origen");
   	        tipo_persona     =   request.getParameter("tipo_persona");
   	        
   	        cbo_fecharpta     =   request.getParameter("cbo_fecharpta");
   	        fecha_rpta     =   request.getParameter("fecha_rpta");
   	        numero_referencia     =   request.getParameter("numero_referencia");
   	        dirigido     =   request.getParameter("dirigido");
   	        
   	        /**
   	         * JAZANERO
   	         */
   	        codigo_intitucion=request.getParameter("codigo_intitucion");
   	        
   	        chk_copia = ((FFormMantDocumento)form).getChk_copia();
			chk_copias = ((FFormMantDocumento)form).getSelectedItems();
			
			
   	        chk_copia_other = ((FFormMantDocumento)form).getChk_copia_other();
			chk_copias_other = ((FFormMantDocumento)form).getSelectedItems_other();
			
			dirigido=funciones.of_nombre_persona(dirigido);
   	       
   			medio=medio.toUpperCase();
   			codigo_tipo=codigo_tipo.toUpperCase();
   			codigo_oficina=codigo_oficina.toUpperCase();
   			destinatario=destinatario.toUpperCase();
   			codigo_solicitud=codigo_solicitud.toUpperCase();
   			numero_documento=numero_documento.toUpperCase();
   			fecha_registro=fecha_registro.toUpperCase();
   			hora=hora.toUpperCase();
   			folios_documento=folios_documento.toUpperCase();
   			asunto_documento=asunto_documento.toUpperCase();
   			observa_documento=observa_documento.toUpperCase();
   			firmadopor=firmadopor.toUpperCase();
   			desc_origen=desc_origen.toUpperCase();
   			tipo_persona=tipo_persona.toUpperCase();
   			dirigido=dirigido.toUpperCase();
   			
   			
   			String ls_numdoc_valor="";
   			String ls_mensaje="";
   			String ls_numero_expediente ="";
   			
			    
   				if(fecha_rpta==null){
   	    			fecha_rpta="";
   	   	 			//log.info("La fecha_rpta es null de lo contrario es igual a vacio.");
   	   	    		}
   				if(numero_referencia==null || numero_referencia.equals("")){
   					numero_referencia="0";
   	   	 			log.info("La numero_referencia es null o igual a vacio.");
   				}
   				
   					ls_codigo_documento = daoIUsuarioDAO.of_ingresar_documento_interno(cnx,codigo_oficina,medio,codigo_tipo,ls_seleccion,destino_documento.trim(),
   	   	       	    		origen_documento,destinatario,codigo_solicitud,numero_documento,
   	   	    				fecha_registro,hora,folios_documento,asunto_documento,
   	   	    				observa_documento,usuario_actual,firmadopor,origen_documento
   	   	    				,desc_origen,tipo_persona.trim(),ls_persona,fecha_rpta,correlativorecepcion,numero_referencia,dirigido,codigo_intitucion);
   	       	    
   					subirArchivosBloque(ls_codigo_documento,session);
   					/**
   	   	    		 * INICIO DE UPLOAD INTERNOS
   	   	    		 */
   	   	    		       	
   	   	    		     Collection rs_upload_doc_internos_temp = new ArrayList();    	 	
   			   	       	 rs_upload_doc_internos_temp = (Collection) session.getAttribute("rs_upload_doc_internos");
   				  			if(rs_upload_doc_internos_temp!=null && rs_upload_doc_internos_temp.size()>0){
   				  			log.info(">>>>>>>>>>>>>ADJUNTA DOCUMENTOS INTERNOS FIRMADOS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
   				  			subirArchivosBloque(ls_codigo_documento,rs_upload_doc_internos_temp,session);
   				  			}   	 	  		

   	   	    		/**
   	   	    		 * FIN UPLOAD INTERNOS
   	   	    		 */
   	   				log.info("La ls_codigo_documento ...YY :" + ls_codigo_documento);
   	   				
   	   				//recuperarCorreo de destinatario.
   	   				log.info("Correo Principal >>>");
   	   				hDestinatario.put("asunto_documento",asunto_documento);
	   				hDestinatario.put("remitente",hRemitente.get("nombre_personal"));   	   				
   	   				log.info("codigo_destino_oficina_documento:"+destino_documento);   
   	   				//obtenemos los codigos de las persona de la oficina destino
   	   				log.info(">>>>>>>>>>>>>>>>> 1");
   	   				myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,destino_documento);
   	   				
   	   			log.info(">>>>>>>>>>>>>>>>> 2");
   	   				//ponemos en una lista de string
   	   				ArrayList<String> misListaCodigosEnString= new ArrayList<String>();
   	   				for (int codigo : myListCodigos) {
   	   					log.info(" ----> "+codigo);
   	   					misListaCodigosEnString.add(String.valueOf(codigo));
   	   					
					}
   	   			log.info(">>>>>>>>>>>>>>>>> 3");
   	   				//ahora obtendremos los correos
   	   			log.info("Tamaño > "+misListaCodigosEnString.size());
   	   				if(misListaCodigosEnString.size()>0)
   	   				{ myListaCorreosDestinatarios = daoIUsuarioDAO.obtenerDatosUsuario(misListaCodigosEnString);
   	   				} 
   	   			log.info(">>>>>>>>>>>>>>>>> 4");
   	   		//		enviamos los correos	 
   	   				if(myListaCorreosDestinatarios.size()>0)  
   	   				/****
   	   				 * DESACTIVAMOS EMAIL	
   	   				 */
	   	   			{//enviarCorreoElectronico(myListaCorreosDestinatarios,ls_codigo_documento,sessionMail,hDestinatario,IConstante.correoTO_Principal);
	   	   			}
   	   				
   	   				
   	   				
   	   				/*hDestinatario=daoIUsuarioDAO.obtenerDatosUsuario(destinatario);//esto ya no
   	   				hDestinatario.put("asunto_documento",asunto_documento);
   	   				hDestinatario.put("remitente", hRemitente.get("nombre_personal"));
   	   				correo_electronico=hDestinatario.get("email");
   	   				
   	   				log.info("destinatario: " +destinatario);   	   			
   	   				log.info("correo electronico: " +correo_electronico);   	   				
   	   				//enviarCorreo a destinatario con numero de expediente.
   	   				enviarCorreoElectronico(hDestinatario,ls_codigo_documento,sessionMail);*/
   	   				
   	   				session.removeAttribute("FFormMantDocumento");
   					String ls_codigo_sum=funciones.of_codigo_documento_valida(cnx,ls_codigo_documento);
   					log.info("La ls_codigo_sum ...YY" + ls_codigo_sum);
   					
   					if(ls_codigo_sum.equals("4")){
   						
   						
   						if(chk_copia!=null){
   			   	     		
   			   	     		log.info("El chk_copia es diferente de null.." +chk_copia);
   			   	     		
   				   	       	     	for(int i=0; chk_copias!=null && i<  chk_copias.length; i++) 
   					    			{ 
   				        			log.info("El PAR_DES_LAR es diferente de null.." );	
   					    			log.info("Antes del insert Clases.." );	
   					    			//daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
   					    			log.info("las cbo_copia son :" + chk_copias[i]);
   					    			String chk_copias_aux=chk_copias[i];
   					    			String ls_cod_persona=funciones.of_codigo_oficina_persona(cnx,chk_copias_aux);   					    			
   					    			myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,chk_copias_aux);
   					    			
   					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
   					    			if(myListCodigos.size()>0){
   					    				for (int cod : myListCodigos) {
   					    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
										}   					    			
   					    			}
   					    			
   					    			if(!codigo_oficina.trim().equals(chk_copias[i].trim())){
   					    			
   					    			int secuencia_origen = 1;
   					    			daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,codigo_oficina,chk_copias[i],"15",observa_documento
   			   	           	    			,ls_cod_persona,ls_documento,"0",usuario_actual,ls_codigo_documento,li_retorno,
   			   	           	    			fecha_rpta,0,"",codigo_recepcion,"",fecha_rpta,'C',secuencia_origen);
   					    			
   					    			}//if
   					    			
   			   	       	     		}//for
   				   	       	     	
   				   	       	     	
   				   	       	     	/**
   				   					 * Al final recien enviariamos el correo electronico al principal y a las copias.
   				   					 * 
   				   					 * */	
   				   	       	     	
   				   	       	     	log.info("tamanio listaCodigoPersonal: "+myListaCodigoPersonal.size());
   				   	       	     	if(myListaCodigoPersonal.size()>0){
   				   					ArrayList<String> myListaCorreos = daoIUsuarioDAO.obtenerDatosUsuario(myListaCodigoPersonal);
   				   					log.info("tamanio listacorreos: "+myListaCorreos.size());
   				   					// con la lista ahora enviamos los correos respectivos:	
   				   					/**
   				   					 * DESACTIVAMOS EMAIL
   				   					 */
   				   					//enviarCorreoElectronico(myListaCorreos,ls_codigo_documento,sessionMail,hDestinatario, IConstante.correoCC_Copias);
   				   	       	     	}
   			      	     	}//fin if chk_copia  
   						
   					
   						if(chk_copia_other!=null){
   			   	     		
   			   	     		log.info("El chk_copia_other es diferente de null.." +chk_copias_other);
   			   	     		
   				   	       	     	for(int i=0; chk_copias_other!=null && i<  chk_copias_other.length; i++) 
   					    			{ 
   				        			log.info("El PAR_DES_LAR es diferente de null.." );	
   					    			log.info("Antes del insert Clases.." );	
   					    			//daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
   					    			log.info("las cbo_copia son :" + chk_copias_other[i]);
   					    			String chk_copias_aux=chk_copias_other[i];
   					    			String ls_cod_persona=funciones.of_codigo_oficina_persona(cnx,chk_copias_aux);   					    			
   					    			myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,chk_copias_aux);
   					    			
   					    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
   					    			if(myListCodigos.size()>0){
   					    				for (int cod : myListCodigos) {
   					    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
										}   					    			
   					    			}
   					    			
   					    			if(!codigo_oficina.trim().equals(chk_copias_other[i].trim())){
   					    			
   					    			int secuencia_origen = 1;
   					    			daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,codigo_oficina,chk_copias_other[i],"15",observa_documento
   			   	           	    			,ls_cod_persona,ls_documento,"0",usuario_actual,ls_codigo_documento,li_retorno,
   			   	           	    			fecha_rpta,0,"",codigo_recepcion,"",fecha_rpta,'C',secuencia_origen);
   					    			
   					    			}//if
   					    			
   			   	       	     		}//for
   				   	       	     	
   				   	       	     	
   				   	       	     	/**
   				   					 * Al final recien enviariamos el correo electronico al principal y a las copias.
   				   					 * 
   				   					 * */	
   				   	       	     	
   				   	       	     	log.info("tamanio listaCodigoPersonal: "+myListaCodigoPersonal.size());
   				   	       	     	if(myListaCodigoPersonal.size()>0){
   				   					ArrayList<String> myListaCorreos = daoIUsuarioDAO.obtenerDatosUsuario(myListaCodigoPersonal);
   				   					log.info("tamanio listacorreos: "+myListaCorreos.size());
   				   					// con la lista ahora enviamos los correos respectivos:	
   				   					/**
   				   					 * DESACTIVAMOS EMAIL
   				   					 */
   				   					//enviarCorreoElectronico(myListaCorreos,ls_codigo_documento,sessionMail,hDestinatario, IConstante.correoCC_Copias);
   				   	       	     	}
   			      	     	}//fin if chk_copia_other  
   							
   					
   					
   					ls_numero_expediente   = funciones.of_numero_expediente(cnx, correlativorecepcion);
   	   				//ls_numero_secuencia   = funciones.of_numero_secuencia(cnx, correlativorecepcion);
   					ls_numero_secuencia="1";
   					
   				    ls_mensaje ="REGISTRO OK :";
   	   				String ls_mensaje_reg ="Nº DE REGISTRO : "+ls_codigo_documento;
   	   				String ls_mensaje_exp ="Nº DE EXPEDIENTE : "+ls_numero_expediente;
   					log.info("El ls_mensaje es.." + ls_mensaje);
   	   				
   	       	    	//log.info("Despues del Ingreso Documento y Direccionando" );
   	       	    	
   	       	    	/***************************Actualizando la Cabecera Upload Ficheros************************/
   	  		      
   	       	    	String ls_num_doc_upload=daoIUsuarioDAO.ValidaListaDocEntradaDetUpload(ls_codigo_documento);
   	       	    	log.info("El ls_num_doc_upload es.." + ls_num_doc_upload);
   	       	    	
   	       	    	if(Integer.parseInt(ls_num_doc_upload.trim())>=1){
   	       	    		//log.info("si es mayor a uno se crea cabecera..");
   	       	    		daoIUsuarioDAO.CrearCabDocEntradaUploadIng(codigo_oficina,ls_codigo_documento,ls_numero_expediente.trim(),ls_numero_secuencia,correlativorecepcion,usuario);    		
   	       	    	}
   	       	    
   	    				
   	           	  /*********************Upload de Ficheros*****************/
   	           	   
   	       	  log.info("La OFICINA ES..." + codigo_oficina);
     	    	/******************************************************/
  			if(Integer.parseInt(codigo_oficina.trim())>1){
				
				log.info("lA OFICINA ES MAYOR DE 1...");
				BPersona PersonaVO= daoIUsuarioDAO.of_detalle_codigo_persona_oficina(cnx,codigo_oficina);
				//log.info("La ls_persona_oficina es.." + ls_persona_oficina);
				
				String ls_codigo_persona_user=PersonaVO.getCodigo_persona();
				String ls_nombre_persona_user=PersonaVO.getNombre_persona();
				String ls_tipo_user=PersonaVO.getTipo();
				
				log.info("La ls_codigo_persona_user es.." + ls_codigo_persona_user);
				log.info("La ls_nombre_persona_user es.." + ls_nombre_persona_user);
				log.info("La ls_tipo_user es.." + ls_tipo_user);
				
				session.setAttribute("origen_documento",ls_codigo_persona_user);
				session.setAttribute("desc_origen",ls_nombre_persona_user.trim());
				session.setAttribute("tipo_persona","JURIDICA");
				session.setAttribute("naturaleza_documento","JURIDICA");
				session.setAttribute("operacion","T");
				
			}
  		
  			/*********************************************************/
   	     			       	
   	    	session.setAttribute("operacionpopup","1");
   	    	session.setAttribute("mensaje",ls_mensaje);
   	    	session.setAttribute("mensaje_reg",ls_mensaje_reg);
   	    	session.setAttribute("mensaje_exp",ls_mensaje_exp);
   	    	session.removeAttribute("rs_upload_doc_internos");
   	    	
   	    	
   	    	session.setAttribute("listaFrameBusquedaTemp",null);
   	    	return (mapping.findForward("exito"));
   	   		
    		}
   			
       	  }
       	  
       	if (ls_seleccion.equals("W")) {
	    	 
       		log.info("El origen del documento es W, derivar doc entrada a interno" );
       		
       		
       		ls_documento = String.valueOf(session.getAttribute("documento")); 
       		ls_codigoPadre = String.valueOf(session.getAttribute("cod_padre")); 
       		ls_correla   = String.valueOf(session.getAttribute("correlativo"));
       		ls_secuencia_doc   = String.valueOf(session.getAttribute("secuencia"));
       		ls_codigo_expediente   = String.valueOf(session.getAttribute("codigo_expediente"));
       		ls_correlativo_deriva   = String.valueOf(session.getAttribute("correlativo_deriva"));
       		
            codigo_tipo     =   request.getParameter("codigo_tipo");
            numero_documento     =   request.getParameter("numero_documento");
            fecha_registro     =   request.getParameter("fecha_registro");
            asunto_documento     =   request.getParameter("asunto_documento");
            observa_documento     =   request.getParameter("observa_documento");
          
    		codigo_tipo=codigo_tipo.toUpperCase();
    		numero_documento=numero_documento.toUpperCase();
    		fecha_registro=fecha_registro.toUpperCase();
    		asunto_documento=asunto_documento.toUpperCase();
    		observa_documento=observa_documento.toUpperCase();
    		
            	daoIUsuarioDAO.of_ingresar_documento_interno_entrada(cnx,codigo_oficina,codigo_tipo,
         	    origen_documento,numero_documento,fecha_registro,asunto_documento,
      			observa_documento,usuario_actual,ls_persona,ls_codigoPadre,ls_documento,ls_secuencia_doc,ls_nombre_oficina,ls_seleccion,ls_correlativo_deriva);
     	    	
            	session.removeAttribute("documento");
           		session.removeAttribute("cod_padre");
           		session.removeAttribute("correlativo");
           		session.removeAttribute("secuencia");
           		
     	    	log.info("Despues del Ingreso Documento Interno y Direccionando" );
            	
				return (mapping.findForward("exito"));	
				
     	     }
       	break;
       	 case 2:
       		log.info("Entry ... case 2");
       		request.setAttribute("caso", "caso__2");
                log.info("Modificar Documento.........." );
               
       	    	session.removeAttribute("operacion");
       	    	session.removeAttribute("operacionpopup");
       	    	 
       	    	li_retorno = daoIUsuarioDAO.parametro(cnx);
				
		   	 	log.info("li_retorno" + li_retorno);
		   	 	
       	  		codigo_documento     =   request.getParameter("codigo_documento");
       	  		codigo_expediente     =   Integer.valueOf(request.getParameter("codigo_expediente").trim());
       	  		secuencia_movimiento     =   request.getParameter("secuencia_movimiento");
       	    	medio     =   request.getParameter("medio");
       	        codigo_tipo     =   request.getParameter("codigo_tipo");
       	        destino_documento     =   request.getParameter("codigo_oficina");
       	        destinatario     =   request.getParameter("destinatario");
       	        codigo_solicitud     =   request.getParameter("codigo_solicitud");
       	        numero_documento     =   request.getParameter("numero_documento");
       	        fecha_registro     =   request.getParameter("fecha_registro");
       	        hora     =   request.getParameter("hora");
       	        folios_documento     =   request.getParameter("folios_documento");
       	        asunto_documento     =   request.getParameter("asunto_documento");
       	        observa_documento     =   request.getParameter("observa_documento");
       	        firmadopor     =   request.getParameter("firmadopor");
       	        origen_documento     =   request.getParameter("origen_documento");
       	        desc_origen     =   request.getParameter("desc_origen");
       	        tipo_persona     =   request.getParameter("tipo_persona");
       	        
       	        cbo_fecharpta     =   request.getParameter("cbo_fecharpta");
       	        fecha_rpta     =   request.getParameter("fecha_rpta");
       	        numero_referencia     =   request.getParameter("numero_referencia");
       	        codigo_recepcion     =   Integer.valueOf(request.getParameter("codigo_recepcion").trim());
       	        dirigido     =   request.getParameter("dirigido");
       	        
       			medio=medio.toUpperCase();
       			codigo_tipo=codigo_tipo.toUpperCase();
       			codigo_oficina=codigo_oficina.toUpperCase();
       			
       			asunto_documento=asunto_documento.toUpperCase();
       			observa_documento=observa_documento.toUpperCase();
       			firmadopor=firmadopor.toUpperCase();
       			desc_origen=desc_origen.toUpperCase();
       			dirigido=dirigido.toUpperCase();
       			
       			
       			String ls_numdoc_valor="";
       			String ls_mensaje="";
       			String ls_numero_expediente ="";
       			ls_numdoc_valor=funciones.of_numero_documento(cnx, numero_documento);
       			log.info("El ls_numdoc_valor es.. "+ ls_numdoc_valor);
       			//Funciones funciones=new Funciones();
       				
       				//Funciones funciones=new Funciones();
				    
       				if(fecha_rpta==null){
       	    			fecha_rpta="";
       	   	 			log.info("La fecha_rpta es null de lo contrario es igual a vacio.");
       	   	    		}
       				if(numero_referencia==null || numero_referencia.equals("")){
       					numero_referencia="0";
       	   	 			log.info("La fecha_rpta es null de lo contrario es igual a cero." +numero_referencia);
       	   	    		}
     
       				daoIUsuarioDAO.of_modificar_documento(cnx, codigo_documento, codigo_expediente,
       						secuencia_movimiento, codigo_oficina, medio, codigo_tipo, ls_seleccion,
       						destino_documento, origen_documento, destinatario, codigo_solicitud,
       						numero_documento, fecha_registro, hora, folios_documento, asunto_documento, 
       						observa_documento, usuario_actual, firmadopor, desc_origen, tipo_persona,
       						destinatario, fecha_rpta, codigo_recepcion, numero_referencia,dirigido);
       				
       				
       				
           	    	log.info("Despues del Ingreso Documento y Direccionando" );
           	    	
           	    	/***************************Actualizando la Cabecera Upload Ficheros************************/
      		      
           	    	String ls_num_doc_upload=daoIUsuarioDAO.ValidaListaDocEntradaDetUpload(codigo_documento);
           	    	log.info("El ls_num_doc_upload es.." + ls_num_doc_upload);
           	    	
           	    	if(Integer.parseInt(ls_num_doc_upload.trim())>=1){
           	    		log.info("si es mayor a uno se crea cabecera..");
           	    		daoIUsuarioDAO.CrearCabDocEntradaUploadIng(codigo_oficina,codigo_documento,String.valueOf(codigo_expediente),secuencia_movimiento,String.valueOf(codigo_recepcion) ,usuario);    		
           	    	}
           	    
        			    //daoIResponsableColaboradoresDAO.UpdateCabColaboradores(ls_id_proyecto, id_investigador,Integer.parseInt(ls_id_investigador.trim()),Integer.parseInt(parameter.trim()));
        				log.info("Despues del Ingreso Cabecera Registro Entrada upload." );	
        				
               	  /*********************Upload de Ficheros*****************/
               	     
        		BMesaPartes BMesaPartesVO=daoIUsuarioDAO.of_consulta_documento(cnx, codigo_documento, secuencia_movimiento);
         			       	
           	    	
           	    	session.setAttribute("operacion","M");
           	    	session.setAttribute("docEntrada", BMesaPartesVO);
           	    	
           	    	return (mapping.findForward("exitomod"));
           	    	
       	 case 3:
       		request.setAttribute("caso", "caso__3");
       		log.info(" Eliminar documento" );
       		
       		
       		 documento = String.valueOf(session.getAttribute("documento"));
       		 oficina = String.valueOf(session.getAttribute("oficina"));
       		 secuencia = String.valueOf(session.getAttribute("secuencia"));
       		
       		log.info("El documento " + documento );
       		log.info("El oficina " + oficina );
       		log.info("El secuencia " + secuencia );
       		
       		daoIUsuarioDAO.of_eliminar_documento(cnx,documento,oficina,secuencia);
   	    	
   	    	log.info("Despues de Eliminar Documento y Direccionando" );
   	    	
                
				return (mapping.findForward("exito"));	
				
       	case 4:
     	 	log.info("Entry case 4 Adjuntar documento");
       		
       		request.setAttribute("caso", "caso__4");
       		//session.removeAttribute("contador");
       		
       		medio     =   request.getParameter("medio");
       		medio_rq     =   request.getParameter("medio");
   	        codigo_tipo     =   request.getParameter("codigo_tipo");
   	        destino_documento     =   request.getParameter("codigo_oficina");
   	        codigo_oficina_rq     =   request.getParameter("codigo_oficina");
   	        destinatario     =   request.getParameter("destinatario");
   	        codigo_solicitud     =   request.getParameter("codigo_solicitud");
   	        numero_documento     =   request.getParameter("numero_documento");
   	        fecha_registro     =   request.getParameter("fecha_registro");
   	        hora     =   request.getParameter("hora");
   	        folios_documento     =   request.getParameter("folios_documento");
   	        asunto_documento     =   request.getParameter("asunto_documento");
   	        observa_documento     =   request.getParameter("observa_documento");
   	        firmadopor     =   request.getParameter("firmadopor");
   	        origen_documento     =   request.getParameter("origen_documento");
   	        desc_origen     =   request.getParameter("desc_origen");
   	        tipo_persona     =   request.getParameter("tipo_persona");
   	        
   	        cbo_fecharpta     =   request.getParameter("cbo_fecharpta");
   	        fecha_rpta     =   request.getParameter("fecha_rpta");
   	        numero_referencia     =   request.getParameter("numero_referencia");
   	        
   	        desc_origen     =   request.getParameter("desc_origen");
   	        naturaleza_documento     =   request.getParameter("naturaleza_documento");
   	        tipo     =   request.getParameter("tipo");
   	        operacionper     =   request.getParameter("operacionper");
   	        dirigido     =   request.getParameter("dirigido");
   	        
			String ls_dirigido_a= request.getParameter("dirigido_a");
            session.setAttribute("ls_dirigido_a", ls_dirigido_a);
            log.info("memoria------------->"+ls_dirigido_a);
   	        
   	       /****revisar URGENTE***/
       		ls_codigo_documento=funciones.cab_max_upload(cnx);
       		log.info("El ls_cabecera_upload Codigo documento es..."+ ls_codigo_documento );
       		
       		/*
			rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos_doc_entrada(cnx,Integer.parseInt(ls_codigo_documento.trim()));
   	    	log.info("El rs_upload_doc XXXXXXXX es..."+ rs_upload_doc );
			
			String ls_contador=funciones.of_contador_upload_doc(cnx, Integer.parseInt(ls_codigo_documento.trim()));
			log.info("El ls_contador es..."+ ls_contador );
			
			if(ls_contador.equals("0")){
				log.info("Contador igual a vacio e igual a 0..." );
				session.setAttribute("contador", "0");
				
			}else{
				log.info("Contador diferente de vacio e igual a 1..." );
				session.setAttribute("contador", "1");
			}**/
			
			
			session.setAttribute("operacion", "N");
			//session.setAttribute("rs_upload_doc", rs_upload_doc);
			session.setAttribute("cabecera_upload", ls_codigo_documento);
			session.setAttribute("medio", medio);
			session.setAttribute("medio_rq", medio_rq);
			session.setAttribute("codigo_oficina_rq", codigo_oficina_rq);
			session.setAttribute("codigo_tipo", codigo_tipo);
			session.setAttribute("destinatario", destinatario);
			session.setAttribute("codigo_solicitud", codigo_solicitud);
			session.setAttribute("numero_documento", numero_documento);
			session.setAttribute("fecha_registro", fecha_registro);
			session.setAttribute("hora", hora);
			session.setAttribute("folios_documento", folios_documento);
			session.setAttribute("asunto_documento", asunto_documento);
			session.setAttribute("observa_documento", observa_documento);
			session.setAttribute("firmadopor", firmadopor);
			session.setAttribute("origen_documento", origen_documento);
			session.setAttribute("codigo_oficina", codigo_oficina);
			session.setAttribute("desc_origen", desc_origen);
			session.setAttribute("tipo_persona", tipo_persona);
			session.setAttribute("cbo_fecharpta", cbo_fecharpta);
			session.setAttribute("fecha_rpta", fecha_rpta);
			session.setAttribute("numero_referencia", numero_referencia);
			session.setAttribute("desc_origen", desc_origen);
			session.setAttribute("naturaleza_documento", naturaleza_documento);
			session.setAttribute("tipo", tipo);
			session.setAttribute("dirigido", dirigido);
			//session.setAttribute("operacionper", operacionper);
			
   	    	log.info("Despues de Abrir el Popup...");    
				return (mapping.findForward("upload"));	
       	case 5:	
       		
       		
       		request.setAttribute("caso", "caso__5");
       		log.info("Adjuntar multiples documentos.." );
       		
       		log.info("Si tipo es init es.." + tipo);
       		log.info("Listar UploadMultiples Ficheros Archivos..." );			
			ls_cabecera_upload=request.getParameter("cabecera_upload");
			log.info("El ls_cabecera_upload..." + ls_cabecera_upload);
			 
			/*********************Upload de Ficheros*****************/
			 BArchivo archivo = new BArchivo();
				

				archivo.setUsuario(usuario);
				FFormMantDocumento myForm = (FFormMantDocumento) form;

				FormFile myFile = myForm.getTheFile();
				String contentType = myFile.getContentType();
				log.info("el contentType FILE UPLOAd es.."
						+ contentType);

				if(!myFile.getFileName().equals("")){
					archivo.setNombre_archivo(myFile.getFileName());
					log.info("el fileName es.."
							+ archivo.getNombre_archivo());

					archivo.setData(myFile.getFileData());
					
					/**
					 * jazanero
					 */
					
					log.info("JM el codigo de oficina es:  "
							+ codigo_oficina);
					
					String ruta_detalle_upload=service.getRutaDetalleUpload(codigo_oficina);
					
					archivo.setRutaDetalleUpload(ruta_detalle_upload);
					
					if (myForm.getArchivos() == null) {
						Collection archivos = new ArrayList();
						archivos.add(archivo);
						myForm.setArchivos(archivos);
					} else {
						myForm.getArchivos().add(archivo);
					}
				}
				
				//String filePath = Constantes.CarpetaArchivosProduccion.getNombre();
				//String fileName=myFile.getFileName();
				//int ls_id_proyecto=0;
				//String ls_new_nombre_upload="";
 	   /*
 			       	  
 			       	  //log.info("el fileName es.." + fileName);
 			          
 			         
 			       	  byte[] fileData    = myFile.getFileData();
 			          log.info("el fileData es.." + fileData);
 				      
 			         
 			         
 				      log.info("el filePath LLLLLLUUUU es.." + filePath);
 				      int ls_id_proyecto=0;
 				     
 				     
 				      if(!fileName.equals("")){  
 				     	 
 				         int punto = fileName.lastIndexOf('.');
 				        log.info("el puntoLUUUU es.." + punto);
 				       log.info("el puntoLUUUU + 1 es.." + fileName.substring(punto + 1));
 				           if(fileName.substring(punto + 1).equals("DOC")){
				        	
 	 				    	  fileName = fileName.replace("DOC","doc");
 					        	log.info("El Cambiando el nombre a miniscula..XXXXXX:"+fileName);
 					        }
 				        
 				       log.info("El Case es 5..");
				    
				        ls_new_nombre_upload=funciones.of_valida_letras(fileName);	
				        
				        if(funciones.validaNombreDelArchivo(ls_new_nombre_upload)){
				           log.info("El ls_new_nombre_upload ees.." + ls_new_nombre_upload);
				        
				          File fileToCreate = new File(filePath, ls_new_nombre_upload);
 				                     
 				          if(!fileToCreate.exists()){
 				            FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
 				            fileOutStream.write(myFile.getFileData());
 				            fileOutStream.flush();
 				            fileOutStream.close();
 				          } 
 				          daoIUsuarioDAO.CrearDocumentoDocEntradaDetUploadIng(Integer.parseInt(ls_cabecera_upload.trim()), ls_new_nombre_upload, filePath,"A","P",usuario);
 				         request.setAttribute("mensaje_archivo_no_validado", "");
				        }else{
				        	request.setAttribute("mensaje_archivo_no_validado", "El archivo "+ fileName+ " presenta caracteres especiales");
				        }
 				    
 			      }
 				  //***************************FIn upload****************************
 				      
 				
 				   
 					
 				   log.info("Antes del contador.." );
 				  ls_contador=funciones.of_contador_upload_doc(cnx, Integer.parseInt(ls_cabecera_upload.trim()));
 				
 				log.info("El ls_contador es..."+ ls_contador );
 				
 				if(ls_contador.equals("0")){
 					log.info("Contador igual a vacio e igual a 0..." );
 					session.setAttribute("contador", "0");
 					
 				}else{
 					log.info("Contador diferente de vacio e igual a 1..." );
 					session.setAttribute("contador", "1");
 				}
 				*/ 
 				session.setAttribute("operacion", "N");
 				 //rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos_doc_entrada(cnx,Integer.parseInt(ls_cabecera_upload.trim()));
				//session.setAttribute("rs_upload_doc",rs_upload_doc);
 				 //session.setAttribute("contador","1");
				return (mapping.findForward("upload"));
case 51:	
       		
       		
       		request.setAttribute("caso", "caso__51");
       		log.info("Reemplazar documento" );
       		
       		String id_detalle_doc =request.getParameter("id_det");
       		int id_det=Integer.parseInt(id_detalle_doc);
       		log.info("Si tipo es init es.." + tipo);
       			
			
			 
			/*********************Upload de Ficheros*****************/
			 BArchivo archivo2 = new BArchivo();
				

				archivo2.setUsuario(usuario);
				FFormMantDocumento myForm2 = (FFormMantDocumento) form;

				FormFile myFile2 = myForm2.getTheFile();
				String contentType2 = myFile2.getContentType();
				log.info("el contentType FILE UPLOAd es.."
						+ contentType2);

				if(!myFile2.getFileName().equals("")){
					archivo2.setNombre_archivo(myFile2.getFileName());
					log.info("el fileName es.."
							+ archivo2.getNombre_archivo());

					archivo2.setData(myFile2.getFileData());
					
					/**
					 * jmendoza
					 */
					
					log.info("JM el codigo de oficina es:  "
							+ codigo_oficina);
					
					String ruta_detalle_upload=service.getRutaDocumentoUpload(id_det);
					
					archivo2.setRutaDetalleUpload(ruta_detalle_upload);
					
					if (myForm2.getArchivos() == null) {
						Collection archivos = new ArrayList();
						archivos.add(archivo2);
						myForm2.setArchivos(archivos);
					} else {
						myForm2.getArchivos().add(archivo2);
					}
				}
				
				//String filePath = Constantes.CarpetaArchivosProduccion.getNombre();
				//String fileName=myFile.getFileName();
				//int ls_id_proyecto=0;
				//String ls_new_nombre_upload="";
 	   /*
 			       	  
 			       	  //log.info("el fileName es.." + fileName);
 			          
 			         
 			       	  byte[] fileData    = myFile.getFileData();
 			          log.info("el fileData es.." + fileData);
 				      
 			         
 			         
 				      log.info("el filePath LLLLLLUUUU es.." + filePath);
 				      int ls_id_proyecto=0;
 				     
 				     
 				      if(!fileName.equals("")){  
 				     	 
 				         int punto = fileName.lastIndexOf('.');
 				        log.info("el puntoLUUUU es.." + punto);
 				       log.info("el puntoLUUUU + 1 es.." + fileName.substring(punto + 1));
 				           if(fileName.substring(punto + 1).equals("DOC")){
				        	
 	 				    	  fileName = fileName.replace("DOC","doc");
 					        	log.info("El Cambiando el nombre a miniscula..XXXXXX:"+fileName);
 					        }
 				        
 				       log.info("El Case es 5..");
				    
				        ls_new_nombre_upload=funciones.of_valida_letras(fileName);	
				        
				        if(funciones.validaNombreDelArchivo(ls_new_nombre_upload)){
				           log.info("El ls_new_nombre_upload ees.." + ls_new_nombre_upload);
				        
				          File fileToCreate = new File(filePath, ls_new_nombre_upload);
 				                     
 				          if(!fileToCreate.exists()){
 				            FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
 				            fileOutStream.write(myFile.getFileData());
 				            fileOutStream.flush();
 				            fileOutStream.close();
 				          } 
 				          daoIUsuarioDAO.CrearDocumentoDocEntradaDetUploadIng(Integer.parseInt(ls_cabecera_upload.trim()), ls_new_nombre_upload, filePath,"A","P",usuario);
 				         request.setAttribute("mensaje_archivo_no_validado", "");
				        }else{
				        	request.setAttribute("mensaje_archivo_no_validado", "El archivo "+ fileName+ " presenta caracteres especiales");
				        }
 				    
 			      }
 				  //***************************FIn upload****************************
 				      
 				
 				   
 					
 				   log.info("Antes del contador.." );
 				  ls_contador=funciones.of_contador_upload_doc(cnx, Integer.parseInt(ls_cabecera_upload.trim()));
 				
 				log.info("El ls_contador es..."+ ls_contador );
 				
 				if(ls_contador.equals("0")){
 					log.info("Contador igual a vacio e igual a 0..." );
 					session.setAttribute("contador", "0");
 					
 				}else{
 					log.info("Contador diferente de vacio e igual a 1..." );
 					session.setAttribute("contador", "1");
 				}
 				*/ 
 				session.setAttribute("operacion", "N");
 				 //rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos_doc_entrada(cnx,Integer.parseInt(ls_cabecera_upload.trim()));
				//session.setAttribute("rs_upload_doc",rs_upload_doc);
 				 //session.setAttribute("contador","1");
				return (mapping.findForward("uploadadmin"));
						
       	case 6:
       		log.info("Entry case 6 lista ver documento adjunto");
       		request.setAttribute("caso", "caso__6");
       		//log.info("Lista Ver documento Adjunto.." );
       		
       		ls_codigo_documento=request.getParameter("codigo_documento");
       		String ls_secuencia_movimiento=request.getParameter("secuencia_movimiento");
       		
       		log.info("El ls_codigo_documento es..."+ ls_codigo_documento );
       		log.info("El ls_secuencia_movimiento  es..."+ ls_secuencia_movimiento );
       		
			rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos_doc_entrada(cnx,Integer.parseInt(ls_codigo_documento.trim()),Integer.parseInt("2"));
   	    	
			
     		session.setAttribute("contador",rs_upload_doc.size());
			session.setAttribute("operacion", "M");
			session.setAttribute("rs_upload_doc", rs_upload_doc);
			session.setAttribute("cabecera_upload", ls_codigo_documento);
			session.setAttribute("cabecera_secuencia", "2");
			
   	    	log.info("Despues de Abrir el Popup...");    
				return (mapping.findForward("upload"));	
				
       	case 7:	
     	 	log.info("case 7 entry ..., adjuntar archivos multiples ...");
       		request.setAttribute("caso", "caso__7");
       		log.info("Adjuntar multiples documentos.." );
       		
       		log.info("Si tipo es init es.." + tipo);
			log.info("Listar UploadMultiples Ficheros Archivos..." );
			
			 ls_cabecera_upload=request.getParameter("cabecera_upload");
			 ls_cabecera_secuencia=request.getParameter("cabecera_secuencia");
			 
			 log.info("El ls_cabecera_upload..." + ls_cabecera_upload);
			 
			/*********************Upload de Ficheros*****************/
			
			FFormMantDocumento myForm1 = (FFormMantDocumento)form;
			//codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
			//log.info("El codigo_documento FOR..." + codigo_documento);
 		        // Process the FormFile
 				      FormFile myFile1 = myForm1.getTheFile();
 				      String contentType1 = myFile1.getContentType();
 				      log.info("el contentType1 FILE UPLOAd es.." + contentType1);
 				     log.info("El Case es 7..");
 			       	  String fileName1    = myFile1.getFileName();
 			       	log.info("el fileName1 es.." + fileName1);
 			       	
 			          //int fileSize       = myFile.getFileSize();
 			          byte[] fileData1    = myFile1.getFileData();
 			          log.info("el fileData1 es.." + fileData1);
 				      //Get the servers upload directory real path name
 			          
 			          //local host public static final String carpeta = "c:/upload/sisFonCur/";
 			        //produccion public static final String carpeta = "/upload/sisFonCur/";
 			          
 				      // filePath = getServlet().getServletContext().getRealPath("/") +"uploadDocumentos";
 			          //filePath = "c:/upload/docTramite/";
 			         //filePath = "/home/PROYECTOS/PROYECTO01/upload/docTramite/";
 		//	         filePath = Constantes.CarpetaArchivosProduccion.getNombre();
 			         //filePath = "/var/www/virtual/dsicconcytec.com/htdocs/docTramite/";
 			         //filePath = "/var/www/virtual/dsicconcytec.com/htdocs/";
 			         // filePath = "/upload/docTramite";
 			         //filePath = "/documents/";
 			          
 				      //log.info("el filePath es.." + filePath);
 				       int ls_id_proyecto=0;
 				    // String ls_new_nombre_upload="";
 				      /* Save file on the server */
 				      if(!fileName1.equals("")){  
 				        //  log.info("Server path:" +filePath);
 				          //Create file
 				         int punto = fileName1.lastIndexOf('.');
 				        log.info("el puntoLUUUU+1 es.." + fileName1.substring(punto + 1));
 				        
 				       if(fileName1.substring(punto + 1).equals("DOC")){
				        	
 				    	  fileName1 = fileName1.replace("DOC","doc");
				        	log.info("El Cambiando el nombre a miniscula..XXXXXX:"+fileName1);
				        }
				         	//fileName.substring(punto + 1);
				        //ls_new_nombre_upload=funciones.of_generar_nombre_upload(cnx,Integer.parseInt(ls_cabecera_upload.trim()));
				        
				       //  log.info("El ls_new_nombre_upload es...TTTT:" + ls_new_nombre_upload);
				        String ls_new_nombre_upload=funciones.of_valida_letras(fileName1);
				        
				       // log.info("El ls_new_nombre_upload+1..XXXXXX:"+ls_new_nombre_upload.substring(punto + 1));
				        /*if(ls_new_nombre_upload.substring(punto + 1).equals("DOC")){
				        	
				        	ls_new_nombre_upload = ls_new_nombre_upload.replace("DOC","doc");
				        	log.info("El Cambiando el nombre a miniscula..XXXXXX:"+ls_new_nombre_upload);
				        }*/
				        
				       //  log.info("El ls_new_nombre_upload es...XXXXXX:" + ls_new_nombre_upload);
				        //ls_new_nombre_upload=
				          File fileToCreate = new File(filePath, ls_new_nombre_upload);
 				         // File fileToCreate = new File(filePath, fileName);
 				        //  log.info("el fileToCreate es.." + fileToCreate);
 				          //If file does not exists create file                      
 				          if(!fileToCreate.exists()){
 				            FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
 				            //Writer out=new OutputStreamWriter(fileOutStream, "UTF-16");
 				            
 			            //log.info("el fileOutStream es.." + fileOutStream);
 			            //log.info("el myFile.getFileData es.." + myFile.getFileData());
 			            fileOutStream.write(myFile1.getFileData());
 			            fileOutStream.flush();
 			            fileOutStream.close();
 			          }  
 				    //  log.info("Antes Ingreso PROYECTO INVESTIGACION UPLOAD");
 				     // daoIProyectoInvestigacionDAO.ProyectoInvestigacionUpload(Integer.parseInt(ls_id_investigador.trim()),fileName,filePath,ls_id_proyecto);       
 				     daoIUsuarioDAO.CrearDocumentoDocEntradaDetUploadIng(Integer.parseInt(ls_cabecera_upload.trim()), ls_new_nombre_upload, filePath,"I","P",usuario,"O");
 				    //  log.info("Despues del Ingreso PROYECTO INVESTIGACION");
 			      }
 				  /***************************FIn upload*****************************/
 				      
 				//   log.info("Despues del Ingreso Upload de Ficheros Proy Invest" );
 				   
 				  //Collection rs_upload_doc = new ArrayList();
 				    rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos_doc_entrada(cnx,Integer.parseInt(ls_cabecera_upload.trim()),0);
 					//rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos(cnx,Integer.parseInt(ls_cabecera_upload.trim()));
 					
 				//	log.info("el rs_upload_doc es ......"+ rs_upload_doc);
 				    
 				  
 				    
 				   
 				 session.setAttribute("operacion", "M");
 				 session.setAttribute("rs_upload_doc",rs_upload_doc);
 				 session.setAttribute("contador",rs_upload_doc.size());
				return (mapping.findForward("upload"));	
				
        case 8:
        	request.setAttribute("caso", "caso__8");
       		log.info(" Eliminar documento Upload modificar" );
       		
       		
       		String ls_id_det_upload = request.getParameter("id_det_upload");
       		ls_codigo_documento = request.getParameter("codigo_documento");
       		String nombre_archivo = request.getParameter("nombre_archivo");
       		log.info("El ls_id_det_upload " + ls_id_det_upload );
       		log.info("El ls_codigo_documento " + ls_codigo_documento );
       		
       		daoIUsuarioDAO.of_eliminar_documento_upload(cnx,ls_id_det_upload,ls_codigo_documento);
       		
       		log.info("nombre archivo: "+nombre_archivo);       		
			eliminarArchivoFisicamente(nombre_archivo);
   	    	log.info("Despues de Eliminar Documento y Direccionando" );
   	    	
   	    	rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos_doc_entrada(cnx,Integer.parseInt(ls_codigo_documento.trim()),0);
			//rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos(cnx,Integer.parseInt(ls_cabecera_upload.trim()));
			
		//	log.info("el rs_upload_doc es ......"+ rs_upload_doc);
			
   	    	session.setAttribute("operacion", "M");
   	    	session.setAttribute("rs_upload_doc",rs_upload_doc);                
   	    	return (mapping.findForward("upload"));
   	    	
   	 	
        case 9:
        	log.info(">>>>>>>>>>>>>DENTRO DE ADJUNTAR DOCUMENTO INTERNO FIRMADO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        	log.info(">>>>>>>>>>>>>ESTE PROCESO SE REALIZARA INTERNAMENTE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        	log.info(">>>>>>>>>>>>>LLEGAMOS AQUI AL ESCOJER EL DOCUMENTO DE LA LISTA EN BANDEJA<<<<<<<<<<<<<<<<<");
        	
        	request.setAttribute("caso", "caso__9");
       		log.info(" Entre a Adjuntar Documentos Internos....................... terminando por fin" );
       	
       		/*********** QUE HAGO ****************/	      		       		
       		/***ADJUNTO EL DOUCUMENTO INTERNO A LA CABECERA****/
	  		/****CARGA LA CABECERA - LISTA DE DOCUMENTOS ADJUNTADOS*****/
       		ls_codigo_documento=funciones.cab_max_upload(cnx);
       		log.info("El ls_cabecera_upload Codigo documento es..."+ ls_codigo_documento );

			
	  		/**********PONGO EL NOMBRE DEL DOCUMENTO EN EL FORMULARIO********************/
			/**************UPLOAD DE FICHEROS***************/
       		
			log.info("El ls_cabecera_upload..." + ls_codigo_documento);
			 
			/*********************Upload de Ficheros*****************/
			 BArchivo archivointerno = new BArchivo();
			 int  codigo_documento_interno=Integer.parseInt((String)request.getParameter("codigo"));
			 DocumentoInternoBean documentoInterno = service.getDocumentoInterno(codigo_documento_interno,"adjunta");
			 int contador=0;
			 
			 String si_documento_interno = (String) session.getAttribute("documento_interno_presente");
			 si_documento_interno = si_documento_interno == null ? "" : si_documento_interno;
 	  		
			 
			 archivointerno.setUsuario(usuario_actual); //CARGO EL BEAN QUE GUARDARA LOS DATOS QUE GRABARE DESPUES
				archivointerno.setNombre_archivo(documentoInterno.getNombre_arhivo()); //grabo el nombre en el bean archivo
				archivointerno.setTipo_archivo(documentoInterno.getCodigo_documento_interno().toString());
				log.info("el fileName es.."+ archivointerno.getNombre_archivo());
				
				
	  		if(si_documento_interno.equals("1")){
	  			log.info("El formulario contiene un documento interno");
	  			rs_upload_doc_internos_adj = (Collection) session.getAttribute("rs_upload_doc_internos");
	  			if(rs_upload_doc_internos_adj!=null && rs_upload_doc_internos_adj.size()>0){
	  				Iterator it=rs_upload_doc_internos_adj.iterator();
	  				while(it.hasNext()){
	  					BArchivo archivoenlista=(BArchivo)it.next();
	  					if(archivoenlista.getNombre_archivo().equals(archivointerno.getNombre_archivo())){
	  						contador+=1;
	  					}
	  				
	  				}
	  			}
	  			if(contador==0){
	  				rs_upload_doc_internos_adj.add(archivointerno); //agrego el bean archivo en la lista
	  				session.setAttribute("rs_upload_doc_internos", rs_upload_doc_internos_adj);
	  			}
	  			
		  		session.setAttribute("operacion", "FI"); //para el reg_doc_interno.jsp
		
	  			
	  		}else{
	  			log.info("No existe aun documentos internos");
	  			rs_upload_doc_internos_adj.add(archivointerno);
	  			
	  			
	  			session.setAttribute("rs_upload_doc_internos", rs_upload_doc_internos_adj);
	  			session.setAttribute("operacion", "T"); //para el reg_doc_interno.jsp
	  		}
			 		 
			
	  		/***********************FIN****************************/
	  		
	  		///TENGO QUE INDICAR QUE HAY DOCUMENTOS INTERNOS PRESENTES
	  		session.setAttribute("documento_interno_presente", "1");
	  		///PARA VISUALIZAR LA LISTA MIXTA EN EL UPLOAD
    		session.setAttribute("vistamixta", "X");//para la pagina uploadpopup.jsp
    		
    		//session.setAttribute("cabecera_upload", ls_codigo_documento);//CODIGO DEL REGISTRO A CREAR EL CUAL SE GRABARA EN DETALLE UPLOAD //AGREGUE ESTO AL FINAL
    		
    		log.info(">>>>>>>>>>>>>FIN DE PROCESO INTERNO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
   	    	return (mapping.findForward("exito"));
   	    	
   	    	
        case 10:
        	log.info(">>>>>>>>>>>>>DENTRO DE ADJUNTAR DOCUMENTO INTERNO FIRMADO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        	log.info(">>>>>>>>>>>>>ESTE PROCESO SE REALIZARA INTERNAMENTE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        	log.info(">>>>>>>>>>>>>LLEGAMOS AQUI AL ESCOJER EL DOCUMENTO DE LA BANDEJA POPUP DE CREACION DE DOCUMENTOS INTERNOS<<<<<<<<<<<<<<<<<");
        	
        	request.setAttribute("caso", "caso__10");
       		log.info(" Entre a Adjuntar Documentos Internos....................... terminando por fin" );
       	
       		/*********************************/
       		/*********************Upload de Ficheros*****************/
			 BArchivo archivodocinterno = new BArchivo();
			 int  codigo_documento_interno_firmado=Integer.parseInt((String)request.getParameter("codigo"));
			 DocumentoInternoBean documento_interno_firm = service.getDocumentoInterno(codigo_documento_interno_firmado,"adjunta");
			 int conta=0;
			 
			 archivodocinterno.setUsuario(usuario_actual); //CARGO EL BEAN QUE GUARDARA LOS DATOS QUE GRABARE DESPUES
			 archivodocinterno.setNombre_archivo(documento_interno_firm.getNombre_arhivo()); //grabo el nombre en el bean archivo
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
	  		}else{
	  			if(conta==0){
	  				rs_upload_doc_internos_temp.add(archivodocinterno); //agrego el bean archivo en la lista
	  				session.setAttribute("rs_upload_doc_internos", rs_upload_doc_internos_temp);
	  			}
	  		}
	  			
	  		/***********************FIN****************************/
       		
    		log.info(">>>>>>>>>>>>>FIN DE PROCESO INTERNO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
   	    	return (mapping.findForward("uploadinterno"));
   	    	
   	    	
        case 11:
        	request.setAttribute("caso", "caso__11");
        	log.info(">>>>>>>>>>>>>DENTRO DE ELIMINAR DOCUMENTO INTERNO FIRMADO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        	log.info(">>>>>>>>>>>>>ESTE PROCESO SE REALIZARA INTERNAMENTE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        	log.info(">>>>>>>>>>>>>LLEGAMOS AQUI AL ESCOJER EL DOCUMENTO DE LA LISTA IMAGINARIA EN BANDEJA POPUP DE CREACION DE DOCUMENTOS INTERNOS<<<<<<");
        	BArchivo archivodocinternoeliminar = new BArchivo();
        	String nombre=(String)request.getParameter("nombre");
        	//String codigo_documento_eliminar=(String)request.getParameter("cod_doc_interno");
						 
        	Collection rs_upload_doc_internos_eliminar = new ArrayList();	
        	Collection temporal = new ArrayList();
        	
        	rs_upload_doc_internos_eliminar = (Collection) session.getAttribute("rs_upload_doc_internos");
        		
        	
	  		if(rs_upload_doc_internos_eliminar!=null && rs_upload_doc_internos_eliminar.size()>0){
	  			Iterator it=rs_upload_doc_internos_eliminar.iterator();
	  				while(it.hasNext()){
	  					BArchivo archivoenlista=(BArchivo)it.next();
	  										
	  					if(!archivoenlista.getNombre_archivo().equals(nombre)){
	  						temporal.add(archivoenlista);						
	  					}
	  				}
	  				if(temporal.isEmpty()){
	  					session.setAttribute("rs_upload_doc_internos", null);
	  				}else{
	  					session.setAttribute("rs_upload_doc_internos", temporal);
	  				}
	  				
	  			}
	  		
	  		
			 
   	    	return (mapping.findForward("uploadinterno"));	
   	    	
   	    
        case 12:
        	
        	request.setAttribute("caso", "caso__12");
        	log.info(">>>>>>>>>>>>>DENTRO DE GRABACION Y RECEPCION DE DOCUMENTO INTERNO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        	log.info(">>>>>>>>>>>>>ESTE PROCESO SE REALIZARA INTERNAMENTE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        	log.info(">>>>>>>>>>>>>LLEGAMOS AQUI AL FIRMAR UN DOCUMENTO INTERNO<<<<<<");
        	
        	/*****TENGO QUE RECUPERAR LOS DATOS SOLICITADOS EN EL FORMULARIO REGISTRO*****/
        	/*****DESDE EL DOCUMENTO INTERNO *****/
        	session.removeAttribute("pagina");
        	Collection listaDeArchivosAdjuntos = new ArrayList();
        	        	
        	String[] chk_copiasz=null;
        	//String[] chk;
        	int  codigo_doc_para_grabar=Integer.parseInt((String)session.getAttribute("codigo_doc_para_grabar"));
        	String observacion_ls = (String) session.getAttribute("observacion");
        	
        	
        	DocumentoInternoBean documento_interno_registro = service.getDocumentoInterno(codigo_doc_para_grabar,"graba");	
        	
        	String cdnb = (String) session.getAttribute("codigo_persona_noborrar");
        	String firmaManual = (String)session.getAttribute("firmaManual");
        	usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        	
        	        	
        	log.info("El usuario actual es.." + usuario_actual);
    		codigo_oficina = funciones.of_codigo_oficina(cnx, usuario_actual);
    		log.info("El codigo de Oficina es.." + codigo_oficina);
    		String codigo_personall = funciones.of_codigo_persona(cnx, usuario_actual);
    		log.info("El codigo de Persona es.." + codigo_personall);
    		
	  		log.info("codigo_documento_interno: "+codigo_doc_para_grabar);


       		//ls_codigo_documento=funciones.cab_max_upload(cnx);
       		//log.info("El ls_cabecera_upload Codigo documento es..."+ ls_codigo_documento );

	  		/**
   	         * JAZANERO
   	         */
   	        codigo_intitucion=request.getParameter("codigo_intitucion");
   	        codigo_intitucion=(codigo_intitucion==null)? "":codigo_intitucion;
   	        if(codigo_intitucion.equals("")){
   	        	codigo_intitucion=String.valueOf(service.getInstitucion(Integer.parseInt(codigo_oficina)));
   	        }
   	        
   	        
   	        
        	/********************FUNCON DAO REGISTRA****************/
       		li_retorno = daoIUsuarioDAO.parametro(cnx);
			
	   	 	log.info("li_retorno" + li_retorno);
	   	 	
   	    	correlativorecepcion = String.valueOf(li_retorno);
   	  		log.info("El correlativorecepcion es :" + correlativorecepcion);	
			medio="OR";
			codigo_solicitud="1";	
			folios_documento="1";
			observa_documento=observacion_ls;
			//String lss_codigo_persona=funciones.of_codigo_persona(cnx,usuario);//codigo del personal
			BPersona PersonaVO= daoIUsuarioDAO.of_detalle_codigo_persona_oficina(cnx,codigo_oficina);
			log.info(PersonaVO.getNombre_persona()+"    "+PersonaVO.getCodigo_persona());
			desc_origen=PersonaVO.getNombre_persona();
			origen_documento = PersonaVO.getCodigo_persona();
			tipo_persona="JURIDICA";
			ls_persona=cdnb;
			numero_referencia="0";
			Date fecha = new Date(); // fecha y hora locales 
    		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
    		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
    		log.info("la fecha es.."+ formatoFecha.format(fecha) ); 
    		log.info("la hora es.."+ formatoHora.format(fecha) ); 
			fecha_registro=formatoFecha.format(fecha);
			hora=formatoHora.format(fecha);
			
			
				ls_codigo_documento = daoIUsuarioDAO.of_ingresar_documento_interno(cnx,documento_interno_registro.getCodigo_oficina_pertenece().toString(),medio.toUpperCase(),"1","I",documento_interno_registro.getCodigo_oficina().toString(),
						cdnb,documento_interno_registro.getPersonas().toString(),codigo_solicitud,documento_interno_registro.getNombre_arhivo().substring(0, documento_interno_registro.getNombre_arhivo().length()-4),
   	    				fecha_registro,hora,folios_documento,documento_interno_registro.getAsunto(),
   	    				observa_documento,usuario_actual,documento_interno_registro.getFirmado_por(),origen_documento
   	    				,desc_origen,tipo_persona.trim(),ls_persona,fecha_rpta,correlativorecepcion,numero_referencia,documento_interno_registro.getDirigido_a(),codigo_intitucion);
				
			/***
			 * RECEPCION DE PRINCIPAL
			 */
				
			/*	String estado_movimiento_registro="2";
				
				String usuario_destino = funciones.of_usuario_destino(cnx, documento_interno_registro.getPersonas().toString());
				String ls_cargo_personal=funciones.of_cargo_persona(cnx, usuario);
				log.info("user destino "+usuario_destino);
				log.info("cargo personal "+ls_cargo_personal);

				
				daoIUsuarioDAO.of_recibir_doc(cnx,documento_interno_registro.getCodigo_oficina().toString(),ls_codigo_documento,usuario_destino,
				fecha_registro,hora,observa_documento,estado_movimiento_registro,correlativorecepcion,
				funciones.of_numero_secuencia(cnx, correlativorecepcion),documento_interno_registro.getAsunto(),ls_cargo_personal);	
				log.info("cnx.isClosed():" +cnx.isClosed());
				
				if(cnx.isClosed())
						{cnx = getConnection(request, "principal");}	
			*/	
			/***
			 * FIN RECEPCION	
			 */

			/********************************************************/
				/***SUBIDA DE ARCHIVOS***/
			/****************************OBTENGO LA LISTA DE DOC ADJUNTOS***************/
			listaDeArchivosAdjuntos = service.getListaArchivosAdjuntos(codigo_doc_para_grabar);
			
					BArchivo archivoenlista = new BArchivo();
  			  		archivoenlista.setNombre_archivo(documento_interno_registro.getNombre_arhivo());
  			  		archivoenlista.setNombre_archivo_cifrado(documento_interno_registro.getNombre_arhivo());
  			  		archivoenlista.setIsfirmado(true);
  			  		archivoenlista.setRuta(filePathFirmados);
  			  		archivoenlista.setUsuario(usuario);
  			  		
  			  		if(((firmaManual==null)? "":firmaManual).equals("1")){
  			  			BInfo binfo = new BInfo();
  			  			binfo = service.getInfoSobreDocumentoFirmadoManualmente(codigo_doc_para_grabar);
  			  			archivoenlista.setIsfirmadomanual(true);
  			  			archivoenlista.setId_detalle_archivo(binfo.getId_det_upload());
  			  		
  			  		}
  			  		
		  	if(listaDeArchivosAdjuntos != null && !listaDeArchivosAdjuntos.isEmpty()){
		  			log.info("Registro Documento Principal y adjuntos");
		  			listaDeArchivosAdjuntos.add(archivoenlista);
		  			
		  	}
		  	else{
		  			log.info("Registro Documento Principal");
		  			listaDeArchivosAdjuntos = new ArrayList();
			  		listaDeArchivosAdjuntos.add(archivoenlista);
			  			
		  	}
		 		  		
		  	subirArchivosBloque(ls_codigo_documento,listaDeArchivosAdjuntos,session);
		  	/********************* FIN *******************************************************/
		   	log.info("La ls_codigo_documento ...:" + ls_codigo_documento);
		   	if(documento_interno_registro.getCodigos_oficinas_destinos_copias()!=null && documento_interno_registro.getCodigos_oficinas_destinos_copias().length()>0)
	  		{
		   		chk_copiasz=(documento_interno_registro.getCodigos_oficinas_destinos_copias().split(","));
	  		}	
					
	   		/***************************/
		   		HashMap<String, String> hRemitentez=daoIUsuarioDAO.obtenerDatosUsuario(ls_persona);
  		
  				//recuperarCorreo de destinatario.
  				hDestinatario.put("asunto_documento",documento_interno_registro.getAsunto());
				hDestinatario.put("remitente",hRemitentez.get("nombre_personal"));   	   				
  				   
  				//obtenemos los codigos de las persona de la oficina destino
  				myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,documento_interno_registro.getCodigo_oficina().toString());
  				//ponemos en una lista de string
  				ArrayList<String> misListaCodigosEnString= new ArrayList<String>();
  				log.info("**************************************");
  				log.info("Codigos correo principal > ");
  				for (int codigo : myListCodigos) {
  					misListaCodigosEnString.add(String.valueOf(codigo));
  					log.info(String.valueOf(codigo));
  				}
  				log.info("*************************************");
  				//ahora obtendremos los correos   	   			
  				if(misListaCodigosEnString.size()>0)
  				{ myListaCorreosDestinatarios = daoIUsuarioDAO.obtenerDatosUsuario(misListaCodigosEnString);
  				} 
  				/****		ENVIAMOS CORREO AL DESTINO PRINCIPAL****/	 
  				if(myListaCorreosDestinatarios.size()>0)   	   				  	   			
	   			{
  // ENVIO DE CORREOS DESACTIVADO	//enviarCorreoElectronico(myListaCorreosDestinatarios,ls_codigo_documento,sessionMail,hDestinatario, IConstante.correoTO_Principal);
	   			}
  				/****FIN ENVIA CORREO AL PRINCIPAL***/

  				log.info("ls_codigo_documento "+ls_codigo_documento);
				String ls_codigo_sum=funciones.of_codigo_documento_valida(cnx,ls_codigo_documento);
				log.info("La ls_codigo_sum ...YY " + ls_codigo_sum);
				log.info("********************************************** ");
				if(ls_codigo_sum.equals("4")){
						/**copia masiva*/
		   	     		
		   	     				   	     		
			   	       	     	for(int i=0; chk_copiasz!=null && i<  chk_copiasz.length; i++) 
				    			{ 
			        			log.info("El PAR_DES_LAR es diferente de null.." );	
				    			log.info("Antes del insert Clases.." );	
				    			//daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
				    			log.info("las cbo_copia son :" + chk_copiasz[i]);
				    			String chk_copias_aux=chk_copiasz[i];
				    			String ls_cod_persona=funciones.of_codigo_oficina_persona(cnx,chk_copias_aux);   					    			
				    			myListCodigos=funciones.of_codigo_oficina_persona_correo(cnx,chk_copias_aux);
				    			
				    			log.info("El ls_cod_persona es...LLL :" + ls_cod_persona);
				    			if(myListCodigos.size()>0){
				    				for (int cod : myListCodigos) {
				    					myListaCodigoPersonal.add(String.valueOf(cod));//obteniendo la lista de codigos de usuarios
								}   					    			
				    			}
				    			
				    			if(!codigo_oficina.trim().equals(chk_copiasz[i].trim())){
				    				
				    			log.info("codigo_oficina "+codigo_oficina+" "+chk_copiasz[i]+" "+observa_documento+" "+ls_cod_persona+" "+ls_documento+" "+usuario_actual+" "+ls_codigo_documento+" "+li_retorno+" "+fecha_rpta+" "+codigo_recepcion+" "+fecha_rpta);	

				    			//REGISTRO DE COPIAS
				    			log.info("Entre daoIUsuarioDAO.of_registrar_derivacion_mismo");
				    			daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,codigo_oficina,chk_copiasz[i],"15",observa_documento
		   	           	    			,ls_cod_persona,ls_documento,"0",usuario_actual,ls_codigo_documento,li_retorno,
		   	           	    			fecha_rpta,0,"",codigo_recepcion,"",fecha_rpta,'C',1);  ///agregar aqui secuencia_origen
				    			//FIN DE COPIAS
				    			
				    			/**
				    			 * INICIO DE RECEPCION
				    			 */
				    			/*
				    			log.info("las cbo_copia son :" + chk_copiasz[i]);
				    			chk_copias_aux=chk_copiasz[i];
				    			ls_cod_persona=funciones.of_codigo_oficina_persona(cnx,chk_copias_aux);   					    			
				    					    			
				    			usuario_destino = funciones.of_usuario_destino(cnx, ls_cod_persona);
			    				ls_cargo_personal=funciones.of_cargo_persona(cnx, usuario);
			    				
			    				daoIUsuarioDAO.of_recibir_doc(cnx,chk_copias_aux,ls_codigo_documento,usuario_destino,
			    	    				fecha_registro,hora,observa_documento,estado_movimiento_registro,correlativorecepcion,
			    	    				funciones.of_numero_secuencia(cnx, correlativorecepcion),documento_interno_registro.getAsunto(),ls_cargo_personal);
			    				
			    				if(cnx.isClosed())
								{cnx = getConnection(request, "principal");}
				    			
				    			*/
				    			/**
				    			 * FIN DE RECEPCION
				    			 */
				    			}			    				
				    			
		   	       	     		}//for
			   	       	     	
			   	       	     	
			   	       	     	/**
			   					 * Al final recien enviariamos el correo electronico al principal y a las copias.
			   					 * 
			   					 * */	
			   	       	     	
			   	       	     	log.info("tamanio listaCodigoPersonal: "+myListaCodigoPersonal.size());
			   	       	     	if(myListaCodigoPersonal.size()>0){
			   					ArrayList<String> myListaCorreos = daoIUsuarioDAO.obtenerDatosUsuario(myListaCodigoPersonal);
			   					log.info("tamanio listacorreos: "+myListaCorreos.size());
			   					// con la lista ahora enviamos los correos respectivos:	
			   				// ENVIO DE CORREOS DESACTIVADO	//	enviarCorreoElectronico(myListaCorreos,ls_codigo_documento,sessionMail,hDestinatario, IConstante.correoCC_Copias);
			   	       	     	}
		     
				}
			/***********************************************************************/		

					log.info("correlativorecepcion "+correlativorecepcion);
					ls_numero_expediente   = funciones.of_numero_expediente(cnx, correlativorecepcion);
	   				//ls_numero_secuencia   = funciones.of_numero_secuencia(cnx, correlativorecepcion);
					ls_numero_secuencia="1";
					
	   				log.info("ls_numero_expediente "+ls_numero_expediente);
	   				log.info("ls_numero_secuencia_expediente "+ls_numero_secuencia);
	   			
	   				
				    ls_mensaje =Constantes.DocumentoFirmadoExito.getNombre(); //"Documento Firmado con Exito!!!";
	   				String ls_mensaje_reg ="Nº de Registro : "+ls_codigo_documento;
	   				String ls_mensaje_exp ="Nº de Expediente : "+ls_numero_expediente;
					log.info("El ls_mensaje es.." + ls_mensaje_reg);
	   				
	       	    	//log.info("Despues del Ingreso Documento y Direccionando" );
	       	    	
	       	    	/***************************Actualizando la Cabecera Upload Ficheros************************/
	  		      
	       	    	String ls_num_doc_uploadd=daoIUsuarioDAO.ValidaListaDocEntradaDetUpload(ls_codigo_documento);
	       	    	log.info("El ls_num_doc_upload es.." + ls_num_doc_uploadd);
	       	    	log.info("usuario "+usuario);
	       	    	if(Integer.parseInt(ls_num_doc_uploadd.trim())>=1){
	       	    		//log.info("si es mayor a uno se crea cabecera..");
	       	    		daoIUsuarioDAO.CrearCabDocEntradaUploadIng(codigo_oficina,ls_codigo_documento,ls_numero_expediente.trim(),ls_numero_secuencia,correlativorecepcion,usuario);    		
	       	    	}
	       	    
	    				
	           	  /*********************Upload de Ficheros*****************/
	           	   
	       	  log.info("La OFICINA ES..." + codigo_oficina);
 	    	
    	
		/**************************FIN REGISTRO DE DOCUMENTO INTERNO ***************************/	
	       	    		      	    		
	    			session.setAttribute("operacionpopup","X");
	    			//session.setAttribute("titulo", "Mensaje de Registro Doc.");
	       	    	session.setAttribute("mensaje_reg",ls_mensaje_reg);
	       	    	session.setAttribute("mensaje_exp",ls_mensaje_exp);	
	       	    	session.setAttribute("mensaje",ls_mensaje);
	       	    	session.removeAttribute("listaDeArchivosAdjuntos");
	       	    	session.removeAttribute("observacion");
	       	    	session.removeAttribute("tipoenv");
	       	    	session.removeAttribute("firmaManual");
	       	    	
	       	    	request.setAttribute("nombreArchivoVer", documento_interno_registro.getNombre_arhivo());
	       	    	request.setAttribute("tipoDocumentoVer", "3");
	       	    	request.setAttribute("codigoOficinaVer", documento_interno_registro.getCodigo_oficina_pertenece().toString());
	       	    	
	       	    	/**************UPDATE DE LA TABLA DOCUMENTO INTERNO*******/
	    			
	    			service.modificaTablaDocumentosInternos(codigo_doc_para_grabar,Integer.parseInt(ls_codigo_documento),2,"R"); 
	         	
	    log.info("<<<<<<<<<<<<<<<<<<<<<< FIN DE REGISTRO Y RECEPCION DE DOCUMENTOS >>>>>>>>>>>>>>>>>>>><< ");   	
	    
	    /******************************************************/
		if(Integer.parseInt(codigo_oficina.trim())>1){
		
		log.info("lA OFICINA ES MAYOR DE 1...");
		PersonaVO= daoIUsuarioDAO.of_detalle_codigo_persona_oficina(cnx,codigo_oficina);
		//log.info("La ls_persona_oficina es.." + ls_persona_oficina);
		
		String ls_codigo_persona_user=PersonaVO.getCodigo_persona();
		String ls_nombre_persona_user=PersonaVO.getNombre_persona();
		String ls_tipo_user=PersonaVO.getTipo();
		
		log.info("La ls_codigo_persona_user es.." + ls_codigo_persona_user);
		log.info("La ls_nombre_persona_user es.." + ls_nombre_persona_user);
		log.info("La ls_tipo_user es.." + ls_tipo_user);

	 		}
	    	
			session.setAttribute("listaFrameBusquedaTemp",null);
			return (mapping.findForward("exitograbacion"));		
   	    	
       	 }
        	
    	   
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
        	closeConnection(cnx);	
        }
       	return (mapping.findForward("error"));
	}



	private void subirArchivosBloque(String ls_codigo_documento,HttpSession session) {
		FFormMantDocumento oForm_ =(FFormMantDocumento)session.getAttribute("FFormMantDocumento");
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
				log.info("-->"+filePath+archivo.getRutaDetalleUpload()+funciones.getAnioActualTexto()+", "+funciones.getFechaActualyHoraActualTexto()+ls_new_nombre_upload);
				
				File fileToCreate = new File(filePath+archivo.getRutaDetalleUpload()+funciones.getAnioActualTexto(), funciones.getFechaActualyHoraActualTexto()+ls_new_nombre_upload);

				
				if (makeSureDirectoryExists(parent(fileToCreate))) {//sino existe directorio..lo crea
					if (!fileToCreate.exists()) { //sino existe el archivo .. graba
						FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
						fileOutStream.write(archivo.getData());
						fileOutStream.flush();
						fileOutStream.close();
					}
					
				}
								
				daoIUsuarioDAO.CrearDocumentoDocEntradaDetUploadIng(Integer.parseInt(documento.trim()),ls_new_nombre_upload, filePath+archivo.getRutaDetalleUpload()+funciones.getAnioActualTexto(), "A", "P", archivo.getUsuario(),"O");
				
			}

		}
	}


	private void subirArchivosBloque(String ls_codigo_documento, Collection rs_upload_doc_internos_adj,HttpSession session) {
		log.info(">>>>>>>>>>>>>>>>>>>>>>ENTRE EN SUBIR DOCUMENTOS EN BLOQUE >>>>>>>>>>>>>>>>>>>>>>>>>>>");
		log.info(">>>>>>>>>>>>>>>>>>>>>> DOCUMENTOS INTERNOS FIRMADOS>>>>>>>>>>>>>>>>>>>");
	
		
			Iterator it=rs_upload_doc_internos_adj.iterator();
			while(it.hasNext()){
				BArchivo archivo=(BArchivo)it.next();
				try {
					subir_archivo_firmado(ls_codigo_documento, archivo);
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

	
	private void subir_archivo_firmado(String documento,BArchivo archivo) throws SQLException, IOException,Exception {

		log.info("<<<<<<<<<<<<<<<<<<<<<SUBIENDO ARCHIVOS FIRMADOS DESDE LA LISTA>>>>>>>>>>>>>>>>>>>>");
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		Funciones funciones = new Funciones();
		String fileName = archivo.getNombre_archivo_cifrado();
	//	String filePath = Constantes.CarpetaArchivosProduccion.getNombre();

			int punto = fileName.lastIndexOf('.');
			log.info("el puntoLUUUU es.." + punto);
			log.info("el puntoLUUUU + 1 es.."
					+ fileName.substring(punto + 1));
			if (fileName.substring(punto + 1).equals("DOC")) {
				fileName = fileName.replace("DOC", "doc");
				log.info("El Cambiando el nombre a miniscula..XXXXXX: "+fileName);
			}

			log.info("<<<<<<<<<<<<<<<<<<<<<REGISTRANDO ARCHIVO :"+fileName);

			/**	File fileToCreate = new File(filePath, ls_new_nombre_upload);

				if (!fileToCreate.exists()) {
					
					FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
					
					FileInputStream file = new FileInputStream(nombre_original);
					int longitud = file.available();
					byte b[] = new byte[longitud];
										
					fileOutStream.write(file.read(b));
					fileOutStream.flush();
					fileOutStream.close();
					file.close(); 
					
				}**/
			if(archivo.isIsfirmadomanual()){
				log.info("--> M");
				daoIUsuarioDAO.ActualizaDocumentoDocEntradaDetUploadIng(Integer.parseInt(documento.trim()),archivo.getUsuario(), archivo.getId_detalle_archivo());
				
			}else{
				if(archivo.isIsfirmado()){
					log.info("--> F");
					daoIUsuarioDAO.CrearDocumentoDocEntradaDetUploadIng(Integer.parseInt(documento.trim()),archivo.getNombre_archivo_cifrado(), archivo.getRuta(), "A", "P", archivo.getUsuario(), "F");
				}
				if(archivo.isIsvisado()){
					log.info("--> V");
					daoIUsuarioDAO.CrearDocumentoDocEntradaDetUploadIng(Integer.parseInt(documento.trim()),archivo.getNombre_archivo_cifrado(), archivo.getRuta(), "A", "P", archivo.getUsuario(), "V");
				}
				if(!archivo.isIsfirmado() && !archivo.isIsvisado()){
					log.info("--> O");
					daoIUsuarioDAO.CrearDocumentoDocEntradaDetUploadIng(Integer.parseInt(documento.trim()),archivo.getNombre_archivo_cifrado(), archivo.getRuta(), "A", "P", archivo.getUsuario(), "V");
				}	
			}
			

		log.info(">>>>>>>>>>>>>>>>>>>>>>FIN SUBIR ARCHIVO UNITARIO >>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	/*
	 * agregado por Moises Pelaez
	 * Fecha: 23-06-2011
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
	
	protected String getCadenaAlfanumAleatoria (int longitud){
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while ( i <longitud){
		char c = (char)r.nextInt(255);
		if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
		cadenaAleatoria += c;
		i ++;
		}
		}
		return cadenaAleatoria;
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
