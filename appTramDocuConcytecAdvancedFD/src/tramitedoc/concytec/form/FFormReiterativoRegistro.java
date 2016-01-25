package tramitedoc.concytec.form;

import java.util.Collection;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FFormReiterativoRegistro extends ActionForm{

	private String opcion = null;
	private String fecha = null;
	private String observacion = null;
	private String hora = null;
	
	private String tipo = null;
	
	private String codigo_documento = null;
	private int codigo_expediente = 0;
	private String descripcion_tipo = null;
	private String numero_documento = null;
	private String codigo_oficina = null;
	private String estado_movimiento = null;
	private String destinatario = null;
	private String codigo_motivo = null;
	private String fecha_rpta = null;
	private String personas = null;
	private String cbo_fecharpta = null;
	
	private String chk_copia = null;
	private String[] cbo_copia = null;
	
	private String secuencia_movimiento = null;
	private String codigo_inicia = null;
	private String naturaleza_documento = null;
	

	private int codigo_recepcion = 0;
	private FormFile theFile;
	private String doc_resp = null;
	private String fecha_rpta_rq = null;
	private String codigo_documento_env = null;
	private String codigo_expediente_env = null;
	private String codigocontador = null;
	private String codigo_documento_busqueda = null;
	private String codigo_expediente_busqueda = null;
	
	private String observa_documento = null;
	private String procedencia;	
	private int cantidadArchivos=0;
	
	private String chk_copia_other = null;
	private String[] cbo_copia_other = null;
	
	public int getCantidadArchivos() {
		return cantidadArchivos;
	}
	public void setCantidadArchivos(int cantidadArchivos) {
		this.cantidadArchivos = cantidadArchivos;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getHora() {
		return hora;
	}
	
	
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	public void setHora(String hora) {
		this.hora = hora;
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
	public int getCodigo_expediente() {
		return codigo_expediente;
	}
	public void setCodigo_expediente(int codigo_expediente) {
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
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getCodigo_motivo() {
		return codigo_motivo;
	}
	public void setCodigo_motivo(String codigo_motivo) {
		this.codigo_motivo = codigo_motivo;
	}
	public String getFecha_rpta() {
		return fecha_rpta;
	}
	public void setFecha_rpta(String fecha_rpta) {
		this.fecha_rpta = fecha_rpta;
	}
	public String getPersonas() {
		return personas;
	}
	public void setPersonas(String personas) {
		this.personas = personas;
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
	public String[] getCbo_copia() {
		return cbo_copia;
	}
	public void setCbo_copia(String[] cbo_copia) {
		this.cbo_copia = cbo_copia;
	}
	public int getCodigo_recepcion() {
		return codigo_recepcion;
	}
	public void setCodigo_recepcion(int codigo_recepcion) {
		this.codigo_recepcion = codigo_recepcion;
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
	public String getFecha_rpta_rq() {
		return fecha_rpta_rq;
	}
	public void setFecha_rpta_rq(String fecha_rpta_rq) {
		this.fecha_rpta_rq = fecha_rpta_rq;
	}
	public String getCodigo_documento_env() {
		return codigo_documento_env;
	}
	public void setCodigo_documento_env(String codigo_documento_env) {
		this.codigo_documento_env = codigo_documento_env;
	}
	public String getCodigo_expediente_env() {
		return codigo_expediente_env;
	}
	public void setCodigo_expediente_env(String codigo_expediente_env) {
		this.codigo_expediente_env = codigo_expediente_env;
	}
	public String getCodigocontador() {
		return codigocontador;
	}
	public void setCodigocontador(String codigocontador) {
		this.codigocontador = codigocontador;
	}
	public String getCodigo_documento_busqueda() {
		return codigo_documento_busqueda;
	}
	public void setCodigo_documento_busqueda(String codigo_documento_busqueda) {
		this.codigo_documento_busqueda = codigo_documento_busqueda;
	}
	public String getCodigo_expediente_busqueda() {
		return codigo_expediente_busqueda;
	}
	public void setCodigo_expediente_busqueda(String codigo_expediente_busqueda) {
		this.codigo_expediente_busqueda = codigo_expediente_busqueda;
	}
	public String getObserva_documento() {
		return observa_documento;
	}
	public void setObserva_documento(String observa_documento) {
		this.observa_documento = observa_documento;
	}
	public String getProcedencia() {
		return procedencia;
	}
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	public String getChk_copia_other() {
		return chk_copia_other;
	}
	public void setChk_copia_other(String chk_copia_other) {
		this.chk_copia_other = chk_copia_other;
	}
	public String[] getCbo_copia_other() {
		return cbo_copia_other;
	}
	public void setCbo_copia_other(String[] cbo_copia_other) {
		this.cbo_copia_other = cbo_copia_other;
	}
	
	


}
