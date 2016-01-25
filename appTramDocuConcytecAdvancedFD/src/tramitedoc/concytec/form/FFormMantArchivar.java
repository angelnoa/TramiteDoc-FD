package tramitedoc.concytec.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Mapeo de datos del logeo recibidos del jsp  
 */

public class FFormMantArchivar extends ActionForm{
	
	private String fecha = null;
	private String observacion = null;
	private String hora = null;
	
	private String tipo = null;
	
	private String codigo_documento = null;
	private String codigo_expediente = null;
	private String descripcion_tipo = null;
	private String numero_documento = null;
	
	private String codigo_oficina = null;
	private String estado_movimiento = null;
	private String secuencia_movimiento = null;
	
	private String codigo_inicia = null;
	private String naturaleza_documento = null;
	private FormFile theFile;
	
	 public void reset(ActionMapping mapping, HttpServletRequest request) {
			this.tipo = null;
			this.fecha = null;
			this.observacion = null;
			this.hora = null;
			this.codigo_documento = null;
			this.codigo_expediente = null;
			
			this.descripcion_tipo = null;
			this.numero_documento = null;
			this.codigo_oficina = null;
			this.estado_movimiento = null;
			this.secuencia_movimiento = null;
			this.codigo_inicia = null;
			this.naturaleza_documento = null;
			
		    }
	 
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCodigo_documento() {
		return codigo_documento;
	}
	public void setCodigo_documento(String codigo_documento) {
		this.codigo_documento = codigo_documento;
	}
	public String getCodigo_expediente() {
		return codigo_expediente;
	}
	public void setCodigo_expediente(String codigo_expediente) {
		this.codigo_expediente = codigo_expediente;
	}
	public String getDescripcion_tipo() {
		return descripcion_tipo;
	}
	public void setDescripcion_tipo(String descripcion_tipo) {
		this.descripcion_tipo = descripcion_tipo;
	}
	public String getNumero_documento() {
		return numero_documento;
	}
	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}
	public String getCodigo_oficina() {
		return codigo_oficina;
	}
	public void setCodigo_oficina(String codigo_oficina) {
		this.codigo_oficina = codigo_oficina;
	}
	public String getEstado_movimiento() {
		return estado_movimiento;
	}
	public void setEstado_movimiento(String estado_movimiento) {
		this.estado_movimiento = estado_movimiento;
	}


	public String getSecuencia_movimiento() {
		return secuencia_movimiento;
	}


	public void setSecuencia_movimiento(String secuencia_movimiento) {
		this.secuencia_movimiento = secuencia_movimiento;
	}


	public String getCodigo_inicia() {
		return codigo_inicia;
	}


	public void setCodigo_inicia(String codigo_inicia) {
		this.codigo_inicia = codigo_inicia;
	}


	public String getNaturaleza_documento() {
		return naturaleza_documento;
	}


	public void setNaturaleza_documento(String naturaleza_documento) {
		this.naturaleza_documento = naturaleza_documento;
	}


	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}


	public FormFile getTheFile() {
		return theFile;
	}
	
	
}
