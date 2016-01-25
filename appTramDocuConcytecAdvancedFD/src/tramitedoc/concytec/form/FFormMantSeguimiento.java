package tramitedoc.concytec.form;

import java.util.Collection;

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

public class FFormMantSeguimiento extends ActionForm{

	private String fecha = null;
	private String observacion = null;
	private String hora = null;
	private String asunto_documento = null;
	private String tipo = null;
	
	private String codigo_documento = null;
	private String codigo_expediente = null;
	private String descripcion_tipo = null;
	private String numero_documento = null;
	
	private String codigo_oficina = null;
	private String estado_movimiento = null;
	
	private String destinatario = null;
	private String codigo_motivo = null;
	private String fecha_rpta = null;
	private String sle_fecha_recepcion = null;
	private String sle_fecha_derivacion = null;
	
	private String sle_fecha_inicio = null;
	private String sle_fecha_fin = null;
	private String personas = null;
	
	private String cbo_fecharpta = null;
	
	private String chk_copia = null;
	
	
	private String secuencia_movimiento = null;
	private String codigo_inicia = null;
	private String naturaleza_documento = null;
	
	private String[] cbo_copia = null;
	private String codigo_recepcion = null;
	private FormFile theFile;
	private String doc_resp = null;
	private String procedencia = null;
	
	private String sle_fecha = null;
	private String medio = null;
	private String codigo_tipo = null;
	private String firmadopor = null;
	private String dirigido = null;
	private String usuario_actual = null;
	private String codigo_solicitud = null;
	private String fecha_rpta_rq = null;
	private String estado = null;
	
	
	
	
	
	private Collection seguimientoList;

	public Collection getSeguimientoList() {
		return seguimientoList;
	}


	public void setSeguimientoList(Collection seguimientoList) {
		this.seguimientoList = seguimientoList;
	}
	
	public String getProcedencia() {
		return procedencia;
	}


	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}


	public String getSle_fecha() {
		return sle_fecha;
	}


	public void setSle_fecha(String sle_fecha) {
		this.sle_fecha = sle_fecha;
	}


	public String getMedio() {
		return medio;
	}


	public void setMedio(String medio) {
		this.medio = medio;
	}


	public String getCodigo_tipo() {
		return codigo_tipo;
	}


	public void setCodigo_tipo(String codigo_tipo) {
		this.codigo_tipo = codigo_tipo;
	}


	public String getCodigo_solicitud() {
		return codigo_solicitud;
	}


	public void setCodigo_solicitud(String codigo_solicitud) {
		this.codigo_solicitud = codigo_solicitud;
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


	public String getUsuario_actual() {
		return usuario_actual;
	}


	public void setUsuario_actual(String usuario_actual) {
		this.usuario_actual = usuario_actual;
	}

	//private String[] cmb_selclases= null;
	
	
	public String getFecha_rpta_rq() {
		return fecha_rpta_rq;
	}


	public void setFecha_rpta_rq(String fecha_rpta_rq) {
		this.fecha_rpta_rq = fecha_rpta_rq;
	}


	public FormFile getTheFile() {
		return theFile;
	}


	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}


	public String getDoc_resp() {
		return doc_resp;
	}


	public void setDoc_resp(String doc_resp) {
		this.doc_resp = doc_resp;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {
			this.tipo = null;
			this.fecha = null;
			this.observacion = null;
			this.hora = null;
			this.codigo_documento = null;
			this.codigo_expediente = null;
			this.sle_fecha = null;
			this.sle_fecha_inicio = null;
			this.sle_fecha_fin = null;
			this.descripcion_tipo = null;
			this.numero_documento = null;
			this.codigo_oficina = null;
			this.estado_movimiento = null;
			
			this.destinatario = null;
			this.codigo_motivo = null;
			this.fecha_rpta = null;
			this.personas = null;
			this.cbo_fecharpta = null;
			this.chk_copia = null;
			this.cbo_copia = null;
			
			
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


	public String getCodigo_motivo() {
		return codigo_motivo;
	}


	public void setCodigo_motivo(String codigo_motivo) {
		this.codigo_motivo = codigo_motivo;
	}


	public String getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}


	public String getFecha_rpta() {
		return fecha_rpta;
	}


	public void setFecha_rpta(String fecha_rpta) {
		this.fecha_rpta = fecha_rpta;
	}


	


	public String getCbo_fecharpta() {
		return cbo_fecharpta;
	}


	public void setCbo_fecharpta(String cbo_fecharpta) {
		this.cbo_fecharpta = cbo_fecharpta;
	}


	public String getChk_copia() {
		return chk_copia;
	}


	public void setChk_copia(String chk_copia) {
		this.chk_copia = chk_copia;
	}


	public String getPersonas() {
		return personas;
	}


	public void setPersonas(String personas) {
		this.personas = personas;
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


	public String getSecuencia_movimiento() {
		return secuencia_movimiento;
	}


	public void setSecuencia_movimiento(String secuencia_movimiento) {
		this.secuencia_movimiento = secuencia_movimiento;
	}


	public String[] getCbo_copia() {
		return cbo_copia;
	}


	public void setCbo_copia(String[] cbo_copia) {
		this.cbo_copia = cbo_copia;
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


	public String getSle_fecha_recepcion() {
		return sle_fecha_recepcion;
	}


	public void setSle_fecha_recepcion(String sle_fecha_recepcion) {
		this.sle_fecha_recepcion = sle_fecha_recepcion;
	}


	public String getSle_fecha_derivacion() {
		return sle_fecha_derivacion;
	}


	public void setSle_fecha_derivacion(String sle_fecha_derivacion) {
		this.sle_fecha_derivacion = sle_fecha_derivacion;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getSle_fecha_inicio() {
		return sle_fecha_inicio;
	}


	public void setSle_fecha_inicio(String sle_fecha_inicio) {
		this.sle_fecha_inicio = sle_fecha_inicio;
	}


	public String getSle_fecha_fin() {
		return sle_fecha_fin;
	}


	public void setSle_fecha_fin(String sle_fecha_fin) {
		this.sle_fecha_fin = sle_fecha_fin;
	}
	
	
}
