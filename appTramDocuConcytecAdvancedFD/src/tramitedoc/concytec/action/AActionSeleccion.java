package tramitedoc.concytec.action;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BPersona;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.ValidaSessionAction;

/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionSeleccion extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(AActionSeleccion.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		log.info("Dentro de AAction Seleccion.."); 
		
		/*FFormSeleccion oForm = (FFormSeleccion) form;
		String ls_seleccion = oForm.getRb_seleccion();*/
		//log.info("El ls_seleccion... "+ ls_seleccion);
		
		String ls_asociardoc = "";	
		String ls_codigo_oficina = "";
		String codigo_oficina = "";
		String ls_seleccion = "";	
		String codigo_personal = "";
		
		String ls_documento = "";
		String ls_codigo_expediente = "";
		String ls_descripcion_tipo = "";
		String ls_numero_documento = "";
		String usuario_actual = "";
		String ls_origen_documento="";
		String ls_desc_origen="";
		String ls_tipo_persona="";
		String ls_naturaleza_documento="";
		
		int li_retorno=0;
		String ls_correlativorecepcion="";
		String ls_msg_error="";
		ls_seleccion= request.getParameter("rb_seleccion");
		
		
		log.info("El ls_seleccion... "+ ls_seleccion);
		
		HttpSession session = request.getSession(true); 
        
       if (session==null){
        	return (mapping.findForward("expiracion"));
        }
        
        Connection cnx = getConnection(request, "principal"); 
        
		log.info("El cnx  ==> " + cnx);
		
		try {
			
			session.removeAttribute("naturaleza_doc");
			session.removeAttribute("mensaje");
			session.removeAttribute("mensaje_reg");
			session.removeAttribute("mensaje_exp");
			session.removeAttribute("numero_documento");
			
			session.removeAttribute("folios_documento");
			session.removeAttribute("firmadopor");
			session.removeAttribute("dirigido");
			session.removeAttribute("asunto_documento");
			session.removeAttribute("observa_documento");
			session.removeAttribute("numero_referencia");
			session.removeAttribute("tipopersona");
			session.removeAttribute("codigocontador");
			session.removeAttribute("rs_upload_doc");
			session.removeAttribute("codigo_documento");
			
			//session.removeAttribute("operacion ");
			/*Collection rs_tipodoc = new ArrayList();
			Collection rs_personas_nuestraoficina = new ArrayList();
			Collection rs_oficina = new ArrayList();
			Collection rs_solicitud = new ArrayList();*/
			
			IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			BUsuario userSystem = new BUsuario();

			userSystem =(BUsuario) session.getAttribute("nombre_usuario");
			
			
	        Collection rs_tipodoc_todos = daoIUsuarioDAO.of_lista_tipdoc_todos(cnx);
	        //BOficinas NombreOficina = daoIUsuarioDAO.of_nombre_oficina(cnx,ls_oficina);
	        
	        // ls_codigo_oficina = NombreOficina.getCodigo_oficina();
	        //String ls_descripcion_oficina = NombreOficina.getDescripcion_oficina();
	        
	        //log.info("El ls_descripcion_oficina"+ ls_descripcion_oficina);
	        
	        //BPersonal DatosUsuario = daoIUsuarioDAO.of_datos_usuario(cnx,ls_persona,ls_codigo_oficina);
	        
	        //Collection rs_personas_nuestraoficina = daoIUsuarioDAO.of_lista_personas_oficinas(ls_oficina);
	        Collection rs_oficina = null;
	        Collection rs_oficina_other = null; //daoIUsuarioDAO.of_lista_oficinas(cnx);
	        
			ls_codigo_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
			
			log.info("El ls_codigo_oficina"+ ls_codigo_oficina);
	        /**
			 * JAZANERO
			 */
			String ls_codigo_institucion = String.valueOf(session.getAttribute("ls_codigo_institucion"));
			String ls_grupo_oficina = String.valueOf(session.getAttribute("grupo_oficina"));
			String es_padre=null;
			ls_codigo_institucion=(ls_codigo_institucion==null)?"":ls_codigo_institucion;
			ls_grupo_oficina=(ls_grupo_oficina==null)?"":ls_grupo_oficina;
			
			log.info("SEDE -> "+ls_codigo_institucion);
			log.info("OFICINA -> "+ls_grupo_oficina);
			
	        if(!ls_codigo_institucion.equals("")){
	        	rs_oficina= daoIUsuarioDAO.of_lista_oficinas_institucion(ls_codigo_institucion,ls_grupo_oficina,true);
	        	rs_oficina_other= daoIUsuarioDAO.of_lista_oficinas_institucion(ls_codigo_institucion,false);
	        	es_padre=daoIUsuarioDAO.of_es_padre(ls_codigo_oficina);
    		}
	        log.info("-> "+es_padre);
	        
	        Collection rs_motivos = daoIUsuarioDAO.of_lista_motivos(cnx);
	        //Collection rs_correlativos = daoIUsuarioDAO.of_lista_correlativos(cnx,ls_oficina);
	        Collection rs_solicitud = daoIUsuarioDAO.of_lista_solicitud(cnx);
	      
			
			ls_documento = String.valueOf(session.getAttribute("documento"));
			ls_codigo_expediente = String.valueOf(session.getAttribute("codigo_expediente"));
			ls_descripcion_tipo = String.valueOf(session.getAttribute("descripcion_tipo"));
			ls_numero_documento = String.valueOf(session.getAttribute("numero_documento"));
			
			
		
			log.info("Antes de la operacion E");
			
            if (ls_seleccion.equals("E")) {
                // Pagina de Registro de documento por Entrada ....
            	log.info("Entry E"); 
            	
            	session.removeAttribute("FFormMantDocumento");
            	session.removeAttribute("valorct");
        		session.removeAttribute("desc_origen");
        		session.removeAttribute("descripcion_persona");
        		session.removeAttribute("mensajeregistro");
        		session.removeAttribute("operacionpopup");
        		session.removeAttribute("operacion");
        		session.removeAttribute("descripcion_persona_dir");
        		session.removeAttribute("rs_upload_doc_internos");
        		//session.removeAttribute("mensaje");
        		
            	
            	
            	if(ls_codigo_oficina.equals("0002") || ls_codigo_oficina.equals("0003")){
            		
            		log.info("si es igual a 0002 o 0003"); 
            		
            		 ls_msg_error = "El Documento de entrada no se puede registrar, debido a que esta oficina no es MESA DE PARTES...! ";
	                    session.setAttribute("error", ls_msg_error);       
	                    return (mapping.findForward("mensaje"));
	                    
            	}else{
            		
            		log.info("si es diferente a 0002 o 0003"); 
            		String ls_numero_registro="";
            		String ls_codigo_oficina_rq="2";
            		String ls_medio_rq="OR";
            		//String ls_codigo_persona="2";
            		
            		/**
            		 * JAZANERO es para setear las oficinas concytec o fondecyt
            		 */
            		
            		session.setAttribute("ls_codigo_institucion", ls_codigo_institucion);
            		log.info("Jo - CodigoIns"+ ls_codigo_institucion);
            		
            		
            		//JM
            		if(ls_codigo_institucion.equals("3")){
            			ls_codigo_oficina_rq="8";
            		}
            		
            		Funciones funciones=new Funciones();
            		Date fecha = new Date(); // fecha y hora locales 
            		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
            		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
            		log.info("la fecha es.."+ formatoFecha.format(fecha) ); 
            		log.info("la hora es.."+ formatoHora.format(fecha) ); 
            		
            		usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
            		log.info("ls_codigo_oficina es.."+ ls_codigo_oficina ); 
            		
            		
            		/*ls_origen_documento = String.valueOf(session.getAttribute("origen_documento"));
        			ls_desc_origen = String.valueOf(session.getAttribute("desc_origen"));
        			ls_tipo_persona = String.valueOf(session.getAttribute("tipo_persona"));
        			ls_naturaleza_documento = String.valueOf(session.getAttribute("naturaleza_documento"));
        			*/
        			log.info("el ls_codigo_oficina es ......"+ ls_codigo_oficina);
        			log.info("el ls_origen_documento es ......"+ ls_origen_documento);
        			log.info("el ls_desc_origen es ......"+ ls_desc_origen);
        			log.info("el ls_tipo_persona es ......"+ ls_tipo_persona);
        			log.info("el ls_naturaleza_documento es ......"+ ls_naturaleza_documento);
        			
            		log.info("El usuario actual es.." + usuario_actual);
            		codigo_oficina = funciones.of_codigo_oficina(cnx, usuario_actual);
            		log.info("El codigo de Oficina es.." + codigo_oficina);
            		codigo_personal = funciones.of_codigo_persona(cnx, usuario_actual);
            		log.info("El codigo de Persona es.." + codigo_personal);
            		
            		/******************************************************/
        			//if(!ls_codigo_oficina.equals("1")){
            		codigo_oficina = funciones.validar_nulo(codigo_oficina);            		
        			if(Integer.parseInt(codigo_oficina.trim())>1){
					log.info("lA OFICINA ES DIFERENTE DE 1...");
					BPersona PersonaVO= daoIUsuarioDAO.of_detalle_codigo_persona_oficina(cnx,codigo_oficina);
					//log.info("La ls_persona_oficina es.." + ls_persona_oficina);
					
					String ls_codigo_persona_user=PersonaVO.getCodigo_persona();
					String ls_nombre_persona_user=PersonaVO.getNombre_persona();
					String ls_tipo_user=PersonaVO.getTipo();
					log.info("Despues del Bean..");
					String ls_firmado_por=funciones.of_nombre_director_oficina(cnx,codigo_oficina);
					
					log.info("La ls_codigo_persona_user es.." + ls_codigo_persona_user);
					log.info("La ls_nombre_persona_user es.." + ls_nombre_persona_user);
					log.info("La ls_tipo_user es.." + ls_tipo_user);
					log.info("La ls_firmado_por es.." + ls_firmado_por);
					
					session.setAttribute("origen_documento",ls_codigo_persona_user);
					session.setAttribute("desc_origen",ls_nombre_persona_user.trim());
					session.setAttribute("firmado_por",ls_firmado_por.trim());
					session.setAttribute("tipo_persona","JURIDICA");
					session.setAttribute("naturaleza_documento","JURIDICA");
					session.setAttribute("operacion","T");
					/*session.setAttribute("origen_documento",ls_origen_documento);
 					session.setAttribute("desc_origen",ls_desc_origen);
 					session.setAttribute("tipo_persona","JURIDICA");
 					session.setAttribute("naturaleza_documento","JURIDICA");
 					*/
   			 		}else if(Integer.parseInt(codigo_oficina.trim())==1){
   			 			session.setAttribute("rb_seleccion", "E");
   			 		}
        		
        		/*********************************************************/
            		Collection rs_destinatario = daoIUsuarioDAO.of_lista_personas_oficinas(String.valueOf(ls_codigo_oficina_rq));
            		DocumentosInternosService service = new DocumentosInternosServiceImplement();	
               	 	
            		 session.setAttribute("naturaleza_doc", ls_seleccion);
                     session.setAttribute("rs_tipodoc", rs_tipodoc_todos);
                     
                     
                     session.setAttribute("rs_oficina", rs_oficina);
                     session.setAttribute("rs_oficina_other",rs_oficina_other);
                     session.setAttribute("rs_solicitud", rs_solicitud);
                     session.setAttribute("fecha", formatoFecha.format(fecha));
                     session.setAttribute("hora", formatoHora.format(fecha));
                     session.setAttribute("correlativorecepcion", ls_correlativorecepcion);
                     session.setAttribute("usuario_actual", usuario_actual);
                     session.setAttribute("codigo_oficina", codigo_oficina);
                     session.setAttribute("rs_persona", codigo_personal);
                     session.setAttribute("rs_destinatario", rs_destinatario);
                     session.setAttribute("codigo_oficina_rq", ls_codigo_oficina_rq);
                     session.setAttribute("medio_rq",ls_medio_rq);
                     session.setAttribute("codigo_solicitud", 1);
                     session.setAttribute("es_padre", es_padre);
                     
                     
                     if(userSystem==null){
                    	 userSystem = new BUsuario();
                    	 userSystem.setUsuario(usuario_actual);
                    	 
                     }
                     userSystem=service.obtenerResponsabilidad(userSystem);
                     log.info("Que sale >>>>>>>"+userSystem.getFlag());
                     log.info("Que sale >>>>>>>"+userSystem.getResponsable());
                     
                     userSystem=service.obtenerResponsabilidad(userSystem);
                     //userSystem.setUsuario(usuario_actual);
                    
                     //session.setAttribute("numero_registro", ls_numero_registro);
                     
                     log.info("Que sale >>>>>>>"+userSystem.getFlag());
                     log.info("Que sale >>>>>>>"+userSystem.getResponsable());
                     
                     session.setAttribute("nombre_usuario",userSystem);
                     
                     log.info("Entreeeeeeeee nombre_usuario: "+usuario_actual);
                /*
                 * Agregado por mpelaez
                 *     
                      int FIRMADO=3;
                      ArrayList<DocumentoInternoBean> listaDocumentosInternos=service.getlistaDocumentosInternos(Integer.parseInt(ls_codigo_oficina),FIRMADO);
                      request.getSession().setAttribute("listaDocumentosInternos",listaDocumentosInternos);
                     
                      Collection areasAdministrativasList=service.getLista_oficinas();
                      request.getSession().setAttribute("areasAdministrativasList",areasAdministrativasList);
                     
	     			  ArrayList<EstandarBean> tipoDocumentoInternosList=service.getTipoDocumentosInternosLista();	
	     			  log.info("tamnio lista tipoDocumentoInternos:  "+tipoDocumentoInternosList.size());
	     			  request.getSession().setAttribute("tipoDocumentoInternosList",tipoDocumentoInternosList);
	     			   
	     			  ArrayList<EstandarBean> estadoDocumentoInternoList=service.getEstadoDocumentoInternoLista();
	  			      request.getSession().setAttribute("estadoDocumentoInternoList",estadoDocumentoInternoList);
	  			      log.info("tamnio lista estadoDocumentoInternoList:  "+estadoDocumentoInternoList.size());
	  			   */   //agregado mpelaez
                      return (mapping.findForward("entrada"));	 
            	}
               
            }
            
            if (ls_seleccion.equals("I")) { 
            	log.info("Entry I");
            	
                session.setAttribute("naturaleza_doc",ls_seleccion);
                session.setAttribute("rs_tipodoc", rs_tipodoc_todos);
                //session.setAttribute("rs_personas_nuestraoficina", rs_personas_nuestraoficina);
                //session.setAttribute("nombre_oficina",nombre_oficina);
                session.setAttribute("rs_oficina",rs_oficina);
                session.setAttribute("rs_oficina_other",rs_oficina_other);
                
                session.setAttribute("rs_solicitud",rs_solicitud);
                session.setAttribute("es_padre", es_padre);
                //session.removeAttribute("mensaje");
                
                // Pagina de Registro de documento Interno ....
                return (mapping.findForward("interno"));
            }
            
            if (ls_asociardoc.equals("W")) { 
            	
            
                session.setAttribute("naturaleza_doc",ls_asociardoc);
                session.setAttribute("rs_tipodoc", rs_tipodoc_todos);
                //session.setAttribute("rs_personas_nuestraoficina", rs_personas_nuestraoficina);
                //session.setAttribute("nombre_oficina",nombre_oficina);
                session.setAttribute("rs_oficina",rs_oficina);
                session.setAttribute("rs_solicitud",rs_solicitud);
                
                session.setAttribute("documento",ls_documento);
                session.setAttribute("codigo_expediente",ls_codigo_expediente);
                session.setAttribute("descripcion_tipo",ls_descripcion_tipo);
                session.setAttribute("numero_documento",ls_numero_documento);
                session.setAttribute("es_padre", es_padre);
                
                // Pagina de Registro de documento Interno asociado a documento de entrada ....
                return (mapping.findForward("asociarintent"));
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
}
