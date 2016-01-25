package tramitedoc.concytec.bean;

import tramitedoc.concytec.util.Bean;

public class BPojo extends Bean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private int codigo_recepcion=0;
	private int id_cab_upload=0;
	public void setCodigo_recepcion(int codigo_recepcion) {
		this.codigo_recepcion = codigo_recepcion;
	}
	public int getCodigo_recepcion() {
		return codigo_recepcion;
	}
	public void setId_cab_upload(int id_cab_upload) {
		this.id_cab_upload = id_cab_upload;
	}
	public int getId_cab_upload() {
		return id_cab_upload;
	}
	
	
	
}