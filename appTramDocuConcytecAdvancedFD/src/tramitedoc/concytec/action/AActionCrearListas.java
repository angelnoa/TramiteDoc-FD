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

public class AActionCrearListas extends BaseAjaxAction {
	protected  Log log = LogFactory.getLog(AActionCrearListas.class);
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
        int li_operacion = 0;
        log.info("Cambia es XXXXX"+ cambia);
        String pOficina = request.getParameter("codigo_oficina");
        String codigo_oficina = (String) request.getParameter("ls_texto");
        
        String variableInput2= request.getParameter("term");
        variableInput2=(variableInput2==null)? "":variableInput2;
        log.info("variableInput2 "+variableInput2);
        
        String texto = "";
        String ls_operacion = "";
        String accion = "";
        String tipoNaturaleza="";
        String ejecuta =null;
        String ls_codigo=null;
        int codigo=0;
        String tipo_busqueda="";
        
        if (!esVacio(cambia)) {
        	
        	IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			
        	
        	if (cambia.equals("ofiInterno")){
                li_operacion = 1;
            }else 
            	if (cambia.equals("ofi")){
    	            li_operacion = 2;
    	    } else 
    	    	if (cambia.equals("dirigidoa")){
                li_operacion = 3;
    	    }  else 
    	    	if (cambia.equals("nombreoficina")){
                li_operacion = 4;
    	    }  else 
    	    	if (cambia.equals("eliminarnombreoficina")){
                li_operacion = 5;
    	    } else 
    	    	if (cambia.equals("codAccion")){
                li_operacion = 6;
    	    } else 
    	    	if (cambia.equals("codAccionffff")){
                li_operacion = 7;
    	    }else 
    	    	if (cambia.equals("setear")){
    	    	li_operacion = 8;
    	    }else 
    	    	if (cambia.equals("setear2")){
    	    	li_operacion = 9;
    	    }else 
    	    	if (cambia.equals("setearcero")){
    	    	li_operacion = 10;
    	    }else 
    	    	if (cambia.equals("setearceroproy")){
    	    	li_operacion = 11;
    	    }else 
    	    	if (cambia.equals("ruta")){
    	    	li_operacion = 12;
    	    }else 
    	    	if (cambia.equals("per")){
    	    	li_operacion = 13;
    	    }else 
    	    	if (cambia.equals("procedenciaa")){
    	    	li_operacion = 14;
    	    }else 
    	    	if (cambia.equals("seteaModifica")){
        	    	li_operacion = 15;
        	    }
        	
        	switch(li_operacion){
          	 case 1:

             	log.info("Cambia es igual a ofiInterno");
                 
                 log.info("pOficina es "+ pOficina);
                
                 //return new AjaxXmlBuilder().addItems(daoIUsuarioDAO.of_lista_personas_oficinas(pOficina),"nombre_personal", "codigo_personal").toString();
                 PrintWriter out = response.getWriter();
                 //out.println("<option value='0'>Seleccione producto</option>");
                 
                 Collection ListaPersonasOficinas = new ArrayList();
                 ListaPersonasOficinas = daoIUsuarioDAO.of_lista_personas_oficinas(pOficina);
                 
     			if(ListaPersonasOficinas!=null && ListaPersonasOficinas.size()>0){
 	  				Iterator it=ListaPersonasOficinas.iterator();
 	  				while(it.hasNext()){
 	  					BPersonal BPersonalVO =(BPersonal)it.next();
 	  					out.printf("<option value='%1s'>%2s</option>", BPersonalVO.getCodigo_personal(), BPersonalVO.getNombre_personal());		  					
 	  				}
 	  						  				
 	  			}
                 out.close();
             
        		break;
          	 case 2:

             	log.info("Cambia es igual a ofi");
                 //String pOficina = request.getParameter("codigo_oficina");
                 log.info("pOficina es "+ pOficina);
                
                 return new AjaxXmlBuilder().addItems(daoIUsuarioDAO.of_lista_personas_oficinas(pOficina),"nombre_personal", "codigo_personal").toString();
                
             
          		//break;
          	case 3:

            	log.info("Cambia es igual a dirigidoa");
                texto = (String) request.getParameter("ls_texto");
                ls_operacion = request.getParameter("operacion");
                accion = request.getParameter("accion");
                ejecuta=request.getParameter("ls_ejecuta");
                ejecuta = (ejecuta==null)? "":ejecuta.trim();
                ls_codigo = request.getParameter("ls_codigo");
                tipo_busqueda = request.getParameter("tipo_busqueda");
                
                if (ejecuta.equals("modificanombre")){
                	System.out.println("Modifique....");
                	daoIUsuarioDAO.of_modificar_procedencia(ls_codigo.trim(),texto.trim().toUpperCase(),ls_operacion.trim());
                }
                if (ejecuta.equals("eliminanombre")){
                	System.out.println("Elimina....");
                	daoIUsuarioDAO.of_eliminar_procedencia(ls_codigo.trim());
                }
                if (ejecuta.equals("agreganombre")){
                	System.out.println("Agrega....daoIUsuarioDAO.of_ingresar_procedencia("+texto.trim().toUpperCase()+","+ls_operacion.trim()+");");
                	daoIUsuarioDAO.of_ingresar_procedencia(texto.trim().toUpperCase(),ls_operacion.trim());
                }
                if (ejecuta.equals("limpialista")){
                	PrintWriter outz2 = response.getWriter();
                	outz2.printf("");
                	outz2.close();
                }
                tipoNaturaleza="";
               // log.info("texto es "+ texto);
				
				PrintWriter outz = response.getWriter();
    				/****if ((texto != null) && (ls_operacion != null)) {
    						Collection listaFrameBusqueda = new ArrayList();
    						log.info("daoIUsuarioDAO.of_procedencia_listar("+ls_operacion+","+texto+");");
    						listaFrameBusqueda = daoIUsuarioDAO.of_procedencia_listar(ls_operacion,texto);
    						//String sel="Seleccionar";
    						if(listaFrameBusqueda!=null && listaFrameBusqueda.size()>0){
    			  				Iterator it=listaFrameBusqueda.iterator();
    			  				outz.printf("<table  rules='all' border='1' ><th width='129' height='20' align='center' valign='middle' bgcolor='265ca6'><font color='FFFFFF'>Accion</font></th><th width='250' height='20' align='center' valign='middle' bgcolor='265ca6'><font color='FFFFFF'>Descripcion</font></th><th width='100' height='20' align='center' valign='middle' bgcolor='265ca6'><font color='FFFFFF'>Tipo</font></th><th  width='200px' height='20' colspan='2' align='center' valign='middle' bgcolor='265ca6'><font color='FFFFFF'>Acción</font></th></tr>");
    			  				int i =0;
    			  				while(it.hasNext()){
    			  					i++;
		  							BPersona BPersonaVO =(BPersona)it.next();
		  							tipoNaturaleza = (BPersonaVO.getTipo().equals("J"))? "JURIDICA":"NATURAL";
		  							if(i%2==0)
		  								outz.printf("<tr bgcolor='F0F5FB'><td height='25' align='center' valign='middle'><A  target='_parent' href='ValidaPaginas.do?tipo=seleccion_dir&codigo_persona_dir=%1s&nombre_persona_dir=%2s&tipodoc_dir=%3s&operacion=D&accion_p=%4s' >Seleccion</A></td ><td align='left' valign='middle' >&nbsp;&nbsp;&nbsp;&nbsp; %5s</td><td align='center' valign='middle'>%6s</td>  <td height='25' align='center' valign='middle'><A  target='_parent' href='DetalleFrame.do?tipo=dirigidos&codigo_persona=%7s&nombre_persona=%8s&tipodoc=%9s&operacion=M&accion=%10s' >Modificar</A></td >  <td height='25' align='center' valign='middle'><A  target='_parent' href='DetalleFrame.do?tipo=dirigidos&codigo_persona=%11s&nombre_persona=%12s&tipodoc=%13s&operacion=E&accion=%14s' >Eliminar</A></td > </tr>",BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getNombre_persona(),tipoNaturaleza,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion);
		  							else
		  								outz.printf("<tr bgcolor='81DAF5'><td height='25' align='center' valign='middle'><A  target='_parent' href='ValidaPaginas.do?tipo=seleccion_dir&codigo_persona_dir=%1s&nombre_persona_dir=%2s&tipodoc_dir=%3s&operacion=D&accion_p=%4s' >Seleccion</A></td ><td align='left' valign='middle' >&nbsp;&nbsp;&nbsp;&nbsp; %5s</td><td align='center' valign='middle'>%6s</td>  <td height='25' align='center' valign='middle'><A  target='_parent' href='DetalleFrame.do?tipo=dirigidos&codigo_persona=%7s&nombre_persona=%8s&tipodoc=%9s&operacion=M&accion=%10s' >Modificar</A></td >  <td height='25' align='center' valign='middle'><A  target='_parent' href='DetalleFrame.do?tipo=dirigidos&codigo_persona=%11s&nombre_persona=%12s&tipodoc=%13s&operacion=E&accion=%14s' >Eliminar</A></td > </tr>",BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getNombre_persona(),tipoNaturaleza,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion);
    			  				}
    			  				outz.printf("</table>");
    						}	
			         }****/
				if (tipo_busqueda.equals("interno")){
					if ((texto != null) && (ls_operacion != null)) {
    					Collection listaFrameBusqueda = new ArrayList();
    					log.info("daoIUsuarioDAO.of_procedencia_interno_listar("+texto+");");
    					listaFrameBusqueda = daoIUsuarioDAO.of_procedencia_interno_listar(texto);
    					if(listaFrameBusqueda!=null && listaFrameBusqueda.size()>0){
    		  				Iterator it=listaFrameBusqueda.iterator();
    		  				outz.printf("<table  cellpadding='0' cellspacing='0'><thead><tr><th width='129' >Accion</th><th width='250' >Nombres y apellidos</th><th width='100' >Oficina</th><tbody>");
    		  				int i =0;
    		  				while(it.hasNext()){
    		  					i++;
      							BPersona BPersonaVO =(BPersona)it.next();
      							tipoNaturaleza = (BPersonaVO.getTipo().equals("J"))? "JURIDICA":"NATURAL";
      							
      							if(i%2==0)
      								outz.printf("<tr class='odd'><td height='25' align='center' valign='middle'><A  target='_parent' href='ValidaPaginas.do?tipo=seleccion_dir&codigo_persona_dir=%1s&nombre_persona_dir=%2s&tipodoc_dir=%3s&operacion=D&accion_p=%4s' >Seleccion</A></td ><td align='left' valign='middle' >&nbsp;&nbsp;&nbsp;&nbsp; %5s</td><td align='center' valign='middle'>%6s</td> </tr>",BPersonaVO.getCodigo_persona().trim(),BPersonaVO.getNombre_persona().trim(),BPersonaVO.getTipo().trim(),accion.trim(),BPersonaVO.getNombre_persona().trim(),BPersonaVO.getOficina_pertenece().trim(),BPersonaVO.getCodigo_persona().trim(),BPersonaVO.getNombre_persona().trim(),BPersonaVO.getTipo().trim(),accion.trim(),BPersonaVO.getCodigo_persona().trim(),BPersonaVO.getNombre_persona().trim(),BPersonaVO.getTipo().trim(),accion.trim());
      							else
      								outz.printf("<tr  bgcolor='ffffff'><td height='25' align='center' valign='middle'><A  target='_parent' href='ValidaPaginas.do?tipo=seleccion_dir&codigo_persona_dir=%1s&nombre_persona_dir=%2s&tipodoc_dir=%3s&operacion=D&accion_p=%4s' >Seleccion</A></td ><td align='left' valign='middle' >&nbsp;&nbsp;&nbsp;&nbsp; %5s</td><td align='center' valign='middle'>%6s</td> </tr>",BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getNombre_persona(),BPersonaVO.getOficina_pertenece().trim(),BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion);
      							
    		  				}
    		  				outz.printf("</tbody></table>");
    					}	
    				}
                }
				if (tipo_busqueda.equals("externo")){
					if ((texto != null) && (ls_operacion != null)) {
    					Collection listaFrameBusqueda = new ArrayList();
    					log.info("daoIUsuarioDAO.of_procedencia_listar("+ls_operacion+","+texto+");");
    					listaFrameBusqueda = daoIUsuarioDAO.of_procedencia_listar(ls_operacion,texto);
    					if(listaFrameBusqueda!=null && listaFrameBusqueda.size()>0){
    		  				Iterator it=listaFrameBusqueda.iterator();
    		  				outz.printf("<table  cellpadding='0' cellspacing='0'><thead><tr><th width='129' >Accion</th><th width='250' >Descripcion</th><th width='100' >Tipo</th><th  width='200px' colspan='2' >Acción</th></tr></thead><tbody>");
    		  				int i =0;
    		  				while(it.hasNext()){
    		  					i++;
      							BPersona BPersonaVO =(BPersona)it.next();
      							tipoNaturaleza = (BPersonaVO.getTipo().equals("J"))? "JURIDICA":"NATURAL";
      							
      							if(i%2==0)
      								outz.printf("<tr class='odd'><td height='25' align='center' valign='middle'><A  target='_parent' href='ValidaPaginas.do?tipo=seleccion_dir&codigo_persona_dir=%1s&nombre_persona_dir=%2s&tipodoc_dir=%3s&operacion=D&accion_p=%4s' >Seleccion</A></td ><td align='left' valign='middle' >&nbsp;&nbsp;&nbsp;&nbsp; %5s</td><td align='center' valign='middle'>%6s</td>  <td height='25' align='center' valign='middle'><A href='javascript:modifica_lista(%7$s);'>Modificar</A></td >  <td height='25' align='center' valign='middle'><A  href='javascript:elimina_de_lista(%7$s);' >Eliminar</A></td > </tr>",BPersonaVO.getCodigo_persona().trim(),BPersonaVO.getNombre_persona().trim(),BPersonaVO.getTipo().trim(),accion.trim(),BPersonaVO.getNombre_persona().trim(),tipoNaturaleza.trim(),BPersonaVO.getCodigo_persona().trim(),BPersonaVO.getNombre_persona().trim(),BPersonaVO.getTipo().trim(),accion.trim(),BPersonaVO.getCodigo_persona().trim(),BPersonaVO.getNombre_persona().trim(),BPersonaVO.getTipo().trim(),accion.trim());
      							else
      								outz.printf("<tr  bgcolor='ffffff'><td height='25' align='center' valign='middle'><A  target='_parent' href='ValidaPaginas.do?tipo=seleccion_dir&codigo_persona_dir=%1s&nombre_persona_dir=%2s&tipodoc_dir=%3s&operacion=D&accion_p=%4s' >Seleccion</A></td ><td align='left' valign='middle' >&nbsp;&nbsp;&nbsp;&nbsp; %5s</td><td align='center' valign='middle'>%6s</td>  <td height='25' align='center' valign='middle'><A href='javascript:modifica_lista(%7$s);'>Modificar</A></td >  <td height='25' align='center' valign='middle'><A  href='javascript:elimina_de_lista(%7$s);' >Eliminar</A></td > </tr>",BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getNombre_persona(),tipoNaturaleza,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion);
      							
    		  				}
    		  				outz.printf("</tbody></table>");
    					}	
    				}
				}
				if (tipo_busqueda.equals("limpialista")){
					outz.printf("&nbsp;");
				}
		        outz.close();

        		break;
        		
          	 case 4:
             	
             	log.info("Cambia es igual a nombreoficina");
                 //String codigo_oficina = (String) request.getParameter("ls_texto");
                 String nuevo_nombre_sello_oficina = (String) session.getAttribute("nuevo_nombre_sello_oficina");
                 
                 log.info("codigo_oficina es "+ codigo_oficina);
 				
 				PrintWriter out1 = response.getWriter();
 					
     			String nombre_sello_oficina = service.of_nombre_archivo_sello_oficina(codigo_oficina);
     			nombre_sello_oficina =(nombre_sello_oficina==null)? "":nombre_sello_oficina;
     			
     			System.out.println("--"+nuevo_nombre_sello_oficina);
     			nuevo_nombre_sello_oficina =(nuevo_nombre_sello_oficina==null)? "":nuevo_nombre_sello_oficina;
     			if(nuevo_nombre_sello_oficina.equals("")){
     				out1.printf("Archivo cargado: <br>");
     				
     				if(nombre_sello_oficina.equals("")){
     					out1.printf("<font color='red' > NINGUNO </FONT> <br>");
     				}else{
     				out1.println(nombre_sello_oficina);
     				}
     				
     				out1.printf("<br><br> Usted no cargo ninguna imagen");
     				
     			}else{
     				out1.printf("Archivo cargado: <br>");
     				if(nombre_sello_oficina.equals("")){
     					out1.printf("<font color='red' > NINGUNO </FONT> <br>");
     				}else{
     				out1.println(nombre_sello_oficina);
     				}
     				
     				out1.printf("<br><br> Usted cargo nueva imagen: <br>");
     				out1.println(nuevo_nombre_sello_oficina);
     				out1.printf("<br><br> Presione GRABAR para guardar los cambios.");
     				
     				
     			}
     			
    	            out1.close();

          		break;
          	case 5:
          		

            	log.info("Cambia es igual a eliminarnombreoficina");
                
                
                log.info("codigo_oficina es "+ codigo_oficina);
				
				PrintWriter out3 = response.getWriter();
    			System.out.println("daoIUsuarioDAO.of_procedencia_listar("+cambia+","+codigo_oficina+");");
    						
    			service.delete_of_nombre_archivo_sello_oficina(codigo_oficina);
    			out3.printf("Archivo cargado: <br> ");
    			//out.println(nombre_sello_oficina);
   	            out3.close();

            
        		break;
          	 case 6:

             	log.info("Cambia es igual a codAccion");
             	String valor = (String) session.getAttribute("codigo_motivo_observacion");
             	valor = (valor==null)? "0":valor.trim();
             	//valor="3";
             	log.info("valor-> "+valor);
                 PrintWriter out4 = response.getWriter();
                 out4.println("<option value='0'>Seleccione motivo</option>");
                 
                 Collection rs_motivos = new ArrayList();
                 rs_motivos = daoIUsuarioDAO.of_lista_motivos();
                 
     			if(rs_motivos!=null && rs_motivos.size()>0){
 	  				Iterator it=rs_motivos.iterator();
 	  				while(it.hasNext()){
 	  					BMotivo BMotivoVO =(BMotivo)it.next();
 	  					if(valor.equals(BMotivoVO.getCodigo_motivo().trim())){
 	  						out4.printf("<option value='%1s' selected >%2s</option>", BMotivoVO.getCodigo_motivo(), BMotivoVO.getDescripcion_motivo());
 	  					}else{
 	  						out4.printf("<option value='%1s'>%2s</option>", BMotivoVO.getCodigo_motivo(), BMotivoVO.getDescripcion_motivo());
 	  					}
 	  				}
 	  						  				
 	  			}
                 out4.close();
             
          		break;
          	case 7:
          		String valorz = (String) session.getAttribute("contador");
            	valorz = (valorz==null)? "0":valorz;
            	System.out.println("entre "+valorz);
            	if(valorz.equals("1") || valorz.equals("2")){
            		session.setAttribute("verLista", valorz);
            	}else{
            		session.setAttribute("verLista", "0");
            	}
        		break;
          	 case 8:
          		session.setAttribute("verLista", "1");
           		session.setAttribute("contador", "1");
          		
          		break;
          	case 9:
          		session.setAttribute("verLista", "2");
           		session.setAttribute("contador", "2");
        		break;
          	 case 10:
          		session.setAttribute("verLista", "0");
           		session.setAttribute("contador", "0");
          		break;
          	case 11:
          		session.setAttribute("verLista", "0");
           		session.setAttribute("contador", "0");
        		break;
          	 case 12:
          		String pcod_ruta = request.getParameter("cod_ruta");
                log.info("Listando todos las rutas"+pcod_ruta);
                return new AjaxXmlBuilder().addItems(daoIUsuarioDAO.of_lista_modalidad_rutas(pcod_ruta),
				"nombre_modalidad", "cod_modalidad").toString();
          		//break;
          	case 13:
          		log.info("Cambia es igual a per");
                String codigo_personal = request.getParameter("codigo_personal");
                log.info("Codigo personal es "+ codigo_personal);
               
                return new AjaxXmlBuilder().addItem(daoIUsuarioDAO.of_abreviatura(codigo_personal),
				"abreviatura").toString();
                
        		//break;
                
          	case 14:

          		log.info("Cambia es igual a procedenciaa");
                texto = (String) request.getParameter("ls_texto");
                ls_operacion = request.getParameter("operacion");
                accion = request.getParameter("accion");
                ejecuta=request.getParameter("ls_ejecuta");
                ejecuta = (ejecuta==null)? "":ejecuta.trim();
                ls_codigo = request.getParameter("ls_codigo");
                
                if (ejecuta.equals("modificanombre")){
                	System.out.println("Modifique....");
                	daoIUsuarioDAO.of_modificar_procedencia(ls_codigo.trim(),texto.trim().toUpperCase(),ls_operacion.trim());
                }
                if (ejecuta.equals("eliminanombre")){
                	System.out.println("Elimina....");
                	daoIUsuarioDAO.of_eliminar_procedencia(ls_codigo.trim());
                }
                if (ejecuta.equals("agreganombre")){
                	System.out.println("Agrega....daoIUsuarioDAO.of_ingresar_procedencia("+texto.trim().toUpperCase()+","+ls_operacion.trim()+");");
                	daoIUsuarioDAO.of_ingresar_procedencia(texto.trim().toUpperCase(),ls_operacion.trim());
                }
                tipoNaturaleza="";
                log.info("texto es "+ texto);
				
				PrintWriter outx = response.getWriter();
    				
				if ((texto != null) && (ls_operacion != null)) {
					Collection listaFrameBusqueda = new ArrayList();
					log.info("daoIUsuarioDAO.of_procedencia_listar("+ls_operacion+","+texto+");");
					listaFrameBusqueda = daoIUsuarioDAO.of_procedencia_listar(ls_operacion,texto);
					if(listaFrameBusqueda!=null && listaFrameBusqueda.size()>0){
		  				Iterator it=listaFrameBusqueda.iterator();
		  				outx.printf("<table  cellpadding='0' cellspacing='0'><thead><tr><th width='129' >Accion</th><th width='250' >Descripcion</th><th width='100' >Tipo</th><th  width='200px' colspan='2' >Acción</th></tr></thead><tbody>");
		  				int i =0;
		  				while(it.hasNext()){
		  					i++;
  							BPersona BPersonaVO =(BPersona)it.next();
  							tipoNaturaleza = (BPersonaVO.getTipo().equals("J"))? "JURIDICA":"NATURAL";
  						//outx.printf("<tr class='odd'><td height='25' align='center' valign='middle'><A  target='_parent' href='ValidaPaginas.do?tipo=seleccion&codigo_persona=%1s&nombre_persona=%2s&tipodoc=%3s&operacion=T&accion_p=%4s' >Seleccion</A></td ><td align='left' valign='middle' >&nbsp;&nbsp;&nbsp;&nbsp; %5s</td><td align='center' valign='middle'>%6s</td>  <td height='25' align='center' valign='middle'><A  target='_parent' href='DetalleFrame.do?tipo=procedencia&codigo_persona=%7s&nombre_persona=%8s&tipodoc=%9s&operacion=M&accion=%10s' >Modificar</A></td >  <td height='25' align='center' valign='middle'><A  target='_parent' href='DetalleFrame.do?tipo=procedencia&codigo_persona=%11s&nombre_persona=%12s&tipodoc=%13s&operacion=E&accion=%14s' >Eliminar</A></td > </tr>",BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getNombre_persona(),tipoNaturaleza,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion);
  						//outx.printf("<tr  bgcolor='ffffff'><td height='25' align='center' valign='middle'><A  target='_parent' href='ValidaPaginas.do?tipo=seleccion&codigo_persona=%1s&nombre_persona=%2s&tipodoc=%3s&operacion=T&accion_p=%4s' >Seleccion</A></td ><td align='left' valign='middle' >&nbsp;&nbsp;&nbsp;&nbsp; %5s</td><td align='center' valign='middle'>%6s</td>  <td height='25' align='center' valign='middle'><A  target='_parent' href='DetalleFrame.do?tipo=procedencia&codigo_persona=%7s&nombre_persona=%8s&tipodoc=%9s&operacion=M&accion=%10s' >Modificar</A></td >  <td height='25' align='center' valign='middle'><A  target='_parent' href='DetalleFrame.do?tipo=procedencia&codigo_persona=%11s&nombre_persona=%12s&tipodoc=%13s&operacion=E&accion=%14s' >Eliminar</A></td > </tr>",BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getNombre_persona(),tipoNaturaleza,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion);
  							if(i%2==0)
  								outx.printf("<tr class='odd'><td height='25' align='center' valign='middle'><A  target='_parent' href='ValidaPaginas.do?tipo=seleccion&codigo_persona=%1s&nombre_persona=%2s&tipodoc=%3s&operacion=T&accion_p=%4s' >Seleccion</A></td ><td align='left' valign='middle' >&nbsp;&nbsp;&nbsp;&nbsp; %5s</td><td align='center' valign='middle'>%6s</td>  <td height='25' align='center' valign='middle'><A href='javascript:modifica_lista(%7$s);'>Modificar</A></td >  <td height='25' align='center' valign='middle'><A  href='javascript:elimina_de_lista(%7$s);' >Eliminar</A></td > </tr>",BPersonaVO.getCodigo_persona().trim(),BPersonaVO.getNombre_persona().trim(),BPersonaVO.getTipo().trim(),accion.trim(),BPersonaVO.getNombre_persona().trim(),tipoNaturaleza.trim(),BPersonaVO.getCodigo_persona().trim(),BPersonaVO.getNombre_persona().trim(),BPersonaVO.getTipo().trim(),accion.trim(),BPersonaVO.getCodigo_persona().trim(),BPersonaVO.getNombre_persona().trim(),BPersonaVO.getTipo().trim(),accion.trim());
  							else                                                                                                                                                                                                                                                                                                                                                                                                                                    
  								outx.printf("<tr  bgcolor='ffffff'><td height='25' align='center' valign='middle'><A  target='_parent' href='ValidaPaginas.do?tipo=seleccion&codigo_persona=%1s&nombre_persona=%2s&tipodoc=%3s&operacion=T&accion_p=%4s' >Seleccion</A></td ><td align='left' valign='middle' >&nbsp;&nbsp;&nbsp;&nbsp; %5s</td><td align='center' valign='middle'>%6s</td>  <td height='25' align='center' valign='middle'><A href='javascript:modifica_lista(%7$s);'>Modificar</A></td >  <td height='25' align='center' valign='middle'><A  href='javascript:elimina_de_lista(%7$s);' >Eliminar</A></td > </tr>",BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getNombre_persona(),tipoNaturaleza,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion);
		  				}
		  				outx.printf("</tbody></table>");
					}	
				}
		         outx.close();

        		break;
        		
        		
          	case 15:
          		log.info("Cambia es igual a seteaModifica");
                codigo =  Integer.parseInt(request.getParameter("ls_codigo"));
                log.info("codigo es "+ codigo);
                BPersona BPersonaVO= null;
				PrintWriter poutz = response.getWriter();
					
					log.info("daoIUsuarioDAO.of_busca_persona("+codigo+");");
					BPersonaVO = daoIUsuarioDAO.of_busca_persona(codigo);
										
					poutz.printf("<p><select name='sel_opcion_modal' id='sel_opcion_modal' class='input-medium' size='1'>");
					if(BPersonaVO.getTipo().trim().equals("N")){
						poutz.printf("<option value='N' selected >Persona Natural</option><option value='J'>Persona Juridica</option></select>");
					}else{
						poutz.printf("<option value='N'  >Persona Natural</option><option value='J' selected>Persona Juridica</option></select>");
					}
					
					poutz.printf("<input type='text' name='texto_modifica' id='texto_modifica'  style='width: 250px;' class='input' value='%s'/></p>",BPersonaVO.getNombre_persona().trim());

		         poutz.close();
        		break;
          	
        	}
            
        }
        return "";
    }

    static boolean esVacio(String cad) {
        return cad == null || cad.trim().equals("");
    }


}
