package tramitedoc.concytec.action;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BMesaPartes;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.util.ValidaSessionAction;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionConsultaDocumento extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
		 HttpSession session = request.getSession(false);        

	        /*Verificar si la sesion se perdio*/
	        if (session==null){
	        	
	        	return (mapping.findForward("expiracion"));
	        	
	        }   
		
	        int li_retorno=0;
	        String ls_operacion=null;
	        String documento=null;
	        String secuencia=null;
	        String oficina=null;
	        String motivo=null;
	        String codigo_oficina="";
	        
	        
	        ls_operacion = request.getParameter("tipo");
	        oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
	        
	        //motivo = request.getParameter("motivo");
	        
	        System.out.println("El ls_operacion " + ls_operacion);
	        
	        
		try {
			
			Connection cnx = getConnection(request, "principal");
			System.out.println("El cnx  ==> " + cnx);
			
			 session.removeAttribute("detalleConsulta");
			 
			IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			
			session.removeAttribute("detalleConsulta");
			
			 	if(ls_operacion.equals("mesadepartes")){
			 		
			 		System.out.println("Dentro de Mesa de partes ");
			 		
			 		documento = request.getParameter("documento");
			        secuencia = request.getParameter("secuencia");
			        
			 		BMesaPartes detalleConsulta=daoIUsuarioDAO.of_detalle_documento(cnx,oficina,documento,secuencia);
			          
					System.out.println("El detalleConsulta " + detalleConsulta);
					
			            session.setAttribute("detalleConsulta",detalleConsulta);
			            
			            return (mapping.findForward("mesadepartes"));
			            
			 	}else if(ls_operacion.equals("reportes")){
			 		
			 		//session.removeAttribute("detalleConsulta");
			 		
			 		//String buscarframe="R";
			 		System.out.println("Dentro de Reportes ");
			 		System.out.println("El oficina reportes detalle consulta" + oficina);
			 		
			 		documento = request.getParameter("documento");
			        secuencia = request.getParameter("secuencia");
			        codigo_oficina = request.getParameter("codigo_oficina");
			        
			        System.out.println("El documento " + documento);
			        System.out.println("El secuencia " + secuencia);
			        System.out.println("El codigo_oficina " + codigo_oficina);
			        
			 		BMesaPartes detalleConsulta=daoIUsuarioDAO.of_detalle_documento(cnx,codigo_oficina,documento,secuencia);
			          
					System.out.println("El detalleConsulta " + detalleConsulta);
					
			            session.setAttribute("detalleConsulta",detalleConsulta);
			            
			            
			            
			            return (mapping.findForward("reportes"));
			            
			 	}
			 	else if(ls_operacion.equals("reporteslinea")){
			 		
			 		//session.removeAttribute("detalleConsulta");
			 		
			 		//String buscarframe="R";
			 		System.out.println("Dentro de Reportes ");
			 		System.out.println("El oficina reportes detalle consulta" + oficina);
			 		
			 		documento = request.getParameter("documento");
			        secuencia = request.getParameter("secuencia");
			        codigo_oficina = request.getParameter("codigo_oficina");
			        
			        System.out.println("El documento " + documento);
			        System.out.println("El secuencia " + secuencia);
			        System.out.println("El codigo_oficina " + codigo_oficina);
			        
			 		BMesaPartes detalleConsulta=daoIUsuarioDAO.of_detalle_documento(cnx,codigo_oficina,documento,secuencia);
			          
					System.out.println("El detalleConsulta " + detalleConsulta);
					
			            session.setAttribute("detalleConsulta",detalleConsulta);
			            
			            
			            
			            return (mapping.findForward("reporteslinea"));
			            
			 	}
			 	
			 	else if(ls_operacion.equals("reportseguimiento")){
			 		
			 		//session.removeAttribute("detalleConsulta");
			 		
			 		//String buscarframe="R";
			 		System.out.println("Dentro de Reportes Seguimiento ");
			 		
			 		String ls_documento   = String.valueOf(session.getAttribute("documento"));
			 		String ls_secuencia   = String.valueOf(session.getAttribute("secuencia"));
			 		String ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
			 		
			        System.out.println("El ls_documento " + ls_documento);
			        System.out.println("El ls_secuencia " + ls_secuencia);
			        System.out.println("El ls_oficina " + ls_oficina);
			        
			 		BMesaPartes detalleConsulta=daoIUsuarioDAO.of_detalle_documento(cnx,ls_oficina,ls_documento,ls_secuencia);
			          
					System.out.println("El detalleConsulta " + detalleConsulta);
					
			        session.setAttribute("detalleConsulta",detalleConsulta);
			      
			        return (mapping.findForward("reportes"));
			            
			 	}
	            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
}
