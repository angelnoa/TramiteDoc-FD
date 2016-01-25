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

public class FFormMantDocumento extends ActionForm{

	private String codigo_personal = null;
	private String nombreapellido_usuario = null;
	private String cargo_personal = null;
	private String c_usuario = null;
	private String oficina = null;
	private String tipo = null;
	private String es_responsable = null;
	private Integer tipo_firma = 0;
	private String validacion = null;
	
	private String codigoUsuarioBuscar;
	private String sede = null;
	
	/**
	 * @return
	 */
	
	 public void reset(ActionMapping mapping, HttpServletRequest request) {
		 	this.tipo = null;
		 	this.codigo_personal = null;
		 	this.c_usuario = null;
		 	this.nombreapellido_usuario = null;
			this.cargo_personal = null;
			this.oficina = null;
			this.es_responsable = null;
			this.tipo_firma = 0;
			this.sede = null;
		    }
	 
	public String getC_usuario() {
		return c_usuario;
	}
	public void setC_usuario(String c_usuario) {
		this.c_usuario = c_usuario;
	}
	public String getCargo_personal() {
		return cargo_personal;
	}
	public void setCargo_personal(String cargo_personal) {
		this.cargo_personal = cargo_personal;
	}
	public String getCodigo_personal() {
		return codigo_personal;
	}
	public void setCodigo_personal(String codigo_personal) {
		this.codigo_personal = codigo_personal;
	}
	public String getNombreapellido_usuario() {
		return nombreapellido_usuario;
	}
	public void setNombreapellido_usuario(String nombreapellido_usuario) {
		this.nombreapellido_usuario = nombreapellido_usuario;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEs_responsable() {
		return es_responsable;
	}
	public void setEs_responsable(String es_responsable) {
		this.es_responsable = es_responsable;
	}
	public void setTipo_firma(Integer tipo_firma) {
		this.tipo_firma = tipo_firma;
	}
	public Integer getTipo_firma() {
		return tipo_firma;
	}

	public String getValidacion() {
		return validacion;
	}

	public void setValidacion(String validacion) {
		this.validacion = validacion;
	}

	public String getCodigoUsuarioBuscar() {
		return codigoUsuarioBuscar;
	}

	public void setCodigoUsuarioBuscar(String codigoUsuarioBuscar) {
		this.codigoUsuarioBuscar = codigoUsuarioBuscar;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}
	 
	
	

}