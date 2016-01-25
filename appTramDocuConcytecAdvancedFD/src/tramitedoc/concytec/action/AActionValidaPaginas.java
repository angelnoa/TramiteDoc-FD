package tramitedoc.concytec.action;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BMesaPartes;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.dao.interfaces.IAdministradorDAO;
import tramitedoc.concytec.dao.interfaces.IReportesDAO;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlAdministradorDAO;
import tramitedoc.concytec.dao.sql.SqlReportesDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.ObtieneConexion;
import tramitedoc.concytec.util.ValidaSessionAction;

/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Valida Paginas  
 */

public class AActionValidaPaginas extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(AActionValidaPaginas.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Connection cnx =null;
		
		try {
			
		  	String ls_operacion="";
		  	String tipo = request.getParameter("tipo");
		  	log.info("El tipo " + tipo);
		  	
		  	String ls_mensaje="";
		  	String ls_codigo_recepcion="";
		  	String ls_accion="";
		  	HttpSession session = request.getSession(true);
		  	
			cnx = getConnection(request, "principal");
			log.info("El cnx  ==> " + cnx);
			IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO(); 
			String ls_codigooficina= (String) session.getAttribute("codigo_oficina");
			IReportesDAO daoIReportesDAO = new SqlReportesDAO();
						
			Collection listado = null;
			
			if (tipo.equals("seleccion")){
				
				log.info("Dentro de SELECCION........ ");
				session.removeAttribute("operacionpopup");
				session.removeAttribute("mensaje_reg");
				session.removeAttribute("mensaje_exp");
				session.removeAttribute("contador");
				
				String operacion=request.getParameter("operacion");
				String codigo=request.getParameter("codigo_persona");
				
				String nombre_persona=request.getParameter("nombre_persona");
				String tipopersona=request.getParameter("tipodoc");
				//String valorct=request.getParameter("valorct");
				String accion_p=request.getParameter("accion_p");
				
				String tipopersonadesc="";

				if ((codigo != null) && (operacion != null)) {
					codigo=codigo.trim();
					nombre_persona=nombre_persona.trim();
					tipopersona=tipopersona.trim();
					accion_p=accion_p.trim();
					
					log.info("El operacion " + operacion);
					log.info("El codigo" + codigo);
					log.info("El tipopersona " + tipopersona);
					log.info("El nombre_persona " + nombre_persona);
					log.info("El accion_p " + accion_p);
					
					log.info("La operacion es T y el codigo es diferente de null");
					
					
						if(tipopersona.equals("N")){
						
							tipopersonadesc="NATURAL";
							log.info("El tipopersona Natural " + tipopersonadesc);
							
						}else{
							
							tipopersonadesc="JURIDICA";
							log.info("El tipopersona Juridica " + tipopersonadesc);
							
						}
						
						if(accion_p.equals("T")){
							
							log.info("Si el accion_p es...T " + accion_p);
							session.setAttribute("operacion","CT");		
							session.setAttribute("codigo",codigo);
							session.setAttribute("descripcion_persona",nombre_persona);
							session.setAttribute("tipopersona",tipopersonadesc);
							session.setAttribute("accion_p",accion_p);
							
						
						return (mapping.findForward("reporte"));
						
						}else if(accion_p.equals("R")){
							
							log.info("Si el accion_p es.. igual a R "+ accion_p);
							
							session.setAttribute("operacion",operacion);		
							session.setAttribute("codigo",codigo);
							session.setAttribute("descripcion_persona",nombre_persona);
							session.setAttribute("tipopersona",tipopersonadesc);
							session.setAttribute("accion_p",accion_p);
						
						return (mapping.findForward("entrada"));
						
						}
						else if(accion_p.equals("RD")){
							
							log.info("Si el accion_p es.. igual a R "+ accion_p);
							log.info("La OPERACION..CCCCC "+ operacion);
							
							//String codigo_persona_dir=request.getParameter("codigo_persona_dir");
							String nombre_persona_dir=request.getParameter("nombre_persona_dir");
							//String tipodoc_dir=request.getParameter("tipodoc_dir");
							//String tipopersona_dir=request.getParameter("tipodoc_dir");
							
							session.setAttribute("operacion_dir",operacion);		
							//session.setAttribute("codigo",codigo);
							session.setAttribute("descripcion_persona",nombre_persona_dir);
							//session.setAttribute("tipopersona",tipopersonadesc);
							session.setAttribute("accion_p",accion_p);
						
						return (mapping.findForward("entrada"));
						
						}
						else if(accion_p.equals("RM")){
							
							log.info("Si el accion_p es.. igual a R "+ accion_p);
							log.info("La operacion es.."+ operacion);
							
							session.setAttribute("operacion","M");
							session.setAttribute("operacionper",operacion);
							session.setAttribute("codigo",codigo);
							session.setAttribute("descripcion_persona",nombre_persona);
							session.setAttribute("tipopersona",tipopersonadesc);
							session.setAttribute("accion_p",accion_p);
						
						return (mapping.findForward("modificardoc"));
						
						}
					
				}
				
				if((codigo==null) && (operacion==null)){
					
					
	            		session.removeAttribute("operacion");
						session.removeAttribute("codigo");
						session.removeAttribute("descripcion_persona");
						session.removeAttribute("tipopersona");
						session.removeAttribute("medio");
						session.removeAttribute("codigo_tipo");
						session.removeAttribute("numero_documento");
						
						log.info("Los valores T y Codigo son nulos");
						return (mapping.findForward("seleccion"));	
						
				}
				
				//if((codigocontador!=null) && (operacion.equals("MA"))){
				if(operacion.equals("T")){
					
					//session.removeAttribute("contador");
					log.info("Si el codigo contador es diferente de null y operacion es MA..");
					
					String codigocontador=request.getParameter("codigocontador");
					log.info("El codigocontador " + codigocontador);
					
					String medio   =   String.valueOf(session.getAttribute("medio"));
					String medio_rq   =   String.valueOf(session.getAttribute("medio_rq"));
					String codigo_oficina_rq   =   String.valueOf(session.getAttribute("codigo_oficina_rq"));
					String codigo_tipo   =   String.valueOf(session.getAttribute("codigo_tipo"));
					String destinatario   =   String.valueOf(session.getAttribute("destinatario"));
					String destino_documento   =   String.valueOf(session.getAttribute("destino_documento"));
					
					Collection rs_destinatario = daoIUsuarioDAO.of_lista_personas_oficinas(String.valueOf(codigo_oficina_rq));
					
					String codigo_solicitud   =   String.valueOf(session.getAttribute("codigo_solicitud"));
					String numero_documento   =   String.valueOf(session.getAttribute("numero_documento"));
					String fecha_registro   =   String.valueOf(session.getAttribute("fecha_registro"));
					String hora   =   String.valueOf(session.getAttribute("hora"));
					String folios_documento   =   String.valueOf(session.getAttribute("folios_documento"));
					String asunto_documento   =   String.valueOf(session.getAttribute("asunto_documento"));
					String observa_documento   =   String.valueOf(session.getAttribute("observa_documento"));
					String firmadopor   =   String.valueOf(session.getAttribute("firmadopor"));
					String origen_documento   =   String.valueOf(session.getAttribute("origen_documento"));
					String codigo_oficina   =   String.valueOf(session.getAttribute("codigo_oficina"));
					String desc_origen   =   String.valueOf(session.getAttribute("desc_origen"));
					String tipo_persona   =   String.valueOf(session.getAttribute("tipo_persona"));
					String cbo_fecharpta   =   String.valueOf(session.getAttribute("cbo_fecharpta"));
					String fecha_rpta   =   String.valueOf(session.getAttribute("fecha_rpta"));
					String numero_referencia   =   String.valueOf(session.getAttribute("numero_referencia"));
					String naturaleza_documento   =   String.valueOf(session.getAttribute("naturaleza_documento"));
					tipo   =   String.valueOf(session.getAttribute("tipo"));
					String operacionper   =   String.valueOf(session.getAttribute("operacionper"));
					String dirigido   =   String.valueOf(session.getAttribute("dirigido"));
					
					 	/*log.info("medio... "+ medio);
						log.info("codigo_tipo... "+ codigo_tipo);
						log.info("codigo_oficina... "+ codigo_oficina);
						log.info("destino_documento... "+ destino_documento);
						log.info("destinatario... "+ destinatario);
						log.info("codigo_solicitud... "+ codigo_solicitud);
						log.info("numero_documento... "+ numero_documento);
						log.info("fecha_registro... "+ fecha_registro);
						log.info("hora... "+ hora);
						log.info("folios_documento... "+ folios_documento);
						log.info("asunto_documento... "+ asunto_documento);
						log.info("observa_documento... "+ observa_documento);
						log.info("firmadopor... "+ firmadopor);
						log.info("origen_documento... "+ origen_documento);
						log.info("desc_origen... "+ desc_origen);
						log.info("tipo_persona... "+ tipo_persona);
						
						log.info("cbo_fecharpta... "+ cbo_fecharpta);
						log.info("fecha_rpta... "+ fecha_rpta);
						log.info("numero_referencia... "+ numero_referencia);
						log.info("naturaleza_documento... "+ naturaleza_documento);
						log.info("dirigido... "+ dirigido);
						log.info("tipo... "+ tipo);
						log.info("operacionper... "+ operacionper);
						*/
						session.setAttribute("medio", medio);
						session.setAttribute("medio_rq", medio_rq);
						session.setAttribute("codigo_oficina_rq", codigo_oficina_rq);
						session.setAttribute("codigo_tipo", codigo_tipo);
						session.setAttribute("destinatario", destinatario);
						session.setAttribute("codigo_solicitud", codigo_solicitud);
						session.setAttribute("numero_documento", numero_documento.trim());
						session.setAttribute("fecha_registro", fecha_registro);
						session.setAttribute("hora", hora);
						session.setAttribute("folios_documento", folios_documento);
						session.setAttribute("asunto_documento", asunto_documento.trim());
						session.setAttribute("observa_documento", observa_documento.trim());
						session.setAttribute("firmadopor", firmadopor.trim());
						
						session.setAttribute("codigo_oficina", codigo_oficina);
						session.setAttribute("descripcion_persona", desc_origen.trim());
						session.setAttribute("tipo_persona", tipo_persona);
						session.setAttribute("cbo_fecharpta", cbo_fecharpta);
						session.setAttribute("fecha_rpta", fecha_rpta);
						session.setAttribute("numero_referencia", numero_referencia);
						
						//session.setAttribute("operacion", "T");
						session.setAttribute("tipopersona", naturaleza_documento);
						session.setAttribute("origen_documento", origen_documento);
						session.setAttribute("tipo", tipo);
						session.setAttribute("codigocontador", codigocontador);
						session.setAttribute("descripcion_persona_dir", dirigido);
						session.setAttribute("rs_destinatario", rs_destinatario);
						//session.setAttribute("operacionper", operacionper);
						
						session.setAttribute("operacion",operacion);
					
					
					
					return (mapping.findForward("entrada"));	
					
				}

				
				if(operacion.equals("D") ){
					
					//session.removeAttribute("contador");
					log.info("Si La operacion es D y la accion es RM..");
					
					String codigocontador=request.getParameter("codigocontador");
					log.info("El codigocontador " + codigocontador);
					
					String medio   =   String.valueOf(session.getAttribute("medio"));
					String codigo_tipo   =   String.valueOf(session.getAttribute("codigo_tipo"));
					String destinatario   =   String.valueOf(session.getAttribute("destinatario"));
					String destino_documento   =   String.valueOf(session.getAttribute("destino_documento"));
					
					String codigo_solicitud   =   String.valueOf(session.getAttribute("codigo_solicitud"));
					String numero_documento   =   String.valueOf(session.getAttribute("numero_documento"));
					String fecha_registro   =   String.valueOf(session.getAttribute("fecha_registro"));
					String hora   =   String.valueOf(session.getAttribute("hora"));
					String folios_documento   =   String.valueOf(session.getAttribute("folios_documento"));
					String asunto_documento   =   String.valueOf(session.getAttribute("asunto_documento"));
					String observa_documento   =   String.valueOf(session.getAttribute("observa_documento"));
					String firmadopor   =   String.valueOf(session.getAttribute("firmadopor"));
					String origen_documento   =   String.valueOf(session.getAttribute("origen_documento"));
					String codigo_oficina   =   String.valueOf(session.getAttribute("codigo_oficina"));
					String desc_origen   =   String.valueOf(session.getAttribute("desc_origen"));
					String tipo_persona   =   String.valueOf(session.getAttribute("tipo_persona"));
					String cbo_fecharpta   =   String.valueOf(session.getAttribute("cbo_fecharpta"));
					String fecha_rpta   =   String.valueOf(session.getAttribute("fecha_rpta"));
					String numero_referencia   =   String.valueOf(session.getAttribute("numero_referencia"));
					String naturaleza_documento   =   String.valueOf(session.getAttribute("naturaleza_documento"));
					 		tipo   =   String.valueOf(session.getAttribute("tipo"));
					String operacionper   =   String.valueOf(session.getAttribute("operacionper"));
					
					 log.info("medio... "+ medio);
						log.info("codigo_tipo... "+ codigo_tipo);
						log.info("codigo_oficina... "+ codigo_oficina);
						log.info("destino_documento... "+ destino_documento);
						log.info("destinatario... "+ destinatario);
						log.info("codigo_solicitud... "+ codigo_solicitud);
						log.info("numero_documento... "+ numero_documento);
						log.info("fecha_registro... "+ fecha_registro);
						log.info("hora... "+ hora);
						log.info("folios_documento... "+ folios_documento);
						log.info("asunto_documento... "+ asunto_documento);
						log.info("observa_documento... "+ observa_documento);
						log.info("firmadopor... "+ firmadopor);
						log.info("origen_documento... "+ origen_documento);
						log.info("desc_origen... "+ desc_origen);
						log.info("tipo_persona... "+ tipo_persona);
						
						log.info("cbo_fecharpta... "+ cbo_fecharpta);
						log.info("fecha_rpta... "+ fecha_rpta);
						log.info("numero_referencia... "+ numero_referencia);
						log.info("naturaleza_documento... "+ naturaleza_documento);
						log.info("tipo... "+ tipo);
						log.info("operacionper... "+ operacionper);
						
						session.setAttribute("medio ", medio);
						session.setAttribute("codigo_tipo", codigo_tipo);
						session.setAttribute("destinatario", destinatario);
						session.setAttribute("codigo_solicitud", codigo_solicitud);
						session.setAttribute("numero_documento", numero_documento.trim());
						session.setAttribute("fecha_registro", fecha_registro);
						session.setAttribute("hora", hora);
						session.setAttribute("folios_documento", folios_documento);
						session.setAttribute("asunto_documento", asunto_documento.trim());
						session.setAttribute("observa_documento", observa_documento.trim());
						session.setAttribute("firmadopor", firmadopor.trim());
						
						session.setAttribute("codigo_oficina", codigo_oficina);
						session.setAttribute("descripcion_persona", desc_origen.trim());
						session.setAttribute("tipo_persona", tipo_persona);
						session.setAttribute("cbo_fecharpta", cbo_fecharpta);
						session.setAttribute("fecha_rpta", fecha_rpta);
						session.setAttribute("numero_referencia", numero_referencia);
						
						//session.setAttribute("operacion", "T");
						session.setAttribute("tipopersona", naturaleza_documento);
						session.setAttribute("origen_documento", origen_documento);
						session.setAttribute("tipo", tipo);
						session.setAttribute("codigocontador", codigocontador);
						//session.setAttribute("operacionper", operacionper);
						
					if(codigocontador!=null && !codigocontador.equals("")){
						session.setAttribute("operacion_dir",operacion);
						session.setAttribute("accion_p",accion_p);
					}else{
					
						session.removeAttribute("operacion_dir");
						session.removeAttribute("accion_p");
					}
					
					
					return (mapping.findForward("entrada"));	
					
				}
				
			}else if (tipo.equals("seleccion_dir")){
				
				log.info("Dentro de SELECCION_DIRRR........ ");
				session.removeAttribute("operacionpopup");
				session.removeAttribute("mensaje_reg");
				session.removeAttribute("mensaje_exp");
				session.removeAttribute("contador");
				
				String operacion=request.getParameter("operacion");
				
				String codigo=request.getParameter("codigo_persona_dir");
				String nombre_persona_dir=request.getParameter("nombre_persona_dir");
				String tipopersona_dir=request.getParameter("tipodoc_dir");
				//String valorct=request.getParameter("valorct");
				String accion_p=request.getParameter("accion_p");
				
				codigo=codigo.trim();
				nombre_persona_dir=nombre_persona_dir.trim();
				tipopersona_dir=tipopersona_dir.trim();
				accion_p=accion_p.trim();
				
				String tipopersonadesc="";
				
				
				log.info("El operacion " + operacion);
				log.info("El codigo" + codigo);
				log.info("El tipopersona_dir " + tipopersona_dir);
				log.info("El nombre_persona_dir " + nombre_persona_dir);
				log.info("El accion_p " + accion_p);
				
				if ((codigo != null) && (operacion != null)) {
					
					log.info("La operacion es T y el codigo es diferente de null");
					
					
						if(tipopersona_dir.equals("N")){
						
							tipopersonadesc="NATURAL";
							log.info("El tipopersona Natural " + tipopersonadesc);
							
						}else{
							
							tipopersonadesc="JURIDICA";
							log.info("El tipopersona Juridica " + tipopersonadesc);
							
						}
						
						if(accion_p.equals("RD")){
							
							log.info("Si el accion_p es.. igual a R "+ accion_p);
							log.info("La OPERACION..CCCCC "+ operacion);
							
							//String codigo_persona_dir=request.getParameter("codigo_persona_dir");
							//String nombre_persona_dir=request.getParameter("nombre_persona_dir");
							//String tipodoc_dir=request.getParameter("tipodoc_dir");
							//String tipopersona_dir=request.getParameter("tipodoc_dir");
							
							session.setAttribute("operacion_dir",operacion);		
							//session.setAttribute("codigo",codigo);
							session.setAttribute("descripcion_persona_dir",nombre_persona_dir);
							//session.setAttribute("tipopersona",tipopersonadesc);
							session.setAttribute("accion_p",accion_p);
						
						return (mapping.findForward("entrada"));
						
						}
						
						if(accion_p.equals("DI")){
							
							log.info("Si el accion_p es.. igual a R "+ accion_p);
							log.info("La OPERACION..CCCCC "+ operacion);
							
							//String codigo_persona_dir=request.getParameter("codigo_persona_dir");
							//String nombre_persona_dir=request.getParameter("nombre_persona_dir");
							//String tipodoc_dir=request.getParameter("tipodoc_dir");
							//String tipopersona_dir=request.getParameter("tipodoc_dir");
							
							session.setAttribute("operacion_dir",operacion);		
							//session.setAttribute("codigo",codigo);
							session.setAttribute("dirigido_a",nombre_persona_dir);
							//session.setAttribute("tipopersona",tipopersonadesc);
							session.setAttribute("accion_p",accion_p);
						
						return (mapping.findForward("entrada_doc_interno"));
						
						}
						
					
				}
				
				if((codigo==null) && (operacion==null)){
					
					
	            		session.removeAttribute("operacion");
						session.removeAttribute("codigo");
						session.removeAttribute("descripcion_persona");
						session.removeAttribute("tipopersona");
						session.removeAttribute("medio");
						session.removeAttribute("codigo_tipo");
						session.removeAttribute("numero_documento");
						
						log.info("Los valores T y Codigo son nulos");
						return (mapping.findForward("seleccion"));	
						
				}

				
				
	            
		  		//return (mapping.findForward("entrada"));
			} 
			
			
			
			else if (tipo.equals("instcontactos")){
				String rb_seleccion = request.getParameter("rb_seleccion");
				session.setAttribute("rb_seleccion",rb_seleccion);
				log.info("Remove");
				log.info("Dentro de instcontactos...ÑÑÑ");
				session.removeAttribute("listaFrameNatural");
				session.removeAttribute("listaFrameJuridica");
				session.removeAttribute("medio");
				session.removeAttribute("codigo_tipo");
				session.removeAttribute("numero_documento");
				session.removeAttribute("codigocontador");
				session.removeAttribute("firmadopor");
				session.removeAttribute("fecha_registro");
				session.removeAttribute("dirigido");
				session.removeAttribute("hora");
				session.removeAttribute("folios_documento");
				session.removeAttribute("asunto_documento");
				session.removeAttribute("codigo_solicitud");
				session.removeAttribute("observa_documento");
				session.removeAttribute("cbo_fecharpta");
				session.removeAttribute("fecha_rpta");
				session.removeAttribute("numero_referencia");
				session.removeAttribute("personas");
				
				log.info("Despues de los RemoveAttribute");
				//session.removeAttribute("accion");
				String ls_medio="";
				String ls_codigo_tipo="";
				String ls_numero_documento="";
				
				String ls_firmadopor="";
				String ls_fecha_registro="";
				String ls_dirigido="";
				String ls_hora="";
				String ls_folios_documento="";
				String ls_asunto_documento="";
				String ls_codigo_solicitud="";
				String ls_observa_documento="";
				String ls_cbo_fecharpta="";
				String ls_fecha_rpta="";
				String ls_numero_referencia="";
				String ls_dirigido_a="";
				
				String codigo_tipo="";
				String firmadopor="";
				String valorct="";
				String ls_personas="";
				String correo="";
				String dominio="";
				//String ls_accion="";
				log.info("Despues de los String...!!");
				//String rb_seleccion = request.getParameter("rb_seleccion");
				ls_medio = request.getParameter("medio");
				ls_codigo_tipo = request.getParameter("codigo_tipo");
				ls_numero_documento = request.getParameter("numero_documento");
				log.info("Despues de los 3 primeros...!!");
				ls_codigo_tipo = request.getParameter("codigo_tipo");
				ls_firmadopor = request.getParameter("firmadopor");
				ls_fecha_registro = request.getParameter("fecha_registro");
				log.info("Despues de los 6 primeros...!!");
				ls_dirigido = request.getParameter("dirigido");
				ls_hora = request.getParameter("hora");
				ls_folios_documento = request.getParameter("folios_documento");
				log.info("Despues de los 9 primeros...!!");
				ls_asunto_documento = request.getParameter("asunto_documento");
				ls_codigo_solicitud = request.getParameter("codigo_solicitud");
				ls_observa_documento = request.getParameter("observa_documento");
				log.info("Despues de los 12 primeros...!!");
				ls_cbo_fecharpta = request.getParameter("cbo_fecharpta");
				ls_fecha_rpta = request.getParameter("fecha_rpta");
				ls_numero_referencia = request.getParameter("numero_referencia");
				ls_dirigido_a= request.getParameter("dirigido_a");
				
				log.info("Despues de los 15 primeros...!!");
				ls_accion = request.getParameter("accion");
				ls_personas = request.getParameter("personas");
				correo = request.getParameter("correo");
				dominio = request.getParameter("dominio");
				log.info("Despues de la ls_personas...!!" + ls_personas);
				
				log.info("correo...!!" + correo);
				log.info("dominio...!!" + dominio);
				//valorct = request.getParameter("valorct");
				
				log.info("El ls_medio" + ls_medio);
				log.info("El ls_codigo_tipo" + ls_codigo_tipo);
				log.info("El ls_numero_documento" + ls_numero_documento);
				log.info("El ls_personas" + ls_personas);
				
				/*log.info("El codigo_tipo" + codigo_tipo);
				log.info("El firmadopor" + firmadopor);
				log.info("El valorct" + valorct);
				*/
				
				session.setAttribute("medio", ls_medio);
	            session.setAttribute("codigo_tipo", ls_codigo_tipo);
	            session.setAttribute("numero_documento",ls_numero_documento);
	            session.setAttribute("codigo_tipo",ls_codigo_tipo);
	            session.setAttribute("firmadopor",ls_firmadopor);
	            session.setAttribute("fecha_registro",ls_fecha_registro);
	            
	            
	            session.setAttribute("dirigido",ls_dirigido);
	            log.info("campo------------->"+ls_dirigido);
	            session.setAttribute("ls_dirigido_a", ls_dirigido_a);
	            log.info("memoria------------->"+ls_dirigido_a);
	            
	            session.setAttribute("hora",ls_hora);
	            session.setAttribute("folios_documento",ls_folios_documento);
	            session.setAttribute("asunto_documento",ls_asunto_documento);
	            session.setAttribute("codigo_solicitud",ls_codigo_solicitud);
	            session.setAttribute("observa_documento",ls_observa_documento);
	            session.setAttribute("cbo_fecharpta",ls_cbo_fecharpta);
	            session.setAttribute("fecha_rpta",ls_fecha_rpta);
	            session.setAttribute("numero_referencia",ls_numero_referencia);
	            session.setAttribute("destinatario",ls_personas);
	            request.setAttribute("accion",ls_accion);
	            request.setAttribute("tipo_default", "J");
	            
	            request.setAttribute("correo",correo);
	            request.setAttribute("dominio", dominio);
	            
	            
	   //         session.setAttribute("rb_seleccion",rb_seleccion);
	            //session.setAttribute("valorct",valorct);
	            
		  		return (mapping.findForward("instcontactos"));
			} 
			else if (tipo.equals("dirigidos")){
				String rb_seleccion = request.getParameter("rb_seleccion");
				session.setAttribute("rb_seleccion",rb_seleccion);
				log.info("Dentro de Dirigidos...ÑÑÑ");
				log.info("Remove");
				session.removeAttribute("listaFrameNatural");
				session.removeAttribute("listaFrameJuridica");
				session.removeAttribute("medio");
				session.removeAttribute("codigo_tipo");
				session.removeAttribute("numero_documento");
				session.removeAttribute("codigocontador");
				session.removeAttribute("firmadopor");
				session.removeAttribute("fecha_registro");
				session.removeAttribute("dirigido");
				session.removeAttribute("hora");
				session.removeAttribute("folios_documento");
				session.removeAttribute("asunto_documento");
				session.removeAttribute("codigo_solicitud");
				session.removeAttribute("observa_documento");
				session.removeAttribute("cbo_fecharpta");
				session.removeAttribute("fecha_rpta");
				session.removeAttribute("numero_referencia");
			
				String ls_medio="";
				String ls_codigo_tipo="";
				String ls_numero_documento="";
				
				String ls_firmadopor="";
				String ls_fecha_registro="";
				String ls_dirigido="";
				String ls_hora="";
				String ls_folios_documento="";
				String ls_asunto_documento="";
				String ls_codigo_solicitud="";
				String ls_observa_documento="";
				String ls_cbo_fecharpta="";
				String ls_fecha_rpta="";
				String ls_numero_referencia="";
				
				//String codigo_tipo="";
				//String firmadopor="";
				//String valorct="";
				String ls_codigo_persona="";
				String ls_codigo_oficina_destino="";
				
				//String ls_accion="";
				
				ls_medio = request.getParameter("medio");
				ls_codigo_tipo = request.getParameter("codigo_tipo");
				ls_numero_documento = request.getParameter("numero_documento");
				
				ls_codigo_tipo = request.getParameter("codigo_tipo");
				ls_firmadopor = request.getParameter("firmadopor");
				
				ls_fecha_registro = request.getParameter("fecha_registro");
				ls_dirigido = request.getParameter("dirigido");
				ls_hora = request.getParameter("hora");
				ls_folios_documento = request.getParameter("folios_documento");
				ls_asunto_documento = request.getParameter("asunto_documento");
				ls_codigo_solicitud = request.getParameter("codigo_solicitud");
				ls_observa_documento = request.getParameter("observa_documento");
				ls_cbo_fecharpta = request.getParameter("cbo_fecharpta");
				ls_fecha_rpta = request.getParameter("fecha_rpta");
				ls_numero_referencia = request.getParameter("numero_referencia");
				ls_accion = request.getParameter("accion");
				ls_codigo_persona=request.getParameter("codper");
				ls_codigo_oficina_destino= request.getParameter("codes");
				
				//String chk_copias[]=request.getParameterValues("selectedItems");

					/*for(int i=0;chk_copias!=null && i<chk_copias.length;i++){
						log.info("enviando oficina:"+chk_copias[i]);
					}*/
				
				log.info("El ls_medio" + ls_medio);
				log.info("El ls_codigo_tipo" + ls_codigo_tipo);
				log.info("El ls_numero_documento" + ls_numero_documento);
				log.info("El ls_accion.." + ls_accion);
				/*log.info("El codigo_tipo" + codigo_tipo);
				log.info("El firmadopor" + firmadopor);
				log.info("El valorct" + valorct);
				*/
				session.setAttribute("medio", ls_medio);
	            session.setAttribute("codigo_tipo", ls_codigo_tipo);
	            session.setAttribute("numero_documento",ls_numero_documento);
	            session.setAttribute("codigo_tipo",ls_codigo_tipo);
	            session.setAttribute("firmadopor",ls_firmadopor);
	            session.setAttribute("fecha_registro",ls_fecha_registro);
	            session.setAttribute("dirigido",ls_dirigido);
	            session.setAttribute("hora",ls_hora);
	            session.setAttribute("folios_documento",ls_folios_documento);
	            session.setAttribute("asunto_documento",ls_asunto_documento);
	            session.setAttribute("codigo_solicitud",ls_codigo_solicitud);
	            session.setAttribute("observa_documento",ls_observa_documento);
	            session.setAttribute("cbo_fecharpta",ls_cbo_fecharpta);
	            session.setAttribute("fecha_rpta",ls_fecha_rpta);
	            session.setAttribute("numero_referencia",ls_numero_referencia);
	            request.setAttribute("accion",ls_accion);
	            
	            session.setAttribute("codigo_oficina_rq", ls_codigo_oficina_destino);
	            session.setAttribute("destinatario", ls_codigo_persona);
	            
	            Collection rs_destinatario = new ArrayList();
	            rs_destinatario = daoIUsuarioDAO.of_lista_personas_oficinas(ls_codigo_oficina_destino);
	            session.setAttribute("rs_destinatario", rs_destinatario);
	            
	            request.setAttribute("tipo_default", "N");
	            
		  		return (mapping.findForward("dirigidos"));
			}
			else if (tipo.equals("nuevaprocedencia")){
				
				log.info("Pagina registro nueva procedencia");
				
				 ls_operacion=request.getParameter("operacion");
				 ls_accion=request.getParameter("accion");
				
				 log.info("La operacion es:" + ls_operacion);
				 
				 session.setAttribute("operacion",ls_operacion);
				 session.setAttribute("accion",ls_accion);
		  		return (mapping.findForward("nuevaprocedencia"));
			} 
			else if (tipo.equals("mismodocumento")){
				
				//IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
				
				int li_retorno = daoIUsuarioDAO.parametro(cnx);
				String ls_codigo_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
				log.info("El ls_codigo_oficina es:" + ls_codigo_oficina);
				if(ls_codigo_oficina.equals("1")){
					
					session.setAttribute("codigo_oficina",ls_codigo_oficina);
				}
				
				log.info("El li_retorno es:" + li_retorno);
				session.setAttribute("correlativo",String.valueOf(li_retorno));
				
				return (mapping.findForward("mismodocumento"));
				
			} 
			else if (tipo.equals("selecnatuderiva")){
				
				
		  		return (mapping.findForward("selecnatuderiva"));
			} 
			
			else if (tipo.equals("selecreporte")){
				
				session.removeAttribute("listaoficinas");
				session.removeAttribute("listausuarios");
				session.removeAttribute("listatipodoc");
				
				
				
		  		return (mapping.findForward("selecreporte"));
			} 
			
			else if (tipo.equals("rangocodigos")){
				
				
		  		return (mapping.findForward("rangocodigos"));
			} 
			else if (tipo.equals("regresar")){

				
				session.removeAttribute("operacionpopup");
				session.removeAttribute("mensaje_reg");
				session.removeAttribute("mensaje_exp");
				
				String ls_pagina = request.getParameter("pagina");
				
				if(ls_pagina.equals("docentrada")){
					
					String ls_medio="";
					String ls_codigo_tipo="";
					String ls_numero_documento="";
					
					ls_medio   =   String.valueOf(session.getAttribute("medio"));
					ls_codigo_tipo   =   String.valueOf(session.getAttribute("codigo_tipo"));
					ls_numero_documento   =   String.valueOf(session.getAttribute("numero_documento"));
					
					log.info("El ls_medio es:" + ls_medio);
					log.info("El ls_codigo_tipo es:" + ls_codigo_tipo);
					log.info("El ls_numero_documento es:" + ls_numero_documento);
					
					session.setAttribute("medio", ls_medio);
		            session.setAttribute("codigo_tipo", ls_codigo_tipo);
		            session.setAttribute("numero_documento",ls_numero_documento);
		            
			  		return (mapping.findForward("regresar"));
				}
				else 
				if(ls_pagina.equals("reportes")){

			  		return (mapping.findForward("regreportes"));
				}
				
			} 
			
			else if (tipo.equals("reportes")){

				
				session.removeAttribute("valorct");
				session.removeAttribute("desc_origen");
				session.removeAttribute("descripcion_persona");
				
				//String ls_accion="";
				String ls_valor="";
				
				ls_accion = request.getParameter("accion");
				
				log.info("El ls_accion es:" + ls_accion);
				
				IAdministradorDAO daoIAdministradorDAO = new SqlAdministradorDAO();
				
				//Collection listaoficinas = daoIAdministradorDAO.of_lista_oficinas(cnx);
				
				Collection listausuarios = daoIAdministradorDAO.of_lista_usuarios(cnx);
				Collection listatipodoc = daoIAdministradorDAO.of_lista_tipodocumento(cnx);
				Collection listasolicitud = daoIAdministradorDAO.of_lista_solicitud(cnx);
				
				
				if(ls_accion.equals("general")){
					
					String ls_codigo_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
					
					if(ls_codigo_oficina.equals("2") || ls_codigo_oficina.equals("14")){
						log.info("Si el codigo oficina es 2 o 14..");
						Collection rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx);	
						session.setAttribute("listaoficinas", rs_oficina);
						session.setAttribute("listausuarios",listausuarios);
						session.setAttribute("listatipodoc",listatipodoc);
						session.setAttribute("rs_solicitud",listasolicitud);
						
						return (mapping.findForward("reportesgenerales"));
					}else{
						log.info("Si el codigo oficina es diferente a 2 o 14..");	
						Collection rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx,ls_codigo_oficina);	
						session.setAttribute("listaoficinas", rs_oficina);
						session.setAttribute("listausuarios",listausuarios);
						session.setAttribute("listatipodoc",listatipodoc);
						session.setAttribute("rs_solicitud",listasolicitud);
						
						return (mapping.findForward("reportesgenerales"));
					}
					
					//session.setAttribute("listaoficinas",listaoficinas);
					
					
				}else 
				if(ls_accion.equals("criteriousuario")){
					
					ls_valor="CU";
					 String ls_codigo_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
					
					if(ls_codigo_oficina.equals("2") || ls_codigo_oficina.equals("14")){
						log.info("Si el codigo oficina es 2 o 14..");
						Collection rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx);	
						session.setAttribute("listaoficinas", rs_oficina);
						session.setAttribute("listausuarios",listausuarios);
						session.setAttribute("listatipodoc",listatipodoc);
						session.setAttribute("rs_solicitud",listasolicitud);
						session.setAttribute("codigo_oficina",ls_codigo_oficina);
						return (mapping.findForward("reportescriterio"));
					}else{
						log.info("Si el codigo oficina es diferente a 2 o 14..");	
						Collection rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx,ls_codigo_oficina);	
						session.setAttribute("listaoficinas", rs_oficina);
						session.setAttribute("listausuarios",listausuarios);
						session.setAttribute("listatipodoc",listatipodoc);
						session.setAttribute("rs_solicitud",listasolicitud);
						session.setAttribute("codigo_oficina",ls_codigo_oficina);
						return (mapping.findForward("reportescriterio"));
					}
					
				}else 
					if(ls_accion.equals("criteriotipodoc")){
						
						/*String valorct = String.valueOf(session.getAttribute("valorct"));
						log.info("El valorct del frame  es:" + valorct);
						*/
						ls_valor="CT";
						
						 String ls_codigo_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
							
							if(ls_codigo_oficina.equals("2") || ls_codigo_oficina.equals("14")){
								log.info("Si el codigo oficina es 2 o 14..");
								Collection rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx);	
								session.setAttribute("listaoficinas", rs_oficina);
								session.setAttribute("listausuarios",listausuarios);
								session.setAttribute("listatipodoc",listatipodoc);
								session.setAttribute("rs_solicitud",listasolicitud);
								session.setAttribute("codigo_oficina",ls_codigo_oficina);
								session.setAttribute("operacion",ls_valor);
								return (mapping.findForward("reportescriterio"));
							}else{
								log.info("Si el codigo oficina es diferente a 2 o 14..");	
								Collection rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx,ls_codigo_oficina);	
								session.setAttribute("listaoficinas", rs_oficina);
								session.setAttribute("listausuarios",listausuarios);
								session.setAttribute("listatipodoc",listatipodoc);
								session.setAttribute("rs_solicitud",listasolicitud);
								session.setAttribute("codigo_oficina",ls_codigo_oficina);
								session.setAttribute("operacion",ls_valor);
								return (mapping.findForward("reportescriterio"));
							}
						
				}else 
					if(ls_accion.equals("criteriogenerales")){
						ls_valor="CG";
						
						 String ls_codigo_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
							
							if(ls_codigo_oficina.equals("2") || ls_codigo_oficina.equals("14")){
								log.info("Si el codigo oficina es 2 o 14..");
								Collection rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx);	
								session.setAttribute("listaoficinas", rs_oficina);
								session.setAttribute("listausuarios",listausuarios);
								session.setAttribute("listatipodoc",listatipodoc);
								session.setAttribute("rs_solicitud",listasolicitud);
								session.setAttribute("codigo_oficina",ls_codigo_oficina);
								session.setAttribute("operacion",ls_valor);
								return (mapping.findForward("reportescriterio"));
							}else{
								log.info("Si el codigo oficina es diferente a 2 o 14..");	
								Collection rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx,ls_codigo_oficina);	
								session.setAttribute("listaoficinas", rs_oficina);
								session.setAttribute("listausuarios",listausuarios);
								session.setAttribute("listatipodoc",listatipodoc);
								session.setAttribute("rs_solicitud",listasolicitud);
								session.setAttribute("codigo_oficina",ls_codigo_oficina);
								session.setAttribute("operacion",ls_valor);
								return (mapping.findForward("reportescriterio"));
							}
							
					}
				
					else 
						if(ls_accion.equals("fecharecorrido")){
							log.info("Dentro de detalleseg");
							
							String usuario_actual="";
							String codigo_oficina="";
							Funciones funciones=new Funciones();
							
							usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
			        		log.info("El usuario actual es.." + usuario_actual);
			        		codigo_oficina = funciones.of_codigo_oficina(cnx, usuario_actual);
			        		log.info("El codigo de Oficina es.." + codigo_oficina);
			        		
			        		String codigo_documento=request.getParameter("codigo_documento");
			        		String codigo_expediente=request.getParameter("codigo_expediente");
			        		String fecha_rpta=request.getParameter("fecha_rpta");
			        		String secuencia_movimiento=request.getParameter("secuencia_movimiento");
			        		
			        		log.info("El codigo_documento es.." + codigo_documento);
			        		log.info("El codigo_expediente es.." + codigo_expediente);
			        		log.info("El fecha_rpta es.." + fecha_rpta);
			        		log.info("El secuencia_movimiento es.." + secuencia_movimiento);
			        		
							
								 
			        		if(fecha_rpta.equals("") || fecha_rpta==null ){
			            		
								session.setAttribute("codigo_oficina", codigo_oficina);
								session.setAttribute("documento", codigo_documento);
								session.setAttribute("secuencia", secuencia_movimiento);
								session.setAttribute("accion",ls_accion);
			            		return (mapping.findForward("recorridofecnull"));
			            		
			            	}else{
			            		
			            		session.setAttribute("codigo_oficina", codigo_oficina);
			            		session.setAttribute("documento", codigo_documento);
								session.setAttribute("secuencia", secuencia_movimiento);
								session.setAttribute("accion",ls_accion);
								
			            		return (mapping.findForward("recorrido"));
			            		 }
								
								
							
							//return (mapping.findForward("fechanull"));
						}
						else 
							if(ls_accion.equals("criterioestado")){
								ls_valor="CE";
								
								Collection rs_oficina;
								 String ls_codigo_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
								 BUsuario userSystem =(BUsuario) session.getAttribute("nombre_usuario");
								 String ls_ess = userSystem.getFlag();	
								 
								 	session.setAttribute("listausuarios",listausuarios);
									session.setAttribute("listatipodoc",listatipodoc);
									session.setAttribute("rs_solicitud",listasolicitud);
									session.setAttribute("codigo_oficina",ls_codigo_oficina);
									session.setAttribute("operacion",ls_valor);
								 
									log.info("El flag es >>> "+ls_ess);	 
									if(ls_codigo_oficina.equals("2") || ls_codigo_oficina.equals("14") || ls_ess.equals("S")){
										log.info("Si el codigo oficina es 2 o 14 o S");
										rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx);	
									}else{
										log.info("Si el codigo oficina es diferente a 2 o 14..");	
										rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx,ls_codigo_oficina);	
										
									}
									session.setAttribute("listaoficinas",rs_oficina);
									return (mapping.findForward("reportescriterio"));
									
							}
			}
			
			else 
				if(tipo.equals("cambioclave")){
					
					log.info("Direccionando a modificar clave");
					
					
					
					return (mapping.findForward("cambioclave"));
				}
			
           
				else 
				if(tipo.equals("mensajerecepcion")){
					
					log.info("Direccionando mensaje de recepcion");
					
					String ls_numero_expediente ="";
					String codigo_oficina ="";
				    //ls_codigo_recepcion = request.getParameter("codigo_recepcion");
				    codigo_oficina   =   String.valueOf(session.getAttribute("codigo_oficina"));
				   // String mensaje   =   String.valueOf(session.getAttribute("mensaje"));
				    String mensaje_reg   =   String.valueOf(session.getAttribute("mensaje_reg"));
				    String mensaje_exp   =   String.valueOf(session.getAttribute("mensaje_exp"));
				    
				    //log.info("El ls_codigo_recepcion es.." + ls_codigo_recepcion);
				    log.info("El codigo_oficina es.." + codigo_oficina);
				   // log.info("El mensaje es.." + mensaje);
				    log.info("El mensaje_reg es.." + mensaje_reg);
				    log.info("El mensaje_exp es.." + mensaje_exp);
				    
				    String vcodigo_documento=mensaje_reg.substring(mensaje_reg.indexOf(':')+1,mensaje_reg.length());
				    String vcodigo_expediente=mensaje_exp.substring(mensaje_exp.indexOf(':')+1,mensaje_exp.length());
				    
				    vcodigo_documento=(vcodigo_documento==null)? "":vcodigo_documento.trim();
				    vcodigo_expediente=(vcodigo_expediente==null)? "":vcodigo_expediente.trim();
				    
				    request.setAttribute("vcodigo_documento", vcodigo_documento);
				    request.setAttribute("vcodigo_expediente", vcodigo_expediente);
				    
				    /*Funciones funciones=new Funciones();
				    
				    ls_numero_expediente   = funciones.of_numero_expediente(cnx, ls_codigo_recepcion)  ;
				    
				    log.info("El ls_numero_expediente es.." + ls_numero_expediente);*/
				    
					//ls_mensaje ="SE HA DERIVADO EL DOCUMENTO FISICO CON NUMERO DE ENVIO   :   "+ls_codigo_recepcion  + " Y NUMERO DE EXPEDIENTE   :   "+ls_numero_expediente;
				    //ls_mensaje ="REGISTRO OK :";
					log.info("El ls_codigo_recepcion es.." + ls_codigo_recepcion);
					
					//session.setAttribute("mensaje",ls_mensaje);
					//session.setAttribute("mensaje_reg",mensaje_reg);
					//session.setAttribute("mensaje_exp",mensaje_exp);
					
					return (mapping.findForward("mensajerecepcion"));
				}
			
				else 

				if(tipo.equals("inicio")){
					
					return (mapping.findForward("inicio"));
				}
			
				else if(tipo.equals("recepcion")){
						
						String  usuario_actual="";
						String  codigo_oficina="";
						Funciones funciones=new Funciones();
						
						usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
		        		log.info("El usuario actual es.." + usuario_actual);
		        		codigo_oficina = funciones.of_codigo_oficina(cnx, usuario_actual);
		        		log.info("El codigo de Oficina es.." + codigo_oficina);
		        		
						
							 session.removeAttribute("rs_recepcion_doc");
							 
							session.setAttribute("nombreusuario", usuario_actual);
							session.setAttribute("codigo_oficina", codigo_oficina);
							
							return (mapping.findForward("recepcion"));
						
					}
				else if(tipo.equals("ayudamedios")){
					
					log.info("Dentro de Ayuda Medios..");
					
					String numero_registro=request.getParameter("numero_registro");
	        		String codigo_expediente=request.getParameter("codigo_expediente");
	        		
	        		log.info("El numero_registro es.." + numero_registro);
	        		log.info("El codigo_expediente es.." + codigo_expediente);
	        		log.info("Codigo de oficina origen : "+ls_codigooficina);
					//IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();	
					
					Collection listaFrameBusqueda = daoIUsuarioDAO.of_busca_documentos_pendientes(cnx,numero_registro,ls_codigooficina);
					
					session.setAttribute("rs_lista_pendientes",listaFrameBusqueda);
					
						return (mapping.findForward("ayudamedios"));
					
				}else if(tipo.equals("ayudareportes")){
					
					log.info("Dentro de ayudareportes..");
					Funciones funcion = new Funciones();
					String codigo_oficina=request.getParameter("codofi");
	        		String operacion=request.getParameter("operacion"); //1,0
	        		String ls_fecha_inicio = (String) session.getAttribute("fecha_inicio");
	        		String ls_fecha_fin = (String) session.getAttribute("fecha_fin");
	        		String ls_codigo_motivo = (String) session.getAttribute("codigo_motivo");
	        		String nom_motivo =null;
	        		log.info("Fecha inicio >> "+ls_fecha_inicio);
					log.info("Fecha fin >> "+ls_fecha_fin);
					log.info("Codigo motivo >> "+ls_codigo_motivo);
					
					
	        		ls_fecha_inicio=(ls_fecha_inicio==null)?"":ls_fecha_inicio;
					ls_fecha_fin=(ls_fecha_fin==null)?"":ls_fecha_fin;
					ls_codigo_motivo=(ls_codigo_motivo==null)?"":ls_codigo_motivo;
					nom_motivo=(ls_codigo_motivo=="0")?null:ls_codigo_motivo;
					nom_motivo = funcion.of_nombre_motivo(cnx,nom_motivo);
					
	        		DateFormat df=new SimpleDateFormat ("dd/MM/yyyy");
					Date fecha_inicio = null;
					Date fecha_fin = null;

					try{
					fecha_inicio = df.parse(ls_fecha_inicio);
					}catch(ParseException ex){}
					
					try{
						fecha_fin = df.parse(ls_fecha_fin);
					}catch(ParseException ex){}
					
	        		if(operacion.equals("1")){
	        			log.info("es por recibir");//
	        			listado = daoIReportesDAO.porrecibirreporte(cnx, fecha_inicio, fecha_fin, codigo_oficina,ls_codigo_motivo);
	        			
	        		}else{
	        			log.info("es en tramite");//
	        			listado = daoIReportesDAO.ntramitereporte(cnx, fecha_inicio, fecha_fin, codigo_oficina,ls_codigo_motivo);
	        			
	        		}
	        		session.setAttribute("oficinaz", funcion.of_nombre_oficina(cnx, codigo_oficina)+" - "+funcion.of_nombre_corto_oficina(cnx, codigo_oficina));
	        		log.info("pongo en session >> "+operacion);
	        		session.setAttribute("operacion", operacion);
	        		session.setAttribute("nom_motivo", nom_motivo);
	        		session.setAttribute("listadocumentos", listado);
	        		
	        		return (mapping.findForward("ayudareportes"));
					
				}
				else if(tipo.equals("hojaenvio")){
					
					/*session.setAttribute("codigo_recepcion",codigo_recepcion_env);
					session.setAttribute("codigo_expediente",codigo_expediente_env);
					session.setAttribute("codigo_documento",codigo_documento_env);
					session.setAttribute("detalleDocumento",BMesaPartesVO);*/
					
					/*session.removeAttribute("codigo_recepcion");
					session.removeAttribute("codigo_expediente");
					session.removeAttribute("codigo_documento");
					session.removeAttribute("detalleDocumento");*/
					
					
					session.removeAttribute("codigo_oficina0");
			    	 session.removeAttribute("codigo_oficina1");
			    	 session.removeAttribute("codigo_oficina2");
					 session.removeAttribute("codigo_oficina3");
					 
					log.info("Dentro de Hoja de Envio..");
					String codigo_recepcion_env="";
					String codigo_expediente_env="";
					String numerodocumento_env="";
					String codigo_documento_env="";
					String fecha_env="";
					String codigo_motivo_env="";
					String codigo_oficina_env="";
					String observacion_env="";
					String codigo_secuencia_env="";
					//IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
					Funciones funciones=new Funciones();
					String ls_oficinas="";
					codigo_expediente_env   =  request.getParameter("codigo_expediente");
					codigo_documento_env   =   request.getParameter("codigo_documento");
					codigo_recepcion_env   =   funciones.of_max_hoja_envio(cnx,codigo_documento_env);
					codigo_secuencia_env   =   funciones.of_max_secuencia_envio(cnx,codigo_documento_env);
					
					log.info("El codigo_recepcion_env es.." + codigo_recepcion_env);
					log.info("El codigo_expediente_env es.." + codigo_expediente_env);
					log.info("El codigo_documento_env es.." + codigo_documento_env);
					log.info("El codigo_secuencia_env es.." + codigo_secuencia_env);
					
		ls_oficinas=funciones.of_oficina_deriva(cnx, codigo_recepcion_env, codigo_documento_env);
		log.info("El ls_oficinas es.." + ls_oficinas);
		
			BMesaPartes BMesaPartesVO=daoIUsuarioDAO.of_detalle_documento_hoja_envio(cnx, codigo_documento_env
				, codigo_recepcion_env,codigo_secuencia_env);
			
			String[] arrayOficinas = ls_oficinas.split("-");
			
			log.info("El arrayOficinas es.." + arrayOficinas);
			
			String codigo_oficina0 ="";
			String codigo_oficina1 ="";
			String codigo_oficina2 ="";
			String codigo_oficina3 ="";
			
			//int longitud = ls_oficinas.length();

			log.info("El longitud Array es.." + arrayOficinas.length);
			
			   //codigo_oficina0 = arrayOficinas[0];
		       //codigo_oficina1 = arrayOficinas[1];
		      /*String codigo_oficina2 = arrayOficinas[2];
		      String codigo_oficina3 = arrayOficinas[3];*/
		     
		        
		        
		        
		      //  session.setAttribute("codigo_oficina0",codigo_oficina0);
				/*log.info("El codigo_oficina1 es.." + codigo_oficina1);
				log.info("El codigo_oficina2 es.." + codigo_oficina2);
				log.info("El codigo_oficina3 es.." + codigo_oficina3);*/
				
			/*Vector fila = new Vector(); 
			
			fila.add(arrayOficinas);
			ls_oficinas.*/
			for (int i = 0; i < arrayOficinas.length; i++) {
				log.info("codigo_oficina"+i+"---"+ arrayOficinas[i]);
				
				session.setAttribute("codigo_oficina"+i,arrayOficinas[i]);
				//String[i]=arrayOficinas.
			//session.setAttribute("codigo_oficina0",arrayOficinas[i]);
				/*String codigo_oficina0=arrayOficinas[0];
				String codigo_oficina1=arrayOficinas[1];
				String codigo_oficina2=arrayOficinas[2];
				String codigo_oficina3=arrayOficinas[3];
				log.info("El codigo_oficina0 es.." + codigo_oficina0);
				log.info("El codigo_oficina1 es.." + codigo_oficina1);
				log.info("El codigo_oficina2 es.." + codigo_oficina2);
				log.info("El codigo_oficina3 es.." + codigo_oficina3);*/
				
				//String[] codigo_oficina1=(String) arrayOficinas.toString();
			}
					
					session.setAttribute("codigo_recepcion",codigo_recepcion_env);
					session.setAttribute("codigo_expediente",codigo_expediente_env);
					session.setAttribute("codigo_documento",codigo_documento_env);
					session.setAttribute("detalleDocumento",BMesaPartesVO);
					/*session.setAttribute("codigo_oficina0",codigo_oficina0);
					session.setAttribute("codigo_oficina1",codigo_oficina1);*/
					/*session.setAttribute("codigo_oficina2",codigo_oficina2);
					session.setAttribute("codigo_oficina3",codigo_oficina3);*/
					
					/*session.setAttribute("fecha",fecha_env);
					session.setAttribute("codigo_motivo",codigo_motivo_env);
					session.setAttribute("codigo_oficina",codigo_oficina_env);
					session.setAttribute("observacion",observacion_env.toUpperCase());*/
					
						return (mapping.findForward("hojaenvio"));
					
				}
			
				 else if(tipo.equals("verhojaruta")){
					/*Modificado por jbrunomp@gmail.com
					 * Fecha:03-11-2015
					 * Observacion: el metodo anterior provocaba unas excepciones
					 * el actual corrige eso totalmente y se evita usar la jsp
					 * */
					log.info("Dentro de Ver hoja de ruta.. nuevo");
					
	        		String codigo_documento=request.getParameter("codigo_documento");
	        		log.info("El codigo_documento es.." + codigo_documento);
	        		//IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
	        		String ls_codigo_documento="";
	        		String ls_desc_origen="";
	        		String ls_numero_documento="";
	        		String ls_folios_documento="";
	        		String ls_asunto_documento="";
	        		String ls_fecha_rpta="";
	        		String ls_fecha_registro="";
	        		int anio=0;
	        		log.info("Dentro de Ver hoja de ruta.. nuevo 2");
	        		 Map parameters = new HashMap();
	        		
	        		 ServletOutputStream servletOutputStream = response.getOutputStream();
	        		    InputStream reportStream = getServlet().getServletConfig()
	        		        .getServletContext().getResourceAsStream("/reportes/hojaruta_double.jasper");
	        		    response.setContentType("application/pdf");
	        		    /* ServletOutputStream servletOutputStream = response.getOutputStream();
	        		    InputStream reportStream = getServlet().getServletConfig()
	        		        .getServletContext().getResourceAsStream("/reportes/report2.jasper");
	        		    response.setContentType("application/pdf");*/
	        		 
	        		   
					try {
						log.info("Dentro de Ver hoja de ruta.. nuevo 3");
						
						
						if(cnx.isClosed()){
							 System.out.println("AbriConexion - Reportes Hoja Ruta");
							 cnx = getConnection(request, "principal");
						}
						

						
						BMesaPartes BMesaPartesVO=daoIUsuarioDAO.of_detalle_documento_hoja_ruta(cnx,codigo_documento);
						log.info("Dentro de Ver hoja de ruta.. nuevo 4");
		        		ls_codigo_documento=BMesaPartesVO.getCodigo_documento();
		        		ls_desc_origen=BMesaPartesVO.getDesc_origen();
		        		ls_numero_documento=BMesaPartesVO.getNumero_documento();
		        		ls_folios_documento=BMesaPartesVO.getFolios_documento();
		        		ls_asunto_documento=BMesaPartesVO.getAsunto_documento();
		        		ls_fecha_rpta=BMesaPartesVO.getFecha_rpta();
		        		ls_fecha_registro=BMesaPartesVO.getFecha_registro();
		        		anio=BMesaPartesVO.getAnio();
		        		Date fecha=new Date(); 
		        		
		        		String sFecha = ""; 
		        		int iEntero = fecha.getDate(); 
		        		sFecha = String.valueOf(iEntero); 
		        		iEntero = fecha.getMonth(); 
		        		sFecha = sFecha + "/" + String.valueOf(iEntero); 
		        		iEntero = fecha.getYear() + 1900; 
		        		sFecha = sFecha + "/" + String.valueOf(iEntero); 
		        		anio=iEntero;

		        		log.info("EL AÑO ACTUAL ES: " +iEntero );
		        		
		        		parameters.put("codigo_documento",ls_codigo_documento);
		        		 parameters.put("secuencia_movimiento","1");
		        		 parameters.put("desc_origen",ls_desc_origen);
		        		 parameters.put("numero_documento",ls_numero_documento);
		        		 parameters.put("folios_documento",ls_folios_documento);
		        		 parameters.put("asunto_documento",ls_asunto_documento);
		        		 parameters.put("fecha_rpta",ls_fecha_rpta);
		        		 parameters.put("fecha_registro",ls_fecha_registro);
		        		 parameters.put("iEntero",anio);
		        		 //parameters.put("iEntero",iEntero);
		        		 //../../resources/images/logoConcytec.PNG
		        		 parameters.put("ruta_logoConcy", "../../img/cabeceraconcytec.gif");    
		        		 log.info("Dentro de Ver hoja de ruta.. nuevo 5");
		        	
		        		 
		        		 
		        		 JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,
		        		    		parameters, cnx);
		        		 
		        		 /*JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,
		        		    		parameters, cnx);*/
		        		 log.info("Dentro de Ver hoja de ruta.. nuevo 6");
		        		 
		        		
		        			
	                } catch( Exception e) {
	                	e.printStackTrace();
	                	log.info("Archivo no encontrado..");
	                    /* Hacer algo */
	                 }finally{
	                	// cnx.close();
		        		    servletOutputStream.flush();
		        		    servletOutputStream.close();
	                 }
					
						return null; //este es el secreto, que evita la excepcion
						
				}
			
				 else if(tipo.equals("verhojaruta_old")){
						
						log.info("Dentro de Ver hoja de ruta..");
						
		        		String codigo_documento=request.getParameter("codigo_documento");
		        		log.info("El codigo_documento es.." + codigo_documento);
		        		//IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		        		String ls_codigo_documento="";
		        		String ls_desc_origen="";
		        		String ls_numero_documento="";
		        		String ls_folios_documento="";
		        		String ls_asunto_documento="";
		        		String ls_fecha_rpta="";
		        		String ls_fecha_registro="";
		        		
		        		
		        		
						try {
						
							BMesaPartes BMesaPartesVO=daoIUsuarioDAO.of_detalle_documento_hoja_ruta(cnx,codigo_documento);
			        		
			        		ls_codigo_documento=BMesaPartesVO.getCodigo_documento();
			        		ls_desc_origen=BMesaPartesVO.getDesc_origen();
			        		ls_numero_documento=BMesaPartesVO.getNumero_documento();
			        		ls_folios_documento=BMesaPartesVO.getFolios_documento();
			        		ls_asunto_documento=BMesaPartesVO.getAsunto_documento();
			        		ls_fecha_rpta=BMesaPartesVO.getFecha_rpta();
			        		ls_fecha_registro=BMesaPartesVO.getFecha_registro();
			        		
			        		/*log.info("El ls_codigo_documento es.." + ls_codigo_documento);
			        		log.info("El ls_desc_origen es.." + ls_desc_origen);
			        		log.info("El ls_numero_documento es.." + ls_numero_documento);
			        		log.info("El ls_folios_documento es.." + ls_folios_documento);
			        		log.info("El ls_asunto_documento es.." + ls_asunto_documento);
			        		log.info("El ls_fecha_rpta es.." + ls_fecha_rpta);
			        		log.info("El ls_fecha_registro es.." + ls_fecha_registro);*/
							
			        		session.setAttribute("codigo_documento",ls_codigo_documento);
			        		session.setAttribute("desc_origen",ls_desc_origen);
			        		session.setAttribute("numero_documento",ls_numero_documento);
			        		session.setAttribute("folios_documento",ls_folios_documento);
			        		session.setAttribute("asunto_documento",ls_asunto_documento);
			        		session.setAttribute("fecha_rpta",ls_fecha_rpta);
			        		session.setAttribute("fecha_registro",ls_fecha_registro);
			        	
			        		



							    		
		                } catch( Exception e) {
		                	e.printStackTrace();
		                	log.info("Archivo no encontrado..");
		                    /* Hacer algo */
		                 }
							return (mapping.findForward("verhojaruta"));
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
