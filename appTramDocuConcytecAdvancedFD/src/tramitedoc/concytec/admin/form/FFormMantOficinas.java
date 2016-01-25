package tramitedoc.concytec.admin.form;

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

public class FFormMantOficinas extends ActionForm{
	
	private String tipo = null;
	private String codigo_oficina = null;
	private String descripcion_oficina = null;
	private String nombre_corto = null;
	private String siglas_oficina = null;
	private String es_activo = null;
	private String sede = null;
	
	private String oficina = null;
	/**
	 * @return
	 */
	
	 public void reset(ActionMapping mapping, HttpServletRequest request) {
			this.tipo = null;
			this.codigo_oficina = null;
			this.descripcion_oficina = null;
			this.nombre_corto = null;
			this.siglas_oficina = null;
			this.es_activo = null;
			
			
		    }
	 
	public String getCodigo_oficina() {
		return codigo_oficina;
	}
	public void setCodigo_oficina(String codigo_oficina) {
		this.codigo_oficina = codigo_oficina;
	}
	public String getDescripcion_oficina() {
		return descripcion_oficina;
	}
	public void setDescripcion_oficina(String descripcion_oficina) {
		this.descripcion_oficina = descripcion_oficina;
	}
	public String getEs_activo() {
		return es_activo;
	}
	public void setEs_activo(String es_activo) {
		this.es_activo = es_activo;
	}
	public String getNombre_corto() {
		return nombre_corto;
	}
	public void setNombre_corto(String nombre_corto) {
		this.nombre_corto = nombre_corto;
	}
	public String getSiglas_oficina() {
		return siglas_oficina;
	}
	public void setSiglas_oficina(String siglas_oficina) {
		this.siglas_oficina = siglas_oficina;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	 
	

}