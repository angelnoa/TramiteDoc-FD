package tramitedoc.concytec.action;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Funciones;



/*
 * Author: Moises Pelaez Sarmiento
 * Genera un reporte en Excel de las resoluciones
 * */
public class GenerateReportWhitExcelAction extends Action {
	
	private  Log log = LogFactory.getLog(GenerateReportWhitExcelAction.class);
	private static final String[] cabecera = {"Nº Registro","Nº Expediente","Fecha Registro","Nº Documento","Dependencia Origen ","Firmado por ","Asunto","Oficina Destino Inicial","Oficina Destino Actual","Observacion"};
	private static final String[] cabecerareporte = {"Nº Registro","Nº Expediente","Nº Documento","Fecha Registro","Firmado por ","Dependencia Origen ","Oficina Destino ","Persona Destino","Asunto"};
	private static final String[] cabecerareportefirmados = {"Nº Doc.","Fecha de Envio","Oficina Destino","Asunto","Registro "};
	private static final String[] cabecerareporteadmin = {"Nº ","Usuario","Nombre","Apellidos","Email","Estado","Perfil","Sede"};
	private static final String[] cabecerareporteadminpers = {"Nº ","Usuario","Nombre / Apellidos","Sede","Oficina","Cargo","Estado","Perfil","Email"};
	
	final static String SUBREPORT_DIR ="/reports/"; 
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
			ActionErrors errors = new ActionErrors();
			response.setContentType("application/xls");
		
	/*	if ( !Utils.isLoggedIn(request) ){
            return mapping.findForward(IConstants.SESSION_TIME_OUT_KEY);
          }
		
		 GenerateReportForm generateReportForm=(GenerateReportForm)form;
		
    	ControlResolucionService controlResolucionService = new ControlResolucionServiceImpl();*/
			
			System.out.println("Aqui uso el EXCEL...............");
			
			/********************** RECUPERO VALORES ***************/
			HttpSession session = request.getSession(true); 
			String tipo=request.getParameter("tipo");
			tipo=(tipo==null)?"":tipo;
/*********************************************************/	
			System.out.println("Tipo >> "+tipo);
			
			String ls_fecha_inicio = (String) session.getAttribute("fecha_inicio");
    		String ls_fecha_fin = (String) session.getAttribute("fecha_fin");
    		String ls_codigo_motivo = (String) session.getAttribute("nom_motivo");
    		ls_codigo_motivo=(ls_codigo_motivo==null)?"":ls_codigo_motivo;
    		
    		String ls_oficinaz_reporte = (String)session.getAttribute("oficinaz");
    		String ls_operacion = (String)session.getAttribute("operacion");
    		ls_operacion=(ls_operacion==null)?"":ls_operacion;
    		ls_oficinaz_reporte=(ls_oficinaz_reporte==null)?"":ls_oficinaz_reporte;
    		ls_fecha_inicio=(ls_fecha_inicio==null)?"":ls_fecha_inicio;
			ls_fecha_fin=(ls_fecha_fin==null)?"":ls_fecha_fin;
    		System.out.println("Operacion >> "+ls_operacion);
    		System.out.println("Oficina >> "+ls_oficinaz_reporte);
    		System.out.println("Motivo >> "+ls_codigo_motivo);
    		

		try {
			
			if (tipo.equals("RD")){
				/*CREO EL REPORTE QUE VIENE DE CONSOLIDADO DE PENDIENTES*/
								
				/**********************/
	    		Collection listaReporte = new ArrayList();
	    		listaReporte =  (Collection) session.getAttribute("listadocumentos");	

				response.setContentType("application/vnd.ms-excel");
				Iterator<Hashtable> iters=listaReporte.iterator();
							

			HSSFWorkbook wb = new HSSFWorkbook(); //creando el excel
			
	        HSSFSheet sheet1 = wb.createSheet("reporte");
	        
	        sheet1.getPrintSetup().setLandscape(true);        
	        sheet1.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);      

	       
	        sheet1.setMargin(HSSFSheet.TopMargin, 0.5);       
	        sheet1.setMargin(HSSFSheet.BottomMargin, 0.5);   
	        sheet1.setMargin(HSSFSheet.LeftMargin, 0.25);
	        sheet1.setMargin(HSSFSheet.RightMargin, 0.25);
	        sheet1.getPrintSetup().setScale((short)65);
	        
	        System.out.println("Scale" +sheet1.getPrintSetup().getScale());        
	      
	        HSSFCellStyle cs = wb.createCellStyle();
	        HSSFCellStyle csz = wb.createCellStyle();
	        
	       
			
	        //csz.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
	        //csz.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        
	       
	        HSSFFont fg = wb.createFont();
	        
	        fg.setFontHeightInPoints((short) 12);
			fg.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					
			csz.setFont(fg);
	        
	        cs.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
			cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//ver
	        
	        HSSFFont f = wb.createFont();
			
			f.setFontHeightInPoints((short) 12);
			f.setColor((short) 0xA);
			f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					
			cs.setFont(f);
			cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			crearCabeceraReporte(sheet1, cs,csz,ls_oficinaz_reporte,ls_operacion,ls_codigo_motivo,ls_fecha_inicio,ls_fecha_fin);
			
			HSSFFont f2 = wb.createFont();
			f2.setFontHeightInPoints((short) 10);
			HSSFCellStyle cs2 = wb.createCellStyle();
			cs2.setFont(f2);		
			cs2.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
			cs2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//ver
			cs2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cs2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cs2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cs2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      
	        /*
	        String fecRegistro = generateReportForm.getFecRegistro();
	        HashMap<String, Date> parameter = new HashMap<String, Date>();
	        parameter.put("fecha",Dates.getDate(fecRegistro,"yyyy-MM-dd" ));
	        System.out.println("fecRegistro:" +fecRegistro);
	        
	        Usuario user =(Usuario)request.getSession().getAttribute( IConstants.USER_VIEW_KEY );*/
	        
					
			if (!iters.hasNext()) {
				System.out.println("No objects to display.");
				errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required.sinregistros"));
	    		saveErrors(request, errors);
				
			}
			
			fillRowsReport(sheet1,iters,cs2);

			String fileName="Reporte_Consolidado.xls";
			
	        FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream(fileName);
				wb.write(fileOut);			
				fileOut.close();
				response.setHeader("Content-Disposition","attachment; filename="+fileName);		
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			File excelFile = new File(fileName);		

			BufferedInputStream buf = null;
			FileInputStream input;
			ServletOutputStream stream = null;
			 
			try {stream = response.getOutputStream();
				input = new FileInputStream(excelFile);
				 buf = new BufferedInputStream(input);
			     int readBytes = 0;
			     
			     while((readBytes = buf.read()) != -1)
				        stream.write(readBytes);

				     } catch (IOException ioe){
				     
				        try {
							throw new ServletException(ioe.getMessage());
						} catch (ServletException e) {
						
							e.printStackTrace();
						}
				         
				     } finally {
				     
				//close the input/output streams
				    try {
				     if(stream != null)
						stream.close();
				     if(buf != null)
				 		buf.close(); 
					
				    } catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				      }

			//response.addHeader("Content-Disposition","attachment; filename="+fileName);			
			response.setContentLength( (int)excelFile.length());
			
			}else{
				if(tipo.equals("ADMINUSU")){
					

				/*CREO EL REPORTE QUE VIENE DE ADMINISTRADOR MODULO USUARIO*/
					String operacion=request.getParameter("operacion");
					operacion=(operacion==null)?"":operacion;
		
					System.out.println("operacion >> "+operacion);				
				/**********************/
															
	    		Collection listageneral = new ArrayList();
	    		
	    		if(operacion.equals("usu")){
	    			listageneral =  (Collection) session.getAttribute("listausuarios");	
					
				}else
					if(operacion.equals("pers")){
						listageneral =  (Collection) session.getAttribute("listapersonal");	
					}
	    		
	    		

				response.setContentType("application/vnd.ms-excel");
				Iterator<Hashtable> iters=listageneral.iterator();
								

				HSSFWorkbook wb = new HSSFWorkbook(); //creando el excel
				
		        HSSFSheet sheet1 = wb.createSheet("reporte");
		        
		        sheet1.getPrintSetup().setLandscape(true);        
		        sheet1.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);      

		       
		        sheet1.setMargin(HSSFSheet.TopMargin, 0.5);       
		        sheet1.setMargin(HSSFSheet.BottomMargin, 0.5);   
		        sheet1.setMargin(HSSFSheet.LeftMargin, 0.25);
		        sheet1.setMargin(HSSFSheet.RightMargin, 0.25);
		        sheet1.getPrintSetup().setScale((short)65);
		        
		        System.out.println("Scale" +sheet1.getPrintSetup().getScale());        
		      
		        HSSFCellStyle cs = wb.createCellStyle();
		        HSSFCellStyle csz = wb.createCellStyle();
		        
		        //csz.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
		        //csz.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		        
		       
		        HSSFFont fg = wb.createFont();
		        
		        fg.setFontHeightInPoints((short) 12);
				fg.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
						
				csz.setFont(fg);
		        
		        cs.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
				cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//ver
		        
		        HSSFFont f = wb.createFont();
				
				f.setFontHeightInPoints((short) 12);
				f.setColor((short) 0xA);
				f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
						
				cs.setFont(f);
				cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				
				crearCabeceraReporteAdmin(sheet1, cs,csz,operacion);
				
				HSSFFont f2 = wb.createFont();
				f2.setFontHeightInPoints((short) 10);
				HSSFCellStyle cs2 = wb.createCellStyle();
				cs2.setFont(f2);		
				cs2.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
				cs2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//ver
				cs2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cs2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cs2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cs2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		      
		        /*
		        String fecRegistro = generateReportForm.getFecRegistro();
		        HashMap<String, Date> parameter = new HashMap<String, Date>();
		        parameter.put("fecha",Dates.getDate(fecRegistro,"yyyy-MM-dd" ));
		        System.out.println("fecRegistro:" +fecRegistro);
		        
		        Usuario user =(Usuario)request.getSession().getAttribute( IConstants.USER_VIEW_KEY );*/
		        
						
				if (!iters.hasNext()) {
					System.out.println("No objects to display.");
					errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required.sinregistros"));
		    		saveErrors(request, errors);
					
				}
				
				

				String fileName="";
				
				if(operacion.equals("usu")){
					fileName="Reporte_Consolidado_Usuarios_STD.xls";
					fillRowsReportAdmin(sheet1,iters,cs2);
					
				}else
					if(operacion.equals("pers")){
						fileName="Reporte_Consolidado_Personal_STD.xls";
						fillRowsReportAdminPers(sheet1,iters,cs2);
					}
				
				
		        FileOutputStream fileOut;
				try {
					fileOut = new FileOutputStream(fileName);
					wb.write(fileOut);			
					fileOut.close();
					response.setHeader("Content-Disposition","attachment; filename="+fileName);		
					
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				File excelFile = new File(fileName);		

				BufferedInputStream buf = null;
				FileInputStream input;
				ServletOutputStream stream = null;
				 
				try {stream = response.getOutputStream();
					input = new FileInputStream(excelFile);
					 buf = new BufferedInputStream(input);
				     int readBytes = 0;
				     
				     while((readBytes = buf.read()) != -1)
					        stream.write(readBytes);

					     } catch (IOException ioe){
					     
					        try {
								throw new ServletException(ioe.getMessage());
							} catch (ServletException e) {
							
								e.printStackTrace();
							}
					         
					     } finally {
					     
					//close the input/output streams
					    try {
					     if(stream != null)
							stream.close();
					     if(buf != null)
					 		buf.close(); 
						
					    } catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					      }

				//response.addHeader("Content-Disposition","attachment; filename="+fileName);			
				response.setContentLength( (int)excelFile.length());
				
				
				
				}else{
					if(tipo.equals("RDF")){
						
						ls_fecha_inicio = (String) session.getAttribute("fechainicio_ls_ls");
			    		ls_fecha_fin = (String) session.getAttribute("fechafin_ls_ls");
			    		String tipo_documento_ls = (String) session.getAttribute("tipo_documento_ls");
			    		ls_oficinaz_reporte = (String)session.getAttribute("oficinas_nombre_ls");
			    		
			    		ls_fecha_inicio=(ls_fecha_inicio==null)?"":ls_fecha_inicio;
			    		ls_fecha_fin=(ls_fecha_fin==null)?"":ls_fecha_fin;
			    		tipo_documento_ls=(tipo_documento_ls==null)?"":tipo_documento_ls;
			    		ls_oficinaz_reporte=(ls_oficinaz_reporte==null)?"":ls_oficinaz_reporte;
			    		
						
						/*CREO EL REPORTE QUE VIENE DE CONSOLIDADO DE DOCUMENTOS FIRMADOS*/
						
						/**********************/
			    		//Collection listaReporte = new ArrayList();
			    		ArrayList<DocumentoInternoBean> listaReporte =  (ArrayList<DocumentoInternoBean>) session.getAttribute("reportelistaDocumentosInternos");	
			    		Collections.reverse(listaReporte);
			    		
						response.setContentType("application/vnd.ms-excel");
						//Iterator<Hashtable> iters=listaReporte.iterator();
									

					HSSFWorkbook wb = new HSSFWorkbook(); //creando el excel
					
			        HSSFSheet sheet1 = wb.createSheet("reporte");
			        
			        sheet1.getPrintSetup().setLandscape(true);        
			        sheet1.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);      

			       
			        sheet1.setMargin(HSSFSheet.TopMargin, 0.5);       
			        sheet1.setMargin(HSSFSheet.BottomMargin, 0.5);   
			        sheet1.setMargin(HSSFSheet.LeftMargin, 0.25);
			        sheet1.setMargin(HSSFSheet.RightMargin, 0.25);
			        sheet1.getPrintSetup().setScale((short)65);
			        
			        System.out.println("Scale" +sheet1.getPrintSetup().getScale());        
			      
			        HSSFCellStyle cs = wb.createCellStyle();
			        HSSFCellStyle csz = wb.createCellStyle();
			        
			       
					
			        //csz.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
			        //csz.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			        
			       
			        HSSFFont fg = wb.createFont();
			        
			        fg.setFontHeightInPoints((short) 12);
					fg.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
							
					csz.setFont(fg);
			        
			        cs.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
					cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//ver
			        
			        HSSFFont f = wb.createFont();
					
					f.setFontHeightInPoints((short) 12);
					f.setColor((short) 0xA);
					f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
							
					cs.setFont(f);
					cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					crearCabeceraReporteDocumentosFirmados(sheet1, cs,csz,ls_oficinaz_reporte,tipo_documento_ls,ls_fecha_inicio,ls_fecha_fin);
					
					HSSFFont f2 = wb.createFont();
					f2.setFontHeightInPoints((short) 10);
					HSSFCellStyle cs2 = wb.createCellStyle();
					cs2.setFont(f2);		
					cs2.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
					cs2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//ver
					cs2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cs2.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cs2.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cs2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			      
			        /*
			        String fecRegistro = generateReportForm.getFecRegistro();
			        HashMap<String, Date> parameter = new HashMap<String, Date>();
			        parameter.put("fecha",Dates.getDate(fecRegistro,"yyyy-MM-dd" ));
			        System.out.println("fecRegistro:" +fecRegistro);
			        
			        Usuario user =(Usuario)request.getSession().getAttribute( IConstants.USER_VIEW_KEY );*/
			        
							
					if (listaReporte.isEmpty()) {
						System.out.println("No objects to display.");
						errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required.sinregistros"));
			    		saveErrors(request, errors);
						
					}
					
					fillRowsReportFirmados(sheet1,listaReporte,cs2);

					String fileName="Reporte_Documentos_Firmados.xls";
					
			        FileOutputStream fileOut;
					try {
						fileOut = new FileOutputStream(fileName);
						wb.write(fileOut);			
						fileOut.close();
						
						response.setHeader("Content-Disposition","attachment; filename="+fileName);		
						
					} catch (FileNotFoundException e) {
						
						e.printStackTrace();
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					File excelFile = new File(fileName);		

					BufferedInputStream buf = null;
					FileInputStream input;
					ServletOutputStream stream = null;
					 
					try {stream = response.getOutputStream();
						input = new FileInputStream(excelFile);
						 buf = new BufferedInputStream(input);
					     int readBytes = 0;
					     
					     while((readBytes = buf.read()) != -1)
						        stream.write(readBytes);

						     } catch (IOException ioe){
						     
						        try {
									throw new ServletException(ioe.getMessage());
								} catch (ServletException e) {
								
									e.printStackTrace();
								}
						         
						     } finally {
						     
						//close the input/output streams
						    try {
						     if(stream != null)
								stream.close();
						     if(buf != null)
						 		buf.close(); 
							
						    } catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						      }

					//response.addHeader("Content-Disposition","attachment; filename="+fileName);			
					response.setContentLength( (int)excelFile.length());
						
						
						
						
					}else{

						/*CREO REPORTE DE SEGUIMIENTO*/
						Collection listaFrameBusqueda_xlsEspecial = new ArrayList();
						//listaFrameBusqueda_xlsEspecial=(Collection) session.getAttribute("listaFrameBusqueda_xlsEspecial");
						
						listaFrameBusqueda_xlsEspecial=(Collection) session.getAttribute("listaFrameBusquedaTemp");

						response.setContentType("application/vnd.ms-excel");
						Iterator<Hashtable> iter=listaFrameBusqueda_xlsEspecial.iterator();
									


					
					HSSFWorkbook wb = new HSSFWorkbook(); //creando el excel
					
			        HSSFSheet sheet1 = wb.createSheet("informe");
			        
			        sheet1.getPrintSetup().setLandscape(true);        
			        sheet1.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);      

			       
			        sheet1.setMargin(HSSFSheet.TopMargin, 0.5);       
			        sheet1.setMargin(HSSFSheet.BottomMargin, 0.5);   
			        sheet1.setMargin(HSSFSheet.LeftMargin, 0.25);
			        sheet1.setMargin(HSSFSheet.RightMargin, 0.25);
			        sheet1.getPrintSetup().setScale((short)65);
			        
			        System.out.println("Scale" +sheet1.getPrintSetup().getScale());        
			      
			        /*****ESTILOS****/
			        HSSFCellStyle cs = wb.createCellStyle();
			        HSSFCellStyle csz = wb.createCellStyle();
			        
			        cs.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
					cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//ver
			        csz.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
			        csz.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			        
			        HSSFFont f = wb.createFont();
			        HSSFFont fg = wb.createFont();
					
					f.setFontHeightInPoints((short) 12);
					f.setColor((short) 0xA);
					f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					
					fg.setFontHeightInPoints((short) 12);
					fg.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
							
					cs.setFont(f);
					csz.setFont(fg);
					
					cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					
					csz.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					csz.setBorderRight(HSSFCellStyle.BORDER_THIN);
					csz.setBorderTop(HSSFCellStyle.BORDER_THIN);
					csz.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					
					crearCabecera(sheet1,cs);
					
					HSSFFont f2 = wb.createFont();
					f2.setFontHeightInPoints((short) 10);
					HSSFCellStyle cs2 = wb.createCellStyle();
					cs2.setFont(f2);		
					cs2.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
					cs2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//ver
					cs2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cs2.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cs2.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cs2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			      
			        /*
			        String fecRegistro = generateReportForm.getFecRegistro();
			        HashMap<String, Date> parameter = new HashMap<String, Date>();
			        parameter.put("fecha",Dates.getDate(fecRegistro,"yyyy-MM-dd" ));
			        System.out.println("fecRegistro:" +fecRegistro);
			        
			        Usuario user =(Usuario)request.getSession().getAttribute( IConstants.USER_VIEW_KEY );*/
			        
					
					
					if (!iter.hasNext()) {
						System.out.println("No objects to display.");
						errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required.sinregistros"));
			    		saveErrors(request, errors);
			    	//	generateReportForm.setFecRegistro(null);
						//return mapping.findForward(IConstants.NO_FOUND_REGISTERS);
						
					}
					fillRows(sheet1,iter,cs2);

					String fileName="Reporte_Seguimiento.xls";
					
			        FileOutputStream fileOut;
					try {
						fileOut = new FileOutputStream(fileName);
						
						wb.write(fileOut);			
						fileOut.close();
						response.setHeader("Content-Disposition","attachment; filename="+fileName);	
						
					} catch (FileNotFoundException e) {
						
						e.printStackTrace();
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					File excelFile = new File(fileName);		

					BufferedInputStream buf = null;
					FileInputStream input;
					ServletOutputStream stream = null;
					 
					try {stream = response.getOutputStream();
						input = new FileInputStream(excelFile);
						 buf = new BufferedInputStream(input);
					     int readBytes = 0;
					     
					     while((readBytes = buf.read()) != -1)
						        stream.write(readBytes);

						     } catch (IOException ioe){
						     
						        try {
									throw new ServletException(ioe.getMessage());
								} catch (ServletException e) {
								
									e.printStackTrace();
								}
						         
						     } finally {
						     
						//close the input/output streams
						    try {
						     if(stream != null)
								stream.close();
						     if(buf != null)
						 		buf.close(); 
							
						    } catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						      }

					//response.setHeader("Content-Disposition","attachment; filename="+fileName);			
					response.setContentLength( (int)excelFile.length());
					
					
					//mapping.findForward(IConstants.SUCCESS_KEY);
					
					}
				}
				
			}
					
			return null;
		
		 } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
		
	}

	private void fillRows(HSSFSheet sheet1, Iterator<Hashtable> iter, HSSFCellStyle cs) {
		     
		int numRow=1;
		while (iter.hasNext()) {
			
			Hashtable objListaDoc = iter.next();
			HSSFRow r = sheet1.createRow(numRow);
			HSSFCell c = null;
			//r.setHeight((short) 0x986);
			r.setHeight((short) (4*256));
			
			for(int i=0;i<cabecera.length;i++){
				c = r.createCell((short) i);
				c.setCellStyle(cs);
				switch (i) {
			
				case 0:
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("codigo_documento_reporte"))));
					
					break;
				case 1://Fecha
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("codigo_expediente_reporte"))));		
					break;
					
				case 2://Fecha
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("fecha_registro_reporte"))));
					break;	
					
				case 3:
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("numero_documento_reporte"))));
					break;
						
				case 4://inicia
					//c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("codigo_oficina"))));
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("desc_origen_reporte"))));
					
					break;
					
				case 5://inicia
					//c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("codigo_oficina"))));
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("firmadopor_reporte"))));
					
					break;	
					
				case 6://Asunto
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("asunto_documento_reporte"))));
					
					
					break;	
					
				case 7://Destino
					
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("oficina_origen_reporte"))));
					
					break;
						
				case 8:
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("oficina_deriva_reporte"))));
					
					
					break;
				case 9://descripcion					
				c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("observa_movimiento_reporte")).toUpperCase()));	
					
					break;	
				/*	
				case 9://Area Administrativa
					c.setCellValue(new HSSFRichTextString(est.getNomestado().toUpperCase()));
					
					break;
					*/

				default:
					break;
				}
				
	
			
			//c.setCellStyle(cs);
			}
			numRow++;
			
		//fin while
	}
			}
	
	private void fillRowsReport(HSSFSheet sheet1,  Iterator<Hashtable> iter  , HSSFCellStyle cs) {
	     
		System.out.println("Entre a la funcion de fillRowsReport");
		int numRow=4;
	
		
		while (iter.hasNext()) {
			Hashtable objListaDoc = iter.next();
			HSSFRow r = sheet1.createRow(numRow);
			HSSFCell c = null;
			//r.setHeight((short) 0x986);
			r.setHeight((short) (4*256));
			for(int i=0;i<cabecerareporte.length;i++){
				c = r.createCell((short) i);
				c.setCellStyle(cs);
				switch (i) {
			
				case 0:
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("codigo_documento"))));
					break;
				case 1://Fecha
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("codigo_expediente"))));		
					break;
					
				case 2://Fecha
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("numero_documento"))));
					break;	
					
				case 3:
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("fecha_registro"))));
					break;
						
				case 4://inicia
					//c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("codigo_oficina"))));
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("firmadopor"))));
					
					break;
					
				case 5://inicia
					//c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("codigo_oficina"))));
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("oficina_origen"))));
					
					break;	
					
				case 6://Asunto
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("oficina_deriva"))));
					
					
					break;	
					
				case 7://Destino
					
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("persona_destino"))));
					
					break;
					
				case 8://Destino
					
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("asunto_documento"))));
					
					break;
					
				default:
					break;
				}
				
	
			
			//c.setCellStyle(cs);
			}
			numRow++;
			
		//fin while
	}
}
	
	private void fillRowsReportAdmin(HSSFSheet sheet1,  Iterator<Hashtable> iter  , HSSFCellStyle cs) {
	     
		System.out.println("Entre a la funcion de fillRowsReportAdmin");
		int numRow=3;
		int cont=1;
		
		while (iter.hasNext()) {
			Hashtable objListaDoc = iter.next();
			HSSFRow r = sheet1.createRow(numRow);
			HSSFCell c = null;
			//r.setHeight((short) 0x986);
			r.setHeight((short) (4*256));
			
						
						
			for(int i=0;i<cabecerareporteadmin.length;i++){
				c = r.createCell((short) i);
				c.setCellStyle(cs);
				switch (i) {
			
				case 0:
					c.setCellValue(new HSSFRichTextString(String.valueOf(cont)));
					break;
				case 1://Fecha
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("usuario"))));		
					break;
					
				case 2://Fecha
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("nombres"))));
					break;	
					
				case 3:
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("apellidos"))));
					break;
						
				case 4://inicia
					//c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("codigo_oficina"))));
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("email"))));
					
					break;
					
				case 5://inicia
					//c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("codigo_oficina"))));
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("tipo_estado"))));
					
					break;	
					
				case 6://Asunto
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("tipo_usuario"))));
					
					
					break;	
					
				case 7://Destino
					
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("tipo_sede"))));
					
					break;
								
					
				default:
					break;
				}
				
			
			//c.setCellStyle(cs);
			}
			numRow++;
			cont++;
		//fin while
	}
}
	
	private void fillRowsReportAdminPers(HSSFSheet sheet1,  Iterator<Hashtable> iter  , HSSFCellStyle cs) {
	     
		System.out.println("Entre a la funcion de fillRowsReportAdminPers");
		int numRow=3;
		int cont=1;
		
		while (iter.hasNext()) {
			Hashtable objListaDoc = iter.next();
			HSSFRow r = sheet1.createRow(numRow);
			HSSFCell c = null;
			//r.setHeight((short) 0x986);
			r.setHeight((short) (4*256));
			
			
						
			for(int i=0;i<cabecerareporteadminpers.length;i++){
				c = r.createCell((short) i);
				c.setCellStyle(cs);
				switch (i) {
			
				case 0:
					c.setCellValue(new HSSFRichTextString(String.valueOf(cont)));
					break;
				case 1://Fecha
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("c_usuario"))));		
					break;
					
				case 2://Fecha
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("nombre_personal"))));
					break;	
					
			
					
				case 3://inicia
					//c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("codigo_oficina"))));
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("nombre_sede"))));
					
					break;	
					
				case 4://Asunto
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("oficina"))));
					
					
					break;	
					
				case 5://Destino
					
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("cargo_personal"))));
					
					break;
					
				case 6://Destino
					
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("tipo_estado"))));
					
					break;	
					
				case 7://Destino
									
									
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("tipo_usuario"))));
					
					break;
									
				case 8://Destino
					
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("email"))));
					
					break;
								
					
				default:
					break;
				}
				
			
			//c.setCellStyle(cs);
			}
			numRow++;
			cont++;
		//fin while
	}
}
	
	private void fillRowsReportFirmados(HSSFSheet sheet1,  ArrayList<DocumentoInternoBean> iter, HSSFCellStyle cs) {
		DocumentosInternosService service = new DocumentosInternosServiceImplement();
		
		System.out.println("Entre a la funcion de fillRowsReportFirmados");
		int numRow=4;
		
		Iterator itin = iter.iterator();
		
		//int i=0;
			while (itin.hasNext()) {	
				HSSFRow r = sheet1.createRow(numRow);
				HSSFCell c = null;
				r.setHeight((short) (4*256));
				DocumentoInternoBean archivo = (DocumentoInternoBean) itin.next();
				log.info("archivo "+archivo.getNombre_arhivo());
				log.info("fila "+numRow);
				c = r.createCell((short) 0);
				c.setCellStyle(cs);
				c.setCellValue(new HSSFRichTextString(String.valueOf(archivo.getNumero_doc())));
				
				DateFormat df=new SimpleDateFormat ("dd/MM/yyyy");
				
				c = r.createCell((short) 1);
				c.setCellStyle(cs);
				c.setCellValue(new HSSFRichTextString(df.format(archivo.getFecha_modificacion())));
				
				String nombre_oficina="";
				try {
					nombre_oficina = service.getNombreOficina(archivo.getCodigo_oficina());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				nombre_oficina = (nombre_oficina==null)? "":nombre_oficina;
				
				c = r.createCell((short) 2);
				c.setCellStyle(cs);
				c.setCellValue(new HSSFRichTextString(nombre_oficina));
				
				c = r.createCell((short) 3);
				c.setCellStyle(cs);
				c.setCellValue(new HSSFRichTextString(String.valueOf(archivo.getAsunto())));
				
				c = r.createCell((short) 4);
				c.setCellStyle(cs);
				c.setCellValue(new HSSFRichTextString(String.valueOf(archivo.getCodigo_documento())));

				numRow++;
			}
		
		
		/*while (iter.hasNext()) {
			Hashtable objListaDoc = iter.next();
			HSSFRow r = sheet1.createRow(numRow);
			HSSFCell c = null;
			//r.setHeight((short) 0x986);
			r.setHeight((short) (4*256));
			for(int i=0;i<cabecerareportefirmados.length;i++){
				c = r.createCell((short) i);
				c.setCellStyle(cs);
				switch (i) {
			
				case 0:
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("numero_doc"))));
					break;
				case 1://Fecha
					
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("fecha_modificacion"))));		
					break;
					
				case 2://Fecha
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("dirigido_a"))));
					break;	
					
				case 3:
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("asunto"))));
					break;
						
				case 4://inicia
										
					c.setCellValue(new HSSFRichTextString(String.valueOf(objListaDoc.get("codigo_documento"))));
					
					break;

					
				default:
					break;
				}
				
	
			
			//c.setCellStyle(cs);
			}
			numRow++;
			
		//fin while
	}*/
}
/*
	private boolean Modificado(ControlResolucion con, Date date) {
		boolean modificado=false;
		Iterator<ResolucionHistorico> iter=con.getHistoriaResolucion().iterator();
		while(iter.hasNext()){
			
			log.info("date:" +iter.next().getFecReg());
			if(iter.next().getFecReg().equals())
			{	modificado= true;
			break;
			}
			
		}
		return modificado;
	}*/

	private void crearCabecera(HSSFSheet sheet1, HSSFCellStyle cs) {
		int rownum;
		
		for (rownum = 0; rownum < 1; rownum++) {
        	HSSFRow r = sheet1.createRow(rownum);
           	       	
        	    r.setHeight((short) 0x249);
        		sheet1.setColumnWidth((short) 1, (short) (24*256));
        			
			for (int cellnum = 0; cellnum < cabecera.length; cellnum ++) {
				ponerAchoColumnas(sheet1, cellnum);
				HSSFCell c = r.createCell((short) cellnum);
				c.setCellValue(new HSSFRichTextString(cabecera[cellnum]));				
				c.setCellStyle(cs);
			}
        	
        }
		
	}

	private void crearCabeceraReporte(HSSFSheet sheet1, HSSFCellStyle cs,HSSFCellStyle csz,String ls_oficinaz_reporte,String ls_operacion,String ls_codigo_motivo,String ls_fecha_inicio,String ls_fecha_fin) {
		int rownum;
				
		for (rownum = 0; rownum < 4; rownum++) {
        	HSSFRow r = sheet1.createRow(rownum);
           	       	
        	if(rownum == 0){
        		r.setHeight((short) 0x249);
        		
        		sheet1.setColumnWidth((short) 1, (short) (24*256));
        		HSSFCell c = r.createCell((short) 0);
        		if (ls_operacion.equals("1")){
        			c.setCellValue(new HSSFRichTextString("REPORTE DE DOCUMENTOS POR RECIBIR "));				
    				c.setCellStyle(csz);
        			
        		}else{
        			c.setCellValue(new HSSFRichTextString("REPORTE DE DOCUMENTOS EN TRAMITE"));				
    				c.setCellStyle(csz);
        		}
				/**pongo la fecha**/
        		HSSFCell ce = r.createCell((short) 6);
        		HSSFCell cd = r.createCell((short) 7);
        		ce.setCellValue(new HSSFRichTextString("Fecha: "));				
				ce.setCellStyle(csz);
				cd.setCellValue(new HSSFRichTextString(fechaActual()));				
				cd.setCellStyle(csz);
        		
        	}
        	if(rownum == 1){
        		r.setHeight((short) 0x249);
        		
        		sheet1.setColumnWidth((short) 1, (short) (36*256));
        		HSSFCell c = r.createCell((short) 0);
        		c.setCellValue(new HSSFRichTextString("OFICINA : "+ls_oficinaz_reporte));	
        		c.setCellStyle(csz);
        		
        		HSSFCell ce = r.createCell((short) 6);
        		HSSFCell cd = r.createCell((short) 7);
        		ce.setCellValue(new HSSFRichTextString("Hora: "));				
				ce.setCellStyle(csz);
				cd.setCellValue(new HSSFRichTextString(horaActual()));				
				cd.setCellStyle(csz);
        		
        	}
        	if (rownum == 2){
        		if (!ls_fecha_inicio.equals("")){
        			r.setHeight((short) 0x249);
       			
        			if(!ls_fecha_fin.equals("")){
        				//imprimo 
                		sheet1.setColumnWidth((short) 1, (short) (36*256));
                		HSSFCell c = r.createCell((short) 0);
                		c.setCellValue(new HSSFRichTextString("Desde: "+ls_fecha_inicio+"   Hasta: "+ls_fecha_fin));	
         				c.setCellStyle(csz);

        			}else{
        				sheet1.setColumnWidth((short) 1, (short) (36*256));
                		HSSFCell c = r.createCell((short) 0);
                		c.setCellValue(new HSSFRichTextString("Desde: "+ls_fecha_inicio+"   Hasta:"+fechaActual()));	
         				c.setCellStyle(csz);
        			}
        			
        			
        		}else{
        			if(!ls_fecha_fin.equals("")){
        				sheet1.setColumnWidth((short) 1, (short) (36*256));
                		HSSFCell c = r.createCell((short) 0);
                		c.setCellValue(new HSSFRichTextString("Hasta: "+ls_fecha_fin));	
         				c.setCellStyle(csz);
        			}else{
        				sheet1.setColumnWidth((short) 1, (short) (36*256));
                		HSSFCell c = r.createCell((short) 0);
                		c.setCellValue(new HSSFRichTextString("Hasta: "+fechaActual()));	
         				c.setCellStyle(csz);
        			}
        		}
        		
        		HSSFCell ce = r.createCell((short) 6);
        		HSSFCell cd = r.createCell((short) 7);
        		//if(!ls_codigo_motivo.equals("0")){

        		ce.setCellValue(new HSSFRichTextString("Motivo: "));				
				ce.setCellStyle(csz);
				cd.setCellValue(new HSSFRichTextString(" "+ls_codigo_motivo));				
				cd.setCellStyle(csz);
        		
        	//}
        	}
        	if (rownum == 3){
        		r.setHeight((short) 0x249);
		
			for (int cellnum = 0; cellnum < cabecerareporte.length; cellnum ++) {
				ponerAchoColumnas(sheet1, cellnum);
				HSSFCell c = r.createCell((short) cellnum);
				c.setCellValue(new HSSFRichTextString(cabecerareporte[cellnum]));				
				c.setCellStyle(cs);
				}
        	}
        	
        	} 
		}
	
	
	private void crearCabeceraReporteAdmin(HSSFSheet sheet1, HSSFCellStyle cs,HSSFCellStyle csz,String ls_operacion) {
		int rownum;
				
		for (rownum = 0; rownum < 3; rownum++) {
        	HSSFRow r = sheet1.createRow(rownum);
           	       	
        	if(rownum == 0){
        		r.setHeight((short) 0x249);
        		
        		sheet1.setColumnWidth((short) 1, (short) (24*256));
        		HSSFCell c = r.createCell((short) 0);
        		if (ls_operacion.equals("usu")){
        			c.setCellValue(new HSSFRichTextString("REPORTE DE USUARIOS"));				
    				c.setCellStyle(csz);
    				
    				/**pongo la fecha**/
            		HSSFCell ce = r.createCell((short) 6);
            		HSSFCell cd = r.createCell((short) 7);
            		ce.setCellValue(new HSSFRichTextString("Fecha: "));				
    				ce.setCellStyle(csz);
    				cd.setCellValue(new HSSFRichTextString(fechaActual()));				
    				cd.setCellStyle(csz);
        			
        		}else
        		if (ls_operacion.equals("pers")){
        			c.setCellValue(new HSSFRichTextString("REPORTE DE PERSONAL"));				
    				c.setCellStyle(csz);
    				
    				/**pongo la fecha**/
            		HSSFCell ce = r.createCell((short) 5);
            		HSSFCell cd = r.createCell((short) 6);
            		ce.setCellValue(new HSSFRichTextString("Fecha: "));				
    				ce.setCellStyle(csz);
    				cd.setCellValue(new HSSFRichTextString(fechaActual()));				
    				cd.setCellStyle(csz);
        			
        		}
        		
				
        		
        	}
        	
        	if (rownum == 2){
        		r.setHeight((short) 0x249);
		
        		if (ls_operacion.equals("usu")){
        			for (int cellnum = 0; cellnum < cabecerareporteadmin.length; cellnum ++) {
        				ponerAchoColumnas(sheet1, cellnum);
        				HSSFCell c = r.createCell((short) cellnum);
        				c.setCellValue(new HSSFRichTextString(cabecerareporteadmin[cellnum]));				
        				c.setCellStyle(cs);
        				}
        			
        		}else
        		if (ls_operacion.equals("pers")){
        			for (int cellnum = 0; cellnum < cabecerareporteadminpers.length; cellnum ++) {
        				ponerAchoColumnas(sheet1, cellnum);
        				HSSFCell c = r.createCell((short) cellnum);
        				c.setCellValue(new HSSFRichTextString(cabecerareporteadminpers[cellnum]));				
        				c.setCellStyle(cs);
        				}
        			
        		}
			
        	}
        	
        	} 
		}
	
	
	private void ponerAchoColumnas(HSSFSheet sheet1, int cellnum) {
	
		
		switch (cellnum) {
		
		case 0://NUM REGISTRO
			sheet1.setColumnWidth((short) cellnum, (short) (12*256));
			break;
		case 1://NUM EXPEDIENTE
			sheet1.setColumnWidth((short) cellnum, (short) (14*256));
			break;
		case 2://NUMERO DOC
			sheet1.setColumnWidth((short) cellnum, (short) (15*256));
			break;	
		case 3://Fecha de Registro
			sheet1.setColumnWidth((short) cellnum,  (short) (25*256));
			break;
		case 4://administrado
			sheet1.setColumnWidth((short) cellnum, (short) (20*256));
			break;
		case 5://asunto
			sheet1.setColumnWidth((short) cellnum, (short) (35*256));
			break;
		case 6://proyecto
			sheet1.setColumnWidth((short) cellnum, (short) (20*256));
			break;
		case 7://Asunto
			sheet1.setColumnWidth((short) cellnum, (short) (20*256));
			break;
			
		case 8://Descripcion
			sheet1.setColumnWidth((short) cellnum, (short) (45*256));
			break;
		case 9://Descripcion
			sheet1.setColumnWidth((short) cellnum, (short) (45*256));
			break;	
			/*	
		case 9://Direccion actual
			sheet1.setColumnWidth(cellnum, (short) (30*256));
			break;	*/

		default:
			break;
		}
		
	}
	
private void ponerAchoColumnasFirma(HSSFSheet sheet1, int cellnum) {
	
		
		switch (cellnum) {
		
		case 0://NUM DOC
			sheet1.setColumnWidth((short) cellnum, (short) (12*256));
			break;
		case 1://FECHA
			sheet1.setColumnWidth((short) cellnum, (short) (25*256));
			break;
		case 2://DIRIGIDO A
			sheet1.setColumnWidth((short) cellnum, (short) (35*256));
			break;	
		case 3://asunto
			sheet1.setColumnWidth((short) cellnum,  (short) (35*256));
			break;
		case 4://nro registro
			sheet1.setColumnWidth((short) cellnum, (short) (12*256));
			break;
			/*	
		case 9://Direccion actual
			sheet1.setColumnWidth(cellnum, (short) (30*256));
			break;	*/

		default:
			break;
		}
		
	}

	private void crearCabeceraReporteDocumentosFirmados(HSSFSheet sheet1, HSSFCellStyle cs,HSSFCellStyle csz,String ls_oficinaz_reporte,String tipo_documento_ls,String ls_fecha_inicio,String ls_fecha_fin) {
		int rownum;
				
		for (rownum = 0; rownum < 4; rownum++) {
        	HSSFRow r = sheet1.createRow(rownum);
           	       	
        	if(rownum == 0){
        		r.setHeight((short) 0x249);
        		
        		sheet1.setColumnWidth((short) 1, (short) (24*256));
        		HSSFCell c = r.createCell((short) 0);
        		if (tipo_documento_ls.equals("0")){
        			c.setCellValue(new HSSFRichTextString("RELACION GENERAL DE DOCUMENTOS FIRMADOS "));				
    				c.setCellStyle(csz);
        			
        		}if (tipo_documento_ls.equals("1")){
        			c.setCellValue(new HSSFRichTextString("RELACION DE MEMORANDUMS EMITIDOS "));				
    				c.setCellStyle(csz);
        			
        		}if (tipo_documento_ls.equals("2")){
        			c.setCellValue(new HSSFRichTextString("RELACION DE OFICIOS EMITIDOS  "));				
    				c.setCellStyle(csz);
        			
        		}if (tipo_documento_ls.equals("3")){
        			c.setCellValue(new HSSFRichTextString("RELACION DE RESOLUCIONES EMITIDAS  "));				
    				c.setCellStyle(csz);
        			
        		}if (tipo_documento_ls.equals("4")){
        			c.setCellValue(new HSSFRichTextString("RELACION DE CARTAS EMITIDAS  "));				
    				c.setCellStyle(csz);
        			
        		}if (tipo_documento_ls.equals("5")){
        			c.setCellValue(new HSSFRichTextString("RELACION DE INFORMES EMITIDOS  "));				
    				c.setCellStyle(csz);
        			
        		}if (tipo_documento_ls.equals("6")){
        			c.setCellValue(new HSSFRichTextString("RELACION DE CCP'S EMITIDOS "));				
    				c.setCellStyle(csz);
        			
        		}if (tipo_documento_ls.equals("7")){
        			c.setCellValue(new HSSFRichTextString("RELACION DE TDR'S EMITIDOS  "));				
    				c.setCellStyle(csz);
        			
        		}if (tipo_documento_ls.equals("8")){
        			c.setCellValue(new HSSFRichTextString("RELACION DE MEMORANDUMS MULTIPLES EMITIDOS "));				
    				c.setCellStyle(csz);
        		}	

				/**pongo la fecha**/
        		HSSFCell ce = r.createCell((short) 3);
        		HSSFCell cd = r.createCell((short) 4);
        		ce.setCellValue(new HSSFRichTextString("Fecha: "));				
				ce.setCellStyle(csz);
				cd.setCellValue(new HSSFRichTextString(fechaActual()));				
				cd.setCellStyle(csz);
        		
        	}
        		
        	if(rownum == 1){
        		r.setHeight((short) 0x249);
        		
        		sheet1.setColumnWidth((short) 1, (short) (36*256));
        		HSSFCell c = r.createCell((short) 0);
        		c.setCellValue(new HSSFRichTextString("OFICINA : "+ls_oficinaz_reporte));	
        		c.setCellStyle(csz);
        		
        		HSSFCell ce = r.createCell((short) 3);
        		HSSFCell cd = r.createCell((short) 4);
        		ce.setCellValue(new HSSFRichTextString("Hora: "));				
				ce.setCellStyle(csz);
				cd.setCellValue(new HSSFRichTextString(horaActual()));				
				cd.setCellStyle(csz);
        		
        	}
        	if (rownum == 2){
        		if (!ls_fecha_inicio.equals("")){
        			r.setHeight((short) 0x249);
       			
        			if(!ls_fecha_fin.equals("")){
        				//imprimo 
                		sheet1.setColumnWidth((short) 1, (short) (36*256));
                		HSSFCell c = r.createCell((short) 0);
                		c.setCellValue(new HSSFRichTextString("Desde: "+ls_fecha_inicio+"   Hasta: "+ls_fecha_fin));	
         				c.setCellStyle(csz);

        			}else{
        				sheet1.setColumnWidth((short) 1, (short) (36*256));
        				HSSFCell c = r.createCell((short) 0);
                		c.setCellValue(new HSSFRichTextString("Desde: "+ls_fecha_inicio+"   Hasta:"+fechaActual()));	
         				c.setCellStyle(csz);
        			}
        			
        			
        		}else{
        			if(!ls_fecha_fin.equals("")){
        				sheet1.setColumnWidth((short) 1, (short) (36*256));
        				HSSFCell c = r.createCell((short) 0);
                		c.setCellValue(new HSSFRichTextString("Hasta: "+ls_fecha_fin));	
         				c.setCellStyle(csz);
        			}else{
        				sheet1.setColumnWidth((short) 1, (short) (36*256));
        				HSSFCell c = r.createCell((short) 0);
                		c.setCellValue(new HSSFRichTextString("Hasta: "+fechaActual()));	
         				c.setCellStyle(csz);
        			}
        		}
        		
        	/*	HSSFCell ce = r.createCell((short) 6);
        		HSSFCell cd = r.createCell((short) 7);

        		ce.setCellValue(new HSSFRichTextString("Motivo: "));				
				ce.setCellStyle(csz);
				cd.setCellValue(new HSSFRichTextString(" ls_codigo_motivo"));				
				cd.setCellStyle(csz);*/
        		
        	//}
        	}
        	if (rownum == 3){
        		r.setHeight((short) 0x249);
		
			for (int cellnum = 0; cellnum < cabecerareportefirmados.length; cellnum ++) {
				ponerAchoColumnasFirma(sheet1, cellnum);
				HSSFCell c = r.createCell((short) cellnum);
				c.setCellValue(new HSSFRichTextString(cabecerareportefirmados[cellnum]));				
				c.setCellStyle(cs);
				}
        	}
        	
        	} 
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

	private String horaActual() {
		Date fecha = new Date(); // fecha y hora locales 
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
		String hora=formatoHora.format(fecha);
		return hora;
	}
	
	private String fechaActual() {
		Date fecha = new Date(); // fecha y hora locales 
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
		String fecha_registro=formatoFecha.format(fecha);
		return fecha_registro;
	}
	
/*	private String generarNombreArchivo() {
		Date today = new Date();
		DateFormat df_es = 
		DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);//germany
		System.out.println(df_es.format(today) );		
		String sufijo= df_es.format(today) +"."+today.getHours()+"." +today.getMinutes()+"."+today.getSeconds();		
		System.out.println("sufijo: "+sufijo);
		return sufijo;
	}*/
}
