package tramitedoc.concytec.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;

import tramitedoc.concytec.util.CipherWord;
//import directoriocti.concytec.util.UDataConstant;
import tramitedoc.concytec.util.Constantes;

public class AActionFirmaDigital extends Action {
	private static Logger logger = Logger.getLogger(AActionFirmaDigital.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String randonID = request.getParameter("randonid");
		String nom_arch = request.getParameter("nom_arch");
		String archivo = "";
		try {
			CipherWord cipher = new CipherWord();
			archivo = cipher.decrypt(nom_arch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Preparando los parametros para realizar la firma Digital");
		
		String pagina = "http://192.168.5.172:8080/SignnetSignature/firmaAppletPDF.htm?";		
		String comentario = "";
		String cargo = "";
		String ubicacion = "CONCYTEC";
		String nombreArchivos = archivo;
		String imagen = ""; //"firma.jpg";
		
		String rutaOrigen= Constantes.CarpetaArchivosDesarrolloTemporalOrigen.getNombre();
		String rutaDestino=Constantes.CarpetaArchivosDesarrolloTemporalDestino.getNombre();
		String rutaImagen=Constantes.CarpetaArchivosDesarrolloTemporalImagen.getNombre();
		
	
		
		String razon = "Firma de documento";
		String nombreTag = "";
		String listarArchivos = "1";
		String firmarPfxAlmacen = "1";
		String actiStringDescripcion = "1";
		String posicionFirma = "SD";
		String estiloFirma = "ID";
		String ubicacionPagina = "PP";
		String altoRubrica = "50";
		String anchoRubrica = "160";
		String aplicarImagen = "1";
		String usarPersonalizado = "1";
		String nomarch = randonID;
		String url = pagina + "cargo=" + cargo + 
					"&comentario=" + comentario + 
					"&razon=" + razon + 
					"&ubicacion=" + ubicacion + 
					"&imagen=" + imagen + 
					"&nombreArchivos=" + nombreArchivos + 
					"&rutaOrigen=" + rutaOrigen + 
					"&rutaDestino=" + rutaDestino + 
					"&rutaImagen=" + rutaImagen + 
					"&nombreTag=" + nombreTag + 
					"&listarArchivos=" + listarArchivos + 
					"&firmarPfxAlmacen=" + firmarPfxAlmacen + 
					"&activarDescripcion=" + actiStringDescripcion + 
					"&posicionFirma=" + posicionFirma + 
					"&estiloFirma=" + estiloFirma + 
					"&ubicacionPagina=" + ubicacionPagina + 
					"&altoRubrica=" + altoRubrica + 
					"&anchoRubrica=" + anchoRubrica + 
					"&aplicarImagen=" + aplicarImagen + 
					"&usarPersonalizado=" + usarPersonalizado +
					"&nomarch=" + nomarch;
		
		request.setAttribute("srcIframe", url);
		request.setAttribute("archID", randonID);
		return (mapping.findForward("exitoFirma"));
	}

}