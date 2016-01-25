package tramitedoc.concytec.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Mapeo de datos del logeo recibidos del jsp  
 */

public class FFormMantDocumentosArchivar extends ActionForm{
	
	private String sle_observacion = null;

	public String getSle_observacion() {
		return sle_observacion;
	}

	public void setSle_observacion(String sle_observacion) {
		this.sle_observacion = sle_observacion;
	}

}
