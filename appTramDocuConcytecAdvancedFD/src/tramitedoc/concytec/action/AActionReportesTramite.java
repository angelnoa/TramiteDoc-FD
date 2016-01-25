package tramitedoc.concytec.action;
import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BOficinas;
import tramitedoc.concytec.bean.BReporteConsolidadoTransacciones;
import tramitedoc.concytec.dao.interfaces.IReportesDAO;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlReportesDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.util.ValidaSessionAction;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionReportesTramite extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(AActionReportesTramite.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.info("Dentro de Ver el Reporte");
		String ls_oficina=null;
        String ls_persona=null;
        String ls_naturaleza=null;
        String ls_estado=null;
        String ls_fecha_inicio=null;
        String ls_fecha=null;
        String ls_filtro, ls_valorfiltro, ls_pagina=null;
        String ls_msg_error=null;
        String ls_operacion=null;
        
        String ls_codigo_tipo=null;
        String ls_usuario=null;
        String ls_sle_fecha=null;
        String ls_codigo_oficina=null;
        String ls_procedencia=null;
        String ls_firmadopor=null;
        String ls_tipodoc=null;
        
        String ls_accion="";
        String ls_mensaje="";
        
        String fecha_rpta="";
		String numero_referencia="";
		 String ls_estado_criterio_usuario="";
         String ls_estado_criterio="";
         String ls_fecha_rpta="";
         String ls_numero_referencia="";
         
        int li_retorno=0;
        
		try {
			
			Connection cnx = getConnection(request, "principal");
			log.info("El cnx  ==> " + cnx);
			
			 HttpSession session = request.getSession(true);        

		        /*Verificar si la sesion se perdio*/
		        
		         ls_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
		         ls_persona = String.valueOf(session.getAttribute("rs_persona"));
		        
		         ls_operacion = request.getParameter("accion");
		         
		         ls_naturaleza = request.getParameter("naturaleza");
		         ls_tipodoc = request.getParameter("tipodoc");
		         ls_estado = request.getParameter("estado");
		         ls_usuario = request.getParameter("usuario");
		         ls_sle_fecha= request.getParameter("sle_fecha");
		         ls_codigo_oficina= request.getParameter("codigo_oficina");
		         ls_estado_criterio_usuario= request.getParameter("estado_criterio_usuario");
		         ls_codigo_tipo= request.getParameter("codigo_tipo");
		         ls_procedencia= request.getParameter("procedencia");
		         ls_firmadopor= request.getParameter("firmadopor");
		         ls_estado_criterio= request.getParameter("estado_criterio");
		         ls_fecha_rpta= request.getParameter("fecha_rpta");
		         ls_numero_referencia= request.getParameter("numero_referencia");
		         
		        log.info("El ls_naturaleza" + ls_naturaleza);
		        log.info("El ls_tipodoc" + ls_tipodoc);
		        log.info("El ls_estado" + ls_estado);
		        log.info("El ls_usuario" + ls_usuario);
		        log.info("El ls_sle_fecha" + ls_sle_fecha);
		        log.info("El ls_codigo_oficina >> " + ls_codigo_oficina);
		        log.info("El ls_estado_criterio_usuario" + ls_estado_criterio_usuario);
		        log.info("El ls_codigo_tipo" + ls_codigo_tipo);
		        log.info("El ls_procedencia" + ls_procedencia);
		        log.info("El ls_firmadopor" + ls_firmadopor);
		        log.info("El ls_estado_criterio >> " + ls_estado_criterio);
		        log.info("El ls_fecha_rpta" + ls_fecha_rpta);
		        log.info("El ls_numero_referencia" + ls_numero_referencia);
		       
		       IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		       // IReportesDAO daoIReportesDAO = new SqlReportesDAO();
		        
		       /**
		        * Agregado Jazanero
		        */
		        
		        Collection  rs_reporte = daoIUsuarioDAO.reportes_criterio_buscar
    		        (cnx, ls_naturaleza, ls_tipodoc, ls_estado, ls_usuario, ls_sle_fecha, ls_codigo_oficina,
    		        		ls_estado_criterio_usuario, ls_codigo_tipo, ls_procedencia, ls_firmadopor, 
    		        		ls_estado_criterio, ls_fecha_rpta, ls_numero_referencia);
        			
		      // Collection  rs_reporte = null;		        		        
				        
		        request.setAttribute("cantidad_reporte", rs_reporte.size());
		        request.setAttribute("rs_reporte",rs_reporte);

	
		            return (mapping.findForward("reportes"));
		        		        
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
	
	
}
