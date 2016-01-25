package tramitedoc.concytec.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.util.Constantes;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class AActionSubirPDFFirmado extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		//String dato=request.getParameter("rutaDestinoFirmado");
		String pdf64=request.getParameter("param");
		String nombre=request.getParameter("nombre");
		String namePdf="C:"+Constantes.CarpetaArchivosDesarrolloenTramiteFirmados.getNombre()+nombre;
		System.out.println("====================");
		System.out.println("Entre a Subir PDF");
		System.out.println("Ruta "+namePdf);
		byte[] pdfByte=Base64.decode(pdf64);
		
		if(pdfByte!=null){
			escribirFile(pdfByte, namePdf); 
		}
		 
		//FileInputStream is=new FileInputStream(file);
		
		//copiando pdf en directorio temporal
		request.setAttribute("servicioExito", "SI");
		//return mapping.findForward("exito");
		return null;
	}
	
	
	private static void escribirFile(byte[] pdf,String namePdf) throws IOException{

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

	/*private void eliminarFichero(String pstrCodDoc){
		String pathSep = File.separator;
		String path =getServletContext().getRealPath("/")+"TempPDF"+pathSep+pstrCodDoc+".pdf";

		File f = new File(path);
		if ( f.exists() ){
			System.out.println("method:eliminarFichero | INFO | class:com.servlet.DownloadFichero | Eliminar archivo de la carpeta TempPDF");
	
			try {
				if (f.delete())
					System.out.println("method:eliminarFichero | INFO | class:com.servlet.DownloadFichero | Archivo elminado");
				else
					System.out.println("method:eliminarFichero | INFO | class:com.servlet.DownloadFichero | No se pudo eliminar archivo");
			} catch (Exception e) {
				System.out.println("method:eliminarFichero | ERROR | class:com.servlet.DownloadFichero | error= "+ e.getMessage());
			}			 
		}else{
			System.out.println("method:eliminarFichero | INFO | class:com.servlet.DownloadFichero | No existe el archivo para ser eliminado");
			
		}
	}*/	
}
