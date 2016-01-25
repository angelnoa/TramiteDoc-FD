package tramitedoc.concytec.action;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import tramitedoc.concytec.bean.BOficinas;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.form.FFormDocumentoInterno;
import tramitedoc.concytec.form.FFormMantDocumento;
import tramitedoc.concytec.util.IConstante;

public class AActionMoverArchivoAAdjunto extends Action {
	protected  Log log = LogFactory.getLog(AActionMoverArchivoAAdjunto.class);
	  public ActionForward execute(ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response)
			    throws Exception
			  {
		  		log.info("Entry AActionMoverArchivoAAdjunto... ");
		  		ActionMessages mensajes = new ActionMessages();
		  		response.setContentType("application/pdf");
		  		response.setCharacterEncoding("UTF-8");
		  		request.setCharacterEncoding("UTF-8");
		  		
		  		 HttpSession session = request.getSession(false);        

			        /*Verificar si la sesion se perdio*/
			        if (session==null){
			        	
			        	return (mapping.findForward("expiracion"));
			        		
			        }   
			        FFormMantDocumento fformDocumentoInterno = (FFormMantDocumento) form;	
			        log.info("asuntoooooooooooo: " +fformDocumentoInterno.getAsunto_documento() );
		  		
			        /*	DocumentosInternosService service = new DocumentosInternosServiceImplement();	
		  		FFormDocumentoInterno fformDocumentoInterno = (FFormDocumentoInterno) form;	
		  		
		  		
		  		Image img1 = null;
		  		FormFile fileSignature = fformDocumentoInterno.getFile();

		  		if(fileSignature != null && fileSignature.getFileName().length()>0){
		              log.info("Entry..")  ;
		              log.info("file name: " +fileSignature.getFileName())  ;
		              java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(fileSignature.getFileData());
				  	  img1 = Image.getInstance(awtImage, null);
		         }
		  		 
		  		 
		  		String codigos_copia_oficina_destino[]=fformDocumentoInterno.getCopia_oficinas_destino();		  		
		  		StringBuffer codigos_copias_oficinas =new StringBuffer();		  		
		  		if(codigos_copia_oficina_destino!=null && codigos_copia_oficina_destino.length>0 ){
		  		 for (int   i = 0;   i < codigos_copia_oficina_destino.length;   i++) {
		  			codigos_copias_oficinas.append(codigos_copia_oficina_destino[i]+",");					
				}
		  		 
		  		log.info("codigos oficinas destinos: " +codigos_copias_oficinas.toString().substring(0, codigos_copias_oficinas.length()-1) );
		  		fformDocumentoInterno.getDocumentoInterno().setCodigos_oficinas_destinos_copias(codigos_copias_oficinas.toString().substring(0, codigos_copias_oficinas.length()-1));
		  		
		  		}
		  		
		  		
		  		ArrayList<BOficinas> areasAdministrativasList =	 (ArrayList<BOficinas>) request.getSession().getAttribute("areasAdministrativasList");
		  		 
		  		log.info("Tamanio de colleccion areasAdministrativasList: "+ areasAdministrativasList.size());
		  		ArrayList<String>  siglasOficinasCopias= new ArrayList<String>();
		  		if(codigos_copia_oficina_destino !=null && codigos_copia_oficina_destino.length>0){
		  		 obtener_siglas_oficina(codigos_copia_oficina_destino,areasAdministrativasList, siglasOficinasCopias);
		  		 }
		  		log.info("Tamanio de colleccion siglasOficinasCopias: "+ siglasOficinasCopias.size());
		  		
		  		log.info("personassss" +fformDocumentoInterno.getPersonas());
		  		
		  		String cargo_persona_destinatario=service.getCargo(fformDocumentoInterno.getPersonas());
		  		String dirigido_a=service.getNombre_Personal(fformDocumentoInterno.getPersonas());
		  		fformDocumentoInterno.setDirigido_a(dirigido_a);
		  		log.info("***********************");
		  		log.info("A: " +fformDocumentoInterno.getCodigo_oficina());
		  		log.info("B: " +fformDocumentoInterno.getCodigo_oficina_pertenece());
		  		log.info("***********************");
		  		String nombre_oficina_destino=obtener_siglas_oficina(fformDocumentoInterno.getCodigo_oficina(),areasAdministrativasList);
		  		
		  		
		  		fformDocumentoInterno.setCargo_persona_destinatario(cargo_persona_destinatario);
		  		String nombreSelloOficina= service.getNombreSelloOficina(fformDocumentoInterno.getCodigo_oficina_pertenece());
		  		String nombreOficialAnio=service.getNombreOficialAnio();
		  		fformDocumentoInterno.setNombre_oficial_anio(nombreOficialAnio);
		  		
		  		fformDocumentoInterno.setNombreSelloOficina(nombreSelloOficina);
		  		
		  		log.info("nombre sello de oficina: " +fformDocumentoInterno.getNombreSelloOficina());
		  		int numero_doc=0;;
		  		PDFcreator pdf = new PDFcreator();
		  		//Determinar segun el tipo de documento y tambien segun la oficina el nombre del documento
		  		String nombreOficina=service.getSiglasOficina(fformDocumentoInterno.getCodigo_oficina_pertenece());
		  		String filename="";	
		  		if(fformDocumentoInterno.getCodigo_documento_interno() ==null){ //se trata documento nuevo
		  
		  		numero_doc=service.getSiguienteNumeroDocumento(fformDocumentoInterno.getCodigo_oficina_pertenece(),fformDocumentoInterno.getCodigo_tipo_documento_interno());
		  		filename=generarNombreArchivoPDF(nombreOficina,fformDocumentoInterno.getCodigo_tipo_documento_interno(),numero_doc);
			  		//String filename=IConstante.filePath+"\\MEMO_01.pdf";
			  		log.info("filename" +filename);
			  	File file= new File(filename);	
			  	fformDocumentoInterno.getDocumentoInterno().setNombre_arhivo(file.getName());
			  		
		  		
		  		
		  		fformDocumentoInterno.setNumero_doc(numero_doc);
		  		service.saveDocumentoInterno(fformDocumentoInterno.getDocumentoInterno());
		  		
		  		mensajes.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("tramite.documento.creado.exito"));
		  		
	    		
		  		
		  		}else //se trata de una actualizacion
		  		{
		  			numero_doc=fformDocumentoInterno.getNumero_doc();
		  			service.updateDocumentoInterno(fformDocumentoInterno.getDocumentoInterno());
		  			mensajes.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("tramite.documento.actualizado.exito"));
		  		}
		  		
		  		request.setAttribute("docInterno",fformDocumentoInterno.getDocumentoInterno());
		        request.setAttribute(Globals.MESSAGE_KEY, mensajes);
		  			
		  		log.info("filename global" +fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo());
		  		
		  		
		  		ArrayList<EstandarBean> tipoDocumentoInternosList=service.getTipoDocumentosInternosLista();
		  		String nombre_tipo_documento = getNombreDocumentoTipo(fformDocumentoInterno.getCodigo_tipo_documento_interno(),tipoDocumentoInternosList);
		  		try{ 
		  			
		  			switch (fformDocumentoInterno.getCodigo_tipo_documento_interno()) {
		  			case 2:
						pdf.createPdf_Oficio(fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo(), fformDocumentoInterno.getDocumentoInterno(),nombre_tipo_documento,nombreOficina,siglasOficinasCopias,nombre_oficina_destino);
						break;
					case 5:
						pdf.createPdf_Informe(fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo(), fformDocumentoInterno.getDocumentoInterno(),nombre_tipo_documento,nombreOficina,siglasOficinasCopias,nombre_oficina_destino);
						break;

					default:
						pdf.createPdf(fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo(), fformDocumentoInterno.getDocumentoInterno(),nombre_tipo_documento,nombreOficina,siglasOficinasCopias,nombre_oficina_destino,img1);
						break;
					}
		  			
		  			
		  		
		  	
		  		 Desktop d=java.awt.Desktop.getDesktop ();
		         d.open (new java.io.File (new File(IConstante.filePath),fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo()));
		         
		  		}
		  		 catch(FileNotFoundException e ){
		  			 
		  			log.error("No se encuentra el archivo o esta abierto siendo utilizado por otro proceso");
		  			 
		  		 }
		  		catch(NullPointerException e2){
		  			 
		  			log.error("No se encuentra el archivo o es null");
		  		 }
		  		
		  		//Mostra codigo de Investigadores
			    //colocamos la lista para ser accesada desde la jsp
			    // fformDocumentoInterno.setTipoDocumentoInternosList(tipoDocumentoInternosList);
			    // log.info("tamanio lista tipoDocumentoInternos:  "+fformDocumentoInterno.getTipoDocumentoInternosList().size());
			    // Forward control to this Action's input page.
		         
		  		//ahora mostraremos el archivo adjunto
		  		
		  		//File scfile = new File(IConstante.filePath, fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo()  );
		  		
		  	    //mostrarPDFGenerado(response, fformDocumentoInterno, scfile);
               */
                 
                 
		  		
		  		
		         
			    return mapping.findForward("exito");
			
			  }
	public void mostrarPDFGenerado(HttpServletResponse response,
			FFormDocumentoInterno fformDocumentoInterno, File scfile) {
		try {
			FileInputStream file = new FileInputStream(scfile);
			int longitud = file.available();
			byte b[] = new byte[longitud];
			file.read(b);
			file.close();            		
			response.setHeader("Content-Disposition",
		    		"attachment;filename="+ fformDocumentoInterno.getDocumentoInterno().getNombre_arhivo() );

		    		ServletOutputStream salida = response.getOutputStream();
		    		salida.write(b);
		    		salida.flush();
		    		salida.close();
		    		//response.sendRedirect("/MostrarFormularioCrearcionDocumentoInterno.do");
		    		
		    		
		} catch( FileNotFoundException e ) {
			System.out.println("Archivo no encontrado..");
		    /* Hacer algo */
		 }catch (IOException io){
			 System.out.println("Error de entrada Salida..");
		 }
		


	}
	private String obtener_siglas_oficina(Integer codigo_oficina,ArrayList<BOficinas> areasAdministrativasList) {
		// TODO Auto-generated method stub
		String nombre_oficina_destino="";
		String _codigo_oficina=String.valueOf(codigo_oficina);
		for (BOficinas item:areasAdministrativasList) {
			if(_codigo_oficina.equals(item.getCodigo_oficina()))
					{
				nombre_oficina_destino=item.getOficina();
					}
		}
		
		return nombre_oficina_destino;
		
	}
	public void obtener_siglas_oficina(String[] codigos_copia_oficina_destino,
			ArrayList<BOficinas> areasAdministrativasList,
			ArrayList<String> siglasOficinasCopias) {
		for (String codigo_oficina:codigos_copia_oficina_destino){
			 
			 for (BOficinas item:areasAdministrativasList) {
				if(codigo_oficina.equals(item.getCodigo_oficina()))
						{
					siglasOficinasCopias.add(item.getSiglas_oficina());
						}
			}
		}
	}
	
	
	private String getNombreDocumentoTipo(
			int tipo_documento_interno,
			ArrayList<EstandarBean> tipoDocumentoInternosList) {
		String nombre_tipo_documento="";
		for (Iterator<EstandarBean> iterator = tipoDocumentoInternosList.iterator(); iterator
				.hasNext();) {
			EstandarBean estandarBean = iterator.next();
			if(estandarBean.getIdBean()==tipo_documento_interno)
			{	nombre_tipo_documento=estandarBean.getNombreBean();
				break;
				}
		}
		return nombre_tipo_documento;
	}
	private String generarNombreArchivoPDF(String nombreOficina,
			Integer codigo_tipo_documento_interno, int numero_doc) {
		// TODO Auto-generated method stub
		
	
		 String anio = getAnioActual();
		
		String nombre1="";
		switch (codigo_tipo_documento_interno) {
		case 1://MEMO
			nombre1="MEMO_";
			break;
		case 2://MEMO
			nombre1="OFI_";
			break;
		case 3://MEMO
			nombre1="RESO_";
			break;
		case 4://MEMO
			nombre1="CARTA_";
			break;
		case 5://MEMO
			nombre1="INFORME_";
			break;

		default:
			break;
		}
		String nombre_archivo_generado=	IConstante.filePath+"\\"+nombre1+numero_doc+"_"+anio+"_CONCYTEC_"+nombreOficina+".pdf";
		log.info("nombre documento:" + nombre_archivo_generado);
		return nombre_archivo_generado;
	}
	private String getAnioActual() {
		Calendar c = new GregorianCalendar(); 
		String anio = Integer.toString(c.get(Calendar.YEAR));
		return anio;
	}
	
			}

