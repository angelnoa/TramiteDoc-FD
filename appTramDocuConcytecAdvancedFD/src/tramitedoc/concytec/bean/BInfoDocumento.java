package tramitedoc.concytec.bean;

import tramitedoc.concytec.util.Bean;

public class BInfoDocumento extends Bean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4500531132195555012L;
	private int codigo_documento_interno = 0;
	private int estado_documento_interno = 0;
	private int codigo_tipo_documento_interno = 0;
	private int numero_doc = 0;
	private boolean isInterno = false;
	private String tipo_envio;
	private String observacion ="";
	private int codigo_motivo=0;
	
	public void setCodigo_documento_interno(int codigo_documento_interno) {
		this.codigo_documento_interno = codigo_documento_interno;
	}
	public int getCodigo_documento_interno() {
		return codigo_documento_interno;
	}
	public void setEstado_documento_interno(int estado_documento_interno) {
		this.estado_documento_interno = estado_documento_interno;
	}
	public int getEstado_documento_interno() {
		return estado_documento_interno;
	}
	public void setInterno(boolean isInterno) {
		this.isInterno = isInterno;
	}
	public boolean isInterno() {
		return isInterno;
	}
	public void setTipo_envio(String tipo_envio) {
		this.tipo_envio = tipo_envio;
	}
	public String getTipo_envio() {
		return tipo_envio;
	}
	public void setCodigo_tipo_documento_interno(
			int codigo_tipo_documento_interno) {
		this.codigo_tipo_documento_interno = codigo_tipo_documento_interno;
	}
	public int getCodigo_tipo_documento_interno() {
		return codigo_tipo_documento_interno;
	}
	public void setNumero_doc(int numero_doc) {
		this.numero_doc = numero_doc;
	}
	public int getNumero_doc() {
		return numero_doc;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setCodigo_motivo(int codigo_motivo) {
		this.codigo_motivo = codigo_motivo;
	}
	public int getCodigo_motivo() {
		return codigo_motivo;
	}
	
}
