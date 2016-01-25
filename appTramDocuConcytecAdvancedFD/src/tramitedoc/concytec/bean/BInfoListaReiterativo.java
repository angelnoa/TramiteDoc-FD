package tramitedoc.concytec.bean;

import tramitedoc.concytec.util.Bean;

public class BInfoListaReiterativo extends Bean{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1717990262897383675L;
	private int codigo_documento_interno = 0;
	private int estado_documento_interno = 0;
	private int codigo_documento_interno_origen = 0;
	
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
	public void setCodigo_documento_interno_origen(
			int codigo_documento_interno_origen) {
		this.codigo_documento_interno_origen = codigo_documento_interno_origen;
	}
	public int getCodigo_documento_interno_origen() {
		return codigo_documento_interno_origen;
	}

}
