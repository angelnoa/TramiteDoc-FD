package tramitedoc.concytec.action;
import java.sql.Connection;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.ValidaSessionAction;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionVerFrameListas extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(AActionVerFrameListas.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		String tipo;
	  	tipo=request.getParameter("tipo");
		System.out.println("El tipo...." + tipo);
	  	HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx  ==> " + cnx);
		String ls_mensaje="";
		
		try {
			
			if(tipo.equals("procedencia")){
		  		
			  	
				String ls_operacion=request.getParameter("operacion");
				String ls_texto=request.getParameter("sle_texto");
				//System.out.println("Entre "+ls_texto);
				//String valorct = String.valueOf(session.getAttribute("valorct"));
				String accion = request.getParameter("accion");
				
				System.out.println("El ls_operacion es texto .."+ ls_operacion);
				System.out.println("El ls_texto es texto .."+ ls_texto);
				System.out.println("El accion CCCCC es texto .."+ accion);
				
				IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
				
		  	/* Si se ingresa en texto y el tipo*/
				
				session.removeAttribute("listaFrameNatural");
				session.removeAttribute("listaFrameJuridica");
				
				if ((ls_texto != null) && (ls_operacion != null)) {
          	
					//ls_texto = "%"+ls_texto+"%";	
					ls_texto = "%"+ls_texto+"%";
              	
								System.out.println("El ls_texto .."+ ls_texto);
					
		  		
								Collection listaFrameBusqueda = daoIUsuarioDAO.of_procedencia_listar(cnx,ls_operacion,ls_texto);
								
								session.setAttribute("listaPersonas",listaFrameBusqueda);
								session.setAttribute("accion_p",accion);
								
								return (mapping.findForward("procedencia"));
								
			
              	
					}
							
				/* Si el texto y el tipo es null*/		
					if (ls_texto == null  && (ls_operacion == null)) { 
						request.setAttribute("ls_nombre", ls_texto);
						request.setAttribute("ls_operacion", ls_operacion);
						Collection listaPersonas =  daoIUsuarioDAO.of_listar_personas(cnx);
		
						System.out.println("El listaPersonas es "+ listaPersonas);
						request.setAttribute("listaPersonas", listaPersonas);
						session.setAttribute("accion_p",accion);
						return (mapping.findForward("procedencia"));
      	
						} 

			
			
			} 
			
			if(tipo.equals("dirigidos")){
		  		
			  	
				String ls_operacion=request.getParameter("operacion");
				String ls_texto=request.getParameter("sle_texto");
				
				//String valorct = String.valueOf(session.getAttribute("valorct"));
				String accion = request.getParameter("accion");
				
				System.out.println("El ls_operacion es texto null.."+ ls_operacion);
				System.out.println("El ls_texto es texto null.."+ ls_texto);
				System.out.println("El accion CCCCC es texto null.."+ accion);
				
				IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
				
		  	/* Si se ingresa en texto y el tipo*/
				
				session.removeAttribute("listaFrameNatural");
				session.removeAttribute("listaFrameJuridica");
				
				if ((ls_texto != null) && (ls_operacion != null)) {
          	
					ls_texto = "%"+ls_texto+"%";	
					//ls_texto = ls_texto+"%";
              	
								System.out.println("El ls_texto .."+ ls_texto);
					
		  		
								Collection listaFrameBusqueda = daoIUsuarioDAO.of_procedencia_listar(cnx,ls_operacion,ls_texto);
								
								session.setAttribute("listaPersonas",listaFrameBusqueda);
								session.setAttribute("accion_p",accion);
								
								return (mapping.findForward("dirigidos"));
								
			
              	
					}
							
				/* Si el texto y el tipo es null*/		
					if (ls_texto == null  && (ls_operacion == null)) { 
						request.setAttribute("ls_nombre", ls_texto);
						request.setAttribute("ls_operacion", ls_operacion);
						Collection listaPersonas =  daoIUsuarioDAO.of_listar_personas(cnx);
		
						System.out.println("El listaPersonas es "+ listaPersonas);
						request.setAttribute("listaPersonas", listaPersonas);
						session.setAttribute("accion_p",accion);
						return (mapping.findForward("dirigidos"));
      	
						} 

			
			
			} 

			
			
			else if(tipo.equals("derivadocumento")){
		  		
				System.out.println("Dentro de la lista derivacion documento");
			
				IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
				
				String ls_correla   = String.valueOf(session.getAttribute("correlativo"));
				
				 int ls_correla_conv=Integer.parseInt(ls_correla.trim());
			        System.out.println(" ls_correla_conv.. "+ ls_correla_conv);
			        
				Collection listaDerivacion =  daoIUsuarioDAO.of_lista_derivacion_documento(cnx,ls_correla_conv);

				System.out.println("El listaDerivacion es "+ listaDerivacion);
				session.removeAttribute("listaDerivacion");
				session.setAttribute("listaDerivacion",listaDerivacion);
				
				return (mapping.findForward("derivadocumento"));
      	
						

			} 
			
			else if(tipo.equals("rangocodigos")){
		  		
				System.out.println("Dentro de la lista rango de codigos");
			
				IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
				
				String ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
				String ls_naturaleza   = request.getParameter("naturaleza");
				String ls_fecha   = request.getParameter("fecha");
				
				System.out.println("El ls_oficina es" + ls_oficina);
				System.out.println("El ls_naturaleza es" + ls_naturaleza);
				System.out.println("El ls_fecha es" + ls_fecha);
				
				Collection listaRangoCodigos =  daoIUsuarioDAO.of_lista_rango_codigos(cnx,ls_oficina,ls_naturaleza,ls_fecha);

				System.out.println("El listaDerivacion es "+ listaRangoCodigos);
				session.removeAttribute("listaRangoCodigos");
				session.setAttribute("rs_rangocodigos",listaRangoCodigos);
				
				return (mapping.findForward("rangocodigos"));
      	
			} 
			
			else if(tipo.equals("reportegenerales")){
		  		
				
				String ls_operacion=request.getParameter("procedencia");
				String ls_texto=request.getParameter("accion");
				
				System.out.println("El ls_operacion es texto null.."+ ls_operacion);
				System.out.println("El ls_texto es texto null.."+ ls_texto);
				
				
				
				session.removeAttribute("rs_listaDocumentos");
				
				System.out.println("Dentro de Reportes Generales");
			
				IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
				
				String ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
				String ls_naturaleza   = request.getParameter("naturaleza");
				String ls_fecha   = request.getParameter("fecha");
				
				String ls_procedencia="";
		        String ls_sle_fecha="";
		        String ls_codigo_oficina="";
		        String ls_codigo_solicitud="";
		       
		        String ls_medio="";
		        String ls_codigo_tipo="";
		        String ls_reporte="";
		        
		        String ls_dirigido="";
		        String ls_firmadopor="";
		       
		        String ls_accion="";
		        String usuario_actual="";
		        String codigo_oficina="";
		        String ls_numero_expediente="";
		        String ls_numero_registro="";
		        String ls_estado="";
		        String ls_numero_documento="";
				String ls_doc_resp="";
				String ls_asunto_documento="";
				String ls_codigo_motivo="";
				String ls_sle_fecha_inicio="";
				String ls_sle_fecha_fin="";
				
				ls_procedencia=request.getParameter("procedencia");
		         ls_sle_fecha=request.getParameter("sle_fecha");
		         ls_codigo_oficina=request.getParameter("codigo_oficina");
		         ls_codigo_solicitud=request.getParameter("codigo_solicitud");
		         ls_numero_expediente=request.getParameter("numero_expediente");
		         ls_medio=request.getParameter("medio");
		         ls_codigo_tipo=request.getParameter("codigo_tipo");
		         ls_numero_registro=request.getParameter("numero_registro");
		         
		         ls_dirigido=request.getParameter("dirigido");
		         ls_estado=request.getParameter("estado");
		         ls_firmadopor=request.getParameter("firmadopor");
		         
				System.out.println("El ls_oficina es" + ls_oficina);
				System.out.println("El ls_naturaleza es" + ls_naturaleza);
				System.out.println("El ls_fecha es" + ls_fecha);
				
				Collection listaFrameBusqueda = daoIUsuarioDAO.of_busca_documentos_report_general(cnx, ls_oficina, ls_procedencia,
						ls_sle_fecha, ls_medio, ls_codigo_tipo, ls_codigo_oficina, ls_codigo_solicitud, ls_numero_registro,
						ls_numero_expediente,ls_firmadopor,ls_dirigido,ls_estado, ls_numero_documento, ls_doc_resp, 
						ls_asunto_documento, ls_sle_fecha_inicio, ls_sle_fecha_fin, ls_codigo_motivo);
				
				
				//Collection listaDocumento =  daoIUsuarioDAO.of_busca_documentos_report_general(cnx,ls_oficina);

				//System.out.println("El listaDocumento es "+ listaDocumento);
				
				session.setAttribute("rs_listaDocumentos",listaFrameBusqueda);
				//session.setAttribute("selectoficina",ls_oficina);
				
				return (mapping.findForward("reportegenerales"));
      	
			} 
			
			else if(tipo.equals("reportehistorial")){
		  		
				session.removeAttribute("rs_listaHistorial");
				session.removeAttribute("rs_listaDocumentos");
				
				System.out.println("Dentro de Reportes Historial");
				String ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
				IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
				
				String ls_codigo_doc = request.getParameter("codigo_documento");
				
				System.out.println("El ls_codigo_doc historial es" + ls_codigo_doc);
				
				Collection listaDocumento =  daoIUsuarioDAO.of_lista_frame_historial(cnx,ls_codigo_doc,ls_oficina,"");

				System.out.println("El listaDocumento es "+ listaDocumento);
				
				session.setAttribute("rs_listaHistorial",listaDocumento);
				
				return (mapping.findForward("reportehistorial"));
      	
			} 
			else if(tipo.equals("busquedalinea")){
		  		log.info("Entry busquedalinea ...");
				session.removeAttribute("mensaje");
				
				
			
				IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
				
				String anio = request.getParameter("anio");
				String num_exp = request.getParameter("num_exp");
				String password = request.getParameter("password");
				
				System.out.println("El anio es" + anio);
				System.out.println("El num_exp es" + num_exp);
				System.out.println("El password es" + password);
				
				Funciones funciones=new Funciones();
				
				if(anio==null && num_exp==null && password==null){
					log.info("anio=null,num_exp=null, password=null");
					return (mapping.findForward("blank"));
				}
				//else{
					
						//String ls_validar_clave=funciones.of_validar_clave(cnx,password);
						String ls_validar_clave=funciones.of_validar_clave_expediente(cnx,password);
						System.out.println("El ls_validar_clave es.." + ls_validar_clave);
						
						if(ls_validar_clave.equals("0")){
							log.info("clave= 0");
							ls_mensaje="LOS DATOS INGRESADOS NO SON CORRECTOS, POR FAVOR INGRESAR NUEVAMENTE ESTOS VALORES..!";	
							session.setAttribute("mensaje",ls_mensaje);
							
							return (mapping.findForward("blank"));
							
						}else{
							
							Collection listaDocumento =  daoIUsuarioDAO.of_lista_busqueda_linea(cnx,anio,num_exp);
		
							System.out.println("El listaDocumento es "+ listaDocumento);
							
							session.setAttribute("rs_listaDocumentos",listaDocumento);
							
							return (mapping.findForward("busquedalinea"));
							
						}
					
					//}
			} 
			else if(tipo.equals("reportehistoriallinea")){
		  		
				session.removeAttribute("rs_listaHistorial");
				session.removeAttribute("rs_listaDocumentos");
				
				System.out.println("Dentro de Reportes Historial");
				String ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
				IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
				
				String ls_codigo_doc = request.getParameter("codigo_documento");
				
				System.out.println("El ls_codigo_doc historial es" + ls_codigo_doc);
				
				Collection listaDocumento =  daoIUsuarioDAO.of_lista_frame_historial(cnx,ls_codigo_doc,ls_oficina,"");

				System.out.println("El listaDocumento es "+ listaDocumento);
				
				session.setAttribute("rs_listaHistorial",listaDocumento);
				
				return (mapping.findForward("reportehistoriallinea"));
      	
			} 
			
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{  
        	log.info("Entry al finally ...");
        	closeConnection(cnx);	
        }
       	return (mapping.findForward("error"));
	}
}
