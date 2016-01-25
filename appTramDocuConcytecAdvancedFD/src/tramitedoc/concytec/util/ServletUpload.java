package tramitedoc.concytec.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet implementation class ServletUpload
 */
public class ServletUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//String dato=request.getParameter("rutaDestinoFirmado");
		HttpSession session = request.getSession(true);
		
		String pdf64=request.getParameter("param");
		
		String nombre=request.getParameter("nombre");
		String tipoFirma=request.getParameter("tipoFirma");
		
		System.out.println("");
		System.out.println("nombre--> "+nombre);
		System.out.println("tipoFirma--> "+tipoFirma);
		
		String namePdf=Constantes.CarpetaArchivosDesarrolloenTramiteVisados.getNombre()+nombre;
		
		if(tipoFirma.equals("1")){
			namePdf=Constantes.CarpetaArchivosDesarrolloenTramiteFirmados.getNombre()+nombre;
		}
		
		System.out.println("====================");
		System.out.println("Entre a Subir PDF");
		byte[] pdfByte=Base64.decode(pdf64);
		
		if(pdfByte!=null){
			escribirFile(pdfByte, namePdf); 
		}
		
		String usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
		System.out.println(usuario_actual); 
	}
	
	private void escribirFile(byte[] pdf,String namePdf) throws IOException{

		File file = new File(namePdf);
		file.createNewFile();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pdf);
		OutputStream outputStream = new FileOutputStream(namePdf);
		int data;
		while ((data = byteArrayInputStream.read()) != -1) {
			outputStream.write(data);
		}
		
		outputStream.close();
		byteArrayInputStream.close();
	}

}
