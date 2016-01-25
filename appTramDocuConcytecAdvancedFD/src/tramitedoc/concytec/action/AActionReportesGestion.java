package tramitedoc.concytec.action;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BOficinas;
import tramitedoc.concytec.bean.BReporteConsolidadoTransacciones;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;

public class AActionReportesGestion extends ValidaSessionAction {
	protected  Log log = LogFactory.getLog(AActionReportesGestion.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Connection cnx =null;
		try {
			log.info("Entry  ...");
			Funciones funciones=new Funciones();
			cnx = getConnection(request, "principal");
			log.info("El cnx  ==> " + cnx);

			HttpSession session = request.getSession(true);
			String ls_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
			String ls_persona = String.valueOf(session.getAttribute("rs_persona"));

			String ls_operacion = request.getParameter("accion");

			IReportesDAO daoIReportesDAO = new SqlReportesDAO();
			//IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			
			Collection listado = null;
			
			String tipo = request.getParameter("tipo");
			
			if(tipo.equals("mos_cons_tran")){
				
				///AQUI DEBO INGRESAR FECHA ACTUAAL!!!
				
				String mesActual = getMesActual();
				String annoActual = getAnioActual();
				int mesEnNumero = Integer.parseInt(mesActual)+1;
				if(mesEnNumero>=10){
					request.setAttribute("fecha_inicio", "01/"+mesEnNumero+"/"+annoActual);
				}else{
					request.setAttribute("fecha_inicio", "01/0"+mesEnNumero+"/"+annoActual);
				}
				return (mapping.findForward("reporte_cons_trans"));
				
			}else if(tipo.equals("mos_doc_pend")){
				
				BUsuario usuario = (BUsuario)session.getAttribute("nombre_usuario");
						
				log.info("usuario:" +usuario.getUsuario())	;
				
				
				Collection rs_oficina = daoIReportesDAO.of_lista_oficinas(cnx, usuario);
				session.setAttribute("rs_oficina_reporte", rs_oficina);
				return (mapping.findForward("reporte_doc_pendientes"));
			}else if (tipo.equals("cons_tran")) {

				DateFormat df=new SimpleDateFormat ("dd/MM/yyyy");
				Date fecha_inicio = null;
				Date fecha_fin = null;
				String ls_fecha_inicio=request.getParameter("fecha_inicio");
				String ls_fecha_fin=request.getParameter("fecha_fin");
				try{
				fecha_inicio = df.parse(ls_fecha_inicio);
				}catch(ParseException ex){}
				
				try{
					fecha_fin = df.parse(ls_fecha_fin);
				}catch(ParseException ex){}
				
				request.setAttribute("fecha_inicio", ls_fecha_inicio);
				request.setAttribute("fecha_fin", ls_fecha_fin);
				
				log.info("Ingreso a el reporte consolidado");
				listado = daoIReportesDAO.consolidado_transacciones(cnx,fecha_inicio, fecha_fin);			

				int sumas[]=sumas(listado);
				BReporteConsolidadoTransacciones r=new BReporteConsolidadoTransacciones();
				BOficinas of=new BOficinas();
				of.setNombre_corto("Total");
				r.setOficina(of);
				r.setCreados(sumas[0]);
				r.setDerivados(sumas[1]);
				r.setArchivados(sumas[2]);
				r.setAnulados(sumas[3]);
				listado.add(r);
				
				request.setAttribute("listado_trans", listado);
				
				
				return (mapping.findForward("reporte_cons_trans"));
			} else if (tipo.equals("doc_pend")) {
				
				DateFormat df=new SimpleDateFormat ("dd/MM/yyyy");
				Date fecha_inicio = null;
				Date fecha_fin = null;
				String ls_fecha_inicio="";
				String ls_fecha_fin="";
				String  ls_codigo_oficina=request.getParameter("codigo_oficina");
				
				
				request.setAttribute("fecha_inicio", ls_fecha_inicio);
				request.setAttribute("fecha_fin", ls_fecha_fin);
				request.setAttribute("codigo_oficina", ls_codigo_oficina);
				
				log.info("Ingreso a el reporte de documentos pendientes");
				if(ls_codigo_oficina.equals("0")){
				request.setAttribute("mensaje_reporte", "Ingrese una oficina a buscar");	
				}else{
				listado = daoIReportesDAO.documentos_pendientes_oficina(cnx,fecha_inicio, fecha_fin,ls_codigo_oficina);
				}
				session.setAttribute("listado_docs", listado);
				return (mapping.findForward("reporte_doc_pendientes"));
			}else if(tipo.equals("rep_mos_doc")){
				log.info("Nuevo reporte");
				
								
				session.removeAttribute("listado_rep_obs");
				session.removeAttribute("fecha_inicio");
				session.removeAttribute("fecha_fin");
				
				String mesActual = getMesActual();
				String annoActual = getAnioActual();
				int mesEnNumero = Integer.parseInt(mesActual)+1;
				if(mesEnNumero>=10){
					request.setAttribute("fecha_inicio", "01/"+mesEnNumero+"/"+annoActual);
				}else{
					request.setAttribute("fecha_inicio", "01/0"+mesEnNumero+"/"+annoActual);
				}
				
				return (mapping.findForward("reporte_mos_doc"));
			}else if(tipo.equals("cons_pend")){
				log.info("Entrando a la busqueda");

				String flag = (String) session.getAttribute("flag");
				String cargo_personal = (String) session.getAttribute("cargo_personal");
				
				flag=(flag==null)?"":flag;
				cargo_personal=(cargo_personal==null)?"":cargo_personal;
										
				
				DateFormat df=new SimpleDateFormat ("dd/MM/yyyy");
				Date fecha_inicio = null;
				Date fecha_fin = null;
				String ls_fecha_inicio=request.getParameter("fecha_inicio");
				String ls_fecha_fin=request.getParameter("fecha_fin");
				String ls_codigo_motivo=request.getParameter("codigo_motivo");
				int grupo_oficina=0;
				try{
				fecha_inicio = df.parse(ls_fecha_inicio);
				}catch(ParseException ex){}
				
				try{
					fecha_fin = df.parse(ls_fecha_fin);
				}catch(ParseException ex){}
				
				ls_fecha_inicio=(ls_fecha_inicio==null)?"":ls_fecha_inicio;
				ls_fecha_fin=(ls_fecha_fin==null)?"":ls_fecha_fin;
				ls_codigo_motivo=(ls_codigo_motivo==null)?"":ls_codigo_motivo;
								
				log.info("Fecha inicio >> "+ls_fecha_inicio);
				log.info("Fecha fin >> "+ls_fecha_fin);
				log.info("Codigo motivo >> "+ls_codigo_motivo);
				
				session.setAttribute("fecha_inicio", ls_fecha_inicio);
				session.setAttribute("fecha_fin", ls_fecha_fin);
				session.setAttribute("codigo_motivo", ls_codigo_motivo);
				/****AQUI ES****/
				log.info("flag >>>>> "+flag);
				
				if(flag.equals("S") || flag.equals("A") || cargo_personal.equals("11")){
					listado = daoIReportesDAO.consolidado_transaccionesn(cnx,fecha_inicio, fecha_fin,ls_fecha_inicio,ls_fecha_fin,ls_codigo_motivo);
				}else{
				if(cargo_personal.equals("3") || cargo_personal.equals("10") || cargo_personal.equals("13")){
					
					grupo_oficina=funciones.of_grupo_oficina(cnx,ls_oficina);
					listado = daoIReportesDAO.consolidado_transaccionesn(cnx,fecha_inicio, fecha_fin,ls_fecha_inicio,ls_fecha_fin,ls_codigo_motivo,String.valueOf(grupo_oficina));
				}
				}	

				int sumas[]=sumasn(listado);
				Hashtable objListaDoc = new Hashtable();

				objListaDoc.put("oficina","Total");
				objListaDoc.put("actionporrecibir",sumas[0]);
				objListaDoc.put("actionntramite",sumas[1]);

				listado.add(objListaDoc);
				/***/
				/*****/
				session.setAttribute("listado_rep_obs", listado);
								
				return (mapping.findForward("reporte_mos_doc"));
			}			

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			closeConnection(cnx);
		}
		return (mapping.findForward("error"));
	}

	private int[] sumasn(Collection listado) {
		int total[]= {0,0};
		Iterator it=listado.iterator();
		
		while(it.hasNext()){
			Hashtable objListaDoc = (Hashtable) it.next();
			total[0]+=(Integer) objListaDoc.get("porrecibir");
			total[1]+=(Integer) objListaDoc.get("ntramite");

		}
		return total;
	}
	
	private int[] sumas(Collection listado) {
		int total[]= {0,0,0,0};
		Iterator it=listado.iterator();
		while(it.hasNext()){
			BReporteConsolidadoTransacciones rep=(BReporteConsolidadoTransacciones)it.next();
			total[0]+=rep.getCreados();
			total[1]+=rep.getDerivados();
			total[2]+=rep.getArchivados();
			total[3]+=rep.getAnulados();
		}
		return total;
	}
	
	private String getAnioActual() {
		Calendar c = new GregorianCalendar(); 
		String anio = Integer.toString(c.get(Calendar.YEAR));
		return anio;
	}
	private String getDiaActual() {
		Calendar c = new GregorianCalendar(); 
		String fecha = Integer.toString(c.get(Calendar.DATE));
		return fecha;
	}
	private String getMesActual() {
		Calendar c = new GregorianCalendar(); 
		String fecha = Integer.toString(c.get(Calendar.MONTH));
		return fecha;
	}
}
