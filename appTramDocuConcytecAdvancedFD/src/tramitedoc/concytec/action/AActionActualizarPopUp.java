package tramitedoc.concytec.action;

	import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.dao.*;
import tramitedoc.concytec.form.*;
import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;

	public class AActionActualizarPopUp extends BaseAjaxAction {
		protected  Log log = LogFactory.getLog(AActionActualizarPopUp.class);
	    public String getXmlContent(ActionMapping actionMapping,
	                                ActionForm actionForm,
	                                HttpServletRequest request,
	                                HttpServletResponse response) throws Exception {
	    	
	    	response.setContentType("text/html;charset=UTF-8");
	    	response.setCharacterEncoding("UTF-8");
	  		request.setCharacterEncoding("UTF-8");
	  		HttpSession session = request.getSession(false); 
	  		DocumentosInternosService service = new DocumentosInternosServiceImplement();	
	  		
	        String cambia = request.getParameter("cambia");
	        
	        log.info("Cambia es XXXXX"+ cambia);
	        
	        if (!esVacio(cambia)) {
	        	
	        	IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
				
	            if (cambia.equals("actualiza")) {
	            	log.info("Cambia es igual a actualiza");
	            	FFormDocumentoInterno oForm = (FFormDocumentoInterno) session.getAttribute("FFormDocumentoInterno");
	            	//String hayDocumentosAdjuntos = (String) request.getAttribute("hayDocumentosAdjuntos");
	            	Collection rs_upload_doc_internos_temp = new ArrayList();	
	  		  		rs_upload_doc_internos_temp = (Collection) session.getAttribute("rs_upload_doc_internos");
	  		  		
	  		  		StringBuilder titulos = new StringBuilder("");
	  		  		StringBuilder titulos2 = new StringBuilder("");
	  		  	
	  		  		titulos.append("&nbsp;<img src='img/attach2icon.png'  width='16' height='16' border='0' align='middle' ");
	  		  		
	  		  		PrintWriter out = response.getWriter();
	  		  		if((oForm.getArchivos() != null && oForm.getArchivos().size() > 0)){
	  		  				Collection _archivos = oForm.getArchivos();
		  		  			Iterator it = _archivos.iterator();
		  		  			titulos2.append("title='");
			  		        while(it.hasNext()) {
			  		        	BArchivo archivoenlista=(BArchivo)it.next();
			  		        	titulos2.append(archivoenlista.getNombre_archivo()+",");
			  		        }
			  		        
			  		        if(rs_upload_doc_internos_temp!=null && rs_upload_doc_internos_temp.size()>0){
			  		  			it = rs_upload_doc_internos_temp.iterator();
				  		        while(it.hasNext()) {
				  		        	BArchivo archivoenlista=(BArchivo)it.next();
				  		        	titulos2.append(archivoenlista.getNombre_archivo()+",");
				  		        	
				  		        }
		  		  			}
			  		        titulos.append(titulos2.substring(0, titulos2.length()-1)+"' >");
			  		        out.printf(titulos.toString());
	  		  				
	  		  		}else{
	  		  			if(rs_upload_doc_internos_temp!=null && rs_upload_doc_internos_temp.size()>0){
		  		  			Iterator it = rs_upload_doc_internos_temp.iterator();
		  		  			titulos2.append("title='");
			  		        while(it.hasNext()) {
			  		        	BArchivo archivoenlista=(BArchivo)it.next();
			  		        	titulos2.append(archivoenlista.getNombre_archivo()+",");
			  		        	
			  		        }
			  		        titulos.append(titulos2.substring(0, titulos2.length()-1)+"' >");
			  		        out.printf(titulos.toString());
	  		  			}else{
	  		  				out.printf("&nbsp;");
	  		  			}
	  		  		}
  		  			

	  		       out.close();
	  		  	
	            } else if (cambia.equals("actualizaarchiva")) {
	            	log.info("Cambia es igual a actualiza");
	            	String contador = (String) session.getAttribute("contador");
	            	contador = (contador==null)? "0":contador;
	            	
	  		  		PrintWriter out = response.getWriter();
	  		  		if (!contador.equals("0")){
	  		  			log.info("entre");
	  		  			out.printf("<p><label>Adjuntar Doc.:</label><input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' title='Agregar Archivos'  class='button' >");
	  		  			out.printf("<img src='img/attach2icon.png' title='Documentos Adjuntos'></p>");
	  		  			
	  		  		}else{
	  		  			log.info("entre2");
	  		  			out.printf("<p><label>Adjuntar Doc.:</label><input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' title='Agregar Archivos'  class='button' ></p>");
	  		  		}
	  		  		out.close();
	            } else 	if (cambia.equals("actualizaderiva")) {
	            	log.info("Cambia es igual a actualizaderiva");
	            	String operacion = (String) session.getAttribute("operacion");
	            	String hayDocumentosAdjuntos = (String) session.getAttribute("contador");
	            	String nombre_file_adjunto = (String) session.getAttribute("nombre_file_adjunto");
	            	nombre_file_adjunto=(nombre_file_adjunto==null)? "":nombre_file_adjunto;
	            	hayDocumentosAdjuntos=(hayDocumentosAdjuntos==null)? "0":hayDocumentosAdjuntos;
	  		  		PrintWriter out = response.getWriter();
	  		  		
	  		  		if(operacion.equals("editar") || operacion.equals("N") || operacion.equals("editar_obs")) {
	  		  			log.info("entre actualizaderiva");
	  		  			if(hayDocumentosAdjuntos.equals("1")){
		  		  			out.printf("<p><label>Doc. de Rpta:</label>");	
			  		  		out.printf("<input  class='input-medium'  id='doc_resp'  maxlength='60' size='40' name='doc_resp' value='%1s' >",nombre_file_adjunto);
			  		  		out.printf("<input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' title='Agregar Archivos'  class='button' >"); 
			  		  		out.printf("<c:choose><c:when test='${operacion ==  'contadjunto' && codigocontador ==  '1'}'>");
			  		  		out.printf("<img src='img/attach2icon.png' title='Documentos Adjuntos'>	 ");	
			  		  		out.printf("</c:when></c:choose></p>");
	  		  			}else{
	  		  				System.out.println("jaaaaaa");
		  		  			out.printf("<p><label>Doc. de Rpta:</label>");	
			  		  		out.printf("<input  class='input-medium'  id='doc_resp'  maxlength='60' size='40' name='doc_resp' value=''>");
			  		  		out.printf("<input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' title='Agregar Archivos'  class='button' >"); 
			  		  		out.printf("</p>");
	  		  			}
	  		  		}else{
	  		  			log.info("entre2");
	  		  		
	  		  		}
	  		  		
	  		  		/*
	  		  		 * if(hayDocumentosAdjuntos.equals("1")) {
	  		  			log.info("entre actualizaderiva");
	  		  		out.printf("<input  class='caja1'  id='doc_resp'  maxlength='60' size='40' name='doc_resp' value='%1s' >",nombre_file_adjunto);
	  		  		out.printf("<div id='cuadro-docs' style='display:visibility; float:left; '>"); 
	  		  		out.printf("<input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' alt='Agregar Archivos' style=' width: 120;' class='boton' >");
	  		  		out.printf("</div>");
	  		  		out.printf("<div id='cuadro-img' style='display:visibility; float:left;'> ");	
	  		  		out.printf("&nbsp;<img src='img/attach2icon.png'  width='16' height='16' border='0' align='middle'>");
	  		  		out.printf("</div>");	
	  		  		}else{
	  		  			log.info("entre2");
	  		  		out.printf("<input  class='caja1'  id='doc_resp'  maxlength='60' size='40' name='doc_resp' >");
	  		  		out.printf("<div id='cuadro-docs' style='display:visibility; float:left; '>"); 
	  		  		out.printf("<input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' alt='Agregar Archivos' style=' width: 120;' class='boton' >");
	  		  		out.printf("</div>");
	  		  		out.printf("&nbsp;");
	  		  		}
	  		  		 */
	  		  	out.close();
	            }else 	if (cambia.equals("actualizareitera")) {
	            	log.info("Cambia es igual a actualizareitera");
	            	Collection rs_upload_doc = new ArrayList();
	            	String pcodigo_documento = request.getParameter("codigo_documento");
	            	String poperacion = request.getParameter("operacion");
	            	//rs_upload_doc = iRe.lista_upload_documentos_detalle(Integer.parseInt(pcodigo_documento));

	            	//String hayDocumentosAdjuntos = (String) session.getAttribute("contador");
	            	String nombre_file_adjunto = (String) session.getAttribute("nombre_file_adjunto");
	            	nombre_file_adjunto=(nombre_file_adjunto==null)? "":nombre_file_adjunto;
	            	poperacion=(poperacion==null)? "":poperacion;
	  		  		PrintWriter out = response.getWriter();
	  		  		
	  		  			
  		  			if(poperacion.equals("limpia")){
	  		  			out.printf("<p><label>Doc. Reiterativo:</label>");	
		  		  		out.printf("<input  class='input-medium'  id='doc_resp'  maxlength='60' size='40' name='doc_resp' value='%1s' >",nombre_file_adjunto);
		  		  		out.printf("<input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' title='Agregar Archivos'  class='button' >"); 
		  		  		out.printf("</p>");
  		  			}else{

	  		  			out.printf("<p><label>Doc. Reiterativo:</label>");	
		  		  		out.printf("<input  class='input-medium'  id='doc_resp'  maxlength='60' size='40' name='doc_resp' value='%1s' >",nombre_file_adjunto);
		  		  		out.printf("<input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' title='Agregar Archivos'  class='button' >"); 
		  		  		out.printf("<img src='img/attach2icon.png' title='Documentos Adjuntos'>	 ");	
		  		  		out.printf("</p>");
  		  			}
		  		  			

	  		  	out.close();
	            }else if (cambia.equals("iniciaactualizaderiva")) {
	            	log.info("Cambia es igual a iniciaactualizaderiva");

	            	String pcodigo_documento = request.getParameter("pcodigo_documento");
	            	String psecuencia_movimiento = request.getParameter("psecuencia_movimiento");
	            	System.out.println(pcodigo_documento);
	            	System.out.println(psecuencia_movimiento);
	            	BInfo binfo = new BInfo() ;
	            	binfo = service.getInfo(pcodigo_documento,psecuencia_movimiento);
	            	
	            	String doc_rpta=binfo.getDoc_rpta();
	            	String nombre_archivo = binfo.getNombrearchivo();
	            	
	            	doc_rpta = (doc_rpta==null)? "":doc_rpta;
	            	nombre_archivo = (nombre_archivo==null)? "":nombre_archivo;
	            	
	            	if(!doc_rpta.equals("") && doc_rpta.length()>0){
	            		System.out.println("1");
	            		session.setAttribute("nombre_file_adjunto",doc_rpta);
	            		session.setAttribute("contador", "1");
	            	}else{
	            		if(!nombre_archivo.equals("") && nombre_archivo.length()>0){
	            			System.out.println("2");
	            			session.setAttribute("nombre_file_adjunto",nombre_archivo);
		            		session.setAttribute("contador", "1");
	            		}else{
	            			System.out.println("3");
	            			session.removeAttribute("nombre_file_adjunto");
	            			session.removeAttribute("contador");
	            		}
	            	}
	            	
	            	/*String hayDocumentosAdjuntos = (String) session.getAttribute("contador");
	            	String nombre_file_adjunto = (String) session.getAttribute("nombre_file_adjunto");
	            	nombre_file_adjunto=(nombre_file_adjunto==null)? "":nombre_file_adjunto;
	            	hayDocumentosAdjuntos=(hayDocumentosAdjuntos==null)? "0":hayDocumentosAdjuntos;*/
	            	
	            	
	  		  		//PrintWriter out = response.getWriter();
	  		  		
	  		  		/*if(operacion.equals("editar") || operacion.equals("N") || operacion.equals("editar_obs")) {
	  		  			log.info("entre actualizaderiva");
	  		  			if(hayDocumentosAdjuntos.equals("1")){
		  		  			out.printf("<p><label>Doc. de Rpta:</label>");	
			  		  		out.printf("<input  class='input-medium'  id='doc_resp'  maxlength='60' size='40' name='doc_resp' value='%1s' >",nombre_file_adjunto);
			  		  		out.printf("<input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' title='Agregar Archivos'  class='button' >"); 
			  		  		out.printf("<c:choose><c:when test='${operacion ==  'contadjunto' && codigocontador ==  '1'}'>");
			  		  		out.printf("<img src='img/attach2icon.png' title='Documentos Adjuntos'>	 ");	
			  		  		out.printf("</c:when></c:choose></p>");
	  		  			}else{
	  		  				System.out.println("jaaaaaa");
		  		  			out.printf("<p><label>Doc. de Rpta:</label>");	
			  		  		out.printf("<input  class='input-medium'  id='doc_resp'  maxlength='60' size='40' name='doc_resp' value=''>");
			  		  		out.printf("<input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' title='Agregar Archivos'  class='button' >"); 
			  		  		out.printf("</p>");
	  		  			}
	  		  		}else{
	  		  			log.info("entre2");
	  		  		
	  		  		}*/
	  		  		
	  		  		/*
	  		  		 * if(hayDocumentosAdjuntos.equals("1")) {
	  		  			log.info("entre actualizaderiva");
	  		  		out.printf("<input  class='caja1'  id='doc_resp'  maxlength='60' size='40' name='doc_resp' value='%1s' >",nombre_file_adjunto);
	  		  		out.printf("<div id='cuadro-docs' style='display:visibility; float:left; '>"); 
	  		  		out.printf("<input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' alt='Agregar Archivos' style=' width: 120;' class='boton' >");
	  		  		out.printf("</div>");
	  		  		out.printf("<div id='cuadro-img' style='display:visibility; float:left;'> ");	
	  		  		out.printf("&nbsp;<img src='img/attach2icon.png'  width='16' height='16' border='0' align='middle'>");
	  		  		out.printf("</div>");	
	  		  		}else{
	  		  			log.info("entre2");
	  		  		out.printf("<input  class='caja1'  id='doc_resp'  maxlength='60' size='40' name='doc_resp' >");
	  		  		out.printf("<div id='cuadro-docs' style='display:visibility; float:left; '>"); 
	  		  		out.printf("<input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' alt='Agregar Archivos' style=' width: 120;' class='boton' >");
	  		  		out.printf("</div>");
	  		  		out.printf("&nbsp;");
	  		  		}
	  		  		 */
	  		  	//out.close();
	            } 
	            
	            else if (cambia.equals("limpiaderiva")) {
	            	log.info("Cambia es igual a limpiaderiva");
	            	
	  		  		PrintWriter out = response.getWriter();
	  		  		out.printf("&nbsp;");
	  		  		
	  		  		out.close();
	  		  		
	            } else if (cambia.equals("limpiaderivaall")) {
	            	log.info("Cambia es igual a limpiaderivaall");
	            	session.removeAttribute("nombre_file_adjunto");
        			session.removeAttribute("contador");
        			
	  		  		PrintWriter out = response.getWriter();
	  		  		out.printf("&nbsp;");
	  		  		
	  		  		out.close();
	            } else if (cambia.equals("limpiaarchiva")) {
	            	session.removeAttribute("nombre_file_adjunto");
        			session.removeAttribute("contador");
        			
	  		  		PrintWriter out = response.getWriter();
	  		  		out.printf("<p><label>Adjuntar Doc.:</label><input type='button'  id='btnUpload' name='btnUpload'  value='Agregar Archivos' onClick='AgregarArchivos()' title='Agregar Archivos'  class='button' ></p>");
	  		  		out.close();
	            }
	            else if (cambia.equals("actualizaccp")) {
	            	log.info("Cambia es igual a actualiza");
	            	FFormDocumentoInternoEspecialC oForm = (FFormDocumentoInternoEspecialC) session.getAttribute("FFormDocumentoInternoEspecialC");
	            	//String hayDocumentosAdjuntos = (String) request.getAttribute("hayDocumentosAdjuntos");
	            	Collection rs_upload_doc_internos_temp = new ArrayList();	
	  		  		rs_upload_doc_internos_temp = (Collection) session.getAttribute("rs_upload_doc_internos");
	  		  //	!rs_upload_doc_internos_temp.isEmpty() || (oForm.getArchivos() != null && oForm.getArchivos().size() > 0)
	  		  		PrintWriter out = response.getWriter();
	  		  		if((oForm.getArchivos() != null && oForm.getArchivos().size() > 0) || (rs_upload_doc_internos_temp!=null && rs_upload_doc_internos_temp.size()>0) ){
	  		  			log.info("entre3");
	  		  			out.printf("&nbsp;<img src='img/attach2icon.png'  width='16' height='16' border='0' align='middle'>");
	  		  			
	  		  		}else{
	  		  			log.info("entre4");
	  		  		out.printf("&nbsp;");
	  		  		}
	  		  	out.close();
	            } 
	            
	        }
	        return "";
	    }

	    static boolean esVacio(String cad) {
	        return cad == null || cad.trim().equals("");
	    }


	}