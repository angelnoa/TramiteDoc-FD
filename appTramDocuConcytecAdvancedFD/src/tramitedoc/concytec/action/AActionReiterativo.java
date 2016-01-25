package tramitedoc.concytec.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BDatosFirmante;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IReiterativoDAO;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlReiterativoDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormReiterativo;
import tramitedoc.concytec.form.FFormReiterativoMantenimiento;
import tramitedoc.concytec.form.FFormReiterativoRegistro;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.service.ReiterativoService;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.ValidaSessionAction;

public class AActionReiterativo extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(AActionReiterativo.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		//Connection cnx = getConnection(request, "principal");
		
		
		FFormReiterativo fRe = (FFormReiterativo)form;
		FFormReiterativoRegistro fReRe=new FFormReiterativoRegistro();
		FFormReiterativoMantenimiento fReMa=new FFormReiterativoMantenimiento();
		String codigo_oficina_user = (String) session.getAttribute("codigo_oficina_user");
		String usuario   = String.valueOf(session.getAttribute("nombreusuario"));
		
		String tipo = (String) request.getParameter("tipo");
		tipo=(tipo==null)?"":tipo;
		codigo_oficina_user=(codigo_oficina_user==null)?"":codigo_oficina_user;
		
		System.out.println("Codigo Oficina = "+fRe.getCodigo_documento());
		Funciones f=new Funciones();
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		String btn = request.getParameter("btn");
		ReiterativoService service = new ReiterativoService();
		DocumentosInternosService serviceDocumentoInterno = new DocumentosInternosServiceImplement();
		IUsuarioDAO uDao=new SqlUsuarioDAO();
		
		
		
		String  mensaje_usuario= "";
		Collection listaDocsDetalles = null;
		Collection listaDocs = new ArrayList();
		String ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
		session.setAttribute("rs_upload_doc",null);
		
		System.out.println("------> "+fRe.getOperacion());
		
		if(fRe.getOperacion()==null && tipo.equals("regresarbr")){
			fRe.setOperacion("buscar");
			fRe.setCodigo_oficina(codigo_oficina_user);
			session.setAttribute("ls_tipenv", "");
		}
		
		if(fRe.getOperacion()!=null){
			
			/**
			 * JAZANERO
			 */
			Collection rs_oficina = null;
	        Collection rs_oficina_other = null;
			String ls_codigo_institucion = String.valueOf(session.getAttribute("ls_codigo_institucion"));
			String ls_grupo_oficina = String.valueOf(session.getAttribute("grupo_oficina"));
			String es_padre=null;
			
			ls_codigo_institucion=(ls_codigo_institucion==null)?"":ls_codigo_institucion;
			ls_grupo_oficina=(ls_grupo_oficina==null)?"":ls_grupo_oficina;
			

			log.info("-> "+ls_codigo_institucion);
			log.info("-> "+ls_grupo_oficina);
			
			
			ls_codigo_institucion=(ls_codigo_institucion==null)?"":ls_codigo_institucion;
	        if(!ls_codigo_institucion.equals("")){
	        	//rs_oficina= daoIUsuarioDAO.of_lista_oficinas_institucion(ls_codigo_institucion,true);
	        	rs_oficina= daoIUsuarioDAO.of_lista_oficinas_institucion(ls_codigo_institucion,ls_grupo_oficina,true);
	        	rs_oficina_other= daoIUsuarioDAO.of_lista_oficinas_institucion(ls_codigo_institucion,false);
	        	es_padre=daoIUsuarioDAO.of_es_padre(ls_oficina);
    		}
	        
	        
	        
  			/***
  			 * CREADO PARA SABER SU CONFIGURACION PERSONAL DE FIRMA DIGITAL
  			 */
  			BDatosFirmante resultado= new BDatosFirmante();
  			resultado = serviceDocumentoInterno.getDatosFirmante(usuario);
  			
  			int valor=(resultado.getTipo_firma() == 2)? 0:1; //1:tiene firma, 0:notiene firma
			
				if(fRe.getOperacion().equals("registrar") ) {
					log.info("Entry registrar");
					 listaDocs = (Collection)session.getAttribute("listaDocs");
					 if(listaDocs!=null && listaDocs.size()>0){
						 Hashtable tabla = (Hashtable)listaDocs.toArray()[fRe.getIndice()];
						 System.out.println("Indice > "+fRe.getIndice());
						 fReRe.setCodigo_documento((String)tabla.get("codigo_documento"));
						 fReRe.setNumero_documento((String)tabla.get("numero_documento"));						 
						 fReRe.setProcedencia((String)tabla.get("procedencia"));
						 fReRe.setNaturaleza_documento((String)tabla.get("naturaleza_documento"));
						 fReRe.setCodigo_expediente(Integer.parseInt((String)tabla.get("codigo_expediente")));
						 fReRe.setCantidadArchivos(service.ObtieneCantidadArchivos(fReRe.getCodigo_documento()));
						 String fecha = f.convertirFecha(new Date());
						 fReRe.setFecha(fecha);
						 listaDocsDetalles = service.listarSecuencias(fReRe.getCodigo_documento());
						 session.setAttribute("listadoSecuenciasRegistro", listaDocsDetalles);
						 request.setAttribute("FFormReiterativoRegistro", null);
						 session.setAttribute("rs_personas", null);
						 session.setAttribute("rs_oficina", rs_oficina);
						 session.setAttribute("rs_oficina_other",rs_oficina_other);
	                     session.setAttribute("es_padre", es_padre);
						 
						 
						 log.info("Lista Docs es no nulo");
					 }else{
						 log.info("Lista Docs nulo");
					 }
					 request.setAttribute("FFormReiterativoRegistro", fReRe);
					 
					return (mapping.findForward("registro"));
				}else if(fRe.getOperacion().equals("mantener")){
					log.info("Entry mantener");
					listaDocs = (Collection)session.getAttribute("listaDocs");
					 if(listaDocs!=null && listaDocs.size()>0){
						 Hashtable tabla = (Hashtable)listaDocs.toArray()[fRe.getIndice()];
							fReMa.setCodigo_documento((String)tabla.get("codigo_documento"));
							fReMa.setNumero_documento((String)tabla.get("numero_documento"));
							fReMa.setObserva_documento((String)tabla.get("observa_documento"));
							fReMa.setCodigo_expediente(Integer.parseInt((String)tabla.get("codigo_expediente")));
							fReMa.setProcedencia((String)tabla.get("procedencia"));
							
							listaDocsDetalles  = service.listarSecuenciasMantenimiento(fReMa.getCodigo_documento());
							 
					 }				
					 session.setAttribute("FFormReiterativoMantenimiento", fReMa);
					 session.setAttribute("listadoSecuenciasMantenimiento", listaDocsDetalles);
					return (mapping.findForward("mantenimiento"));
				
			}else if(fRe.getOperacion().equals("buscar")){
				log.info("Entry buscar");
				session.setAttribute("listaDocs", null);
				
				String  cuadro_mensaje = (String) session.getAttribute("cuadro_mensaje");
				String  operacionpopup = (String) session.getAttribute("operacionpopup");
				
				cuadro_mensaje = (cuadro_mensaje == null)? "":cuadro_mensaje;
				operacionpopup = (operacionpopup == null)? "":operacionpopup;
				
				if(cuadro_mensaje.equals("BREI") || operacionpopup.equals("Y") ){
				System.out.println("aqui->>"+cuadro_mensaje);
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
				
				
				if( f.esNulo(fRe.getCodigo_oficina()) && f.esNulo(fRe.getCodigo_documento()) && f.esNulo(fRe.getCodigo_expediente())){
					mensaje_usuario = "Debe Seleccionar una Oficina";					
				}else{
					
					//System.out.println("essssssssssssssssssssssssssssssssssssss");
					
					
					mensaje_usuario = service.listarDocs(fRe.getCodigo_oficina(), fRe.getCodigo_expediente(), fRe.getCodigo_documento(),listaDocs,valor);
					fRe.setListaDocs(listaDocs);
					session.setAttribute("listaDocs", fRe.getListaDocs());
				}
				request.setAttribute("mensaje_usuario", mensaje_usuario);
			}

		}
		
		return (mapping.findForward("buscar_documentos"));
	}
}
