package tramitedoc.concytec.action;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import tramitedoc.concytec.dao.*;
import tramitedoc.concytec.form.*;
import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionMantSeguimiento extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(AActionMantSeguimiento.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.info("DENTRO DEL MANTSEGUIMIENTO DOC..");
		
		HttpSession session = request.getSession(true);
		Connection cnx=null;
		    
	  	
		//session.removeAttribute("cod_padre");
	    //session.removeAttribute("derivar");
	       
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		
		FFormMantSeguimiento oForm = (FFormMantSeguimiento) form;
		log.info("Antes del FFormMantDerivar...");	  
		String tipo = oForm.getTipo() == null ? "init" : oForm.getTipo().trim();
		log.info("Despues del tipo...");	 
		log.info("El tipo 1 "+ tipo);
		tipo = tipo.equals("") ? "init" : tipo;
		log.info("El tipo 2 "+ tipo);
		
		String codigo_documento;
		String codigo_expediente;
		String descripcion_tipo;
		String numero_documento;
		String fecha;
		String hora;
		String observacion;
		String mensaje;
		String usuario;
		String codigo_oficina;
		String estado_movimiento;
		
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
        String destinatario;
		String codigo_motivo;
		String ls_cod_padre;
		String personas;
		String cbo_fecharpta;
		String chk_copia;
		String[] cbo_copia;
		String doc_resp;
		//String[] PAR_DES_LAR=null
		String secuencia_movimiento;
		String codigo_inicia;
		String naturaleza_documento;
		
		String fecha_rpta;
		String correlativorecepcion;
		String codigo_recepcion;
		String fecha_rpta_rq;
        int ls_correla_conv  = 0;
        
		String ls_correlativorecepcion;
		int li_retorno=0;
		
		usuario   = String.valueOf(session.getAttribute("nombreusuario"));
		
		codigo_documento = ((FFormMantSeguimiento)form).getCodigo_documento();
		codigo_expediente = ((FFormMantSeguimiento)form).getCodigo_expediente();
		descripcion_tipo = ((FFormMantSeguimiento)form).getDescripcion_tipo();
		numero_documento = ((FFormMantSeguimiento)form).getNumero_documento();
		
		fecha = ((FFormMantSeguimiento)form).getFecha();
		/*hora = ((FFormMantDerivar)form).getHora();
		observacion = ((FFormMantDerivar)form).getObservacion();
		estado_movimiento = ((FFormMantDerivar)form).getEstado_movimiento();*/
		
		codigo_oficina = ((FFormMantSeguimiento)form).getCodigo_oficina();
		destinatario = ((FFormMantSeguimiento)form).getDestinatario();
		codigo_motivo = ((FFormMantSeguimiento)form).getCodigo_motivo();
		observacion = ((FFormMantSeguimiento)form).getObservacion();
		fecha_rpta = ((FFormMantSeguimiento)form).getFecha_rpta();
		
		personas = ((FFormMantSeguimiento)form).getPersonas();
		cbo_fecharpta = ((FFormMantSeguimiento)form).getCbo_fecharpta();
		chk_copia = ((FFormMantSeguimiento)form).getChk_copia();
		cbo_copia = ((FFormMantSeguimiento)form).getCbo_copia();
		
		secuencia_movimiento = ((FFormMantSeguimiento)form).getSecuencia_movimiento();
		codigo_inicia = ((FFormMantSeguimiento)form).getCodigo_inicia();
		naturaleza_documento = ((FFormMantSeguimiento)form).getNaturaleza_documento();
		codigo_recepcion = ((FFormMantSeguimiento)form).getCodigo_recepcion();
		doc_resp = ((FFormMantSeguimiento)form).getDoc_resp();
		ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
		fecha_rpta_rq = ((FFormMantSeguimiento)form).getFecha_rpta_rq();
		
		log.info(" usuario.. "+ usuario);
		log.info(" codigo_documento.. "+ codigo_documento);
		log.info(" codigo_expediente.. "+ codigo_expediente);
		log.info(" descripcion_tipo.. "+ descripcion_tipo);
		log.info(" numero_documento.. "+ numero_documento);
		log.info(" fecha.. "+ fecha);
		log.info(" codigo_oficina.. "+ codigo_oficina);
		log.info(" destinatario.. "+ destinatario);
		log.info(" codigo_motivo.. "+ codigo_motivo);
		log.info(" observacion.. "+ observacion);
		log.info(" fecha_rpta.. "+ fecha_rpta);
		log.info(" chk_copia.. "+ chk_copia);
		log.info(" cbo_copia.. "+ cbo_copia);
		log.info(" codigo_recepcion.. "+ codigo_recepcion);
		log.info(" doc_resp.. "+ doc_resp);
		/*int li_retorno_correlativo = daoIUsuarioDAO.parametro(cnx);
		 ls_correla   = String.valueOf(li_retorno_correlativo);
		 
		 ls_correla_conv=Integer.parseInt(ls_correla.trim());
		 
		 log.info(" li_retorno_correlativo.. "+ li_retorno_correlativo);
		 log.info(" ls_correla.. "+ ls_correla);*/
		//codigo_oficina = ((FFormMantDerivar)form).getCodigo_oficina();
		 /*codigo_oficina   =   String.valueOf(session.getAttribute("codigo_oficina"));
	     log.info("El session codigo_oficina derivar doc es :" + codigo_oficina);*/
	     
		
	     	
		
		//session.removeAttribute("fecha_rec");
		try {
			cnx = getConnection(request, "principal");
			log.info("El cnx............" + cnx);	
			
			if (tipo.equals("seguimiento") ){
				
				log.info("Si tipo es init " + tipo);
				log.info("Listar Recepcion Documentos....." );
				
				Collection rs_derivacion_doc = new ArrayList();
				Collection rs_oficinas = new ArrayList();
				Collection rs_motivos = new ArrayList();
				
				rs_derivacion_doc = daoIUsuarioDAO.lista_documentos_todos(cnx,ls_oficina);
				rs_oficinas = daoIUsuarioDAO.of_lista_oficinas(cnx);
				rs_motivos = daoIUsuarioDAO.of_lista_motivos(cnx);
					log.info("el rs_derivacion_doc es ......"+ rs_derivacion_doc);
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_derivacion_doc",rs_derivacion_doc);
					session.setAttribute("rs_oficina",rs_oficinas);
					session.setAttribute("rs_motivos",rs_motivos);
					
					//session.setAttribute("correlativorecepcion",ls_correla_conv);
					//return (mapping.findForward("exito"));	
			}
			
			else if (tipo.equalsIgnoreCase("editar")){
			try {
				//session.removeAttribute("rs_personas");
				log.info("Si tipo es editar init" + tipo);
				log.info("Dentro de Editar en MantRecepcion");	

   	    		log.info("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
   	    	
   	    		if(observacion.equals("null")){
   	   	 			observacion="";
   	   	 			log.info("La observacion es null de lo contrario es igual a vacio.");
   	   	    		}
   	    		
      	    	// daoIUsuarioDAO.of_derivar_doc_edit(cnx,codigo_oficina,codigo_documento,usuario,fecha,hora,observacion,estado_movimiento,ls_correlativorecepcion);
				
      	    	Collection rs_oficinas = new ArrayList();
      	    	Collection rs_personas = new ArrayList();
   	    		Collection rs_derivacion_doc = new ArrayList();
		        
   	    		rs_derivacion_doc = daoIUsuarioDAO.lista_documentos_todos(cnx,ls_oficina);
   	    		rs_oficinas = daoIUsuarioDAO.of_lista_oficinas(cnx);
   	    		//rs_personas = daoIUsuarioDAO.of_lista_personal(cnx);
   	    		
				log.info("el rs_derivacion_doc es ......"+ rs_derivacion_doc);
				log.info("el rs_personas es ......"+ rs_personas);
				
				session.setAttribute("operacion",tipo);
				session.setAttribute("rs_derivacion_doc",rs_derivacion_doc);
				session.setAttribute("rs_oficina",rs_oficinas);
				
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
			else if (tipo.equalsIgnoreCase("enviar")){

			try {
				
				
				BMesaPartes BMesaPartesVO = daoIUsuarioDAO.of_obtener_padre(cnx,codigo_documento);
				
				 log.info(" codigo_documento.. "+ codigo_documento);
				 
		        ls_documento = codigo_documento; 
		        ls_cod_padre = BMesaPartesVO.getCodigo_inicia();
		        log.info(" ls_cod_padre.. "+ ls_cod_padre);
		        ls_usuario   = String.valueOf(session.getAttribute("nombreusuario"));
		        //observar el correlativo derivacion
		       
		        
		        //ls_correla_conv=Integer.parseInt(ls_correla.trim());
		        
			
		        log.info(" ls_oficina.. "+ ls_oficina);
		        log.info(" ls_documento.. "+ ls_documento);
		        log.info(" ls_codigoPadre.. "+ ls_codigoPadre);
		        log.info(" ls_usuario.. "+ ls_usuario);
		        log.info(" ls_correla.. "+ ls_correla);
		        log.info(" ls_secuencia.. "+ ls_secuencia);
		        log.info(" ls_codigo_inicia.. "+ ls_codigo_inicia);
		        log.info(" ls_correla_conv.. "+ ls_correla_conv);
		        log.info(" codigo_expediente.. "+ codigo_expediente);
		        //log.info(" ls_naturaleza_documento.. "+ ls_naturaleza_documento);
		        log.info(" ls_descripcion_oficina.. "+ ls_descripcion_oficina);
		        log.info("ls_cod_padre" + ls_cod_padre);
		        
			log.info("usuario... "+ usuario);
			log.info("codigo_documento... "+ codigo_documento);
			log.info("codigo_expediente... "+ codigo_expediente);
			log.info("codigo_expediente... "+ codigo_expediente);
			log.info("numero_documento... "+ numero_documento);
			log.info("fecha... "+ fecha);
			//log.info("hora... "+ hora);
			log.info("observacion... "+ observacion);
			
			log.info("codigo_oficina... "+ codigo_oficina);
			//log.info("estado_movimiento... "+ estado_movimiento);
			
			log.info("Modificacion de... MantRecepcion .........." );
			
			/*li_retorno = daoIUsuarioDAO.parametro(cnx);
			
	   	 	log.info("li_retorno" + li_retorno);
	   	 	
	   	 	ls_correlativorecepcion=String.valueOf(li_retorno);
	   	 	log.info("ls_correlativorecepcion" + ls_correlativorecepcion);*/
			
	   	 	Date fecha_recep = new Date(); // fecha y hora locales 
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
			SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
			log.info("la fecha es.."+ formatoFecha.format(fecha_recep) ); 
			log.info("la hora es.."+ formatoHora.format(fecha_recep) ); 
			log.info("la fecha_rpta es.."+ fecha_rpta); 
			
				mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correla;
   	    		session.setAttribute("mensaje",mensaje);
   	    		int secuencia_origen = Integer.parseInt(secuencia_movimiento);
   	    		if(fecha_rpta==null){
   	    			fecha_rpta="";
   	   	 			log.info("La fecha_rpta es null de lo contrario es igual a vacio.");
   	   	    		}
				
   	    	
   	    			if(ls_documento.equals(ls_cod_padre)){
   	       	     		
   	       	     		
   	       	     		if(naturaleza_documento.equals("I")){
   	       	     			
   	       	     		log.info("El ls_Documento es igual a codigo padre y el documento es Interno" );	
   	           	     	daoIUsuarioDAO.of_registrar_derivacion_interno(cnx,ls_oficina,codigo_oficina,codigo_motivo,observacion
   	           	    			,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_cod_padre,ls_correla_conv,
   	           	    			fecha_rpta,Integer.valueOf(codigo_expediente.trim()),ls_descripcion_oficina,Integer.valueOf(codigo_recepcion.trim()) ,doc_resp,fecha_rpta_rq);
   	           	    	
   	           	    	log.info("Despues del Ingreso Documento,si codigo inicial es igual a cod documento" );	
   	       	     			
   	       	     		}else{
   	       	     		
   	       	     		if(chk_copia==null){
   	       	     			
   	       	     		log.info("El chk_copia es == null" );
   	       	     		log.info("El ls_Documento es igual a codigo padre y el documento es de Entrada" );	
   	           	     	daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_oficina,codigo_oficina,codigo_motivo,observacion
   	           	    			,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_cod_padre,ls_correla_conv,fecha_rpta,
   	           	    		Integer.valueOf(codigo_expediente.trim()) ,ls_descripcion_oficina,Integer.valueOf(codigo_recepcion.trim()) ,doc_resp,fecha_rpta_rq,'P',secuencia_origen);
   	           	    	
   	           	    	log.info("Despues del Ingreso Documento,si codigo inicial es igual a cod documento" );	
   	           	    	
   	       	     		}else{
   	       	     			
   	       	     		log.info("El chk_copia es diferente de null.." +chk_copia);
   	       	     		
		   	       	     	for(int i=0; i<cbo_copia.length; i++) 
			    			{ 
		        			log.info("El PAR_DES_LAR es diferente de null.." );	
			    			log.info("Antes del insert Clases.." );	
			    			//daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
			    			log.info("las cbo_copia son :" + cbo_copia[i]);
			    			
			    			daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_oficina,cbo_copia[i],codigo_motivo,observacion
	   	           	    			,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_cod_padre,ls_correla_conv,
	   	           	    			fecha_rpta,Integer.valueOf(codigo_expediente.trim()) ,ls_descripcion_oficina,
	   	           	    		Integer.valueOf(codigo_recepcion.trim()) ,doc_resp,fecha_rpta_rq,'C',secuencia_origen);
	   	           	    	
			    			
			    			} 
   	       	     	
   	       	     		}
   	    	    			
   	       	     		}
   	       	     	
   	       	     	}else{
   	       	     		
   	       	     	log.info("El ls_Documento es diferente a codigo padre" );	
   	       	     	
   	       	     	daoIUsuarioDAO.of_registrar_derivacion_intent(cnx,ls_oficina,codigo_oficina,codigo_motivo,observacion
   	       	    			,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_cod_padre,ls_correla_conv,fecha_rpta,ls_correlativo_deriva,ls_descripcion_oficina);
   	       	    	
   	       	    	log.info("Despues del Ingreso Documento y Direccionando,si codigo inicial es diferente a cod documento" );	
   	       	    	
   	       	     	}

   	  	    		Collection rs_derivacion_doc = new ArrayList();
   			        
   	  	    		rs_derivacion_doc = daoIUsuarioDAO.lista_documentos_todos(cnx,ls_oficina);
   				      
   					log.info("el rs_derivacion_doc es ......"+ rs_derivacion_doc);
   					
   					session.setAttribute("operacion",tipo);
   					session.setAttribute("rs_derivacion_doc",rs_derivacion_doc);
   					//session.setAttribute("correlativorecepcion",ls_correla_conv);
   					session.setAttribute("mensaje",mensaje);
   					
   	    		
			}catch (SQLException e) {
				e.printStackTrace();
				closeConnection(cnx);
				//return mapping.getInputForward();
	
			}
			

		} 
			
			else if (tipo.equals("upload") ){
				
				log.info("Si tipo es init es.." + tipo);
				log.info("Listar Upload Archivos..." );
				
				//codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
				String ls_codigo_documento=request.getParameter("codigo_documento");
				String ls_operacion=request.getParameter("operacion");
				ls_codigo_expediente=request.getParameter("codigo_expediente");
				String ls_secuencia_movimiento=request.getParameter("secuencia_movimiento");
				String ls_codigo_recepcion=request.getParameter("codigo_recepcion");
				
				log.info("EL ls_codigo_documento upload..."+ls_codigo_documento );
				log.info("EL ls_operacion upload..."+ls_operacion );
				log.info("EL ls_codigo_expediente upload..."+ls_codigo_expediente );
			    log.info("EL ls_secuencia_movimiento upload..."+ls_secuencia_movimiento );
			    log.info("EL ls_codigo_recepcion upload..."+ls_codigo_recepcion );
			    
				Collection rs_upload_doc = new ArrayList();
				
				rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos_detalle(cnx,
						Integer.parseInt(ls_codigo_documento.trim()));
				
				log.info("el rs_upload_doc es ......"+ rs_upload_doc);
					
				session.setAttribute("operacion",ls_operacion);
				session.setAttribute("rs_upload_doc",rs_upload_doc);
				session.setAttribute("codigo_documento",ls_codigo_documento);
				session.setAttribute("codigo_expediente",ls_codigo_expediente);
				session.setAttribute("secuencia_movimiento",ls_secuencia_movimiento);
				
				return (mapping.findForward("upload"));
			}
			else if (tipo.equals("uploadficheros") ){
				
				log.info("Si tipo es init es.." + tipo);
				log.info("Listar UploadMultiples Ficheros Archivos..." );
				
				/*********************Upload de Ficheros*****************/
				String ls_codigo_documento=request.getParameter("codigo_documento");
				 ls_codigo_expediente=request.getParameter("codigo_expediente");
				String ls_secuencia_movimiento=request.getParameter("secuencia_movimiento");
				
				log.info("El ls_codigo_documento..." + ls_codigo_documento );
				log.info("El ls_codigo_expediente..." + ls_codigo_expediente );
				log.info("El ls_secuencia_movimiento..." + ls_secuencia_movimiento );
				
				FFormMantSeguimiento myForm = (FFormMantSeguimiento)form;
				codigo_documento = ((FFormMantSeguimiento)form).getCodigo_documento();
				log.info("El codigo_documento FOR..." + codigo_documento);
	 		        // Process the FormFile
	 				      FormFile myFile = myForm.getTheFile();
	 				      String contentType = myFile.getContentType();
	 				      log.info("el contentType FILE UPLOAd es.." + contentType);
	 	   
	 			       	  String fileName    = myFile.getFileName();
	 			       	log.info("el fileName es.." + fileName);
	 			          //int fileSize       = myFile.getFileSize();
	 			          byte[] fileData    = myFile.getFileData();
	 			          log.info("el fileData es.." + fileData);
	 				      //Get the servers upload directory real path name
	 			          
	 			          //local host public static final String carpeta = "c:/upload/sisFonCur/";
	 			        //produccion public static final String carpeta = "/upload/sisFonCur/";
	 			          
	 				      String filePath = getServlet().getServletContext().getRealPath("/") +"uploadDocumentos";
	 				      log.info("el filePath es.." + filePath);
	 				      int ls_id_proyecto=0;
	 				
	 				      /* Save file on the server */
	 				      if(!fileName.equals("")){  
	 				          log.info("Server path:" +filePath);
	 				          //Create file
	 				          File fileToCreate = new File(filePath, fileName);
	 				          log.info("el fileToCreate es.." + fileToCreate);
	 				          //If file does not exists create file                      
	 				          if(!fileToCreate.exists()){
	 				            FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
	 			            log.info("el fileOutStream es.." + fileOutStream);
	 			            log.info("el myFile.getFileData es.." + myFile.getFileData());
	 			            fileOutStream.write(myFile.getFileData());
	 			            fileOutStream.flush();
	 			            fileOutStream.close();
	 			          }  
	 				      log.info("Antes Ingreso PROYECTO INVESTIGACION UPLOAD");
	 				     // daoIProyectoInvestigacionDAO.ProyectoInvestigacionUpload(Integer.parseInt(ls_id_investigador.trim()),fileName,filePath,ls_id_proyecto);       
	 				     daoIUsuarioDAO.DerivarDocumentoDetUploadIng(Integer.parseInt(ls_codigo_documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),Integer.parseInt(ls_secuencia_movimiento.trim()), fileName, filePath,"A","P","O");
	 				      log.info("Despues del Ingreso PROYECTO INVESTIGACION");
	 			      }
	 				  /***************************FIn upload*****************************/
	 				      
	 				   log.info("Despues del Ingreso Upload de Ficheros Proy Invest" );
	 				   
	 				  Collection rs_upload_doc = new ArrayList();
	 					rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos(cnx,Integer.parseInt(ls_codigo_documento.trim()));
	 					
	 					log.info("el rs_upload_doc es ......"+ rs_upload_doc);
	 					
	 				   session.setAttribute("operacion", "N");
	 				  session.setAttribute("rs_upload_doc",rs_upload_doc);
				return (mapping.findForward("upload"));
			}	
				
			else if (tipo.equals("eliminar") ){
				
				log.info("Si tipo es init es.." + tipo);
				log.info("Listar Eliminar Upload Archivos..." );
				
				//codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
				String ls_id_det_upload=request.getParameter("id_det_upload");
				String ls_codigo_documento=request.getParameter("codigo_documento");
				
				log.info("EL ls_id_det_upload upload..."+ls_id_det_upload );
				
				daoIUsuarioDAO.EliminarDocumentoDetUpload(cnx,Integer.parseInt(ls_id_det_upload.trim()));
				
				 Collection rs_upload_doc = new ArrayList();
					rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos(cnx,Integer.parseInt(ls_codigo_documento.trim()));
					
					log.info("el rs_upload_doc es ......"+ rs_upload_doc);
					session.setAttribute("operacion", "N");
					session.setAttribute("rs_upload_doc",rs_upload_doc);
					
				return (mapping.findForward("upload"));
			}
		
		log.info("Saliendo de MantSeguimiento....");	
		//closeConnection(cnx);
		oForm.reset(mapping, request);
		oForm.setTipo("nuevo");
		return (mapping.findForward("exito"));
 
    	   
        } 
        catch (Exception e) {
            e.printStackTrace();
        } finally{
        	closeConnection(cnx);	
        }
       	return (mapping.findForward("error"));
	}
}
