package tramitedoc.concytec.action;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormMantArchivar;
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

public class AActionMantArchivar extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("DENTRO DEL MANTARCHIVAR DOC...");
		
		HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx............" + cnx);	    
	  		
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
	
		FFormMantArchivar oForm = (FFormMantArchivar) form;
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
		String secuencia_movimiento;
		String codigo_inicia;
		String naturaleza_documento;
		
		String ls_correlativorecepcion="";
		String ls_msg_error="";
		
		int li_retorno=0;
		
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
		
		System.out.println("Modificacion de... Archivar doc .........." );
		
		/*li_retorno = daoIUsuarioDAO.parametro(cnx);
		
   	 	System.out.println("li_retorno" + li_retorno);
   	 	
   	 	ls_correlativorecepcion=String.valueOf(li_retorno);
   	 	System.out.println("ls_correlativorecepcion" + ls_correlativorecepcion);*/
		
   	 	Date fecha_recep = new Date(); // fecha y hora locales 
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
		System.out.println("la fecha es.."+ formatoFecha.format(fecha_recep) ); 
		System.out.println("la hora es.."+ formatoHora.format(fecha_recep) ); 
		
		Funciones funciones = new Funciones();
		session.removeAttribute("error");
		try {
			
			
			if (tipo.equals("archivar") ){
				
				System.out.println("Si tipo es init " + tipo);
				System.out.println("Listar Archivar Documentos....." );
				
				Collection rs_archivar_doc = new ArrayList();
		        String ls_inicia=(String)request.getAttribute("inicia");
		        ls_inicia=(ls_inicia==null)? "":ls_inicia.trim();
		        String ls_codigo_doc = (String)request.getAttribute("codigo_doc");
		        
		        
		        
				if(!ls_inicia.equals("SI")){
					rs_archivar_doc = daoIUsuarioDAO.lista_documentos_archivar(cnx, codigo_oficina,(ls_codigo_doc==null)? (String)request.getParameter("codigo_busca"): ls_codigo_doc);
				}
				
			      
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_archivar_doc",rs_archivar_doc);
					
					//return (mapping.findForward("exito"));	
			}
			
			else if (tipo.equalsIgnoreCase("editar")){
			
				System.out.println("Si tipo es editar init" + tipo);
				System.out.println("Dentro de Editar en MantArchivar");	

   	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
   	    		codigo_documento = ((FFormMantArchivar)form).getCodigo_documento();
   	 		codigo_expediente = ((FFormMantArchivar)form).getCodigo_expediente();
   	 		descripcion_tipo = ((FFormMantArchivar)form).getDescripcion_tipo();
   	 		numero_documento = ((FFormMantArchivar)form).getNumero_documento();
   	 		
   	 		fecha = ((FFormMantArchivar)form).getFecha();
   	 		hora = ((FFormMantArchivar)form).getHora();
   	 		observacion = ((FFormMantArchivar)form).getObservacion();
   	 		
   	 		
   	 		//ls_codigo_oficina = ((FFormMantRecepcion)form).getCodigo_oficina();
   	 		estado_movimiento = ((FFormMantArchivar)form).getEstado_movimiento();
   	 		secuencia_movimiento = ((FFormMantArchivar)form).getSecuencia_movimiento();
   	 		codigo_inicia = ((FFormMantArchivar)form).getCodigo_inicia();
   	 		naturaleza_documento = ((FFormMantArchivar)form).getNaturaleza_documento();
   	 		
   	 		
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
   	 		System.out.println("codigo_inicia... "+ codigo_inicia);
   	 		System.out.println("naturaleza_documento... "+ naturaleza_documento);
   	    		System.out.println("La observacion es...."+ observacion);	
   	    		
   	    		if(observacion.equals("null")){
   	 			observacion="";
   	 			System.out.println("La observacion es null de lo contrario es igual a vacio.");
   	    		}
   	    		System.out.println("La observacion ahora es...."+ observacion);
   	    		
   	    		FFormMantArchivar formulario = daoIUsuarioDAO.obtenerDetalleBandejaArchivo(cnx, codigo_documento, secuencia_movimiento);
   	    		request.setAttribute("FFormMantArchivar", formulario);
					session.setAttribute("operacion",tipo);
				
					return mapping.getInputForward();
	
		} 
			else if (tipo.equalsIgnoreCase("enviar")){

				System.out.println("DENTRO DE ENVIAR DOCUMENTO.. ");
				
				codigo_documento = ((FFormMantArchivar)form).getCodigo_documento();
				codigo_expediente = ((FFormMantArchivar)form).getCodigo_expediente();
				descripcion_tipo = ((FFormMantArchivar)form).getDescripcion_tipo();
				numero_documento = ((FFormMantArchivar)form).getNumero_documento();
				
				fecha = ((FFormMantArchivar)form).getFecha();
				hora = ((FFormMantArchivar)form).getHora();
				observacion = ((FFormMantArchivar)form).getObservacion();
				
				
				//ls_codigo_oficina = ((FFormMantRecepcion)form).getCodigo_oficina();
				estado_movimiento = ((FFormMantArchivar)form).getEstado_movimiento();
				secuencia_movimiento = ((FFormMantArchivar)form).getSecuencia_movimiento();
				codigo_inicia = ((FFormMantArchivar)form).getCodigo_inicia();
				naturaleza_documento = ((FFormMantArchivar)form).getNaturaleza_documento();
				
				
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
				System.out.println("codigo_inicia... "+ codigo_inicia);
				System.out.println("naturaleza_documento... "+ naturaleza_documento);
				
				if (estado_movimiento.equals("3") || estado_movimiento.equals("7")){
	   	    		Collection rs_archivar_doc = new ArrayList();
	   	    		
	   	    		daoIUsuarioDAO.of_archivar(cnx,codigo_documento,codigo_oficina,secuencia_movimiento
	   	    				,observacion,estado_movimiento );
					
	   	    		li_retorno = daoIUsuarioDAO.parametro(cnx);
	   	    		int parameter_upload=0; //funciones.parametroDocumentosUpload();
   			      	System.out.println("Parameter dentro de MANT Derivar Documento parameter_upload.."+parameter_upload );
	   	    		System.out.println("ACTUALIZANDO LA CABECERA UPLOADING POR LA DERIVACION CON DerivarDocumentoCabUploadIng");
	   	    		
	   			    daoIUsuarioDAO.DerivarDocumentoCabUploadIng(codigo_oficina,parameter_upload,Integer.parseInt(codigo_documento.trim()),
	   			    		Integer.parseInt(codigo_expediente.trim()),Integer.parseInt(secuencia_movimiento .trim()) ,usuario,li_retorno);
	   			    
	   	    		if(estado_movimiento.equals("4")){
	   	    			mensaje="El documento "+codigo_documento+ " ha sido Des-Archivado";
	   	    		}else{
	   	    			mensaje="El documento "+codigo_documento+ " ha sido Archivado";
	   	    			session.setAttribute("listaFrameBusquedaTemp",null);
	   	    		}
	   	    		
	   	    		request.setAttribute("mensaje_exito",mensaje);
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_archivar_doc",rs_archivar_doc);
                }else
                {
                    ls_msg_error = "No se puede Archivar el Documento!!";
                    session.setAttribute("error", ls_msg_error);  
                    session.setAttribute("operacion",tipo);
                    //return (mapping.findForward("exito"));
                }
				
		} else if (tipo.equalsIgnoreCase("desarchivar")){

			codigo_documento = ((FFormMantArchivar)form).getCodigo_documento();
			codigo_expediente = ((FFormMantArchivar)form).getCodigo_expediente();
			descripcion_tipo = ((FFormMantArchivar)form).getDescripcion_tipo();
			numero_documento = ((FFormMantArchivar)form).getNumero_documento();
			
			fecha = ((FFormMantArchivar)form).getFecha();
			hora = ((FFormMantArchivar)form).getHora();
			observacion = ((FFormMantArchivar)form).getObservacion();
			
			
			//ls_codigo_oficina = ((FFormMantRecepcion)form).getCodigo_oficina();
			estado_movimiento = ((FFormMantArchivar)form).getEstado_movimiento();
			secuencia_movimiento = ((FFormMantArchivar)form).getSecuencia_movimiento();
			codigo_inicia = ((FFormMantArchivar)form).getCodigo_inicia();
			naturaleza_documento = ((FFormMantArchivar)form).getNaturaleza_documento();
			
			
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
			System.out.println("codigo_inicia... "+ codigo_inicia);
			System.out.println("naturaleza_documento... "+ naturaleza_documento);
				
				if (estado_movimiento.equals("4")) 
                {
					mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
	   	    		session.setAttribute("mensaje",mensaje);
	   	    		
	   	    		daoIUsuarioDAO.of_archivar(cnx,codigo_documento,codigo_oficina,
	   	    				secuencia_movimiento,observacion,estado_movimiento);
					
	  	    		Collection rs_archivar_doc = new ArrayList();
			        
	  	    		rs_archivar_doc = daoIUsuarioDAO.lista_documentos_archivar(cnx,codigo_oficina,"");
				      
					//System.out.println("el rs_archivar_doc es ......"+ rs_archivar_doc);
					//System.out.println("rs_archivar_doc" + rs_archivar_doc);
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_archivar_doc",rs_archivar_doc);
                } else
                {
                    ls_msg_error = "No se puede Desarchivar el Documento, el Estado del Documento debe ser Archivado..!";
                    session.setAttribute("error", ls_msg_error);       
                    //return (mapping.findForward("exito"));
                }
				
		} else if (tipo.equalsIgnoreCase("upload")){

			System.out.println("Si tipo es init es.." + tipo);
			System.out.println("Listar Upload Archivos...>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" );
			
			
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
			String ls_contador=funciones.of_contador_upload_doc(cnx, Integer.parseInt(ls_codigo_documento.trim()));
				
			System.out.println("El ls_contador es..."+ ls_contador );
				
				if(ls_contador.equals("0")){
					System.out.println("Contador igual a vacio e igual a 0..." );
					session.setAttribute("contador", "0");
					
				}else{
					System.out.println("Contador diferente de vacio e igual a 1..." );
					session.setAttribute("contador", "1");
				}
				
			session.setAttribute("operacion",ls_operacion);
			session.setAttribute("rs_upload_doc",rs_upload_doc);
			session.setAttribute("codigo_documento",ls_codigo_documento);
			session.setAttribute("codigo_expediente",ls_codigo_expediente);
			session.setAttribute("secuencia_movimiento",ls_secuencia_movimiento);
			
			return (mapping.findForward("upload"));
			
		}else if (tipo.equalsIgnoreCase("uploadficheros")){

			System.out.println("Si tipo es init es.." + tipo);
			System.out.println("Listar UploadMultiples Ficheros Archivos..." );
			
			/*********************Upload de Ficheros*****************/
			String ls_codigo_documento=request.getParameter("codigo_documento");
			String ls_codigo_expediente=request.getParameter("codigo_expediente");
			String ls_secuencia_movimiento=request.getParameter("secuencia_movimiento");
			
			System.out.println("El ls_codigo_documento..." + ls_codigo_documento );
			System.out.println("El ls_codigo_expediente..." + ls_codigo_expediente );
			System.out.println("El ls_secuencia_movimiento..." + ls_secuencia_movimiento );
			
			FFormMantArchivar myForm = (FFormMantArchivar)form;
			codigo_documento = ((FFormMantArchivar)form).getCodigo_documento();
			System.out.println("El codigo_documento FOR..." + codigo_documento);
			
 		        // Process the FormFile
 				      FormFile myFile = myForm.getTheFile();
 				      String contentType = myFile.getContentType();
 				      System.out.println("el contentType FILE UPLOAd es.." + contentType);
 	   
 			       	  String fileName    = myFile.getFileName();
 			       	  System.out.println("el fileName es.." + fileName);
 			          byte[] fileData    = myFile.getFileData();
 			          int ls_id_proyecto=0;

 			          String ls_new_nombre_upload="";
 				      /* Save file on the server */
 				      if(!fileName.equals("")){  
 				          //Create file
 				        int punto = fileName.lastIndexOf('.');
 				        System.out.println("el puntoLUUUU+1 es.." + fileName.substring(punto + 1));
 				        
  				       if(fileName.substring(punto + 1).equals("DOC")){
  				    	   	fileName = fileName.replace("DOC","doc");
 				        	System.out.println("El Cambiando el nombre a miniscula..XXXXXX:"+fileName);
 				        }
 				        
 				        ls_new_nombre_upload=funciones.of_valida_letras(fileName);
 				        
				        File fileToCreate = new File(Constantes.CarpetaArchivosDesarrollo.getNombre(), ls_new_nombre_upload);
                   
 				          if(!fileToCreate.exists()){
 				            FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
	 			            fileOutStream.write(myFile.getFileData());
	 			            fileOutStream.flush();
	 			            fileOutStream.close();
	 			          }  
 				     
 				     log.info("DerivarDocumentoDetUploadIng("+ls_codigo_documento+","+ls_codigo_expediente+","+ls_secuencia_movimiento+","+ls_new_nombre_upload+")");     
 				     daoIUsuarioDAO.DerivarDocumentoDetUploadIng(Integer.parseInt(ls_codigo_documento.trim()),Integer.parseInt(ls_codigo_expediente.trim()),Integer.parseInt(ls_secuencia_movimiento.trim()), ls_new_nombre_upload, Constantes.CarpetaArchivosDesarrollo.getNombre(),"A","P","O");

 			      }
 				  /***************************FIn upload*****************************/
 				      
 				//   System.out.println("Despues del Ingreso Upload de Ficheros Proy Invest" );
 				   
 				  Collection rs_upload_doc = new ArrayList();
 				  rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos(cnx,Integer.parseInt(ls_codigo_documento.trim()));
 					
 				  String ls_contador=funciones.of_contador_upload_doc(cnx, Integer.parseInt(ls_codigo_documento.trim()));
 					
 					//System.out.println("El ls_contador es..."+ ls_contador );
 					
 					if(ls_contador.equals("0")){
 						System.out.println("Contador igual a vacio e igual a 0..." );
 						session.setAttribute("contador", "0");
 						
 					}else{
 						System.out.println("Contador diferente de vacio e igual a 1..." );
 						session.setAttribute("contador", "1");
 					}
 					
 				//	System.out.println("el rs_upload_doc es ......"+ rs_upload_doc);
 					
		   session.setAttribute("operacion", "N");
		   session.setAttribute("rs_upload_doc",rs_upload_doc);
			return (mapping.findForward("upload"));
		
			
			
		}else if (tipo.equals("eliminar") ){
			
			System.out.println("Si tipo es init es.." + tipo);
			System.out.println("Listar Eliminar Upload Archivos..." );
			
			//codigo_documento = ((FFormMantDerivar)form).getCodigo_documento();
			String ls_id_det_upload=request.getParameter("id_det_upload");
			String ls_codigo_documento=request.getParameter("codigo_documento");
			
			System.out.println("EL ls_id_det_upload upload..."+ls_id_det_upload );
			
			
			String nombre_archivo=daoIUsuarioDAO.obtenerNombreArchivo(cnx,ls_id_det_upload,ls_codigo_documento);
			//Falta eliminar Fisicamente.
			log.info("nombre de archivo a eliminar ..." +nombre_archivo);
			
			
			
			daoIUsuarioDAO.EliminarDocumentoDetUpload(cnx,Integer.parseInt(ls_id_det_upload.trim()));
			eliminarArchivoFisicamente(nombre_archivo);
			log.info("se elimino el archivo: " +nombre_archivo);
			
			 Collection rs_upload_doc = new ArrayList();
				rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos(cnx,Integer.parseInt(ls_codigo_documento.trim()));
				
				if(rs_upload_doc.isEmpty()){
					System.out.println("Contador igual a vacio e igual a 0..." );
					session.setAttribute("contador", "0");
				}
				
				System.out.println("el rs_upload_doc es ......"+ rs_upload_doc);
				session.setAttribute("operacion", "N");
				session.setAttribute("rs_upload_doc",rs_upload_doc);
				
			return (mapping.findForward("upload"));
		}

		//closeConnection(cnx);
		oForm.reset(mapping, request);
		oForm.setTipo("archivar");
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
	
	private void eliminarArchivoFisicamente(String nombre_archivo) {
		File tem= new File(IConstante.filePath,nombre_archivo);       		
		if(tem.exists()&& tem.isFile()){
			if(tem.delete())
			{
				log.info("archivo: "+nombre_archivo +"Elimando con exito");
			}else
				log.info("archivo: "+nombre_archivo +"no pudo ser eliminado");
		}
	}
}
