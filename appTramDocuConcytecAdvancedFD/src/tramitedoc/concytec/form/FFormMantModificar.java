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

public class FFormMantModificar extends ActionForm{
	
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
	
	private String codigo_recepcion = null;
	private String asunto_documento = null;
	private String observa_movimiento = null;
	private String fecha_registro = null;
	private String hora_registro = null;
	private String procedencia = null;
	private String firmadopor = null;
	private String dirigido = null;
	private String medio = null;
	private String codigo_solicitud = null;
	private String observa_documento = null;
	
	
	
	 public String getObserva_documento() {
		return observa_documento;
	}


	public void setObserva_documento(String observa_documento) {
		this.observa_documento = observa_documento;
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


	public String getObserva_movimiento() {
		return observa_movimiento;
	}


	public void setObserva_movimiento(String observa_movimiento) {
		this.observa_movimiento = observa_movimiento;
	}


	public String getFecha_registro() {
		return fecha_registro;
	}


	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}


	public String getHora_registro() {
		return hora_registro;
	}


	public void setHora_registro(String hora_registro) {
		this.hora_registro = hora_registro;
	}


	public String getProcedencia() {
		return procedencia;
	}


	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}


	public String getFirmadopor() {
		return firmadopor;
	}


	public void setFirmadopor(String firmadopor) {
		this.firmadopor = firmadopor;
	}


	public String getDirigido() {
		return dirigido;
	}


	public void setDirigido(String dirigido) {
		this.dirigido = dirigido;
	}


	public String getMedio() {
		return medio;
	}


	public void setMedio(String medio) {
		this.medio = medio;
	}


	public String getCodigo_solicitud() {
		return codigo_solicitud;
	}


	public void setCodigo_solicitud(String codigo_solicitud) {
		this.codigo_solicitud = codigo_solicitud;
	}


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
	
	
}
