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

public class FFormMantPreliquidar extends ActionForm{
	
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
	private String codigo_recepcion = null;
	private String asunto_documento = null;
	
	private String codigo_tipo = null;
	private String cod_ruta = null;
	private String cod_modalidad = null;
	private String descripcion = null;
	private String cod_oficina_rem = null;
	
	
	 


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
			this.codigo_recepcion = null;
			this.asunto_documento = null;
			
			this.codigo_tipo = null;
			this.cod_ruta = null;
			this.cod_modalidad = null;
			this.cod_modalidad = null;
			this.cod_oficina_rem = null;
		    }
	 
	public String getCod_oficina_rem() {
		return cod_oficina_rem;
	}

	public void setCod_oficina_rem(String cod_oficina_rem) {
		this.cod_oficina_rem = cod_oficina_rem;
	}
	
	public String getCodigo_tipo() {
		return codigo_tipo;
	}


	public void setCodigo_tipo(String codigo_tipo) {
		this.codigo_tipo = codigo_tipo;
	}


	public String getCod_ruta() {
		return cod_ruta;
	}


	public void setCod_ruta(String cod_ruta) {
		this.cod_ruta = cod_ruta;
	}


	public String getCod_modalidad() {
		return cod_modalidad;
	}


	public void setCod_modalidad(String cod_modalidad) {
		this.cod_modalidad = cod_modalidad;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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


	public String getCodigo_recepcion() {
		return codigo_recepcion;
	}


	public void setCodigo_recepcion(String codigo_recepcion) {
		this.codigo_recepcion = codigo_recepcion;
	}


	public String getAsunto_documento() {
		return asunto_documento;
	}


	public void setAsunto_documento(String asunto_documento) {
		this.asunto_documento = asunto_documento;
	}
	
	
}
