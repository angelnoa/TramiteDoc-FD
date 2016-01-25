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

public class AActionCrearListasDestinatario extends BaseAjaxAction {
	protected  Log log = LogFactory.getLog(AActionCrearListasDestinatario.class);
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
        log.info("Entre AActionCrearListasDestinatario");
        log.info("Cambia es XXXXX"+ cambia);
        String pOficina = request.getParameter("codigo_oficina");
        String codigo_oficina = (String) request.getParameter("ls_texto");
        String id_cab_upinternos = (String) request.getParameter("id_cab_upinternos");
        String estadop = (String) request.getParameter("estado");
    	BUsuario usuario_sistema = (BUsuario)session.getAttribute("usuario_sistema");
        String pcodigo_institucion = (String) request.getParameter("codigo_institucion");
        
        String codigo_padre = (String) request.getParameter("codigo_padre");
        log.info("Cambia a Institucion"+ pcodigo_institucion);
        log.info("Cambia a Codigo de Oficina"+ codigo_padre);
        String pnombre_usuario = (String) request.getParameter("name");
        String ppertenece_sede = (String) usuario_sistema.getSede();
       // if(ppertenece_sede.equals("1")){
      //  	ppertenece_sede=pcodigo_institucion;
       // }
        String ppertenece_a_ls = (String) request.getParameter("pertenece");
        String pexterno=(String)request.getParameter("externo_ls");
        
        String es_padre = (String)session.getAttribute("es_padre");
        //log.info("-->"+es_padre+"<--");
        
        int cont=0;
        
        if (!esVacio(cambia)) {
        	
        	IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			
        	
        	if (cambia.equals("ofiInterno")){
                li_operacion = 1;
        	}else 
            	if (cambia.equals("ofiInterno2")){
    	            li_operacion = 111;
    	    }else 
            	if (cambia.equals("ofi")){
    	            li_operacion = 2;
    	    } else 
    	    	if (cambia.equals("procedencia")){
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
    	    	if (cambia.equals("oficinasxsede")){
    	    	li_operacion = 17;
    	    }else 
        	    if (cambia.equals("oficinasxsedexgrupoxpadre")){
        	    li_operacion = 18;
    	    }else 
        	    if (cambia.equals("detalle")){
        	    li_operacion = 14;
    	    }else 
    	    	if (cambia.equals("institucion")){
        	    	li_operacion = 15;
        	    }else 
        	    	if (cambia.equals("nuevo_metodo")){
            	    	li_operacion = 16;
            	    }
        	
        	switch(li_operacion){
          	 case 1:

             	log.info("Cambia es igual a ofiInterno");
                 
                 log.info("pOficina es "+ pOficina);
                 pOficina=(pOficina==null)? "":pOficina;
                 log.info("pOficina es "+ pOficina);
                
                 //return new AjaxXmlBuilder().addItems(daoIUsuarioDAO.of_lista_personas_oficinas(pOficina),"nombre_personal", "codigo_personal").toString();
                 PrintWriter out = response.getWriter();
                 out.println("<option value='0'>Seleccione personal</option>");
                 
                 Collection ListaPersonasOficinas = new ArrayList();
                 
                 if(!pOficina.equals("") &&  pOficina.trim().length()>0){
                                  ListaPersonasOficinas = daoIUsuarioDAO.of_lista_personas_oficinas(pOficina);
                 }
                 
                cont=0;
     			if(ListaPersonasOficinas!=null && ListaPersonasOficinas.size()>0){
 	  				Iterator it=ListaPersonasOficinas.iterator();
 	  				while(it.hasNext()){
 	  					
 	  					BPersonal BPersonalVO =(BPersonal)it.next();
 	  					cont++;
 	  					if(cont==1){
 	  						out.printf("<option value='%1s' selected >%2s</option>", BPersonalVO.getCodigo_personal(), BPersonalVO.getNombre_personal());	
 	  					}else{
 	  						out.printf("<option value='%1s'>%2s</option>", BPersonalVO.getCodigo_personal(), BPersonalVO.getNombre_personal());	
 	  					}
 	  						  					
 	  				}
 	  						  				
 	  			}else{
 	  				
 	  			}
                 out.close();
             
        		break;
          	case 111:

             	log.info("Cambia es igual a ofiInterno2");
                 
                 log.info("pOficina es "+ pOficina);
                 pOficina=(pOficina==null)? "":pOficina;
                 log.info("pOficina es "+ pOficina);
                
                 //return new AjaxXmlBuilder().addItems(daoIUsuarioDAO.of_lista_personas_oficinas(pOficina),"nombre_personal", "codigo_personal").toString();
                 PrintWriter out21 = response.getWriter();
                 
                 
                 Collection ListaPersonasOficinas2 = new ArrayList();
                 
                 if(!pOficina.equals("") &&  pOficina.trim().length()>0){
                                  ListaPersonasOficinas2 = daoIUsuarioDAO.of_lista_personas_oficinas(pOficina);
                 }
                 
                cont=0;
     			if(ListaPersonasOficinas2!=null && ListaPersonasOficinas2.size()>0){
 	  				Iterator it=ListaPersonasOficinas2.iterator();
 	  				while(it.hasNext()){
 	  					
 	  					BPersonal BPersonalVO =(BPersonal)it.next();
 	  					cont++;
 	  					out21.println("<tr>");
 	  					out21.println("<td>");
 	  					out21.printf("<input  class=\"caja\" type=\"checkbox\" name=\"selectedItems_other\" value='%1s'></td>", BPersonalVO.getCodigo_personal());	
 	  					out21.println("<td class=\"texto_small\">");
 	  					out21.printf(" %1s </td>",BPersonalVO.getNombre_personal());
 	  					out21.println("</tr>");
 	  				}
 	  						  				
 	  			}else{
 	  				
 	  			}
     			out21.close();
             
        		break;
          	 case 2:

             	log.info("Cambia es igual a ofi");
                 //String pOficina = request.getParameter("codigo_oficina");
                 log.info("pOficina es "+ pOficina);
                
                 return new AjaxXmlBuilder().addItems(daoIUsuarioDAO.of_lista_personas_oficinas(pOficina),"nombre_personal", "codigo_personal").toString();
                
             
          		//break;
          	case 3:

            	log.info("Cambia es igual a procedencia");
                String texto = (String) request.getParameter("ls_texto");
                String ls_operacion = request.getParameter("operacion");
                String accion = request.getParameter("accion");
                
                log.info("texto es "+ texto);
				
				PrintWriter out2 = response.getWriter();
    				if ((texto != null) && (ls_operacion != null)) {
    						Collection listaFrameBusqueda = new ArrayList();
    						System.out.println("daoIUsuarioDAO.of_procedencia_listar("+ls_operacion+","+texto+");");
    						listaFrameBusqueda = daoIUsuarioDAO.of_procedencia_listar(ls_operacion,texto);
    						String sel="Seleccionar";
    						if(listaFrameBusqueda!=null && listaFrameBusqueda.size()>0){
    			  				Iterator it=listaFrameBusqueda.iterator();
    			  				out2.printf("<table  border='1' cellpadding='0' cellspacing='0'><thead><th width='129' height='20' align='center' valign='middle' bgcolor='265ca6'>Accion</th><th width='250' height='20' align='center' valign='middle' bgcolor='265ca6'>Descripcion</th><th width='100' height='20' align='center' valign='middle' bgcolor='265ca6'>Tipo</th></tr></thead><tbody>");
    			  				while(it.hasNext()){
		  							BPersona BPersonaVO =(BPersona)it.next();
    			  					out2.printf("<tr><td height='25' align='center' valign='middle'><A  target='_parent' href='ValidaPaginas.do?tipo=seleccion_dir&codigo_persona_dir=%1s&nombre_persona_dir=%2s&tipodoc_dir=%3s&operacion=D&accion_p=%4s' >Seleccion</A></td ><td align='left' valign='middle'>&nbsp;&nbsp;&nbsp;&nbsp; %5s</td><td align='center' valign='middle'>%6s</td></tr>",BPersonaVO.getCodigo_persona(),BPersonaVO.getNombre_persona(),BPersonaVO.getTipo(),accion,BPersonaVO.getNombre_persona(),BPersonaVO.getTipo());
    			  			    }
    			  				out2.printf("</tbody></table>");
    						}	
			         }
		         out2.close();

            
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

          	case 14:
          		PrintWriter out33 = response.getWriter();
          		id_cab_upinternos=(id_cab_upinternos==null)? "":id_cab_upinternos;
          		estadop=(estadop==null)? "":estadop;
          		          		
          		out33.printf("<table  border='1' cellpadding='0' cellspacing='0'><thead><th width='129' height='20' align='center' valign='middle' bgcolor='265ca6'>Oficina</th><th width='250' height='20' align='center' valign='middle' bgcolor='265ca6'>Estado</th></tr></thead><tbody>");
          		Collection listaEstadosDocumento = new ArrayList();
				System.out.println("service.of_mostrar_estados_documento("+id_cab_upinternos+","+estadop+");");
				
				listaEstadosDocumento = service.of_mostrar_estados_documento(Integer.parseInt(id_cab_upinternos),estadop);
				if(listaEstadosDocumento!=null && listaEstadosDocumento.size()>0){
 	  				Iterator it=listaEstadosDocumento.iterator();
 	  				while(it.hasNext()){
 	  					BArchivo BArchivoVO =(BArchivo)it.next();
 	  					if(BArchivoVO.getEstado_usuario_fin().equals("A")){
 	  						out33.printf("<tr><td>%1s</td><td><font color='red' >POR FIRMAR</font></td></tr>", BArchivoVO.getNombre_archivo());
 	  					}else{
 	  						out33.printf("<tr><td>%1s</td><td><font color='green' >FIRMADO</font></td></tr>", BArchivoVO.getNombre_archivo());
 	  					}
 	  							  					
 	  				}
 	  						  				
 	  			}
				out33.printf("<tbody></table>");
          		out33.close();
        		break;
        		
          	case 15:
          		log.info("Cambia es igual a institucion");
                log.info("pcodigo_institucion es "+ pcodigo_institucion);
                log.info("ppertenece_a_ls "+ppertenece_a_ls);
                
                pexterno=(pexterno==null)?"I":pexterno;
                log.info("pexterno es "+pexterno);
                
                PrintWriter outf = response.getWriter();
                //outf.println("<option value='0'>Seleccione Oficina</option>");
                
                Collection ListaOficinas = new ArrayList();
                ListaOficinas = daoIUsuarioDAO.of_lista_oficinas_institucion(pcodigo_institucion,(String) session.getAttribute("grupo_oficina"),true);
                
                int conta=0;
    			if(ListaOficinas!=null && ListaOficinas.size()>0){
	  				Iterator it=ListaOficinas.iterator();
	  				while(it.hasNext()){
	  					BOficinas BOficinasVO =(BOficinas)it.next();
	  					conta++;
	  					if(ppertenece_a_ls.equals("2") && pcodigo_institucion.equals("3") ){
	  					outf.printf("<option value='%1s'>%2s</option>", BOficinasVO.getCodigo_oficina(), BOficinasVO.getSiglas_oficina()+" - "+BOficinasVO.getDescripcion_oficina());
	  					}
	  					if(ppertenece_a_ls.equals("2") && pcodigo_institucion.equals("2")){
	  						
		  					outf.printf("<option value='%1s'>%2s</option>", BOficinasVO.getCodigo_oficina(), BOficinasVO.getSiglas_oficina()+" - "+BOficinasVO.getDescripcion_oficina());
	  					}
	  					if(ppertenece_a_ls.equals("3") && pcodigo_institucion.equals("3")){
	  						outf.printf("<option value='%1s'>%2s</option>", BOficinasVO.getCodigo_oficina(), BOficinasVO.getSiglas_oficina()+" - "+BOficinasVO.getDescripcion_oficina());
	  					}
	  					if(ppertenece_a_ls.equals("3") && pcodigo_institucion.equals ("2")){
	  						outf.printf("<option value='%1s'>%2s</option>", BOficinasVO.getCodigo_oficina(), BOficinasVO.getSiglas_oficina()+" - "+BOficinasVO.getDescripcion_oficina());
	  					}
	  					
	  				}
	  						  				
	  			}
    			outf.close();
        		break;
        		
          	case 16:
          		log.info("Cambia es igual a nuevo metodo para obtener oficinas");
                log.info("pcodigo_institucion es "+ pcodigo_institucion);
                log.info("Nombre de usuario "+pnombre_usuario);
                
                PrintWriter outn = response.getWriter();
                outn.println("<option value='0'>Seleccione Oficina</option>");
                
                Collection ListaOficinasNuevas = new ArrayList();
                ListaOficinasNuevas = daoIUsuarioDAO.of_lista_oficinas_institucion(pcodigo_institucion,pnombre_usuario);
                cont=0;
                
    			if(ListaOficinasNuevas!=null && ListaOficinasNuevas.size()>0){
	  				Iterator it=ListaOficinasNuevas.iterator();
	  				while(it.hasNext()){
	  					BOficinas BOficinasVO =(BOficinas)it.next();
	  					cont++;
 	  					if(cont==1){
 	  						outn.printf("<option value='%1s' selected >%2s</option>", BOficinasVO.getCodigo_oficina(), BOficinasVO.getSiglas_oficina()+" - "+BOficinasVO.getDescripcion_oficina());
 	  					}else{
 	  						outn.printf("<option value='%1s'>%2s</option>", BOficinasVO.getCodigo_oficina(), BOficinasVO.getSiglas_oficina()+" - "+BOficinasVO.getDescripcion_oficina());
 	  					}
	  							  					
	  				}
	  						  				
	  			}
    			outn.close();
        		break;
          	case 17:
          		log.info("ADMIN PERSONAL - VER OFICINAS");
                log.info("pcodigo_institucion es "+ pcodigo_institucion);
               
                
                PrintWriter outjm = response.getWriter();
                
                
              Collection ListaOficinasjm = new ArrayList();
              ListaOficinasjm = daoIUsuarioDAO.of_lista_oficinas_institucion(pcodigo_institucion,true);
                cont=0;
                
    			if(ListaOficinasjm!=null && ListaOficinasjm.size()>0){
	  				Iterator it=ListaOficinasjm.iterator();
	  				while(it.hasNext()){
	  					BOficinas BOficinasVO =(BOficinas)it.next();
	  					cont++;
 	  					if(cont==1){
 	  						outjm.printf("<option value='%1s' selected >%2s</option>", BOficinasVO.getCodigo_oficina(), BOficinasVO.getDescripcion_oficina());
 	  					}else{
 	  						outjm.printf("<option value='%1s'>%2s</option>", BOficinasVO.getCodigo_oficina(), BOficinasVO.getDescripcion_oficina());
 	  					}
	  							  					
	  				}
	  						  				
	  			}
    			//outn.close();
        		break;
          	case 18:
          		log.info("ADMIN PERSONAL - VER OFICINAS SELECCIONADA");
                log.info("pcodigo_oficina es "+ codigo_padre);
                
               
                
                PrintWriter outjs = response.getWriter();
                
                
              Collection ListaOficinasjs = new ArrayList();
              ListaOficinasjs = daoIUsuarioDAO.of_lista_oficinas_institucion(pcodigo_institucion,true);
                cont=0;
                
    			if(ListaOficinasjs!=null && ListaOficinasjs.size()>0){
	  				Iterator it=ListaOficinasjs.iterator();
	  				while(it.hasNext()){
	  					BOficinas BOficinasVO =(BOficinas)it.next();
	  					
 	  					if(BOficinasVO.getCodigo_oficina().equals(codigo_padre)){
 	  						outjs.printf("<option value='%1s' selected >%2s</option>", BOficinasVO.getCodigo_oficina(), BOficinasVO.getDescripcion_oficina());
 	  					}else{
 	  						outjs.printf("<option value='%1s'>%2s</option>", BOficinasVO.getCodigo_oficina(), BOficinasVO.getDescripcion_oficina());
 	  					}
	  							  					
	  				}
	  						  				
	  			}
    			//outn.close();
        		break;
        	}
            
        }
        return "";
    }

    static boolean esVacio(String cad) {
        return cad == null || cad.trim().equals("");
    }


}