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

public class AActionMantRecepcion extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("DENTRO DEL MANTRECEPCION...");
		
		HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx............" + cnx);	    
	  		
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
	
		FFormMantRecepcion oForm = (FFormMantRecepcion) form;
		System.out.println("Antes del FFormMantRecepcion...");	  
		String tipo = oForm.getTipo() == null ? "init" : oForm.getTipo().trim();
		System.out.println("Despues del tipo...");	 
		System.out.println("El tipo 1 "+ tipo);
		tipo = tipo.equals("") ? "init" : tipo;
		System.out.println("El tipo 2 "+ tipo);
		
		String codigo_documento;
		String codigo_expediente;
		String descripcion_tipo;
		String numero_documento;
		String ls_codigo_oficina;
		String fecha;
		String hora;
		String observacion;
		String mensaje;
		String usuario;
		String codigo_oficina;
		String estado_movimiento;
		String codigo_recepcion;
		String secuencia_movimiento;
		String asunto_documento;
		String ls_correlativorecepcion="";
		String ls_documento = null;
		String ls_usuario = null;
		  
		String codigo_oficina_drv;
		String destinatario;
		String codigo_motivo;
		String ls_cod_padre;
		String personas;
		String cbo_fecharpta;
		String chk_copia;
		String[] cbo_copia;
		String codigo_inicia;
		String naturaleza_documento;
		String fecha_rpta;
		String correlativorecepcion;
		String mensaje_recepcion;
		String ls_descripcion_oficina="";
		String ls_correlativo_deriva="";
		String ls_tiporecepcion="";
		String doc_resp;
		int ls_correla_conv  = 0;
		String fecha_rpta_rq="";
		String codigo_tipo="";
		String codigooficina="";
		String cod_ruta="";
		String cod_modalidad="";
		String descripcion="";
		
		int li_retorno=0;
		Funciones funciones=new Funciones();
		Collection rs_recepcion_doc = new ArrayList();
		Collection rs_derivacion_doc = new ArrayList();
		Collection rs_oficinas = new ArrayList();
		Collection rs_motivos = new ArrayList();
		
		//Lista de Sessiones 
		 //usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        codigo_oficina   =   String.valueOf(session.getAttribute("codigo_oficina"));
        System.out.println("El session codigo_oficina es :" + codigo_oficina);
        /*ls_persona   =   String.valueOf(session.getAttribute("rs_persona"));
        System.out.println("El session ls_persona es :" + ls_persona);
        ls_nombre_oficina   =   String.valueOf(session.getAttribute("nombre_oficina"));
        System.out.println("El session ls_nombre_oficina es :" + ls_nombre_oficina);
	        */
		usuario   = String.valueOf(session.getAttribute("nombreusuario"));
		ls_tiporecepcion   = String.valueOf(session.getAttribute("tiporecepcion"));
		
		codigo_documento = ((FFormMantRecepcion)form).getCodigo_documento();
		codigo_expediente = ((FFormMantRecepcion)form).getCodigo_expediente();
		descripcion_tipo = ((FFormMantRecepcion)form).getDescripcion_tipo();
		numero_documento = ((FFormMantRecepcion)form).getNumero_documento();
		
		fecha = ((FFormMantRecepcion)form).getFecha();
		hora = ((FFormMantRecepcion)form).getHora();
		observacion = ((FFormMantRecepcion)form).getObservacion();
		
		
		//ls_codigo_oficina = ((FFormMantRecepcion)form).getCodigo_oficina();
		estado_movimiento = ((FFormMantRecepcion)form).getEstado_movimiento();
		codigo_recepcion = ((FFormMantRecepcion)form).getCodigo_recepcion();
		secuencia_movimiento = ((FFormMantRecepcion)form).getSecuencia_movimiento();
		asunto_documento = ((FFormMantRecepcion)form).getAsunto_documento();
		
		
		codigo_oficina_drv = ((FFormMantRecepcion)form).getCodigo_oficina_drv();
		destinatario = ((FFormMantRecepcion)form).getDestinatario();
		codigo_motivo = ((FFormMantRecepcion)form).getCodigo_motivo();
		observacion = ((FFormMantRecepcion)form).getObservacion();
		fecha_rpta = ((FFormMantRecepcion)form).getFecha_rpta();
		personas = ((FFormMantRecepcion)form).getPersonas();
		cbo_fecharpta = ((FFormMantRecepcion)form).getCbo_fecharpta();
		chk_copia = ((FFormMantRecepcion)form).getChk_copia();
		cbo_copia = ((FFormMantRecepcion)form).getCbo_copia();
		secuencia_movimiento = ((FFormMantRecepcion)form).getSecuencia_movimiento();
		codigo_inicia = ((FFormMantRecepcion)form).getCodigo_inicia();
		naturaleza_documento = ((FFormMantRecepcion)form).getNaturaleza_documento();
		codigo_recepcion = ((FFormMantRecepcion)form).getCodigo_recepcion();
		//doc_resp = ((FFormMantRecepcion)form).getDoc_resp();
		fecha_rpta_rq = ((FFormMantRecepcion)form).getFecha_rpta_rq();
		
		System.out.println("usuario... "+ usuario);
		System.out.println("codigo_documento... "+ codigo_documento);
		System.out.println("codigo_expediente... "+ codigo_expediente);
		System.out.println("codigo_expediente... "+ codigo_expediente);
		System.out.println("numero_documento... "+ numero_documento);
		System.out.println("fecha... "+ fecha);
		System.out.println("hora... "+ hora);
		System.out.println("observacion... "+ observacion);
		
		System.out.println("codigo_oficina... "+ codigo_oficina);
		System.out.println("estado_movimiento... "+ estado_movimiento);
		System.out.println("secuencia_movimiento... "+ secuencia_movimiento);
		System.out.println("asunto_documento... "+ asunto_documento);
		System.out.println("naturaleza_documento... "+ naturaleza_documento);
		System.out.println("fecha_rpta_rq... "+ fecha_rpta_rq);
		
		System.out.println("Modificacion de... MantRecepcion .........." );
		
		
		
   	 	Date fecha_recep = new Date(); // fecha y hora locales 
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
		System.out.println("la fecha es.."+ formatoFecha.format(fecha_recep) ); 
		System.out.println("la hora es.."+ formatoHora.format(fecha_recep) ); 
		
		session.removeAttribute("fecha_rec");
		try {
			
			
			if (tipo.equals("recepcion") ){
				
				System.out.println("Si tipo es init " + tipo);
				System.out.println("Listar Recepcion Documentos....." );
				
				  	rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos(cnx,codigo_oficina);
			      
					//rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_oficina);
					rs_oficinas = daoIUsuarioDAO.of_lista_oficinas(cnx);
					rs_motivos = daoIUsuarioDAO.of_lista_motivos(cnx);
					
					System.out.println("el rs_recepcion_doc es ......"+ rs_recepcion_doc);
					
					System.out.println("rs_recepcion_doc" + rs_recepcion_doc);
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);
					session.setAttribute("rs_oficina",rs_oficinas);
					session.setAttribute("rs_motivos",rs_motivos);
					
					//return (mapping.findForward("exito"));	
			}
			
			else if (tipo.equalsIgnoreCase("editar")){
			try {
				System.out.println("Si tipo es editar init" + tipo);
				System.out.println("Dentro de Editar en MantRecepcion");	

   	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
   	    		
   	    		System.out.println("La observacion es...."+ observacion);	
   	    		
   	    		session.removeAttribute("mensaje_recepcion_exito");
   	    		session.removeAttribute("mensaje_numreg");
				session.removeAttribute("mensaje_numexp");
				session.removeAttribute("mensaje_numenv");
				
   	    		/*session.removeAttribute("codigo_documento_env");
				session.removeAttribute("codigo_expediente_env");
				session.removeAttribute("numenvio");*/
				
   	    		if(observacion.equals("null")){
   	 			observacion="";
   	 			System.out.println("La observacion es null de lo contrario es igual a vacio.");
   	    		}
   	    		System.out.println("La observacion ahora es...."+ observacion);
   	    		
      	    	// daoIUsuarioDAO.of_recibir_doc_edit(cnx,codigo_oficina,codigo_documento,usuario,fecha,hora,observacion,estado_movimiento,codigo_recepcion);
				
   	    		rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos(cnx,codigo_oficina);
   	    		rs_oficinas = daoIUsuarioDAO.of_lista_oficinas(cnx);
				rs_motivos = daoIUsuarioDAO.of_lista_motivos(cnx);
				
				System.out.println("el rs_recepcion_doc es ......"+ rs_recepcion_doc);
				System.out.println("rs_recepcion_doc" + rs_recepcion_doc);
				
				session.setAttribute("operacion",tipo);
				session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);
				session.setAttribute("rs_oficina",rs_oficinas);
				session.setAttribute("rs_motivos",rs_motivos);
				//session.setAttribute("fecha_rec", formatoFecha.format(fecha));
                // session.setAttribute("hora", formatoHora.format(fecha));	
   	    		//return (mapping.findForward("exito"));	
   	    		
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
				
				//mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
   	    		//session.setAttribute("mensaje",mensaje);
   	    		
				li_retorno = daoIUsuarioDAO.parametro(cnx);
				
		   	 	System.out.println("li_retorno" + li_retorno);
		   	 	
		   	 	ls_correlativorecepcion=String.valueOf(li_retorno);
		   	 	System.out.println("ls_correlativorecepcion" + ls_correlativorecepcion);
		   	 	
				System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
				
				
				if(codigo_oficina.equals("1")){
					
					daoIUsuarioDAO.of_preliquidar_doc(cnx,codigo_oficina,codigo_documento,usuario,
							fecha,hora,observacion,estado_movimiento,secuencia_movimiento,asunto_documento
							,codigo_tipo,codigooficina,cod_ruta,cod_modalidad,descripcion);
					
				}else{
					
					//daoIUsuarioDAO.of_recibir_doc(cnx,codigo_oficina,codigo_documento,usuario,fecha,hora,observacion,estado_movimiento,codigo_recepcion,secuencia_movimiento,asunto_documento);
					
					BMesaPartes BMesaPartesVO = daoIUsuarioDAO.of_obtener_padre(cnx,codigo_documento);
					
					 System.out.println(" codigo_documento.. "+ codigo_documento);
					 
			        ls_documento = codigo_documento; 
			        ls_cod_padre = BMesaPartesVO.getCodigo_inicia();
			        System.out.println(" ls_cod_padre.. "+ ls_cod_padre);
			        ls_usuario   = String.valueOf(session.getAttribute("nombreusuario"));
			        //observar el correlativo derivacion
			       
			        
			        //ls_correla_conv=Integer.parseInt(ls_correla.trim());
			        
				
			        //System.out.println(" ls_oficina.. "+ ls_oficina);
			        System.out.println(" ls_documento.. "+ ls_documento);
			        //System.out.println(" ls_codigoPadre.. "+ ls_codigoPadre);
			        System.out.println(" ls_usuario.. "+ ls_usuario);
			        //System.out.println(" ls_correla.. "+ ls_correla);
			        //System.out.println(" ls_secuencia.. "+ ls_secuencia);
			        //System.out.println(" ls_codigo_inicia.. "+ ls_codigo_inicia);
			        //System.out.println(" ls_correla_conv.. "+ ls_correla_conv);
			        System.out.println(" codigo_expediente.. "+ codigo_expediente);
			        //System.out.println(" ls_naturaleza_documento.. "+ ls_naturaleza_documento);
			        //System.out.println(" ls_descripcion_oficina.. "+ ls_descripcion_oficina);
			        System.out.println("ls_cod_padre" + ls_cod_padre);
			        
				System.out.println("usuario... "+ usuario);
				System.out.println("codigo_documento... "+ codigo_documento);
				System.out.println("codigo_expediente... "+ codigo_expediente);
				System.out.println("codigo_expediente... "+ codigo_expediente);
				System.out.println("numero_documento... "+ numero_documento);
				System.out.println("fecha... "+ fecha);
				//System.out.println("hora... "+ hora);
				System.out.println("observacion... "+ observacion);
				
				System.out.println("codigo_oficina... "+ codigo_oficina);
				System.out.println("la fecha_rpta es.."+ fecha_rpta);
				//System.out.println("estado_movimiento... "+ estado_movimiento);
				
				System.out.println("Modificacion de... MantRecepcion .........." );
				
				/*li_retorno = daoIUsuarioDAO.parametro(cnx);
				
		   	 	System.out.println("li_retorno" + li_retorno);*/
		   	 		
	   	    		if(fecha_rpta==null){
	   	    			fecha_rpta="";
	   	   	 			System.out.println("La fecha_rpta es null de lo contrario es igual a vacio.");
	   	   	    		}
					
	   	    	
	   	    			if(ls_documento.equals(ls_cod_padre)){
	   	       	     		
	   	       	     		
	   	       	     		if(naturaleza_documento.equals("I")){
	   	       	     			
	   	       	     		System.out.println("El ls_Documento es igual a codigo padre y el documento es Interno" );	
	   	           	     	daoIUsuarioDAO.of_registrar_derivacion_interno(cnx,codigo_oficina,codigo_oficina_drv,codigo_motivo,observacion
	   	           	    			,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_cod_padre,li_retorno,
	   	           	    			fecha_rpta,Integer.valueOf(codigo_expediente.trim()),ls_descripcion_oficina,Integer.valueOf(codigo_recepcion.trim()) ,"",fecha_rpta_rq);
	   	           	    	
	   	           	    	System.out.println("Despues del Ingreso Documento,si codigo inicial es igual a cod documento" );	
	   	       	     			
	   	           	    	
	   	       	     		}else{
	   	       	     		
	   	       	     		if(chk_copia!=null){
	   	       	     		
	   	       	     		System.out.println("El chk_copia es diferente de null.." +chk_copia);
	   	       	     		
		   	       	     	for(int i=0; i<cbo_copia.length; i++) 
			    			{ 
		        			System.out.println("El PAR_DES_LAR es diferente de null.." );	
			    			System.out.println("Antes del insert Clases.." );	
			    			//daoProducto.insertar_detalle_clases(Integer.valueOf(ls_correlativo).intValue(),PAR_DES_LAR[i]);
			    			System.out.println("las cbo_copia son :" + cbo_copia[i]);
			    			
			    			daoIUsuarioDAO.of_registrar_recepcion_mismo(cnx,codigo_oficina,cbo_copia[i],codigo_motivo,observacion
	   	           	    			,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_cod_padre,li_retorno,fecha_rpta,codigo_expediente,ls_descripcion_oficina,ls_correlativorecepcion,fecha_rpta_rq);
	   	           	    	
	   	       	     		
			    			session.setAttribute("codigo_oficina_copia",cbo_copia[i]);
			    			
	   	       	     		}
		   	       	     	}
		   	       	     	
		   	       	     	System.out.println("El chk_copia es == null" );
		       	     		System.out.println("El ls_Documento es igual a codigo padre y el documento es de Entrada" );	
		           	     	daoIUsuarioDAO.of_registrar_recepcion_mismo(cnx,codigo_oficina,codigo_oficina_drv,codigo_motivo,observacion
		           	    			,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_cod_padre,li_retorno,fecha_rpta,codigo_expediente,ls_descripcion_oficina,ls_correlativorecepcion,fecha_rpta_rq);
		           	    	
		           	    	System.out.println("Despues del Ingreso Documento,si codigo inicial es igual a cod documento" );	
		           	    	
	   	       	     	
	   	       	     		
	   	    	    			
	   	       	     		}
	   	       	     	
	   	       	     	}else{
	   	       	     		
	   	       	     	System.out.println("El ls_Documento es diferente a codigo padre" );	
	   	       	     	
	   	       	     	daoIUsuarioDAO.of_registrar_derivacion_intent(cnx,codigo_oficina,codigo_oficina_drv,codigo_motivo,observacion
	   	       	    			,personas,ls_documento,secuencia_movimiento,ls_usuario,ls_cod_padre,ls_correla_conv,fecha_rpta,ls_correlativo_deriva,ls_descripcion_oficina);
	   	       	    	
	   	       	    	System.out.println("Despues del Ingreso Documento y Direccionando,si codigo inicial es diferente a cod documento" );	
	   	       	    	
	   	       	     	}

	   	  	    		//Collection rs_derivacion_doc = new ArrayList();
	   			        
	   	  	    		//rs_derivacion_doc = daoIUsuarioDAO.lista_derivacion_documentos(cnx,ls_oficina);
	   				      
	   					//System.out.println("el rs_derivacion_doc es ......"+ rs_derivacion_doc);
	   					
	   					/*mensaje_recepcion ="SE HA DERIVADO EL DOCUMENTO FISICO CON NUMERO DE REGISTRO : "+codigo_documento
	   					+ " Y NUMERO DE EXPEDIENTE : "+codigo_expediente;
						System.out.println("El mensaje_recepcion es.." + mensaje_recepcion);*/
						
	   	    			mensaje_recepcion ="DOCUMENTO RECIBIDO CON EXITO:";
	   					
	   					/*String mensaje_numreg ="N° Registro:"+ codigo_documento;
	   					String mensaje_numexp ="N° Expediente:"+ codigo_expediente;
	   					String mensaje_numenv ="N° Envio:"+ li_retorno;
						System.out.println("El mensaje_recepcion es.." + mensaje_recepcion);*/
						
	   					session.setAttribute("operacion",tipo);
	   					//session.setAttribute("rs_derivacion_doc",rs_derivacion_doc);
	   					//session.setAttribute("correlativorecepcion",ls_correla_conv);
	   					//session.setAttribute("mensaje",mensaje);
	   					//session.setAttribute("mensaje_recepcion",mensaje_recepcion);
	   					
	   					//session.setAttribute("naturaleza_documento","2");
	   					if(naturaleza_documento.equals("I")){
	   						System.out.println("La naturaleza I mensaje recepcion--2");
	   						session.setAttribute("mensaje_recepcion","2");
	   						
	   					}else{
	   						System.out.println("La naturaleza E mensaje recepcion--1");
	   						session.setAttribute("mensaje_recepcion","1");
	   						
	   					}
	   					
	   					String mensaje_recepcion_exito ="DOCUMENTO RECIBIDO CON EXITO:";
	   					String mensaje_numreg ="N° Registro:"+ codigo_documento;
	   					String mensaje_numexp ="N° Expediente:"+ codigo_expediente;
	   					String mensaje_numenv ="N° Envio:"+ li_retorno;
	   					
	   					session.setAttribute("mensaje_recepcion_exito",mensaje_recepcion_exito);
	   					session.setAttribute("mensaje_numreg",mensaje_numreg);
	   					session.setAttribute("mensaje_numexp",mensaje_numexp);
	   					session.setAttribute("mensaje_numenv",mensaje_numenv);
	   					session.setAttribute("numerodocumento_env",ls_documento);
	   					//session.setAttribute("codigo_documento_env",codigo_documento);
	   					session.setAttribute("fecha_env",fecha);
	   					session.setAttribute("codigo_motivo_env",codigo_motivo);
	   					session.setAttribute("codigo_oficina_env",codigo_oficina);
	   					session.setAttribute("observacion_env",observacion);
	   					session.setAttribute("hojaenvio","H");
	   					session.setAttribute("numenvio",String.valueOf(li_retorno));
				}
     	    	 
				
     	    	daoIUsuarioDAO.of_modificar_fecharespuesta(cnx,codigo_oficina,codigo_documento);
  	    		System.out.println("Despues de Limpiar la fecha de Respuesta 3" );
				
				  rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos(cnx,codigo_oficina);
				  rs_oficinas = daoIUsuarioDAO.of_lista_oficinas(cnx);
				  rs_motivos = daoIUsuarioDAO.of_lista_motivos(cnx);
				
				  
				System.out.println("el rs_recepcion_doc es ......"+ rs_recepcion_doc);
				System.out.println("rs_recepcion_doc" + rs_recepcion_doc);
				
				/***************************Actualizando la Cabecera Upload Ficheros************************/
 			      
			       	int parameter_upload=0; //funciones.parametroDocumentosUpload();
			      	System.out.println("Parameter dentro de MANT Derivar Documento parameter_upload.."+parameter_upload );
					
					//IResponsableColaboradoresDAO daoIResponsableColaboradoresDAO = new SqlResponsableColaboradoresDAO();
			 	System.out.println("El codigo_documento TTTT.."+codigo_documento );
			 	System.out.println("El codigo_expediente TTTT.."+codigo_expediente );
			 	System.out.println("El usuario TTTT.."+usuario );
			 	
			     daoIUsuarioDAO.DerivarDocumentoCabUploadIng(codigo_oficina,parameter_upload,Integer.parseInt(codigo_documento.trim()),Integer.parseInt(codigo_expediente .trim()),Integer.parseInt(secuencia_movimiento .trim()) ,usuario,li_retorno);

			     /***************************Fin Actualizando la Cabecera Upload Ficheros************************/
			     
				session.setAttribute("operacion",tipo);
				session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);
				session.setAttribute("rs_oficina",rs_oficinas);
				session.setAttribute("rs_motivos",rs_motivos);
				
				
				
			}catch (SQLException e) {
				e.printStackTrace();
				closeConnection(cnx);
				//return mapping.getInputForward();
	
			}
			

		} else if (tipo.equals("upload") ){
			
			System.out.println("Si tipo es init es.." + tipo);
			System.out.println("Listar Upload Archivos..." );
			
			//codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
			String ls_codigo_documento=request.getParameter("codigo_documento");
			String ls_operacion=request.getParameter("operacion");
			String ls_codigo_expediente=request.getParameter("codigo_expediente");
			String ls_secuencia_movimiento=request.getParameter("secuencia_movimiento");
			
			System.out.println("EL ls_codigo_documento upload..."+ls_codigo_documento );
			System.out.println("EL ls_operacion upload..."+ls_operacion );
			System.out.println("EL ls_codigo_expediente upload..."+ls_codigo_expediente );
		    System.out.println("EL ls_secuencia_movimiento upload..."+ls_secuencia_movimiento );
				
			Collection rs_upload_doc = new ArrayList();
			
			rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos(cnx,Integer.parseInt(ls_codigo_documento.trim()));
			
			System.out.println("el rs_upload_doc es ......"+ rs_upload_doc);
				
			session.setAttribute("operacion",ls_operacion);
			session.setAttribute("rs_upload_doc",rs_upload_doc);
			session.setAttribute("codigo_documento",ls_codigo_documento);
			session.setAttribute("codigo_expediente",ls_codigo_expediente);
			session.setAttribute("secuencia_movimiento",ls_secuencia_movimiento);
			
			return (mapping.findForward("upload"));
		}
		else if (tipo.equals("uploadficheros") ){
			
			System.out.println("Si tipo es init es.." + tipo);
			System.out.println("Listar UploadMultiples Ficheros Archivos..." );
			
			/*********************Upload de Ficheros*****************/
			String ls_codigo_documento=request.getParameter("codigo_documento");
			String ls_codigo_expediente=request.getParameter("codigo_expediente");
			String ls_secuencia_movimiento=request.getParameter("secuencia_movimiento");
			
			System.out.println("El ls_codigo_documento..." + ls_codigo_documento );
			System.out.println("El ls_codigo_expediente..." + ls_codigo_expediente );
			System.out.println("El ls_secuencia_movimiento..." + ls_secuencia_movimiento );
			
			FFormMantRecepcion myForm = (FFormMantRecepcion)form;
			codigo_documento = ((FFormMantRecepcion)form).getCodigo_documento();
			System.out.println("El codigo_documento FOR..." + codigo_documento);
 		        // Process the FormFile
 				      FormFile myFile = myForm.getTheFile();
 				      String contentType = myFile.getContentType();
 				      System.out.println("el contentType FILE UPLOAd es.." + contentType);
 	   
 			       	  String fileName    = myFile.getFileName();
 			       	System.out.println("el fileName es.." + fileName);
 			          //int fileSize       = myFile.getFileSize();
 			          byte[] fileData    = myFile.getFileData();
 			          System.out.println("el fileData es.." + fileData);
 				      //Get the servers upload directory real path name
 			          
 			          //local host public static final String carpeta = "c:/upload/sisFonCur/";
 			        //produccion public static final String carpeta = "/upload/sisFonCur/";
 			          
 				      String filePath = getServlet().getServletContext().getRealPath("/") +"uploadDocumentos";
 				      System.out.println("el filePath es.." + filePath);
 				      int ls_id_proyecto=0;
 				     String ls_new_nombre_upload="";
 				      /* Save file on the server */
 				      if(!fileName.equals("")){  
 				          System.out.println("Server path:" +filePath);
 				          //Create file
 				          
 				         //fileName
 				         int punto = fileName.lastIndexOf('.');
 				         	//fileName.substring(punto + 1);
 				        ls_new_nombre_upload=funciones.of_generar_nombre_upload(cnx,Integer.parseInt(codigo_documento.trim()) , Integer.parseInt(codigo_expediente.trim()) , Integer.parseInt(secuencia_movimiento.trim()) );
 				        // System.out.println("El ls_new_nombre_upload es...TTTT:" + ls_new_nombre_upload);
 				        ls_new_nombre_upload=ls_new_nombre_upload+"."+fileName.substring(punto + 1);
 				         //System.out.println("El ls_new_nombre_upload es...XXXXXX:" + ls_new_nombre_upload);
 				        //ls_new_nombre_upload=
 				          File fileToCreate = new File(filePath, ls_new_nombre_upload);
 				          //System.out.println("el fileToCreate es.." + fileToCreate);
 				          //If file does not exists create file                      
 				          if(!fileToCreate.exists()){
 				            FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
 			            //System.out.println("el fileOutStream es.." + fileOutStream);
 			            //System.out.println("el myFile.getFileData es.." + myFile.getFileData());
 			            fileOutStream.write(myFile.getFileData());
 			            fileOutStream.flush();
 			            fileOutStream.close();
 			          }  
 				      System.out.println("Antes Ingreso PROYECTO INVESTIGACION UPLOAD");
 				     // daoIProyectoInvestigacionDAO.ProyectoInvestigacionUpload(Integer.parseInt(ls_id_investigador.trim()),fileName,filePath,ls_id_proyecto);       
 				     daoIUsuarioDAO.DerivarDocumentoDetUploadIng(Integer.parseInt(ls_codigo_documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),Integer.parseInt(ls_secuencia_movimiento.trim()), ls_new_nombre_upload, filePath,"A","P","O");
 				   //   System.out.println("Despues del Ingreso PROYECTO INVESTIGACION");
 			      }
 				  /***************************FIn upload*****************************/
 				      
 				   System.out.println("Despues del Ingreso Upload de Ficheros Proy Invest" );
 				   
 				  Collection rs_upload_doc = new ArrayList();
 					rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos(cnx,Integer.parseInt(ls_codigo_documento.trim()));
 					
 					System.out.println("el rs_upload_doc es ......"+ rs_upload_doc);
 					
 				   session.setAttribute("operacion", "N");
 				  session.setAttribute("rs_upload_doc",rs_upload_doc);
			return (mapping.findForward("upload"));
		}	
			
		else if (tipo.equals("eliminar") ){
			
			System.out.println("Si tipo es init es.." + tipo);
			System.out.println("Listar Eliminar Upload Archivos..." );
			
			//codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
			String ls_id_det_upload=request.getParameter("id_det_upload");
			String ls_codigo_documento=request.getParameter("codigo_documento");
			
			System.out.println("EL ls_id_det_upload upload..."+ls_id_det_upload );
			
			daoIUsuarioDAO.EliminarDocumentoDetUpload(cnx,Integer.parseInt(ls_id_det_upload.trim()));
			
			 Collection rs_upload_doc = new ArrayList();
				rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos(cnx,Integer.parseInt(ls_codigo_documento.trim()));
				
				System.out.println("el rs_upload_doc es ......"+ rs_upload_doc);
				session.setAttribute("operacion", "N");
				session.setAttribute("rs_upload_doc",rs_upload_doc);
				
			return (mapping.findForward("upload"));
		}

		closeConnection(cnx);
		oForm.reset(mapping, request);
		oForm.setTipo("nuevo");
		return (mapping.findForward("exito"));
 
    	   
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
}
