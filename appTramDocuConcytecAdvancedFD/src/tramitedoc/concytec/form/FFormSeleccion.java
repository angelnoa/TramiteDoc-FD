package tramitedoc.concytec.form;

import org.apache.struts.action.ActionForm;


/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Mapeo de datos del logeo recibidos del jsp  
 */

public class FFormSeleccion extends ActionForm{
	
	private String rb_seleccion = null;

	public String getRb_seleccion() {
		return rb_seleccion;
	}

	public void setRb_seleccion(String rb_seleccion) {
		this.rb_seleccion = rb_seleccion;
	}
	

}
