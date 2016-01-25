/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

package tramitedoc.concytec.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

//import java.sql.Date;
//import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.mapper.Mapper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import sun.font.FontManager;
import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;

import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.corba.se.spi.ior.WriteContents;


/*
 * Autor:Moises Pelaez Sarmiento
 * Fecha:23-02-2012
 * Descripcion: Clase que crea los archivos PDF usando Itext.
 * 
 * */
public class PDFcreator {
	protected  Log log = LogFactory.getLog(PDFcreator.class);
    /** Path to the resulting PDF file. */
    //public static final String RESULT
      //  = "results/part1/chapter01/hello.pdf";
	 public static final float[][] COLUMNS = {
	        { 36, 36, 296, 806 } , { 299, 36, 559, 806 }
	    };
  public static final String RESULT    = "D:/hello.pdf";
  public  final String nuevalinea    = "\n";
  public  final String tab    = "\t";
  public  final String dobletab    = "\t";
  public static final String lineaSeparator= new File (IConstante.filePathMembretes,"linea_separador.jpg").getAbsolutePath();
  public static final String comillaSimple=new String("");
   // public static final String RESULT    = "//pdf//hello.pdf";
    
    /**
     * Creates a PDF file: hello.pdf
     * @param    args    no arguments needed
     */
    public static void main(String[] args)
    	throws DocumentException, IOException {
    	new PDFcreator().createPdf(RESULT);
    	//new PDFcreator().createPdf(RESULT, "Hola archivo creado por Directorio Nacional de investigadores");
    }

    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws    DocumentException 
     * @throws    IOException 
     */
    public void createPdf(String filename)
	throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.addTitle("Titulo");
        document.open();
        // step 4
       
        document.add(new Paragraph("Hola Ing. Moises Pelaez Sarmiento!"));
      
        // step 5
        document.close();
    }
    
       
    public void ReportesGraficos() throws FileNotFoundException, IOException{
    	
    	DefaultPieDataset data = new DefaultPieDataset();
    	data.setValue("Aprobados", 30);
    	data.setValue("Desaprobados", 5);
    	
    	JFreeChart chart = ChartFactory.createPieChart(
    			"Reporte", 
    			data, 
    			true, //legend
    			true, //tooltips
    			false
    			);//urls
    	
    	ChartFrame frame = new ChartFrame("Primero", chart);
    	frame.pack();
    	frame.setVisible(true);
    	
    	//Guarda en PDF
    	File fileName = new File("c:/rptAlumnos.pdf");
    	createReport(fileName, chart, 400, 300, new DefaultFontMapper());
    	
    	
    }
    
    
    public void saveReport(OutputStream out, JFreeChart jfree, int ancho, int largo, FontMapper font){
    	Rectangle pagesize = new Rectangle(ancho, largo);
    	Document docu = new Document(pagesize, 50, 50, 50, 50);
    	
    	try{
    		PdfWriter writer=  PdfWriter.getInstance(docu, out);
    		docu.addAuthor("Jesus Azañero");
    		docu.addCreationDate();
    		docu.addTitle("Reporte con JFreeChart");
    		docu.addSubject("Generacion de Reporte");
    		docu.open();
    		
    		PdfContentByte cb = writer.getDirectContent();
    		PdfTemplate tp = cb.createTemplate(ancho, largo);
    		
    		Graphics2D g2 = tp.createGraphics(ancho, largo, font);
    		Rectangle2D r2d = new Rectangle2D.Double(0, 0, ancho, largo);
    		
    		jfree.draw(g2, r2d);
    		
    		g2.dispose();
    		cb.addTemplate(tp,0,0);
    		docu.close();
    		
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    public void createReport(File file, JFreeChart jfree, int ancho, int largo, FontMapper font) throws FileNotFoundException, IOException {
    	    		
			OutputStream out = new BufferedOutputStream(new FileOutputStream(file) );
			saveReport(out, jfree, ancho, largo,font);
			out.close();
		
		
    }
    
    public void createPdf(String filename, String parrafo)
    		throws DocumentException, IOException {
    	        // step 1
    	        Document document = new Document();
    	        // step 2
    	        PdfWriter.getInstance(document, new FileOutputStream(filename));
    	        // step 3
    	        document.open();
    	       
    	        // step 4
    	        document.add(new Paragraph(parrafo));
    	        // step 5
    	        document.close();
    	    }
    
    public void createPdf(String filename, DocumentoInternoBean documentoInterno,String nombre_tipo_documento,String nombreOficina,ArrayList<String>  siglasOficinasCopias, String nombre_oficina_destino,Image firmaDigitalizada,boolean llevaIniciales)
    		throws DocumentException, IOException {
    	        // step 1
    	       // Document document = new Document();
    			try {
					
				System.out.println("Entre aqui >>>>>>>>>");
				
				Funciones funciones = new Funciones();
				String iniciales = "";
				
				/*if(documentoInterno.getCodigo_tipo_documento_interno()==4){
				iniciales="-"+funciones.iniciales(documentoInterno.getFirmado_por());
				}*/
				if(llevaIniciales){
					iniciales = "-"+funciones.iniciales(documentoInterno.getFirmado_por());
				}
				
    	        Document document = new Document(PageSize.A4,90, 72, 56, 36);
    	        // step 2
    	        
    	        String ruta = IConstante.filePathFirmadosBackup;
    	        
    	        File file_1 =new File(ruta);
    	        file_1=  new File(file_1,filename);
    	        PdfWriter.getInstance(document, new FileOutputStream(file_1.getAbsolutePath()));
    	       // System.out.println (file_1.getCanonicalPath ());
    	        System.out.println (file_1.getAbsolutePath());
    	        
    	        System.out.println("Aqui1");
    	        // step 3 	        
    	        document.open();
    	          	       
    	        System.out.println("Aqui1.1-----");
    	        Chunk space = new Chunk(' ');
    	       System.out.println("---"+IConstante.filePathMembretes+documentoInterno.getNombreSelloOficina());
    	       String imagenSelloOficina= new File (IConstante.filePathMembretes,documentoInterno.getNombreSelloOficina()).getAbsolutePath();
    	       System.out.println("Aqui1.2-----");

    	     //agregamos la sello del estado
    	       	Image img1 = Image.getInstance(imagenSelloOficina);
	   			img1.setAlignment(Image.ALIGN_MIDDLE);
	   			img1.scalePercent(25);

	   			document.add(img1);
	   			document.add(Chunk.NEWLINE);	
    	       //agregamos el titulo del año
	   			agregar_nombre_anio(documentoInterno, comillaSimple, document);		    	   
	   			
	   			System.out.println("Aqui2");
	   			//agregamos  titulo de memorandum
	   			
	   			Font font = new Font(Font.BOLD, 14, Font.BOLD);
				font.setColor(new Color(0x00, 0x00, 0x00));
				
				Font font1 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
				font1.setColor(new Color(0x00, 0x00, 0x00));
				
				Font font2 = new Font(Font.HELVETICA , 11, Font.NORMAL);
				font2.setColor(new Color(0x00, 0x00, 0x00));
				
				Font font3 = new Font(Font.HELVETICA , 10, Font.BOLD);
				font3.setColor(new Color(0x00, 0x00, 0x00));
				
				
			//	Chunk tituloDocumento = new Chunk(nombre_tipo_documento.toUpperCase()+" nº "+documentoInterno.getNumero_doc()+"-"+getAnioActual()+"-CONCYTEC-" +nombreOficina, font);
				
				System.out.println("Aqui3");
				String numero_doc="";
				if(documentoInterno.getNumero_doc()<10){
					numero_doc="0"+documentoInterno.getNumero_doc();
				}else
				{
					numero_doc=numero_doc+documentoInterno.getNumero_doc();
				}
				Chunk tituloDocumento = new Chunk(nombre_tipo_documento.toUpperCase()+" Nº "+numero_doc+"-"+getAnioActual()+"-CONCYTEC-" +nombreOficina+iniciales, font);
				
				
				
				tituloDocumento.setBackground(new Color(0xD0, 0xD0, 0xD0));
				
				
				document.add(new Paragraph(space));	
			
				Paragraph paragraph = new Paragraph();
				paragraph.add(tituloDocumento);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				font = new Font(Font.BOLD, 11, Font.HELVETICA);
				font.setColor(new Color(0x00, 0x00, 0x00));
				
				
				/*
				 * Colummas
				 * 
				 * */
	
				
				/*
				 * fin/
				 */
				log.info("Aqui5");
				//para
				Phrase para = new Phrase("PARA",font);
				agregarEspacio(space, para,13);
				para.add(":");
				document.add(createFirstTable(para,documentoInterno.getAbreviatura_grado_profesion()+" "+documentoInterno.getDirigido_a()));
								
				//Cargo y nombre oficina destino				
				Phrase cargo = new Phrase("",font2);
				
				System.out.println(cargo);
				System.out.println(documentoInterno.getCargo_persona_destinatario());
				System.out.println(nombre_oficina_destino);
				if(documentoInterno.getCargo_persona_destinatario().equals("DIRECTOR") && nombre_oficina_destino.equals("SECRETARIA GENERAL")){
					document.add(createFirstTable(cargo,"SECRETARIO GENERAL"));
				}else{
					document.add(createFirstTable(cargo,documentoInterno.getCargo_persona_destinatario() +" DE " + nombre_oficina_destino));
				}
								
								
				//document.add(Chunk.NEWLINE);
				document.add(createFirstTable(new Phrase(), ""));
				document.add(createFirstTable(new Phrase(), ""));
				//Asunto
				
				Phrase asunto = new Phrase("ASUNTO        ",font);
				//agregarEspacio(space, asunto,0);
				asunto.add(":");
				
				document.add(createFirstTable(asunto,documentoInterno.getAsunto()));
				
				document.add(createFirstTable(new Phrase(), ""));
				document.add(createFirstTable(new Phrase(), ""));
				
				//Referencia
				log.info("***************");
				
				log.info(documentoInterno.getReferencia());
				log.info("***************");
				if(!documentoInterno.getReferencia().isEmpty()  && documentoInterno.getReferencia().length()>0){
				
				System.out.println("Aqui6");
				Phrase referencia = new Phrase("REFERENCIA",font);
				log.info("tamanio referencia1: "+referencia.size());
				agregarEspacio(space, referencia,0);							
				referencia.add(":");
				log.info("tamanio referencia2: "+referencia.size());
				document.add(createFirstTable(referencia,documentoInterno.getReferencia()));
				
				document.add(createFirstTable(new Phrase(), ""));
				document.add(createFirstTable(new Phrase(), ""));
				}
				
				//Fecha
				DateFormat df_pe =DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());
				//log.info("verrrrrrrrrr.  "+df_pe.format(new Date(System.currentTimeMillis())));


				//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Phrase fecha = new Phrase("FECHA",font);
				agregarEspacio(space, fecha,12);							
				//fecha.add(":San Borja, "+sdf.format(new Date(System.currentTimeMillis())));
				
				document.add(fecha);
				//document.add(Chunk.NEWLINE);
				
				Phrase cuerpofecha = new Phrase("",font2);
				//agregarEspacio(space, cargo,20);	
				
				cuerpofecha.add(": San Borja, "+df_pe.format(new Date(System.currentTimeMillis())));	
				
				document.add(cuerpofecha);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				
				
				//fecha.add(": San Borja, "+df_pe.format(new Date(System.currentTimeMillis())));
				//Dar Formato a Fecha
									
								
				//Linea separacion
				
				
				
				Image img2 = Image.getInstance(lineaSeparator);
	   			img2.setAlignment(Image.ALIGN_JUSTIFIED);
	   			img2.scalePercent(3100,50);
	   			document.add(img2);
				
	   			//document.add(Chunk.NEWLINE);
				
	   			font = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
				font.setColor(new Color(0x00, 0x00, 0x00));
				System.out.println("Aqui7");
				
				
				//Descripcion	cuerpo del documento
				StyleSheet styles = new StyleSheet();
			    styles.loadTagStyle("p", "text-align", "justify");
			    
				List<Element> objects  = HTMLWorker.parseToList(new StringReader(documentoInterno.getDescripcion()), styles);
	            for (Element element : objects){

	              /*  PdfPTable tbl = (PdfPTable) element;
			          
			           //so we can set the column widths
			           
	                if (tbl != null) {
	                  tbl.setWidths(new int[]{4, 2, 1});
	                }*/
	                document.add(element);
	              } 
                
            
            
			   /*Paragraph descripcion = new Paragraph(documentoInterno.getDescripcion(),font2);    	       
    	       descripcion.setAlignment(Element.ALIGN_JUSTIFIED);
    	       document.add(descripcion);*/
    	       
    	       
    	       document.add(Chunk.NEWLINE);
    	       document.add(Chunk.NEWLINE);
    	       //atentament
    	       Phrase atentamente = new Phrase("Atentamente,",font2);
    	       //atentamente.add(Element.ALIGN_CENTER);
    	      // agregarEspacio(space, atentamente,40);				
								
				
				Paragraph paragraph_atte = new Paragraph();
				paragraph_atte.add(atentamente);
				paragraph_atte.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph_atte);
				document.add(Chunk.NEWLINE);
	    	    
	    	   
				
	    	    if(firmaDigitalizada!=null){
	    	    	firmaDigitalizada.setAlignment(Image.ALIGN_RIGHT);
	    	    	
	    	    	document.add(firmaDigitalizada);
	    	    }
	    	    
	    	    
	   			
	   			
	    	    System.out.println("Aqui8");
	   		
	    	    //Firmado por
	    	    	Font font_b = new Font(Font.COURIER, 8, Font.COURIER);
					Phrase firmado_por = new Phrase(documentoInterno.getFirmado_por(),font_b);
		    	    document.add(createFirstTable(firmado_por));
		    	   
					 document.add(Chunk.NEWLINE);
			    	 document.add(Chunk.NEWLINE); 
		    	 //con copias
		    	   
			    	System.out.println("Aqui9");
		    	   if(!siglasOficinasCopias.isEmpty()  && siglasOficinasCopias.size()>0){
				   Phrase con_copia_oficinas = new Phrase("CC: "+ siglasOficinasCopias.toString(),font1);		
	    	       
	    	       Paragraph paragraph_con_copia_oficinas = new Paragraph();
	    	       paragraph_con_copia_oficinas.add(con_copia_oficinas);
	    	       paragraph_con_copia_oficinas.setAlignment(Element.ALIGN_LEFT);
				   document.add(paragraph_con_copia_oficinas);
				   System.out.println("Aqui92");
		    	   }
		    	   
		    	   
				   System.out.println("doc adjuntos >"+documentoInterno.getNombre_doc_adjuntos());
				   if(documentoInterno.getNombre_doc_adjuntos()!=null && !documentoInterno.getNombre_doc_adjuntos().isEmpty()  && documentoInterno.getNombre_doc_adjuntos().length()>0){
				   System.out.println("Aqui10"); 
				   
				   String cadenaTotal=null;
			  	   String[] cadena=null;
			  	   String cadenaTotalTemp="";
			  	   String cadenatemporal="";
			  	   cadenaTotal=documentoInterno.getNombre_doc_adjuntos();
				   
			  	   if(cadenaTotal!=null && cadenaTotal.length()>0){
		  				cadena=cadenaTotal.split(",");
		  				if(cadena!=null && cadena.length>0 ){
		  			  		 for (int  i = 0;  i < cadena.length; i++) {
		  			  			int pos = cadena[i].lastIndexOf('.');
		  			  			cadenatemporal = cadena[i].substring(0,pos);
		  			  			String marca = "1011";
		  			  			
		  			  		if(marca.equals(cadenatemporal.substring(0, 4))){
		  			  			cadenatemporal = cadenatemporal.substring(4, cadenatemporal.length());
	  			  				
	  			  			}
		  			  			cadenaTotalTemp = cadenaTotalTemp+cadenatemporal+", ";
		  					}

		  				}
		  				
		  				cadenaTotal=cadenaTotalTemp.substring(0, cadenaTotalTemp.length()-2);
			  	   }
				   
				   Phrase nombre_documentos_adjuntos = new Phrase("Adjuntos: "+ cadenaTotal,font1);		
	    	       
	    	       Paragraph paragraph_nombre_documentos_adjuntos = new Paragraph();
	    	       paragraph_nombre_documentos_adjuntos.add(nombre_documentos_adjuntos);
	    	       paragraph_nombre_documentos_adjuntos.setAlignment(Element.ALIGN_LEFT);
				   document.add(paragraph_nombre_documentos_adjuntos);
				   }
				   
				   //writer.close();
				   	document.close();
    	        
    				} catch(IOException ioe) {
    					throw new ExceptionConverter(ioe);
    				}
    				catch(DocumentException de) {
    				throw new ExceptionConverter(de);
    				}
    				
    	    }



    public PdfPTable createFirstTable(Phrase frase) throws DocumentException {
    	// a table with three columns
		Font font2 = new Font(Font.HELVETICA , 11, Font.NORMAL);
		font2.setColor(new Color(0x00, 0x00, 0x00));
		
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(435);
        table.setLockedWidth(true);        
        table.setWidths(new float[]{3, 2});
        // the cell object
        PdfPCell cell;
        // we add a cell with colspan 3
        cell = new PdfPCell(new Phrase());   
    
       cell.setBorder(Rectangle.NO_BORDER);
       System.out.println(" Ancho de Asuntoooooooooooo"+cell.getWidth());
        
        
        table.addCell(cell);
        // now we add a cell with rowspan 2
       
        cell = new PdfPCell(frase);       
        //cell.setBorder(Rectangle.TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Rectangle.ALIGN_JUSTIFIED_ALL);
        table.addCell(cell);
        
        // we add the four remaining cells with addCell()
        table.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
        System.out.println("porcentaje  " +table.getWidthPercentage());
        table.setWidthPercentage(100);
        System.out.println("porcentaje  " +table.getWidthPercentage());
        return table;
    }



	public void agregar_nombre_anio(DocumentoInternoBean documentoInterno,
		String comillaSimple, Document document) throws DocumentException {
		Font font_b = new Font(Font.COURIER, 8, Font.COURIER);
		font_b.setColor(new Color(0x00, 0x00, 0x00));
		Phrase titulo_anio_peru = new Phrase(comillaSimple+documentoInterno.getNombre_oficial_anio()+comillaSimple,font_b);
		Paragraph paragraph_titulo_anio = new Paragraph();
		paragraph_titulo_anio.add(titulo_anio_peru);
		paragraph_titulo_anio.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph_titulo_anio);
	}
	
    public void createPdf_Informe(String filename, DocumentoInternoBean documentoInterno,String nombre_tipo_documento,String nombreOficina,ArrayList<String>  siglasOficinasCopias, String nombre_oficina_destino,Image firmaDigitalizada)
	throws DocumentException, IOException {
        // step 1
       // Document document = new Document();
    	Funciones funciones = new Funciones();
    	String iniciales = funciones.iniciales(documentoInterno.getFirmado_por());
		Image img2 = Image.getInstance(lineaSeparator);
		Font font = new Font(Font.BOLD, 14, Font.BOLD);
		font.setColor(new Color(0x00, 0x00, 0x00));
		
		Font font2 = new Font(Font.HELVETICA , 11, Font.NORMAL);
		font2.setColor(new Color(0x00, 0x00, 0x00));
		
		Font font3 = new Font(Font.HELVETICA , 10, Font.BOLD);
		font3.setColor(new Color(0x00, 0x00, 0x00));
		
		
	
        Document document = new Document(PageSize.A4,90, 72, 56, 36);
        // step 2
        String ruta = IConstante.filePathFirmadosBackup;
                
        File file_1 =new File(ruta);
        file_1=  new File(file_1,filename);
        PdfWriter.getInstance(document, new FileOutputStream(file_1.getAbsolutePath()));
        // step 3 	        
        Chunk space = new Chunk(' ');
        document.open();
       //agregamos la sello del estado
     /*  	String rutaImageSello=IConstante.filePath;
       	String nombreImagenOficina="DSIC.jpg";*/
      
       
       String imagenSelloOficina= new File (IConstante.filePathMembretes,documentoInterno.getNombreSelloOficina()).getAbsolutePath();
       
       	//Image img1 = Image.getInstance("D://chapter05//DSIC.jpg");
       
   		Image img1 = Image.getInstance(imagenSelloOficina);
			img1.setAlignment(Image.ALIGN_MIDDLE);
			img1.scalePercent(25);
			document.add(img1);
			
			
			//Fecha
			DateFormat df_pe =DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());			
			Phrase cuerpofecha = new Phrase("",font2);
			//agregarEspacio(space, cuerpofecha,87);
			cuerpofecha.add("Lima, "+df_pe.format(new Date(System.currentTimeMillis())));
			
			Paragraph fechaDocumento = new Paragraph();
			fechaDocumento.add(Chunk.NEWLINE);
			fechaDocumento.add(Chunk.NEWLINE);
			
			fechaDocumento.add(cuerpofecha);
			fechaDocumento.setAlignment(Element.ALIGN_RIGHT);
			document.add(fechaDocumento);
			
		
			
			//agregamos  titulo de Informe
			
	
		
		Font font1 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
		font1.setColor(new Color(0x00, 0x00, 0x00));
		
		
		
		font2.setColor(new Color(0x00, 0x00, 0x00));
		String numero_doc="";
		if(documentoInterno.getNumero_doc()<10){
			numero_doc="0"+documentoInterno.getNumero_doc();
		}else
		{
			numero_doc=numero_doc+documentoInterno.getNumero_doc();
		}
		Chunk tituloDocumento = new Chunk(nombre_tipo_documento.toUpperCase()+" Nº "+numero_doc+"-"+getAnioActual()+"-CONCYTEC-" +nombreOficina+"-"+iniciales, font);
		
	
		Paragraph paragraph = new Paragraph();
		//paragraph.setAlignment(arg0)
		paragraph.add(tituloDocumento);
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		
		//Sr
		Phrase abreviatura = new Phrase(documentoInterno.getAbreviatura_grado_profesion(),font2);
		
		
		document.add(abreviatura);
		document.add(Chunk.NEWLINE);
		
		Phrase para = new Phrase(documentoInterno.getDirigido_a(),font);
		document.add(para);
		//Cargo y nombre oficina destino
		document.add(Chunk.NEWLINE);
		
		String cargo_completo="";
		
		if(documentoInterno.getCargo_persona_destinatario().equals("DIRECTOR") && nombre_oficina_destino.equals("SECRETARIA GENERAL")){
			cargo_completo= "SECRETARIO GENERAL";
		}else{
			cargo_completo= documentoInterno.getCargo_persona_destinatario() +" DE " + nombre_oficina_destino;
		}
		
		Phrase cargo = new Phrase(cargo_completo,font2);				
		document.add(cargo);		
		document.add(Chunk.NEWLINE);
		
		
		Phrase presente = new Phrase("Presente.-",font2);		
		document.add(presente);	
		
		//Asunto
		
		
		
		Phrase asunto = new Phrase("Asunto",font);
		agregarEspacio(space, asunto,4);
		asunto.add(":");		
		document.add(createFirstTable(asunto,documentoInterno.getAsunto()));
		document.add(Chunk.NEWLINE);
		
		
		
			
		/*Descripcion	cuerpo del documento			
		Paragraph descripcion = new Paragraph(documentoInterno.getDescripcion(),font2);    	       
		descripcion.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(descripcion);*/
		
		//Descripcion	cuerpo del documento
		StyleSheet styles = new StyleSheet();
	    styles.loadTagStyle("p", "text-align", "justify");
	    
		List<Element> objects  = HTMLWorker.parseToList(new StringReader(documentoInterno.getDescripcion()), styles);
        for (Element element : objects){

          /*  PdfPTable tbl = (PdfPTable) element;
	          
	           //so we can set the column widths
	           
            if (tbl != null) {
              tbl.setWidths(new int[]{4, 2, 1});
            }*/
            document.add(element);
          }
       
       
       document.add(Chunk.NEWLINE);
       document.add(Chunk.NEWLINE);
       //atentament
       Phrase atentamente = new Phrase("Atentamente,",font2);
       //atentamente.add(Element.ALIGN_CENTER);
      // agregarEspacio(space, atentamente,40);				
						
		
		Paragraph paragraph_atte = new Paragraph();
		paragraph_atte.add(atentamente);
		paragraph_atte.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph_atte);
		document.add(Chunk.NEWLINE);
	   
	    
		    
	    //Firmado por
			//Firmado por
	    if(firmaDigitalizada!=null){
	    	firmaDigitalizada.setAlignment(Image.ALIGN_RIGHT);
	    	
	    	document.add(firmaDigitalizada);
	    }
			
	    	Font font_b = new Font(Font.COURIER, 8, Font.COURIER);
			Phrase firmado_por = new Phrase(documentoInterno.getFirmado_por(),font_b);
    	    document.add(createFirstTable(firmado_por));
		   
		   document.add(Chunk.NEWLINE);
    	  
    	    
    	 //con copias

				   System.out.println("doc adjuntos >"+documentoInterno.getNombre_doc_adjuntos());
				   if(documentoInterno.getNombre_doc_adjuntos()!=null && !documentoInterno.getNombre_doc_adjuntos().isEmpty()  && documentoInterno.getNombre_doc_adjuntos().length()>0){
				   System.out.println("Aqui10"); 
				   
				   String cadenaTotal=null;
			  	   String[] cadena=null;
			  	   String cadenaTotalTemp="";
			  	   String cadenatemporal="";
			  	   cadenaTotal=documentoInterno.getNombre_doc_adjuntos();
				   
			  	   if(cadenaTotal!=null && cadenaTotal.length()>0){
		  				cadena=cadenaTotal.split(",");
		  				if(cadena!=null && cadena.length>0 ){
		  			  		 for (int  i = 0;  i < cadena.length; i++) {
		  			  			int pos = cadena[i].lastIndexOf('.');
		  			  			cadenatemporal = cadena[i].substring(0,pos);
		  			  			String marca = "1011";
		  			  			
		  			  		if(marca.equals(cadenatemporal.substring(0, 4))){
		  			  			cadenatemporal = cadenatemporal.substring(4, cadenatemporal.length());
				  				
				  			}
		  			  			cadenaTotalTemp = cadenaTotalTemp+cadenatemporal+", ";
		  					}
	
		  				}
		  				
		  				cadenaTotal=cadenaTotalTemp.substring(0, cadenaTotalTemp.length()-2);
			  	   }
				   
				   Phrase nombre_documentos_adjuntos = new Phrase("Adjuntos: "+ cadenaTotal,font1);		
	 	       
	 	       Paragraph paragraph_nombre_documentos_adjuntos = new Paragraph();
	 	       paragraph_nombre_documentos_adjuntos.add(nombre_documentos_adjuntos);
	 	       paragraph_nombre_documentos_adjuntos.setAlignment(Element.ALIGN_LEFT);
				   document.add(paragraph_nombre_documentos_adjuntos);
				   }

		   
        document.close();
    }
    
    public void createPdf_Oficio(String filename, DocumentoInternoBean documentoInterno,String nombre_tipo_documento,String nombreOficina,ArrayList<String>  siglasOficinasCopias, String nombre_oficina_destino, Image firmaDigitalizada)
	throws DocumentException, IOException {
        // step 1
       // Document document = new Document();
    	
		
		//Image img2 = Image.getInstance(lineaSeparator);
		Font font_ofi = new Font(Font.BOLD, 11, Font.BOLD);
		font_ofi.setColor(new Color(0x00, 0x00, 0x00));
		
		Font font = new Font(Font.BOLD, 14, Font.BOLD);
		font.setColor(new Color(0x00, 0x00, 0x00));
		
		Font font2 = new Font(Font.HELVETICA , 11, Font.NORMAL);
		font2.setColor(new Color(0x00, 0x00, 0x00));
		
		Font font3 = new Font(Font.HELVETICA , 10, Font.BOLD);
		font2.setColor(new Color(0x00, 0x00, 0x00));
	
        Document document = new Document(PageSize.A4,90, 72, 56, 36);
        // step 2
        String ruta = IConstante.filePathFirmadosBackup;
        
        File file_1 =new File(ruta);
        file_1=  new File(file_1,filename);
        PdfWriter.getInstance(document, new FileOutputStream(file_1.getAbsolutePath()));
        // step 3 	        
        Chunk space = new Chunk(' ');
        document.open();
       //agregamos la sello del estado
     /*  	String rutaImageSello=IConstante.filePath;
       	String nombreImagenOficina="DSIC.jpg";*/
       
       
       String imagenSelloOficina=   new File (IConstante.filePathMembretes,documentoInterno.getNombreSelloOficina()).getAbsolutePath();
       
       	//Image img1 = Image.getInstance("D://chapter05//DSIC.jpg");
       
   		Image img1 = Image.getInstance(imagenSelloOficina);
			img1.setAlignment(Image.ALIGN_MIDDLE);
			img1.scalePercent(25);
			document.add(img1);
			
			agregar_nombre_anio(documentoInterno, comillaSimple, document);	
			
			//Fecha
			DateFormat df_pe =DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());			
			Phrase cuerpofecha = new Phrase("",font2);
			agregarEspacio(space, cuerpofecha,87);
			//cuerpofecha.add("Lima, "+df_pe.format(new Date(System.currentTimeMillis())));
			String[] fec_array=df_pe.format(new Date(System.currentTimeMillis())).split(" ");
			String itemFull="";
			for (int i=1;i<fec_array.length;i++){
				itemFull=itemFull+fec_array[i]+ " ";
			}
			cuerpofecha.add("Lima, " +itemFull);
			Paragraph fechaDocumento = new Paragraph();
			fechaDocumento.add(Chunk.NEWLINE);
			fechaDocumento.add(Chunk.NEWLINE);
			
			//fechaDocumento.add(cuerpofecha);
			fechaDocumento.add(cuerpofecha);
			fechaDocumento.setAlignment(Element.ALIGN_RIGHT);
			document.add(fechaDocumento);
			
		
			
			//agregamos  titulo de Informe
			
	
		
		Font font1 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
		font1.setColor(new Color(0x00, 0x00, 0x00));
		
		
		
		font2.setColor(new Color(0x00, 0x00, 0x00));
		String numero_doc="";
		if(documentoInterno.getNumero_doc()<10){
			numero_doc="0"+documentoInterno.getNumero_doc();
		}else
		{
			numero_doc=numero_doc+documentoInterno.getNumero_doc();
		}
		Chunk tituloDocumento = new Chunk(nombre_tipo_documento.toUpperCase()+" Nº "+numero_doc+"-"+getAnioActual()+"-CONCYTEC-" +nombreOficina, font_ofi);
		
	
		Paragraph paragraph = new Paragraph();
		//paragraph.setAlignment(arg0)
		paragraph.add(tituloDocumento);
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		
		//Sr
		Phrase abreviatura = new Phrase(documentoInterno.getAbreviatura_grado_profesion(),font2);
		
		
		document.add(abreviatura);
		document.add(Chunk.NEWLINE);
		
		Phrase para = new Phrase(documentoInterno.getDirigido_a(),font);
		document.add(para);
		//Cargo y nombre oficina destino
		document.add(Chunk.NEWLINE);
		
		String cargo_completo="";
		
		System.out.println(documentoInterno.getCargo_persona_destinatario());
		System.out.println(nombre_oficina_destino);
		
		if(documentoInterno.getCargo_persona_destinatario().equals("DIRECTOR") && nombre_oficina_destino.equals("SECRETARIA GENERAL")){
			cargo_completo= "SECRETARIO GENERAL";
			Phrase cargo = new Phrase(cargo_completo,font2);				
			document.add(cargo);		
			document.add(Chunk.NEWLINE);

		}else{
			//cargo_completo= documentoInterno.getCargo_persona_destinatario() +" DE " + nombre_oficina_destino;
			Phrase cargo = new Phrase(documentoInterno.getCargo_persona_destinatario(),font2);				
			document.add(cargo);		
			document.add(Chunk.NEWLINE);
			
			cargo =new Phrase(nombre_oficina_destino,font2);	
			document.add(cargo);		
			document.add(Chunk.NEWLINE);
		}
		
		/*Phrase cargo = new Phrase(documentoInterno.getCargo_persona_destinatario(),font2);				
		document.add(cargo);		
		document.add(Chunk.NEWLINE);
		
		cargo =new Phrase(nombre_oficina_destino,font2);	
		document.add(cargo);		
		document.add(Chunk.NEWLINE);*/
		
		
		Phrase presente = new Phrase("Presente.-",font2);		
		document.add(presente);
		document.add(Chunk.NEWLINE);
	
		
		
		//Asunto
		
		Phrase asunto = new Phrase("Asunto:",font);
		//agregarEspacio(space, asunto,1);	
		document.add(createFirstTable(asunto, documentoInterno.getAsunto()));
		document.add(Chunk.NEWLINE);
		
		Phrase consideracion = new Phrase("De mi consideración:",font2);		
		document.add(consideracion);
		
			
		/*Descripcion	cuerpo del documento			
		Paragraph descripcion = new Paragraph(documentoInterno.getDescripcion(),font2);    	       
		descripcion.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(descripcion);*/
       
		//Descripcion	cuerpo del documento
		StyleSheet styles = new StyleSheet();
	    styles.loadTagStyle("p", "text-align", "justify");
	    
		List<Element> objects  = HTMLWorker.parseToList(new StringReader(documentoInterno.getDescripcion()), styles);
        for (Element element : objects){

          /*  PdfPTable tbl = (PdfPTable) element;
	          
	           //so we can set the column widths
	           
            if (tbl != null) {
              tbl.setWidths(new int[]{4, 2, 1});
            }*/
            document.add(element);
          }
       
       document.add(Chunk.NEWLINE);
       document.add(Chunk.NEWLINE);
       //atentament
       Phrase atentamente = new Phrase("                                                   Atentamente,",font2);
       //atentamente.add(Element.ALIGN_CENTER);
      	
		
		Paragraph paragraph_atte = new Paragraph();
		paragraph_atte.add(atentamente);
		paragraph_atte.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph_atte);
		
		
		document.add(Chunk.NEWLINE);
	    document.add(Chunk.NEWLINE);
	    
	    //Firmado por
    if(firmaDigitalizada!=null){
    	firmaDigitalizada.setAlignment(Image.ALIGN_RIGHT);
    	
    	document.add(firmaDigitalizada);
    }
	    
    Font font_b = new Font(Font.COURIER, 8, Font.COURIER);
	    Phrase firmado_por = new Phrase(documentoInterno.getFirmado_por(),font_b);
	    document.add(createFirstTable(firmado_por));
	    
	  
		//adjuntos
	    document.add(Chunk.NEWLINE);
		   System.out.println("doc adjuntos >"+documentoInterno.getNombre_doc_adjuntos());
		   if(documentoInterno.getNombre_doc_adjuntos()!=null && !documentoInterno.getNombre_doc_adjuntos().isEmpty()  && documentoInterno.getNombre_doc_adjuntos().length()>0){
		   System.out.println("Aqui10"); 
		   
		   String cadenaTotal=null;
	  	   String[] cadena=null;
	  	   String cadenaTotalTemp="";
	  	   String cadenatemporal="";
	  	   cadenaTotal=documentoInterno.getNombre_doc_adjuntos();
		   
	  	   if(cadenaTotal!=null && cadenaTotal.length()>0){
				cadena=cadenaTotal.split(",");
				if(cadena!=null && cadena.length>0 ){
			  		 for (int  i = 0;  i < cadena.length; i++) {
			  			int pos = cadena[i].lastIndexOf('.');
			  			cadenatemporal = cadena[i].substring(0,pos);
			  			String marca = "1011";
			  			
			  		if(marca.equals(cadenatemporal.substring(0, 4))){
			  			cadenatemporal = cadenatemporal.substring(4, cadenatemporal.length());
		  				
		  			}
			  			cadenaTotalTemp = cadenaTotalTemp+cadenatemporal+", ";
					}

				}
				
				cadenaTotal=cadenaTotalTemp.substring(0, cadenaTotalTemp.length()-2);
	  	   }
		   
		   Phrase nombre_documentos_adjuntos = new Phrase("Adjuntos: "+ cadenaTotal,font1);		
	       
	       Paragraph paragraph_nombre_documentos_adjuntos = new Paragraph();
	       paragraph_nombre_documentos_adjuntos.add(nombre_documentos_adjuntos);
	       paragraph_nombre_documentos_adjuntos.setAlignment(Element.ALIGN_LEFT);
		   document.add(paragraph_nombre_documentos_adjuntos);
		   }
		   
		   
		   
        document.close();
    }

    public void createPdf_Ccp(String filename, DocumentoInternoBean documentoInterno,String nombre_tipo_documento,String nombreOficina,ArrayList<String>  siglasOficinasCopias, String nombre_oficina_destino,String nombre_oficina_origen,Image firmaDigitalizada, String cargo_persona_remitente, String nombre_tipo_requerimiento)
    throws DocumentException, IOException {
    	try {
			System.out.println("Entre aqui >>>>>>>>>");
	        Document document = new Document(PageSize.A4,90, 72, 56, 36);
	        // step 2
	        String ruta = IConstante.filePathFirmadosBackup;
	        
	        File file_1 =new File(ruta);
	        
	        file_1=  new File(file_1,filename);
	        PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(file_1.getAbsolutePath()));
	        //System.out.println (file_1.getCanonicalPath ());
	        
	        System.out.println("Aqui1");
	        // step 3 	        
	        document.open();
	       //agregamos la sello del estado   	       
	        System.out.println("Aqui1.1---");
	       String imagenSelloOficina= new File (IConstante.filePathMembretes,documentoInterno.getNombreSelloOficina()).getAbsolutePath();
	       Chunk space = new Chunk(' ');
	       System.out.println("Aqui1.2---");
	       	//Image img1 = Image.getInstance("D://chapter05//DSIC.jpg");
	       //System.out.println(imagenSelloOficina);
	       	Image img1 = Image.getInstance(imagenSelloOficina);
   			img1.setAlignment(Image.ALIGN_MIDDLE);
   			img1.scalePercent(25);
   		
   			//document.add(img1);
   			document.add(Chunk.NEWLINE);	
	       //agregamos el titulo del año
   			agregar_nombre_anio(documentoInterno, comillaSimple, document);		    	   
   			
   			System.out.println("Aqui2");
   			//agregamos  titulo de memorandum
   			
   			Font font0 = new Font(Font.BOLD, 13, Font.BOLD);
			font0.setColor(new Color(0x00, 0x00, 0x00));
   			
   			Font font = new Font(Font.BOLD, 14, Font.BOLD);
			font.setColor(new Color(0x00, 0x00, 0x00));
			
			Font font1 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
			font1.setColor(new Color(0x00, 0x00, 0x00));
			
			Font font2 = new Font(Font.HELVETICA , 11, Font.NORMAL);
			font2.setColor(new Color(0x00, 0x00, 0x00));
			
			Font font3 = new Font(Font.HELVETICA , 10, Font.BOLD);
			font3.setColor(new Color(0x00, 0x00, 0x00));
			
			
		//	Chunk tituloDocumento = new Chunk(nombre_tipo_documento.toUpperCase()+" nº "+documentoInterno.getNumero_doc()+"-"+getAnioActual()+"-CONCYTEC-" +nombreOficina, font);
			
			System.out.println("Aqui3");
			String numero_doc="";
			if(documentoInterno.getNumero_doc()<10){
				numero_doc="0"+documentoInterno.getNumero_doc();
			}else
			{
				numero_doc=numero_doc+documentoInterno.getNumero_doc();
			}
			//Chunk tituloDocumento = new Chunk("SOLICITUD DE "+nombre_tipo_documento.toUpperCase()+" Nº "+numero_doc+"-"+getAnioActual()+"-" +nombreOficina, font);
			Chunk tituloDocumento = new Chunk("MEMORANDO DE REQUERIMIENTO - Nº "+numero_doc+"/"+getAnioActual()+"/CONCYTEC/" +nombreOficina, font0);
			
			tituloDocumento.setBackground(new Color(0xD0, 0xD0, 0xD0));
						
			document.add(new Paragraph(space));	
		
			Paragraph paragraph = new Paragraph();
			paragraph.add(tituloDocumento);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			
			font = new Font(Font.BOLD, 11, Font.HELVETICA);
			font.setColor(new Color(0x00, 0x00, 0x00));
			
			
			log.info("Aqui5");
			//para
			Phrase para = new Phrase("A",font);
			agregarEspacio(space, para,20);
			para.add(":");
			document.add(createFirstTable(para,documentoInterno.getAbreviatura_grado_profesion()+" "+documentoInterno.getDirigido_a()));
			//document.add(createFirstTable(para,"Oficina General de Administración"));	

			//Cargo y nombre oficina destino				
			Phrase cargo = new Phrase("",font2);
			document.add(createFirstTable(cargo,documentoInterno.getCargo_persona_destinatario() +" DE " + nombre_oficina_destino));
			
			//document.add(Chunk.NEWLINE);
			document.add(createFirstTable(new Phrase(), ""));
			
			log.info("Aqui5-2");
			//para
			Phrase dez = new Phrase("DE",font);
			agregarEspacio(space, dez,18);
			dez.add(":");
			document.add(createFirstTable(dez,documentoInterno.getAbreviatura_grado_profesion()+" "+documentoInterno.getFirmado_por()));
			
			
			//Cargo y nombre oficina destino				
			cargo = new Phrase("",font2);
			document.add(createFirstTable(cargo,cargo_persona_remitente+" DE " + nombre_oficina_origen));
			
			//document.add(Chunk.NEWLINE);
			document.add(createFirstTable(new Phrase(), ""));
			
			
			//document.add(Chunk.NEWLINE);
			//document.add(createFirstTable(new Phrase(), ""));
			//document.add(createFirstTable(new Phrase(), ""));
			//Asunto
			
			Phrase asunto = new Phrase("ASUNTO        ",font);
			//agregarEspacio(space, asunto,0);
			asunto.add(":");
			
			document.add(createFirstTable(asunto,documentoInterno.getAsunto()));
			
			document.add(createFirstTable(new Phrase(), ""));
			document.add(createFirstTable(new Phrase(), ""));
			
			//Referencia
			log.info("***************");
			
			log.info(documentoInterno.getReferencia());
			log.info("***************");
			if(!documentoInterno.getReferencia().isEmpty()  && documentoInterno.getReferencia().length()>0){
			
				System.out.println("Aqui6");
			Phrase referencia = new Phrase("REFERENCIA",font);
			log.info("tamanio referencia1: "+referencia.size());
			agregarEspacio(space, referencia,0);							
			referencia.add(":");
			log.info("tamanio referencia2: "+referencia.size());
			document.add(createFirstTable(referencia,documentoInterno.getReferencia()));
			
			document.add(createFirstTable(new Phrase(), ""));
			document.add(createFirstTable(new Phrase(), ""));
			}
			
			//Fecha
			DateFormat df_pe =DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());
			//log.info("verrrrrrrrrr.  "+df_pe.format(new Date(System.currentTimeMillis())));


			//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Phrase fecha = new Phrase("FECHA",font);
			agregarEspacio(space, fecha,12);							
			//fecha.add(":San Borja, "+sdf.format(new Date(System.currentTimeMillis())));
			
			document.add(fecha);
			//document.add(Chunk.NEWLINE);
			
			
			Phrase cuerpofecha = new Phrase("",font2);
			//agregarEspacio(space, cargo,20);	
			
			cuerpofecha.add(": San Borja, "+df_pe.format(new Date(System.currentTimeMillis())));	
			
			document.add(cuerpofecha);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			
			
			//fecha.add(": San Borja, "+df_pe.format(new Date(System.currentTimeMillis())));
			//Dar Formato a Fecha
								
							
			//Linea separacion
			
			/********************CUERPO ***************************/
			
			Image img2 = Image.getInstance(lineaSeparator);
   			img2.setAlignment(Image.ALIGN_JUSTIFIED);
   			img2.scalePercent(3100,50);
   			document.add(img2);
			
   			//document.add(Chunk.NEWLINE);
			
   			font = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
			font.setColor(new Color(0x00, 0x00, 0x00));
			System.out.println("Aqui7");
			//Descripcion	cuerpo del documento			
			Paragraph descripcion = new Paragraph(documentoInterno.getMotivo(),font2);    	       
	      
	      
	       descripcion.setAlignment(Element.ALIGN_JUSTIFIED);
	       document.add(descripcion);
	       document.add(Chunk.NEWLINE);
	       //document.add(Chunk.NEWLINE);
	       
			
			/*Phrase distribucion = new Phrase("1.  Objeto del Requerimiento:",font);
			document.add(distribucion);
			*/
			document.add(Tabla_Distribucion_Nueva(documentoInterno,nombre_tipo_requerimiento));
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			
			/*if(!documentoInterno.getNota().isEmpty()  && documentoInterno.getNota().length()>0){
				Phrase nota = new Phrase("Nota: "+documentoInterno.getNota(),font);
				document.add(nota);
			}*/
			
			//atentament
		       Phrase atentamente = new Phrase("Atentamente,",font2);
		       //atentamente.add(Element.ALIGN_CENTER);
		      // agregarEspacio(space, atentamente,40);				
								
				
				Paragraph paragraph_atte = new Paragraph();
				paragraph_atte.add(atentamente);
				paragraph_atte.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph_atte);
				document.add(Chunk.NEWLINE);
			   
			    
				    
			    //Firmado por
					//Firmado por
		/*	    if(firmaDigitalizada!=null){
			    	firmaDigitalizada.setAlignment(Image.ALIGN_RIGHT);
			    	
			    	document.add(firmaDigitalizada);
			    }*/
					
			    Font font_b = new Font(Font.COURIER, 8, Font.COURIER);
					Phrase firmado_por = new Phrase(documentoInterno.getFirmado_por(),font_b);
		    	    document.add(createFirstTable(firmado_por));
				   
			//	   document.add(Chunk.NEWLINE);
		    	  
		    	    
		    	 //con copias
		    	   

			
			   // writer.close();
			   	document.close();
	        
				} catch(IOException ioe) {
					throw new ExceptionConverter(ioe);
				}
				catch(DocumentException de) {
				throw new ExceptionConverter(de);
				} 
		
    }
    
    public void createPdf_Tdr(String filename, DocumentoInternoBean documentoInterno,String nombre_tipo_documento,String nombreOficina,ArrayList<String>  siglasOficinasCopias, String nombre_oficina_destino,Image firmaDigitalizada)
	throws DocumentException, IOException {
    	try {
			
			System.out.println("Entre aqui TDR>>>>>>>>>");
	        Document document = new Document(PageSize.A4,90, 72, 56, 36);
	        // step 2
	        String ruta = IConstante.filePathFirmadosBackup;
	        File file_1 =new File(ruta);
	        
	        file_1=  new File(file_1,filename);
	        PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(file_1.getAbsolutePath()));
	        //System.out.println (file_1.getCanonicalPath ());
	        
	        System.out.println("Aqui1");
	        // step 3 	        
	        Chunk space = new Chunk(' ');
	   
	       document.open();
	       //agregamos la sello del estado   	       
	       
	       String imagenSelloOficina= new File (IConstante.filePathMembretes,documentoInterno.getNombreSelloOficina()).getAbsolutePath();
	       
	       
	       	//Image img1 = Image.getInstance("D://chapter05//DSIC.jpg");
	       //System.out.println(imagenSelloOficina);
	       	Image img1 = Image.getInstance(imagenSelloOficina);
   			img1.setAlignment(Image.ALIGN_MIDDLE);
   			img1.scalePercent(25);
   		
   			//document.add(img1);
   			document.add(Chunk.NEWLINE);	
	       //agregamos el titulo del año
   			//agregar_nombre_anio(documentoInterno, comillaSimple, document);		    	   
   			
   			System.out.println("Aqui2");
   			//agregamos  titulo de memorandum
   			
   			Font font = new Font(Font.BOLD, 14, Font.BOLD);
			font.setColor(new Color(0x00, 0x00, 0x00));
			
			Font font1 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
			font1.setColor(new Color(0x00, 0x00, 0x00));
			
			Font font2 = new Font(Font.HELVETICA , 11, Font.NORMAL);
			font2.setColor(new Color(0x00, 0x00, 0x00));
			
			Font font3 = new Font(Font.HELVETICA , 10, Font.BOLD);
			font3.setColor(new Color(0x00, 0x00, 0x00));
			
			
		//	Chunk tituloDocumento = new Chunk(nombre_tipo_documento.toUpperCase()+" nº "+documentoInterno.getNumero_doc()+"-"+getAnioActual()+"-CONCYTEC-" +nombreOficina, font);
			
			System.out.println("Aqui3");
			String numero_doc="";
			if(documentoInterno.getNumero_doc()<10){
				numero_doc="0"+documentoInterno.getNumero_doc();
			}else
			{
				numero_doc=numero_doc+documentoInterno.getNumero_doc();
			}
			
			/***CABECERA****/
			
			Chunk encabezado = new Chunk("FORMATO DE REQUERIMIENTO DE CONTRATACIONES MENORES O IGUALES A 3 UIT", font3);
			Paragraph paragraph = new Paragraph();
			paragraph.add(encabezado);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
			document.add(Chunk.NEWLINE);
			
			
			encabezado = new Chunk("("+documentoInterno.getAsunto().toUpperCase()+")"+Chunk.NEWLINE+"REQUERIMIENTO DE BIENES / SERVICIO Nº"+numero_doc, font3);
			paragraph = new Paragraph();
			paragraph.add(encabezado);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
			document.add(Chunk.NEWLINE);
			
			/******tabla **********/
			document.add(Tabla_Fecha(documentoInterno));
			/*****FIN *****/
			document.add(Chunk.NEWLINE);
			/******tabla **********/
			document.add(Tabla_Body(documentoInterno));
			/*****FIN *****/
			
			document.add(Chunk.NEWLINE);
			   
		    
		    
		    //Firmado por
				//Firmado por
		    if(firmaDigitalizada!=null){
		    	firmaDigitalizada.setAlignment(Image.ALIGN_LEFT);
		    	
		    	document.add(firmaDigitalizada);
		    }
				
		    Font font_b = new Font(Font.COURIER, 8, Font.COURIER);
				Phrase firmado_por = new Phrase("Firma y sello/Área Usuaria",font_b);
	    	    document.add(createFirstTable(firmado_por));
			
			   // writer.close();
			   	document.close();
	        
				} catch(IOException ioe) {
					throw new ExceptionConverter(ioe);
				}
				catch(DocumentException de) {
				throw new ExceptionConverter(de);
				} 
		
    }
 
    private PdfPTable Tabla_Fecha(DocumentoInternoBean documentoInterno)throws DocumentException{    
        
    	/******tabla interna ***********/
    	Date fecha = new Date(); // fecha y hora locales 
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
		System.out.println("la fecha es.."+ formatoFecha.format(fecha) ); 
		System.out.println("la hora es.."+ formatoHora.format(fecha) ); 
		String fecha_actual=formatoFecha.format(fecha);
		String fechafacil[];
		fecha_actual=fecha_actual.trim();
		fechafacil=fecha_actual.split("/");
		
    	PdfPTable mitablacompleja=new PdfPTable(3);

    	
    	mitablacompleja.setTotalWidth(200);
    	mitablacompleja.setLockedWidth(true);        
    	mitablacompleja.setWidths(new float[]{0.9f, 0.9f,0.9f});

    	         PdfPCell celda =new PdfPCell (new Paragraph(fechafacil[0],
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  
    	         
    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(fechafacil[1],
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  
    	         
    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(fechafacil[2],
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  
    	         
    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         mitablacompleja.addCell(celda);
    	         
    	         /****/
    	         celda =new PdfPCell (new Paragraph("DIA",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  
    	         
    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("MES",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  
    	         
    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("AÑO",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  
    	         
    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         mitablacompleja.addCell(celda);
			
		return mitablacompleja;        
    }
    
    
    
 private PdfPTable Tabla_Body(DocumentoInternoBean documentoInterno)throws DocumentException{    
        
    	/******tabla interna ***********/
    	PdfPTable mitablacompleja=new PdfPTable(3);
    	mitablacompleja.setTotalWidth(435);
    	mitablacompleja.setLockedWidth(true);
    	
    	float[] medidaCeldas = {0.55f,0.1f, 8};

    	// ASIGNAS LAS MEDIDAS A LA TABLA (ANCHO)
    	mitablacompleja.setWidths(medidaCeldas);
    	
    	//mitablacompleja.setTotalWidth(435);
    	
    	//mitablacompleja.setLockedWidth(true);        
    	//mitablacompleja.setWidths(new float[]{0.9f, 4});

    	//I
    	         PdfPCell celda =new PdfPCell (new Paragraph("I.",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("ÁREA USUARIA:",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /****************/
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getAsunto()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /********************/
    	   
    	         //II
    	         celda =new PdfPCell (new Paragraph("II.",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("OBJETIVO DE LA CONTRATACIÓN:",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /****************/
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getObjetivo()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /**********************/
    	         
    	         //III
    	         celda =new PdfPCell (new Paragraph("III.",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("JUSTIFICACIÓN DEL REQUERIMIENTO:",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /****************/
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getJustificacion()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /**********************/
    	         //IV
    	         celda =new PdfPCell (new Paragraph("IV.",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("FINALIDAD:",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /****************/
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getFinalidad()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /**********************/
    	         
    	         //V
    	         celda =new PdfPCell (new Paragraph("V.",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("ACTIVIDADES REALIZADAS:",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /****************/
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getActividad()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /**********************/
    	         
    	         //VI
    	         celda =new PdfPCell (new Paragraph("VI.",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("ENTREGABLES:",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /****************/
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getEntregable()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /**********************/
    	         
    	         //VII
    	         celda =new PdfPCell (new Paragraph("VII.",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("PLAZO DE EJECUCIÓN:",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /****************/
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getPlazo()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /**********************/
    	         //VIII
    	         celda =new PdfPCell (new Paragraph("VIII.",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("VALOR ESTIMADO DE LA CONTRATACIÓN:",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /****************/
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getValor()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /**********************/
    	         
    	         //IX
    	         celda =new PdfPCell (new Paragraph("IX.",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("FUENTE DE FINANCIAMIENTO:",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /****************/
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getFinanciamiento()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /**********************/
    	         
    	         //X
    	         celda =new PdfPCell (new Paragraph("X.",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("MODALIDAD DE PAGO:",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /****************/
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getModalidad()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /**********************/
    	         
    	         //XI
    	         celda =new PdfPCell (new Paragraph("XI.",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("RESPONSABLES DE COORDINACIÓN DEL PROCESO:",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /****************/
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getResponsable_coordinacion()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /**********************/
    	         
    	         //XII
    	         celda =new PdfPCell (new Paragraph("XII.",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("RESPONSABLE DE DAR CONFORMIDAD A LA PRESTACIÓN:",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /****************/
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(" ",
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getResponsable_conformidad()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         
    	         mitablacompleja.addCell(celda);
    	         /**********************/
    	         
		return mitablacompleja;        
    }
 
    private PdfPTable Tabla_Simple(DocumentoInternoBean documentoInterno)throws DocumentException{    
    	         
    	/******tabla interna ***********/
    	PdfPTable mitablacompleja=new PdfPTable(2);

    	
    	mitablacompleja.setTotalWidth(425);
    	mitablacompleja.setLockedWidth(true);        
    	mitablacompleja.setWidths(new float[]{0.9f, 4});

    	         PdfPCell celda =new PdfPCell (new Paragraph("El presente tiene por finalidad solicitar la Certificación de Crédito Presupuestal para:"+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

    	         celda.setColspan(2);

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

    	         celda.setBorder(Rectangle.NO_BORDER);
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getMotivo()+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  
    	         celda.setColspan(2);

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
    	         
    	         celda.setBorder(Rectangle.NO_BORDER);
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getDescripcion()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  
    	         celda.setColspan(2);

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

    	         celda.setBorder(Rectangle.NO_BORDER);
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph("Actividad programada en el POI "+getAnioActual(),
    	                 FontFactory.getFont("helvetica",11,Font.BOLD,Color.BLACK)));  
    	         celda.setColspan(2);

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

    	         celda.setBorder(Rectangle.NO_BORDER);
    	         mitablacompleja.addCell(celda);
    	         
    	         celda =new PdfPCell (new Paragraph(documentoInterno.getActividad()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
    	                 FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  
    	         celda.setColspan(2);

    	         celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);

    	         celda.setBorder(Rectangle.NO_BORDER);
    	         mitablacompleja.addCell(celda);
    	
    	/****tabla madre***********/
		PdfPTable mitabla=new PdfPTable(2);
		mitabla.setTotalWidth(435);
		mitabla.setLockedWidth(true);        
		mitabla.setWidths(new float[]{0.9f, 4});
		PdfPCell celdaz =new PdfPCell(mitablacompleja);
		celdaz.setColspan(2);
		mitabla.addCell(celdaz);
    	
			
		return mitabla;        
    }
    private PdfPTable Tabla_Distribucion(DocumentoInternoBean documentoInterno)throws DocumentException{    
        
    	/******tabla interna ***********/
    	PdfPTable mitablacompleja=new PdfPTable(4);

    	
    	mitablacompleja.setTotalWidth(435);
    	
    	mitablacompleja.setLockedWidth(true);        
    	//mitablacompleja.setWidths(new float[]{0.9f, 4});

    	//encabezado
    	PdfPCell celda =new PdfPCell (new Paragraph("Meta Presupuestal",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph("Específica del Gasto",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph("Valor Total",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph("Observaciones",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        mitablacompleja.addCell(celda);
    	
        
        //bucle para pintar las fila
  		int numerofilas=0;
  		String cadenaMeta[]=null;
		String cadenaEspecifica[]=null;
		String cadenaValor[]=null;
		String cadenaObservacion[]=null;
        
    	numerofilas = documentoInterno.getNumerofilas().intValue();
    	log.info("numerofilas -------"+numerofilas);
		cadenaMeta = documentoInterno.getMeta().split("%/");
  		cadenaEspecifica = documentoInterno.getEspecifica().split("%/");
  		cadenaValor = documentoInterno.getValor().split("%/");
  		cadenaObservacion = documentoInterno.getObservacion().split("%/");
    	
    	for(int z=0;z<numerofilas;z++){
    		for(int j=0;j<4;j++){
    			if (j == 0){
    				celda =new PdfPCell (new Paragraph(cadenaMeta[z],
       	            FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK))); 
				 }
				  if (j == 1){
					  celda =new PdfPCell (new Paragraph(cadenaEspecifica[z],
			       	  FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK))); 
				  }
				  if (j == 2){
					  celda =new PdfPCell (new Paragraph(cadenaValor[z],
			       	  FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK))); 
				  }
				  if (j == 3){
					  celda =new PdfPCell (new Paragraph(cadenaObservacion[z],
			       	  FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK))); 
				  }
				  
     	         celda.setHorizontalAlignment(Element.ALIGN_LEFT);
       	         mitablacompleja.addCell(celda);
    			
    		}
   		
    	}
    	 	
		return mitablacompleja;        
    }
   
private PdfPTable Tabla_Distribucion_Nueva(DocumentoInternoBean documentoInterno, String nombre_tipo_requerimiento)throws DocumentException{    
        
    	/******tabla interna ***********/
    	PdfPTable mitablacompleja=new PdfPTable(3);
    	mitablacompleja.setTotalWidth(435);
    	mitablacompleja.setLockedWidth(true);
    	
    	float[] medidaCeldas = {0.55f,0.1f, 8};
    	mitablacompleja.setWidths(medidaCeldas);
    
    	//1era Linea
    	PdfPCell celda =new PdfPCell (new Paragraph("1.",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph(" ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
               
        celda =new PdfPCell (new Paragraph("Objeto del Requerimiento:",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
        
      //2da Linea
    	celda =new PdfPCell (new Paragraph("  ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph("  ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
                
        celda =new PdfPCell (new Paragraph(nombre_tipo_requerimiento+""+Chunk.NEWLINE,
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
      //2da Linea-A
    	celda =new PdfPCell (new Paragraph("  ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph("  ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
                
        celda =new PdfPCell (new Paragraph(documentoInterno.getDescripcion()+""+Chunk.NEWLINE+""+Chunk.NEWLINE ,
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
      //3era Linea
    	celda =new PdfPCell (new Paragraph("2.",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph(" ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
                
        celda =new PdfPCell (new Paragraph("Actividades del POI:",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
      //4era Linea
    	celda =new PdfPCell (new Paragraph("  ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph("  ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
                
        celda =new PdfPCell (new Paragraph(documentoInterno.getActividad()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
      //5era Linea
    	celda =new PdfPCell (new Paragraph("3.",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph(" ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
                
        celda =new PdfPCell (new Paragraph("Meta Presupuestal:",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
      //6era Linea
    	celda =new PdfPCell (new Paragraph("  ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph("  ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
                
        celda =new PdfPCell (new Paragraph(documentoInterno.getMeta()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
      //7era Linea
    	celda =new PdfPCell (new Paragraph("4.",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph(" ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
                
        celda =new PdfPCell (new Paragraph("Otras características técnicas:",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
        
      //8era Linea
    	celda =new PdfPCell (new Paragraph("  ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph("  ",
                FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);
        
        celda =new PdfPCell (new Paragraph(documentoInterno.getObservacion()+""+Chunk.NEWLINE+""+Chunk.NEWLINE,
        FontFactory.getFont("helvetica",11,Font.NORMAL,Color.BLACK)));  

        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        celda.setBorder(Rectangle.NO_BORDER);
        mitablacompleja.addCell(celda);

		return mitablacompleja;        
    }
     
	private void agregarEspacio(Chunk space, Phrase para,int num) {
		
		for(int i=0;i<num;i++){
			para.add(space);
		}
		
	}
	private String getAnioActual() {
		Calendar c = new GregorianCalendar(); 
		String anio = Integer.toString(c.get(Calendar.YEAR));
		return anio;
	}
	
	public PdfPTable createFirstTable(Phrase etiqueta,String texto) throws DocumentException {
    	// a table with three columns
		Font font2 = new Font(Font.HELVETICA , 11, Font.NORMAL);
		font2.setColor(new Color(0x00, 0x00, 0x00));
		
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(435);
        table.setLockedWidth(true);        
        table.setWidths(new float[]{0.9f, 4});
        // the cell object
        PdfPCell cell;
        // we add a cell with colspan 3
        cell = new PdfPCell(etiqueta);  
    
       cell.setBorder(Rectangle.NO_BORDER);
       System.out.println(" Ancho de Asuntoooooooooooo"+cell.getWidth());
       table.addCell(cell);
        // now we add a cell with rowspan 2
       
        cell = new PdfPCell(new Phrase(texto,font2));       
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        
        // we add the four remaining cells with addCell()
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        System.out.println("porcentaje  " +table.getWidthPercentage());
        table.setWidthPercentage(100);
        System.out.println("porcentaje  " +table.getWidthPercentage());
        return table;
    }
    
    
}